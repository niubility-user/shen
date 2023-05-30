package com.jingdong.common.widget.popupwindow;

import android.graphics.drawable.Drawable;

/* loaded from: classes12.dex */
public class ListPopupWindowMoudle {
    public String content;
    public int count;
    public Drawable drawable;
    public String imageUrl;
    public String info;
    public boolean isShowRedPoint;
    public String type;

    public ListPopupWindowMoudle(String str, String str2, String str3, String str4) {
        this.imageUrl = str;
        this.info = str2;
        this.type = str3;
        this.content = str4;
    }

    public ListPopupWindowMoudle(Drawable drawable, String str, String str2, String str3) {
        this.drawable = drawable;
        this.info = str;
        this.type = str2;
        this.content = str3;
    }

    public ListPopupWindowMoudle(String str, String str2, boolean z, int i2) {
        this.imageUrl = str;
        this.info = str2;
        this.isShowRedPoint = z;
        this.count = i2;
    }

    public ListPopupWindowMoudle(Drawable drawable, String str, boolean z, int i2) {
        this.drawable = drawable;
        this.info = str;
        this.isShowRedPoint = z;
        this.count = i2;
    }
}
