package com.yiblog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * For the return of user information after a successful login
 */
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
