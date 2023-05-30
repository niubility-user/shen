package com.meizu.cloud.pushsdk.f.g;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class e {
    private static final String a = "e";

    public static long a(String str) {
        long j2;
        long j3 = 0;
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt <= '\u007f') {
                j2 = 1;
            } else if (charAt <= '\u07ff') {
                j2 = 2;
            } else {
                if (charAt >= '\ud800' && charAt <= '\udfff') {
                    j3 += 4;
                    i2++;
                } else if (charAt < '\uffff') {
                    j2 = 3;
                } else {
                    j3 += 4;
                }
                i2++;
            }
            j3 += j2;
            i2++;
        }
        return j3;
    }

    private static Object b(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            return obj;
        }
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj;
        }
        if (obj instanceof Collection) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = ((Collection) obj).iterator();
            while (it.hasNext()) {
                jSONArray.put(b(it.next()));
            }
            return jSONArray;
        } else if (obj.getClass().isArray()) {
            JSONArray jSONArray2 = new JSONArray();
            int length = Array.getLength(obj);
            for (int i2 = 0; i2 < length; i2++) {
                jSONArray2.put(b(Array.get(obj, i2)));
            }
            return jSONArray2;
        } else if (obj instanceof Map) {
            return e((Map) obj);
        } else {
            if ((obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof String)) {
                return obj;
            }
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        }
    }

    public static String c() {
        return UUID.randomUUID().toString();
    }

    public static String d(Context context) {
        try {
            if (((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)) != null) {
                return BaseInfo.getNetworkOperatorName();
            }
            return null;
        } catch (Exception e2) {
            c.f(a, "getCarrier: %s", e2.toString());
            return null;
        }
    }

    public static JSONObject e(Map map) {
        if (Build.VERSION.SDK_INT >= 19) {
            return new JSONObject(map);
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            Object b = b(entry.getValue());
            try {
                jSONObject.put(str, b);
            } catch (JSONException e2) {
                c.f(a, "Could not put key '%s' and value '%s' into new JSONObject: %s", str, b, e2);
                e2.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static boolean f(long j2, long j3, long j4) {
        return j2 > j3 - j4;
    }

    @TargetApi(19)
    public static Point g(Context context) {
        WindowManager windowManager;
        Point point2 = new Point();
        Display display = null;
        try {
            windowManager = (WindowManager) context.getSystemService("window");
        } catch (Exception unused) {
            String str = a;
            c.f(str, "Display.getSize isn't available on older devices.", new Object[0]);
            if (display != null) {
                point2.x = display.getWidth();
                point2.y = display.getHeight();
            } else {
                c.f(str, "error get display", new Object[0]);
            }
        }
        if (windowManager == null) {
            return null;
        }
        display = windowManager.getDefaultDisplay();
        Display.class.getMethod("getSize", Point.class);
        display.getSize(point2);
        return point2;
    }

    public static String h() {
        return Long.toString(System.currentTimeMillis());
    }

    public static String i(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE);
            if (telephonyManager != null) {
                return telephonyManager.getSimOperator();
            }
        } catch (Exception e2) {
            c.f(a, "getOperator error " + e2.getMessage(), new Object[0]);
        }
        return null;
    }

    public static boolean j(Context context) {
        try {
            String str = a;
            c.g(str, "Checking tracker internet connectivity.", new Object[0]);
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
            c.e(str, "Tracker connection online: %s", Boolean.valueOf(z));
            return z;
        } catch (Exception e2) {
            c.f(a, "Security exception checking connection: %s", e2.toString());
            return true;
        }
    }
}
