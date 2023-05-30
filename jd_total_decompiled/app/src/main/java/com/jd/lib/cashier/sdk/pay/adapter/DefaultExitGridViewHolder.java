package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J!\u0010\n\u001a\u00020\u00042\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\f\u0010\u0006R\u001e\u0010\u0010\u001a\n \u000e*\u0004\u0018\u00010\r0\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u000fR\u001e\u0010\u0013\u001a\n \u000e*\u0004\u0018\u00010\u00110\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0012R\u001e\u0010\u0015\u001a\n \u000e*\u0004\u0018\u00010\r0\r8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u000fR\u001e\u0010\u0016\u001a\n \u000e*\u0004\u0018\u00010\u00110\u00118\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0012\u00a8\u0006\u001b"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/DefaultExitGridViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/jd/lib/cashier/sdk/pay/dialog/k/e/e;", "exitRetainOptionRow", "", "b", "(Lcom/jd/lib/cashier/sdk/pay/dialog/k/e/e;)V", "Lkotlin/Function1;", "", "onClickListener", "d", "(Lkotlin/jvm/functions/Function1;)V", "c", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "Landroid/widget/TextView;", "itemTitleTv2", "Landroid/view/ViewGroup;", "Landroid/view/ViewGroup;", "itemViewContainer1", com.jingdong.jdsdk.a.a.a, "itemTitleTv1", "itemViewContainer2", "Landroid/view/View;", "gridItemView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class DefaultExitGridViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: from kotlin metadata */
    private final TextView itemTitleTv1;

    /* renamed from: b  reason: from kotlin metadata */
    private final TextView itemTitleTv2;

    /* renamed from: c  reason: collision with root package name and from kotlin metadata */
    private final ViewGroup itemViewContainer1;

    /* renamed from: d  reason: from kotlin metadata */
    private final ViewGroup itemViewContainer2;

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Function1 f3824g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f3825h;

        a(Function1 function1, int i2) {
            this.f3824g = function1;
            this.f3825h = i2;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3824g.invoke(Integer.valueOf(this.f3825h));
        }
    }

    public DefaultExitGridViewHolder(@NotNull View view) {
        super(view);
        this.itemTitleTv1 = (TextView) view.findViewById(R.id.lib_cashier_dialog_grid_item_title_one);
        this.itemTitleTv2 = (TextView) view.findViewById(R.id.lib_cashier_dialog_grid_item_title_two);
        this.itemViewContainer1 = (ViewGroup) view.findViewById(R.id.lib_cashier_dialog_grid_item_container_one);
        this.itemViewContainer2 = (ViewGroup) view.findViewById(R.id.lib_cashier_dialog_grid_item_container_two);
    }

    public final void b(@NotNull com.jd.lib.cashier.sdk.pay.dialog.k.e.e exitRetainOptionRow) {
        if (exitRetainOptionRow.a().size() == 2) {
            j0.d(this.itemViewContainer1);
            TextView textView = this.itemTitleTv1;
            if (textView != null) {
                String str = exitRetainOptionRow.a().get(0).btnText;
                if (str == null) {
                    str = "";
                }
                textView.setText(str);
            }
            j0.d(this.itemViewContainer2);
            TextView textView2 = this.itemTitleTv2;
            if (textView2 != null) {
                String str2 = exitRetainOptionRow.a().get(1).btnText;
                textView2.setText(str2 != null ? str2 : "");
                return;
            }
            return;
        }
        j0.d(this.itemViewContainer1);
        TextView textView3 = this.itemTitleTv1;
        if (textView3 != null) {
            String str3 = exitRetainOptionRow.a().get(0).btnText;
            textView3.setText(str3 != null ? str3 : "");
        }
        j0.c(this.itemViewContainer2);
    }

    public final void c(@NotNull com.jd.lib.cashier.sdk.pay.dialog.k.e.e exitRetainOptionRow) {
        ViewGroup[] viewGroupArr;
        TextView[] textViewArr;
        int i2;
        int darkColor;
        int size = exitRetainOptionRow.a().size();
        boolean z = size == 2;
        if (z) {
            ViewGroup itemViewContainer1 = this.itemViewContainer1;
            Intrinsics.checkExpressionValueIsNotNull(itemViewContainer1, "itemViewContainer1");
            ViewGroup itemViewContainer2 = this.itemViewContainer2;
            Intrinsics.checkExpressionValueIsNotNull(itemViewContainer2, "itemViewContainer2");
            viewGroupArr = new ViewGroup[]{itemViewContainer1, itemViewContainer2};
        } else {
            ViewGroup itemViewContainer12 = this.itemViewContainer1;
            Intrinsics.checkExpressionValueIsNotNull(itemViewContainer12, "itemViewContainer1");
            viewGroupArr = new ViewGroup[]{itemViewContainer12};
        }
        if (z) {
            TextView itemTitleTv1 = this.itemTitleTv1;
            Intrinsics.checkExpressionValueIsNotNull(itemTitleTv1, "itemTitleTv1");
            TextView itemTitleTv2 = this.itemTitleTv2;
            Intrinsics.checkExpressionValueIsNotNull(itemTitleTv2, "itemTitleTv2");
            textViewArr = new TextView[]{itemTitleTv1, itemTitleTv2};
        } else {
            TextView itemTitleTv12 = this.itemTitleTv1;
            Intrinsics.checkExpressionValueIsNotNull(itemTitleTv12, "itemTitleTv1");
            textViewArr = new TextView[]{itemTitleTv12};
        }
        boolean isDarkMode = JDDarkUtil.isDarkMode();
        for (int i3 = 0; i3 < size; i3++) {
            ViewGroup viewGroup = viewGroupArr[i3];
            if (exitRetainOptionRow.a().get(i3).selected) {
                if (isDarkMode) {
                    i2 = R.drawable.lib_cashier_sdk_pay_exit_dialog_stay_item_select_bg_dark;
                } else {
                    i2 = R.drawable.lib_cashier_sdk_pay_exit_dialog_stay_item_select_bg;
                }
            } else if (isDarkMode) {
                i2 = R.drawable.lib_cashier_sdk_pay_exit_dialog_stay_item_normal_bg_dark;
            } else {
                i2 = R.drawable.lib_cashier_sdk_pay_exit_dialog_stay_item_normal_bg;
            }
            viewGroup.setBackgroundResource(i2);
            TextView textView = textViewArr[i3];
            if (exitRetainOptionRow.a().get(i3).selected) {
                darkColor = JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FA2C19);
            } else {
                darkColor = JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A);
            }
            textView.setTextColor(darkColor);
        }
    }

    public final void d(@NotNull Function1<? super Integer, Unit> onClickListener) {
        ArrayList arrayListOf;
        ViewGroup itemViewContainer1 = this.itemViewContainer1;
        Intrinsics.checkExpressionValueIsNotNull(itemViewContainer1, "itemViewContainer1");
        ViewGroup itemViewContainer2 = this.itemViewContainer2;
        Intrinsics.checkExpressionValueIsNotNull(itemViewContainer2, "itemViewContainer2");
        arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(itemViewContainer1, itemViewContainer2);
        int size = arrayListOf.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ViewGroup) arrayListOf.get(i2)).setOnClickListener(new a(onClickListener, i2));
        }
    }
}
