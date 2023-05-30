package com.jingdong.common.unification.uniutil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.RequiresApi;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Deprecated
/* loaded from: classes6.dex */
public class UnAndroidUtils {
    private static final String BRAND_HUAWEI = "HUAWEI";
    private static final String BRAND_OPPO = "OPPO";
    private static final String BRAND_VIVO = "VIVO";
    private static final String BRAND_XIAOMI = "XIAOMI";
    public static final int NOTCH_IN_SCREEN_VOIO = 32;
    private static String[] cutoutPhoneModels = {""};

    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT < 17) {
            return (ViewConfiguration.get(activity).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point2 = new Point();
        Point point3 = new Point();
        defaultDisplay.getSize(point2);
        defaultDisplay.getRealSize(point3);
        return point3.y != point2.y;
    }

    public static int computeUsableHeight(Activity activity) {
        View findViewById;
        if (activity == null || (findViewById = activity.findViewById(16908290)) == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        return rect.bottom;
    }

    public static int getAndroidVersion() {
        return com.jd.lib.un.utils.UnAndroidUtils.getAndroidVersion();
    }

    public static String getBrand() {
        return com.jd.lib.un.utils.UnAndroidUtils.getBrand();
    }

    public static String getModel() {
        return com.jd.lib.un.utils.UnAndroidUtils.getModel();
    }

    public static String getParnter() {
        return Configuration.getProperty(Configuration.PARTNER, "");
    }

    public static String getSystemProperties(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod(IMantoServerRequester.GET, String.class).invoke(cls, str);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return "";
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            return "";
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
            return "";
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            return "";
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return "";
        }
    }

    public static boolean hasNotchInScreenAtHuawei(Context context) {
        try {
            try {
                Class<?> loadClass = context.getClassLoader().loadClass("com.huawei.android.util.HwNotchSizeUtil");
                return ((Boolean) loadClass.getMethod("hasNotchInScreen", new Class[0]).invoke(loadClass, new Object[0])).booleanValue();
            } catch (ClassNotFoundException unused) {
                if (Log.E) {
                    Log.e("test", "hasNotchInScreen ClassNotFoundException");
                }
                return false;
            } catch (NoSuchMethodException unused2) {
                if (Log.E) {
                    Log.e("test", "hasNotchInScreen NoSuchMethodException");
                }
                return false;
            } catch (Exception unused3) {
                if (Log.E) {
                    Log.e("test", "hasNotchInScreen Exception");
                }
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    public static boolean hasNotchInScreenAtOppo(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    public static boolean hasNotchInScreenAtVivo(Context context) {
        try {
            try {
                Class<?> loadClass = context.getClassLoader().loadClass("com.util.FtFeature");
                return ((Boolean) loadClass.getMethod("isFeatureSupport", Integer.TYPE).invoke(loadClass, 32)).booleanValue();
            } catch (ClassNotFoundException unused) {
                if (Log.E) {
                    Log.e("test", "hasNotchInScreen ClassNotFoundException");
                }
                return false;
            } catch (NoSuchMethodException unused2) {
                if (Log.E) {
                    Log.e("test", "hasNotchInScreen NoSuchMethodException");
                }
                return false;
            } catch (Exception unused3) {
                if (Log.E) {
                    Log.e("test", "hasNotchInScreen Exception");
                }
                return false;
            }
        } catch (Throwable unused4) {
            return false;
        }
    }

    public static boolean hasNotchInScreenXiaomi(Context context) {
        return TextUtils.equals(getSystemProperties("ro.miui.notch"), "1");
    }

    public static boolean isDisplayCutout() {
        String brand = getBrand();
        if (Log.D) {
            Log.d(ConfigUtil.KEY_DISPLAY_CUTOUT_PHONE_MODEL, "\u5382\u5546\uff1a" + brand + LangUtils.SINGLE_SPACE + Build.VERSION.SDK_INT);
        }
        if (TextUtils.isEmpty(brand)) {
            return false;
        }
        if (TextUtils.equals(BRAND_HUAWEI, brand.toUpperCase())) {
            if (Log.D) {
                Log.d(ConfigUtil.KEY_DISPLAY_CUTOUT_PHONE_MODEL, "\u534e\u4e3a\u624b\u673a\uff1a " + hasNotchInScreenAtHuawei(JdSdk.getInstance().getApplicationContext()));
            }
            return hasNotchInScreenAtHuawei(JdSdk.getInstance().getApplicationContext());
        } else if (TextUtils.equals("VIVO", brand.toUpperCase())) {
            if (Log.D) {
                Log.d(ConfigUtil.KEY_DISPLAY_CUTOUT_PHONE_MODEL, "vivo\u624b\u673a\uff1a " + hasNotchInScreenAtVivo(JdSdk.getInstance().getApplicationContext()));
            }
            return hasNotchInScreenAtVivo(JdSdk.getInstance().getApplicationContext());
        } else if (TextUtils.equals("OPPO", brand.toUpperCase())) {
            if (Log.D) {
                Log.d(ConfigUtil.KEY_DISPLAY_CUTOUT_PHONE_MODEL, "oppo\u624b\u673a\uff1a " + hasNotchInScreenAtOppo(JdSdk.getInstance().getApplicationContext()));
            }
            return hasNotchInScreenAtOppo(JdSdk.getInstance().getApplicationContext());
        } else if (TextUtils.equals(BRAND_XIAOMI, brand.toUpperCase())) {
            if (Log.D) {
                Log.d(ConfigUtil.KEY_DISPLAY_CUTOUT_PHONE_MODEL, "\u5c0f\u7c73\u624b\u673a\uff1a " + hasNotchInScreenXiaomi(JdSdk.getInstance().getApplicationContext()));
            }
            return hasNotchInScreenXiaomi(JdSdk.getInstance().getApplicationContext());
        } else {
            String model = getModel();
            if (TextUtils.isEmpty(model)) {
                return false;
            }
            String lowerCase = model.toLowerCase();
            String stringFromPreference = ConfigUtil.getStringFromPreference(ConfigUtil.KEY_DISPLAY_CUTOUT_PHONE_MODEL, "");
            if (!TextUtils.isEmpty(stringFromPreference)) {
                List parseArray = JDJSON.parseArray(stringFromPreference.toLowerCase(), String.class);
                if (parseArray != null && parseArray.size() > 0) {
                    return parseArray.contains(lowerCase);
                }
            } else {
                for (String str : cutoutPhoneModels) {
                    if (TextUtils.equals(lowerCase, str)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @RequiresApi(api = 17)
    public static boolean isFullScreenModel() {
        String string = Settings.Global.getString(JdSdk.getInstance().getApplicationContext().getContentResolver(), "policy_control");
        if (string != null) {
            string = string.trim();
        }
        return TextUtils.equals("immersive.full=*", string) || TextUtils.equals("immersive.status=*", string);
    }

    public static boolean isTabletDevice(Context context) {
        return context != null && (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }
}
