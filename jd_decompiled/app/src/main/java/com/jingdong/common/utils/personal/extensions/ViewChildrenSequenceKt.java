package com.jingdong.common.utils.personal.extensions;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a'\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Landroid/view/ViewGroup;", "Lkotlin/Function1;", "Landroid/view/View;", "", "predicate", "firstChildOrNull", "(Landroid/view/ViewGroup;Lkotlin/jvm/functions/Function1;)Landroid/view/View;", "personallib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class ViewChildrenSequenceKt {
    @Nullable
    public static final View firstChildOrNull(@NotNull ViewGroup viewGroup, @NotNull Function1<? super View, Boolean> function1) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View child = viewGroup.getChildAt(i2);
            Intrinsics.checkExpressionValueIsNotNull(child, "child");
            if (function1.invoke(child).booleanValue()) {
                return child;
            }
        }
        return null;
    }
}
