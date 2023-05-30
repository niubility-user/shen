package com.jingdong.service.service;

import android.content.Context;
import android.view.View;
import com.jingdong.service.callback.ExposureStateListener;
import java.util.HashMap;

/* loaded from: classes10.dex */
public interface MtaService {
    boolean enableAccurateEp();

    void initRVExp(int i2, View view, int i3, ExposureStateListener exposureStateListener);

    void onClickWithPageId(Context context, String str, String str2, String str3, String str4, String str5);

    void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap);

    void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8);

    void sendEpData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7);

    void sendEpData(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap);

    void sendExposureData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7);

    void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap);

    void sendPagePv(Context context, Object obj, String str, String str2, String str3);
}
