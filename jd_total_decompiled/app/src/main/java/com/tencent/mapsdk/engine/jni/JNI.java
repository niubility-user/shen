package com.tencent.mapsdk.engine.jni;

import android.graphics.Rect;
import androidx.annotation.Keep;
import com.tencent.map.lib.JNIInterface;
import com.tencent.map.lib.callbacks.TileOverlayCallback;
import com.tencent.map.lib.models.AggregationOverlayInfo;
import com.tencent.map.lib.models.AnnocationText;
import com.tencent.map.lib.models.AnnocationTextResult;
import com.tencent.map.lib.models.ArcLineOverlayInfo;
import com.tencent.map.lib.models.CircleInfo;
import com.tencent.map.lib.models.CityTrafficInfo;
import com.tencent.map.lib.models.GLModelInfo;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.lib.models.GroundOverlayInfo;
import com.tencent.map.lib.models.HeatmapInfo;
import com.tencent.map.lib.models.IndoorCellInfo;
import com.tencent.map.lib.models.IntersectionOverlayInfo;
import com.tencent.map.lib.models.MarkerInfo;
import com.tencent.map.lib.models.MaskLayer;
import com.tencent.map.lib.models.PolygonInfo;
import com.tencent.map.lib.models.ScatterPlotInfo;
import com.tencent.map.lib.models.TrailOverlayInfo;
import com.tencent.mapsdk.internal.ae;
import com.tencent.mapsdk.internal.de;
import com.tencent.mapsdk.internal.fe;
import com.tencent.mapsdk.internal.ge;
import com.tencent.mapsdk.internal.ke;
import com.tencent.mapsdk.internal.la;
import com.tencent.mapsdk.internal.le;
import com.tencent.mapsdk.internal.ma;
import com.tencent.mapsdk.internal.me;
import com.tencent.mapsdk.internal.ne;
import com.tencent.mapsdk.internal.oe;
import com.tencent.mapsdk.internal.pe;
import com.tencent.mapsdk.internal.r1;
import com.tencent.mapsdk.internal.w;
import com.tencent.mapsdk.shell.events.EngineWriteDataModel;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import com.tencent.tencentmap.mapsdk.maps.model.TrafficStyle;
import com.tencent.tencentmap.mapsdk.maps.model.VectorHeatAggregationUnit;
import java.util.ArrayList;
import java.util.List;

@Keep
/* loaded from: classes9.dex */
public class JNI {
    private JNICallback mCallback;
    private JNIInterface mJNIInterface;

    public static synchronized void nativeEndProfile() {
        synchronized (JNI.class) {
            JNIInterface.nativeEndProfile();
        }
    }

    public static synchronized void nativeStartProfile() {
        synchronized (JNI.class) {
            JNIInterface.nativeStartProfile();
        }
    }

    public int addLineText(long j2, GeoPoint[] geoPointArr, PolylineOptions.Text text) {
        return this.mJNIInterface.addLineText(j2, geoPointArr, text);
    }

    public boolean checkMapLoadFinishedTask(long j2, int i2) {
        return this.mJNIInterface.checkMapLoadFinishedTask(j2, i2);
    }

    public void destory() {
        this.mCallback.destory();
        this.mCallback = null;
        this.mJNIInterface = null;
    }

    public VectorHeatAggregationUnit getAggregationUnit(long j2, long j3, LatLng latLng) {
        return this.mJNIInterface.nativeGetAggregationUnit(j2, j3, latLng);
    }

    public int getIndoorOutlineZoom(long j2, String str) {
        return this.mJNIInterface.getIndoorOutlineZoom(j2, str);
    }

    public String getMapEngineRenderStatus(long j2) {
        return this.mJNIInterface.getMapEngineRenderStatus(j2);
    }

    public void initCallback(ae aeVar, w wVar, ge geVar, fe feVar, oe oeVar, ke keVar, ne neVar, r1 r1Var, pe peVar, me meVar, de deVar) {
        JNICallback jNICallback = new JNICallback(aeVar, wVar, geVar, feVar, oeVar, keVar, neVar, r1Var, peVar, meVar, deVar);
        this.mCallback = jNICallback;
        this.mJNIInterface = new JNIInterface(jNICallback);
    }

    public long nativeAddAggregatioinOverlay(long j2, AggregationOverlayInfo aggregationOverlayInfo) {
        return this.mJNIInterface.nativeAddAggregationOverlay(j2, aggregationOverlayInfo);
    }

    public long nativeAddArcLineOverlay(long j2, ArcLineOverlayInfo arcLineOverlayInfo) {
        return this.mJNIInterface.nativeAddArcLineOverlay(j2, arcLineOverlayInfo);
    }

    public int nativeAddCircle(long j2, CircleInfo circleInfo) {
        return this.mJNIInterface.nativeAddCircle(j2, circleInfo);
    }

    public long nativeAddGLModel(long j2, GLModelInfo gLModelInfo) {
        return this.mJNIInterface.nativeAddGLModel(j2, gLModelInfo);
    }

