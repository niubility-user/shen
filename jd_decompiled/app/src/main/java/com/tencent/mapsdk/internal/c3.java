package com.tencent.mapsdk.internal;

import com.jingdong.jdsdk.constant.JshopConst;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;

/* loaded from: classes.dex */
public interface c3 extends j2.a {
    @NetRequest(constQuery = "styleid=7", method = NetMethod.URL, path = "scenic/", queryKeys = {JshopConst.JSHOP_PROMOTIO_X, JshopConst.JSHOP_PROMOTIO_Y, "z", "version"}, useExtraQuery = false)
    String sketchTileUrl(int i2, int i3, int i4, int i5);
}
