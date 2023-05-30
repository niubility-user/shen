package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.QueryTrafficCardsResp;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class ResponTrafficcardBean implements Serializable {
    private String name;
    private QueryTrafficCardsResp response;

    public String getName() {
        return this.name;
    }

    public QueryTrafficCardsResp getResponse() {
        return this.response;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setResponse(QueryTrafficCardsResp queryTrafficCardsResp) {
        this.response = queryTrafficCardsResp;
    }
}
