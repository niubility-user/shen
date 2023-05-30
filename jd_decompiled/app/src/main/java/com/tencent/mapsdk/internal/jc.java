package com.tencent.mapsdk.internal;

import android.content.Context;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class jc extends hc {

    /* renamed from: c  reason: collision with root package name */
    private static final String f16740c = "Tencent_MapSDK_SUB_CONFIG";
    private static Map<String, hc> d = new HashMap();

    private jc(Context context, String str) {
        this.a = context.getSharedPreferences("Tencent_MapSDK_SUB_CONFIG_" + str, 0);
    }

    public static hc a(Context context, String str) {
        if (e7.b(str)) {
            return kc.a(context);
        }
        if (d.get(str) == null) {
            synchronized (jc.class) {
                if (d.get(str) == null) {
                    jc jcVar = new jc(context, str);
                    d.put(str, jcVar);
                    return jcVar;
                }
            }
        }
        return d.get(str);
    }

    public static void a(Context context) {
        try {
            File[] listFiles = new File(context.getFilesDir().getParent() + File.separator + "shared_prefs").listFiles();
            if (listFiles == null) {
                return;
            }
            for (File file : listFiles) {
                if (file.getName().startsWith(f16740c)) {
                    file.delete();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void c() {
        Map<String, hc> map = d;
        if (map != null) {
            map.clear();
        }
    }

    public boolean b(Context context, String str) {
        if (e7.b(str)) {
            return false;
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(context.getFilesDir().getParent());
            String str2 = File.separator;
            sb.append(str2);
            sb.append("shared_prefs");
            sb.append(str2);
            sb.append(f16740c);
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            sb.append(str);
            File file = new File(sb.toString());
            if (file.exists()) {
                return file.delete();
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
