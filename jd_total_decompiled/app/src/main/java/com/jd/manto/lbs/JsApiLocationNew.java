package com.jd.manto.lbs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jd.manto.map.Tools;
import com.jingdong.common.lbs.jdlocation.JDLocationListener;
import com.jingdong.common.lbs.jdlocation.JDLocationManager;
import com.jingdong.common.lbs.jdlocation.JDLocationOption;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.lbs.JsApiLocation;
import com.jingdong.manto.jsapi.refact.lbs.LocationChangeListener;
import com.jingdong.manto.sdk.api.IPermission;
import com.jingdong.manto.utils.MantoPermission;

/* loaded from: classes17.dex */
public class JsApiLocationNew extends JsApiLocation {
    @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation
    public boolean hasLocationPermission() {
        return MantoPermission.hasLocationPermissionWithScene("locService", Tools.JD_LOCATION_ID);
    }

    @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation
    public void requestLocation(Context context, final Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
        new Bundle();
        JDLocationOption jDLocationOption = new JDLocationOption();
        jDLocationOption.setBusinessId(Tools.JD_LOCATION_ID);
        jDLocationOption.setSceneId("locService");
        JDLocationManager.getInstance().getLatLng(jDLocationOption, new JDLocationListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001d: INVOKE 
              (wrap: com.jingdong.common.lbs.jdlocation.JDLocationManager : 0x0014: INVOKE  type: STATIC call: com.jingdong.common.lbs.jdlocation.JDLocationManager.getInstance():com.jingdong.common.lbs.jdlocation.JDLocationManager A[MD:():com.jingdong.common.lbs.jdlocation.JDLocationManager (m), WRAPPED] (LINE:5))
              (r0v0 'jDLocationOption' com.jingdong.common.lbs.jdlocation.JDLocationOption)
              (wrap: com.jingdong.common.lbs.jdlocation.JDLocationListener : 0x001a: CONSTRUCTOR 
              (r3v0 'this' com.jd.manto.lbs.JsApiLocationNew A[IMMUTABLE_TYPE, THIS])
              (r4 I:android.os.Bundle A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r5v0 'bundle' android.os.Bundle A[DONT_INLINE])
              (r6v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
             A[MD:(com.jd.manto.lbs.JsApiLocationNew, android.os.Bundle, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] call: com.jd.manto.lbs.JsApiLocationNew.2.<init>(com.jd.manto.lbs.JsApiLocationNew, android.os.Bundle, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.lbs.jdlocation.JDLocationManager.getLatLng(com.jingdong.common.lbs.jdlocation.JDLocationOption, com.jingdong.common.lbs.jdlocation.JDLocationListener):void A[MD:(com.jingdong.common.lbs.jdlocation.JDLocationOption, com.jingdong.common.lbs.jdlocation.JDLocationListener):void (m)] (LINE:5) in method: com.jd.manto.lbs.JsApiLocationNew.requestLocation(android.content.Context, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
            com.jingdong.common.lbs.jdlocation.JDLocationOption r0 = new com.jingdong.common.lbs.jdlocation.JDLocationOption
            r0.<init>()
            java.lang.String r1 = "e62e6209b70a03f9ba03f225d76e8f78"
            r0.setBusinessId(r1)
            java.lang.String r1 = "locService"
            r0.setSceneId(r1)
            com.jingdong.common.lbs.jdlocation.JDLocationManager r1 = com.jingdong.common.lbs.jdlocation.JDLocationManager.getInstance()
            com.jd.manto.lbs.JsApiLocationNew$2 r2 = new com.jd.manto.lbs.JsApiLocationNew$2
            r2.<init>()
            r1.getLatLng(r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.lbs.JsApiLocationNew.requestLocation(android.content.Context, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
    }

    @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation
    public void requestLocationInterval(Context context, Bundle bundle, LocationChangeListener locationChangeListener, boolean z) {
    }

    @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation
    public void requireLocationPermission(Activity activity, final JsApiLocation.Permission permission) {
        MantoPermission.requestLocationPermissionWithScene(activity, new IPermission.PermissionCallBack() { // from class: com.jd.manto.lbs.JsApiLocationNew.1
            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onDenied() {
                permission.onDenied();
            }

            @Override // com.jingdong.manto.sdk.api.IPermission.PermissionCallBack
            public void onGranted() {
                permission.onGranted();
            }
        }, "locService", Tools.JD_LOCATION_ID, null);
    }

    @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation
    public void startChooseLocation(Activity activity, int i2) {
        activity.startActivityForResult(new Intent(activity, MantoChooseLocationActivity.class), i2);
    }

    @Override // com.jingdong.manto.jsapi.refact.lbs.JsApiLocation
    public void startOpenLocation(Activity activity, float f2, float f3, String str, String str2, int i2) {
        Intent intent = new Intent();
        intent.putExtra("kwebmap_slat", f3);
        intent.putExtra("kwebmap_lng", f2);
        if (i2 > 0) {
            intent.putExtra("kwebmap_scale", i2);
        }
        intent.putExtra("kPoiName", str);
        intent.putExtra("Kwebmap_location", str2);
        intent.setClass(activity, MantoOpenLocationActivity.class);
        activity.startActivity(intent);
    }
}
