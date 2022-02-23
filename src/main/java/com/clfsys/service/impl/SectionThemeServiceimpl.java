package com.clfsys.service.impl;

import com.clfsys.dao.SectionThemeMapper;
import com.clfsys.pojo.SectionTheme;
import com.clfsys.service.SectionThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 19:54
 */
@Service
public class SectionThemeServiceimpl implements SectionThemeService {

    @Autowired
    SectionThemeMapper sectionThemeMapper;

    @Override
    public String showThemeNameById(int secId) {
        return sectionThemeMapper.showThemeNameById(secId);
    }

    @Override
    public List<SectionTheme> showAllThemeById(int secId) {
        return sectionThemeMapper.showAllThemeById(secId);
    }

    @Override
    public void delTheme(int themeId) {
        sectionThemeMapper.delTheme(themeId);
    }

    @Override
    public void addTheme(SectionTheme sectionTheme) {
        sectionThemeMapper.addTheme(sectionTheme);
    }
}
