package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class y6 {
    private static HashMap<String, ArrayList<g7>> a(Context context, List<g7> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap<String, ArrayList<g7>> hashMap = new HashMap<>();
        for (g7 g7Var : list) {
            d(context, g7Var);
            ArrayList<g7> arrayList = hashMap.get(g7Var.c());
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                hashMap.put(g7Var.c(), arrayList);
            }
            arrayList.add(g7Var);
        }
        return hashMap;
    }

    private static void b(Context context, b7 b7Var, HashMap<String, ArrayList<g7>> hashMap) {
        for (Map.Entry<String, ArrayList<g7>> entry : hashMap.entrySet()) {
            try {
                ArrayList<g7> value = entry.getValue();
                if (value != null && value.size() != 0) {
                    b7Var.a(value, value.get(0).e(), entry.getKey());
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void c(Context context, b7 b7Var, List<g7> list) {
        HashMap<String, ArrayList<g7>> a = a(context, list);
        if (a != null && a.size() != 0) {
            b(context, b7Var, a);
            return;
        }
        g.j.a.a.a.c.o("TinyData TinyDataCacheUploader.uploadTinyData itemsUploading == null || itemsUploading.size() == 0  ts:" + System.currentTimeMillis());
    }

    private static void d(Context context, g7 g7Var) {
        if (g7Var.f145a) {
            g7Var.a("push_sdk_channel");
        }
        if (TextUtils.isEmpty(g7Var.d())) {
            g7Var.f(com.xiaomi.push.service.e1.b());
        }
        g7Var.b(System.currentTimeMillis());
        if (TextUtils.isEmpty(g7Var.e())) {
            g7Var.e(context.getPackageName());
        }
        if (TextUtils.isEmpty(g7Var.c())) {
            g7Var.e(g7Var.e());
        }
    }
}
