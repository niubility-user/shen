package com.jingdong.common.widget.popupwindow.entity;

/* loaded from: classes12.dex */
public class BaseCircleButtonInfo {
    public int clickableButtonTextColorRes;
    public int clickableCircleColorRes;
    public boolean enable;
    public String text;
    public int unClickableButtonTextColorRes;
    public int unClickableCirclecolorRes;

    public BaseCircleButtonInfo(String str, boolean z, int i2, int i3, int i4, int i5) {
        this.text = str;
        this.enable = z;
        this.clickableCircleColorRes = i2;
        this.clickableButtonTextColorRes = i3;
        this.unClickableCirclecolorRes = i4;
        this.unClickableButtonTextColorRes = i5;
    }
}
