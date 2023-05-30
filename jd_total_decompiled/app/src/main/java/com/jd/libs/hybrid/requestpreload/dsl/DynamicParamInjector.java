package com.jd.libs.hybrid.requestpreload.dsl;

import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
import com.jd.libs.hybrid.requestpreload.network.RequestCacheManager;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.e;
import com.jingdong.jdsdk.a.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.B;\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\"\u0010+\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020'j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`(\u00a2\u0006\u0004\b,\u0010-J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J!\u0010\b\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ!\u0010\n\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ%\u0010\f\u001a\u0004\u0018\u00010\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00112\u0006\u0010\u0012\u001a\u00028\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0016\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0015R$\u0010\u001d\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000f\u0010\u0015R\"\u0010 \u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010&\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\u001f\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R2\u0010+\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020'j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002`(8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b)\u0010*\u00a8\u0006/"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/dsl/DynamicParamInjector;", "", "", "expr", "c", "(Ljava/lang/String;)Ljava/lang/String;", "value", "defaultValue", a.a, "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "d", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "b", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "", e.a, "(Ljava/lang/Object;)Z", "T", "data", "injectParams", "(Ljava/lang/Object;)Ljava/lang/Object;", "Ljava/lang/String;", "appId", "Lorg/json/JSONObject;", "Lorg/json/JSONObject;", "getParamsDe", "()Lorg/json/JSONObject;", "setParamsDe", "(Lorg/json/JSONObject;)V", "paramsDe", VerifyTracker.KEY_TIMESTAMP, "Z", "isSuccess", "()Z", "setSuccess", "(Z)V", "getNeedCustom", "setNeedCustom", "needCustom", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "f", "Ljava/util/HashMap;", "urlQuery", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/HashMap;)V", "Companion", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class DynamicParamInjector {
    @NotNull
    public static final String EXPR_TYPE_CONVERT_BOOLEAN = "toBoolean()";
    @NotNull
    public static final String EXPR_TYPE_CONVERT_NUMBER = "toNumber()";
    @NotNull
    public static final String EXPR_TYPE_CONVERT_STRING = "toString()";

    /* renamed from: a  reason: from kotlin metadata */
    private boolean isSuccess;

    /* renamed from: b  reason: from kotlin metadata */
    private boolean needCustom;
    @Nullable

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private JSONObject paramsDe;

    /* renamed from: d  reason: from kotlin metadata */
    private final String appId;

    /* renamed from: e  reason: collision with root package name and from kotlin metadata */
    private final String timestamp;

    /* renamed from: f  reason: collision with root package name and from kotlin metadata */
    private final HashMap<String, String> urlQuery;

    public DynamicParamInjector(@NotNull String str, @NotNull String str2, @NotNull HashMap<String, String> hashMap) {
        this.appId = str;
        this.timestamp = str2;
        this.urlQuery = hashMap;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01a5  */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.jd.libs.hybrid.requestpreload.dsl.DynamicParamInjector] */
    /* JADX WARN: Type inference failed for: r9v50, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v51 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Object a(String value, String defaultValue) {
        List split$default;
        split$default = StringsKt__StringsKt.split$default((CharSequence) value, new char[]{OrderISVUtil.MONEY_DECIMAL_CHAR}, false, 0, 6, (Object) null);
        RequestCacheManager requestCacheManager = RequestPreloadSDK.INSTANCE.getInstance().getRequestCacheManager();
        if (split$default == null || !(!split$default.isEmpty()) == true) {
            return defaultValue;
        }
        String str = (String) split$default.get(0);
        switch (str.hashCode()) {
            case -1626927939:
                if (str.equals("$urlQuery")) {
                    if (split$default.size() <= 1 || TextUtils.isEmpty(this.urlQuery.get(split$default.get(1)))) {
                        return defaultValue;
                    }
                    Object b = b(this.urlQuery.get(split$default.get(1)), split$default.size() > 2 ? (String) split$default.get(2) : "");
                    if (b == null) {
                        Intrinsics.throwNpe();
                    }
                    return b;
                }
                value = defaultValue;
                break;
            case -967514448:
                if (str.equals("$osVersion")) {
                    value = d(HybridBase.getInstance().getSetting(HybridSDK.OS_VERSION), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case -248360727:
                if (str.equals("$clientVersion")) {
                    value = d(HybridBase.getInstance().getSetting(HybridSDK.APP_VERSION), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case 1179387:
                if (str.equals("$lat")) {
                    HybridBase hybridBase = HybridBase.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(hybridBase, "HybridBase.getInstance()");
                    value = d(hybridBase.getLat(), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case 1179777:
                if (str.equals("$lng")) {
                    HybridBase hybridBase2 = HybridBase.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(hybridBase2, "HybridBase.getInstance()");
                    value = d(hybridBase2.getLng(), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case 36249265:
                if (str.equals("$area")) {
                    HybridBase hybridBase3 = HybridBase.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(hybridBase3, "HybridBase.getInstance()");
                    value = d(hybridBase3.getArea(), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case 36743574:
                if (str.equals("$repo")) {
                    if (split$default.size() > 1 && split$default.get(1) != null) {
                        CacheItem result = requestCacheManager.getResult(this.appId, (String) split$default.get(1), this.timestamp);
                        if ((result != null ? result.getJsonData() : null) != null) {
                            List subList = split$default.subList(2, split$default.size());
                            Object jsonData = result.getJsonData();
                            Iterator it = subList.iterator();
                            while (it.hasNext()) {
                                Pair<String, Integer> splitKeyAndArrayIndex = CommonUtil.INSTANCE.splitKeyAndArrayIndex((String) it.next());
                                String component1 = splitKeyAndArrayIndex.component1();
                                int intValue = splitKeyAndArrayIndex.component2().intValue();
                                if (jsonData instanceof JSONObject) {
                                    JSONObject jSONObject = (JSONObject) jsonData;
                                    if (jSONObject.has(component1)) {
                                        if (jSONObject.isNull(component1)) {
                                            jsonData = null;
                                        } else {
                                            jsonData = jSONObject.get(component1);
                                            if ((jsonData instanceof JSONArray) && intValue != -1) {
                                                JSONArray jSONArray = (JSONArray) jsonData;
                                                if (jSONArray.length() > intValue && jSONArray.get(intValue) != null) {
                                                    jsonData = jSONArray.get(intValue);
                                                } else if (TextUtils.isEmpty(defaultValue)) {
                                                    this.isSuccess = false;
                                                }
                                            }
                                        }
                                    } else if (TextUtils.isEmpty(defaultValue)) {
                                        this.isSuccess = false;
                                    }
                                    jsonData = defaultValue;
                                } else if ((jsonData instanceof JSONArray) && intValue != -1) {
                                    JSONArray jSONArray2 = (JSONArray) jsonData;
                                    if (jSONArray2.length() > intValue && jSONArray2.get(intValue) != null) {
                                        jsonData = jSONArray2.get(intValue);
                                    } else {
                                        if (TextUtils.isEmpty(defaultValue)) {
                                            this.isSuccess = false;
                                        }
                                        jsonData = defaultValue;
                                    }
                                } else if (e(component1)) {
                                    jsonData = b(jsonData, component1);
                                    if (jsonData == null) {
                                        Intrinsics.throwNpe();
                                    }
                                } else {
                                    if (TextUtils.isEmpty(defaultValue)) {
                                        this.isSuccess = false;
                                    }
                                    jsonData = defaultValue;
                                }
                                if (jsonData == 0 && (jsonData instanceof String)) {
                                    return d((String) jsonData, defaultValue);
                                }
                                return jsonData == 0 ? jsonData : defaultValue;
                            }
                            if (jsonData == 0) {
                            }
                            if (jsonData == 0) {
                            }
                        } else {
                            if ((result != null ? result.getRawData() : null) != null) {
                                if (split$default.size() > 2) {
                                    if (TextUtils.isEmpty(defaultValue)) {
                                        this.isSuccess = false;
                                    }
                                    return defaultValue;
                                }
                                return result.getRawData();
                            }
                        }
                    }
                    return defaultValue;
                }
                value = defaultValue;
                break;
            case 36848095:
                if (str.equals("$uuid")) {
                    value = d(HybridBase.getInstance().getSetting("uuid"), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case 527649007:
                if (str.equals("$client")) {
                    value = d(HybridBase.getInstance().getSetting("client"), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case 536273045:
                if (str.equals("$custom")) {
                    if (split$default.size() <= 1) {
                        return defaultValue;
                    }
                    this.needCustom = true;
                    if (this.paramsDe != null) {
                        value = HybridBase.getInstance().custom((String) split$default.get(1), this.paramsDe);
                        break;
                    }
                }
                value = defaultValue;
                break;
            case 1124744394:
                if (str.equals("$build")) {
                    value = d(HybridBase.getInstance().getSetting(HybridSDK.APP_VERSION_CODE), defaultValue);
                    break;
                }
                value = defaultValue;
                break;
            case 1141190741:
                if (str.equals("$token")) {
                    value = String.valueOf(System.currentTimeMillis());
                    break;
                }
                value = defaultValue;
                break;
            default:
                value = defaultValue;
                break;
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "when(arr[0]) {\n         \u2026efaultValue\n            }");
        return value;
    }

    private final Object b(Object value, Object expr) {
        String obj;
        return Intrinsics.areEqual(expr, EXPR_TYPE_CONVERT_STRING) ? (value == null || (obj = value.toString()) == null) ? DYConstants.DY_NULL_STR : obj : Intrinsics.areEqual(expr, EXPR_TYPE_CONVERT_NUMBER) ? CommonUtil.INSTANCE.parse2Number(value) : Intrinsics.areEqual(expr, EXPR_TYPE_CONVERT_BOOLEAN) ? Boolean.valueOf(CommonUtil.INSTANCE.parse2Boolean(value)) : value;
    }

    private final String c(String expr) {
        String str;
        String replaceFirst$default;
        Pattern compile = Pattern.compile(this.paramsDe == null ? "\"?\\$\\{([^\\}]+)\\}(\"?)" : "\"?(\\$[^\"}]+)(\"?)");
        Matcher matcher = compile.matcher(expr);
        String str2 = expr;
        while (matcher.find()) {
            String whole = matcher.group(0);
            String value = matcher.group(1);
            String group = matcher.group(2);
            List<String> split = value != null ? new Regex("\\s*\\?\\?\\s*").split(value, 0) : null;
            if (split == null || split.size() <= 1) {
                str = "";
            } else {
                value = split.get(0);
                str = split.get(1);
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "value");
            Object a = a(value, str);
            if (!TextUtils.isEmpty(group) && (a instanceof String)) {
                Intrinsics.checkExpressionValueIsNotNull(whole, "whole");
                StringBuilder sb = new StringBuilder();
                sb.append(Typography.quote);
                sb.append(a);
                sb.append(Typography.quote);
                replaceFirst$default = StringsKt__StringsJVMKt.replaceFirst$default(str2, whole, sb.toString(), false, 4, (Object) null);
            } else {
                Intrinsics.checkExpressionValueIsNotNull(whole, "whole");
                replaceFirst$default = StringsKt__StringsJVMKt.replaceFirst$default(str2, whole, a.toString(), false, 4, (Object) null);
            }
            str2 = replaceFirst$default;
            matcher = compile.matcher(str2);
        }
        return str2;
    }

    private final String d(String value, String defaultValue) {
        if (TextUtils.isEmpty(value)) {
            return defaultValue;
        }
        if (value == null) {
            Intrinsics.throwNpe();
        }
        return value;
    }

    private final boolean e(Object expr) {
        return Intrinsics.areEqual(expr, EXPR_TYPE_CONVERT_STRING) || Intrinsics.areEqual(expr, EXPR_TYPE_CONVERT_NUMBER) || Intrinsics.areEqual(expr, EXPR_TYPE_CONVERT_BOOLEAN);
    }

    public final boolean getNeedCustom() {
        return this.needCustom;
    }

    @Nullable
    public final JSONObject getParamsDe() {
        return this.paramsDe;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [org.json.JSONObject] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.LinkedHashMap, java.util.Map] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.jd.libs.hybrid.requestpreload.dsl.DynamicParamInjector] */
    public final <T> T injectParams(T data) {
        ?? jSONObject;
        this.isSuccess = true;
        if (data instanceof JSONArray) {
            jSONObject = new JSONArray();
            JSONArray jSONArray = (JSONArray) data;
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                jSONObject.put(i2, injectParams(jSONArray.get(i2)));
            }
        } else if (data instanceof LinkedHashMap) {
            jSONObject = new LinkedHashMap();
            for (Map.Entry entry : ((Map) data).entrySet()) {
                Object key = entry.getKey();
                Intrinsics.checkExpressionValueIsNotNull(key, "it.key");
                Object injectParams = injectParams(entry.getValue());
                Intrinsics.checkExpressionValueIsNotNull(injectParams, "injectParams(it.value)");
                jSONObject.put(key, injectParams);
            }
        } else if (!(data instanceof JSONObject)) {
            return data instanceof String ? (T) c((String) data) : data;
        } else {
            jSONObject = new JSONObject();
            JSONObject jSONObject2 = (JSONObject) data;
            Iterator<String> keys = jSONObject2.keys();
            Intrinsics.checkExpressionValueIsNotNull(keys, "result.keys()");
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, injectParams(jSONObject2.get(next)));
                jSONObject.put(next, injectParams(jSONObject2.get(next)));
            }
        }
        return jSONObject;
    }

    /* renamed from: isSuccess  reason: from getter */
    public final boolean getIsSuccess() {
        return this.isSuccess;
    }

    public final void setNeedCustom(boolean z) {
        this.needCustom = z;
    }

    public final void setParamsDe(@Nullable JSONObject jSONObject) {
        this.paramsDe = jSONObject;
    }

    public final void setSuccess(boolean z) {
        this.isSuccess = z;
    }
}
