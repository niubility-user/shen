package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class O2OProduct {
    private static final String TAG = "O2OProduct";
    public String tagName;
    public String tagValue;

    public O2OProduct(JSONObjectProxy jSONObjectProxy) {
        update(jSONObjectProxy);
    }

    public Boolean isOneArrive() {
        return Boolean.valueOf((TextUtils.isEmpty(this.tagName) || TextUtils.isEmpty(this.tagValue)) ? false : true);
    }

    public void update(JSONObjectProxy jSONObjectProxy) {
        this.tagName = jSONObjectProxy.getStringOrNull("tagName");
        this.tagValue = jSONObjectProxy.getStringOrNull("tagValue");
        if (OKLog.D) {
            OKLog.d(TAG, "jsonObject = " + jSONObjectProxy);
            OKLog.d(TAG, "tagName = " + this.tagName);
            OKLog.d(TAG, "tagValue = " + this.tagValue);
        }
    }

    public O2OProduct() {
    }
}
