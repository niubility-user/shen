package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.hms.framework.common.ContainerUtils;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.connect.auth.b;
import com.tencent.connect.common.Constants;
import com.tencent.open.b.h;
import com.tencent.open.log.SLog;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.m;
import com.tencent.open.web.security.JniInterface;
import com.tencent.open.web.security.SecureJsInterface;
import com.tencent.tauth.DefaultUiListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class a extends Dialog {
    private String a;
    private b b;

    /* renamed from: c  reason: collision with root package name */
    private IUiListener f16111c;
    private Handler d;

    /* renamed from: e  reason: collision with root package name */
    private FrameLayout f16112e;

    /* renamed from: f  reason: collision with root package name */
    private LinearLayout f16113f;

    /* renamed from: g  reason: collision with root package name */
    private FrameLayout f16114g;

    /* renamed from: h  reason: collision with root package name */
    private ProgressBar f16115h;

    /* renamed from: i  reason: collision with root package name */
    private String f16116i;

    /* renamed from: j  reason: collision with root package name */
    private com.tencent.open.c.d f16117j;

    /* renamed from: k  reason: collision with root package name */
    private Context f16118k;

    /* renamed from: l  reason: collision with root package name */
    private com.tencent.open.web.security.b f16119l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f16120m;

    /* renamed from: n  reason: collision with root package name */
    private int f16121n;
    private String o;
    private String p;
    private long q;
    private long r;
    private HashMap<String, Runnable> s;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.tencent.connect.auth.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class C0777a extends WebViewClient {
        private C0777a() {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            SLog.v("openSDK_LOG.AuthDialog", "-->onPageFinished, url: " + str);
            a.this.f16114g.setVisibility(8);
            if (a.this.f16117j != null) {
                a.this.f16117j.setVisibility(0);
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            a.this.d.removeCallbacks((Runnable) a.this.s.remove(str));
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            SLog.v("openSDK_LOG.AuthDialog", "-->onPageStarted, url: " + str);
            super.onPageStarted(webView, str, bitmap);
            a.this.f16114g.setVisibility(0);
            a.this.q = SystemClock.elapsedRealtime();
            if (!TextUtils.isEmpty(a.this.o)) {
                a.this.d.removeCallbacks((Runnable) a.this.s.remove(a.this.o));
            }
            a.this.o = str;
            a aVar = a.this;
            d dVar = new d(aVar.o);
            a.this.s.put(str, dVar);
            a.this.d.postDelayed(dVar, 120000L);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            super.onReceivedError(webView, i2, str, str2);
            SLog.i("openSDK_LOG.AuthDialog", "-->onReceivedError, errorCode: " + i2 + " | description: " + str);
            if (!m.b(a.this.f16118k)) {
                a.this.b.onError(new UiError(9001, "\u5f53\u524d\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", str2));
                a.this.dismiss();
            } else if (!a.this.o.startsWith("https://imgcache.qq.com/ptlogin/static/qzsjump.html?")) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - a.this.q;
                if (a.this.f16121n >= 1 || elapsedRealtime >= a.this.r) {
                    a.this.f16117j.loadUrl(a.this.a());
                    return;
                }
                a.m(a.this);
                a.this.d.postDelayed(new Runnable() { // from class: com.tencent.connect.auth.a.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.f16117j.loadUrl(a.this.o);
                    }
                }, 500L);
            } else {
                a.this.b.onError(new UiError(i2, str, str2));
                a.this.dismiss();
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(8)
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            SLog.e("openSDK_LOG.AuthDialog", "-->onReceivedSslError " + sslError.getPrimaryError() + "\u8bf7\u6c42\u4e0d\u5408\u6cd5\uff0c\u8bf7\u68c0\u67e5\u624b\u673a\u5b89\u5168\u8bbe\u7f6e\uff0c\u5982\u7cfb\u7edf\u65f6\u95f4\u3001\u4ee3\u7406\u7b49");
            sslErrorHandler.cancel();
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse;
            List<String> pathSegments;
            SLog.v("openSDK_LOG.AuthDialog", "-->Redirect URL: " + str);
            if (str.startsWith("auth://browser")) {
                JSONObject c2 = m.c(str);
                a aVar = a.this;
                aVar.f16120m = aVar.e();
                if (!a.this.f16120m) {
                    if (c2.optString("fail_cb", null) != null) {
                        a.this.a(c2.optString("fail_cb"), "");
                    } else if (c2.optInt("fall_to_wv") == 1) {
                        a aVar2 = a.this;
                        StringBuilder sb = new StringBuilder();
                        sb.append(a.this.a);
                        sb.append(a.this.a.indexOf("?") > -1 ? ContainerUtils.FIELD_DELIMITER : "?");
                        aVar2.a = sb.toString();
                        a.this.a = a.this.a + "browser_error=1";
                        a.this.f16117j.loadUrl(a.this.a);
                    } else {
                        String optString = c2.optString("redir", null);
                        if (optString != null) {
                            a.this.f16117j.loadUrl(optString);
                        }
                    }
                }
                return true;
            } else if (str.startsWith("auth://tauth.qq.com/")) {
                a.this.b.onComplete(m.c(str));
                a.this.dismiss();
                return true;
            } else if (str.startsWith(Constants.CANCEL_URI)) {
                a.this.b.onCancel();
                a.this.dismiss();
                return true;
            } else if (str.startsWith(Constants.CLOSE_URI)) {
                a.this.dismiss();
                return true;
            } else if (!str.startsWith(Constants.DOWNLOAD_URI) && !str.endsWith(".apk")) {
                if (str.startsWith("auth://progress")) {
                    try {
                        pathSegments = Uri.parse(str).getPathSegments();
                    } catch (Exception unused) {
                    }
                    if (pathSegments.isEmpty()) {
                        return true;
                    }
                    int intValue = Integer.valueOf(pathSegments.get(0)).intValue();
                    if (intValue == 0) {
                        a.this.f16114g.setVisibility(8);
                        a.this.f16117j.setVisibility(0);
                    } else if (intValue == 1) {
                        a.this.f16114g.setVisibility(0);
                    }
                    return true;
                } else if (!str.startsWith("auth://onLoginSubmit")) {
                    if (a.this.f16119l.a(a.this.f16117j, str)) {
                        return true;
                    }
                    SLog.i("openSDK_LOG.AuthDialog", "-->Redirect URL: return false");
                    return false;
                } else {
                    try {
                        List<String> pathSegments2 = Uri.parse(str).getPathSegments();
                        if (!pathSegments2.isEmpty()) {
                            a.this.p = pathSegments2.get(0);
                        }
                    } catch (Exception unused2) {
                    }
                    return true;
                }
            } else {
                try {
                    if (str.startsWith(Constants.DOWNLOAD_URI)) {
                        parse = Uri.parse(Uri.decode(str.substring(11)));
                    } else {
                        parse = Uri.parse(Uri.decode(str));
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", parse);
                    intent.addFlags(268435456);
                    a.this.f16118k.startActivity(intent);
                } catch (Exception e2) {
                    SLog.e("openSDK_LOG.AuthDialog", "-->start download activity exception, e: ", e2);
                }
                return true;
            }
        }
    }

    /* loaded from: classes9.dex */
    private class b extends DefaultUiListener {
        String a;
        String b;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private IUiListener f16123e;

        public b(String str, String str2, String str3, IUiListener iUiListener) {
            this.d = str;
            this.a = str2;
            this.b = str3;
            this.f16123e = iUiListener;
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onCancel() {
            IUiListener iUiListener = this.f16123e;
            if (iUiListener != null) {
                iUiListener.onCancel();
                this.f16123e = null;
            }
        }

        @Override // com.tencent.tauth.DefaultUiListener, com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            h.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, jSONObject.optInt("ret", -6), this.a, false);
            IUiListener iUiListener = this.f16123e;
            if (iUiListener != null) {
                iUiListener.onComplete(jSONObject);
                this.f16123e = null;
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
            h.a().a(this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, uiError.errorCode, str, false);
            a.this.a(str);
            IUiListener iUiListener = this.f16123e;
            if (iUiListener != null) {
                iUiListener.onError(uiError);
                this.f16123e = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            try {
                onComplete(m.d(str));
            } catch (JSONException e2) {
                e2.printStackTrace();
                onError(new UiError(-4, Constants.MSG_JSON_ERROR, str));
            }
        }
    }

    /* loaded from: classes9.dex */
    private class c extends Handler {
        private b b;

        public c(b bVar, Looper looper) {
            super(looper);
            this.b = bVar;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                this.b.a((String) message.obj);
            } else if (i2 == 2) {
                this.b.onCancel();
            } else if (i2 != 3) {
            } else {
                a.b(a.this.f16118k, (String) message.obj);
            }
        }
    }

    /* loaded from: classes9.dex */
    class d implements Runnable {
        String a;

        public d(String str) {
            this.a = "";
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            SLog.v("openSDK_LOG.AuthDialog", "-->timeoutUrl: " + this.a + " | mRetryUrl: " + a.this.o);
            if (this.a.equals(a.this.o)) {
                a.this.b.onError(new UiError(9002, "\u8bf7\u6c42\u9875\u9762\u8d85\u65f6\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5\uff01", a.this.o));
                a.this.dismiss();
            }
        }
    }

    public a(Context context, String str, String str2, IUiListener iUiListener, QQToken qQToken) {
        super(context, 16973840);
        this.f16120m = false;
        this.q = 0L;
        this.r = Final.HALF_MINUTE;
        this.f16118k = context;
        this.a = str2;
        this.b = new b(str, str2, qQToken.getAppId(), iUiListener);
        this.d = new c(this.b, context.getMainLooper());
        this.f16111c = iUiListener;
        this.f16116i = str;
        this.f16119l = new com.tencent.open.web.security.b();
        getWindow().setSoftInputMode(32);
    }

    static /* synthetic */ int m(a aVar) {
        int i2 = aVar.f16121n;
        aVar.f16121n = i2 + 1;
        return i2;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.s.clear();
        this.d.removeCallbacksAndMessages(null);
        try {
            Context context = this.f16118k;
            if ((context instanceof Activity) && !((Activity) context).isFinishing() && isShowing()) {
                super.dismiss();
                SLog.i("openSDK_LOG.AuthDialog", "-->dismiss dialog");
            }
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.AuthDialog", "-->dismiss dialog exception:", e2);
        }
        com.tencent.open.c.d dVar = this.f16117j;
        if (dVar != null) {
            dVar.destroy();
            this.f16117j = null;
        }
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (!this.f16120m) {
            this.b.onCancel();
        }
        super.onBackPressed();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        com.tencent.open.a.a(getWindow());
        b();
        d();
        this.s = new HashMap<>();
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e() {
        com.tencent.connect.auth.b a = com.tencent.connect.auth.b.a();
        String c2 = a.c();
        b.a aVar = new b.a();
        aVar.a = this.f16111c;
        aVar.b = this;
        aVar.f16126c = c2;
        String a2 = a.a(aVar);
        String str = this.a;
        String substring = str.substring(0, str.indexOf("?"));
        Bundle b2 = m.b(this.a);
        b2.putString("token_key", c2);
        b2.putString("serial", a2);
        b2.putString("browser", "1");
        String str2 = substring + "?" + HttpUtils.encodeUrl(b2);
        this.a = str2;
        return m.a(this.f16118k, str2);
    }

    private void b() {
        try {
            c();
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            com.tencent.open.c.d dVar = new com.tencent.open.c.d(this.f16118k);
            this.f16117j = dVar;
            if (Build.VERSION.SDK_INT >= 11) {
                dVar.setLayerType(1, null);
            }
            this.f16117j.setLayoutParams(layoutParams);
            layoutParams.gravity = 17;
            com.tencent.open.c.c cVar = new com.tencent.open.c.c(this.f16118k);
            cVar.setLayoutParams(layoutParams);
            cVar.addView(this.f16117j);
            FrameLayout frameLayout = new FrameLayout(this.f16118k);
            this.f16112e = frameLayout;
            frameLayout.addView(cVar);
            this.f16112e.setBackgroundColor(-1);
            this.f16112e.addView(this.f16114g);
            String string = m.b(this.a).getString(DeeplinkProductDetailHelper.LAYER_STYLE);
            if (string != null && "qr".equals(string)) {
                a(this.f16112e);
            }
            setContentView(this.f16112e);
        } catch (Exception e2) {
            SLog.e("openSDK_LOG.AuthDialog", "onCreateView exception", e2);
            com.tencent.open.a.a(this, this.d);
        }
    }

    private void c() {
        TextView textView;
        this.f16115h = new ProgressBar(this.f16118k);
        this.f16115h.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.f16113f = new LinearLayout(this.f16118k);
        if (this.f16116i.equals("action_login")) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            layoutParams.leftMargin = 5;
            textView = new TextView(this.f16118k);
            if (Locale.getDefault().getLanguage().equals("zh")) {
                textView.setText("\u767b\u5f55\u4e2d...");
            } else {
                textView.setText("Logging in...");
            }
            textView.setTextColor(Color.rgb(255, 255, 255));
            textView.setTextSize(18.0f);
            textView.setLayoutParams(layoutParams);
        } else {
            textView = null;
        }
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        this.f16113f.setLayoutParams(layoutParams2);
        this.f16113f.addView(this.f16115h);
        if (textView != null) {
            this.f16113f.addView(textView);
        }
        this.f16114g = new FrameLayout(this.f16118k);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams3.gravity = 17;
        this.f16114g.setLayoutParams(layoutParams3);
        this.f16114g.setBackgroundColor(Color.parseColor("#B3000000"));
        this.f16114g.addView(this.f16113f);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void d() {
        this.f16117j.setVerticalScrollBarEnabled(false);
        this.f16117j.setHorizontalScrollBarEnabled(false);
        this.f16117j.setWebViewClient(new C0777a());
        this.f16117j.setWebChromeClient(new WebChromeClient());
        this.f16117j.clearFormData();
        this.f16117j.clearSslPreferences();
        this.f16117j.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.tencent.connect.auth.a.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.f16117j.setOnTouchListener(new View.OnTouchListener() { // from class: com.tencent.connect.auth.a.3
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if ((action == 0 || action == 1) && !view.hasFocus()) {
                    view.requestFocus();
                    return false;
                }
                return false;
            }
        });
        WebSettings settings = this.f16117j.getSettings();
        com.tencent.open.web.a.a(this.f16117j);
        settings.setSaveFormData(false);
        settings.setCacheMode(-1);
        settings.setNeedInitialFocus(false);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(this.f16118k.getDir("databases", 0).getPath());
        settings.setDomStorageEnabled(true);
        SLog.v("openSDK_LOG.AuthDialog", "-->mUrl : " + this.a);
        String str = this.a;
        this.o = str;
        this.f16117j.loadUrl(str);
        this.f16117j.setVisibility(4);
        this.f16119l.a(new SecureJsInterface(), "SecureJsInterface");
        SecureJsInterface.isPWDEdit = false;
        super.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.tencent.connect.auth.a.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                try {
                    if (JniInterface.isJniOk) {
                        JniInterface.clearAllPWD();
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (!TextUtils.isEmpty(this.p) && this.p.length() >= 4) {
            String str2 = this.p;
            String substring = str2.substring(str2.length() - 4);
            sb.append("_u_");
            sb.append(substring);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a() {
        String str = this.a;
        String str2 = "https://imgcache.qq.com/ptlogin/static/qzsjump.html?" + str.substring(str.indexOf("?") + 1);
        SLog.i("openSDK_LOG.AuthDialog", "-->generateDownloadUrl, url: https://imgcache.qq.com/ptlogin/static/qzsjump.html?");
        return str2;
    }

    private void a(ViewGroup viewGroup) {
        ImageView imageView = new ImageView(this.f16118k);
        int a = com.tencent.connect.avatar.a.a(this.f16118k, 15.6f);
        int a2 = com.tencent.connect.avatar.a.a(this.f16118k, 25.2f);
        int a3 = com.tencent.connect.avatar.a.a(this.f16118k, 10.0f);
        int i2 = a3 * 2;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(a + i2, a2 + i2);
        layoutParams.leftMargin = a3;
        imageView.setLayoutParams(layoutParams);
        imageView.setPadding(a3, a3, a3, a3);
        imageView.setImageDrawable(m.a("h5_qr_back.png", this.f16118k));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.tencent.connect.auth.a.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a.this.dismiss();
                if (a.this.f16120m || a.this.b == null) {
                    return;
                }
                a.this.b.onCancel();
            }
        });
        viewGroup.addView(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str) {
        try {
            JSONObject d2 = m.d(str);
            int i2 = d2.getInt("type");
            Toast.makeText(context.getApplicationContext(), d2.getString("msg"), i2).show();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void a(String str, String str2) {
        this.f16117j.loadUrl("javascript:" + str + "(" + str2 + ");void(" + System.currentTimeMillis() + ");");
    }
}
