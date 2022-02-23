package com.clfsys.dao;

import com.clfsys.pojo.UserInfo;
import com.clfsys.pojo.page.HotUser;
import com.clfsys.pojo.page.SearchForm;
import com.clfsys.pojo.page.UserShowAdming;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/1 18:51
 */

public interface UserInfoMapper {
    //插入用户信息
    @Insert("\n" +
            "INSERT INTO user_info(user_gender,user_mail,user_name,user_password,last_login_time,last_login_ip,user_act,user_icon,register_time,activecode,user_post_number,collection_number) VALUES(#{userGender},#{userMail},#{userName},#{userPassword},#{lastLoginTime},#{lastLoginIp},#{userAct},#{userIcon},#{registerTime},#{activeCode},#{userPostNumber},#{collectionNumber})")
    public void register(UserInfo userInfo);

    //查询所有用户
    @Select("select * from user_info limit #{page},#{limit}")
    public List<UserShowAdming> getAllUser(@Param("page") int page,@Param("limit") int limit);

    //获取登录密码
    @Select("SELECT user_password FROM user_info where user_name = #{userName}")
    public String loginUserInfo(String userName);

    //根据查询用户名
    @Select("SELECT COUNT(*) FROM user_info where user_name = #{username}")
    public int checkUserName(String username);

    //查询用户名
    @Select("SELECT user_name FROM user_info where user_id = #{userId}")
    public String checkUserNameById(int userId);


    //查询邮箱
    @Select("SELECT COUNT(*) FROM user_info where user_mail = #{userMail}")
    public int checkUserMail(String userMail);


    //查询用户
    @Select("SELECT * FROM user_info where user_name = #{userName}")
    public UserInfo selectByUserName(String userName);

    //查询用户数
    @Select("SELECT COUNT(*) FROM user_info")
    public int selectUserNum();
    //获取活跃度前5的用户
    @Select("SELECT user_id,user_name,user_icon FROM user_info order by user_act DESC LIMIT 0,5")
    public List<HotUser> getHotUser();

    //根据邮箱和用户名获取用户id
    @Select("SELECT user_id FROM user_info WHERE user_name=#{userName}AND user_mail=#{userMail}")
    public int getUserByMailAndName(@Param("userName") String userName, @Param("userMail")String userMail );

    //用户更换密码
    @Update("UPDATE user_info SET user_password = #{userPass} where user_id = #{userId}")
    public void updPassword(@Param("userId") int userId,@Param("userPass")String userPass);
    //根据用户id获取用户信息
    @Select("select a.`user_icon`,a.`user_id`,a.`user_name`,a.`last_login_ip`,a.`last_login_time`,a.`register_time`,a.`user_gender`,a.`user_post_number`,a.`user_act` from user_info as a where user_id = #{userId}")
    public UserInfo getUserinfoById(int userId);
    //删除用户信息
    @Delete("delete from user_info where user_id = #{userId}")
    public void delUser(int userId);
    //更新用户名或邮箱
    @Update("UPDATE user_info SET user_name = #{userName},user_mail = #{userMail} WHERE user_id = #{userId}")
    public void updUser(@Param("userName")String userName,@Param("userMail")String userMail,@Param("userId")int userId);
    //根据用户名获取用户id
    @Select("SELECT user_id FROM user_info WHERE user_name = #{userName}")
    public int getUidByName(String userName);
    //根据邮箱获取用户id
    @Select("SELECT user_id FROM user_info WHERE user_mail = #{userMail}")
    public int getUidByMail(String userMail);
    //用户信息的多条件查询
    @Select("select * from user_info where user_name like CONCAT ('%',#{userName2},'%') and user_mail like CONCAT ('%',#{userMail2},'%') and last_login_ip like CONCAT ('%',#{loginIp2},'%') and user_post_number like CONCAT ('%',#{postNumber2},'%') and user_gender like CONCAT ('%',#{userGender2},'%') and register_time >= #{regTime} AND register_time <= #{regTime2} and last_login_time >= #{loginTime} AND last_login_time <= #{loginTime2} ")
    public List<UserShowAdming> getSearchUser(SearchForm searchForm);
    //获取所有用户id
    @Select("SELECT user_id from user_info")
    public List<Integer> getAllUserId();




}
