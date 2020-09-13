package com.example.blog.entity.article.dto;

import com.example.blog.entity.article.Article;
import com.example.blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDTO extends Article {
    private List<Tag> tagList;
}
