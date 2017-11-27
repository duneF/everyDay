package com.taotao.service;


import com.taotao.mapper.ItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ItemParamService {
	
	@Autowired
	private ItemParamMapper mapper;

	//查询所有的商品规格
	public List<TbItem> findItemParamList(Integer page, Integer rows) {
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		map.put("page", (page-1)*rows);
		map.put("rows", rows);
		return mapper.findItemParamList(map);
	}

	//查询商品规格表中所有的记录数
	public int count() {
		return mapper.count();
	}

	//根据item_cat_id查询商品规格
	public TbItemParam queryItemParamByItemCatId(long id) {
		// TODO Auto-generated method stub
		return mapper.queryItemParamByItemCatId( id);
	}

	//添加商品规格
	public void saveItemParam(TbItemParam param) {
		mapper.saveItemParam(param);
	}

	//删除商品规格
	public void deleteItemParam(long id) {
		//System.out.println(13);
		mapper.deleteItemParam(id);
	}

	//查询一个商品的详细规格
	public TbItemParamItem queryItemParamItem(long id) {
		
		return mapper.queryItemParamItem(id);
	}

}