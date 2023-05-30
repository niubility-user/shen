package com.jingdong.app.mall.navigationbar;

import android.content.SharedPreferences;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.widget.custom.discovery.RedPointManager;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class d {

    /* renamed from: c  reason: collision with root package name */
    public static final String f11379c = "d";
    private static d d;
    private SharedPreferences a;
    private SharedPreferences.Editor b;

    private d() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        this.a = jdSharedPreferences;
        this.b = jdSharedPreferences.edit();
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (d == null) {
                d = new d();
            }
            dVar = d;
        }
        return dVar;
    }

    private void d(int i2, boolean z) {
        if (Log.D) {
            Log.d("VideoTabRedPointManager", "showVideoRedPoint: tab = " + i2 + ", isH5 = " + z);
        }
        com.jingdong.app.mall.l.a.a.e().j(i2 != 6 && com.jingdong.app.mall.l.a.a.e().g());
    }

    public boolean b(int i2, boolean z) {
        if (z) {
            return false;
        }
        if (i2 != 2) {
            return this.a.getInt(RedPointManager.SHARED_FAXIAN_REDPOINTFLAG, 0) == 1;
        }
        this.b.putInt(RedPointManager.SHARED_FAXIAN_REDPOINTFLAG, 0).apply();
        return false;
    }

    public void c(int i2, boolean z) {
        if (Log.D) {
            Log.d(f11379c, "RedPointOpt-showRedpoint");
        }
        d(i2, z);
    }
}
