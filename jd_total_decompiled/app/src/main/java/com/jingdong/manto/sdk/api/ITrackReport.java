package com.jingdong.manto.sdk.api;

import android.content.Context;
import com.jingdong.manto.sdk.IMantoSdkBase;
import java.util.Map;

/* loaded from: classes16.dex */
public interface ITrackReport extends IMantoSdkBase {
    void sendClickData(Context context, Map<String, String> map, Map<String, String> map2);

    void sendExposureData(Context context, Map<String, String> map, Map<String, String> map2);

    void sendJDOrderInfo(Context context, Map<String, String> map, Map<String, String> map2);

    void sendPagePv(Context context, Map<String, String> map, Map<String, String> map2);
}
