package com.jingdong.aura.core.shadow;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.aura.a.c.l;
import com.jingdong.aura.core.reflection.Hack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
    */
    public void a(AssetManager assetManager, int i2, String str) {
        XmlResourceParser xmlResourceParser;
        XmlResourceParser xmlResourceParser2;
        Object a2;
        Resources resources;
        XmlResourceParser xmlResourceParser3 = null;
        r0 = null;
        Object obj = null;
        try {
            a2 = Build.VERSION.SDK_INT <= 20 ? com.jingdong.aura.core.reflection.b.b0.a(str) : com.jingdong.aura.core.reflection.b.b0.a(new Object[0]);
            resources = new Resources(assetManager, l.d.getDisplayMetrics(), l.d.getConfiguration());
            xmlResourceParser2 = assetManager.openXmlResourceParser(i2, "AndroidManifest.xml");
        } catch (Exception e2) {
            e = e2;
            xmlResourceParser = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            obj = a(a2, resources, xmlResourceParser2);
        } catch (Exception e3) {
            xmlResourceParser = xmlResourceParser2;
            e = e3;
            try {
                e.printStackTrace();
                if (xmlResourceParser != null) {
                    xmlResourceParser2 = xmlResourceParser;
                    xmlResourceParser2.close();
                }
                if (obj == null) {
                    return;
                }
                com.jingdong.aura.core.reflection.b.X.a((Hack.d<Object, String>) obj, l.a.getPackageName());
                ApplicationInfo a3 = com.jingdong.aura.core.reflection.b.Z.a((Hack.d<Object, ApplicationInfo>) obj);
                a3.name = l.a.getApplicationInfo().name;
                a3.className = l.a.getApplicationInfo().className;
                a3.taskAffinity = l.a.getApplicationInfo().taskAffinity;
                a3.permission = l.a.getApplicationInfo().permission;
                a3.processName = l.a.getApplicationInfo().processName;
                a3.theme = l.a.getApplicationInfo().theme;
                a3.flags = l.a.getApplicationInfo().flags;
                a3.uiOptions = l.a.getApplicationInfo().uiOptions;
                a3.backupAgentName = l.a.getApplicationInfo().backupAgentName;
                a3.descriptionRes = l.a.getApplicationInfo().descriptionRes;
                a3.targetSdkVersion = l.a.getApplicationInfo().targetSdkVersion;
                a3.compatibleWidthLimitDp = l.a.getApplicationInfo().compatibleWidthLimitDp;
                a3.uid = l.a.getApplicationInfo().uid;
                a3.largestWidthLimitDp = l.a.getApplicationInfo().largestWidthLimitDp;
                a3.enabled = l.a.getApplicationInfo().enabled;
                a3.requiresSmallestWidthDp = l.a.getApplicationInfo().requiresSmallestWidthDp;
                a3.packageName = l.a.getApplicationInfo().packageName;
                ArrayList<Object> a4 = com.jingdong.aura.core.reflection.b.Y.a((Hack.d<Object, ArrayList<Object>>) obj);
                if (a4 != null) {
                    Iterator<Object> it = a4.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        try {
                            ActivityInfo activityInfo = (ActivityInfo) next.getClass().getField("info").get(next);
                            if (activityInfo.targetActivity != null || activityInfo.taskAffinity.equals(activityInfo.packageName)) {
                                activityInfo.taskAffinity = l.a.getApplicationInfo().taskAffinity;
                            }
                            String packageName = TextUtils.isEmpty(activityInfo.processName) ? l.a.getPackageName() : activityInfo.processName;
                            activityInfo.processName = packageName;
                            if (packageName.startsWith(activityInfo.packageName + ":")) {
                                activityInfo.processName = l.a.getApplicationInfo().packageName + activityInfo.processName.substring(activityInfo.packageName.length());
                            }
                            activityInfo.packageName = l.a.getApplicationInfo().packageName;
                            a((ComponentName) com.jingdong.aura.core.reflection.b.a0.a(next, new Object[0]), next);
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                xmlResourceParser3 = xmlResourceParser;
                if (xmlResourceParser3 != null) {
                    xmlResourceParser3.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            xmlResourceParser3 = xmlResourceParser2;
            th = th3;
            if (xmlResourceParser3 != null) {
            }
            throw th;
        }
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
