package com.jd.hwsupersdk.sdk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Process;
import com.huawei.emui.hiexperience.hwperf.HwPerfFactory;
import com.huawei.emui.hiexperience.hwperf.speedloader.HwPerfSpeedLoader;
import com.huawei.emui.hiexperience.hwperf.threadpool.HwPerfThreadPoolSize;
import com.huawei.emui.hiexperience.hwperf.thumbnailmanager.HwPerfThumbnailManager;
import com.huawei.emui.hiexperience.iaware.sdk.appsdk.IAwareAppSdk;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes13.dex */
public class JDImproveSDKUtils {
    private static final String HUAWEI = "HUAWEI";
    private static final String PagName = "com.jingdong.app.mall";
    public static final int SCENE_APP_START = 1;
    public static final int SCENE_CAMERA = 7;
    public static final int SCENE_CODING = 5;
    public static final int SCENE_DATA_TRANSFER = 3;
    public static final int SCENE_LOADING = 4;
    public static final int SCENE_MESSAGE = 6;
    public static final int SCENE_WINDOW_SWITCH = 2;
    public static final int STATUS_BEGIN = 1;
    public static final int STATUS_END = 2;
    private static final String TAG = "JDImproveSDKUtils";
    private static boolean isRegister;
    private static IAwareAppSdk mIAwareAppSdk;

    static {
        IAwareAppSdk iAwareAppSdk = new IAwareAppSdk();
        mIAwareAppSdk = iAwareAppSdk;
        isRegister = iAwareAppSdk.registerApp("com.jingdong.app.mall");
    }

    public static void addVIPThreads(long[] jArr) {
        if (isHuawei()) {
            try {
                if (isRegister) {
                    mIAwareAppSdk.addVipThreads(jArr);
                }
            } catch (Exception e2) {
                String str = "JDImproveSDKUtils addVIPThreads Exception" + e2.toString();
            }
        }
    }

    public static void cancelVIPThreads(long[] jArr) {
        if (isHuawei()) {
            try {
                if (isRegister) {
                    mIAwareAppSdk.cancelVipThreads(jArr);
                }
            } catch (Exception e2) {
                String str = "JDImproveSDKUtils cancelVIPThreads Exception" + e2.toString();
            }
        }
    }

    public static long[] getThreadId() {
        return new long[]{Process.myTid()};
    }

    public static int getThreadPoolSize(Context context) {
        HwPerfThreadPoolSize hwPerfThreadPoolSize;
        if (isHuawei()) {
            try {
                HwPerfFactory hwPerfFactory = HwPerfFactory.getInstance(context.getApplicationContext());
                if (hwPerfFactory == null || (hwPerfThreadPoolSize = (HwPerfThreadPoolSize) hwPerfFactory.createFeature(4)) == null) {
                    return -1;
                }
                return hwPerfThreadPoolSize.HwPerfGetPoolSize();
            } catch (Exception e2) {
                String str = "JDImproveSDKUtils getThreadPoolSize Exception" + e2.toString();
                return -1;
            }
        }
        return -1;
    }

    public static Bitmap getThumbnail(Context context, int i2, long j2, int i3, int i4, BitmapFactory.Options options) {
        HwPerfThumbnailManager hwPerfThumbnailManager;
        if (isHuawei() && context != null) {
            try {
                HwPerfFactory hwPerfFactory = HwPerfFactory.getInstance(context.getApplicationContext());
                if (hwPerfFactory == null || (hwPerfThumbnailManager = (HwPerfThumbnailManager) hwPerfFactory.createFeature(3)) == null) {
                    return null;
                }
                return hwPerfThumbnailManager.getThumbnail(i2, j2, i3, i4, options);
            } catch (Exception e2) {
                String str = "JDImproveSDKUtils getThumbnails Exception" + e2.toString();
                return null;
            }
        }
        return null;
    }

    public static boolean isHuawei() {
        return HUAWEI.equals(BaseInfo.getDeviceBrand()) || HUAWEI.equals(BaseInfo.getDeviceModel());
    }

    public static boolean setDefalutListSpeedCallback(Context context, Object obj, HwPerfSpeedLoader.HwPerfVelocityCallback hwPerfVelocityCallback) {
        HwPerfSpeedLoader hwPerfSpeedLoader;
        String HwPerfGetSystemRefVelocity;
        if (isHuawei() && obj != null) {
            try {
                HwPerfFactory hwPerfFactory = HwPerfFactory.getInstance(context.getApplicationContext());
                if (hwPerfFactory == null || (hwPerfSpeedLoader = (HwPerfSpeedLoader) hwPerfFactory.createFeature(1)) == null) {
                    return false;
                }
                boolean HwPerfSetSpeedLoaderListener = hwPerfSpeedLoader.HwPerfSetSpeedLoaderListener(obj, hwPerfVelocityCallback);
                if (HwPerfSetSpeedLoaderListener && (HwPerfGetSystemRefVelocity = hwPerfSpeedLoader.HwPerfGetSystemRefVelocity()) != null) {
                    String[] split = HwPerfGetSystemRefVelocity.split(":");
                    if (split.length > 0 && !"-1".equals(split[2])) {
                        hwPerfSpeedLoader.HwPerfSetThresholdVelocity(Float.valueOf(split[2]).floatValue());
                    }
                }
                return HwPerfSetSpeedLoaderListener;
            } catch (Exception e2) {
                String str = "JDImproveSDKUtils setDefalutListSpeedCallback Exception" + e2.toString();
                return false;
            }
        }
        return false;
    }

    public static void setVIPSceneStatus(int i2, int i3) {
        if (isHuawei()) {
            try {
                if (isRegister) {
                    mIAwareAppSdk.notifyAppScene(i2, i3);
                }
            } catch (Exception e2) {
                String str = "JDImproveSDKUtils setVIPSceneStatus Exception" + e2.toString();
            }
        }
    }
}
