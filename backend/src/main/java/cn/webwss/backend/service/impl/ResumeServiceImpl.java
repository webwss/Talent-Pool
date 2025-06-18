package cn.webwss.backend.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.ResumeRequestionMapper;
import cn.webwss.backend.model.dto.resume.ResumeAddDTO;
import cn.webwss.backend.model.entity.ResumeRequestion;
import cn.webwss.backend.utils.QiniuUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webwss.backend.model.entity.Resume;
import cn.webwss.backend.service.ResumeService;
import cn.webwss.backend.mapper.ResumeMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
* @author admin
* @description 针对表【resume】的数据库操作Service实现
* @createDate 2025-06-16 10:39:29
*/
@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume>
    implements ResumeService{

    @Resource
    private QiniuUtil qiniuUtil;

    @Resource
    private ResumeRequestionMapper resumeRequestionMapper;

    /**
     * 新增简历
     * @param resumeAddDTO
     * @return
     */
    @Override
    public Boolean addResume(ResumeAddDTO resumeAddDTO) {
        Integer talentId = resumeAddDTO.getTalentId();
        String resumeUrl = resumeAddDTO.getResumeUrl();
        if (talentId == null || resumeUrl == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Resume resume = new Resume();
        resume.setTalentId(talentId);
        resume.setResumeUrl(resumeUrl);
        boolean save = save(resume);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "简历添加失败");
        }
        return true;
    }

    /**
     * 根据人才id获取简历列表
     * @param talentId
     * @return
     */
    @Override
    public List<Resume> getList(Integer talentId) {
        if (talentId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Resume> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("talentId", talentId);
        return list(queryWrapper);
    }

    /**
     * 删除简历
     * @param resumeId
     * @return
     */
    @Override
    public Boolean deleteResume(Integer resumeId) {
        if (resumeId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
//        查询图片地址
        Resume resume = getById(resumeId);
        String fileName = resume.getResumeUrl().substring(resume.getResumeUrl().lastIndexOf("/") + 1);
        qiniuUtil.deleteFile(fileName, "resume/");
//        删除数据库数据
        boolean removeById = removeById(resumeId);
        if (!removeById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败");
        }
//        删除简历面试题
        QueryWrapper<ResumeRequestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("resume_id", resumeId);
        int delete = resumeRequestionMapper.delete(queryWrapper);
        if (delete < 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "删除失败");
        }
        return true;
    }


    /**
     * 批量新增简历
     * @param talentId
     * @param resume
     * @return
     */
    @Override
    public Boolean addResumes(Integer talentId, MultipartFile[] resume) throws IOException {
        if (talentId == null || resume == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        for (MultipartFile multipartFile : resume) {
            String originalFilename = RandomUtil.randomString(cn.hutool.core.lang.UUID.fastUUID().toString().replace("-", ""), 8) + multipartFile.getOriginalFilename();
            String resumeUrl = qiniuUtil.uploadFile(multipartFile.getBytes(), originalFilename, "resume/");
            ResumeAddDTO resumeAddDTO = new ResumeAddDTO();
            resumeAddDTO.setTalentId(talentId);
            resumeAddDTO.setResumeUrl("https://" + resumeUrl);
            addResume(resumeAddDTO);
        }
        return true;
    }
}




