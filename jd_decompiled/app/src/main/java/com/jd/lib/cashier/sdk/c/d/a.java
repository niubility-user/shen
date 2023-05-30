package com.jd.lib.cashier.sdk.c.d;

import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleChangeEventCreator;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeEvent;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewScrollListener;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewTitleChangeListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript;
import com.jd.lib.cashier.sdk.core.ui.entity.CashierConfig;
import com.jd.lib.cashier.sdk.core.utils.e;
import com.jd.lib.cashier.sdk.core.utils.e0;
import com.jd.lib.cashier.sdk.core.utils.i0;
import com.jd.lib.cashier.sdk.core.utils.l;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import java.util.Map;

/* loaded from: classes14.dex */
public class a implements IWebViewTitleChangeListener, IWebViewScrollListener, ITitleThemeChangeListener, c {

    /* renamed from: g  reason: collision with root package name */
    private String f2912g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f2913h;

    /* renamed from: i  reason: collision with root package name */
    private View f2914i;

    /* renamed from: j  reason: collision with root package name */
    private View f2915j;

    /* renamed from: k  reason: collision with root package name */
    private RelativeLayout f2916k;

    /* renamed from: l  reason: collision with root package name */
    private FragmentActivity f2917l;

    /* renamed from: m  reason: collision with root package name */
    private PayFinishJavaScript f2918m;

    /* renamed from: n  reason: collision with root package name */
    private ITitleThemeChangeEvent f2919n;
    private int o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.cashier.sdk.c.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class ViewOnClickListenerC0104a implements View.OnClickListener {
        ViewOnClickListenerC0104a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Map map;
            CashierConfig cashDeskConfig;
            if (a.this.f2918m != null && (cashDeskConfig = a.this.f2918m.getCashDeskConfig()) != null) {
                if (TextUtils.equals(cashDeskConfig.notifyFlag, "1")) {
                    com.jd.lib.cashier.sdk.d.g.b.a.a().e(a.this.f2917l);
                }
                if (!"1".equals(cashDeskConfig.closeType)) {
                    if ("2".equals(cashDeskConfig.closeType)) {
                        e.c();
                    } else if ("3".equals(cashDeskConfig.closeType)) {
                        e.c();
                        p.a(a.this.f2917l, cashDeskConfig.finishUrl);
                    } else {
                        e.a(cashDeskConfig);
                        if (!TextUtils.isEmpty(cashDeskConfig.finishUrl)) {
                            p.a(a.this.f2917l, cashDeskConfig.finishUrl);
                        }
                    }
                }
            }
            CashierConfig cashDeskConfig2 = a.this.f2918m != null ? a.this.f2918m.getCashDeskConfig() : null;
            com.jd.lib.cashier.sdk.b.b.a aVar = new com.jd.lib.cashier.sdk.b.b.a();
            if (cashDeskConfig2 != null) {
                try {
                    if (!TextUtils.isEmpty(cashDeskConfig2.abTestParamMap) && (map = (Map) o.a(cashDeskConfig2.abTestParamMap, Map.class)) != null && !map.isEmpty()) {
                        for (String str : map.keySet()) {
                            aVar.put((com.jd.lib.cashier.sdk.b.b.a) str, (String) map.get(str));
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            com.jd.lib.cashier.sdk.c.e.a.c().a(a.this.f2917l, a.this.f2912g, cashDeskConfig2 != null ? cashDeskConfig2.source : "", cashDeskConfig2 != null ? cashDeskConfig2.leaveto : "", cashDeskConfig2 != null ? cashDeskConfig2.sourceType : "", cashDeskConfig2 != null ? cashDeskConfig2.orderId : "", aVar);
            if (a.this.f2917l != null) {
                a.this.f2917l.finish();
            }
        }
    }

    public a(FragmentActivity fragmentActivity, String str, PayFinishJavaScript payFinishJavaScript, View view) {
        this.f2912g = str;
        this.f2917l = fragmentActivity;
        this.f2914i = view;
        this.f2918m = payFinishJavaScript;
        this.f2916k = (RelativeLayout) fragmentActivity.getWindow().findViewById(R.id.lib_cashier_complete_root_webview_layout);
        i0.b(this.f2914i, this);
        i0.c(this.f2914i, this);
    }

    private void g(int i2, int i3) {
        int i4 = i3 >> 1;
        if (i2 <= i4) {
            if (1 != this.o) {
                r();
                c();
            }
            this.o = 1;
        } else if (i2 > i4 && i2 <= i3) {
            if (2 != this.o) {
                e0.o(this.f2915j, this.f2917l);
            }
            e0.q(this.f2915j, (i2 / i3) * 1.0f);
            this.o = 2;
        } else if (i2 > i3) {
            if (3 != this.o) {
                s();
                l();
            }
            this.o = 3;
        }
    }

    private void i() {
        View view;
        if (!y.p() || (view = this.f2915j) == null) {
            return;
        }
        e0.g(view);
    }

    private void j() {
        RelativeLayout relativeLayout = this.f2916k;
        if (relativeLayout == null || this.f2914i == null || this.f2915j == null) {
            return;
        }
        relativeLayout.removeAllViews();
        this.f2916k.addView(this.f2914i);
        this.f2916k.addView(this.f2915j);
    }

    private void k() {
        FragmentActivity fragmentActivity;
        PayFinishJavaScript payFinishJavaScript = this.f2918m;
        if (payFinishJavaScript != null) {
            CashierConfig cashDeskConfig = payFinishJavaScript.getCashDeskConfig();
            String str = (cashDeskConfig == null || TextUtils.isEmpty(cashDeskConfig.finishBtn)) ? "" : cashDeskConfig.finishBtn;
            if (TextUtils.isEmpty(str) && (fragmentActivity = this.f2917l) != null) {
                str = fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_complete_finish_btn);
            }
            View view = this.f2915j;
            if (view != null) {
                e0.m(view, str);
            }
        }
    }

    private void l() {
        View view = this.f2915j;
        if (view != null) {
            TextView a = e0.a(view);
            if (a != null) {
                if (e0.d(this.f2915j)) {
                    a.setTextColor(-1);
                } else {
                    a.setTextColor(Color.parseColor("#848689"));
                }
            }
            e0.n(this.f2915j, 0);
        }
    }

    private void m(String str) {
        if (this.f2915j == null || TextUtils.isEmpty(str)) {
            return;
        }
        e0.r(this.f2915j, str);
    }

    private void n() {
        if (this.f2915j != null) {
            r.b("WebViewThemeTitleChangeImpl", "on Setting Title Color");
            TextView b = e0.b(this.f2915j);
            if (b != null) {
                if (e0.d(this.f2915j)) {
                    b.setTextColor(-1);
                } else {
                    b.setTextColor(Color.parseColor("#232326"));
                }
            }
        }
    }

    private void o() {
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.f2919n;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.registerTitleThemeChangeEvent(this);
        }
    }

    private void p() {
        RelativeLayout relativeLayout = this.f2916k;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
        }
    }

