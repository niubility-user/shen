package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.HashMap;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0017\u0010\u0018B\u001b\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0004\b\u0017\u0010\u001bB#\b\u0016\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u00a2\u0006\u0004\b\u0017\u0010\u001eJ'\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ'\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u000f\u0010\u000b\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0002\u00a2\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J'\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0013\u0010\bJ\u000f\u0010\u0014\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u000f\u00a8\u0006\u001f"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/MSCountDownStatelessView;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountDownStatelessView;", "Landroid/view/View;", "firstContainer", "secondContainer", "thirdContainer", "", "onReSize", "(Landroid/view/View;Landroid/view/View;Landroid/view/View;)V", "onReLayout", "Landroid/widget/TextView;", "generateSplitView", "()Landroid/widget/TextView;", "generateDotView", "changeTextViewFont", "()V", "", "getCountDownContentDescription", "()Ljava/lang/String;", "layoutStatelessView", "onChangeSkin", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attributeSet", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class MSCountDownStatelessView extends AbsCountDownStatelessView {
    private HashMap _$_findViewCache;

    public MSCountDownStatelessView(@NotNull Context context) {
        super(context);
    }

    private final TextView generateDotView() {
        TextView textView = new TextView(super.getContext());
        textView.setText(OrderISVUtil.MONEY_DECIMAL);
        textView.setTextSize(10.0f);
        textView.setTextColor(Color.parseColor("#FC8075"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        layoutParams.leftMargin = DpiUtil.dip2px(textView.getContext(), 2.0f);
        layoutParams.rightMargin = DpiUtil.dip2px(textView.getContext(), 2.0f);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private final TextView generateSplitView() {
        TextView textView = new TextView(super.getContext());
        textView.setText(":");
        textView.setTextSize(12.0f);
        textView.setTextColor(Color.parseColor("#FC8075"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        layoutParams.bottomMargin = DpiUtil.dip2px(textView.getContext(), 1.0f);
        layoutParams.leftMargin = DpiUtil.dip2px(textView.getContext(), 2.0f);
        layoutParams.rightMargin = DpiUtil.dip2px(textView.getContext(), 2.0f);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private final void onReLayout(View firstContainer, View secondContainer, View thirdContainer) {
        addView(firstContainer);
        addView(generateSplitView());
        addView(secondContainer);
        addView(generateDotView());
        addView(thirdContainer);
    }

    private final void onReSize(View firstContainer, View secondContainer, View thirdContainer) {
        firstContainer.setMinimumHeight(DpiUtil.dip2px(getContext(), 14.0f));
        firstContainer.setMinimumWidth(DpiUtil.dip2px(getContext(), 18.0f));
        secondContainer.setMinimumHeight(DpiUtil.dip2px(getContext(), 14.0f));
        secondContainer.setMinimumWidth(DpiUtil.dip2px(getContext(), 18.0f));
        thirdContainer.setMinimumHeight(DpiUtil.dip2px(getContext(), 14.0f));
        thirdContainer.setMinimumWidth(DpiUtil.dip2px(getContext(), 18.0f));
        getHourClockTv$cashier_release().setTextSize(12.0f);
        getMinuteClockTv$cashier_release().setTextSize(12.0f);
        getSecondClockTv$cashier_release().setTextSize(12.0f);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountDownStatelessView
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountDownStatelessView
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

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountDownStatelessView
    public void changeTextViewFont() {
        m0.a(getHourClockTv$cashier_release(), (byte) 3);
        m0.a(getMinuteClockTv$cashier_release(), (byte) 3);
        m0.a(getSecondClockTv$cashier_release(), (byte) 3);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountDownStatelessView
    @NotNull
    public String getCountDownContentDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHourClockTv$cashier_release().getContentDescription());
        sb.append('\u5206');
        sb.append(getMinuteClockTv$cashier_release().getContentDescription());
        sb.append('\u79d2');
        return sb.toString();
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountDownStatelessView
    public void layoutStatelessView(@NotNull View firstContainer, @NotNull View secondContainer, @NotNull View thirdContainer) {
        onReSize(firstContainer, secondContainer, thirdContainer);
        onReLayout(firstContainer, secondContainer, thirdContainer);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountDownStatelessView, com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        int i2 = JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_bg_digital_clock_ten_billion_view_dark : R.drawable.lib_cashier_sdk_bg_digital_clock_ten_billion_view;
        getHourContainer$cashier_release().setBackgroundResource(i2);
        getMinuteContainer$cashier_release().setBackgroundResource(i2);
        getSecondContainer$cashier_release().setBackgroundResource(i2);
        JDDarkUtil.isDarkMode();
        getHourClockTv$cashier_release().setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        getMinuteClockTv$cashier_release().setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
        getSecondClockTv$cashier_release().setTextColor(Color.parseColor(JDDarkUtil.COLOR_FFFFFFF));
    }

    public MSCountDownStatelessView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MSCountDownStatelessView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
