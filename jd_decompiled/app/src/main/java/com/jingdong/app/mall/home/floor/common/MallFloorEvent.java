package com.jingdong.app.mall.home.floor.common;

import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.cleanmvp.common.BaseEvent;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class MallFloorEvent extends BaseEvent {
    private int a;
    private int b;

    /* renamed from: c */
    private boolean f9271c;
    private Object d;

    /* loaded from: classes4.dex */
    public class a extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g */
        final /* synthetic */ String f9272g;

        /* renamed from: h */
        final /* synthetic */ MallFloorEvent f9273h;

        a(String str, MallFloorEvent mallFloorEvent) {
            this.f9272g = str;
            this.f9273h = mallFloorEvent;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (TextUtils.isEmpty(this.f9272g)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("UIClassName", this.f9272g);
            this.f9273h.setBundle(bundle);
            EventBus.getDefault().post(this.f9273h);
        }
    }

    public MallFloorEvent(String str) {
        super(str);
        this.d = null;
    }

    private static void e(MallFloorEvent mallFloorEvent, String str) {
        com.jingdong.app.mall.home.w.a.b(new a(str, mallFloorEvent), false);
    }

    public static void f() {
        EventBus.getDefault().post(new MallFloorEvent("home_refresh"));
    }

    public static void g() {
        com.jingdong.app.mall.home.a.f8559h = true;
        EventBus.getDefault().post(new MallFloorEvent("home_on_scroll"));
    }

    public static void h() {
        com.jingdong.app.mall.home.a.f8559h = false;
        EventBus.getDefault().post(new MallFloorEvent("home_scroll_stop", com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k));
    }

    public static void i(boolean z) {
        if ((!z || JDHomeFragment.O0()) && !com.jingdong.app.mall.home.a.f8559h) {
            h();
        }
    }

    public static void j(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e(new MallFloorEvent("home_refresh_floor", obj), str);
    }

    public static void k(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        e(new MallFloorEvent("home_visible_floor", Boolean.valueOf(z)), str);
    }

    public static void l() {
        EventBus.getDefault().post(new MallFloorEvent("home_data_back"));
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public Object c() {
        return this.d;
    }

    public boolean d() {
        return this.f9271c;
    }

    public MallFloorEvent(String str, Object obj) {
        super(str);
        this.d = null;
        this.d = obj;
    }

    public MallFloorEvent(String str, int i2, int i3) {
        super(str);
        this.d = null;
        this.a = i2;
        this.b = i3;
    }

    public MallFloorEvent(String str, int i2, int i3, boolean z) {
        super(str);
        this.d = null;
        this.a = i2;
        this.b = i3;
        this.f9271c = z;
    }
}
