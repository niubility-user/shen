package com.jdjr.risk.cer;

import android.content.Context;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.util.httputil.LorasHttpCallback;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes18.dex */
public class JDTDRiskService {
    private static JDTDRiskService instance = new JDTDRiskService();

    public static JDTDRiskService getInstanceService() {
        return instance;
    }

    public void payRiskValidationWithData(Context context, String str, String str2, String str3, String str4, final IEncryptCompletionBlock iEncryptCompletionBlock) {
        BiometricManager.getInstance().getToken(context, "JD_OLD_NOSECRET_ANDROID", str4, new LorasHttpCallback() { // from class: com.jdjr.risk.cer.JDTDRiskService.1
            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onFailInCurentThread(int i2, String str5) {
                iEncryptCompletionBlock.getEncryptedData(R2.attr.textAllCaps, "");
            }

            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onFailInNetThread(int i2, String str5) {
                iEncryptCompletionBlock.getEncryptedData(R2.attr.textAllCaps, "");
            }

            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onSuccess(String str5) {
                iEncryptCompletionBlock.getEncryptedData(0, "P-TOKEN*_*" + str5);
            }
        });
    }
}
