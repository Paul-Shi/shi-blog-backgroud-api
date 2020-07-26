package com.example.blog.auth.service.impl;

import com.example.blog.auth.service.ShiroService;
import com.example.blog.entity.sys.SysUser;
import com.example.blog.entity.sys.SysUserToken;
import com.example.blog.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Set<String> getUserPermissions(Integer userId) {
        return null;
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return null;
    }

    @Override
    public SysUser queryUser(Integer userId) {
        return null;
    }

    @Override
    public void refreshToken(Integer userId, String accessToken) {

    }
}
