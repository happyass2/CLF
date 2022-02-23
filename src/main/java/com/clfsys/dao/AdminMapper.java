package com.clfsys.dao;

import com.clfsys.pojo.Admin;
import com.clfsys.pojo.Moderator;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 20:05
 */
public interface AdminMapper {

//    管理员登录
    @Select("SELECT admin_id,admin_name FROM admin WHERE admin_name =#{adminName} and admin_password = #{adminPassword}")
    public Admin loginAdmin(@Param("adminName") String adminName,@Param("adminPassword") String adminPassword);

    //    版主登录
    @Select("SELECT \n" +
            "  b.*\n" +
            "FROM\n" +
            "  user_info AS a \n" +
            "  RIGHT JOIN moderator AS b \n" +
            "    ON a.`user_id` = b.`user_id` \n" +
            "WHERE a.user_name =#{userName} and a.user_password = #{userPassword}")
    public List<Moderator> loginModerator(@Param("userName") String userName,@Param("userPassword") String userPassword);
}
