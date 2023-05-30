package com.jingdong.app.mall.home.floor.ctrl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class n {

    /* renamed from: k  reason: collision with root package name */
    private static n f9467k;
    private int d;

    /* renamed from: g  reason: collision with root package name */
    private boolean f9471g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f9472h;

    /* renamed from: i  reason: collision with root package name */
    private int f9473i;

    /* renamed from: j  reason: collision with root package name */
    private String f9474j;
    private int a = 1;
    private int b = 10;

    /* renamed from: c  reason: collision with root package name */
    private List<j> f9468c = new CopyOnWriteArrayList();

    /* renamed from: e  reason: collision with root package name */
    private Map<String, c> f9469e = new ConcurrentHashMap();

    /* renamed from: f  reason: collision with root package name */
    private Handler f9470f = new a(Looper.getMainLooper());

    /* loaded from: classes4.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            if (message.what != 608 || n.this.d < 0 || n.this.d >= n.this.f9468c.size()) {
                return;
            }
            ((j) n.this.f9468c.get(n.this.d)).displayAnimation();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {
        b() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        public void safeRun() {
            n.this.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class c {
        private int a;
        private int b;

        /* synthetic */ c(int i2, int i3, a aVar) {
            this(i2, i3);
        }

        private c(int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }
    }

    private n() {
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (n()) {
            for (int i2 = 0; i2 < this.f9468c.size(); i2++) {
                if (o()) {
                    k();
                    return;
                }
                p();
            }
        }
    }

    private void f() {
        this.f9470f.postDelayed(new b(), 100L);
    }

    private void g() {
        this.f9468c.clear();
        this.d = 0;
        this.f9470f.removeCallbacksAndMessages(null);
        this.f9469e.clear();
        this.f9473i = 0;
    }

    private int h(String str) {
        if (this.f9468c.isEmpty()) {
            return -1;
        }
        for (int i2 = 0; i2 < this.f9468c.size(); i2++) {
            if (TextUtils.equals(str, this.f9468c.get(i2).getSkuAnimationId())) {
                return i2;
            }
        }
        return -1;
    }

    public static n i() {
        if (f9467k == null) {
            synchronized (n.class) {
                if (f9467k == null) {
                    f9467k = new n();
                }
            }
        }
        return f9467k;
    }

    private void k() {
        if (this.f9470f.hasMessages(R2.attr.circularflow_viewCenter)) {
            return;
        }
        this.f9470f.sendEmptyMessageDelayed(R2.attr.circularflow_viewCenter, this.b * 100);
    }

    public static void l() {
        n nVar = f9467k;
        if (nVar != null) {
            nVar.g();
        }
    }

    private boolean n() {
        if (this.f9468c.isEmpty()) {
            return false;
        }
        int i2 = this.d;
        if (i2 < 0 || i2 >= this.f9468c.size() || !this.f9468c.get(this.d).isAnimationDisplay()) {
            Iterator<j> it = this.f9468c.iterator();
            while (it.hasNext()) {
                if (it.next().canPlayAnimation()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean o() {
        return this.d < this.f9468c.size() && this.f9468c.get(this.d).canPlayAnimation();
    }

    private void p() {
        int i2;
        int i3 = this.d;
        int i4 = i3 % 4;
        if (i4 != 3) {
            if (i4 != 2) {
                this.d = i3 + (this.a != 1 ? 2 : 1);
                return;
            } else if (this.a == 2) {
                int i5 = i3 + 2;
                int i6 = i5 < this.f9468c.size() ? i5 : 0;
                if (TextUtils.equals(this.f9468c.get(i6).getFloorId(), this.f9474j) && i6 != 0) {
                    this.d += 2;
                    return;
                } else {
                    this.d = this.f9473i + 1;
                    return;
                }
            } else {
                this.d = i3 + 1;
                return;
            }
        }
        int i7 = i3 + 1;
        this.d = i7;
        if (i7 >= this.f9468c.size()) {
            this.d = 0;
        }
        String floorId = this.f9468c.get(this.d).getFloorId();
        if (!TextUtils.equals(floorId, this.f9474j)) {
            this.f9474j = floorId;
            c cVar = this.f9469e.get(floorId);
            if (cVar == null) {
                return;
            }
            this.a = cVar.a;
            this.b = cVar.b;
            if (this.a == 2) {
                this.f9473i = this.d;
            }
        } else if (this.a != 2 || (i2 = this.d) == 0) {
        } else {
            this.d = i2 + 1;
        }
    }

    public void d(j jVar) {
        if (jVar == null) {
            return;
        }
        try {
            if (!jVar.hasSkuAnimation()) {
                this.f9468c.remove(jVar);
            } else if (this.f9468c.contains(jVar)) {
            } else {
                int h2 = h(jVar.getSkuAnimationId());
                if (h2 != -1 && h2 < this.f9468c.size()) {
                    this.f9468c.set(h2, jVar);
                    return;
                }
                this.f9468c.add(jVar);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void j() {
        p();
        if (this.f9471g || this.f9472h) {
            return;
        }
        e();
    }

    public void m(String str, int i2, int i3) {
        if (i2 == 1 || i2 == 2) {
            if (this.f9469e.isEmpty()) {
                this.f9474j = str;
                this.a = i2;
                this.b = i3;
            }
            if (this.f9469e.containsKey(str)) {
                return;
            }
            this.f9469e.put(str, new c(i2, i3, null));
        }
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
            case -254829437:
                if (type.equals("home_check_mta")) {
                    c2 = 1;
                    break;
                }
                break;
            case 818672077:
                if (type.equals("home_on_scroll")) {
                    c2 = 2;
                    break;
                }
                break;
            case 881725140:
                if (type.equals("home_scroll_stop")) {
                    c2 = 3;
                    break;
                }
                break;
            case 2118188898:
                if (type.equals("home_stop")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.f9472h = false;
                f();
                return;
            case 1:
                this.f9470f.removeCallbacksAndMessages(null);
                if (this.f9471g || this.f9472h) {
                    return;
                }
                f();
                return;
            case 2:
                this.f9471g = true;
                this.f9470f.removeCallbacksAndMessages(null);
                return;
            case 3:
                this.f9471g = false;
                f();
                return;
            case 4:
                this.f9472h = true;
                this.f9470f.removeCallbacksAndMessages(null);
                return;
            default:
                return;
        }
    }
}
