package com.jd.lib.cashier.sdk.c.d;

import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewTitleChangeListener;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.e0;
import com.jd.lib.cashier.sdk.core.utils.i0;
import com.jd.lib.cashier.sdk.core.utils.l;

/* loaded from: classes14.dex */
public class b implements IWebViewTitleChangeListener, c {

    /* renamed from: g  reason: collision with root package name */
    private String f2921g;

    /* renamed from: h  reason: collision with root package name */
    private View f2922h;

    /* renamed from: i  reason: collision with root package name */
    private View f2923i;

    /* renamed from: j  reason: collision with root package name */
    private RelativeLayout f2924j;

    /* renamed from: k  reason: collision with root package name */
    private FragmentActivity f2925k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jd.lib.cashier.sdk.c.e.a.c().a(b.this.f2925k, b.this.f2921g, "", "5", "", "", null);
            if (b.this.f2925k != null) {
                b.this.f2925k.finish();
            }
        }
    }

    public b(FragmentActivity fragmentActivity, String str, View view) {
        this.f2921g = str;
        this.f2925k = fragmentActivity;
        this.f2922h = view;
        this.f2924j = (RelativeLayout) fragmentActivity.getWindow().findViewById(R.id.lib_cashier_complete_root_webview_layout);
        i0.c(this.f2922h, this);
    }

    private void g() {
        RelativeLayout relativeLayout = this.f2924j;
        if (relativeLayout == null || this.f2922h == null || this.f2923i == null) {
            return;
        }
        relativeLayout.removeAllViews();
        ((RelativeLayout.LayoutParams) this.f2922h.getLayoutParams()).addRule(3, this.f2923i.getId());
        this.f2924j.addView(this.f2923i);
        this.f2924j.addView(this.f2922h);
    }

    private void h() {
        FragmentActivity fragmentActivity;
        View view = this.f2923i;
        if (view == null || (fragmentActivity = this.f2925k) == null) {
            return;
        }
        e0.m(view, fragmentActivity.getResources().getString(R.string.lib_cashier_sdk_complete_finish_btn));
    }

    private void i(String str) {
        if (this.f2923i == null || TextUtils.isEmpty(str)) {
            return;
        }
        e0.r(this.f2923i, str);
    }

    private void j() {
        RelativeLayout relativeLayout = this.f2924j;
        if (relativeLayout != null) {
            relativeLayout.removeAllViews();
        }
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void a() {
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void b() {
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void c() {
    }

    public void f() {
        View c2 = e0.c(this.f2925k);
        this.f2923i = c2;
        if (c2 != null) {
            c2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
            if (Build.VERSION.SDK_INT >= 17) {
                this.f2923i.setId(View.generateViewId());
            } else {
                this.f2923i.setId(R.id.lib_cashier_sdk_cashier_title);
            }
            e0.h(this.f2923i, false);
            e0.i(this.f2923i);
            e0.p(this.f2923i, false);
            e0.n(this.f2923i, 0);
            e0.j(this.f2923i, 8);
            TextView b = e0.b(this.f2923i);
            if (b != null) {
                b.setTextColor(Color.parseColor("#232326"));
                b.setTextSize(l.a(this.f2925k, b.getTextSize()));
            }
            TextView a2 = e0.a(this.f2923i);
            if (a2 != null) {
                a2.setTextColor(-16777216);
                a2.setTextSize(l.a(this.f2925k, a2.getTextSize()));
            }
        }
    }

    public void k() {
        View view = this.f2923i;
        if (view != null) {
            e0.l(view, new a());
        }
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void onDestroy() {
        this.f2925k = null;
        this.f2923i = null;
        j();
    }

    @Override // com.jd.lib.cashier.sdk.c.d.c
    public void onLayout() {
        f();
        k();
        g();
        h();
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewTitleChangeListener
    public void onWebViewTitleChange(String str) {
        i(str);
    }
}
