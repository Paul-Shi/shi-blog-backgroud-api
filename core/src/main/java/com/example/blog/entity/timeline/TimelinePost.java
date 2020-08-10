package com.example.blog.entity.timeline;

import lombok.Data;

import java.util.Date;

@Data
public class TimelinePost {
    private Integer id;

    private String title;

    private String description;

    private String postType;

    private Date createTime;
}
