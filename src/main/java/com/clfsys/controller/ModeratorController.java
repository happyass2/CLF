package com.clfsys.controller;

import com.clfsys.pojo.page.ModeratorPage;
import com.clfsys.service.impl.ModeratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cdy
 * @date 2021/5/7 1:55
 * 版主管理
 * 展示、添加、删除、搜索版主
 */
@RequestMapping("/moderator")
@Controller
public class ModeratorController {

    @Autowired
    ModeratorServiceImpl moderatorService;
//展示所有版主
    @RequestMapping(value = "/getAllModerator",method = RequestMethod.POST)
    @ResponseBody
    public Map getAllModerator(int page, int limit){
        Map<String,Object> map = new HashMap<>();

        map.put("data",moderatorService.getAllModerator((page - 1) * limit,limit));
        map.put("code",0);
        map.put("count",moderatorService.getModeratorNum());
        map.put("msg",null);
        return map;
    }


//添加版主（需要指定用户Id与板块Id）
    @RequestMapping(value = "/addModerator",method = RequestMethod.POST)
    public void addModerator(int userId, int sectionId){
        moderatorService.addModerator(userId,sectionId);
    }


//    删除版主
    @RequestMapping(value = "/delModerator",method = RequestMethod.POST)
    public void delModerator(int moderatorId[]){
        for (int i =0;i<moderatorId.length;i++){
            moderatorService.delModerator(moderatorId[i]);
        }
    }


//搜索版主
    @RequestMapping(value = "/getModeratorSearch",method = RequestMethod.POST)
    @ResponseBody
    public Map getModeratorSearch(int userId, int sectionId, int page, int limit){
        Map<String,Object> map = new HashMap<>();

        map.put("data",moderatorService.getModeratorSearch(userId,sectionId,(page - 1) * limit,limit));
        map.put("code",0);
        map.put("count",moderatorService.getModeratorSearchNum(userId,sectionId));
        map.put("msg",null);
        return map;
    }



}
