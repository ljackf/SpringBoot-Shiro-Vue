package com.rrrent.theia.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author : jack
 * @date : 2020/7/2
 * @Description:
 */
public class OpenidToken extends UsernamePasswordToken {

    private String pswd="ok";

    private  String  openid;

    public OpenidToken(String openid) {
        this.openid = openid;
    }

    @Override
    public Object getPrincipal() {
        return this.openid;
    }

    /**
     * 默认给固定密码
     * @return
     */
    @Override
    public Object getCredentials() {
        return this.pswd;
    }

    /**
     * 默认给记住
     * @return
     */
    @Override
    public boolean isRememberMe() {
        return Boolean.TRUE;
    }

}
