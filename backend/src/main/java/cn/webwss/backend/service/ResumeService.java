package cn.webwss.backend.service;

import cn.webwss.backend.model.dto.resume.ResumeAddDTO;
import cn.webwss.backend.model.entity.Resume;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* @author admin
* @description 针对表【resume】的数据库操作Service
* @createDate 2025-06-16 10:39:29
*/
public interface ResumeService extends IService<Resume> {

    /**
     * 新增简历
     * @param resumeAddDTO
     * @return
     */
    Boolean addResume(ResumeAddDTO resumeAddDTO);


    /**
     * 获取简历列表
     * @param talentId
     * @return
     */
    List<Resume> getList(Integer talentId);



    /**
     * 删除简历
     * @param resumeId
     * @return
     */
    Boolean deleteResume(Integer resumeId);


    /**
     * 批量新增简历
     * @param talentId
     * @param resume
     * @return
     */
    Boolean addResumes(Integer talentId, MultipartFile[] resume) throws IOException;



}
