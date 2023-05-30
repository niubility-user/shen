package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes11.dex */
public class d4 {
    private static volatile d4 b;
    private Context a;

    private d4(Context context) {
        this.a = context;
    }

    public static d4 a(Context context) {
        if (b == null) {
            synchronized (d4.class) {
                if (b == null) {
                    b = new d4(context);
                }
            }
        }
        return b;
    }

    private void b(g.j.b.a.d dVar) {
        if (dVar instanceof g.j.b.a.c) {
            g.j.b.b.a.c(this.a, (g.j.b.a.c) dVar);
        } else if (dVar instanceof g.j.b.a.b) {
            g.j.b.b.a.b(this.a, (g.j.b.a.b) dVar);
        }
    }

    public void c(String str, int i2, long j2, long j3) {
        if (i2 < 0 || j3 < 0 || j2 <= 0) {
            return;
        }
        g.j.b.a.c g2 = c4.g(this.a, i2, j2, j3);
        g2.a(str);
        g2.b("5_3_0-C");
        b(g2);
    }

    public void d(String str, Intent intent, int i2, String str2) {
        if (intent == null) {
            return;
        }
        f(str, c4.j(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), i2, System.currentTimeMillis(), str2);
    }

    public void e(String str, Intent intent, String str2) {
        if (intent == null) {
            return;
        }
        f(str, c4.j(intent.getIntExtra("eventMessageType", -1)), intent.getStringExtra("messageId"), 5001, System.currentTimeMillis(), str2);
    }

    public void f(String str, String str2, String str3, int i2, long j2, String str4) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return;
        }
        g.j.b.a.b d = c4.d(this.a, str2, str3, i2, j2, str4);
        d.a(str);
        d.b("5_3_0-C");
        b(d);
    }

    public void g(String str, String str2, String str3, int i2, String str4) {
        f(str, str2, str3, i2, System.currentTimeMillis(), str4);
    }

    public void h(String str, String str2, String str3, String str4) {
        f(str, str2, str3, 5002, System.currentTimeMillis(), str4);
    }

    public void i(String str, String str2, String str3, String str4) {
        f(str, str2, str3, 5001, System.currentTimeMillis(), str4);
    }

    public void j(String str, String str2, String str3, String str4) {
        f(str, str2, str3, R2.color.jrtxt_main_title_color, System.currentTimeMillis(), str4);
    }
}
