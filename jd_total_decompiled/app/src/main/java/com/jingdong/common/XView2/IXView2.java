package com.jingdong.common.XView2;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface IXView2 {
    void createXView2();

    void dispatchPop(Context context, Bundle bundle);

    boolean displayXView2();

    boolean displayXView2ById(XViewConfigEntity.LayersEntity layersEntity);

    boolean displayXView2Immediately();

    Fragment getCurrentFragment();

    String getCurrentTargetName();

    XView2Delegate getXView2Delegate();

    void startPopStrategy();

    void startXView2();

    void startXView2ById(XViewConfigEntity.LayersEntity layersEntity, JSONObject jSONObject);

    void startXView2Immediately();
}
