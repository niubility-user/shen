package com.jingdong.common.entity;

import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class TwoProduct {
    private static final String TAG = "TwoProduct";
    private Product productOne;
    private Product productTwo;

    public TwoProduct(Product product, Product product2) {
        this.productOne = product;
        this.productTwo = product2;
    }

    public static ArrayList<TwoProduct> toList(ArrayList<Product> arrayList) {
        if (arrayList != null && arrayList.size() >= 1) {
            ArrayList<TwoProduct> arrayList2 = new ArrayList<>();
            int size = arrayList.size();
            int i2 = size / 2;
            int i3 = size % 2;
            if (OKLog.D) {
                OKLog.d(TAG, "toList() -->> n = " + i2);
                OKLog.d(TAG, "toList() -->> r = " + i3);
            }
            if (i2 > 0) {
                for (int i4 = 0; i4 < i2; i4++) {
                    int i5 = i4 * 2;
                    arrayList2.add(new TwoProduct(arrayList.get(i5), arrayList.get(i5 + 1)));
                }
            }
            if (i3 == 1) {
                arrayList2.add(new TwoProduct(arrayList.get(size - 1), null));
            }
            return arrayList2;
        }
        return new ArrayList<>();
    }

    public Product getProductOne() {
        return this.productOne;
    }

    public Product getProductTwo() {
        return this.productTwo;
    }
}
