package c.t.m.g;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.tencent.connect.common.Constants;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentPoi;
import com.tencent.map.geolocation.util.SoUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class q5 implements Parcelable, TencentLocation {
    public static final Parcelable.Creator<TencentLocation> CREATOR = new a();
    public static final q5 w = new q5(-1);

    /* renamed from: g  reason: collision with root package name */
    public d3 f627g;

    /* renamed from: h  reason: collision with root package name */
    public v2 f628h;

    /* renamed from: i  reason: collision with root package name */
    public int f629i;

    /* renamed from: j  reason: collision with root package name */
    public int f630j;

    /* renamed from: k  reason: collision with root package name */
    public String f631k;

    /* renamed from: l  reason: collision with root package name */
    public int f632l;

    /* renamed from: m  reason: collision with root package name */
    public n2 f633m;

    /* renamed from: n  reason: collision with root package name */
    public final Bundle f634n;
    public String o;
    public String p;
    public Location q;
    public final long r;
    public long s;
    public long t;
    public int u;
    public int v;

    /* loaded from: classes.dex */
    public static class a implements Parcelable.Creator<TencentLocation> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public final TencentLocation createFromParcel(Parcel parcel) {
            q5 q5Var = new q5(parcel.readInt(), (a) null);
            d3 d3Var = new d3();
            n2 n2Var = new n2();
            t3 t3Var = new t3();
            n2Var.f562c = t3Var;
            q5Var.o = parcel.readString();
            q5Var.p = parcel.readString();
            d3Var.a = parcel.readDouble();
            d3Var.b = parcel.readDouble();
            d3Var.d = parcel.readFloat();
            d3Var.f348c = parcel.readDouble();
            d3Var.f350f = parcel.readString();
            t3Var.a = parcel.readString();
            t3Var.f683e = parcel.readString();
            t3Var.f684f = parcel.readString();
            t3Var.f685g = parcel.readString();
            t3Var.f688j = parcel.readString();
            t3Var.f689k = parcel.readString();
            t3Var.b = parcel.readString();
            q5Var.f627g = d3Var;
            q5Var.f633m = n2Var;
            q5Var.s = parcel.readLong();
            q5Var.t = parcel.readLong();
            Bundle readBundle = parcel.readBundle();
            if (readBundle != null) {
                q5Var.f634n.putAll(readBundle);
            }
            return q5Var;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b  reason: merged with bridge method [inline-methods] */
        public final TencentLocation[] newArray(int i2) {
            return new TencentLocation[i2];
        }
    }

    /* loaded from: classes.dex */
    public static class b {
        public String a;
        public q5 b;

        /* renamed from: c  reason: collision with root package name */
        public int f635c;
        public String d = "network";

        /* renamed from: e  reason: collision with root package name */
        public Location f636e;

        /* renamed from: f  reason: collision with root package name */
        public Bundle f637f;

        public b a(int i2) {
            this.f635c = i2;
            return this;
        }

        public b b(Location location) {
            this.f636e = new Location(location);
            return this;
        }

        public b c(Bundle bundle) {
            this.f637f = bundle;
            return this;
        }

        public b d(q5 q5Var) {
            this.b = q5Var;
            return this;
        }

        public b e(String str) {
            this.a = str;
            return this;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
            jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: c.t.m.g.q5.h(c.t.m.g.q5, int):c.t.m.g.q5
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:74)
            	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
            Caused by: java.lang.NullPointerException
            	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
            	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
            	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
            	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
            	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
            	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:63)
            	... 1 more
            */
        public c.t.m.g.q5 f() {
            /*
                r6 = this;
                java.lang.Class<java.lang.Long> r0 = java.lang.Long.class
                java.lang.String r1 = r6.a
                if (r1 == 0) goto L12
                c.t.m.g.q5 r1 = new c.t.m.g.q5     // Catch: org.json.JSONException -> Lf
                java.lang.String r2 = r6.a     // Catch: org.json.JSONException -> Lf
                r3 = 0
                r1.<init>(r2, r3)     // Catch: org.json.JSONException -> Lf
                goto L18
            Lf:
                c.t.m.g.q5 r0 = c.t.m.g.q5.w
                return r0
            L12:
                c.t.m.g.q5 r1 = r6.b
                c.t.m.g.q5 r1 = c.t.m.g.q5.r(r1)
            L18:
                int r2 = r6.f635c
                c.t.m.g.q5.h(r1, r2)
                java.lang.String r2 = r6.d
                r1.l(r2)
                android.location.Location r2 = r6.f636e
                c.t.m.g.q5.i(r1, r2)
                android.os.Bundle r2 = r6.f637f
                if (r2 == 0) goto L34
                android.os.Bundle r2 = c.t.m.g.q5.c(r1)
                android.os.Bundle r3 = r6.f637f
                r2.putAll(r3)
            L34:
                android.location.Location r2 = r6.f636e
                c.t.m.g.j1.b(r1, r2)
                android.os.Bundle r2 = c.t.m.g.q5.c(r1)
                java.lang.Long r3 = new java.lang.Long
                long r4 = c.t.m.g.u0.a
                r3.<init>(r4)
                java.lang.String r4 = "lastNetLocationTimeStampUseWifi"
                c.t.m.g.a3.a(r2, r4, r3, r0)
                android.os.Bundle r2 = c.t.m.g.q5.c(r1)
                java.lang.Long r3 = new java.lang.Long
                long r4 = c.t.m.g.u0.b
                r3.<init>(r4)
                java.lang.String r4 = "lastNetLocationTimeStampUseCellOnly"
                c.t.m.g.a3.a(r2, r4, r3, r0)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.q5.b.f():c.t.m.g.q5");
        }

        public b g(String str) {
            this.d = str;
            return this;
        }
    }

    public q5(int i2) {
        this.f634n = new Bundle(9);
        this.o = "network";
        this.p = "wifi";
        this.f629i = i2;
        this.r = SystemClock.elapsedRealtime();
        this.s = System.currentTimeMillis();
    }

    public /* synthetic */ q5(int i2, a aVar) {
        this(i2);
    }

    public q5(String str) {
        t3 t3Var;
        this.f634n = new Bundle(9);
        this.o = "network";
        this.p = "wifi";
        this.r = SystemClock.elapsedRealtime();
        this.s = System.currentTimeMillis();
        JSONObject jSONObject = new JSONObject(str);
        try {
            this.f627g = new d3(jSONObject.getJSONObject(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID));
            try {
                this.f628h = new v2(jSONObject.getJSONObject("indoorinfo"));
            } catch (Throwable unused) {
            }
            this.f631k = jSONObject.optString("bearing");
            this.f630j = jSONObject.optInt("fackgps", 0);
            long optLong = jSONObject.optLong(VerifyTracker.KEY_TIMESTAMP, System.currentTimeMillis());
            this.t = optLong;
            this.s = optLong;
            try {
                String optString = jSONObject.optString("icontrol");
                if (!TextUtils.isEmpty(optString)) {
                    this.f634n.putInt("icontrol", Integer.valueOf(optString.split(DYConstants.DY_REGEX_COMMA)[0]).intValue());
                }
            } catch (Exception unused2) {
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("details");
            if (optJSONObject != null) {
                try {
                    this.f633m = new n2(optJSONObject);
                } catch (JSONException e2) {
                    throw e2;
                }
            } else {
                JSONObject optJSONObject2 = jSONObject.optJSONObject("addrdesp");
                if (optJSONObject2 != null && optJSONObject2.has("detail")) {
                    this.f633m = new n2(optJSONObject2.optJSONObject("detail"));
                }
            }
            n2 n2Var = this.f633m;
            if (n2Var == null || (t3Var = n2Var.f562c) == null) {
                return;
            }
            this.f634n.putAll(t3Var.f691m);
        } catch (JSONException e3) {
            throw e3;
        }
    }

    public /* synthetic */ q5(String str, a aVar) {
        this(str);
    }

    public static /* synthetic */ Bundle c(q5 q5Var) {
        return q5Var.f634n;
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
        */
    public static /* synthetic */ c.t.m.g.q5 h(c.t.m.g.q5 r0, int r1) {
        /*
            r0.q(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.q5.h(c.t.m.g.q5, int):c.t.m.g.q5");
    }

    /*  JADX ERROR: NullPointerException in pass: MarkMethodsForInline
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:37)
        */
    public static /* synthetic */ c.t.m.g.q5 i(c.t.m.g.q5 r0, android.location.Location r1) {
        /*
            r0.g(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.t.m.g.q5.i(c.t.m.g.q5, android.location.Location):c.t.m.g.q5");
    }

    public static q5 j(q5 q5Var, q5 q5Var2) {
        if (q5Var != null && q5Var2 != null) {
            d3 d3Var = q5Var2.f627g;
            if (d3Var != null) {
                d3 d3Var2 = q5Var.f627g;
                if (d3Var2 == null) {
                    d3Var2 = new d3();
                }
                d3Var2.f349e = d3Var.f349e;
                d3Var2.f350f = d3Var.f350f;
                q5Var.f627g = d3Var2;
            }
            q5Var.f633m = n2.a(q5Var2.f633m);
        }
        return q5Var;
    }

    public static q5 k(q5 q5Var, boolean z) {
        String str;
        if (q5Var != null && (str = q5Var.f631k) != null && !z) {
            int i2 = 0;
            if (str != null && str.split(DYConstants.DY_REGEX_COMMA).length > 1) {
                i2 = Integer.parseInt(str.split(DYConstants.DY_REGEX_COMMA)[1]);
            }
            d3 d3Var = q5Var.f627g;
            if (d3Var != null) {
                try {
                    d3Var.d = (float) SoUtils.fun_r(d3Var.d, i2, -70);
                } catch (UnsatisfiedLinkError unused) {
                }
            }
        }
        return q5Var;
    }

    public static q5 s(q5 q5Var, int i2) {
        q5Var.u = i2;
        return q5Var;
    }

    public static q5 x(q5 q5Var) {
        q5 q5Var2 = new q5(-1);
        if (q5Var == null) {
            q5Var2.f627g = new d3();
        } else {
            q5Var2.f627g = d3.a(q5Var.f627g);
            q5Var2.f629i = q5Var.f629i;
            q5Var2.f631k = q5Var.f631k;
            q5Var2.f633m = n2.a(q5Var.f633m);
            if (q5Var.f634n.size() > 0) {
                q5Var2.f634n.putAll(q5Var.f634n);
            }
        }
        return q5Var2;
    }

    public static void z(q5 q5Var) {
        if (q5Var == w) {
            throw new JSONException("location failed");
        }
    }

    public final float a() {
        float max = (getFakeReason() & 1) != 0 ? Math.max(0.0f, 0.99f) : 0.0f;
        if ((getFakeReason() & 2) != 0) {
            max = Math.max(max, 0.8f);
        }
        if ((getFakeReason() & 4) != 0) {
            max = Math.max(max, 0.8f);
        }
        if ((getFakeReason() & 8) != 0) {
            max = Math.max(max, 0.9f);
        }
        if ((getFakeReason() & 16) != 0) {
            max = Math.max(max, 0.9f);
        }
        if ((getFakeReason() & 32) != 0) {
            max = Math.max(max, 0.8f);
        }
        return (getFakeReason() & 64) != 0 ? Math.max(max, 0.9f) : max;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public q5 f(long j2) {
        this.s = j2;
        return this;
    }

    public final q5 g(Location location) {
        this.q = location;
        return this;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getAccuracy() {
        d3 d3Var = this.f627g;
        if (d3Var != null) {
            return d3Var.d;
        }
        return 0.0f;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getAddress() {
        int i2 = this.f629i;
        if (i2 == 5) {
            return this.f634n.getString("addrdesp.name");
        }
        if (i2 == 3) {
            n2 n2Var = this.f633m;
            if (n2Var != null) {
                return n2Var.f562c.f690l;
            }
            return null;
        }
        d3 d3Var = this.f627g;
        if (d3Var != null) {
            return d3Var.f350f;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getAltitude() {
        d3 d3Var = this.f627g;
        if (d3Var != null) {
            return d3Var.f348c;
        }
        return 0.0d;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public Integer getAreaStat() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return Integer.valueOf(n2Var.a);
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getBearing() {
        Location location = this.q;
        if (location == null) {
            return 0.0f;
        }
        return location.getBearing();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCity() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f684f;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCityCode() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f682c;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getCityPhoneCode() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.d;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getCoordinateType() {
        return this.u;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getDirection() {
        Bundle bundle = this.f634n;
        if (bundle != null) {
            return bundle.getDouble("direction");
        }
        return Double.NaN;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getDistrict() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f685g;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public long getElapsedRealtime() {
        return this.r;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public Bundle getExtra() {
        return this.f634n;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getFakeProbability() {
        return a();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getFakeReason() {
        return this.v;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getGPSRssi() {
        Bundle extras;
        Location location = this.q;
        if (location == null || (extras = location.getExtras()) == null) {
            return 0;
        }
        return extras.getInt("rssi", 0);
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getIndoorBuildingFloor() {
        v2 v2Var = this.f628h;
        return v2Var != null ? v2Var.b : Constants.DEFAULT_UIN;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getIndoorBuildingId() {
        v2 v2Var = this.f628h;
        if (v2Var != null) {
            return v2Var.a;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getIndoorLocationType() {
        v2 v2Var = this.f628h;
        if (v2Var != null) {
            return v2Var.f720c;
        }
        return -1;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getLatitude() {
        d3 d3Var = this.f627g;
        if (d3Var != null) {
            return d3Var.a;
        }
        return 0.0d;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public double getLongitude() {
        d3 d3Var = this.f627g;
        if (d3Var != null) {
            return d3Var.b;
        }
        return 0.0d;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getName() {
        int i2 = this.f629i;
        if (i2 == 5) {
            return this.f634n.getString("addrdesp.name");
        }
        if (i2 == 3) {
            n2 n2Var = this.f633m;
            if (n2Var != null) {
                return n2Var.f562c.b;
            }
            return null;
        }
        d3 d3Var = this.f627g;
        if (d3Var != null) {
            return d3Var.f349e;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getNation() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.a;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int getNationCode() {
        return this.f632l;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public List<TencentPoi> getPoiList() {
        return this.f633m != null ? new ArrayList(this.f633m.b) : Collections.emptyList();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getProvider() {
        return this.o;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getProvince() {
        n2 n2Var = this.f633m;
        return n2Var != null ? n2Var.f562c.f683e : "";
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getSourceProvider() {
        return this.p;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public float getSpeed() {
        Location location = this.q;
        if (location == null) {
            return 0.0f;
        }
        return location.getSpeed();
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getStreet() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f688j;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getStreetNo() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f689k;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public long getTime() {
        return this.s;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getTown() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f686h;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getVillage() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f687i;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public String getadCode() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f682c;
        }
        return null;
    }

    @Override // com.tencent.map.geolocation.TencentLocation
    public int isMockGps() {
        return this.f630j;
    }

    public q5 l(String str) {
        this.o = str;
        return this;
    }

    public void n(double d, double d2) {
        d3 d3Var = this.f627g;
        double round = Math.round(d * 1000000.0d);
        Double.isNaN(round);
        d3Var.a = round / 1000000.0d;
        d3 d3Var2 = this.f627g;
        double round2 = Math.round(d2 * 1000000.0d);
        Double.isNaN(round2);
        d3Var2.b = round2 / 1000000.0d;
    }

    public void o(int i2) {
        this.f632l = i2;
    }

    public final q5 q(int i2) {
        this.f629i = i2;
        return this;
    }

    public String t() {
        n2 n2Var = this.f633m;
        if (n2Var != null) {
            return n2Var.f562c.f682c;
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TxLocation{level=");
        sb.append(this.f629i);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("name=");
        sb.append(getName());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("address=");
        sb.append(getAddress());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("provider=");
        sb.append(getProvider());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("latitude=");
        sb.append(getLatitude());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("longitude=");
        sb.append(getLongitude());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("altitude=");
        sb.append(getAltitude());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("accuracy=");
        sb.append(getAccuracy());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("sourceProvider=");
        sb.append(getSourceProvider());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("fakeReason=");
        sb.append(getFakeReason());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("fakeProbability=");
        sb.append(getFakeProbability());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("cityCode=");
        sb.append(getCityCode());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("areaStat=");
        sb.append(getAreaStat());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("nation=");
        sb.append(getNation());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("province=");
        sb.append(getProvince());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("city=");
        sb.append(getCity());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("district=");
        sb.append(getDistrict());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("street=");
        sb.append(getStreet());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("streetNo=");
        sb.append(getStreetNo());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("town=");
        sb.append(getTown());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("village=");
        sb.append(getVillage());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("bearing=");
        sb.append(getBearing());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("time=");
        sb.append(getTime());
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("poilist=[");
        Iterator<TencentPoi> it = getPoiList().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(DYConstants.DY_REGEX_COMMA);
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }

    public void v(Location location) {
        if (location == null || this.f627g == null) {
            return;
        }
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        double round = Math.round(latitude * 1000000.0d);
        Double.isNaN(round);
        double round2 = Math.round(longitude * 1000000.0d);
        Double.isNaN(round2);
        d3 d3Var = this.f627g;
        d3Var.a = round / 1000000.0d;
        d3Var.b = round2 / 1000000.0d;
        d3Var.f348c = location.getAltitude();
        this.f627g.d = location.getAccuracy();
    }

    public long w() {
        return this.t;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f629i);
        parcel.writeString(getProvider());
        parcel.writeDouble(getLatitude());
        parcel.writeDouble(getLongitude());
        parcel.writeDouble(getAccuracy());
        parcel.writeDouble(getAltitude());
        parcel.writeString(getAddress());
        parcel.writeString(getSourceProvider());
        parcel.writeString(getNation());
        parcel.writeString(getProvince());
        parcel.writeString(getCity());
        parcel.writeString(getDistrict());
        parcel.writeString(getStreet());
        parcel.writeString(getStreetNo());
        parcel.writeString(t());
        parcel.writeString(getName());
        parcel.writeLong(this.s);
        parcel.writeLong(this.t);
        parcel.writeBundle(this.f634n);
    }

    public void y(int i2) {
        String provider;
        if ("gps".equals(getProvider())) {
            if (i2 == 0) {
                this.p = "gps";
            }
            this.p = TencentLocation.FAKE;
        } else {
            if ("network".equals(getProvider())) {
                if (i2 == 0) {
                    provider = ((double) getAccuracy()) <= 150.0d ? "wifi" : "cell";
                }
                this.p = TencentLocation.FAKE;
            } else {
                provider = getProvider();
            }
            this.p = provider;
        }
        this.v = i2;
    }
}
