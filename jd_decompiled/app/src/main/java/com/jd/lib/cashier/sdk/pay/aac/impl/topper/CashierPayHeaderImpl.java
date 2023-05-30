package com.jd.lib.cashier.sdk.pay.aac.impl.topper;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.des.IDes;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView;
import com.jd.lib.cashier.sdk.core.ui.widget.CashierNoticeView;
import com.jd.lib.cashier.sdk.core.ui.widget.RollingDigitTextView;
import com.jd.lib.cashier.sdk.core.ui.widget.SecondCountDownView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.h0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.DfPriceInfo;
import com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo;
import com.jd.lib.cashier.sdk.pay.bean.HomeLetter;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceMtaObject;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.UpdateHeaderFloorInfo;
import com.jd.lib.cashier.sdk.pay.dialog.g;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002B\u000f\u0012\u0006\u0010g\u001a\u00020e\u00a2\u0006\u0004\bh\u0010iJ\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000e\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0014\u0010\u0012J\u0017\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0015H\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0019\u0010\u0012J7\u0010 \u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001aH\u0002\u00a2\u0006\u0004\b \u0010!J#\u0010#\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\"\u001a\u0004\u0018\u00010\u001aH\u0002\u00a2\u0006\u0004\b#\u0010$J#\u0010'\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001a2\b\u0010&\u001a\u0004\u0018\u00010%H\u0002\u00a2\u0006\u0004\b'\u0010(J\u0019\u0010+\u001a\u00020\u00062\b\u0010*\u001a\u0004\u0018\u00010)H\u0002\u00a2\u0006\u0004\b+\u0010,J\u0017\u0010/\u001a\u00020\u00062\u0006\u0010.\u001a\u00020-H\u0002\u00a2\u0006\u0004\b/\u00100J\u0017\u00101\u001a\u00020\u00062\u0006\u0010.\u001a\u00020-H\u0002\u00a2\u0006\u0004\b1\u00100J\u0017\u00104\u001a\u00020\u00062\u0006\u00103\u001a\u000202H\u0002\u00a2\u0006\u0004\b4\u00105J\u0017\u00106\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b6\u0010\u000fJ\u0017\u00107\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b7\u0010\u000fJ\u0017\u00108\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b8\u0010\u000fJ\u001f\u0010=\u001a\u00020<2\u0006\u00109\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020:H\u0002\u00a2\u0006\u0004\b=\u0010>J\u001f\u0010?\u001a\u00020<2\u0006\u00109\u001a\u00020\u001a2\u0006\u0010;\u001a\u00020:H\u0002\u00a2\u0006\u0004\b?\u0010>R\u0018\u0010B\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010E\u001a\u00020C8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0019\u0010DR\u0018\u0010G\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u0010AR\u0018\u0010J\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010IR\u0018\u0010L\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010AR\u0018\u0010O\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0014\u0010NR\u0018\u0010S\u001a\u0004\u0018\u00010P8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010U\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bT\u0010AR\u0018\u0010W\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bV\u0010AR\u0018\u0010X\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b=\u0010AR\u0018\u0010\\\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bZ\u0010[R\u0018\u0010_\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b]\u0010^R\u0018\u0010a\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b`\u0010AR\u0018\u0010d\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010cR\u0016\u0010g\u001a\u00020e8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000e\u0010f\u00a8\u0006j"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/impl/topper/CashierPayHeaderImpl;", "", "Landroidx/lifecycle/Observer;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/r;", "Landroid/view/Window;", "window", "", JshopConst.JSHOP_PROMOTIO_H, "(Landroid/view/Window;)V", "Landroidx/fragment/app/FragmentActivity;", "activity", "f", "(Landroidx/fragment/app/FragmentActivity;)V", "httpEvent", "u", "(Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/r;)V", "F", "onDestroy", "()V", "onChangeSkin", "q", "Lcom/jd/lib/cashier/sdk/pay/bean/DfPriceInfo;", "dfPriceInfo", "z", "(Lcom/jd/lib/cashier/sdk/pay/bean/DfPriceInfo;)V", "t", "", "isGradualPayFlag", "Lcom/jd/lib/cashier/sdk/pay/bean/GradualPayInfo;", "gradualPayInfo", "gradualPayFlag", "graduallyPayAmount", "C", "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/GradualPayInfo;Ljava/lang/String;Ljava/lang/String;)V", "title", "B", "(Ljava/lang/String;Ljava/lang/String;)V", "Landroid/view/View;", "anchorView", "A", "(Ljava/lang/String;Landroid/view/View;)V", "Lcom/jd/lib/cashier/sdk/pay/bean/HomeLetter;", "homeLetter", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "(Lcom/jd/lib/cashier/sdk/pay/bean/HomeLetter;)V", "Lcom/jd/lib/cashier/sdk/pay/bean/TopFloor;", "topFloor", "G", "(Lcom/jd/lib/cashier/sdk/pay/bean/TopFloor;)V", JshopConst.JSHOP_PROMOTIO_W, "Lcom/jd/lib/cashier/sdk/core/model/CashierCommonPopConfig;", "cashierPopConfig", JshopConst.JSHOP_PROMOTIO_Y, "(Lcom/jd/lib/cashier/sdk/core/model/CashierCommonPopConfig;)V", JshopConst.JSHOP_PROMOTIO_X, "E", "v", "value", "", "cashierA", "Landroid/widget/TextView;", "r", "(Ljava/lang/String;Z)Landroid/widget/TextView;", "s", "k", "Landroid/widget/TextView;", "mTvPriceSubTitle", "Lcom/jd/lib/cashier/sdk/pay/aac/impl/topper/a;", "Lcom/jd/lib/cashier/sdk/pay/aac/impl/topper/a;", "largePaymentProxy", "j", "mTvPriceExtend", "Lcom/jd/lib/cashier/sdk/core/ui/widget/RollingDigitTextView;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/RollingDigitTextView;", "mPayPriceView", PersonalConstants.ICON_STYLE_N, "mSuffixTextView", "Landroid/widget/ImageView;", "Landroid/widget/ImageView;", "mGradualEditIcon", "Landroid/view/ViewGroup;", NotifyType.LIGHTS, "Landroid/view/ViewGroup;", "mCountDownViewContainer", "m", "mPrefixTextView", "i", "mTvPayPriceUnit", "mGradualTitle", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView;", "o", "Lcom/jd/lib/cashier/sdk/core/ui/widget/AbsCountdownView;", "mCountDownView", "g", "Landroid/view/View;", "mRootView", "p", "mChargeNumberView", "Lcom/jd/lib/cashier/sdk/core/ui/widget/CashierNoticeView;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/CashierNoticeView;", "mNoticeView", "Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;", "Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;", "mCashierPayActivity", "<init>", "(Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierPayHeaderImpl implements Object, Observer<r>, com.jd.lib.cashier.sdk.d.d.a, com.jd.lib.cashier.sdk.core.aac.e, Observer {
    private static final String v = "CashierPayHeaderImpl";

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private View mRootView;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private RollingDigitTextView mPayPriceView;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private TextView mTvPayPriceUnit;

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private TextView mTvPriceExtend;

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private TextView mTvPriceSubTitle;

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private ViewGroup mCountDownViewContainer;

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private TextView mPrefixTextView;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private TextView mSuffixTextView;

    /* renamed from: o  reason: from kotlin metadata */
    private AbsCountdownView mCountDownView;

    /* renamed from: p  reason: from kotlin metadata */
    private TextView mChargeNumberView;

    /* renamed from: q  reason: from kotlin metadata */
    private ImageView mGradualEditIcon;

    /* renamed from: r  reason: from kotlin metadata */
    private TextView mGradualTitle;

    /* renamed from: s  reason: from kotlin metadata */
    private CashierNoticeView mNoticeView;

    /* renamed from: t  reason: from kotlin metadata */
    private final com.jd.lib.cashier.sdk.pay.aac.impl.topper.a largePaymentProxy = new com.jd.lib.cashier.sdk.pay.aac.impl.topper.a();

    /* renamed from: u  reason: from kotlin metadata */
    private final CashierPayActivity mCashierPayActivity;

    /* loaded from: classes14.dex */
    public static final class a implements AbsCountdownView.a {
        final /* synthetic */ CashierCommonPopConfig b;

        a(CashierCommonPopConfig cashierCommonPopConfig, Ref.LongRef longRef) {
            this.b = cashierCommonPopConfig;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView.a
        public void onFinish() {
            PayTaskStackManager.removeCashierFriendPayDialogTask();
            CashierPayHeaderImpl cashierPayHeaderImpl = CashierPayHeaderImpl.this;
            CashierCommonPopConfig cashierPopConfig = this.b;
            Intrinsics.checkExpressionValueIsNotNull(cashierPopConfig, "cashierPopConfig");
            cashierPayHeaderImpl.y(cashierPopConfig);
        }
    }

    /* loaded from: classes14.dex */
    public static final class b implements AbsCountdownView.a {
        final /* synthetic */ CashierCommonPopConfig b;

        b(CashierCommonPopConfig cashierCommonPopConfig, Ref.LongRef longRef) {
            this.b = cashierCommonPopConfig;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView.a
        public void onFinish() {
            PayTaskStackManager.removeCashierFriendPayDialogTask();
            CashierPayHeaderImpl cashierPayHeaderImpl = CashierPayHeaderImpl.this;
            CashierCommonPopConfig cashierPopConfig = this.b;
            Intrinsics.checkExpressionValueIsNotNull(cashierPopConfig, "cashierPopConfig");
            cashierPayHeaderImpl.y(cashierPopConfig);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c implements com.jd.lib.cashier.sdk.b.d.b.b {
        c() {
        }

        @Override // com.jd.lib.cashier.sdk.b.d.b.b
        public final void a(String str) {
            CashierPayHeaderImpl.this.mCashierPayActivity.w().c(CashierPayHeaderImpl.this.mCashierPayActivity, str);
            CashierPayHeaderImpl.this.mCashierPayActivity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class d implements View.OnClickListener {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f3775h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ GradualPayInfo f3776i;

        d(String str, GradualPayInfo gradualPayInfo) {
            this.f3775h = str;
            this.f3776i = gradualPayInfo;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
            CashierPayActivity cashierPayActivity = CashierPayHeaderImpl.this.mCashierPayActivity;
            String str = this.f3775h;
            if (str == null) {
                str = "";
            }
            d.Y(cashierPayActivity, str);
            g.f(CashierPayHeaderImpl.this.mCashierPayActivity, this.f3776i);
        }
    }

    /* loaded from: classes14.dex */
    public static final class e implements AbsCountdownView.a {
        final /* synthetic */ CashierCommonPopConfig b;

        e(CashierCommonPopConfig cashierCommonPopConfig, Ref.LongRef longRef) {
            this.b = cashierCommonPopConfig;
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView.a
        public void onFinish() {
            PayTaskStackManager.removeCashierFriendPayDialogTask();
            CashierPayHeaderImpl cashierPayHeaderImpl = CashierPayHeaderImpl.this;
            CashierCommonPopConfig cashierPopConfig = this.b;
            Intrinsics.checkExpressionValueIsNotNull(cashierPopConfig, "cashierPopConfig");
            cashierPayHeaderImpl.y(cashierPopConfig);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdateHeaderFloorInfo;", "updateHeaderFloorInfo", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/UpdateHeaderFloorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class f extends Lambda implements Function1<UpdateHeaderFloorInfo, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UpdateHeaderFloorInfo updateHeaderFloorInfo) {
            invoke2(updateHeaderFloorInfo);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull UpdateHeaderFloorInfo updateHeaderFloorInfo) {
            String str;
            String str2;
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            CashierPayEntity cashierPayEntity;
            TopPriceMtaObject topPriceMtaObject = updateHeaderFloorInfo.getTopPriceMtaObject();
            TopPriceAnimationInfo topPriceAnimationInfo = updateHeaderFloorInfo.getTopPriceAnimationInfo();
            com.jd.lib.cashier.sdk.core.utils.r.b(CashierPayHeaderImpl.v, "topPriceAnimationInfo = " + topPriceAnimationInfo);
            FragmentActivity fragmentActivity = this.$activity;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            TopFloor topFloor = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null || (cashierPayEntity = b.K) == null) ? null : cashierPayEntity.topFloor;
            if (topFloor == null || (str = topFloor.moneyFlag) == null) {
                str = "";
            }
            if (topFloor == null || (str2 = topFloor.payprice) == null) {
                str2 = "";
            }
            String str3 = topPriceAnimationInfo != null ? topPriceAnimationInfo.price : null;
            if (!TextUtils.isEmpty(str3)) {
                str2 = str3;
            }
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                j0.b(CashierPayHeaderImpl.this.mPayPriceView);
                j0.b(CashierPayHeaderImpl.this.mTvPayPriceUnit);
            } else {
                j0.d(CashierPayHeaderImpl.this.mPayPriceView);
                j0.d(CashierPayHeaderImpl.this.mTvPayPriceUnit);
                TextView textView = CashierPayHeaderImpl.this.mTvPayPriceUnit;
                if (textView != null) {
                    textView.setText(str);
                }
                RollingDigitTextView rollingDigitTextView = CashierPayHeaderImpl.this.mPayPriceView;
                if (rollingDigitTextView != null) {
                    rollingDigitTextView.l(h0.b(CashierPayHeaderImpl.this.mCashierPayActivity, str2));
                }
                if (topPriceAnimationInfo == null || !topPriceAnimationInfo.openAnimation) {
                    RollingDigitTextView rollingDigitTextView2 = CashierPayHeaderImpl.this.mPayPriceView;
                    if (rollingDigitTextView2 != null) {
                        rollingDigitTextView2.r();
                    }
                    RollingDigitTextView rollingDigitTextView3 = CashierPayHeaderImpl.this.mPayPriceView;
                    if (rollingDigitTextView3 != null) {
                        rollingDigitTextView3.setText(h0.b(CashierPayHeaderImpl.this.mCashierPayActivity, str2));
                    }
                } else {
                    RollingDigitTextView rollingDigitTextView4 = CashierPayHeaderImpl.this.mPayPriceView;
                    if (rollingDigitTextView4 != null) {
                        rollingDigitTextView4.setText(str2);
                    }
                    int length = str2 != null ? str2.length() : 0;
                    int[] iArr = new int[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        iArr[i2] = (length - i2) + 8;
                    }
                    RollingDigitTextView rollingDigitTextView5 = CashierPayHeaderImpl.this.mPayPriceView;
                    if (rollingDigitTextView5 != null) {
                        rollingDigitTextView5.o(iArr);
                    }
                    RollingDigitTextView rollingDigitTextView6 = CashierPayHeaderImpl.this.mPayPriceView;
                    if (rollingDigitTextView6 != null) {
                        rollingDigitTextView6.h();
                    }
                    RollingDigitTextView rollingDigitTextView7 = CashierPayHeaderImpl.this.mPayPriceView;
                    if (rollingDigitTextView7 != null) {
                        rollingDigitTextView7.p();
                    }
                }
            }
            RollingDigitTextView rollingDigitTextView8 = CashierPayHeaderImpl.this.mPayPriceView;
            if (rollingDigitTextView8 != null) {
                rollingDigitTextView8.setContentDescription(str + str2);
            }
            if (!TextUtils.isEmpty(topPriceAnimationInfo != null ? topPriceAnimationInfo.priceExtend : null)) {
                j0.d(CashierPayHeaderImpl.this.mTvPriceExtend);
                TextView textView2 = CashierPayHeaderImpl.this.mTvPriceExtend;
                if (textView2 != null) {
                    textView2.setText(topPriceAnimationInfo != null ? topPriceAnimationInfo.priceExtend : null);
                }
            } else {
                j0.b(CashierPayHeaderImpl.this.mTvPriceExtend);
                TextView textView3 = CashierPayHeaderImpl.this.mTvPriceExtend;
                if (textView3 != null) {
                    textView3.setText("");
                }
            }
            TextView textView4 = CashierPayHeaderImpl.this.mTvPriceSubTitle;
            if (textView4 != null) {
                textView4.setText("");
            }
            com.jd.lib.cashier.sdk.h.e.a.d().h0(this.$activity, topPriceMtaObject != null ? topPriceMtaObject.code : null, topPriceMtaObject != null ? topPriceMtaObject.uniqueChannelId : null, topPriceMtaObject != null ? topPriceMtaObject.couponType : null);
        }
    }

    public CashierPayHeaderImpl(@NotNull CashierPayActivity cashierPayActivity) {
        this.mCashierPayActivity = cashierPayActivity;
    }

    private final void A(String gradualPayFlag, View anchorView) {
        if (Intrinsics.areEqual("1", gradualPayFlag)) {
            com.jd.lib.cashier.sdk.b.i.c.b.a(this.mCashierPayActivity, anchorView);
        }
    }

    private final void B(String isGradualPayFlag, String title) {
        if ((!Intrinsics.areEqual("1", isGradualPayFlag)) != false) {
            j0.b(this.mGradualTitle);
        } else if (TextUtils.isEmpty(title)) {
            j0.b(this.mGradualTitle);
        } else {
            A(isGradualPayFlag, this.mGradualTitle);
            j0.d(this.mGradualTitle);
            TextView textView = this.mGradualTitle;
            if (textView != null) {
                textView.setText(title);
            }
        }
    }

    private final void C(String isGradualPayFlag, GradualPayInfo gradualPayInfo, String gradualPayFlag, String graduallyPayAmount) {
        String str;
        CashierPayViewModel x;
        com.jd.lib.cashier.sdk.h.c.a b2;
        CashierPayEntity cashierPayEntity;
        TopFloor topFloor;
        com.jd.lib.cashier.sdk.core.utils.r.b(v, "gradualPayInfo = " + gradualPayInfo);
        if (Intrinsics.areEqual("1", isGradualPayFlag)) {
            CashierPayActivity cashierPayActivity = this.mCashierPayActivity;
            String str2 = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b2 = x.b()) == null || (cashierPayEntity = b2.K) == null || (topFloor = cashierPayEntity.topFloor) == null) ? null : topFloor.payprice;
            String str3 = "";
            com.jd.lib.cashier.sdk.h.e.a.d().Z(this.mCashierPayActivity, str2 != null ? str2 : "");
            j0.d(this.mGradualEditIcon);
            ImageView imageView = this.mGradualEditIcon;
            if (imageView != null) {
                imageView.setOnClickListener(new d(str2, gradualPayInfo));
            }
            if (gradualPayInfo != null && (str = gradualPayInfo.popup) != null) {
                str3 = str;
            }
            boolean areEqual = Intrinsics.areEqual("1", str3);
            boolean z = !TextUtils.isEmpty(gradualPayFlag) && TextUtils.isEmpty(graduallyPayAmount);
            boolean z2 = TextUtils.isEmpty(gradualPayFlag) && TextUtils.isEmpty(graduallyPayAmount);
            if ((areEqual && z) || (areEqual && z2)) {
                g.f(this.mCashierPayActivity, gradualPayInfo);
            }
        } else {
            j0.b(this.mGradualEditIcon);
        }
        B(isGradualPayFlag, gradualPayInfo != null ? gradualPayInfo.graduallyPayTip : null);
    }

    private final void D(HomeLetter homeLetter) {
        if (homeLetter != null && !TextUtils.isEmpty(homeLetter.tip)) {
            j0.d(this.mNoticeView);
            CashierNoticeView cashierNoticeView = this.mNoticeView;
            if (cashierNoticeView != null) {
                String str = homeLetter.tip;
                Intrinsics.checkExpressionValueIsNotNull(str, "homeLetter.tip");
                cashierNoticeView.g(str);
                return;
            }
            return;
        }
        j0.b(this.mNoticeView);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x008d, code lost:
        r11 = kotlin.text.StringsKt__StringsJVMKt.replace$default(r5, "{$cancelTime}", com.jd.dynamic.DYConstants.DY_REGEX_VERTICAL_LINE, false, 4, (java.lang.Object) null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void E(com.jd.lib.cashier.sdk.pay.aac.livedata.a.r r18) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.aac.impl.topper.CashierPayHeaderImpl.E(com.jd.lib.cashier.sdk.pay.aac.livedata.a.r):void");
    }

    private final void G(TopFloor topFloor) {
        if (!TextUtils.isEmpty(topFloor.moneyFlag) && !TextUtils.isEmpty(topFloor.payprice)) {
            j0.d(this.mPayPriceView);
            j0.d(this.mTvPayPriceUnit);
            TextView textView = this.mTvPayPriceUnit;
            if (textView != null) {
                textView.setText(topFloor.moneyFlag);
            }
            RollingDigitTextView rollingDigitTextView = this.mPayPriceView;
            if (rollingDigitTextView != null) {
                rollingDigitTextView.r();
            }
            RollingDigitTextView rollingDigitTextView2 = this.mPayPriceView;
            if (rollingDigitTextView2 != null) {
                rollingDigitTextView2.setText(h0.b(this.mCashierPayActivity, topFloor.payprice));
            }
        } else {
            j0.b(this.mPayPriceView);
            j0.b(this.mTvPayPriceUnit);
        }
        j0.b(this.mTvPriceExtend);
        j0.b(this.mTvPriceSubTitle);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x006c A[Catch: Exception -> 0x00b8, TryCatch #0 {Exception -> 0x00b8, blocks: (B:2:0x0000, B:5:0x0014, B:8:0x0025, B:10:0x0029, B:11:0x0030, B:13:0x0034, B:14:0x003b, B:16:0x003f, B:26:0x0068, B:28:0x006c, B:29:0x0073, B:32:0x0079, B:33:0x0080, B:35:0x0084, B:36:0x008b, B:38:0x008f, B:39:0x009a, B:41:0x009e, B:42:0x00a5, B:44:0x00a9, B:45:0x00b0, B:47:0x00b4, B:17:0x0047, B:19:0x004b, B:20:0x0052, B:22:0x0056, B:23:0x005d, B:25:0x0061), top: B:52:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079 A[Catch: Exception -> 0x00b8, TRY_ENTER, TryCatch #0 {Exception -> 0x00b8, blocks: (B:2:0x0000, B:5:0x0014, B:8:0x0025, B:10:0x0029, B:11:0x0030, B:13:0x0034, B:14:0x003b, B:16:0x003f, B:26:0x0068, B:28:0x006c, B:29:0x0073, B:32:0x0079, B:33:0x0080, B:35:0x0084, B:36:0x008b, B:38:0x008f, B:39:0x009a, B:41:0x009e, B:42:0x00a5, B:44:0x00a9, B:45:0x00b0, B:47:0x00b4, B:17:0x0047, B:19:0x004b, B:20:0x0052, B:22:0x0056, B:23:0x005d, B:25:0x0061), top: B:52:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0084 A[Catch: Exception -> 0x00b8, TryCatch #0 {Exception -> 0x00b8, blocks: (B:2:0x0000, B:5:0x0014, B:8:0x0025, B:10:0x0029, B:11:0x0030, B:13:0x0034, B:14:0x003b, B:16:0x003f, B:26:0x0068, B:28:0x006c, B:29:0x0073, B:32:0x0079, B:33:0x0080, B:35:0x0084, B:36:0x008b, B:38:0x008f, B:39:0x009a, B:41:0x009e, B:42:0x00a5, B:44:0x00a9, B:45:0x00b0, B:47:0x00b4, B:17:0x0047, B:19:0x004b, B:20:0x0052, B:22:0x0056, B:23:0x005d, B:25:0x0061), top: B:52:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008f A[Catch: Exception -> 0x00b8, TryCatch #0 {Exception -> 0x00b8, blocks: (B:2:0x0000, B:5:0x0014, B:8:0x0025, B:10:0x0029, B:11:0x0030, B:13:0x0034, B:14:0x003b, B:16:0x003f, B:26:0x0068, B:28:0x006c, B:29:0x0073, B:32:0x0079, B:33:0x0080, B:35:0x0084, B:36:0x008b, B:38:0x008f, B:39:0x009a, B:41:0x009e, B:42:0x00a5, B:44:0x00a9, B:45:0x00b0, B:47:0x00b4, B:17:0x0047, B:19:0x004b, B:20:0x0052, B:22:0x0056, B:23:0x005d, B:25:0x0061), top: B:52:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009e A[Catch: Exception -> 0x00b8, TryCatch #0 {Exception -> 0x00b8, blocks: (B:2:0x0000, B:5:0x0014, B:8:0x0025, B:10:0x0029, B:11:0x0030, B:13:0x0034, B:14:0x003b, B:16:0x003f, B:26:0x0068, B:28:0x006c, B:29:0x0073, B:32:0x0079, B:33:0x0080, B:35:0x0084, B:36:0x008b, B:38:0x008f, B:39:0x009a, B:41:0x009e, B:42:0x00a5, B:44:0x00a9, B:45:0x00b0, B:47:0x00b4, B:17:0x0047, B:19:0x004b, B:20:0x0052, B:22:0x0056, B:23:0x005d, B:25:0x0061), top: B:52:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a9 A[Catch: Exception -> 0x00b8, TryCatch #0 {Exception -> 0x00b8, blocks: (B:2:0x0000, B:5:0x0014, B:8:0x0025, B:10:0x0029, B:11:0x0030, B:13:0x0034, B:14:0x003b, B:16:0x003f, B:26:0x0068, B:28:0x006c, B:29:0x0073, B:32:0x0079, B:33:0x0080, B:35:0x0084, B:36:0x008b, B:38:0x008f, B:39:0x009a, B:41:0x009e, B:42:0x00a5, B:44:0x00a9, B:45:0x00b0, B:47:0x00b4, B:17:0x0047, B:19:0x004b, B:20:0x0052, B:22:0x0056, B:23:0x005d, B:25:0x0061), top: B:52:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b4 A[Catch: Exception -> 0x00b8, TRY_LEAVE, TryCatch #0 {Exception -> 0x00b8, blocks: (B:2:0x0000, B:5:0x0014, B:8:0x0025, B:10:0x0029, B:11:0x0030, B:13:0x0034, B:14:0x003b, B:16:0x003f, B:26:0x0068, B:28:0x006c, B:29:0x0073, B:32:0x0079, B:33:0x0080, B:35:0x0084, B:36:0x008b, B:38:0x008f, B:39:0x009a, B:41:0x009e, B:42:0x00a5, B:44:0x00a9, B:45:0x00b0, B:47:0x00b4, B:17:0x0047, B:19:0x004b, B:20:0x0052, B:22:0x0056, B:23:0x005d, B:25:0x0061), top: B:52:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void q() {
        /*
            r4 = this;
            java.lang.String r0 = "2"
            com.jd.lib.cashier.sdk.core.utils.m r1 = com.jd.lib.cashier.sdk.core.utils.m.f()     // Catch: java.lang.Exception -> Lb8
            java.lang.String r1 = r1.j()     // Catch: java.lang.Exception -> Lb8
            boolean r0 = android.text.TextUtils.equals(r0, r1)     // Catch: java.lang.Exception -> Lb8
            java.lang.String r1 = "#f2270c"
            java.lang.String r2 = "#1A1A1A"
            if (r0 != 0) goto L47
            java.lang.String r0 = "3"
            com.jd.lib.cashier.sdk.core.utils.m r3 = com.jd.lib.cashier.sdk.core.utils.m.f()     // Catch: java.lang.Exception -> Lb8
            java.lang.String r3 = r3.j()     // Catch: java.lang.Exception -> Lb8
            boolean r0 = android.text.TextUtils.equals(r0, r3)     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L25
            goto L47
        L25:
            com.jd.lib.cashier.sdk.core.ui.widget.RollingDigitTextView r0 = r4.mPayPriceView     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L30
            int r3 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r1)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> Lb8
        L30:
            android.widget.TextView r0 = r4.mTvPayPriceUnit     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L3b
            int r3 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r1)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> Lb8
        L3b:
            android.widget.TextView r0 = r4.mTvPriceExtend     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L68
            int r3 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r1)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> Lb8
            goto L68
        L47:
            com.jd.lib.cashier.sdk.core.ui.widget.RollingDigitTextView r0 = r4.mPayPriceView     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L52
            int r3 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r2)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> Lb8
        L52:
            android.widget.TextView r0 = r4.mTvPayPriceUnit     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L5d
            int r3 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r2)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> Lb8
        L5d:
            android.widget.TextView r0 = r4.mTvPriceExtend     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L68
            int r3 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r2)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> Lb8
        L68:
            android.widget.TextView r0 = r4.mTvPriceSubTitle     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L73
            int r1 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r1)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r1)     // Catch: java.lang.Exception -> Lb8
        L73:
            android.widget.TextView r0 = r4.mGradualTitle     // Catch: java.lang.Exception -> Lb8
            java.lang.String r1 = "#8c8c8c"
            if (r0 == 0) goto L80
            int r3 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r1)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r3)     // Catch: java.lang.Exception -> Lb8
        L80:
            android.widget.TextView r0 = r4.mChargeNumberView     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L8b
            int r1 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r1)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r1)     // Catch: java.lang.Exception -> Lb8
        L8b:
            android.view.View r0 = r4.mRootView     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto L9a
            java.lang.String r1 = "#F5F5F5"
            java.lang.String r3 = "#141212"
            int r1 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r1, r3)     // Catch: java.lang.Exception -> Lb8
            r0.setBackgroundColor(r1)     // Catch: java.lang.Exception -> Lb8
        L9a:
            android.widget.TextView r0 = r4.mPrefixTextView     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto La5
            int r1 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r2)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r1)     // Catch: java.lang.Exception -> Lb8
        La5:
            android.widget.TextView r0 = r4.mSuffixTextView     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto Lb0
            int r1 = com.jd.lib.cashier.sdk.core.utils.JDDarkUtil.getDarkColor(r2)     // Catch: java.lang.Exception -> Lb8
            r0.setTextColor(r1)     // Catch: java.lang.Exception -> Lb8
        Lb0:
            com.jd.lib.cashier.sdk.core.ui.widget.AbsCountdownView r0 = r4.mCountDownView     // Catch: java.lang.Exception -> Lb8
            if (r0 == 0) goto Lc2
            r0.onChangeSkin()     // Catch: java.lang.Exception -> Lb8
            goto Lc2
        Lb8:
            r0 = move-exception
            java.lang.String r1 = com.jd.lib.cashier.sdk.pay.aac.impl.topper.CashierPayHeaderImpl.v
            java.lang.String r0 = r0.getMessage()
            com.jd.lib.cashier.sdk.core.utils.r.d(r1, r0)
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.aac.impl.topper.CashierPayHeaderImpl.q():void");
    }

    private final TextView r(String value, boolean cashierA) {
        int dip2px;
        TextView textView = new TextView(this.mCashierPayActivity);
        textView.setText(value);
        textView.setTextSize(14.0f);
        textView.setLines(1);
        textView.setIncludeFontPadding(false);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (cashierA) {
            dip2px = DpiUtil.dip2px(textView.getContext(), 2.0f);
        } else {
            dip2px = DpiUtil.dip2px(textView.getContext(), 4.0f);
        }
        layoutParams.rightMargin = dip2px;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private final TextView s(String value, boolean cashierA) {
        int dip2px;
        TextView textView = new TextView(this.mCashierPayActivity);
        textView.setText(value);
        textView.setTextSize(14.0f);
        textView.setLines(1);
        textView.setIncludeFontPadding(false);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (cashierA) {
            dip2px = DpiUtil.dip2px(textView.getContext(), 2.0f);
        } else {
            dip2px = DpiUtil.dip2px(textView.getContext(), 4.0f);
        }
        layoutParams.leftMargin = dip2px;
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private final void t() {
        j0.b(this.mChargeNumberView);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x008d, code lost:
        r11 = kotlin.text.StringsKt__StringsJVMKt.replace$default(r5, "{$cancelTime}", com.jd.dynamic.DYConstants.DY_REGEX_VERTICAL_LINE, false, 4, (java.lang.Object) null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void v(com.jd.lib.cashier.sdk.pay.aac.livedata.a.r r18) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.aac.impl.topper.CashierPayHeaderImpl.v(com.jd.lib.cashier.sdk.pay.aac.livedata.a.r):void");
    }

    private final void w(TopFloor topFloor) {
        String str;
        try {
            if (TextUtils.isEmpty(topFloor.mobileTitle) && TextUtils.isEmpty(topFloor.mobile)) {
                j0.b(this.mChargeNumberView);
                return;
            }
            j0.d(this.mChargeNumberView);
            IDes des = DependInitializer.getDes();
            if (des != null) {
                String str2 = topFloor.mobile;
                if (str2 == null) {
                    str2 = "";
                }
                str = des.decrypt(str2, "ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8ST2@y15c$*4e9%b8");
            } else {
                str = null;
            }
            topFloor.mobile = str;
            String str3 = topFloor.mobileTitle + topFloor.mobile;
            TextView textView = this.mChargeNumberView;
            if (textView != null) {
                textView.setText(str3);
            }
        } catch (Exception e2) {
            com.jd.lib.cashier.sdk.core.utils.r.d(v, e2.getMessage());
        }
    }

    private final void x(r httpEvent) {
        Long l2;
        long longValue;
        TopFloor topFloor = httpEvent.a;
        CashierCommonPopConfig cashierPopConfig = httpEvent.f3792f;
        if (TextUtils.isEmpty(topFloor.countdownTime)) {
            j0.b(this.mCountDownViewContainer);
            return;
        }
        Ref.LongRef longRef = new Ref.LongRef();
        try {
            Intrinsics.checkExpressionValueIsNotNull(topFloor.countdownTime, "topFloor.countdownTime");
            longRef.element = Float.parseFloat(r2) * ((float) 1000);
            if (TextUtils.isEmpty(topFloor.triggerCountdownTime)) {
                Long l3 = com.jd.lib.cashier.sdk.d.b.a.a;
                Intrinsics.checkExpressionValueIsNotNull(l3, "CashierConstant.CONSTANT_VALUE_12_HOUR");
                longValue = l3.longValue();
            } else {
                try {
                    String str = topFloor.triggerCountdownTime;
                    Intrinsics.checkExpressionValueIsNotNull(str, "topFloor.triggerCountdownTime");
                    l2 = Long.valueOf(Long.parseLong(str));
                } catch (Exception unused) {
                    l2 = com.jd.lib.cashier.sdk.d.b.a.a;
                }
                Intrinsics.checkExpressionValueIsNotNull(l2, "try {\n                to\u2026LUE_12_HOUR\n            }");
                longValue = l2.longValue();
            }
            long j2 = longRef.element;
            if (j2 > longValue * 1000) {
                j0.b(this.mCountDownViewContainer);
            } else if (j2 <= 0) {
                j0.b(this.mCountDownViewContainer);
                Intrinsics.checkExpressionValueIsNotNull(cashierPopConfig, "cashierPopConfig");
                y(cashierPopConfig);
            } else {
                j0.d(this.mCountDownViewContainer);
                ViewGroup viewGroup = this.mCountDownViewContainer;
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                String string = this.mCashierPayActivity.getString(R.string.lib_cashier_sdk_pay_count_down);
                Intrinsics.checkExpressionValueIsNotNull(string, "mCashierPayActivity.getS\u2026shier_sdk_pay_count_down)");
                TextView r = r(string, false);
                this.mPrefixTextView = r;
                ViewGroup viewGroup2 = this.mCountDownViewContainer;
                if (viewGroup2 != null) {
                    viewGroup2.addView(r);
                }
                SecondCountDownView secondCountDownView = new SecondCountDownView(this.mCashierPayActivity);
                this.mCountDownView = secondCountDownView;
                if (secondCountDownView != null) {
                    secondCountDownView.n(new b(cashierPopConfig, longRef));
                    secondCountDownView.m(longRef.element);
                }
                ViewGroup viewGroup3 = this.mCountDownViewContainer;
                if (viewGroup3 != null) {
                    viewGroup3.addView(this.mCountDownView);
                }
                AbsCountdownView absCountdownView = this.mCountDownView;
                if (absCountdownView != null) {
                    absCountdownView.onChangeSkin();
                }
                AbsCountdownView absCountdownView2 = this.mCountDownView;
                if (absCountdownView2 != null) {
                    absCountdownView2.o();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            j0.b(this.mCountDownViewContainer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void y(CashierCommonPopConfig cashierPopConfig) {
        com.jd.lib.cashier.sdk.core.utils.e.b();
        com.jd.lib.cashier.sdk.b.d.a.b(this.mCashierPayActivity, cashierPopConfig, new c());
    }

    private final void z(DfPriceInfo dfPriceInfo) {
        if (TextUtils.isEmpty(dfPriceInfo != null ? dfPriceInfo.customerName : null)) {
            return;
        }
        TextView textView = this.mChargeNumberView;
        if (textView != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(dfPriceInfo != null ? dfPriceInfo.customerNameTitle : null);
            sb.append(dfPriceInfo != null ? dfPriceInfo.customerName : null);
            textView.setText(sb.toString());
        }
        j0.d(this.mChargeNumberView);
    }

    public void F(@NotNull r httpEvent) {
        TopFloor topFloor = httpEvent.a;
        if (topFloor != null) {
            j0.d(this.mRootView);
            D(topFloor.homeLetter);
            G(topFloor);
            C(httpEvent.b, httpEvent.f3791e, httpEvent.f3790c, httpEvent.d);
            if (TextUtils.equals(httpEvent.f3793g, "1") && !TextUtils.isEmpty(topFloor.subsidyCountDownTip)) {
                E(httpEvent);
            } else if (httpEvent.f3794h && !TextUtils.isEmpty(topFloor.subsidyCountDownTip)) {
                v(httpEvent);
            } else {
                x(httpEvent);
            }
            DfPriceInfo dfPriceInfo = topFloor.dfPriceInfo;
            if (dfPriceInfo != null && !TextUtils.isEmpty(dfPriceInfo.customerName)) {
                DfPriceInfo dfPriceInfo2 = topFloor.dfPriceInfo;
                Intrinsics.checkExpressionValueIsNotNull(dfPriceInfo2, "topFloor.dfPriceInfo");
                z(dfPriceInfo2);
                return;
            }
            t();
            w(topFloor);
            return;
        }
        j0.b(this.mRootView);
    }

    public void f(@NotNull FragmentActivity activity) {
        if (g0.a(activity)) {
            ViewModel viewModel = ViewModelProviders.of(activity).get(CashierPayViewModel.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(ac\u2026PayViewModel::class.java)");
            ((CashierPayViewModel) viewModel).I().observe(activity, this);
        }
        com.jd.lib.cashier.sdk.b.i.e.e("update_top_floor_price_info", null, new f(activity), 2, null);
        this.largePaymentProxy.f(activity);
    }

    public void h(@Nullable Window window) {
        View findViewById = window != null ? window.findViewById(R.id.lib_cashier_pay_top_floor_layout) : null;
        this.mRootView = findViewById;
        this.mNoticeView = findViewById != null ? (CashierNoticeView) findViewById.findViewById(R.id.lib_cashier_pay_top_floor_notice_view) : null;
        View view = this.mRootView;
        this.mTvPayPriceUnit = view != null ? (TextView) view.findViewById(R.id.lib_cashier_pay_top_floor_price_unit) : null;
        View view2 = this.mRootView;
        this.mPayPriceView = view2 != null ? (RollingDigitTextView) view2.findViewById(R.id.lib_cashier_pay_top_floor_rolling_price) : null;
        View view3 = this.mRootView;
        this.mTvPriceExtend = view3 != null ? (TextView) view3.findViewById(R.id.lib_cashier_pay_top_floor_price_extend) : null;
        View view4 = this.mRootView;
        this.mTvPriceSubTitle = view4 != null ? (TextView) view4.findViewById(R.id.lib_cashier_pay_top_floor_price_subTitle) : null;
        RollingDigitTextView rollingDigitTextView = this.mPayPriceView;
        if (rollingDigitTextView != null) {
            rollingDigitTextView.m(false);
        }
        RollingDigitTextView rollingDigitTextView2 = this.mPayPriceView;
        if (rollingDigitTextView2 != null) {
            rollingDigitTextView2.n(10);
        }
        m0.a(this.mTvPayPriceUnit, (byte) 3);
        m0.a(this.mPayPriceView, (byte) 3);
        m0.a(this.mTvPriceExtend, (byte) 3);
        View view5 = this.mRootView;
        this.mCountDownViewContainer = view5 != null ? (ViewGroup) view5.findViewById(R.id.lib_cashier_pay_top_floor_countdown_container) : null;
        View view6 = this.mRootView;
        this.mChargeNumberView = view6 != null ? (TextView) view6.findViewById(R.id.lib_cashier_pay_top_floor_charge_number) : null;
        View view7 = this.mRootView;
        this.mGradualEditIcon = view7 != null ? (ImageView) view7.findViewById(R.id.lib_cashier_pay_top_floor_edit_icon) : null;
        View view8 = this.mRootView;
        this.mGradualTitle = view8 != null ? (TextView) view8.findViewById(R.id.lib_cashier_pay_top_floor_gradual_title) : null;
        q();
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        q();
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        AbsCountdownView absCountdownView = this.mCountDownView;
        if (absCountdownView != null) {
            absCountdownView.p();
        }
        this.mRootView = null;
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: u  reason: merged with bridge method [inline-methods] */
    public void onChanged(@Nullable r httpEvent) {
        if (httpEvent != null) {
            com.jd.lib.cashier.sdk.core.utils.r.b(v, "onChanged httpEvent = " + httpEvent);
            F(httpEvent);
        }
    }
}
