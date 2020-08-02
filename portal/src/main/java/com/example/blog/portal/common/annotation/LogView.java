package com.example.blog.portal.common.annotation;

import org.springframework.data.elasticsearch.annotations.Document;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogView {
    String type();
}
