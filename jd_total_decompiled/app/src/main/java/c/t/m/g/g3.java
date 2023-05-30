package c.t.m.g;

import android.content.SharedPreferences;
import java.util.Observable;

/* loaded from: classes.dex */
public class g3 extends Observable implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: g */
    public static volatile String f442g = "cc_c_t_m_l_";

    /* renamed from: h */
    public static volatile g3 f443h;

    /* renamed from: i */
    public static volatile SharedPreferences f444i;

    public g3() {
        f444i = r3.b(f442g);
    }

    public static synchronized g3 a() {
        g3 g3Var;
        synchronized (g3.class) {
            if (f443h == null) {
                synchronized (g3.class) {
                    f443h = new g3();
                }
            }
            g3Var = f443h;
        }
        return g3Var;
    }

    public static void b(String str) {
        f442g = "cc_c_t_m_l_".concat(String.valueOf(str));
    }

    public synchronized SharedPreferences c() {
        if (f444i == null) {
            f444i = r3.b(f442g);
        }
        return f444i;
    }

    public synchronized void d() {
        if (f444i != null) {
            addObserver(y2.f());
            f444i.registerOnSharedPreferenceChangeListener(this);
        }
    }

    public synchronized void e() {
        if (f444i != null) {
            f444i.unregisterOnSharedPreferenceChangeListener(this);
            deleteObserver(y2.f());
        }
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        setChanged();
        notifyObservers(str);
    }
}
