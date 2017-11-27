package com.taotao.common.pojo;

import java.util.List;

public class EasyUITreeNode {

	private long id;
	private String text;
	private String iconCls = "icon-save";
	private String state;
	private List<EasyUITreeNode> list;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		//System.out.println(state);
		if(state.equals("0")){
			this.state = "undefined";
		}else{
			this.state = "closed";
		}
		
	}
	public List<EasyUITreeNode> getList() {
		return list;
	}
	public void setList(List<EasyUITreeNode> list) {
		this.list = list;
	}
	

	
}
