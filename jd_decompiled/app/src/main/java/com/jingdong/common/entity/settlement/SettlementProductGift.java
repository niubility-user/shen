package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class SettlementProductGift implements Serializable {
    public static final String SYMBOL_STOCK_NO = "\u65e0\u8d27";
    public String id;
    public String imageDomain;
    public String imageUrl;
    public String imgUrl;
    public String name;
    public String num;
    public String stockStatus;
    public Integer type;

    public String getId() {
        String str = this.id;
        return str == null ? "" : str;
    }

    public String getImageUrl() {
        String str = this.imgUrl;
        if (str == null) {
            return "";
        }
        if (str.startsWith("http://")) {
            return this.imgUrl;
        }
        if (this.imageDomain == null) {
            this.imageDomain = "";
        }
        return this.imageDomain + this.imgUrl;
    }

    public String getImgUrl() {
        return TextUtils.isEmpty(this.imgUrl) ? "" : this.imgUrl;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public String getNum() {
        String str = this.num;
        return str == null ? "" : str;
    }

    public String getStockStatus() {
        String str = this.stockStatus;
        return str == null ? "" : str;
    }

    public Integer getType() {
        Integer num = this.type;
        if (num == null) {
            return -1;
        }
        return num;
    }

    public boolean isNoStock() {
        return TextUtils.equals(getStockStatus(), SYMBOL_STOCK_NO);
    }
}
