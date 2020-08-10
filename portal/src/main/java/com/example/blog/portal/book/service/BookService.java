package com.example.blog.portal.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.book.Book;
import com.example.blog.entity.book.vo.BookVO;

import java.util.Map;

public interface BookService extends IService<Book> {
    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取Book对象
     *
     * @param id
     * @return
     */
    BookVO getBookVo(Integer id);
}
