package com.jingdong.aura.a.c;

import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.aura.a.a.a;
import com.jingdong.common.utils.LangUtils;
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
    */
    public static j b(File file) {
        XmlResourceParser xmlResourceParser;
        XmlResourceParser xmlResourceParser2;
        String str;
        AssetManager assetManager;
        int intValue;
        XmlResourceParser xmlResourceParser3 = null;
        try {
            try {
                assetManager = (AssetManager) AssetManager.class.newInstance();
                intValue = ((Integer) AssetManager.class.getMethod("addAssetPath", String.class).invoke(assetManager, file.getAbsolutePath())).intValue();
                xmlResourceParser = assetManager.openXmlResourceParser(intValue, "AndroidManifest.xml");
            } catch (Exception e2) {
                e = e2;
                xmlResourceParser = null;
            } catch (Throwable th) {
                th = th;
                if (xmlResourceParser3 != null) {
                }
                throw th;
            }
            try {
                com.jingdong.aura.core.shadow.b.a().a(assetManager, intValue, file.getAbsolutePath());
                if (xmlResourceParser != null) {
                    j a = a(xmlResourceParser);
                    if (a == null) {
                        a = new j();
                    }
                    xmlResourceParser.close();
                    if (xmlResourceParser != null) {
                        xmlResourceParser.close();
                    }
                    return a;
                }
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                if (file != null) {
                    str = "parse failed. " + file.getAbsolutePath() + LangUtils.SINGLE_SPACE + com.jingdong.aura.core.util.d.b(file.getAbsolutePath());
                } else {
                    str = "parse failed. " + DYConstants.DY_NULL_STR;
                }
                com.jingdong.aura.a.b.e.a("PackageLite", str, "PackageLite.parse", e);
            }
        } catch (Throwable th2) {
            th = th2;
            xmlResourceParser3 = xmlResourceParser2;
            if (xmlResourceParser3 != null) {
                xmlResourceParser3.close();
            }
            throw th;
        }
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
    */
    private static void b(j jVar, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        String str = null;
        String str2 = null;
        int i2 = 0;
        for (int i3 = 0; i3 < attributeSet.getAttributeCount(); i3++) {
            String attributeName = attributeSet.getAttributeName(i3);
            if (attributeName.equals("name")) {
                str2 = attributeSet.getAttributeValue(i3);
            } else {
                if (attributeName.equals("value")) {
                    str = attributeSet.getAttributeValue(i3);
                }
                if (i2 < 2) {
                    break;
                }
            }
            i2++;
            if (i2 < 2) {
            }
        }
        if (str2 == null || str == null) {
            return;
        }
        if (!"auraFragment".equals(str2) && !"manualComponents".equals(str2)) {
            jVar.f12139h.putString(str2, str);
            return;
        }
        for (String str3 : str.split(DYConstants.DY_REGEX_COMMA)) {
            jVar.f12137f.add(str3.trim());
        }
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
