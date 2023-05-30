package com.jd.dynamic.lib.dynamic.parser;

import androidx.annotation.Keep;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.MtaTimePair;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.t;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser.internalParseViewNode(com.jd.dynamic.lib.dynamic.parser.f, com.jd.dynamic.entity.ViewNode, com.jd.dynamic.entity.ViewNode, java.lang.String, java.lang.String, java.util.concurrent.atomic.AtomicInteger, java.util.List):com.jd.dynamic.entity.ViewNode");
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

    /* JADX WARN: Removed duplicated region for block: B:196:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x01ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jd.dynamic.entity.ResultEntity parseBinaryToResultEntity(java.lang.String r15, boolean r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 496
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser.parseBinaryToResultEntity(java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String, boolean):com.jd.dynamic.entity.ResultEntity");
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

    /* JADX WARN: Removed duplicated region for block: B:140:0x00c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0104 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:149:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jd.dynamic.entity.ViewNode parseBinaryToViewNode(java.lang.String r14, boolean r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser.parseBinaryToViewNode(java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String, boolean):com.jd.dynamic.entity.ViewNode");
    }

    private static void reportStartLoad(String str, String str2, String str3) {
        DynamicMtaUtil.startLoadTemplate(str2, str, str3);
    }
}
