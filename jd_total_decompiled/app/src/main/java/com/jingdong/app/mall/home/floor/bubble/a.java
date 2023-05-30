package com.jingdong.app.mall.home.floor.bubble;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {
    private static final String p = "a";
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f9244c;
    public boolean d;

    /* renamed from: e  reason: collision with root package name */
    public int f9245e;

    /* renamed from: f  reason: collision with root package name */
    public int f9246f;

    /* renamed from: g  reason: collision with root package name */
    public int f9247g;

    /* renamed from: h  reason: collision with root package name */
    public String f9248h;

    /* renamed from: i  reason: collision with root package name */
    public String f9249i;

    /* renamed from: j  reason: collision with root package name */
    public String f9250j;

    /* renamed from: k  reason: collision with root package name */
    public String f9251k;

    /* renamed from: l  reason: collision with root package name */
    private JDJSONObject f9252l;

    /* renamed from: m  reason: collision with root package name */
    private String f9253m;

    /* renamed from: n  reason: collision with root package name */
    private int f9254n;
    final CopyOnWriteArrayList<C0284a> a = new CopyOnWriteArrayList<>();
    private JSONArray o = com.jingdong.app.mall.home.r.c.b.d();

    /* renamed from: com.jingdong.app.mall.home.floor.bubble.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public static class C0284a {
        private String a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private CharSequence f9255c;
        public JumpEntity d;

        /* renamed from: e  reason: collision with root package name */
        private JSONObject f9256e;

        public C0284a(JDJSONObject jDJSONObject) {
            if (jDJSONObject == null) {
                return;
            }
            this.a = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "imgUrl", "");
            this.b = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "name", "");
            String jSONStringWithDefault = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "price", "");
            int indexOf = this.b.indexOf("#_#");
            if (indexOf >= 0 && !TextUtils.isEmpty(jSONStringWithDefault)) {
                String replace = this.b.replace("#_#", com.jingdong.app.mall.home.category.floor.feedssub.a.e(jSONStringWithDefault));
                this.b = replace;
                this.f9255c = a.i(replace, indexOf, 0.65f);
            } else {
                this.f9255c = this.b.replace("#_#", "");
            }
            try {
                this.d = (JumpEntity) JDJSON.parseObject(jDJSONObject.optString("jump", ""), JumpEntity.class);
                this.f9256e = new JSONObject(this.d.srvJson);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        public CharSequence b() {
            return this.f9255c;
        }

        public String c() {
            JSONObject jSONObject = this.f9256e;
            return jSONObject == null ? "" : jSONObject.toString();
        }

        public String d() {
            return this.a;
        }

        public boolean e() {
            return !TextUtils.isEmpty(this.f9255c);
        }
    }

    public a(String str, String str2, int i2) {
        this.f9253m = str2;
        this.f9254n = i2;
    }

    private String g(String str, String str2) {
        return CommonBase.getJdSharedPreferences().getString(str, str2);
    }

    public static SpannableString i(String str, int i2, float f2) {
        SpannableString spannableString = new SpannableString(str);
        if (TextUtils.isEmpty(str)) {
            return spannableString;
        }
        try {
            int length = str.length();
            if (i2 >= 0 && i2 < length) {
                spannableString.setSpan(new RelativeSizeSpan(f2), i2, i2 + 1, 17);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return spannableString;
    }

    private void n(String str, String str2) {
        CommonBase.getJdSharedPreferences().edit().putString(str, str2).apply();
    }

    public void a(C0284a c0284a) {
        if (c0284a == null || c0284a.f9256e == null) {
            return;
        }
        this.o.put(c0284a.f9256e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        float f2;
        int i2;
        long currentTimeMillis;
        int i3;
        if (this.b <= 0 || this.f9244c <= 0) {
            return false;
        }
        try {
            String[] split = TextUtils.split(g(this.f9251k, ""), "##");
            if (split.length > 1) {
                i2 = Integer.parseInt(split[0]) + 1;
                f2 = Float.parseFloat(split[1]);
            } else {
                f2 = 0.0f;
                i2 = 1;
            }
            if (OKLog.D) {
                f.r0(p, "\u6c14\u6ce1\u5df2\u5c55\u793a\u6b21\u6570: " + i2);
            }
            currentTimeMillis = System.currentTimeMillis();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (f2 > 0.0f && (i3 = this.f9244c) > 0 && (((float) currentTimeMillis) - f2) / 8.64E7f >= i3) {
            n(this.f9251k, "");
            return true;
        } else if (i2 > this.b) {
            return false;
        } else {
            n(this.f9251k, (i2 + "").concat("##").concat(currentTimeMillis + ""));
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        float f2;
        int i2;
        if (this.b > 0 && this.f9244c > 0) {
            try {
                String[] split = TextUtils.split(g(this.f9251k, ""), "##");
                if (split.length > 1) {
                    i2 = Integer.parseInt(split[0]);
                    f2 = Float.parseFloat(split[1]);
                } else {
                    f2 = 0.0f;
                    i2 = 0;
                }
                if (f2 <= 0.0f || this.f9244c <= 0 || (((float) System.currentTimeMillis()) - f2) / 8.64E7f < this.f9244c) {
                    return i2 < this.b;
                }
                n(this.f9251k, "");
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        n(this.f9251k, "");
    }

    public void e() {
        this.o = com.jingdong.app.mall.home.r.c.b.d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.a.clear();
    }

    public String h() {
        return this.o.length() > 0 ? this.o.toString() : "";
    }

    public void j(JDJSONObject jDJSONObject) {
        this.f9252l = jDJSONObject;
        this.f9251k = "jd_bubble_key_".concat(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "id", ""));
        this.b = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "expoUnclickCount", 0);
        int jSONIntWithDefault = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "statisticsTime", 0);
        this.f9244c = jSONIntWithDefault;
        this.f9244c = Math.max(jSONIntWithDefault, 1);
        this.d = 1 == JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "showCloseBtn", 0);
        int jSONIntWithDefault2 = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "appearTime", 0);
        this.f9245e = jSONIntWithDefault2;
        if (jSONIntWithDefault2 < 1) {
            jSONIntWithDefault2 = 5;
        }
        this.f9245e = jSONIntWithDefault2 * 1000;
        int jSONIntWithDefault3 = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "showInterval", 0);
        this.f9246f = jSONIntWithDefault3;
        if (jSONIntWithDefault3 < 1) {
            jSONIntWithDefault3 = 2;
        }
        this.f9246f = jSONIntWithDefault3 * 1000;
        int jSONIntWithDefault4 = JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "showTime", 5);
        this.f9247g = jSONIntWithDefault4;
        this.f9247g = (jSONIntWithDefault4 >= 2 ? jSONIntWithDefault4 : 5) * 1000;
        this.f9248h = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "expoLog", "");
        this.f9249i = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "clkLog", "");
        this.f9250j = JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "closeLog", "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return this.a.size() > 0 && !com.jingdong.app.mall.home.state.old.a.f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(Context context, JumpEntity jumpEntity) {
        if (jumpEntity != null) {
            JumpUtil.execJump(context, jumpEntity, this.f9254n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        JDJSONArray optJSONArray;
        int size;
        JDJSONObject jDJSONObject = this.f9252l;
        if (jDJSONObject == null || (optJSONArray = jDJSONObject.optJSONArray("templates")) == null || (size = optJSONArray.size()) <= 0) {
            return;
        }
        this.a.clear();
        for (int i2 = 0; i2 < size; i2++) {
            C0284a c0284a = new C0284a(optJSONArray.getJSONObject(i2));
            if (c0284a.e()) {
                this.a.add(c0284a);
            }
        }
    }

    public void o(String str, String str2) {
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", "", this.f9253m, com.jingdong.app.mall.home.r.c.a.f10642k, "", "", str2, null);
    }

    public void p(String str, String str2) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), str, "", this.f9253m, com.jingdong.app.mall.home.r.c.a.f10642k, "", str2, null);
    }
}
