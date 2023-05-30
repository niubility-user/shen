package com.jingdong.common.phonecharge.model;

import com.jingdong.jdsdk.utils.JSONObjectProxy;

/* loaded from: classes5.dex */
public class CardInfo {
    public String cardNo;
    public String cardPass;
    public String expiretime;

    public CardInfo() {
    }

    public CardInfo(JSONObjectProxy jSONObjectProxy) {
        this.cardNo = jSONObjectProxy.optString("cardNo");
        this.cardPass = jSONObjectProxy.optString("cardPass");
        this.expiretime = jSONObjectProxy.optString("expireTime");
    }
}
