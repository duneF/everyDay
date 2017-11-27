package com.taotao.web;

import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IremPageController {

	@Autowired
	private ItemService service;
	
	//进入项目之后直接进入首页
	@RequestMapping("/")
	public String index(){
		
		return "index";
	}
	
	//首页页面跳转的方法
	@RequestMapping("/{page}")
	public String showPage(@PathVariable String page){
		
		return page;
	}
	
	//查询所有的商品
	@RequestMapping("/item/list")
	@ResponseBody
	public Map<String,Object> findItemList(Integer page,Integer rows){
		//System.out.println(111);
		
		List<TbItem> list = service.findItemList(page,rows);
		int count = service.count();
		Map<String,Object> map = new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
//		System.out.println(page);
//		System.out.println(rows);
//		System.out.println(list.size());
//		System.out.println(list.toString());
		return map;
	}
}
