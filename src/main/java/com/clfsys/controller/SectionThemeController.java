package com.clfsys.controller;

import com.clfsys.pojo.SectionTheme;
import com.clfsys.service.impl.SectionThemeServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cdy
 * @date 2021/2/4 19:55
 * 主题相关功能
 *板块页:展示相应主题,
 * 管理页面:查询相应主题,删除,添加
 *
 */
@Controller
@RequestMapping("/sectiontheme")
public class SectionThemeController {

    @Autowired
    SectionThemeServiceimpl sectionThemeServiceimpl;

//根据板块展示主题
    @RequestMapping(value = "/showthemebysec",method = RequestMethod.POST)
    @ResponseBody
    public Map showSectionNameById(int secId){


        Map<String,Object> map = new HashMap<>();

        List<SectionTheme> st = sectionThemeServiceimpl.showAllThemeById(secId);

        map.put("obj",st);

        return map;
    }

//根据板块展示主题
    @RequestMapping(value = "/showthemebysecAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Map showthemebysecAdmin(int secId){



        Map<String,Object> map = new HashMap<>();

        List<SectionTheme> st = sectionThemeServiceimpl.showAllThemeById(secId);

        map.put("data",st);
        map.put("count",st.size());
        map.put("msg",null);
        map.put("code",0);

        return map;
    }


//删除主题
    @RequestMapping(value = "/delTheme",method = RequestMethod.POST)
    public void delTheme(int[] themeId){

        for (int i = 0;i<themeId.length;i++)
        {
            sectionThemeServiceimpl.delTheme(themeId[i]);
        }


    }


//添加主题
    @RequestMapping(value = "/addTheme",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public void addTheme(@RequestBody SectionTheme sectionTheme){

        sectionThemeServiceimpl.addTheme(sectionTheme);
    }
}
