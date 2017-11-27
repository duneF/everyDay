package com.taotao.web;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品规格的Controller
 * @author 15034585292
 *
 */
@Controller
public class ItemParamController {

	@Autowired
	private ItemParamService service;
	
	//查询所有的商品规格
	@RequestMapping("/item/param/list")
	@ResponseBody
	public Map<String,Object> findItemList(Integer page,Integer rows){
		
		
		List<TbItem> list = service.findItemParamList(page,rows);
		int count = service.count();
		Map<String,Object> map = new HashMap<>();
		map.put("total", count);
		map.put("rows", list);

		return map;
	}
	
	//根据item_cat_id查询商品规格，返回json对象
	@RequestMapping("/item/param/query/itemcatid/{id}")
	@ResponseBody
	public HashMap<String,Object> queryItemParamByItemCatId(@PathVariable long id,HttpServletResponse response){
		
		response.setContentType("text/json;charset=utf-8");
		
		TbItemParam param = service.queryItemParamByItemCatId(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(param == null){
			map.put("data", null);
			return map;
		}
		map.put("status", 200);
		map.put("data", param);
		return map;
		/*//System.out.println(param);
		JSONObject json = new JSONObject(param);
		//json.put("data",param);
		response.getWriter().print(json.toString());*/	
	}
	
	
	//添加商品规格
	@RequestMapping("/item/param/save/{id}")
	@ResponseBody
	public Map<String, Object> saveItemParam(@PathVariable long id,@RequestBody String paramData) throws UnsupportedEncodingException{
		//System.out.println(id);
		//paramData = new String(paramData.getBytes("ISO-8859-1"), "UTF-8");
		//System.out.println(paramData);
		
		Date date = new Date();
		//封装数据
		TbItemParam param = new TbItemParam();
		param.setCreated(date);
		param.setUpdated(date);
		param.setItemCatId(id);
		param.setParamData(paramData);
		//调用service层的方法	
		service.saveItemParam(param);
		//返回值，及页面显示添加成功
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("status", 200);
		return map;
		
	}
	
	//删除商品规格
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public Map<String, Object> deleteItemParam(String ids){
		//System.out.println(ids);		
		String[] arr = ids.split(",");
		for (int i = 0; i < arr.length; i++) {
			//System.out.println(arr[i]);
			service.deleteItemParam(Long.parseLong(arr[i]));
		}
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("status", 200); 
		return map;
	}
	
	
	//查询一个商品的详细规格
	@RequestMapping("/rest/item/param/item/query/{id}")
	@ResponseBody
	public Map<String, Object> uqeryItemParamItem(@PathVariable long id){
		TbItemParamItem param = service.queryItemParamItem(id);
		Map<String, Object> map = new HashMap<String , Object>();
		map.put("status", 200); 
		map.put("data", param); 
		return map;
	}
}
