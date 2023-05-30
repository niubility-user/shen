package com.jingdong.app.mall.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class c {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: i  reason: collision with root package name */
    private static volatile c f8617i;
    private b a;
    private final AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private final AtomicBoolean f8618c = new AtomicBoolean(false);
    private String d = com.jingdong.app.mall.home.o.a.f.O("home_current_privacy_version", "");

    /* renamed from: e  reason: collision with root package name */
    private JDDialog f8619e;

    /* renamed from: f  reason: collision with root package name */
    private TextView f8620f;

    /* renamed from: g  reason: collision with root package name */
    private TextView f8621g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f8622h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ b f8623g;

        a(b bVar) {
            this.f8623g = bVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            c.this.k(this.f8623g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class b {
        public String a;
        public String b;

        /* renamed from: c  reason: collision with root package name */
        public boolean f8625c;
        public String d;

        /* renamed from: e  reason: collision with root package name */
        public String f8626e;

        b() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.c$c  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public static class C0274c extends ClickableSpan {

        /* renamed from: g  reason: collision with root package name */
        private final Context f8627g;

        /* renamed from: h  reason: collision with root package name */
        private final String f8628h;

        public C0274c(Context context, String str) {
            this.f8627g = context;
            this.f8628h = str;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(@NonNull View view) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.f8628h));
                intent.setFlags(268435456);
                this.f8627g.startActivity(intent);
            } catch (Throwable th) {
                th.printStackTrace();
                ToastUtils.shortToast(this.f8627g, "\u672a\u68c0\u6d4b\u5230\u60a8\u5b89\u88c5\u624b\u673a\u6d4f\u89c8\u5668!");
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(@NonNull TextPaint textPaint) {
            textPaint.setUnderlineText(false);
            textPaint.setFakeBoldText(true);
        }
    }

    private c() {
        if (OKLog.D) {
            com.jingdong.app.mall.home.o.a.f.r0("HomePrivacyDialogCtrl", "\u9996\u9875\u672c\u6b21\u542f\u52a8\u9690\u79c1\u7248\u672c\u53f7\uff1a" + this.d);
        }
    }

    private void b(b bVar) {
        this.f8622h.setOnClickListener(new a(bVar));
    }

    private void c() {
        JDDialog jDDialog = this.f8619e;
        if (jDDialog == null) {
            return;
        }
        LinearLayout linearLayout = (LinearLayout) jDDialog.findViewById(R.id.root_layout);
        com.jingdong.app.mall.home.floor.common.f.c(linearLayout, new com.jingdong.app.mall.home.floor.common.f(R2.attr.chipSpacingVertical, -2));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(com.jingdong.app.mall.home.floor.common.d.d(24));
        gradientDrawable.setColor(-1);
        linearLayout.setBackground(gradientDrawable);
        TextView textView = (TextView) this.f8619e.findViewById(R.id.dialog_title);
        this.f8620f = textView;
        textView.setSingleLine(true);
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(R2.attr.btnIconAlpha, -2);
        fVar.F(new Rect(0, 48, 0, 0));
        com.jingdong.app.mall.home.floor.common.f.c(this.f8620f, fVar);
        com.jingdong.app.mall.home.floor.common.f.O(this.f8620f, 32);
        this.f8621g = (TextView) this.f8619e.findViewById(R.id.dialog_content);
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(R2.attr.blurDownScale, -2);
        fVar2.F(new Rect(0, 32, 0, 0));
        com.jingdong.app.mall.home.floor.common.f.c(this.f8621g, fVar2);
        this.f8621g.setMaxHeight(com.jingdong.app.mall.home.floor.common.d.d(R2.attr.borderWidth));
        this.f8621g.setLineSpacing(10.0f, 1.0f);
        this.f8621g.setMovementMethod(LinkMovementMethod.getInstance());
        com.jingdong.app.mall.home.floor.common.f.O(this.f8621g, 28);
        this.f8622h = (TextView) this.f8619e.findViewById(R.id.confirm_button);
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(414, 76);
        fVar3.F(new Rect(0, 40, 0, 48));
        com.jingdong.app.mall.home.floor.common.f.c(this.f8622h, fVar3);
        com.jingdong.app.mall.home.floor.common.f.O(this.f8622h, 28);
    }

    private void d(Dialog dialog) {
        try {
            dialog.dismiss();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static c f() {
        if (f8617i == null) {
            synchronized (c.class) {
                if (f8617i == null) {
                    f8617i = new c();
                }
            }
        }
        return f8617i;
    }

    private Spanned i(Spanned spanned) {
        if (spanned != null && spanned.length() != 0) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spanned);
            URLSpan[] uRLSpanArr = (URLSpan[]) spanned.getSpans(0, spanned.length(), URLSpan.class);
            if (uRLSpanArr != null && uRLSpanArr.length != 0) {
                for (URLSpan uRLSpan : uRLSpanArr) {
                    spannableStringBuilder.setSpan(new C0274c(this.f8621g.getContext(), uRLSpan.getURL()), spanned.getSpanStart(uRLSpan), spanned.getSpanEnd(uRLSpan), 17);
                }
                return spannableStringBuilder;
            }
        }
        return spanned;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(b bVar) {
        if (bVar == null) {
            return;
        }
        q(bVar.d);
        d(this.f8619e);
        com.jingdong.app.mall.home.r.c.a.s("Home_PolicyUpdateAgree", "", bVar.f8626e);
        m();
    }

    private void m() {
        n();
        com.jingdong.app.mall.home.o.a.f.K0(this.f8618c);
    }

    private void o(b bVar) {
        this.f8620f.setText(bVar.a);
        try {
            this.f8621g.setLinkTextColor(Color.argb(255, 240, 43, 43));
            this.f8621g.setText(i(Html.fromHtml(bVar.b)));
        } catch (Throwable unused) {
        }
    }

    private void p(Dialog dialog) {
        try {
            dialog.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void q(String str) {
        if (TextUtils.equals(str, this.d)) {
            return;
        }
        this.d = str;
        com.jingdong.app.mall.home.o.a.f.A0("home_current_privacy_version", str);
        if (OKLog.D) {
            com.jingdong.app.mall.home.o.a.f.r0("HomePrivacyDialogCtrl", "\u9996\u9875\u66f4\u65b0\u9690\u79c1\u7248\u672c\u53f7\uff1a" + this.d);
        }
    }

    public String e() {
        return this.d;
    }

    public void g(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null || !com.jingdong.app.mall.home.o.a.f.k0() || this.b.get() || com.jingdong.app.mall.home.o.a.h.c()) {
            return;
        }
        this.b.set(true);
        String optString = jDJSONObject.optString("bid", "");
        if (TextUtils.isEmpty(optString) || TextUtils.equals(optString, this.d)) {
            return;
        }
        b bVar = new b();
        bVar.d = optString;
        bVar.a = jDJSONObject.optString("title", "");
        bVar.b = jDJSONObject.optString("content", "");
        bVar.f8625c = jDJSONObject.optInt("xviewConflict", 0) == 1;
        if (!TextUtils.isEmpty(this.d) && !TextUtils.isEmpty(bVar.a) && !TextUtils.isEmpty(bVar.b)) {
            bVar.f8626e = new com.jingdong.app.mall.home.r.c.b().a("policyid", optString).toString();
            this.a = bVar;
            if (bVar.f8625c) {
                com.jingdong.app.mall.home.floor.ctrl.t.i.p().D(true);
                com.jingdong.app.mall.ad.c.l().y(true);
            }
            com.jingdong.app.mall.home.u.a.w().F(true);
            return;
        }
        q(optString);
    }

    public boolean h() {
        JDDialog jDDialog = this.f8619e;
        return jDDialog != null && jDDialog.isShowing();
    }

    public boolean j() {
        return !this.b.get();
    }

    public boolean l(Activity activity) {
        if (activity == null || this.a == null) {
            return false;
        }
        JDDialog jDDialog = this.f8619e;
        if (jDDialog == null || !jDDialog.isShowing()) {
            com.jingdong.app.mall.home.o.a.f.m(this.f8618c);
            JDDialog jDDialog2 = new JDDialog(activity);
            this.f8619e = jDDialog2;
            jDDialog2.setContentView(R.layout.home_privacy_confirm_dialog);
            this.f8619e.setCanceledOnTouchOutside(false);
            this.f8619e.setCancelable(false);
            c();
            o(this.a);
            b(this.a);
            p(this.f8619e);
            com.jingdong.app.mall.home.r.c.a.y("Home_PolicyUpdateExpo", "", this.a.f8626e);
            return true;
        }
        return true;
    }

    public void n() {
        this.a = null;
        this.f8619e = null;
        this.f8620f = null;
        this.f8622h = null;
        this.f8621g = null;
    }
}
