package com.yiblog.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yiblog.common.dto.LogupDto;
import com.yiblog.common.lang.Result;
import com.yiblog.entity.User;
import com.yiblog.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 *
 * @author YiXia
 * @since 2021-10-14
 */
@Api(tags="User")
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService iUserService;

    @ApiOperation("userinfo")
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = iUserService.getById(1L);
        return Result.succ(user);
    }

    /**
     * Using @Validated:
     * if the entity does not meet the requirements,
     * MethodArgumentNotValidException will be thrown.
     * @param logupDto
     * @param response
     * @return
     */
    @ApiOperation("save user")
    @PostMapping("/save")
    @ResponseBody
    public Result save(@Validated @RequestBody LogupDto logupDto, HttpServletResponse response) {
        User user = iUserService.getOne(new QueryWrapper<User>().eq("username", logupDto.getUsername()));
        if(user==null){
            user = new User();
            user.setUsername(logupDto.getUsername());
            user.setPassword(SecureUtil.md5(logupDto.getPassword()));
            user.setEmail(logupDto.getEmail());
            user.setCreated(LocalDateTime.now());
            user.setStatus(0);
//            user.setAvatar(logupDto.getAvatar());
            user.setAvatar("http://54.177.15.182/files/989769f8-4c12-4689-bed9-779e214d8fa4.jpg");
            iUserService.saveOrUpdate(user);
            return Result.succ(user);
        }else{
            return Result.fail("The user exists");
        }
    }

}

