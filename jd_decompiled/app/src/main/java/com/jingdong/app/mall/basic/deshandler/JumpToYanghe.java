package com.jingdong.app.mall.basic.deshandler;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.app.mall.libs.Des;
import com.jingdong.app.mall.nfc.NfcIntentHandleActivity;
import com.jingdong.corelib.utils.Log;

@Des(des = "nfc_yanghe,nfc_sy")
/* loaded from: classes19.dex */
public class JumpToYanghe extends a {
    public static final String b = "JumpToYanghe";

    @Override // com.jingdong.app.mall.basic.deshandler.a
    public void forward(Context context, Bundle bundle) {
        if (Log.D) {
            Log.d(b, "JumpToYanghe forward context = " + context);
        }
        if (context instanceof NfcIntentHandleActivity) {
            ((NfcIntentHandleActivity) context).w();
        }
    }
}
