package com.example.blog.entity.article.vo;

import com.example.blog.entity.article.Article;
import com.example.blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVO extends Article {
    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;
}
