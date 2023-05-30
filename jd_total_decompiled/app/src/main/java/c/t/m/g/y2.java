package c.t.m.g;

import android.content.SharedPreferences;
import android.os.Build;
import com.jingdong.common.utils.ApplicationUpgradeHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.web.managers.WebPerfManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class y2 implements Observer {

    /* renamed from: g  reason: collision with root package name */
    public static volatile String f772g = "";

    /* renamed from: h  reason: collision with root package name */
    public static volatile String f773h = "";

    /* renamed from: i  reason: collision with root package name */
    public static volatile String f774i = "";

    /* renamed from: j  reason: collision with root package name */
    public static final HashMap<String, String> f775j = new HashMap<>();

    /* renamed from: k  reason: collision with root package name */
    public static volatile y2 f776k = null;
    public volatile ConcurrentHashMap<String, String> b;

    /* renamed from: c  reason: collision with root package name */
    public volatile ConcurrentHashMap<String, List<i2>> f777c;
    public HashMap<Class<?>, Object> a = new HashMap<>();
    public volatile boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    public AtomicBoolean f778e = new AtomicBoolean(true);

    /* renamed from: f  reason: collision with root package name */
    public AtomicBoolean f779f = new AtomicBoolean(true);

    public y2() {
        this.b = null;
        this.f777c = null;
        HashMap<String, String> hashMap = f775j;
        this.b = new ConcurrentHashMap<>(((hashMap.size() * 4) / 3) + 1);
        this.f777c = new ConcurrentHashMap<>(((hashMap.size() * 4) / 3) + 1);
        this.a.put(String.class, "");
        this.a.put(Integer.class, Integer.MIN_VALUE);
        this.a.put(Float.class, Float.valueOf(Float.MIN_VALUE));
        this.a.put(Double.class, Double.valueOf(Double.MIN_VALUE));
        this.a.put(Long.class, Long.MIN_VALUE);
        this.a.put(Boolean.class, Boolean.FALSE);
        k();
    }

    public static void d(String str, String str2) {
        f772g = str;
        f774i = str2;
        h(ApplicationUpgradeHelper.APP_VERSION, str2);
    }

    public static void e(HashMap<String, String> hashMap) {
        h(WebPerfManager.HYBRID_SHARED_FILE_VERSION, "-1");
        h("cc_req_interval", "10800000");
        h("last_pull_time", "0");
        h("list_valid_model", "");
        h("list_valid_sdk_int", "");
        for (String str : hashMap.keySet()) {
            h(str, hashMap.get(str));
        }
    }

    public static synchronized y2 f() {
        y2 y2Var;
        synchronized (y2.class) {
            if (f776k == null) {
                synchronized (y2.class) {
                    f776k = new y2();
                }
            }
            y2Var = f776k;
        }
        return y2Var;
    }

    public static void h(String str, String str2) {
        f775j.put(str, str2);
    }

    public static void q(String str) {
        f773h = str;
    }

    public final Object a(String str, Class<?> cls) {
        if (!this.d) {
            k();
        }
        if (!this.a.containsKey(cls)) {
            throw new IllegalStateException("The property \"" + str + "\" don't support class type \"" + cls.toString() + "\"");
        }
        String str2 = this.b.get(str);
        if (str2 == null || str2.length() == 0) {
            str2 = f775j.get(str);
        }
        if (str2 == null) {
            str2 = "";
        }
        if (!this.f778e.get() || !this.f779f.get()) {
            if (!str2.contains("[f]")) {
                return g(f775j.get(str), cls);
            }
            str2 = str2.replace("[f]", "");
        }
        try {
            try {
                return g(str2, cls);
            } catch (Throwable unused) {
                return g(f775j.get(str), cls);
            }
        } catch (Throwable unused2) {
            return this.a.get(cls);
        }
    }

    public final String b(String str) {
        HashMap<String, String> hashMap = f775j;
        if (hashMap.containsKey(str)) {
            return hashMap.get(str);
        }
        throw new NullPointerException("Not exists property name \"" + str + "\"");
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0054 A[Catch: all -> 0x007d, TryCatch #0 {all -> 0x007d, blocks: (B:13:0x0044, B:15:0x0054, B:17:0x005a, B:19:0x006c, B:21:0x0074), top: B:26:0x0044 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x005a A[Catch: all -> 0x007d, TryCatch #0 {all -> 0x007d, blocks: (B:13:0x0044, B:15:0x0054, B:17:0x005a, B:19:0x006c, B:21:0x0074), top: B:26:0x0044 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void c() {
        String str;
        String str2;
        try {
            str2 = this.b.get("list_valid_model");
        } catch (Throwable unused) {
            this.f778e.getAndSet(true);
        }
        try {
            if (!t2.c(str2)) {
                String replace = z3.k().replace(LangUtils.SINGLE_SPACE, "");
                String[] split = str2.split(";");
                this.f778e.getAndSet(false);
                for (String str3 : split) {
                    if (!replace.equalsIgnoreCase(str3)) {
                    }
                }
                str = this.b.get("list_valid_sdk_int");
                if (!t2.c(str)) {
                    this.f779f.getAndSet(true);
                    return;
                }
                String num = Integer.toString(Build.VERSION.SDK_INT);
                String[] split2 = str.split(";");
                this.f779f.getAndSet(false);
                for (String str4 : split2) {
                    if (num.equalsIgnoreCase(str4)) {
                        this.f779f.getAndSet(true);
                        return;
                    }
                }
                return;
            }
            str = this.b.get("list_valid_sdk_int");
            if (!t2.c(str)) {
            }
        } catch (Throwable unused2) {
            this.f779f.getAndSet(true);
            return;
        }
        this.f778e.getAndSet(true);
    }

    public final Object g(String str, Class<?> cls) {
        return cls == Integer.class ? Integer.valueOf(Integer.parseInt(str)) : cls == Long.class ? Long.valueOf(Long.parseLong(str)) : cls == Boolean.class ? Boolean.valueOf(Boolean.parseBoolean(str)) : cls == Float.class ? Float.valueOf(Float.parseFloat(str)) : cls == Double.class ? Double.valueOf(Double.parseDouble(str)) : str;
    }

    public boolean i(String str) {
        return ((Boolean) a(str, Boolean.class)).booleanValue();
    }

    public int j(String str) {
        return ((Integer) a(str, Integer.class)).intValue();
    }

    public final void k() {
        try {
            m();
            o();
        } catch (Throwable unused) {
        }
    }

    public long l(String str) {
        return ((Long) a(str, Long.class)).longValue();
    }

    public final void m() {
        SharedPreferences c2 = g3.a().c();
        if (c2 == null) {
            return;
        }
        String str = (String) r3.c(c2, ApplicationUpgradeHelper.APP_VERSION, "");
        StringBuilder sb = new StringBuilder("tagVer:");
        sb.append(f774i);
        sb.append(",spVer:");
        sb.append(str);
        if (f774i.length() <= 0 || f774i.equals(str)) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("clear sp > pre:");
        sb2.append(str);
        sb2.append(",now:");
        sb2.append(f774i);
        c2.edit().clear().apply();
        r3.f(c2, ApplicationUpgradeHelper.APP_VERSION, f774i);
    }

    public String n(String str) {
        return (String) a(str, String.class);
    }

    public void o() {
        if (g3.a().c() == null) {
            return;
        }
        Iterator<String> it = f775j.keySet().iterator();
        while (it.hasNext()) {
            r(it.next());
        }
        this.d = true;
        c();
        new StringBuilder("---> read xml:").append(this.b.toString());
    }

    public void p() {
        this.f777c.clear();
    }

    public final void r(String str) {
        if (f775j.get(str) == null) {
            return;
        }
        try {
            SharedPreferences c2 = g3.a().c();
            if (c2 != null) {
                this.b.put(str, c2.getString(str, b(str)));
            }
        } catch (Throwable unused) {
            StringBuilder sb = new StringBuilder("update set value error [");
            sb.append(str);
            sb.append("]");
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        String str = obj == null ? null : (String) obj;
        if (str == null || str.length() == 0) {
            return;
        }
        String str2 = "update [" + str + "] : " + this.b.get(str) + " --> ";
        r(str);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(this.b.get(str));
        List<i2> list = this.f777c.get(str);
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<i2> it = list.iterator();
        while (it.hasNext()) {
            it.next().a(str);
        }
    }
}
