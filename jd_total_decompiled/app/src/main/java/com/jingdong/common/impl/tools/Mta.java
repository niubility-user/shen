package com.jingdong.common.impl.tools;

import android.content.Context;
import com.jingdong.common.protocol.tools.IMta;
import com.jingdong.jdsdk.mta.JDMtaUtils;

/* loaded from: classes5.dex */
public class Mta implements IMta {
    @Override // com.jingdong.common.protocol.tools.IMta
    public void onClickMta(Context context, String str, String str2, String str3, String str4) {
        if (context != null) {
            JDMtaUtils.sendCommonData(context.getApplicationContext(), str, str2, "", context.getClass().getName(), str3, "", "", str4, "");
        }
    }

    @Override // com.jingdong.common.protocol.tools.IMta
    public void onExpoMta(Context context, String str, String str2, String str3, String str4) {
        if (context != null) {
            JDMtaUtils.sendExposureData(context.getApplicationContext(), context.getClass().getName(), str4, str3, str, str2, "", "", "");
        }
    }
}
