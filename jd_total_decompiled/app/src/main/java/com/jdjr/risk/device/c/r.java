package com.jdjr.risk.device.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import androidx.core.hardware.display.DisplayManagerCompat;
import androidx.core.view.InputDeviceCompat;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jdjr.risk.assist.info.R;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes18.dex */
public class r {
    private static AtomicBoolean a = new AtomicBoolean(false);
    private static String b = "";

    /* renamed from: c */
    private static List<String> f7354c = new ArrayList();
    private static int d = -2;

    public static String a() {
        return b;
    }

    private static String a(String str) {
        try {
            String a2 = j.a("stat " + str, true);
            if (TextUtils.isEmpty(a2)) {
                return "";
            }
            String[] split = a2.split(System.getProperty("line.separator"));
            return split.length > 4 ? split[4] : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static void a(Context context) {
    }

    private static byte[] a(ByteBuffer byteBuffer) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
            messageDigest.update(byteBuffer.array());
            return messageDigest.digest();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static int b() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return "harmony".equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0])) ? 1 : 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String b(Context context) {
        try {
            Drawable drawable = context.getResources().getDrawable(R.drawable.jr_risk_dog);
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            ByteBuffer allocate = ByteBuffer.allocate(createBitmap.getByteCount());
            createBitmap.copyPixelsToBuffer(allocate);
            return Base64.encodeToString(a(allocate), 0);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String c() {
        boolean z = true;
        try {
            Class.forName("com.jingdong.common.lbs.jdlocation.JDLocationCache").getMethod("isMockLocation", Class.forName("com.jingdong.common.lbs.jdlocation.JDLocationCacheOption"));
        } catch (Throwable unused) {
            z = false;
        }
        if (z) {
            try {
                JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
                jDLocationCacheOption.setBusinessId("969f8a4c33e098979ce372b8d40630e2");
                return JDLocationCache.getInstance().isMockLocation(jDLocationCacheOption) ? "1" : "0";
            } catch (Throwable unused2) {
                return "0";
            }
        }
        return "0";
    }

    public static String c(Context context) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            canvas.drawRect(0.0f, 0.0f, 10.0f, 10.0f, paint);
            canvas.drawRect(2.0f, 2.0f, 8.0f, 8.0f, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(-39424);
            canvas.drawRect(125.0f, 1.0f, 187.0f, 21.0f, paint);
            paint.setTypeface(Typeface.MONOSPACE);
            paint.setColor(-16750951);
            canvas.drawText("Cwwm aa fjorddbank glbyphs veext qtuiz, \u1f603", 2.0f, 15.0f, paint);
            paint.setTypeface(Typeface.SANS_SERIF);
            paint.setColor(879152128);
            canvas.drawText("Cwwm aa fjorddbank glbyphs veext qtuiz, \u1f603", 4.0f, 45.0f, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
            paint.setStrokeWidth(0.0f);
            paint.setColor(-65281);
            canvas.drawCircle(50.0f, 50.0f, 50.0f, paint);
            paint.setColor(-16711681);
            canvas.drawCircle(100.0f, 50.0f, 50.0f, paint);
            paint.setColor(InputDeviceCompat.SOURCE_ANY);
            canvas.drawCircle(75.0f, 100.0f, 50.0f, paint);
            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.addRect(new RectF(0.0f, 0.0f, 10.0f, 10.0f), Path.Direction.CCW);
            path.addRect(new RectF(2.0f, 2.0f, 8.0f, 8.0f), Path.Direction.CCW);
            path.addRect(new RectF(125.0f, 1.0f, 187.0f, 21.0f), Path.Direction.CCW);
            path.addCircle(50.0f, 50.0f, 50.0f, Path.Direction.CCW);
            path.addCircle(100.0f, 50.0f, 50.0f, Path.Direction.CCW);
            path.addCircle(75.0f, 100.0f, 50.0f, Path.Direction.CCW);
            path.lineTo(150.0f, 75.0f);
            path.lineTo(100.0f, 75.0f);
            path.close();
            path.addCircle(75.0f, 75.0f, 75.0f, Path.Direction.CCW);
            path.addCircle(75.0f, 75.0f, 25.0f, Path.Direction.CCW);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(0.0f);
            paint.setColor(-65281);
            canvas.drawPath(path, paint);
            ByteBuffer allocate = ByteBuffer.allocate(createBitmap.getByteCount());
            createBitmap.copyPixelsToBuffer(allocate);
            return Base64.encodeToString(a(allocate), 0);
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String d() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(a("/sdcard/Download"));
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(a("/data/media"));
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(a("/sdcard"));
            sb.append(DYConstants.DY_REGEX_COMMA);
            sb.append(a("/data"));
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    public static String d(Context context) {
        try {
            return BaseInfo.getUserAgent();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String e() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(IMantoServerRequester.GET, String.class, String.class).invoke(null, "ro.boot.flash.locked", "");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String e(Context context) {
        String str = "";
        try {
            if (com.jd.sec.a.d() == null) {
                str = BaseInfo.getUserAgent();
            } else if (com.jd.sec.a.d().UserAgent() != null) {
                str = com.jd.sec.a.d().UserAgent();
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    public static String f(Context context) {
        try {
            return System.getProperty("http.agent");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String g(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            return resolveActivity != null ? resolveActivity.activityInfo.packageName : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String h(Context context) {
        try {
            if (f7354c.size() == 0) {
                f7354c.add("DT1901A");
                f7354c.add("DT1902A");
                f7354c.add("DT2002C");
            }
            if (Build.VERSION.SDK_INT < 18 || f7354c.contains(g.b().toUpperCase())) {
                return "";
            }
            try {
                return Base64.encodeToString(new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L)).getPropertyByteArray("deviceUniqueId"), 8);
            } catch (UnsupportedSchemeException unused) {
                return "unsupported";
            }
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static int i(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                return ((DisplayManager) context.getSystemService(ViewProps.DISPLAY)).getDisplays(DisplayManagerCompat.DISPLAY_CATEGORY_PRESENTATION).length;
            }
            return 0;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static int j(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                return ((DisplayManager) context.getSystemService(ViewProps.DISPLAY)).getDisplays(null).length;
            }
            return 0;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static int k(Context context) {
        try {
            return ((AudioManager) context.getSystemService("audio")).getMode();
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String l(Context context) {
        StringBuilder sb = new StringBuilder();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
        if (Build.VERSION.SDK_INT >= 31 && !com.jdjr.risk.util.a.e.a(context, "android.permission.READ_PHONE_STATE")) {
            sb.append("-1");
            return sb.toString();
        }
        int i2 = 0;
        try {
            sb.append(telephonyManager.getCallState());
        } catch (Throwable unused) {
            sb.append(0);
        }
        try {
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getCallState", Integer.TYPE);
            int i3 = 1;
            while (true) {
                if (i3 >= 21) {
                    break;
                }
                int intValue = ((Integer) declaredMethod.invoke(telephonyManager, Integer.valueOf(i3))).intValue();
                if (intValue != 0) {
                    i2 = intValue;
                    break;
                }
                i3++;
            }
        } catch (Throwable unused2) {
        }
        sb.append(i2);
        return sb.toString();
    }

    public static int m(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.nfc") ? 1 : 0;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String n(Context context) {
        return "";
    }

    public static int o(Context context) {
        try {
            return Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), "accessibility_enabled");
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String p(Context context) {
        try {
            return 1 == Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled") ? BaseInfo.getAndroidIdWithAOPBySecure(context.getContentResolver(), "enabled_accessibility_services") : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static List<String> q(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            String p = p(context);
            if (!TextUtils.isEmpty(p)) {
                String[] split = p.split(":");
                if (split.length > 0) {
                    for (String str : split) {
                        String substring = str.substring(0, str.indexOf("/"));
                        if (!TextUtils.isEmpty(substring)) {
                            arrayList.add(substring);
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    public static String r(Context context) {
        try {
            return BaseInfo.getAndroidIdWithAOPBySystem(context.getContentResolver(), "screen_off_timeout");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String s(Context context) {
        try {
            return BaseInfo.getAndroidIdWithAOPBySecure(context.getContentResolver(), "bluetooth_name");
        } catch (Throwable unused) {
            return "";
        }
    }

    public static int t(Context context) {
        TelephonyManager telephonyManager;
        try {
            if (-2 == d && (telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)) != null) {
                d = telephonyManager.getSimState();
            }
        } catch (Throwable unused) {
            d = -1;
        }
        return d;
    }

    public static String u(Context context) {
        try {
            return (BaseInfo.isAgreedPrivacy() && BaseInfo.isAppForeground() && TextUtils.isEmpty("")) ? ac.a() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String v(Context context) {
        try {
            return String.valueOf(BaseInfo.getAppPackageType());
        } catch (Throwable unused) {
            return "0";
        }
    }
}
