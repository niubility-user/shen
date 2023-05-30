package com.jingdong.app.mall.home.deploy.view.base;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.h.c;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.widget.HomeRecycleView;
import com.jingdong.cleanmvp.common.BaseEvent;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public abstract class BaseView extends RelativeLayout implements c {

    /* renamed from: g */
    private int f8883g;

    /* renamed from: h */
    private BaseModel f8884h;

    /* renamed from: i */
    private final AtomicBoolean f8885i;

    /* renamed from: j */
    protected int f8886j;

    /* renamed from: k */
    private final com.jingdong.app.mall.home.floor.common.h.b f8887k;

    public BaseView(Context context) {
        super(context);
        this.f8885i = new AtomicBoolean(false);
        this.f8886j = -1;
        this.f8887k = new com.jingdong.app.mall.home.floor.common.h.b();
    }

    private int g(View view) {
        if (view != null && !(view instanceof HomeRecycleView)) {
            if (view instanceof BaseMallColorFloor) {
                return ((BaseMallColorFloor) view).getLayoutTop() + getTop();
            }
            return g((View) view.getParent());
        }
        return this.f8884h.f8878h.X + getTop();
    }

    public void b(BaseModel baseModel) {
        if (j(baseModel)) {
            if (!this.f8885i.getAndSet(true)) {
                k();
            }
            int c2 = baseModel.n().c();
            if (c2 != this.f8886j) {
                i(baseModel, c2);
                this.f8886j = c2;
            } else {
                int i2 = this.f8883g;
                if (i2 > 0 && i2 != d.f9279g) {
                    q();
                }
            }
            this.f8883g = d.f9279g;
            if (this.f8884h == baseModel) {
                return;
            }
            this.f8884h = baseModel;
            m();
        }
    }

    public void f(boolean z) {
    }

    @Override // com.jingdong.app.mall.home.floor.common.h.c
    public com.jingdong.app.mall.home.floor.common.h.b getClickInfo() {
        try {
            this.f8887k.f(getWidth());
            this.f8887k.d(getHeight());
            this.f8887k.e(g(this));
            return this.f8887k;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public Rect h(String str) {
        if (TextUtils.equals(this.f8884h.f("id"), str)) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            int width = getWidth();
            int height = getHeight();
            if (width <= 0 || height <= 0) {
                return null;
            }
            return new Rect(iArr[0], iArr[1], width, height);
        }
        return null;
    }

    public void i(BaseModel baseModel, int i2) {
    }

    protected abstract boolean j(BaseModel baseModel);

    protected abstract void k();

    public boolean l() {
        ViewParent parent = getParent();
        if (parent == null || parent.getParent() == null) {
            return false;
        }
        return m.I(this, com.jingdong.app.mall.home.a.f8560i + com.jingdong.app.mall.home.a.j(), com.jingdong.app.mall.home.a.f8562k, false);
    }

    protected abstract void m();

    public void n() {
    }

    public void o() {
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        if (baseEvent instanceof MallFloorEvent) {
            MallFloorEvent mallFloorEvent = (MallFloorEvent) baseEvent;
            String type = mallFloorEvent.getType();
            type.hashCode();
            char c2 = '\uffff';
            switch (type.hashCode()) {
                case -277321843:
                    if (type.equals("home_resume")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 881725140:
                    if (type.equals("home_scroll_stop")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1236015766:
                    if (type.equals("home_pause")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    o();
                    return;
                case 1:
                    p(mallFloorEvent.a(), mallFloorEvent.b());
                    return;
                case 2:
                    n();
                    return;
                default:
                    return;
            }
        }
    }

    public void p(int i2, int i3) {
        f(l());
    }

    public void q() {
    }
}