    public long nativeAddGroundOverlay(long j2, GroundOverlayInfo groundOverlayInfo) {
        return this.mJNIInterface.nativeAddGroundOverlay(j2, groundOverlayInfo);
    }

    public long nativeAddHeatmapOverlay(long j2, HeatmapInfo heatmapInfo) {
        return this.mJNIInterface.nativeAddHeatmapOverlay(j2, heatmapInfo);
    }

    public long nativeAddIntersectionOverlay(long j2, IntersectionOverlayInfo intersectionOverlayInfo) {
        return this.mJNIInterface.nativeAddIntersectionOverlay(j2, intersectionOverlayInfo);
    }

    public int nativeAddMarker(long j2, String str, double d, double d2, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i2, int i3) {
        return this.mJNIInterface.nativeAddMarker(j2, str, d, d2, f2, f3, f4, f5, f6, f7, z, z2, z3, z4, z5, i2, i3);
    }

    public long nativeAddMarker2(long j2, MarkerInfo markerInfo) {
        return this.mJNIInterface.nativeAddMarker2(j2, markerInfo);
    }

    public int nativeAddMaskLayer(long j2, MaskLayer maskLayer) {
        return this.mJNIInterface.nativeAddMaskLayer(j2, maskLayer);
    }

    public int nativeAddPolygon(long j2, PolygonInfo polygonInfo) {
        return this.mJNIInterface.nativeAddPolygon(j2, polygonInfo);
    }

    public void nativeAddRouteNameSegments(long j2, byte[][] bArr, int i2, GeoPoint[] geoPointArr, int i3) {
        this.mJNIInterface.nativeAddRouteNameSegments(j2, bArr, i2, geoPointArr, i3);
    }

    public long nativeAddScatterOverlay(long j2, ScatterPlotInfo scatterPlotInfo) {
        return this.mJNIInterface.nativeAddScatterPlotOverlay(j2, scatterPlotInfo);
    }

    public int nativeAddTileOverlay(long j2, TileOverlayCallback tileOverlayCallback, boolean z) {
        return this.mJNIInterface.nativeAddTileOverlay(j2, tileOverlayCallback, z);
    }

    public long nativeAddTrailOverlay(long j2, TrailOverlayInfo trailOverlayInfo) {
        return this.mJNIInterface.nativeAddTrailOverlay(j2, trailOverlayInfo);
    }

    public void nativeBringElementAbove(long j2, int i2, int i3) {
        this.mJNIInterface.nativeBringElementAbove(j2, i2, i3);
    }

    public void nativeBringElementBelow(long j2, int i2, int i3) {
        this.mJNIInterface.nativeBringElementBelow(j2, i2, i3);
    }

    public void nativeCheckTrafficBlockCache(long j2, int i2, int i3, int i4, int i5, int i6) {
        this.mJNIInterface.nativeCheckTrafficBlockCache(j2, i2, i3, i4, i5, i6);
    }

    public void nativeCheckTrafficBlockCacheForReplay(long j2, int i2, int i3, int i4, int i5, int i6) {
        this.mJNIInterface.nativeCheckTrafficBlockCacheForReplay(j2, i2, i3, i4, i5, i6);
    }

    public int nativeClearCache(long j2) {
        return this.mJNIInterface.nativeClearCache(j2);
    }

    public void nativeClearDownloadURLCache(long j2) {
        this.mJNIInterface.nativeClearDownloadURLCache(j2);
    }

    public void nativeClearRouteNameSegments(long j2) {
        this.mJNIInterface.nativeClearRouteNameSegments(j2);
    }

    public AnnocationTextResult nativeCreateAnnotationTextBitmap(long j2, AnnocationText annocationText) {
        return this.mJNIInterface.nativeCreateAnnotationText(j2, annocationText);
    }

    public int nativeCreateOrUpdateLine(long j2, int i2, int[] iArr, int[] iArr2, GeoPoint[] geoPointArr, String str, float f2, int i3, boolean z, boolean z2, boolean z3, boolean z4, float f3, boolean z5, int[] iArr3, int[] iArr4, float f4, int[] iArr5, float f5, int i4, boolean z6) {
        ma.a(la.f16819f, "create or update line = " + i2 + " p:" + geoPointArr.length + " w:" + f2 + " v:" + z6 + " a:" + f5 + " bw:" + f4);
        return this.mJNIInterface.nativeCreateOrUpdateLine(j2, i2, iArr, iArr2, geoPointArr, str, f2, i3, z, z2, z3, z4, (int) f3, z5, iArr3, iArr4, f4, iArr5, f5, i4, z6);
    }

    public void nativeDeleteCircle(long j2, int i2) {
        this.mJNIInterface.nativeDeleteCircle(j2, i2);
    }

    public void nativeDeleteIcons(long j2, int[] iArr, int i2) {
        this.mJNIInterface.nativeDeleteIcons(j2, iArr, i2);
    }

