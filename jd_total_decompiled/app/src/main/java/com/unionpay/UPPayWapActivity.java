package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jingdong.common.utils.pay.CashierDeskConstantBean;
import com.unionpay.utils.UPUtils;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class UPPayWapActivity extends Activity {
    private static String a = "ex_mode";
    private WebView b;

    /* renamed from: c  reason: collision with root package name */
    private WebViewJavascriptBridge f18136c;
    private AlertDialog d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18137e = false;

    /* renamed from: f  reason: collision with root package name */
    private String f18138f = "";

    /* renamed from: g  reason: collision with root package name */
    private String f18139g;

    /* renamed from: h  reason: collision with root package name */
    private View f18140h;

    /* renamed from: i  reason: collision with root package name */
    private ab f18141i;

    private View a(RelativeLayout relativeLayout, View.OnClickListener onClickListener) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundDrawable(com.unionpay.utils.g.a(com.unionpay.utils.h.b));
        int a2 = com.unionpay.utils.f.a(this, 24.0f);
        int a3 = com.unionpay.utils.f.a(this, 18.0f);
        int a4 = com.unionpay.utils.f.a(this, 14.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(10, -1);
        layoutParams.setMargins(a3, a4, 0, 0);
        relativeLayout.addView(imageView, layoutParams);
        if (onClickListener == null) {
            imageView.setOnClickListener(new m(this));
        } else {
            imageView.setOnClickListener(onClickListener);
        }
        return imageView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(uPPayWapActivity);
        uPPayWapActivity.d = builder.create();
        builder.setMessage(com.unionpay.utils.k.a().a);
        builder.setTitle(com.unionpay.utils.k.a().d);
        builder.setPositiveButton(com.unionpay.utils.k.a().b, new o(uPPayWapActivity));
        builder.setNegativeButton(com.unionpay.utils.k.a().f18233c, new p(uPPayWapActivity));
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity, boolean z) {
        View view = uPPayWapActivity.f18140h;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("pay_result", str);
        intent.putExtra("result_data", str2);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject.put("code", str);
            }
            if (str2 != null) {
                jSONObject.put("msg", str2);
            }
            if (str3 != null) {
                jSONObject.put("value", str3);
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String str, String str2, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject2.put("code", str);
            }
            if (str2 != null) {
                jSONObject2.put("msg", str2);
            }
            if (jSONObject != null) {
                jSONObject2.put("value", jSONObject);
            }
            return jSONObject2.toString();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
        } catch (Exception unused) {
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1 && i3 == -1) {
            try {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String str = "";
                    String string = extras.containsKey("pay_result") ? extras.getString("pay_result") : extras.containsKey("code") ? extras.getString("code") : "";
                    if (TextUtils.isEmpty(string)) {
                        string = "";
                    }
                    String string2 = extras.containsKey("data") ? extras.getString("data") : "";
                    if (!TextUtils.isEmpty(string2)) {
                        str = string2;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("code", string);
                    jSONObject.put("data", str);
                    ab abVar = this.f18141i;
                    if (abVar != null) {
                        abVar.a(b("0", (String) null, jSONObject));
                    }
                } else {
                    ab abVar2 = this.f18141i;
                    if (abVar2 != null) {
                        abVar2.a(b("1", "No pay result", (String) null));
                    }
                }
            } catch (Exception unused) {
                ab abVar3 = this.f18141i;
                if (abVar3 != null) {
                    abVar3.a(b("1", "No pay result", (String) null));
                }
            }
            this.f18141i = null;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        View.OnClickListener nVar;
        super.onCreate(bundle);
        getWindow().addFlags(8192);
        try {
            try {
                if (!"949A1CC".equalsIgnoreCase(getIntent().getStringExtra("magic_data"))) {
                    finish();
                }
                this.f18137e = "link".equals(getIntent().getStringExtra("actionType"));
                String stringExtra = getIntent().getStringExtra(a);
                this.f18138f = stringExtra;
                if (TextUtils.isEmpty(stringExtra)) {
                    this.f18138f = "00";
                }
                str = "";
                getWindow().requestFeature(1);
                RelativeLayout relativeLayout = new RelativeLayout(this);
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(1);
                relativeLayout.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -1));
                setContentView(relativeLayout);
                this.b = new WebView(this);
                String stringExtra2 = getIntent().getStringExtra("actionType");
                this.f18139g = stringExtra2;
                if ("link".equals(stringExtra2)) {
                    str = getIntent().getStringExtra("wapurl");
                } else if ("wcd".equals(this.f18139g)) {
                    this.f18140h = a(relativeLayout, (View.OnClickListener) null);
                    String stringExtra3 = getIntent().getStringExtra("wapurl");
                    String stringExtra4 = getIntent().getStringExtra("pay_tn");
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("os", "android");
                        jSONObject.put("tn", stringExtra4);
                        String jSONObject2 = jSONObject.toString();
                        int i2 = 0;
                        try {
                            i2 = Integer.parseInt(this.f18138f);
                        } catch (Exception unused) {
                        }
                        str = stringExtra3 + "?s=" + UPUtils.forWap(i2, com.unionpay.utils.b.b(jSONObject2));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    String stringExtra5 = getIntent().getStringExtra("waptype");
                    if (stringExtra5 == null || !stringExtra5.equals("new_page")) {
                        String stringExtra6 = getIntent().getStringExtra("wapurl");
                        String stringExtra7 = getIntent().getStringExtra("paydata");
                        if (stringExtra7 != null) {
                            str = stringExtra6 + "?s=" + stringExtra7;
                        }
                        com.unionpay.utils.k.a();
                        nVar = new n(this);
                    } else {
                        String stringExtra8 = getIntent().getStringExtra("wapurl");
                        getIntent().getStringExtra("waptitle");
                        str = stringExtra8 != null ? stringExtra8 : "";
                        nVar = new f(this);
                    }
                    this.f18140h = a(relativeLayout, nVar);
                }
                this.b.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                linearLayout.addView(this.b);
                WebViewJavascriptBridge webViewJavascriptBridge = new WebViewJavascriptBridge(this, this.b, null);
                this.f18136c = webViewJavascriptBridge;
                webViewJavascriptBridge.setAllowScheme(this.f18137e);
                WebView webView = this.b;
                if (webView != null) {
                    webView.loadUrl(str);
                }
                WebViewJavascriptBridge webViewJavascriptBridge2 = this.f18136c;
                if (webViewJavascriptBridge2 != null) {
                    webViewJavascriptBridge2.registerHandler("getDeviceInfo", new q(this));
                    this.f18136c.registerHandler("saveData", new r(this));
                    this.f18136c.registerHandler("getData", new s(this));
                    this.f18136c.registerHandler("removeData", new t(this));
                    this.f18136c.registerHandler("setPageBackEnable", new u(this));
                    this.f18136c.registerHandler("payBySDK", new g(this));
                    this.f18136c.registerHandler(CashierDeskConstantBean.JDCASHIER_BROADCAST_FOR_SETTLEMENT, new h(this));
                    this.f18136c.registerHandler("closePage", new i(this));
                    this.f18136c.registerHandler("openNewPage", new j(this));
                    this.f18136c.registerHandler("checkBankSchemes", new k(this));
                    this.f18136c.registerHandler("openBankApp", new l(this));
                }
            } catch (Exception unused2) {
                finish();
            }
        } catch (Exception unused3) {
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (this.f18137e) {
                WebView webView = this.b;
                if (webView != null && webView.canGoBack()) {
                    this.b.goBack();
                    return true;
                }
                a("cancel", (String) null);
            } else {
                onPause();
            }
            return true;
        }
        return super.onKeyDown(i2, keyEvent);
    }
}
