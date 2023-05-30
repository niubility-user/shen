package com.jingdong.common.XView2.event;

import android.content.Context;
import android.os.Bundle;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface IPopEvent {
    void backAction(JSONObject jSONObject);

    void changeLayout(JSONObject jSONObject);

    void closeLayer(JSONObject jSONObject);

    void dispatchPopEvent(Context context, Bundle bundle);

    void preview(JSONObject jSONObject);

    void removeLayer(JSONObject jSONObject);

    void setLayerVisible(JSONObject jSONObject);

    void showLayer(JSONObject jSONObject);

    void startXView2ById(XViewConfigEntity.LayersEntity layersEntity, JSONObject jSONObject);
}
