package com.jingdong.common.unification.statusbar;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class UnDeviceHelper {
    private static final String FLYME = "flyme";
    private static final String KEY_FLYME_VERSION_NAME = "ro.build.display.id";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String[] MEIZUBOARD = {"m9", "M9", "mx", "MX"};
    private static final String ZTEC2016 = "zte c2016";
    private static final String ZUKZ1 = "zuk z1";
    private static String sFlymeVersionName;
    private static String sMiuiVersionName;

    @Nullable
    private static String getLowerCaseName(Properties properties, Method method, String str) {
        String property = properties.getProperty(str);
        if (property == null) {
            try {
                return (String) method.invoke(null, str);
            } catch (Exception unused) {
                return property;
            }
        }
        return property.toLowerCase();
    }

    public static void init() {
        FileInputStream fileInputStream;
        Exception e2;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                } catch (Exception e3) {
                    fileInputStream = null;
                    e2 = e3;
                } catch (Throwable th) {
                    fileInputStream = null;
                    th = th;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    Properties properties = new Properties();
                    properties.load(fileInputStream);
                    Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod(IMantoServerRequester.GET, String.class);
                    sMiuiVersionName = getLowerCaseName(properties, declaredMethod, KEY_MIUI_VERSION_NAME);
                    sFlymeVersionName = getLowerCaseName(properties, declaredMethod, KEY_FLYME_VERSION_NAME);
                    fileInputStream.close();
                } catch (Exception e5) {
                    e2 = e5;
                    e2.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                }
            } catch (IOException e6) {
                e6.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isFlyme() {
        return !TextUtils.isEmpty(sFlymeVersionName) && sFlymeVersionName.contains(FLYME);
    }

    public static boolean isFlymeVersionHigher5_2_4() {
        boolean z;
        String group;
        String str = sFlymeVersionName;
        if (str != null && !str.equals("")) {
            Matcher matcher = Pattern.compile("(\\d+\\.){2}\\d").matcher(sFlymeVersionName);
            if (matcher.find() && (group = matcher.group()) != null && !group.equals("")) {
                String[] split = group.split("\\.");
                if (split.length == 3) {
                    if (Integer.valueOf(split[0]).intValue() >= 5) {
                        if (Integer.valueOf(split[0]).intValue() <= 5) {
                            if (Integer.valueOf(split[1]).intValue() >= 2) {
                                if (Integer.valueOf(split[1]).intValue() <= 2) {
                                    if (Integer.valueOf(split[2]).intValue() >= 4) {
                                        Integer.valueOf(split[2]).intValue();
                                    }
                                }
                            }
                        }
                    }
                    z = false;
                    return isMeizu() && z;
                }
            }
        }
        z = true;
        if (isMeizu()) {
            return false;
        }
    }

    public static boolean isMIUI() {
        return !TextUtils.isEmpty(sMiuiVersionName);
    }

    public static boolean isMIUIV5() {
        return "v5".equals(sMiuiVersionName);
    }

    public static boolean isMIUIV6() {
        return "v6".equals(sMiuiVersionName);
    }

    public static boolean isMIUIV7() {
        return "v7".equals(sMiuiVersionName);
    }

    public static boolean isMIUIV8() {
        return "v8".equals(sMiuiVersionName);
    }

    public static boolean isMIUIV9() {
        return "v9".equals(sMiuiVersionName);
    }

    public static boolean isMeizu() {
        return isPhone(MEIZUBOARD) || isFlyme();
    }

    private static boolean isPhone(String[] strArr) {
        String str = Build.BOARD;
        if (str == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isXiaomi() {
        return BaseInfo.getDeviceBrand().toLowerCase().contains("xiaomi");
    }

    public static boolean isZTKC2016() {
        String deviceModel = BaseInfo.getDeviceModel();
        return deviceModel != null && deviceModel.toLowerCase().contains(ZTEC2016);
    }

    public static boolean isZUKZ1() {
        String deviceModel = BaseInfo.getDeviceModel();
        return deviceModel != null && deviceModel.toLowerCase().contains(ZUKZ1);
    }
}
