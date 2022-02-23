package com.clfsys.service;

import com.clfsys.pojo.UserCollection;
import com.clfsys.pojo.page.ShowPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 19:53
 */
public interface UserCollectionService {

    public void addCollection(UserCollection userCollection);

    public int checkCollection(int postId, int collectorId);

    public List<ShowPost> getUserCollection(int userId, int pageNum, int pageSize);

    public int getCollectNum(int userId);

    public void delCollection(int postId,int collectorId);
}
