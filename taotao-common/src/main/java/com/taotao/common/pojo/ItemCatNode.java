package com.taotao.common.pojo;

import java.util.List;

public class ItemCatNode {

	private String n;        //点击分类名称跳转的路径
	private String u;		 //让标签可以点击<a href="/products/1.html">图书、音箱</a>	
	private List<?> i;		 //子节点	
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
	public String getU() {
		return u;
	}
	public void setU(String u) {
		this.u = u;
	}
	public List<?> getI() {
		return i;
	}
	public void setI(List<?> i) {
		this.i = i;
	}
	@Override
	public String toString() {
		return "ItemCatNode [n=" + n + ", u=" + u + ", i=" + i + "]";
	}
	
}
