package com.example.blog.common.xss;

import com.example.blog.common.exception.MyException;
import com.example.blog.common.exception.enums.ErrorEnum;
import org.apache.commons.lang.StringUtils;

public class SQLFilter {
    public static String sqlInject(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = org.springframework.util.StringUtils.replace(str, "'", "");
        str = org.springframework.util.StringUtils.replace(str, "\"", "");
        str = org.springframework.util.StringUtils.replace(str, ";", "");
        str = org.springframework.util.StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new MyException(ErrorEnum.SQL_ILLEGAL);
            }
        }

        return str;
    }
}
