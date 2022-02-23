package com.clfsys.service.impl;

import com.clfsys.dao.ModeratorMapper;
import com.clfsys.pojo.Moderator;
import com.clfsys.pojo.page.ModeratorPage;
import com.clfsys.service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/5/7 1:55
 */
@Service
public class ModeratorServiceImpl implements ModeratorService {
    @Autowired
    ModeratorMapper moderatorMapper;

    @Override
    public List<ModeratorPage> getAllModerator(int page, int limit){
       return moderatorMapper.getAllModerator(page,limit);
    }

    @Override
    public int getModeratorNum() {
        return moderatorMapper.getModeratorNum();
    }

    @Override
    public void addModerator(int userId, int sectionId) {
        moderatorMapper.addModerator(userId,sectionId);
    }

    @Override
    public void delModerator(int moderatorId) {
        moderatorMapper.delModerator(moderatorId);
    }

    @Override
    public List<ModeratorPage> getModeratorSearch(int userId, int sectionId, int page, int limit) {
        return moderatorMapper.getModeratorSearch(userId,sectionId,page,limit);
    }

    @Override
    public String getModeratorSearchNum(int userId, int sectionId) {
        return moderatorMapper.getModeratorSearchNum(userId,sectionId);
    }
}
