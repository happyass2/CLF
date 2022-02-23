package com.clfsys.service;

import com.clfsys.pojo.PostComment;
import com.clfsys.pojo.page.CommentPage;
import com.clfsys.pojo.page.ShowUserComment;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 14:32
 */
public interface PostCommentService {

    public void addPostComment(PostComment postComment);

    public List<PostComment> getPostComment(int postId,int pageNum,int pageSize);

    public List<CommentPage> getPostCommentPage(int postId, int pageNum, int pageSize);

    public int getCommentNum(int postId);

    public int getCommentFloor(int postId);

    public List<ShowUserComment> getUserComment(int userId,  int pageNum,int pageSize);

    public int getUserCommentNum(int userId);

    public List<ShowUserComment> getMyPostComment(int userId,int pageNum,int pageSize);

    public int getMyPostCommentNum(int userId);

    public void delComment(int commentId);

    public List<CommentPage> getSearchComment(int postId, int commentorId,String commentContent);
}
