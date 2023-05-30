package com.tencent.tencentmap.mapsdk.maps.interfaces;

/* loaded from: classes9.dex */
public interface Selectable {

    /* loaded from: classes9.dex */
    public interface OnSelectedListener<T> {
        void onSelected(T t);
    }

    boolean isSelected();

    void setSelected(boolean z);

    <T> void setSelectedListener(OnSelectedListener<T> onSelectedListener);
}
