package com.jingdong.aura.a.b;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public class b {
    private static final String[] a = {"/system/framework/framework-qrom-res"};
    private static final String[] b = {"multiui", "com.blinq.theme.theroad", "com.blinq.theme.vibrance", "com.vivek.template", "digital.black.gold.cm", "io.flowrome.hipsta.theme", "com.minimal.theme.cm12", "oldestblackstone", "se.jstark.themes.cubot", "theme000-maint-", "SemcGenericUxpRes", "OperatorVerizonTheme", "com.sonymobile.themes.id012", "/system/priv-app/xuiskin"};

    /* renamed from: c  reason: collision with root package name */
    private static Set<String> f12088c = new HashSet();
    private static Set<String> d = new HashSet();

    /* renamed from: e  reason: collision with root package name */
    private static Set<String> f12089e = new HashSet();

    private static Set<String> a() {
        int i2 = 0;
        if (c.C()) {
            String[] strArr = a;
            int length = strArr.length;
            while (i2 < length) {
                f12088c.add(strArr[i2].toLowerCase());
                i2++;
            }
        } else {
            String[] strArr2 = b;
            int length2 = strArr2.length;
            while (i2 < length2) {
                f12088c.add(strArr2[i2].toLowerCase());
                i2++;
            }
        }
        Set<String> set = d;
        if (set != null) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                f12088c.add(it.next().toLowerCase());
            }
        }
        Set<String> set2 = f12089e;
        if (set2 != null) {
            Iterator<String> it2 = set2.iterator();
            while (it2.hasNext()) {
                f12088c.remove(it2.next().toLowerCase());
            }
        }
        return f12088c;
    }

    public static boolean a(String str) {
        Set<String> a2;
        if (str == null || (a2 = a()) == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        for (String str2 : a2) {
            if (str2 != null && lowerCase.indexOf(str2) != -1) {
                return true;
            }
        }
        return false;
    }

    public static void a(Set<String> set, Set<String> set2) {
        d = set;
        f12089e = set2;
    }
}
