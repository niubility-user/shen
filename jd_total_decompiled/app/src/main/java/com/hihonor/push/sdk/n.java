package com.hihonor.push.sdk;

import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.sdk.common.data.ApiException;

/* loaded from: classes12.dex */
public class n extends u<Void> {
    public n(String str, IMessageEntity iMessageEntity) {
        super(str, iMessageEntity);
    }

    @Override // com.hihonor.push.sdk.u
    public void a(ApiException apiException, Object obj) {
        if (apiException == null) {
            apiException = com.hihonor.push.sdk.b0.a.ERROR_UNKNOWN.toApiException();
        }
        if (apiException.getErrorCode() == com.hihonor.push.sdk.b0.a.SUCCESS.getErrorCode()) {
            this.a.b(null);
            return;
        }
        String str = "task execute failed. error:" + apiException.getErrorCode();
        this.a.a(apiException);
    }
}
