package com.taotao.common.pojo;

/**
 * @Author:dunef
 * @Description:
 * @Date:Created in 下午3:21 2017/12/3
 * @Modified By:
 */
public class Ad {
    private String srcB;
    private String height;
    private String alt;
    private String width;
    private String src;
    private String widthB;
    private String href;
    private String heightB;

    @Override
    public String toString() {
        return "Ad{" +
                "srcB='" + srcB + '\'' +
                ", height='" + height + '\'' +
                ", alt='" + alt + '\'' +
                ", width='" + width + '\'' +
                ", src='" + src + '\'' +
                ", widthB='" + widthB + '\'' +
                ", href='" + href + '\'' +
                ", heightB='" + heightB + '\'' +
                '}';
    }

    public String getSrcB() {
        return srcB;
    }

    public void setSrcB(String srcB) {
        this.srcB = srcB;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getWidthB() {
        return widthB;
    }

    public void setWidthB(String widthB) {
        this.widthB = widthB;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getHeightB() {
        return heightB;
    }

    public void setHeightB(String heightB) {
        this.heightB = heightB;
    }
}
