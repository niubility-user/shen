package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeNetworkListener {
    void fetch(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void fetchWithoutCertificate(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    boolean isBeta();

    boolean isBetaHost();
}
