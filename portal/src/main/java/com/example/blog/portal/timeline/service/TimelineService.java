package com.example.blog.portal.timeline.service;

import com.example.blog.entity.timeline.Timeline;

import java.util.List;

public interface TimelineService {
    /**
     * 获取timeline数据
     * @return
     */
    List<Timeline> listTimeLine();
}
