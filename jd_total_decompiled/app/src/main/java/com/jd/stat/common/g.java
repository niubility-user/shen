package com.jd.stat.common;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.react.uimanager.ViewProps;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONObject;

@SuppressLint({"MissingPermission"})
/* loaded from: classes18.dex */
public class g {
    public static JSONObject a(Context context) {
        return new com.jd.stat.common.b.d().a().a("AndroidId.a", BaseInfo.getAndroidId()).a("AndroidId.b", com.jingdong.jdsdk.a.a.a).a("DeviceId", com.jingdong.jdsdk.a.a.a).a("Brand", BaseInfo.getDeviceBrand()).b();
    }

    public static JSONObject a() {
        return new com.jd.stat.common.b.d().a().a("Mac", com.jingdong.jdsdk.a.a.a).a("Brand", com.jingdong.jdsdk.a.a.a).a("abi", r.a(NativeInfo.getProp("ro.product.cpu.abi"))).a("abilist", r.a(NativeInfo.getProp("ro.product.cpu.abilist"))).a("abilist32", r.a(NativeInfo.getProp("ro.product.cpu.abilist32"))).a("abilist64", r.a(NativeInfo.getProp("ro.product.cpu.abilist64"))).a("tags", NativeInfo.getProp("ro.build.tags")).a("board", NativeInfo.getProp("ro.product.board")).a("bootloader", NativeInfo.getProp("ro.bootloader")).a("bid", NativeInfo.getProp("ro.build.id")).a(ViewProps.DISPLAY, com.jingdong.jdsdk.a.a.a).a(NoStockRecommendHead.DEVICE, com.jingdong.jdsdk.a.a.a).a("buildInfo", n.o()).a("hardware", NativeInfo.getProp("ro.hardware")).a("fingerprint", NativeInfo.getProp("ro.build.fingerprint")).b();
    }
}
