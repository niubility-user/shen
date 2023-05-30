package com.jingdong.common.entity.settlement;

import android.text.TextUtils;
import com.jingdong.common.R;
import com.jingdong.jdsdk.JdSdk;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class SettlementProduct implements Serializable {
    public static final int TYPE_PORDUCT_UNKNOW = -1;
    public ArrayList<? extends SettlementProductGift> gifts;
    public String id;
    public String imageUrl;
    public boolean isMainSku;
    public String jdPrice;
    public String name;
    public String num;
    public int num_int;
    public String promoId;
    public String stockStatus;
    public Integer type;
    public static final String SYMBOL_STOCK_YES = JdSdk.getInstance().getApplicationContext().getResources().getString(R.string.settlement_order_commodity_stroke_yes);
    public static final String SYMBOL_STOCK_NO = JdSdk.getInstance().getApplicationContext().getResources().getString(R.string.settlement_order_commodity_stroke_no);

    public ArrayList<? extends SettlementProductGift> getGifts() {
        ArrayList<? extends SettlementProductGift> arrayList = this.gifts;
        return arrayList == null ? new ArrayList<>() : arrayList;
    }

    public String getId() {
        String str = this.id;
        return str == null ? "" : str;
    }

    public String getPromoId() {
        return TextUtils.isEmpty(this.promoId) ? "" : this.promoId;
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

    public void setGifts(ArrayList<? extends SettlementProductGift> arrayList) {
        this.gifts = arrayList;
    }
}
