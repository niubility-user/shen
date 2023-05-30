package com.jingdong.app.mall.home.r.f.a;

import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public abstract class c<UI extends IBaseUI> {
    protected WeakReference<UI> a = null;

    public void b(UI ui) {
        WeakReference<UI> weakReference;
        if (ui != null && ((weakReference = this.a) == null || ui != weakReference.get())) {
            this.a = new WeakReference<>(ui);
        }
    }

    public synchronized UI c() {
        WeakReference<UI> weakReference;
        weakReference = this.a;
        return weakReference == null ? null : weakReference.get();
    }

    public void d() {
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    public void e() {
        com.jingdong.app.mall.home.o.a.f.H0(this);
        this.a = null;
    }

    public abstract void onEvent(BaseEvent baseEvent);

    public abstract void onEventMainThread(BaseEvent baseEvent);
}
