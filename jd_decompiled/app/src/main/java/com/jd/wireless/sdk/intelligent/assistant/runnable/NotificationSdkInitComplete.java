package com.jd.wireless.sdk.intelligent.assistant.runnable;

import com.jd.wireless.sdk.intelligent.assistant.IntelligentAssistanceCallBack;

/* loaded from: classes18.dex */
public class NotificationSdkInitComplete implements Runnable {
    private byte initState;
    private IntelligentAssistanceCallBack intelligentAssistanceCallBack;

    public NotificationSdkInitComplete(IntelligentAssistanceCallBack intelligentAssistanceCallBack, byte b) {
        this.intelligentAssistanceCallBack = intelligentAssistanceCallBack;
        this.initState = b;
    }

    @Override // java.lang.Runnable
    public void run() {
        IntelligentAssistanceCallBack intelligentAssistanceCallBack = this.intelligentAssistanceCallBack;
        if (intelligentAssistanceCallBack != null) {
            intelligentAssistanceCallBack.initComplete(this.initState);
        }
    }
}
