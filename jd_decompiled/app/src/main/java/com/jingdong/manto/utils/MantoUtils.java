package com.jingdong.manto.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Looper;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.INetwork;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* loaded from: classes16.dex */
public class MantoUtils {
    private static final String TAG = "MantoUtils";
    private static volatile com.jingdong.manto.sdk.thread.a workerHandlerThread;
    private static final Object lock = new Object();
    private static final char[] readableChars = {Typography.less, Typography.greater, Typography.quote, '\'', Typography.amp, '\r', '\n', ' ', '\t'};
    private static final String[] htmlChars = {"&lt;", "&gt;", "&quot;", "&apos;", "&amp;", "&#x0D;", "&#x0A;", "&#x20;", "&#x09;"};

    public static boolean buildEG(int i2) {
        return Build.VERSION.SDK_INT >= i2;
    }

    public static boolean containsChineseCharacter(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(str).find();
    }

    public static Map formatBundle(Bundle bundle) {
        Pair<Integer, Object> jsonType;
        if (bundle == null) {
            return null;
        }
        Set<String> keySet = bundle.keySet();
        HashMap hashMap = new HashMap(1);
        if (keySet != null && (r0 = keySet.iterator()) != null) {
            for (String str : keySet) {
                Object obj = bundle.get(str);
                if ((obj instanceof String) && (jsonType = getJsonType((String) obj)) != null) {
                    obj = jsonType.second;
                }
                hashMap.put(str, obj);
            }
        }
        return hashMap;
    }

    public static JSONObject formatBundleToJson(Bundle bundle) {
        Map formatBundle = formatBundle(bundle);
        mapToJson(formatBundle);
        return new JSONObject(formatBundle);
    }

    public static Bundle formatJson(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                bundle.putString(next, jSONObject.getString(next));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return bundle;
    }

    public static String generateAppUniqueId(String str, String str2) {
        return MantoMd5Utils.md5OfString(str + "(O_O)" + str2);
    }

