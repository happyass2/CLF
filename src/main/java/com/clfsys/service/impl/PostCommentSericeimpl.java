package com.clfsys.service.impl;

import com.clfsys.dao.PostCommentMapper;
import com.clfsys.pojo.Post;
import com.clfsys.pojo.PostComment;
import com.clfsys.pojo.page.CommentPage;
import com.clfsys.pojo.page.ShowUserComment;
import com.clfsys.service.PostCommentService;
import com.clfsys.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 14:32
 */
@Service
public class PostCommentSericeimpl implements PostCommentService {

    @Autowired
    PostCommentMapper postCommentMapper;

    @Override
    public void addPostComment(PostComment postComment) {
        postCommentMapper.addPostComment(postComment);
    }

    @Override
    public List<PostComment> getPostComment(int postId, int pageNum, int pageSize) {
        return postCommentMapper.getPostComment(postId,pageNum,pageSize);
    }

    @Override
    public List<CommentPage> getPostCommentPage(int postId, int pageNum, int pageSize) {
        return postCommentMapper.getPostCommentPage(postId,pageNum,pageSize);
    }

    @Override
    public int getCommentNum(int postId) {
        return postCommentMapper.getCommentNum(postId);
    }

    @Override
    public int getCommentFloor(int postId) {
        return postCommentMapper.getCommentFloor(postId);
    }

    @Override
    public List<ShowUserComment> getUserComment(int userId, int pageNum, int pageSize) {
        return postCommentMapper.getUserComment(userId,pageNum,pageSize);
    }

    @Override
    public int getUserCommentNum(int userId) {
        return postCommentMapper.getUserCommentNum(userId);
    }

    @Override
    public List<ShowUserComment> getMyPostComment(int userId, int pageNum, int pageSize) {
        return postCommentMapper.getMyPostComment(userId,pageNum,pageSize);
    }

    @Override
    public int getMyPostCommentNum(int userId) {
        return postCommentMapper.getMyPostCommentNum(userId);
    }

    @Override
    public void delComment(int commentId) {
        postCommentMapper.delComment(commentId);
    }

    @Override
    public List<CommentPage> getSearchComment(int postId, int commentorId, String commentContent) {
        return postCommentMapper.getSearchComment(postId,commentorId,commentContent);
    }


}
