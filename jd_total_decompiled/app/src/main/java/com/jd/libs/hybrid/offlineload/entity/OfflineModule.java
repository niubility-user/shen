package com.jd.libs.hybrid.offlineload.entity;

import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class OfflineModule extends Module<OfflineModule> {
    private int P;
    private String[] Q;
    public String downloadFrom;

    public String[] getDownloadTiming() {
        return this.Q;
    }

    public int getLevel() {
        return this.P;
    }

    public void setDownloadTiming(String[] strArr) {
        this.Q = strArr;
    }

    public void setLevel(int i2) {
        this.P = i2;
    }

    @Override // com.jd.libs.hybrid.offlineload.entity.Module, com.jd.libs.hybrid.base.entity.IJsonfy
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        JSONObject optJSONObject = json.optJSONObject("file");
        if (optJSONObject != null) {
            optJSONObject.put("project_priority", getLevel());
            String[] strArr = this.Q;
            if (strArr != null && strArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (String str : this.Q) {
                    jSONArray.put(str);
                }
                optJSONObject.put("demand_classes", jSONArray);
            }
        }
        return json;
    }

    @Override // com.jd.libs.hybrid.base.entity.IJsonfy
    public OfflineModule fromJson(JSONObject jSONObject) throws JSONException {
        super.a(jSONObject);
        OfflineModule offlineModule = this;
        JSONObject optJSONObject = jSONObject.optJSONObject("file");
        if (optJSONObject != null) {
            offlineModule.setLevel(optJSONObject.optInt("project_priority"));
            JSONArray optJSONArray = optJSONObject.optJSONArray("demand_classes");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                String[] strArr = new String[optJSONArray.length()];
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    strArr[i2] = optJSONArray.optString(i2);
                }
                offlineModule.setDownloadTiming(strArr);
            }
        }
        return offlineModule;
    }

    @Override // com.jd.libs.hybrid.base.entity.IClone
    @NonNull
    public OfflineModule publicClone() throws CloneNotSupportedException {
        return (OfflineModule) super.clone();
    }
}
