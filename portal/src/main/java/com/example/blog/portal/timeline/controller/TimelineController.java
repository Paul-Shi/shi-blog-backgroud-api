package com.example.blog.portal.timeline.controller;

import com.example.blog.common.Result;
import com.example.blog.entity.timeline.Timeline;
import com.example.blog.portal.timeline.service.TimelineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TimelineController {
    @Resource
    private TimelineService timelineService;

    @GetMapping("")
    public Result listTimeline() {
        List<Timeline> timelineList = timelineService.listTimeLine();
        return Result.ok().put("timelineList", timelineList);
    }
}
