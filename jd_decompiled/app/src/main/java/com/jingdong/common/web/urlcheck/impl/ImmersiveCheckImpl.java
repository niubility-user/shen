package com.jingdong.common.web.urlcheck.impl;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.util.WebSwitchQueryFetcher;
import com.jingdong.corelib.utils.Log;
import com.tencent.smtt.sdk.WebView;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class ImmersiveCheckImpl extends BaseWebComponent implements ICheckUrl {
    private final String TAG;
    private Pattern pattern;

    public ImmersiveCheckImpl(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = ImmersiveCheckImpl.class.getSimpleName();
        this.pattern = Pattern.compile("/cgi-bin/app/appjmp");
    }

    public boolean checkImmersive(String str, boolean z) {
        Uri parse;
        String str2 = "";
        if (SwitchQueryFetcher.getSwitchBooleanValue(WebSwitchQueryFetcher.FIX_CHECK_URL, false)) {
            if (str != null) {
                str = str.trim();
            }
            if (!TextUtils.isEmpty(str) && (str.startsWith("https%") || str.startsWith("http%"))) {
                try {
                    str = URLDecoder.decode(str, "UTF-8");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            str2 = str;
        } else {
            try {
                str2 = URLDecoder.decode(str, "UTF-8");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            parse = Uri.parse(str2);
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        if (this.pattern.matcher(str2).find()) {
            return false;
        }
        String queryParameter = parse.getQueryParameter("transparent");
        Log.d(this.TAG, "transparentStr:" + queryParameter + " isOncreate:" + z + "   url:" + str2);
        if ("1".equals(queryParameter)) {
            if (this.webUiBinder.getJdWebView() != null) {
                this.webUiBinder.getJdWebView().setImmersive(true);
            }
        } else if (!z && this.webUiBinder.getJdWebView() != null) {
            this.webUiBinder.getJdWebView().setImmersive(false);
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkReturn() {
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public boolean checkUrl(WebView webView, String str) {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null || this.webUiBinder.getJdWebView().getShallCheckImmersiveOnInit() || TextUtils.isEmpty(str) || !str.equals(this.webUiBinder.getJdWebView().getUrl())) {
            return checkImmersive(str, false);
        }
        return false;
    }

    @Override // com.jingdong.common.web.urlcheck.ICheckUrl
    public String getKey() {
        return WebUiConstans.UrlCheckKeys.CHECK_IMMERSIVE;
    }
}
