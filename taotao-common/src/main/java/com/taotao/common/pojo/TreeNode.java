package com.taotao.common.pojo;

import java.util.List;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午5:00 2017/12/4
 * @Modified By:
 */
public class TreeNode {
    private String id;

    private String text;

    private String state = "closed";

    private String iconCls = "icon-save";

    private List<?> children;


    public TreeNode() {
        super();
    }

    public TreeNode(String id, String text, String state, String iconCls, List<TreeNode> children) {
        super();
        this.id = id;
        this.text = text;
        this.state = state;
        this.iconCls = iconCls;
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state.equals("0")) {
            this.state = "undefined";
        } else {
            this.state = "closed";
        }
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }


    public List<?> getChildren() {
        return children;
    }


    public void setChildren(List<?> children) {
        this.children = children;
    }


    @Override
    public String toString() {
        return "TreeNode [id=" + id + ", text=" + text + ", state=" + state + ", iconCls=" + iconCls + ", children="
                + children + "]";
    }

}

