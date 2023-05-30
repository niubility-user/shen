package com.jingdong.app.mall.home.r.e.k;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class b extends com.jingdong.app.mall.home.r.e.b {
    private static final Map<String, Boolean> s = new ConcurrentHashMap();
    private static final SharedPreferences t = f.L("shake_lottie_info");
    private static final Paint u;
    private static final float v;
    private final boolean a;
    private final String b;

    /* renamed from: c  reason: collision with root package name */
    public String f10714c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f10715e;

    /* renamed from: f  reason: collision with root package name */
    private final String f10716f;

    /* renamed from: g  reason: collision with root package name */
    private String f10717g;

    /* renamed from: h  reason: collision with root package name */
    private String f10718h;

    /* renamed from: i  reason: collision with root package name */
    public String f10719i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f10720j;

    /* renamed from: k  reason: collision with root package name */
    public com.jingdong.app.mall.home.r.c.b f10721k;

    /* renamed from: l  reason: collision with root package name */
    private String f10722l;

    /* renamed from: m  reason: collision with root package name */
    public boolean f10723m;

    /* renamed from: n  reason: collision with root package name */
    private final JumpEntity f10724n;
    private final String o;
    private final String p;
    private final int q;
    private final int r;

    static {
        Paint paint = new Paint();
        u = paint;
        paint.setTextSize(30.0f);
        v = paint.measureText("\u6ee188");
    }

    public b(JDJSONObject jDJSONObject, String str, boolean z) {
        super(jDJSONObject);
        this.f10722l = "";
        this.f10716f = str;
        this.a = z;
        this.f10714c = getJsonString("id");
        this.d = getJsonString("name");
        this.f10715e = getJsonString("icon");
        this.f10720j = TextUtils.equals(getJsonString("isSub", "0"), "1");
        JumpEntity jumpEntity = (JumpEntity) getObject("jump", JumpEntity.class);
        this.f10724n = jumpEntity;
        this.b = getJsonString("channelId") + "_moreChannel";
        this.o = getJsonString("expoUrl");
        this.p = getJsonString("clkUrl");
        this.q = Math.max(0, getJsonInt("expoDays"));
        this.r = Math.max(0, getJsonInt("frozenDays"));
        this.f10723m = !"0".equals(getJsonString("redControl", "1"));
        s();
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jumpEntity != null ? jumpEntity.getSrvJson() : "");
        this.f10721k = c2;
        c2.a("labelword", this.f10722l);
    }

    public static void a(String str) {
        try {
            t.edit().putString(str, "").apply();
        } catch (Exception e2) {
            f.s0("shake_lottie_info", e2);
        }
    }

    public static String g(String str) {
        String string = t.getString(str, "");
        String str2 = "shake_lottie_info" + str;
        String str3 = "\u5f53\u524d\u66dd\u5149\u63a7\u5236\u4fe1\u606f" + string;
        return string;
    }

    private static boolean j(String str, String str2, int i2, int i3) {
        int i4;
        if (!TextUtils.isEmpty(str2) && i2 != 0 && i3 != 0) {
            String[] split = str2.split("#");
            String V = f.V();
            if (split.length < 2) {
                String str3 = "shake_lottie_info" + str;
                return true;
            }
            try {
                i4 = Integer.parseInt(split[1]);
            } catch (Exception e2) {
                f.s0("shake_lottie_info", e2);
                i4 = 0;
            }
            if (!TextUtils.isEmpty(split[0]) && split[0].equals(V)) {
                i4--;
            }
            if (i4 >= i2 + i3) {
                String str4 = "shake_lottie_info" + str;
                String str5 = "\u8d85\u8fc7\u603b\u5468\u671f   \u672c\u5730\u4fdd\u5b58\u65f6\u95f4\uff1a" + i4 + "   \u4e0b\u53d1N\uff1a" + i2 + "   \u4e0b\u53d1M" + i3;
                a(str);
                return true;
            } else if (i4 < i2) {
                String str6 = "shake_lottie_info" + str;
                String str7 = "\u8fd8\u5728\u66dd\u5149\u65f6\u95f4\u5185   \u672c\u5730\u4fdd\u5b58\u65f6\u95f4\uff1a" + i4 + "   \u4e0b\u53d1N\uff1a" + i2;
                return true;
            } else {
                v(str2, str);
                String str8 = "shake_lottie_info" + str;
                String str9 = "\u8fd8\u5728\u51bb\u7ed3\u5468\u671f\u5185   \u672c\u5730\u4fdd\u5b58\u65f6\u95f4\uff1a" + i4 + "   \u4e0b\u53d1N\uff1a" + i2;
                return false;
            }
        }
        String str10 = "shake_lottie_info" + str;
        return true;
    }

    private void s() {
        JDJSONArray jsonArr;
        if (this.a) {
            return;
        }
        Boolean bool = s.get(this.b);
        if ((bool == null || !bool.booleanValue()) && Build.VERSION.SDK_INT >= 21 && DPIUtil.getDensity() >= 2.0f && (jsonArr = getJsonArr("labelText")) != null && jsonArr.size() > 0) {
            if (jsonArr.size() == 1 && (n() || o())) {
                String string = jsonArr.getString(0);
                if (TextUtils.isEmpty(string) || f.Y(string) > 5.0f) {
                    return;
                }
                String str = this.b;
                if (j(str, g(str), this.q, this.r)) {
                    this.f10717g = string;
                    this.f10722l = string;
                }
            } else if (jsonArr.size() == 2 && l()) {
                String string2 = jsonArr.getString(0);
                String string3 = jsonArr.getString(1);
                if (TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                    return;
                }
                String str2 = this.b;
                if (j(str2, g(str2), this.q, this.r)) {
                    Paint paint = u;
                    float measureText = paint.measureText(string2);
                    float measureText2 = paint.measureText(string3);
                    float f2 = v;
                    if (measureText > f2 || measureText2 > f2) {
                        return;
                    }
                    this.f10717g = string2;
                    this.f10718h = string3;
                    this.f10722l = string2.concat("#").concat(string3);
                }
            }
        }
    }

    public static void v(String str, String str2) {
        String V = f.V();
        int i2 = 0;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split("#");
            if (split.length == 2) {
                if (split[0].equals(V)) {
                    return;
                }
                if (!TextUtils.isEmpty(split[1])) {
                    i2 = com.jingdong.app.mall.home.n.h.c.g(split[1]);
                }
            }
        }
        try {
            t.edit().putString(str2, V + "#" + (i2 + 1)).apply();
        } catch (Exception e2) {
            f.s0("shake_lottie_info", e2);
        }
    }

    public void b() {
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        this.f10721k.a("labelword", "");
        s.put(this.b, Boolean.TRUE);
    }

    public void c(boolean z) {
        if (z && r() && f.Y(this.f10717g) > 2.0f) {
            this.f10717g = "";
            this.f10721k.a("labelword", "");
        }
    }

    public String d() {
        return TextUtils.isEmpty(this.f10719i) ? "" : this.f10719i;
    }

    public String e() {
        return this.f10716f;
    }

    public String f() {
        String concat = this.f10714c.concat("#").concat(this.f10716f).concat("#").concat(this.f10717g);
        return !TextUtils.isEmpty(this.f10718h) ? concat.concat("#").concat(this.f10718h) : concat;
    }

    public JumpEntity getJump() {
        return this.f10724n;
    }

    public String h() {
        String str = this.f10717g;
        return str == null ? "" : str;
    }

    public String i() {
        String str = this.f10718h;
        return str == null ? "" : str;
    }

    public boolean k() {
        return this.a;
    }

    public boolean l() {
        return TextUtils.equals("2", this.f10716f);
    }

    public boolean m() {
        return n() || l();
    }

    public boolean n() {
        return TextUtils.equals("1", this.f10716f);
    }

    public boolean o() {
        return TextUtils.equals("0", this.f10716f);
    }

    public boolean p() {
        Boolean bool = s.get(this.b);
        boolean z = (bool == null || !bool.booleanValue()) & (!TextUtils.isEmpty(this.f10717g));
        return l() ? z & (true ^ TextUtils.isEmpty(this.f10718h)) : z;
    }

    public boolean q() {
        return p() && (n() || l());
    }

    public boolean r() {
        return p() && o();
    }

    public void t() {
        if (TextUtils.isEmpty(this.b)) {
            return;
        }
        a(this.b);
        Boolean bool = s.get(this.b);
        if (bool == null || !bool.booleanValue()) {
            new com.jingdong.app.mall.home.q.a("\u767e\u5b9d\u7bb1Icon\u70b9\u51fb", this.p).b();
        }
    }

    public void u() {
        if (p()) {
            String str = "shake_lottie_info" + this.b;
            String str2 = this.a + " \u662f\u5426\u5fae\u7f13\u5b58\u6570\u636e";
            d.k(d.c(this.b), this.b);
        }
        if (TextUtils.isEmpty(this.o) || !p()) {
            return;
        }
        new com.jingdong.app.mall.home.q.a("\u767e\u5b9d\u7bb1Icon\u66dd\u5149", true, this.o).b();
    }
}
