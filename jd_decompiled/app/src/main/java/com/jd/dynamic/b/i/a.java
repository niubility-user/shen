package com.jd.dynamic.b.i;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.utils.t;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class a {
    public static final a b = new a();
    @NotNull
    private static Map<String, String> a = new HashMap();

    private a() {
    }

    @JvmStatic
    @Nullable
    public static final ResultEntity a(@Nullable String str, @Nullable String str2, int i2, @Nullable String str3, boolean z) {
        boolean endsWith$default;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        Map<String, String> map = a;
        if (map != null) {
            if (!map.containsKey(str2)) {
                e(str, str2);
            }
            Map<String, String> map2 = a;
            if (map2 != null) {
                if (map2.containsKey(str2)) {
                    String str4 = a.get(str2);
                    if (str4 == null) {
                        Intrinsics.throwNpe();
                    }
                    endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str4, ".zip", false, 2, null);
                    if (endsWith$default) {
                        a aVar = b;
                        if (str == null) {
                            Intrinsics.throwNpe();
                        }
                        if (str2 == null) {
                            Intrinsics.throwNpe();
                        }
                        return aVar.f(str, str2, i2, str3, z);
                    }
                    StringBuilder sb = new StringBuilder();
                    DynamicSdk.Engine engine = DynamicSdk.getEngine();
                    Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
                    sb.append(engine.getLocalResourcePath());
                    String str5 = File.separator;
                    sb.append(str5);
                    sb.append(str);
                    sb.append(str5);
                    sb.append(str2);
                    String sb2 = sb.toString();
                    t.g("LocalTemplateManager", "loadBinaryLocalFile", "bizField is " + str2 + ",path is " + sb2);
                    return NewDynamicXParser.parseBinaryToResultEntity(sb2, true, str2, str, str3, z);
                }
                return null;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
    }

    public static /* synthetic */ ResultEntity b(String str, String str2, int i2, String str3, boolean z, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z = true;
        }
        return a(str, str2, i2, str3, z);
    }

    private final ResultEntity c(String str, String str2, String str3, String str4, boolean z) {
        ResultEntity parseBinaryToResultEntity = NewDynamicXParser.parseBinaryToResultEntity(str3, str2, str, str4, z);
        Intrinsics.checkExpressionValueIsNotNull(parseBinaryToResultEntity, "NewDynamicXParser.parseB\u2026    withLoadMta\n        )");
        return parseBinaryToResultEntity;
    }

    @JvmStatic
    @NotNull
    public static final String d() {
        DynamicSdk.Engine engine = DynamicSdk.getEngine();
        Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
        Context context = engine.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "DynamicSdk.getEngine().context.applicationContext");
        File rootDir = applicationContext.getFilesDir();
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkExpressionValueIsNotNull(rootDir, "rootDir");
        sb.append(rootDir.getAbsolutePath());
        String str = File.separator;
        sb.append(str);
        DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
        Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
        sb.append(engine2.getCacheDir());
        sb.append(str);
        sb.append("localTemp");
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00a5 A[Catch: Exception -> 0x00e4, TryCatch #0 {Exception -> 0x00e4, blocks: (B:93:0x007d, B:95:0x0097, B:104:0x00a5, B:106:0x00a9, B:108:0x00c2, B:111:0x00c9, B:112:0x00cc, B:114:0x00d0, B:115:0x00d3), top: B:121:0x007d }] */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean e(@org.jetbrains.annotations.Nullable java.lang.String r8, @org.jetbrains.annotations.Nullable java.lang.String r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            r1 = 0
            if (r0 != 0) goto Le4
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            if (r0 == 0) goto Lf
            goto Le4
        Lf:
            java.lang.String r5 = com.jd.dynamic.base.DynamicSdk.getDynamicStatus()
            java.lang.String r0 = "0"
            boolean r2 = android.text.TextUtils.equals(r5, r0)
            if (r2 == 0) goto L25
        L1b:
            r6 = 0
            r7 = 0
            java.lang.String r4 = ""
            r2 = r8
            r3 = r9
            com.jd.dynamic.base.DynamicMtaUtil.uploadDowngradeTemplateMta(r2, r3, r4, r5, r6, r7)
            return r1
        L25:
            com.jd.dynamic.b.d.a r2 = com.jd.dynamic.b.d.a.g(r8)
            java.lang.String r3 = r2.b
            boolean r0 = android.text.TextUtils.equals(r3, r0)
            if (r0 == 0) goto L3e
            boolean r0 = r2.b(r9)
            if (r0 != 0) goto L3e
            boolean r0 = r2.e(r9)
            if (r0 != 0) goto L3e
            goto L1b
        L3e:
            com.jd.dynamic.base.DynamicSdk$Engine r0 = com.jd.dynamic.base.DynamicSdk.getEngine()
            if (r0 == 0) goto L49
            java.lang.String r0 = r0.getLocalResourcePath()
            goto L4a
        L49:
            r0 = 0
        L4a:
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto Le4
            java.util.Map<java.lang.String, java.lang.String> r0 = com.jd.dynamic.b.i.a.a
            if (r0 == 0) goto Ldc
            boolean r0 = r0.containsKey(r9)
            r2 = 1
            if (r0 == 0) goto L5c
            return r2
        L5c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.jd.dynamic.base.DynamicSdk$Engine r3 = com.jd.dynamic.base.DynamicSdk.getEngine()
            java.lang.String r4 = "DynamicSdk.getEngine()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.String r3 = r3.getLocalResourcePath()
            r0.append(r3)
            java.lang.String r3 = java.io.File.separator
            r0.append(r3)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            com.jd.dynamic.base.DynamicSdk$Engine r0 = com.jd.dynamic.base.DynamicSdk.getEngine()     // Catch: java.lang.Exception -> Le4
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r4)     // Catch: java.lang.Exception -> Le4
            android.content.Context r0 = r0.getContext()     // Catch: java.lang.Exception -> Le4
            java.lang.String r3 = "DynamicSdk.getEngine().context"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)     // Catch: java.lang.Exception -> Le4
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch: java.lang.Exception -> Le4
            java.lang.String[] r8 = r0.list(r8)     // Catch: java.lang.Exception -> Le4
            if (r8 == 0) goto La2
            int r0 = r8.length     // Catch: java.lang.Exception -> Le4
            if (r0 != 0) goto L9c
            r0 = 1
            goto L9d
        L9c:
            r0 = 0
        L9d:
            if (r0 == 0) goto La0
            goto La2
        La0:
            r0 = 0
            goto La3
        La2:
            r0 = 1
        La3:
            if (r0 != 0) goto Le4
            int r0 = r8.length     // Catch: java.lang.Exception -> Le4
            r3 = 0
        La7:
            if (r3 >= r0) goto Le4
            r4 = r8[r3]     // Catch: java.lang.Exception -> Le4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Le4
            r5.<init>()     // Catch: java.lang.Exception -> Le4
            r5.append(r9)     // Catch: java.lang.Exception -> Le4
            java.lang.String r6 = ".zip"
            r5.append(r6)     // Catch: java.lang.Exception -> Le4
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> Le4
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r5)     // Catch: java.lang.Exception -> Le4
            if (r5 != 0) goto Lcc
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r9)     // Catch: java.lang.Exception -> Le4
            if (r5 == 0) goto Lc9
            goto Lcc
        Lc9:
            int r3 = r3 + 1
            goto La7
        Lcc:
            java.util.Map<java.lang.String, java.lang.String> r8 = com.jd.dynamic.b.i.a.a     // Catch: java.lang.Exception -> Le4
            if (r9 != 0) goto Ld3
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Exception -> Le4
        Ld3:
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r0)     // Catch: java.lang.Exception -> Le4
            r8.put(r9, r4)     // Catch: java.lang.Exception -> Le4
            return r2
        Ldc:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.collections.Map<K, *>"
            r8.<init>(r9)
            throw r8
        Le4:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.b.i.a.e(java.lang.String, java.lang.String):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0079, code lost:
        if ((r2.length == 0) != false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007c, code lost:
        if (r4 == false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.jd.dynamic.entity.ResultEntity f(java.lang.String r9, java.lang.String r10, int r11, java.lang.String r12, boolean r13) {
        /*
            r8 = this;
            java.lang.String r0 = "DynamicSdk.getEngine()"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lab
            r1.<init>()     // Catch: java.lang.Exception -> Lab
            com.jd.dynamic.base.DynamicSdk$Engine r2 = com.jd.dynamic.base.DynamicSdk.getEngine()     // Catch: java.lang.Exception -> Lab
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)     // Catch: java.lang.Exception -> Lab
            java.lang.String r2 = r2.getLocalResourcePath()     // Catch: java.lang.Exception -> Lab
            r1.append(r2)     // Catch: java.lang.Exception -> Lab
            java.lang.String r2 = java.io.File.separator     // Catch: java.lang.Exception -> Lab
            r1.append(r2)     // Catch: java.lang.Exception -> Lab
            r1.append(r9)     // Catch: java.lang.Exception -> Lab
            r1.append(r2)     // Catch: java.lang.Exception -> Lab
            r1.append(r10)     // Catch: java.lang.Exception -> Lab
            java.lang.String r2 = ".zip"
            r1.append(r2)     // Catch: java.lang.Exception -> Lab
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> Lab
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> Lab
            java.lang.String r3 = "LocalTemplateManager"
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.Exception -> Lab
            java.lang.String r3 = "loadZipLocalFile"
            r5 = 1
            r2[r5] = r3     // Catch: java.lang.Exception -> Lab
            r3 = 2
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lab
            r6.<init>()     // Catch: java.lang.Exception -> Lab
            java.lang.String r7 = "bizField is "
            r6.append(r7)     // Catch: java.lang.Exception -> Lab
            r6.append(r10)     // Catch: java.lang.Exception -> Lab
            java.lang.String r7 = ",path is "
            r6.append(r7)     // Catch: java.lang.Exception -> Lab
            r6.append(r1)     // Catch: java.lang.Exception -> Lab
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> Lab
            r2[r3] = r6     // Catch: java.lang.Exception -> Lab
            com.jd.dynamic.lib.utils.t.g(r2)     // Catch: java.lang.Exception -> Lab
            java.lang.String r6 = r8.h(r9, r10)     // Catch: java.lang.Exception -> Lab
            java.io.File r2 = new java.io.File     // Catch: java.lang.Exception -> Lab
            r2.<init>(r6)     // Catch: java.lang.Exception -> Lab
            boolean r3 = r2.exists()     // Catch: java.lang.Exception -> Lab
            if (r3 == 0) goto L89
            boolean r3 = r2.isDirectory()     // Catch: java.lang.Exception -> Lab
            if (r3 == 0) goto L89
            java.lang.String[] r2 = r2.list()     // Catch: java.lang.Exception -> Lab
            if (r2 == 0) goto L7b
            int r2 = r2.length     // Catch: java.lang.Exception -> Lab
            if (r2 != 0) goto L78
            r2 = 1
            goto L79
        L78:
            r2 = 0
        L79:
            if (r2 == 0) goto L7c
        L7b:
            r4 = 1
        L7c:
            if (r4 != 0) goto L89
        L7e:
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r6
            r5 = r12
            r6 = r13
            com.jd.dynamic.entity.ResultEntity r0 = r1.c(r2, r3, r4, r5, r6)     // Catch: java.lang.Exception -> Lab
            goto Laa
        L89:
            com.jd.dynamic.base.DynamicSdk$Engine r2 = com.jd.dynamic.base.DynamicSdk.getEngine()     // Catch: java.lang.Exception -> Lab
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r0)     // Catch: java.lang.Exception -> Lab
            android.content.Context r0 = r2.getContext()     // Catch: java.lang.Exception -> Lab
            java.lang.String r2 = "DynamicSdk.getEngine().context"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)     // Catch: java.lang.Exception -> Lab
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch: java.lang.Exception -> Lab
            java.io.InputStream r0 = r0.open(r1)     // Catch: java.lang.Exception -> Lab
            java.lang.String r1 = "DynamicSdk.getEngine().c\u2026ssets.open(localTempName)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)     // Catch: java.lang.Exception -> Lab
            com.jd.dynamic.lib.utils.o.f(r0, r6)     // Catch: java.lang.Exception -> Lab
            goto L7e
        Laa:
            return r0
        Lab:
            r0 = move-exception
            r6 = r0
            java.lang.String r1 = "backUpErr"
            java.lang.String r2 = "\u52a0\u8f7d\u515c\u5e95\u6587\u4ef6\u5931\u8d25"
            r3 = r10
            r4 = r9
            r5 = r11
            com.jd.dynamic.base.DynamicSdk.handException(r1, r2, r3, r4, r5, r6)
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.b.i.a.f(java.lang.String, java.lang.String, int, java.lang.String, boolean):com.jd.dynamic.entity.ResultEntity");
    }

    private final String h(String str, String str2) {
        DynamicSdk.Engine engine = DynamicSdk.getEngine();
        Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
        Context context = engine.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
        PackageManager packageManager = context.getPackageManager();
        DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
        Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
        Context context2 = engine2.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "DynamicSdk.getEngine().context");
        String str3 = packageManager.getPackageInfo(context2.getPackageName(), 0).versionName;
        StringBuilder sb = new StringBuilder();
        sb.append(d());
        String str4 = File.separator;
        sb.append(str4);
        sb.append(str3);
        sb.append(str4);
        sb.append(str);
        sb.append(str4);
        sb.append(str2);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x0074, code lost:
        if ((r3.length == 0) != false) goto L53;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(@org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull java.lang.String r8) {
        /*
            r6 = this;
            java.util.Map<java.lang.String, java.lang.String> r0 = com.jd.dynamic.b.i.a.a
            boolean r0 = r0.containsKey(r8)
            if (r0 != 0) goto Lb
            e(r7, r8)
        Lb:
            java.util.Map<java.lang.String, java.lang.String> r0 = com.jd.dynamic.b.i.a.a
            boolean r0 = r0.containsKey(r8)
            r1 = 0
            if (r0 == 0) goto Lc6
            java.util.Map<java.lang.String, java.lang.String> r0 = com.jd.dynamic.b.i.a.a
            java.lang.Object r0 = r0.get(r8)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = "DynamicSdk.getEngine()"
            if (r0 == 0) goto La0
            r3 = 2
            java.lang.String r4 = ".zip"
            r5 = 0
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r0, r4, r5, r3, r1)
            r1 = 1
            if (r1 != r0) goto La0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.jd.dynamic.base.DynamicSdk$Engine r3 = com.jd.dynamic.base.DynamicSdk.getEngine()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r2)
            java.lang.String r3 = r3.getLocalResourcePath()
            r0.append(r3)
            java.lang.String r3 = java.io.File.separator
            r0.append(r3)
            r0.append(r7)
            r0.append(r3)
            r0.append(r8)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.lang.String r7 = r6.h(r7, r8)
            java.io.File r8 = new java.io.File
            r8.<init>(r7)
            boolean r3 = r8.exists()
            if (r3 == 0) goto L7a
            boolean r3 = r8.isDirectory()
            if (r3 == 0) goto L7a
            java.lang.String[] r3 = r8.list()
            if (r3 == 0) goto L76
            int r3 = r3.length
            if (r3 != 0) goto L73
            r3 = 1
            goto L74
        L73:
            r3 = 0
        L74:
            if (r3 == 0) goto L77
        L76:
            r5 = 1
        L77:
            if (r5 != 0) goto L7a
            goto L9f
        L7a:
            com.jd.dynamic.base.DynamicSdk$Engine r8 = com.jd.dynamic.base.DynamicSdk.getEngine()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r2)
            android.content.Context r8 = r8.getContext()
            java.lang.String r1 = "DynamicSdk.getEngine().context"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r1)
            android.content.res.AssetManager r8 = r8.getAssets()
            java.io.InputStream r8 = r8.open(r0)
            java.lang.String r0 = "DynamicSdk.getEngine().c\u2026ssets.open(localTempName)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r0)
            com.jd.dynamic.lib.utils.o.f(r8, r7)
            java.io.File r8 = new java.io.File
            r8.<init>(r7)
        L9f:
            return r8
        La0:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            com.jd.dynamic.base.DynamicSdk$Engine r1 = com.jd.dynamic.base.DynamicSdk.getEngine()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            java.lang.String r1 = r1.getLocalResourcePath()
            r0.append(r1)
            java.lang.String r1 = java.io.File.separator
            r0.append(r1)
            r0.append(r7)
            r0.append(r1)
            r0.append(r8)
            java.lang.String r7 = r0.toString()
            return r7
        Lc6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.b.i.a.g(java.lang.String, java.lang.String):java.lang.Object");
    }
}
