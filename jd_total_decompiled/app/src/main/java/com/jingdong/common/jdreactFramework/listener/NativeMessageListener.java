package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;

/* loaded from: classes5.dex */
public interface NativeMessageListener {
    void getJDMessageRedDot(JDCallback jDCallback, JDCallback jDCallback2);

    void startMsgObserving();

    void stopMsgObserving();
}
