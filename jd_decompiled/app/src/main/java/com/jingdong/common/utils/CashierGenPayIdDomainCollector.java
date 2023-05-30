package com.jingdong.common.utils;

import androidx.annotation.Keep;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Keep
/* loaded from: classes6.dex */
public class CashierGenPayIdDomainCollector {
    private static final String TAG = "CashierGenPayIdDomainCollector";
    private static volatile CashierGenPayIdDomainCollector mInstance;
    private volatile ArrayList<String> mUrlList = new ArrayList<>();

    private CashierGenPayIdDomainCollector() {
    }

    public static CashierGenPayIdDomainCollector getInstance() {
        if (mInstance == null) {
            synchronized (CashierGenPayIdDomainCollector.class) {
                if (mInstance == null) {
                    mInstance = new CashierGenPayIdDomainCollector();
                }
            }
        }
        return mInstance;
    }

    public synchronized ArrayList<String> getUrlList() {
        return this.mUrlList;
    }

    public synchronized void onDestroy() {
        if (this.mUrlList != null) {
            this.mUrlList.clear();
        }
    }

    public synchronized void preHandHistoryUrl(JDWebView jDWebView, String str) {
        List<String> urlHistory;
        try {
            if (this.mUrlList != null) {
                this.mUrlList.clear();
            }
            if (JumpUtils.checkPayHttpHost(str) && this.mUrlList != null && jDWebView != null && (urlHistory = jDWebView.getUrlHistory()) != null && !urlHistory.isEmpty()) {
                this.mUrlList.addAll(urlHistory);
            }
            if (Log.D && this.mUrlList != null && !this.mUrlList.isEmpty()) {
                Iterator<String> it = this.mUrlList.iterator();
                while (it.hasNext()) {
                    Log.d(TAG, it.next());
                }
            }
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
    }
}
