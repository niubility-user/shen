package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class x {

    /* renamed from: c  reason: collision with root package name */
    private static volatile x f18434c;
    private Context a;
    private List<k1> b = new ArrayList();

    private x(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        if (applicationContext == null) {
            this.a = context;
        }
    }

    public static x b(Context context) {
        if (f18434c == null) {
            synchronized (x.class) {
                if (f18434c == null) {
                    f18434c = new x(context);
                }
            }
        }
        return f18434c;
    }

    public int a(String str) {
        synchronized (this.b) {
            k1 k1Var = new k1();
            k1Var.b = str;
            if (this.b.contains(k1Var)) {
                for (k1 k1Var2 : this.b) {
                    if (k1Var2.equals(k1Var)) {
                        return k1Var2.a;
                    }
                }
            }
            return 0;
        }
    }

    public synchronized String c(l0 l0Var) {
        return this.a.getSharedPreferences("mipush_extra", 0).getString(l0Var.name(), "");
    }

    public synchronized void d(l0 l0Var, String str) {
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("mipush_extra", 0);
        sharedPreferences.edit().putString(l0Var.name(), str).apply();
    }

    public void e(String str) {
        synchronized (this.b) {
            k1 k1Var = new k1();
            k1Var.a = 0;
            k1Var.b = str;
            if (this.b.contains(k1Var)) {
                this.b.remove(k1Var);
            }
            this.b.add(k1Var);
        }
    }

    public boolean f(String str) {
        synchronized (this.b) {
            k1 k1Var = new k1();
            k1Var.b = str;
            return this.b.contains(k1Var);
        }
    }

    public void g(String str) {
        synchronized (this.b) {
            k1 k1Var = new k1();
            k1Var.b = str;
            if (this.b.contains(k1Var)) {
                Iterator<k1> it = this.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    k1 next = it.next();
                    if (k1Var.equals(next)) {
                        k1Var = next;
                        break;
                    }
                }
            }
            k1Var.a++;
            this.b.remove(k1Var);
            this.b.add(k1Var);
        }
    }

    public void h(String str) {
        synchronized (this.b) {
            k1 k1Var = new k1();
            k1Var.b = str;
            if (this.b.contains(k1Var)) {
                this.b.remove(k1Var);
            }
        }
    }
}
