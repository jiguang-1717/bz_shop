package com.bjsxt.utils;

import java.io.Serializable;
import java.util.List;

public class CatResult implements Serializable {

    //?表示可以为任意类型，给啥就是啥
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
