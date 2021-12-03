package com.yiblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author YiXia
 * @since 2021-12-01
 */
@TableName("blog_comment_detail")
@ApiModel(value = "BlogCommentDetail", description = "relationship between comments and blogs")
public class BlogCommentDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      private Long id;

    private Long blogId;

    private Long commentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "BlogCommentDetail{" +
        "id=" + id +
        ", blogId=" + blogId +
        ", commentId=" + commentId +
        "}";
    }
}
