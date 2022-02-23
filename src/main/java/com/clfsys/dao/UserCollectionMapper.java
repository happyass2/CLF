package com.clfsys.dao;

import com.clfsys.pojo.UserCollection;
import com.clfsys.pojo.page.ShowPost;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 2:12
 */
public interface UserCollectionMapper {
    //添加收藏
    @Insert("INSERT INTO user_collection(post_id,collector_id,collect_datetime) VALUES (#{postId},#{collectorId},#{collectDatetime})")
    public void addCollection(UserCollection userCollection);

    //查看用户是否已收藏
    @Select("SELECT COUNT(*) FROM user_collection WHERE post_id = #{postId} AND collector_id = #{collectorId}")
    public int checkCollection(@Param("postId") int postId,@Param("collectorId") int collectorId);

    //展示收藏
    @Select("SELECT \n" +
            "  d.* \n" +
            "FROM\n" +
            "  (SELECT \n" +
            "    a.`post_id`,\n" +
            "    a.`post_icon`,\n" +
            "    a.`post_title`,\n" +
            "    a.`create_time`,\n" +
            "    a.`last_time`,\n" +
            "    b.`user_name` \n" +
            "  FROM\n" +
            "    post AS a \n" +
            "    LEFT JOIN user_info AS b \n" +
            "      ON a.`publisher_id` = b.`user_id`) AS d \n" +
            "  LEFT JOIN user_collection AS c \n" +
            "    ON d.post_id = c.post_id \n" +
            "WHERE c.collector_id = #{userId} ORDER BY d.create_time DESC LIMIT #{pageNum},#{pageSize}")
    public List<ShowPost> getUserCollection(@Param("userId") int userId,@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);
    //获取收藏数量
    @Select("select count(*) from user_collection where collector_id =1")
    public int getCollectNum(int userId);

    //删除收藏
    @Delete("DELETE FROM user_collection WHERE post_id = #{postId} and collector_id = #{collectorId}")
    public void delCollection(@Param("postId") int postId,@Param("collectorId")int collectorId);
}
