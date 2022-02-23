package com.clfsys.service.impl;

import com.clfsys.dao.UserCollectionMapper;
import com.clfsys.pojo.UserCollection;
import com.clfsys.pojo.page.ShowPost;
import com.clfsys.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 19:54
 */
@Service
public class UserCollectionServiceimpl implements UserCollectionService {

    @Autowired
    UserCollectionMapper userCollectionMapper;


    @Override
    public void addCollection(UserCollection userCollection) {
        userCollectionMapper.addCollection(userCollection);
    }

    @Override
    public int checkCollection(int postId, int collectorId) {
        return userCollectionMapper.checkCollection(postId,collectorId);
    }

    @Override
    public List<ShowPost> getUserCollection(int userId, int pageNum, int pageSize) {
        return userCollectionMapper.getUserCollection(userId,pageNum,pageSize);
    }

    @Override
    public int getCollectNum(int userId) {
        return userCollectionMapper.getCollectNum(userId);
    }

    @Override
    public void delCollection(int postId, int collectorId) {
        userCollectionMapper.delCollection(postId,collectorId);
    }
}
