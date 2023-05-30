package com.jingdong.common.sample.jshopmember.entity;

import com.jingdong.common.widget.popupwindow.ListPopupWindowMoudle;

/* loaded from: classes6.dex */
public class JShopListPopupWindowMoudle extends ListPopupWindowMoudle {
    public static final int TYPE_SHOW_NONE = 2;
    public static final int TYPE_SHOW_NUMBER = 0;
    public static final int TYPE_SHOW_NUMBER99 = 4;
    public static final int TYPE_SHOW_REDDOT = 1;
    public int type;

    public JShopListPopupWindowMoudle(String str, String str2, boolean z, int i2) {
        super(str, str2, z, i2);
    }

    public void setType(int i2) {
        this.type = i2;
    }
}