    private void r() {
        View view = this.f2915j;
        if (view != null) {
            e0.o(view, this.f2917l);
            e0.q(this.f2915j, 0.0f);
            TextView b = e0.b(this.f2915j);
            if (b != null) {
                b.setVisibility(4);
            }
        }
    }

    private void s() {
        View view = this.f2915j;
        if (view != null) {
            e0.o(view, this.f2917l);
            e0.q(this.f2915j, 1.0f);
            TextView b = e0.b(this.f2915j);
            if (b != null) {
                b.setVisibility(0);
            }
        }
    }

    private void t() {
        ITitleThemeChangeEvent iTitleThemeChangeEvent = this.f2919n;
        if (iTitleThemeChangeEvent != null) {
            iTitleThemeChangeEvent.unRegisterTitleThemeChangeEvent(this);
        }
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void a() {
        if (this.f2913h) {
            return;
        }
        this.f2913h = true;
        i();
        r();
        k();
        c();
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void b() {
        View view = this.f2915j;
        if (view != null) {
            e0.e(view);
        }
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void c() {
        PayFinishJavaScript payFinishJavaScript = this.f2918m;
        if (payFinishJavaScript == null || this.f2915j == null) {
            return;
        }
        CashierConfig cashDeskConfig = payFinishJavaScript.getCashDeskConfig();
        TextView a = e0.a(this.f2915j);
        if (a != null) {
            if (cashDeskConfig != null && cashDeskConfig.getColorType() != -1) {
                a.setTextColor(cashDeskConfig.getColorType());
            } else {
                a.setTextColor(-16777216);
            }
        }
        e0.n(this.f2915j, 0);
    }

    public void h() {
        ITitleChangeEventCreator titleChangeEventCreator = DependInitializer.getTitleChangeEventCreator();
        if (titleChangeEventCreator != null) {
            this.f2919n = titleChangeEventCreator.instanceTitleThemeChangeEvent();
        }
        o();
        View c2 = e0.c(this.f2917l);
        this.f2915j = c2;
        if (c2 != null) {
            c2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            if (Build.VERSION.SDK_INT >= 17) {
                this.f2915j.setId(View.generateViewId());
            } else {
                this.f2915j.setId(R.id.lib_cashier_sdk_cashier_title);
            }
            e0.h(this.f2915j, true);
            e0.i(this.f2915j);
            e0.p(this.f2915j, true);
            e0.e(this.f2915j);
            e0.n(this.f2915j, 4);
            e0.j(this.f2915j, 8);
            TextView b = e0.b(this.f2915j);
            if (b != null) {
                b.setTextSize(l.a(this.f2917l, b.getTextSize()));
            }
            TextView a = e0.a(this.f2915j);
            if (a != null) {
                a.setTextSize(l.a(this.f2917l, a.getTextSize()));
            }
            r();
        }
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void onDestroy() {
        this.f2917l = null;
        this.f2915j = null;
        this.f2918m = null;
        p();
        t();
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void onLayout() {
        h();
        q();
        j();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewScrollListener
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        View view;
        if (!this.f2913h || (view = this.f2915j) == null) {
            return;
        }
        g(i3, view.getMeasuredHeight());
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleThemeChangeListener
    public void onThemeChange(boolean z, String str) {
        View view = this.f2915j;
        if (view != null) {
            e0.f(view);
            n();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewTitleChangeListener
    public void onWebViewTitleChange(String str) {
        m(str);
        n();
    }

    public void q() {
        View view = this.f2915j;
        if (view != null) {
            e0.l(view, new ViewOnClickListenerC0104a());
        }
    }
}
