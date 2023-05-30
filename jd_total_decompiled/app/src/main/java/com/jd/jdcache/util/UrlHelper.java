package com.jd.jdcache.util;

import android.net.Uri;
import android.text.TextUtils;
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
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String getFileNameFromUrl(@NotNull String str) {
        boolean endsWith$default;
        int lastIndexOf$default;
        endsWith$default = StringsKt__StringsJVMKt.endsWith$default(str, "/", false, 2, null);
        if (endsWith$default || lastIndexOf$default == -1) {
            return null;
        }
        String substring = str.substring(lastIndexOf$default + 1);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return substring;
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
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String urlToKey(@NotNull Uri uri) {
        boolean equals;
        boolean startsWith$default;
        boolean equals2;
        String scheme = uri.getScheme();
        String safeUrlPart = safeUrlPart(uri.getHost());
        equals = StringsKt__StringsJVMKt.equals("https", scheme, true);
        if (!equals) {
            equals2 = StringsKt__StringsJVMKt.equals("http", scheme, true);
        }
        if (!TextUtils.isEmpty(safeUrlPart)) {
            String safeUrlPart2 = safeUrlPart(uri.getPath());
            StringBuilder sb = new StringBuilder();
            sb.append(safeUrlPart);
            if (safeUrlPart2 != null) {
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(safeUrlPart2, "/", false, 2, null);
                if (!startsWith$default) {
                    safeUrlPart2 = '/' + safeUrlPart2;
                }
            }
            safeUrlPart2 = "";
            sb.append((Object) safeUrlPart2);
            return sb.toString();
        }
        String uri2 = uri.toString();
        Intrinsics.checkExpressionValueIsNotNull(uri2, "this.toString()");
        return uri2;
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
