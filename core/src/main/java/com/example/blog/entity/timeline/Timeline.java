package com.example.blog.entity.timeline;

import lombok.Data;

import java.util.List;

@Data
public class Timeline {
    private Integer year;

    private Integer count;

    private List<TimelineMonth> months;
}
