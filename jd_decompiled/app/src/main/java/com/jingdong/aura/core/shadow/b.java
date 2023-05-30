package com.jingdong.aura.core.shadow;

import android.content.ComponentName;
import android.content.pm.ComponentInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import com.jingdong.aura.a.c.l;
import com.jingdong.aura.core.reflection.Hack;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class b {
    private static com.jingdong.aura.core.util.l.b b = com.jingdong.aura.core.util.l.c.a("BundlePackageManager");
    private final HashMap<ComponentName, Object> a = new HashMap<>();

    /* loaded from: classes4.dex */
    private static class a {
        private static b a = new b();
    }

    public static b a() {
        return a.a;
    }

    public <T extends ComponentInfo> T a(ComponentName componentName, Class<T> cls) {
        Object obj = this.a.get(componentName);
        if (obj != null) {
            try {
                return (T) obj.getClass().getField("info").get(obj);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                return null;
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0037, code lost:
        if (r7 != null) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.res.AssetManager r7, int r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.core.shadow.b.a(android.content.res.AssetManager, int, java.lang.String):void");
    }

    private Object a(Object obj, Resources resources, XmlResourceParser xmlResourceParser) {
        String[] strArr = new String[1];
        try {
            int i2 = l.a.getApplicationInfo().targetSdkVersion;
            com.jingdong.aura.core.util.l.b bVar = b;
            StringBuilder sb = new StringBuilder();
            sb.append("target api:");
            sb.append(i2);
            sb.append(" sdk:");
            int i3 = Build.VERSION.SDK_INT;
            sb.append(i3);
            bVar.a(sb.toString());
            if (i2 > 28 && i3 > 28) {
                b.a("can't parsePackage");
                return null;
            } else if (i3 >= 26) {
                return com.jingdong.aura.core.reflection.b.S.a("parseBaseApk", String.class, Resources.class, XmlResourceParser.class, Integer.TYPE, String[].class).a(obj, l.a.getPackageName(), resources, xmlResourceParser, 0, strArr);
            } else {
                if (i3 > 20) {
                    return com.jingdong.aura.core.reflection.b.S.a("parseBaseApk", Resources.class, XmlResourceParser.class, Integer.TYPE, String[].class).a(obj, resources, xmlResourceParser, 0, strArr);
                }
                try {
                    return com.jingdong.aura.core.reflection.b.S.a("parsePackage", Resources.class, XmlResourceParser.class, Integer.TYPE, String[].class).a(obj, resources, xmlResourceParser, 0, strArr);
                } catch (Hack.HackDeclaration.HackAssertionException unused) {
                    return com.jingdong.aura.core.reflection.b.S.a("parsePackage", Resources.class, XmlResourceParser.class, Integer.TYPE, Boolean.TYPE, String[].class).a(obj, resources, xmlResourceParser, 0, Boolean.FALSE, strArr);
                }
            }
        } catch (Throwable th) {
            if ((l.a.getApplicationInfo().flags & 2) == 0) {
                return null;
            }
            throw new RuntimeException(th);
        }
    }

    private void a(ComponentName componentName, Object obj) {
        this.a.put(componentName, obj);
    }
}
