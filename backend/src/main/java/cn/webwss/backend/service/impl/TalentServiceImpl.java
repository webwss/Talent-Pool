package cn.webwss.backend.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.*;
import cn.webwss.backend.model.dto.resume.ResumeAddDTO;
import cn.webwss.backend.model.dto.talent.*;
import cn.webwss.backend.model.entity.*;
import cn.webwss.backend.model.vo.talent.TalentVO;
import cn.webwss.backend.service.ResumeRequestionService;
import cn.webwss.backend.service.ResumeService;
import cn.webwss.backend.service.StaffService;
import cn.webwss.backend.service.TalentService;
import cn.webwss.backend.utils.QiniuUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

/**
* @author admin
* @description 针对表【talent】的数据库操作Service实现
* @createDate 2025-05-27 11:11:47
*/
@Service
@Slf4j
public class TalentServiceImpl extends ServiceImpl<TalentMapper, Talent>
    implements TalentService {


    @Resource
    private PostMapper postMapper;
    @Resource
    private EducationMapper educationMapper;
    @Resource
    private StaffMapper staffMapper;
    @Resource
    private InteractMapper interactMapper;
    @Resource
    private InterviewMapper interviewMapper;
    @Resource
    private ResumeMapper resumeMapper;
    @Resource
    private StaffService staffService;
    @Resource
    private ResumeService resumeService;
    @Resource
    private ResumeRequestionService resumeRequestionService;

    @Resource
    private Executor generateQuestionsTaskExecutor;

    @Resource
    private QiniuUtil qiniuUtil;

    /**
     * 条件查询
     * @param talentRequestDTO
     * @return
     */
    @Override
    public QueryWrapper getQueryWrapper(TalentRequestDTO talentRequestDTO, HttpServletRequest request) {
        Staff staff = staffService.checkToken(request);
        Integer staffId = staff.getStaffId(); // 员工id

        String name = talentRequestDTO.getName();
        Integer postId = talentRequestDTO.getPostId();
        Integer educationId = talentRequestDTO.getEducationId();
        String phone = talentRequestDTO.getPhone();
        Integer sex = talentRequestDTO.getSex();
        Integer marriage = talentRequestDTO.getMarriage();
        Integer jobStatus = talentRequestDTO.getJobStatus();
        Integer result = talentRequestDTO.getResult();
        String sortField = talentRequestDTO.getSortField();
        String sortOrder = talentRequestDTO.getSortOrder();
        String searchText = talentRequestDTO.getSearchText();
        Integer delStatus  = talentRequestDTO.getDeleteStatus();

        QueryWrapper<Talent> queryWrapper = new QueryWrapper<>();
        if (staffId != null) {
            queryWrapper.eq("create_staff_id", staffId);
        }
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (postId != null) {
            queryWrapper.eq("post_id", postId);
        }
        if (educationId != null) {
            queryWrapper.eq("education_id", educationId);
        }
        if (StrUtil.isNotBlank(phone)) {
            queryWrapper.like("phone", phone);
        }
        if (sex != null) {
            queryWrapper.eq("sex", sex);
        }
        if (marriage != null) {
            queryWrapper.eq("marriage", marriage);
        }
        if (jobStatus != null) {
            queryWrapper.eq("job_status", jobStatus);
        }
        if (result != null) {
            queryWrapper.eq("result", result);
        }
        if(delStatus != null) {
            queryWrapper.eq("delete_status", delStatus);
        }


        queryWrapper.orderBy(StrUtil.isNotBlank(sortField), sortOrder.equals("ascend"), sortField);
        queryWrapper.orderByDesc("create_time");
        return queryWrapper;

    }

    @Override
    public TalentVO getTalentDetail(Integer talentId) {
        if (talentId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Talent talent = getById(talentId);
        TalentVO talentVo = new TalentVO();
        BeanUtils.copyProperties(talent, talentVo);
//        查询岗位信息
        Post post = postMapper.selectById(talent.getPostId());
//        查询学历信息
        Education education = educationMapper.selectById(talent.getEducationId());
//        查询员工信息
        Staff staff = staffMapper.selectById(talent.getCreateStaffId());
//        获取沟通记录列表
        QueryWrapper<Interact> interactQueryWrapper = new QueryWrapper<>();
        interactQueryWrapper.eq("talent_id", talentId);
        List<Interact> interactList = interactMapper.selectList(interactQueryWrapper);
//        获取面试记录列表
        QueryWrapper<Interview> interviewQueryWrapper = new QueryWrapper<>();
        interviewQueryWrapper.eq("talent_id", talentId);
        List<Interview> interviewList = interviewMapper.selectList(interviewQueryWrapper);
//        获取简历列表
        QueryWrapper<Resume> resumeQueryWrapper = new QueryWrapper<>();
        resumeQueryWrapper.eq("talent_id", talentId);
        List<Resume> resumeList = resumeMapper.selectList(resumeQueryWrapper);
        resumeList.forEach(resume -> {
//            获取相关面试题
            ResumeRequestion resumeRequestion = resumeRequestionService.getQuestions(resume.getResumeId());
            resume.setResumeRequestion(resumeRequestion);
        });

        talentVo.setPost(post); // 设置职位
        talentVo.setEducation(education); // 设置学历
        talentVo.setStaff(staff); // 设置员工
        talentVo.setInteractList(interactList); // 设置沟通记录列表
        talentVo.setInterviewList(interviewList); // 设置面试记录列表
        talentVo.setResumeList(resumeList); // 设置简历列表
        return talentVo;
    }

    /**
     * 添加人才
     * @param talentAddDTO
     * @param request
     * @param photo
     * @param resumes
     * @return
     */
    @Override
    public Boolean addTalent(TalentAddDTO talentAddDTO, HttpServletRequest request, MultipartFile photo, MultipartFile[] resumes) throws IOException {
//        获取当前登录员工
        Staff staff = staffService.checkToken(request);
        String name = talentAddDTO.getName(); // 姓名
        Integer postId = talentAddDTO.getPostId(); // 岗位
        Integer educationId = talentAddDTO.getEducationId(); // 学历
        String phone = talentAddDTO.getPhone(); // 手机号
        Integer sex = talentAddDTO.getSex(); // 性别
        String age = talentAddDTO.getAge(); // 年龄
        Integer marriage = talentAddDTO.getMarriage(); // 婚姻
        String currentSalary = talentAddDTO.getCurrentSalary(); // 当前薪资
        String expectedSalary = talentAddDTO.getExpectedSalary(); // 期望薪资
        Integer jobStatus = talentAddDTO.getJobStatus(); // 工作状态
        Integer jobYear = talentAddDTO.getJobYear(); // 工作年限
        String address = talentAddDTO.getAddress(); // 地址
        String currentPost = talentAddDTO.getCurrentPost(); // 当前岗位
        String reasonForLeaving = talentAddDTO.getReasonForLeaving(); // 离职原因
        String advantage = talentAddDTO.getAdvantage(); // 优点
        String disadvantage = talentAddDTO.getDisadvantage(); // 缺点
        Integer result = talentAddDTO.getResult(); // 招聘结果
        Date nextInterviewTime = talentAddDTO.getNextInterviewTime(); // 下次面试时间
        if (StrUtil.hasBlank(name, phone, currentSalary, expectedSalary)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (postId == null || educationId == null || sex == null || age == null || marriage == null || photo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
//       上传个人照片
        //        获取文件名，拼接随机字符
        String originalFilename = RandomUtil.randomString(cn.hutool.core.lang.UUID.fastUUID().toString().replace("-", ""), 8) + photo.getOriginalFilename();
        String url = qiniuUtil.uploadFile(photo.getBytes(), originalFilename, "photo/");
        Talent talent = new Talent();
        talent.setPhoto("https://" + url);
        talent.setName(name);
        talent.setPostId(postId);
        talent.setEducationId(educationId);
        talent.setPhone(phone);
        talent.setSex(sex);
        talent.setAge(age);
        talent.setMarriage(marriage);
        talent.setCurrentSalary(currentSalary);
        talent.setExpectedSalary(expectedSalary);
        talent.setJobStatus(jobStatus);
        talent.setJobYear(jobYear);
        talent.setAddress(address);
        talent.setCurrentPost(currentPost);
        talent.setReasonForLeaving(reasonForLeaving);
        talent.setAdvantage(advantage);
        talent.setDisadvantage(disadvantage);
        talent.setResult(result);
        talent.setNextInterviewTime(nextInterviewTime);
        talent.setCreateStaffId(staff.getStaffId()); // 设置创建员工id
        boolean save = save(talent);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
//        查询是否有简历照片
        if (resumes != null) {
            ResumeAddDTO resumeAddDTO = new ResumeAddDTO();
            resumeAddDTO.setTalentId(talent.getTalentId());
//        上传简历照片
            for (MultipartFile resume : resumes) {
                String originalFilename1 = RandomUtil.randomString(cn.hutool.core.lang.UUID.fastUUID().toString().replace("-", ""), 8) + resume.getOriginalFilename();
                String resumeUrl = qiniuUtil.uploadFile(resume.getBytes(), originalFilename1, "resume/");
                resumeAddDTO.setResumeUrl("https://" + resumeUrl);
                resumeService.addResume(resumeAddDTO);
                log.info("上传简历照片成功");
            }
        }
        return true;
    }

    /**
     * 编辑人才基础信息
     * @param talentEditBaseInfoDTO
     * @param request
     * @return
     */
    @Override
    public Boolean editBaseInfo(TalentEditBaseInfoDTO talentEditBaseInfoDTO, HttpServletRequest request) {
        Integer talentId = talentEditBaseInfoDTO.getTalentId();
        String name = talentEditBaseInfoDTO.getName();
        Integer postId = talentEditBaseInfoDTO.getPostId();
        Integer educationId = talentEditBaseInfoDTO.getEducationId();
        String phone = talentEditBaseInfoDTO.getPhone();
        Integer sex = talentEditBaseInfoDTO.getSex();
        String age = talentEditBaseInfoDTO.getAge();
        Integer marriage = talentEditBaseInfoDTO.getMarriage();
        String currentSalary = talentEditBaseInfoDTO.getCurrentSalary();
        String expectedSalary = talentEditBaseInfoDTO.getExpectedSalary();
        Integer jobStatus = talentEditBaseInfoDTO.getJobStatus();
        Integer jobYear = talentEditBaseInfoDTO.getJobYear();
        String address = talentEditBaseInfoDTO.getAddress();
        String currentPost = talentEditBaseInfoDTO.getCurrentPost();
        String reasonForLeaving = talentEditBaseInfoDTO.getReasonForLeaving();
        String advantage = talentEditBaseInfoDTO.getAdvantage();
        String disadvantage = talentEditBaseInfoDTO.getDisadvantage();
        Date nextInterviewTime = talentEditBaseInfoDTO.getNextInterviewTime();
        Integer result = talentEditBaseInfoDTO.getResult();
        if (StrUtil.hasBlank(name, phone, currentSalary, expectedSalary)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (postId == null || educationId == null || sex == null || age == null || marriage == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
//        编辑信息
        Talent talent = new Talent();
        talent.setTalentId(talentId);
        talent.setName(name);
        talent.setPostId(postId);
        talent.setEducationId(educationId);
        talent.setPhone(phone);
        talent.setSex(sex);
        talent.setAge(age);
        talent.setMarriage(marriage);
        talent.setCurrentSalary(currentSalary);
        talent.setExpectedSalary(expectedSalary);
        talent.setJobStatus(jobStatus);
        talent.setJobYear(jobYear);
        talent.setAddress(address);
        talent.setCurrentPost(currentPost);
        talent.setReasonForLeaving(reasonForLeaving);
        talent.setAdvantage(advantage);
        talent.setDisadvantage(disadvantage);
        talent.setNextInterviewTime(nextInterviewTime);
        talent.setResult(result);
        boolean update = updateById(talent);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "人才基础信息修改失败");
        }
        updateEditTimeAndSum(talentId);
        return true;
    }


    /**
     * 更新编辑时间和次数
     */
    @Override
    public void updateEditTimeAndSum(Integer talentId) {
        Talent talent = getById(talentId);
        talent.setEditTime(new Date());
        talent.setEditSum(talent.getEditSum() + 1);
        boolean update = updateById(talent);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新编辑时间和次数失败");
        }
    }

    /**
     * 编辑人才照片
     * @param talentId
     * @param photo
     * @param request
     * @return
     */
    @Override
    public Boolean editPhoto(Integer talentId, MultipartFile photo, HttpServletRequest request) throws IOException {
        if (talentId == null || talentId <= 0 || photo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Talent talent = getById(talentId);
        if (talent == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
//        获取原头像地址
        String photoUrl = talent.getPhoto();
//        删除云端图片
        if (photoUrl != null) {
            String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
            qiniuUtil.deleteFile(fileName, "photo/");
        }
//        新增图片至云端并更新头像地址
        String originalFilename = RandomUtil.randomString(cn.hutool.core.lang.UUID.fastUUID().toString().replace("-", ""), 8) + photo.getOriginalFilename();
        String photoUrl1 = qiniuUtil.uploadFile(photo.getBytes(), originalFilename, "photo/");
        talent.setPhoto("https://" + photoUrl1);
        boolean update = updateById(talent);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "人才照片修改失败");
        }
        updateEditTimeAndSum(talentId);
        return true;
    }

    /**
     * 发送删除人才请求
     * @param talentDeleteDTO
     * @return
     */
    @Override
    public Boolean deleteTalent(TalentDeleteDTO talentDeleteDTO) {
        Integer talentId = talentDeleteDTO.getTalentId();
        String delReason = talentDeleteDTO.getDelReason();
        if (talentId == null || talentId <= 0 || StrUtil.hasBlank(delReason)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Talent talent = getById(talentId);
        if (talent == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        talent.setDelStatus(2);
        talent.setDelReason(delReason);
        boolean update = updateById(talent);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "人才删除提交失败");
        }
        return true;
    }

    /**
     * 获取人才删除查询条件
     * @param talentRequestDTO
     * @param request
     * @return
     */
    @Override
    public Wrapper<Talent> getDelQueryWrapper(TalentRequestDTO talentRequestDTO, HttpServletRequest request) {
        Integer delStatus  = talentRequestDTO.getDeleteStatus();
        String sortField = talentRequestDTO.getSortField();
        String sortOrder = talentRequestDTO.getSortOrder();
        if (delStatus == null || delStatus <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Talent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_status", delStatus);
        queryWrapper.orderBy(StrUtil.isNotBlank(sortField), sortOrder.equals("ascend"), sortField);
        queryWrapper.orderByDesc("create_time");
        return queryWrapper;
    }

    /**
     * 同意人才删除
     * @param talentId
     * @return
     */
    @Override
    public Boolean agreeDelete(Integer talentId) {
        if (talentId == null || talentId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Talent talent = getById(talentId);
        if (talent == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
//        删除云端头像图片
        String photoUrl = talent.getPhoto();
        if (photoUrl != null) {
            String fileName = photoUrl.substring(photoUrl.lastIndexOf("/") + 1);
            qiniuUtil.deleteFile(fileName, "photo/");
        }
//        删除数据库人才信息
        removeById(talentId);
//        删除云端简历图片
        List<Resume> resumeList = resumeMapper.selectList(new QueryWrapper<Resume>().eq("talent_id", talentId));
        for (Resume resume : resumeList) {
            String resumeUrl = resume.getResumeUrl();
            if (resumeUrl != null) {
                String fileName = resumeUrl.substring(resumeUrl.lastIndexOf("/") + 1);
                qiniuUtil.deleteFile(fileName, "resume/");
            }
        }
//        删除数据库简历信息
        resumeMapper.delete(new QueryWrapper<Resume>().eq("talent_id", talentId));
//        删除数据库沟通信息
        interactMapper.delete(new QueryWrapper<Interact>().eq("talent_id", talentId));
//        删除数据库面试信息
        interviewMapper.delete(new QueryWrapper<Interview>().eq("talent_id", talentId));
        return true;
    }

    /**
     * 拒绝人才删除
     * @param talentId
     * @return
     */
    @Override
    public Boolean refuseDelete(Integer talentId) {
        if (talentId == null || talentId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Talent talent = getById(talentId);
        if (talent == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        talent.setDelStatus(1);
        boolean update = updateById(talent);
        if (!update) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "人才删除操作失败");
        }
        return true;
    }

    /**
     * 批量添加人才
     * @param file
     * @return
     */
    @Override
    public Boolean addTalentBatch(MultipartFile file, HttpServletRequest request) throws IOException {
//        判断文件格式
        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.endsWith(".xlsx")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件格式错误");
        }
        Staff staff = staffService.checkToken(request);
//        定义一个监听器，用于处理读取到的数据
        ReadListener<TalentAddBatchDTO> listener =  new ReadListener<TalentAddBatchDTO>() {

            private final List<Talent> talentList = new ArrayList<>();

            @Override
            public void invoke(TalentAddBatchDTO talentAddBatchDTO, AnalysisContext analysisContext) {
//                数据校验
                validateTalentDTO(talentAddBatchDTO);
                Talent talent = convertToEntity(talentAddBatchDTO ,staff.getStaffId());
                talentList.add(talent);

//                批量插入，每500条一批
                if (talentList.size() >= 500) {
                    saveBatchAndClear(talentList);
                }

            }

            private void validateTalentDTO(TalentAddBatchDTO talentAddBatchDTO) {
                if (talentAddBatchDTO.getSeq() == null || talentAddBatchDTO.getPostId() == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "序号和岗位不能为空");
                }
                if (talentAddBatchDTO.getName() == null || talentAddBatchDTO.getPhone() == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "姓名和联系电话不能为空");
                }
                if (talentAddBatchDTO.getSex() == null || talentAddBatchDTO.getAge() == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "性别和出生年月不能为空");
                }
                if (talentAddBatchDTO.getMarriage() == null || talentAddBatchDTO.getEducationId() == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "婚育情况和学历不能为空");
                }
                if (talentAddBatchDTO.getCurrentSalary() == null || talentAddBatchDTO.getExpectedSalary() == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "目前薪资和期望薪资不能为空");
                }
                if (talentAddBatchDTO.getJobStatus() == null || talentAddBatchDTO.getCurrentPost() == null){
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "工作状态和目前岗位不能为空");
                }
                if (talentAddBatchDTO.getAddress() == null || talentAddBatchDTO.getReasonForLeaving() == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "目前地址和离职原因不能为空");
                }
                if (talentAddBatchDTO.getAdvantage() == null || talentAddBatchDTO.getDisadvantage() == null) {
                    throw new BusinessException(ErrorCode.PARAMS_ERROR, "优势和劣势不能为空");
                }
            }

            private Talent convertToEntity(TalentAddBatchDTO dto, Integer staffId) {
                Talent talent = new Talent();
                talent.setPostId(dto.getPostId());
                talent.setName(dto.getName());
                talent.setPhone(dto.getPhone());
                talent.setSex(dto.getSex());
                talent.setAge(dto.getAge());
                talent.setMarriage(dto.getMarriage());
                talent.setEducationId(dto.getEducationId());
                talent.setCurrentSalary(dto.getCurrentSalary());
                talent.setExpectedSalary(dto.getExpectedSalary());
                talent.setJobStatus(dto.getJobStatus());
                talent.setCurrentPost(dto.getCurrentPost());
                talent.setAddress(dto.getAddress());
                talent.setReasonForLeaving(dto.getReasonForLeaving());
                talent.setAdvantage(dto.getAdvantage());
                talent.setDisadvantage(dto.getDisadvantage());
                talent.setCreateStaffId(staffId);
                talent.setPhoto("https://jiananpump-img.jiananpump.com/new_jianan_rck/static/photo.png");
                return talent;
            }



            private void saveBatchAndClear(List<Talent> list) {
                boolean success = saveBatch(list);
                if (!success) {
                    throw new BusinessException(ErrorCode.OPERATION_ERROR, "批量保存失败");
                }
                list.clear();
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                if (!talentList.isEmpty()) {
                    saveBatchAndClear(talentList);
                }
                log.info("Excel 导入完成");
            }


        };

//        使用EasyExcel读取文件
        EasyExcel.read(file.getInputStream(), TalentAddBatchDTO.class, listener)
                .sheet()
                .doRead();
        return true;

    }




}




