package com.taotao.mapper;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;


public interface ItemCatMapper {

	List<EasyUITreeNode> findAllItemCatByParentId(long id);

}
