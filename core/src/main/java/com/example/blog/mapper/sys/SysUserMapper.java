package com.example.blog.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.sys.SysUser;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 查询用户所有权限
     *
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Integer userId);

    /**
     * 查询用户的muenId
     *
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);
}
