package cn.webwss.backend.service;

import cn.webwss.backend.model.dto.talent.TalentAddDTO;
import cn.webwss.backend.model.dto.talent.TalentDeleteDTO;
import cn.webwss.backend.model.dto.talent.TalentEditBaseInfoDTO;
import cn.webwss.backend.model.dto.talent.TalentRequestDTO;
import cn.webwss.backend.model.entity.Talent;
import cn.webwss.backend.model.vo.talent.TalentVO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
* @author admin
* @description 针对表【talent】的数据库操作Service
* @createDate 2025-05-27 11:11:48
*/
public interface TalentService extends IService<Talent> {


    /**
     * 条件查询
     */
    QueryWrapper getQueryWrapper(TalentRequestDTO talentRequestDTO, HttpServletRequest request);


    /**
     * 获取人才详情
     * @param talentId
     * @return
     */
    TalentVO getTalentDetail(Integer talentId);

    /**
     * 新增人才
     * @param talentAddDTO
     * @param request
     * @param photo
     * @param resumes
     * @return
     */
    Boolean addTalent(TalentAddDTO talentAddDTO, HttpServletRequest request, MultipartFile photo, MultipartFile[] resumes) throws IOException;


    /**
     * 编辑人才基本信息
     * @param talentEditBaseInfoDTO
     * @param request
     * @return
     */
    Boolean editBaseInfo(TalentEditBaseInfoDTO talentEditBaseInfoDTO, HttpServletRequest request);

    /**
     * 更新人才编辑次数和编辑时间
     * @param talentId
     */
    void updateEditTimeAndSum(Integer talentId);

    /**
     * 编辑人才照片
     * @param talentId
     * @param photo
     * @param request
     * @return
     */
    Boolean editPhoto(Integer talentId, MultipartFile photo, HttpServletRequest request) throws IOException;


    /**
     * 删除人才审核
     * @param talentDeleteDTO
     * @return
     */
    Boolean deleteTalent(TalentDeleteDTO talentDeleteDTO);

    /**
     * 获取人才删除列表
     * @param talentRequestDTO
     * @param request
     * @return
     */
    Wrapper<Talent> getDelQueryWrapper(TalentRequestDTO talentRequestDTO, HttpServletRequest request);

    /**
     * 同意人才删除
     * @param talentId
     * @return
     */
    Boolean agreeDelete(Integer talentId);


    /**
     * 拒绝人才删除
     * @param talentId
     * @return
     */
    Boolean refuseDelete(Integer talentId);

    /**
     * 批量添加人才
     * @param file
     * @return
     */
    Boolean addTalentBatch(MultipartFile file, HttpServletRequest request) throws IOException;



}
