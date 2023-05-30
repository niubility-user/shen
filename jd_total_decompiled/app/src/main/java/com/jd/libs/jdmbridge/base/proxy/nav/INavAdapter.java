package com.jd.libs.jdmbridge.base.proxy.nav;

/* loaded from: classes16.dex */
public interface INavAdapter {
    public static final String BTN_TYPE_SHARE = "SHARE";
    public static final int STYLE_DARK = 1;
    public static final int STYLE_LIGHT = 2;

    void hideRightButton(int i2);

    void hideRightButton(String str);

    void resetRightButtons();

    void setCloseBtnVisibility(int i2);

    void showRightButton(String str, int i2);
}
