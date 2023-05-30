package com.jingdong.manto.m.i1;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes15.dex */
public class c {
    public static String a(String str, String str2) {
        return com.jingdong.manto.c.a().getSharedPreferences("manto_push_" + str, 4).getString("not_ask_" + str2, "");
    }

    public static void a(String str) {
        com.jingdong.manto.c.a().getSharedPreferences("manto_push_" + str, 4).edit().clear().commit();
    }

    public static void a(boolean z, String str, List<String> list) {
        SharedPreferences sharedPreferences = com.jingdong.manto.c.a().getSharedPreferences("manto_push_" + str, 4);
        String str2 = z ? "agree" : "disAgree";
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sharedPreferences.edit().putString("not_ask_" + it.next(), str2).commit();
        }
    }

    public static boolean b(String str) {
        return TextUtils.equals(str, "agree");
    }

    public static boolean c(String str) {
        return TextUtils.equals(str, "agree") || TextUtils.equals(str, "disAgree");
    }

    public static boolean d(String str) {
        return com.jingdong.manto.c.a().getSharedPreferences("manto_push_user_operate", 4).getBoolean(str, false);
    }

    public static void e(String str) {
        com.jingdong.manto.c.a().getSharedPreferences("manto_push_user_operate", 4).edit().putBoolean(str, true).commit();
    }
}
