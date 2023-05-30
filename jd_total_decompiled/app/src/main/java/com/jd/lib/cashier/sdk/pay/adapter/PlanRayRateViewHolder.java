package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.PlanRowEntity;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010#\u001a\u00020\u0011\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0004J\u0015\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007\u00a2\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0007\u00a2\u0006\u0004\b\f\u0010\nR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\u000eR\u001e\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\u000eR\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u001aR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u001aR\u001e\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010\"\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u000e\u00a8\u0006&"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/PlanRayRateViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", com.jingdong.app.mall.e.a, "()V", "d", "f", "Lcom/jd/lib/cashier/sdk/core/model/PlanRowEntity;", "planPayRateEntity", "b", "(Lcom/jd/lib/cashier/sdk/core/model/PlanRowEntity;)V", "payRateEntity", "c", "Landroid/widget/TextView;", "Landroid/widget/TextView;", "columnTv1", "", "Landroid/view/View;", "i", "[Landroid/view/View;", "lineArray", "Landroid/view/ViewGroup;", com.jingdong.jdsdk.a.a.a, "Landroid/view/ViewGroup;", "containerColumn", "columnTv0", "Landroid/view/View;", "lineVertical0", "g", "lineHorizontal", "lineVertical1", JshopConst.JSHOP_PROMOTIO_H, "[Landroid/widget/TextView;", "columnTextArray", "columnTv2", "rateItemView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class PlanRayRateViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: from kotlin metadata */
    private final ViewGroup containerColumn;

    /* renamed from: b  reason: from kotlin metadata */
    private final TextView columnTv0;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final TextView columnTv1;

    /* renamed from: d  reason: from kotlin metadata */
    private final TextView columnTv2;

    /* renamed from: e  reason: collision with root package name and from kotlin metadata */
    private final View lineVertical0;

    /* renamed from: f  reason: collision with root package name and from kotlin metadata */
    private final View lineVertical1;

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private final View lineHorizontal;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private final TextView[] columnTextArray;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private final View[] lineArray;

    public PlanRayRateViewHolder(@NotNull View view) {
        super(view);
        this.containerColumn = (ViewGroup) view.findViewById(R.id.container_lib_cashier_dialog_plan_rate_item);
        TextView textView = (TextView) view.findViewById(R.id.tv_lib_cashier_dialog_plan_rate_item_column0);
        this.columnTv0 = textView;
        TextView textView2 = (TextView) view.findViewById(R.id.tv_lib_cashier_dialog_plan_rate_item_column1);
        this.columnTv1 = textView2;
        TextView textView3 = (TextView) view.findViewById(R.id.tv_lib_cashier_dialog_plan_rate_item_column2);
        this.columnTv2 = textView3;
        View findViewById = view.findViewById(R.id.line_lib_cashier_dialog_plan_rate_item_vertical0);
        this.lineVertical0 = findViewById;
        View findViewById2 = view.findViewById(R.id.line_lib_cashier_dialog_plan_rate_item_vertical1);
        this.lineVertical1 = findViewById2;
        View findViewById3 = view.findViewById(R.id.line_lib_cashier_dialog_plan_rate_item_horizontal);
        this.lineHorizontal = findViewById3;
        this.columnTextArray = new TextView[]{textView, textView2, textView3};
        this.lineArray = new View[]{findViewById, findViewById2, findViewById3};
    }

    private final void d() {
        ViewGroup viewGroup = this.containerColumn;
        if (viewGroup != null) {
            viewGroup.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_dialog_plan_rate_item_cell_bg_dark : R.drawable.lib_cashier_sdk_dialog_plan_rate_item_cell_bg);
        }
    }

    private final void e() {
        ViewGroup viewGroup = this.containerColumn;
        if (viewGroup != null) {
            viewGroup.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_dialog_plan_rate_item_header_bg_dark : R.drawable.lib_cashier_sdk_dialog_plan_rate_item_header_bg);
        }
    }

    private final void f() {
        ViewGroup viewGroup = this.containerColumn;
        if (viewGroup != null) {
            viewGroup.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_dialog_plan_rate_item_tail_bg_dark : R.drawable.lib_cashier_sdk_dialog_plan_rate_item_tail_bg);
        }
    }

    public final void b(@NotNull PlanRowEntity planPayRateEntity) {
        String[] strArr = {planPayRateEntity.column0, planPayRateEntity.column1, planPayRateEntity.column2};
        for (int i2 = 0; i2 < 3; i2++) {
            TextView textView = this.columnTextArray[i2];
            if (textView != null) {
                String str = strArr[i2];
                if (str == null) {
                    str = "";
                }
                textView.setText(str);
            }
        }
    }

    public final void c(@NotNull PlanRowEntity payRateEntity) {
        PlanRowEntity.TYPE type = payRateEntity.type;
        if (type == null) {
            type = PlanRowEntity.TYPE.CELL;
        }
        int i2 = f.$EnumSwitchMapping$0[type.ordinal()];
        if (i2 == 1) {
            e();
        } else if (i2 == 2) {
            d();
        } else if (i2 == 3) {
            f();
        }
        for (TextView textView : this.columnTextArray) {
            if (textView != null) {
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            }
        }
        for (View view : this.lineArray) {
            if (view != null) {
                view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
        }
    }
}
