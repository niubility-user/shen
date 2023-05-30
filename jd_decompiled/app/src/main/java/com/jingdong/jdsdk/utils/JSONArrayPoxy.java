package com.jingdong.jdsdk.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class JSONArrayPoxy extends JSONArray {
    private JSONArray jsonArray;

    public JSONArrayPoxy(JSONArray jSONArray) {
        this.jsonArray = jSONArray;
    }

    @Override // org.json.JSONArray
    public boolean equals(Object obj) {
        return this.jsonArray.equals(obj);
    }

    @Override // org.json.JSONArray
    public Object get(int i2) throws JSONException {
        return this.jsonArray.get(i2);
    }

    @Override // org.json.JSONArray
    public boolean getBoolean(int i2) throws JSONException {
        return this.jsonArray.getBoolean(i2);
    }

    @Override // org.json.JSONArray
    public double getDouble(int i2) throws JSONException {
        return this.jsonArray.getDouble(i2);
    }

    @Override // org.json.JSONArray
    public int getInt(int i2) throws JSONException {
        return this.jsonArray.getInt(i2);
    }

    public JSONArrayPoxy getJSONArrayOrNull(int i2) {
        try {
            return new JSONArrayPoxy(this.jsonArray.getJSONArray(i2));
        } catch (JSONException unused) {
            return null;
        }
    }

    public JSONObjectProxy getJSONObjectOrNull(int i2) {
        try {
            return new JSONObjectProxy(new JSONObjectProxy(this.jsonArray.getJSONObject(i2)));
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // org.json.JSONArray
    public long getLong(int i2) throws JSONException {
        return this.jsonArray.getLong(i2);
    }

    @Override // org.json.JSONArray
    public String getString(int i2) throws JSONException {
        return this.jsonArray.getString(i2);
    }

    @Override // org.json.JSONArray
    public int hashCode() {
        return this.jsonArray.hashCode();
    }

    @Override // org.json.JSONArray
    public boolean isNull(int i2) {
        return this.jsonArray.isNull(i2);
    }

    @Override // org.json.JSONArray
    public String join(String str) throws JSONException {
        return this.jsonArray.join(str);
    }

    @Override // org.json.JSONArray
    public int length() {
        return this.jsonArray.length();
    }

    @Override // org.json.JSONArray
    public Object opt(int i2) {
        return this.jsonArray.opt(i2);
    }

    @Override // org.json.JSONArray
    public boolean optBoolean(int i2, boolean z) {
        return this.jsonArray.optBoolean(i2, z);
    }

    @Override // org.json.JSONArray
    public double optDouble(int i2, double d) {
        return this.jsonArray.optDouble(i2, d);
    }

    @Override // org.json.JSONArray
    public int optInt(int i2, int i3) {
        return this.jsonArray.optInt(i2, i3);
    }

    @Override // org.json.JSONArray
    public JSONArray optJSONArray(int i2) {
        return this.jsonArray.optJSONArray(i2);
    }

    @Override // org.json.JSONArray
    public JSONObject optJSONObject(int i2) {
        return this.jsonArray.optJSONObject(i2);
    }

    @Override // org.json.JSONArray
    public long optLong(int i2, long j2) {
        return this.jsonArray.optLong(i2, j2);
    }

    @Override // org.json.JSONArray
    public String optString(int i2, String str) {
        return this.jsonArray.optString(i2, str);
    }

    @Override // org.json.JSONArray
    public JSONArray put(boolean z) {
        return this.jsonArray.put(z);
    }

    @Override // org.json.JSONArray
    public JSONObject toJSONObject(JSONArray jSONArray) throws JSONException {
        return this.jsonArray.toJSONObject(jSONArray);
    }

    @Override // org.json.JSONArray
    public String toString() {
        return this.jsonArray.toString();
    }

    @Override // org.json.JSONArray
    public JSONArrayPoxy getJSONArray(int i2) throws JSONException {
        return new JSONArrayPoxy(this.jsonArray.getJSONArray(i2));
    }

    @Override // org.json.JSONArray
    public JSONObjectProxy getJSONObject(int i2) throws JSONException {
        return new JSONObjectProxy(new JSONObjectProxy(this.jsonArray.getJSONObject(i2)));
    }

    @Override // org.json.JSONArray
    public boolean optBoolean(int i2) {
        return this.jsonArray.optBoolean(i2);
    }

    @Override // org.json.JSONArray
    public double optDouble(int i2) {
        return this.jsonArray.optDouble(i2);
    }

    @Override // org.json.JSONArray
    public int optInt(int i2) {
        return this.jsonArray.optInt(i2);
    }

    @Override // org.json.JSONArray
    public long optLong(int i2) {
        return this.jsonArray.optLong(i2);
    }

    @Override // org.json.JSONArray
    public String optString(int i2) {
        return this.jsonArray.optString(i2);
    }

    @Override // org.json.JSONArray
    public JSONArray put(double d) throws JSONException {
        return this.jsonArray.put(d);
    }

    @Override // org.json.JSONArray
    public String toString(int i2) throws JSONException {
        return this.jsonArray.toString(i2);
    }

    public JSONArrayPoxy() {
        this.jsonArray = new JSONArray();
    }

    @Override // org.json.JSONArray
    public JSONArray put(int i2, boolean z) throws JSONException {
        return this.jsonArray.put(i2, z);
    }

    @Override // org.json.JSONArray
    public JSONArray put(int i2, double d) throws JSONException {
        return this.jsonArray.put(i2, d);
    }

    @Override // org.json.JSONArray
    public JSONArray put(int i2, int i3) throws JSONException {
        return this.jsonArray.put(i2, i3);
    }

    @Override // org.json.JSONArray
    public JSONArray put(int i2, long j2) throws JSONException {
        return this.jsonArray.put(i2, j2);
    }

    @Override // org.json.JSONArray
    public JSONArray put(int i2, Object obj) throws JSONException {
        return this.jsonArray.put(i2, obj);
    }

    @Override // org.json.JSONArray
    public JSONArray put(int i2) {
        return this.jsonArray.put(i2);
    }

    @Override // org.json.JSONArray
    public JSONArray put(long j2) {
        return this.jsonArray.put(j2);
    }

    @Override // org.json.JSONArray
    public JSONArray put(Object obj) {
        return this.jsonArray.put(obj);
    }
}