    public static String getActivityTaskAffinity(ComponentName componentName) {
        PackageManager packageManager;
        if (componentName != null && (packageManager = com.jingdong.manto.c.a().getPackageManager()) != null) {
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
                if (activityInfo != null) {
                    return activityInfo.taskAffinity;
                }
            } catch (Exception e2) {
                MantoLog.e(TAG, "getActivityTaskAffinity e = ", e2);
            }
        }
        return "[UNKNOWN]";
    }

    public static String getAppVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static long getApplicationMemory(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return 0L;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return (memoryInfo.availMem / 1024) / 1024;
    }

    public static float getFloat(String str, float f2) {
        if (str != null) {
            try {
                return Float.parseFloat(str);
            } catch (Throwable unused) {
                return f2;
            }
        }
        return f2;
    }

    public static int getInt(Integer num, int i2) {
        return num == null ? i2 : num.intValue();
    }

    public static int getInt(String str, int i2) {
        if (str != null) {
            try {
                return str.length() > 0 ? Integer.decode(str).intValue() : i2;
            } catch (Throwable unused) {
                return i2;
            }
        }
        return i2;
    }

    public static Pair<Integer, Object> getJsonType(String str) {
        Pair<Integer, Object> pair;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Object nextValue = new JSONTokener(str).nextValue();
            if (nextValue instanceof JSONObject) {
                pair = new Pair<>(1, nextValue);
            } else if (!(nextValue instanceof JSONArray)) {
                return null;
            } else {
                pair = new Pair<>(2, nextValue);
            }
            return pair;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static String getMeta(String str) {
        Context a = com.jingdong.manto.c.a();
        PackageManager packageManager = a.getPackageManager();
        if (packageManager != null) {
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(a.getPackageName(), 128);
                if (applicationInfo != null) {
                    return applicationInfo.metaData.getString(str);
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static int getNetType(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return -1;
        }
        if (activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 9) {
            return 0;
        }
        MantoLog.d(TAG, String.format("activeNetInfo extra=%s, type=%d", activeNetworkInfo.getExtraInfo(), Integer.valueOf(activeNetworkInfo.getType())));
        if (activeNetworkInfo.getExtraInfo() != null) {
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("uninet")) {
                return 1;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("uniwap")) {
                return 2;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("3gwap")) {
                return 3;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("3gnet")) {
                return 4;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("cmwap")) {
                return 5;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("cmnet")) {
                return 6;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("ctwap")) {
                return 7;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("ctnet")) {
                return 8;
            }
            if (activeNetworkInfo.getExtraInfo().equalsIgnoreCase("LTE")) {
                return 10;
            }
        }
        return 9;
    }

    public static String getNetworkType(Context context) {
        INetwork iNetwork = (INetwork) MantoSdkManager.instanceOf(INetwork.class);
        if (iNetwork != null) {
            return iNetwork.getNetworkType(context);
        }
        if (isConnected(context)) {
            if (isWifi(context)) {
                return "wifi";
            }
            try {
                switch (Build.VERSION.SDK_INT >= 30 ? ((TelephonyManager) context.getSystemService(SignUpTable.TB_COLUMN_PHONE)).getDataNetworkType() : BaseInfo.getNetworkTypeInt()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2g";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3g";
                    case 13:
                        return "4g";
                    default:
                        return "unknown";
                }
            } catch (Throwable unused) {
                return "unknown";
            }
        }
        return "none";
    }

    public static String getNonNull(String str) {
        return str == null ? "" : str;
    }

    public static String getNonNull(String str, String str2) {
        return str == null ? str2 : str;
    }

    public static int getProcessMemory(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            return memoryInfo.getTotalPss() / 1024;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static String getProviderAuthorities(Class<? extends ContentProvider> cls) {
        Context a = com.jingdong.manto.c.a();
        PackageManager packageManager = a.getPackageManager();
        if (packageManager != null) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(a.getPackageName(), 8);
                if (packageInfo != null) {
                    for (ProviderInfo providerInfo : packageInfo.providers) {
                        if (providerInfo.name.equals(cls.getName())) {
                            return providerInfo.authority;
                        }
                    }
                    return null;
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static long getTime() {
        return SystemClock.elapsedRealtime();
    }

    public static long getTimeCost(long j2) {
        return SystemClock.elapsedRealtime() - j2;
    }

    public static boolean hasInstalledMapApp(Context context) {
        String[] strArr = {"com.tencent.map", "com.baidu.BaiduMap", "com.autonavi.minimap"};
        for (int i2 = 0; i2 < 3; i2++) {
            if (isPackageInstalled(strArr[i2])) {
                return true;
            }
        }
        return false;
    }

    public static boolean isConnected(Context context) {
        INetwork iNetwork = (INetwork) MantoSdkManager.instanceOf(INetwork.class);
        if (iNetwork != null) {
            return iNetwork.isConnected(context);
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        try {
            return connectivityManager.getActiveNetworkInfo().isConnected();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isPackageInstalled(String str) {
        PackageInfo packageInfo;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            packageInfo = com.jingdong.manto.c.a().getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public static Boolean isTrue(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (DYConstants.DY_TRUE.equalsIgnoreCase(str)) {
                return Boolean.TRUE;
            }
            if (DYConstants.DY_FALSE.equalsIgnoreCase(str)) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    public static boolean isWifi(Context context) {
        return getNetType(context) == 0;
    }

    public static Bundle jsonStringToBundle(String str) {
        try {
            return jsonToBundle(toJsonObject(str));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return new Bundle();
        }
    }

    private static Bundle jsonToBundle(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            setBundleValue(bundle, next, jSONObject.get(next));
        }
        return bundle;
    }

    public static Map<String, Object> jsonToMap(JSONObject jSONObject) {
        return jSONObject != JSONObject.NULL ? toMap(jSONObject) : new HashMap();
    }

    public static void mapToJson(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if ((key instanceof String) && (value instanceof Map)) {
                Map map2 = (Map) value;
                mapToJson(map2);
                map.put(key, new JSONObject(map2));
            }
        }
    }

    public static void qualityClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                MantoLog.e(TAG, "qualityClose", th);
            }
        }
    }

    public static String replaceChangeLineCharacter(String str) {
        if (str == null) {
            return null;
        }
        return str.replace('\u2028', '\n').replace('\u2029', '\n');
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            com.jingdong.manto.sdk.thread.a.a(runnable);
        } else {
            runnable.run();
        }
    }

    public static void setBundleValue(Bundle bundle, String str, Object obj) {
        if (obj instanceof Boolean) {
            bundle.putBoolean(str, isTrue(obj).booleanValue());
        } else if (obj instanceof Byte) {
            bundle.putByte(str, ((Byte) obj).byteValue());
        } else if (obj instanceof Character) {
            bundle.putChar(str, ((Character) obj).charValue());
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        } else if (obj instanceof Float) {
            bundle.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Short) {
            bundle.putShort(str, ((Short) obj).shortValue());
        } else {
            bundle.putString(str, obj instanceof String ? (String) obj : obj.toString());
        }
    }

    public static void setStatusBarBackColor(Window window) {
        if (window == null || Build.VERSION.SDK_INT < 21) {
            return;
        }
        if ((window.getAttributes().flags & Integer.MIN_VALUE) == 0 || window.getStatusBarColor() != 0) {
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(-16777216);
        }
    }

    public static boolean setSystemUiVisible(Window window, boolean z) {
        if (window == null || window.getDecorView() == null || Build.VERSION.SDK_INT < 23) {
            return false;
        }
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        return true;
    }

    private static JSONObject toJsonObject(String str) {
        return new JSONObject(str);
    }

    public static List<Object> toList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            Object obj = jSONArray.get(i2);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                obj = toList((JSONArray) obj);
            } else if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            hashMap.put(next, obj);
        }
        return hashMap;
    }

    public static String transferHtmlCharacters(String str) {
        int i2;
        StringBuffer stringBuffer = new StringBuffer();
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = 0;
            while (i4 < readableChars.length) {
                String str2 = htmlChars[i4];
                int i5 = 0;
                while (i5 < str2.length() && (i2 = i3 + i5) < length && str2.charAt(i5) == str.charAt(i2)) {
                    i5++;
                }
                if (i5 == str2.length()) {
                    break;
                }
                i4++;
            }
            char[] cArr = readableChars;
            if (i4 != cArr.length) {
                stringBuffer.append(cArr[i4]);
                i3 += htmlChars[i4].length();
            } else {
                stringBuffer.append(str.charAt(i3));
                i3++;
            }
        }
        return stringBuffer.toString();
    }
}
