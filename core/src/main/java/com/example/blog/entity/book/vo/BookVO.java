package com.example.blog.entity.book.vo;

import com.example.blog.entity.book.Book;
import com.example.blog.entity.book.BookNote;
import com.example.blog.entity.book.BookSense;
import com.example.blog.entity.operation.Tag;
import lombok.Data;

import java.util.List;

@Data
public class BookVO extends Book {
    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;

    /**
     * 所属标签
     */
    private List<Tag> tagList;

    /**
     * 所属标记
     */
    private List<BookNote> bookNoteList;

    /**
     * 读后感
     */
    private BookSense bookSense;
}
