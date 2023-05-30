package com.jingdong.app.mall.home.floor.ctrl;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class m {
    private static m d;
    private List<j> a;
    private int b = 10;

    /* renamed from: c  reason: collision with root package name */
    private Handler f9466c = new a(Looper.getMainLooper());

    /* loaded from: classes4.dex */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            if (message.what == 60009) {
                m.this.c();
            }
        }
    }

    private m() {
        com.jingdong.app.mall.home.o.a.f.G0(this);
        this.a = new CopyOnWriteArrayList();
    }

    private void b() {
        this.a.clear();
        this.f9466c.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Iterator<j> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().displayAnimation();
        }
    }

    public static m e() {
        if (d == null) {
            synchronized (l.class) {
                if (d == null) {
                    d = new m();
                }
            }
        }
        return d;
    }

    public static void f() {
        m mVar = d;
        if (mVar != null) {
            mVar.b();
        }
    }

    public void d() {
        this.f9466c.removeCallbacksAndMessages(null);
        this.f9466c.sendEmptyMessageDelayed(60009, this.b);
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
                this.f9466c.sendEmptyMessageDelayed(60009, this.b);
                return;
            case 1:
                this.f9466c.removeCallbacksAndMessages(null);
                return;
            case 2:
                this.f9466c.sendEmptyMessageDelayed(60009, this.b);
                return;
            case 3:
                this.f9466c.removeCallbacksAndMessages(null);
                return;
            default:
                return;
        }
    }
}
