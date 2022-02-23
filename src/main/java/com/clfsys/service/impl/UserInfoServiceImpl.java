package com.clfsys.service.impl;

import com.clfsys.dao.UserInfoMapper;
import com.clfsys.pojo.UserInfo;
import com.clfsys.pojo.page.HotUser;
import com.clfsys.pojo.page.SearchForm;
import com.clfsys.pojo.page.UserShowAdming;
import com.clfsys.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/1 19:17
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;


    @Override
    public void register(UserInfo userInfo) {
        userInfoMapper.register(userInfo);
    }

    @Override
    public String loginUserInfo(String userName) {
        return userInfoMapper.loginUserInfo(userName);
    }

    @Override
    public int checkUserName(String username) {
        return userInfoMapper.checkUserName(username);
    }

    @Override
    public String checkUserNameById(int userId) {
        return userInfoMapper.checkUserNameById(userId);
    }


    @Override
    public int checkUserMail(String userMail) {
        return userInfoMapper.checkUserMail(userMail);
    }

    @Override
    public UserInfo selectByUserName(String userName) {
        return userInfoMapper.selectByUserName(userName);
    }

    @Override
    public int selectUserNum() {
        return userInfoMapper.selectUserNum();
    }

    @Override
    public List<HotUser> getHotUser() {
        return userInfoMapper.getHotUser();
    }

    @Override
    public int getUserByMailAndName(String userName, String userMail) {
        return userInfoMapper.getUserByMailAndName(userName,userMail);
    }

    @Override
    public void updPassword(int userId, String userPass) {
        userInfoMapper.updPassword(userId,userPass);
    }

    @Override
    public UserInfo getUserinfoById(int userId) {
        return userInfoMapper.getUserinfoById(userId);
    }

    @Override
    public List<UserShowAdming> getAllUser(int page,int limit) {
        return userInfoMapper.getAllUser( page, limit);
    }

    @Override
    public void delUser(int userId) {
        userInfoMapper.delUser(userId);
    }

    @Override
    public void updUser(String userName, String userMail, int userId) {
        userInfoMapper.updUser(userName,userMail,userId);
    }

    @Override
    public int getUidByName(String userName) {
        return userInfoMapper.getUidByName(userName);
    }

    @Override
    public int getUidByMail(String userMail) {
        return userInfoMapper.getUidByMail(userMail);
    }

    @Override
    public List<UserShowAdming> getSearchUser(SearchForm searchForm) {
        return userInfoMapper.getSearchUser(searchForm);
    }

    @Override
    public List<Integer> getAllUserId() {
        return userInfoMapper.getAllUserId();
    }


}
