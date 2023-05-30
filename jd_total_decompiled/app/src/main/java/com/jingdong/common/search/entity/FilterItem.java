package com.jingdong.common.search.entity;

import java.io.Serializable;

/* loaded from: classes6.dex */
public class FilterItem implements Serializable {
    public String key;
    public String value;

    public FilterItem(String str, String str2) {
        this.key = str;
        this.value = str2;
    }

    public FilterItem() {
    }
}
