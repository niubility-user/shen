package com.jingdong.common.entity;

import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;

/* loaded from: classes5.dex */
public class ProductDetailEntityBase {
    private static final String TAG = "ProductDetailEntityBase";
    public String deliveryId;
    public String dist;
    public ProductDetailPrice mJdPrice;
    public SourceEntity mSourceEntity;
    public String name;
    public int number = 1;
    public String skuId;

    public long getId() {
        try {
            return Long.parseLong(this.skuId);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return 0L;
        }
    }

    public String getJdPrice() {
        Double valueOf;
        try {
            ProductDetailPrice productDetailPrice = this.mJdPrice;
            if (productDetailPrice != null && (valueOf = Double.valueOf(productDetailPrice.getDefaultValue())) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
            return StringUtil.no_price;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return StringUtil.no_price;
            }
            return StringUtil.no_price;
        }
    }
}
