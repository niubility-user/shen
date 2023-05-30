package c.t.m.g;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class t3 {

    /* renamed from: n  reason: collision with root package name */
    public static final t3 f681n = new t3();
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f682c;
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public String f683e;

    /* renamed from: f  reason: collision with root package name */
    public String f684f;

    /* renamed from: g  reason: collision with root package name */
    public String f685g;

    /* renamed from: h  reason: collision with root package name */
    public String f686h;

    /* renamed from: i  reason: collision with root package name */
    public String f687i;

    /* renamed from: j  reason: collision with root package name */
    public String f688j;

    /* renamed from: k  reason: collision with root package name */
    public String f689k;

    /* renamed from: l  reason: collision with root package name */
    public String f690l;

    /* renamed from: m  reason: collision with root package name */
    public final Bundle f691m;

    public t3() {
        this.f691m = new Bundle();
    }

    public t3(t3 t3Var) {
        Bundle bundle = new Bundle();
        this.f691m = bundle;
        if (t3Var.f691m.size() > 0) {
            bundle.putAll(t3Var.f691m);
            return;
        }
        this.a = t3Var.a;
        this.b = t3Var.b;
        this.f682c = t3Var.f682c;
        this.d = t3Var.d;
        this.f683e = t3Var.f683e;
        this.f684f = t3Var.f684f;
        this.f685g = t3Var.f685g;
        this.f686h = t3Var.f686h;
        this.f687i = t3Var.f687i;
        this.f688j = t3Var.f688j;
        this.f689k = t3Var.f689k;
        this.f690l = t3Var.f690l;
    }

    public t3(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        this.f691m = bundle;
        if (jSONObject.has("admin_level_1")) {
            String optString = jSONObject.optString("nation");
            String optString2 = jSONObject.optString("admin_level_1");
            String optString3 = jSONObject.optString("admin_level_2");
            String optString4 = jSONObject.optString("admin_level_3");
            String optString5 = jSONObject.optString("locality");
            String optString6 = jSONObject.optString("sublocality");
            String optString7 = jSONObject.optString("route");
            bundle.putString("nation", optString);
            bundle.putString("admin_level_1", optString2);
            bundle.putString("admin_level_2", optString3);
            bundle.putString("admin_level_3", optString4);
            bundle.putString("locality", optString5);
            bundle.putString("sublocality", optString6);
            bundle.putString("route", optString7);
            return;
        }
        this.b = jSONObject.optString("name", null);
        this.f682c = jSONObject.optString("code", null);
        this.d = jSONObject.optString("pncode", null);
        this.a = jSONObject.optString("nation", null);
        this.f683e = jSONObject.optString("province", null);
        this.f684f = jSONObject.optString("city", null);
        this.f685g = jSONObject.optString("district", null);
        this.f686h = jSONObject.optString("town", null);
        this.f687i = jSONObject.optString("village", null);
        this.f688j = jSONObject.optString("street", null);
        this.f689k = jSONObject.optString("street_no", null);
        String optString8 = jSONObject.optString("mergedname", null);
        String optString9 = jSONObject.optString("mergedaddr", null);
        if (!TextUtils.isEmpty(optString8)) {
            this.b = optString8;
        }
        if (TextUtils.isEmpty(optString9)) {
            return;
        }
        this.f690l = optString9;
    }

    public static t3 a(t3 t3Var) {
        if (t3Var == null) {
            return null;
        }
        return new t3(t3Var);
    }

    public String toString() {
        return "SubnationData{name=" + this.b + DYConstants.DY_REGEX_COMMA + "address=" + this.f690l + DYConstants.DY_REGEX_COMMA + "code=" + this.f682c + DYConstants.DY_REGEX_COMMA + "phCode=" + this.d + DYConstants.DY_REGEX_COMMA + "nation=" + this.a + DYConstants.DY_REGEX_COMMA + "province=" + this.f683e + DYConstants.DY_REGEX_COMMA + "city=" + this.f684f + DYConstants.DY_REGEX_COMMA + "district=" + this.f685g + DYConstants.DY_REGEX_COMMA + "town=" + this.f686h + DYConstants.DY_REGEX_COMMA + "village=" + this.f687i + DYConstants.DY_REGEX_COMMA + "street=" + this.f688j + DYConstants.DY_REGEX_COMMA + "street_no=" + this.f689k + DYConstants.DY_REGEX_COMMA + "bundle" + this.f691m + DYConstants.DY_REGEX_COMMA + "}";
    }
}
