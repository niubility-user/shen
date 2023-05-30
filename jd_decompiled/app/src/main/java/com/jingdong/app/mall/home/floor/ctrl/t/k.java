package com.jingdong.app.mall.home.floor.ctrl.t;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import androidx.annotation.UiThread;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.home.deploy.view.base.BaseView;
import com.jingdong.app.mall.home.floor.bottomfloat.BaseFloatPriority;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.MallFloorDeploy;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.app.mall.home.xview.HomeXview;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.unification.navigationbar.NavigationTabLocationEntry;
import com.jingdong.common.unification.navigationbar.newbar.NavigationButton;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class k extends n {
    private int A;
    private String s = "";
    private int t = 0;
    private int u = 0;
    private int v = 0;
    private boolean w = true;
    private ValueAnimator x;
    private ValueAnimator y;
    private String z;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {
        a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            k.super.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ HomeRecycleView f9573g;

        b(HomeRecycleView homeRecycleView) {
            this.f9573g = homeRecycleView;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (this.f9573g == null) {
                return;
            }
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (intValue - k.this.t > 0) {
                this.f9573g.scrollBy(0, intValue - k.this.t);
                k.this.t = intValue;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends AnimatorListenerAdapter {
        c(k kVar) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ HomeRecycleView f9575g;

        d(HomeRecycleView homeRecycleView) {
            this.f9575g = homeRecycleView;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            if (this.f9575g == null) {
                return;
            }
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            if (intValue - k.this.u > 0) {
                this.f9575g.scrollBy(0, -(intValue - k.this.u));
                k.this.u = intValue;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e extends AnimatorListenerAdapter {
        e() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            super.onAnimationCancel(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            HomeXview homeXview = k.this.f9590g;
            if (homeXview != null) {
                homeXview.closeXView();
            }
        }
    }

    private void K(String str) {
        this.z = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0089, code lost:
        if (r0.equals("06006") == false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int L(int r6, com.jingdong.app.mall.home.r.e.d r7, com.jingdong.app.mall.home.r.e.f r8, int r9) {
        /*
            r5 = this;
            java.lang.String r0 = r7.a
            java.lang.String r7 = r7.b
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 0
            if (r1 == 0) goto Lc
            return r2
        Lc:
            java.lang.String r1 = "09B"
            boolean r7 = android.text.TextUtils.equals(r7, r1)
            r1 = -1
            r3 = 2
            r4 = 1
            if (r7 == 0) goto L2a
            com.jingdong.app.mall.home.floor.view.b.a r6 = com.jingdong.app.mall.home.floor.common.i.u.f(r8)
            int r6 = r6.getSubWeight()
            if (r6 != r3) goto L24
            int r6 = r9 >> 1
            return r6
        L24:
            if (r6 != r4) goto L29
            int r6 = r9 >> 2
            return r6
        L29:
            return r1
        L2a:
            r0.hashCode()
            int r7 = r0.hashCode()
            switch(r7) {
                case 45985392: goto L83;
                case 45985418: goto L78;
                case 45985420: goto L6d;
                case 45985514: goto L62;
                case 45985543: goto L57;
                case 45985544: goto L4c;
                case 45985548: goto L41;
                case 46074790: goto L36;
                default: goto L34;
            }
        L34:
            r2 = -1
            goto L8c
        L36:
            java.lang.String r7 = "09010"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L3f
            goto L34
        L3f:
            r2 = 7
            goto L8c
        L41:
            java.lang.String r7 = "06057"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L4a
            goto L34
        L4a:
            r2 = 6
            goto L8c
        L4c:
            java.lang.String r7 = "06053"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L55
            goto L34
        L55:
            r2 = 5
            goto L8c
        L57:
            java.lang.String r7 = "06052"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L60
            goto L34
        L60:
            r2 = 4
            goto L8c
        L62:
            java.lang.String r7 = "06044"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L6b
            goto L34
        L6b:
            r2 = 3
            goto L8c
        L6d:
            java.lang.String r7 = "06013"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L76
            goto L34
        L76:
            r2 = 2
            goto L8c
        L78:
            java.lang.String r7 = "06011"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L81
            goto L34
        L81:
            r2 = 1
            goto L8c
        L83:
            java.lang.String r7 = "06006"
            boolean r7 = r0.equals(r7)
            if (r7 != 0) goto L8c
            goto L34
        L8c:
            switch(r2) {
                case 0: goto La3;
                case 1: goto L9b;
                case 2: goto L98;
                case 3: goto L90;
                case 4: goto La3;
                case 5: goto La3;
                case 6: goto L90;
                case 7: goto L9b;
                default: goto L8f;
            }
        L8f:
            return r9
        L90:
            if (r6 >= r3) goto L95
            int r6 = r9 >> 2
            return r6
        L95:
            int r6 = r9 >> 1
            return r6
        L98:
            int r6 = r9 >> 2
            return r6
        L9b:
            if (r6 >= r4) goto La0
            int r6 = r9 >> 1
            return r6
        La0:
            int r6 = r9 >> 2
            return r6
        La3:
            int r6 = r9 >> 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.ctrl.t.k.L(int, com.jingdong.app.mall.home.r.e.d, com.jingdong.app.mall.home.r.e.f, int):int");
    }

    private JSONObject M() {
        try {
            if (this.f9596m == 2) {
                return X();
            }
            return W();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void N() {
        ValueAnimator valueAnimator = this.x;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.x.cancel();
    }

    private void O() {
        if (Y()) {
            destroy();
        } else {
            this.f9590g.displayXView();
        }
    }

    private void P() {
        if (this.f9596m == 2) {
            x();
            return;
        }
        com.jingdong.app.mall.home.l.a c2 = com.jingdong.app.mall.home.l.b.b().c(this.z);
        if (c2 != null) {
            c2.c(100, this.z, 0L);
        }
    }

    private void Q() {
        HomeRecycleView f2;
        ValueAnimator valueAnimator = this.y;
        if ((valueAnimator == null || !valueAnimator.isRunning()) && this.t > 0 && (f2 = com.jingdong.app.mall.home.a.f()) != null) {
            ValueAnimator ofInt = ValueAnimator.ofInt(0, this.t);
            this.y = ofInt;
            ofInt.setDuration(500L);
            this.y.setInterpolator(new LinearInterpolator());
            this.y.addUpdateListener(new d(f2));
            this.y.addListener(new e());
            this.y.start();
        }
    }

    private void R() {
        HomeRecycleView f2;
        if (this.v > 0 && (f2 = com.jingdong.app.mall.home.a.f()) != null) {
            ValueAnimator ofInt = ValueAnimator.ofInt(0, this.v);
            this.x = ofInt;
            ofInt.setDuration(500L);
            this.x.setInterpolator(new LinearInterpolator());
            this.x.addUpdateListener(new b(f2));
            this.x.addListener(new c(this));
            this.x.start();
        }
    }

    private Rect S(String str) {
        Rect T;
        HomeRecycleView f2 = com.jingdong.app.mall.home.a.f();
        if (f2 == null) {
            return null;
        }
        int childCount = f2.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = f2.getChildAt(i2);
            if ((childAt instanceof MallFloorDeploy) && (T = T((MallFloorDeploy) childAt, str)) != null) {
                return T;
            }
        }
        return null;
    }

    private Rect T(MallFloorDeploy mallFloorDeploy, String str) {
        Rect h2;
        if (mallFloorDeploy == null) {
            return null;
        }
        int childCount = mallFloorDeploy.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = mallFloorDeploy.getChildAt(i2);
            if ((childAt instanceof BaseView) && (h2 = ((BaseView) childAt).h(str)) != null) {
                return h2;
            }
        }
        return null;
    }

    private int U(com.jingdong.app.mall.home.r.e.d dVar) {
        int floorHeight = dVar.getFloorHeight();
        return (!TextUtils.equals(dVar.b, "09B") || dVar.n()) ? floorHeight : floorHeight - com.jingdong.app.mall.home.floor.common.d.d(24);
    }

    private int V(int i2, int i3) {
        int v = com.jingdong.app.mall.home.floor.common.i.m.v(this.f9590g.getContext());
        this.A = v;
        double d2 = v;
        Double.isNaN(d2);
        double d3 = i2 + i3;
        if (d2 * 0.7d < d3) {
            double d4 = v;
            Double.isNaN(d4);
            Double.isNaN(d3);
            this.v = (int) (d3 - (d4 * 0.7d));
            double d5 = v;
            Double.isNaN(d5);
            double d6 = i3;
            Double.isNaN(d6);
            return (int) ((d5 * 0.7d) - d6);
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v6, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r2v9 */
    private JSONObject W() {
        float f2;
        int i2;
        int floorHeight;
        int L;
        float f3;
        int i3;
        int i4;
        int i5;
        int i6;
        Rect rect;
        int U;
        int V;
        HomeRecycleView f4 = com.jingdong.app.mall.home.a.f();
        boolean z = false;
        if (f4 == null || !(f4.getAdapter() instanceof HomeRecyclerAdapter)) {
            return null;
        }
        List<com.jingdong.app.mall.home.r.e.d> dataList = ((HomeRecyclerAdapter) f4.getAdapter()).getDataList();
        if (this.f9590g == null || dataList == null || dataList.size() < 1) {
            return null;
        }
        float density = DPIUtil.getDensity();
        if (density == 0.0f) {
            return null;
        }
        f fVar = null;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            if (i7 >= dataList.size()) {
                f2 = density;
                i2 = i8;
                break;
            }
            com.jingdong.app.mall.home.r.e.d dVar = dataList.get(i7);
            if (dVar != null) {
                if (dVar.mParentModel != null && !dVar.q()) {
                    int i9 = dVar.mParentModel.d;
                    com.jingdong.app.mall.home.r.e.d r = dVar.r();
                    boolean o = r.o();
                    int d2 = i9 + (o ? com.jingdong.app.mall.home.floor.common.d.d(12) : 0);
                    int i10 = R2.attr.decimalNumber;
                    int i11 = 750 - (d2 << 1);
                    if (!r.g(this.s)) {
                        i8 += r.getFloorHeight();
                    } else {
                        int size = r.f10682c.size();
                        int i12 = 0;
                        ?? r2 = z;
                        while (i12 < size) {
                            com.jingdong.app.mall.home.r.e.f fVar2 = r.f10682c.get(i12);
                            if (fVar2 == null || (L = L(i12, r, fVar2, i11)) < 0) {
                                return r2;
                            }
                            String s = fVar2.s();
                            if (TextUtils.equals(this.s, s)) {
                                if (TextUtils.equals(r.a, "deploy")) {
                                    rect = S(this.s);
                                    if (rect != null) {
                                        d2 = com.jingdong.app.mall.home.floor.common.d.i(rect.left);
                                        L = com.jingdong.app.mall.home.floor.common.d.i(rect.right);
                                    }
                                } else {
                                    rect = r2;
                                }
                                int i13 = L;
                                int i14 = d2;
                                if (i14 + i13 > i10) {
                                    return r2;
                                }
                                if (rect != null) {
                                    int i15 = rect.bottom;
                                    V = V(rect.top, i15);
                                    U = i15;
                                } else {
                                    U = U(r) - (o ? com.jingdong.app.mall.home.floor.common.d.d(6) : 0);
                                    V = V(i8, U);
                                }
                                int d3 = com.jingdong.app.mall.home.floor.common.d.d(i14);
                                int d4 = com.jingdong.app.mall.home.floor.common.d.d(i13);
                                f3 = density;
                                double d5 = this.v;
                                i5 = i8;
                                double d6 = this.A;
                                Double.isNaN(d6);
                                i3 = i12;
                                i6 = size;
                                int i16 = U;
                                i4 = i11;
                                fVar = new f(d3, V, d4, i16, i5, d5 > d6 * 0.3d ? 600 : 300);
                                K(s);
                                d2 = i14;
                            } else {
                                f3 = density;
                                i3 = i12;
                                i4 = i11;
                                i5 = i8;
                                i6 = size;
                                d2 += L;
                            }
                            i12 = i3 + 1;
                            i11 = i4;
                            size = i6;
                            density = f3;
                            i8 = i5;
                            r2 = 0;
                            i10 = R2.attr.decimalNumber;
                        }
                        f2 = density;
                        i2 = i8;
                        if (r.q == t.FLOOR_RECOMMEND) {
                            break;
                        }
                        floorHeight = r.getFloorHeight();
                    }
                } else {
                    f2 = density;
                    i2 = i8;
                    floorHeight = dVar.getFloorHeight();
                }
                i8 = i2 + floorHeight;
                i7++;
                density = f2;
                z = false;
            }
            f2 = density;
            i7++;
            density = f2;
            z = false;
        }
        if (fVar != null) {
            fVar.c(1);
            String str = (i2 - fVar.f9580f) + "";
            return fVar.b(f2);
        }
        return null;
    }

    private JSONObject X() {
        NavigationTabLocationEntry iconLocation;
        float density = DPIUtil.getDensity();
        NavigationButton E = com.jingdong.app.mall.home.o.a.f.E("new");
        this.f9597n = E;
        if (density <= 0.0f || E == null || (iconLocation = E.getIconLocation()) == null) {
            return null;
        }
        return new f(iconLocation, 0, 300).b(density);
    }

    private boolean Y() {
        HomeXview homeXview;
        HomeRecycleView f2 = com.jingdong.app.mall.home.a.f();
        return !this.w || f2 == null || f2.i() != 0 || (homeXview = this.f9590g) == null || homeXview.h() == XView.PAGESTATE.STOP || com.jingdong.app.mall.home.floor.ctrl.h.N().O() > 0 || com.jingdong.app.mall.home.a.p;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n
    protected void E() {
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void a(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
        super.a(homeWebFloorEntity, baseActivity);
        com.jingdong.app.mall.home.floor.common.i.k.g();
        this.s = String.valueOf(homeWebFloorEntity.bindModule);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean c() {
        ValueAnimator valueAnimator;
        ValueAnimator valueAnimator2 = this.x;
        if (valueAnimator2 == null || !valueAnimator2.isRunning() || ((valueAnimator = this.y) != null && valueAnimator.isRunning())) {
            super.c();
        }
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.app.mall.home.floor.ctrl.t.j
    public void destroy() {
        ValueAnimator valueAnimator = this.y;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            n.p.postDelayed(new a(), 500L);
        } else {
            super.destroy();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f
    public void n(ViewGroup viewGroup) {
        super.n(viewGroup);
        XViewEntity xViewEntity = this.f9552e;
        if (xViewEntity != null) {
            xViewEntity.needAutoDisplay = false;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onCloseButtonClicked() {
        super.onCloseButtonClicked();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onError(int i2) {
        super.onError(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n
    public void onEventMainThread(BaseEvent baseEvent) {
        super.onEventMainThread(baseEvent);
        String type = baseEvent.getType();
        type.hashCode();
        if (type.equals("home_on_scroll") || type.equals("home_pull_down")) {
            this.w = false;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        super.onXViewDisplayed();
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewReady() {
        if (Y()) {
            destroy();
        } else {
            super.onXViewReady();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXViewRequest(XViewRequest xViewRequest) {
        String str;
        super.onXViewRequest(xViewRequest);
        if (xViewRequest == null || (str = xViewRequest.requestParams) == null) {
            return;
        }
        String optString = JDJSON.parseObject(str).optString("action");
        optString.hashCode();
        char c2 = '\uffff';
        switch (optString.hashCode()) {
            case -817161415:
                if (optString.equals("doAnimation")) {
                    c2 = 0;
                    break;
                }
                break;
            case -400960011:
                if (optString.equals("doRevertAnimation")) {
                    c2 = 1;
                    break;
                }
                break;
            case 108386723:
                if (optString.equals("ready")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1950219401:
                if (optString.equals("doShakeAnimation")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                R();
                HomeXview homeXview = this.f9590g;
                if (homeXview != null) {
                    homeXview.setCloseButtonVisible(8);
                    return;
                }
                return;
            case 1:
                Q();
                return;
            case 2:
                if (this.f9590g != null) {
                    try {
                        BaseFloatPriority baseFloatPriority = this.f9591h;
                        if (baseFloatPriority != null && !baseFloatPriority.a()) {
                            destroy();
                            return;
                        }
                        O();
                        JSONObject M = M();
                        if (M == null) {
                            return;
                        }
                        this.f9590g.execJs("window.h5BridgeTest && window.h5BridgeTest('" + M.toString() + "');");
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                return;
            case 3:
                P();
                return;
            default:
                return;
        }
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n, com.jingdong.app.mall.home.floor.ctrl.t.f, com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        N();
        Q();
        super.onXVivewClosed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.ctrl.t.n
    @UiThread
    public void u(ViewGroup viewGroup) {
        super.u(viewGroup);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class f {
        int a;
        int b;

        /* renamed from: c  reason: collision with root package name */
        int f9578c;
        int d;

        /* renamed from: e  reason: collision with root package name */
        int f9579e;

        /* renamed from: f  reason: collision with root package name */
        int f9580f;

        /* renamed from: g  reason: collision with root package name */
        int f9581g;

        /* renamed from: h  reason: collision with root package name */
        String f9582h;

        public f(NavigationTabLocationEntry navigationTabLocationEntry, int i2, int i3) {
            this.f9582h = "";
            this.a = navigationTabLocationEntry.topX;
            this.b = navigationTabLocationEntry.topY;
            this.f9578c = navigationTabLocationEntry.width;
            int i4 = navigationTabLocationEntry.height;
            this.d = i4;
            this.f9582h = "bottom";
            this.f9581g = i3;
            this.f9580f = i2 + i4;
        }

        public RectF a() {
            return new RectF(this.a, this.b, r1 + this.f9578c, r3 + this.d);
        }

        JSONObject b(float f2) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(JshopConst.JSHOP_PROMOTIO_X, this.a / f2);
                jSONObject.put(JshopConst.JSHOP_PROMOTIO_Y, this.b / f2);
                jSONObject.put(JshopConst.JSHOP_PROMOTIO_W, this.f9578c / f2);
                jSONObject.put(JshopConst.JSHOP_PROMOTIO_H, this.d / f2);
                jSONObject.put("drawLine", this.f9579e);
                jSONObject.put("scrollTime", this.f9581g);
                jSONObject.put("type", this.f9582h);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            jSONObject.toString();
            return jSONObject;
        }

        public void c(int i2) {
            this.f9579e = i2;
        }

        public f(int i2, int i3, int i4, int i5, int i6, int i7) {
            this.f9582h = "";
            this.a = i2;
            this.b = i3;
            this.f9578c = i4;
            this.d = i5;
            this.f9581g = i7;
            this.f9580f = i6 + i5;
        }
    }
}
