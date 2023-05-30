package com.jingdong.aura.core.shadow;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.aura.a.b.e;
import com.jingdong.aura.a.c.l;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class c {
    private static HashMap<String, String> a = new HashMap<>();
    private static HashMap<String, String> b = new HashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private static HashMap<String, List<String>> f12166c = new HashMap<>();
    private static final com.jingdong.aura.core.util.l.b d = com.jingdong.aura.core.util.l.c.a("ShadowWrapper");

    private static String a(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? "std" : "sins" : "stk" : "stp";
    }

    public static String a(ActivityInfo activityInfo) {
        String str = a.get(activityInfo.name);
        if (str != null) {
            return str;
        }
        String a2 = a(activityInfo.processName);
        String b2 = b(activityInfo.taskAffinity);
        StringBuilder sb = new StringBuilder("com.jingdong.aura.shadow");
        if (!TextUtils.isEmpty(a2)) {
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(a2);
        }
        if (!TextUtils.isEmpty(b2)) {
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(b2);
        }
        String a3 = a(activityInfo.launchMode);
        if (!TextUtils.isEmpty(a3)) {
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(a3);
        }
        sb.append("_Activity");
        String sb2 = sb.toString();
        List<String> list = f12166c.get(sb2);
        if (list == null) {
            list = c(sb2);
            f12166c.put(sb2, list);
        }
        Iterator<String> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String next = it.next();
            if (b.get(next) == null) {
                str = next;
                break;
            }
        }
        if (str == null) {
            StringBuilder sb3 = new StringBuilder();
            for (String str2 : list) {
                d.d(String.format("shadow info:[%s -> %s]", b.get(str2), str2));
                sb3.append(String.format("shadow info:[%s -> %s]\n", b.get(str2), str2));
            }
            e.a("shadow_drain", "com.jingdong.aura", 0, sb3.toString(), "ShadowWrapper");
            return "com.jingdong.aura.shadow_std_Activity0";
        }
        if (!a3.equals("std")) {
            b.put(str, activityInfo.name);
            a.put(activityInfo.name, str);
        }
        return str;
    }

    public static Intent b(Intent intent) {
        if (intent.getComponent().getClassName().startsWith("com.jingdong.aura.shadow")) {
            return (Intent) intent.getParcelableExtra("aura_shadow_origin_intent");
        }
        return null;
    }

    private static List<String> c(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            PackageInfo packageInfo = l.a.getPackageManager().getPackageInfo(l.a.getPackageName(), 1);
            int i2 = 0;
            while (true) {
                ActivityInfo[] activityInfoArr = packageInfo.activities;
                if (i2 >= activityInfoArr.length) {
                    break;
                }
                ActivityInfo activityInfo = activityInfoArr[i2];
                if (activityInfo.name.startsWith(str)) {
                    arrayList.add(activityInfo.name);
                }
                i2++;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    public static void d(String str) {
        String str2 = a.get(str);
        if (str2 != null) {
            a.remove(str);
            b.remove(str2);
        }
    }

    private static String b(String str) {
        if (str.equals(l.a.getApplicationInfo().taskAffinity)) {
            return null;
        }
        return str.replace(OrderISVUtil.MONEY_DECIMAL, CartConstant.KEY_YB_INFO_LINK);
    }

    public static Intent c(Intent intent) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            Intent intent2 = new Intent();
            intent2.addFlags(intent.getFlags());
            intent2.setComponent(new ComponentName(component.getPackageName(), "com.jingdong.aura.core.shadow.BridgeActivity"));
            intent2.putExtra("aura_bridge_to_intent", intent);
            return intent2;
        }
        return intent;
    }

    public static Intent a(Intent intent) {
        return (Intent) intent.getParcelableExtra("aura_bridge_to_intent");
    }

    public static Intent a(Intent intent, String str) {
        ComponentName component = intent.getComponent();
        if (component != null) {
            Intent intent2 = new Intent();
            intent2.addFlags(intent.getFlags());
            intent2.setComponent(new ComponentName(component.getPackageName(), str));
            intent2.putExtra("aura_shadow_origin_intent", intent);
            return intent2;
        }
        return intent;
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = l.a.getPackageName();
        }
        if (str.equals(l.a.getPackageName())) {
            return null;
        }
        String str2 = l.a.getPackageName() + ":";
        if (str.startsWith(str2)) {
            return str.substring(str2.length());
        }
        return str.replace(OrderISVUtil.MONEY_DECIMAL, CartConstant.KEY_YB_INFO_LINK);
    }
}
