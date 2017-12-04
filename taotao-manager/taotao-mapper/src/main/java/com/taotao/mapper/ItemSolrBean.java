package com.taotao.mapper;

public class ItemSolrBean {
    private String id;
    private String title;
    private String sell_point;
    private String price;
    private String image;
    private String  item_desc;
    private String  name;

    @Override
    public String toString() {
        return "ItemSolrBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", sell_point='" + sell_point + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", item_desc='" + item_desc + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    private String imageItem;

    public String getImageItem(){
        String [] split=image.split(",");
        return split[0];
    }


    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
