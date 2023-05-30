package c.t.m.g;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class g5 {
    public int a;
    public String b;

    /* renamed from: c */
    public String f445c;
    public String d;

    /* renamed from: e */
    public String f446e;

    /* renamed from: f */
    public String f447f;

    /* renamed from: g */
    public String f448g;

    /* renamed from: h */
    public long f449h;

    /* renamed from: i */
    public String f450i = "";

    /* renamed from: j */
    public volatile long f451j = 0;

    /* renamed from: k */
    public volatile long f452k = 0;

    /* renamed from: l */
    public volatile long f453l = 0;

    /* renamed from: m */
    public volatile long f454m = 0;

    /* renamed from: n */
    public String f455n;

    public String A() {
        return o.a(this.f446e);
    }

    public void B(String str) {
    }

    public String C() {
        return this.f450i;
    }

    public void D(String str) {
        this.f447f = str;
    }

    public long E() {
        return this.f454m;
    }

    public String F() {
        return u4.c();
    }

    public int G() {
        return this.a;
    }

    public String H() {
        String c2 = f0.c("LocationSDK", "location_device_id", "");
        return (TextUtils.isEmpty(this.f445c) || "0123456789ABCDEF".equals(this.f445c)) ? c2 : this.f445c;
    }

    public String I() {
        return o.a(this.d);
    }

    public long J() {
        return this.f451j;
    }

    @Nullable
    public String K() {
        return this.f447f;
    }

    public long L() {
        return this.f449h;
    }

    public String a() {
        if (TextUtils.isEmpty(this.f455n)) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(u.a)) {
                sb.append(u.a);
                sb.append(CartConstant.KEY_YB_INFO_LINK);
            }
            sb.append(u4.c());
            this.f455n = c1.a(sb.toString());
        }
        return this.f455n;
    }

    public void b(int i2) {
        this.a = i2;
    }

    public void c(long j2) {
        this.f453l = j2;
    }

    public void d(long j2, boolean z) {
        this.f449h = Math.max(y2.f().l("min_wifi_scan_interval"), j2);
        if (z) {
            this.f449h = Final.FIVE_SECOND;
        }
    }

    public void e(String str) {
        this.f448g = str;
    }

    public void f(boolean z) {
    }

    public String g() {
        return z3.a();
    }

    public void h(int i2) {
    }

    public void i(long j2) {
        this.f452k = j2;
    }

    public void j(String str) {
        this.b = str;
    }

    public void k(boolean z) {
    }

    public String l() {
        return this.f448g;
    }

    public void m(long j2) {
        this.f454m = j2;
    }

    public void n(String str) {
    }

    public void o(boolean z) {
    }

    public String p() {
        return "0123456789ABCDEF";
    }

    public void q(long j2) {
        this.f451j = j2;
    }

    public void r(String str) {
        this.f446e = str;
    }

    public String s() {
        return this.b;
    }

    public void t(String str) {
        this.f450i = str;
    }

    public String u() {
        HashMap hashMap = new HashMap();
        hashMap.put("imei", "");
        hashMap.put("imsi", "");
        hashMap.put(SignUpTable.TB_COLUMN_QQ, H());
        hashMap.put(Constant.KEY_MAC, "");
        hashMap.put("qimei", u.a);
        hashMap.put("q16", u.b);
        hashMap.put("q36", u.f702c);
        hashMap.put("idfa", ";" + F());
        hashMap.put("idfv", TextUtils.isEmpty(F()) ? g() : "");
        return new JSONObject(hashMap).toString();
    }

    public void v(String str) {
    }

    public long w() {
        return this.f453l;
    }

    public void x(String str) {
        this.f445c = str;
    }

    public long y() {
        return this.f452k;
    }

    public void z(String str) {
        this.d = str;
    }
}
