package com.huawei.hms.common;

import com.huawei.hms.support.api.client.Status;

/* loaded from: classes12.dex */
public class ApiException extends Exception {
    protected final Status mStatus;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ApiException(Status status) {
        super(r0.toString());
        StringBuilder sb = new StringBuilder();
        sb.append(status.getStatusCode());
        sb.append(": ");
        sb.append(status.getStatusMessage() != null ? status.getStatusMessage() : "");
        this.mStatus = status;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    @Deprecated
    public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}
