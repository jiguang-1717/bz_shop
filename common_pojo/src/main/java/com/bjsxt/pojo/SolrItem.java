package com.bjsxt.pojo;

import java.io.Serializable;

/**
 * 针对搜索创建的实体类
 */
public class SolrItem implements Serializable {

    private long id;
    private String title;
    private String sell_point;
    private long price;
    private String image;
    private String name;
    private String item_desc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    @Override
    public String toString() {
        return "SolrItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", sell_point='" + sell_point + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", item_desc='" + item_desc + '\'' +
                '}';
    }
}
