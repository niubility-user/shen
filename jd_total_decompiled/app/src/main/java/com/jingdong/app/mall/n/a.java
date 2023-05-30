package com.jingdong.app.mall.n;

import com.jingdong.app.mall.MainFrameActivity;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import de.greenrobot.event.EventBus;

/* loaded from: classes4.dex */
public class a {
    private static a a;

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public MainFrameActivity b() {
        IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
        if (mainFrameActivity == null || !(mainFrameActivity instanceof MainFrameActivity)) {
            return null;
        }
        return (MainFrameActivity) mainFrameActivity;
    }

    public void c() {
        if (EventBus.getDefault().isRegistered(a())) {
            return;
        }
        EventBus.getDefault().register(a());
    }

    public void d() {
        EventBus.getDefault().unregister(a());
    }

    public void e() {
        if (EventBus.getDefault().isRegistered(a())) {
            return;
        }
        EventBus.getDefault().register(a());
    }

    public void f() {
    }

    public void g(BaseFragment baseFragment) {
        c.a = baseFragment != null && (baseFragment instanceof JDHomeFragment);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void onEvent(b bVar) {
        if (bVar != null) {
            String str = bVar.a;
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -2060613010:
                    if (str.equals("FUNC_finish")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -518026003:
                    if (str.equals("PARAM_toPersonal")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -445816418:
                    if (str.equals("FUNC_toShoppingCart")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 373060100:
                    if (str.equals("FUNC_toHomeActivity")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 919928096:
                    if (str.equals("FUNC_removeAllRecords")) {
                        c2 = 4;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    if (b() != null) {
                        b().finish();
                        return;
                    }
                    return;
                case 1:
                    break;
                case 2:
                    if (b() != null) {
                        b().toShoppingCart();
                        return;
                    }
                    return;
                case 3:
                    if (b() != null) {
                        b().toHomeActivity();
                        return;
                    }
                    return;
                case 4:
                    if (b() != null) {
                        b().removeAllRecords(bVar.b.getBoolean("PARAM_removeAllRecords"));
                        break;
                    }
                    break;
                default:
                    return;
            }
            if (b() != null) {
                b().getNavigationFragment().z(4);
            }
        }
    }

    public void onEventMainThread(b bVar) {
    }
}
