package com.clfsys.service;

import com.clfsys.pojo.Section;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 22:26
 */
public interface SectionService {
    public List<Section> showAllsection();

    public void addSection(Section section);

    public String showSectionNameById(int sectionId);

    public int getSectionNum();

    public void delSection(int sectionId);

    public void addSectionAdmin(Section section);

    public void updSection(Section section);

    public String getSectionRuleById(int sectionId);
}
