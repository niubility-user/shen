package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.xiaomi.push.h7;
import com.xiaomi.push.i7;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class b0 {
    private static volatile b0 d;
    protected SharedPreferences a;
    protected SharedPreferences b;

    /* renamed from: c  reason: collision with root package name */
    private HashSet<a> f19060c = new HashSet<>();

    /* loaded from: classes11.dex */
    public static abstract class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private int f19061g;

        public a(int i2, String str) {
            this.f19061g = i2;
        }

        protected abstract void b();

        public boolean equals(Object obj) {
            return (obj instanceof a) && this.f19061g == ((a) obj).f19061g;
        }

        public int hashCode() {
            return this.f19061g;
        }

        @Override // java.lang.Runnable
        public final void run() {
            b();
        }
    }

    private b0(Context context) {
        this.a = context.getSharedPreferences("mipush_oc_normal", 0);
        this.b = context.getSharedPreferences("mipush_oc_custom", 0);
    }

    public static b0 d(Context context) {
        if (d == null) {
            synchronized (b0.class) {
                if (d == null) {
                    d = new b0(context);
                }
            }
        }
        return d;
    }

    private String e(int i2) {
        return "oc_" + i2;
    }

    private String g(i7 i7Var) {
        return "oc_version_" + i7Var.a();
    }

    private void i(SharedPreferences.Editor editor, Pair<Integer, Object> pair, String str) {
        Object obj = pair.second;
        if (obj instanceof Integer) {
            editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            editor.putLong(str, ((Long) obj).longValue());
        } else if (!(obj instanceof String)) {
            if (obj instanceof Boolean) {
                editor.putBoolean(str, ((Boolean) obj).booleanValue());
            }
        } else {
            String str2 = (String) obj;
            if (str.equals(e(h7.AppIsInstalledList.a()))) {
                str2 = com.xiaomi.push.m0.a(str2);
            }
            editor.putString(str, str2);
        }
    }

    public int a(int i2, int i3) {
        try {
            String e2 = e(i2);
            return this.b.contains(e2) ? this.b.getInt(e2, 0) : this.a.contains(e2) ? this.a.getInt(e2, 0) : i3;
        } catch (Exception e3) {
            g.j.a.a.a.c.o(i2 + " oc int error " + e3);
            return i3;
        }
    }

    public int b(i7 i7Var, int i2) {
        try {
            return this.a.getInt(g(i7Var), i2);
        } catch (Exception e2) {
            g.j.a.a.a.c.o(i7Var + " version error " + e2);
            return i2;
        }
    }

    public long c(int i2, long j2) {
        try {
            String e2 = e(i2);
            return this.b.contains(e2) ? this.b.getLong(e2, 0L) : this.a.contains(e2) ? this.a.getLong(e2, 0L) : j2;
        } catch (Exception e3) {
            g.j.a.a.a.c.o(i2 + " oc long error " + e3);
            return j2;
        }
    }

    public String f(int i2, String str) {
        try {
            String e2 = e(i2);
            return this.b.contains(e2) ? this.b.getString(e2, null) : this.a.contains(e2) ? this.a.getString(e2, null) : str;
        } catch (Exception e3) {
            g.j.a.a.a.c.o(i2 + " oc string error " + e3);
            return str;
        }
    }

    public synchronized void h() {
        this.f19060c.clear();
    }

    public synchronized void j(a aVar) {
        if (!this.f19060c.contains(aVar)) {
            this.f19060c.add(aVar);
        }
    }

    public void k(List<Pair<Integer, Object>> list) {
        if (com.xiaomi.push.d.a(list)) {
            return;
        }
        SharedPreferences.Editor edit = this.b.edit();
        for (Pair<Integer, Object> pair : list) {
            Object obj = pair.first;
            if (obj != null) {
                String e2 = e(((Integer) obj).intValue());
                if (pair.second == null) {
                    edit.remove(e2);
                } else {
                    i(edit, pair, e2);
                }
            }
        }
        edit.apply();
    }

    public void l(List<Pair<i7, Integer>> list, List<Pair<Integer, Object>> list2) {
        if (com.xiaomi.push.d.a(list) || com.xiaomi.push.d.a(list2)) {
            g.j.a.a.a.c.o("not update oc, because versions or configs are empty");
            return;
        }
        SharedPreferences.Editor edit = this.a.edit();
        edit.clear();
        for (Pair<i7, Integer> pair : list) {
            Object obj = pair.first;
            if (obj != null && pair.second != null) {
                edit.putInt(g((i7) obj), ((Integer) pair.second).intValue());
            }
        }
        for (Pair<Integer, Object> pair2 : list2) {
            Object obj2 = pair2.first;
            if (obj2 != null && pair2.second != null) {
                i(edit, pair2, e(((Integer) obj2).intValue()));
            }
        }
        edit.apply();
    }

    public boolean m(int i2, boolean z) {
        try {
            String e2 = e(i2);
            return this.b.contains(e2) ? this.b.getBoolean(e2, false) : this.a.contains(e2) ? this.a.getBoolean(e2, false) : z;
        } catch (Exception e3) {
            g.j.a.a.a.c.o(i2 + " oc boolean error " + e3);
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n() {
        g.j.a.a.a.c.B("OC_Callback : receive new oc data");
        HashSet hashSet = new HashSet();
        synchronized (this) {
            hashSet.addAll(this.f19060c);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar != null) {
                aVar.run();
            }
        }
        hashSet.clear();
    }
}
