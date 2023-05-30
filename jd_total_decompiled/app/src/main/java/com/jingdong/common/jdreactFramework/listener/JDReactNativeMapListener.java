package com.jingdong.common.jdreactFramework.listener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdflutter.JDFlutterCall;
import com.jingdong.common.jdflutter.JDFlutterCallResult;
import com.jingdong.common.jdreactFramework.JDCallback;
import com.jingdong.common.jdreactFramework.utils.JLog;
import com.jingdong.jdreact.plugin.map.JDReactMapView;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
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
    */
    public void takeSnapshot(final int i2, HashMap hashMap, final JDCallback jDCallback, final JDCallback jDCallback2, Context context) {
        final Bitmap.CompressFormat compressFormat;
        Bitmap.CompressFormat compressFormat2;
        int i3;
        int i4;
        final ReactApplicationContext reactApplicationContext = (ReactApplicationContext) context;
        if (reactApplicationContext == null) {
            return;
        }
        String str = hashMap.containsKey("format") ? (String) hashMap.get("format") : null;
        final String str2 = TextUtils.isEmpty(str) ? SNAPSHOT_FORMAT_PNG : str;
        if (SNAPSHOT_FORMAT_PNG.equals(str2)) {
            compressFormat2 = Bitmap.CompressFormat.PNG;
        } else if (!SNAPSHOT_FORMAT_JPG.equals(str2)) {
            compressFormat = null;
            final double doubleValue = !hashMap.containsKey("quality") ? ((Double) hashMap.get("quality")).doubleValue() : 1.0d;
            float density = BaseInfo.getDensity();
            if (hashMap.containsKey("width")) {
                i3 = 0;
            } else {
                double d = density;
                double doubleValue2 = ((Double) hashMap.get("width")).doubleValue();
                Double.isNaN(d);
                i3 = (int) (d * doubleValue2);
            }
            if (hashMap.containsKey("height")) {
                i4 = 0;
            } else {
                double d2 = density;
                double doubleValue3 = ((Double) hashMap.get("height")).doubleValue();
                Double.isNaN(d2);
                i4 = (int) (d2 * doubleValue3);
            }
            final String str3 = !hashMap.containsKey("result") ? (String) hashMap.get("result") : "file";
            if (i3 != 0 || i4 == 0 || compressFormat == null || TextUtils.isEmpty(str3)) {
                invokeCallback(jDCallback2, "params invalid");
            }
            final int i5 = i3;
            final int i6 = i4;
            ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener.1
                @Override // com.facebook.react.uimanager.UIBlock
                public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                    try {
                        JDReactMapView jDReactMapView = (JDReactMapView) nativeViewHierarchyManager.resolveView(i2);
                        if (jDReactMapView == null) {
                            JDReactNativeMapListener.this.invokeCallback(jDCallback2, "JDReactMapView not found");
                            return;
                        }
                        TencentMap tencentMap = jDReactMapView.map;
                        if (tencentMap == null) {
                            JDReactNativeMapListener.this.invokeCallback(jDCallback2, "JDReactMapView.map is not valid");
                        } else {
                            tencentMap.snapshot(new TencentMap.SnapshotReadyCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener.1.1
                                /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
                                    if (r2 != r3) goto L10;
                                 */
                                @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.SnapshotReadyCallback
                                /*
                                    Code decompiled incorrectly, please refer to instructions dump.
                                */
                                public void onSnapshotReady(Bitmap bitmap) {
                                    Bitmap bitmap2;
                                    if (bitmap == null) {
                                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                        JDReactNativeMapListener.this.invokeCallback(jDCallback2, "Failed to generate bitmap, snapshot = null");
                                        return;
                                    }
                                    if (i5 == bitmap.getWidth()) {
                                        int i7 = i6;
                                        int height = bitmap.getHeight();
                                        bitmap2 = bitmap;
                                    }
                                    AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                                    bitmap2 = Bitmap.createScaledBitmap(bitmap, i5, i6, true);
                                    FileOutputStream fileOutputStream = null;
                                    try {
                                        try {
                                            if ("file".equals(str3)) {
                                                File createTempFile = File.createTempFile("JDReactMapSnapshot", OrderISVUtil.MONEY_DECIMAL + str2, reactApplicationContext.getCacheDir());
                                                FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile);
                                                try {
                                                    AnonymousClass1 anonymousClass13 = AnonymousClass1.this;
                                                    bitmap2.compress(compressFormat, (int) (doubleValue * 100.0d), fileOutputStream2);
                                                    String uri = Uri.fromFile(createTempFile).toString();
                                                    AnonymousClass1 anonymousClass14 = AnonymousClass1.this;
                                                    JDReactNativeMapListener.this.invokeCallback(jDCallback, uri);
                                                    fileOutputStream = fileOutputStream2;
                                                } catch (Exception e2) {
                                                    e = e2;
                                                    fileOutputStream = fileOutputStream2;
                                                    AnonymousClass1 anonymousClass15 = AnonymousClass1.this;
                                                    JDReactNativeMapListener.this.invokeCallback(jDCallback2, e.toString());
                                                    JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                                } catch (Throwable th) {
                                                    th = th;
                                                    fileOutputStream = fileOutputStream2;
                                                    JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                                    throw th;
                                                }
                                            } else if ("base64".equals(str3)) {
                                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                                try {
                                                    AnonymousClass1 anonymousClass16 = AnonymousClass1.this;
                                                    bitmap2.compress(compressFormat, (int) (doubleValue * 100.0d), byteArrayOutputStream);
                                                    String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                                                    AnonymousClass1 anonymousClass17 = AnonymousClass1.this;
                                                    JDReactNativeMapListener.this.invokeCallback(jDCallback, encodeToString);
                                                    fileOutputStream = byteArrayOutputStream;
                                                } catch (Exception e3) {
                                                    e = e3;
                                                    fileOutputStream = byteArrayOutputStream;
                                                    AnonymousClass1 anonymousClass152 = AnonymousClass1.this;
                                                    JDReactNativeMapListener.this.invokeCallback(jDCallback2, e.toString());
                                                    JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    fileOutputStream = byteArrayOutputStream;
                                                    JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                                    throw th;
                                                }
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                        }
                                    } catch (Exception e4) {
                                        e = e4;
                                    }
                                    JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                }
                            });
                        }
                    } catch (Exception e2) {
                        JLog.e("JDReactMapTakeSnapshot", e2.toString());
                        JDReactNativeMapListener.this.invokeCallback(jDCallback2, e2.toString());
                    }
                }
            });
        } else {
            compressFormat2 = Bitmap.CompressFormat.JPEG;
        }
        compressFormat = compressFormat2;
        final double doubleValue4 = !hashMap.containsKey("quality") ? ((Double) hashMap.get("quality")).doubleValue() : 1.0d;
        float density2 = BaseInfo.getDensity();
        if (hashMap.containsKey("width")) {
        }
        if (hashMap.containsKey("height")) {
        }
        final String str32 = !hashMap.containsKey("result") ? (String) hashMap.get("result") : "file";
        if (i3 != 0) {
        }
        invokeCallback(jDCallback2, "params invalid");
        final int i52 = i3;
        final int i62 = i4;
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener.1
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    JDReactMapView jDReactMapView = (JDReactMapView) nativeViewHierarchyManager.resolveView(i2);
                    if (jDReactMapView == null) {
                        JDReactNativeMapListener.this.invokeCallback(jDCallback2, "JDReactMapView not found");
                        return;
                    }
                    TencentMap tencentMap = jDReactMapView.map;
                    if (tencentMap == null) {
                        JDReactNativeMapListener.this.invokeCallback(jDCallback2, "JDReactMapView.map is not valid");
                    } else {
                        tencentMap.snapshot(new TencentMap.SnapshotReadyCallback() { // from class: com.jingdong.common.jdreactFramework.listener.JDReactNativeMapListener.1.1
                            /* JADX WARN: Code restructure failed: missing block: B:9:0x0026, code lost:
                                if (r2 != r3) goto L10;
                             */
                            @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.SnapshotReadyCallback
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                            */
                            public void onSnapshotReady(Bitmap bitmap) {
                                Bitmap bitmap2;
                                if (bitmap == null) {
                                    AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                    JDReactNativeMapListener.this.invokeCallback(jDCallback2, "Failed to generate bitmap, snapshot = null");
                                    return;
                                }
                                if (i52 == bitmap.getWidth()) {
                                    int i7 = i62;
                                    int height = bitmap.getHeight();
                                    bitmap2 = bitmap;
                                }
                                AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                                bitmap2 = Bitmap.createScaledBitmap(bitmap, i52, i62, true);
                                FileOutputStream fileOutputStream = null;
                                try {
                                    try {
                                        if ("file".equals(str32)) {
                                            File createTempFile = File.createTempFile("JDReactMapSnapshot", OrderISVUtil.MONEY_DECIMAL + str2, reactApplicationContext.getCacheDir());
                                            FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile);
                                            try {
                                                AnonymousClass1 anonymousClass13 = AnonymousClass1.this;
                                                bitmap2.compress(compressFormat, (int) (doubleValue4 * 100.0d), fileOutputStream2);
                                                String uri = Uri.fromFile(createTempFile).toString();
                                                AnonymousClass1 anonymousClass14 = AnonymousClass1.this;
                                                JDReactNativeMapListener.this.invokeCallback(jDCallback, uri);
                                                fileOutputStream = fileOutputStream2;
                                            } catch (Exception e2) {
                                                e = e2;
                                                fileOutputStream = fileOutputStream2;
                                                AnonymousClass1 anonymousClass152 = AnonymousClass1.this;
                                                JDReactNativeMapListener.this.invokeCallback(jDCallback2, e.toString());
                                                JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                            } catch (Throwable th) {
                                                th = th;
                                                fileOutputStream = fileOutputStream2;
                                                JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                                throw th;
                                            }
                                        } else if ("base64".equals(str32)) {
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            try {
                                                AnonymousClass1 anonymousClass16 = AnonymousClass1.this;
                                                bitmap2.compress(compressFormat, (int) (doubleValue4 * 100.0d), byteArrayOutputStream);
                                                String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                                                AnonymousClass1 anonymousClass17 = AnonymousClass1.this;
                                                JDReactNativeMapListener.this.invokeCallback(jDCallback, encodeToString);
                                                fileOutputStream = byteArrayOutputStream;
                                            } catch (Exception e3) {
                                                e = e3;
                                                fileOutputStream = byteArrayOutputStream;
                                                AnonymousClass1 anonymousClass1522 = AnonymousClass1.this;
                                                JDReactNativeMapListener.this.invokeCallback(jDCallback2, e.toString());
                                                JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                fileOutputStream = byteArrayOutputStream;
                                                JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                                                throw th;
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                }
                                JDReactNativeMapListener.this.closeQuietly(fileOutputStream);
                            }
                        });
                    }
                } catch (Exception e2) {
                    JLog.e("JDReactMapTakeSnapshot", e2.toString());
                    JDReactNativeMapListener.this.invokeCallback(jDCallback2, e2.toString());
                }
            }
        });
    }

    public double calculateDistance(double d, double d2, double d3, double d4) {
        double rad = rad(d);
        double rad2 = rad(d3);
        double round = Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin((rad - rad2) / 2.0d), 2.0d) + (Math.cos(rad) * Math.cos(rad2) * Math.pow(Math.sin((rad(d2) - rad(d4)) / 2.0d), 2.0d)))) * 2.0d * 6378.137d * 10000.0d);
        Double.isNaN(round);
        return round / 10000.0d;
    }
}
