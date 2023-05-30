package com.jingdong.app.mall.home.floor.ctrl.t;

import android.os.Bundle;
import android.view.ViewGroup;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorEntity;
import com.jingdong.app.mall.home.floor.model.entity.HomeWebFloorViewEntity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewEntity;
import com.jingdong.common.XView.XViewRequest;
import de.greenrobot.event.EventBus;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public abstract class f implements j {
    protected BaseActivity b;

    /* renamed from: c  reason: collision with root package name */
    protected HomeWebFloorEntity f9551c;
    protected int d;

    /* renamed from: e  reason: collision with root package name */
    protected XViewEntity f9552e;
    protected final AtomicBoolean a = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    protected int f9553f = 0;

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public void a(HomeWebFloorEntity homeWebFloorEntity, BaseActivity baseActivity) {
        this.b = baseActivity;
        this.f9551c = homeWebFloorEntity;
        this.f9553f = 1;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public void b() {
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean c() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean d() {
        int i2 = this.f9553f;
        return i2 == 2 || i2 == 3 || i2 == 4;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public void destroy() {
        com.jingdong.app.mall.home.o.a.f.K0(this.a);
        this.f9553f = 0;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public void f() {
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public int getPriority() {
        return this.d;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public void h() {
    }

    public boolean i() {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.ctrl.t.j
    public boolean isShowing() {
        if (this.f9553f == 4) {
            return true;
        }
        XView l2 = l();
        return (l2 == null || l2.getVisibility() != 0 || l2.getParent() == null) ? false : true;
    }

    public HomeWebFloorEntity j() {
        return this.f9551c;
    }

    public int k() {
        return this.f9553f;
    }

    protected XView l() {
        return null;
    }

    public void m(String str, HomeWebFloorViewEntity homeWebFloorViewEntity) {
        this.f9553f = 1;
    }

    public void n(ViewGroup viewGroup) {
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onCloseButtonClicked() {
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onError(int i2) {
        this.f9553f = 5;
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onStart() {
        this.f9553f = 2;
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        this.f9553f = 4;
        if (this.d < 100) {
            com.jingdong.app.mall.home.floor.common.e eVar = new com.jingdong.app.mall.home.floor.common.e("homePageXViewDisplay");
            Bundle bundle = new Bundle();
            bundle.putInt("XViewType", e());
            eVar.setBundle(bundle);
            EventBus.getDefault().post(eVar);
        }
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewLoadingUrl(String str) {
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewReady() {
        this.f9553f = 3;
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewRequest(XViewRequest xViewRequest) {
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewVisibleChanged(boolean z) {
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        com.jingdong.app.mall.home.o.a.f.K0(this.a);
        this.f9553f = 6;
        if (this.d < 100) {
            com.jingdong.app.mall.home.floor.common.e eVar = new com.jingdong.app.mall.home.floor.common.e("homePageXViewClose");
            Bundle bundle = new Bundle();
            bundle.putInt("XViewType", e());
            eVar.setBundle(bundle);
            EventBus.getDefault().post(eVar);
        }
    }
}
