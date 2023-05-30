package com.tencent.mapsdk.internal;

import com.jingdong.jdsdk.constant.JshopConst;
import com.tencent.map.tools.net.NetMethod;
import com.tencent.mapsdk.core.components.protocol.service.net.annotation.NetRequest;
import com.tencent.mapsdk.internal.j2;

/* loaded from: classes.dex */
public interface b3 extends j2.a {
    @NetRequest(constQuery = "styleid=0", method = NetMethod.URL, path = "satellite", queryKeys = {"z", JshopConst.JSHOP_PROMOTIO_X, JshopConst.JSHOP_PROMOTIO_Y, "version"}, useExtraQuery = false)
    String satelliteUrl(String str, String str2, String str3, String str4);

    @NetRequest(constQuery = "styleid=0", method = NetMethod.URL, path = "satellite", queryKeys = {"z", JshopConst.JSHOP_PROMOTIO_X, JshopConst.JSHOP_PROMOTIO_Y, "version"})
    String satelliteUrl2(String str, String str2, String str3, String str4);
}
