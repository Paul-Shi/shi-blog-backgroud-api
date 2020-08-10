package com.example.blog.portal.book.service.Impl;

import com.example.blog.common.util.PageUtils;
import com.example.blog.common.util.Query;
import com.example.blog.entity.book.Book;
import com.example.blog.entity.book.vo.BookVO;
import com.example.blog.mapper.book.BookMapper;
import com.example.blog.portal.book.service.BookService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("bookPortalService")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<BookVO> page = new Query<BookVO>(params).getPage();
        List<BookVO> bookVOList = baseMapper.queryPageCondition(page, params);
        page.setRecords(bookVOList);
        return new PageUtils(page);
    }

    @Override
    public BookVO getBookVo(Integer id) {
        return this.baseMapper.selectByIdWithSubList(id);
    }
}
