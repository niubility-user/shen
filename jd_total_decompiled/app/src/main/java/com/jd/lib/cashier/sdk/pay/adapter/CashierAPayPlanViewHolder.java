package com.jd.lib.cashier.sdk.pay.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.ui.widget.AMianXiPayPlanItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.APayPlanItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.APlusPayPlanItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanItemView;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener;
import com.jd.lib.cashier.sdk.core.utils.g;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jingdong.amon.router.annotation.AnnoConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J9\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0017\u001a\n \u0014*\u0004\u0018\u00010\u00130\u00138\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016\u00a8\u0006\u001c"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanItemView;", "planItemView", "", "b", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsPayPlanItemView;)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "entity", "", "mianxiHighlight", "Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "onClickCouponListener", "", "viewNeedPadding", "c", "(Landroid/content/Context;Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;Ljava/lang/String;Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;Z)V", "Landroid/widget/LinearLayout;", "kotlin.jvm.PlatformType", com.jingdong.jdsdk.a.a.a, "Landroid/widget/LinearLayout;", "mPayPlanRootView", "Landroid/view/View;", "infoItemView", "<init>", "(Landroid/view/View;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierAPayPlanViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: from kotlin metadata */
    private final LinearLayout mPayPlanRootView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ AbsPayPlanItemView f3811g;

        a(AbsPayPlanItemView absPayPlanItemView) {
            this.f3811g = absPayPlanItemView;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.f3811g.clearFocus();
            this.f3811g.sendAccessibilityEvent(65536);
            this.f3811g.sendAccessibilityEvent(4);
            this.f3811g.sendAccessibilityEvent(8);
            this.f3811g.sendAccessibilityEvent(32768);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Ref.ObjectRef f3812g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ OnPlanViewClickListener f3813h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ IPlanItemViewEntity f3814i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Ref.ObjectRef f3815j;

        b(Ref.ObjectRef objectRef, OnPlanViewClickListener onPlanViewClickListener, IPlanItemViewEntity iPlanItemViewEntity, Ref.ObjectRef objectRef2) {
            this.f3812g = objectRef;
            this.f3813h = onPlanViewClickListener;
            this.f3814i = iPlanItemViewEntity;
            this.f3815j = objectRef2;
        }

        /* JADX WARN: Type inference failed for: r2v2, types: [com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanItemView, T, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v5, types: [T, com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity] */
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            boolean z = view instanceof AbsPayPlanItemView;
            ?? r2 = (AbsPayPlanItemView) (!z ? null : view);
            if (o0.a(CashierAPayPlanAdapter.INSTANCE.a()) && Intrinsics.areEqual((AbsPayPlanItemView) this.f3812g.element, (Object) r2)) {
                return;
            }
            OnPlanViewClickListener onPlanViewClickListener = this.f3813h;
            if (onPlanViewClickListener != null) {
                IPlanItemViewEntity iPlanItemViewEntity = this.f3814i;
                IPlanItemViewEntity iPlanItemViewEntity2 = (IPlanItemViewEntity) this.f3815j.element;
                if (iPlanItemViewEntity2 == null) {
                    iPlanItemViewEntity2 = iPlanItemViewEntity;
                }
                onPlanViewClickListener.onClick(iPlanItemViewEntity, iPlanItemViewEntity2);
            }
            this.f3812g.element = r2;
            Ref.ObjectRef objectRef = this.f3815j;
            ?? r3 = this.f3814i;
            objectRef.element = r3;
            AbsPayPlanItemView absPayPlanItemView = z ? view : null;
            if (absPayPlanItemView != null) {
                absPayPlanItemView.m(r3.isCheckable());
            }
            view.sendAccessibilityEvent(4);
        }
    }

    public CashierAPayPlanViewHolder(@NotNull View view) {
        super(view);
        this.mPayPlanRootView = (LinearLayout) view.findViewById(R.id.lib_cashier_a_pay_plan_root_view);
    }

    private final void b(AbsPayPlanItemView planItemView) {
        if (planItemView != null) {
            try {
                if (g.a(planItemView.getContext())) {
                    planItemView.post(new a(planItemView));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [com.jd.lib.cashier.sdk.core.ui.widget.AbsPayPlanItemView, T, android.widget.FrameLayout, android.view.View] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void c(@NotNull Context context, @NotNull IPlanItemViewEntity entity, @Nullable String mianxiHighlight, @Nullable OnPlanViewClickListener onClickCouponListener, boolean viewNeedPadding) {
        ViewParent aPlusPayPlanItemView;
        ?? r7;
        LinearLayout linearLayout;
        LinearLayout linearLayout2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = null;
        int selectorType = entity.getSelectorType();
        if (selectorType == 20000) {
            aPlusPayPlanItemView = new APlusPayPlanItemView(context, mianxiHighlight);
        } else if (selectorType != 30000) {
            r7 = new APayPlanItemView(context);
            if (entity.isCheckable()) {
                r7.setEnabled(false);
            } else {
                r7.setEnabled(true);
                r7.setChecked(entity.isChecked());
                if (entity.isChecked()) {
                    objectRef.element = r7;
                    objectRef2.element = entity;
                    b(r7);
                }
            }
            r7.f(entity.getTopText());
            r7.e(entity.getPlanFeeInfo());
            r7.d(entity.getFlagText());
            if (!viewNeedPadding) {
                r7.i(0, DpiUtil.dip2px(context, 7.0f), 0, 0);
            } else {
                r7.i(0, 0, 0, 0);
            }
            r7.m(entity.isCheckable());
            r7.setOnClickListener(new b(objectRef, onClickCouponListener, entity, objectRef2));
            linearLayout = this.mPayPlanRootView;
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
            linearLayout2 = this.mPayPlanRootView;
            if (linearLayout2 == 0) {
                linearLayout2.addView(r7);
                return;
            }
            return;
        } else {
            aPlusPayPlanItemView = new AMianXiPayPlanItemView(context, mianxiHighlight);
        }
        r7 = aPlusPayPlanItemView;
        if (entity.isCheckable()) {
        }
        r7.f(entity.getTopText());
        r7.e(entity.getPlanFeeInfo());
        r7.d(entity.getFlagText());
        if (!viewNeedPadding) {
        }
        r7.m(entity.isCheckable());
        r7.setOnClickListener(new b(objectRef, onClickCouponListener, entity, objectRef2));
        linearLayout = this.mPayPlanRootView;
        if (linearLayout != null) {
        }
        linearLayout2 = this.mPayPlanRootView;
        if (linearLayout2 == 0) {
        }
    }
}
