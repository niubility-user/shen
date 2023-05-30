package com.jd.lib.productdetail.core.entitys.warebusiness;

import androidx.annotation.NonNull;

/* loaded from: classes15.dex */
public class WareBasicInfo implements Cloneable {
    public String name;
    public String skuId;
    public String venderId;

    @NonNull
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
