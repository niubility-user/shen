package com.jingdong.service.impl;

import android.content.Context;
import android.view.View;
import com.jingdong.service.BaseService;
import com.jingdong.service.callback.ExposureStateListener;
import com.jingdong.service.service.MtaService;
import java.util.HashMap;

/* loaded from: classes10.dex */
public class IMMta extends BaseService implements MtaService {
    private static final String TAG = "IMMta";

    public boolean enableAccurateEp() {
        return false;
    }

    public void initRVExp(int i2, View view, int i3, ExposureStateListener exposureStateListener) {
    }

    public void onClickWithPageId(Context context, String str, String str2, String str3, String str4, String str5) {
    }

    public void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap) {
    }

    public void sendCommonData(Context context, String str, String str2, String str3, Object obj, String str4, String str5, String str6, String str7, String str8) {
    }

    public void sendEpData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
    }

    public void sendEpData(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
    }

    public void sendExposureData(Context context, Object obj, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
    }

    public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
    }

    public void sendPagePv(Context context, Object obj, String str, String str2, String str3) {
    }
}
