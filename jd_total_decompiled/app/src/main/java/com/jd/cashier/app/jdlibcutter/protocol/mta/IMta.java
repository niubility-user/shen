package com.jd.cashier.app.jdlibcutter.protocol.mta;

import android.content.Context;

/* loaded from: classes13.dex */
public interface IMta {
    void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6);

    void sendCommonData(Context context, String str, String str2, String str3, String str4, String str5);

    void sendExposureData(Context context, String str, String str2, String str3, String str4, String str5);

    void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6);

    void sendPagePv(Context context, String str, String str2, String str3);

    void setMtaContent(Context context, String str);

    void setMtaContentUnion(String str);
}
