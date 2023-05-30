package com.jd.lib.un.utils.fold;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jd.lib.un.utils.UnLog;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jd.lib.un.utils.config.UnUtilsConfig;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;

/* loaded from: classes16.dex */
public class FoldableUtils {
    private static final String TAG = "FoldableUtils";
    private static Pattern appBoundsPattern;
    private static String foldModelJson;
    private static List<String> foldModelList;
    private static Pattern maxBoundsPattern;
    private static volatile String sModel;

    static {
        try {
            if (appBoundsPattern == null) {
                appBoundsPattern = Pattern.compile("mBounds=Rect\\((-?\\d+),\\s(-?\\d+)\\s-\\s(\\d+),\\s(\\d+)\\)");
            }
            if (maxBoundsPattern == null) {
                maxBoundsPattern = Pattern.compile("mMaxBounds=Rect\\((\\d+),\\s(\\d+)\\s-\\s(\\d+),\\s(\\d+)\\)");
            }
        } catch (Throwable unused) {
        }
    }

    private FoldableUtils() {
    }

    @RequiresApi(api = 17)
    private static Pair<Rect, Rect> computeRectPairFromConfiguration(Activity activity) {
        Rect rect;
        String configuration = UnAndroidUtils.getResources(activity).getConfiguration().toString();
        Rect rect2 = null;
        if (appBoundsPattern != null && configuration.contains("mBounds")) {
            Matcher matcher = appBoundsPattern.matcher(configuration);
            if (matcher.find()) {
                rect = new Rect(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
                UnLog.i(TAG, "computeFromConfiguration() obtain rect:" + rect);
            } else {
                rect = null;
            }
        } else {
            Point point2 = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(point2);
            rect = new Rect(0, 0, point2.x, point2.y);
            UnLog.e(TAG, "computeFromConfiguration()  obtain rect from getSize(point):" + point2);
        }
        if (maxBoundsPattern != null && configuration.contains("mMaxBounds")) {
            Matcher matcher2 = maxBoundsPattern.matcher(configuration);
            if (matcher2.find()) {
                rect2 = new Rect(Integer.parseInt(matcher2.group(1)), Integer.parseInt(matcher2.group(2)), Integer.parseInt(matcher2.group(3)), Integer.parseInt(matcher2.group(4)));
                UnLog.i(TAG, "computeFromConfiguration() obtain maxRect:" + rect2);
            }
        } else {
            Point point3 = new Point();
            activity.getWindowManager().getDefaultDisplay().getRealSize(point3);
            rect2 = new Rect(0, 0, point3.x, point3.y);
            UnLog.e(TAG, "computeFromConfiguration()  obtain maxRect from getRealSize(point):" + rect2);
        }
        return new Pair<>(rect, rect2);
    }

    private static String getModel() {
        if (sModel == null) {
            sModel = UnDeviceInfo.getDeviceModel();
        }
        return sModel;
    }

    private static Pair<Rect, Rect> getRectPair(Activity activity) {
        Object obj;
        Object obj2;
        Pair<Rect, Rect> computeRectPairFromConfiguration = Build.VERSION.SDK_INT >= 17 ? computeRectPairFromConfiguration(activity) : null;
        if (computeRectPairFromConfiguration != null && (obj = computeRectPairFromConfiguration.first) != null && (obj2 = computeRectPairFromConfiguration.second) != null) {
            if (((Rect) obj).left < ((Rect) obj2).left) {
                ((Rect) obj).offset(((Rect) obj2).left - ((Rect) obj).left, 0);
            }
            Object obj3 = computeRectPairFromConfiguration.first;
            int i2 = ((Rect) obj3).top;
            Object obj4 = computeRectPairFromConfiguration.second;
            if (i2 < ((Rect) obj4).top) {
                ((Rect) obj3).offset(0, ((Rect) obj4).top - ((Rect) obj3).top);
            }
            Object obj5 = computeRectPairFromConfiguration.second;
            int i3 = ((Rect) obj5).right;
            Object obj6 = computeRectPairFromConfiguration.first;
            if (i3 < ((Rect) obj6).right) {
                ((Rect) obj6).offset(((Rect) obj5).right - ((Rect) obj6).right, 0);
            }
            Object obj7 = computeRectPairFromConfiguration.second;
            int i4 = ((Rect) obj7).bottom;
            Object obj8 = computeRectPairFromConfiguration.first;
            if (i4 < ((Rect) obj8).bottom) {
                ((Rect) obj8).offset(0, ((Rect) obj7).bottom - ((Rect) obj8).bottom);
            }
        }
        return computeRectPairFromConfiguration;
    }

    @WindowLocState
    public static int getWindowLocSate(Activity activity) {
        Object obj;
        Object obj2;
        int i2 = 0;
        if (Build.VERSION.SDK_INT >= 24 && activity != null) {
            try {
                if (isInMultiWindowMode(activity)) {
                    Pair<Rect, Rect> rectPair = getRectPair(activity);
                    if (rectPair != null) {
                        UnLog.e(TAG, "getWindowLocSate()  currentRect::" + rectPair.first + "maxRect::" + rectPair.second);
                    }
                    if (rectPair != null && (obj = rectPair.first) != null && (obj2 = rectPair.second) != null) {
                        Rect rect = (Rect) obj;
                        Rect rect2 = (Rect) obj2;
                        int i3 = rect2.left;
                        Rect rect3 = new Rect(i3, rect2.top, ((int) ((rect2.width() * 0.9f) + 1.0f)) + i3, rect2.bottom);
                        Rect rect4 = new Rect(rect2.left + ((int) ((rect2.width() * 0.1f) - 1.0f)), rect2.top, rect2.right, rect2.bottom);
                        int i4 = rect2.left;
                        int i5 = rect2.top;
                        Rect rect5 = new Rect(i4, i5, rect2.right, ((int) ((rect2.height() * 0.9f) + 1.0f)) + i5);
                        Rect rect6 = new Rect(rect2.left, rect2.top + ((int) ((rect2.height() * 0.1f) - 1.0f)), rect2.right, rect2.bottom);
                        if (rect3.contains(rect)) {
                            i2 = 1;
                        } else if (rect4.contains(rect)) {
                            i2 = 2;
                        } else if (rect5.contains(rect)) {
                            i2 = 3;
                        } else if (rect6.contains(rect)) {
                            i2 = 4;
                        }
                    }
                } else {
                    UnLog.e(TAG, "getWindowLocSate() not in isInMultiWindowMode");
                }
            } catch (Throwable unused) {
            }
            UnLog.e(TAG, "getWindowLocSate::state::" + i2);
        }
        return i2;
    }

    public static String getWindowLocSateStr(Activity activity) {
        int windowLocSate = getWindowLocSate(activity);
        return windowLocSate != 0 ? windowLocSate != 1 ? windowLocSate != 2 ? windowLocSate != 3 ? windowLocSate != 4 ? JDReactConstant.BUFF_DIR_FULL : "bottom" : "top" : "right" : "left" : JDReactConstant.BUFF_DIR_FULL;
    }

    public static boolean isFoldableDeviceFromConfig() {
        List<String> list;
        if (getModel() != null) {
            String config = JDMobileConfig.getInstance().getConfig("unification", "baseConfig", "foldScreen");
            if (TextUtils.equals(config, foldModelJson) && (list = foldModelList) != null) {
                return list.contains(sModel);
            }
            try {
                foldModelJson = config;
                JSONArray jSONArray = new JSONArray(foldModelJson);
                if (jSONArray.length() == 0) {
                    return false;
                }
                foldModelList = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    foldModelList.add(jSONArray.getString(i2));
                }
                return foldModelList.contains(sModel);
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean isHonorFoldableDevice() {
        try {
            return UnUtilsConfig.getInstance().getApplication().getPackageManager().hasSystemFeature("com.hihonor.hardware.sensor.posture");
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean isHuaweiFoldableDevice() {
        try {
            return UnUtilsConfig.getInstance().getApplication().getPackageManager().hasSystemFeature("com.huawei.hardware.sensor.posture");
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean isInMultiWindowMode(Activity activity) {
        if (Build.VERSION.SDK_INT < 24 || activity == null) {
            return false;
        }
        return activity.isInMultiWindowMode() || UnAndroidUtils.mateXEasyClientNew(activity) || isInMultiWindowModeFromConfig(activity);
    }

    private static boolean isInMultiWindowModeFromConfig(Activity activity) {
        if (activity != null) {
            String configuration = UnAndroidUtils.getResources(activity).getConfiguration().toString();
            for (String str : Constants.multiScreenTagArray) {
                if (configuration.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isOppoFoldableDevice() {
        try {
            Class<?> cls = Class.forName("com.oplus.content.OplusFeatureConfigManager");
            Object invoke = cls.getDeclaredMethod("hasFeature", String.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), "oplus.hardware.type.fold");
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isSamsungFoldableDevice() {
        try {
            if (getModel() != null) {
                if (!TextUtils.equals(sModel, "SM-F9000")) {
                    if (!TextUtils.equals(sModel, "SM-F9160")) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isSplitScreenInOppo(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains("oplus-magic-windows");
    }

    public static boolean isSplitScreenInVivo(String str) {
        return !TextUtils.isEmpty(str) && "VIVO".equalsIgnoreCase(UnDeviceInfo.getManufacturer()) && str.contains("multi-landscape");
    }

    public static boolean isVivoFoldableDevice() {
        try {
            Class<?> cls = Class.forName("android.util.FtDeviceInfo");
            return "foldable".equals(cls.getMethod("getDeviceType", new Class[0]).invoke(cls, new Object[0]));
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }
}
