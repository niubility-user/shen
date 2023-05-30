package com.jd.dynamic.b.i;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jd.dynamic.base.DynamicMtaUtil;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.ResultEntity;
import com.jd.dynamic.lib.dynamic.parser.NewDynamicXParser;
import com.jd.dynamic.lib.utils.o;
import com.jd.dynamic.lib.utils.t;
import java.io.File;
import java.io.InputStream;
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

    /* JADX WARN: Removed duplicated region for block: B:167:0x00a5 A[Catch: Exception -> 0x00e4, TryCatch #0 {Exception -> 0x00e4, blocks: (B:156:0x007d, B:158:0x0097, B:167:0x00a5, B:169:0x00a9, B:171:0x00c2, B:174:0x00c9, B:175:0x00cc, B:177:0x00d0, B:178:0x00d3), top: B:184:0x007d }] */
    @JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final boolean e(@Nullable String str, @Nullable String str2) {
        boolean z;
        int i2;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String dynamicStatus = DynamicSdk.getDynamicStatus();
            if (!TextUtils.equals(dynamicStatus, "0")) {
                com.jd.dynamic.b.d.a g2 = com.jd.dynamic.b.d.a.g(str);
                if (!TextUtils.equals(g2.b, "0") || g2.b(str2) || g2.e(str2)) {
                    DynamicSdk.Engine engine = DynamicSdk.getEngine();
                    if (!TextUtils.isEmpty(engine != null ? engine.getLocalResourcePath() : null)) {
                        Map<String, String> map = a;
                        if (map == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.Map<K, *>");
                        }
                        if (map.containsKey(str2)) {
                            return true;
                        }
                        StringBuilder sb = new StringBuilder();
                        DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
                        Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
                        sb.append(engine2.getLocalResourcePath());
                        sb.append(File.separator);
                        sb.append(str);
                        String sb2 = sb.toString();
                        try {
                            DynamicSdk.Engine engine3 = DynamicSdk.getEngine();
                            Intrinsics.checkExpressionValueIsNotNull(engine3, "DynamicSdk.getEngine()");
                            Context context = engine3.getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
                            String[] list = context.getAssets().list(sb2);
                            if (list != null) {
                                if (!(list.length == 0)) {
                                    z = false;
                                    if (!z) {
                                        int length = list.length;
                                        while (i2 < length) {
                                            String file = list[i2];
                                            StringBuilder sb3 = new StringBuilder();
                                            sb3.append(str2);
                                            sb3.append(".zip");
                                            i2 = (Intrinsics.areEqual(file, sb3.toString()) || Intrinsics.areEqual(file, str2)) ? 0 : i2 + 1;
                                            Map<String, String> map2 = a;
                                            if (str2 == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            Intrinsics.checkExpressionValueIsNotNull(file, "file");
                                            map2.put(str2, file);
                                            return true;
                                        }
                                    }
                                }
                            }
                            z = true;
                            if (!z) {
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            DynamicMtaUtil.uploadDowngradeTemplateMta(str, str2, "", dynamicStatus, null, null);
            return false;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0079, code lost:
        if ((r2.length == 0) != false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x007c, code lost:
        if (r4 == false) goto L66;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final ResultEntity f(String str, String str2, int i2, String str3, boolean z) {
        try {
            StringBuilder sb = new StringBuilder();
            DynamicSdk.Engine engine = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
            sb.append(engine.getLocalResourcePath());
            String str4 = File.separator;
            sb.append(str4);
            sb.append(str);
            sb.append(str4);
            sb.append(str2);
            sb.append(".zip");
            String sb2 = sb.toString();
            boolean z2 = false;
            t.g("LocalTemplateManager", "loadZipLocalFile", "bizField is " + str2 + ",path is " + sb2);
            String h2 = h(str, str2);
            File file = new File(h2);
            if (file.exists() && file.isDirectory()) {
                String[] list = file.list();
                if (list != null) {
                }
                z2 = true;
            }
            DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
            Context context = engine2.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
            InputStream open = context.getAssets().open(sb2);
            Intrinsics.checkExpressionValueIsNotNull(open, "DynamicSdk.getEngine().c\u2026ssets.open(localTempName)");
            o.f(open, h2);
            return c(str, str2, h2, str3, z);
        } catch (Exception e2) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_LOCAL_BACKUP_ERROR, "\u52a0\u8f7d\u515c\u5e95\u6587\u4ef6\u5931\u8d25", str2, str, i2, e2);
            return null;
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:83:0x0074, code lost:
        if ((r3.length == 0) != false) goto L84;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object g(@NotNull String str, @NotNull String str2) {
        boolean endsWith$default;
        if (!a.containsKey(str2)) {
            e(str, str2);
        }
        if (a.containsKey(str2)) {
            String str3 = a.get(str2);
            if (str3 != null) {
                boolean z = false;
                endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str3, ".zip", false, 2, null);
                if (true == endsWith$default) {
                    StringBuilder sb = new StringBuilder();
                    DynamicSdk.Engine engine = DynamicSdk.getEngine();
                    Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
                    sb.append(engine.getLocalResourcePath());
                    String str4 = File.separator;
                    sb.append(str4);
                    sb.append(str);
                    sb.append(str4);
                    sb.append(str2);
                    sb.append(".zip");
                    String sb2 = sb.toString();
                    String h2 = h(str, str2);
                    File file = new File(h2);
                    if (file.exists() && file.isDirectory()) {
                        String[] list = file.list();
                        if (list != null) {
                        }
                        z = true;
                        if (!z) {
                            return file;
                        }
                    }
                    DynamicSdk.Engine engine2 = DynamicSdk.getEngine();
                    Intrinsics.checkExpressionValueIsNotNull(engine2, "DynamicSdk.getEngine()");
                    Context context = engine2.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "DynamicSdk.getEngine().context");
                    InputStream open = context.getAssets().open(sb2);
                    Intrinsics.checkExpressionValueIsNotNull(open, "DynamicSdk.getEngine().c\u2026ssets.open(localTempName)");
                    o.f(open, h2);
                    return new File(h2);
                }
            }
            StringBuilder sb3 = new StringBuilder();
            DynamicSdk.Engine engine3 = DynamicSdk.getEngine();
            Intrinsics.checkExpressionValueIsNotNull(engine3, "DynamicSdk.getEngine()");
            sb3.append(engine3.getLocalResourcePath());
            String str5 = File.separator;
            sb3.append(str5);
            sb3.append(str);
            sb3.append(str5);
            sb3.append(str2);
            return sb3.toString();
        }
        return null;
    }
}
