package com.jingdong.app.mall.m.b;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class a implements Serializable {
    private static final long serialVersionUID = 6386237951842844681L;
    private String id;
    private boolean isSelect;
    private String tabValue;

    public a() {
    }

    public String getId() {
        return this.id;
    }

    public String getTabValue() {
        return this.tabValue;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public void setTabValue(String str) {
        this.tabValue = str;
    }

    public a(boolean z, String str, String str2) {
        this.isSelect = z;
        this.id = str;
        this.tabValue = str2;
    }
}
