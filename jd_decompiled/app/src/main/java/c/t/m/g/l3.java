package c.t.m.g;

import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.geolocation.TencentPoi;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class l3 implements TencentPoi {
    public String a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public String f541c;
    public double d;

    /* renamed from: e  reason: collision with root package name */
    public String f542e;

    /* renamed from: f  reason: collision with root package name */
    public double f543f;

    /* renamed from: g  reason: collision with root package name */
    public double f544g;

    /* renamed from: h  reason: collision with root package name */
    public String f545h;

    public l3(TencentPoi tencentPoi) {
        this.a = tencentPoi.getName();
        this.b = tencentPoi.getAddress();
        this.f541c = tencentPoi.getCatalog();
        this.d = tencentPoi.getDistance();
        this.f542e = tencentPoi.getUid();
        this.f543f = tencentPoi.getLatitude();
        this.f544g = tencentPoi.getLongitude();
        this.f545h = tencentPoi.getDirection();
    }

    public l3(JSONObject jSONObject) {
        b(jSONObject);
    }

    public final void a(JSONObject jSONObject) {
        this.f545h = jSONObject.optString("direction", "");
        if (Double.isNaN(this.f543f)) {
            this.f543f = jSONObject.optDouble("pointy");
        }
        if (Double.isNaN(this.f544g)) {
            this.f544g = jSONObject.optDouble("pointx");
        }
    }

    public final void b(JSONObject jSONObject) {
        this.a = jSONObject.optString("name");
        this.b = jSONObject.optString(SignUpTable.TB_COLUMN_ADDR);
        this.f541c = jSONObject.optString("catalog");
        this.d = jSONObject.optDouble(CartConstant.KEY_DIST);
        this.f542e = jSONObject.optString("uid");
        this.f543f = jSONObject.optDouble(PdLVBody.LATITUDE);
        this.f544g = jSONObject.optDouble(PdLVBody.LONGITUDE);
        a(jSONObject);
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public String getAddress() {
        return this.b;
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public String getCatalog() {
        return this.f541c;
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public String getDirection() {
        return this.f545h;
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public double getDistance() {
        return this.d;
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public double getLatitude() {
        return this.f543f;
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public double getLongitude() {
        return this.f544g;
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public String getName() {
        return this.a;
    }

    @Override // com.tencent.map.geolocation.TencentPoi
    public String getUid() {
        return this.f542e;
    }

    public String toString() {
        return "PoiData{name=" + this.a + DYConstants.DY_REGEX_COMMA + "addr=" + this.b + DYConstants.DY_REGEX_COMMA + "catalog=" + this.f541c + DYConstants.DY_REGEX_COMMA + "dist=" + this.d + DYConstants.DY_REGEX_COMMA + "latitude=" + this.f543f + DYConstants.DY_REGEX_COMMA + "longitude=" + this.f544g + DYConstants.DY_REGEX_COMMA + "direction=" + this.f545h + DYConstants.DY_REGEX_COMMA + "}";
    }
}
