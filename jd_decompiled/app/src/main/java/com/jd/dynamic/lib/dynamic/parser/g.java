package com.jd.dynamic.lib.dynamic.parser;

import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.ViewNode;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jd.dynamic.entity.ViewNode b(com.jd.dynamic.lib.dynamic.parser.f r18, com.jd.dynamic.entity.ViewNode r19, com.jd.dynamic.entity.ViewNode r20, java.lang.String r21, java.lang.String r22, java.util.concurrent.atomic.AtomicInteger r23, java.util.List<java.lang.Integer> r24) {
        /*
            Method dump skipped, instructions count: 321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.g.b(com.jd.dynamic.lib.dynamic.parser.f, com.jd.dynamic.entity.ViewNode, com.jd.dynamic.entity.ViewNode, java.lang.String, java.lang.String, java.util.concurrent.atomic.AtomicInteger, java.util.List):com.jd.dynamic.entity.ViewNode");
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0022, code lost:
        if (r0 != 0) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jd.dynamic.entity.ViewNode c(com.jd.dynamic.lib.dynamic.parser.f r9, java.lang.String r10, java.lang.String r11) {
        /*
            r8 = this;
            com.jd.dynamic.entity.ViewNode r0 = new com.jd.dynamic.entity.ViewNode
            r0.<init>()
            int r0 = r9.f()
            int r1 = r9.f()
            r2 = 8
            if (r0 >= r2) goto L19
            r0 = 104(0x68, float:1.46E-43)
        L13:
            r8.i(r0, r9, r10, r11)
            com.jd.dynamic.entity.ViewNode r9 = com.jd.dynamic.lib.dynamic.parser.g.f2230c
            return r9
        L19:
            if (r1 <= 0) goto L25
            r9.c(r0)
            int r0 = r8.a(r9, r1)
            if (r0 == 0) goto L25
            goto L13
        L25:
            r9.c(r2)
            java.util.concurrent.atomic.AtomicInteger r6 = new java.util.concurrent.atomic.AtomicInteger
            r0 = 900000(0xdbba0, float:1.261169E-39)
            r6.<init>(r0)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r2 = 0
            r3 = 0
            r0 = r8
            r1 = r9
            r4 = r10
            r5 = r11
            com.jd.dynamic.entity.ViewNode r0 = r0.b(r1, r2, r3, r4, r5, r6, r7)
            if (r0 == 0) goto L4c
            boolean r1 = r0.parseSuccess
            if (r1 != 0) goto L4c
            r0 = 105(0x69, float:1.47E-43)
            r8.i(r0, r9, r10, r11)
            com.jd.dynamic.entity.ViewNode r0 = com.jd.dynamic.lib.dynamic.parser.g.f2230c
        L4c:
            r9.a()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.g.c(com.jd.dynamic.lib.dynamic.parser.f, java.lang.String, java.lang.String):com.jd.dynamic.entity.ViewNode");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jd.dynamic.entity.ViewNode l(java.io.InputStream r11, java.lang.String r12, java.lang.String r13, java.lang.String r14) {
        /*
            r10 = this;
            com.jd.dynamic.entity.ViewNode r0 = new com.jd.dynamic.entity.ViewNode
            r0.<init>()
            if (r11 != 0) goto Lb
            r10.j(r12, r13, r14)
            return r0
        Lb:
            com.jd.dynamic.lib.dynamic.parser.f r1 = new com.jd.dynamic.lib.dynamic.parser.f
            r1.<init>()
            r2 = 0
            r3 = 1
            int r4 = r11.available()     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            byte[] r5 = new byte[r4]     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            int r4 = r11.read(r5, r2, r4)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            if (r4 > 0) goto L34
            r2 = 103(0x67, float:1.44E-43)
            r4 = 0
            r10.i(r2, r4, r12, r13)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            r10.j(r12, r13, r14)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            com.jd.dynamic.entity.ViewNode r12 = com.jd.dynamic.lib.dynamic.parser.g.f2230c     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            if (r11 == 0) goto L33
            r11.close()     // Catch: java.io.IOException -> L2f
            goto L33
        L2f:
            r11 = move-exception
            r11.printStackTrace()
        L33:
            return r12
        L34:
            r1.b(r5)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            if (r11 == 0) goto L5c
            r11.close()     // Catch: java.io.IOException -> L3d
            goto L5c
        L3d:
            r11 = move-exception
            r11.printStackTrace()
            goto L5c
        L42:
            r12 = move-exception
            goto L93
        L44:
            r2 = move-exception
            r9 = r2
            java.lang.String r4 = "parse"
            java.lang.String r5 = "parseBinaryToViewNode catch exception"
            r8 = 1302(0x516, float:1.824E-42)
            r6 = r12
            r7 = r13
            com.jd.dynamic.base.DynamicSdk.handException(r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L42
            if (r11 == 0) goto L5b
            r11.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r11 = move-exception
            r11.printStackTrace()
        L5b:
            r2 = 1
        L5c:
            boolean r11 = r10.b
            if (r11 == 0) goto L63
            r10.j(r12, r13, r14)
        L63:
            com.jd.dynamic.base.DynamicMtaUtil.appendGetTemplateEnd(r14)
            if (r2 == 0) goto L6b
            com.jd.dynamic.entity.ViewNode r11 = com.jd.dynamic.lib.dynamic.parser.g.f2230c
            return r11
        L6b:
            com.jd.dynamic.entity.MtaTimePair r11 = new com.jd.dynamic.entity.MtaTimePair
            r11.<init>()
            r11.startRecord()
            com.jd.dynamic.entity.ViewNode r0 = r10.c(r1, r12, r13)     // Catch: java.lang.Exception -> L79 com.jd.dynamic.lib.dynamic.parser.e.a.c -> L7c com.jd.dynamic.lib.dynamic.parser.e.a.b -> L7f com.jd.dynamic.lib.dynamic.parser.e.a.C0075a -> L82
            r3 = r2
            goto L87
        L79:
            r2 = 105(0x69, float:1.47E-43)
            goto L84
        L7c:
            r2 = 102(0x66, float:1.43E-43)
            goto L84
        L7f:
            r2 = 101(0x65, float:1.42E-43)
            goto L84
        L82:
            r2 = 100
        L84:
            r10.i(r2, r1, r12, r13)
        L87:
            r11.endRecord()
            com.jd.dynamic.base.DynamicMtaUtil.appendCreateModelMtaStat(r14, r11)
            if (r3 == 0) goto L92
            com.jd.dynamic.entity.ViewNode r11 = com.jd.dynamic.lib.dynamic.parser.g.f2230c
            return r11
        L92:
            return r0
        L93:
            if (r11 == 0) goto L9d
            r11.close()     // Catch: java.io.IOException -> L99
            goto L9d
        L99:
            r11 = move-exception
            r11.printStackTrace()
        L9d:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.g.l(java.io.InputStream, java.lang.String, java.lang.String, java.lang.String):com.jd.dynamic.entity.ViewNode");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.dynamic.entity.ViewNode e(java.lang.String r16, boolean r17, java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            r15 = this;
            r1 = r15
            r0 = r16
            r4 = r18
            r5 = r19
            r2 = r20
            com.jd.dynamic.entity.ViewNode r8 = new com.jd.dynamic.entity.ViewNode
            r8.<init>()
            boolean r3 = android.text.TextUtils.isEmpty(r16)
            if (r3 == 0) goto L18
            r15.j(r4, r5, r2)
            return r8
        L18:
            if (r17 != 0) goto L1f
            java.lang.String r3 = com.jd.dynamic.b.e.a.a.o(r5, r4)
            goto L20
        L1f:
            r3 = r0
        L20:
            com.jd.dynamic.entity.ViewNode r6 = com.jd.dynamic.b.e.a.a.q(r3)
            r9 = 1
            r10 = 0
            r11 = 2
            if (r6 == 0) goto L53
            java.lang.String r7 = r6.getViewName()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L53
            r15.j(r4, r5, r2)
            java.lang.Object[] r0 = new java.lang.Object[r11]
            java.lang.String r2 = "InnerParser"
            r0[r10] = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "parseBinaryToViewNode  hit viewnode cache return !!!  cachekey::"
            r2.append(r4)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0[r9] = r2
            com.jd.dynamic.lib.utils.t.e(r0)
            return r6
        L53:
            r3 = 0
            if (r17 == 0) goto L67
            com.jd.dynamic.base.DynamicSdk$Engine r6 = com.jd.dynamic.base.DynamicSdk.getEngine()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            android.content.Context r6 = r6.getContext()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            java.io.InputStream r3 = r6.open(r0)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            goto L8c
        L67:
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            boolean r7 = r6.exists()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            if (r7 == 0) goto L8c
            boolean r7 = r6.isFile()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            if (r7 == 0) goto L8c
            long r6 = r6.length()     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            r12 = 0
            int r14 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r14 >= 0) goto L86
            r15.j(r4, r5, r2)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            return r8
        L86:
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            r3 = r6
        L8c:
            com.jd.dynamic.entity.ViewNode r8 = r15.l(r3, r4, r5, r2)     // Catch: java.lang.Throwable -> L9c java.lang.Exception -> L9f
            if (r3 == 0) goto Lc6
            r3.close()     // Catch: java.io.IOException -> L96
            goto Lc6
        L96:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
            goto Lc6
        L9c:
            r0 = move-exception
            r2 = r0
            goto Lca
        L9f:
            r0 = move-exception
            r12 = r3
            r15.j(r4, r5, r2)     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r2 = "parse"
            java.lang.String r3 = "parseBinaryToViewNode catch exception"
            r6 = 1302(0x516, float:1.824E-42)
            r4 = r18
            r5 = r19
            r7 = r0
            com.jd.dynamic.base.DynamicSdk.handException(r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> Lc7
            java.lang.Object[] r2 = new java.lang.Object[r11]     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r3 = "parseBinaryToViewNode"
            r2[r10] = r3     // Catch: java.lang.Throwable -> Lc7
            java.lang.String r0 = com.jd.dynamic.lib.utils.t.d(r0)     // Catch: java.lang.Throwable -> Lc7
            r2[r9] = r0     // Catch: java.lang.Throwable -> Lc7
            com.jd.dynamic.lib.utils.t.e(r2)     // Catch: java.lang.Throwable -> Lc7
            if (r12 == 0) goto Lc6
            r12.close()     // Catch: java.io.IOException -> L96
        Lc6:
            return r8
        Lc7:
            r0 = move-exception
            r2 = r0
            r3 = r12
        Lca:
            if (r3 == 0) goto Ld5
            r3.close()     // Catch: java.io.IOException -> Ld0
            goto Ld5
        Ld0:
            r0 = move-exception
            r3 = r0
            r3.printStackTrace()
        Ld5:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.g.e(java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String):com.jd.dynamic.entity.ViewNode");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jd.dynamic.entity.ResultEntity k(java.lang.String r20, boolean r21, java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 545
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.dynamic.parser.g.k(java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String):com.jd.dynamic.entity.ResultEntity");
    }
}
