package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.text.TextUtils;
import com.jdjr.risk.device.entity.JdcnOaidInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class JdcnOaidManager {
    private static volatile JdcnOaidManager a;

    /* loaded from: classes18.dex */
    public interface JdcnOaidRequestListener {
        void oaidRequestComplete(JdcnOaidInfo jdcnOaidInfo);
    }

    private JdcnOaidManager() {
    }

    public static JdcnOaidManager getInstance() {
        if (a == null) {
            synchronized (JdcnOaidManager.class) {
                if (a == null) {
                    a = new JdcnOaidManager();
                }
            }
        }
        return a;
    }

    public String getOaid(Context context) {
        try {
            return BaseInfo.getOAID();
        } catch (Exception unused) {
            return "";
        }
    }

    public void startRequestOaid(final Context context, final JdcnOaidRequestListener jdcnOaidRequestListener) {
        new Thread() { // from class: com.jdjr.risk.biometric.core.JdcnOaidManager.1
            {
                JdcnOaidManager.this = this;
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                String oaid = JdcnOaidManager.this.getOaid(context);
                JdcnOaidInfo jdcnOaidInfo = new JdcnOaidInfo();
                if (!TextUtils.isEmpty(oaid)) {
                    jdcnOaidInfo.setOaid(oaid);
                }
                JdcnOaidRequestListener jdcnOaidRequestListener2 = jdcnOaidRequestListener;
                if (jdcnOaidRequestListener2 != null) {
                    jdcnOaidRequestListener2.oaidRequestComplete(jdcnOaidInfo);
                }
            }
        }.start();
    }
}
