package com.example.blog.portal.book.controller;

import com.example.blog.common.Result;
import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.book.vo.BookVO;
import com.example.blog.portal.book.service.BookService;
import com.example.blog.portal.common.annotation.LogLike;
import com.example.blog.portal.common.annotation.LogView;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController("bookPortalController")
@CacheConfig(cacheNames = {RedisCacheNames.BOOK})
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("book/{bookId}")
    @LogView(type = "book")
    public Result getBook(@PathVariable Integer bookId) {
        BookVO bookVO = bookService.getBookVo(bookId);
        return Result.ok().put("book", bookVO);
    }

    @GetMapping("/books")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = bookService.queryPageCondition(params);
        return Result.ok().put("page", pageUtils);
    }

    @PutMapping("/book/like/{id}")
    @LogLike(type = "book")
    public Result likeBook(@PathVariable Integer id) {
        return Result.ok();
    }
}
