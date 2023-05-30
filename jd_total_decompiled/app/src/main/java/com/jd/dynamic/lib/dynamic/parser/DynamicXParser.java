package com.jd.dynamic.lib.dynamic.parser;

import android.app.Activity;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.DPIUtil;
import com.jd.dynamic.lib.utils.m;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.functions.Action1;

@Keep
/* loaded from: classes13.dex */
public class DynamicXParser {
    private static int RES_HEADER_LENGTH = 8;
    private static final String TAG = "DynamicXmlParser";
    public static double height;
    private static final Map<Integer, String> sConfigAttrs;
    private static final Map<Integer, String> sDefaultAttrs;
    public static double statusBarHeight;
    public static double width;
    private static final Object sLock = new Object();
    public static final ViewNode EMPTY_VIEW_NODE = new ViewNode();

    static {
        String[] strArr = {ViewProps.FLEX_DIRECTION, ViewProps.FLEX_WRAP, ViewProps.JUSTIFY_CONTENT, ViewProps.ALIGN_ITEMS, ViewProps.ALIGN_CONTENT, "marginLeft", "marginRight", "marginTop", "marginBottom", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom", "width", "height", ViewProps.FLEX_GROW, ViewProps.FLEX_SHRINK, ViewProps.ALIGN_SELF, "flexBasisPercent", ViewProps.MIN_WIDTH, "maxWidth", ViewProps.MIN_HEIGHT, "maxHeight", "layoutId", "alpha", "visibility", DYConstants.DY_BG_COLOR, "borderWidth", "borderColor", "borderRadius", DYConstants.DY_TEXT_SIZE, DYConstants.DY_TEXT_COLOR, "text", DYConstants.DY_TEXT_MAXLINES, DYConstants.DY_TEXT_ELLIPSIZE, DYConstants.DY_TEXT_STYLE, DYConstants.DY_GRAVITY, "scaleType", "src", "row", DYConstants.DY_ROW_REVERSE, "column", DYConstants.DY_COLUMN_REVERSE, "nowrap", "wrap", "wrap_reverse", DYConstants.DY_FLEX_START, DYConstants.DY_FLEX_END, DYConstants.DY_CENTER, "space_between", "space_around", DYConstants.DY_BASE_LINE, DYConstants.DY_STRETCH, DYConstants.DY_MATCH_PARENT, DYConstants.DY_WRAP_CONTENT, "auto", "none", "start", DYConstants.DY_MIDDLE, "end", "bold", "normal", "left", "right", DYConstants.DY_FIT, DYConstants.DY_FILL};
        sDefaultAttrs = new HashMap();
        for (int i2 = 0; i2 < 66; i2++) {
            sDefaultAttrs.put(new Integer(i2 + 0), strArr[i2]);
        }
        sConfigAttrs = new HashMap();
        EMPTY_VIEW_NODE.parseSuccess = false;
        width = 0.0d;
        height = 0.0d;
        statusBarHeight = 0.0d;
    }

    private DynamicXParser() {
    }

    public static /* synthetic */ void a(HashMap hashMap, Map.Entry entry) {
        if (DynamicUtils.isElOrKnownSymbol((String) entry.getValue())) {
            hashMap.put(entry.getKey(), entry.getValue());
        }
    }

    public static /* synthetic */ void b(HashMap hashMap, ConcurrentHashMap concurrentHashMap, ViewNode viewNode) {
        if (m.J(hashMap)) {
            concurrentHashMap.put(viewNode, hashMap);
        }
    }

    public static /* synthetic */ void d(Throwable th) {
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

    public static double getWidth() {
        return width;
    }

    public static void initUnBindMap(final ViewNode viewNode, final ConcurrentHashMap<ViewNode, HashMap<String, String>> concurrentHashMap) {
        new HashMap();
        Observable.concat(Observable.from(viewNode.getAttributes().entrySet()).doOnNext(new Action1
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003e: INVOKE 
              (wrap: rx.Observable : 0x003a: INVOKE 
              (wrap: rx.Observable : 0x0034: INVOKE 
              (wrap: rx.Observable : 0x001f: INVOKE 
              (wrap: rx.Observable : 0x0016: INVOKE 
              (wrap: rx.Observable : 0x000d: INVOKE 
              (wrap: java.util.Set<java.util.Map$Entry<java.lang.String, java.lang.String>> : 0x0009: INVOKE 
              (wrap: java.util.HashMap<java.lang.String, java.lang.String> : 0x0005: INVOKE (r3v0 'viewNode' com.jd.dynamic.entity.ViewNode) type: VIRTUAL call: com.jd.dynamic.entity.ViewNode.getAttributes():java.util.HashMap A[MD:():java.util.HashMap<java.lang.String, java.lang.String> (m), WRAPPED])
             type: VIRTUAL call: java.util.HashMap.entrySet():java.util.Set A[MD:():java.util.Set<java.util.Map$Entry<K, V>> (c), WRAPPED])
             type: STATIC call: rx.Observable.from(java.lang.Iterable):rx.Observable A[MD:<T>:(java.lang.Iterable<? extends T>):rx.Observable<T> (m), WRAPPED])
              (wrap: rx.functions.Action1 : 0x0013: CONSTRUCTOR (r0 I:java.util.HashMap A[DONT_GENERATE, DONT_INLINE, REMOVE]) A[MD:(java.util.HashMap):void (m), WRAPPED] call: com.jd.dynamic.lib.dynamic.parser.a.<init>(java.util.HashMap):void type: CONSTRUCTOR)
             type: VIRTUAL call: rx.Observable.doOnNext(rx.functions.Action1):rx.Observable A[MD:(rx.functions.Action1<? super T>):rx.Observable<T> (m), WRAPPED])
              (wrap: rx.functions.Action0 : 0x001c: CONSTRUCTOR 
              (r0 I:java.util.HashMap A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r4v0 'concurrentHashMap' java.util.concurrent.ConcurrentHashMap<com.jd.dynamic.entity.ViewNode, java.util.HashMap<java.lang.String, java.lang.String>> A[DONT_INLINE])
              (r3v0 'viewNode' com.jd.dynamic.entity.ViewNode A[DONT_INLINE])
             A[MD:(java.util.HashMap, java.util.concurrent.ConcurrentHashMap, com.jd.dynamic.entity.ViewNode):void (m), WRAPPED] call: com.jd.dynamic.lib.dynamic.parser.d.<init>(java.util.HashMap, java.util.concurrent.ConcurrentHashMap, com.jd.dynamic.entity.ViewNode):void type: CONSTRUCTOR)
             type: VIRTUAL call: rx.Observable.doOnCompleted(rx.functions.Action0):rx.Observable A[MD:(rx.functions.Action0):rx.Observable<T> (m), WRAPPED])
              (wrap: rx.Observable : 0x0030: INVOKE 
              (wrap: rx.Observable : 0x0027: INVOKE 
              (wrap: java.util.List<com.jd.dynamic.entity.ViewNode> : 0x0023: INVOKE (r3v0 'viewNode' com.jd.dynamic.entity.ViewNode) type: VIRTUAL call: com.jd.dynamic.entity.ViewNode.getChilds():java.util.List A[MD:():java.util.List<com.jd.dynamic.entity.ViewNode> (m), WRAPPED])
             type: STATIC call: rx.Observable.from(java.lang.Iterable):rx.Observable A[MD:<T>:(java.lang.Iterable<? extends T>):rx.Observable<T> (m), WRAPPED])
              (wrap: rx.functions.Action1 : 0x002d: CONSTRUCTOR 
              (r4v0 'concurrentHashMap' java.util.concurrent.ConcurrentHashMap<com.jd.dynamic.entity.ViewNode, java.util.HashMap<java.lang.String, java.lang.String>> A[DONT_INLINE])
             A[MD:(java.util.concurrent.ConcurrentHashMap):void (m), WRAPPED] call: com.jd.dynamic.lib.dynamic.parser.c.<init>(java.util.concurrent.ConcurrentHashMap):void type: CONSTRUCTOR)
             type: VIRTUAL call: rx.Observable.doOnNext(rx.functions.Action1):rx.Observable A[MD:(rx.functions.Action1<? super T>):rx.Observable<T> (m), WRAPPED])
             type: STATIC call: rx.Observable.concat(rx.Observable, rx.Observable):rx.Observable A[MD:<T>:(rx.Observable<? extends T>, rx.Observable<? extends T>):rx.Observable<T> (m), WRAPPED])
              (wrap: com.jd.dynamic.lib.dynamic.parser.b : 0x0038: SGET  A[WRAPPED] com.jd.dynamic.lib.dynamic.parser.b.g com.jd.dynamic.lib.dynamic.parser.b)
             type: VIRTUAL call: rx.Observable.doOnError(rx.functions.Action1):rx.Observable A[MD:(rx.functions.Action1<java.lang.Throwable>):rx.Observable<T> (m), WRAPPED])
             type: VIRTUAL call: rx.Observable.subscribe():rx.Subscription A[MD:():rx.Subscription (m)] in method: com.jd.dynamic.lib.dynamic.parser.DynamicXParser.initUnBindMap(com.jd.dynamic.entity.ViewNode, java.util.concurrent.ConcurrentHashMap<com.jd.dynamic.entity.ViewNode, java.util.HashMap<java.lang.String, java.lang.String>>):void, file: classes13.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            */
        /*
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r1 = r3.getAttributes()
            java.util.Set r1 = r1.entrySet()
            rx.Observable r1 = rx.Observable.from(r1)
            com.jd.dynamic.lib.dynamic.parser.a r2 = new com.jd.dynamic.lib.dynamic.parser.a
            r2.<init>()
            rx.Observable r1 = r1.doOnNext(r2)
            com.jd.dynamic.lib.dynamic.parser.d r2 = new com.jd.dynamic.lib.dynamic.parser.d
            r2.<init>()
            rx.Observable r0 = r1.doOnCompleted(r2)
            java.util.List r3 = r3.getChilds()
            rx.Observable r3 = rx.Observable.from(r3)
            com.jd.dynamic.lib.dynamic.parser.c r1 = new com.jd.dynamic.lib.dynamic.parser.c
            r1.<init>()
            rx.Observable r3 = r3.doOnNext(r1)
            rx.Observable r3 = rx.Observable.concat(r0, r3)
            com.jd.dynamic.lib.dynamic.parser.b r4 = com.jd.dynamic.lib.dynamic.parser.b.f2224g
            rx.Observable r3 = r3.doOnError(r4)
            r3.subscribe()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.DynamicXParser.initUnBindMap(com.jd.dynamic.entity.ViewNode, java.util.concurrent.ConcurrentHashMap):void");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:90:0x0024 -> B:112:0x003d). Please submit an issue!!! */
    private static ViewNode innerParseUseStream(InputStream inputStream, String str, String str2, String str3) {
        ViewNode viewNode = new ViewNode();
        if (inputStream == null) {
            reportStartLoad(str, str2, str3);
            return viewNode;
        }
        f fVar = new f();
        boolean z = false;
        try {
            try {
                try {
                    int available = inputStream.available();
                    byte[] bArr = new byte[available];
                    inputStream.read(bArr, 0, available);
                    fVar.b(bArr);
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e3) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "parseBinaryToViewNode catch exception", str, str2, (int) R2.attr.liteMode, e3);
                z = true;
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        reportStartLoad(str, str2, str3);
        DynamicMtaUtil.appendGetTemplateEnd(str3);
        if (z) {
            return viewNode;
        }
        MtaTimePair mtaTimePair = new MtaTimePair();
        mtaTimePair.startRecord();
        ViewNode parseBinaryToViewNode = parseBinaryToViewNode(fVar, str, str2);
        mtaTimePair.endRecord();
        DynamicMtaUtil.appendCreateModelMtaStat(str3, mtaTimePair);
        return parseBinaryToViewNode;
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
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "internalParseViewNode parse failed node name is null", str, str2, (int) R2.attr.loadProgressColor, new Exception());
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

    private static ViewNode parseBinaryToViewNode(f fVar, String str, String str2) {
        new ViewNode();
        int f2 = fVar.f();
        int f3 = fVar.f();
        if (f2 < RES_HEADER_LENGTH) {
            return EMPTY_VIEW_NODE;
        }
        if (f3 > 0) {
            fVar.c(f2);
            internalParseCustomRes(fVar, f3);
        }
        fVar.c(RES_HEADER_LENGTH);
        ViewNode internalParseViewNode = internalParseViewNode(fVar, null, null, str, str2, new AtomicInteger(900000), new ArrayList());
        if (internalParseViewNode != null && !internalParseViewNode.parseSuccess) {
            internalParseViewNode = EMPTY_VIEW_NODE;
        }
        fVar.a();
        return internalParseViewNode;
    }

    public static ViewNode parseBinaryToViewNode(InputStream inputStream, String str, String str2, String str3) {
        if (com.jd.dynamic.b.a.b.o().O()) {
            return g.f().d(inputStream, str, str2, str3);
        }
        return inputStream == null ? new ViewNode() : innerParseUseStream(inputStream, str, str2, str3);
    }

    public static ViewNode parseBinaryToViewNode(String str, String str2, String str3, String str4) {
        return parseBinaryToViewNode(str, false, str2, str3, str4);
    }

    /* JADX WARN: Removed duplicated region for block: B:200:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ViewNode parseBinaryToViewNode(String str, boolean z, String str2, String str3, String str4) {
        Throwable th;
        String str5;
        if (com.jd.dynamic.b.a.b.o().O()) {
            return g.f().e(str, z, str2, str3, str4);
        }
        ViewNode viewNode = new ViewNode();
        if (TextUtils.isEmpty(str)) {
            reportStartLoad(str2, str3, str4);
            return viewNode;
        }
        String o = !z ? com.jd.dynamic.b.e.a.a.o(str3, str2) : str;
        ViewNode q = com.jd.dynamic.b.e.a.a.q(o);
        if (q != null && !TextUtils.isEmpty(q.getViewName())) {
            t.e(TAG, "parseBinaryToViewNode  hit viewnode cache return !!!  cachekey::" + o);
            reportStartLoad(str2, str3, str4);
            return q;
        }
        FileInputStream fileInputStream = null;
        try {
            if (z) {
                fileInputStream = DynamicSdk.getEngine().getContext().getAssets().open(str);
            } else {
                File file = new File(str);
                if (file.exists() && file.isFile()) {
                    if (file.length() < 0) {
                        reportStartLoad(str2, str3, str4);
                        return viewNode;
                    }
                    str5 = str4;
                    fileInputStream = new FileInputStream(str);
                    ViewNode innerParseUseStream = innerParseUseStream(fileInputStream, str2, str3, str5);
                    sConfigAttrs.clear();
                    if (fileInputStream == null) {
                        try {
                            fileInputStream.close();
                            return innerParseUseStream;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            return innerParseUseStream;
                        }
                    }
                    return innerParseUseStream;
                }
            }
            str5 = str4;
            ViewNode innerParseUseStream2 = innerParseUseStream(fileInputStream, str2, str3, str5);
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
                return viewNode;
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

    public static void updateWAndH(Activity activity) {
        if (activity == null) {
            return;
        }
        width = DPIUtil.getWidthWithDp(activity);
        height = DPIUtil.getHeightWithDp(activity);
        statusBarHeight = DPIUtil.getStatusBarHeightDP(activity);
    }
}
