package com.taotao.mapper;



import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;

import java.util.HashMap;
import java.util.List;

public interface ItemParamMapper {
	//查询所有的商品规格
	List<TbItem> findItemParamList(HashMap<String, Integer> map);
	//查询商品规格表中所有的记录数
	int count();
	//根据item_cat_id查询商品规格
	TbItemParam queryItemParamByItemCatId(long id);
	//添加商品规格
	void saveItemParam(TbItemParam param);
	//删除商品规格
	void deleteItemParam(long id);
	//查询一个商品的详细规格
	TbItemParamItem queryItemParamItem(long id);

}
