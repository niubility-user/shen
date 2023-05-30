package com.jingdong.aura.a.c;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.aura.a.a.a;
import com.tencent.open.SocialConstants;
import com.unionpay.tsmservice.mi.data.Constant;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class j {
    static com.jingdong.aura.core.util.l.b p = com.jingdong.aura.core.util.l.c.a("PackageLite");
    public String a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public int f12135c;
    public int d;

    /* renamed from: i  reason: collision with root package name */
    public String f12140i;

    /* renamed from: j  reason: collision with root package name */
    public int f12141j;

    /* renamed from: k  reason: collision with root package name */
    public String f12142k;

    /* renamed from: e  reason: collision with root package name */
    public final Set<String> f12136e = new HashSet();

    /* renamed from: g  reason: collision with root package name */
    public final Set<String> f12138g = new HashSet();

    /* renamed from: f  reason: collision with root package name */
    public final Set<String> f12137f = new HashSet();

    /* renamed from: h  reason: collision with root package name */
    public Bundle f12139h = new Bundle();

    /* renamed from: l  reason: collision with root package name */
    public final Set<String> f12143l = new HashSet();

    /* renamed from: m  reason: collision with root package name */
    public final Set<String> f12144m = new HashSet();

    /* renamed from: n  reason: collision with root package name */
    public final Set<String> f12145n = new HashSet();
    public final Set<String> o = new HashSet();

    j() {
    }

    protected static j a(XmlResourceParser xmlResourceParser) {
        int next = xmlResourceParser.next();
        j jVar = new j();
        while (next != 1) {
            if (next == 1) {
                xmlResourceParser.close();
            } else if (next == 2) {
                if (xmlResourceParser.getName().equals("manifest")) {
                    a(xmlResourceParser, jVar);
                }
                if (xmlResourceParser.getName().equals("application")) {
                    if (a(jVar, xmlResourceParser, xmlResourceParser)) {
                        return jVar;
                    }
                    return null;
                }
            } else {
                continue;
            }
            next = xmlResourceParser.next();
        }
        return jVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0052, code lost:
        if (r3 != null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009e, code lost:
        if (r3 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a0, code lost:
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a3, code lost:
        return null;
     */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x00a5: MOVE (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:33:0x00a5 */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.aura.a.c.j b(java.io.File r8) {
        /*
            r0 = 0
            java.lang.Class<android.content.res.AssetManager> r1 = android.content.res.AssetManager.class
            java.lang.Object r1 = r1.newInstance()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            android.content.res.AssetManager r1 = (android.content.res.AssetManager) r1     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Class<android.content.res.AssetManager> r2 = android.content.res.AssetManager.class
            java.lang.String r3 = "addAssetPath"
            r4 = 1
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r7 = 0
            r5[r7] = r6     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.reflect.Method r2 = r2.getMethod(r3, r5)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r4 = r8.getAbsolutePath()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r3[r7] = r4     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Object r2 = r2.invoke(r1, r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            int r2 = r2.intValue()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r3 = "AndroidManifest.xml"
            android.content.res.XmlResourceParser r3 = r1.openXmlResourceParser(r2, r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.aura.core.shadow.b r4 = com.jingdong.aura.core.shadow.b.a()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> La4
            java.lang.String r5 = r8.getAbsolutePath()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> La4
            r4.a(r1, r2, r5)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> La4
            if (r3 == 0) goto L52
            com.jingdong.aura.a.c.j r1 = a(r3)     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> La4
            if (r1 != 0) goto L49
            com.jingdong.aura.a.c.j r1 = new com.jingdong.aura.a.c.j     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> La4
            r1.<init>()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> La4
        L49:
            r3.close()     // Catch: java.lang.Exception -> L55 java.lang.Throwable -> La4
            if (r3 == 0) goto L51
            r3.close()
        L51:
            return r1
        L52:
            if (r3 == 0) goto La3
            goto La0
        L55:
            r1 = move-exception
            goto L5b
        L57:
            r8 = move-exception
            goto La6
        L59:
            r1 = move-exception
            r3 = r0
        L5b:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> La4
            java.lang.String r2 = "parse failed. "
            if (r8 == 0) goto L86
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La4
            r4.<init>()     // Catch: java.lang.Throwable -> La4
            r4.append(r2)     // Catch: java.lang.Throwable -> La4
            java.lang.String r2 = r8.getAbsolutePath()     // Catch: java.lang.Throwable -> La4
            r4.append(r2)     // Catch: java.lang.Throwable -> La4
            java.lang.String r2 = " "
            r4.append(r2)     // Catch: java.lang.Throwable -> La4
            java.lang.String r8 = r8.getAbsolutePath()     // Catch: java.lang.Throwable -> La4
            java.lang.String r8 = com.jingdong.aura.core.util.d.b(r8)     // Catch: java.lang.Throwable -> La4
            r4.append(r8)     // Catch: java.lang.Throwable -> La4
            java.lang.String r8 = r4.toString()     // Catch: java.lang.Throwable -> La4
            goto L97
        L86:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La4
            r8.<init>()     // Catch: java.lang.Throwable -> La4
            r8.append(r2)     // Catch: java.lang.Throwable -> La4
            java.lang.String r2 = "null"
            r8.append(r2)     // Catch: java.lang.Throwable -> La4
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> La4
        L97:
            java.lang.String r2 = "PackageLite"
            java.lang.String r4 = "PackageLite.parse"
            com.jingdong.aura.a.b.e.a(r2, r8, r4, r1)     // Catch: java.lang.Throwable -> La4
            if (r3 == 0) goto La3
        La0:
            r3.close()
        La3:
            return r0
        La4:
            r8 = move-exception
            r0 = r3
        La6:
            if (r0 == 0) goto Lab
            r0.close()
        Lab:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.c.j.b(java.io.File):com.jingdong.aura.a.c.j");
    }

    private static void a(XmlResourceParser xmlResourceParser, j jVar) {
        for (int i2 = 0; i2 < xmlResourceParser.getAttributeCount(); i2++) {
            String attributeName = xmlResourceParser.getAttributeName(i2);
            if (attributeName.equalsIgnoreCase("package")) {
                jVar.f12140i = xmlResourceParser.getAttributeValue(i2);
            }
            if (attributeName.equals("versionCode")) {
                jVar.f12141j = xmlResourceParser.getAttributeIntValue(i2, 0);
            } else if (attributeName.equals("versionName")) {
                jVar.f12142k = xmlResourceParser.getAttributeValue(i2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030 A[LOOP:0: B:3:0x0005->B:15:0x0030, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0033 A[EDGE_INSN: B:29:0x0033->B:16:0x0033 BREAK  A[LOOP:0: B:3:0x0005->B:15:0x0030], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void b(com.jingdong.aura.a.c.j r6, org.xmlpull.v1.XmlPullParser r7, android.util.AttributeSet r8) {
        /*
            r7 = 0
            r0 = 0
            r1 = r7
            r2 = 0
            r3 = 0
        L5:
            int r4 = r8.getAttributeCount()
            if (r2 >= r4) goto L33
            java.lang.String r4 = r8.getAttributeName(r2)
            java.lang.String r5 = "name"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L1e
            java.lang.String r1 = r8.getAttributeValue(r2)
        L1b:
            int r3 = r3 + 1
            goto L2c
        L1e:
            java.lang.String r5 = "value"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L2c
            java.lang.String r7 = r8.getAttributeValue(r2)
            goto L1b
        L2c:
            r4 = 2
            if (r3 < r4) goto L30
            goto L33
        L30:
            int r2 = r2 + 1
            goto L5
        L33:
            if (r1 == 0) goto L65
            if (r7 == 0) goto L65
            java.lang.String r8 = "auraFragment"
            boolean r8 = r8.equals(r1)
            if (r8 != 0) goto L4e
            java.lang.String r8 = "manualComponents"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L48
            goto L4e
        L48:
            android.os.Bundle r6 = r6.f12139h
            r6.putString(r1, r7)
            goto L65
        L4e:
            java.lang.String r8 = ","
            java.lang.String[] r7 = r7.split(r8)
            int r8 = r7.length
        L55:
            if (r0 >= r8) goto L65
            r1 = r7[r0]
            java.util.Set<java.lang.String> r2 = r6.f12137f
            java.lang.String r1 = r1.trim()
            r2.add(r1)
            int r0 = r0 + 1
            goto L55
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.aura.a.c.j.b(com.jingdong.aura.a.c.j, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet):void");
    }

    private static boolean a(j jVar, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        String str = jVar.f12140i;
        for (int i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
            String attributeName = attributeSet.getAttributeName(i2);
            if (attributeName.equals("name")) {
                jVar.a = a(str, (CharSequence) attributeSet.getAttributeValue(i2));
            } else if (attributeName.equals("icon")) {
                jVar.f12135c = attributeSet.getAttributeResourceValue(i2, 0);
            } else if (attributeName.equals(Constant.KEY_PROMOTION_LABEL)) {
                jVar.d = attributeSet.getAttributeResourceValue(i2, 0);
            } else if (attributeName.equals("description")) {
                jVar.b = attributeSet.getAttributeResourceValue(i2, 0);
            }
        }
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1 || (next == 3 && xmlPullParser.getDepth() <= depth)) {
                break;
            } else if (next != 3 && next != 4) {
                String name = xmlPullParser.getName();
                if (name.equals("activity")) {
                    a(jVar, xmlPullParser, attributeSet, false, name);
                } else if (name.equals(SocialConstants.PARAM_RECEIVER)) {
                    a(jVar, xmlPullParser, attributeSet, true, name);
                } else if (name.equals("service")) {
                    a(jVar, xmlPullParser, attributeSet, true, name);
                } else if (name.equals("provider")) {
                    a(jVar, xmlPullParser, attributeSet, false, name);
                } else if (!name.equals("activity-alias")) {
                    if (xmlPullParser.getName().equals("meta-data")) {
                        b(jVar, xmlPullParser, attributeSet);
                    } else if (!name.equals("uses-library")) {
                        name.equals("uses-package");
                    }
                }
            }
        }
        return true;
    }

    private static String a(String str, CharSequence charSequence) {
        if (charSequence != null && charSequence.length() > 0) {
            String charSequence2 = charSequence.toString();
            char charAt = charSequence2.charAt(0);
            if (charAt == '.') {
                return (str + charSequence2).intern();
            } else if (charSequence2.indexOf(46) < 0) {
                return (str + OrderISVUtil.MONEY_DECIMAL_CHAR + charSequence2).intern();
            } else if (charAt >= 'a' && charAt <= 'z') {
                return charSequence2.intern();
            } else {
                System.out.println("Bad class name " + charSequence2 + " in package " + str);
                return null;
            }
        }
        System.out.println("Empty class name in package " + str);
        return null;
    }

    private static void a(j jVar, XmlPullParser xmlPullParser, AttributeSet attributeSet, boolean z, String str) {
        String str2 = jVar.f12140i;
        for (int i2 = 0; i2 < attributeSet.getAttributeCount(); i2++) {
            if (attributeSet.getAttributeName(i2).equals("name")) {
                String attributeValue = attributeSet.getAttributeValue(i2);
                if (attributeValue.startsWith(OrderISVUtil.MONEY_DECIMAL)) {
                    attributeValue = str2.concat(attributeValue);
                }
                a(jVar, str, attributeValue);
                jVar.f12136e.add(attributeValue);
                if (z) {
                    jVar.f12138g.add(attributeValue);
                }
            }
        }
    }

    private static void a(j jVar, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        str.hashCode();
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1655966961:
                if (str.equals("activity")) {
                    c2 = 0;
                    break;
                }
                break;
            case -987494927:
                if (str.equals("provider")) {
                    c2 = 1;
                    break;
                }
                break;
            case -808719889:
                if (str.equals(SocialConstants.PARAM_RECEIVER)) {
                    c2 = 2;
                    break;
                }
                break;
            case 1984153269:
                if (str.equals("service")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                jVar.f12143l.add(str2);
                return;
            case 1:
                jVar.f12144m.add(str2);
                return;
            case 2:
                jVar.f12145n.add(str2);
                return;
            case 3:
                jVar.o.add(str2);
                return;
            default:
                return;
        }
    }

    public static a.C0391a a(String str, String str2) {
        j b;
        if (str == null || str2 == null) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || (b = b(file)) == null) {
            return null;
        }
        a.C0391a c0391a = new a.C0391a();
        c0391a.f12073f = b.f12140i;
        c0391a.f12076i = b.f12142k;
        c0391a.f12077j = b.f12141j;
        c0391a.f12074g = a(file);
        c0391a.f12078k = b.a;
        c0391a.f12079l = str2;
        c0391a.f12075h = file.length();
        c0391a.f12081n = 2;
        c0391a.o = null;
        ArrayList arrayList = new ArrayList();
        c0391a.a = arrayList;
        arrayList.addAll(b.f12136e);
        ArrayList arrayList2 = new ArrayList();
        c0391a.b = arrayList2;
        arrayList2.addAll(b.f12137f);
        ArrayList arrayList3 = new ArrayList();
        c0391a.p = arrayList3;
        arrayList3.addAll(b.f12143l);
        ArrayList arrayList4 = new ArrayList();
        c0391a.q = arrayList4;
        arrayList4.addAll(b.o);
        ArrayList arrayList5 = new ArrayList();
        c0391a.r = arrayList5;
        arrayList5.addAll(b.f12144m);
        ArrayList arrayList6 = new ArrayList();
        c0391a.s = arrayList6;
        arrayList6.addAll(b.f12145n);
        c0391a.d = new ArrayList();
        p.a("bundleInfo =" + c0391a);
        Bundle bundle = b.f12139h;
        if (bundle != null && bundle.get("dependency") != null) {
            String obj = b.f12139h.get("dependency").toString();
            if (!TextUtils.isEmpty(obj)) {
                c0391a.d.addAll(Arrays.asList(obj.split(DYConstants.DY_REGEX_COMMA)));
            }
        }
        c0391a.f12072e = new ArrayList();
        Bundle bundle2 = b.f12139h;
        if (bundle2 != null && bundle2.get("auraDependentSo") != null) {
            String obj2 = b.f12139h.get("auraDependentSo").toString();
            if (!TextUtils.isEmpty(obj2)) {
                c0391a.f12072e.addAll(Arrays.asList(obj2.split(DYConstants.DY_REGEX_COMMA)));
            }
        }
        return c0391a;
    }

    public static boolean a(File file) {
        ZipFile zipFile = null;
        try {
            try {
                ZipFile zipFile2 = new ZipFile(file);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile2.entries();
                    while (entries.hasMoreElements()) {
                        String name = entries.nextElement().getName();
                        if (name != null && name.endsWith(".so")) {
                            com.jingdong.aura.core.util.d.a(zipFile2);
                            return true;
                        }
                    }
                    com.jingdong.aura.core.util.d.a(zipFile2);
                    return false;
                } catch (IOException e2) {
                    e = e2;
                    zipFile = zipFile2;
                    e.printStackTrace();
                    com.jingdong.aura.core.util.d.a(zipFile);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    zipFile = zipFile2;
                    com.jingdong.aura.core.util.d.a(zipFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }
}
