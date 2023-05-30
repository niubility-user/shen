package com.jd.stat.common;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    private static final String a = "a";

    public static String a(Context context) {
        try {
            if (Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled", 0) == 1) {
                ArrayList arrayList = new ArrayList();
                List<AccessibilityServiceInfo> enabledAccessibilityServiceList = ((AccessibilityManager) context.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1);
                if (enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.size() > 0) {
                    Iterator<AccessibilityServiceInfo> it = enabledAccessibilityServiceList.iterator();
                    while (it.hasNext()) {
                        ResolveInfo resolveInfo = it.next().getResolveInfo();
                        if (resolveInfo != null) {
                            arrayList.add(resolveInfo);
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    HashMap hashMap = new HashMap();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ServiceInfo serviceInfo = ((ResolveInfo) it2.next()).serviceInfo;
                        String str = serviceInfo.packageName;
                        String str2 = serviceInfo.name;
                        List list = (List) hashMap.get(str);
                        if (list == null) {
                            list = new ArrayList();
                            hashMap.put(str, list);
                        }
                        list.add(str2);
                        hashMap.put(str, list);
                    }
                    if (hashMap.size() > 0) {
                        HashMap hashMap2 = new HashMap();
                        for (String str3 : hashMap.keySet()) {
                            List list2 = (List) hashMap.get(str3);
                            if (list2 != null) {
                                Iterator it3 = list2.iterator();
                                while (it3.hasNext()) {
                                    if (a(context, str3, (String) it3.next())) {
                                        hashMap2.put(str3, Integer.valueOf((hashMap2.containsKey(str3) ? ((Integer) hashMap2.get(str3)).intValue() : 0) + 1));
                                    }
                                }
                            }
                        }
                        if (hashMap2.size() > 0) {
                            Iterator it4 = hashMap2.keySet().iterator();
                            String str4 = "";
                            while (it4.hasNext()) {
                                str4 = str4 + ((String) it4.next()) + ";";
                            }
                            return str4;
                        }
                        return "";
                    }
                    return "";
                }
                return "";
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        return a(context, false);
    }

    public static String c(Context context) {
        String str;
        String str2;
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (Settings.Secure.getInt(contentResolver, "accessibility_enabled", 0) == 1) {
                try {
                    str = a("", BaseInfo.getAndroidIdWithAOPBySecure(contentResolver, "accessibility_display_inversion_enabled"));
                } catch (Exception unused) {
                    str = "c";
                }
                try {
                    str2 = a(str, BaseInfo.getAndroidIdWithAOPBySecure(contentResolver, "speak_password"));
                } catch (Exception unused2) {
                    str2 = str + "c";
                }
                try {
                    return a(str2, BaseInfo.getAndroidIdWithAOPBySecure(contentResolver, "touch_exploration_enabled"));
                } catch (Exception unused3) {
                    return str2 + "c";
                }
            }
            return Constant.DEFAULT_CVN2;
        } catch (Exception unused4) {
            return "ccc";
        }
    }

    private static boolean b(Context context, String str, String str2) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 15) {
            Cursor query = context.getContentResolver().query(Uri.parse("content://" + str + ".providers.StatusProvider"), null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int i3 = query.getInt(0);
                query.close();
                if (i3 == 1) {
                    return true;
                }
            }
        } else if (i2 > 15) {
            return a(context, str + "/" + str2);
        }
        return false;
    }

    public static String a(Context context, boolean z) {
        boolean a2;
        List list;
        try {
            if (Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled", 0) == 1) {
                ArrayList arrayList = new ArrayList();
                List<AccessibilityServiceInfo> enabledAccessibilityServiceList = ((AccessibilityManager) context.getSystemService("accessibility")).getEnabledAccessibilityServiceList(-1);
                if (enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.size() > 0) {
                    Iterator<AccessibilityServiceInfo> it = enabledAccessibilityServiceList.iterator();
                    while (it.hasNext()) {
                        ResolveInfo resolveInfo = it.next().getResolveInfo();
                        if (resolveInfo != null) {
                            arrayList.add(resolveInfo);
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    HashMap hashMap = new HashMap();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        ServiceInfo serviceInfo = ((ResolveInfo) it2.next()).serviceInfo;
                        String str = serviceInfo.packageName;
                        String str2 = serviceInfo.name;
                        if (!hashMap.containsKey(str)) {
                            list = new ArrayList();
                        } else {
                            list = (List) hashMap.get(str);
                        }
                        list.add(str2);
                        hashMap.put(str, list);
                    }
                    if (hashMap.size() > 0) {
                        HashMap hashMap2 = new HashMap();
                        for (String str3 : hashMap.keySet()) {
                            for (String str4 : (List) hashMap.get(str3)) {
                                if (z) {
                                    a2 = b(context, str3, str4);
                                } else {
                                    a2 = a(context, str3, str4);
                                }
                                if (a2) {
                                    hashMap2.put(str3, Integer.valueOf((hashMap2.containsKey(str3) ? ((Integer) hashMap2.get(str3)).intValue() : 0) + 1));
                                }
                            }
                        }
                        if (hashMap2.size() > 0) {
                            JSONArray jSONArray = new JSONArray();
                            for (String str5 : hashMap2.keySet()) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("pg", str5);
                                jSONObject.put("count", hashMap2.get(str5));
                                jSONArray.put(jSONObject);
                            }
                            return jSONArray.toString();
                        }
                        return "a";
                    }
                    return "a";
                }
                return "a";
            }
            return "a";
        } catch (Exception unused) {
            return "c";
        }
    }

    private static boolean a(Context context, String str, String str2) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 <= 15) {
            Cursor query = context.getContentResolver().query(Uri.parse("content://" + str + ".providers.StatusProvider"), null, null, null, null);
            if (query != null && query.moveToFirst()) {
                int i3 = query.getInt(0);
                query.close();
                if (i3 == 1) {
                    return true;
                }
            }
        } else if (i2 >= 26) {
            return a(context, str + "/" + str2);
        } else {
            ArrayList arrayList = new ArrayList();
            Iterator<ActivityManager.RunningServiceInfo> it = BaseInfo.getRunningServices(context).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().service.getClassName());
            }
            if (arrayList.contains(str2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean a(Context context, String str) {
        TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
        String androidIdWithAOPBySecure = BaseInfo.getAndroidIdWithAOPBySecure(context.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
        if (androidIdWithAOPBySecure != null) {
            simpleStringSplitter.setString(androidIdWithAOPBySecure);
            while (simpleStringSplitter.hasNext()) {
                if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private static String a(String str, String str2) {
        if ("1".equals(str2)) {
            return str + "1";
        }
        return str + "0";
    }
}
