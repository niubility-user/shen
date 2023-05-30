package com.jd.manto.lbs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.manto.utils.MantoUtils;
import com.tencent.map.geolocation.TencentLocationUtils;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes17.dex */
public class MantoMapHelper {
    private static final int ALLOWED_SAME_POI_DISTANCE = 5;
    private static String META_NAME = "TencentMapSDK";
    private static String SN = "";
    private static String qqMapApiKey;

    public static String getQqMapApiKey(Context context) {
        if (TextUtils.isEmpty(qqMapApiKey)) {
            qqMapApiKey = MantoUtils.getMeta(META_NAME);
        }
        return qqMapApiKey;
    }

    public static String getSN() {
        return SN;
    }

    public static boolean isAmapMapInstalled(Context context) {
        return MantoUtils.isPackageInstalled("com.autonavi.minimap");
    }

    public static boolean isBaiDuMapInstalled(Context context) {
        return MantoUtils.isPackageInstalled("com.baidu.BaiduMap");
    }

    public static boolean isQQMapInstalled(Context context) {
        return MantoUtils.isPackageInstalled("com.tencent.map");
    }

    public static boolean isSamePOIByDistance(LatLng latLng, LatLng latLng2) {
        return (latLng == null || latLng2 == null || 5.0d <= TencentLocationUtils.distanceBetween(latLng.getLatitude(), latLng.getLongitude(), latLng2.getLatitude(), latLng2.getLongitude())) ? false : true;
    }

    public static void setSN(String str) {
        SN = str;
    }

    public static void startAmapMapNavigate(Activity activity, String str, double d, double d2, String str2, double d3, double d4) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("amapuri://route/plan?t=0&dev=0&sname=" + str + "&slat" + d + "&slon" + d2 + "&dname=" + str2 + "&dlat" + d3 + "&dlon" + d4 + "&sourceApplication=" + activity.getPackageName()));
        intent.setFlags(268435456);
        try {
            activity.startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    public static void startBaiDuMapNavigate(Activity activity, String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/direction?mode=driving&origin=name:" + str + "|latlng:" + str2 + "&destination=name:" + str3 + "|latlng:" + str4 + "&src=" + activity.getPackageName() + "&coord_type=" + str5));
        intent.setFlags(268435456);
        try {
            activity.startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    public static void startQQMapNavigate(Activity activity, String str, String str2, String str3, String str4) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("qqmap://map/routeplan?type=drive&from=" + str + "&fromcoord=" + str2 + "&to=" + str3 + "&tocoord=" + str4 + "&referer=" + getQqMapApiKey(activity)));
        intent.setFlags(268435456);
        try {
            activity.startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    public static boolean isSamePOIByDistance(LatLng latLng, double d, double d2) {
        return latLng != null && 5.0d > TencentLocationUtils.distanceBetween(latLng.getLatitude(), latLng.getLongitude(), d, d2);
    }
}
