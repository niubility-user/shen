package com.jd.libs.hybrid.requestpreload;

import com.jd.libs.hybrid.requestpreload.dsl.DynamicParamInjector;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u001a\r\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\u001f\u0010\u0006\u001a\u00020\u0000\"\b\b\u0000\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"", "main", "()V", "", "T", "value", "println", "(Ljava/lang/Object;)V", "request-preload_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class TestKt {
    public static final void main() {
        CommonUtil commonUtil = CommonUtil.INSTANCE;
        println(commonUtil.splitKeyAndArrayIndex("abc"));
        println(commonUtil.splitKeyAndArrayIndex(DynamicParamInjector.EXPR_TYPE_CONVERT_NUMBER));
        println(commonUtil.splitKeyAndArrayIndex("abc[1]"));
        println(Boolean.valueOf(commonUtil.parse2Boolean("0")));
        println(Boolean.valueOf(commonUtil.parse2Boolean("abc")));
        println(Boolean.valueOf(commonUtil.parse2Boolean("")));
        Boolean bool = Boolean.TRUE;
        println(Boolean.valueOf(commonUtil.parse2Boolean(bool)));
        Boolean bool2 = Boolean.FALSE;
        println(Boolean.valueOf(commonUtil.parse2Boolean(bool2)));
        println(Boolean.valueOf(commonUtil.parse2Boolean(0)));
        println(Boolean.valueOf(commonUtil.parse2Boolean(1)));
        println(commonUtil.parse2Number("500000000000000000000000000000000"));
        println(commonUtil.parse2Number(50000000));
        println(commonUtil.parse2Number(bool));
        println(commonUtil.parse2Number(bool2));
    }

    public static final <T> void println(@NotNull T t) {
        System.out.println((Object) (t + ", " + t.getClass()));
    }
}
