package com.jd.dynamic.lib.dynamic.parser;

import android.app.Activity;
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
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
            com.jd.dynamic.lib.dynamic.parser.b r4 = new rx.functions.Action1() { // from class: com.jd.dynamic.lib.dynamic.parser.b
                static {
                    /*
                        com.jd.dynamic.lib.dynamic.parser.b r0 = new com.jd.dynamic.lib.dynamic.parser.b
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.jd.dynamic.lib.dynamic.parser.b) com.jd.dynamic.lib.dynamic.parser.b.g com.jd.dynamic.lib.dynamic.parser.b
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.b.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.b.<init>():void");
                }

                @Override // rx.functions.Action1
                public final void call(java.lang.Object r1) {
                    /*
                        r0 = this;
                        java.lang.Throwable r1 = (java.lang.Throwable) r1
                        com.jd.dynamic.lib.dynamic.parser.DynamicXParser.d(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.b.call(java.lang.Object):void");
                }
            }
            rx.Observable r3 = r3.doOnError(r4)
            r3.subscribe()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.DynamicXParser.initUnBindMap(com.jd.dynamic.entity.ViewNode, java.util.concurrent.ConcurrentHashMap):void");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x0024 -> B:73:0x003d). Please submit an issue!!! */
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

    /* JADX WARN: Removed duplicated region for block: B:101:0x00ac A[Catch: Exception -> 0x00f4, TryCatch #1 {Exception -> 0x00f4, blocks: (B:70:0x000d, B:73:0x002f, B:75:0x0045, B:77:0x004b, B:79:0x0051, B:81:0x0056, B:83:0x005c, B:84:0x0060, B:85:0x0063, B:86:0x0066, B:89:0x0074, B:92:0x007a, B:99:0x009b, B:101:0x00ac, B:102:0x00b1, B:104:0x00bc, B:106:0x00d8, B:107:0x00ed, B:108:0x00f0, B:98:0x0096, B:97:0x0093, B:96:0x008f, B:91:0x0078, B:94:0x0088), top: B:118:0x000d, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x00bc A[Catch: Exception -> 0x00f4, TryCatch #1 {Exception -> 0x00f4, blocks: (B:70:0x000d, B:73:0x002f, B:75:0x0045, B:77:0x004b, B:79:0x0051, B:81:0x0056, B:83:0x005c, B:84:0x0060, B:85:0x0063, B:86:0x0066, B:89:0x0074, B:92:0x007a, B:99:0x009b, B:101:0x00ac, B:102:0x00b1, B:104:0x00bc, B:106:0x00d8, B:107:0x00ed, B:108:0x00f0, B:98:0x0096, B:97:0x0093, B:96:0x008f, B:91:0x0078, B:94:0x0088), top: B:118:0x000d, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x00f0 A[EDGE_INSN: B:125:0x00f0->B:108:0x00f0 BREAK  A[LOOP:1: B:103:0x00ba->B:107:0x00ed], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.jd.dynamic.entity.ViewNode internalParseViewNode(com.jd.dynamic.lib.dynamic.parser.f r14, com.jd.dynamic.entity.ViewNode r15, com.jd.dynamic.entity.ViewNode r16, java.lang.String r17, java.lang.String r18, java.util.concurrent.atomic.AtomicInteger r19, java.util.List<java.lang.Integer> r20) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.DynamicXParser.internalParseViewNode(com.jd.dynamic.lib.dynamic.parser.f, com.jd.dynamic.entity.ViewNode, com.jd.dynamic.entity.ViewNode, java.lang.String, java.lang.String, java.util.concurrent.atomic.AtomicInteger, java.util.List):com.jd.dynamic.entity.ViewNode");
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

    /* JADX WARN: Removed duplicated region for block: B:130:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jd.dynamic.entity.ViewNode parseBinaryToViewNode(java.lang.String r13, boolean r14, java.lang.String r15, java.lang.String r16, java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.DynamicXParser.parseBinaryToViewNode(java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String):com.jd.dynamic.entity.ViewNode");
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
