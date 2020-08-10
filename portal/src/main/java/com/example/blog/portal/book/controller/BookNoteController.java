package com.example.blog.portal.book.controller;

import com.example.blog.common.Result;
import com.example.blog.common.constants.RedisCacheNames;
import com.example.blog.common.util.PageUtils;
import com.example.blog.entity.book.BookNote;
import com.example.blog.portal.book.service.BookNoteService;
import com.example.blog.portal.common.annotation.LogLike;
import com.example.blog.portal.common.annotation.LogView;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController("bookNotePortalController")
@CacheConfig(cacheNames = {RedisCacheNames.BOOKNOTE})
public class BookNoteController {
    @Resource
    private BookNoteService bookNoteService;

    @GetMapping("/bookNote/{bookNoteId}")
    @LogView(type = "bookNote")
    public Result getBookNote(@PathVariable Integer bookNoteId) {
        BookNote bookNote = bookNoteService.getById(bookNoteId);
        return Result.ok().put("bookNote", bookNote);
    }

    @GetMapping("/bookNotes")
    @Cacheable
    public Result list(@RequestParam Map<String, Object> params) {
        PageUtils pageUtils = bookNoteService.queryPageCondition(params);
        return Result.ok().put("page", pageUtils);
    }

    @PutMapping("/bookNote/like/{id}")
    @LogLike(type = "bookNote")
    public Result likeBookNote(@PathVariable Integer id) {
        return Result.ok();
    }
}
