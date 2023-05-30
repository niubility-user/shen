package com.tencent.open;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.b;
import com.tencent.open.b.h;
import com.tencent.open.c.a;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.j;
import com.tencent.open.utils.m;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class d extends com.tencent.open.c implements a.InterfaceC0809a {

    /* renamed from: c */
    static Toast f17659c;
    private String d;

    /* renamed from: e */
    private IUiListener f17660e;

    /* renamed from: f */
    private c f17661f;

    /* renamed from: g */
    private Handler f17662g;

    /* renamed from: h */
    private com.tencent.open.c.a f17663h;

    /* renamed from: i */
    private com.tencent.open.c.b f17664i;

    /* renamed from: j */
    private WeakReference<Context> f17665j;

    /* renamed from: k */
    private int f17666k;

    /* loaded from: classes9.dex */
    public class a extends WebViewClient {
        private a() {
            d.this = r1;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            d.this.f17664i.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            SLog.v("openSDK_LOG.PKDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            d.this.f17661f.onError(new UiError(i2, str, str2));
            if (d.this.f17665j != null && d.this.f17665j.get() != null) {
                Toast.makeText((Context) d.this.f17665j.get(), "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\u6216\u7cfb\u7edf\u9519\u8bef", 0).show();
            }
            d.this.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            SLog.v("openSDK_LOG.PKDialog", "Redirect URL: " + str);
            if (str.startsWith(j.a().a((Context) d.this.f17665j.get(), "auth://tauth.qq.com/"))) {
                d.this.f17661f.onComplete(m.c(str));
                d.this.dismiss();
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                d.this.f17661f.onCancel();
                d.this.dismiss();
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                d.this.dismiss();
                return true;
            } else {
                return false;
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b extends b.C0808b {
        private b() {
            d.this = r1;
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends DefaultUiListener {
        String a;
        String b;

        /* renamed from: c */
        private WeakReference<Context> f17667c;
        private String d;

        /* renamed from: e */
        private IUiListener f17668e;

        public c(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.f17667c = new WeakReference<>(context);
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.f17668e = iUiListener;
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f17668e;
            if (iUiListener != null) {
                iUiListener.onCancel();
                this.f17668e = null;
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            h.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.a, false);
            IUiListener iUiListener = this.f17668e;
            if (iUiListener != null) {
                iUiListener.onComplete(jSONObject);
                this.f17668e = null;
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            String str;
            if (uiError.errorMessage != null) {
                str = uiError.errorMessage + this.a;
            } else {
                str = this.a;
            }
            h a = h.a();
            a.a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, str, false);
            IUiListener iUiListener = this.f17668e;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
                this.f17668e = null;
            }
        }

        public void a(String str) {
            try {
                onComplete(m.d(str));
            } catch (JSONException e2) {
                e2.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }
    }

    /* renamed from: com.tencent.open.d$d */
    /* loaded from: classes9.dex */
    public class HandlerC0810d extends Handler {
        private c b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HandlerC0810d(c cVar, Looper looper) {
            super(looper);
            d.this = r1;
            this.b = cVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SLog.d("openSDK_LOG.PKDialog", "msg = " + message.what);
            int i2 = message.what;
            if (i2 == 1) {
                this.b.a((String) message.obj);
            } else if (i2 == 2) {
                this.b.onCancel();
            } else if (i2 != 3) {
                if (i2 != 5 || d.this.f17665j == null || d.this.f17665j.get() == null) {
                    return;
                }
                d.d((Context) d.this.f17665j.get(), (String) message.obj);
            } else if (d.this.f17665j == null || d.this.f17665j.get() == null) {
            } else {
                d.c((Context) d.this.f17665j.get(), (String) message.obj);
            }
        }
    }

    public d(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.f17665j = new WeakReference<>(context);
        this.d = str2;
        this.f17661f = new c(context, str, str2, qQToken.getAppId(), iUiListener);
        this.f17662g = new HandlerC0810d(this.f17661f, context.getMainLooper());
        this.f17660e = iUiListener;
        this.f17666k = Math.round(context.getResources().getDisplayMetrics().density * 185.0f);
        SLog.e("openSDK_LOG.PKDialog", "density=" + context.getResources().getDisplayMetrics().density + "; webviewHeight=" + this.f17666k);
    }

    public static void d(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            JSONObject d = m.d(str);
            d.getInt("action");
            d.getString("msg");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.tencent.open.c, android.app.Dialog
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        getWindow().setSoftInputMode(16);
        getWindow().setSoftInputMode(1);
        b();
        c();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void c() {
        this.f17664i.setVerticalScrollBarEnabled(false);
        this.f17664i.setHorizontalScrollBarEnabled(false);
        this.f17664i.setWebViewClient(new a());
        this.f17664i.setWebChromeClient(this.b);
        this.f17664i.clearFormData();
        WebSettings settings = this.f17664i.getSettings();
        if (settings == null) {
            return;
        }
        com.tencent.open.web.a.a(this.f17664i);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        WeakReference<Context> weakReference = this.f17665j;
        if (weakReference != null && weakReference.get() != null) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(this.f17665j.get().getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.a.a(new b(), "sdk_js_if");
        this.f17664i.clearView();
        this.f17664i.loadUrl(this.d);
    }

    private void b() {
        com.tencent.open.c.a aVar = new com.tencent.open.c.a(this.f17665j.get());
        this.f17663h = aVar;
        aVar.setBackgroundColor(1711276032);
        this.f17663h.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        com.tencent.open.c.b bVar = new com.tencent.open.c.b(this.f17665j.get());
        this.f17664i = bVar;
        bVar.setBackgroundColor(0);
        this.f17664i.setBackgroundDrawable(null);
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                View.class.getMethod("setLayerType", Integer.TYPE, Paint.class).invoke(this.f17664i, 1, new Paint());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, this.f17666k);
        layoutParams.addRule(13, -1);
        this.f17664i.setLayoutParams(layoutParams);
        this.f17663h.addView(this.f17664i);
        this.f17663h.a(this);
        setContentView(this.f17663h);
    }

    @Override // com.tencent.open.c.a.InterfaceC0809a
    public void a(int i2) {
        WeakReference<Context> weakReference = this.f17665j;
        if (weakReference != null && weakReference.get() != null) {
            if (i2 < this.f17666k && 2 == this.f17665j.get().getResources().getConfiguration().orientation) {
                this.f17664i.getLayoutParams().height = i2;
            } else {
                this.f17664i.getLayoutParams().height = this.f17666k;
            }
        }
        SLog.e("openSDK_LOG.PKDialog", "onKeyboardShown keyboard show");
    }

    @Override // com.tencent.open.c.a.InterfaceC0809a
    public void a() {
        this.f17664i.getLayoutParams().height = this.f17666k;
        SLog.e("openSDK_LOG.PKDialog", "onKeyboardHidden keyboard hide");
    }

    @Override // com.tencent.open.c
    protected void a(String str) {
        SLog.d("openSDK_LOG.PKDialog", "--onConsoleMessage--");
        try {
            this.a.a(this.f17664i, str);
        } catch (Exception unused) {
        }
    }

    public static void c(Context context, String str) {
        try {
            JSONObject d = m.d(str);
            int i2 = d.getInt("type");
            String string = d.getString("msg");
            if (i2 == 0) {
                Toast toast = f17659c;
                if (toast == null) {
                    f17659c = Toast.makeText(context, string, 0);
                } else {
                    toast.setView(toast.getView());
                    f17659c.setText(string);
                    f17659c.setDuration(0);
                }
                f17659c.show();
            } else if (i2 == 1) {
                Toast toast2 = f17659c;
                if (toast2 == null) {
                    f17659c = Toast.makeText(context, string, 1);
                } else {
                    toast2.setView(toast2.getView());
                    f17659c.setText(string);
                    f17659c.setDuration(1);
                }
                f17659c.show();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
