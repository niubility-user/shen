package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;

/* loaded from: classes15.dex */
public class PdLiveFloatEntity {
    public String benefitIcon;
    public boolean explain;
    public String explainStatus;
    public String firstFloatMsg;
    public String floatImg;
    public String h5pullUrl;
    public String indexImage;
    public boolean isBenefit;
    public boolean isCloseDetailLive;
    public String liveId;
    public String livePic;
    public boolean liveStyleNew;
    public String liveType;
    public String msgColor;
    public boolean newStyle;
    public int noDisturb;
    public String openapp;
    public String screen;
    public String secFloatMsg;
    public String shopLiving;
    public String shrinkIcon;
    public String status;
    public String title;

    public boolean isShopLiving() {
        return TextUtils.equals("1", this.shopLiving);
    }
}
