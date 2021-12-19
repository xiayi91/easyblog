package com.yiblog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.yiblog.common.dto.CommentDto;
import com.yiblog.common.lang.Result;
import com.yiblog.entity.BlogCommentDetail;
import com.yiblog.entity.Comment;
import com.yiblog.service.IBlogCommentDetailService;
import com.yiblog.service.ICommentService;
import com.yiblog.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author YiXia
 * @since 2021-12-01
 */
@Api(tags="Comment")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService iCommentService;

    @Autowired
    IBlogCommentDetailService iBlogCommentDetailService;

    @ApiOperation("comments")
    @GetMapping("/comment")
    public Result list(@RequestParam(name = "id") Long blogId) {

        //Page page = new Page(currentPage, 10);
        List<CommentDto> comments = iCommentService.listByBlogId(blogId);

        //IPage pageData = iCommentService.page(page, new QueryWrapper<Comment>().in("id", ids).orderByDesc("id"));

        return Result.succ(comments);
    }

    @ApiOperation("comments edition")
    @PostMapping("/edit")
    public Result edit(@Validated @RequestBody CommentDto commentDto) {
        //save comment
        Comment temp = null;
        temp = new Comment();
        temp.setUserId(ShiroUtils.getProfile().getId());
        temp.setCreated(LocalDateTime.now());

        BeanUtil.copyProperties(commentDto, temp, "id", "userId", "created", "userName", "blogId");
        iCommentService.saveOrUpdate(temp);

        //save blog_comment_detail
        if (temp.getId() != null) {
            BlogCommentDetail blogCommentDetail = new BlogCommentDetail();
            blogCommentDetail.setBlogId(commentDto.getBlogId());
            blogCommentDetail.setCommentId(temp.getId());
            iBlogCommentDetailService.saveOrUpdate(blogCommentDetail);
        }

        return Result.succ(null);
    }

}

