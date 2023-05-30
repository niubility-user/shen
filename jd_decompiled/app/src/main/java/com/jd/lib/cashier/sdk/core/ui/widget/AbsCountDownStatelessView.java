package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jingdong.amon.router.annotation.AnnoConst;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b+\u0010\u0007B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010-\u001a\u0004\u0018\u00010,\u00a2\u0006\u0004\b+\u0010.B#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010-\u001a\u0004\u0018\u00010,\u0012\u0006\u00100\u001a\u00020/\u00a2\u0006\u0004\b+\u00101J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H&\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH&\u00a2\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH&\u00a2\u0006\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u00020\u00018\u0000@\u0000X\u0080.\u00a2\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\"\u0010\u0019\u001a\u00020\u00018\u0000@\u0000X\u0080.\u00a2\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\"\u0010\u001d\u001a\u00020\u001c8\u0000@\u0000X\u0080.\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\"\u0010#\u001a\u00020\u001c8\u0000@\u0000X\u0080.\u00a2\u0006\u0012\n\u0004\b#\u0010\u001e\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R\"\u0010&\u001a\u00020\u001c8\u0000@\u0000X\u0080.\u00a2\u0006\u0012\n\u0004\b&\u0010\u001e\u001a\u0004\b'\u0010 \"\u0004\b(\u0010\"R\"\u0010\u000f\u001a\u00020\u00018\u0000@\u0000X\u0080.\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0014\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018\u00a8\u00062"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "Landroid/widget/LinearLayout;", "Lcom/jd/lib/cashier/sdk/core/aac/e;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "initialize", "(Landroid/content/Context;)V", "changeTextViewFont", "()V", "", "getCountDownContentDescription", "()Ljava/lang/String;", "Landroid/view/View;", "firstContainer", "secondContainer", "thirdContainer", "layoutStatelessView", "(Landroid/view/View;Landroid/view/View;Landroid/view/View;)V", "hourContainer", "Landroid/widget/LinearLayout;", "getHourContainer$cashier_release", "()Landroid/widget/LinearLayout;", "setHourContainer$cashier_release", "(Landroid/widget/LinearLayout;)V", "minuteContainer", "getMinuteContainer$cashier_release", "setMinuteContainer$cashier_release", "Landroid/widget/TextView;", "hourClockTv", "Landroid/widget/TextView;", "getHourClockTv$cashier_release", "()Landroid/widget/TextView;", "setHourClockTv$cashier_release", "(Landroid/widget/TextView;)V", "minuteClockTv", "getMinuteClockTv$cashier_release", "setMinuteClockTv$cashier_release", "secondClockTv", "getSecondClockTv$cashier_release", "setSecondClockTv$cashier_release", "getSecondContainer$cashier_release", "setSecondContainer$cashier_release", "<init>", "Landroid/util/AttributeSet;", "attributeSet", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public abstract class AbsCountDownStatelessView extends LinearLayout implements com.jd.lib.cashier.sdk.core.aac.e {
    private HashMap _$_findViewCache;
    @NotNull
    public TextView hourClockTv;
    @NotNull
    public LinearLayout hourContainer;
    @NotNull
    public TextView minuteClockTv;
    @NotNull
    public LinearLayout minuteContainer;
    @NotNull
    public TextView secondClockTv;
    @NotNull
    public LinearLayout secondContainer;

    public AbsCountDownStatelessView(@NotNull Context context) {
        super(context);
        initialize(context);
    }

    private final void initialize(Context context) {
        setOrientation(0);
        setGravity(16);
        LayoutInflater from = LayoutInflater.from(context);
        int i2 = R.layout.lib_cashier_sdk_digital_clock_view;
        View hourClockContainer = from.inflate(i2, (ViewGroup) this, false);
        View minuteClockContainer = LayoutInflater.from(context).inflate(i2, (ViewGroup) this, false);
        View secondClockContainer = LayoutInflater.from(context).inflate(i2, (ViewGroup) this, false);
        int i3 = R.id.lib_cashier_count_down_item_container;
        View findViewById = hourClockContainer.findViewById(i3);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "hourClockContainer.findV\u2026ount_down_item_container)");
        this.hourContainer = (LinearLayout) findViewById;
        View findViewById2 = minuteClockContainer.findViewById(i3);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "minuteClockContainer.fin\u2026ount_down_item_container)");
        this.minuteContainer = (LinearLayout) findViewById2;
        View findViewById3 = secondClockContainer.findViewById(i3);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "secondClockContainer.fin\u2026ount_down_item_container)");
        this.secondContainer = (LinearLayout) findViewById3;
        int i4 = R.id.tv_cashier_digital_clock;
        View findViewById4 = hourClockContainer.findViewById(i4);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "hourClockContainer.findV\u2026tv_cashier_digital_clock)");
        this.hourClockTv = (TextView) findViewById4;
        View findViewById5 = minuteClockContainer.findViewById(i4);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "minuteClockContainer.fin\u2026tv_cashier_digital_clock)");
        this.minuteClockTv = (TextView) findViewById5;
        View findViewById6 = secondClockContainer.findViewById(i4);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "secondClockContainer.fin\u2026tv_cashier_digital_clock)");
        this.secondClockTv = (TextView) findViewById6;
        changeTextViewFont();
        Intrinsics.checkExpressionValueIsNotNull(hourClockContainer, "hourClockContainer");
        Intrinsics.checkExpressionValueIsNotNull(minuteClockContainer, "minuteClockContainer");
        Intrinsics.checkExpressionValueIsNotNull(secondClockContainer, "secondClockContainer");
        layoutStatelessView(hourClockContainer, minuteClockContainer, secondClockContainer);
        onChangeSkin();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    public abstract void changeTextViewFont();

    @NotNull
    public abstract String getCountDownContentDescription();

    @NotNull
    public final TextView getHourClockTv$cashier_release() {
        TextView textView = this.hourClockTv;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hourClockTv");
        }
        return textView;
    }

    @NotNull
    public final LinearLayout getHourContainer$cashier_release() {
        LinearLayout linearLayout = this.hourContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hourContainer");
        }
        return linearLayout;
    }

    @NotNull
    public final TextView getMinuteClockTv$cashier_release() {
        TextView textView = this.minuteClockTv;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minuteClockTv");
        }
        return textView;
    }

    @NotNull
    public final LinearLayout getMinuteContainer$cashier_release() {
        LinearLayout linearLayout = this.minuteContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("minuteContainer");
        }
        return linearLayout;
    }

    @NotNull
    public final TextView getSecondClockTv$cashier_release() {
        TextView textView = this.secondClockTv;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondClockTv");
        }
        return textView;
    }

    @NotNull
    public final LinearLayout getSecondContainer$cashier_release() {
        LinearLayout linearLayout = this.secondContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondContainer");
        }
        return linearLayout;
    }

    public abstract void layoutStatelessView(@NotNull View firstContainer, @NotNull View secondContainer, @NotNull View thirdContainer);

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public abstract /* synthetic */ void onChangeSkin();

    public final void setHourClockTv$cashier_release(@NotNull TextView textView) {
        this.hourClockTv = textView;
    }

    public final void setHourContainer$cashier_release(@NotNull LinearLayout linearLayout) {
        this.hourContainer = linearLayout;
    }

    public final void setMinuteClockTv$cashier_release(@NotNull TextView textView) {
        this.minuteClockTv = textView;
    }

    public final void setMinuteContainer$cashier_release(@NotNull LinearLayout linearLayout) {
        this.minuteContainer = linearLayout;
    }

    public final void setSecondClockTv$cashier_release(@NotNull TextView textView) {
        this.secondClockTv = textView;
    }

    public final void setSecondContainer$cashier_release(@NotNull LinearLayout linearLayout) {
        this.secondContainer = linearLayout;
    }

    public AbsCountDownStatelessView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initialize(context);
    }

    public AbsCountDownStatelessView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initialize(context);
    }
}
