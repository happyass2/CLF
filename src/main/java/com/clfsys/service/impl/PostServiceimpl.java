package com.clfsys.service.impl;

import com.clfsys.dao.PostMapper;
import com.clfsys.pojo.Post;
import com.clfsys.pojo.page.*;
import com.clfsys.service.PostService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 2:45
 */
@Service
public class PostServiceimpl implements PostService {

    @Autowired
    PostMapper postMapper;


    @Override
    public List<Post> showAllPost(int sectionId) {
        return postMapper.showAllPost(sectionId);
    }

    @Override
    public void writePost(Post post) {
        postMapper.writePost(post);
    }

    @Override
    public List<Post> showPostBySnT(int sectionId, int themeId,int pageNum,int pageSize) {

        return postMapper.showPostBySnT(sectionId,themeId, pageNum, pageSize);
    }

    @Override
    public int postNum(int sectionId,int themeId) {
        return postMapper.postNum(sectionId,themeId);
    }

    @Override
    public int postNumByS(int sectionId) {
        return postMapper.postNumByS(sectionId);
    }

    @Override
    public List<Post> showPostByS(int sectionId, int pageNum, int pageSize) {
        return postMapper.showPostByS(sectionId,pageNum,pageSize);
    }

    @Override
    public int getPostNum() {
        return postMapper.getPostNum();
    }

    @Override
    public int getPostNumByDate() {
        return postMapper.getPostNumByDate();
    }

    @Override
    public int getPostNumBySection(int sectionId) {
        return postMapper.getPostNumBySection(sectionId);
    }

    @Override
    public Post getNewestPost(int sectionId) {
        return postMapper.getNewestPost(sectionId);
    }

    @Override
    public List<HotPost> getHostPost() {
        return postMapper.getHostPost();
    }

    @Override
    public CommentTitle getCommentTitle(int postId) {
        return postMapper.getCommentTitle(postId);
    }

    @Override
    public List<SearchPost> getSearchPost(String searchStr,int pageNum,int pageSize) {
        return postMapper.getSearchPost(searchStr,pageNum,pageSize);
    }
    @Override
    public int getSearchNum(String searchStr){
        return postMapper.getSearchNum(searchStr);
    }

    @Override
    public List<ShowPost> getUserPosts(int userId, int pageNum, int pageSize) {
        return postMapper.getUserPosts(userId,pageNum,pageSize);
    }

    @Override
    public int getUserPostsNum(int userId) {
        return postMapper.getUserPostsNum(userId);
    }

    @Override
    public void delPost(int postId) {
        postMapper.delPost(postId);
    }

    @Override
    public List<AdminPost> getAllpost(int page,int limit) {
        return postMapper.getAllpost(page,limit);
    }

    @Override
    public List<AdminPost> getSearchPostAdmin(SearchPostAdmin searchPostAdmin) {
        return postMapper.getSearchPostAdmin(searchPostAdmin);
    }

    @Override
    public void setTop(int postId) {
        postMapper.setTop(postId);
    }

    @Override
    public void setElite(int postId) {
        postMapper.setElite(postId);
    }

    @Override
    public void cancelTop(int postId) {
        postMapper.cancelTop(postId);
    }

    @Override
    public void cancelElite(int postId) {
        postMapper.cancelElite(postId);
    }

    @Override
    public void addPostCommentNumber(int postId) {
        postMapper.addPostCommentNumber(postId);
    }

    @Override
    public void addPostWatchNumber(int postId) {
        postMapper.addPostWatchNumber(postId);
    }

    @Override
    public List<AdminPost> getAllpostModerator(int page, int limit, int sectionId) {
        return postMapper.getAllpostModerator(page,limit,sectionId);
    }


}
