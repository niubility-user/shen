package com.jingdong.common.web.urlcheck.impl;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkMHelper;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.corelib.utils.Log;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class SecondLevelPageCheckImpl extends BaseWebComponent implements ICheckUrl {
    public SecondLevelPageCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    private boolean checkSecondLevel(String str, WebView.HitTestResult hitTestResult) {
        if (hitTestResult == null || TextUtils.isEmpty(hitTestResult.getExtra())) {
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                if ("1".equals(Uri.parse(str).getQueryParameter("forceCurrentView"))) {
                    return false;
                }
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e("SecondLevelPageCheckImpl", e2.getMessage(), e2);
                }
            }
        }
        DeepLinkMHelper.startWebActivity(this.webUiBinder.getBaseActivity(), str);
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        return checkSecondLevel(str, webView.getHitTestResult());
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_SUBPAGE;
    }
}
