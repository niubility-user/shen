package com.jd.cashier.app.jdlibcutter.protocol.mta;

import android.content.Context;
import java.util.HashMap;

/* loaded from: classes13.dex */
public interface IABMta {
    void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap);

    void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap);
}
