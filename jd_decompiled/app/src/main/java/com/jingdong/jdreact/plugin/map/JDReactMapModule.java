package com.jingdong.jdreact.plugin.map;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes13.dex */
public class JDReactMapModule extends ReactContextBaseJavaModule {
    private static final double RADIUS = 6378.137d;
    private static final String SNAPSHOT_FORMAT_JPG = "jpg";
    private static final String SNAPSHOT_FORMAT_PNG = "png";
    private static final String SNAPSHOT_RESULT_BASE64 = "base64";
    private static final String SNAPSHOT_RESULT_FILE = "file";

    public JDReactMapModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public static double calculateDistance(double d, double d2, double d3, double d4) {
        double rad = rad(d);
        double rad2 = rad(d3);
        double round = Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin((rad - rad2) / 2.0d), 2.0d) + (Math.cos(rad) * Math.cos(rad2) * Math.pow(Math.sin((rad(d2) - rad(d4)) / 2.0d), 2.0d)))) * 2.0d * RADIUS * 10000.0d);
        Double.isNaN(round);
        return round / 10000.0d;
    }

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
    public void invokeCallback(Callback callback, Object... objArr) {
        if (callback != null) {
            callback.invoke(objArr);
        }
    }

    private static double rad(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "JDReactMapModule";
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008b  */
    @com.facebook.react.bridge.ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void takeSnapshot(final int r17, com.facebook.react.bridge.ReadableMap r18, final com.facebook.react.bridge.Callback r19, final com.facebook.react.bridge.Callback r20) {
        /*
            r16 = this;
            r0 = r18
            com.facebook.react.bridge.ReactApplicationContext r8 = r16.getReactApplicationContext()
            if (r8 != 0) goto L9
            return
        L9:
            java.lang.String r1 = "format"
            boolean r2 = r0.hasKey(r1)
            r3 = 0
            if (r2 == 0) goto L17
            java.lang.String r1 = r0.getString(r1)
            goto L18
        L17:
            r1 = r3
        L18:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            java.lang.String r4 = "png"
            if (r2 == 0) goto L22
            r7 = r4
            goto L23
        L22:
            r7 = r1
        L23:
            boolean r1 = r4.equals(r7)
            if (r1 == 0) goto L2d
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.PNG
        L2b:
            r9 = r1
            goto L39
        L2d:
            java.lang.String r1 = "jpg"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L38
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG
            goto L2b
        L38:
            r9 = r3
        L39:
            java.lang.String r1 = "quality"
            boolean r2 = r0.hasKey(r1)
            if (r2 == 0) goto L46
            double r1 = r0.getDouble(r1)
            goto L48
        L46:
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L48:
            r10 = r1
            com.jingdong.common.jdreactFramework.JDReactHelper r1 = com.jingdong.common.jdreactFramework.JDReactHelper.newInstance()
            float r1 = r1.getDensity(r8)
            java.lang.String r2 = "width"
            boolean r3 = r0.hasKey(r2)
            r4 = 0
            if (r3 == 0) goto L67
            double r5 = (double) r1
            double r2 = r0.getDouble(r2)
            java.lang.Double.isNaN(r5)
            double r5 = r5 * r2
            int r2 = (int) r5
            r5 = r2
            goto L68
        L67:
            r5 = 0
        L68:
            java.lang.String r2 = "height"
            boolean r3 = r0.hasKey(r2)
            if (r3 == 0) goto L7d
            double r12 = (double) r1
            double r1 = r0.getDouble(r2)
            java.lang.Double.isNaN(r12)
            double r12 = r12 * r1
            int r1 = (int) r12
            r6 = r1
            goto L7e
        L7d:
            r6 = 0
        L7e:
            java.lang.String r1 = "result"
            boolean r2 = r0.hasKey(r1)
            if (r2 == 0) goto L8b
            java.lang.String r0 = r0.getString(r1)
            goto L8d
        L8b:
            java.lang.String r0 = "file"
        L8d:
            r12 = r0
            if (r5 == 0) goto La0
            if (r6 == 0) goto La0
            if (r9 == 0) goto La0
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            if (r0 == 0) goto L9b
            goto La0
        L9b:
            r13 = r16
            r3 = r20
            goto Lae
        La0:
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r1 = "params invalid"
            r0[r4] = r1
            r13 = r16
            r3 = r20
            r13.invokeCallback(r3, r0)
        Lae:
            java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r0 = com.facebook.react.uimanager.UIManagerModule.class
            com.facebook.react.bridge.NativeModule r0 = r8.getNativeModule(r0)
            r14 = r0
            com.facebook.react.uimanager.UIManagerModule r14 = (com.facebook.react.uimanager.UIManagerModule) r14
            com.jingdong.jdreact.plugin.map.JDReactMapModule$1 r15 = new com.jingdong.jdreact.plugin.map.JDReactMapModule$1
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r20
            r4 = r5
            r5 = r6
            r6 = r12
            r12 = r19
            r0.<init>()
            r14.addUIBlock(r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.map.JDReactMapModule.takeSnapshot(int, com.facebook.react.bridge.ReadableMap, com.facebook.react.bridge.Callback, com.facebook.react.bridge.Callback):void");
    }

    @ReactMethod
    public void calculateDistance(ReadableMap readableMap, Callback callback, Callback callback2) {
        if (readableMap == null) {
            invokeCallback(callback2, new Object[0]);
            return;
        }
        try {
            invokeCallback(callback, Double.valueOf(calculateDistance(readableMap.hasKey("lat1") ? readableMap.getDouble("lat1") : 0.0d, readableMap.hasKey("lng1") ? readableMap.getDouble("lng1") : 0.0d, readableMap.hasKey("lat2") ? readableMap.getDouble("lat2") : 0.0d, readableMap.hasKey("lng2") ? readableMap.getDouble("lng2") : 0.0d) * 1000.0d));
        } catch (Exception e2) {
            invokeCallback(callback2, e2.toString());
        }
    }
}
