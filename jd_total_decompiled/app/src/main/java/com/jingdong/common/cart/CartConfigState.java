package com.jingdong.common.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.cart.CartConfigDetail;
import com.jingdong.common.entity.cart.CartConfigInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartConfigState {
    public static final String TAG = "CartConfigState";
    private static CartConfigState state = new CartConfigState();
    private volatile CartConfigInfo cartConfigInfo;
    public volatile JDJSONObject dataJsonObject;
    private volatile int configVersion = 0;
    private volatile int degradation = 0;
    private volatile boolean isLoad = false;
    public ArrayList<String> specProductCheckedSkuIds = new ArrayList<>();

    public static CartConfigState getInstance() {
        return state;
    }

    public void clearCache() {
        this.specProductCheckedSkuIds.clear();
    }

    public CartConfigInfo getCartConfigInfo() {
        return this.cartConfigInfo;
    }

    public int getConfigVersion() {
        return this.configVersion;
    }

    public int getDegradation() {
        return this.degradation;
    }

    public boolean isLoad() {
        if (OKLog.D) {
            OKLog.d(TAG, " loadConfig  ---> isLoad : " + this.isLoad);
        }
        return this.isLoad;
    }

    public boolean isValid() {
        return (this.cartConfigInfo == null || this.cartConfigInfo.cartConfigDetail == null || this.configVersion == 0 || this.dataJsonObject == null) ? false : true;
    }

    public JDJSONObject mergeCartConfigInfo(JDJSONObject jDJSONObject) {
        if (OKLog.D) {
            OKLog.d(TAG, "mergeCartConfigInfo");
        }
        if (this.dataJsonObject == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "mergeCartConfigInfo \u5f53\u524d\u7f13\u5b58\u6570\u636e\u4e3a\u7a7a return \u964d\u7ea7\u6570\u636e");
            }
            return jDJSONObject;
        }
        return CartConfigDetail.mergeDetail((JDJSONObject) this.dataJsonObject.clone(), jDJSONObject);
    }

    public void setCartConfigInfo(CartConfigInfo cartConfigInfo, int i2, int i3, JDJSONObject jDJSONObject) {
        if (OKLog.D) {
            OKLog.d(TAG, "setCartConfigInfo");
        }
        this.cartConfigInfo = cartConfigInfo;
        this.configVersion = i2;
        this.degradation = i3;
        this.dataJsonObject = jDJSONObject;
    }

    public void setConfigVersion(int i2) {
        this.configVersion = i2;
    }

    public void setDegradation(int i2) {
        this.degradation = i2;
    }

    public void setLoad(boolean z) {
        this.isLoad = z;
    }
}
