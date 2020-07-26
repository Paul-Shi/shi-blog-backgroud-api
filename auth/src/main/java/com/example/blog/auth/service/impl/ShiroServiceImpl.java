package com.example.blog.auth.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.blog.auth.service.ShiroService;
import com.example.blog.auth.service.SysUserTokenService;
import com.example.blog.common.constants.RedisKeyConstants;
import com.example.blog.common.constants.SysConstants;
import com.example.blog.entity.sys.SysMenu;
import com.example.blog.entity.sys.SysUser;
import com.example.blog.entity.sys.SysUserToken;
import com.example.blog.mapper.sys.SysMenuMapper;
import com.example.blog.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Override
    public Set<String> getUserPermissions(Integer userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (SysConstants.SUPER_ADMIN.equals(userId)) {
            List<SysMenu> menuList = sysMenuMapper.selectList(null);
            permsList = new ArrayList<>(menuList.size());
            menuList.forEach(menu -> permsList.add(menu.getPerms()));
        } else {
            permsList = sysUserMapper.queryAllPerms(userId);
        }

        return permsList.stream()
                //过滤空置的字符串
                .filter(perms -> !StringUtils.isEmpty(perms))
                //把小的list合并成大的list
                .flatMap(perms -> Arrays.stream(perms.split(",")))
                //转换成set集合
                .collect(Collectors.toSet());
    }

    @Override
    public SysUserToken queryByToken(String token) {
        return sysUserTokenService.queryByToken(RedisKeyConstants.MANAGE_SYS_USER_TOKEN + token);
    }

    @Override
    public SysUser queryUser(Integer userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public void refreshToken(Integer userId, String accessToken) {
        sysUserTokenService.refreshToken(userId, accessToken);
    }
}
