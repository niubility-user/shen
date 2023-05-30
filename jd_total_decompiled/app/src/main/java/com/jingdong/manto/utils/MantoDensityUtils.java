package com.jingdong.manto.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.jingdong.manto.sdk.api.IHostBaseInfo;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class MantoDensityUtils {
    private static DisplayMetrics displayMetrics = com.jingdong.manto.c.a().getResources().getDisplayMetrics();
    private static float density = -1.0f;

    public static float convertToDeviceSize(float f2) {
        return dip2pixel(f2);
    }

    private static int convertToDeviceSize(JSONArray jSONArray, int i2) {
        if (jSONArray != null) {
            try {
                return Math.round(convertToDeviceSize((float) jSONArray.getDouble(i2)));
            } catch (Exception e2) {
                MantoLog.e("Canvas", e2.getMessage());
            }
        }
        return 0;
    }

    public static int convertToDeviceSize(JSONObject jSONObject, String str) {
        return Math.round(convertToDeviceSize((float) jSONObject.optDouble(str)));
    }

    public static float convertToDeviceSize2(JSONObject jSONObject, String str, float f2) {
        if (jSONObject != null) {
            try {
                return convertToDeviceSize((float) jSONObject.optDouble(str, f2));
            } catch (Exception unused) {
                return f2;
            }
        }
        return f2;
    }

    public static int convertToDeviceSize2(JSONArray jSONArray, int i2) {
        return convertToDeviceSize(jSONArray, i2);
    }

    public static int convertToDeviceSize2(JSONObject jSONObject, String str, int i2) {
        if (jSONObject != null) {
            try {
                return Math.round(convertToDeviceSize((float) jSONObject.getDouble(str)));
            } catch (Exception unused) {
                return i2;
            }
        }
        return i2;
    }

    public static float convertToDeviceSizeByFloat(JSONObject jSONObject, String str) {
        return convertToDeviceSize((float) jSONObject.optDouble(str));
    }

    public static int convertToDeviceSizeByInt(int i2) {
        return Math.round(dip2pixel(i2));
    }

    public static int convertToWebSize(int i2) {
        return pixel2dip(i2);
    }

    private static int convertToWebSize(JSONArray jSONArray, int i2) {
        if (jSONArray != null) {
            try {
                return Math.round(convertToWebSize(jSONArray.getInt(i2)));
            } catch (Exception unused) {
            }
        }
        return 0;
    }

    public static float dip2pixel(double d) {
        if (displayMetrics != null) {
            double density2 = getDensity(null);
            Double.isNaN(density2);
            d *= density2;
        }
        return (float) d;
    }

    public static float dip2pixel(float f2) {
        return displayMetrics == null ? f2 : f2 * getDensity(null);
    }

    public static float dip2pixel(int i2) {
        float f2 = i2;
        return displayMetrics == null ? f2 : f2 * getDensity(null);
    }

    public static int dip2pixel(Context context, int i2) {
        return Math.round(getDensity(context) * i2);
    }

    public static DisplayMetrics getDM() {
        IHostBaseInfo iHostBaseInfo = (IHostBaseInfo) com.jingdong.a.n(IHostBaseInfo.class);
        return iHostBaseInfo != null ? iHostBaseInfo.getDm() : com.jingdong.manto.c.a().getResources().getDisplayMetrics();
    }

    public static int getDMHeightPixels() {
        IHostBaseInfo iHostBaseInfo = (IHostBaseInfo) com.jingdong.a.n(IHostBaseInfo.class);
        return iHostBaseInfo != null ? iHostBaseInfo.getDMHeightPixels() : com.jingdong.manto.c.a().getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDMWidthPixels() {
        IHostBaseInfo iHostBaseInfo = (IHostBaseInfo) com.jingdong.a.n(IHostBaseInfo.class);
        return iHostBaseInfo != null ? iHostBaseInfo.getDMWidthPixels() : com.jingdong.manto.c.a().getResources().getDisplayMetrics().widthPixels;
    }

    public static float getDensity(Context context) {
        float f2 = density;
        if (f2 > 0.0f) {
            return f2;
        }
        IHostBaseInfo iHostBaseInfo = (IHostBaseInfo) com.jingdong.a.n(IHostBaseInfo.class);
        if (iHostBaseInfo != null) {
            return iHostBaseInfo.getDensity();
        }
        if (context == null) {
            context = com.jingdong.manto.c.a();
        }
        if (density < 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static DisplayMetrics getRealDM(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            IHostBaseInfo iHostBaseInfo = (IHostBaseInfo) com.jingdong.a.n(IHostBaseInfo.class);
            if (iHostBaseInfo != null) {
                return iHostBaseInfo.getRealDm(activity);
            }
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics2);
            return displayMetrics2;
        }
        return null;
    }

    public static int getStatusBarHeight(Context context, int i2) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(MantoUtils.getInt(cls.getField("status_bar_height").get(cls.newInstance()).toString(), 0));
        } catch (Throwable unused) {
            return i2;
        }
    }

    public static int getVisibleTopPosition(Context context) {
        int statusBarHeight = getStatusBarHeight(context, -1);
        if (statusBarHeight > 0) {
            Rect rect = new Rect();
            ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            if (rect.top > statusBarHeight) {
                return 0;
            }
            return statusBarHeight;
        } else if (context instanceof Activity) {
            Rect rect2 = new Rect();
            Activity activity = (Activity) context;
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
            int height = activity.getWindow().getDecorView().getHeight();
            int[] iArr = new int[2];
            activity.getWindow().getDecorView().getLocationOnScreen(iArr);
            return (height - rect2.height() < 0 || iArr[1] <= 200) ? rect2.top : height - rect2.height();
        } else {
            return dip2pixel(context, 20);
        }
    }

    public static int parseColor(String str) {
        long parseLong;
        long parseLong2;
        if (str != null && str.length() != 0) {
            if (str.charAt(0) == '#') {
                if (str.length() == 7) {
                    parseLong = Long.parseLong(str.substring(1), 16);
                    parseLong2 = -16777216;
                } else if (str.length() != 9) {
                    throw new IllegalArgumentException("Unknown color");
                } else {
                    parseLong = Long.parseLong(str.substring(1, 7), 16);
                    parseLong2 = Long.parseLong(str.substring(7, 9), 16) << 24;
                }
                return (int) (parseLong | parseLong2);
            }
            try {
                return Color.parseColor(str);
            } catch (IllegalArgumentException unused) {
            }
        }
        return 0;
    }

    public static int parseColor(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return parseColor(str);
        } catch (Exception unused) {
            return i2;
        }
    }

    public static int parseColor(JSONArray jSONArray) {
        try {
            int optInt = jSONArray.optInt(0);
            int optInt2 = jSONArray.optInt(1);
            int optInt3 = jSONArray.optInt(2);
            return jSONArray.length() == 3 ? Color.argb(255, optInt, optInt2, optInt3) : Color.argb(jSONArray.optInt(3), optInt, optInt2, optInt3);
        } catch (Throwable th) {
            th.printStackTrace();
            return -16777216;
        }
    }

    public static int parseInt(JSONArray jSONArray, int i2) {
        try {
            return jSONArray.optInt(0);
        } catch (Exception unused) {
            return i2;
        }
    }

    public static int parseIntFromPosition(JSONArray jSONArray, int i2) {
        try {
            return jSONArray.optInt(i2);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int parseToPixFromPosition(JSONArray jSONArray, int i2) {
        int i3;
        try {
            i3 = jSONArray.optInt(i2);
        } catch (Exception unused) {
            i3 = 0;
        }
        return Math.round(dip2pixel(i3));
    }

    public static float pixel2dip(float f2) {
        return displayMetrics == null ? f2 : f2 / getDensity(null);
    }

    public static int pixel2dip(int i2) {
        return displayMetrics == null ? i2 : (int) (i2 / getDensity(null));
    }
}
