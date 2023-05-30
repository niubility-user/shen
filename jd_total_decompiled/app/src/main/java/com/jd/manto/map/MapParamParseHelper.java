package com.jd.manto.map;

import android.graphics.Color;
import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jd.manto.map.Beans;
import com.jingdong.common.jdreactFramework.views.pureVideo.JDPureVideoManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.manto.utils.MantoUtils;
import com.unionpay.tsmservice.mi.data.Constant;
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
    */
    public final Beans.MarkInfo getMarkerInfo(JSONObject jSONObject) {
        JSONObject jSONObject2;
        String str;
        Beans.MarkInfo markInfo;
        JSONObject optJSONObject;
        Beans.Anchor anchor;
        if (jSONObject == null) {
            return null;
        }
        Beans.MarkInfo markInfo2 = new Beans.MarkInfo();
        markInfo2.latitude = jSONObject.optDouble(PdLVBody.LATITUDE, 0.0d);
        markInfo2.longitude = jSONObject.optDouble(PdLVBody.LONGITUDE, 0.0d);
        markInfo2.title = jSONObject.optString("title", "");
        markInfo2.id = jSONObject.optInt("id", 0);
        markInfo2.rotate = Tools.parseFloat(jSONObject.optString("rotate"), 0.0f);
        markInfo2.alpha = Tools.parseFloat(jSONObject.optString("alpha"), 1.0f);
        markInfo2.zIndex = jSONObject.optInt("zIndex", 0);
        markInfo2.data = jSONObject.optString("data", "");
        String optString = jSONObject.optString("iconPath");
        float f2 = MantoUtils.getFloat(jSONObject.optString("width"), 0.0f);
        float f3 = MantoUtils.getFloat(jSONObject.optString("height"), 0.0f);
        if (!TextUtils.isEmpty(optString)) {
            markInfo2.iconPath = optString;
            markInfo2.width = f2;
            markInfo2.height = f3;
        }
        if (jSONObject.has("anchor")) {
            JSONObject optJSONObject2 = jSONObject.optJSONObject("anchor");
            if (optJSONObject2 != null) {
                anchor = new Beans.Anchor(MantoUtils.getFloat(optJSONObject2.optString(JshopConst.JSHOP_PROMOTIO_X), 0.5f), MantoUtils.getFloat(optJSONObject2.optString(JshopConst.JSHOP_PROMOTIO_Y), 1.0f));
            } else {
                anchor = new Beans.Anchor(0.5f, 1.0f);
            }
            markInfo2.anchor = anchor;
        }
        if (jSONObject.has("callout")) {
            try {
                jSONObject2 = new JSONObject(jSONObject.optString("callout"));
            } catch (JSONException unused) {
                jSONObject2 = null;
            }
            if (jSONObject2 != null) {
                Beans.CallOut callOut = new Beans.CallOut();
                callOut.content = jSONObject2.optString("content");
                str = "color";
                callOut.color = Tools.parseColor(jSONObject2.optString("color", JDDarkUtil.COLOR_0000000), Color.parseColor(JDDarkUtil.COLOR_0000000));
                callOut.fontSize = jSONObject2.optInt(ViewProps.FONT_SIZE, 12);
                callOut.borderRadius = jSONObject2.optInt("borderRadius", 0);
                callOut.bgColor = Tools.parseColor(jSONObject2.optString(DYConstants.DY_BG_COLOR, JDDarkUtil.COLOR_0000000), Color.parseColor(JDDarkUtil.COLOR_0000000));
                callOut.borderWidth = jSONObject2.optInt("borderWidth", 0);
                callOut.borderColor = Tools.parseColor(jSONObject2.optString("borderColor", JDDarkUtil.COLOR_FFFFFFF), Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
                callOut.padding = jSONObject2.optInt("padding", 0);
                callOut.display = jSONObject2.optInt(ViewProps.DISPLAY, 0);
                callOut.textAlign = jSONObject2.optString(ViewProps.TEXT_ALIGN, "");
                callOut.anchorX = jSONObject2.optInt("anchorX", 0);
                callOut.anchorY = jSONObject2.optInt("anchorY", 0);
                markInfo = markInfo2;
                markInfo.callOut = callOut;
                optJSONObject = jSONObject.optJSONObject(Constant.KEY_PROMOTION_LABEL);
                if (optJSONObject != null) {
                    Beans.Label label = new Beans.Label();
                    label.content = optJSONObject.optString("content", "");
                    label.color = Tools.parseColor(optJSONObject.optString(str, ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
                    label.fontSize = optJSONObject.optInt(ViewProps.FONT_SIZE, 12);
                    label.anchorX = Tools.parseFloat(optJSONObject.optString("anchorX"), 0.0f);
                    label.anchorY = Tools.parseFloat(optJSONObject.optString("anchorY"), 0.0f);
                    label.bgColor = Tools.parseColor(optJSONObject.optString(DYConstants.DY_BG_COLOR, ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
                    label.borderRadius = optJSONObject.optInt("borderRadius", 0);
                    label.borderWidth = optJSONObject.optInt("borderWidth", 0);
                    label.borderColor = Tools.parseColor(optJSONObject.optString("borderColor", ""), Color.parseColor(JDDarkUtil.COLOR_0000000));
                    label.textAlign = optJSONObject.optString(ViewProps.TEXT_ALIGN, "");
                    label.padding = optJSONObject.optInt("padding", 0);
                    markInfo.label = label;
                }
                return markInfo;
            }
        }
        str = "color";
        markInfo = markInfo2;
        optJSONObject = jSONObject.optJSONObject(Constant.KEY_PROMOTION_LABEL);
        if (optJSONObject != null) {
        }
        return markInfo;
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
