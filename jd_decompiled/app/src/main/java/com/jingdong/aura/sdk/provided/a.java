package com.jingdong.aura.sdk.provided;

import android.content.Context;
import com.jingdong.aura.provided.api.AuraInstallRequest;
import com.jingdong.aura.provided.api.IAuraInstallCallBack;
import com.jingdong.aura.wrapper.AuraInitializer;
import java.util.List;

/* loaded from: classes4.dex */
public class a implements IAuraInstallCallBack {
    @Override // com.jingdong.aura.serviceloder.IServiceProvider
    public void init(Context context) {
    }

    @Override // com.jingdong.aura.provided.api.IAuraInstallCallBack
    public void installFinished(String str, boolean z, Exception exc) {
        if (b.a.containsKey(str)) {
            if (z) {
                try {
                    AuraInitializer.loadBundle(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (b.a.containsKey(str)) {
                List<AuraInstallRequest.IInstallListener> list = b.a.get(str);
                if (list != null) {
                    for (AuraInstallRequest.IInstallListener iInstallListener : list) {
                        if (z) {
                            if (iInstallListener.getOnSuccessListener() != null) {
                                iInstallListener.getOnSuccessListener().onSuccess();
                            }
                        } else if (iInstallListener.getOnFailerListener() != null) {
                            iInstallListener.getOnFailerListener().onFailure(exc);
                        }
                    }
                }
                b.a.remove(str);
            }
        }
    }
}
