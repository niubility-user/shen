package com.tencent.mapsdk.internal;

import com.tencent.map.sdk.comps.vis.VisualLayer;
import com.tencent.map.sdk.comps.vis.VisualLayerOptions;
import com.tencent.map.tools.Callback;
import com.tencent.tencentmap.mapsdk.maps.TencentMapComponent;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public interface y3 extends TencentMapComponent.Component {
    VisualLayer a(VisualLayerOptions visualLayerOptions);

    void a(String str);

    void a(String str, int i2);

    void a(String str, int i2, int i3);

    void a(String str, Callback<byte[]> callback);

    void a(String str, byte[] bArr);

    void a(JSONObject jSONObject);

    int b(String str);

    void b(String str, Callback<byte[]> callback);

    void c(String str);

    boolean c();

    void d(String str);

    void e(String str);

    boolean f(String str);

    String g(String str);

    o1 getMapContext();
}
