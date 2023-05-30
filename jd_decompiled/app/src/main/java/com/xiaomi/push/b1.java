package com.xiaomi.push;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.common.widget.custom.livewidget.bean.VideoPerfEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class b1 {
    public String a;
    private long b;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f18463e;

    /* renamed from: f  reason: collision with root package name */
    public String f18464f;

    /* renamed from: g  reason: collision with root package name */
    public String f18465g;

    /* renamed from: h  reason: collision with root package name */
    public String f18466h;

    /* renamed from: i  reason: collision with root package name */
    public String f18467i;

    /* renamed from: j  reason: collision with root package name */
    protected String f18468j;

    /* renamed from: k  reason: collision with root package name */
    private String f18469k;

    /* renamed from: c  reason: collision with root package name */
    private ArrayList<k1> f18462c = new ArrayList<>();

    /* renamed from: l  reason: collision with root package name */
    private double f18470l = 0.1d;

    /* renamed from: m  reason: collision with root package name */
    private long f18471m = 86400000;

    public b1(String str) {
        this.a = "";
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.b = System.currentTimeMillis();
        this.f18462c.add(new k1(str, -1));
        this.a = f1.d();
        this.d = str;
    }

    private synchronized void v(String str) {
        Iterator<k1> it = this.f18462c.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().f18790h, str)) {
                it.remove();
            }
        }
    }

    public synchronized b1 a(JSONObject jSONObject) {
        this.a = jSONObject.optString("net");
        this.f18471m = jSONObject.getLong(RemoteMessageConst.TTL);
        this.f18470l = jSONObject.getDouble("pct");
        this.b = jSONObject.getLong("ts");
        this.f18464f = jSONObject.optString("city");
        this.f18463e = jSONObject.optString(VideoPerfEntity.FIELD_PRV);
        this.f18467i = jSONObject.optString("cty");
        this.f18465g = jSONObject.optString("isp");
        this.f18466h = jSONObject.optString("ip");
        this.d = jSONObject.optString("host");
        this.f18468j = jSONObject.optString("xf");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            k1 k1Var = new k1();
            k1Var.b(jSONArray.getJSONObject(i2));
            i(k1Var);
        }
        return this;
    }

    public synchronized String b() {
        if (!TextUtils.isEmpty(this.f18469k)) {
            return this.f18469k;
        } else if (TextUtils.isEmpty(this.f18465g)) {
            return "hardcode_isp";
        } else {
            String g2 = p0.g(new String[]{this.f18465g, this.f18463e, this.f18464f, this.f18467i, this.f18466h}, CartConstant.KEY_YB_INFO_LINK);
            this.f18469k = g2;
            return g2;
        }
    }

    public synchronized ArrayList<String> c() {
        return e(false);
    }

    public ArrayList<String> d(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty.");
        }
        URL url = new URL(str);
        if (TextUtils.equals(url.getHost(), this.d)) {
            ArrayList<String> arrayList = new ArrayList<>();
            Iterator<String> it = e(true).iterator();
            while (it.hasNext()) {
                d1 b = d1.b(it.next(), url.getPort());
                arrayList.add(new URL(url.getProtocol(), b.c(), b.a(), url.getFile()).toString());
            }
            return arrayList;
        }
        throw new IllegalArgumentException("the url is not supported by the fallback");
    }

    public synchronized ArrayList<String> e(boolean z) {
        ArrayList<String> arrayList;
        String substring;
        int size = this.f18462c.size();
        k1[] k1VarArr = new k1[size];
        this.f18462c.toArray(k1VarArr);
        Arrays.sort(k1VarArr);
        arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < size; i2++) {
            k1 k1Var = k1VarArr[i2];
            if (z) {
                substring = k1Var.f18790h;
            } else {
                int indexOf = k1Var.f18790h.indexOf(":");
                substring = indexOf != -1 ? k1Var.f18790h.substring(0, indexOf) : k1Var.f18790h;
            }
            arrayList.add(substring);
        }
        return arrayList;
    }

    public synchronized JSONObject f() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("net", this.a);
        jSONObject.put(RemoteMessageConst.TTL, this.f18471m);
        jSONObject.put("pct", this.f18470l);
        jSONObject.put("ts", this.b);
        jSONObject.put("city", this.f18464f);
        jSONObject.put(VideoPerfEntity.FIELD_PRV, this.f18463e);
        jSONObject.put("cty", this.f18467i);
        jSONObject.put("isp", this.f18465g);
        jSONObject.put("ip", this.f18466h);
        jSONObject.put("host", this.d);
        jSONObject.put("xf", this.f18468j);
        JSONArray jSONArray = new JSONArray();
        Iterator<k1> it = this.f18462c.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().c());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public void g(double d) {
        this.f18470l = d;
    }

    public void h(long j2) {
        if (j2 > 0) {
            this.f18471m = j2;
            return;
        }
        throw new IllegalArgumentException("the duration is invalid " + j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void i(k1 k1Var) {
        v(k1Var.f18790h);
        this.f18462c.add(k1Var);
    }

    public synchronized void j(String str) {
        i(new k1(str));
    }

    public void k(String str, int i2, long j2, long j3, Exception exc) {
        n(str, new a1(i2, j2, j3, exc));
    }

    public void l(String str, long j2, long j3) {
        try {
            s(new URL(str).getHost(), j2, j3);
        } catch (MalformedURLException unused) {
        }
    }

    public void m(String str, long j2, long j3, Exception exc) {
        try {
            t(new URL(str).getHost(), j2, j3, exc);
        } catch (MalformedURLException unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        r1.d(r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void n(java.lang.String r4, com.xiaomi.push.a1 r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.ArrayList<com.xiaomi.push.k1> r0 = r3.f18462c     // Catch: java.lang.Throwable -> L20
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L20
        L7:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L20
            if (r1 == 0) goto L1e
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L20
            com.xiaomi.push.k1 r1 = (com.xiaomi.push.k1) r1     // Catch: java.lang.Throwable -> L20
            java.lang.String r2 = r1.f18790h     // Catch: java.lang.Throwable -> L20
            boolean r2 = android.text.TextUtils.equals(r4, r2)     // Catch: java.lang.Throwable -> L20
            if (r2 == 0) goto L7
            r1.d(r5)     // Catch: java.lang.Throwable -> L20
        L1e:
            monitor-exit(r3)
            return
        L20:
            r4 = move-exception
            monitor-exit(r3)
            goto L24
        L23:
            throw r4
        L24:
            goto L23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.b1.n(java.lang.String, com.xiaomi.push.a1):void");
    }

    public synchronized void o(String[] strArr) {
        int i2;
        int size = this.f18462c.size() - 1;
        while (true) {
            i2 = 0;
            if (size < 0) {
                break;
            }
            int length = strArr.length;
            while (true) {
                if (i2 < length) {
                    if (TextUtils.equals(this.f18462c.get(size).f18790h, strArr[i2])) {
                        this.f18462c.remove(size);
                        break;
                    }
                    i2++;
                }
            }
            size--;
        }
        Iterator<k1> it = this.f18462c.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            int i4 = it.next().f18792j;
            if (i4 > i3) {
                i3 = i4;
            }
        }
        while (i2 < strArr.length) {
            i(new k1(strArr[i2], (strArr.length + i3) - i2));
            i2++;
        }
    }

    public boolean p() {
        return TextUtils.equals(this.a, f1.d());
    }

    public boolean q(b1 b1Var) {
        return TextUtils.equals(this.a, b1Var.a);
    }

    public void r(String str) {
    }

    public void s(String str, long j2, long j3) {
        k(str, 0, j2, j3, null);
    }

    public void t(String str, long j2, long j3, Exception exc) {
        k(str, -1, j2, j3, exc);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append(b());
        Iterator<k1> it = this.f18462c.iterator();
        while (it.hasNext()) {
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            sb.append(it.next().toString());
        }
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        return sb.toString();
    }

    public boolean u() {
        return System.currentTimeMillis() - this.b < this.f18471m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean w() {
        long j2 = this.f18471m;
        if (864000000 >= j2) {
            j2 = 864000000;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j3 = this.b;
        return currentTimeMillis - j3 > j2 || (currentTimeMillis - j3 > this.f18471m && this.a.startsWith("WIFI-"));
    }
}