    public void nativeDeleteLine(long j2, long j3, boolean z) {
        this.mJNIInterface.nativeDeleteLine(j2, j3, z);
    }

    public void nativeDeletePolygon(long j2, int i2, int i3) {
        this.mJNIInterface.nativeDeletePolygon(j2, i2, i3);
    }

    public void nativeDestroyEngine(long j2) {
        this.mJNIInterface.nativeDestroyEngine(j2);
    }

    public boolean nativeDrawFrame(long j2) {
        return this.mJNIInterface.nativeDrawFrame(j2);
    }

    public void nativeEnableBaseMap(long j2, boolean z) {
        this.mJNIInterface.nativeEnableBaseMap(j2, z);
    }

    public void nativeEnableBuilding(long j2, boolean z) {
        this.mJNIInterface.nativeEnableBuilding(j2, z);
    }

    public void nativeEnablePOI(long j2, boolean z) {
        this.mJNIInterface.nativeEnablePOI(j2, z);
    }

    public int[] nativeFetchLackedTrafficBlocks(long j2) {
        return this.mJNIInterface.nativeFetchLackedTrafficBlocks(j2);
    }

    public void nativeFromScreenLocation(long j2, byte[] bArr, float f2, float f3, double[] dArr) {
        this.mJNIInterface.nativeFromScreenLocation(j2, bArr, f2, f3, dArr);
    }

    public float[] nativeGLProjectMatrix() {
        return this.mJNIInterface.nativeGLProjectMatrix();
    }

    public double[] nativeGLViewMatrix() {
        return this.mJNIInterface.nativeGLViewMatrix();
    }

    public float nativeGLViewScaleRatio() {
        return this.mJNIInterface.nativeGLViewScaleRatio();
    }

    public int[] nativeGLViewport() {
        return this.mJNIInterface.nativeGLViewport();
    }

    public boolean nativeGenerateTextures(long j2) {
        return this.mJNIInterface.nativeGenerateTextures(j2);
    }

    public String nativeGetActiveIndoorBuildingGUID(long j2) {
        return this.mJNIInterface.nativeGetActiveIndoorBuildingGUID(j2);
    }

    public boolean nativeGetAndResetDirty(long j2) {
        return this.mJNIInterface.nativeGetAndResetDirty(j2);
    }

    public String nativeGetBlockRouteInfo(long j2, int i2, int i3) {
        return this.mJNIInterface.nativeGetBlockRouteInfo(j2, i2, i3);
    }

    public void nativeGetCenterMapPoint(long j2, GeoPoint geoPoint) {
        this.mJNIInterface.nativeGetCenterMapPoint(j2, geoPoint);
    }

    public byte[] nativeGetCityName(long j2, GeoPoint geoPoint) {
        return this.mJNIInterface.nativeGetCityName(j2, geoPoint);
    }

    public String nativeGetCurIndoorName(long j2, GeoPoint geoPoint) {
        return this.mJNIInterface.nativeGetCurIndoorName(j2, geoPoint);
    }

    public int nativeGetCurrentMaterialVariant(long j2, long j3) {
        return this.mJNIInterface.nativeGetCurrentMaterialVariant(j2, j3);
    }

    public String nativeGetDataEngineVersion(long j2) {
        return this.mJNIInterface.nativeGetDataEngineVersion(j2);
    }

    public int nativeGetEngineId(long j2) {
        return this.mJNIInterface.nativeGetEngineId(j2);
    }

    public String nativeGetEngineLogInfo(long j2) {
        return this.mJNIInterface.nativeGetEngineLogInfo(j2);
    }

    public int nativeGetGLModelSkeletonAnimationCount(long j2, long j3) {
        return this.mJNIInterface.nativeGetGLModelSkeletonAnimationCount(j2, j3);
    }

    public float[] nativeGetGLModelSkeletonAnimationDuration(long j2, long j3) {
        return this.mJNIInterface.nativeGetGLModelSkeletonAnimationDuration(j2, j3);
    }

    public String[] nativeGetGLModelSkeletonAnimationName(long j2, long j3) {
        return this.mJNIInterface.nativeGetGLModelSkeletonAnimationName(j2, j3);
    }

    public Rect nativeGetIndoorBound(long j2) {
        return this.mJNIInterface.nativeGetIndoorBound(j2);
    }

    public int nativeGetIndoorCurrentFloorId(long j2) {
        return this.mJNIInterface.nativeGetIndoorCurrentFloorId(j2);
    }

    public String[] nativeGetIndoorFloorNames(long j2) {
        return this.mJNIInterface.nativeGetIndoorFloorNames(j2);
    }

    public int nativeGetLanguage(long j2) {
        return this.mJNIInterface.nativeGetLanguage(j2);
    }

    public String nativeGetMapEngineVersion(long j2) {
        return this.mJNIInterface.nativeGetDataEngineVersion(j2);
    }

    public int nativeGetMapStyle(long j2) {
        return this.mJNIInterface.nativeGetMapStyle(j2);
    }

