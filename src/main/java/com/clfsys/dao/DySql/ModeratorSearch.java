package com.clfsys.dao.DySql;

import org.apache.ibatis.annotations.Param;

/**
 * @author cdy
 * @date 2021/5/7 2:55
 */
public class ModeratorSearch {

    public String getModeratorSearch(@Param("userId") int userId, @Param("sectionId")int sectionId, @Param("page") int page, @Param("limit") int limit){

        String sql ="SELECT \n" +
                "  a.`moderator_id`,\n" +
                "  a.`user_id`,\n" +
                "  a.`section_id`,\n" +
                "  b.`user_name`,\n" +
                "  b.`user_mail`,\n" +
                "  b.`last_login_ip`,\n" +
                "  b.`last_login_time`,\n" +
                "  c.`section_name` \n" +
                "FROM\n" +
                "  moderator AS a \n" +
                "  LEFT JOIN user_info AS b \n" +
                "    ON a.user_id = b.`user_id` \n" +
                "  LEFT JOIN section AS c on a.section_id = c.section_id  where 1=1" ;
        StringBuilder sb = new StringBuilder(sql);

        if (userId!=0)
        {
            sb.append(" and a.user_id = #{userId} ");
        }
        if (sectionId!=0){
            sb.append(" and a.section_id = #{sectionId} ");
        }

        sb.append(" limit #{page},#{limit}");

        return sb.toString();

    }

    public String getModeratorSearchNum(@Param("userId") int userId, @Param("sectionId")int sectionId){

        String sql ="SELECT \n" +
                "  count(*)\n" +
                "FROM\n" +
                "  moderator where 1=1 \n";
        StringBuilder sb = new StringBuilder(sql);

        if (userId!=0)
        {
            sb.append(" and user_id = #{userId} ");
        }
        if (sectionId!=0){
            sb.append(" and section_id = #{sectionId} ");
        }

        return sb.toString();

    }
}
