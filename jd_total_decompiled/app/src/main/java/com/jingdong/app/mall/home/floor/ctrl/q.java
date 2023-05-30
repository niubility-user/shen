package com.jingdong.app.mall.home.floor.ctrl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.floor.common.i.u;
import com.jingdong.app.mall.home.floor.model.entity.BannerFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorBannerItem;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.app.mall.home.widget.HomeSurfaceView;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class q {
    private View a;
    private HomeSurfaceView b;

    /* renamed from: c  reason: collision with root package name */
    private String f9485c;
    private String[] d;

    /* renamed from: e  reason: collision with root package name */
    private f f9486e;

    /* renamed from: f  reason: collision with root package name */
    private int f9487f;

    /* renamed from: g  reason: collision with root package name */
    private int f9488g;

    /* renamed from: h  reason: collision with root package name */
    private int f9489h;

    /* renamed from: i  reason: collision with root package name */
    private int f9490i;

    /* renamed from: j  reason: collision with root package name */
    private int f9491j;

    /* renamed from: k  reason: collision with root package name */
    private int f9492k;

    /* renamed from: l  reason: collision with root package name */
    private int f9493l;

    /* renamed from: m  reason: collision with root package name */
    private View f9494m;

    /* renamed from: n  reason: collision with root package name */
    private String f9495n;

    /* loaded from: classes4.dex */
    class a implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f9496g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9497h;

        a(int i2, int i3) {
            this.f9496g = i2;
            this.f9497h = i3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float f2 = floatValue / q.this.f9487f;
            int i2 = (int) (q.this.f9489h * f2);
            int i3 = (int) (q.this.f9490i * f2);
            q.this.b.setTranslationY(-floatValue);
            q.this.b.h(com.jingdong.app.mall.home.floor.common.d.d(i2), i3, this.f9496g - com.jingdong.app.mall.home.floor.common.d.d(i2), this.f9497h - i3, com.jingdong.app.mall.home.floor.common.d.d((int) (q.this.f9492k * f2)));
            q.this.a.setAlpha(1.0f - f2);
        }
    }

    /* loaded from: classes4.dex */
    class b extends AnimatorListenerAdapter {
        b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
            q.this.m();
        }
    }

    /* loaded from: classes4.dex */
    class c implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f9500g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f9501h;

        c(int i2, int i3) {
            this.f9500g = i2;
            this.f9501h = i3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float f2 = floatValue / q.this.f9488g;
            int i2 = (int) (q.this.f9491j * f2);
            int i3 = (int) floatValue;
            q.this.b.setTranslationY((-q.this.f9487f) + floatValue);
            q.this.b.h(com.jingdong.app.mall.home.floor.common.d.d(q.this.f9489h - i2), q.this.f9490i - i3, this.f9500g - com.jingdong.app.mall.home.floor.common.d.d(q.this.f9489h - i2), (this.f9501h - q.this.f9490i) + i3, com.jingdong.app.mall.home.floor.common.d.d(q.this.f9493l + ((int) ((q.this.f9492k - q.this.f9493l) * f2))));
        }
    }

    /* loaded from: classes4.dex */
    class d extends AnimatorListenerAdapter {
        d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            q.this.m();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            q.this.m();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends com.jingdong.app.mall.home.o.a.b {
        e() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (q.this.f9486e != null) {
                q.this.f9486e.finish();
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface f {
        void finish();

        void start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class g {
        int a;
        int b;

        g(q qVar, int i2, int i3, int i4) {
            this.a = i3;
            this.b = i4;
        }
    }

    public q(HomeSurfaceView homeSurfaceView, View view, String str, String str2, f fVar) {
        this.b = homeSurfaceView;
        this.f9485c = str;
        this.d = str2.split(DYConstants.DY_REGEX_COMMA);
        this.f9486e = fVar;
        this.a = view;
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x011a, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private g l() {
        List<com.jingdong.app.mall.home.r.e.d> dataList;
        com.jingdong.app.mall.home.r.e.h hVar;
        HomeRecycleView f2 = com.jingdong.app.mall.home.a.f();
        if (f2 != null && (f2.getAdapter() instanceof HomeRecyclerAdapter) && (dataList = ((HomeRecyclerAdapter) f2.getAdapter()).getDataList()) != null && dataList.size() >= 1 && this.d.length >= 1) {
            for (int i2 = 0; i2 < dataList.size(); i2++) {
                com.jingdong.app.mall.home.r.e.d dVar = dataList.get(i2);
                if (dVar != null && (hVar = dVar.mParentModel) != null) {
                    int i3 = hVar.X;
                    double height = this.b.getHeight();
                    Double.isNaN(height);
                    if (i3 > height * 0.8d || hVar.Z) {
                        return null;
                    }
                    int i4 = 0;
                    while (true) {
                        String[] strArr = this.d;
                        if (i4 < strArr.length) {
                            String str = strArr[i4];
                            if ("0".equals(this.f9485c)) {
                                FloorEntity floorEntity = dVar.o;
                                if (floorEntity instanceof BannerFloorEntity) {
                                    ArrayList<MallFloorBannerItem> arrayList = ((BannerFloorEntity) floorEntity).mCommercialList;
                                    if (arrayList != null && arrayList.size() >= 1) {
                                        if (arrayList.get(0).id.equals(str)) {
                                            com.jingdong.app.mall.home.widget.b lastCreateView = u.h(dVar.i()).getLastCreateView();
                                            com.jingdong.app.mall.home.o.a.f.n(lastCreateView);
                                            View view = (View) lastCreateView;
                                            this.f9494m = view;
                                            if (view != null) {
                                                int d2 = com.jingdong.app.mall.home.floor.common.d.d(h.u);
                                                this.f9495n = str;
                                                return new g(this, com.jingdong.app.mall.home.floor.common.d.d(R2.attr.ambientEnabled), (this.f9494m.getTop() > 0 ? this.f9494m.getTop() : hVar.f10692c) + (d2 >> 1), d2);
                                            }
                                        }
                                    }
                                } else {
                                    continue;
                                }
                            } else if ("1".equals(this.f9485c) && hVar.A.equals(str) && p(dVar.a)) {
                                com.jingdong.app.mall.home.widget.b lastCreateView2 = u.h(dVar.i()).getLastCreateView();
                                com.jingdong.app.mall.home.o.a.f.n(lastCreateView2);
                                this.f9494m = (View) lastCreateView2;
                                int i5 = dVar.mFloorHeight;
                                this.f9495n = str;
                                return new g(this, com.jingdong.app.mall.home.floor.common.d.d(R2.attr.ambientEnabled), i3 + (i5 / 2) + com.jingdong.app.mall.home.floor.common.d.d(hVar.f10692c), i5);
                            }
                            i4++;
                        }
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.b.setTranslationX(com.jingdong.app.mall.home.floor.common.d.f9279g << 1);
        View view = this.f9494m;
        if (view != null) {
            view.setAlpha(1.0f);
        }
        com.jingdong.app.mall.home.o.a.f.u0(new e());
    }

    private void n() {
        com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
        bVar.a("targetFloorType", this.f9485c);
        bVar.a("targetIdsStr", this.f9495n);
        com.jingdong.app.mall.home.r.c.a.y("Home_TargetPictureExpo", "", bVar.toString());
    }

    private boolean p(String str) {
        return "08002".equals(str) || "06018".equals(str) || "06062".equals(str) || "dynamic".equals(str);
    }

    public void o() {
        g l2 = l();
        if (l2 != null && this.f9494m != null) {
            int width = this.b.getWidth();
            int height = this.b.getHeight();
            double d2 = height;
            Double.isNaN(d2);
            double d3 = d2 * 0.8d;
            if (height != 0 && width != 0 && l2.a <= d3) {
                com.jingdong.app.mall.home.o.a.h.q();
                this.f9492k = 20;
                this.f9493l = 24;
                this.f9489h = 31;
                this.f9491j = 31 - 24;
                int d4 = com.jingdong.app.mall.home.floor.common.d.d(20);
                int i2 = d4 >> 1;
                this.f9488g = i2;
                int i3 = ((height >> 1) - l2.a) + i2;
                this.f9487f = i3;
                this.f9490i = ((height - l2.b) + d4) >> 1;
                ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, i3);
                ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
                ofFloat.setDuration(500L);
                ofFloat.addUpdateListener(new a(width, height));
                ofFloat.addListener(new b());
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.a, "alpha", 1.0f, 0.0f);
                ofFloat2.setInterpolator(new LinearInterpolator());
                ofFloat2.setDuration(500L);
                ValueAnimator ofFloat3 = ValueAnimator.ofFloat(0.0f, this.f9488g);
                ofFloat3.setInterpolator(new AccelerateDecelerateInterpolator());
                ofFloat3.setDuration(250L);
                ofFloat3.addUpdateListener(new c(width, height));
                ofFloat3.addListener(new d());
                f fVar = this.f9486e;
                if (fVar != null) {
                    fVar.start();
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ofFloat).with(ofFloat2).before(ofFloat3);
                animatorSet.start();
                this.f9494m.setAlpha(0.0f);
                n();
                return;
            }
            m();
            return;
        }
        m();
    }
}
