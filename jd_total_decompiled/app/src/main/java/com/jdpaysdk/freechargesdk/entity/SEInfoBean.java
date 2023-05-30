package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.SEInfoResp;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class SEInfoBean implements Serializable {
    private String name;
    private SEInfoResp response;

    public String getName() {
        return this.name;
    }

    public SEInfoResp getResponse() {
        return this.response;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setResponse(SEInfoResp sEInfoResp) {
        this.response = sEInfoResp;
    }
}
