package com.jingdong.common.xbridge;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.jd.framework.json.JDJSON;
import com.jd.xbridge.base.IBridgeCallback;
import com.jd.xbridge.base.IBridgePlugin;
import com.jd.xbridge.base.IBridgeWebView;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebPerformance;
import com.jingdong.common.web.managers.WebPerformanceHolder;
import com.jingdong.common.web.ui.JDWebView;

/* loaded from: classes12.dex */
public class MtaBridgePlugin implements IBridgePlugin {
    private boolean addH5Data2WebView(IBridgeWebView iBridgeWebView, String str) {
        JDWebView jDWebView;
        WebPerformanceHolder performanceHolder;
        WebPerformance currentRecord;
        if (!(iBridgeWebView instanceof View) || (jDWebView = getJDWebView((View) iBridgeWebView)) == null || (performanceHolder = jDWebView.getPerformanceHolder()) == null || (currentRecord = performanceHolder.getCurrentRecord()) == null) {
            return false;
        }
        currentRecord.appendH5Data(str);
        return true;
    }

    private JDWebView getJDWebView(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof View) {
            if (parent instanceof JDWebView) {
                return (JDWebView) parent;
            }
            return getJDWebView((View) parent);
        }
        return null;
    }

    @Override // com.jd.xbridge.base.IBridgePlugin
    public boolean execute(IBridgeWebView iBridgeWebView, String str, String str2, IBridgeCallback iBridgeCallback) {
        String optString;
        if ("h5PerfReport".equals(str)) {
            try {
                optString = JDJSON.parseObject(str2).optString(WebPerfManager.H5_DATA);
            } catch (Exception e2) {
                e2.printStackTrace();
                iBridgeCallback.onError("throw java exception!" + e2.getMessage());
            }
            if (TextUtils.isEmpty(optString)) {
                iBridgeCallback.onError("h5 data is Empty!");
                return true;
            }
            if (addH5Data2WebView(iBridgeWebView, optString)) {
                iBridgeCallback.onSuccess("success!");
            } else {
                iBridgeCallback.onError("unknown error!");
            }
            return true;
        }
        return false;
    }
}
