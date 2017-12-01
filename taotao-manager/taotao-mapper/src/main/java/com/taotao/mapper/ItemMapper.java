package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

import java.util.*;

public interface ItemMapper {

	//查询所有的商品，分页显示
	List<TbItem> findItemList(Map<String, Integer> map);
	//查询商品的总记录数
	int count();
	//添加商品
	void saveItem(TbItem item);
	//添加商品描述
	void saveItemDesc(TbItemDesc itemDesc);
	//添加商品规格
	void saveItemParamItem(TbItemParamItem tbItemParamItem);
	//删除商品
	void deleteItem(long parseLong);
	//删除商品描述
	void deleteItemDesc(long parseLong);
	//删除商品详细的规格
	void deleteItemParamItem(long parseLong);
	//通过商品ID查询商品描述
	TbItemDesc queryItemDescByItemId(long id);
	//修改商品
	void updateItem(TbItem item);
	//修改商品描述
	void updateItemDesc(TbItemDesc itemDesc);
	//修改商品规格
	void updateItemParamItem(TbItemParamItem tbItemParamItem);
	//商品下架
	void updateItemStatus(TbItem item);
	//商品上架
	void updateItemStatusToOne(TbItem item);



//	添加信息到solr索引库
	List<ItemSolrBean>findAllByTbitem();
}
