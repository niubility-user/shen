package com.jd.cashier.app.jdlibcutter.impl.mta;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.protocol.mta.IABMta;
import com.jingdong.common.abmta.ABMtaUtils;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class ABMtaImpl implements IABMta {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IABMta
    public void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        if (context != null) {
            ABMtaUtils.sendClickDataWithExt(context, str, str2, "", str3, str4, str5, "", str6, null, hashMap);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IABMta
    public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap) {
        if (context != null) {
            ABMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, null, hashMap);
        }
    }
}
