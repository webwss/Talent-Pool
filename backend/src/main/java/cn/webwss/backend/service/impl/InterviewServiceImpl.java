package cn.webwss.backend.service.impl;

import cn.webwss.backend.service.InterviewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webwss.backend.model.entity.Interview;
import cn.webwss.backend.mapper.InterviewMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【interview】的数据库操作Service实现
* @createDate 2025-06-16 10:39:29
*/
@Service
public class InterviewServiceImpl extends ServiceImpl<InterviewMapper, Interview>
    implements InterviewService {

}




