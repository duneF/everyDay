package com.portal.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taotao.common.pojo.HttpRequestUtils;
import com.taotao.mapper.ItemSolrBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午10:04 2017/12/3
 * @Modified By:
 */
@Controller
public class indexController {private Object httpGetArray;
    private String q;
    private String jsonArray;
    private Gson gson;
    private List<ItemSolrBean> itemList;

    @RequestMapping("/portal/index")
    public String toIndex(Model model){
        httpGetArray = HttpRequestUtils.httpGetArray("http://localhost:8081/portal/mainAd");
        model.addAttribute("ad1",httpGetArray);

        return "index";
    }
    @RequestMapping("/search")
    public String search(String q,Model m){

        try {
            q = new String(q.getBytes("iso-8859-1"), "utf-8");
            System.out.println("参数q:"+q);
            jsonArray = (String) HttpRequestUtils.httpGetArray("http://localhost:8083/search/q?q="+q);

            //把json还原成集合
            gson = new Gson();
            itemList = gson.fromJson(jsonArray, new TypeToken<List<ItemSolrBean>>() {
            }.getType());

            m.addAttribute("itemList",itemList);
            //查询到itemList，设置到域中
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "search";
    }
}
