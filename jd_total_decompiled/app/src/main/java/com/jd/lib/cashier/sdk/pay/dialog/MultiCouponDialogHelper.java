package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.h.m;
import com.jd.lib.cashier.sdk.pay.aac.livedata.BTMultiCouponLiveData;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.MultiCouponAdapter;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoCouponResponse;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B]\u0012\u0006\u0010G\u001a\u00020#\u0012\u0006\u0010I\u001a\u00020#\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012<\u0010b\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b@\u0012\b\bA\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b@\u0012\b\bA\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u0003\u0018\u00010?\u00a2\u0006\u0004\bc\u0010dJ\u000f\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0003\u00a2\u0006\u0004\b\t\u0010\u0005J\r\u0010\u000b\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ-\u0010\u0011\u001a\u00020\u00032\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u00a2\u0006\u0004\b\u0011\u0010\u0012J9\u0010\u0016\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u001a\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0019\u001a\u00020\u0018\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001d\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010 \u001a\u00020\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0003\u00a2\u0006\u0004\b\"\u0010\u0005J5\u0010(\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010#2\b\u0010%\u001a\u0004\u0018\u00010\u000e2\b\u0010&\u001a\u0004\u0018\u00010\u000e2\b\u0010'\u001a\u0004\u0018\u00010#\u00a2\u0006\u0004\b(\u0010)J\r\u0010*\u001a\u00020\u0003\u00a2\u0006\u0004\b*\u0010\u0005R$\u0010%\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0018\u00104\u001a\u0004\u0018\u0001018\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R*\u0010<\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u0001058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0016\u0010>\u001a\u00020\n8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b/\u0010=RL\u0010C\u001a8\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b@\u0012\b\bA\u0012\u0004\b\b(%\u0012\u0015\u0012\u0013\u0018\u00010\u000e\u00a2\u0006\f\b@\u0012\b\bA\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u0003\u0018\u00010?8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010BR\u0019\u0010G\u001a\u00020#8\u0006@\u0006\u00a2\u0006\f\n\u0004\b:\u0010D\u001a\u0004\bE\u0010FR\u0019\u0010I\u001a\u00020#8\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010D\u001a\u0004\bH\u0010FR\u0019\u0010\u0019\u001a\u00020\u00188\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u001a\u0010J\u001a\u0004\bK\u0010LR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010MR\u001c\u0010P\u001a\b\u0012\u0004\u0012\u00020\u000e0N8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b \u0010OR\u001c\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u000e0N8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b8\u0010OR\u0018\u0010T\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bK\u0010SR$\u0010V\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010,\u001a\u0004\b+\u0010.\"\u0004\bU\u00100R\u001d\u0010Z\u001a\u00020W8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\bU\u0010X\u001a\u0004\b2\u0010YR\u001d\u0010]\u001a\u00020[8B@\u0002X\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010X\u001a\u0004\b6\u0010\\R\u001c\u0010^\u001a\b\u0012\u0004\u0012\u00020\u000e0N8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010OR$\u0010a\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b_\u0010,\u001a\u0004\b_\u0010.\"\u0004\b`\u00100\u00a8\u0006e"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/MultiCouponDialogHelper;", "Landroidx/lifecycle/Observer;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/a;", "", JshopConst.JSHOP_PROMOTIO_X, "()V", "Landroid/view/View;", "g", "()Landroid/view/View;", "d", "", "o", "()Z", "", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "availableCouponEntityList", "disableCouponEntityList", "v", "(Ljava/util/List;Ljava/util/List;)V", "currentSelectedCouponEntity", "availableCouponList", "disableCouponList", "f", "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;Ljava/util/List;Ljava/util/List;)Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "Landroidx/fragment/app/FragmentActivity;", "activity", JshopConst.JSHOP_PROMOTIO_W, "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;Landroidx/fragment/app/FragmentActivity;)V", "contentView", "q", "(Landroid/view/View;)V", "baiTiaoCouponHttpEvent", "p", "(Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/a;)V", "c", "", PairKey.COMBINE_TYPE, "currentCoupon", "targetCoupon", "operation", com.jingdong.app.mall.e.a, "(Ljava/lang/String;Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;Ljava/lang/String;)V", "r", "i", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "k", "()Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "t", "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;)V", "Lcom/jd/lib/cashier/sdk/pay/adapter/MultiCouponAdapter;", NotifyType.LIGHTS, "Lcom/jd/lib/cashier/sdk/pay/adapter/MultiCouponAdapter;", "allCouponAdapter", "Lkotlin/Function0;", "m", "Lkotlin/jvm/functions/Function0;", PersonalConstants.ICON_STYLE_N, "()Lkotlin/jvm/functions/Function0;", "u", "(Lkotlin/jvm/functions/Function0;)V", "onCannotUserCouponClickListener", "Z", "addObserver", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "Lkotlin/jvm/functions/Function2;", "onConfirmClickListener", "Ljava/lang/String;", "getChannelType", "()Ljava/lang/String;", PairKey.CHANNEL_TYPE, "getChannelId", "channelId", "Landroidx/fragment/app/FragmentActivity;", JshopConst.JSHOP_PROMOTIO_H, "()Landroidx/fragment/app/FragmentActivity;", "Landroid/view/View;", "", "Ljava/util/List;", "cantUserCouponList", "couponSourceList", "Landroid/app/Dialog;", "Landroid/app/Dialog;", "bottomDialog", "s", "computedCurrentCoupon", "Lcom/jd/lib/cashier/sdk/b/c/d;", "Lkotlin/Lazy;", "()Lcom/jd/lib/cashier/sdk/b/c/d;", "multiCouponGenerator", "Lcom/jd/lib/cashier/sdk/b/c/e;", "()Lcom/jd/lib/cashier/sdk/b/c/e;", "normalCouponGenerator", "canUserCouponList", "j", "setComputedResultCoupon", "computedResultCoupon", "confirmListener", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function2;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class MultiCouponDialogHelper implements Observer<com.jd.lib.cashier.sdk.pay.aac.livedata.a.a> {
    private static final String x = "MultiCouponDialogHelper";

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private View contentView;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private Dialog bottomDialog;
    @Nullable

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private CouponEntity currentCoupon;
    @Nullable

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private CouponEntity computedResultCoupon;
    @Nullable

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private CouponEntity computedCurrentCoupon;

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private MultiCouponAdapter allCouponAdapter;
    @Nullable

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private Function0<Unit> onCannotUserCouponClickListener;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private final List<CouponEntity> couponSourceList = new ArrayList();

    /* renamed from: o  reason: from kotlin metadata */
    private final List<CouponEntity> canUserCouponList = new ArrayList();

    /* renamed from: p  reason: from kotlin metadata */
    private final List<CouponEntity> cantUserCouponList = new ArrayList();

    /* renamed from: q  reason: from kotlin metadata */
    private Function2<? super CouponEntity, ? super CouponEntity, Unit> onConfirmClickListener;

    /* renamed from: r  reason: from kotlin metadata */
    private final Lazy normalCouponGenerator;

    /* renamed from: s  reason: from kotlin metadata */
    private final Lazy multiCouponGenerator;

    /* renamed from: t  reason: from kotlin metadata */
    private boolean addObserver;
    @NotNull

    /* renamed from: u  reason: from kotlin metadata */
    private final String channelType;
    @NotNull

    /* renamed from: v  reason: from kotlin metadata */
    private final String channelId;
    @NotNull

    /* renamed from: w  reason: from kotlin metadata */
    private final FragmentActivity activity;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\n\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "couponItemEntity", "", "checked", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;Ljava/lang/Boolean;)V", "com/jd/lib/cashier/sdk/pay/dialog/MultiCouponDialogHelper$generateContentView$4$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class a extends Lambda implements Function2<CouponEntity, Boolean, Unit> {

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/j;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/j;)V", "com/jd/lib/cashier/sdk/pay/dialog/MultiCouponDialogHelper$generateContentView$4$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.MultiCouponDialogHelper$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class C0134a extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.j, Unit> {
            final /* synthetic */ CouponEntity $couponItemEntity;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0134a(CouponEntity couponEntity) {
                super(1);
                this.$couponItemEntity = couponEntity;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.j jVar) {
                invoke2(jVar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.j jVar) {
                String str;
                com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
                FragmentActivity activity = MultiCouponDialogHelper.this.getActivity();
                String b = jVar.b();
                String a = jVar.a();
                boolean c2 = jVar.c();
                CouponEntity couponEntity = this.$couponItemEntity;
                if (couponEntity == null || (str = couponEntity.getPayMarketingUUID()) == null) {
                    str = "";
                }
                d.u(activity, b, a, c2, str);
            }
        }

        a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(CouponEntity couponEntity, Boolean bool) {
            invoke2(couponEntity, bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable CouponEntity couponEntity, @Nullable Boolean bool) {
            CashierPayViewModel x;
            com.jd.lib.cashier.sdk.h.c.a b;
            String str = Intrinsics.areEqual(bool, Boolean.TRUE) ? "2" : "3";
            FragmentActivity activity = MultiCouponDialogHelper.this.getActivity();
            if (!(activity instanceof CashierPayActivity)) {
                activity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) activity;
            Payment payment = (cashierPayActivity == null || (x = cashierPayActivity.x()) == null || (b = x.b()) == null) ? null : b.O;
            MultiCouponDialogHelper multiCouponDialogHelper = MultiCouponDialogHelper.this;
            multiCouponDialogHelper.e(payment != null ? payment.combineType : null, multiCouponDialogHelper.getComputedCurrentCoupon(), couponEntity, str);
            com.jd.lib.cashier.sdk.h.h.c.a(MultiCouponDialogHelper.this.getActivity(), MultiCouponDialogHelper.this.getComputedResultCoupon(), new C0134a(couponEntity));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {
        b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Function2 function2;
            Dialog dialog;
            CouponEntity computedResultCoupon = MultiCouponDialogHelper.this.getComputedResultCoupon();
            if (TextUtils.equals(computedResultCoupon != null ? computedResultCoupon.getCouponId() : null, "doNotUse")) {
                Function0<Unit> n2 = MultiCouponDialogHelper.this.n();
                if (n2 != null) {
                    n2.invoke();
                }
            } else {
                CouponEntity computedResultCoupon2 = MultiCouponDialogHelper.this.getComputedResultCoupon();
                if (!TextUtils.equals(computedResultCoupon2 != null ? computedResultCoupon2.getCouponId() : null, "empty") && (function2 = MultiCouponDialogHelper.this.onConfirmClickListener) != null) {
                    Unit unit = (Unit) function2.invoke(MultiCouponDialogHelper.this.getCurrentCoupon(), MultiCouponDialogHelper.this.getComputedResultCoupon());
                }
            }
            Dialog dialog2 = MultiCouponDialogHelper.this.bottomDialog;
            if (dialog2 != null && dialog2.isShowing() && (dialog = MultiCouponDialogHelper.this.bottomDialog) != null) {
                dialog.dismiss();
            }
            com.jd.lib.cashier.sdk.h.e.a.d().x(MultiCouponDialogHelper.this.getActivity());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog;
            Dialog dialog2 = MultiCouponDialogHelper.this.bottomDialog;
            if (dialog2 == null || !dialog2.isShowing() || (dialog = MultiCouponDialogHelper.this.bottomDialog) == null) {
                return;
            }
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog;
            Dialog dialog2 = MultiCouponDialogHelper.this.bottomDialog;
            if (dialog2 != null && dialog2.isShowing() && (dialog = MultiCouponDialogHelper.this.bottomDialog) != null) {
                dialog.dismiss();
            }
            Function0<Unit> n2 = MultiCouponDialogHelper.this.n();
            if (n2 != null) {
                n2.invoke();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/b/c/d;", "invoke", "()Lcom/jd/lib/cashier/sdk/b/c/d;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class e extends Lambda implements Function0<com.jd.lib.cashier.sdk.b.c.d> {
        e() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.b.c.d invoke() {
            return new com.jd.lib.cashier.sdk.b.c.d(MultiCouponDialogHelper.this.getActivity().getString(R.string.lib_cashier_sdk_multi_coupon_combination_text));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/b/c/e;", "invoke", "()Lcom/jd/lib/cashier/sdk/b/c/e;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class f extends Lambda implements Function0<com.jd.lib.cashier.sdk.b.c.e> {
        public static final f INSTANCE = new f();

        f() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final com.jd.lib.cashier.sdk.b.c.e invoke() {
            return new com.jd.lib.cashier.sdk.b.c.e();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class g implements DialogInterface.OnDismissListener {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/h;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/h;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
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

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.h hVar) {
                com.jd.lib.cashier.sdk.h.e.a.d().w(MultiCouponDialogHelper.this.getActivity(), hVar.c(), hVar.b());
            }
        }

        g() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            com.jd.lib.cashier.sdk.h.h.c.b(MultiCouponDialogHelper.this.getActivity(), new a());
        }
    }

    public MultiCouponDialogHelper(@NotNull String str, @NotNull String str2, @NotNull FragmentActivity fragmentActivity, @Nullable Function2<? super CouponEntity, ? super CouponEntity, Unit> function2) {
        Lazy lazy;
        Lazy lazy2;
        this.channelType = str;
        this.channelId = str2;
        this.activity = fragmentActivity;
        this.onConfirmClickListener = function2;
        lazy = LazyKt__LazyJVMKt.lazy(f.INSTANCE);
        this.normalCouponGenerator = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(new e());
        this.multiCouponGenerator = lazy2;
    }

    private final View g() {
        View inflate = LayoutInflater.from(this.activity).inflate(R.layout.lib_cashier_sdk_dialog_cashier_pay_multi_coupon_list, (ViewGroup) null, false);
        this.contentView = inflate;
        View findViewById = inflate != null ? inflate.findViewById(R.id.lib_cashier_multi_coupon_confirm_btn) : null;
        View view = this.contentView;
        ImageView imageView = view != null ? (ImageView) view.findViewById(R.id.img_pay_coupon_dialog_close) : null;
        View view2 = this.contentView;
        TextView textView = view2 != null ? (TextView) view2.findViewById(R.id.tv_pay_coupon_dialog_cannot_use) : null;
        View view3 = this.contentView;
        RecyclerView recyclerView = view3 != null ? (RecyclerView) view3.findViewById(R.id.recycler_pay_coupon_dialog) : null;
        if (findViewById != null) {
            findViewById.setOnClickListener(new b());
        }
        if (imageView != null) {
            imageView.setOnClickListener(new c());
        }
        if (textView != null) {
            textView.setOnClickListener(new d());
        }
        MultiCouponAdapter multiCouponAdapter = new MultiCouponAdapter(this.activity, this.couponSourceList);
        multiCouponAdapter.m(new a());
        this.allCouponAdapter = multiCouponAdapter;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this.activity, 1, false));
            recyclerView.setAdapter(this.allCouponAdapter);
        }
        return this.contentView;
    }

    private final com.jd.lib.cashier.sdk.b.c.d l() {
        return (com.jd.lib.cashier.sdk.b.c.d) this.multiCouponGenerator.getValue();
    }

    private final com.jd.lib.cashier.sdk.b.c.e m() {
        return (com.jd.lib.cashier.sdk.b.c.e) this.normalCouponGenerator.getValue();
    }

    private final void x() {
        Dialog dialog;
        if (g0.a(this.activity)) {
            if (this.bottomDialog == null) {
                Dialog b2 = com.jd.lib.cashier.sdk.core.utils.j.b(this.activity);
                this.bottomDialog = b2;
                if (b2 != null) {
                    b2.setCanceledOnTouchOutside(true);
                }
                View g2 = g();
                q(g2);
                com.jd.lib.cashier.sdk.core.utils.j.a(this.bottomDialog, g2, 0.6f);
            }
            Dialog dialog2 = this.bottomDialog;
            if (dialog2 != null) {
                if (this.allCouponAdapter != null && dialog2 != null && dialog2.isShowing()) {
                    MultiCouponAdapter multiCouponAdapter = this.allCouponAdapter;
                    if (multiCouponAdapter != null) {
                        multiCouponAdapter.notifyDataSetChanged();
                    }
                } else {
                    Dialog dialog3 = this.bottomDialog;
                    if (dialog3 != null) {
                        dialog3.show();
                    }
                    MultiCouponAdapter multiCouponAdapter2 = this.allCouponAdapter;
                    if (multiCouponAdapter2 != null && multiCouponAdapter2 != null) {
                        multiCouponAdapter2.notifyDataSetChanged();
                    }
                    q(this.contentView);
                }
                Dialog dialog4 = this.bottomDialog;
                if (dialog4 == null || true != dialog4.isShowing() || (dialog = this.bottomDialog) == null) {
                    return;
                }
                dialog.setOnDismissListener(new g());
            }
        }
    }

    public final void c() {
        BTMultiCouponLiveData p;
        if (this.addObserver) {
            return;
        }
        this.addObserver = true;
        FragmentActivity fragmentActivity = this.activity;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        CashierPayViewModel x2 = cashierPayActivity != null ? cashierPayActivity.x() : null;
        if (x2 == null || (p = x2.p()) == null) {
            return;
        }
        p.observe(this.activity, this);
    }

    public final void d() {
        Iterator<T> it = this.couponSourceList.iterator();
        while (it.hasNext()) {
            ((CouponEntity) it.next()).setSelected(Boolean.FALSE);
        }
        MultiCouponAdapter multiCouponAdapter = this.allCouponAdapter;
        if (multiCouponAdapter != null) {
            multiCouponAdapter.notifyDataSetChanged();
        }
    }

    public final void e(@Nullable String combineType, @Nullable CouponEntity currentCoupon, @Nullable CouponEntity targetCoupon, @Nullable String operation) {
        Pair<CouponEntity, List<CouponEntity>> b2;
        ArrayList arrayList = new ArrayList();
        for (CouponEntity couponEntity : this.canUserCouponList) {
            CouponEntity couponEntity2 = new CouponEntity();
            couponEntity2.setCouponId(couponEntity.getCouponId());
            couponEntity2.setActivityId(couponEntity.getActivityId());
            couponEntity2.setCouponType(couponEntity.getCouponType());
            couponEntity2.setPlans(couponEntity.getPlans());
            couponEntity2.setExtInfo(couponEntity.getExtInfo());
            couponEntity2.setDefaultPlan(couponEntity.getDefaultPlan());
            couponEntity2.setCanUse(couponEntity.getCanUse());
            couponEntity2.setCouponDesc(couponEntity.getCouponDesc());
            couponEntity2.setTermOfValidity(couponEntity.getTermOfValidity());
            couponEntity2.setCouponInfo(couponEntity.getCouponInfo());
            couponEntity2.setCouponTypeDesc(couponEntity.getCouponTypeDesc());
            couponEntity2.setCutOffType(couponEntity.getCutOffType());
            couponEntity2.setActUuId(couponEntity.getActUuId());
            couponEntity2.setStackableTag(couponEntity.getStackableTag());
            couponEntity2.setCombinationCouponList(couponEntity.getCombinationCouponList());
            couponEntity2.setCanCombinationActUuidList(couponEntity.getCanCombinationActUuidList());
            arrayList.add(couponEntity2);
        }
        if (TextUtils.equals(combineType, "1")) {
            b2 = l().b(operation, targetCoupon, currentCoupon, arrayList);
        } else {
            b2 = m().b(operation, targetCoupon, currentCoupon, arrayList);
        }
        Object obj = b2.first;
        if (obj != null) {
            this.computedResultCoupon = (CouponEntity) obj;
            this.computedCurrentCoupon = (CouponEntity) obj;
        }
        this.couponSourceList.clear();
        Collection collection = (Collection) b2.second;
        if (!(collection == null || collection.isEmpty())) {
            List<CouponEntity> list = this.couponSourceList;
            Object obj2 = b2.second;
            Intrinsics.checkExpressionValueIsNotNull(obj2, "result.second");
            list.addAll((Collection) obj2);
        }
        List<CouponEntity> list2 = this.cantUserCouponList;
        if (!(list2 == null || list2.isEmpty())) {
            this.couponSourceList.addAll(this.cantUserCouponList);
        }
        if (this.couponSourceList.isEmpty()) {
            return;
        }
        x();
    }

    @Nullable
    public final CouponEntity f(@Nullable CouponEntity currentSelectedCouponEntity, @Nullable List<CouponEntity> availableCouponList, @Nullable List<CouponEntity> disableCouponList) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        boolean z2 = true;
        if (availableCouponList != null && (!availableCouponList.isEmpty()) != false) {
            arrayList.addAll(availableCouponList);
        }
        if (disableCouponList != null && (!disableCouponList.isEmpty()) != false) {
            arrayList.addAll(disableCouponList);
        }
        Object obj = null;
        if (currentSelectedCouponEntity == null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (obj2 instanceof CouponEntity) {
                    arrayList2.add(obj2);
                }
            }
            if (!arrayList2.isEmpty()) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    if (((CouponEntity) it.next()).getCanUse()) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (z) {
                return com.jd.lib.cashier.sdk.b.c.c.a();
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj3 : arrayList) {
                if (obj3 instanceof CouponEntity) {
                    arrayList3.add(obj3);
                }
            }
            if (!arrayList3.isEmpty()) {
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    if ((!((CouponEntity) it2.next()).getCanUse()) != false) {
                        break;
                    }
                }
            }
            z2 = false;
            if (z2) {
                return com.jd.lib.cashier.sdk.b.c.c.b();
            }
            return null;
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : arrayList) {
            if (obj4 instanceof CouponEntity) {
                arrayList4.add(obj4);
            }
        }
        Iterator it3 = arrayList4.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object next = it3.next();
            if (Intrinsics.areEqual((CouponEntity) next, currentSelectedCouponEntity)) {
                obj = next;
                break;
            }
        }
        CouponEntity couponEntity = (CouponEntity) obj;
        if (couponEntity != null) {
            currentSelectedCouponEntity = couponEntity;
        }
        r.b(x, "filterSelectedCoupon foundCouponEntity = " + currentSelectedCouponEntity);
        return currentSelectedCouponEntity;
    }

    @NotNull
    /* renamed from: h  reason: from getter */
    public final FragmentActivity getActivity() {
        return this.activity;
    }

    @Nullable
    /* renamed from: i  reason: from getter */
    public final CouponEntity getComputedCurrentCoupon() {
        return this.computedCurrentCoupon;
    }

    @Nullable
    /* renamed from: j  reason: from getter */
    public final CouponEntity getComputedResultCoupon() {
        return this.computedResultCoupon;
    }

    @Nullable
    /* renamed from: k  reason: from getter */
    public final CouponEntity getCurrentCoupon() {
        return this.currentCoupon;
    }

    @Nullable
    public final Function0<Unit> n() {
        return this.onCannotUserCouponClickListener;
    }

    public final boolean o() {
        return this.canUserCouponList.isEmpty() && this.cantUserCouponList.isEmpty();
    }

    @Override // androidx.lifecycle.Observer
    /* renamed from: p  reason: merged with bridge method [inline-methods] */
    public void onChanged(@Nullable com.jd.lib.cashier.sdk.pay.aac.livedata.a.a baiTiaoCouponHttpEvent) {
        String str;
        CashierPayViewModel x2;
        com.jd.lib.cashier.sdk.h.c.a b2;
        if (baiTiaoCouponHttpEvent == null || !Intrinsics.areEqual(baiTiaoCouponHttpEvent.b(), Boolean.TRUE)) {
            return;
        }
        FragmentActivity fragmentActivity = this.activity;
        if (!(fragmentActivity instanceof CashierPayActivity)) {
            fragmentActivity = null;
        }
        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
        Payment payment = (cashierPayActivity == null || (x2 = cashierPayActivity.x()) == null || (b2 = x2.b()) == null) ? null : b2.O;
        BaiTiaoCouponResponse a2 = baiTiaoCouponHttpEvent.a();
        if (payment == null || (str = com.jd.lib.cashier.sdk.h.h.e.a.d(payment)) == null) {
            str = "";
        }
        if (TextUtils.equals(payment != null ? payment.code : null, this.channelType) && TextUtils.equals(this.channelId, str) && TextUtils.equals(a2.channelType, this.channelType) && TextUtils.equals(a2.channelId, this.channelId)) {
            v(a2.canUseCouponList, a2.cantUseCouponList);
            e(payment != null ? payment.combineType : null, this.currentCoupon, null, "1");
        }
    }

    public final void q(@Nullable View contentView) {
        if (contentView != null) {
            View findViewById = contentView.findViewById(R.id.lib_cashier_sdk_multi_coupon_confirm_btn_root);
            ((TextView) contentView.findViewById(R.id.lib_cashier_pay_coupon_dialog_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            ((TextView) contentView.findViewById(R.id.tv_pay_coupon_dialog_cannot_use)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
            contentView.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            findViewById.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    public final void r() {
        this.currentCoupon = null;
        this.computedResultCoupon = null;
        this.computedCurrentCoupon = null;
        this.couponSourceList.clear();
        this.canUserCouponList.clear();
        this.cantUserCouponList.clear();
    }

    public final void s(@Nullable CouponEntity couponEntity) {
        this.computedCurrentCoupon = couponEntity;
    }

    public final void t(@Nullable CouponEntity couponEntity) {
        this.currentCoupon = couponEntity;
    }

    public final void u(@Nullable Function0<Unit> function0) {
        this.onCannotUserCouponClickListener = function0;
    }

    public final void v(@Nullable List<CouponEntity> availableCouponEntityList, @Nullable List<CouponEntity> disableCouponEntityList) {
        if (availableCouponEntityList != null && (!availableCouponEntityList.isEmpty()) != false) {
            this.canUserCouponList.clear();
            this.canUserCouponList.addAll(availableCouponEntityList);
        }
        if (disableCouponEntityList == null || !(!disableCouponEntityList.isEmpty()) == true) {
            return;
        }
        this.cantUserCouponList.clear();
        this.cantUserCouponList.addAll(disableCouponEntityList);
    }

    public final void w(@Nullable CouponEntity currentSelectedCouponEntity, @NotNull FragmentActivity activity) {
        String str;
        String str2;
        str = "";
        if (currentSelectedCouponEntity == null || Intrinsics.areEqual(currentSelectedCouponEntity, com.jd.lib.cashier.sdk.b.c.c.b()) || Intrinsics.areEqual(currentSelectedCouponEntity, com.jd.lib.cashier.sdk.b.c.c.a()) || TextUtils.equals(currentSelectedCouponEntity.getPayMarketingUUID(), "combination")) {
            str2 = "";
        } else {
            String couponId = currentSelectedCouponEntity.getCouponId();
            if (couponId == null) {
                couponId = "";
            }
            String activityId = currentSelectedCouponEntity.getActivityId();
            str2 = activityId != null ? activityId : "";
            str = couponId;
        }
        m.q(activity, str, str2);
    }
}
