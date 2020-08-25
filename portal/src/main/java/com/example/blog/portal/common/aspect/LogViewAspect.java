package com.example.blog.portal.common.aspect;

import com.example.blog.common.util.HttpContextUtils;
import com.example.blog.common.util.IPUtils;
import com.example.blog.common.util.JsonUtils;
import com.example.blog.mapper.article.ArticleMapper;
import com.example.blog.mapper.book.BookMapper;
import com.example.blog.mapper.book.BookNoteMapper;
import com.example.blog.mapper.log.LogViewMapper;
import com.example.blog.portal.common.annotation.LogView;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogViewAspect {

    @Autowired
    private LogViewMapper logViewMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookNoteMapper bookNoteMapper;

    @Pointcut("@annotation(com.example.blog.portal.common.annotation.LogView)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    @Transactional(rollbackFor = Exception.class)
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveViewLog(point, time);

        return result;
    }

    private void saveViewLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        com.example.blog.entity.log.LogView viewLogEntity = new com.example.blog.entity.log.LogView();
        LogView viewLog = method.getAnnotation(LogView.class);
        //注解上的类型
        String type = viewLog.type();
        viewLogEntity.setType(type);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        String id = JsonUtils.toJson(args[0]);
        //根据注解类型增加数量
        switch (type) {
            case "article":
                articleMapper.updateReadNum(Integer.parseInt(id));
                break;
            case "book":
                bookMapper.updateReadNum(Integer.parseInt(id));
                break;
            case "bookNote":
                bookNoteMapper.updateReadNum(Integer.parseInt(id));
                break;
            default:
                break;
        }
        viewLogEntity.setParams(id);
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        viewLogEntity.setMethod(className + "." + methodName + "()");
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //获取IP地址
        viewLogEntity.setIp(IPUtils.getIpAddr(request));
        viewLogEntity.setTime(time);
        viewLogEntity.setCreateDate((LocalDateTime.now()));
        logViewMapper.insert(viewLogEntity);
    }

}
