package com.jd.dynamic.lib.dynamic.parser;

import android.text.TextUtils;
import androidx.annotation.Keep;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.Template;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.o;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Keep
/* loaded from: classes13.dex */
public class NewDynamicXParser {
    private static int RES_HEADER_LENGTH = 8;
    private static final String TAG = "DynamicXmlParser";
    private static final Map<Integer, String> sConfigAttrs;
    private static final Map<Integer, String> sDefaultAttrs;
    private static Object sLock = new Object();

    static {
        String[] strArr = {ViewProps.FLEX_DIRECTION, ViewProps.FLEX_WRAP, ViewProps.JUSTIFY_CONTENT, ViewProps.ALIGN_ITEMS, ViewProps.ALIGN_CONTENT, "marginLeft", "marginRight", "marginTop", "marginBottom", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "width", "height", ViewProps.FLEX_GROW, ViewProps.FLEX_SHRINK, ViewProps.ALIGN_SELF, "flexBasisPercent", ViewProps.MIN_WIDTH, "maxWidth", ViewProps.MIN_HEIGHT, "maxHeight", "layoutId", "alpha", "visibility", DYConstants.DY_BG_COLOR, "borderWidth", "borderColor", "borderRadius", DYConstants.DY_TEXT_SIZE, DYConstants.DY_TEXT_COLOR, "text", DYConstants.DY_TEXT_MAXLINES, DYConstants.DY_TEXT_ELLIPSIZE, DYConstants.DY_TEXT_STYLE, DYConstants.DY_GRAVITY, "scaleType", "src", "row", DYConstants.DY_ROW_REVERSE, "column", DYConstants.DY_COLUMN_REVERSE, "nowrap", "wrap", "wrap_reverse", DYConstants.DY_FLEX_START, DYConstants.DY_FLEX_END, DYConstants.DY_CENTER, "space_between", "space_around", DYConstants.DY_BASE_LINE, DYConstants.DY_STRETCH, DYConstants.DY_MATCH_PARENT, DYConstants.DY_WRAP_CONTENT, "auto", "none", "start", DYConstants.DY_MIDDLE, "end", "bold", "normal", "left", "right", DYConstants.DY_FIT, DYConstants.DY_FILL};
        sDefaultAttrs = new HashMap();
        for (int i2 = 0; i2 < 66; i2++) {
            sDefaultAttrs.put(new Integer(i2 + 0), strArr[i2]);
        }
        sConfigAttrs = new HashMap();
        try {
            System.loadLibrary("dynamic_math");
        } catch (Throwable th) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "load libdynamic_math.so failed!!!", (String) null, (String) null, (int) R2.attr.loadMax, new RuntimeException(th));
            t.e(TAG, "load libdynamic_math.so failed!!!", t.d(th));
        }
    }

    private NewDynamicXParser() {
    }

    private static String getValue(int i2) {
        synchronized (sLock) {
            Map<Integer, String> map = sDefaultAttrs;
            if (map.containsKey(Integer.valueOf(i2))) {
                return map.get(Integer.valueOf(i2));
            }
            Map<Integer, String> map2 = sConfigAttrs;
            if (map2.containsKey(Integer.valueOf(i2))) {
                return map2.get(Integer.valueOf(i2));
            }
            return null;
        }
    }

    private static boolean internalParseCustomRes(f fVar, int i2) {
        synchronized (sLock) {
            for (int i3 = 0; i3 < i2; i3++) {
                int f2 = fVar.f();
                sConfigAttrs.put(Integer.valueOf(f2), fVar.d(fVar.e()));
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:164:0x00ac A[Catch: Exception -> 0x00f4, TryCatch #1 {Exception -> 0x00f4, blocks: (B:133:0x000d, B:136:0x002f, B:138:0x0045, B:140:0x004b, B:142:0x0051, B:144:0x0056, B:146:0x005c, B:147:0x0060, B:148:0x0063, B:149:0x0066, B:152:0x0074, B:155:0x007a, B:162:0x009b, B:164:0x00ac, B:165:0x00b1, B:167:0x00bc, B:169:0x00d8, B:170:0x00ed, B:171:0x00f0, B:161:0x0096, B:160:0x0093, B:159:0x008f, B:154:0x0078, B:157:0x0088), top: B:181:0x000d, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:167:0x00bc A[Catch: Exception -> 0x00f4, TryCatch #1 {Exception -> 0x00f4, blocks: (B:133:0x000d, B:136:0x002f, B:138:0x0045, B:140:0x004b, B:142:0x0051, B:144:0x0056, B:146:0x005c, B:147:0x0060, B:148:0x0063, B:149:0x0066, B:152:0x0074, B:155:0x007a, B:162:0x009b, B:164:0x00ac, B:165:0x00b1, B:167:0x00bc, B:169:0x00d8, B:170:0x00ed, B:171:0x00f0, B:161:0x0096, B:160:0x0093, B:159:0x008f, B:154:0x0078, B:157:0x0088), top: B:181:0x000d, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x00f0 A[EDGE_INSN: B:188:0x00f0->B:171:0x00f0 BREAK  A[LOOP:1: B:166:0x00ba->B:170:0x00ed], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ViewNode internalParseViewNode(f fVar, ViewNode viewNode, ViewNode viewNode2, String str, String str2, AtomicInteger atomicInteger, List<Integer> list) {
        int f2;
        HashMap<String, String> hashMap;
        HashMap<String, String> hashMap2;
        String str3;
        int b;
        int i2;
        ViewNode viewNode3 = new ViewNode();
        ViewNode viewNode4 = viewNode == null ? viewNode3 : viewNode;
        try {
            String value = getValue(fVar.f());
            int f3 = fVar.f();
            f2 = fVar.f();
            viewNode3.setViewName(value);
            hashMap = new HashMap<>();
            hashMap2 = new HashMap<>();
            boolean z = false;
            for (int i3 = 0; i3 < f3; i3++) {
                String value2 = getValue(fVar.f());
                String value3 = getValue(fVar.f());
                if (!TextUtils.isEmpty(value2) && !TextUtils.isEmpty(value3)) {
                    if (DynamicUtils.isElOrKnownSymbol(value3)) {
                        hashMap2.put(value2, value3);
                        if (!z) {
                            z = viewNode3.setVisibilityExp(value2, value3, true);
                        }
                    }
                    if (!z) {
                        z = viewNode3.setVisibilityExp(value2, value3, false);
                    }
                    hashMap.put(value2, value3);
                }
            }
            if (DYConstants.DY_ITEMS.equals(viewNode3.getViewName()) || (viewNode2 != null && viewNode2.unNeedInitBind)) {
                viewNode3.unNeedInitBind = true;
            }
            str3 = hashMap.get("layoutId");
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "internalParseViewNode catch exception", str, str2, (int) R2.attr.liteMode, e2);
            if (viewNode4 != null) {
                viewNode4.parseSuccess = false;
            }
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(str3)) {
            try {
                viewNode3.viewId = Integer.parseInt(str3);
            } catch (Exception unused) {
                b = h.b(atomicInteger, list);
            }
            list.add(Integer.valueOf(viewNode3.viewId));
            if (m.J(hashMap2)) {
                viewNode4.unBindMaps.put(viewNode3, hashMap2);
            }
            viewNode3.setAttributes(hashMap);
            ArrayList arrayList = new ArrayList();
            i2 = 0;
            while (true) {
                if (i2 < f2) {
                    break;
                }
                ViewNode internalParseViewNode = internalParseViewNode(fVar, viewNode4, viewNode3, str, str2, atomicInteger, list);
                arrayList.add(internalParseViewNode);
                if (TextUtils.isEmpty(internalParseViewNode.getViewName())) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "NewDynamicXParser internalParseViewNode parse failed node name is null", str, str2, (int) R2.attr.logo, new Exception());
                    viewNode4.parseSuccess = false;
                    break;
                }
                i2++;
            }
            viewNode3.setChilds(arrayList);
            return viewNode3;
        }
        b = h.b(atomicInteger, list);
        viewNode3.viewId = b;
        list.add(Integer.valueOf(viewNode3.viewId));
        if (m.J(hashMap2)) {
        }
        viewNode3.setAttributes(hashMap);
        ArrayList arrayList2 = new ArrayList();
        i2 = 0;
        while (true) {
            if (i2 < f2) {
            }
            i2++;
        }
        viewNode3.setChilds(arrayList2);
        return viewNode3;
    }

    public static ResultEntity parseBinaryToResultEntity(String str, String str2, String str3, String str4) {
        return parseBinaryToResultEntity(str, false, str2, str3, str4, true);
    }

    public static ResultEntity parseBinaryToResultEntity(String str, String str2, String str3, String str4, boolean z) {
        return parseBinaryToResultEntity(str, false, str2, str3, str4, z);
    }

    public static ResultEntity parseBinaryToResultEntity(String str, boolean z, String str2, String str3, String str4) {
        return parseBinaryToResultEntity(str, z, str2, str3, str4, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:320:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ResultEntity parseBinaryToResultEntity(String str, boolean z, String str2, String str3, String str4, boolean z2) {
        Exception exc;
        File file;
        File file2;
        long nanoTime;
        File file3;
        List<File> c2;
        File file4;
        if (com.jd.dynamic.b.a.b.o().O()) {
            g f2 = g.f();
            f2.g(z2);
            return f2.k(str, z, str2, str3, str4);
        }
        FileInputStream fileInputStream = null;
        if (TextUtils.isEmpty(str)) {
            if (z2) {
                reportStartLoad(str2, str3, str4);
            }
            return null;
        }
        String r = !z ? com.jd.dynamic.b.e.a.b.r(str2, str3) : str;
        ResultEntity v = com.jd.dynamic.b.e.a.b.v(r);
        if (v != null) {
            v.isAssets = z;
            ViewNode viewNode = v.viewNode;
            if (viewNode != null && !TextUtils.isEmpty(viewNode.getViewName())) {
                if (z2) {
                    reportStartLoad(str2, str3, str4);
                }
                t.e(TAG, "parseBinaryToViewNode  hit viewnode cache return !!!  cachekey::" + r);
                if (!TextUtils.isEmpty(v.jsString)) {
                    DynamicMtaUtil.updateTemplateType2JS(str4);
                } else if (!TextUtils.isEmpty(v.zipDir)) {
                    DynamicMtaUtil.updateTemplateType2Zip(str4);
                }
                return v;
            }
        }
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.isAssets = z;
        File file5 = new File(str);
        if (!z && file5.exists() && file5.isFile()) {
            resultEntity.viewNode = parseBinaryToViewNode(str, false, str2, str3, str4, z2);
            return resultEntity;
        }
        if (z) {
            try {
                if (!str.endsWith(".zip")) {
                    resultEntity.viewNode = parseBinaryToViewNode(str, z, str2, str3, str4, z2);
                    return resultEntity;
                }
                String q = com.jd.dynamic.b.e.a.b.q(str.replace(".zip", ""));
                file2 = new File(q);
                try {
                    resultEntity.zipDir = q;
                    if (!file2.exists() || !file2.isDirectory()) {
                        try {
                            nanoTime = System.nanoTime();
                            file3 = new File(q + ".zip");
                            if (!file3.exists()) {
                                o.j(file3);
                            }
                            o.e(DynamicSdk.getEngine().getContext().getAssets().open(str), file3);
                            c2 = o.c(file3.getPath(), q);
                            file4 = new File(q);
                        } catch (Exception e2) {
                            e = e2;
                        }
                        try {
                            file3.delete();
                            long nanoTime2 = System.nanoTime();
                            Template a = com.jd.dynamic.b.e.a.b.a(str3, str2);
                            if (a != null) {
                                DynamicMtaUtil.uploadUnZipTempMta(str3, a, nanoTime2 - nanoTime, c2 != null, true);
                            }
                            file2 = file4;
                        } catch (Exception e3) {
                            e = e3;
                            file2 = file4;
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, "unzip assets source catch Error", str2, str3, e);
                            if (file2.exists()) {
                            }
                            if (z2) {
                            }
                            return resultEntity;
                        }
                    }
                } catch (Exception e4) {
                    exc = e4;
                    file = file2;
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToResultEntity catch Error", str2, str3, (int) R2.attr.liteMode, exc);
                    file2 = file;
                    if (file2.exists()) {
                    }
                    if (z2) {
                    }
                    return resultEntity;
                }
            } catch (Exception e5) {
                exc = e5;
                file = null;
            }
        } else {
            file2 = new File(str);
            resultEntity.zipDir = str;
        }
        if (file2.exists() || !file2.isDirectory()) {
            if (z2) {
                reportStartLoad(str2, str3, str4);
            }
            return resultEntity;
        }
        File[] listFiles = file2.listFiles();
        if (listFiles != null) {
            try {
                for (File file6 : listFiles) {
                    if (file6 != null) {
                        if (o.l(file6)) {
                            if (file6.length() < 0) {
                                if (z2) {
                                    reportStartLoad(str2, str3, str4);
                                }
                                return resultEntity;
                            }
                            fileInputStream = new FileInputStream(file6);
                        } else if (o.n(file6)) {
                            resultEntity.jsString = o.o(file6);
                        }
                    }
                }
            } catch (Exception e6) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToResultEntity2 catch Error", str2, str3, (int) R2.attr.liteMode, e6);
            }
        }
        if (fileInputStream != null) {
            resultEntity.viewNode = parseBinaryToViewNode(fileInputStream, str2, str3, str4, z2);
        }
        if (!TextUtils.isEmpty(resultEntity.jsString)) {
            DynamicMtaUtil.updateTemplateType2JS(str4);
        } else if (!TextUtils.isEmpty(resultEntity.zipDir)) {
            DynamicMtaUtil.updateTemplateType2Zip(str4);
        }
        return resultEntity;
    }

    public static ViewNode parseBinaryToViewNode(f fVar, String str, String str2) {
        new ViewNode();
        int f2 = fVar.f();
        int f3 = fVar.f();
        if (f2 < RES_HEADER_LENGTH) {
            return DynamicXParser.EMPTY_VIEW_NODE;
        }
        if (f3 > 0) {
            fVar.c(f2);
            internalParseCustomRes(fVar, f3);
        }
        fVar.c(RES_HEADER_LENGTH);
        ViewNode internalParseViewNode = internalParseViewNode(fVar, null, null, str, str2, new AtomicInteger(900000), new ArrayList());
        if (internalParseViewNode != null && !internalParseViewNode.parseSuccess) {
            internalParseViewNode = DynamicXParser.EMPTY_VIEW_NODE;
        }
        fVar.a();
        return internalParseViewNode;
    }

    public static ViewNode parseBinaryToViewNode(InputStream inputStream, String str, String str2, String str3) {
        return parseBinaryToViewNode(inputStream, str, str2, str3, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v4 */
    public static ViewNode parseBinaryToViewNode(InputStream e2, String str, String str2, String str3, boolean z) {
        if (com.jd.dynamic.b.a.b.o().O()) {
            g f2 = g.f();
            f2.g(z);
            return f2.d(e2, str, str2, str3);
        }
        ViewNode viewNode = new ViewNode();
        if (e2 == 0) {
            if (z) {
                reportStartLoad(str, str2, str3);
            }
            return viewNode;
        }
        f fVar = new f();
        boolean z2 = false;
        try {
        } catch (IOException e3) {
            e2 = e3;
            e2.printStackTrace();
        }
        try {
            try {
                int available = e2.available();
                byte[] bArr = new byte[available];
                e2.read(bArr, 0, available);
                fVar.b(bArr);
            } catch (IOException e4) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToViewNode catch exception", str, str2, (int) R2.attr.liteMode, e4);
                z2 = true;
                if (e2 != 0) {
                    e2.close();
                }
            }
            if (e2 != 0) {
                e2.close();
            }
            if (z) {
                reportStartLoad(str, str2, str3);
            }
            DynamicMtaUtil.appendGetTemplateEnd(str3);
            if (z2) {
                return viewNode;
            }
            MtaTimePair mtaTimePair = new MtaTimePair();
            mtaTimePair.startRecord();
            ViewNode parseBinaryToViewNode = parseBinaryToViewNode(fVar, str, str2);
            mtaTimePair.endRecord();
            DynamicMtaUtil.appendCreateModelMtaStat(str3, mtaTimePair);
            return parseBinaryToViewNode;
        } catch (Throwable th) {
            if (e2 != 0) {
                try {
                    e2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static ViewNode parseBinaryToViewNode(String str, boolean z, String str2, String str3, String str4) {
        return parseBinaryToViewNode(str, z, str2, str3, str4, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:216:0x00c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0104 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:225:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ViewNode parseBinaryToViewNode(String str, boolean z, String str2, String str3, String str4, boolean z2) {
        Throwable th;
        String str5;
        ViewNode viewNode;
        if (com.jd.dynamic.b.a.b.o().O()) {
            g f2 = g.f();
            f2.g(z2);
            return f2.e(str, z, str2, str3, str4);
        }
        ViewNode viewNode2 = new ViewNode();
        if (TextUtils.isEmpty(str)) {
            if (z2) {
                reportStartLoad(str2, str3, str4);
            }
            return viewNode2;
        }
        String r = !z ? com.jd.dynamic.b.e.a.b.r(str3, str2) : str;
        ResultEntity v = com.jd.dynamic.b.e.a.b.v(r);
        if (v != null && (viewNode = v.viewNode) != null && !TextUtils.isEmpty(viewNode.getViewName())) {
            if (z2) {
                reportStartLoad(str2, str3, str4);
            }
            t.e(TAG, "parseBinaryToViewNode  hit viewnode cache return !!!  cachekey::" + r);
            return viewNode;
        }
        FileInputStream fileInputStream = null;
        try {
            if (z) {
                fileInputStream = DynamicSdk.getEngine().getContext().getAssets().open(str);
            } else {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    if (file.length() < 0) {
                        if (z2) {
                            reportStartLoad(str2, str3, str4);
                        }
                        return viewNode2;
                    }
                    str5 = str4;
                    fileInputStream = new FileInputStream(str);
                    ViewNode parseBinaryToViewNode = parseBinaryToViewNode(fileInputStream, str2, str3, str5, z2);
                    sConfigAttrs.clear();
                    if (fileInputStream == null) {
                        try {
                            fileInputStream.close();
                            return parseBinaryToViewNode;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return parseBinaryToViewNode;
                        }
                    }
                    return parseBinaryToViewNode;
                }
            }
            str5 = str4;
            ViewNode parseBinaryToViewNode2 = parseBinaryToViewNode(fileInputStream, str2, str3, str5, z2);
            sConfigAttrs.clear();
            if (fileInputStream == null) {
            }
        } catch (Exception e3) {
            try {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToViewNode catch exception", str2, str3, (int) R2.attr.liteMode, e3);
                t.e("parseBinaryToViewNode", t.d(e3));
                if (0 != 0) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return viewNode2;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = null;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (fileInputStream != null) {
            }
            throw th;
        }
    }

    private static void reportStartLoad(String str, String str2, String str3) {
        DynamicMtaUtil.startLoadTemplate(str2, str, str3);
    }
}
