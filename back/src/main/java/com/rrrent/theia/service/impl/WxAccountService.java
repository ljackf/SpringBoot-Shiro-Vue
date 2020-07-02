package com.rrrent.theia.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.bo.Code2SessionResponse;
import com.rrrent.theia.bo.WxAccount;
import com.rrrent.theia.config.jwt.JwtConfig;
import com.rrrent.theia.dto.TokenDTO;
import com.rrrent.theia.service.UserService;
import com.rrrent.theia.service.WxAppletService;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;

/**
 * Created by EalenXie on 2018/11/26 10:50.
 * 实体 行为描述
 */
@Service
public class WxAccountService implements WxAppletService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${wx.applet.appid}")
    private String appid;

    @Value("${wx.applet.appsecret}")
    private String appSecret;

    @Resource
    private UserService userService;

    @Resource
    private JwtConfig jwtConfig;

    /**
     * 微信的 code2session 接口 获取微信用户信息
     * 官方说明 : https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     */
    private String code2Session(String jsCode) {
        String code2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("appid", appid);
        params.add("secret", appSecret);
        params.add("js_code", jsCode);
        params.add("grant_type", "authorization_code");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(code2SessionUrl).queryParams(params);
        URI code2Session = builder.build().encode().toUri();
        return restTemplate.exchange(code2Session, HttpMethod.GET, new HttpEntity<String>(new HttpHeaders()), String.class).getBody();
    }


    /**
     * 微信小程序用户登陆，完整流程可参考下面官方地址，本例中是按此流程开发
     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html
     *
     * @param code 小程序端 调用 wx.login 获取到的code,用于调用 微信code2session接口
     * @return 返回后端 自定义登陆态 token  基于JWT实现
     */
    @Override
    public TokenDTO wxUserLogin(String code) {
        //1 . code2session返回JSON数据
        String resultJson = code2Session(code);
        //2 . 解析数据
        Code2SessionResponse response = JSONObject.toJavaObject(JSONObject.parseObject(resultJson),
                Code2SessionResponse.class);
        if (!response.getErrcode().equals("0"))
            throw new AuthenticationException("code2session失败 : " + response.getErrmsg());
        else {
            //3 . 先从本地数据库中查找用户是否存在
            JSONObject wxAccount = userService.getbyWxOpenId(response.getOpenid());
            if (wxAccount == null) {
                wxAccount.put("openId",response.getOpenid());    //不存在就新建用户
            }
            //4 . 更新sessionKey和 登陆时间
            wxAccount.put("sessionKey",response.getSession_key());
            userService.addUser(wxAccount);
            //5 . JWT 返回自定义登陆态 Token
            String token = jwtConfig.createTokenByWxAccount(wxAccount);
            return new TokenDTO(token);
        }
    }
}
