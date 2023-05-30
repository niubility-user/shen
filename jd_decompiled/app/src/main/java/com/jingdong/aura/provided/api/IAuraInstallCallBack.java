package com.jingdong.aura.provided.api;

import com.jingdong.aura.serviceloder.IServiceProvider;

/* loaded from: classes4.dex */
public interface IAuraInstallCallBack extends IServiceProvider {
    void installFinished(String str, boolean z, Exception exc);
}
