package cn.webwss.backend.service.impl;

import cn.webwss.backend.service.PostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webwss.backend.model.entity.Post;
import cn.webwss.backend.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【post】的数据库操作Service实现
* @createDate 2025-06-16 10:39:29
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService {

}




