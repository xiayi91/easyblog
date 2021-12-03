package com.yiblog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiblog.common.lang.Result;
import com.yiblog.entity.Blog;
import com.yiblog.service.IBlogService;
import com.yiblog.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author YiXia
 * @since 2021-10-14
 */
@RestController
//@RequestMapping("/blog")
public class BlogController {
    @Autowired
    IBlogService iBlogService;

    @Value("${yiblog.upload.path}")
    private String baseFolderPath;

    @Value("${yiblog.upload.realPath}")
    private String realPath;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {

        Page page = new Page(currentPage, 5);
        IPage pageData = iBlogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = iBlogService.getById(id);
        Assert.notNull(blog, "The blog is deleted");

        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {

        Blog temp = null;
        if(blog.getId() != null) {
            temp = iBlogService.getById(blog.getId());
            // can only edit own blog
            System.out.println(ShiroUtils.getProfile().getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtils.getProfile().getId().longValue(), "No permission to edit");

        } else {

            temp = new Blog();
            temp.setUserId(ShiroUtils.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        iBlogService.saveOrUpdate(temp);

        return Result.succ(null);
    }

    //upload picture
    @PostMapping("/blog/uploadpic")
    public Result upload(HttpServletRequest request, MultipartFile image) {

        StringBuffer url = new StringBuffer();
        url
                .append(request.getScheme())
                .append("://")
                .append(request.getServerName())
//                .append(":")
//                .append(request.getServerPort())
//                .append(request.getContextPath())
                .append(baseFolderPath);
//                .append(filePath);

        String suffix =  image.getOriginalFilename().split("\\.")[1];
        String imgName = UUID.randomUUID().toString().replace("_", "")+"."+suffix;

        try {

            File dest = new File(realPath, imgName);
            FileCopyUtils.copy(image.getBytes(), dest);
            url.append(imgName);

            JSONObject object = new JSONObject();
            object.put("url", url);

            return Result.succ(object);
        } catch (IOException e) {

            System.out.println("Error , uri: {} , caused by: "+request.getRequestURI() + e);
            return Result.fail("Error to upload image");
        }
    }


}

