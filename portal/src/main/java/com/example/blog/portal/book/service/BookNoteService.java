package com.example.blog.portal.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.book.BookNote;
import com.example.blog.entity.book.vo.BookNoteVO;

import java.util.List;
import java.util.Map;

public interface BookNoteService extends IService<BookNote> {
    /**
     * 分页分类获取列表
     *
     * @param params
     * @return
     */
    PageUtils queryPageCondition(Map<String, Object> params);

    /**
     * 获取简单对象
     *
     * @param bookNoteId
     * @return
     */
    BookNoteVO getSimpleBookNoteVo(Integer bookNoteId);

    /**
     * 获取简单的List
     *
     * @param bookId
     * @return
     */
    List<BookNote> listSimpleBookNote(Integer bookId);
}
