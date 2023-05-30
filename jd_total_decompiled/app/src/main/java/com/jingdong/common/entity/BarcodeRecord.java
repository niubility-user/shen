package com.jingdong.common.entity;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class BarcodeRecord implements Serializable {
    public static String BARCODE_TYPE_EXTERNAL_URL = "notinwhitelist";
    public static String BARCODE_TYPE_INTERNAL_URL = "othersinwhitelist";
    public static String BARCODE_TYPE_ITEM = "itemInfo";
    public static String BARCODE_TYPE_JDPAY = "FinancePay";
    public static String BARCODE_TYPE_JS = "js";
    public static String BARCODE_TYPE_LOGININ = "loginin";
    public static String BARCODE_TYPE_ORDER = "orderInfo";
    public static String BARCODE_TYPE_TEXT = "text";
    private static final long serialVersionUID = -8631766404071371818L;
    public String barcodeType;
    public String content;
    public String format;
    public String imageUrl;
    private Product product;
    private String productName = "\u67e5\u8be2\u4e2d...";
    public String productPrice;
    public String timeStamp;
    public String type;
    public String wareId;

    public Product getProduct() {
        return this.product;
    }

    public String getProductName() {
        Product product = this.product;
        return product != null ? product.getName() : this.productName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProductName(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.productName = str;
    }
}
