package com.jd.libs.hybrid.requestpreload.dsl;

import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object a(java.lang.String r9, java.lang.String r10) {
        /*
            Method dump skipped, instructions count: 700
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.requestpreload.dsl.DynamicParamInjector.a(java.lang.String, java.lang.String):java.lang.Object");
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
