package com.jingdong.jdsdk.network.dependency;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes.dex */
public interface IPHCEncryptionPlugin {
    String getEncryptBodyParamStr(HttpSetting httpSetting, JDJSONObject jDJSONObject);

    void reportDecryptError(Throwable th);

    void reportEncryptError(String str, Throwable th);

    void reportGateWayDecryptError(String str, String str2);

    void reportInitError(String str, String str2);

    void resendEncryptError(String str);

    void resendServer731Error(String str, String str2);
}
