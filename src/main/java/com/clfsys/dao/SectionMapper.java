package com.clfsys.dao;

import com.clfsys.pojo.Section;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 22:21
 */
public interface SectionMapper {
//展示所有板块
    @Select("SELECT * FROM section")
    public List<Section> showAllsection();
//增加板块信息（测试）
    @Insert("INSERT INTO section(section_name,section_describe) VALUES (#{sectionName},#{sectionDescribe})")
    public void addSection(Section section);
//根据板块id展示板块名
    @Select("Select section_name FROM section WHERE section_id = #{sectionId}")
    public String showSectionNameById(int sectionId);
//读取板块数量
    @Select("Select COUNT(*) FROM section")
    public int getSectionNum();
//删除板块信息
    @Delete("delete from section where section_id = #{sectionId}")
    public void delSection(int sectionId);
//管理员添加板块信息
    @Insert("INSERT INTO section (\n" +
            "  section_name,\n" +
            "  section_describe,\n" +
            "  section_icon,\n" +
            "  section_rule\n" +
            ") \n" +
            "VALUES\n" +
            "  (#{sectionName}, #{sectionDescribe}, #{sectionIcon}, #{sectionRule})")
    public void addSectionAdmin(Section section);
//更新板块信息
    @Update("UPDATE \n" +
            "  section \n" +
            "SET\n" +
            "  section_name = #{sectionName},\n" +
            "  section_describe =  #{sectionDescribe},\n" +
            "  section_icon =  #{sectionIcon},\n" +
            "  section_rule =  #{sectionRule} \n" +
            "WHERE section_id =  #{sectionId} ")
    public void updSection(Section section);
//根据板块id获取板块规则
    @Select("Select section_rule FROM section WHERE section_id = #{sectionId}")
    public String getSectionRuleById(int sectionId);
}
