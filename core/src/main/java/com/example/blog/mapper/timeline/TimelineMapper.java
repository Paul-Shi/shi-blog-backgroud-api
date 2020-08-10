package com.example.blog.mapper.timeline;

import com.example.blog.entity.timeline.Timeline;
import com.example.blog.entity.timeline.TimelinePost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TimelineMapper {
    List<TimelinePost> listTimelinePost(@Param("year") Integer year, @Param("month") Integer month);

    List<Timeline> listTimeline();
}
