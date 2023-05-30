package com.jingdong.common.entity;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class TheFreightRisk implements Serializable {
    private boolean selected;
    private String venderId;

    public String getVenderId() {
        return this.venderId;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public void setVenderId(String str) {
        this.venderId = str;
    }
}
