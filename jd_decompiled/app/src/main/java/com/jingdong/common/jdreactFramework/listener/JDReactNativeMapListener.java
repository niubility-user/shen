package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: classes5.dex */
public class JDReactNativeMapListener implements NativeMapListener, JDFlutterCall {
    public static final String MAPCHANNEL = "com.jd.jdflutter/map";
    private static final String SNAPSHOT_FORMAT_JPG = "jpg";
    private static final String SNAPSHOT_FORMAT_PNG = "png";
    private static final String SNAPSHOT_RESULT_BASE64 = "base64";
    private static final String SNAPSHOT_RESULT_FILE = "file";
    private static final String TAG = "JDReactNativeMapListener";

    /* JADX INFO: Access modifiers changed from: private */
    public void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeCallback(JDCallback jDCallback, Object... objArr) {
        if (jDCallback != null) {
            jDCallback.invoke(objArr);
        }
    }

    private static double rad(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    @Override // com.jingdong.common.jdreactFramework.listener.NativeMapListener
    public void calculateDistance(HashMap hashMap, JDCallback jDCallback, JDCallback jDCallback2) {
        if (hashMap == null) {
            invokeCallback(jDCallback2, new Object[0]);
            return;
        }
        try {
            invokeCallback(jDCallback, Double.valueOf(calculateDistance(hashMap.containsKey("lat1") ? ((Double) hashMap.get("lat1")).doubleValue() : 0.0d, hashMap.containsKey("lng1") ? ((Double) hashMap.get("lng1")).doubleValue() : 0.0d, hashMap.containsKey("lat2") ? ((Double) hashMap.get("lat2")).doubleValue() : 0.0d, hashMap.containsKey("lng2") ? ((Double) hashMap.get("lng2")).doubleValue() : 0.0d) * 1000.0d));
        } catch (Exception e2) {
            invokeCallback(jDCallback2, e2.toString());
        }
    }

    @Override // com.jingdong.common.jdflutter.JDFlutterCall
    public void onMethodCall(String str, HashMap hashMap, final JDFlutterCallResult jDFlutterCallResult, Activity activity) {
        if (str.equals("calculateDistance")) {
            calculateDistance(hashMap, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener.2
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.success(objArr[0].toString());
                }
            }, new JDCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener.3
                @Override // com.jingdong.common.jdreactFramework.JDCallback
                public void invoke(Object... objArr) {
                    jDFlutterCallResult.error("", "", "");
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009d  */
    @Override // com.jingdong.common.jdreactFramework.listener.NativeMapListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void takeSnapshot(final int r17, java.util.HashMap r18, final com.jingdong.common.jdreactFramework.JDCallback r19, final com.jingdong.common.jdreactFramework.JDCallback r20, android.content.Context r21) {
        /*
            Method dump skipped, instructions count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener.takeSnapshot(int, java.util.HashMap, com.jingdong.common.jdreactFramework.JDCallback, com.jingdong.common.jdreactFramework.JDCallback, android.content.Context):void");
    }

    public double calculateDistance(double d, double d2, double d3, double d4) {
        double rad = rad(d);
        double rad2 = rad(d3);
        double round = Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin((rad - rad2) / 2.0d), 2.0d) + (Math.cos(rad) * Math.cos(rad2) * Math.pow(Math.sin((rad(d2) - rad(d4)) / 2.0d), 2.0d)))) * 2.0d * 6378.137d * 10000.0d);
        Double.isNaN(round);
        return round / 10000.0d;
    }
}
