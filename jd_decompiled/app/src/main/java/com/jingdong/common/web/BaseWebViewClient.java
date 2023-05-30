package com.jingdong.common.web;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/* loaded from: classes6.dex */
public class BaseWebViewClient extends WebViewClient {
    private final String TAG = BaseWebViewClient.class.getSimpleName();
    private String[] validHosts = {"jdpay.com", "360buy.com", "www.msxiaoice.com", "s.yuntv.letv.com", "dwz.cn", "m.winphone.com", "car.h5.yiche.com", "m.jd.aihuishou.com", "t.cn", "itunes.apple.com", "m.car.ccopel.com", "test.car.m.ccopel.com", "hd.3g.qq.com", "mm.wanggou.com", "m.wangyin.com", "360buy.com", "jd.care", "jd.hk", "jd.com", "360buyimg.com"};
    private boolean pageStartedOnce = false;

    private void reportErrorURL(String str) {
        String c2 = com.jingdong.jdsdk.b.a.c();
        if ("WebActivity".equals(c2)) {
            c2 = com.jingdong.jdsdk.b.a.a();
        }
        ExceptionReporter.reportWebViewErrorUrl(str, c2);
    }

    private void validateUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            reportErrorURL(str);
            return;
        }
        Uri parse = Uri.parse(str);
        if (Log.D) {
            Log.d(this.TAG, "validateUrl() uri -->> " + parse);
        }
        if (parse != null) {
            String scheme = parse.getScheme();
            if (Log.D) {
                Log.d(this.TAG, "validateUrl() scheme -->> " + scheme);
            }
            if (TextUtils.isEmpty(scheme)) {
                return;
            }
            if (scheme.startsWith("http") || scheme.startsWith("https")) {
                String host = parse.getHost();
                if (Log.D) {
                    Log.d(this.TAG, "validateUrl() host -->> " + host);
                }
                boolean z = false;
                if (!TextUtils.isEmpty(host)) {
                    for (int i2 = 0; i2 < this.validHosts.length; i2++) {
                        if (host.endsWith(OrderISVUtil.MONEY_DECIMAL + this.validHosts[i2]) || host.equals(this.validHosts[i2])) {
                            break;
                        }
                    }
                }
                z = true;
                if (Log.D) {
                    Log.d(this.TAG, "validateUrl() needUploadError -->> " + z);
                }
                if (z) {
                    reportErrorURL(str);
                }
            }
        }
    }

    @Override // com.tencent.smtt.sdk.WebViewClient
    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        super.doUpdateVisitedHistory(webView, str, z);
    }

    public boolean isPageStartedOnce() {
        return this.pageStartedOnce;
    }

    @Override // com.tencent.smtt.sdk.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Log.d(this.TAG, "onPageStarted() url -->> " + str);
        this.pageStartedOnce = true;
        try {
            validateUrl(str);
        } catch (Exception e2) {
            if (Log.E) {
                e2.printStackTrace();
            }
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.tencent.smtt.sdk.WebViewClient
    public void onReceivedError(WebView webView, int i2, String str, String str2) {
        Log.d(this.TAG, "onReceivedError() failingUrl -->> " + str2);
        WebViewHelper.enablePlatformNotifications();
        super.onReceivedError(webView, i2, str, str2);
    }

    @Override // com.tencent.smtt.sdk.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        String str = this.TAG;
        String str2 = "onReceivedHttpError, request url--->" + webResourceRequest;
        String str3 = DYConstants.DY_NULL_STR;
        if (str2 != null) {
            if ((webResourceRequest.getUrl() + "   response--->" + webResourceRequest) != null) {
                str3 = webResourceResponse.getStatusCode() + "";
            }
        }
        Log.d(str, str3);
    }
}
