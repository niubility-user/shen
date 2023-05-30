package com.jingdong.common.web.urlcheck.impl;

import android.net.Uri;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes12.dex */
public class JdAuthCheckImpl extends BaseWebComponent implements ICheckUrl {
    public JdAuthCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    private boolean checkJdAuthLogin(Uri uri) {
        if (this.webUiBinder.getWebEntity().isFromAuthSdk && JumpUtil.isJdScheme(uri.getScheme())) {
            try {
                JDJSONObject parseObject = JDJSON.parseObject(uri.getQueryParameter("params"));
                String optString = parseObject.optString("code");
                this.webUiBinder.getWebEntity().msgcode = parseObject.optString("msgcode", "");
                if (!TextUtils.isEmpty(optString) && !"-1".equals(optString)) {
                    this.webUiBinder.getWebEntity().oauthCode = optString;
                    this.webUiBinder.getWebEntity().oautherror = 0;
                } else {
                    this.webUiBinder.getWebEntity().oauthCode = "-1";
                    this.webUiBinder.getWebEntity().oautherror = 1;
                }
                WebUtils.jdAuthNotifyThirdApp(this.webUiBinder);
                WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JdAuthCheckImpl-checkJdAuthLogin1");
            } catch (Exception e2) {
                e2.printStackTrace();
                this.webUiBinder.getWebEntity().oautherror = 1;
                this.webUiBinder.getWebEntity().oauthCode = "-1";
                WebUtils.jdAuthNotifyThirdApp(this.webUiBinder);
            }
            return true;
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return true;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        return checkJdAuthLogin(Uri.parse(str));
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_JDAUTH;
    }
}
