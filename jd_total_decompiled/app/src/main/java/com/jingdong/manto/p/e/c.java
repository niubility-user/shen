package com.jingdong.manto.p.e;

import com.jingdong.manto.i.e;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.n;
import java.util.ArrayList;
import java.util.Iterator;
import javax.net.ssl.SSLContext;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class c {

    /* renamed from: h  reason: collision with root package name */
    public static int f13927h = -1;

    /* renamed from: i  reason: collision with root package name */
    public static int f13928i;
    public int a;

    /* renamed from: c  reason: collision with root package name */
    public SSLContext f13929c;
    public final String d;

    /* renamed from: g  reason: collision with root package name */
    public String f13932g;
    public String b = n.b + "/manto/";

    /* renamed from: e  reason: collision with root package name */
    protected final ArrayList<String> f13930e = new ArrayList<>();

    /* renamed from: f  reason: collision with root package name */
    public final ArrayList<com.jingdong.manto.p.e.b> f13931f = new ArrayList<>();

    /* loaded from: classes16.dex */
    public interface a {
        void a(int i2, long j2, long j3);

        void a(int i2, String str, String str2, int i3, JSONObject jSONObject);

        void a(String str);

        void a(JSONObject jSONObject);
    }

    /* loaded from: classes16.dex */
    public static class b {
        final a a;
        final c b;

        /* renamed from: c  reason: collision with root package name */
        final String f13933c;

        public b(c cVar, String str, a aVar) {
            this.b = cVar;
            this.f13933c = str;
            this.a = aVar;
        }

        public final void a(int i2, long j2, long j3) {
            this.a.a(i2, j2, j3);
        }

        public final void a(String str, String str2) {
            MantoLog.i("DownloadTaskManager", String.format("download start! filename %s, url %s", str, str2));
        }

        public final void a(String str, String str2, String str3) {
            MantoLog.e("DownloadTaskManager", String.format("download error! filename %s, url %s", str, str2));
            this.a.a(str3);
            this.b.c(this.f13933c);
        }

        public final void a(String str, String str2, String str3, int i2, JSONObject jSONObject) {
            this.b.c(this.f13933c);
            this.a.a(c.f13928i, str2, str, i2, jSONObject);
            MantoLog.i("DownloadTaskManager", String.format("download success! filename %s, url %s", str, str3));
        }

        public void a(JSONObject jSONObject) {
            this.a.a(jSONObject);
        }
    }

    public c(String str, String str2, e eVar) {
        this.f13932g = str;
        this.a = eVar.f13097g;
        this.f13929c = com.jingdong.manto.p.c.c(str);
        this.d = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        if (str != null) {
            synchronized (this.f13931f) {
                Iterator<com.jingdong.manto.p.e.b> it = this.f13931f.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    com.jingdong.manto.p.e.b next = it.next();
                    if (str.equals(next.f13923i)) {
                        this.f13931f.remove(next);
                        break;
                    }
                }
            }
        }
    }

    public final com.jingdong.manto.p.e.b a(String str) {
        if (str == null) {
            return null;
        }
        synchronized (this.f13931f) {
            Iterator<com.jingdong.manto.p.e.b> it = this.f13931f.iterator();
            while (it.hasNext()) {
                com.jingdong.manto.p.e.b next = it.next();
                if (str.equals(next.f13923i)) {
                    return next;
                }
            }
            return null;
        }
    }

    public final void a(com.jingdong.manto.p.e.b bVar) {
        MantoLog.e("DownloadTask!!!!!!!!!", "abort   " + Thread.currentThread().getName() + System.currentTimeMillis());
        if (bVar != null) {
            this.f13930e.add(bVar.f13923i);
            c(bVar.f13923i);
            bVar.a();
        }
    }

    public final boolean b(String str) {
        return this.f13930e.contains(str);
    }
}
