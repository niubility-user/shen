package com.jingdong.jdsdk.depend;

import android.content.Context;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;

@Deprecated
/* loaded from: classes14.dex */
public interface Idepend {
    @Deprecated
    void autoNetDiagnose();

    @Deprecated
    String getCookies();

    @Deprecated
    String getReportParamNetworkType();

    @Deprecated
    String getSignFromJni(Context context, String str, String str2, String str3, String str4, String str5) throws Exception;

    @Deprecated
    String getSoftwareVersionName();

    @Deprecated
    String getStringFromPreference(String str);

    @Deprecated
    String getUUID();

    @Deprecated
    JSONObjectProxy handlerEncrypt(JSONObjectProxy jSONObjectProxy) throws UnsupportedEncodingException, JSONException;

    @Deprecated
    boolean isAllowNetworkConnection();

    @Deprecated
    boolean isUseOkhttp();

    @Deprecated
    void networkSettingsPreSignature();

    @Deprecated
    void saveCookies(String str);
}
