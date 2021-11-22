package com.yiblog.service.impl;

import com.yiblog.entity.Blog;
import com.yiblog.mapper.BlogMapper;
import com.yiblog.service.IBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YiXia
 * @since 2021-10-14
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

}
