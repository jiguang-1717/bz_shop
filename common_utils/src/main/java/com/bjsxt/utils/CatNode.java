package com.bjsxt.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class CatNode implements Serializable {

    @JsonProperty("n") //在传输的时候name会变为n
    private String name;
    @JsonProperty("i")//在传输的时候item会变为i
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
