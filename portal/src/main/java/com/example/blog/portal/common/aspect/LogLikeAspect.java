package com.example.blog.portal.common.aspect;

import com.example.blog.common.util.HttpContextUtils;
import com.example.blog.common.util.IPUtils;
import com.example.blog.common.util.JsonUtils;
import com.example.blog.mapper.article.ArticleMapper;
import com.example.blog.mapper.book.BookMapper;
import com.example.blog.mapper.book.BookNoteMapper;
import com.example.blog.mapper.log.LogLikeMapper;
import com.example.blog.portal.common.annotation.LogLike;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogLikeAspect {
    @Autowired
    private LogLikeMapper logLikeMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookNoteMapper bookNoteMapper;

    @Pointcut("@annotation(com.example.blog.portal.common.annotation.LogLike)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    @Transactional(rollbackFor = Exception.class)
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长（毫秒）
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveLogLike(point, time);
        return result;
    }

    private void saveLogLike(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.example.blog.entity.log.LogLike logLikeEntity = new com.example.blog.entity.log.LogLike();
        LogLike viewLog = method.getAnnotation(LogLike.class);
        //注解上的类型
        String type = viewLog.type();
        logLikeEntity.setType(type);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String id = JsonUtils.toJson(args[0]);
        switch (type) {
            case "article":
                articleMapper.updateLikeNum(Integer.parseInt(id));
                break;
            case "book":
                bookMapper.updateLikeNum(Integer.parseInt(id));
                break;
            case "bookNote":
                bookNoteMapper.updateLikeNum(Integer.parseInt(id));
                break;
            default:
                break;
        }
        logLikeEntity.setParams(id);
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP
        logLikeEntity.setIp(IPUtils.getIpAddr(request));
        logLikeEntity.setTime(time);
        logLikeEntity.setCreateDate(LocalDateTime.now());
        logLikeMapper.insert(logLikeEntity);
    }
}
