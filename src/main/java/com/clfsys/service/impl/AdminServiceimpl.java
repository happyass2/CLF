package com.clfsys.service.impl;

import com.clfsys.dao.AdminMapper;
import com.clfsys.pojo.Admin;
import com.clfsys.pojo.Moderator;
import com.clfsys.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cdy
 * @date 2021/2/4 20:06
 */
@Service
public class AdminServiceimpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;


    @Override
    public Admin loginAdmin(String adminName, String adminPassword) {
        return adminMapper.loginAdmin(adminName,adminPassword);
    }

    @Override
    public List<Moderator> loginModerator(String userName, String userPassword) {
        return adminMapper.loginModerator(userName,userPassword);
    }
}
