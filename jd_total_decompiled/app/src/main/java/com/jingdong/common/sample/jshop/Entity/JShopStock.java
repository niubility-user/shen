package com.jingdong.common.sample.jshop.Entity;

import com.jingdong.common.entity.settlement.SettlementProductGift;

/* loaded from: classes6.dex */
public enum JShopStock {
    INSTOCK(0, "\u6709\u8d27"),
    SOLDOUT(1, SettlementProductGift.SYMBOL_STOCK_NO),
    OFFSHELF(2, "\u4e0b\u67b6");
    
    public String name;
    public int value;

    JShopStock(int i2, String str) {
        this.value = i2;
        this.name = str;
    }

    public static JShopStock parseFromInt(int i2, int i3) {
        if (i2 == 0 && i3 == -1) {
            return INSTOCK;
        }
        if (i2 != 0) {
            return OFFSHELF;
        }
        return SOLDOUT;
    }

    @Override // java.lang.Enum
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("JShopStock{");
        stringBuffer.append("value=");
        stringBuffer.append(this.value);
        stringBuffer.append(", name='");
        stringBuffer.append(this.name);
        stringBuffer.append('\'');
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public static JShopStock parseFromInt(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                return INSTOCK;
            }
            return OFFSHELF;
        }
        return SOLDOUT;
    }
}
