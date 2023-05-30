package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.common.entity.JumpEntity;

/* loaded from: classes4.dex */
public class RuleFloatLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private SimpleDraweeView f10132g;

    /* renamed from: h  reason: collision with root package name */
    private View f10133h;

    /* renamed from: i  reason: collision with root package name */
    private f f10134i;

    /* renamed from: j  reason: collision with root package name */
    private f f10135j;

    /* renamed from: k  reason: collision with root package name */
    private f f10136k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f10137l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f10138m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f10139n;
    private int o;
    private String p;
    private int q;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements e.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onFailed(String str, View view) {
            RuleFloatLayout.this.f10137l = false;
            RuleFloatLayout.this.d();
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onStart(String str, View view) {
        }

        @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
        public void onSuccess(String str, View view) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ h f10140g;

        b(h hVar) {
            this.f10140g = hVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (l.h()) {
                return;
            }
            RuleFloatLayout.this.c(this.f10140g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ h f10142g;

        c(h hVar) {
            this.f10142g = hVar;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JumpEntity jumpEntity = this.f10142g.D;
            if (jumpEntity == null) {
                return;
            }
            l.e(RuleFloatLayout.this.getContext(), jumpEntity);
            com.jingdong.app.mall.home.r.c.a.s("Home_GZFloating", "", jumpEntity.srvJson);
        }
    }

    public RuleFloatLayout(@NonNull Context context) {
        super(context);
        this.f10134i = new f(26, 100);
        this.f10135j = new f(-1, -1);
        this.f10136k = new f(40, 40);
        RelativeLayout.LayoutParams u = this.f10134i.u(this);
        u.addRule(11);
        u.addRule(8, R.id.pull_refresh_scroll);
        setLayoutParams(u);
        HomeDraweeView homeDraweeView = new HomeDraweeView(context);
        this.f10132g = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        View view = this.f10132g;
        addView(view, this.f10135j.u(view));
        View view2 = new View(context);
        this.f10133h = view2;
        RelativeLayout.LayoutParams u2 = this.f10136k.u(view2);
        u2.addRule(11);
        addView(this.f10133h, u2);
        com.jingdong.app.mall.home.n.h.c.k(true, this.f10133h);
    }

    private boolean b(h hVar) {
        if (hVar == null) {
            return false;
        }
        int jsonInt = hVar.getJsonInt("ruleType", 0);
        this.o = jsonInt;
        if (jsonInt == 0) {
            this.f10139n = false;
            this.p = hVar.getJsonString("img3");
        } else {
            boolean z = 1 == hVar.getJsonInt("isDisplayCloseBtn", 0);
            this.f10139n = z;
            this.p = z ? hVar.y : hVar.getJsonString("img2");
        }
        int i2 = this.o == 0 ? 50 : 66;
        int jsonInt2 = hVar.getJsonInt("ruleModulePosition", i2);
        this.q = jsonInt2;
        if (jsonInt2 <= 0) {
            this.q = i2;
        }
        return !TextUtils.isEmpty(this.p);
    }

    public void c(h hVar) {
        JumpEntity jumpEntity;
        this.f10138m = true;
        m.K(this);
        com.jingdong.app.mall.home.r.c.a.s("Home_GZFloatingClose", "", (hVar == null || (jumpEntity = hVar.D) == null) ? "" : jumpEntity.srvJson);
    }

    public void d() {
        com.jingdong.app.mall.home.n.h.c.k(true, this);
    }

    public void e(RelativeLayout relativeLayout, h hVar, int i2) {
        if (!this.f10138m && b(hVar)) {
            this.f10137l = true;
            j();
            e.p(this.f10132g, this.p, e.b, new a());
            if (this.f10139n) {
                com.jingdong.app.mall.home.n.h.c.k(false, this.f10133h);
                this.f10133h.setOnClickListener(new b(hVar));
            } else {
                com.jingdong.app.mall.home.n.h.c.k(true, this.f10133h);
            }
            setOnClickListener(new c(hVar));
            if (this.o == 0) {
                this.f10134i.R(26, 100);
                this.f10134i.F(new Rect(0, 0, 0, this.q << 1));
            } else {
                if (this.f10139n) {
                    this.f10134i.R(140, 50);
                } else {
                    this.f10134i.R(120, 40);
                }
                this.f10134i.F(new Rect(0, 0, 12, this.q << 1));
            }
            setLayoutParams(this.f10134i.u(this));
            m.b(relativeLayout, this, i2);
            com.jingdong.app.mall.home.r.c.a.y("Home_GZFloatingExpo", "", hVar.f());
            return;
        }
        this.f10137l = false;
        d();
    }

    public void f() {
        if (!this.f10137l || this.f10138m) {
            return;
        }
        j();
    }

    public void g() {
        if (!this.f10137l || this.f10138m) {
            return;
        }
        d();
    }

    public void h() {
        f.c(this, this.f10134i);
        f.c(this.f10132g, this.f10135j);
        f.c(this.f10133h, this.f10136k);
    }

    public void i() {
        this.f10137l = false;
        m.K(this);
    }

    public void j() {
        com.jingdong.app.mall.home.n.h.c.k(false, this);
    }
}
