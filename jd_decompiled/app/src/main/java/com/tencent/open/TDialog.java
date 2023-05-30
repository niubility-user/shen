package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.open.b;
import com.tencent.open.b.h;
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
public class TDialog extends c {

    /* renamed from: c */
    static final FrameLayout.LayoutParams f17620c = new FrameLayout.LayoutParams(-1, -1);
    static Toast d = null;

    /* renamed from: f */
    private static WeakReference<ProgressDialog> f17621f;

    /* renamed from: e */
    private WeakReference<Context> f17622e;

    /* renamed from: g */
    private String f17623g;

    /* renamed from: h */
    private OnTimeListener f17624h;

    /* renamed from: i */
    private IUiListener f17625i;

    /* renamed from: j */
    private FrameLayout f17626j;

    /* renamed from: k */
    private com.tencent.open.c.b f17627k;

    /* renamed from: l */
    private Handler f17628l;

    /* renamed from: m */
    private boolean f17629m;

    /* renamed from: n */
    private QQToken f17630n;

    /* loaded from: classes9.dex */
    public class FbWebViewClient extends WebViewClient {
        private FbWebViewClient() {
            TDialog.this = r1;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            TDialog.this.f17627k.setVisibility(0);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            SLog.v("openSDK_LOG.TDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            TDialog.this.f17624h.onError(new UiError(i2, str, str2));
            if (TDialog.this.f17622e != null && TDialog.this.f17622e.get() != null) {
                Toast.makeText((Context) TDialog.this.f17622e.get(), "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\u6216\u7cfb\u7edf\u9519\u8bef", 0).show();
            }
            TDialog.this.dismiss();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse;
            SLog.v("openSDK_LOG.TDialog", "Redirect URL: " + str);
            if (str.startsWith(j.a().a((Context) TDialog.this.f17622e.get(), "auth://tauth.qq.com/"))) {
                TDialog.this.f17624h.onComplete(m.c(str));
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                TDialog.this.f17624h.onCancel();
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                if (TDialog.this.isShowing()) {
                    TDialog.this.dismiss();
                }
                return true;
            } else if (!str.startsWith(Constants.DOWNLOAD_URI) && !str.endsWith(".apk")) {
                return str.startsWith("auth://progress");
            } else {
                try {
                    if (str.startsWith(Constants.DOWNLOAD_URI)) {
                        parse = Uri.parse(Uri.decode(str.substring(11)));
                    } else {
                        parse = Uri.parse(Uri.decode(str));
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", parse);
                    intent.addFlags(268435456);
                    if (TDialog.this.f17622e != null && TDialog.this.f17622e.get() != null) {
                        ((Context) TDialog.this.f17622e.get()).startActivity(intent);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return true;
            }
        }
    }

    /* loaded from: classes9.dex */
    public class JsListener extends b.C0808b {
        private JsListener() {
            TDialog.this = r1;
        }

        public void onAddShare(String str) {
            SLog.d("openSDK_LOG.TDialog", "JsListener onAddShare");
            onComplete(str);
        }

        public void onCancel(String str) {
            SLog.e("openSDK_LOG.TDialog", "JsListener onCancel --msg = " + str);
            TDialog.this.f17628l.obtainMessage(2, str).sendToTarget();
            TDialog.this.dismiss();
        }

        public void onCancelAddShare(String str) {
            SLog.e("openSDK_LOG.TDialog", "JsListener onCancelAddShare" + str);
            onCancel("cancel");
        }

        public void onCancelInvite() {
            SLog.e("openSDK_LOG.TDialog", "JsListener onCancelInvite");
            onCancel("");
        }

        public void onCancelLogin() {
            onCancel("");
        }

        public void onComplete(String str) {
            TDialog.this.f17628l.obtainMessage(1, str).sendToTarget();
            SLog.e("openSDK_LOG.TDialog", "JsListener onComplete" + str);
            TDialog.this.dismiss();
        }

        public void onInvite(String str) {
            onComplete(str);
        }

        public void onLoad(String str) {
            TDialog.this.f17628l.obtainMessage(4, str).sendToTarget();
        }

        public void showMsg(String str) {
            TDialog.this.f17628l.obtainMessage(3, str).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class OnTimeListener extends DefaultUiListener {
        String a;
        String b;

        /* renamed from: c */
        private WeakReference<Context> f17631c;
        private String d;

        /* renamed from: e */
        private IUiListener f17632e;

        public OnTimeListener(Context context, String str, String str2, String str3, IUiListener iUiListener) {
            this.f17631c = new WeakReference<>(context);
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.f17632e = iUiListener;
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f17632e;
            if (iUiListener != null) {
                iUiListener.onCancel();
                this.f17632e = null;
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            h.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.a, false);
            IUiListener iUiListener = this.f17632e;
            if (iUiListener != null) {
                iUiListener.onComplete(jSONObject);
                this.f17632e = null;
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
            IUiListener iUiListener = this.f17632e;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
                this.f17632e = null;
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

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class THandler extends Handler {
        private OnTimeListener b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public THandler(OnTimeListener onTimeListener, Looper looper) {
            super(looper);
            TDialog.this = r1;
            this.b = onTimeListener;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            SLog.d("openSDK_LOG.TDialog", "--handleMessage--msg.WHAT = " + message.what);
            int i2 = message.what;
            if (i2 == 1) {
                this.b.a((String) message.obj);
            } else if (i2 == 2) {
                this.b.onCancel();
            } else if (i2 != 3) {
                if (i2 != 5 || TDialog.this.f17622e == null || TDialog.this.f17622e.get() == null) {
                    return;
                }
                TDialog.d((Context) TDialog.this.f17622e.get(), (String) message.obj);
            } else if (TDialog.this.f17622e == null || TDialog.this.f17622e.get() == null) {
            } else {
                TDialog.c((Context) TDialog.this.f17622e.get(), (String) message.obj);
            }
        }
    }

    public TDialog(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.f17629m = false;
        this.f17630n = null;
        this.f17622e = new WeakReference<>(context);
        this.f17623g = str2;
        this.f17624h = new OnTimeListener(context, str, str2, qQToken.getAppId(), iUiListener);
        this.f17628l = new THandler(this.f17624h, context.getMainLooper());
        this.f17625i = iUiListener;
        this.f17630n = qQToken;
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        OnTimeListener onTimeListener = this.f17624h;
        if (onTimeListener != null) {
            onTimeListener.onCancel();
        }
        super.onBackPressed();
    }

    @Override // com.tencent.open.c, android.app.Dialog
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        a.a(getWindow());
        a();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.tencent.open.TDialog.1
            {
                TDialog.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                View decorView;
                View childAt;
                Window window = TDialog.this.getWindow();
                if (window == null || (decorView = window.getDecorView()) == null || (childAt = ((ViewGroup) decorView).getChildAt(0)) == null) {
                    return;
                }
                childAt.setPadding(0, 0, 0, 0);
            }
        });
        b();
    }

    public static void c(Context context, String str) {
        try {
            JSONObject d2 = m.d(str);
            int i2 = d2.getInt("type");
            String string = d2.getString("msg");
            if (i2 == 0) {
                Toast toast = d;
                if (toast == null) {
                    d = Toast.makeText(context, string, 0);
                } else {
                    toast.setView(toast.getView());
                    d.setText(string);
                    d.setDuration(0);
                }
                d.show();
            } else if (i2 == 1) {
                Toast toast2 = d;
                if (toast2 == null) {
                    d = Toast.makeText(context, string, 1);
                } else {
                    toast2.setView(toast2.getView());
                    d.setText(string);
                    d.setDuration(1);
                }
                d.show();
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void d(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        try {
            JSONObject d2 = m.d(str);
            int i2 = d2.getInt("action");
            String string = d2.getString("msg");
            if (i2 == 1) {
                WeakReference<ProgressDialog> weakReference = f17621f;
                if (weakReference != null && weakReference.get() != null) {
                    f17621f.get().setMessage(string);
                    if (!f17621f.get().isShowing()) {
                        f17621f.get().show();
                    }
                }
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(string);
                f17621f = new WeakReference<>(progressDialog);
                progressDialog.show();
            } else if (i2 == 0) {
                WeakReference<ProgressDialog> weakReference2 = f17621f;
                if (weakReference2 == null) {
                    return;
                }
                if (weakReference2.get() != null && f17621f.get().isShowing()) {
                    f17621f.get().dismiss();
                    f17621f = null;
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void a() {
        try {
            new TextView(this.f17622e.get()).setText("test");
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            com.tencent.open.c.b bVar = new com.tencent.open.c.b(this.f17622e.get());
            this.f17627k = bVar;
            bVar.setLayoutParams(layoutParams);
            layoutParams.gravity = 17;
            com.tencent.open.c.c cVar = new com.tencent.open.c.c(this.f17622e.get());
            this.f17626j = cVar;
            cVar.setLayoutParams(layoutParams);
            this.f17626j.setBackgroundColor(-1);
            this.f17626j.addView(this.f17627k);
            setContentView(this.f17626j);
        } catch (Throwable th) {
            SLog.e("openSDK_LOG.TDialog", "onCreateView exception", th);
            a.a(this, this.f17628l);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void b() {
        this.f17627k.setVerticalScrollBarEnabled(false);
        this.f17627k.setHorizontalScrollBarEnabled(false);
        this.f17627k.setWebViewClient(new FbWebViewClient());
        this.f17627k.setWebChromeClient(this.b);
        this.f17627k.clearFormData();
        WebSettings settings = this.f17627k.getSettings();
        if (settings == null) {
            return;
        }
        com.tencent.open.web.a.a(this.f17627k);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        WeakReference<Context> weakReference = this.f17622e;
        if (weakReference != null && weakReference.get() != null) {
            settings.setDatabaseEnabled(true);
            settings.setDatabasePath(this.f17622e.get().getApplicationContext().getDir("databases", 0).getPath());
        }
        settings.setDomStorageEnabled(true);
        this.a.a(new JsListener(), "sdk_js_if");
        this.f17627k.loadUrl(this.f17623g);
        this.f17627k.setLayoutParams(f17620c);
        this.f17627k.setVisibility(4);
    }

    @Override // com.tencent.open.c
    protected void a(String str) {
        SLog.d("openSDK_LOG.TDialog", "--onConsoleMessage--");
        try {
            this.a.a(this.f17627k, str);
        } catch (Exception unused) {
        }
    }
}
