package com.clfsys.service.impl;

import com.clfsys.dao.SectionMapper;
import com.clfsys.pojo.Section;
import com.clfsys.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/3 22:27
 */
@Service
public class SectionServiceimpl implements SectionService {

    @Autowired
    SectionMapper sectionMapper;

    @Override
    public List<Section> showAllsection() {
        return sectionMapper.showAllsection();
    }

    @Override
    public void addSection(Section section) {
        sectionMapper.addSection(section);
    }

    @Override
    public String showSectionNameById(int sectionId) {
        return sectionMapper.showSectionNameById(sectionId);
    }

    @Override
    public int getSectionNum() {
        return sectionMapper.getSectionNum();
    }

    @Override
    public void delSection(int sectionId) {
        sectionMapper.delSection(sectionId);
    }

    @Override
    public void addSectionAdmin(Section section) {
        sectionMapper.addSectionAdmin(section);
    }

    @Override
    public void updSection(Section section) {
        sectionMapper.updSection(section);
    }

    @Override
    public String getSectionRuleById(int sectionId) {
        return sectionMapper.getSectionRuleById(sectionId);
    }
}
