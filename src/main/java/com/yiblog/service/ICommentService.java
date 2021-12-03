package com.yiblog.service;

import com.yiblog.common.dto.CommentDto;
import com.yiblog.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YiXia
 * @since 2021-12-01
 */
public interface ICommentService extends IService<Comment> {
    //get comments list by blogId
    List<CommentDto> listByBlogId(Long blogId);
}
