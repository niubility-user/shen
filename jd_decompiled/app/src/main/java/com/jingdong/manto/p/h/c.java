package com.jingdong.manto.p.h;

import com.jingdong.manto.i.e;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.Call;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class c {
    public static int d = -1;

    /* renamed from: e  reason: collision with root package name */
    public static int f13964e;
    public int b;
    public final ArrayList<b> a = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    protected final ArrayList<String> f13965c = new ArrayList<>();

    /* loaded from: classes16.dex */
    public interface a {
        void a(int i2, long j2, long j3);

        void a(int i2, String str, int i3, JSONObject jSONObject);

        void a(String str);
    }

    public c(String str, String str2, e eVar) {
        this.b = 15;
        this.b = eVar.f13096f;
    }

    private void a(String str, Call call) {
        if (str != null) {
            synchronized (this.a) {
                Iterator<b> it = this.a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    b next = it.next();
                    if (str.equals(next.f13957i)) {
                        this.a.remove(next);
                        break;
                    }
                }
            }
        }
        if (call != null) {
            try {
                call.cancel();
            } catch (Exception unused) {
            }
        }
    }

    public final b a(String str) {
        if (str == null) {
            return null;
        }
        synchronized (this.a) {
            Iterator<b> it = this.a.iterator();
            while (it.hasNext()) {
                b next = it.next();
                if (str.equals(next.f13957i)) {
                    return next;
                }
            }
            return null;
        }
    }

    public final void a(b bVar) {
        if (bVar != null) {
            this.f13965c.add(bVar.f13957i);
            bVar.a = false;
            a(bVar.f13957i, bVar.d);
        }
    }

    public final boolean b(String str) {
        return this.f13965c.contains(str);
    }
}
