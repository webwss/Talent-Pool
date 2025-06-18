package cn.webwss.backend.service;

import cn.webwss.backend.model.dto.post.PostEditDTO;
import cn.webwss.backend.model.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【post】的数据库操作Service
* @createDate 2025-06-16 10:39:29
*/
public interface PostService extends IService<Post> {


    /**
     * 添加岗位
     * @param postName
     * @return
     */
    boolean addPost(String postName);


    /**
     * 删除岗位
     * @param postId
     * @return
     */
    Boolean deletePost(Integer postId);


    /**
     * 编辑岗位
     * @param postEditDTO
     * @return
     */
    boolean editPost(PostEditDTO postEditDTO);
}
