package com.taotao.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ItemController {
	
	@Autowired
	ItemService service;
	
	//添加商品
	@RequestMapping("/item/save")
	@ResponseBody
	public Map<String , Object> saveItem(TbItem item, String desc, String itemParams){
		//System.out.println(itemParams);
		service.saveItem(item,desc,itemParams);
		//service.saveItem(item,desc,itemParams);
		Map<String , Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	//删除商品
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public Map<String , Object> deleteItem(String ids){
		System.out.println(ids);
		service.deleteItem(ids);
		Map<String , Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	//修改商品
	@RequestMapping("rest/page/item-edit")
	public String editItem(){
		
		return "item-edit";
	}

	//查询商品描述
	@RequestMapping("/rest/item/query/item/desc/{id}")
	@ResponseBody
	public Map<String , Object> queryItemDescByItemId(@PathVariable long id){
		//System.out.println(id);
		TbItemDesc desc = service.queryItemDescByItemId(id);
		//System.out.println("desc"+desc);
		Map<String , Object> map = new HashMap<>();
		map.put("status", 200);
		map.put("data", desc);
		return map;
	}
	
	//修改商品
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public Map<String , Object> updateItem(TbItem item,String desc,String itemParams){
		System.out.println(item);
		System.out.println(desc);
		System.out.println(itemParams);
		System.out.println(123);
		
		service.updateItem(item,desc,itemParams);
		
		Map<String , Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	//商品下架
	@RequestMapping("/rest/item/instock")
	@ResponseBody
	public Map<String , Object> updateItemStatus(String ids){
		//System.out.println(ids);
		service.updateItemStatus(ids);
		Map<String , Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
	
	//商品上架
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public Map<String , Object> updateItemStatusToOne(String ids){
		//System.out.println(ids);
		service.updateItemStatusToOne(ids);
		Map<String , Object> map = new HashMap<>();
		map.put("status", 200);
		return map;
	}
}
