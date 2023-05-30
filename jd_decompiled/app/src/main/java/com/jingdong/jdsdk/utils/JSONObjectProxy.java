package com.jingdong.jdsdk.utils;

import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class JSONObjectProxy extends JSONObject implements Serializable {
    private JSONObject jsonObject;

    public JSONObjectProxy(JSONObject jSONObject) {
        this.jsonObject = jSONObject;
    }

    @Override // org.json.JSONObject
    public JSONObject accumulate(String str, Object obj) throws JSONException {
        return this.jsonObject.accumulate(str, obj);
    }

    public boolean equals(JSONObject jSONObject) {
        JSONObject jSONObject2 = this.jsonObject;
        if (jSONObject2 == jSONObject) {
            return true;
        }
        if (jSONObject2 == null || jSONObject == null) {
            return false;
        }
        return jSONObject2.toString().equals(jSONObject.toString());
    }

    @Override // org.json.JSONObject
    public Object get(String str) throws JSONException {
        return this.jsonObject.get(str);
    }

    @Override // org.json.JSONObject
    public boolean getBoolean(String str) throws JSONException {
        return this.jsonObject.getBoolean(str);
    }

    @Deprecated
    public Boolean getBooleanOrNull(String str) {
        if (this.jsonObject.isNull(str)) {
            return null;
        }
        try {
            return Boolean.valueOf(this.jsonObject.getBoolean(str));
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(JSONObjectProxy.class.getName(), e2.getMessage());
            }
            return null;
        }
    }

    @Override // org.json.JSONObject
    public double getDouble(String str) throws JSONException {
        return this.jsonObject.getDouble(str);
    }

    @Deprecated
    public Double getDoubleOrNull(String str) {
        if (this.jsonObject.isNull(str)) {
            return null;
        }
        try {
            return Double.valueOf(this.jsonObject.getDouble(str));
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(JSONObjectProxy.class.getName(), e2.getMessage());
            }
            return null;
        }
    }

    public double getDoubleValue(String str) {
        if (this.jsonObject.isNull(str)) {
            return -1.0d;
        }
        try {
            return Double.valueOf(this.jsonObject.getDouble(str)).doubleValue();
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(JSONObjectProxy.class.getName(), e2.getMessage());
            }
            return -1.0d;
        }
    }

    @Override // org.json.JSONObject
    public int getInt(String str) throws JSONException {
        return this.jsonObject.getInt(str);
    }

    @Deprecated
    public Integer getIntOrNull(String str) {
        if (this.jsonObject.isNull(str)) {
            return null;
        }
        try {
            return Integer.valueOf(this.jsonObject.getInt(str));
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(JSONObjectProxy.class.getName(), e2.getMessage());
            }
            return null;
        }
    }

    public int getIntegerValue(String str) {
        if (this.jsonObject.isNull(str)) {
            return -1;
        }
        try {
            return this.jsonObject.getInt(str);
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(JSONObjectProxy.class.getName(), e2.getMessage());
            }
            return -1;
        }
    }

    public JSONArrayPoxy getJSONArrayOrNull(String str) {
        if (this.jsonObject.isNull(str)) {
            return null;
        }
        try {
            return new JSONArrayPoxy(this.jsonObject.getJSONArray(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public JSONObjectProxy getJSONObjectOrNull(String str) {
        if (this.jsonObject.isNull(str)) {
            return null;
        }
        try {
            return new JSONObjectProxy(this.jsonObject.getJSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // org.json.JSONObject
    public long getLong(String str) throws JSONException {
        return this.jsonObject.getLong(str);
    }

    @Deprecated
    public Long getLongOrNull(String str) {
        if (this.jsonObject.isNull(str)) {
            return null;
        }
        try {
            return Long.valueOf(this.jsonObject.getLong(str));
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(JSONObjectProxy.class.getName(), e2.getMessage());
            }
            return null;
        }
    }

    @Override // org.json.JSONObject
    public String getString(String str) throws JSONException {
        return this.jsonObject.getString(str);
    }

    @Deprecated
    public String getStringOrNull(String str) {
        if (this.jsonObject.isNull(str)) {
            return null;
        }
        try {
            return this.jsonObject.getString(str);
        } catch (JSONException e2) {
            if (OKLog.V) {
                OKLog.v(JSONObjectProxy.class.getName(), e2.getMessage());
            }
            return null;
        }
    }

    @Override // org.json.JSONObject
    public boolean has(String str) {
        return this.jsonObject.has(str);
    }

    public int hashCode() {
        return this.jsonObject.hashCode();
    }

    public JSONObject innerJSONObject() {
        return this.jsonObject;
    }

    @Override // org.json.JSONObject
    public boolean isNull(String str) {
        return this.jsonObject.isNull(str);
    }

    @Override // org.json.JSONObject
    public Iterator keys() {
        return this.jsonObject.keys();
    }

    @Override // org.json.JSONObject
    public int length() {
        return this.jsonObject.length();
    }

    @Override // org.json.JSONObject
    public JSONArray names() {
        return this.jsonObject.names();
    }

    @Override // org.json.JSONObject
    public Object opt(String str) {
        return this.jsonObject.opt(str);
    }

    @Override // org.json.JSONObject
    public boolean optBoolean(String str, boolean z) {
        return this.jsonObject.optBoolean(str, z);
    }

    @Override // org.json.JSONObject
    public double optDouble(String str, double d) {
        return this.jsonObject.optDouble(str, d);
    }

    @Override // org.json.JSONObject
    public int optInt(String str, int i2) {
        return this.jsonObject.optInt(str, i2);
    }

    @Override // org.json.JSONObject
    public JSONArray optJSONArray(String str) {
        return this.jsonObject.optJSONArray(str);
    }

    @Override // org.json.JSONObject
    public JSONObject optJSONObject(String str) {
        return this.jsonObject.optJSONObject(str);
    }

    @Override // org.json.JSONObject
    public long optLong(String str, long j2) {
        return this.jsonObject.optLong(str, j2);
    }

    @Override // org.json.JSONObject
    public String optString(String str, String str2) {
        return this.jsonObject.optString(str, str2);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, boolean z) throws JSONException {
        return this.jsonObject.put(str, z);
    }

    @Override // org.json.JSONObject
    public JSONObject putOpt(String str, Object obj) throws JSONException {
        return this.jsonObject.putOpt(str, obj);
    }

    @Override // org.json.JSONObject
    public Object remove(String str) {
        return this.jsonObject.remove(str);
    }

    @Override // org.json.JSONObject
    public JSONArray toJSONArray(JSONArray jSONArray) throws JSONException {
        return this.jsonObject.toJSONArray(jSONArray);
    }

    @Override // org.json.JSONObject
    public String toString() {
        return this.jsonObject.toString();
    }

    @Override // org.json.JSONObject
    public JSONArrayPoxy getJSONArray(String str) throws JSONException {
        return new JSONArrayPoxy(this.jsonObject.getJSONArray(str));
    }

    @Override // org.json.JSONObject
    public JSONObjectProxy getJSONObject(String str) throws JSONException {
        return new JSONObjectProxy(this.jsonObject.getJSONObject(str));
    }

    @Override // org.json.JSONObject
    public boolean optBoolean(String str) {
        return this.jsonObject.optBoolean(str);
    }

    @Override // org.json.JSONObject
    public double optDouble(String str) {
        return this.jsonObject.optDouble(str);
    }

    @Override // org.json.JSONObject
    public int optInt(String str) {
        return this.jsonObject.optInt(str);
    }

    @Override // org.json.JSONObject
    public long optLong(String str) {
        return this.jsonObject.optLong(str);
    }

    @Override // org.json.JSONObject
    public String optString(String str) {
        return this.jsonObject.optString(str);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, double d) throws JSONException {
        return this.jsonObject.put(str, d);
    }

    @Override // org.json.JSONObject
    public String toString(int i2) throws JSONException {
        return this.jsonObject.toString(i2);
    }

    public JSONObjectProxy() {
        this.jsonObject = new JSONObject();
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, int i2) throws JSONException {
        return this.jsonObject.put(str, i2);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, long j2) throws JSONException {
        return this.jsonObject.put(str, j2);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, Object obj) throws JSONException {
        return this.jsonObject.put(str, obj);
    }
}
