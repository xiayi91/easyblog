package com.yiblog.shiro;

import org.apache.shiro.authc.AuthenticationToken;


/**
 * shiro supports UsernamePasswordToken by default,
 * so here I customize a JwtToken to complete shiro's supports method
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
