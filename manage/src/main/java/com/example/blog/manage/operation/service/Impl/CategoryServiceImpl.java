package com.example.blog.manage.operation.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.entity.operation.Category;
import com.example.blog.manage.operation.service.CategoryService;
import com.example.blog.mapper.operation.CategoryMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> queryWithParentName(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public List<Category> queryListParentId(Integer id) {
        return baseMapper.selectList(new QueryWrapper<Category>().lambda().eq(Category::getParentId, id));
    }

    @Override
    public String renderCategoryArr(String categoryIds, List<Category> categoryList) {
        if (StringUtils.isEmpty(categoryIds)) {
            return "";
        }
        List<String> categoryStrList = new ArrayList<>();
        String[] categoryIdArr = categoryIds.split(",");
        for (int i = 0; i < categoryIdArr.length; i++) {
            Integer categoryId = Integer.parseInt(categoryIdArr[i]);
            //根据Id查找类别名称
            String categoryStr = categoryList
                    .stream()
                    .filter(category -> category.getId().equals(categoryId))
                    .map(Category::getName)
                    .findAny().orElse("类别已被删除");
            categoryStrList.add(categoryStr);
        }
        return String.join(",", categoryStrList);
    }
}
