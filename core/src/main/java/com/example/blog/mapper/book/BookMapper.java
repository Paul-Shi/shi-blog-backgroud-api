package com.example.blog.mapper.book;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.book.Book;
import com.example.blog.entity.book.vo.BookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    /**
     * 获取bookVo
     *
     * @param page
     * @param params
     * @return
     */
    List<BookVO> listBookVo(Page<BookVO> page, @Param("params") Map<String, Object> params);

    /**
     * 根据条件分页查询
     *
     * @param page
     * @param params
     * @return
     */
    List<BookVO> queryPageCondition(Page<BookVO> page, @Param("params") Map<String, Object> params);

    /**
     * 更新阅读记录
     *
     * @param id
     */
    void updateReadNum(int id);

    /**
     * 更新点赞记录
     *
     * @param parseInt
     */
    void updateLikeNum(int parseInt);

    /**
     * 判断类别下是否有图书
     *
     * @param categoryId
     * @return
     */
    int checkByCategory(Integer categoryId);

    /**
     * 根据Id查询包含tag，bookNote子列表的Book实体
     *
     * @param id
     * @return
     */
    BookVO selectByIdWithSubList(Integer id);
}
