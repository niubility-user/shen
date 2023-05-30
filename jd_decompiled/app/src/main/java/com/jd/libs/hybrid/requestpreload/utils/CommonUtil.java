package com.jd.libs.hybrid.requestpreload.utils;

import android.net.Uri;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b*\u0010+J\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\bJ1\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\nj\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`\u000b2\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0015\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000e\u0010\bJ\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0006J\u0015\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0011\u0010\bJ\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0015J!\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0016\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ5\u0010$\u001a\u00020#2&\u0010\"\u001a\"\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010 j\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u0001`!\u00a2\u0006\u0004\b$\u0010%J\u0015\u0010(\u001a\u00020'2\u0006\u0010&\u001a\u00020\u0002\u00a2\u0006\u0004\b(\u0010)\u00a8\u0006,"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/utils/CommonUtil;", "", "", "url", "", "isUrl", "(Ljava/lang/String;)Z", "getUrlWithoutQuery", "(Ljava/lang/String;)Ljava/lang/String;", "getUrlWithoutPath", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getQueryMap", "(Ljava/lang/String;)Ljava/util/HashMap;", "completeUrlScheme", "isUrlWithScheme", "functionId", "createUrlWithFunctionId", "expStr", "", "findDependencyIds", "(Ljava/lang/String;)Ljava/util/List;", "value", "Lkotlin/Pair;", "", "splitKeyAndArrayIndex", "(Ljava/lang/String;)Lkotlin/Pair;", "parse2Boolean", "(Ljava/lang/Object;)Z", "", "parse2Number", "(Ljava/lang/Object;)Ljava/lang/Number;", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "paramsMap", "Lorg/json/JSONObject;", "getJsonParams", "(Ljava/util/LinkedHashMap;)Lorg/json/JSONObject;", "msg", "", "logX", "(Ljava/lang/String;)V", "<init>", "()V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class CommonUtil {
    public static final CommonUtil INSTANCE = new CommonUtil();

    private CommonUtil() {
    }

    @NotNull
    public final String completeUrlScheme(@NotNull String url) {
        if (isUrlWithScheme(url)) {
            return url;
        }
        return "https://" + url;
    }

    @NotNull
    public final String createUrlWithFunctionId(@NotNull String functionId) {
        return "https://api.m.jd.com/client.action?functionId=" + functionId;
    }

    @NotNull
    public final List<String> findDependencyIds(@NotNull String expStr) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile("\\$\\{\\$repo\\.([^\\.]+)\\..+\\}").matcher(expStr);
        while (matcher.find()) {
            String group = matcher.group(1);
            if (group != null) {
                arrayList.add(group);
            }
        }
        return arrayList;
    }

    @NotNull
    public final JSONObject getJsonParams(@Nullable LinkedHashMap<String, String> paramsMap) {
        JSONObject jSONObject = new JSONObject();
        if (paramsMap != null) {
            for (String str : paramsMap.keySet()) {
                jSONObject.put(str, paramsMap.get(str));
            }
        }
        return jSONObject;
    }

    @NotNull
    public final HashMap<String, String> getQueryMap(@NotNull String url) {
        Uri uri = Uri.parse(url);
        HashMap<String, String> hashMap = new HashMap<>();
        Intrinsics.checkExpressionValueIsNotNull(uri, "uri");
        Set<String> names = uri.getQueryParameterNames();
        Intrinsics.checkExpressionValueIsNotNull(names, "names");
        for (String str : names) {
            String queryParameter = uri.getQueryParameter(str);
            if (queryParameter != null) {
                hashMap.put(str, queryParameter);
            }
        }
        return hashMap;
    }

    @Nullable
    public final String getUrlWithoutPath(@NotNull String url) {
        String sb;
        if (isUrl(url)) {
            Uri uri = Uri.parse(completeUrlScheme(url));
            Intrinsics.checkExpressionValueIsNotNull(uri, "uri");
            if (uri.getPort() == -1) {
                sb = "";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(':');
                sb2.append(uri.getPort());
                sb = sb2.toString();
            }
            return uri.getScheme() + "://" + uri.getHost() + sb;
        }
        return url;
    }

    @NotNull
    public final String getUrlWithoutQuery(@NotNull String url) {
        String sb;
        if (isUrl(url)) {
            Uri uri = Uri.parse(completeUrlScheme(url));
            Intrinsics.checkExpressionValueIsNotNull(uri, "uri");
            if (uri.getPort() == -1) {
                sb = "";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(':');
                sb2.append(uri.getPort());
                sb = sb2.toString();
            }
            return uri.getScheme() + "://" + uri.getHost() + sb + uri.getPath();
        }
        return url;
    }

    public final boolean isUrl(@Nullable String url) {
        int indexOf$default;
        if (url == null) {
            return false;
        }
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) url, (char) OrderISVUtil.MONEY_DECIMAL_CHAR, 0, false, 6, (Object) null);
        return indexOf$default != -1;
    }

    public final boolean isUrlWithScheme(@NotNull String url) {
        boolean startsWith;
        boolean startsWith2;
        startsWith = StringsKt__StringsJVMKt.startsWith(url, "https", false);
        if (!startsWith) {
            startsWith2 = StringsKt__StringsJVMKt.startsWith(url, "http", false);
            if (!startsWith2) {
                return false;
            }
        }
        return true;
    }

    public final void logX(@NotNull String msg) {
        if (Log.isDebug()) {
            Log.xLogD(RequestPreloadSDK.TAG, "\u65b0\u7248\u63a5\u53e3\u9884\u52a0\u8f7d\uff1a" + msg);
        }
    }

    public final boolean parse2Boolean(@Nullable Object value) {
        boolean areEqual;
        if (value == null) {
            return false;
        }
        if (value instanceof Integer) {
            areEqual = Intrinsics.areEqual(value, (Object) 0);
        } else if (!(value instanceof String)) {
            if (value instanceof Boolean) {
                return ((Boolean) value).booleanValue();
            }
            return true;
        } else {
            areEqual = Intrinsics.areEqual(value, "");
        }
        return true ^ areEqual;
    }

    @NotNull
    public final Number parse2Number(@Nullable Object value) {
        BigDecimal bigDecimalOrNull;
        if (value == null) {
            return 0;
        }
        if (value instanceof Number) {
            return (Number) value;
        }
        if (!(value instanceof String)) {
            if (value instanceof Boolean) {
                return Integer.valueOf(((Boolean) value).booleanValue() ? 1 : 0);
            }
            return 0;
        }
        bigDecimalOrNull = StringsKt__StringNumberConversionsJVMKt.toBigDecimalOrNull((String) value);
        if (bigDecimalOrNull != null) {
            return bigDecimalOrNull;
        }
        return 0;
    }

    @NotNull
    public final Pair<String, Integer> splitKeyAndArrayIndex(@NotNull String value) {
        String str;
        Matcher matcher = Pattern.compile("([^\\[\\]]+)(\\[(\\d+)\\])?").matcher(value);
        int i2 = -1;
        if (matcher.find()) {
            str = matcher.group(1);
            Intrinsics.checkExpressionValueIsNotNull(str, "matcher.group(1)");
            try {
                i2 = Integer.parseInt(matcher.group(3));
            } catch (Exception unused) {
            }
        } else {
            str = "";
        }
        return TuplesKt.to(str, Integer.valueOf(i2));
    }
}
