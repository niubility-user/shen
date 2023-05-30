package com.jingdong.jdreact.plugin.viewshot;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.jdreactFramework.JDReactHelper;
import com.jingdong.jdreact.plugin.viewshot.ViewShot;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/* loaded from: classes14.dex */
public class JDReactViewShot extends ReactContextBaseJavaModule {
    public static final String RNVIEW_SHOT = "JDReactViewShot";
    private static final String TEMP_FILE_PREFIX = "ReactNative-snapshot-image";
    private final ReactApplicationContext reactContext;

    /* loaded from: classes14.dex */
    private static class CleanTask extends GuardedAsyncTask<Void, Void> implements FilenameFilter {
        private final File cacheDir;
        private final File externalCacheDir;

        private void cleanDirectory(File file) {
            File[] listFiles = file.listFiles(this);
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (file2.delete()) {
                        String str = "deleted file: " + file2.getAbsolutePath();
                    }
                }
            }
        }

        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return str.startsWith(JDReactViewShot.TEMP_FILE_PREFIX);
        }

        private CleanTask(ReactContext reactContext) {
            super(reactContext);
            this.cacheDir = reactContext.getCacheDir();
            this.externalCacheDir = reactContext.getExternalCacheDir();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.react.bridge.GuardedAsyncTask
        public void doInBackgroundGuarded(Void... voidArr) {
            File file = this.cacheDir;
            if (file != null) {
                cleanDirectory(file);
            }
            File file2 = this.externalCacheDir;
            if (file2 != null) {
                cleanDirectory(file2);
            }
        }
    }

    public JDReactViewShot(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    private File createTempFile(Context context, String str, boolean z) throws IOException {
        File externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File cacheDir = context.getCacheDir();
        if (z) {
            if (externalFilesDir == null) {
                throw new IOException("No cache directory available");
            }
        } else if (externalFilesDir == null && cacheDir == null) {
            throw new IOException("No cache directory available");
        } else {
            if (externalFilesDir == null || (cacheDir != null && externalFilesDir.getFreeSpace() <= cacheDir.getFreeSpace())) {
                externalFilesDir = cacheDir;
            }
        }
        return File.createTempFile(TEMP_FILE_PREFIX, OrderISVUtil.MONEY_DECIMAL + str, externalFilesDir);
    }

    @ReactMethod
    public void captureRef(int i2, ReadableMap readableMap, Promise promise) {
        int i3;
        Integer num;
        Integer num2;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        String string = readableMap.getString("format");
        if ("jpg".equals(string)) {
            i3 = 0;
        } else if ("webm".equals(string)) {
            i3 = 2;
        } else {
            i3 = "raw".equals(string) ? -1 : 1;
        }
        double d = readableMap.getDouble("quality");
        float density = JDReactHelper.newInstance().getDensity(reactApplicationContext);
        if (readableMap.hasKey("width")) {
            double d2 = density;
            double d3 = readableMap.getDouble("width");
            Double.isNaN(d2);
            num = Integer.valueOf((int) (d2 * d3));
        } else {
            num = null;
        }
        if (readableMap.hasKey("height")) {
            double d4 = density;
            double d5 = readableMap.getDouble("height");
            Double.isNaN(d4);
            num2 = Integer.valueOf((int) (d4 * d5));
        } else {
            num2 = null;
        }
        String string2 = readableMap.getString("result");
        try {
            ((UIManagerModule) this.reactContext.getNativeModule(UIManagerModule.class)).addUIBlock(new ViewShot(i2, string, i3, d, num, num2, ViewShot.Results.TEMP_FILE.equals(string2) ? createTempFile(getReactApplicationContext(), string, readableMap.hasKey("external") ? readableMap.getBoolean("external") : true) : null, string2, Boolean.valueOf(readableMap.getBoolean("snapshotContentContainer")), this.reactContext, getCurrentActivity(), promise));
        } catch (Throwable unused) {
            String str = "Failed to snapshot view tag " + i2;
            promise.reject(ViewShot.ERROR_UNABLE_TO_SNAPSHOT, "Failed to snapshot view tag " + i2);
        }
    }

    @ReactMethod
    public void captureScreen(ReadableMap readableMap, Promise promise) {
        captureRef(-1, readableMap, promise);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        return Collections.emptyMap();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return RNVIEW_SHOT;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        new CleanTask(getReactApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    @ReactMethod
    public void releaseCapture(String str) {
        String path = Uri.parse(str).getPath();
        if (path == null) {
            return;
        }
        File file = new File(path);
        if (file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile.equals(this.reactContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES)) || parentFile.equals(this.reactContext.getCacheDir())) {
                file.delete();
            }
        }
    }
}
