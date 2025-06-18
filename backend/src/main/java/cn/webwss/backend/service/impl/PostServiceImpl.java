package cn.webwss.backend.service.impl;

import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.TalentMapper;
import cn.webwss.backend.model.dto.post.PostEditDTO;
import cn.webwss.backend.model.entity.Talent;
import cn.webwss.backend.service.PostService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webwss.backend.model.entity.Post;
import cn.webwss.backend.mapper.PostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author admin
* @description 针对表【post】的数据库操作Service实现
* @createDate 2025-06-16 10:39:29
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService {

    @Resource
    private TalentMapper talentMapper;

    @Override
    public boolean addPost(String postName) {
        if (postName == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (postName.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "岗位名称不能为空");
        }
        if (postName.length() > 10) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "岗位名称过长");
        }
        Post post = new Post();
        post.setPostName(postName);
        boolean save = save(post);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "岗位添加失败");
        }
        return true;
    }

    @Override
    public Boolean deletePost(Integer postId) {
        if (postId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
//        查询Talent中是否有该岗位
        QueryWrapper<Talent> talentQueryWrapper = new QueryWrapper<>();
        talentQueryWrapper.eq("post_id", postId);
        Long l = talentMapper.selectCount(talentQueryWrapper);
        if (l > 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "该岗位有人才使用，请勿删除");
        }
        boolean removeById = removeById(postId);
        if (!removeById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "岗位删除失败");
        }
        return true;
    }

    @Override
    public boolean editPost(PostEditDTO postEditDTO) {
        Integer postId = postEditDTO.getPostId();
        String postName = postEditDTO.getPostName();
        if (postId == null || postName == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        Post post = new Post();
        post.setPostId(postId);
        post.setPostName(postName);
        boolean b = updateById(post);
        if (!b) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "修改失败");
        }
        return true;
    }
}




