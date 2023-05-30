package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeVerifyListener {
    void free(JDCallback jDCallback, JDCallback jDCallback2);

    void getSeesionID(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void verify(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
