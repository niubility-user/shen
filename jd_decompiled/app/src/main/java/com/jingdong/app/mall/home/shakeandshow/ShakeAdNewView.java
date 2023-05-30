package com.jingdong.app.mall.home.shakeandshow;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.widget.JDShakeLottie;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class ShakeAdNewView extends RelativeLayout {
    private static Handler y = new Handler(Looper.getMainLooper());

    /* renamed from: g */
    private final JDDisplayImageOptions f10807g;

    /* renamed from: h */
    private AtomicBoolean f10808h;

    /* renamed from: i */
    private Vibrator f10809i;

    /* renamed from: j */
    private e f10810j;

    /* renamed from: k */
    private com.jingdong.app.mall.home.shakeandshow.a f10811k;

    /* renamed from: l */
    private JumpEntity f10812l;

    /* renamed from: m */
    private RelativeLayout f10813m;

    /* renamed from: n */
    private com.jingdong.app.mall.home.floor.common.f f10814n;
    private JDShakeLottie o;
    private com.jingdong.app.mall.home.floor.common.f p;
    private SimpleDraweeView q;
    private SimpleDraweeView r;
    private com.jingdong.app.mall.home.floor.common.f s;
    private com.jingdong.app.mall.home.floor.common.f t;
    private SimpleDraweeView u;
    private com.jingdong.app.mall.home.floor.common.f v;
    private SimpleDraweeView w;
    private com.jingdong.app.mall.home.floor.common.f x;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements View.OnClickListener {
        a() {
            ShakeAdNewView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            com.jingdong.app.mall.home.r.c.a.s("Home_ShakerClose", ShakeAdNewView.this.f10812l.getSrv(), ShakeAdNewView.this.f10812l.getSrvJson());
            if (ShakeAdNewView.this.f10811k != null) {
                ShakeAdNewView.this.f10811k.b();
                ShakeAdNewView.this.f10811k.onClose();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {
        b(ShakeAdNewView shakeAdNewView) {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {
        c() {
            ShakeAdNewView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ShakeAdNewView.this.g("1");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
            ShakeAdNewView.this = r1;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            ShakeAdNewView.this.g("0");
        }
    }

    public ShakeAdNewView(Context context, com.jingdong.app.mall.home.shakeandshow.a aVar) {
        super(context);
        JDDisplayImageOptions isScale = com.jingdong.app.mall.home.floor.ctrl.f.a().isScale(false);
        int i2 = R.drawable.home_shake_text;
        this.f10807g = isScale.showImageForEmptyUri(i2).showImageOnLoading(i2).showImageOnFail(i2);
        this.f10808h = new AtomicBoolean(false);
        this.f10811k = aVar;
        this.f10809i = (Vibrator) context.getSystemService("vibrator");
        setBackgroundColor(-1308622848);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.f10813m = relativeLayout;
        relativeLayout.setId(R.id.homefloor_shakefloor_content);
        com.jingdong.app.mall.home.floor.common.f fVar = new com.jingdong.app.mall.home.floor.common.f(400, R2.attr.barLength);
        this.f10814n = fVar;
        RelativeLayout.LayoutParams u = fVar.u(this.f10813m);
        u.addRule(13);
        addView(this.f10813m, u);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.q = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.q.setBackgroundResource(R.drawable.home_shake_bg);
        com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(300, 300);
        this.t = fVar2;
        fVar2.E(0, 50, 0, 0);
        RelativeLayout.LayoutParams u2 = this.t.u(this.q);
        u2.addRule(14);
        this.f10813m.addView(this.q, u2);
        this.o = new JDShakeLottie(context);
        com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(300, 300);
        this.p = fVar3;
        fVar3.E(0, 50, 0, 0);
        RelativeLayout.LayoutParams u3 = this.p.u(this.o);
        u3.addRule(14);
        this.f10813m.addView(this.o, u3);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(context);
        this.r = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        com.jingdong.app.mall.home.floor.common.f fVar4 = new com.jingdong.app.mall.home.floor.common.f(-1, -1);
        this.s = fVar4;
        this.f10813m.addView(this.r, fVar4.u(this.r));
        this.r.setVisibility(8);
        HomeDraweeView homeDraweeView3 = new HomeDraweeView(context);
        this.u = homeDraweeView3;
        homeDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
        com.jingdong.app.mall.home.floor.common.f fVar5 = new com.jingdong.app.mall.home.floor.common.f(320, 72);
        this.v = fVar5;
        fVar5.E(0, 0, 0, 10);
        RelativeLayout.LayoutParams u4 = this.v.u(this.u);
        u4.addRule(14);
        u4.addRule(12);
        this.f10813m.addView(this.u, u4);
        HomeDraweeView homeDraweeView4 = new HomeDraweeView(context);
        this.w = homeDraweeView4;
        homeDraweeView4.setBackgroundResource(R.drawable.home_shake_close);
        this.w.setVisibility(8);
        com.jingdong.app.mall.home.floor.common.f fVar6 = new com.jingdong.app.mall.home.floor.common.f(48, 48);
        this.x = fVar6;
        fVar6.E(0, 32, 0, 0);
        RelativeLayout.LayoutParams u5 = this.x.u(this.w);
        u5.addRule(3, this.f10813m.getId());
        u5.addRule(14);
        addView(this.w, u5);
        this.w.setOnClickListener(new a());
    }

    private void e() {
        com.jingdong.app.mall.home.shakeandshow.a aVar = this.f10811k;
        if (aVar != null) {
            aVar.onClose();
        }
    }

    public void g(String str) {
        if (this.f10812l == null || this.f10810j == null || this.f10808h.get()) {
            return;
        }
        com.jingdong.app.mall.home.shakeandshow.a aVar = this.f10811k;
        if (aVar != null) {
            aVar.a();
        }
        if (!this.f10810j.f10850g) {
            str = "2";
        }
        l.e(getContext(), this.f10812l);
        com.jingdong.app.mall.home.r.c.a.s("Home_ShakerJump", this.f10812l.getSrv() + CartConstant.KEY_YB_INFO_LINK + str, com.jingdong.app.mall.home.r.c.b.c(this.f10812l.getSrvJson()).put("type", str).toString());
        e();
    }

    public boolean d(e eVar) {
        this.f10810j = eVar;
        if (eVar == null) {
            e();
            return false;
        }
        JumpEntity jumpEntity = eVar.f10853j;
        this.f10812l = jumpEntity;
        if (jumpEntity == null) {
            e();
            return false;
        }
        setOnClickListener(new b(this));
        this.f10813m.setOnClickListener(new c());
        return true;
    }

    public void f() {
        this.o.pauseAnimation();
        this.f10808h.set(true);
        y.removeCallbacksAndMessages(null);
        m.K(this);
    }

    public void h() {
        ViewGroup K = com.jingdong.app.mall.home.o.a.f.K();
        if (K != null && JDHomeFragment.O0()) {
            com.jingdong.app.mall.home.floor.common.f.c(this.f10813m, this.f10814n);
            com.jingdong.app.mall.home.floor.common.f.c(this.u, this.v);
            com.jingdong.app.mall.home.floor.common.f.c(this.w, this.x);
            String d2 = h.d();
            boolean z = !TextUtils.isEmpty(d2);
            if (z) {
                com.jingdong.app.mall.home.floor.common.f.c(this.r, this.s);
                com.jingdong.app.mall.home.floor.ctrl.e.u(this.r, d2);
            } else {
                com.jingdong.app.mall.home.floor.common.f.c(this.o, this.t);
                com.jingdong.app.mall.home.floor.common.f.c(this.q, this.t);
                this.o.playAnimation();
            }
            this.r.setVisibility(z ? 0 : 8);
            this.o.setVisibility(z ? 8 : 0);
            this.q.setVisibility(z ? 8 : 0);
            this.f10808h.set(false);
            this.w.setVisibility(this.f10810j.f10849f ? 0 : 8);
            boolean z2 = this.f10810j.f10850g;
            if (!z) {
                z2 &= this.o.b();
            }
            setVisibility(z2 ? 0 : 8);
            com.jingdong.app.mall.home.floor.ctrl.e.f(this.f10810j.a(), this.u, this.f10807g);
            com.jingdong.app.mall.home.r.c.a.y("Home_ShakerExpo", this.f10812l.getSrv(), com.jingdong.app.mall.home.r.c.b.c(this.f10812l.getSrvJson()).put("sourcetype", z ? "0" : "1").toString());
            setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            m.a(K, this);
            y.removeCallbacksAndMessages(null);
            y.postDelayed(new d(), this.f10810j.a);
            Vibrator vibrator = this.f10809i;
            if (vibrator != null) {
                vibrator.vibrate(300L);
                return;
            }
            return;
        }
        f();
    }
}
