package com.yiblog.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yiblog.entity.Comment;

public class CommentDto extends Comment {


    //userName
    @TableField(exist = false)
    private String userName;

    //blogId
    @TableField(exist = false)
    private Long blogId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) { this.blogId = blogId; }

}
