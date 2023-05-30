package com.jingdong.sdk.jweb.x5;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.jingdong.jweb.R;
import com.jingdong.sdk.jweb.JWebFactory;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes7.dex */
public class DebugX5Activity extends Activity {

    /* loaded from: classes7.dex */
    class a extends WebChromeClient {
        a(DebugX5Activity debugX5Activity) {
        }

        @Override // com.tencent.smtt.sdk.WebChromeClient
        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            return super.onJsAlert(webView, str, str2, jsResult);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.jweb_activity_debug_x5);
        if (JWebFactory.isCoreReady(JWebFactory.JSContextType.CT_TYPE_X5)) {
            WebView webView = new WebView(this);
            webView.setWebChromeClient(new a(this));
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webView.loadUrl("http://debugx5.qq.com");
            ((FrameLayout) findViewById(R.id.jweb_debug_x5_root_layout)).addView(webView);
        }
    }
}
