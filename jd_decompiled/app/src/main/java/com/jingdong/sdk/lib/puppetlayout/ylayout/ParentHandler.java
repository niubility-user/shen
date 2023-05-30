package com.jingdong.sdk.lib.puppetlayout.ylayout;

/* loaded from: classes8.dex */
public abstract class ParentHandler<T> {
    private T floorData = null;

    public T getFloorData() {
        return this.floorData;
    }

    public abstract String getRealValue(String str);

    public void setFloorData(T t) {
        this.floorData = t;
    }
}
