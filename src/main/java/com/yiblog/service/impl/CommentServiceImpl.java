package com.yiblog.service.impl;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yiblog.common.dto.CommentDto;
import com.yiblog.entity.BlogCommentDetail;
import com.yiblog.entity.Comment;
import com.yiblog.entity.User;
import com.yiblog.mapper.BlogCommentDetailMapper;
import com.yiblog.mapper.CommentMapper;
import com.yiblog.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yiblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YiXia
 * @since 2021-12-01
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    CommentMapper commentMapper;

    public List<CommentDto> listByBlogId(Long blogId){

        List<CommentDto> comments = commentMapper.listByBlogId(blogId);

        return comments;
    };

}
