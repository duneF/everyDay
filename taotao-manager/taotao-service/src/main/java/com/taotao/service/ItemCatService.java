package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.ItemCatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemCatService {

	@Autowired
	private ItemCatMapper mapper;

	public List<EasyUITreeNode> findAllItemCatByParentId(long id) {
		
		return mapper.findAllItemCatByParentId(id);
	}

	

}
