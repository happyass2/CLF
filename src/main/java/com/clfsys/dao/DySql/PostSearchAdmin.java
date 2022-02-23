package com.clfsys.dao.DySql;

import com.clfsys.pojo.page.SearchPostAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author cdy
 * @date 2021/5/4 1:55
 */
public class PostSearchAdmin {

    public String getSearchPostAdmin(SearchPostAdmin searchPostAdmin){
        String sql = "SELECT \n" +
                "  a.`post_id`,\n" +
                "  a.`post_title`,\n" +
                "  a.`post_icon`,\n" +
                "  a.`watch_number`,\n" +
                "  a.`collect_number`,\n" +
                "  a.`comment_number`,\n" +
                "  a.`create_time`,\n" +
                "  a.`last_time`,\n" +
                "  a.`top_post`,\n" +
                "  a.`elite_post`,\n" +
                "  a.`publisher_id`,\n" +
                "  b.`section_name`,\n" +
                "  c.`theme_name`,\n" +
                "  d.`user_name` \n" +
                "FROM\n" +
                "  post AS a \n" +
                "  LEFT JOIN section AS b \n" +
                "    ON a.`post_section_id` = b.`section_id` \n" +
                "  LEFT JOIN section_theme AS c \n" +
                "    ON a.`post_theme_id` = c.`theme_id` \n" +
                "  LEFT JOIN user_info AS d \n" +
                "    ON a.`publisher_id` = d.`user_id` where 1=1";


        StringBuilder sb = new StringBuilder(sql);

                if (searchPostAdmin.getPostTitle()!=null&&!searchPostAdmin.getPostTitle().equals("")){
                    sb.append(" and post_title like concat ('%',#{postTitle},'%')");
                }
                if (searchPostAdmin.getCreateTime()!=null&&!searchPostAdmin.getCreateTime().equals("")){
                    sb.append(" and create_time >= #{createTime}");
                }if (searchPostAdmin.getCreateTime2()!=null&&!searchPostAdmin.getCreateTime2().equals("")){
                    sb.append(" and create_time <= #{createTime2}");
                }
                if (searchPostAdmin.getLastTime()!=null&&!searchPostAdmin.getLastTime().equals("")){
                    sb.append(" and last_time <= #{lastTime}");
                }
                if (searchPostAdmin.getLastTime2()!=null&&!searchPostAdmin.getLastTime2().equals("")){
                    sb.append(" and last_time <= #{lastTime2}");
                }
                if (searchPostAdmin.getPublisherId()!=0)
                {
                    sb.append(" and publisher_id <= #{publisherId}");
                }
                if (searchPostAdmin.getPostSectionId()!=0){
                    sb.append(" and post_section_id = #{postSectionId}");
                }
                if (searchPostAdmin.getPostThemeId()!=0){
                    sb.append(" and post_theme_id = #{postThemeId}");
                }
                sql = sb.toString();
                return sql;

    }


}
