package com.example.blog.entity.operation.vo;

import com.example.blog.entity.operation.Recommend;
import com.example.blog.entity.operation.Tag;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RecommendVO extends Recommend {
    private String description;

    private Long readNum;

    private Long commentNum;

    private Long likeNum;

    private String urlType;

    private String cover;

    private Date createTime;

    private List<Tag> tagList;
}
