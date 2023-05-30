package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.util.HashMap;

/* loaded from: classes5.dex */
public interface NativeMesssageEventListener {
    void sendEventToJDMinProgram(HashMap hashMap, Activity activity, JDCallback jDCallback, JDCallback jDCallback2);

    void sendEventToNative(String str, Object obj, JDCallback jDCallback, JDCallback jDCallback2);
}
