package com.jingdong.app.mall.home.r.f.a;

import android.graphics.Path;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.model.entity.FloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.FloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.cleanmvp.common.BaseEvent;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public abstract class b<E extends FloorEntity, G extends FloorEngine, U extends IMallFloorUI> extends c<U> {
    private Class<E> b;

    /* renamed from: c  reason: collision with root package name */
    private Class<G> f10737c;

    /* renamed from: f  reason: collision with root package name */
    protected com.jingdong.app.mall.home.r.e.d f10739f;
    protected E d = null;

    /* renamed from: e  reason: collision with root package name */
    protected G f10738e = null;

    /* renamed from: g  reason: collision with root package name */
    protected ArrayList<Boolean> f10740g = null;

    public b() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            com.jingdong.app.mall.home.o.a.f.n(genericSuperclass);
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            Type type = actualTypeArguments[0];
            Type type2 = actualTypeArguments[1];
            if (type instanceof Class) {
                com.jingdong.app.mall.home.o.a.f.n(type);
                this.b = (Class) type;
            } else if (type instanceof TypeVariable) {
                Type type3 = ((TypeVariable) type).getBounds()[0];
                com.jingdong.app.mall.home.o.a.f.n(type3);
                this.b = (Class) type3;
            }
            if (type2 instanceof Class) {
                com.jingdong.app.mall.home.o.a.f.n(type2);
                this.f10737c = (Class) type2;
            } else if (type2 instanceof TypeVariable) {
                Type type4 = ((TypeVariable) type2).getBounds()[0];
                com.jingdong.app.mall.home.o.a.f.n(type4);
                this.f10737c = (Class) type4;
            }
            u();
        }
    }

    private void u() {
        try {
            this.d = this.b.newInstance();
            this.f10738e = this.f10737c.newInstance();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean y(BaseEvent baseEvent) {
        IMallFloorUI iMallFloorUI;
        Bundle bundle;
        if (!(baseEvent instanceof MallFloorEvent) || (iMallFloorUI = (IMallFloorUI) c()) == null || (bundle = baseEvent.getBundle()) == null) {
            return false;
        }
        String string = bundle.getString("UIClassName");
        return !TextUtils.isEmpty(string) && string.contains(iMallFloorUI.getClass().getName());
    }

    public boolean A(int i2) {
        return i2 == 3;
    }

    public boolean B(int i2) {
        return i2 == 1 || i2 == 2;
    }

    public boolean C(int i2) {
        return A(this.d.getSeparationDownloadParams(i2).f9349i);
    }

    public boolean D(int i2) {
        return E(this.d.getSeparationDownloadParams(i2));
    }

    public boolean E(p.a.C0288a c0288a) {
        return B(c0288a.f9349i);
    }

    protected void F(MallFloorEvent mallFloorEvent) {
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (iMallFloorUI == null || !iMallFloorUI.isAttachWindow()) {
            return;
        }
        String type = mallFloorEvent.getType();
        type.hashCode();
        if (type.equals("home_refresh_floor")) {
            iMallFloorUI.onRefreshView();
        } else if (type.equals("home_visible_floor")) {
            Object c2 = mallFloorEvent.c();
            if (c2 instanceof Boolean) {
                iMallFloorUI.onSetVisible(((Boolean) c2).booleanValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void G(MallFloorEvent mallFloorEvent) {
    }

    public void H(IMallFloorUI iMallFloorUI) {
    }

    public void I(IMallFloorUI iMallFloorUI) {
        iMallFloorUI.onHomePause();
    }

    public void J(IMallFloorUI iMallFloorUI) {
        iMallFloorUI.onHomeRefresh();
    }

    public void K(IMallFloorUI iMallFloorUI, MallFloorEvent mallFloorEvent) {
        iMallFloorUI.onHomeResume(mallFloorEvent);
    }

    public void L(IMallFloorUI iMallFloorUI) {
        iMallFloorUI.onHomeStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void M(@NotNull IMallFloorUI iMallFloorUI) {
        iMallFloorUI.onParseEnd();
    }

    public final void N(com.jingdong.app.mall.home.r.e.d dVar, boolean z) {
        if (dVar.f10670n == null || dVar.o == null) {
            G g2 = this.f10738e;
            dVar.f10670n = g2;
            E e2 = this.d;
            dVar.o = e2;
            g2.e(dVar.mParentModel, dVar, e2);
        }
    }

    public final void O(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        E e2;
        if (dVar != null && dVar.mParentModel != null) {
            G g2 = (G) dVar.f10670n;
            if (g2 != null && (e2 = (E) dVar.o) != null) {
                this.f10738e = g2;
                this.d = e2;
                return;
            }
            u();
            G g3 = this.f10738e;
            dVar.f10670n = g3;
            E e3 = this.d;
            dVar.o = e3;
            g3.e(dVar.mParentModel, dVar, e3);
            return;
        }
        u();
        this.f10738e.e(hVar, dVar, this.d);
    }

    @Override // com.jingdong.app.mall.home.r.f.a.c
    public final void e() {
        super.e();
        this.f10738e = null;
        this.d = null;
    }

    public int f() {
        return this.d.getDividerColor();
    }

    public ArrayList<String> g() {
        return this.d.getExpoData();
    }

    public ArrayList<com.jingdong.app.mall.home.r.c.b> h() {
        return this.d.getExpoJson();
    }

    public String i() {
        return this.d.getFloorId();
    }

    public boolean j() {
        return this.d.isDataFromCache();
    }

    public int k() {
        return this.d.getItemDividerColor();
    }

    public List<Path> l() {
        return this.d.getItemDividerPaths();
    }

    public int m() {
        return this.d.getItemDividerWidth();
    }

    public final int n() {
        return this.d.getLayoutHeight();
    }

    public final int o() {
        return this.d.getLayoutLeftRightMargin();
    }

    @Override // com.jingdong.app.mall.home.r.f.a.c
    public void onEvent(BaseEvent baseEvent) {
        IMallFloorUI iMallFloorUI;
        if ((baseEvent instanceof MallFloorEvent) && (iMallFloorUI = (IMallFloorUI) c()) != null && iMallFloorUI.isAttachWindow()) {
            MallFloorEvent mallFloorEvent = (MallFloorEvent) baseEvent;
            if (!y(baseEvent)) {
                String type = baseEvent.getType();
                type.hashCode();
                if (type.equals("home_data_back")) {
                    H(iMallFloorUI);
                    return;
                }
                return;
            }
            F(mallFloorEvent);
        }
    }

    @Override // com.jingdong.app.mall.home.r.f.a.c
    public void onEventMainThread(BaseEvent baseEvent) {
        IMallFloorUI iMallFloorUI;
        if ((baseEvent instanceof MallFloorEvent) && (iMallFloorUI = (IMallFloorUI) c()) != null && iMallFloorUI.isAttachWindow()) {
            MallFloorEvent mallFloorEvent = (MallFloorEvent) baseEvent;
            String type = mallFloorEvent.getType();
            type.hashCode();
            char c2 = '\uffff';
            switch (type.hashCode()) {
                case -539747902:
                    if (type.equals("home_splash_open")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -277321843:
                    if (type.equals("home_resume")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -254829437:
                    if (type.equals("home_check_mta")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -19144837:
                    if (type.equals("home_refresh")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 436492672:
                    if (type.equals("home_splash_close")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 818672077:
                    if (type.equals("home_on_scroll")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 881725140:
                    if (type.equals("home_scroll_stop")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 1236015766:
                    if (type.equals("home_pause")) {
                        c2 = 7;
                        break;
                    }
                    break;
                case 2118188898:
                    if (type.equals("home_stop")) {
                        c2 = '\b';
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    ((IMallFloorUI) c()).onHomeSplashOpened(mallFloorEvent.a(), mallFloorEvent.b());
                    break;
                case 1:
                    K(iMallFloorUI, mallFloorEvent);
                    break;
                case 2:
                    iMallFloorUI.onCheckMta();
                    break;
                case 3:
                    J(iMallFloorUI);
                    break;
                case 4:
                    ((IMallFloorUI) c()).onHomeSplashClosed(mallFloorEvent.a(), mallFloorEvent.b());
                    break;
                case 5:
                    ((IMallFloorUI) c()).onHomeScroll();
                    break;
                case 6:
                    ((IMallFloorUI) c()).onHomeScrollStop(mallFloorEvent.a(), mallFloorEvent.b());
                    break;
                case 7:
                    I(iMallFloorUI);
                    break;
                case '\b':
                    L(iMallFloorUI);
                    break;
            }
            if (y(baseEvent)) {
                G(mallFloorEvent);
            }
        }
    }

    public final int p() {
        return this.d.getLayoutWidth();
    }

    public String q() {
        return this.d.getRightCornerText();
    }

    public float[] r() {
        return this.d.getShapedFloorRadii();
    }

    public String s() {
        return this.d.getTitleText();
    }

    public void t(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
        ArrayList<Boolean> arrayList = this.f10740g;
        if (arrayList != null) {
            arrayList.clear();
        }
        IMallFloorUI iMallFloorUI = (IMallFloorUI) c();
        if (dVar == null || iMallFloorUI == null) {
            return;
        }
        this.f10739f = dVar;
        this.d.resetItemDividerPath();
        this.f10738e.h(iMallFloorUI.getClass().getName());
        M(iMallFloorUI);
        v(hVar, dVar);
        iMallFloorUI.onFloorInitEnd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void v(com.jingdong.app.mall.home.r.e.h hVar, com.jingdong.app.mall.home.r.e.d dVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void w(int i2) {
        if (this.f10740g == null) {
            this.f10740g = new ArrayList<>();
        }
        this.f10740g.clear();
        for (int i3 = 0; i3 < i2; i3++) {
            this.f10740g.add(Boolean.FALSE);
        }
    }

    public boolean x() {
        ArrayList<Boolean> arrayList = this.f10740g;
        if (arrayList == null || arrayList.isEmpty()) {
            return false;
        }
        return !this.f10740g.contains(Boolean.FALSE);
    }

    public boolean z(int i2) {
        return i2 == 2;
    }

    public b(Class<E> cls, Class<G> cls2) {
        this.b = cls;
        this.f10737c = cls2;
        u();
    }
}
