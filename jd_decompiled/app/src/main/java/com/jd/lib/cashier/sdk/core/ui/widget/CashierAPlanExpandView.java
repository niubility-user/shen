package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010C\u001a\u0004\u0018\u00010B\u00a2\u0006\u0004\bD\u0010EB#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010C\u001a\u0004\u0018\u00010B\u0012\u0006\u0010G\u001a\u00020F\u00a2\u0006\u0004\bD\u0010HJ\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b\u000e\u0010\u000fJ1\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019R$\u0010!\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010)\u001a\u00020\"8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u0010-\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b*\u0010\u001c\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 R$\u00105\u001a\u0004\u0018\u00010.8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R$\u00109\u001a\u0004\u0018\u00010.8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u00100\u001a\u0004\b7\u00102\"\u0004\b8\u00104R$\u0010=\u001a\u0004\u0018\u00010\u001a8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b:\u0010\u001c\u001a\u0004\b;\u0010\u001e\"\u0004\b<\u0010 R$\u0010A\u001a\u0004\u0018\u00010\"8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u0010$\u001a\u0004\b?\u0010&\"\u0004\b@\u0010(\u00a8\u0006I"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/CashierAPlanExpandView;", "Landroid/widget/FrameLayout;", "Lcom/jd/lib/cashier/sdk/core/aac/e;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", com.jingdong.jdsdk.a.a.a, "(Landroid/content/Context;)V", "", "collectionText", "c", "(Ljava/lang/String;)V", "Landroid/view/View$OnClickListener;", "clickListener", "b", "(Landroid/view/View$OnClickListener;)V", "Lcom/jd/lib/cashier/sdk/pay/bean/baitiao/PlanServiceMap;", "currentServiceMap", "Lcom/jd/lib/cashier/sdk/pay/bean/baitiao/AgreementServiceMapMap;", "agreementServiceMapMap", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", com.jingdong.app.mall.e.a, "(Landroid/content/Context;Lcom/jd/lib/cashier/sdk/pay/bean/baitiao/PlanServiceMap;Lcom/jd/lib/cashier/sdk/pay/bean/baitiao/AgreementServiceMapMap;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "onChangeSkin", "()V", "Landroid/widget/TextView;", NotifyType.LIGHTS, "Landroid/widget/TextView;", "getPayChannelTipTv", "()Landroid/widget/TextView;", "setPayChannelTipTv", "(Landroid/widget/TextView;)V", "payChannelTipTv", "Landroid/view/View;", "g", "Landroid/view/View;", "getPlanExpandRootView", "()Landroid/view/View;", "setPlanExpandRootView", "(Landroid/view/View;)V", "planExpandRootView", "m", "getPayChannelCollectionTv", "setPayChannelCollectionTv", "payChannelCollectionTv", "Landroid/view/ViewGroup;", "i", "Landroid/view/ViewGroup;", "getPayChannelAgreementContainer", "()Landroid/view/ViewGroup;", "setPayChannelAgreementContainer", "(Landroid/view/ViewGroup;)V", "payChannelAgreementContainer", JshopConst.JSHOP_PROMOTIO_H, "getPayChannelTipContainer", "setPayChannelTipContainer", "payChannelTipContainer", "j", "getPayChannelAgreementNameTv", "setPayChannelAgreementNameTv", "payChannelAgreementNameTv", "k", "getPayChannelAgreementView", "setPayChannelAgreementView", "payChannelAgreementView", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierAPlanExpandView extends FrameLayout implements com.jd.lib.cashier.sdk.core.aac.e {
    @NotNull

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    public View planExpandRootView;
    @Nullable

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private ViewGroup payChannelTipContainer;
    @Nullable

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private ViewGroup payChannelAgreementContainer;
    @Nullable

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private TextView payChannelAgreementNameTv;
    @Nullable

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private View payChannelAgreementView;
    @Nullable

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private TextView payChannelTipTv;
    @Nullable

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private TextView payChannelCollectionTv;

    /* loaded from: classes14.dex */
    public static final class a extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f3052j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ Payment f3053k;

        /* renamed from: l  reason: collision with root package name */
        final /* synthetic */ PlanServiceMap f3054l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(Context context, Payment payment, PlanServiceMap planServiceMap, long j2) {
            super(j2);
            this.f3052j = context;
            this.f3053k = payment;
            this.f3054l = planServiceMap;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(@Nullable View view) {
            Context context = this.f3052j;
            if (context instanceof CashierPayActivity) {
                if (context != null) {
                    CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
                    com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
                    Payment payment = this.f3053k;
                    d.O(cashierPayActivity, payment.code, com.jd.lib.cashier.sdk.h.h.e.a.f(payment));
                    com.jd.lib.cashier.sdk.b.d.a.j(cashierPayActivity, this.f3054l.planServiceFeeToast);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.jd.lib.cashier.sdk.pay.view.CashierPayActivity");
            }
        }
    }

    /* loaded from: classes14.dex */
    public static final class b extends com.jd.lib.cashier.sdk.core.utils.b {

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ Context f3055j;

        /* renamed from: k  reason: collision with root package name */
        final /* synthetic */ AgreementServiceMapMap f3056k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Context context, AgreementServiceMapMap agreementServiceMapMap, long j2) {
            super(j2);
            this.f3055j = context;
            this.f3056k = agreementServiceMapMap;
        }

        @Override // com.jd.lib.cashier.sdk.core.utils.b
        public void b(@Nullable View view) {
            Context context = this.f3055j;
            if (context instanceof CashierPayActivity) {
                com.jd.lib.cashier.sdk.pay.dialog.a aVar = new com.jd.lib.cashier.sdk.pay.dialog.a();
                if (context != null) {
                    String str = this.f3056k.agreementUrl;
                    Intrinsics.checkExpressionValueIsNotNull(str, "agreementServiceMapMap.agreementUrl");
                    String str2 = this.f3056k.agreementTitle;
                    Intrinsics.checkExpressionValueIsNotNull(str2, "agreementServiceMapMap.agreementTitle");
                    aVar.e((CashierPayActivity) context, str, str2);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.jd.lib.cashier.sdk.pay.view.CashierPayActivity");
            }
        }
    }

    static {
        Intrinsics.checkExpressionValueIsNotNull(CashierAPlanExpandView.class.getSimpleName(), "CashierAPlanExpandView::class.java.simpleName");
    }

    public CashierAPlanExpandView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private final void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_a_pay_plan_expand_view, (ViewGroup) this, true);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont\u2026_expand_view, this, true)");
        this.planExpandRootView = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planExpandRootView");
        }
        View findViewById = inflate.findViewById(R.id.lib_cashier_a_pay_channel_expand_floor_root);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "planExpandRootView.findV\u2026hannel_expand_floor_root)");
        LinearLayout linearLayout = (LinearLayout) findViewById;
        View view = this.planExpandRootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planExpandRootView");
        }
        this.payChannelTipTv = (TextView) view.findViewById(R.id.lib_cashier_a_pay_channel_floor_tip);
        View view2 = this.planExpandRootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planExpandRootView");
        }
        this.payChannelAgreementView = view2.findViewById(R.id.lib_cashier_a_pay_channel_floor_agreement_view);
        View view3 = this.planExpandRootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planExpandRootView");
        }
        this.payChannelAgreementNameTv = (TextView) view3.findViewById(R.id.lib_cashier_a_pay_channel_floor_agreement_name);
        View view4 = this.planExpandRootView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planExpandRootView");
        }
        this.payChannelAgreementContainer = (ViewGroup) view4.findViewById(R.id.lib_cashier_a_pay_channel_floor_agreement_container);
        View view5 = this.planExpandRootView;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planExpandRootView");
        }
        this.payChannelTipContainer = (ViewGroup) view5.findViewById(R.id.lib_cashier_a_pay_channel_floor_tip_container);
        View view6 = this.planExpandRootView;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planExpandRootView");
        }
        this.payChannelCollectionTv = (TextView) view6.findViewById(R.id.lib_cashier_a_pay_channel_floor_collection);
    }

    public final void b(@Nullable View.OnClickListener clickListener) {
        TextView textView = this.payChannelCollectionTv;
        if (textView != null && j0.a(textView)) {
            Drawable drawable = getResources().getDrawable(R.drawable.lib_cashier_sdk_icon_style_light);
            TextView textView2 = this.payChannelCollectionTv;
            if (textView2 != null) {
                textView2.setCompoundDrawablePadding(DpiUtil.dip2px(getContext(), 4.0f));
            }
            TextView textView3 = this.payChannelCollectionTv;
            if (textView3 != null) {
                textView3.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            }
            TextView textView4 = this.payChannelCollectionTv;
            if (textView4 != null) {
                textView4.setOnClickListener(clickListener);
            }
        }
    }

    public final void c(@NotNull String collectionText) {
        if (TextUtils.isEmpty(collectionText)) {
            TextView textView = this.payChannelCollectionTv;
            if (textView != null) {
                textView.setVisibility(8);
                return;
            }
            return;
        }
        TextView textView2 = this.payChannelCollectionTv;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        TextView textView3 = this.payChannelCollectionTv;
        if (textView3 != null) {
            textView3.setText(collectionText);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0063, code lost:
        if ((!r1.isEmpty()) != false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(@org.jetbrains.annotations.NotNull android.content.Context r11, @org.jetbrains.annotations.Nullable com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap r12, @org.jetbrains.annotations.Nullable com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap r13, @org.jetbrains.annotations.NotNull com.jd.lib.cashier.sdk.pay.bean.Payment r14) {
        /*
            Method dump skipped, instructions count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.core.ui.widget.CashierAPlanExpandView.e(android.content.Context, com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap, com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap, com.jd.lib.cashier.sdk.pay.bean.Payment):void");
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        View view = this.payChannelAgreementView;
        if (view != null) {
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_BFBFBF, JDDarkUtil.COLOR_404040));
        }
        ViewGroup viewGroup = this.payChannelTipContainer;
        if (viewGroup != null) {
            viewGroup.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    public CashierAPlanExpandView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context);
    }
}
