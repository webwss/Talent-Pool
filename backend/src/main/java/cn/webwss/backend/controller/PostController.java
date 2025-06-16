package cn.webwss.backend.controller;

import cn.webwss.backend.common.BaseResponse;
import cn.webwss.backend.common.ResultUtils;
import cn.webwss.backend.model.dto.post.PostEditDTO;
import cn.webwss.backend.model.entity.Post;
import cn.webwss.backend.service.PostService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private PostService postService;

    /**
     * 获取岗位列表
     */
    @GetMapping("/list")
    public BaseResponse<List<Post>> getList() {
        List<Post> list = postService.list();
        return ResultUtils.success(list);
    }

    /**
     * 获取岗位信息
     */
    @GetMapping("/get")
    public BaseResponse<Post> getPost(@RequestParam("postId") Integer postId) {
        Post post = postService.getById(postId);
        return ResultUtils.success(post);
    }


    /**
     * 新增岗位信息
     */
    @PostMapping("/add")
    public BaseResponse<Boolean> addPost(@RequestParam("postName") String postName) {
        boolean result = postService.addPost(postName);
        return ResultUtils.success(result);
    }


    /**
     * 编辑岗位信息
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> editPost(@RequestBody PostEditDTO postEditDTO) {
        boolean result = postService.editPost(postEditDTO);
        return ResultUtils.success(result);
    }



    /**
     * 删除岗位信息
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deletePost(@RequestParam("postId") Integer postId) {
        Boolean result = postService.deletePost(postId);
        return ResultUtils.success(result);
    }


}
