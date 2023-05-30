package com.jingdong.app.mall.lockscreen;

import android.content.Context;
import android.content.Intent;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class a {
    private static a b;
    private Context a;

    private a(Context context) {
        this.a = context;
    }

    public static a a(Context context) {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a(context);
                }
            }
        }
        return b;
    }

    public void b() {
        try {
            if (this.a != null) {
                this.a.startService(new Intent(this.a, LockScreenService.class));
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("LockScreen", "LockScreen--->" + e2.toString());
            }
        }
    }

    public void c() {
        try {
            if (this.a != null) {
                this.a.stopService(new Intent(this.a, LockScreenService.class));
            }
        } catch (Exception e2) {
            if (Log.D) {
                Log.e("LockScreen", "LockScreen--->" + e2.toString());
            }
        }
    }
}
