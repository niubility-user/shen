package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019B#\b\u0016\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0018\u0010\u001bJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ9\u0010\u0012\u001a\u00020\u0002\"\b\b\u0000\u0010\f*\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001c"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/PayPlanView;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanView;", "", "q", "()V", "", JshopConst.JSHOP_PROMOTIO_H, "()I", "currentWidth", "u", "(I)V", "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "W", "", "data", "screenWidth", "", "mianxiHighlight", JshopConst.JSHOP_PROMOTIO_W, "(Ljava/util/List;ILjava/lang/String;)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class PayPlanView extends AbsPayPlanView {

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IPlanItemViewEntity f3067h;

        a(IPlanItemViewEntity iPlanItemViewEntity) {
            this.f3067h = iPlanItemViewEntity;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            boolean z = view instanceof AbsPayPlanItemView;
            AbsPayPlanItemView absPayPlanItemView = (AbsPayPlanItemView) (!z ? null : view);
            if (o0.a(AbsPayPlanView.INSTANCE.a()) && Intrinsics.areEqual(PayPlanView.this.getLastCheckedAbsPayPlanItemView(), absPayPlanItemView)) {
                return;
            }
            if (PayPlanView.this.getIsClickAutoSelection()) {
                AbsPayPlanItemView lastCheckedAbsPayPlanItemView = PayPlanView.this.getLastCheckedAbsPayPlanItemView();
                if (lastCheckedAbsPayPlanItemView != null) {
                    lastCheckedAbsPayPlanItemView.toggle();
                }
                if (absPayPlanItemView != null) {
                    absPayPlanItemView.setChecked(true);
                }
            }
            OnPlanViewClickListener onItemClickListener = PayPlanView.this.getOnItemClickListener();
            if (onItemClickListener != null) {
                IPlanItemViewEntity iPlanItemViewEntity = this.f3067h;
                IPlanItemViewEntity lastPlanItemViewEntity = PayPlanView.this.getLastPlanItemViewEntity();
                if (lastPlanItemViewEntity == null) {
                    lastPlanItemViewEntity = this.f3067h;
                }
                onItemClickListener.onClick(iPlanItemViewEntity, lastPlanItemViewEntity);
            }
            PayPlanView.this.A(absPayPlanItemView);
            PayPlanView.this.B(this.f3067h);
            AbsPayPlanItemView absPayPlanItemView2 = z ? view : null;
            if (absPayPlanItemView2 != null) {
                absPayPlanItemView2.m(this.f3067h.isCheckable());
            }
            view.sendAccessibilityEvent(4);
        }
    }

    public PayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView
    public int h() {
        return R.layout.lib_cashier_sdk_regulator_pay_plan_container;
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView
    public void q() {
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
                double dip2px = currentWidth - DpiUtil.dip2px(getContext(), 30.0f);
                Double.isNaN(dip2px);
                int i3 = (int) (dip2px * 0.5d);
                if (i3 > 0 && (o = o(i2)) != null) {
                    AbsPayPlanItemView o2 = o(i2);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(o2 != null ? o2.getLayoutParams() : null);
                    layoutParams.width = i3;
                    layoutParams.height = -2;
                    layoutParams.setMargins(0, 0, DpiUtil.dip2px(super.getContext(), 10.0f), DpiUtil.dip2px(super.getContext(), 2.0f));
                    o.setLayoutParams(layoutParams);
                }
                i2++;
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanView
    public <W extends IPlanItemViewEntity> void w(@NotNull List<? extends W> data, int screenWidth, @Nullable String mianxiHighlight) {
        AbsPayPlanItemView plusPayPlanItemView;
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
                    plusPayPlanItemView = new PlusPayPlanItemView(getContext(), mianxiHighlight);
                } else if (selectorType != 30000) {
                    plusPayPlanItemView = new PayPlanItemView(getContext());
                } else {
                    plusPayPlanItemView = new MianXiPayPlanItemView(getContext(), mianxiHighlight);
                }
                if (!iPlanItemViewEntity.isCheckable()) {
                    plusPayPlanItemView.setEnabled(false);
                } else {
                    plusPayPlanItemView.setEnabled(true);
                    plusPayPlanItemView.setChecked(iPlanItemViewEntity.isChecked());
                    if (iPlanItemViewEntity.isChecked()) {
                        A(plusPayPlanItemView);
                        B(iPlanItemViewEntity);
                        t(plusPayPlanItemView);
                    }
                }
                plusPayPlanItemView.f(iPlanItemViewEntity.getTopText());
                plusPayPlanItemView.e(iPlanItemViewEntity.getPlanFeeInfo());
                plusPayPlanItemView.d(iPlanItemViewEntity.getFlagText());
                plusPayPlanItemView.m(iPlanItemViewEntity.isCheckable());
                plusPayPlanItemView.setOnClickListener(new a(iPlanItemViewEntity));
                double dip2px = screenWidth - DpiUtil.dip2px(getContext(), 30.0f);
                Double.isNaN(dip2px);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (dip2px * 0.5d), -2);
                layoutParams.setMargins(0, 0, DpiUtil.dip2px(super.getContext(), 10.0f), DpiUtil.dip2px(super.getContext(), 2.0f));
                int i4 = i3 / 2;
                LinearLayout container3 = getContainer();
                View childAt = container3 != null ? container3.getChildAt(i4) : null;
                LinearLayout linearLayout2 = childAt instanceof LinearLayout ? childAt : null;
                if (linearLayout2 != null) {
                    linearLayout2.addView(plusPayPlanItemView, layoutParams);
                }
                i3++;
            }
        }
    }

    public PayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
