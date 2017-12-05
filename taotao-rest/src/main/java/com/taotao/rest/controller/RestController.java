package com.taotao.rest.controller;

import com.taotao.common.pojo.TreeNode;
import com.taotao.pojo.IndexNode;
import com.taotao.service.ItemService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:dunef
 * @Description: get Fist page data service
 * @Date:Created in 下午4:34 2017/12/4
 * @Modified By:
 */
@Controller
@RequestMapping("/rest")
public class RestController {
    @Autowired
    private ItemService itemService;

    private HashMap<String, Object> map;
    private ArrayList<IndexNode> data;
    private List<TreeNode> parent;
    private IndexNode in;
    private ArrayList<IndexNode> sonNodes;
    private List<TreeNode> son;
    private IndexNode son_node;
    private ArrayList<IndexNode> grandsonNodes;
    private ArrayList<String> child;
    private List<TreeNode> grandson;
    private IndexNode grandson_node;
    private JSONObject obj;

    @RequestMapping("/indexJson")
    public void indexCatJson(@RequestParam(defaultValue = "0")Integer id, HttpServletResponse response){
        System.out.println("动态，动态");
        response.setContentType("text/html;charset=utf-8");

        map = new HashMap<>();
        //所有一级分类
        data = new ArrayList<>();
        parent = itemService.findTreeNodeByid(id);


        for (TreeNode item : parent) {
            in = new IndexNode();
            in.setU("/products/"+item.getId()+".html");
            in.setN("<a href='/products/"+item.getId()+".html'>"+item.getText()+"</a>");


            sonNodes = new ArrayList<>();

            //查询并添加子节点
            son = itemService.findTreeNodeByid(Integer.parseInt(item.getId()));
            for (TreeNode son_item : son) {
                son_node = new IndexNode();
                son_node.setU("/products/"+son_item.getId()+".html");
                son_node.setN("<a href='/products/"+son_item.getId()+".html'>"+son_item.getText()+"</a>");

                //添加子菜单到父级中
                sonNodes.add(son_node);

                grandsonNodes = new ArrayList<>();
                //查询孙节点
                grandson = itemService.findTreeNodeByid(Integer.parseInt(son_item.getId()));
                child = new ArrayList<>();
                for (TreeNode grandson_item : grandson) {
                    grandson_node = new IndexNode();
                    grandson_node.setU("/products/"+grandson_item.getId()+".html");
                    grandson_node.setN("<a href='/products/"+grandson_item.getId()+".html'>"+grandson_item.getText()+"</a>");

                    //添加孙节点到孙集合
                    grandsonNodes.add(grandson_node);
                    child.add("/products/"+grandson_item.getId()+".html|"+grandson_item.getText());
                    son_node.setI(child);
                }
            }
            in.setI(sonNodes);
            data.add(in);
        }
        map.put("data",data);
        obj = new JSONObject(map);
        try {
            response.getWriter().write("category.getDataService("+obj.toString()+")");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
