package com.taotao.mapper;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;


public interface ItemCatMapper {

	List<EasyUITreeNode> findAllItemCatByParentId(long id);
	

}
