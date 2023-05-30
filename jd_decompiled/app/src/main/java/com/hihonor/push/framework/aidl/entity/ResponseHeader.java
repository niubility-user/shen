package com.hihonor.push.framework.aidl.entity;

import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.annotation.Packed;

/* loaded from: classes12.dex */
public class ResponseHeader implements IMessageEntity {
    @Packed
    public int statusCode;
    @Packed
    public String statusMessage;

    public ResponseHeader() {
        this.statusCode = -1;
    }

    public ResponseHeader(int i2, String str) {
        this.statusCode = -1;
        this.statusCode = i2;
        this.statusMessage = str;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public void setStatusMessage(String str) {
        this.statusMessage = str;
    }
}
