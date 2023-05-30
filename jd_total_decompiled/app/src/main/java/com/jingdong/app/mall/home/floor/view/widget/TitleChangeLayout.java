package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.r.e.j;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.cleanmvp.common.BaseEvent;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class TitleChangeLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private GradientTextView f10156g;

    /* renamed from: h  reason: collision with root package name */
    private TextView f10157h;

    /* renamed from: i  reason: collision with root package name */
    private SimpleDraweeView f10158i;

    /* renamed from: j  reason: collision with root package name */
    private f f10159j;

    /* renamed from: k  reason: collision with root package name */
    private f f10160k;

    /* renamed from: l  reason: collision with root package name */
    private f f10161l;

    /* renamed from: m  reason: collision with root package name */
    private int f10162m;

    /* renamed from: n  reason: collision with root package name */
    private int f10163n;
    private boolean o;
    private Handler p;
    private SpannableStringBuilder q;
    private AbsoluteSizeSpan r;
    private AbsoluteSizeSpan s;
    private com.jingdong.app.mall.home.o.a.b t;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements JDImageLoadingListener {

        /* renamed from: com.jingdong.app.mall.home.floor.view.widget.TitleChangeLayout$a$a  reason: collision with other inner class name */
        /* loaded from: classes4.dex */
        class C0304a extends com.jingdong.app.mall.home.o.a.b {
            C0304a() {
            }

            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                TitleChangeLayout.this.f10156g.setAlpha(0.0f);
                TitleChangeLayout.this.f10158i.bringToFront();
            }
        }

        a() {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingCancelled(String str, View view) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            if (bitmap != null) {
                TitleChangeLayout.this.post(new C0304a());
            }
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
        }

        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingStarted(String str, View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TitleChangeLayout.this.u();
            TitleChangeLayout.this.t();
            com.jingdong.app.mall.home.r.c.a.s("Home_FloorChange", "", "");
        }
    }

    /* loaded from: classes4.dex */
    class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            TitleChangeLayout.i(TitleChangeLayout.this);
            if (TitleChangeLayout.this.f10162m < 0) {
                TitleChangeLayout titleChangeLayout = TitleChangeLayout.this;
                titleChangeLayout.f10162m = titleChangeLayout.f10163n;
                TitleChangeLayout.this.t();
            }
            TitleChangeLayout.this.q.delete(0, TitleChangeLayout.this.q.length());
            TitleChangeLayout.this.q.append((CharSequence) String.valueOf(TitleChangeLayout.this.f10162m)).append((CharSequence) "S");
            TitleChangeLayout.this.q.setSpan(TitleChangeLayout.this.s, 0, TitleChangeLayout.this.q.length() - 1, 17);
            TitleChangeLayout.this.q.setSpan(TitleChangeLayout.this.r, TitleChangeLayout.this.q.length() - 1, TitleChangeLayout.this.q.length(), 17);
            TitleChangeLayout.this.f10157h.setText(TitleChangeLayout.this.q);
            TitleChangeLayout.this.p.postDelayed(TitleChangeLayout.this.t, 1000L);
        }
    }

    public TitleChangeLayout(Context context) {
        super(context);
        this.f10162m = 3;
        this.f10163n = 3;
        this.p = new Handler(Looper.getMainLooper());
        this.r = new AbsoluteSizeSpan(d.d(18), false);
        this.s = new AbsoluteSizeSpan(d.d(20), false);
        this.t = new c();
        s();
    }

    static /* synthetic */ int i(TitleChangeLayout titleChangeLayout) {
        int i2 = titleChangeLayout.f10162m;
        titleChangeLayout.f10162m = i2 - 1;
        return i2;
    }

    private void q() {
        TextView textView = this.f10157h;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    private void s() {
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.f10158i = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.f10161l = new f(88, 40);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f10161l.v(), this.f10161l.h());
        layoutParams.addRule(11);
        addView(this.f10158i, layoutParams);
        GradientTextView gradientTextView = new GradientTextView(getContext());
        this.f10156g = gradientTextView;
        gradientTextView.setText("\u6362\u4e00\u6362");
        this.f10156g.setGravity(17);
        this.f10156g.setTextSize(0, d.d(20));
        this.f10156g.setId(R.id.mallfloor_title_change);
        this.f10156g.setOnClickListener(new b());
        this.f10159j = new f(88, 40);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.f10159j.v(), this.f10159j.h());
        layoutParams2.addRule(11);
        addView(this.f10156g, layoutParams2);
        TextView textView = new TextView(getContext());
        this.f10157h = textView;
        textView.setGravity(16);
        this.f10157h.setMaxLines(1);
        this.f10157h.setTextSize(0, d.d(18));
        this.f10160k = new f(-2, 39);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(this.f10160k.v(), this.f10160k.h());
        layoutParams3.addRule(0, this.f10156g.getId());
        layoutParams3.rightMargin = d.d(12);
        addView(this.f10157h, layoutParams3);
        this.q = new SpannableStringBuilder(this.f10163n + "S");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        EventBus.getDefault().post(new MallFloorEvent("data_change"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        if (!this.o || this.f10157h == null) {
            return;
        }
        SpannableStringBuilder spannableStringBuilder = this.q;
        spannableStringBuilder.delete(0, spannableStringBuilder.length());
        this.q.append((CharSequence) String.valueOf(this.f10163n)).append((CharSequence) "S");
        this.q.setSpan(this.s, 0, r0.length() - 1, 17);
        this.q.setSpan(this.r, r0.length() - 1, this.q.length(), 17);
        this.f10157h.setText(this.q);
        this.p.removeCallbacks(this.t);
        this.f10162m = this.f10163n + 1;
        this.p.postDelayed(this.t, 500L);
    }

    private void x() {
        if (!this.o) {
            this.p.removeCallbacks(this.t);
            q();
            return;
        }
        this.f10156g.setAlpha(1.0f);
        this.f10156g.bringToFront();
        this.f10157h.setVisibility(0);
        SpannableStringBuilder spannableStringBuilder = this.q;
        spannableStringBuilder.delete(0, spannableStringBuilder.length());
        this.q.append((CharSequence) String.valueOf(this.f10163n)).append((CharSequence) "S");
        this.q.setSpan(this.s, 0, r0.length() - 1, 17);
        this.q.setSpan(this.r, r0.length() - 1, this.q.length(), 17);
        this.f10157h.setText(this.q);
    }

    public void o() {
        f.c(this.f10158i, this.f10161l);
        f.c(this.f10156g, this.f10159j);
        f.c(this.f10157h, this.f10160k);
        this.f10156g.setTextSize(0, d.d(20));
        this.r = new AbsoluteSizeSpan(d.d(18), false);
        this.s = new AbsoluteSizeSpan(d.d(20), false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.jingdong.app.mall.home.o.a.f.G0(this);
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        com.jingdong.app.mall.home.o.a.f.H0(this);
        v();
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals("home_resume")) {
            u();
        } else if (type.equals("home_pause")) {
            v();
        }
    }

    public void p(String str) {
        SimpleDraweeView simpleDraweeView;
        if (TextUtils.isEmpty(str) || (simpleDraweeView = this.f10158i) == null) {
            return;
        }
        e.s(simpleDraweeView, str, new a());
    }

    public void r(j jVar) {
        int j2 = m.j(jVar.mParentModel.s, R.color.c_262626);
        w(jVar.mParentModel.getJsonInt("aInterval", 30) / 10);
        p(jVar.mParentModel.t);
        this.f10157h.setTextColor(j2);
        this.f10156g.setTextColor(j2);
        x();
    }

    public void v() {
        this.p.removeCallbacks(this.t);
    }

    public void w(int i2) {
        if (i2 == 0) {
            i2 = 3;
        }
        boolean z = i2 > 0;
        this.o = z;
        this.f10163n = z ? i2 > 8 ? 8 : i2 : 3;
        this.f10162m = z ? i2 > 8 ? 8 : i2 : 3;
    }
}
