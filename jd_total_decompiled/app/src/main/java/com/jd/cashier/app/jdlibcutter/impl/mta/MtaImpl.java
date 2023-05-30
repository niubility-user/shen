package com.jd.cashier.app.jdlibcutter.impl.mta;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.protocol.mta.IMta;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes13.dex */
public class MtaImpl implements IMta {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IMta
    public void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (context != null) {
            JDMtaUtils.sendClickDataWithExt(context, str, str2, "", str3, str4, str5, "", str6, null);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IMta
    public void sendCommonData(Context context, String str, String str2, String str3, String str4, String str5) {
        if (context != null) {
            JDMtaUtils.sendCommonData(context, str, str2, "", str4, str5, "", "", str3, "");
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IMta
    public void sendExposureData(Context context, String str, String str2, String str3, String str4, String str5) {
        if (context != null) {
            JDMtaUtils.sendExposureData(context, str4, str3, str5, str, str2, "", "", "");
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IMta
    public void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        if (context != null) {
            JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, str6, null);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IMta
    public void sendPagePv(Context context, String str, String str2, String str3) {
        if (context != null) {
            JDMtaUtils.sendPagePv(context, str2, str3, str, "");
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IMta
    public void setMtaContent(Context context, String str) {
        if (context != null) {
            JDMtaUtils.setMtaContent(context, str);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.mta.IMta
    public void setMtaContentUnion(String str) {
        JDMtaUtils.setMtaContentUnion(str);
    }
}
