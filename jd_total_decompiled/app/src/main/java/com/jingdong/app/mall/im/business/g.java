package com.jingdong.app.mall.im.business;

import android.content.Context;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.util.httputil.LorasHttpCallback;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.callback.BiometricTokenCallback;
import com.jingdong.service.impl.IMBiometric;

/* loaded from: classes4.dex */
public class g extends IMBiometric {
    private static final String a = "g";

    /* loaded from: classes4.dex */
    class a implements LorasHttpCallback {
        final /* synthetic */ BiometricTokenCallback a;

        a(g gVar, BiometricTokenCallback biometricTokenCallback) {
            this.a = biometricTokenCallback;
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onFailInCurentThread(int i2, String str) {
            OKLog.d("bundleicssdkservice", g.a + "---getToken failed:" + str);
            BiometricTokenCallback biometricTokenCallback = this.a;
            if (biometricTokenCallback != null) {
                biometricTokenCallback.onFailInCurrentThread(i2, str);
            }
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onFailInNetThread(int i2, String str) {
            OKLog.d("bundleicssdkservice", g.a + "---getToken failed:" + str);
            BiometricTokenCallback biometricTokenCallback = this.a;
            if (biometricTokenCallback != null) {
                biometricTokenCallback.onFailInNetThread(i2, str);
            }
        }

        @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
        public void onSuccess(String str) {
            OKLog.d("bundleicssdkservice", g.a + "---getToken success:" + str);
            BiometricTokenCallback biometricTokenCallback = this.a;
            if (biometricTokenCallback != null) {
                biometricTokenCallback.onSuccess(str);
            }
        }
    }

    @Override // com.jingdong.service.impl.IMBiometric, com.jingdong.service.service.BiometricService
    public String getCacheTokenByBizId(Context context, String str, String str2) {
        String cacheTokenByBizId = BiometricManager.getInstance().getCacheTokenByBizId(context, str, str2);
        OKLog.d("bundleicssdkservice", a + "---getCacheTokenByBizId:" + cacheTokenByBizId);
        return cacheTokenByBizId;
    }

    @Override // com.jingdong.service.impl.IMBiometric, com.jingdong.service.service.BiometricService
    public void getToken(Context context, String str, String str2, BiometricTokenCallback biometricTokenCallback) {
        BiometricManager.getInstance().startBiometric(context, str, str2);
        BiometricManager.getInstance().getToken(context, str, str2, new a(this, biometricTokenCallback));
    }
}
