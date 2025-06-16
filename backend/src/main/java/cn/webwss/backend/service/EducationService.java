package cn.webwss.backend.service;

import cn.webwss.backend.model.dto.education.EducationEditDTO;
import cn.webwss.backend.model.entity.Education;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【education】的数据库操作Service
* @createDate 2025-06-16 10:39:29
*/
public interface EducationService extends IService<Education> {


    /**
     * 添加学历
     * @param educationName
     * @return
     */
    Boolean addEducation(String educationName);


    /**
     * 删除学历
     * @param educationId
     * @return
     */
    Boolean deleteEducation(Integer educationId);


    /**
     * 修改学历
     * @param educationEditDTO
     * @return
     */
    Boolean editEducation(EducationEditDTO educationEditDTO);
}
