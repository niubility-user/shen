package com.jd.dynamic.lib.dynamic.parser;

import android.text.TextUtils;
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
import com.jd.dynamic.lib.dynamic.parser.e.a;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class g {

    /* renamed from: c  reason: collision with root package name */
    private static final ViewNode f2230c = new ViewNode();
    private static final Map<Integer, String> d;
    private final Map<Integer, String> a = new HashMap();
    private boolean b = true;

    static {
        String[] strArr = {ViewProps.FLEX_DIRECTION, ViewProps.FLEX_WRAP, ViewProps.JUSTIFY_CONTENT, ViewProps.ALIGN_ITEMS, ViewProps.ALIGN_CONTENT, "marginLeft", "marginRight", "marginTop", "marginBottom", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "width", "height", ViewProps.FLEX_GROW, ViewProps.FLEX_SHRINK, ViewProps.ALIGN_SELF, "flexBasisPercent", ViewProps.MIN_WIDTH, "maxWidth", ViewProps.MIN_HEIGHT, "maxHeight", "layoutId", "alpha", "visibility", DYConstants.DY_BG_COLOR, "borderWidth", "borderColor", "borderRadius", DYConstants.DY_TEXT_SIZE, DYConstants.DY_TEXT_COLOR, "text", DYConstants.DY_TEXT_MAXLINES, DYConstants.DY_TEXT_ELLIPSIZE, DYConstants.DY_TEXT_STYLE, DYConstants.DY_GRAVITY, "scaleType", "src", "row", DYConstants.DY_ROW_REVERSE, "column", DYConstants.DY_COLUMN_REVERSE, "nowrap", "wrap", "wrap_reverse", DYConstants.DY_FLEX_START, DYConstants.DY_FLEX_END, DYConstants.DY_CENTER, "space_between", "space_around", DYConstants.DY_BASE_LINE, DYConstants.DY_STRETCH, DYConstants.DY_MATCH_PARENT, DYConstants.DY_WRAP_CONTENT, "auto", "none", "start", DYConstants.DY_MIDDLE, "end", "bold", "normal", "left", "right", DYConstants.DY_FIT, DYConstants.DY_FILL};
        d = new HashMap();
        for (int i2 = 0; i2 < 66; i2++) {
            d.put(new Integer(i2), strArr[i2]);
        }
    }

    private g() {
        f2230c.parseSuccess = false;
    }

    private int a(f fVar, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int f2 = fVar.f();
            if (-1 == f2) {
                return 100;
            }
            short e2 = fVar.e();
            if (-1 == e2) {
                return 101;
            }
            String d2 = fVar.d(e2);
            if (d2 == null) {
                return 102;
            }
            this.a.put(Integer.valueOf(f2), d2);
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00be A[Catch: Exception -> 0x011a, c -> 0x0135, b -> 0x013b, a -> 0x013e, TryCatch #3 {a -> 0x013e, b -> 0x013b, c -> 0x0135, Exception -> 0x011a, blocks: (B:6:0x0015, B:8:0x0036, B:10:0x004c, B:12:0x0052, B:14:0x0058, B:20:0x006c, B:21:0x0071, B:16:0x0060, B:18:0x0065, B:22:0x0074, B:23:0x0077, B:26:0x0085, B:29:0x008c, B:36:0x00ad, B:38:0x00be, B:39:0x00ca, B:41:0x00d5, B:43:0x00f7, B:45:0x010f, B:46:0x0115, B:35:0x00a8, B:34:0x00a5, B:33:0x00a1, B:28:0x0089), top: B:61:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d5 A[Catch: Exception -> 0x011a, c -> 0x0135, b -> 0x013b, a -> 0x013e, TryCatch #3 {a -> 0x013e, b -> 0x013b, c -> 0x0135, Exception -> 0x011a, blocks: (B:6:0x0015, B:8:0x0036, B:10:0x004c, B:12:0x0052, B:14:0x0058, B:20:0x006c, B:21:0x0071, B:16:0x0060, B:18:0x0065, B:22:0x0074, B:23:0x0077, B:26:0x0085, B:29:0x008c, B:36:0x00ad, B:38:0x00be, B:39:0x00ca, B:41:0x00d5, B:43:0x00f7, B:45:0x010f, B:46:0x0115, B:35:0x00a8, B:34:0x00a5, B:33:0x00a1, B:28:0x0089), top: B:61:0x0015 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ViewNode b(f fVar, ViewNode viewNode, ViewNode viewNode2, String str, String str2, AtomicInteger atomicInteger, List<Integer> list) {
        int i2;
        int b;
        int i3;
        ViewNode viewNode3 = new ViewNode();
        ViewNode viewNode4 = viewNode == null ? viewNode3 : viewNode;
        try {
            String h2 = h(fVar.f());
            int f2 = fVar.f();
            int f3 = fVar.f();
            viewNode3.setViewName(h2);
            HashMap<String, String> hashMap = new HashMap<>();
            HashMap<String, String> hashMap2 = new HashMap<>();
            boolean z = false;
            for (int i4 = 0; i4 < f2; i4++) {
                String h3 = h(fVar.f());
                String h4 = h(fVar.f());
                if (!TextUtils.isEmpty(h3) && !TextUtils.isEmpty(h4)) {
                    if (DynamicUtils.isElOrKnownSymbol(h4) || TextUtils.equals(h3, DYConstants.DYN_KEEP_ATTRS)) {
                        hashMap2.put(h3, h4);
                        if (!z) {
                            z = viewNode3.setVisibilityExp(h3, h4, true);
                        }
                    }
                    if (!z) {
                        z = viewNode3.setVisibilityExp(h3, h4, false);
                    }
                    hashMap.put(h3, h4);
                }
            }
            if (DYConstants.DY_ITEMS.equals(viewNode3.getViewName()) || (viewNode2 != null && viewNode2.unNeedInitBind)) {
                viewNode3.unNeedInitBind = true;
            }
            String str3 = hashMap.get("layoutId");
            if (!TextUtils.isEmpty(str3)) {
                try {
                    viewNode3.viewId = Integer.parseInt(str3);
                } catch (Exception unused) {
                    b = h.b(atomicInteger, list);
                }
                list.add(Integer.valueOf(viewNode3.viewId));
                if (m.J(hashMap2)) {
                    viewNode4.elAttrMapping.put(viewNode3.viewId, hashMap2);
                    viewNode4.unBindMaps.put(viewNode3, hashMap2);
                }
                viewNode3.setAttributes(hashMap);
                ArrayList arrayList = new ArrayList();
                i3 = 0;
                while (i3 < f3) {
                    int i5 = i3;
                    ArrayList arrayList2 = arrayList;
                    ViewNode b2 = b(fVar, viewNode4, viewNode3, str, str2, atomicInteger, list);
                    arrayList2.add(b2);
                    if (TextUtils.isEmpty(b2.getViewName())) {
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "ObjectParser internalParseViewNode parse failed node name is null", str, str2, (int) R2.attr.loadprogress, new Exception());
                        viewNode4.parseSuccess = false;
                        return f2230c;
                    }
                    i3 = i5 + 1;
                    arrayList = arrayList2;
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
            ArrayList arrayList3 = new ArrayList();
            i3 = 0;
            while (i3 < f3) {
            }
            viewNode3.setChilds(arrayList3);
            return viewNode3;
        } catch (a.C0075a unused2) {
            i2 = 100;
            i(i2, fVar, str, str2);
            return f2230c;
        } catch (a.b unused3) {
            i2 = 101;
            i(i2, fVar, str, str2);
            return f2230c;
        } catch (a.c unused4) {
            i2 = 102;
            i(i2, fVar, str, str2);
            return f2230c;
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "internalParseViewNode catch exception", str, str2, (int) R2.attr.liteMode, e2);
            if (viewNode4 != null) {
                viewNode4.parseSuccess = false;
            }
            e2.printStackTrace();
            return f2230c;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        if (r0 != 0) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ViewNode c(f fVar, String str, String str2) {
        int a;
        new ViewNode();
        int f2 = fVar.f();
        int f3 = fVar.f();
        if (f2 >= 8) {
            if (f3 > 0) {
                fVar.c(f2);
                a = a(fVar, f3);
            }
            fVar.c(8);
            ViewNode b = b(fVar, null, null, str, str2, new AtomicInteger(900000), new ArrayList());
            if (b != null && !b.parseSuccess) {
                i(105, fVar, str, str2);
                b = f2230c;
            }
            fVar.a();
            return b;
        }
        a = 104;
        i(a, fVar, str, str2);
        return f2230c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g f() {
        return new g();
    }

    private String h(int i2) {
        Map<Integer, String> map = d;
        if (!map.containsKey(Integer.valueOf(i2))) {
            if (!this.a.containsKey(Integer.valueOf(i2))) {
                return null;
            }
            map = this.a;
        }
        return map.get(Integer.valueOf(i2));
    }

    private void i(int i2, f fVar, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(" parse error: ");
        sb.append(i2);
        sb.append(" reader: ");
        sb.append(fVar == null ? -1 : fVar.toString());
        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, sb.toString(), str, str2, (int) R2.attr.logoDescription, new Exception());
    }

    private void j(String str, String str2, String str3) {
        if (this.b) {
            DynamicMtaUtil.startLoadTemplateObj(str2, str, str3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0092 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ViewNode l(InputStream inputStream, String str, String str2, String str3) {
        int i2;
        int available;
        byte[] bArr;
        ViewNode viewNode = new ViewNode();
        if (inputStream == null) {
            j(str, str2, str3);
            return viewNode;
        }
        f fVar = new f();
        boolean z = false;
        boolean z2 = true;
        try {
            try {
                available = inputStream.available();
                bArr = new byte[available];
            } catch (IOException e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToViewNode catch exception", str, str2, (int) R2.attr.liteMode, e2);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                z = true;
            }
            if (inputStream.read(bArr, 0, available) <= 0) {
                i(103, null, str, str2);
                j(str, str2, str3);
                ViewNode viewNode2 = f2230c;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return viewNode2;
            }
            fVar.b(bArr);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            if (this.b) {
                j(str, str2, str3);
            }
            DynamicMtaUtil.appendGetTemplateEnd(str3);
            if (z) {
                return f2230c;
            }
            MtaTimePair mtaTimePair = new MtaTimePair();
            mtaTimePair.startRecord();
            try {
                viewNode = c(fVar, str, str2);
                z2 = z;
            } catch (a.C0075a unused) {
                i2 = 100;
                i(i2, fVar, str, str2);
                mtaTimePair.endRecord();
                DynamicMtaUtil.appendCreateModelMtaStat(str3, mtaTimePair);
                if (z2) {
                }
            } catch (a.b unused2) {
                i2 = 101;
                i(i2, fVar, str, str2);
                mtaTimePair.endRecord();
                DynamicMtaUtil.appendCreateModelMtaStat(str3, mtaTimePair);
                if (z2) {
                }
            } catch (a.c unused3) {
                i2 = 102;
                i(i2, fVar, str, str2);
                mtaTimePair.endRecord();
                DynamicMtaUtil.appendCreateModelMtaStat(str3, mtaTimePair);
                if (z2) {
                }
            } catch (Exception unused4) {
                i2 = 105;
                i(i2, fVar, str, str2);
                mtaTimePair.endRecord();
                DynamicMtaUtil.appendCreateModelMtaStat(str3, mtaTimePair);
                if (z2) {
                }
            }
            mtaTimePair.endRecord();
            DynamicMtaUtil.appendCreateModelMtaStat(str3, mtaTimePair);
            return z2 ? f2230c : viewNode;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewNode d(InputStream inputStream, String str, String str2, String str3) {
        if (inputStream == null) {
            j(str, str2, str3);
            return f2230c;
        }
        return l(inputStream, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ViewNode e(String str, boolean z, String str2, String str3, String str4) {
        Throwable th;
        ViewNode viewNode = new ViewNode();
        if (TextUtils.isEmpty(str)) {
            j(str2, str3, str4);
            return viewNode;
        }
        String o = !z ? com.jd.dynamic.b.e.a.a.o(str3, str2) : str;
        ViewNode q = com.jd.dynamic.b.e.a.a.q(o);
        if (q != null && !TextUtils.isEmpty(q.getViewName())) {
            j(str2, str3, str4);
            t.e("InnerParser", "parseBinaryToViewNode  hit viewnode cache return !!!  cachekey::" + o);
            return q;
        }
        InputStream inputStream = null;
        try {
            try {
                if (z) {
                    inputStream = DynamicSdk.getEngine().getContext().getAssets().open(str);
                } else {
                    File file = new File(str);
                    if (file.exists() && file.isFile()) {
                        if (file.length() < 0) {
                            j(str2, str3, str4);
                            return viewNode;
                        }
                        inputStream = new FileInputStream(str);
                    }
                }
                viewNode = l(inputStream, str2, str3, str4);
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e2) {
                try {
                    j(str2, str3, str4);
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToViewNode catch exception", str2, str3, (int) R2.attr.liteMode, e2);
                    t.e("parseBinaryToViewNode", t.d(e2));
                    if (0 != 0) {
                        inputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = null;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (inputStream != null) {
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return viewNode;
    }

    public g g(boolean z) {
        this.b = z;
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x020d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ResultEntity k(String str, boolean z, String str2, String str3, String str4) {
        Exception exc;
        File file;
        File file2;
        long nanoTime;
        File file3;
        List<File> c2;
        File file4;
        Exception exc2;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        if (TextUtils.isEmpty(str)) {
            j(str2, str3, str4);
            return null;
        }
        String r = !z ? com.jd.dynamic.b.e.a.b.r(str2, str3) : str;
        long nanoTime2 = System.nanoTime();
        ResultEntity v = com.jd.dynamic.b.e.a.b.v(r);
        boolean z2 = true;
        if (v != null) {
            t.e("getViewNodeFromMem", "take time = " + DynamicMtaUtil.getCurMicroseconds(System.nanoTime() - nanoTime2));
            v.isAssets = z;
            ViewNode viewNode = v.viewNode;
            if (viewNode != null && !TextUtils.isEmpty(viewNode.getViewName())) {
                if (!TextUtils.isEmpty(v.jsString)) {
                    DynamicMtaUtil.updateTemplateType2JS(str4);
                } else if (!TextUtils.isEmpty(v.zipDir)) {
                    DynamicMtaUtil.updateTemplateType2Zip(str4);
                }
                t.e("InnerParser", "parseBinaryToViewNode  hit viewnode cache return !!!  cachekey::" + r);
                j(str2, str3, str4);
                return v;
            }
        }
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.isAssets = z;
        File file5 = new File(str);
        if (!z && file5.exists() && file5.isFile()) {
            resultEntity.viewNode = e(str, false, str2, str3, str4);
            return resultEntity;
        }
        if (z) {
            try {
                if (!str.endsWith(".zip")) {
                    resultEntity.viewNode = e(str, z, str2, str3, str4);
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
                            long nanoTime3 = System.nanoTime();
                            Template a = com.jd.dynamic.b.e.a.b.a(str3, str2);
                            if (a != null) {
                                long j2 = nanoTime3 - nanoTime;
                                if (c2 == null) {
                                    z2 = false;
                                }
                                DynamicMtaUtil.uploadUnZipTempMta(str3, a, j2, z2, true);
                            }
                            file2 = file4;
                        } catch (Exception e3) {
                            e = e3;
                            file2 = file4;
                            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_UNZIP, "unzip assets source catch Error", str2, str3, e);
                            if (file2.exists()) {
                            }
                            j(str2, str3, str4);
                            return resultEntity;
                        }
                    }
                } catch (Exception e4) {
                    exc = e4;
                    file = file2;
                    j(str2, str3, str4);
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToResultEntity catch Error", str2, str3, (int) R2.attr.liteMode, exc);
                    file2 = file;
                    if (file2.exists()) {
                    }
                    j(str2, str3, str4);
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
            j(str2, str3, str4);
            return resultEntity;
        }
        File[] listFiles = file2.listFiles();
        String k2 = o.k(file2.getAbsolutePath());
        resultEntity.zipVersion = k2;
        DynamicMtaUtil.updateTemplateZipVersion(str4, k2);
        if (listFiles != null) {
            try {
                FileInputStream fileInputStream3 = null;
                for (File file6 : listFiles) {
                    try {
                        if (file6 != null) {
                            if (o.l(file6)) {
                                if (file6.length() < 0) {
                                    j(str2, str3, str4);
                                    return resultEntity;
                                }
                                fileInputStream3 = new FileInputStream(file6);
                            } else if (o.n(file6)) {
                                resultEntity.jsString = o.o(file6);
                            }
                        }
                    } catch (Exception e6) {
                        exc2 = e6;
                        fileInputStream = fileInputStream3;
                        j(str2, str3, str4);
                        DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToResultEntity2 catch Error", str2, str3, R2.attr.liteMode, exc2, m.q(resultEntity.zipVersion, null));
                        if (fileInputStream != null) {
                        }
                        if (TextUtils.isEmpty(resultEntity.jsString)) {
                        }
                        return resultEntity;
                    }
                }
                fileInputStream2 = fileInputStream3;
            } catch (Exception e7) {
                exc2 = e7;
                fileInputStream = null;
            }
        } else {
            fileInputStream2 = null;
        }
        fileInputStream = fileInputStream2;
        if (fileInputStream != null) {
            ViewNode d2 = d(fileInputStream, str2, str3, str4);
            resultEntity.viewNode = d2;
            d2.zipVersion = resultEntity.zipVersion;
        }
        if (TextUtils.isEmpty(resultEntity.jsString)) {
            DynamicMtaUtil.updateTemplateType2JS(str4);
        } else if (!TextUtils.isEmpty(resultEntity.zipDir)) {
            DynamicMtaUtil.updateTemplateType2Zip(str4);
        }
        return resultEntity;
    }
}
