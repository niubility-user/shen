package com.jingdong.common.XView2.strategy.downloader.entity;

import com.jd.hybrid.downloader.m.a;
import com.jingdong.common.XView2.common.XView2Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XViewFileEntity extends a {
    public long expiredTime;
    public boolean isDsl;
    public String layerId;
    public String name;
    public String suffix;
    public int targetId;

    public long getExpiredTime() {
        return this.expiredTime;
    }

    @Override // com.jd.hybrid.downloader.m.a, com.jd.libs.hybrid.base.entity.IJsonfy
    public synchronized JSONObject toJson() throws JSONException {
        JSONObject json;
        json = super.toJson();
        json.put("suffix", this.suffix);
        json.put("expiredTime", this.expiredTime);
        json.put(XView2Constants.LAYER_ID, this.layerId);
        json.put("name", this.name);
        json.put("isDsl", this.isDsl);
        json.put("targetId", this.targetId);
        return json;
    }

    @Override // com.jd.hybrid.downloader.m.a, com.jd.libs.hybrid.base.entity.IJsonfy
    public synchronized a fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this;
        }
        XViewFileEntity xViewFileEntity = (XViewFileEntity) super.fromJson(jSONObject);
        this.expiredTime = jSONObject.optLong("expiredTime");
        this.suffix = jSONObject.optString("suffix");
        this.layerId = jSONObject.optString(XView2Constants.LAYER_ID);
        this.name = jSONObject.optString("name");
        this.isDsl = jSONObject.optBoolean("isDsl");
        this.targetId = jSONObject.optInt("targetId");
        return xViewFileEntity;
    }

    @Override // com.jd.hybrid.downloader.m.a, com.jd.libs.hybrid.base.entity.IClone
    public a publicClone() throws CloneNotSupportedException {
        a publicClone = super.publicClone();
        if (publicClone instanceof XViewFileEntity) {
            XViewFileEntity xViewFileEntity = (XViewFileEntity) publicClone;
            xViewFileEntity.expiredTime = this.expiredTime;
            xViewFileEntity.suffix = this.suffix;
            xViewFileEntity.layerId = this.layerId;
            xViewFileEntity.name = this.name;
            xViewFileEntity.isDsl = this.isDsl;
            xViewFileEntity.targetId = this.targetId;
            return xViewFileEntity;
        }
        return null;
    }
}
