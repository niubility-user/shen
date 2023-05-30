package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.QueryTrafficCardInfoResp;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class QueryTrafficCardInfoBean implements Serializable {
    private String name;
    private QueryTrafficCardInfoResp response;

    public String getName() {
        return this.name;
    }

    public QueryTrafficCardInfoResp getResponse() {
        return this.response;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setResponse(QueryTrafficCardInfoResp queryTrafficCardInfoResp) {
        this.response = queryTrafficCardInfoResp;
    }
}
