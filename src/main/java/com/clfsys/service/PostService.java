package com.clfsys.service;

import com.clfsys.pojo.Post;
import com.clfsys.pojo.page.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 2:45
 */
public interface PostService {

    public List<Post> showAllPost(int sectionId);

    public void writePost(Post post);

    public List<Post> showPostBySnT (int sectionId,int themeId,int pageNum,int pageSize);

    public int postNum(int sectionId,int themeId);

    public int postNumByS(int sectionId);

    public List<Post> showPostByS (int sectionId,int pageNum,int pageSize);

    public int getPostNum();

    public int getPostNumByDate();

    public int getPostNumBySection(int sectionId);

    public Post getNewestPost(int sectionId);

    public List<HotPost> getHostPost();

    public CommentTitle getCommentTitle(int postId);

    public List<SearchPost> getSearchPost(String searchStr,int pageNum,int pageSize);

    public int getSearchNum(String searchStr);

    public List<ShowPost> getUserPosts(int userId, int pageNum, int pageSize);

    public int getUserPostsNum(int userId);

    public void delPost(int postId);

    public List<AdminPost> getAllpost(int page,int limit);

    public List<AdminPost> getSearchPostAdmin(SearchPostAdmin searchPostAdmin);

    public void setTop(int postId);

    public void setElite(int postId);

    public void cancelTop(int postId);

    public void cancelElite(int postId);

    public void addPostCommentNumber(int postId);

    public void addPostWatchNumber(int postId);

    public List<AdminPost> getAllpostModerator( int page,int limit, int sectionId);
}
