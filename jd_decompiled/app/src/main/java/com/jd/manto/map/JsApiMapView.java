package com.jd.manto.map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jd.manto.map.Beans;
import com.jingdong.a;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.base.MantoCallback;
import com.jingdong.manto.jsapi.coverview.CoverViewContainer;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.tencent.tencentmap.mapsdk.maps.MapView;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public final class JsApiMapView extends AbstractMantoViewManager {
    static final String ADD_CIRCLE_NAME = "addMapCircles";
    static final String ADD_CTRL_NAME = "addMapControls";
    static final String ADD_LINE_NAME = "addMapLines";
    static final String ADD_MARK_NAME = "addMapMarkers";
    static final String GET_MAP_CENTER = "getMapCenterLocation";
    static final String GET_MAP_REGION = "getMapRegion";
    static final String GET_MAP_ROTATE = "getMapRotate";
    static final String GET_MAP_SCALE = "getMapScale";
    static final String GET_MAP_SKEW = "getMapSkew";
    static final String INCLUDE_POINTS_NAME = "includeMapPoints";
    static final String INSERT_NAME = "insertOrUpdateMap";
    static final String MOVE_TO_LOCATION = "moveToMapLocation";
    static final String REMOVE_MARK_NAME = "removeMapMarkers";
    static final String REMOVE_NAME = "removeMap";
    static final String SET_CENTER_OFFSET = "setCenterOffset";
    static final String TRANSLATE_MARKER = "translateMapMarker";
    static final int idx_ADD_CIRCLE = 7;
    static final int idx_ADD_CTRL = 8;
    static final int idx_ADD_LINE = 6;
    static final int idx_ADD_MARK = 5;
    static final int idx_GET_MAP_CENTER = 10;
    static final int idx_GET_REGION = 11;
    static final int idx_GET_ROTATE = 13;
    static final int idx_GET_SCALE = 12;
    static final int idx_GET_SKEW = 14;
    static final int idx_INCLUDE_POINTS = 3;
    static final int idx_INSERT = 1;
    static final int idx_MOVE_TO_LOCATION = 15;
    static final int idx_REMOVE = 2;
    static final int idx_REMOVE_MARK = 4;
    static final int idx_SET_CENTER_OFFSET = 17;
    static final int idx_TRANSLATE_MARKER = 16;
    private final Map<String, MapAndProcessCombineView> combineViewMap = new ConcurrentHashMap();
    private MantoLifecycleLisener mantoLifecycleLisener = new MantoLifecycleLisener() { // from class: com.jd.manto.map.JsApiMapView.1
        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onBackground() {
            for (Map.Entry entry : JsApiMapView.this.combineViewMap.entrySet()) {
                String.format("mapId: %s,onBackground", entry.getKey());
                if (entry.getValue() != null && ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter() != null) {
                    ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter().onPause();
                    ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter().onStop();
                }
            }
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onDestroy() {
            for (Map.Entry entry : JsApiMapView.this.combineViewMap.entrySet()) {
                String.format("mapId: %s,onDestroy", entry.getKey());
                if (entry.getValue() != null && ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter() != null) {
                    ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter().onDestroy();
                }
            }
            JsApiMapView.this.combineViewMap.clear();
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onForeground() {
            for (Map.Entry entry : JsApiMapView.this.combineViewMap.entrySet()) {
                String.format("mapId: %s,onForeground", entry.getKey());
                if (entry.getValue() != null && ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter() != null) {
                    ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter().onStart();
                    ((MapAndProcessCombineView) entry.getValue()).getMapProcessCenter().onResume();
                }
            }
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onPause() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public void onReady() {
        }

        @Override // com.jingdong.manto.jsapi.openmodule.MantoLifecycleLisener
        public boolean onRemove() {
            return false;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void addMapCircle(final MapProcessCenter mapProcessCenter, MapView mapView, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
        final List<Beans.CircleInfo> circleInfoList = mapProcessCenter.getCircleInfoList(jSONObject);
        mapView.getMap();
        if (circleInfoList != null && circleInfoList.size() > 0) {
            MantoThreadUtils.runOnUIThread(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0019: INVOKE 
                  (wrap: java.lang.Runnable : 0x0016: CONSTRUCTOR 
                  (r6v0 'this' com.jd.manto.map.JsApiMapView A[IMMUTABLE_TYPE, THIS])
                  (r7v0 'mapProcessCenter' com.jd.manto.map.MapProcessCenter A[DONT_INLINE])
                  (r3 I:com.tencent.tencentmap.mapsdk.maps.TencentMap A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r4v0 'circleInfoList' java.util.List<com.jd.manto.map.Beans$CircleInfo> A[DONT_INLINE])
                  (r10v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                 A[MD:(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.TencentMap, java.util.List, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] (LINE:4) call: com.jd.manto.map.JsApiMapView.7.<init>(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.TencentMap, java.util.List, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
                 type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:4) in method: com.jd.manto.map.JsApiMapView.addMapCircle(com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                */
            /*
                this = this;
                java.util.List r4 = r7.getCircleInfoList(r9)
                com.tencent.tencentmap.mapsdk.maps.TencentMap r3 = r8.getMap()
                if (r4 == 0) goto L1d
                int r8 = r4.size()
                if (r8 <= 0) goto L1d
                com.jd.manto.map.JsApiMapView$7 r8 = new com.jd.manto.map.JsApiMapView$7
                r0 = r8
                r1 = r6
                r2 = r7
                r5 = r10
                r0.<init>()
                com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r8)
                goto L2d
            L1d:
                android.os.Bundle r7 = new android.os.Bundle
                r8 = 2
                r7.<init>(r8)
                java.lang.String r8 = "message"
                java.lang.String r9 = "has not circles."
                r7.putString(r8, r9)
                r10.onFailed(r7)
            L2d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.JsApiMapView.addMapCircle(com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addMapCtrl(final MantoCore mantoCore, final MapProcessCenter mapProcessCenter, MapView mapView, final int i2, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
            mapProcessCenter.getControlInfoList(jSONObject);
            jSONObject.optInt(AbstractMantoViewManager.VIEW_ID_KEY);
            MantoThreadUtils.runOnUIThread(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: INVOKE 
                  (wrap: java.lang.Runnable : 0x0012: CONSTRUCTOR 
                  (r8v0 'this' com.jd.manto.map.JsApiMapView A[IMMUTABLE_TYPE, THIS])
                  (r10v0 'mapProcessCenter' com.jd.manto.map.MapProcessCenter A[DONT_INLINE])
                  (r3 I:java.util.List A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r9v0 'mantoCore' com.jingdong.manto.MantoCore A[DONT_INLINE])
                  (r5 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r12v0 'i2' int A[DONT_INLINE])
                  (r14v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                 A[MD:(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, java.util.List, com.jingdong.manto.MantoCore, int, int, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] (LINE:3) call: com.jd.manto.map.JsApiMapView.6.<init>(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, java.util.List, com.jingdong.manto.MantoCore, int, int, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
                 type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:3) in method: com.jd.manto.map.JsApiMapView.addMapCtrl(com.jingdong.manto.MantoCore, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, int, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
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
                */
            /*
                this = this;
                java.util.List r3 = r10.getControlInfoList(r13)
                java.lang.String r11 = "viewID"
                int r5 = r13.optInt(r11)
                com.jd.manto.map.JsApiMapView$6 r11 = new com.jd.manto.map.JsApiMapView$6
                r0 = r11
                r1 = r8
                r2 = r10
                r4 = r9
                r6 = r12
                r7 = r14
                r0.<init>()
                com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r11)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.JsApiMapView.addMapCtrl(com.jingdong.manto.MantoCore, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, int, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addMapLine(final MantoCore mantoCore, final MapProcessCenter mapProcessCenter, MapView mapView, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
            mapView.getMap();
            final List<Beans.LineInfo> lineInfoList = mapProcessCenter.getLineInfoList(jSONObject);
            if (lineInfoList != null && lineInfoList.size() > 0) {
                MantoThreadUtils.runOnUIThread(new Runnable
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: INVOKE 
                      (wrap: java.lang.Runnable : 0x0017: CONSTRUCTOR 
                      (r7v0 'this' com.jd.manto.map.JsApiMapView A[IMMUTABLE_TYPE, THIS])
                      (r9v0 'mapProcessCenter' com.jd.manto.map.MapProcessCenter A[DONT_INLINE])
                      (r8v0 'mantoCore' com.jingdong.manto.MantoCore A[DONT_INLINE])
                      (r4 I:com.tencent.tencentmap.mapsdk.maps.TencentMap A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r5v0 'lineInfoList' java.util.List<com.jd.manto.map.Beans$LineInfo> A[DONT_INLINE])
                      (r12v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                     A[MD:(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, com.jingdong.manto.MantoCore, com.tencent.tencentmap.mapsdk.maps.TencentMap, java.util.List, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] (LINE:4) call: com.jd.manto.map.JsApiMapView.8.<init>(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, com.jingdong.manto.MantoCore, com.tencent.tencentmap.mapsdk.maps.TencentMap, java.util.List, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
                     type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:4) in method: com.jd.manto.map.JsApiMapView.addMapLine(com.jingdong.manto.MantoCore, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                    */
                /*
                    this = this;
                    com.tencent.tencentmap.mapsdk.maps.TencentMap r4 = r10.getMap()
                    java.util.List r5 = r9.getLineInfoList(r11)
                    if (r5 == 0) goto L1e
                    int r10 = r5.size()
                    if (r10 <= 0) goto L1e
                    com.jd.manto.map.JsApiMapView$8 r10 = new com.jd.manto.map.JsApiMapView$8
                    r0 = r10
                    r1 = r7
                    r2 = r9
                    r3 = r8
                    r6 = r12
                    r0.<init>()
                    com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r10)
                    goto L2e
                L1e:
                    android.os.Bundle r8 = new android.os.Bundle
                    r9 = 2
                    r8.<init>(r9)
                    java.lang.String r9 = "message"
                    java.lang.String r10 = "has not lines."
                    r8.putString(r9, r10)
                    r12.onFailed(r8)
                L2e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.JsApiMapView.addMapLine(com.jingdong.manto.MantoCore, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void addMarker(final MantoCore mantoCore, final MapProcessCenter mapProcessCenter, MapView mapView, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
                mapView.getMap();
                if (jSONObject.optBoolean("clear", true)) {
                    MantoThreadUtils.runOnUIThread(new Runnable() { // from class: com.jd.manto.map.JsApiMapView.10
                        @Override // java.lang.Runnable
                        public void run() {
                            mapProcessCenter.clearMarker();
                        }
                    });
                }
                final List<Beans.MarkInfo> markInfoList = mapProcessCenter.getMarkInfoList(jSONObject);
                if (markInfoList != null && markInfoList.size() > 0) {
                    MantoThreadUtils.runOnUIThread(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: INVOKE 
                          (wrap: java.lang.Runnable : 0x0028: CONSTRUCTOR 
                          (r7v0 'this' com.jd.manto.map.JsApiMapView A[IMMUTABLE_TYPE, THIS])
                          (r9v0 'mapProcessCenter' com.jd.manto.map.MapProcessCenter A[DONT_INLINE])
                          (r8v0 'mantoCore' com.jingdong.manto.MantoCore A[DONT_INLINE])
                          (r4 I:com.tencent.tencentmap.mapsdk.maps.TencentMap A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r5v0 'markInfoList' java.util.List<com.jd.manto.map.Beans$MarkInfo> A[DONT_INLINE])
                          (r12v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                         A[MD:(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, com.jingdong.manto.MantoCore, com.tencent.tencentmap.mapsdk.maps.TencentMap, java.util.List, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] (LINE:6) call: com.jd.manto.map.JsApiMapView.11.<init>(com.jd.manto.map.JsApiMapView, com.jd.manto.map.MapProcessCenter, com.jingdong.manto.MantoCore, com.tencent.tencentmap.mapsdk.maps.TencentMap, java.util.List, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
                         type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:6) in method: com.jd.manto.map.JsApiMapView.addMarker(com.jingdong.manto.MantoCore, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                        */
                    /*
                        this = this;
                        com.tencent.tencentmap.mapsdk.maps.TencentMap r4 = r10.getMap()
                        java.lang.String r10 = "clear"
                        r0 = 1
                        boolean r10 = r11.optBoolean(r10, r0)
                        if (r10 == 0) goto L15
                        com.jd.manto.map.JsApiMapView$10 r10 = new com.jd.manto.map.JsApiMapView$10
                        r10.<init>()
                        com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r10)
                    L15:
                        java.util.List r5 = r9.getMarkInfoList(r11)
                        if (r5 == 0) goto L2f
                        int r10 = r5.size()
                        if (r10 <= 0) goto L2f
                        com.jd.manto.map.JsApiMapView$11 r10 = new com.jd.manto.map.JsApiMapView$11
                        r0 = r10
                        r1 = r7
                        r2 = r9
                        r3 = r8
                        r6 = r12
                        r0.<init>()
                        com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r10)
                        goto L3f
                    L2f:
                        android.os.Bundle r8 = new android.os.Bundle
                        r9 = 2
                        r8.<init>(r9)
                        java.lang.String r9 = "message"
                        java.lang.String r10 = "no mark add."
                        r8.putString(r9, r10)
                        r12.onFailed(r8)
                    L3f:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.JsApiMapView.addMarker(com.jingdong.manto.MantoCore, com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void getMapCenter(MapProcessCenter mapProcessCenter, MapView mapView, JSONObject jSONObject, MantoResultCallBack mantoResultCallBack) {
                    TencentMap map = mapView.getMap();
                    Bundle bundle = new Bundle(2);
                    if (map != null) {
                        LatLng latLng = map.getCameraPosition().target;
                        bundle.putDouble(PdLVBody.LONGITUDE, latLng.getLongitude());
                        bundle.putDouble(PdLVBody.LATITUDE, latLng.getLatitude());
                        mantoResultCallBack.onSuccess(bundle);
                        return;
                    }
                    mantoResultCallBack.onFailed(null);
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void getRegion(MapView mapView, MantoResultCallBack mantoResultCallBack) {
                    try {
                        LatLngBounds latLngBounds = mapView.getMap().getProjection().getVisibleRegion().latLngBounds;
                        LatLng southWest = latLngBounds.getSouthWest();
                        LatLng northEast = latLngBounds.getNorthEast();
                        Bundle bundle = new Bundle(2);
                        JSONObject jSONObject = new JSONObject();
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject.put(PdLVBody.LONGITUDE, southWest.getLongitude());
                        jSONObject.put(PdLVBody.LATITUDE, southWest.getLatitude());
                        jSONObject2.put(PdLVBody.LONGITUDE, northEast.getLongitude());
                        jSONObject2.put(PdLVBody.LATITUDE, northEast.getLatitude());
                        bundle.putString("southwest", jSONObject.toString());
                        bundle.putString("northeast", jSONObject2.toString());
                        mantoResultCallBack.onSuccess(bundle);
                    } catch (Exception e2) {
                        mantoResultCallBack.onFailed(null);
                        MantoLog.e("map", e2);
                    }
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void includeMapPoint(final MapProcessCenter mapProcessCenter, final MapView mapView, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
                    double d;
                    final List<Beans.Point> pointList = mapProcessCenter.getPointList(jSONObject);
                    JSONArray optJSONArray = jSONObject.optJSONArray("padding");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        try {
                            d = optJSONArray.getDouble(0);
                        } catch (JSONException unused) {
                        }
                        final double d2 = d;
                        if (pointList == null && pointList.size() > 0) {
                            MantoThreadUtils.runOnUIThread(new Runnable() { // from class: com.jd.manto.map.JsApiMapView.5
                                @Override // java.lang.Runnable
                                public void run() {
                                    mapProcessCenter.includePoints(mapView.getMap(), pointList, d2);
                                    mantoResultCallBack.onSuccess(null);
                                }
                            });
                            return;
                        }
                        Bundle bundle = new Bundle(2);
                        bundle.putString("message", "has not points.");
                        mantoResultCallBack.onFailed(bundle);
                    }
                    d = 0.0d;
                    final double d22 = d;
                    if (pointList == null) {
                    }
                    Bundle bundle2 = new Bundle(2);
                    bundle2.putString("message", "has not points.");
                    mantoResultCallBack.onFailed(bundle2);
                }

                private boolean insertOrUpdateMap(MantoCore mantoCore, Bundle bundle, View view, boolean z) {
                    MapAndProcessCombineView mapAndProcessCombineView;
                    MapProcessCenter mapProcessCenter;
                    Beans.MapInfo mapInfo;
                    int i2 = bundle.getInt("hashCode");
                    int i3 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
                    try {
                        JSONObject jSONObject = new JSONObject(bundle.getString("json"));
                        if ((view instanceof CoverViewContainer) && (mapAndProcessCombineView = this.combineViewMap.get(String.format("%s_%s", Integer.valueOf(i2), Integer.valueOf(i3)))) != null && (mapProcessCenter = mapAndProcessCombineView.getMapProcessCenter()) != null) {
                            MapAndProcessCombineView mapAndProcessCombineView2 = (MapAndProcessCombineView) ((CoverViewContainer) view).convertTo(MapAndProcessCombineView.class);
                            if (mapAndProcessCombineView2 != null && (mapInfo = mapProcessCenter.getMapInfo(jSONObject)) != null) {
                                if (z) {
                                    mapProcessCenter.drawMap(mantoCore, mapAndProcessCombineView2.getMapView(), mapInfo, i3, i2);
                                } else {
                                    mapProcessCenter.updateMap(mantoCore, mapAndProcessCombineView2.getMapView(), mapInfo, mapProcessCenter.getUpdateMapInfo(jSONObject), i3, i2);
                                }
                            }
                            return true;
                        }
                    } catch (JSONException unused) {
                    }
                    return false;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void moveToLocation(MapProcessCenter mapProcessCenter, final MapView mapView, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
                    if (jSONObject.has(PdLVBody.LATITUDE) && jSONObject.has(PdLVBody.LONGITUDE)) {
                        new Beans.Point(jSONObject.optDouble(PdLVBody.LATITUDE, 0.0d), jSONObject.optDouble(PdLVBody.LONGITUDE, 0.0d));
                        jSONObject.optInt("mapId", 0);
                        MantoThreadUtils.runOnUIThread(new Runnable
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002a: INVOKE 
                              (wrap: java.lang.Runnable : 0x0027: CONSTRUCTOR 
                              (r6v0 'this' com.jd.manto.map.JsApiMapView A[IMMUTABLE_TYPE, THIS])
                              (r1 I:com.jd.manto.map.Beans$Point A[DONT_GENERATE, DONT_INLINE, REMOVE])
                              (r8v0 'mapView' com.tencent.tencentmap.mapsdk.maps.MapView A[DONT_INLINE])
                              (r10v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                             A[MD:(com.jd.manto.map.JsApiMapView, com.jd.manto.map.Beans$Point, com.tencent.tencentmap.mapsdk.maps.MapView, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] (LINE:4) call: com.jd.manto.map.JsApiMapView.4.<init>(com.jd.manto.map.JsApiMapView, com.jd.manto.map.Beans$Point, com.tencent.tencentmap.mapsdk.maps.MapView, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
                             type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:4) in method: com.jd.manto.map.JsApiMapView.moveToLocation(com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                            */
                        /*
                            this = this;
                            java.lang.String r7 = "latitude"
                            boolean r0 = r9.has(r7)
                            if (r0 == 0) goto L2e
                            java.lang.String r0 = "longitude"
                            boolean r1 = r9.has(r0)
                            if (r1 == 0) goto L2e
                            com.jd.manto.map.Beans$Point r1 = new com.jd.manto.map.Beans$Point
                            r2 = 0
                            double r4 = r9.optDouble(r7, r2)
                            double r2 = r9.optDouble(r0, r2)
                            r1.<init>(r4, r2)
                            r7 = 0
                            java.lang.String r0 = "mapId"
                            r9.optInt(r0, r7)
                            com.jd.manto.map.JsApiMapView$4 r7 = new com.jd.manto.map.JsApiMapView$4
                            r7.<init>()
                            com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r7)
                            goto L3e
                        L2e:
                            android.os.Bundle r7 = new android.os.Bundle
                            r8 = 2
                            r7.<init>(r8)
                            java.lang.String r8 = "message"
                            java.lang.String r9 = "has not points."
                            r7.putString(r8, r9)
                            r10.onFailed(r7)
                        L3e:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.JsApiMapView.moveToLocation(com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public void removeMark(final MapProcessCenter mapProcessCenter, MapView mapView, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
                        if (jSONObject.has("markers")) {
                            try {
                                final JSONArray jSONArray = new JSONArray(jSONObject.optString("markers"));
                                if (jSONArray.length() <= 0) {
                                    mantoResultCallBack.onFailed(null);
                                    return;
                                } else {
                                    MantoThreadUtils.runOnUIThread(new Runnable() { // from class: com.jd.manto.map.JsApiMapView.9
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                                mapProcessCenter.removeMarker(jSONArray.optString(i2, ""));
                                            }
                                            mantoResultCallBack.onSuccess(null);
                                        }
                                    });
                                    return;
                                }
                            } catch (JSONException unused) {
                                mantoResultCallBack.onFailed(null);
                                return;
                            }
                        }
                        mantoResultCallBack.onFailed(null);
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public void translateMarker(final MapProcessCenter mapProcessCenter, final MapView mapView, JSONObject jSONObject, final MantoResultCallBack mantoResultCallBack) {
                        jSONObject.optString("markerId");
                        mapProcessCenter.getTranslateMarkerList(jSONObject);
                        MantoThreadUtils.runOnUIThread(new Runnable
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0014: INVOKE 
                              (wrap: java.lang.Runnable : 0x0011: CONSTRUCTOR 
                              (r8v0 'this' com.jd.manto.map.JsApiMapView A[IMMUTABLE_TYPE, THIS])
                              (r3 I:java.util.List A[DONT_GENERATE, DONT_INLINE, REMOVE])
                              (r9v0 'mapProcessCenter' com.jd.manto.map.MapProcessCenter A[DONT_INLINE])
                              (r5 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                              (r10v0 'mapView' com.tencent.tencentmap.mapsdk.maps.MapView A[DONT_INLINE])
                              (r12v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                             A[MD:(com.jd.manto.map.JsApiMapView, java.util.List, com.jd.manto.map.MapProcessCenter, java.lang.String, com.tencent.tencentmap.mapsdk.maps.MapView, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void (m), WRAPPED] (LINE:3) call: com.jd.manto.map.JsApiMapView.3.<init>(com.jd.manto.map.JsApiMapView, java.util.List, com.jd.manto.map.MapProcessCenter, java.lang.String, com.tencent.tencentmap.mapsdk.maps.MapView, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void type: CONSTRUCTOR)
                             type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:3) in method: com.jd.manto.map.JsApiMapView.translateMarker(com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
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
                            */
                        /*
                            this = this;
                            java.lang.String r0 = "markerId"
                            java.lang.String r5 = r11.optString(r0)
                            java.util.List r3 = r9.getTranslateMarkerList(r11)
                            com.jd.manto.map.JsApiMapView$3 r11 = new com.jd.manto.map.JsApiMapView$3
                            r1 = r11
                            r2 = r8
                            r4 = r9
                            r6 = r10
                            r7 = r12
                            r1.<init>()
                            com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r11)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.JsApiMapView.translateMarker(com.jd.manto.map.MapProcessCenter, com.tencent.tencentmap.mapsdk.maps.MapView, org.json.JSONObject, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public MantoLifecycleLisener addLifecycleLisener(Bundle bundle) {
                        return this.mantoLifecycleLisener;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public View getCustomView(MantoCore mantoCore) {
                        Context g2 = a.g();
                        return new CoverViewContainer(g2, new MapAndProcessCombineView(g2));
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public int getInsertIndex() {
                        return 1;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
                    public String getModuleName() {
                        return "Map";
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public int getRemoveIndex() {
                        return 2;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public int getUpdateIndex() {
                        return 3;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public String getViewName() {
                        return "Map";
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public final Bundle handleInsertData(MantoCore mantoCore, JSONObject jSONObject) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("mapId"));
                        bundle.putBoolean("abg", true);
                        bundle.putBoolean("enableLongClick", false);
                        bundle.putString("json", jSONObject.toString());
                        return bundle;
                    }

                    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                    public void handleMethod(final String str, final View view, final MantoCore mantoCore, Bundle bundle, final MantoResultCallBack mantoResultCallBack) {
                        final Bundle bundle2 = new Bundle(2);
                        bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
                        try {
                            new JSONObject(bundle.getString("json"));
                            if (mantoCore != null && !mantoCore.isFinishing()) {
                                bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
                                bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
                                mantoCore.getActivity();
                                MantoThreadUtils.runOnUIThread(new Runnable
                                /*  JADX ERROR: Method code generation error
                                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0038: INVOKE 
                                      (wrap: java.lang.Runnable : 0x0035: CONSTRUCTOR 
                                      (r10v0 'this' com.jd.manto.map.JsApiMapView A[IMMUTABLE_TYPE, THIS])
                                      (r3 I:org.json.JSONObject A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                      (r12v0 'view' android.view.View A[DONT_INLINE])
                                      (r5v0 'bundle2' android.os.Bundle A[DONT_INLINE])
                                      (r15v0 'mantoResultCallBack' com.jingdong.manto.jsapi.openmodule.MantoResultCallBack A[DONT_INLINE])
                                      (r11v0 'str' java.lang.String A[DONT_INLINE])
                                      (r13v0 'mantoCore' com.jingdong.manto.MantoCore A[DONT_INLINE])
                                      (r9 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                     A[MD:(com.jd.manto.map.JsApiMapView, org.json.JSONObject, android.view.View, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack, java.lang.String, com.jingdong.manto.MantoCore, int):void (m), WRAPPED] (LINE:8) call: com.jd.manto.map.JsApiMapView.2.<init>(com.jd.manto.map.JsApiMapView, org.json.JSONObject, android.view.View, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack, java.lang.String, com.jingdong.manto.MantoCore, int):void type: CONSTRUCTOR)
                                     type: STATIC call: com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:8) in method: com.jd.manto.map.JsApiMapView.handleMethod(java.lang.String, android.view.View, com.jingdong.manto.MantoCore, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void, file: classes17.dex
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                                    */
                                /*
                                    this = this;
                                    java.lang.String r0 = "message"
                                    android.os.Bundle r5 = new android.os.Bundle
                                    r1 = 2
                                    r5.<init>(r1)
                                    java.lang.String r1 = "component_hashcode"
                                    int r9 = r14.getInt(r1)
                                    org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L45
                                    java.lang.String r2 = "json"
                                    java.lang.String r2 = r14.getString(r2)     // Catch: org.json.JSONException -> L45
                                    r3.<init>(r2)     // Catch: org.json.JSONException -> L45
                                    if (r13 == 0) goto L3c
                                    boolean r2 = r13.isFinishing()
                                    if (r2 == 0) goto L22
                                    goto L3c
                                L22:
                                    java.lang.String r0 = "viewID"
                                    r14.getInt(r0)
                                    r14.getInt(r1)
                                    r13.getActivity()
                                    com.jd.manto.map.JsApiMapView$2 r14 = new com.jd.manto.map.JsApiMapView$2
                                    r1 = r14
                                    r2 = r10
                                    r4 = r12
                                    r6 = r15
                                    r7 = r11
                                    r8 = r13
                                    r1.<init>()
                                    com.jingdong.manto.utils.MantoThreadUtils.runOnUIThread(r14)
                                    return
                                L3c:
                                    java.lang.String r11 = "page is finish."
                                    r5.putString(r0, r11)
                                    r15.onFailed(r5)
                                    return
                                L45:
                                    java.lang.String r11 = "json parser error"
                                    r5.putString(r0, r11)
                                    r15.onFailed(r5)
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.JsApiMapView.handleMethod(java.lang.String, android.view.View, com.jingdong.manto.MantoCore, android.os.Bundle, com.jingdong.manto.jsapi.openmodule.MantoResultCallBack):void");
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                            public Bundle handleRemoveData(MantoCore mantoCore, JSONObject jSONObject) {
                                Bundle bundle = new Bundle();
                                bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("mapId"));
                                bundle.putString("json", jSONObject.toString());
                                return bundle;
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                            public Bundle handleUpdateData(MantoCore mantoCore, JSONObject jSONObject) {
                                Bundle bundle = new Bundle();
                                bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("mapId"));
                                bundle.putBoolean("abg", true);
                                bundle.putBoolean("abi", false);
                                bundle.putString("json", jSONObject.toString());
                                return bundle;
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
                            public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
                                Bundle bundle = new Bundle(1);
                                bundle.putInt(AbstractMantoViewManager.VIEW_ID_KEY, jSONObject.optInt("mapId", 0));
                                bundle.putString("json", jSONObject == null ? "" : jSONObject.toString());
                                return bundle;
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                            protected void injectJsApiMethod(List<JsApiMethod> list) {
                                list.add(new JsApiMethod(INCLUDE_POINTS_NAME, 3, 5));
                                list.add(new JsApiMethod(REMOVE_MARK_NAME, 4, 5));
                                list.add(new JsApiMethod(ADD_MARK_NAME, 5, 5));
                                list.add(new JsApiMethod(ADD_LINE_NAME, 6, 5));
                                list.add(new JsApiMethod(ADD_CIRCLE_NAME, 7, 5));
                                list.add(new JsApiMethod(ADD_CTRL_NAME, 8, 5));
                                list.add(new JsApiMethod(GET_MAP_CENTER, 10, 5));
                                list.add(new JsApiMethod(GET_MAP_REGION, 11, 5));
                                list.add(new JsApiMethod(GET_MAP_SCALE, 12, 5));
                                list.add(new JsApiMethod(MOVE_TO_LOCATION, 15, 5));
                                list.add(new JsApiMethod(GET_MAP_SKEW, 14, 5));
                                list.add(new JsApiMethod(TRANSLATE_MARKER, 16, 5));
                                list.add(new JsApiMethod(SET_CENTER_OFFSET, 17, 5));
                                list.add(new JsApiMethod(GET_MAP_ROTATE, 13, 5));
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                            public boolean onViewInsert(Bundle bundle, View view, MantoCore mantoCore) {
                                int i2 = bundle.getInt("hashCode");
                                int i3 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
                                CoverViewContainer coverViewContainer = (CoverViewContainer) view;
                                MapAndProcessCombineView mapAndProcessCombineView = (MapAndProcessCombineView) coverViewContainer.convertTo(MapAndProcessCombineView.class);
                                MapProcessCenter mapProcessCenter = new MapProcessCenter(mantoCore.getActivity(), coverViewContainer, mapAndProcessCombineView.getMapView(), this);
                                mapProcessCenter.setEventInAddView(mantoCore, i3, i2);
                                mapAndProcessCombineView.setMapProcessCenter(mapProcessCenter);
                                this.combineViewMap.put(String.format("%s_%s", Integer.valueOf(i2), Integer.valueOf(i3)), mapAndProcessCombineView);
                                return insertOrUpdateMap(mantoCore, bundle, view, true);
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                            public boolean onViewRemove(Bundle bundle, View view, MantoCore mantoCore) {
                                if (bundle != null) {
                                    int i2 = bundle.getInt(AbstractMantoViewManager.VIEW_ID_KEY);
                                    int i3 = bundle.getInt("hashCode");
                                    MapAndProcessCombineView mapAndProcessCombineView = this.combineViewMap.get(String.format("%s_%s", Integer.valueOf(i3), Integer.valueOf(i2)));
                                    if (mapAndProcessCombineView == null) {
                                        return false;
                                    }
                                    MapProcessCenter mapProcessCenter = mapAndProcessCombineView.getMapProcessCenter();
                                    if (mapProcessCenter != null) {
                                        mapProcessCenter.onDestroy();
                                        this.combineViewMap.remove(String.format("%s_%s", Integer.valueOf(i3), Integer.valueOf(i2)));
                                    }
                                    return true;
                                }
                                return false;
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                            public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle) {
                                return insertOrUpdateMap(mantoCore, bundle, view, false);
                            }

                            @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager
                            public boolean onViewUpdate(MantoCore mantoCore, View view, Bundle bundle, MantoCallback mantoCallback) {
                                return false;
                            }
                        }
