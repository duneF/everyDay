package com.taotao.service;


import com.taotao.common.pojo.IDUtils;
import com.taotao.common.pojo.TreeNode;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.mapper.ItemSolrBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ItemService {

	@Autowired
	private ItemMapper mapper;

	public List<TbItem> findItemList(Integer page, Integer rows) {
		Map<String, Integer> map = new HashMap<String, Integer> ();
		map.put ( "page", (page - 1) * rows );
		map.put ( "rows", rows );
		return mapper.findItemList ( map );
	}

	//统计总记录数
	public int count() {

		return mapper.count ();
	}

	//添加商品
	public void saveItem(TbItem item, String desc, String itemParams) {
		//设置主键
		long id = IDUtils.genItemId ();
		item.setId ( id );
		//设置时间
		Date date = new Date ();
		item.setCreated ( date );
		item.setStatus ( Byte.parseByte ( "1" ) );
		item.setUpdated ( date );

		TbItemDesc itemDesc = new TbItemDesc ();
		itemDesc.setItemId ( id );
		itemDesc.setItemDesc ( desc );
		itemDesc.setCreated ( date );
		itemDesc.setUpdated ( date );

		TbItemParamItem tbItemParamItem = new TbItemParamItem ();
		tbItemParamItem.setCreated ( date );
		tbItemParamItem.setUpdated ( date );
		tbItemParamItem.setItemId ( id );
		tbItemParamItem.setParamData ( itemParams );


//		System.out.println(item);
//		System.out.println(itemDesc);

		mapper.saveItem ( item );
		mapper.saveItemDesc ( itemDesc );
		mapper.saveItemParamItem ( tbItemParamItem );
	}

	//删除商品
	public void deleteItem(String ids) {
		String[] array = ids.split ( "," );
		for (int i = 0; i < array.length; i++) {
			//删除商品描述
			mapper.deleteItemDesc ( Long.parseLong ( array[i] ) );
			//删除商品的详细规格
			mapper.deleteItemParamItem ( Long.parseLong ( array[i] ) );
			//删除商品
			mapper.deleteItem ( Long.parseLong ( array[i] ) );
		}
	}

	//通过商品ID查询商品描述
	public TbItemDesc queryItemDescByItemId(long id) {

		return mapper.queryItemDescByItemId ( id );
	}

	//修改商品
	public void updateItem(TbItem item, String desc, String itemParams) {
		//设置时间
		Date date = new Date ();
		item.setCreated ( date );
		item.setStatus ( Byte.parseByte ( "1" ) );
		item.setUpdated ( date );
		//获取商品的ID
		long id = item.getId ();

		TbItemDesc itemDesc = new TbItemDesc ();
		itemDesc.setItemId ( id );
		itemDesc.setItemDesc ( desc );
		itemDesc.setCreated ( date );
		itemDesc.setUpdated ( date );

		TbItemParamItem tbItemParamItem = new TbItemParamItem ();
		tbItemParamItem.setCreated ( date );
		tbItemParamItem.setUpdated ( date );
		tbItemParamItem.setItemId ( id );
		tbItemParamItem.setParamData ( itemParams );
		mapper.updateItem ( item );
		mapper.updateItemDesc ( itemDesc );
		mapper.updateItemParamItem ( tbItemParamItem );
	}

	//商品下架
	public void updateItemStatus(String ids) {
		String[] arr = ids.split ( "," );
		for (int i = 0; i < arr.length; i++) {
			//System.out.println("i"+arr[i]);
			long id = Long.parseLong ( arr[i] );
			TbItem item = new TbItem ();
			item.setId ( id );
			byte status = Byte.parseByte ( "2" );
			item.setStatus ( status );
			mapper.updateItemStatus ( item );
		}
	}

	//商品上架
	public void updateItemStatusToOne(String ids) {
		String[] arr = ids.split ( "," );
		for (int i = 0; i < arr.length; i++) {
			long id = Long.parseLong ( arr[i] );
			TbItem item = new TbItem ();
			item.setId ( id );
			byte status = Byte.parseByte ( "1" );
			item.setStatus ( status );
			mapper.updateItemStatusToOne ( item );
		}
	}

	//删除商品
//	public void deleteItem(String ids) {
//		String[] array = ids.split(",");
//		for (int i = 0; i < array.length; i++) {
//			//删除商品描述
//			mapper.deleteItemDesc(Long.parseLong(array[i]));
//			//删除商品的详细规格
//			mapper.deleteItemParamItem(Long.parseLong(array[i]));
//			//删除商品
//			mapper.deleteItem(Long.parseLong(array[i]));
//		}
//	}


	//导出到Excel
//	public List<TbItem> outToExcel(String ids) {
//		//new HashMap<> (  )
//		String[] array = ids.split ( "," );
//		for (int i = 0; i < array.length; i++) {
////			//删除商品描述
////			mapper.deleteItemDesc(Long.parseLong(array[i]));
////			//删除商品的详细规格
////			mapper.deleteItemParamItem(Long.parseLong(array[i]));
////			//删除商品
////			mapper.deleteItem(Long.parseLong(array[i]));
////		}
//			System.out.println ("outToExcel"+mapper.outToExcel (Long.parseLong ( array[i] ) ).toString ());
//
//			return mapper.outToExcel ( Long.parseLong ( array[i] ) );
//		}
//		return null;
//	}
	//public List<TbItem> outToExcel(String ids) {
	public List<TbItem> outToExcel() {
		//new HashMap<> (  )
		//System.out.println (mapper.outToExcel ().toString ());
			return mapper.outToExcel();
	}


	//添加到solr索引库
	public List<ItemSolrBean>findAllByTbitem(){
		return mapper.findAllByTbitem();
	}

    public List<TreeNode> findTreeNodeByid(Integer id) {
		return mapper.findTreeNodeByid(id);
    }
}