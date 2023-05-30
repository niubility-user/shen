package com.jingdong.aura.provided.api;

import android.content.Context;
import com.jingdong.aura.serviceloder.IServiceProvider;

/* loaded from: classes4.dex */
public interface IAuraInstallManager extends IServiceProvider {
    void startInstall(Context context, AuraInstallRequest auraInstallRequest);
}