    public ArrayList nativeGetPoisInScreen(long j2) {
        return this.mJNIInterface.nativeGetPoisInScreen(j2);
    }

    public float nativeGetRotate(long j2) {
        return this.mJNIInterface.nativeGetRotate(j2);
    }

    public double nativeGetScale(long j2) {
        return this.mJNIInterface.nativeGetScale(j2);
    }

    public int nativeGetScaleLevel(long j2) {
        return this.mJNIInterface.nativeGetScaleLevel(j2);
    }

    public float nativeGetSkew(long j2) {
        return this.mJNIInterface.nativeGetSkew(j2);
    }

    public double nativeGetTargetScale(long j2, Rect rect, Rect rect2) {
        return this.mJNIInterface.nativeGetTargetScale(j2, rect, rect2);
    }

    public boolean nativeGetTrafficCityInfo(long j2, String str, CityTrafficInfo cityTrafficInfo) {
        return this.mJNIInterface.nativeGetTrafficCityInfo(j2, str, cityTrafficInfo);
    }

    public String[] nativeGetVariantNames(long j2, long j3) {
        return this.mJNIInterface.nativeGetVariantNames(j2, j3);
    }

    public boolean nativeHasStreetRoad(long j2, String str) {
        return this.mJNIInterface.nativeHasStreetRoad(j2, str);
    }

    public void nativeHideCompass(long j2) {
        this.mJNIInterface.nativeHideCompass(j2);
    }

    public void nativeHideIcons(long j2, int[] iArr, int i2) {
        this.mJNIInterface.nativeHideIcons(j2, iArr, i2);
    }

    public void nativeHideStreetRoad(long j2) {
        this.mJNIInterface.nativeHideStreetRoad(j2);
    }

    public void nativeHideTraffic(long j2) {
        this.mJNIInterface.nativeHideTraffic(j2);
    }

    public void nativeIndoorBuildingEnabled(long j2, boolean z) {
        this.mJNIInterface.nativeIndoorBuildingEnabled(j2, z);
    }

    public long nativeInitEngine(String str, String str2, String str3, float f2, int i2, float f3, int[] iArr, boolean z, int i3) {
        return this.mJNIInterface.nativeInitEngine(str, str2, str3, f2, i2, f3, iArr, z, i3);
    }

    public int nativeIsCityHasTraffic(long j2, String str) {
        return this.mJNIInterface.nativeIsCityHasTraffic(j2, str);
    }

    @Deprecated
    public boolean nativeIsMapDrawFinished(long j2) {
        return this.mJNIInterface.nativeIsMapDrawFinished(j2);
    }

    public boolean nativeIsTileOverlayEnabled(long j2) {
        return this.mJNIInterface.nativeIsTileOverlayEnabled(j2);
    }

    public void nativeLineClearPoint(long j2, long j3, GeoPoint geoPoint, int i2) {
        this.mJNIInterface.nativeLineClearPoint(j2, j3, geoPoint, i2);
    }

    public void nativeLineInsertPoint(long j2, long j3, GeoPoint geoPoint, int i2) {
        this.mJNIInterface.nativeLineInsertPoint(j2, j3, geoPoint, i2);
    }

    public void nativeLoadBlockRouteCityList(long j2, int[] iArr, int[] iArr2, int i2) {
        this.mJNIInterface.nativeLoadBlockRouteCityList(j2, iArr, iArr2, i2);
    }

    public void nativeLockEngine(long j2) {
        this.mJNIInterface.nativeLockEngine(j2);
    }

    public void nativeMapLoadKMLFile(long j2, String str) {
        this.mJNIInterface.nativeMapLoadKMLFile(j2, str);
    }

    public void nativeMapSetSatelliteServerFullUrl(long j2, String str) {
        this.mJNIInterface.nativeMapSetSatelliteServerFullUrl(j2, str);
    }

    public float nativeMapSightGetOnScreenHeight(long j2) {
        return this.mJNIInterface.nativeMapSightGetOnScreenHeight(j2);
    }

    public void nativeMoveBy(long j2, float f2, float f3, boolean z) {
        this.mJNIInterface.nativeMoveBy(j2, f2, f3, z);
    }

    public boolean nativeNeedDispaly(long j2) {
        return this.mJNIInterface.nativeNeedDispaly(j2);
    }

    public boolean nativeNeedRedraw(long j2) {
        return this.mJNIInterface.nativeNeedRedraw(j2);
    }

    public byte[] nativeOnTap(long j2, float f2, float f3) {
        return this.mJNIInterface.nativeOnTap(j2, f2, f3);
    }

    public boolean nativeOnTapLine(long j2, float f2, float f3) {
        return this.mJNIInterface.nativeOnTapLine(j2, f2, f3);
    }

    public int nativeQueryCityCodeList(long j2, Rect rect, int i2, int[] iArr, int i3) {
        return this.mJNIInterface.nativeQueryCityCodeList(j2, rect, i2, iArr, i3);
    }

