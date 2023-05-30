package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u00a2\u0006\u0004\b\u001f\u0010 B#\b\u0016\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\u0006\u0010!\u001a\u00020\u0005\u00a2\u0006\u0004\b\u001f\u0010\"J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ9\u0010\u0012\u001a\u00020\u0002\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\u0015\u0010\u0016R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0018\u0010\u0019\u00a8\u0006#"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/BPayPlanView;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanView;", "", "q", "()V", "", JshopConst.JSHOP_PROMOTIO_H, "()I", "currentWidth", "u", "(I)V", "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "W", "", "data", "screenWidth", "", "mianxiHighlight", JshopConst.JSHOP_PROMOTIO_W, "(Ljava/util/List;ILjava/lang/String;)V", "investorDoc", "L", "(Ljava/lang/String;)V", "Landroid/widget/TextView;", "C", "Landroid/widget/TextView;", "tvInvestorDoc", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class BPayPlanView extends AbsPayPlanView {

    /* renamed from: C  reason: from kotlin metadata */
    private TextView tvInvestorDoc;

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IPlanItemViewEntity f3034h;

        a(IPlanItemViewEntity iPlanItemViewEntity) {
            this.f3034h = iPlanItemViewEntity;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            boolean z = view instanceof AbsPayPlanItemView;
            AbsPayPlanItemView absPayPlanItemView = (AbsPayPlanItemView) (!z ? null : view);
            if (o0.a(AbsPayPlanView.INSTANCE.a()) && Intrinsics.areEqual(BPayPlanView.this.getLastCheckedAbsPayPlanItemView(), absPayPlanItemView)) {
                return;
            }
            if (BPayPlanView.this.getIsClickAutoSelection()) {
                AbsPayPlanItemView lastCheckedAbsPayPlanItemView = BPayPlanView.this.getLastCheckedAbsPayPlanItemView();
                if (lastCheckedAbsPayPlanItemView != null) {
                    lastCheckedAbsPayPlanItemView.toggle();
                }
                if (absPayPlanItemView != null) {
                    absPayPlanItemView.setChecked(true);
                }
            }
            OnPlanViewClickListener onItemClickListener = BPayPlanView.this.getOnItemClickListener();
            if (onItemClickListener != null) {
                IPlanItemViewEntity iPlanItemViewEntity = this.f3034h;
                IPlanItemViewEntity lastPlanItemViewEntity = BPayPlanView.this.getLastPlanItemViewEntity();
                if (lastPlanItemViewEntity == null) {
                    lastPlanItemViewEntity = this.f3034h;
                }
                onItemClickListener.onClick(iPlanItemViewEntity, lastPlanItemViewEntity);
            }
            BPayPlanView.this.A(absPayPlanItemView);
            BPayPlanView.this.B(this.f3034h);
            AbsPayPlanItemView absPayPlanItemView2 = z ? view : null;
            if (absPayPlanItemView2 != null) {
                absPayPlanItemView2.m(this.f3034h.isCheckable());
            }
            view.sendAccessibilityEvent(4);
        }
    }

    public BPayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void L(@Nullable String investorDoc) {
        if (!TextUtils.isEmpty(investorDoc)) {
            TextView textView = this.tvInvestorDoc;
            if (textView != null) {
                textView.setVisibility(0);
            }
            TextView textView2 = this.tvInvestorDoc;
            if (textView2 != null) {
                if (investorDoc == null) {
                    investorDoc = "";
                }
                textView2.setText(investorDoc);
                return;
            }
            return;
        }
        TextView textView3 = this.tvInvestorDoc;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView
    public int h() {
        return R.layout.lib_cashier_sdk_b_pay_plan_container;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView
    public void q() {
        this.tvInvestorDoc = (TextView) p().findViewById(R.id.lib_cashier_bt_investor_doc);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView
    public void u(int currentWidth) {
        AbsPayPlanItemView o;
        List<IPlanItemViewEntity> e2 = e();
        if (e2 != null) {
            Iterator<T> it = e2.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                it.next();
                double dip2px = currentWidth - DpiUtil.dip2px(getContext(), 36.0f);
                Double.isNaN(dip2px);
                int i3 = (int) (dip2px * 0.5d);
                if (i3 > 0 && (o = o(i2)) != null) {
                    AbsPayPlanItemView o2 = o(i2);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(o2 != null ? o2.getLayoutParams() : null);
                    layoutParams.width = i3;
                    layoutParams.height = -2;
                    layoutParams.setMargins(0, 0, DpiUtil.dip2px(super.getContext(), 12.0f), DpiUtil.dip2px(super.getContext(), 2.0f));
                    o.setLayoutParams(layoutParams);
                }
                i2++;
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView
    public <W extends IPlanItemViewEntity> void w(@NotNull List<? extends W> data, int screenWidth, @Nullable String mianxiHighlight) {
        AbsPayPlanItemView bPlusPayPlanItemView;
        LinearLayout container = getContainer();
        if (container != null) {
            container.removeAllViews();
        }
        z(data);
        C(mianxiHighlight);
        if (!data.isEmpty() && screenWidth > 0) {
            int ceil = (int) Math.ceil(data.size() / 2);
            if (1 <= ceil) {
                int i2 = 1;
                while (true) {
                    LinearLayout linearLayout = new LinearLayout(super.getContext());
                    linearLayout.setOrientation(0);
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    linearLayout.setPadding(0, 0, 0, 0);
                    LinearLayout container2 = getContainer();
                    if (container2 != null) {
                        container2.addView(linearLayout);
                    }
                    if (i2 == ceil) {
                        break;
                    }
                    i2++;
                }
            }
            Iterator<T> it = data.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                IPlanItemViewEntity iPlanItemViewEntity = (IPlanItemViewEntity) it.next();
                int selectorType = iPlanItemViewEntity.getSelectorType();
                if (selectorType == 20000) {
                    bPlusPayPlanItemView = new BPlusPayPlanItemView(getContext(), mianxiHighlight);
                } else if (selectorType != 30000) {
                    bPlusPayPlanItemView = new BPayPlanItemView(getContext());
                } else {
                    bPlusPayPlanItemView = new BMianXiPayPlanItemView(getContext(), mianxiHighlight);
                }
                if (!iPlanItemViewEntity.isCheckable()) {
                    bPlusPayPlanItemView.setEnabled(false);
                } else {
                    bPlusPayPlanItemView.setEnabled(true);
                    bPlusPayPlanItemView.setChecked(iPlanItemViewEntity.isChecked());
                    if (iPlanItemViewEntity.isChecked()) {
                        A(bPlusPayPlanItemView);
                        B(iPlanItemViewEntity);
                        t(bPlusPayPlanItemView);
                    }
                }
                bPlusPayPlanItemView.f(iPlanItemViewEntity.getTopText());
                bPlusPayPlanItemView.e(iPlanItemViewEntity.getPlanFeeInfo());
                bPlusPayPlanItemView.d(iPlanItemViewEntity.getFlagText());
                bPlusPayPlanItemView.m(iPlanItemViewEntity.isCheckable());
                bPlusPayPlanItemView.setOnClickListener(new a(iPlanItemViewEntity));
                double dip2px = screenWidth - DpiUtil.dip2px(getContext(), 36.0f);
                Double.isNaN(dip2px);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (dip2px * 0.5d), -2);
                layoutParams.setMargins(0, 0, DpiUtil.dip2px(super.getContext(), 12.0f), DpiUtil.dip2px(super.getContext(), 2.0f));
                int i4 = i3 / 2;
                LinearLayout container3 = getContainer();
                View childAt = container3 != null ? container3.getChildAt(i4) : null;
                LinearLayout linearLayout2 = childAt instanceof LinearLayout ? childAt : null;
                if (linearLayout2 != null) {
                    linearLayout2.addView(bPlusPayPlanItemView, layoutParams);
                }
                i3++;
            }
        }
    }

    public BPayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
