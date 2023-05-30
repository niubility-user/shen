package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.layoutmanager.WrapContentLinearLayoutManager;
import com.jd.lib.cashier.sdk.core.ui.widget.RollingDigitTextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CyberMoneyCouponLiveData;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.AllCouponAdapter;
import com.jd.lib.cashier.sdk.pay.adapter.DigitalBankAdapter;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.CyberMoneyCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCyberMoneyChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickCyberMoneyCouponItemEvent;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickPayChannelItemEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\b\u0012\u0004\u0012\u00020\u00040\u0003B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\u0004\bO\u0010%J#\u0010\t\u001a\u00020\b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000f\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0010J)\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001b\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001b\u0010\fJ\u000f\u0010\u001c\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001c\u0010\fJ\u0019\u0010\u001e\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0018H\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\bH\u0002\u00a2\u0006\u0004\b \u0010\fJ\u0019\u0010!\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002\u00a2\u0006\u0004\b!\u0010\u0010J\u0019\u0010\"\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\"\u0010#J\u0019\u0010$\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016\u00a2\u0006\u0004\b$\u0010%J\u001f\u0010&\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b&\u0010'J\u000f\u0010(\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b(\u0010\fR\u0019\u0010\u0013\u001a\u00020\u00128\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0019\u0010)\u001a\u0004\b*\u0010+R\u0018\u0010/\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b-\u0010.R\u0018\u00103\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u00102R\u0018\u00106\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b4\u00105R\u0018\u00108\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b7\u00105R\u0016\u0010<\u001a\u0002098\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\u0018\u0010@\u001a\u0004\u0018\u00010=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\u0018\u0010D\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0018\u0010F\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u0010CR\u001c\u0010J\u001a\b\u0012\u0004\u0012\u00020H0G8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001c\u0010IR\u0018\u0010L\u001a\u0004\u0018\u00010A8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010CR\u0018\u0010N\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bM\u0010.\u00a8\u0006P"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/channel/CyberMoneyPaymentProxy;", "Lcom/jd/lib/cashier/sdk/core/aac/d;", "Lcom/jd/lib/cashier/sdk/d/d/a;", "Landroidx/lifecycle/Observer;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/m;", "Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;", "selectedBankChannel", "cyberMoneyCoupon", "", "A", "(Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/m;)V", "t", "()V", "Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "couponEntity", "C", "(Lcom/jd/lib/cashier/sdk/pay/dialog/e;)V", "B", "Landroidx/fragment/app/FragmentActivity;", "activity", "Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyPayEntity;", "digitalMoneyPayEntity", "Ljava/lang/Runnable;", "onConfirmClickListener", "Landroid/view/View;", "r", "(Landroidx/fragment/app/FragmentActivity;Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyPayEntity;Ljava/lang/Runnable;)Landroid/view/View;", JshopConst.JSHOP_PROMOTIO_W, "q", "contentView", "v", "(Landroid/view/View;)V", "z", JshopConst.JSHOP_PROMOTIO_Y, "u", "(Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/m;)V", "f", "(Landroidx/fragment/app/FragmentActivity;)V", JshopConst.JSHOP_PROMOTIO_X, "(Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyPayEntity;Ljava/lang/Runnable;)V", "onDestroy", "Landroidx/fragment/app/FragmentActivity;", "s", "()Landroidx/fragment/app/FragmentActivity;", "Landroidx/recyclerview/widget/RecyclerView;", "p", "Landroidx/recyclerview/widget/RecyclerView;", "cyberMoneyCouponRecyclerView", "Landroid/app/Dialog;", "j", "Landroid/app/Dialog;", "cyberMoneyDialog", JshopConst.JSHOP_PROMOTIO_H, "Landroid/view/View;", "containerCoupon", "i", "containerBankChannel", "", "g", "F", "screenWidth", "Lcom/jd/lib/cashier/sdk/core/ui/widget/RollingDigitTextView;", PersonalConstants.ICON_STYLE_N, "Lcom/jd/lib/cashier/sdk/core/ui/widget/RollingDigitTextView;", "mTvPayPriceView", "Landroid/widget/TextView;", NotifyType.LIGHTS, "Landroid/widget/TextView;", "mTvPayPriceUnit", "m", "mTvPayPriceExtend", "", "Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/CyberMoneyCouponEntity;", "Ljava/util/List;", "cyberMoneyCouponSourceList", "k", "mTvPaySubtitle", "o", "cyberMoneyBankRecyclerView", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CyberMoneyPaymentProxy implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a, Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.m> {

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private float screenWidth;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private View containerCoupon;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private View containerBankChannel;

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private Dialog cyberMoneyDialog;

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private TextView mTvPaySubtitle;

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private TextView mTvPayPriceUnit;

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private TextView mTvPayPriceExtend;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private RollingDigitTextView mTvPayPriceView;

    /* renamed from: o  reason: from kotlin metadata */
    private RecyclerView cyberMoneyBankRecyclerView;

    /* renamed from: p  reason: from kotlin metadata */
    private RecyclerView cyberMoneyCouponRecyclerView;

    /* renamed from: q  reason: from kotlin metadata */
    private final List<CyberMoneyCouponEntity> cyberMoneyCouponSourceList = new ArrayList();
    @NotNull

    /* renamed from: r  reason: from kotlin metadata */
    private final FragmentActivity activity;

    /* loaded from: classes14.dex */
    public static final class a implements Animator.AnimatorListener {
        a() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(@Nullable Animator animator) {
            View view = CyberMoneyPaymentProxy.this.containerCoupon;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = CyberMoneyPaymentProxy.this.containerBankChannel;
            if (view2 != null) {
                view2.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(@Nullable Animator animator) {
            View view = CyberMoneyPaymentProxy.this.containerBankChannel;
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Runnable f3667h;

        b(FragmentActivity fragmentActivity, DigitalMoneyPayEntity digitalMoneyPayEntity, Runnable runnable) {
            this.f3667h = runnable;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog = CyberMoneyPaymentProxy.this.cyberMoneyDialog;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3668g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ DigitalMoneyPayEntity f3669h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Runnable f3670i;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/h;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/h;)V", "com/jd/lib/cashier/sdk/pay/aac/impl/channel/CyberMoneyPaymentProxy$generateContentView$1$2$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        static final class a extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.h, Unit> {
            a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.h hVar) {
                invoke2(hVar);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:14:0x0025, code lost:
                if (r0 != null) goto L17;
             */
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void invoke2(@org.jetbrains.annotations.NotNull com.jd.lib.cashier.sdk.h.h.h r8) {
                /*
                    r7 = this;
                    com.jd.lib.cashier.sdk.pay.aac.impl.channel.CyberMoneyPaymentProxy$c r0 = com.jd.lib.cashier.sdk.pay.aac.impl.channel.CyberMoneyPaymentProxy.c.this
                    com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyPayEntity r0 = r0.f3669h
                    java.util.List<com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard> r0 = r0.bankCardList
                    if (r0 == 0) goto L28
                    java.util.Iterator r0 = r0.iterator()
                Lc:
                    boolean r1 = r0.hasNext()
                    if (r1 == 0) goto L1e
                    java.lang.Object r1 = r0.next()
                    r2 = r1
                    com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard r2 = (com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard) r2
                    boolean r2 = r2.selected
                    if (r2 == 0) goto Lc
                    goto L1f
                L1e:
                    r1 = 0
                L1f:
                    com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard r1 = (com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard) r1
                    if (r1 == 0) goto L28
                    java.lang.String r0 = r1.uniqueChannelId
                    if (r0 == 0) goto L28
                    goto L2a
                L28:
                    java.lang.String r0 = ""
                L2a:
                    r4 = r0
                    com.jd.lib.cashier.sdk.h.e.a r1 = com.jd.lib.cashier.sdk.h.e.a.d()
                    com.jd.lib.cashier.sdk.pay.aac.impl.channel.CyberMoneyPaymentProxy$c r0 = com.jd.lib.cashier.sdk.pay.aac.impl.channel.CyberMoneyPaymentProxy.c.this
                    androidx.fragment.app.FragmentActivity r2 = r0.f3668g
                    java.lang.String r3 = r8.c()
                    java.lang.String r5 = r8.f()
                    java.lang.String r6 = r8.i()
                    r1.z(r2, r3, r4, r5, r6)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.aac.impl.channel.CyberMoneyPaymentProxy.c.a.invoke2(com.jd.lib.cashier.sdk.h.h.h):void");
            }
        }

        c(CyberMoneyPaymentProxy cyberMoneyPaymentProxy, FragmentActivity fragmentActivity, DigitalMoneyPayEntity digitalMoneyPayEntity, Runnable runnable) {
            this.f3668g = fragmentActivity;
            this.f3669h = digitalMoneyPayEntity;
            this.f3670i = runnable;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            com.jd.lib.cashier.sdk.h.h.c.b(this.f3668g, new a());
            Runnable runnable = this.f3670i;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class d implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Runnable f3672h;

        d(FragmentActivity fragmentActivity, DigitalMoneyPayEntity digitalMoneyPayEntity, Runnable runnable) {
            this.f3672h = runnable;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog = CyberMoneyPaymentProxy.this.cyberMoneyDialog;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class e implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Runnable f3674h;

        e(FragmentActivity fragmentActivity, DigitalMoneyPayEntity digitalMoneyPayEntity, Runnable runnable) {
            this.f3674h = runnable;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CyberMoneyPaymentProxy.this.q();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class f implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ FragmentActivity f3676h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ Runnable f3677i;

        f(FragmentActivity fragmentActivity, DigitalMoneyPayEntity digitalMoneyPayEntity, Runnable runnable) {
            this.f3676h = fragmentActivity;
            this.f3677i = runnable;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CyberMoneyCouponEntity cyberMoneyCouponEntity = new CyberMoneyCouponEntity();
            cyberMoneyCouponEntity.payMarketingUUID = "doNotUse";
            cyberMoneyCouponEntity.promotionDesc = this.f3676h.getString(R.string.lib_cashier_sdk_cyber_money_coupon_not_use);
            CyberMoneyPaymentProxy.this.C(cyberMoneyCouponEntity);
            CyberMoneyPaymentProxy.this.B(cyberMoneyCouponEntity);
            CyberMoneyPaymentProxy.this.q();
            CyberMoneyPaymentProxy.this.z();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/e;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/dialog/e;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class g extends Lambda implements Function1<com.jd.lib.cashier.sdk.pay.dialog.e, Unit> {
        g() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
            invoke2(eVar);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
            CyberMoneyPaymentProxy.this.C(eVar);
            CyberMoneyPaymentProxy.this.B(eVar);
            CyberMoneyPaymentProxy.this.q();
            CyberMoneyPaymentProxy.this.z();
        }
    }

    /* loaded from: classes14.dex */
    public static final class h implements Animator.AnimatorListener {
        h() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(@Nullable Animator animator) {
            View view = CyberMoneyPaymentProxy.this.containerBankChannel;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = CyberMoneyPaymentProxy.this.containerCoupon;
            if (view2 != null) {
                view2.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(@Nullable Animator animator) {
            View view = CyberMoneyPaymentProxy.this.containerCoupon;
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    /* loaded from: classes14.dex */
    static final class i implements DialogInterface.OnShowListener {
        i() {
        }

        @Override // android.content.DialogInterface.OnShowListener
        public final void onShow(DialogInterface dialogInterface) {
            CyberMoneyPaymentProxy.this.z();
        }
    }

    /* loaded from: classes14.dex */
    static final class j implements DialogInterface.OnDismissListener {
        j() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            RollingDigitTextView rollingDigitTextView = CyberMoneyPaymentProxy.this.mTvPayPriceView;
            if (rollingDigitTextView != null) {
                rollingDigitTextView.r();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/i;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/i;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class k extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.i, Unit> {
        k() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.i iVar) {
            invoke2(iVar);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.i iVar) {
            com.jd.lib.cashier.sdk.h.e.a.d().A(CyberMoneyPaymentProxy.this.getActivity(), iVar.c(), iVar.f());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCyberMoneyCouponItemEvent;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCyberMoneyCouponItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class l extends Lambda implements Function1<ClickCyberMoneyCouponItemEvent, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickCyberMoneyCouponItemEvent clickCyberMoneyCouponItemEvent) {
            invoke2(clickCyberMoneyCouponItemEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickCyberMoneyCouponItemEvent clickCyberMoneyCouponItemEvent) {
            CashierPayViewModel x;
            FragmentActivity fragmentActivity = this.$activity;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null) {
                return;
            }
            x.o((CashierPayActivity) this.$activity, clickCyberMoneyCouponItemEvent.getCyberMoneyChannel());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCyberMoneyChannelItemEvent;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickCyberMoneyChannelItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class m extends Lambda implements Function1<ClickCyberMoneyChannelItemEvent, Unit> {
        m() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickCyberMoneyChannelItemEvent clickCyberMoneyChannelItemEvent) {
            invoke2(clickCyberMoneyChannelItemEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickCyberMoneyChannelItemEvent clickCyberMoneyChannelItemEvent) {
            CyberMoneyPaymentProxy.this.C(clickCyberMoneyChannelItemEvent.getCouponEntity());
            CyberMoneyPaymentProxy.this.z();
        }
    }

    public CyberMoneyPaymentProxy(@NotNull FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
    }

    private final void A(DigitalMoneyBankCard selectedBankChannel, com.jd.lib.cashier.sdk.pay.aac.livedata.a.m cyberMoneyCoupon) {
        Collection<? extends CyberMoneyCouponEntity> arrayList;
        CashierPayViewModel x;
        CyberMoneyCouponLiveData z;
        CashierPayViewModel x2;
        CyberMoneyCouponLiveData z2;
        this.cyberMoneyCouponSourceList.clear();
        FragmentActivity fragmentActivity = this.activity;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        if (cashierPayActivity != null && (x2 = cashierPayActivity.x()) != null && (z2 = x2.z()) != null) {
            z2.a(cyberMoneyCoupon);
        }
        FragmentActivity fragmentActivity2 = this.activity;
        if (!(fragmentActivity2 instanceof CashierPayActivity)) {
            fragmentActivity2 = null;
        }
        CashierPayActivity cashierPayActivity2 = (CashierPayActivity) fragmentActivity2;
        if (cashierPayActivity2 != null && (x = cashierPayActivity2.x()) != null && (z = x.z()) != null) {
            z.c(selectedBankChannel != null ? selectedBankChannel.selectedCoupon : null, cyberMoneyCoupon);
        }
        List<CyberMoneyCouponEntity> list = this.cyberMoneyCouponSourceList;
        if (cyberMoneyCoupon == null || (arrayList = cyberMoneyCoupon.d) == null) {
            arrayList = new ArrayList<>();
        }
        list.addAll(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void B(com.jd.lib.cashier.sdk.pay.dialog.e couponEntity) {
        RecyclerView.Adapter adapter;
        RecyclerView recyclerView = this.cyberMoneyBankRecyclerView;
        RecyclerView.Adapter adapter2 = recyclerView != null ? recyclerView.getAdapter() : null;
        if (!(adapter2 instanceof DigitalBankAdapter)) {
            adapter2 = null;
        }
        DigitalBankAdapter digitalBankAdapter = (DigitalBankAdapter) adapter2;
        DigitalMoneyBankCard h2 = digitalBankAdapter != null ? digitalBankAdapter.h() : null;
        if (h2 != null) {
            h2.selectedCoupon = couponEntity;
        }
        RecyclerView recyclerView2 = this.cyberMoneyBankRecyclerView;
        if (recyclerView2 == null || (adapter = recyclerView2.getAdapter()) == null) {
            return;
        }
        adapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void C(com.jd.lib.cashier.sdk.pay.dialog.e couponEntity) {
        com.jd.lib.cashier.sdk.d.a.e.a aVar;
        Payment a2;
        Payment a3;
        Object obj;
        Payment a4;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b2;
        FragmentActivity fragmentActivity = this.activity;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        List<com.jd.lib.cashier.sdk.d.a.e.a> list = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b2 = x.b()) == null) ? null : b2.M;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                com.jd.lib.cashier.sdk.d.a.e.a aVar2 = (com.jd.lib.cashier.sdk.d.a.e.a) obj;
                if (!(aVar2 instanceof x)) {
                    aVar2 = null;
                }
                x xVar = (x) aVar2;
                if (TextUtils.equals((xVar == null || (a4 = xVar.a()) == null) ? null : a4.code, "cyberMoney")) {
                    break;
                }
            }
            aVar = (com.jd.lib.cashier.sdk.d.a.e.a) obj;
        } else {
            aVar = null;
        }
        x xVar2 = aVar instanceof x ? aVar : null;
        if (xVar2 != null && (a3 = xVar2.a()) != null) {
            a3.selectedCouponEntity = couponEntity;
        }
        if (xVar2 == null || (a2 = xVar2.a()) == null || !a2.selected) {
            return;
        }
        com.jd.lib.cashier.sdk.b.i.e.f("cashier_item_click", new ClickPayChannelItemEvent(xVar2.a()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void q() {
        View view = this.containerBankChannel;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.containerBankChannel, "TranslationX", -this.screenWidth, 0.0f), ObjectAnimator.ofFloat(this.containerCoupon, "TranslationX", 0.0f, this.screenWidth));
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(300L);
        animatorSet.addListener(new a());
        animatorSet.start();
    }

    private final View r(FragmentActivity activity, DigitalMoneyPayEntity digitalMoneyPayEntity, Runnable onConfirmClickListener) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.lib_cashier_sdk_dialog_digital_money, (ViewGroup) null, false);
        this.containerBankChannel = inflate.findViewById(R.id.lib_cashier_dialog_digital_money_bank_channel_container);
        this.containerCoupon = inflate.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_container);
        this.cyberMoneyBankRecyclerView = (RecyclerView) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_recycler);
        this.cyberMoneyCouponRecyclerView = (RecyclerView) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_recycler);
        this.mTvPayPriceUnit = (TextView) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_price_unit);
        this.mTvPayPriceView = (RollingDigitTextView) inflate.findViewById(R.id.lib_cashier_pay_cyber_money_rolling_price);
        this.mTvPaySubtitle = (TextView) inflate.findViewById(R.id.lib_cashier_cyber_money_price_subTitle);
        TextView textView = (TextView) inflate.findViewById(R.id.lib_cashier_pay_cyber_money_price_extend);
        this.mTvPayPriceExtend = textView;
        m0.a(textView, (byte) 3);
        m0.a(this.mTvPayPriceView, (byte) 3);
        m0.a(this.mTvPaySubtitle, (byte) 3);
        TextView textView2 = this.mTvPaySubtitle;
        if (textView2 != null) {
            textView2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
        }
        RollingDigitTextView rollingDigitTextView = this.mTvPayPriceView;
        if (rollingDigitTextView != null) {
            rollingDigitTextView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
        }
        TextView textView3 = this.mTvPayPriceUnit;
        if (textView3 != null) {
            textView3.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
        }
        TextView textView4 = this.mTvPayPriceExtend;
        if (textView4 != null) {
            textView4.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
        }
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_header);
        if (relativeLayout != null) {
            relativeLayout.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_digital_dialog_header_dark : R.drawable.lib_cashier_sdk_digital_dialog_header);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_close);
        if (imageView != null) {
            imageView.setOnClickListener(new b(activity, digitalMoneyPayEntity, onConfirmClickListener));
        }
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_confirm);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(new c(this, activity, digitalMoneyPayEntity, onConfirmClickListener));
        }
        TextView textView5 = (TextView) inflate.findViewById(R.id.lib_cashier_sdk_dialog_digital_money_title);
        if (textView5 != null) {
            String str = digitalMoneyPayEntity.orderDesc;
            if (str == null) {
                str = "";
            }
            textView5.setText(str);
        }
        List<DigitalMoneyBankCard> list = digitalMoneyPayEntity.bankCardList;
        Intrinsics.checkExpressionValueIsNotNull(list, "digitalMoneyPayEntity.bankCardList");
        RecyclerView recyclerView = this.cyberMoneyBankRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
            recyclerView.setAdapter(new DigitalBankAdapter(activity, list));
        }
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_close);
        if (imageView2 != null) {
            imageView2.setOnClickListener(new d(activity, digitalMoneyPayEntity, onConfirmClickListener));
        }
        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_back);
        if (imageView3 != null) {
            imageView3.setOnClickListener(new e(activity, digitalMoneyPayEntity, onConfirmClickListener));
        }
        TextView textView6 = (TextView) inflate.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_not_use);
        if (textView6 != null) {
            textView6.setOnClickListener(new f(activity, digitalMoneyPayEntity, onConfirmClickListener));
        }
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(acti\u2026\n            })\n        }");
        return inflate;
    }

    private final void t() {
        AllCouponAdapter allCouponAdapter = new AllCouponAdapter(this.activity, this.cyberMoneyCouponSourceList);
        allCouponAdapter.m(new g());
        RecyclerView recyclerView = this.cyberMoneyCouponRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new WrapContentLinearLayoutManager(this.activity, 1, false));
        }
        RecyclerView recyclerView2 = this.cyberMoneyCouponRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(allCouponAdapter);
        }
    }

    private final void v(View contentView) {
        if (contentView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_header);
            if (relativeLayout != null) {
                relativeLayout.setBackgroundResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_digital_dialog_header_dark : R.drawable.lib_cashier_sdk_digital_dialog_header);
            }
            ImageView imageView = (ImageView) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_close);
            if (imageView != null) {
                imageView.setImageResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_icon_bold_close_dark : R.drawable.lib_cashier_sdk_icon_bold_close);
            }
            TextView textView = (TextView) contentView.findViewById(R.id.lib_cashier_sdk_dialog_digital_money_title);
            if (textView != null) {
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            }
            ViewGroup viewGroup = (ViewGroup) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_price_container);
            if (viewGroup != null) {
                viewGroup.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
            FrameLayout frameLayout = (FrameLayout) contentView.findViewById(R.id.lib_cashier_digital_money_item_split_line_container);
            if (frameLayout != null) {
                frameLayout.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
            View findViewById = contentView.findViewById(R.id.lib_cashier_digital_money_item_split_line);
            if (findViewById != null) {
                findViewById.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
            }
            RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_recycler);
            if (recyclerView != null) {
                recyclerView.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
            ViewGroup viewGroup2 = (ViewGroup) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_confirm_container);
            if (viewGroup2 != null) {
                viewGroup2.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
            TextView textView2 = (TextView) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_not_use);
            if (textView2 != null) {
                textView2.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            }
            TextView textView3 = (TextView) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_title);
            if (textView3 != null) {
                textView3.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            }
            ImageView imageView2 = (ImageView) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_close);
            if (imageView2 != null) {
                imageView2.setImageResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_icon_bold_close_dark : R.drawable.lib_cashier_sdk_icon_bold_close);
            }
            ImageView imageView3 = (ImageView) contentView.findViewById(R.id.lib_cashier_dialog_digital_money_coupon_back);
            if (imageView3 != null) {
                imageView3.setImageResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_bg_back_selector_dark : R.drawable.lib_cashier_sdk_bg_back_selector);
            }
            View view = this.containerCoupon;
            if (view != null) {
                view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            }
        }
    }

    private final void w() {
        View view = this.containerCoupon;
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.containerBankChannel, "TranslationX", 0.0f, -this.screenWidth), ObjectAnimator.ofFloat(this.containerCoupon, "TranslationX", this.screenWidth, 0.0f));
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(300L);
        animatorSet.addListener(new h());
        animatorSet.start();
    }

    private final void y(com.jd.lib.cashier.sdk.pay.dialog.e couponEntity) {
        String str;
        String str2;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b2;
        CashierPayEntity cashierPayEntity;
        FragmentActivity fragmentActivity = this.activity;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        TopFloor topFloor = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b2 = x.b()) == null || (cashierPayEntity = b2.K) == null) ? null : cashierPayEntity.topFloor;
        if (topFloor == null || (str = topFloor.moneyFlag) == null) {
            str = "";
        }
        if (topFloor == null || (str2 = topFloor.payprice) == null) {
            str2 = "";
        }
        TopPriceAnimationInfo topPriceAnimationInfo = couponEntity != null ? couponEntity.getTopPriceAnimationInfo() : null;
        String str3 = topPriceAnimationInfo != null ? topPriceAnimationInfo.price : null;
        if (!TextUtils.isEmpty(str3)) {
            str2 = str3;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            j0.d(this.mTvPayPriceView);
            j0.d(this.mTvPayPriceUnit);
            TextView textView = this.mTvPayPriceUnit;
            if (textView != null) {
                textView.setText(str);
            }
            RollingDigitTextView rollingDigitTextView = this.mTvPayPriceView;
            if (rollingDigitTextView != null) {
                rollingDigitTextView.l(h0.b(this.activity, str2));
            }
            if (topPriceAnimationInfo != null && topPriceAnimationInfo.openAnimation) {
                RollingDigitTextView rollingDigitTextView2 = this.mTvPayPriceView;
                if (rollingDigitTextView2 != null) {
                    rollingDigitTextView2.setText(str2);
                }
                int length = str2 != null ? str2.length() : 0;
                int[] iArr = new int[length];
                for (int i2 = 0; i2 < length; i2++) {
                    iArr[i2] = (length - i2) + 8;
                }
                RollingDigitTextView rollingDigitTextView3 = this.mTvPayPriceView;
                if (rollingDigitTextView3 != null) {
                    rollingDigitTextView3.o(iArr);
                }
                RollingDigitTextView rollingDigitTextView4 = this.mTvPayPriceView;
                if (rollingDigitTextView4 != null) {
                    rollingDigitTextView4.h();
                }
                RollingDigitTextView rollingDigitTextView5 = this.mTvPayPriceView;
                if (rollingDigitTextView5 != null) {
                    rollingDigitTextView5.p();
                }
            } else {
                RollingDigitTextView rollingDigitTextView6 = this.mTvPayPriceView;
                if (rollingDigitTextView6 != null) {
                    rollingDigitTextView6.r();
                }
                RollingDigitTextView rollingDigitTextView7 = this.mTvPayPriceView;
                if (rollingDigitTextView7 != null) {
                    rollingDigitTextView7.setText(h0.b(this.activity, str2));
                }
            }
        } else {
            j0.b(this.mTvPayPriceView);
            j0.b(this.mTvPayPriceUnit);
        }
        if (!TextUtils.isEmpty(topPriceAnimationInfo != null ? topPriceAnimationInfo.priceExtend : null)) {
            j0.d(this.mTvPayPriceExtend);
            TextView textView2 = this.mTvPayPriceExtend;
            if (textView2 != null) {
                textView2.setText(topPriceAnimationInfo != null ? topPriceAnimationInfo.priceExtend : null);
            }
        } else {
            j0.b(this.mTvPayPriceExtend);
            TextView textView3 = this.mTvPayPriceExtend;
            if (textView3 != null) {
                textView3.setText("");
            }
        }
        if (!TextUtils.isEmpty(topPriceAnimationInfo != null ? topPriceAnimationInfo.subTitle : null)) {
            TextView textView4 = this.mTvPaySubtitle;
            if (textView4 != null) {
                textView4.setText(topPriceAnimationInfo != null ? topPriceAnimationInfo.subTitle : null);
            }
            j0.d(this.mTvPaySubtitle);
            return;
        }
        j0.b(this.mTvPaySubtitle);
        TextView textView5 = this.mTvPaySubtitle;
        if (textView5 != null) {
            textView5.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void z() {
        RecyclerView recyclerView = this.cyberMoneyBankRecyclerView;
        RecyclerView.Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        if (!(adapter instanceof DigitalBankAdapter)) {
            adapter = null;
        }
        DigitalBankAdapter digitalBankAdapter = (DigitalBankAdapter) adapter;
        DigitalMoneyBankCard h2 = digitalBankAdapter != null ? digitalBankAdapter.h() : null;
        y(h2 != null ? h2.selectedCoupon : null);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@Nullable FragmentActivity activity) {
        CashierPayViewModel x;
        CyberMoneyCouponLiveData z;
        if (g0.a(activity)) {
            CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(activity instanceof CashierPayActivity) ? null : activity);
            if (cashierPayActivity != null && (x = cashierPayActivity.x()) != null && (z = x.z()) != null) {
                z.observe(activity, this);
            }
            com.jd.lib.cashier.sdk.b.i.e.e("CLICK_CYBER_MONEY_COUPON", null, new l(activity), 2, null);
            com.jd.lib.cashier.sdk.b.i.e.e("CLICK_CYBER_MONEY_CHANNEL", null, new m(), 2, null);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        this.cyberMoneyDialog = null;
        this.cyberMoneyCouponSourceList.clear();
    }

    @NotNull
    /* renamed from: s  reason: from getter */
    public final FragmentActivity getActivity() {
        return this.activity;
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: u  reason: merged with bridge method [inline-methods] */
    public void onChanged(@Nullable com.jd.lib.cashier.sdk.pay.aac.livedata.a.m cyberMoneyCoupon) {
        Dialog dialog = this.cyberMoneyDialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        RecyclerView recyclerView = this.cyberMoneyBankRecyclerView;
        RecyclerView.Adapter adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        if (!(adapter instanceof DigitalBankAdapter)) {
            adapter = null;
        }
        DigitalBankAdapter digitalBankAdapter = (DigitalBankAdapter) adapter;
        DigitalMoneyBankCard h2 = digitalBankAdapter != null ? digitalBankAdapter.h() : null;
        if (Intrinsics.areEqual(h2, cyberMoneyCoupon != null ? cyberMoneyCoupon.a : null)) {
            A(h2, cyberMoneyCoupon);
            t();
            w();
        }
    }

    public final void x(@Nullable DigitalMoneyPayEntity digitalMoneyPayEntity, @NotNull Runnable onConfirmClickListener) {
        if (!g0.a(this.activity) || digitalMoneyPayEntity == null) {
            return;
        }
        List<DigitalMoneyBankCard> list = digitalMoneyPayEntity.bankCardList;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.screenWidth = DpiUtil.getAppWidth(this.activity);
        this.cyberMoneyDialog = com.jd.lib.cashier.sdk.core.utils.j.b(this.activity);
        View r = r(this.activity, digitalMoneyPayEntity, onConfirmClickListener);
        v(r);
        com.jd.lib.cashier.sdk.core.utils.j.a(this.cyberMoneyDialog, r, 0.5f);
        Dialog dialog = this.cyberMoneyDialog;
        if (dialog != null) {
            dialog.setOnShowListener(new i());
        }
        Dialog dialog2 = this.cyberMoneyDialog;
        if (dialog2 != null) {
            dialog2.setOnDismissListener(new j());
        }
        Dialog dialog3 = this.cyberMoneyDialog;
        if (dialog3 != null) {
            dialog3.show();
        }
        com.jd.lib.cashier.sdk.h.h.c.c(this.activity, new k());
    }
}
