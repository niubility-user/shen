package com.jingdong.remoteimage;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public class RemoteImageManager {
    private static final String TAG = " RemoteImage";
    public static final String XHDPI = "2x";
    public static final String XXHDPI = "3x";
    private static final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
    private static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.jingdong.remoteimage.RemoteImageManager.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            boolean z = Constants.DEBUG;
            DiffMapManger.getDefault().check();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            boolean z = Constants.DEBUG;
        }
    };

    /* loaded from: classes7.dex */
    public static class Builder {
        private Application application;
        private boolean debug;
        protected IMtaExceptionReport exceptionReport;
        private String host;

        public Application getApplication() {
            return this.application;
        }

        public String getHost() {
            return this.host;
        }

        public Builder interceptRequest(boolean z) {
            return this;
        }

        public boolean isDebug() {
            return this.debug;
        }

        public Builder setApplication(Application application) {
            this.application = application;
            return this;
        }

        public Builder setBridge(RemoteBridge remoteBridge) {
            DiffMapManger.sRemoteBridge = remoteBridge;
            return this;
        }

        public Builder setDebug(boolean z) {
            this.debug = z;
            Constants.DEBUG = z;
            return this;
        }

        public Builder setExceptionReport(IMtaExceptionReport iMtaExceptionReport) {
            this.exceptionReport = iMtaExceptionReport;
            return this;
        }

        public Builder setHost(String str) {
            this.host = str;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SCREEN {
    }

    @Nullable
    public static String getImageUrlById(@NonNull String str) {
        return getImageUrlById(str, getScreen());
    }

    public static String getScreen() {
        int i2 = AppUtil.getApplication().getResources().getDisplayMetrics().densityDpi;
        return (i2 == 480 || i2 == 640) ? XXHDPI : XHDPI;
    }

    private static String getUrlFromAsset(@NonNull String str, String str2) {
        String str3;
        Iterator<String> it;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String format = String.format("%s_%s", str, str2);
        synchronized (RemoteImageManager.class) {
            str3 = concurrentHashMap.get(format);
            if (TextUtils.isEmpty(str3)) {
                try {
                    String[] split = str.split(CartConstant.KEY_YB_INFO_LINK);
                    long currentTimeMillis = System.currentTimeMillis();
                    String fromAssets = FileUtil.getFromAssets(Constants.REMOTE_URL_DIR + File.separator + DiffMapManger.getDefault().getRemoteUrlModuleFileName(split[0]), AppUtil.getApplication());
                    if (Constants.DEBUG) {
                        String.format("get remoteimage url from asset file  %s.xjs  spend time is %d: ", split[0], Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    }
                    if (!TextUtils.isEmpty(fromAssets)) {
                        JDJSONObject parseObject = JDJSON.parseObject(fromAssets);
                        JDJSONObject optJSONObject = parseObject.optJSONObject("images");
                        String optString = parseObject.optString("moduleId");
                        Iterator<String> it2 = optJSONObject.keySet().iterator();
                        while (it2.hasNext()) {
                            String next = it2.next();
                            JDJSONObject optJSONObject2 = optJSONObject.optJSONObject(next);
                            String optString2 = optJSONObject2.optString(str2);
                            if (!TextUtils.isEmpty(optString2)) {
                                it = it2;
                            } else if (str2.equals(XHDPI)) {
                                it = it2;
                                optString2 = DiffMapManger.getDefault().getImageUrl(String.format("%s_%s_%s", optString, next, XXHDPI));
                                if (TextUtils.isEmpty(optString2)) {
                                    optString2 = optJSONObject2.optString(XXHDPI);
                                }
                            } else {
                                it = it2;
                                if (str2.equals(XXHDPI)) {
                                    optString2 = DiffMapManger.getDefault().getImageUrl(String.format("%s_%s_%s", optString, next, XHDPI));
                                    if (TextUtils.isEmpty(optString2)) {
                                        optString2 = optJSONObject2.optString(XHDPI);
                                    }
                                }
                            }
                            if (optString2 != null) {
                                if (str3 == null && next.equals(split[1])) {
                                    str3 = optString2;
                                }
                                concurrentHashMap.put(String.format("%s_%s_%s", optString, next, str2), optString2);
                            }
                            it2 = it;
                        }
                        if (Constants.DEBUG) {
                            String str4 = "get url count from asset file is : " + optJSONObject.keySet().size();
                            String str5 = "current cache map url total count : " + concurrentHashMap.size();
                        }
                    } else {
                        MtaService.sendErrMsg("remoteURL_callUrlError", "2", str);
                    }
                    if (TextUtils.isEmpty(str3)) {
                        MtaService.sendErrMsg("remoteURL_callUrlError", "3", str);
                    }
                    if (Constants.DEBUG) {
                        String str6 = "get remoteimage url from asset file and sparse jsonobject into map time : " + (System.currentTimeMillis() - currentTimeMillis);
                    }
                    if (System.currentTimeMillis() - currentTimeMillis >= 100) {
                        MtaService.sendErrNum("remoteURL_callUrl", String.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    }
                } catch (Exception unused) {
                    MtaService.sendErrMsg("remoteURL_callUrlError", "1", str);
                }
            }
        }
        return str3;
    }

    public static void init(@NonNull Builder builder) {
        if (builder.getApplication() != null) {
            AppUtil.init(builder.getApplication());
            if (builder.getHost() != null) {
                AppUtil.getApplication().unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
                AppUtil.getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
                DiffMapManger.getDefault().setHost(builder.getHost());
                MtaService.init(builder.exceptionReport);
                return;
            }
            throw new IllegalArgumentException("RemoteImageManager.Builder host is null");
        }
        throw new IllegalArgumentException("RemoteImageManager.Builder application is null");
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static void onLowMemory() {
    }

    @Deprecated
    public static void reflectRefreshtimeForTest(long j2, long j3) {
        DiffMapManger diffMapManger = DiffMapManger.getDefault();
        if (j2 > -1) {
            try {
                Field declaredField = diffMapManger.getClass().getDeclaredField("refreshTime");
                declaredField.setAccessible(true);
                declaredField.set(diffMapManger, Long.valueOf(j2));
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return;
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
                return;
            }
        }
        if (j3 == 0) {
            Field declaredField2 = diffMapManger.getClass().getDeclaredField("startTime");
            declaredField2.setAccessible(true);
            declaredField2.set(diffMapManger, Long.valueOf(j3));
        }
    }

    @Deprecated
    public static void testRequestAPI() {
        DiffMapManger.getDefault().check();
    }

    @Nullable
    public static String getImageUrlById(@NonNull String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        String format = String.format("%s_%s", str, str2);
        String imageUrl = DiffMapManger.getDefault().getImageUrl(format);
        if (TextUtils.isEmpty(imageUrl)) {
            imageUrl = concurrentHashMap.get(format);
            if (TextUtils.isEmpty(imageUrl)) {
                imageUrl = getUrlFromAsset(str, str2);
            }
        }
        if (TextUtils.isEmpty(imageUrl)) {
            return null;
        }
        if (Constants.DEBUG) {
            String str3 = "get remoteimage url time : " + (System.currentTimeMillis() - currentTimeMillis);
        }
        return DiffMapManger.getDefault().getImgPrefix() + imageUrl;
    }
}
