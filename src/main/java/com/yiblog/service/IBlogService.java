package com.yiblog.service;

import com.yiblog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YiXia
 * @since 2021-10-14
 */
public interface IBlogService extends IService<Blog> {

}
