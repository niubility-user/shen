package com.jingdong.common.entity;

import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class ProvinceMode1 implements Serializable {
    public static final int DIRECT_WARE = 0;
    private static final String TAG = "ProvinceMode1";
    private static final long serialVersionUID = -3176190992875705661L;
    private ArrayList<CityMode1> children;
    private HashMap<Integer, Integer> childrenMap;
    public int id;
    public String name;

    private ProvinceMode1(JSONObjectProxy jSONObjectProxy, int i2, Object[] objArr) {
        if (i2 != 0) {
            return;
        }
        Product product = null;
        if (objArr != null && objArr.length > 0 && objArr[0] != null && (objArr[0] instanceof Product)) {
            product = (Product) objArr[0];
        }
        this.name = jSONObjectProxy.optString("name");
        this.id = jSONObjectProxy.optInt("idProvince");
        setChildren(CityMode1.toList(jSONObjectProxy.getJSONArrayOrNull("idCityes"), i2, new Object[]{this, product}));
    }

    public static ArrayList<ProvinceMode1> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        return toList(jSONArrayPoxy, i2, null);
    }

    public ArrayList<CityMode1> getChildren() {
        return this.children;
    }

    public Integer getCityMode1IndexById(int i2) {
        return this.childrenMap.get(Integer.valueOf(i2));
    }

    public void setChildren(ArrayList<CityMode1> arrayList) {
        this.children = arrayList;
        this.childrenMap = new HashMap<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            this.childrenMap.put(Integer.valueOf(arrayList.get(i2).id), Integer.valueOf(i2));
        }
    }

    public static ArrayList<ProvinceMode1> toList(JSONArrayPoxy jSONArrayPoxy, int i2, Object[] objArr) {
        ArrayList<ProvinceMode1> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<ProvinceMode1> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    arrayList2.add(new ProvinceMode1(jSONArrayPoxy.getJSONObject(i3), i2, objArr));
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
