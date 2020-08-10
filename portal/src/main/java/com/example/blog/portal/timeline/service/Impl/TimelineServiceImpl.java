package com.example.blog.portal.timeline.service.Impl;

import com.example.blog.entity.timeline.Timeline;
import com.example.blog.mapper.timeline.TimelineMapper;
import com.example.blog.portal.timeline.service.TimelineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TimelineServiceImpl implements TimelineService {
    @Resource
    private TimelineMapper timelineMapper;

    @Override
    public List<Timeline> listTimeLine() {
        return null;
    }
}
