package com.jingdong.service.service;

import android.content.Context;
import com.jingdong.service.callback.BiometricTokenCallback;

/* loaded from: classes10.dex */
public interface BiometricService {
    String getCacheTokenByBizId(Context context, String str, String str2);

    void getToken(Context context, String str, String str2, BiometricTokenCallback biometricTokenCallback);
}
