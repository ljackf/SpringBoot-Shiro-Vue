package com.rrrent.theia.config.shiro;


import com.alibaba.fastjson.JSONObject;
import com.rrrent.theia.config.jwt.JwtConfig;
import com.rrrent.theia.config.jwt.JwtToken;
import com.rrrent.theia.service.LoginService;
import com.rrrent.theia.service.UserService;
import com.rrrent.theia.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Realm 的一个配置管理类 allRealm()方法得到所有的realm
 */
@Component
public class ShiroRealmConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Resource
    private JwtConfig jwtConfig;
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    /**
     * 配置所有自定义的realm,方便起见,应对可能有多个realm的情况
     */
    public List<Realm> allRealm() {
        List<Realm> realmList = new LinkedList<>();
        AuthorizingRealm jwtRealm = jwtRealm();
        realmList.add(jwtRealm);
        realmList.add(userRealm());
        return Collections.unmodifiableList(realmList);
    }


    /**
     * 自定义 JWT的 Realm
     * 重写 Realm 的 supports() 方法是通过 JWT 进行登录判断的关键
     */
    public AuthorizingRealm jwtRealm() {
        AuthorizingRealm jwtRealm = new AuthorizingRealm() {
            /**
             * 注意坑点 : 必须重写此方法，不然Shiro会报错
             * 因为创建了 JWTToken 用于替换Shiro原生 token,所以必须在此方法中显式的进行替换，否则在进行判断时会一直失败
             */
            @Override
            public boolean supports(AuthenticationToken token) {
                return token instanceof JwtToken;
            }

            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
                return new SimpleAuthorizationInfo();
            }

            /**
             * 校验 验证token逻辑
             */
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
                String jwtToken = (String) token.getCredentials();
//                String wxOpenId = jwtConfig.getWxOpenIdByToken(jwtToken);
//                String sessionKey = jwtConfig.getSessionKeyByToken(jwtToken);
                String wxOpenId = "hhhhhhhhhh";
                String sessionKey = "ssssssssssss";
                if (StringUtils.isEmpty(wxOpenId)) {
                    throw new AuthenticationException("user account not exits , please check your token");
                }
                if (StringUtils.isEmpty(sessionKey)) {
                    throw new AuthenticationException("sessionKey is invalid , please check your token");
                }
//                if (!jwtConfig.verifyToken(jwtToken)) {
//                    throw new AuthenticationException("token is invalid , please check your token");
//                }
                return new SimpleAuthenticationInfo(token, token, getName());
            }
        };
        jwtRealm.setCredentialsMatcher(credentialsMatcher());
        return jwtRealm;
    }

    public AuthenticatingRealm userRealm(){
        AuthorizingRealm userRealm = new AuthorizingRealm() {

            @Override
            @SuppressWarnings("unchecked")
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
                Session session = SecurityUtils.getSubject().getSession();
                //查询用户的权限
                JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
                logger.info("permission的值为:" + permission);
                logger.info("本用户权限为:" + permission.get("permissionList"));
                //为当前用户设置角色和权限
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
                return authorizationInfo;
            }

            /**
             * 验证当前登录的Subject
             * LoginController.login()方法中执行Subject.login()时 执行此方法
             */
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
                String loginName = (String) authcToken.getPrincipal();
                // 获取用户密码
                String password = new String((char[]) authcToken.getCredentials());
                JSONObject user = loginService.getUser(loginName, password);
                if (user == null) {
                    //没找到帐号
                    throw new UnknownAccountException();
                }
                //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                        user.getString("mobile"),
                        user.getString("password"),
                        //ByteSource.Util.bytes("salt"), salt=mobile+salt,采用明文访问时，不需要此句
                        getName()
                );
                //session中不需要保存密码
                user.remove("password");
                //将用户信息放入session中
                SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
                return authenticationInfo;
            }
        };
        return userRealm;
    }

    /**
     * 注意坑点 : 密码校验 , 这里因为是JWT形式,就无需密码校验和加密,直接让其返回为true(如果不设置的话,该值默认为false,即始终验证不通过)
     */
    private CredentialsMatcher credentialsMatcher() {
        return (token, info) -> true;
    }
}