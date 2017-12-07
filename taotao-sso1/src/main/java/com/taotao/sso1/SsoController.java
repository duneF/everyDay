package com.taotao.sso1;

import com.google.gson.Gson;
import com.taotao.common.pojo.CookieUtils;
import com.taotao.pojo.SSOT;
import com.taotao.pojo.TbUser;
import com.taotao.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
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
    private String ticket;
    private Cookie[] cookies;
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
    public SSOT login(TbUser user, Model model, HttpServletRequest request, HttpServletResponse response) {
        ssot = new SSOT();
        map = new HashMap<>();
        username = user.getUsername();
        password = user.getPassword();
        map.put("username", username);
        map.put("password", password);
        tbUser = tbUserService.findUserByNameAndPasswd(map);
        TT_TOKEN = UUID.randomUUID().toString();
        if (tbUser != null) {
            CookieUtils.setCookie(request, response, "TT_TOKEN", TT_TOKEN);
         //   CookieUtils.setCookie(request, response, "TT_TOKEN", TT_TOKEN, 3600, "utf-8");
            ssot.setStatus(200);
            model.addAttribute("redirect", "http://localhost:8082/portal/index");
        }
        ssot.setMsg("账号密码错误");
        return ssot;
    }

    @RequestMapping("/token/{_ticket}")
    public void getCookieV(@PathVariable String _ticket, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");

        map1 = new HashMap<>();
        map1.put("status",200);
        map1.put("callback",_ticket);
        map1.put("data",tbUser);
        gson = new Gson();
        toJson = gson.toJson(map1);
        String callback=request.getParameter("callback");
        String result=callback+"("+toJson.toString()+")";
        try {
            out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/logout")
    public String logoutLogin(HttpServletRequest request,HttpServletResponse response){
        cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("TT_TOKEN")){
                    System.out.println(cookie.getName()+"Cookie遍历");
                    System.out.println(cookie.getValue()+"Cookie遍历Value");
                    key = cookie.getValue();

                break;
                }
            }
        }
        //删除cookie
        Cookie cookie = new Cookie("TT_TOKEN",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);



       // CookieUtils.setCookie(request,response,"JSESSIONID",null);
      //  CookieUtils.deleteCookie(request,response,"TT_TOKEN");
       // CookieUtils.deleteCookie(request,response,"JSESSIONID");
      //  CookieUtils.setCookie(request, response, "TT_TOKEN", TT_TOKEN, -1, "utf-8");
//        Cookie cookie = new Cookie("key",null);
//        cookie.setMaxAge(0);
//        cookie.setPath("/");

        return "redirect:http://localhost:8082/portal/index";
    }




}
