package com.jingdong.common.web.urlcheck.impl;

import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class SharePinCheckImpl extends BaseWebComponent implements ICheckUrl {
    public SharePinCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        if (str.contains("ShareTm=")) {
            ShareUtil.dealSharedPin(WebViewHelper.getBundleFromUrl(str), LoginUserBase.getUserPin());
            WebUnifiedMtaUtil.functionReport(this.webUiBinder, "SharePinCheckImpl-checkUrl");
            return false;
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_SHAREPIN;
    }
}
