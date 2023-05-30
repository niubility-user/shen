package com.jingdong.app.mall.home.r.e.k;

import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public abstract class d extends com.jingdong.app.mall.home.r.e.b {

    /* renamed from: i  reason: collision with root package name */
    private static final Paint f10728i;

    /* renamed from: j  reason: collision with root package name */
    private static final float f10729j;

    /* renamed from: k  reason: collision with root package name */
    private static final SharedPreferences f10730k;
    protected String a;
    protected final String b;

    /* renamed from: c  reason: collision with root package name */
    protected String f10731c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    protected final String f10732e;

    /* renamed from: f  reason: collision with root package name */
    protected final String f10733f;

    /* renamed from: g  reason: collision with root package name */
    private final int f10734g;

    /* renamed from: h  reason: collision with root package name */
    private final int f10735h;

    static {
        Paint paint = new Paint();
        f10728i = paint;
        f10730k = f.L("shake_lottie_info");
        paint.setTextSize(30.0f);
        f10729j = paint.measureText("\u6ee188");
    }

    public d(JDJSONObject jDJSONObject) {
        super(jDJSONObject);
        this.a = "";
        this.b = getJsonString("channelId") + "_moreChannel";
        this.f10732e = getJsonString("expoLog");
        this.f10733f = getJsonString("clkLog");
        this.f10734g = Math.max(0, getJsonInt("expoDays"));
        this.f10735h = Math.max(0, getJsonInt("frozenDays"));
    }

    public static void a(String str) {
        try {
            f10730k.edit().putString(str, "").apply();
        } catch (Exception e2) {
            f.s0("shake_lottie_info", e2);
        }
    }

    public static String c(String str) {
        String string = f10730k.getString(str, "");
        String str2 = "shake_lottie_info" + str;
        String str3 = "\u5f53\u524d\u66dd\u5149\u63a7\u5236\u4fe1\u606f" + string;
        return string;
    }

    public static boolean f(String str, String str2, int i2, int i3) {
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
                k(str2, str);
                String str8 = "shake_lottie_info" + str;
                String str9 = "\u8fd8\u5728\u51bb\u7ed3\u5468\u671f\u5185   \u672c\u5730\u4fdd\u5b58\u65f6\u95f4\uff1a" + i4 + "   \u4e0b\u53d1N\uff1a" + i2;
                return false;
            }
        }
        String str10 = "shake_lottie_info" + str;
        return true;
    }

    public static void k(String str, String str2) {
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
            f10730k.edit().putString(str2, V + "#" + (i2 + 1)).apply();
        } catch (Exception e2) {
            f.s0("shake_lottie_info", e2);
        }
    }

    public abstract String b();

    public String d() {
        return this.f10731c;
    }

    public String e() {
        return this.d;
    }

    public void g(boolean z, boolean z2) {
        JDJSONArray jsonArr;
        if (Build.VERSION.SDK_INT < 21 || DPIUtil.getDensity() < 2.0f || (jsonArr = getJsonArr("labelText")) == null || jsonArr.size() <= 0) {
            return;
        }
        String string = jsonArr.getString(0);
        String string2 = jsonArr.size() > 1 ? jsonArr.getString(1) : "";
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
            return;
        }
        String str = this.b;
        if (f(str, c(str), this.f10734g, this.f10735h)) {
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(string2)) {
                if (!z2 || z || f.Y(string) > 5.0f) {
                    return;
                }
                this.f10731c = string;
                this.a = string;
            } else if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || !z) {
            } else {
                if (TextUtils.isEmpty(string)) {
                    string = string2;
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = string;
                }
                Paint paint = f10728i;
                float measureText = paint.measureText(string);
                float measureText2 = paint.measureText(string2);
                if (measureText > 0.0f && measureText <= f10729j) {
                    this.f10731c = string;
                }
                if (measureText2 > 0.0f && measureText2 <= f10729j) {
                    this.d = string2;
                }
                if (j()) {
                    this.a = string.concat("#").concat(string2);
                }
            }
        }
    }

    public boolean h() {
        return i() || j();
    }

    public boolean i() {
        return !TextUtils.isEmpty(this.f10731c) && TextUtils.isEmpty(this.d);
    }

    public boolean j() {
        return (TextUtils.isEmpty(this.f10731c) || TextUtils.isEmpty(this.d)) ? false : true;
    }
}
