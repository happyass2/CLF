package com.clfsys.service;

import com.clfsys.pojo.Admin;
import com.clfsys.pojo.Moderator;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 20:06
 */
public interface AdminService {

    //    登录
    public Admin loginAdmin( String adminName,String adminPassword);

    public List<Moderator> loginModerator(String userName, String userPassword);

}
