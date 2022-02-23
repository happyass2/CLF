package com.clfsys.service;

import com.clfsys.dao.SectionThemeMapper;
import com.clfsys.pojo.SectionTheme;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 19:54
 */
public interface SectionThemeService {

    public String showThemeNameById(int themeId);

    public List<SectionTheme> showAllThemeById(int secId);

    public void delTheme(int themeId);

    public void addTheme(SectionTheme sectionTheme);
}
