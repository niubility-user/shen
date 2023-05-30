package com.huawei.hms.common.internal;

import com.huawei.hms.core.aidl.IMessageEntity;

/* loaded from: classes12.dex */
public interface AnyClient {

    /* loaded from: classes12.dex */
    public interface CallBack {
        void onCallback(IMessageEntity iMessageEntity, String str);
    }

    void connect(int i2);

    void connect(int i2, boolean z);

    void disconnect();

    int getRequestHmsVersionCode();

    String getSessionId();

    boolean isConnected();

    boolean isConnecting();

    void post(IMessageEntity iMessageEntity, String str, CallBack callBack);
}
