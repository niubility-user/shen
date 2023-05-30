package com.tencent.mapsdk.internal;

import com.tencent.map.tools.json.annotation.Json;
import com.tencent.mapsdk.shell.events.ReportEvent;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes9.dex */
public class r6 extends w6 {
    @Json(name = "loadSuccess")
    private boolean b;
    @Json(name = "loadSuccessTime")

    /* renamed from: c  reason: collision with root package name */
    private long f17179c;
    @Json(name = "firstLoadTime")
    private long d;
    @Json(name = "configUpdate")

    /* renamed from: e  reason: collision with root package name */
    private b f17180e;
    @Json(name = "tileErrors")

    /* renamed from: f  reason: collision with root package name */
    private Set<e> f17181f;
    @Json(name = "configError")

    /* renamed from: g  reason: collision with root package name */
    private a f17182g;

    /* loaded from: classes9.dex */
    public static class a extends w6 {
        @Json(name = "failUpdates")
        private Set<c> b;
        @Json(name = "missFiles")

        /* renamed from: c  reason: collision with root package name */
        private Set<d> f17183c;

        public a(long j2) {
            super(j2);
        }
    }

    /* loaded from: classes9.dex */
    public static class b extends w6 {
        @Json(name = "success")
        private boolean b;
        @Json(name = "loadBeginTime")

        /* renamed from: c  reason: collision with root package name */
        private long f17184c;

        public b(long j2) {
            super(j2);
            this.b = false;
            this.f17184c = 0L;
        }
    }

    /* loaded from: classes9.dex */
    public static class c extends w6 {
        @Json(name = "name")
        public String b;
        @Json(name = "time")

        /* renamed from: c  reason: collision with root package name */
        public long f17185c;
        @Json(name = "expectMd5")
        public String d;
        @Json(name = "actualMd5")

        /* renamed from: e  reason: collision with root package name */
        public String f17186e;
        @Json(name = "localVer")

        /* renamed from: f  reason: collision with root package name */
        public int f17187f;
        @Json(name = "netError")

        /* renamed from: g  reason: collision with root package name */
        public int f17188g;

        public c() {
            this(0L);
        }

        public c(long j2) {
            super(j2);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof c) {
                return e7.c(this.b, ((c) obj).b);
            }
            return false;
        }

        public int hashCode() {
            String str = this.b;
            return str == null ? super.hashCode() : str.hashCode();
        }
    }

    /* loaded from: classes9.dex */
    public static class d extends w6 {
        @Json(name = "name")
        private String b;
        @Json(name = "time")

        /* renamed from: c  reason: collision with root package name */
        private long f17189c;

        public d(long j2) {
            super(j2);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof d) {
                return e7.c(this.b, ((d) obj).b);
            }
            return false;
        }

        public int hashCode() {
            String str = this.b;
            return str == null ? super.hashCode() : str.hashCode();
        }
    }

    /* loaded from: classes9.dex */
    public static class e extends w6 {
        @Json(name = "time")
        private long b;
        @Json(name = "tid")

        /* renamed from: c  reason: collision with root package name */
        private String f17190c;
        @Json(name = "netError")
        private int d;

        public e(long j2) {
            super(j2);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof e) {
                return e7.c(this.f17190c, ((e) obj).f17190c);
            }
            return false;
        }

        public int hashCode() {
            String str = this.f17190c;
            return str == null ? super.hashCode() : str.hashCode();
        }
    }

    public r6(long j2) {
        super(j2);
        this.b = false;
        this.f17179c = 0L;
        this.d = 0L;
    }

    public void a(long j2, String str) {
        if (this.f17182g == null) {
            this.f17182g = new a(a());
        }
        if (this.f17182g.f17183c == null) {
            this.f17182g.f17183c = new CopyOnWriteArraySet();
        }
        if (this.f17182g.f17183c.size() > 9) {
            return;
        }
        d dVar = new d(this.a);
        dVar.f17189c = j2 - this.a;
        dVar.b = str;
        this.f17182g.f17183c.add(dVar);
        HashMap hashMap = new HashMap();
        hashMap.put("startTime", "" + this.a);
        hashMap.put("endTime", "" + j2);
        u.f().a(new ReportEvent("mapload-missfile", hashMap));
    }

    public void a(long j2, String str, int i2) {
        if (this.f17181f == null) {
            this.f17181f = new CopyOnWriteArraySet();
        }
        if (this.f17181f.size() > 9) {
            return;
        }
        e eVar = new e(j2);
        eVar.b = j2 - this.a;
        eVar.f17190c = str;
        eVar.d = i2;
        this.f17181f.add(eVar);
        HashMap hashMap = new HashMap();
        hashMap.put("tid", str);
        hashMap.put("netError", "" + i2);
        hashMap.put("startTime", "" + this.a);
        hashMap.put("endTime", "" + j2);
        u.f().a(new ReportEvent("mapload-tile", hashMap));
    }

    public void a(c cVar) {
        if (this.f17182g == null) {
            this.f17182g = new a(a());
        }
        if (this.f17182g.b == null) {
            this.f17182g.b = new CopyOnWriteArraySet();
        }
        if (this.f17182g.b.size() > 9) {
            return;
        }
        this.f17182g.b.add(cVar);
        HashMap hashMap = new HashMap();
        hashMap.put("name", cVar.b);
        hashMap.put("localVer", "" + cVar.f17187f);
        hashMap.put("netError", "" + cVar.f17188g);
        hashMap.put("expectMd5", cVar.d);
        hashMap.put("actualMd5", cVar.f17186e);
        hashMap.put("startTime", "" + this.a);
        hashMap.put("endTime", "" + this.a + cVar.f17185c);
        u.f().a(new ReportEvent("mapload-configfile", hashMap));
    }

    public void a(boolean z, long j2) {
        b bVar = new b(a());
        this.f17180e = bVar;
        bVar.b = z;
        long j3 = j2 - this.a;
        if (j3 > 0) {
            this.f17180e.f17184c = j3;
        }
    }

    public void b(boolean z, long j2) {
        this.b = z;
        int i2 = (this.d > 0L ? 1 : (this.d == 0L ? 0 : -1));
        long j3 = j2 - this.a;
        if (i2 > 0) {
            this.f17179c = j3;
        } else {
            this.d = j3;
        }
        this.f17179c = j2;
        HashMap hashMap = new HashMap();
        hashMap.put("success", z + "");
        hashMap.put("startTime", this.a + "");
        hashMap.put("endTime", j2 + "");
        hashMap.put("duration", this.f17179c + "");
        hashMap.put("firstDuration", this.d + "");
        u.f().a(new ReportEvent("mapload", hashMap));
    }
}
