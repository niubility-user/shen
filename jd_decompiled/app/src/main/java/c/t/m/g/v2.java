package c.t.m.g;

import com.tencent.connect.common.Constants;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class v2 {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public int f720c;

    public v2(JSONObject jSONObject) {
        this.a = jSONObject.optString("bid", null);
        this.b = jSONObject.optString("floor", Constants.DEFAULT_UIN);
        this.f720c = jSONObject.optInt("type", -1);
    }
}
