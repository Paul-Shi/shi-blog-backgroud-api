package com.example.blog.portal.book.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.blog.common.util.PageUtils;
import com.example.blog.common.util.Query;
import com.example.blog.entity.book.BookNote;
import com.example.blog.entity.book.vo.BookNoteVO;
import com.example.blog.mapper.book.BookNoteMapper;
import com.example.blog.portal.book.service.BookNoteService;
import com.example.blog.portal.book.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("BookNotePortalService")
public class BookNoteServiceImpl extends ServiceImpl<BookNoteMapper, BookNote> implements BookNoteService {
    @Resource
    private BookService bookService;

    @Override
    public PageUtils queryPageCondition(Map<String, Object> params) {
        Page<BookNoteVO> page = new Query<BookNoteVO>(params).getPage();
        List<BookNoteVO> bookNoteVOList = baseMapper.queryPageCondition(page, params);
        page.setRecords(bookNoteVOList);
        return new PageUtils(page);
    }

    @Override
    public BookNoteVO getSimpleBookNoteVo(Integer bookNoteId) {
        BookNoteVO bookNoteVO = baseMapper.getSimpleBookNoteVo(bookNoteId);
        bookNoteVO.setBook(bookService.getBookVo(bookNoteVO.getBookId()));
        return bookNoteVO;
    }

    @Override
    public List<BookNote> listSimpleBookNote(Integer bookId) {
        return baseMapper.listSimpleBookNote(bookId);
    }
}
