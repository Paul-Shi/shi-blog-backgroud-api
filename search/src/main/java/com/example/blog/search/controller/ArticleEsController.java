package com.example.blog.search.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.blog.common.Result;
import com.example.blog.common.constants.RabbitMqConstants;
import com.example.blog.common.exception.MyExceptionHandler;
import com.example.blog.entity.article.Article;
import com.example.blog.portal.article.service.ArticleService;
import com.example.blog.search.mapper.ArticleRepository;
import com.google.common.collect.Lists;
import com.rabbitmq.client.Channel;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class ArticleEsController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleEsController.class);

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private ArticleService articleService;

    @GetMapping("articles/search")
    public Result search(@RequestParam("keywords") String keywords) {
        //对所有索引进行搜索
        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(keywords);

        Iterable<Article> listIt = articleRepository.search(queryBuilder);

        //Iteralbe转list
        List<Article> articleList = Lists.newArrayList(listIt);

        return Result.ok().put("articleList", articleList);
    }

    @RabbitListener(queues = RabbitMqConstants.REFRESH_ES_INDEX_QUEUE)
    public void refresh(Message message, Channel channel) {
        try {
            //手动确认消息已经被消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            articleRepository.deleteAll();
            List<Article> list = articleService.list(new QueryWrapper<Article>().lambda().eq(Article::getPublish, true));
            articleRepository.saveAll(list);
            logger.info(message.toString());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
