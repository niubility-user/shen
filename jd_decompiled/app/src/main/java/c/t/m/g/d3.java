package c.t.m.g;

import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.database.table.SignUpTable;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d3 {
    public double a;
    public double b;

    /* renamed from: c  reason: collision with root package name */
    public double f348c;
    public float d;

    /* renamed from: e  reason: collision with root package name */
    public String f349e;

    /* renamed from: f  reason: collision with root package name */
    public String f350f;

    public d3() {
    }

    public d3(JSONObject jSONObject) {
        this.a = jSONObject.optDouble(PdLVBody.LATITUDE, 0.0d);
        this.b = jSONObject.optDouble(PdLVBody.LONGITUDE, 0.0d);
        this.f348c = jSONObject.optDouble("altitude", 0.0d);
        this.d = (float) jSONObject.optDouble("accuracy", 0.0d);
        if (jSONObject.optInt("type", -3) == 2) {
            u0.b = System.currentTimeMillis();
        }
        this.f349e = jSONObject.optString("name", null);
        this.f350f = jSONObject.optString(SignUpTable.TB_COLUMN_ADDR, null);
    }

    public static d3 a(d3 d3Var) {
        d3 d3Var2 = new d3();
        if (d3Var != null) {
            d3Var2.a = d3Var.a;
            d3Var2.b = d3Var.b;
            d3Var2.f348c = d3Var.f348c;
            d3Var2.d = d3Var.d;
            d3Var2.f349e = d3Var.f349e;
            d3Var2.f350f = d3Var.f350f;
        }
        return d3Var2;
    }
}
