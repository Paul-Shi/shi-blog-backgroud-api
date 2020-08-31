package com.example.blog.manage.operation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.entity.operation.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService extends IService<Category> {
    /**
     * 查询所有菜单
     *
     * @param params
     * @return
     */
    List<Category> queryWithParentName(Map<String, Object> params);

    /**
     * 根据父级别查询子级别
     *
     * @param id
     * @return
     */
    List<Category> queryListParentId(Integer id);

    /**
     * 根据类别id数组查询类别数组
     *
     * @param categoryIds
     * @param categoryList
     * @return
     */
    String renderCategoryArr(String categoryIds, List<Category> categoryList);
}
