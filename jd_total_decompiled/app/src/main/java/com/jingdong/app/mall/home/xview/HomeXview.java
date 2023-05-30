package com.jingdong.app.mall.home.xview;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.web.ui.JDWebView;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class HomeXview extends XView {

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.app.mall.home.xview.b f11091g;

    /* renamed from: h  reason: collision with root package name */
    private AtomicBoolean f11092h;

    /* renamed from: i  reason: collision with root package name */
    private AtomicBoolean f11093i;

    /* renamed from: j  reason: collision with root package name */
    private HomeWebFloorViewEntity f11094j;

    /* loaded from: classes4.dex */
    class a implements XView.ICloseIntercept {
        a() {
        }

        @Override // com.jingdong.common.XView.XView.ICloseIntercept
        public boolean intercept() {
            HomeXview.this.q(true);
            if (HomeXview.this.f11091g != null) {
                HomeXview.this.f11091g.g();
            } else {
                HomeXview.this.i();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            HomeXview.this.destroyXView();
        }
    }

    /* loaded from: classes4.dex */
    class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            if (((XView) HomeXview.this).mJdWebView == null || HomeXview.this.getVisibility() == 0 || !HomeXview.this.isXViewReady()) {
                return;
            }
            ((XView) HomeXview.this).mJdWebView.onStop();
        }
    }

    /* loaded from: classes4.dex */
    class d implements JDWebView.InterceptTouchEventListener {
        final /* synthetic */ f a;

        d(f fVar) {
            this.a = fVar;
        }

        @Override // com.jingdong.common.web.ui.JDWebView.InterceptTouchEventListener
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (HomeXview.this.f11092h.get() || motionEvent.getAction() != 1) {
                return false;
            }
            HomeXview.this.f11092h.set(true);
            this.a.a();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements JDWebView.InterceptTouchEventListener {
        e() {
        }

        @Override // com.jingdong.common.web.ui.JDWebView.InterceptTouchEventListener
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (HomeXview.this.f11092h.get() || HomeXview.this.f11094j == null || motionEvent.getAction() != 1) {
                return false;
            }
            HomeXview.this.f11092h.set(true);
            String str = HomeXview.this.f11094j.clickEventId;
            if (TextUtils.isEmpty(str)) {
                str = "Home_AutoXVIEWClick";
            }
            com.jingdong.app.mall.home.r.c.a.t(str, HomeXview.this.f11094j.sourceValue, HomeXview.this.f11094j.srvJson, RecommendMtaUtils.Home_PageId);
            HomeXview.this.l();
            return false;
        }
    }

    /* loaded from: classes4.dex */
    public interface f {
        void a();
    }

    public HomeXview(Context context) {
        this(context, null);
    }

    private void f(boolean z) {
        XViewEntity xViewEntity = this.mXViewEntity;
        if (xViewEntity != null) {
            xViewEntity.needAutoClose = z;
        }
    }

    private boolean g(String str, Uri uri) {
        boolean z = true;
        if (uri != null) {
            try {
                if (this.mXViewEntity == null) {
                    return true;
                }
                String optString = com.jingdong.app.mall.home.r.c.b.c(uri.getQueryParameter("params")).optString("closeXView");
                boolean z2 = this.mXViewEntity.needAutoClose;
                if (!TextUtils.isEmpty(optString)) {
                    XViewEntity xViewEntity = this.mXViewEntity;
                    if (TextUtils.equals(optString, DYConstants.DY_FALSE)) {
                        z = false;
                    }
                    xViewEntity.needAutoClose = z;
                }
                return z2;
            } catch (Exception e2) {
                e2.printStackTrace();
                return this.mXViewEntity.needAutoClose;
            }
        }
        return true;
    }

    public static void m(String str, HomeWebFloorViewEntity homeWebFloorViewEntity) {
        com.jingdong.app.mall.home.r.c.a.s("Home_XVIEWClose", str, homeWebFloorViewEntity == null ? "" : homeWebFloorViewEntity.srvJson);
    }

    public static void n(String str, String str2, String str3) {
        com.jingdong.app.mall.home.r.c.d b2 = com.jingdong.app.mall.home.r.c.d.b("Home_AutoXVIEW");
        b2.f(str2);
        b2.e(str);
        b2.a("showtype", str3);
        b2.c();
    }

    @Override // com.jingdong.common.XView.XView, com.jingdong.common.XView.IXView
    public void configXView(ViewGroup viewGroup, XViewEntity xViewEntity, XViewCallBack xViewCallBack) {
        super.configXView(viewGroup, xViewEntity, xViewCallBack);
        q(false);
    }

    @Override // com.jingdong.common.XView.XView, com.jingdong.common.XView.IXView
    public void destroyXView() {
        if (com.jingdong.app.mall.home.o.a.f.o0()) {
            super.destroyXView();
        } else {
            com.jingdong.app.mall.home.o.a.f.E0(new b());
        }
    }

    @Override // com.jingdong.common.XView.XView, com.jingdong.common.XView.IXView
    public boolean displayXView() {
        com.jingdong.app.mall.home.v.c.a.a(this);
        return super.displayXView();
    }

    public XView.PAGESTATE h() {
        return this.mPageState;
    }

    public void i() {
        closeXView();
        this.f11092h.set(false);
    }

    public void j() {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.onResume();
        }
    }

    public void k() {
        com.jingdong.app.mall.home.o.a.f.F0(new c(), 10000L);
    }

    public void l() {
    }

    public void o(com.jingdong.app.mall.home.xview.b bVar) {
        this.f11091g = bVar;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewParent parent = getParent();
        if (parent == null || (!(parent instanceof BaseMallColorFloor) && parent.getParent() == null)) {
            closeXView();
        }
    }

    @Override // com.jingdong.common.XView.XView, com.jingdong.common.XView.IXView
    public void onStop() {
        this.mPageState = XView.PAGESTATE.STOP;
        if (this.mXViewEntity != null && getVisibility() == 0) {
            setVisibility(4);
            this.mJdWebView.onStop();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.XView.XView
    public void openAppJump(String str, Uri uri) {
        if (this.f11093i.get()) {
            return;
        }
        boolean g2 = g(str, uri);
        super.openAppJump(str, uri);
        f(g2);
    }

    public void p(f fVar) {
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.setInterceptTouchEventListener(new d(fVar));
        }
    }

    public void q(boolean z) {
        this.f11093i.set(z);
    }

    public void r(HomeWebFloorViewEntity homeWebFloorViewEntity) {
        this.f11094j = homeWebFloorViewEntity;
        JDWebView jDWebView = this.mJdWebView;
        if (jDWebView != null) {
            jDWebView.setInterceptTouchEventListener(new e());
        }
    }

    public void s(boolean z) {
        this.mPageState = z ? XView.PAGESTATE.STOP : XView.PAGESTATE.RESUME;
    }

    @Override // com.jingdong.common.XView.XView
    public void setCloseButtonVisible(int i2) {
        super.setCloseButtonVisible(i2);
    }

    @Override // com.jingdong.common.XView.XView, android.view.View
    public void setVisibility(int i2) {
        if (i2 == 0) {
            j();
        }
        super.setVisibility(i2);
    }

    public HomeXview(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public HomeXview(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11092h = new AtomicBoolean(false);
        this.f11093i = new AtomicBoolean(false);
        setCloseIntercept(new a());
    }
}
