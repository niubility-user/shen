package g.d.a.j;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/* loaded from: classes12.dex */
public class e {
    private Context a;
    private SharedPreferences b;

    /* renamed from: c  reason: collision with root package name */
    private Object f19447c;

    /* loaded from: classes12.dex */
    private static class b {
        static e a = new e();
    }

    public static e b() {
        return b.a;
    }

    private SharedPreferences c() {
        Context context;
        SharedPreferences sharedPreferences = this.b;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        synchronized (this.f19447c) {
            SharedPreferences sharedPreferences2 = this.b;
            if (sharedPreferences2 != null || (context = this.a) == null) {
                return sharedPreferences2;
            }
            SharedPreferences sharedPreferences3 = context.getSharedPreferences("shared_msg_sdk", 0);
            this.b = sharedPreferences3;
            return sharedPreferences3;
        }
    }

    private Context d(Context context) {
        boolean b2 = g.d.a.j.a.b();
        d.a("fbeVersion is " + b2);
        if (b2 && Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext();
        }
        return context.getApplicationContext();
    }

    public String a() {
        SharedPreferences c2 = c();
        return c2 != null ? c2.getString("decryptTag", "DES") : "DES";
    }

    public boolean e() {
        SharedPreferences c2 = c();
        if (c2 != null) {
            return c2.getBoolean("hasDefaultChannelCreated", false);
        }
        return false;
    }

    public void f(String str) {
        SharedPreferences c2 = c();
        if (c2 != null) {
            c2.edit().putString("decryptTag", str).commit();
        }
    }

    public void g(boolean z) {
        SharedPreferences c2 = c();
        if (c2 != null) {
            c2.edit().putBoolean("hasDefaultChannelCreated", z).commit();
        }
    }

    private e() {
        this.f19447c = new Object();
        Context q = g.d.a.b.s().q();
        if (q != null) {
            this.a = d(q);
        }
        Context context = this.a;
        if (context != null) {
            this.b = context.getSharedPreferences("shared_msg_sdk", 0);
        }
    }
}
