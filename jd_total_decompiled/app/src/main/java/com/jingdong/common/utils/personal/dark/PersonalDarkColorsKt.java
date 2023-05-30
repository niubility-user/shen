package com.jingdong.common.utils.personal.dark;

import android.view.View;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0003\u001a\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u0015\u0010\b\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\u0007\u001a\u0015\u0010\t\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\t\u0010\u0007\u00a8\u0006\n"}, d2 = {"Landroid/widget/TextView;", "", "darkC1", "(Landroid/widget/TextView;)Lkotlin/Unit;", "darkC2", "Landroid/view/View;", "darkB1", "(Landroid/view/View;)Lkotlin/Unit;", "darkB2", "darkB3", "personallib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class PersonalDarkColorsKt {
    @Nullable
    public static final Unit darkB1(@Nullable View view) {
        if (view != null) {
            view.setBackgroundColor(PersonalDarkColors.INSTANCE.darkB1(view.getContext()));
            return Unit.INSTANCE;
        }
        return null;
    }

    @Nullable
    public static final Unit darkB2(@Nullable View view) {
        if (view != null) {
            view.setBackgroundColor(PersonalDarkColors.INSTANCE.darkB2(view.getContext()));
            return Unit.INSTANCE;
        }
        return null;
    }

    @Nullable
    public static final Unit darkB3(@Nullable View view) {
        if (view != null) {
            view.setBackgroundColor(PersonalDarkColors.INSTANCE.darkB3(view.getContext()));
            return Unit.INSTANCE;
        }
        return null;
    }

    @Nullable
    public static final Unit darkC1(@Nullable TextView textView) {
        if (textView != null) {
            textView.setTextColor(PersonalDarkColors.INSTANCE.darkC1(textView.getContext()));
            return Unit.INSTANCE;
        }
        return null;
    }

    @Nullable
    public static final Unit darkC2(@Nullable TextView textView) {
        if (textView != null) {
            textView.setTextColor(PersonalDarkColors.INSTANCE.darkC2(textView.getContext()));
            return Unit.INSTANCE;
        }
        return null;
    }
}
