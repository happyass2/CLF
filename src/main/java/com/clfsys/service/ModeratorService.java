package com.clfsys.service;

import com.clfsys.pojo.Moderator;
import com.clfsys.pojo.page.ModeratorPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cdy
 * @date 2021/5/7 1:55
 */
public interface ModeratorService {

    public List<ModeratorPage> getAllModerator(int page, int limit);

    public int getModeratorNum();

    public void addModerator( int userId,int sectionId);

    public void delModerator(int moderatorId);

    public List<ModeratorPage> getModeratorSearch(int userId,int sectionId,int page, int limit);

    public String getModeratorSearchNum( int userId, int sectionId);
}
