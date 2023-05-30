package com.jingdong.common.jdreactFramework.listener;

import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeCommonShareListener {
    void sendWxShare(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void showCustomShareDialog(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);

    void showShareDialog(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2);
}
