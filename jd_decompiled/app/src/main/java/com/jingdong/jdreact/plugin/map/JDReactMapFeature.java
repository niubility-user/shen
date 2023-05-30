package com.jingdong.jdreact.plugin.map;

import android.content.Context;
import com.facebook.react.views.view.ReactViewGroup;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;

/* loaded from: classes13.dex */
public abstract class JDReactMapFeature extends ReactViewGroup {
    public JDReactMapFeature(Context context) {
        super(context);
    }

    public abstract void addToMap(TencentMap tencentMap);

    public abstract Object getFeature();

    public abstract void removeFromMap(TencentMap tencentMap);
}