    public int nativeRefreshTrafficData(long j2, byte[] bArr, int i2, boolean z, boolean z2) {
        return this.mJNIInterface.nativeRefreshTrafficData(j2, bArr, i2, z, z2);
    }

    public void nativeReloadTileOverlay(long j2, int i2) {
        this.mJNIInterface.nativeReloadTileOverlay(j2, i2);
    }

    public void nativeRemoveEngineOverlay(long j2) {
        this.mJNIInterface.nativeRemoveEngineOverlay(j2);
    }

    public void nativeRemoveGLVisualizationOverlay(long j2, long j3) {
        this.mJNIInterface.nativeRemoveGLVisualizationOverlay(j2, j3);
    }

    public void nativeRemoveMaskLayer(long j2, int i2) {
        this.mJNIInterface.nativeRemoveMaskLayer(j2, i2);
    }

    public void nativeRemovePolygon(long j2, int i2, int i3) {
        this.mJNIInterface.nativeDeletePolygon(j2, i2, i3);
    }

    public void nativeRemoveTileOverlay(long j2, int i2) {
        this.mJNIInterface.nativeRemoveTileOverlay(j2, i2);
    }

    public void nativeResetEnginePath(long j2, String str, String str2, String str3) {
        this.mJNIInterface.nativeResetEnginePath(j2, str, str2, str3);
    }

    public void nativeResetIndoorCellInfo(long j2) {
        this.mJNIInterface.nativeResetIndoorCellInfo(j2);
    }

    public void nativeResetMonoColor(long j2, long j3) {
        this.mJNIInterface.nativeResetMonoColor(j2, j3);
    }

    public void nativeSetBlockRouteVisible(long j2, boolean z) {
        this.mJNIInterface.nativeSetBlockRouteVisible(j2, z);
    }

    public void nativeSetBuilding3DEffect(long j2, boolean z) {
        this.mJNIInterface.nativeSetBuilding3DEffect(j2, z);
    }

    public void nativeSetBuildingBlackList(long j2, LatLngBounds[] latLngBoundsArr) {
        this.mJNIInterface.nativeSetBuildingBlackList(j2, latLngBoundsArr);
    }

    public void nativeSetBuildingToSpecificFloor(long j2, String str, String str2) {
        this.mJNIInterface.nativeSetBuildingToSpecificFloor(j2, str, str2);
    }

    public void nativeSetCallback(long j2) {
        this.mJNIInterface.nativeSetCallback(j2);
    }

    public void nativeSetCenter(long j2, GeoPoint geoPoint, boolean z) {
        this.mJNIInterface.nativeSetCenter(j2, geoPoint, z);
    }

    public void nativeSetCenterMapPointAndScaleLevel(long j2, GeoPoint geoPoint, int i2, boolean z) {
        this.mJNIInterface.nativeSetCenterMapPointAndScaleLevel(j2, geoPoint, i2, z);
    }

    public void nativeSetCompassImage(long j2, String str) {
        this.mJNIInterface.nativeSetCompassImage(j2, str);
    }

    public void nativeSetCompassPosition(long j2, int i2, int i3) {
        this.mJNIInterface.nativeSetCompassPosition(j2, i2, i3);
    }

    public void nativeSetCompassVisible(long j2, boolean z) {
        this.mJNIInterface.nativeSetCompassVisible(j2, z);
    }

    public void nativeSetDrawCap(long j2, long j3, boolean z) {
        this.mJNIInterface.nativeSetDrawCap(j2, j3, z);
    }

    public void nativeSetFlagOfZoomToSpanForLocation(long j2, float f2, float f3, float f4, float f5) {
        this.mJNIInterface.nativeSetFlagOfZoomToSpanForLocation(j2, f2, f3, f4, f5);
    }

    public void nativeSetIconsHidden(long j2, int[] iArr, int i2, boolean z) {
        this.mJNIInterface.nativeSetIconsHidden(j2, iArr, i2, z);
    }

    public void nativeSetIndoorActiveScreenArea(long j2, float f2, float f3, float f4, float f5) {
        this.mJNIInterface.nativeSetIndoorActiveScreenArea(j2, f2, f3, f4, f5);
    }

    public void nativeSetIndoorBuildingPickEnabled(long j2, boolean z) {
        this.mJNIInterface.nativeSetIndoorBuildingPickEnabled(j2, z);
    }

    public void nativeSetIndoorBuildingStyle(long j2, int i2) {
        ma.a(la.f16819f, "nativeSetIndoorBuildingStyle:" + i2);
        this.mJNIInterface.nativeSetIndoorBuildingStyle(j2, i2);
    }

