package com.jingdong.common.web.urlcheck.impl;

import android.os.Bundle;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class ShareGiftCheckImpl extends BaseWebComponent implements ICheckUrl {
    public ShareGiftCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        try {
            Class.forName("com.jingdong.common.XView.ShareGiftXViewHelper").getMethod("dealShareGiftOnCreate", BaseActivity.class, Bundle.class).invoke(null, this.webUiBinder.getBaseActivity(), WebViewHelper.getBundleFromUrl(str));
        } catch (Throwable unused) {
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_SHAREGIFT;
    }
}
