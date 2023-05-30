package c.t.m.g;

import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentPoi;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class n2 {
    public int a;
    public final ArrayList<TencentPoi> b = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    public t3 f562c;

    public n2() {
    }

    public n2(JSONObject jSONObject) {
        this.a = jSONObject.optInt("stat");
        if (jSONObject.has("subnation")) {
            this.f562c = new t3(jSONObject.optJSONObject("subnation"));
        } else if (jSONObject.has("results")) {
            this.f562c = b(jSONObject.optJSONArray("results"));
        } else {
            this.f562c = t3.f681n;
            new StringBuilder("DetailsData: unknown json ").append(jSONObject.toString());
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("poilist");
        if (optJSONArray != null) {
            try {
                int length = optJSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    this.b.add(new l3(optJSONArray.getJSONObject(i2)));
                }
            } catch (JSONException unused) {
            }
        }
    }

    public static n2 a(n2 n2Var) {
        if (n2Var == null) {
            return null;
        }
        n2 n2Var2 = new n2();
        n2Var2.a = n2Var.a;
        n2Var2.f562c = t3.a(n2Var.f562c);
        Iterator<TencentPoi> it = n2Var.b.iterator();
        while (it.hasNext()) {
            n2Var2.b.add(new l3(it.next()));
        }
        return n2Var2;
    }

    public final t3 b(@Nullable JSONArray jSONArray) {
        t3 a;
        JSONObject optJSONObject;
        if (jSONArray == null || (a = t3.a(t3.f681n)) == null) {
            return null;
        }
        int length = jSONArray.length();
        if (length > 0 && (optJSONObject = jSONArray.optJSONObject(0)) != null) {
            a.a = optJSONObject.optString(PersonalConstants.ICON_STYLE_N, null);
            a.f683e = optJSONObject.optString("p", null);
            a.f684f = optJSONObject.optString("c", null);
            a.f685g = optJSONObject.optString("d", null);
            a.f682c = optJSONObject.optString("adcode", null);
        }
        if (length > 1) {
            JSONObject optJSONObject2 = jSONArray.optJSONObject(1);
            a.f691m.putString("addrdesp.name", optJSONObject2.optString("address_name"));
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("landmark");
            JSONObject optJSONObject4 = optJSONObject2.optJSONObject("second_landmark");
            if (optJSONObject3 != null) {
                a.f691m.putParcelable(TencentLocation.EXTRA_ADDRDESP_LANDMARK, new k5(optJSONObject3));
            }
            if (optJSONObject4 != null) {
                a.f691m.putParcelable(TencentLocation.EXTRA_ADDRDESP_SECOND_LANDMARK, new k5(optJSONObject4));
            }
        }
        if (length > 2) {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            for (int i2 = 2; i2 < length; i2++) {
                k5 k5Var = new k5(jSONArray.optJSONObject(i2));
                arrayList.add(k5Var);
                if ("ST".equals(k5Var.f529h)) {
                    a.f688j = k5Var.f528g;
                } else if ("ST_NO".equals(k5Var.f529h)) {
                    a.f689k = k5Var.f528g;
                }
            }
            a.f691m.putParcelableArrayList(TencentLocation.EXTRA_ADDRDESP_OTHERS_RESULTS, arrayList);
        }
        return a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DetailsData{subnation=");
        sb.append(this.f562c);
        sb.append(DYConstants.DY_REGEX_COMMA);
        sb.append("poilist=[");
        Iterator<TencentPoi> it = this.b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(DYConstants.DY_REGEX_COMMA);
        }
        sb.append("]");
        sb.append("}");
        return sb.toString();
    }
}
