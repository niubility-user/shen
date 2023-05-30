package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.settlement.AddressOverSea;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class AddressBaseMode extends AddressOverSea implements Serializable {
    public static final int DIRECT_WARE = 0;
    public static final int STYLE_TYPE_DEFAULT = 0;
    public static final int STYLE_TYPE_RED = 1;
    private static final String TAG = "AddressBaseMode";
    private static final long serialVersionUID = -2561495656353479447L;
    public String action;
    public String flag;
    public String functionId;
    public String id;
    public String name;
    public String parentId;
    public String parentName;
    public String skuId;
    public int style = 0;

    public AddressBaseMode() {
    }

    private boolean parserOverSea(JSONObjectProxy jSONObjectProxy) {
        String optString;
        return (jSONObjectProxy == null || (optString = jSONObjectProxy.optString("overseas")) == null || !optString.equals("1")) ? false : true;
    }

    public static ArrayList<AddressBaseMode> toList(JSONArrayPoxy jSONArrayPoxy, int i2) {
        return toList(jSONArrayPoxy, i2, (Object[]) null);
    }

    public String getAction() {
        return this.action;
    }

    public String getFlag() {
        return this.flag;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getParentId() {
        return this.parentId;
    }

    public String getParentName() {
        return this.parentName;
    }

    public String getSkuId() {
        return this.skuId;
    }

    public static ArrayList<AddressBaseMode> toList(JSONArrayPoxy jSONArrayPoxy, int i2, Object[] objArr) {
        ArrayList<AddressBaseMode> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
            try {
                AddressBaseMode addressBaseMode = new AddressBaseMode(jSONArrayPoxy.getJSONObject(i3), i2, objArr);
                if (!TextUtils.isEmpty(addressBaseMode.getName())) {
                    arrayList.add(addressBaseMode);
                }
            } catch (JSONException e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }

    public AddressBaseMode(JSONObjectProxy jSONObjectProxy, int i2, Object[] objArr) {
        if (i2 != 0) {
            return;
        }
        this.id = jSONObjectProxy.optString("id");
        this.flag = jSONObjectProxy.optString("flag");
        this.name = jSONObjectProxy.optString("name");
        this.action = jSONObjectProxy.optString("action");
        this.skuId = jSONObjectProxy.optString("skuId");
        if (jSONObjectProxy.has("parentId")) {
            this.parentId = jSONObjectProxy.optString("parentId");
        }
        if (jSONObjectProxy.has("parentName")) {
            this.parentName = jSONObjectProxy.optString("parentName");
        }
        this.functionId = jSONObjectProxy.optString("functionId");
        this.isForeignOverSea = parserOverSea(jSONObjectProxy);
    }

    private boolean parserOverSea(JDJSONObject jDJSONObject) {
        String optString;
        return (jDJSONObject == null || (optString = jDJSONObject.optString("overseas")) == null || !optString.equals("1")) ? false : true;
    }

    public static ArrayList<AddressBaseMode> toList(JDJSONArray jDJSONArray, int i2) {
        return toList(jDJSONArray, i2, (Object[]) null);
    }

    public static ArrayList<AddressBaseMode> toList(JDJSONArray jDJSONArray, int i2, Object[] objArr) {
        ArrayList<AddressBaseMode> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            try {
                AddressBaseMode addressBaseMode = new AddressBaseMode(jDJSONArray.getJSONObject(i3), i2, objArr);
                if (!TextUtils.isEmpty(addressBaseMode.getName())) {
                    arrayList.add(addressBaseMode);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }

    public AddressBaseMode(JDJSONObject jDJSONObject, int i2, Object[] objArr) {
        if (i2 != 0) {
            return;
        }
        this.id = jDJSONObject.optString("id");
        this.flag = jDJSONObject.optString("flag");
        this.name = jDJSONObject.optString("name");
        this.action = jDJSONObject.optString("action");
        this.skuId = jDJSONObject.optString("skuId");
        this.parentId = jDJSONObject.optString("parentId");
        this.parentName = jDJSONObject.optString("parentName");
        this.functionId = jDJSONObject.optString("functionId");
        this.isForeignOverSea = parserOverSea(jDJSONObject);
    }
}
