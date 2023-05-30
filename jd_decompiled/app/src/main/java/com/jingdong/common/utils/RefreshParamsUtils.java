package com.jingdong.common.utils;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\r\u0010\nR*\u0010\u0003\u001a\u0004\u0018\u00010\u00028F@\u0006X\u0087\u000e\u00a2\u0006\u0018\n\u0004\b\u0003\u0010\u0004\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0004R\u0016\u0010\f\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0004\u00a8\u0006\u000e"}, d2 = {"Lcom/jingdong/common/utils/RefreshParamsUtils;", "", "", "commonRefresh", "Ljava/lang/String;", "getCommonRefresh", "()Ljava/lang/String;", "setCommonRefresh", "(Ljava/lang/String;)V", "commonRefresh$annotations", "()V", "REFRESH", "NOT_REFRESH", "<init>", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class RefreshParamsUtils {
    @NotNull
    public static final String NOT_REFRESH = "0";
    @NotNull
    public static final String REFRESH = "1";
    public static final RefreshParamsUtils INSTANCE = new RefreshParamsUtils();
    @Nullable
    private static String commonRefresh = "1";

    private RefreshParamsUtils() {
    }

    @JvmStatic
    public static /* synthetic */ void commonRefresh$annotations() {
    }

    @Nullable
    public static final String getCommonRefresh() {
        if (Intrinsics.areEqual(commonRefresh, "1")) {
            commonRefresh = "0";
            return "1";
        }
        return commonRefresh;
    }

    public static final void setCommonRefresh(@Nullable String str) {
        commonRefresh = str;
    }
}
