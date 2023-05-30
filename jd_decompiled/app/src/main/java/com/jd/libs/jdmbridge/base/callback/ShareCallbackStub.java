package com.jd.libs.jdmbridge.base.callback;

/* loaded from: classes16.dex */
public class ShareCallbackStub implements ShareCallback {
    @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
    public void onCancel() {
        System.out.println("onCancel stub!");
    }

    @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
    public void onClick(String str) {
        System.out.println("onClick stub!");
    }

    @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
    public void onComplete(String str) {
        System.out.println("onComplete stub!");
    }

    @Override // com.jd.libs.jdmbridge.base.callback.ShareCallback
    public void onError(String str) {
        System.out.println("onError stub!");
    }
}
