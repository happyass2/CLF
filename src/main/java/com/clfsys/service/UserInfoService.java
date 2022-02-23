package com.clfsys.service;

import com.clfsys.pojo.UserInfo;
import com.clfsys.pojo.page.HotUser;
import com.clfsys.pojo.page.SearchForm;
import com.clfsys.pojo.page.UserShowAdming;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/1 19:15
 */
public interface UserInfoService {
    public void register(UserInfo userInfo);

    public String loginUserInfo(String userName);

    public int checkUserName(String username);

    public String checkUserNameById(int userId);

    public int checkUserMail(String userMail);

    public UserInfo selectByUserName(String userName);

    public int selectUserNum();

    public List<HotUser> getHotUser();

    public int getUserByMailAndName( String userName,String userMail );

    public void updPassword( int userId,String userPass);

    public UserInfo getUserinfoById(int userId);

    public List<UserShowAdming> getAllUser(int page,int limit);

    public void delUser(int userId);

    public void updUser(String userName,String userMail,int userId);

    public int getUidByName(String userName);

    public int getUidByMail(String userMail);

    public List<UserShowAdming> getSearchUser(SearchForm searchForm);

    public List<Integer> getAllUserId();




}
