package com.example.blog.entity.article.dto;

import com.example.blog.entity.article.Article;
import com.example.blog.entity.operation.Tag;

import java.util.List;

public class ArticleDTO extends Article {
    private List<Tag> tagList;
}
