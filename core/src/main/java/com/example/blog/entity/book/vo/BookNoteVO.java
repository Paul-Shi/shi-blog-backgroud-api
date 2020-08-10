package com.example.blog.entity.book.vo;

import com.example.blog.entity.book.Book;
import com.example.blog.entity.book.BookNote;
import com.example.blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

@Data
public class BookNoteVO extends BookNote {
    /**
     * 所属分类，以逗号分割
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

    /**
     * 所属书本
     */
    private Book book;
}
