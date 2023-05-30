package com.jd.lib.productdetail.core.entitys.shop;

import android.text.TextUtils;

/* loaded from: classes15.dex */
public class PdShopChatInfoEntity {
    public String allGoodIcon;
    public String allGoodText;
    public String bottomIcon;
    public String bottomIconType;
    public String bottomText;
    public String bubbleImg;
    public String bubbleImgType;
    public String bubbleUrl;
    public String closeBubbleImg;
    public boolean isBubble;
    public boolean isBubbleHV;
    public String shopIcon;
    public String shopText;
    public String tips;

    public boolean isHasAllGood() {
        return (TextUtils.isEmpty(this.allGoodText) || TextUtils.isEmpty(this.allGoodIcon)) ? false : true;
    }

    public boolean isHasBottomService() {
        return (TextUtils.isEmpty(this.bottomText) || TextUtils.isEmpty(this.bottomIcon)) ? false : true;
    }

    public boolean isHasShopService() {
        return (TextUtils.isEmpty(this.shopText) || TextUtils.isEmpty(this.shopIcon)) ? false : true;
    }
}
