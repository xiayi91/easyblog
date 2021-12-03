package com.yiblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiblog.common.dto.CommentDto;
import com.yiblog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper
 * </p>
 *
 * @author YiXia
 * @since 2021-12-01
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    //get comments by blogId
    List<CommentDto> listByBlogId(Long blogId);

}
