package com.clfsys.dao;

import com.clfsys.dao.DySql.ModeratorSearch;
import com.clfsys.pojo.Moderator;
import com.clfsys.pojo.page.ModeratorPage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author cdy
 * @date 2021/5/7 1:53
 */
public interface ModeratorMapper {

    //获取版主信息
    @Select("select \n" +
            "  a.`moderator_id`,\n" +
            "  a.`user_id`,\n" +
            "  a.`section_id`,\n" +
            "  b.`user_name`,\n" +
            "  b.`user_mail`,\n" +
            "  b.`last_login_ip`,\n" +
            "  b.`last_login_time`,\n" +
            "  c.`section_name` \n" +
            "from\n" +
            "  moderator as a \n" +
            "  left join user_info as b \n" +
            "    on a.user_id = b.`user_id` \n" +
            "  left join section as c \n" +
            "    on c.section_id = a.`section_id` limit #{page},#{limit}")
    public List<ModeratorPage> getAllModerator(@Param("page") int page, @Param("limit") int limit);

    //获取版主数量
    @Select("select count(*) from moderator")
    public int getModeratorNum();
    //添加版主
    @Insert("Insert into moderator(user_id,section_Id) values(#{userId},#{sectionId})")
    public void addModerator(@Param("userId") int userId,@Param("sectionId") int sectionId);
    //删除版主
    @Delete("Delete from moderator where moderator_id = #{moderatorId}")
    public void delModerator(int moderatorId);
//    搜索版主
    @SelectProvider(type = ModeratorSearch.class,method = "getModeratorSearch")
    public List<ModeratorPage> getModeratorSearch(@Param("userId") int userId, @Param("sectionId")int sectionId, @Param("page") int page, @Param("limit") int limit);

//搜索版主的数量
    @SelectProvider(type = ModeratorSearch.class,method ="getModeratorSearchNum" )
    public String getModeratorSearchNum(@Param("userId") int userId, @Param("sectionId")int sectionId);
}
