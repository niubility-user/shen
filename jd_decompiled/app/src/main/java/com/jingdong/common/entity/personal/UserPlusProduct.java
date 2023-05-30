package com.jingdong.common.entity.personal;

import java.io.Serializable;

/* loaded from: classes5.dex */
public class UserPlusProduct implements Serializable {
    private static final long serialVersionUID = -8253793977571497240L;
    public String img;
    public String plusPrice;
    public String promotionPrice;
    public long sku;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserPlusProduct userPlusProduct = (UserPlusProduct) obj;
        if (this.sku == userPlusProduct.sku && this.img.equals(userPlusProduct.img) && this.promotionPrice.equals(userPlusProduct.promotionPrice)) {
            return this.plusPrice.equals(userPlusProduct.plusPrice);
        }
        return false;
    }

    public int hashCode() {
        long j2 = this.sku;
        return (((((((int) (j2 ^ (j2 >>> 32))) * 31) + this.img.hashCode()) * 31) + this.promotionPrice.hashCode()) * 31) + this.plusPrice.hashCode();
    }
}
