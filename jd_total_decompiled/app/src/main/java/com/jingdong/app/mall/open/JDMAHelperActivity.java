package com.jingdong.app.mall.open;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class JDMAHelperActivity extends InterfaceActivity {
    private void E(Intent intent) {
        if (OKLog.V) {
            OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker-----");
        }
        if (intent == null) {
            if (OKLog.V) {
                OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, intent is empty");
                return;
            }
            return;
        }
        Uri data = intent.getData();
        if (data == null) {
            if (OKLog.V) {
                OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, uri is empty");
                return;
            }
            return;
        }
        String scheme = data.getScheme();
        if (scheme == null) {
            if (OKLog.V) {
                OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, scheme is empty");
            }
        } else if (!scheme.equalsIgnoreCase("JDAnalytics")) {
            if (OKLog.V) {
                OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, scheme=" + scheme);
            }
        } else {
            String host = data.getHost();
            if (host == null) {
                if (OKLog.V) {
                    OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, host is empty");
                    return;
                }
                return;
            }
            if (!host.equalsIgnoreCase("mobileChecker") && OKLog.V) {
                OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, host=" + host);
            }
            Uri data2 = intent.getData();
            if (data2 == null) {
                if (OKLog.V) {
                    OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, data is empty");
                    return;
                }
                return;
            }
            String queryParameter = data2.getQueryParameter("param");
            if (TextUtils.isEmpty(queryParameter)) {
                if (OKLog.V) {
                    OKLog.v("JDMAHelperActivity", "handleJDAnalyticsMobileChecker, param is empty");
                    return;
                }
                return;
            }
            com.jingdong.jdsdk.mta.a.a(queryParameter);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.open.BaseEntryActivity
    public void u(Intent intent) {
        super.u(intent);
        E(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.open.InterfaceActivity, com.jingdong.app.mall.open.BaseEntryActivity
    public void v(Intent intent) {
        super.v(intent);
        E(intent);
    }
}