    public void nativeSetIndoorCellInfo(long j2, List<IndoorCellInfo> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (IndoorCellInfo indoorCellInfo : list) {
                if (indoorCellInfo != null && indoorCellInfo.getStyle() != null) {
                    arrayList.add(indoorCellInfo);
                }
            }
            this.mJNIInterface.nativeSetIndoorCellInfo(j2, (IndoorCellInfo[]) arrayList.toArray(new IndoorCellInfo[0]));
        }
    }

    public void nativeSetIndoorConfigType(long j2, int i2) {
        this.mJNIInterface.nativeSetIndoorConfigType(j2, i2);
    }

    public void nativeSetIndoorFloor(long j2, int i2) {
        this.mJNIInterface.nativeSetIndoorFloor(j2, i2);
    }

    public void nativeSetIndoorMaskColor(long j2, int i2) {
        this.mJNIInterface.nativeSetIndoorMaskColor(j2, i2);
    }

    public void nativeSetLanguage(long j2, int i2) {
        this.mJNIInterface.nativeSetLanguage(j2, i2);
    }

    public void nativeSetLineArrowSpacing(long j2, int i2, float f2) {
        this.mJNIInterface.nativeSetLineArrowSpacing(j2, i2, f2);
    }

    public void nativeSetLineDirectionArrowTextureName(long j2, long j3, String str) {
        this.mJNIInterface.nativeSetLineDirectionArrowTextureName(j2, j3, str);
    }

    public void nativeSetLineDrawArrow(long j2, long j3, boolean z) {
        this.mJNIInterface.nativeSetLineDrawArrow(j2, j3, z);
    }

    public void nativeSetLineFootPrintSpacing(long j2, int i2, float f2) {
        this.mJNIInterface.nativeSetLineFootPrintSpacing(j2, i2, f2);
    }

    public void nativeSetLineSelected(long j2, long j3, boolean z) {
        this.mJNIInterface.nativeSetLineSelected(j2, j3, z);
    }

    public void nativeSetLocationCircleColor(long j2, int i2) {
        this.mJNIInterface.nativeSetLocationCircleColor(j2, i2);
    }

    public void nativeSetLocationCircleHidden(long j2, boolean z) {
        this.mJNIInterface.nativeSetLocationCircleHidden(j2, z);
    }

    public void nativeSetLocationCompassGroupImages(long j2, String str, String str2, String str3, String str4, String str5) {
        this.mJNIInterface.nativeSetLocationCompassGroupImages(j2, str, str2, str3, str4, str5);
    }

    public void nativeSetLocationCompassMarkerHidden(long j2, boolean z) {
        this.mJNIInterface.nativeSetLocationCompassMarkerHidden(j2, z);
    }

    public void nativeSetLocationCompassMarkerImage(long j2, String str) {
        this.mJNIInterface.nativeSetLocationCompassMarkerImage(j2, str);
    }

    public void nativeSetLocationFollow(long j2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.mJNIInterface.nativeSetLocationFollow(j2, z, z2, z3, z4);
    }

    public void nativeSetLocationHeading(long j2, float f2) {
        this.mJNIInterface.nativeSetLocationHeading(j2, f2);
    }

    public void nativeSetLocationInfo(long j2, double d, double d2, float f2, float f3, boolean z) {
        this.mJNIInterface.nativeSetLocationInfo(j2, d, d2, f2, f3, z);
    }

    public void nativeSetLocationMarkerHidden(long j2, boolean z) {
        this.mJNIInterface.nativeSetLocationMarkerHidden(j2, z);
    }

    public int nativeSetLocationMarkerImage(long j2, String str, float f2, float f3) {
        return this.mJNIInterface.nativeSetLocationMarkerImage(j2, str, f2, f3);
    }

    public void nativeSetLocationRedLineHidden(long j2, boolean z) {
        this.mJNIInterface.nativeSetLocationRedLineHidden(j2, z);
    }

    public void nativeSetLocationRedLineInfo(long j2, float f2, int i2, LatLng latLng) {
        this.mJNIInterface.nativeSetLocationRedLineInfo(j2, f2, i2, latLng);
    }

    public void nativeSetMapFontSize(long j2, int i2) {
        this.mJNIInterface.nativeSetMapFontSize(j2, i2);
    }

    public void nativeSetMapParam(long j2, byte[] bArr) {
        this.mJNIInterface.nativeSetMapParam(j2, bArr);
    }

    public void nativeSetMapStyle(long j2, int i2, boolean z) {
        this.mJNIInterface.nativeSetMapStyle(j2, i2, z);
    }

    public void nativeSetMarkerMainSubRelation(long j2, int i2, int i3) {
        this.mJNIInterface.nativeSetMarkerMainSubRelation(j2, i2, i3);
    }

    public void nativeSetMarkerScaleLevelRange(long j2, int i2, int i3, int i4) {
        this.mJNIInterface.nativeSetMarkerScaleLevelRange(j2, i2, i3, i4);
    }

    public void nativeSetMaterialVariant(long j2, long j3, int i2) {
        this.mJNIInterface.nativeSetMaterialVariant(j2, j3, i2);
    }

    public void nativeSetMaxScaleLevel(long j2, int i2) {
        this.mJNIInterface.nativeSetMaxScaleLevel(j2, i2);
    }

    public void nativeSetMinScaleLevel(long j2, int i2) {
        this.mJNIInterface.nativeSetMinScaleLevel(j2, i2);
    }

    public void nativeSetMonoColor(long j2, long j3, float f2, float f3, float f4) {
        this.mJNIInterface.nativeSetMonoColor(j2, j3, f2, f3, f4);
    }

    public void nativeSetNeedDisplay(long j2, boolean z) {
        this.mJNIInterface.nativeSetNeedDisplay(j2, z);
    }

    public void nativeSetPolygonHidden(long j2, int i2, int i3, boolean z) {
        nativeSetIconsHidden(j2, new int[]{i2, i3}, 2, z);
    }

    public void nativeSetPriority(long j2, int i2, float f2) {
        this.mJNIInterface.nativeSetPriority(j2, i2, f2);
    }

    public void nativeSetRotate(long j2, float f2, boolean z) {
        this.mJNIInterface.nativeSetRotate(j2, f2, z);
    }

    public void nativeSetSatelliteEnabled(long j2, boolean z) {
        this.mJNIInterface.nativeSetSatelliteEnabled(j2, z);
    }

    public void nativeSetScale(long j2, double d, boolean z) {
        this.mJNIInterface.nativeSetScale(j2, d, z);
    }

    public void nativeSetScaleLevel(long j2, int i2, boolean z) {
        this.mJNIInterface.nativeSetScaleLevel(j2, i2, z);
    }

    public void nativeSetScreenCenterOffset(long j2, float f2, float f3, boolean z) {
        this.mJNIInterface.nativeSetScreenCenterOffset(j2, f2, f3, z);
    }

    public void nativeSetServerHost(long j2, String str) {
        this.mJNIInterface.nativeSetServerHost(j2, str);
    }

    public void nativeSetShowIndoorBuildingWhiteList(long j2, String[] strArr) {
        this.mJNIInterface.nativeSetShowIndoorBuildingWhiteList(j2, strArr);
    }

    public void nativeSetSkew(long j2, float f2, boolean z) {
        this.mJNIInterface.nativeSetSkew(j2, f2, z);
    }

    public void nativeSetTileOverlayDataLevelRange(long j2, int i2, int i3, int i4) {
        this.mJNIInterface.nativeSetTileOverlayDataLevelRange(j2, i2, i3, i4);
    }

    public void nativeSetTileOverlayEnabled(long j2, boolean z) {
        this.mJNIInterface.nativeSetTileOverlayEnabled(j2, z);
    }

    public void nativeSetTileOverlayPriority(long j2, int i2, int i3) {
        this.mJNIInterface.nativeSetTileOverlayPriority(j2, i2, i3);
    }

    public void nativeSetTrafficColor(long j2, int i2, int i3, int i4, int i5) {
        this.mJNIInterface.nativeSetTrafficColor(j2, i2, i3, i4, i5);
    }

    public void nativeSetTrafficMode(long j2, int i2, int i3) {
        this.mJNIInterface.nativeSetTrafficMode(j2, i2, i3);
    }

    public void nativeSetTurnArrow(long j2, long j3, List<GeoPoint> list, int i2, int i3) {
        if (list != null) {
            this.mJNIInterface.nativeSetTurnArrow(j2, j3, (GeoPoint[]) list.toArray(new GeoPoint[0]), i2, i3);
        }
    }

    public void nativeSetTurnArrowStyle(long j2, long j3, int i2, int i3) {
        this.mJNIInterface.nativeSetTurnArrowStyle(j2, j3, i2, i3);
    }

    public void nativeSetViewport(long j2, int i2, int i3, int i4, int i5) {
        this.mJNIInterface.nativeSetViewport(j2, i2, i3, i4, i5);
    }

    public void nativeShowStreetRoad(long j2) {
        this.mJNIInterface.nativeShowStreetRoad(j2);
    }

    public void nativeShowTraffic(long j2) {
        this.mJNIInterface.nativeShowTraffic(j2);
    }

    public void nativeStartGLModelSkeletonAnimation(long j2, long j3, int i2, float f2, boolean z) {
        this.mJNIInterface.nativeStartGLModelSkeletonAnimation(j2, j3, i2, f2, z);
    }

    public void nativeStopGLModelSkeletonAnimation(long j2, long j3) {
        this.mJNIInterface.nativeStopGLModelSkeletonAnimation(j2, j3);
    }

    public void nativeSwitchEngineForeGround(long j2, boolean z) {
        this.mJNIInterface.nativeSwitchEngineForeGround(j2, z);
    }

    public void nativeToScreenLocation(long j2, byte[] bArr, double d, double d2, float[] fArr) {
        this.mJNIInterface.nativeToScreenLocation(j2, bArr, d, d2, fArr);
    }

    public void nativeUnlockEngine(long j2) {
        this.mJNIInterface.nativeUnlockEngine(j2);
    }

    public void nativeUpdateAggregatioinOverlay(long j2, long j3, AggregationOverlayInfo aggregationOverlayInfo) {
        this.mJNIInterface.nativeUpdateAggregationOverlay(j2, j3, aggregationOverlayInfo);
    }

    public void nativeUpdateArcLineOverlay(long j2, long j3, ArcLineOverlayInfo arcLineOverlayInfo) {
        this.mJNIInterface.nativeUpdateArcLineOverlay(j2, j3, arcLineOverlayInfo);
    }

    public void nativeUpdateCircle(long j2, int i2, CircleInfo circleInfo) {
        this.mJNIInterface.nativeUpdateCircle(j2, i2, circleInfo);
    }

    public void nativeUpdateFrame(long j2, double d) {
        this.mJNIInterface.nativeUpdateFrame(j2, d);
    }

    public void nativeUpdateGLModel(long j2, long j3, GLModelInfo gLModelInfo) {
        this.mJNIInterface.nativeUpdateGLModel(j2, j3, gLModelInfo);
    }

    public void nativeUpdateGroundOverlay(long j2, long j3, GroundOverlayInfo groundOverlayInfo) {
        this.mJNIInterface.nativeUpdateGroundOverlay(j2, j3, groundOverlayInfo);
    }

    public void nativeUpdateHeatmapOverlay(long j2, long j3, HeatmapInfo heatmapInfo) {
        this.mJNIInterface.nativeUpdateHeatmapOverlay(j2, j3, heatmapInfo);
    }

    public void nativeUpdateIntersectionOverlay(long j2, IntersectionOverlayInfo intersectionOverlayInfo) {
        this.mJNIInterface.nativeUpdateIntersectionOverlay(j2, intersectionOverlayInfo);
    }

    public void nativeUpdateMapResource(long j2, String str) {
        this.mJNIInterface.nativeUpdateMapResource(j2, str);
    }

    public void nativeUpdateMarker(long j2, MarkerInfo markerInfo) {
        this.mJNIInterface.nativeUpdateMarker(j2, markerInfo);
    }

    public void nativeUpdateMarkerInfo(long j2, int i2, String str, double d, double d2, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i3, int i4) {
        this.mJNIInterface.nativeUpdateMarkerInfo(j2, i2, str, d, d2, f2, f3, f4, f5, f6, f7, z, z2, z3, z4, z5, i3, i4);
    }

    public void nativeUpdateMaskLayer(long j2, int i2, int i3) {
        this.mJNIInterface.nativeUpdateMaskLayer(j2, i2, i3);
    }

    public void nativeUpdatePolygon(long j2, int i2, int i3, PolygonInfo polygonInfo) {
        this.mJNIInterface.nativeUpdatePolygon(j2, i2, i3, polygonInfo);
    }

    public void nativeUpdateScatterPlotOverlay(long j2, long j3, ScatterPlotInfo scatterPlotInfo) {
        this.mJNIInterface.nativeUpdateScatterPlotOverlay(j2, j3, scatterPlotInfo);
    }

    public void nativeUpdateTrailOverlay(long j2, long j3, TrailOverlayInfo trailOverlayInfo) {
        this.mJNIInterface.nativeUpdateTrailOverlay(j2, j3, trailOverlayInfo);
    }

    public EngineWriteDataModel nativeWriteMapDataBlock(long j2, String str, byte[] bArr) {
        return this.mJNIInterface.nativeWriteMapDataBlock(j2, str, bArr);
    }

    public void nativeZoomIn(long j2, float f2, float f3) {
        this.mJNIInterface.nativeZoomIn(j2, f2, f3);
    }

    public void nativeZoomOut(long j2) {
        this.mJNIInterface.nativeZoomOut(j2);
    }

    public void nativeZoomToSpan(long j2, Rect rect, Rect rect2, boolean z) {
        this.mJNIInterface.nativeZoomToSpan(j2, rect, rect2, z);
    }

    public void nativeZoomToSpanForNavigation(long j2, GeoPoint geoPoint, int i2, int i3, boolean z) {
        this.mJNIInterface.nativeZoomToSpanForNavigation(j2, geoPoint, i2, i3, z);
    }

    public void registerCallback(long j2) {
        nativeSetCallback(j2);
    }

    public void removeLineText(long j2, int i2) {
        this.mJNIInterface.removeLineText(j2, i2);
    }

    public void scheduleClickOnNextRender(long j2, float f2, float f3) {
        this.mJNIInterface.nativeScheduleClickOnNextRender(j2, f2, f3);
    }

    public void setLineTextStyle(long j2, int i2, PolylineOptions.Text text) {
        this.mJNIInterface.setLineTextStyle(j2, i2, text);
    }

    public void setMapCallbackGetGLContext(le leVar) {
        JNICallback jNICallback = this.mCallback;
        if (jNICallback != null) {
            jNICallback.setMapCallbackGetGLContext(leVar);
        }
    }

    public void setRestrictBounds(long j2, double[] dArr, double[] dArr2, int i2) {
        this.mJNIInterface.setRestrictBounds(j2, dArr, dArr2, i2);
    }

    public void setTrafficStyle(long j2, TrafficStyle trafficStyle) {
        this.mJNIInterface.nativeSetTrafficStyle(j2, trafficStyle);
    }
}
