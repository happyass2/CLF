package com.clfsys.controller;

import com.clfsys.pojo.Post;
import com.clfsys.pojo.Section;
import com.clfsys.pojo.page.SectionDetail;
import com.clfsys.service.impl.PostServiceimpl;
import com.clfsys.service.impl.SectionServiceimpl;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cdy
 * @date 2021/2/3 22:28
 * 板块相应功能
 *主页:展示板块,展示板块介绍
 * 管理页面:展示板块详细信息,删除板块,修改板块,
 * 板块页面:获取板块规则
 */
@Controller
@RequestMapping("/section")
public class SectionController {

    @Autowired
    SectionServiceimpl sectionServiceimpl;

    @Autowired
    PostServiceimpl postServiceimpl;
//展示所有板块
    @RequestMapping(value = "/showSection")
    @ResponseBody
    public List<Section> showAllSection()
    {
        return sectionServiceimpl.showAllsection();
    }
//添加板块(测试)
    @RequestMapping(value = "/addSection",method = RequestMethod.POST)
    @ResponseBody
    public void addSection(@RequestBody Section section)
    {
        sectionServiceimpl.addSection(section);
    }


//论坛主页展示板块介绍
    @RequestMapping(value = "/showsectionde",method = RequestMethod.POST)
    @ResponseBody
    public List<SectionDetail> showSectionDe(){
        List<SectionDetail> lsd = new ArrayList<>();
        List<Section> list = sectionServiceimpl.showAllsection();



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0;i<list.size();i++)
        {
            SectionDetail sd = new SectionDetail();
            Post p = postServiceimpl.getNewestPost(list.get(i).getSectionId());
            //板块ID
            sd.setSectionId(list.get(i).getSectionId());
            // 板块名
            sd.setSectionName(list.get(i).getSectionName());
            //板块描述
            sd.setSectionDescr(list.get(i).getSectionDescribe());
            //板块最新帖子ID

            if (postServiceimpl.getPostNumBySection(list.get(i).getSectionId()) == 0)
            {
                sd.setPostId(0);

                sd.setPostTitle("暂时没有帖子");

                sd.setCreateTime("  ");

            }else{
                sd.setPostId(p.getPostId());
                //最新帖子标题
                sd.setPostTitle(p.getPostTitle());
                //最新帖子创建时间
                sd.setCreateTime(sdf.format(p.getCreateTime()));
            }
            //板块帖子数
            sd.setPostNum(postServiceimpl.getPostNumBySection(list.get(i).getSectionId()));
            sd.setSectionIcon(list.get(i).getSectionIcon());

            lsd.add(sd);

        }

        return lsd;
    }

//获取所有板块(管理页面)
    @RequestMapping(value = "/getAllSection",method = RequestMethod.POST)
    @ResponseBody
    public Map getAllSection (){
        Map<String,Object> map = new HashMap<>();
        List<Section> sections = sectionServiceimpl.showAllsection();

        map.put("data",sections);
        map.put("count",sections.size());
        map.put("msg",null);
        map.put("code",0);
        return map;
    }

//删除板块
    @RequestMapping(value = "/delSection",method = RequestMethod.POST)
    @ResponseBody
    public void delSection(int[] sectionId){
        for (int i =0;i<sectionId.length;i++){
            sectionServiceimpl.delSection(sectionId[i]);
        }
    }
//   添加板块
    @RequestMapping(value = "/addSectionAdmin",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public void addSectionAdmin(@RequestBody Section section)throws IOException {

        sectionServiceimpl.addSectionAdmin(section);
    }
//修改板块
    @RequestMapping(value = "/updSection",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public void updSection(@RequestBody Section section){
        sectionServiceimpl.updSection(section);
    }
//获取板块规则
    @RequestMapping(value = "/writeSectionDescr",method = RequestMethod.POST)
    @ResponseBody
    public Map writeSectionDescr(int sectionId)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("obj",sectionServiceimpl.getSectionRuleById(sectionId));
        return map;
    }

}
