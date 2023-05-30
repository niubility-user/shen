package com.jd.jdcache.util;

import android.net.Uri;
import androidx.annotation.Keep;
import com.jd.dynamic.DYConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010 \n\u0002\b\u000e\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u0004\u001a\u00020\u0003*\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0004\u001a\u00020\u0003*\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0006J\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003*\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0007\u0010\u0006J\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003*\u00020\u0003\u00a2\u0006\u0004\b\b\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\n*\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ9\u0010\u000f\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r*\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000e\u0018\u00010\r\u00a2\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0012R\u0016\u0010\u0015\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0012R\u0016\u0010\u0016\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0012R\u0016\u0010\u0017\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0012R\u0016\u0010\u0018\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0012R\u0016\u0010\u0019\u001a\u00020\u00038\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u0012\u00a8\u0006\u001c"}, d2 = {"Lcom/jd/jdcache/util/UrlHelper;", "", "Landroid/net/Uri;", "", "urlToKey", "(Landroid/net/Uri;)Ljava/lang/String;", "(Ljava/lang/String;)Ljava/lang/String;", "safeUrlPart", "getFileNameFromUrl", "other", "", "matchHostPath", "(Landroid/net/Uri;Landroid/net/Uri;)Z", "", "", "convertHeader", "(Ljava/util/Map;)Ljava/util/Map;", "METHOD_GET", "Ljava/lang/String;", "METHOD_POST", "METHOD_PATCH", "METHOD_PUT", "METHOD_DELETE", "METHOD_OPTIONS", "METHOD_HEAD", "METHOD_TRACE", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class UrlHelper {
    public static final UrlHelper INSTANCE = new UrlHelper();
    @NotNull
    public static final String METHOD_DELETE = "DELETE";
    @NotNull
    public static final String METHOD_GET = "GET";
    @NotNull
    public static final String METHOD_HEAD = "HEAD";
    @NotNull
    public static final String METHOD_OPTIONS = "OPTIONS";
    @NotNull
    public static final String METHOD_PATCH = "PATCH";
    @NotNull
    public static final String METHOD_POST = "POST";
    @NotNull
    public static final String METHOD_PUT = "PUT";
    @NotNull
    public static final String METHOD_TRACE = "TRACE";

    private UrlHelper() {
    }

    @Nullable
    public final Map<String, String> convertHeader(@Nullable Map<String, ? extends List<String>> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<String, ? extends List<String>> entry : map.entrySet()) {
            StringBuilder sb = new StringBuilder();
            for (String str : entry.getValue()) {
                if (str != null) {
                    if (sb.length() > 0) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                    sb.append(str);
                }
            }
            String key = entry.getKey();
            String sb2 = sb.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb2, "combinedValue.toString()");
            hashMap.put(key, sb2);
        }
        return hashMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
        r0 = kotlin.text.StringsKt__StringsKt.lastIndexOf$default((java.lang.CharSequence) r11, "/", 0, false, 6, (java.lang.Object) null);
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getFileNameFromUrl(@org.jetbrains.annotations.NotNull java.lang.String r11) {
        /*
            r10 = this;
            java.lang.String r0 = "/"
            r1 = 0
            r2 = 2
            r3 = 0
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r11, r0, r1, r2, r3)
            if (r0 != 0) goto L25
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            java.lang.String r5 = "/"
            r4 = r11
            int r0 = kotlin.text.StringsKt.lastIndexOf$default(r4, r5, r6, r7, r8, r9)
            r1 = -1
            if (r0 == r1) goto L25
            int r0 = r0 + 1
            java.lang.String r11 = r11.substring(r0)
            java.lang.String r0 = "(this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r0)
            return r11
        L25:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.util.UrlHelper.getFileNameFromUrl(java.lang.String):java.lang.String");
    }

    public final boolean matchHostPath(@Nullable Uri uri, @Nullable Uri uri2) {
        boolean equals;
        boolean equals2;
        equals = StringsKt__StringsJVMKt.equals(safeUrlPart(uri != null ? uri.getHost() : null), safeUrlPart(uri2 != null ? uri2.getHost() : null), true);
        if (equals) {
            equals2 = StringsKt__StringsJVMKt.equals(safeUrlPart(uri != null ? uri.getPath() : null), safeUrlPart(uri2 != null ? uri2.getPath() : null), false);
            if (equals2) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public final String safeUrlPart(@Nullable String str) {
        CharSequence trimStart;
        int lastIndex;
        String str2;
        if (str != null) {
            if (str != null) {
                trimStart = StringsKt__StringsKt.trimStart((CharSequence) str);
                String obj = trimStart.toString();
                if (obj != null) {
                    lastIndex = StringsKt__StringsKt.getLastIndex(obj);
                    while (true) {
                        if (lastIndex < 0) {
                            str2 = "";
                            break;
                        }
                        char charAt = obj.charAt(lastIndex);
                        if (!(charAt == ' ' || charAt == '/')) {
                            str2 = obj.substring(0, lastIndex + 1);
                            Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                            break;
                        }
                        lastIndex--;
                    }
                    return str2 != null ? str2 : str;
                }
                return str;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0052, code lost:
        if (r6 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001b, code lost:
        if (r0 != false) goto L6;
     */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String urlToKey(@org.jetbrains.annotations.NotNull android.net.Uri r6) {
        /*
            r5 = this;
            java.lang.String r0 = r6.getScheme()
            java.lang.String r1 = r6.getHost()
            java.lang.String r1 = r5.safeUrlPart(r1)
            java.lang.String r2 = "https"
            r3 = 1
            boolean r2 = kotlin.text.StringsKt.equals(r2, r0, r3)
            if (r2 != 0) goto L1d
            java.lang.String r2 = "http"
            boolean r0 = kotlin.text.StringsKt.equals(r2, r0, r3)
            if (r0 == 0) goto L5f
        L1d:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L5f
            java.lang.String r6 = r6.getPath()
            java.lang.String r6 = r5.safeUrlPart(r6)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r1)
            if (r6 == 0) goto L55
            r1 = 0
            r2 = 2
            r3 = 0
            java.lang.String r4 = "/"
            boolean r1 = kotlin.text.StringsKt.startsWith$default(r6, r4, r1, r2, r3)
            if (r1 == 0) goto L41
            goto L52
        L41:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 47
            r1.append(r2)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
        L52:
            if (r6 == 0) goto L55
            goto L57
        L55:
            java.lang.String r6 = ""
        L57:
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            goto L68
        L5f:
            java.lang.String r6 = r6.toString()
            java.lang.String r0 = "this.toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r0)
        L68:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.util.UrlHelper.urlToKey(android.net.Uri):java.lang.String");
    }

    @NotNull
    public final String urlToKey(@NotNull String str) {
        try {
            Uri parse = Uri.parse(str);
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(this)");
            return urlToKey(parse);
        } catch (Exception unused) {
            return str;
        }
    }
}
