package com.jingdong.app.mall.bundle.styleinfoview.entity;

import androidx.annotation.DrawableRes;

/* loaded from: classes3.dex */
public class PdBottomLeftButtonInfo {
    @DrawableRes
    public int backgroundResource = -1;
    public String bbtf;
    public boolean buttonEnable;
    public int buttonEvent;
    public String buttonSubText;
    public CharSequence buttonText;

    public String toString() {
        return "PdBottomLeftButtonInfo{buttonText=" + ((Object) this.buttonText) + ", buttonSubText='" + this.buttonSubText + "', buttonEvent=" + this.buttonEvent + ", buttonEnable=" + this.buttonEnable + ", backgroundResource=" + this.backgroundResource + '}';
    }
}
