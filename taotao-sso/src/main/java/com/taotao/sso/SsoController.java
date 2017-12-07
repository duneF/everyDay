package com.taotao.sso;

import com.google.gson.Gson;
import com.taotao.common.pojo.HttpRequestUtils;
import com.taotao.pojo.TbUser;
import com.taotao.service.TbUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

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
    private HashMap<String, String> map;
    private TbUser tbUser;
    private Gson gson;
    private String nameJson;
    private JSONObject get;


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
    public void login(TbUser user) {
        map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        tbUser = tbUserService.findUserByNameAndPasswd(map);


        gson = new Gson();
        nameJson = gson.toJson(user.getUsername());

        String url="http://localhost:8082/portal/index";
        if (tbUser != null) {
            //get One HttpClients对象
          //  httpClient = HttpClients.createDefault();
            System.out.println("进入if");
            //生成一个get请求
            get = HttpRequestUtils.httpGet(url);
        }
        System.out.println("SsoElase");
       // return "login";

    }

}
