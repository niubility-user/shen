package com.jd.lib.cashier.sdk.c.d;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.router.IInnerRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOuterRouter;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewInterceptor;
import com.jd.lib.cashier.sdk.complete.aac.CashierCompleteViewModel;
import com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript;
import com.jd.lib.cashier.sdk.complete.view.CashierCompleteActivity;
import com.jd.lib.cashier.sdk.core.utils.e;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.i0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.x;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class d implements IWebViewInterceptor {
    private View a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private long f2927c;
    private CashierCompleteActivity d;

    /* renamed from: e  reason: collision with root package name */
    private PayFinishJavaScript f2928e;

    public d(CashierCompleteActivity cashierCompleteActivity, View view, PayFinishJavaScript payFinishJavaScript) {
        this.d = cashierCompleteActivity;
        this.a = view;
        this.f2928e = payFinishJavaScript;
        i0.d(view, this);
    }

    private boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f2927c <= 1200) {
            return true;
        }
        this.f2927c = currentTimeMillis;
        return false;
    }

    private boolean b() {
        if (g0.a(this.d)) {
            return ((CashierCompleteViewModel) ViewModelProviders.of(this.d).get(CashierCompleteViewModel.class)).b().f2910e;
        }
        return false;
    }

    public void c() {
        this.b = true;
    }

    public void d() {
        if (this.d != null) {
            this.d = null;
        }
        View view = this.a;
        if (view != null) {
            i0.i(view);
            this.a = null;
        }
        PayFinishJavaScript payFinishJavaScript = this.f2928e;
        if (payFinishJavaScript != null) {
            payFinishJavaScript.onDestroy();
            this.f2928e = null;
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewInterceptor
    public boolean onInterceptor(String str) {
        r.b("WebViewCheckUrlListenerImpl", "setPayCheck.url=" + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Uri parse = Uri.parse(str);
            IOuterRouter router = DependInitializer.getRouter();
            IInnerRouter innerRouter = DependInitializer.getInnerRouter();
            if (router != null && router.isTargetRouter(str)) {
                if (a()) {
                    return true;
                }
                router.router(this.d, str);
            } else if ("jdmobileCashier".equalsIgnoreCase(parse.getScheme()) && "cashierAction".equals(parse.getHost())) {
                if (a()) {
                    return true;
                }
                String queryParameter = parse.getQueryParameter("params");
                if (!TextUtils.isEmpty(queryParameter) && "close_cashier".equals(new JSONObject(queryParameter).optString("type"))) {
                    PayFinishJavaScript payFinishJavaScript = this.f2928e;
                    if (payFinishJavaScript != null && payFinishJavaScript.getCashDeskConfig() != null) {
                        e.a(this.f2928e.getCashDeskConfig());
                    }
                    this.d.finish();
                    return true;
                }
            } else if (x.a(parse.getScheme())) {
                if (!this.b || b()) {
                    return false;
                }
                if (a()) {
                    return true;
                }
                p.h(this.d, str);
            } else if (innerRouter != null && innerRouter.isTargetRouter(str)) {
                if (a()) {
                    return true;
                }
                innerRouter.router(this.d, str);
            } else {
                try {
                    if (a()) {
                        return true;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", parse);
                    intent.addCategory("android.intent.category.BROWSABLE");
                    this.d.startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
