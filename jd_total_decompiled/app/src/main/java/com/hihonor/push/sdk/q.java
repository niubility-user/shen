package com.hihonor.push.sdk;

import com.hihonor.push.framework.aidl.IMessageEntity;
import com.hihonor.push.framework.aidl.entity.PushTokenResult;
import com.hihonor.push.sdk.common.data.ApiException;

/* loaded from: classes12.dex */
public class q extends u<PushTokenResult> {
    public q(String str, IMessageEntity iMessageEntity) {
        super(str, iMessageEntity);
    }

    @Override // com.hihonor.push.sdk.u
    public void a(ApiException apiException, Object obj) {
        if (apiException == null) {
            apiException = com.hihonor.push.sdk.b0.a.ERROR_UNKNOWN.toApiException();
        }
        if (apiException.getErrorCode() == com.hihonor.push.sdk.b0.a.SUCCESS.getErrorCode()) {
            if (obj instanceof PushTokenResult) {
                PushTokenResult pushTokenResult = (PushTokenResult) obj;
                try {
                    o.b.b(f0.f1091e.a(), pushTokenResult.getPushToken());
                } catch (Exception unused) {
                }
                this.a.b(pushTokenResult);
                return;
            }
            apiException = com.hihonor.push.sdk.b0.a.ERROR_INTERNAL_ERROR.toApiException();
        }
        String str = "task execute failed. error:" + apiException.getErrorCode();
        this.a.a(apiException);
    }
}
