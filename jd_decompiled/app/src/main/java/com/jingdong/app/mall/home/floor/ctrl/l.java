package com.jingdong.app.mall.home.floor.ctrl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class l {

    /* renamed from: f  reason: collision with root package name */
    private static l f9463f;
    private List<j> a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f9464c;
    private int d = 10;

    /* renamed from: e  reason: collision with root package name */
    private Handler f9465e = new a(Looper.getMainLooper());

    /* loaded from: classes4.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            if (message.what == 60069) {
                l.this.d();
            }
        }
    }

    private l() {
        com.jingdong.app.mall.home.o.a.f.G0(this);
        this.a = new CopyOnWriteArrayList();
    }

    private void c() {
        this.a.clear();
        this.f9465e.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Iterator<j> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().displayAnimation();
        }
    }

    private int f(String str) {
        if (this.a.isEmpty()) {
            return -1;
        }
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (TextUtils.equals(str, this.a.get(i2).getSkuAnimationId())) {
                return i2;
            }
        }
        return -1;
    }

    public static l g() {
        if (f9463f == null) {
            synchronized (l.class) {
                if (f9463f == null) {
                    f9463f = new l();
                }
            }
        }
        return f9463f;
    }

    public static void i() {
        l lVar = f9463f;
        if (lVar != null) {
            lVar.c();
        }
    }

    public void b(j jVar) {
        if (jVar == null) {
            return;
        }
        if (!jVar.hasSkuAnimation()) {
            this.a.remove(jVar);
        } else if (this.a.contains(jVar)) {
        } else {
            int f2 = f(jVar.getSkuAnimationId());
            if (f2 == -1) {
                this.a.add(jVar);
            } else {
                this.a.set(f2, jVar);
            }
        }
    }

    public void e() {
        this.f9465e.removeCallbacksAndMessages(null);
        this.f9465e.sendEmptyMessageDelayed(60069, this.d);
    }

    public void h() {
        if (this.b || this.f9464c) {
            return;
        }
        this.f9465e.removeCallbacksAndMessages(null);
        this.f9465e.sendEmptyMessageDelayed(60069, this.d);
    }

    public void j(int i2) {
        this.d = i2 > 10 ? i2 * 100 : 1000;
    }

    public void onEventMainThread(MallFloorEvent mallFloorEvent) {
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
            case 818672077:
                if (type.equals("home_on_scroll")) {
                    c2 = 1;
                    break;
                }
                break;
            case 881725140:
                if (type.equals("home_scroll_stop")) {
                    c2 = 2;
                    break;
                }
                break;
            case 2118188898:
                if (type.equals("home_stop")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.b = false;
                this.f9465e.sendEmptyMessageDelayed(60069, this.d);
                return;
            case 1:
                this.f9464c = true;
                this.f9465e.removeCallbacksAndMessages(null);
                return;
            case 2:
                this.f9464c = false;
                this.f9465e.sendEmptyMessageDelayed(60069, this.d);
                return;
            case 3:
                this.b = true;
                this.f9465e.removeCallbacksAndMessages(null);
                return;
            default:
                return;
        }
    }
}
