package com.taotao.pojo;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午5:03 2017/12/4
 * @Modified By:
 */
public class IndexNode {
    private String u;	//父分类
    private String n;	//链接
    private List<?> i;	//子分类


    public String getU() {
        return u;
    }
    public void setU(String u) {
        this.u = u;
    }
    public String getN() {
        return n;
    }
    public void setN(String n) {
        this.n = n;
    }

    public List<?> getI() {
        return i;
    }
    public void setI(List<?> i) {
        this.i = i;
    }
    @Override
    public String toString() {
        return "IndexNode [u=" + u + ", n=" + n + ", i=" + i + "]";
    }
    public IndexNode() {
        super();
    }
    public IndexNode(String u, String n, List<IndexNode> i) {
        super();
        this.u = u;
        this.n = n;
        this.i = i;
    }
}