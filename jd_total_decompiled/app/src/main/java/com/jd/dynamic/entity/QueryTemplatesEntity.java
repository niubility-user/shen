package com.jd.dynamic.entity;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes13.dex */
public class QueryTemplatesEntity implements Serializable {
    private static final long serialVersionUID = 101;
    private List<Template> data;
    private String expireTime = "0";

    public List<Template> getData() {
        return this.data;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public void setData(List<Template> list) {
        this.data = list;
    }

    public void setExpireTime(String str) {
        this.expireTime = str;
    }
}
