package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class CityMode1 implements Serializable {
    public static final int DIRECT_WARE = 0;
    private static final String TAG = "CityMode1";
    private static final long serialVersionUID = -402570607546268634L;
    public int id;
    public String name;
    private ProvinceMode1 parent;
    public long productId;

    private CityMode1(JSONObjectProxy jSONObjectProxy, int i2, Object[] objArr) {
        if (i2 != 0) {
            return;
        }
        Product product = null;
        ProvinceMode1 provinceMode1 = (objArr == null || objArr.length <= 0 || objArr[0] == null || !(objArr[0] instanceof ProvinceMode1)) ? null : (ProvinceMode1) objArr[0];
        if (objArr != null && objArr.length > 1 && objArr[1] != null && (objArr[1] instanceof Product)) {
            product = (Product) objArr[1];
        }
        this.name = jSONObjectProxy.optString("name");
        this.id = jSONObjectProxy.optInt("idCity");
        this.productId = jSONObjectProxy.optLong(PdMtaUtil.PARAM_KEY_SKUID);
        setParent(provinceMode1);
        if (product != null) {
            product.putInCityMode1Map(Long.valueOf(this.productId), this);
        }
    }

    public static ArrayList<CityMode1> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        return toList(jSONArrayPoxy, i2, null);
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public ProvinceMode1 getParent() {
        return this.parent;
    }

    public void setParent(ProvinceMode1 provinceMode1) {
        this.parent = provinceMode1;
    }

    public static ArrayList<CityMode1> toList(JSONArrayPoxy jSONArrayPoxy, int i2, Object[] objArr) {
        ArrayList<CityMode1> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<CityMode1> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    CityMode1 cityMode1 = new CityMode1(jSONArrayPoxy.getJSONObject(i3), i2, objArr);
                    if (!TextUtils.isEmpty(cityMode1.getName())) {
                        arrayList2.add(cityMode1);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    OKLog.e(TAG, e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }
}
