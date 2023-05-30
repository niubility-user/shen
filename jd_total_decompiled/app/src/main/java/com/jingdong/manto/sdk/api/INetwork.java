package com.jingdong.manto.sdk.api;

import android.content.Context;
import com.jingdong.manto.sdk.IMantoSdkBase;

/* loaded from: classes16.dex */
public interface INetwork extends IMantoSdkBase {
    String getNetworkType(Context context);

    boolean isConnected(Context context);
}
