package com.clfsys.dao;

import com.clfsys.pojo.SectionTheme;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 20:05
 */
public interface SectionThemeMapper {
//根据主题id获取主题名
    @Select("Select theme_name FROM section_theme WHERE theme_id = #{theId}")
    public String showThemeNameById(int theId);
//根据板块id获取板块的所有主题信息
    @Select("Select * FROM section_theme WHERE section_id = #{secId}")
    public List<SectionTheme> showAllThemeById(int secId);
//删除主题
    @Delete("Delete from section_theme where theme_id = #{themeId}")
    public void delTheme(int themeId);
//添加主题信息
    @Insert("INSERT INTO section_theme (theme_name, section_id) \n" +
            "VALUES\n" +
            "  (#{themeName}, #{sectionId}) ")
    public void addTheme(SectionTheme sectionTheme);
}
