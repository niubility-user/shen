package com.jingdong.jdreact.plugin.map;

import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
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
    @ReactMethod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void takeSnapshot(final int i2, ReadableMap readableMap, final Callback callback, final Callback callback2) {
        final Bitmap.CompressFormat compressFormat;
        Bitmap.CompressFormat compressFormat2;
        int i3;
        int i4;
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        if (reactApplicationContext == null) {
            return;
        }
        String string = readableMap.hasKey("format") ? readableMap.getString("format") : null;
        final String str = TextUtils.isEmpty(string) ? SNAPSHOT_FORMAT_PNG : string;
        if (SNAPSHOT_FORMAT_PNG.equals(str)) {
            compressFormat2 = Bitmap.CompressFormat.PNG;
        } else if (!SNAPSHOT_FORMAT_JPG.equals(str)) {
            compressFormat = null;
            final double d = !readableMap.hasKey("quality") ? readableMap.getDouble("quality") : 1.0d;
            float density = JDReactHelper.newInstance().getDensity(reactApplicationContext);
            if (readableMap.hasKey("width")) {
                i3 = 0;
            } else {
                double d2 = density;
                double d3 = readableMap.getDouble("width");
                Double.isNaN(d2);
                i3 = (int) (d2 * d3);
            }
            if (readableMap.hasKey("height")) {
                i4 = 0;
            } else {
                double d4 = density;
                double d5 = readableMap.getDouble("height");
                Double.isNaN(d4);
                i4 = (int) (d4 * d5);
            }
            final String string2 = !readableMap.hasKey("result") ? readableMap.getString("result") : "file";
            if (i3 != 0 || i4 == 0 || compressFormat == null || TextUtils.isEmpty(string2)) {
                invokeCallback(callback2, "params invalid");
            }
            final int i5 = i3;
            final int i6 = i4;
            ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapModule.1
                @Override // com.facebook.react.uimanager.UIBlock
                public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                    try {
                        JDReactMapView jDReactMapView = (JDReactMapView) nativeViewHierarchyManager.resolveView(i2);
                        if (jDReactMapView == null) {
                            JDReactMapModule.this.invokeCallback(callback2, "JDReactMapView not found");
                            return;
                        }
                        TencentMap tencentMap = jDReactMapView.map;
                        if (tencentMap == null) {
                            JDReactMapModule.this.invokeCallback(callback2, "JDReactMapView.map is not valid");
                        } else {
                            tencentMap.snapshot(new TencentMap.SnapshotReadyCallback() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapModule.1.1
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
                                        JDReactMapModule.this.invokeCallback(callback2, "Failed to generate bitmap, snapshot = null");
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
                                            if ("file".equals(string2)) {
                                                File createTempFile = File.createTempFile("JDReactMapSnapshot", OrderISVUtil.MONEY_DECIMAL + str, reactApplicationContext.getCacheDir());
                                                FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile);
                                                try {
                                                    AnonymousClass1 anonymousClass13 = AnonymousClass1.this;
                                                    bitmap2.compress(compressFormat, (int) (d * 100.0d), fileOutputStream2);
                                                    String uri = Uri.fromFile(createTempFile).toString();
                                                    AnonymousClass1 anonymousClass14 = AnonymousClass1.this;
                                                    JDReactMapModule.this.invokeCallback(callback, uri);
                                                    fileOutputStream = fileOutputStream2;
                                                } catch (Exception e2) {
                                                    e = e2;
                                                    fileOutputStream = fileOutputStream2;
                                                    AnonymousClass1 anonymousClass15 = AnonymousClass1.this;
                                                    JDReactMapModule.this.invokeCallback(callback2, e.toString());
                                                    JDReactMapModule.this.closeQuietly(fileOutputStream);
                                                } catch (Throwable th) {
                                                    th = th;
                                                    fileOutputStream = fileOutputStream2;
                                                    JDReactMapModule.this.closeQuietly(fileOutputStream);
                                                    throw th;
                                                }
                                            } else if ("base64".equals(string2)) {
                                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                                try {
                                                    AnonymousClass1 anonymousClass16 = AnonymousClass1.this;
                                                    bitmap2.compress(compressFormat, (int) (d * 100.0d), byteArrayOutputStream);
                                                    String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                                                    AnonymousClass1 anonymousClass17 = AnonymousClass1.this;
                                                    JDReactMapModule.this.invokeCallback(callback, encodeToString);
                                                    fileOutputStream = byteArrayOutputStream;
                                                } catch (Exception e3) {
                                                    e = e3;
                                                    fileOutputStream = byteArrayOutputStream;
                                                    AnonymousClass1 anonymousClass152 = AnonymousClass1.this;
                                                    JDReactMapModule.this.invokeCallback(callback2, e.toString());
                                                    JDReactMapModule.this.closeQuietly(fileOutputStream);
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    fileOutputStream = byteArrayOutputStream;
                                                    JDReactMapModule.this.closeQuietly(fileOutputStream);
                                                    throw th;
                                                }
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                        }
                                    } catch (Exception e4) {
                                        e = e4;
                                    }
                                    JDReactMapModule.this.closeQuietly(fileOutputStream);
                                }
                            });
                        }
                    } catch (Exception e2) {
                        e2.toString();
                        JDReactMapModule.this.invokeCallback(callback2, e2.toString());
                    }
                }
            });
        } else {
            compressFormat2 = Bitmap.CompressFormat.JPEG;
        }
        compressFormat = compressFormat2;
        final double d6 = !readableMap.hasKey("quality") ? readableMap.getDouble("quality") : 1.0d;
        float density2 = JDReactHelper.newInstance().getDensity(reactApplicationContext);
        if (readableMap.hasKey("width")) {
        }
        if (readableMap.hasKey("height")) {
        }
        final String string22 = !readableMap.hasKey("result") ? readableMap.getString("result") : "file";
        if (i3 != 0) {
        }
        invokeCallback(callback2, "params invalid");
        final int i52 = i3;
        final int i62 = i4;
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapModule.1
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                try {
                    JDReactMapView jDReactMapView = (JDReactMapView) nativeViewHierarchyManager.resolveView(i2);
                    if (jDReactMapView == null) {
                        JDReactMapModule.this.invokeCallback(callback2, "JDReactMapView not found");
                        return;
                    }
                    TencentMap tencentMap = jDReactMapView.map;
                    if (tencentMap == null) {
                        JDReactMapModule.this.invokeCallback(callback2, "JDReactMapView.map is not valid");
                    } else {
                        tencentMap.snapshot(new TencentMap.SnapshotReadyCallback() { // from class: com.jingdong.jdreact.plugin.map.JDReactMapModule.1.1
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
                                    JDReactMapModule.this.invokeCallback(callback2, "Failed to generate bitmap, snapshot = null");
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
                                        if ("file".equals(string22)) {
                                            File createTempFile = File.createTempFile("JDReactMapSnapshot", OrderISVUtil.MONEY_DECIMAL + str, reactApplicationContext.getCacheDir());
                                            FileOutputStream fileOutputStream2 = new FileOutputStream(createTempFile);
                                            try {
                                                AnonymousClass1 anonymousClass13 = AnonymousClass1.this;
                                                bitmap2.compress(compressFormat, (int) (d6 * 100.0d), fileOutputStream2);
                                                String uri = Uri.fromFile(createTempFile).toString();
                                                AnonymousClass1 anonymousClass14 = AnonymousClass1.this;
                                                JDReactMapModule.this.invokeCallback(callback, uri);
                                                fileOutputStream = fileOutputStream2;
                                            } catch (Exception e2) {
                                                e = e2;
                                                fileOutputStream = fileOutputStream2;
                                                AnonymousClass1 anonymousClass152 = AnonymousClass1.this;
                                                JDReactMapModule.this.invokeCallback(callback2, e.toString());
                                                JDReactMapModule.this.closeQuietly(fileOutputStream);
                                            } catch (Throwable th) {
                                                th = th;
                                                fileOutputStream = fileOutputStream2;
                                                JDReactMapModule.this.closeQuietly(fileOutputStream);
                                                throw th;
                                            }
                                        } else if ("base64".equals(string22)) {
                                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                            try {
                                                AnonymousClass1 anonymousClass16 = AnonymousClass1.this;
                                                bitmap2.compress(compressFormat, (int) (d6 * 100.0d), byteArrayOutputStream);
                                                String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                                                AnonymousClass1 anonymousClass17 = AnonymousClass1.this;
                                                JDReactMapModule.this.invokeCallback(callback, encodeToString);
                                                fileOutputStream = byteArrayOutputStream;
                                            } catch (Exception e3) {
                                                e = e3;
                                                fileOutputStream = byteArrayOutputStream;
                                                AnonymousClass1 anonymousClass1522 = AnonymousClass1.this;
                                                JDReactMapModule.this.invokeCallback(callback2, e.toString());
                                                JDReactMapModule.this.closeQuietly(fileOutputStream);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                fileOutputStream = byteArrayOutputStream;
                                                JDReactMapModule.this.closeQuietly(fileOutputStream);
                                                throw th;
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                    }
                                } catch (Exception e4) {
                                    e = e4;
                                }
                                JDReactMapModule.this.closeQuietly(fileOutputStream);
                            }
                        });
                    }
                } catch (Exception e2) {
                    e2.toString();
                    JDReactMapModule.this.invokeCallback(callback2, e2.toString());
                }
            }
        });
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
