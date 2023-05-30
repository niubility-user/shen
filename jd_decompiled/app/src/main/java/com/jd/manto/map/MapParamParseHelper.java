package com.jd.manto.map;

import android.graphics.Color;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jd.manto.map.Beans;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.manto.utils.MantoUtils;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes17.dex */
final class MapParamParseHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.MapInfo diffMapInfo(Beans.MapInfo mapInfo, Beans.MapInfo mapInfo2, Beans.UpdateMapInfo updateMapInfo) {
        if (updateMapInfo.updatePoints) {
            mapInfo.includePoints = mapInfo2.includePoints;
        }
        if (updateMapInfo.updateCtrls) {
            mapInfo.controlInfos = mapInfo2.controlInfos;
        }
        if (updateMapInfo.updateCircles) {
            mapInfo.circleInfos = mapInfo2.circleInfos;
        }
        if (updateMapInfo.updateLine) {
            mapInfo.polylines = mapInfo2.polylines;
        }
        if (updateMapInfo.updateMarkers) {
            mapInfo.markers = mapInfo2.markers;
        }
        if (updateMapInfo.updateZoom) {
            mapInfo.enableZoom = mapInfo2.enableZoom;
        }
        if (updateMapInfo.updateTraffic) {
            mapInfo.enableTraffic = mapInfo2.enableTraffic;
        }
        if (updateMapInfo.updateSatellite) {
            mapInfo.enableSatellite = mapInfo2.enableSatellite;
        }
        if (updateMapInfo.update3D) {
            mapInfo.enable3D = mapInfo2.enable3D;
        }
        if (updateMapInfo.updateShowCompass) {
            mapInfo.showCompass = mapInfo2.showCompass;
        }
        if (updateMapInfo.updateRotate) {
            mapInfo.enableRotate = mapInfo2.enableRotate;
        }
        if (updateMapInfo.updateScroll) {
            mapInfo.enableScroll = mapInfo2.enableScroll;
        }
        if (updateMapInfo.updateCoordinate) {
            mapInfo.centerLongitude = mapInfo2.centerLongitude;
            mapInfo.centerLatitude = mapInfo2.centerLatitude;
        }
        if (updateMapInfo.updateLoc) {
            mapInfo.showLocation = mapInfo2.showLocation;
        }
        if (updateMapInfo.updateTheme) {
            mapInfo.theme = mapInfo2.theme;
        }
        return mapInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.CircleInfo getCircleInfo(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Beans.CircleInfo circleInfo = new Beans.CircleInfo();
        circleInfo.latitude = jSONObject.optDouble(PdLVBody.LATITUDE, 0.0d);
        circleInfo.longitude = jSONObject.optDouble(PdLVBody.LONGITUDE, 0.0d);
        circleInfo.strokeColor = Tools.parseColor(jSONObject.optString("color", ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
        circleInfo.fillColor = Tools.parseColor(jSONObject.optString("fillColor", ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
        circleInfo.radius = jSONObject.optInt(JDPureVideoManager.SourceKey.RADIUS);
        circleInfo.strokeWidth = MantoUtils.getFloat(jSONObject.optString("strokeWidth"), 0.0f);
        return circleInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.ControlInfo getControlInfo(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Beans.ControlInfo controlInfo = new Beans.ControlInfo();
        controlInfo.clickable = jSONObject.optBoolean(JDPureVideoManager.SourceKey.CLICKABLE, false);
        controlInfo.iconPath = jSONObject.optString("iconPath", "");
        controlInfo.iconPath = jSONObject.optString("iconPath", "");
        controlInfo.id = jSONObject.optInt("id", 0);
        controlInfo.data = jSONObject.optString("data", "");
        JSONObject optJSONObject = jSONObject.optJSONObject("position");
        if (optJSONObject != null) {
            controlInfo.position = new Beans.Position(optJSONObject.optInt("left"), optJSONObject.optInt("top"), optJSONObject.optInt("width", 0), optJSONObject.optInt("height", 0));
        } else {
            controlInfo.position = new Beans.Position(0, 0, 0, 0);
        }
        return controlInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.LineInfo getLineInfo(JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject == null) {
            return null;
        }
        Beans.LineInfo lineInfo = new Beans.LineInfo();
        ArrayList arrayList = new ArrayList(8);
        JSONArray optJSONArray = jSONObject.optJSONArray("points");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                try {
                    jSONObject2 = optJSONArray.getJSONObject(i2);
                } catch (JSONException unused) {
                    jSONObject2 = null;
                }
                if (jSONObject2 != null) {
                    arrayList.add(new Beans.Point(jSONObject2.optDouble(PdLVBody.LATITUDE, 0.0d), jSONObject2.optDouble(PdLVBody.LONGITUDE, 0.0d)));
                }
            }
            lineInfo.points = arrayList;
            lineInfo.color = Tools.parseColor(jSONObject.optString("color", ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
            lineInfo.borderColor = Tools.parseColor(jSONObject.optString("borderColor", ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
            lineInfo.width = jSONObject.optInt("width", 0);
            lineInfo.dottedLine = jSONObject.optBoolean("dottedLine", false);
            lineInfo.borderWidth = jSONObject.optInt("borderWidth", 0);
            lineInfo.arrowLine = jSONObject.optBoolean("arrowLine", false);
            lineInfo.arrowIconPath = jSONObject.optString("arrowIconPath", "");
        }
        return lineInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.MapInfo getMapInfo(JSONObject jSONObject) {
        MapParamParseHelper mapParamParseHelper;
        if (jSONObject == null) {
            return null;
        }
        Beans.MapInfo mapInfo = new Beans.MapInfo();
        double optDouble = jSONObject.optDouble("centerLongitude", 0.0d);
        double optDouble2 = jSONObject.optDouble("centerLatitude", 0.0d);
        int optInt = jSONObject.optInt("scale", 16);
        boolean optBoolean = jSONObject.optBoolean("enableZoom", true);
        boolean optBoolean2 = jSONObject.optBoolean("enableScroll", true);
        boolean optBoolean3 = jSONObject.optBoolean("enableRotate", false);
        boolean optBoolean4 = jSONObject.optBoolean("showCompass", false);
        boolean optBoolean5 = jSONObject.optBoolean("enable3D", false);
        boolean optBoolean6 = jSONObject.optBoolean("enableOverlooking", false);
        boolean optBoolean7 = jSONObject.optBoolean("enableSatellite", false);
        boolean optBoolean8 = jSONObject.optBoolean("enableTraffic", false);
        boolean optBoolean9 = jSONObject.optBoolean("showLocation", false);
        String optString = jSONObject.optString(CustomThemeConstance.TABLE_NAME, "normal");
        mapInfo.centerLatitude = optDouble2;
        mapInfo.centerLongitude = optDouble;
        mapInfo.scale = optInt;
        mapInfo.enableZoom = optBoolean;
        mapInfo.enableScroll = optBoolean2;
        mapInfo.enableRotate = optBoolean3;
        mapInfo.showCompass = optBoolean4;
        mapInfo.enable3D = optBoolean5;
        mapInfo.enableOverlooking = optBoolean6;
        mapInfo.enableSatellite = optBoolean7;
        mapInfo.enableTraffic = optBoolean8;
        mapInfo.showLocation = optBoolean9;
        mapInfo.theme = optString;
        JSONArray optJSONArray = jSONObject.optJSONArray("covers");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    arrayList.add(new Beans.CoverInfo(optJSONObject.optDouble(PdLVBody.LATITUDE, 0.0d), optJSONObject.optDouble(PdLVBody.LONGITUDE, 0.0d), optJSONObject.optString("iconPath", ""), Tools.parseFloat(optJSONObject.optString("rotate"), 0.0f)));
                }
            }
            mapInfo.coverInfos = arrayList;
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("markers");
        if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
            mapParamParseHelper = this;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                arrayList2.add(getMarkerInfo(optJSONArray2.optJSONObject(i3)));
            }
            mapParamParseHelper = this;
            mapInfo.markers = arrayList2;
        }
        JSONArray optJSONArray3 = jSONObject.optJSONArray("polyline");
        if (optJSONArray3 != null && optJSONArray3.length() > 0) {
            ArrayList arrayList3 = new ArrayList();
            for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                arrayList3.add(mapParamParseHelper.getLineInfo(optJSONArray3.optJSONObject(i4)));
            }
            mapInfo.polylines = arrayList3;
        }
        JSONArray optJSONArray4 = jSONObject.optJSONArray("circles");
        if (optJSONArray4 != null && optJSONArray4.length() > 0) {
            ArrayList arrayList4 = new ArrayList();
            for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                arrayList4.add(mapParamParseHelper.getCircleInfo(optJSONArray4.optJSONObject(i5)));
            }
            mapInfo.circleInfos = arrayList4;
        }
        JSONArray optJSONArray5 = jSONObject.optJSONArray("controls");
        if (optJSONArray5 != null && optJSONArray5.length() > 0) {
            ArrayList arrayList5 = new ArrayList();
            for (int i6 = 0; i6 < optJSONArray5.length(); i6++) {
                arrayList5.add(mapParamParseHelper.getControlInfo(optJSONArray5.optJSONObject(i6)));
            }
            mapInfo.controlInfos = arrayList5;
        }
        JSONArray optJSONArray6 = jSONObject.optJSONArray("includePoints");
        if (optJSONArray6 != null && optJSONArray6.length() > 0) {
            ArrayList arrayList6 = new ArrayList();
            for (int i7 = 0; i7 < optJSONArray6.length(); i7++) {
                JSONObject optJSONObject2 = optJSONArray6.optJSONObject(i7);
                if (optJSONObject2 != null) {
                    arrayList6.add(new Beans.Point(optJSONObject2.optDouble(PdLVBody.LATITUDE, 0.0d), optJSONObject2.optDouble(PdLVBody.LONGITUDE, 0.0d)));
                }
            }
            mapInfo.includePoints = arrayList6;
        }
        return mapInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0166  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.jd.manto.map.Beans.MarkInfo getMarkerInfo(org.json.JSONObject r20) {
        /*
            Method dump skipped, instructions count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.manto.map.MapParamParseHelper.getMarkerInfo(org.json.JSONObject):com.jd.manto.map.Beans$MarkInfo");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.Point getPoint(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new Beans.Point(jSONObject.optDouble(PdLVBody.LATITUDE, 0.0d), jSONObject.optDouble(PdLVBody.LONGITUDE, 0.0d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.TranslateMarkerInfo getTranslateMarkerInfo(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Beans.TranslateMarkerInfo translateMarkerInfo = new Beans.TranslateMarkerInfo();
        translateMarkerInfo.duration = jSONObject.optInt("duration", 0);
        translateMarkerInfo.rotate = Tools.parseFloat(jSONObject.optString("rotate"), 0.0f);
        translateMarkerInfo.latitude = jSONObject.optDouble(PdLVBody.LATITUDE, 0.0d);
        translateMarkerInfo.longitude = jSONObject.optDouble(PdLVBody.LONGITUDE, 0.0d);
        return translateMarkerInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Beans.UpdateMapInfo getUpdateMapInfo(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Beans.UpdateMapInfo updateMapInfo = new Beans.UpdateMapInfo();
        double optDouble = jSONObject.optDouble("centerLongitude", 0.0d);
        if (jSONObject.optDouble("centerLatitude", 0.0d) != 0.0d && optDouble != 0.0d) {
            updateMapInfo.updateCoordinate = true;
        }
        if (jSONObject.has("enableZoom")) {
            updateMapInfo.updateZoom = true;
        }
        if (jSONObject.has("enableScroll")) {
            updateMapInfo.updateScroll = true;
        }
        if (jSONObject.has("enableRotate")) {
            updateMapInfo.updateRotate = true;
        }
        if (jSONObject.has("showCompass")) {
            updateMapInfo.updateShowCompass = true;
        }
        if (jSONObject.has("enable3D")) {
            updateMapInfo.update3D = true;
        }
        jSONObject.has("enableOverlooking");
        if (jSONObject.has("enableSatellite")) {
            updateMapInfo.updateSatellite = true;
        }
        if (jSONObject.has("enableTraffic")) {
            updateMapInfo.updateTraffic = true;
        }
        if (jSONObject.has("showLocation")) {
            updateMapInfo.updateLoc = true;
        }
        if (jSONObject.has(CustomThemeConstance.TABLE_NAME)) {
            updateMapInfo.updateTheme = true;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("markers");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            updateMapInfo.updateMarkers = true;
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("polyline");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            updateMapInfo.updateLine = true;
        }
        JSONArray optJSONArray3 = jSONObject.optJSONArray("circles");
        if (optJSONArray3 != null && optJSONArray3.length() > 0) {
            updateMapInfo.updateCircles = true;
        }
        JSONArray optJSONArray4 = jSONObject.optJSONArray("controls");
        if (optJSONArray4 != null && optJSONArray4.length() > 0) {
            updateMapInfo.updateCtrls = true;
        }
        JSONArray optJSONArray5 = jSONObject.optJSONArray("includePoints");
        if (optJSONArray5 != null && optJSONArray5.length() > 0) {
            updateMapInfo.updatePoints = true;
        }
        return updateMapInfo;
    }
}
