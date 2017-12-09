package com.taotao.sso1;

import com.google.gson.Gson;
import com.taotao.common.pojo.CookieUtils;
import com.taotao.pojo.SSOT;
import com.taotao.pojo.TbUser;
import com.taotao.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 上午9:34 2017/12/5
 * @Modified By:
 */
@Controller
@RequestMapping("/user")
public class SsoController {
    @Autowired
    private TbUserService tbUserService;

//    @Autowired
//    private JedisPool jedisPool;


    private HashMap<String, String> map;
    private TbUser tbUser;
    private SSOT ssot;
    private String username;
    private String password;
    private String TT_TOKEN;
    private HashMap<String, Object> map1;
    private PrintWriter out;
    private Gson gson;
    private String toJson;
    private Cookie[] cookies;
    private JedisPoolConfig poolConfig;
    private Set<HostAndPort> nodes;
    private JedisCluster cluster;
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    private String key;

    @RequestMapping("/showLogin")
    public String toUserLogin() {

        return "login";
    }

    @RequestMapping("/showRegister")
    public String toRegister() {

        return "register";
    }

    @RequestMapping("/login")
    @ResponseBody
    public HashMap<String, String> login(TbUser user, Model model, HttpServletRequest request, HttpServletResponse response) {
        ssot = new SSOT ();
        map = new HashMap<> ();
        username = user.getUsername ();
        password = user.getPassword ();
        map.put ( "username", username );
        map.put ( "password", password );
        tbUser = tbUserService.findUserByNameAndPasswd ( map );
        TT_TOKEN = UUID.randomUUID ().toString ();
        if (tbUser != null) {
            CookieUtils.setCookie ( request, response, "TT_TOKEN", TT_TOKEN );
            //    设置带存活时间的Cookie
            //   CookieUtils.setCookie(request, response, "TT_TOKEN", TT_TOKEN, 3600, "utf-8");
            tbUser.setPassword ( null );
            poolConfig = new JedisPoolConfig ();
            // 最大连接数
            poolConfig.setMaxTotal ( 1 );
            // 最大空闲数
            poolConfig.setMaxIdle ( 1 );
            // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
            // Could not get a resource from the pool
            poolConfig.setMaxWaitMillis ( 1000 );
            nodes = new LinkedHashSet<HostAndPort> ();
            nodes.add ( new HostAndPort ( "192.168.99.134", 7001 ) );
            nodes.add ( new HostAndPort ( "192.168.99.134", 7002 ) );
            nodes.add ( new HostAndPort ( "192.168.99.134", 7003 ) );
            nodes.add ( new HostAndPort ( "192.168.99.134", 7004 ) );
            nodes.add ( new HostAndPort ( "192.168.99.134", 7005 ) );
            nodes.add ( new HostAndPort ( "192.168.99.134", 7006 ) );
            cluster = new JedisCluster ( nodes, poolConfig );
            cluster.set ( "TT_TOKEN", tbUser.toString () );
            System.out.println ( cluster.get ( "TT_TOKEN" ) );
            try {
                cluster.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            ssot.setStatus ( 200 );
            map.put ( "status", "200" );
            map.put ( "redirect", "http://localhost:8082/portal/index" );
        }
        map.put ( "msg", "账号密码错误" );
        return map;
    }

    @RequestMapping("/token/{_ticket}")
    public void getCookieV(@PathVariable String _ticket, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType ( "text/html;charset=utf-8" );
        map1 = new HashMap<> ();
        map1.put ( "status", 200 );
        map1.put ( "callback", _ticket );
        map1.put ( "data", tbUser );
        gson = new Gson ();
        toJson = gson.toJson ( map1 );
        String callback = request.getParameter ( "callback" );
        String result = callback + "(" + toJson.toString () + ")";
        try {
            out = response.getWriter ();
            out.print ( result );
            out.flush ();
            out.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    @RequestMapping("/logout")
    public String logoutLogin(HttpServletRequest request, HttpServletResponse response) {
        cookies = request.getCookies ();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName ().equals ( "TT_TOKEN" )) {
                    key = cookie.getValue ();
                    break;
                }
            }
        }
        //删除cookie
        Cookie cookie = new Cookie ( "TT_TOKEN", null );
        cookie.setMaxAge ( 0 );
        cookie.setPath ( "/" );
        response.addCookie ( cookie );
        //删除redis
        poolConfig = new JedisPoolConfig ();
        // 最大连接数
        poolConfig.setMaxTotal ( 1 );
        // 最大空闲数
        poolConfig.setMaxIdle ( 1 );
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis ( 1000 );
        nodes = new LinkedHashSet<HostAndPort> ();
        nodes.add ( new HostAndPort ( "192.168.99.134", 7001 ) );
        nodes.add ( new HostAndPort ( "192.168.99.134", 7002 ) );
        nodes.add ( new HostAndPort ( "192.168.99.134", 7003 ) );
        nodes.add ( new HostAndPort ( "192.168.99.134", 7004 ) );
        nodes.add ( new HostAndPort ( "192.168.99.134", 7005 ) );
        nodes.add ( new HostAndPort ( "192.168.99.134", 7006 ) );
        cluster = new JedisCluster ( nodes, poolConfig );
        cluster.del ( "TT_TOKEN" );
        try {
            cluster.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return "redirect:http://localhost:8082/portal/index";
    }


}
