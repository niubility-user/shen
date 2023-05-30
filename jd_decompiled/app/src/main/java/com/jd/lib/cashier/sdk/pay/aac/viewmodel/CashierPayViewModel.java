package com.jd.lib.cashier.sdk.pay.aac.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.k0;
import com.jd.lib.cashier.sdk.core.utils.l0;
import com.jd.lib.cashier.sdk.core.utils.n0;
import com.jd.lib.cashier.sdk.core.utils.p0;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.pay.aac.livedata.BTMultiCouponLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.BaiTiaoPayPlanFailureLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.BaiTiaoPayPlanLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.BankCouponLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CashierAPayExpandFloorLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CashierBPayDynamicExpandFloorLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CashierBPayExpandFloorLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CashierPayPageLoadingLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CreditCardPayPlanFailureLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CreditCardPayPlanLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.CyberMoneyCouponLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.LargePaymentLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.OctopusRateLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayChannelHttpLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayExceptionLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayExpandFloorLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayFooterLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayForwardLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayShowDialogLiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayTopFloorLiveData;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.RecChannel;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyBankCard;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.utils.pay.AndroidPayConstants;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\b\u00a2\u0006\u0005\b\u0095\u0001\u0010@J\u0019\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\t\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\t\u0010\bJ\u0019\u0010\n\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002\u00a2\u0006\u0004\b\n\u0010\bJ\u0019\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00112\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0016\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J/\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001aH\u0007\u00a2\u0006\u0004\b\u001d\u0010\u001eJ%\u0010#\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u001a\u00a2\u0006\u0004\b#\u0010$J)\u0010'\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010\u001a2\b\u0010&\u001a\u0004\u0018\u00010\u001a2\u0006\u0010 \u001a\u00020\u0018\u00a2\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020)\u00a2\u0006\u0004\b*\u0010+J\u0015\u0010.\u001a\u00020\u00062\u0006\u0010-\u001a\u00020,\u00a2\u0006\u0004\b.\u0010/J\u001f\u00102\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u001f2\b\u00101\u001a\u0004\u0018\u000100\u00a2\u0006\u0004\b2\u00103J\u0015\u00104\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u001f\u00a2\u0006\u0004\b4\u00105J\u0017\u00108\u001a\u00020\u00062\b\u00107\u001a\u0004\u0018\u000106\u00a2\u0006\u0004\b8\u00109J!\u0010<\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010;\u001a\u0004\u0018\u00010:\u00a2\u0006\u0004\b<\u0010=J\u0017\u0010>\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u001f\u00a2\u0006\u0004\b>\u00105J\u000f\u0010?\u001a\u00020\u0006H\u0014\u00a2\u0006\u0004\b?\u0010@R\u001d\u0010E\u001a\u00020A8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b<\u0010B\u001a\u0004\bC\u0010DR\u001d\u0010J\u001a\u00020F8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bG\u0010B\u001a\u0004\bH\u0010IR\u001d\u0010N\u001a\u00020K8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010B\u001a\u0004\bL\u0010MR\u001d\u0010R\u001a\u00020O8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010B\u001a\u0004\bP\u0010QR\u001d\u0010V\u001a\u00020S8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010B\u001a\u0004\bT\u0010UR\u001d\u0010[\u001a\u00020W8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bX\u0010B\u001a\u0004\bY\u0010ZR\u001d\u0010_\u001a\u00020\\8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b8\u0010B\u001a\u0004\b]\u0010^R\u001d\u0010d\u001a\u00020`8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\ba\u0010B\u001a\u0004\bb\u0010cR\u001d\u0010h\u001a\u00020e8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b.\u0010B\u001a\u0004\bf\u0010gR\u001d\u0010l\u001a\u00020i8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bf\u0010B\u001a\u0004\bj\u0010kR\u001d\u0010q\u001a\u00020m8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bn\u0010B\u001a\u0004\bo\u0010pR\u001d\u0010v\u001a\u00020r8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bs\u0010B\u001a\u0004\bt\u0010uR\u001d\u0010y\u001a\u00020w8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bj\u0010B\u001a\u0004\ba\u0010xR\u001d\u0010}\u001a\u00020z8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b2\u0010B\u001a\u0004\b{\u0010|R\u001f\u0010\u0081\u0001\u001a\u00020~8F@\u0006X\u0086\u0084\u0002\u00a2\u0006\r\n\u0004\b\u000f\u0010B\u001a\u0005\b\u007f\u0010\u0080\u0001R\"\u0010\u0086\u0001\u001a\u00030\u0082\u00018F@\u0006X\u0086\u0084\u0002\u00a2\u0006\u000f\n\u0005\b\u0083\u0001\u0010B\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R!\u0010\u0089\u0001\u001a\u00030\u0087\u00018F@\u0006X\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0004\b4\u0010B\u001a\u0006\b\u0083\u0001\u0010\u0088\u0001R!\u0010\u008d\u0001\u001a\u00030\u008a\u00018F@\u0006X\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0004\bT\u0010B\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R!\u0010\u0091\u0001\u001a\u00030\u008e\u00018F@\u0006X\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0004\b>\u0010B\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R \u0010\u0094\u0001\u001a\u00030\u0092\u00018F@\u0006X\u0086\u0084\u0002\u00a2\u0006\r\n\u0004\b'\u0010B\u001a\u0005\bG\u0010\u0093\u0001\u00a8\u0006\u0096\u0001"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/viewmodel/CashierPayViewModel;", "Lcom/jd/lib/cashier/sdk/core/aac/AbsCashierViewModel;", "Lcom/jd/lib/cashier/sdk/h/c/a;", "", "Landroid/content/Intent;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "", "J", "(Landroid/content/Intent;)V", "K", "M", "Lcom/jd/lib/cashier/sdk/d/f/c;", "requestParam", IShareAdapter.SHARE_ACTION_OPEN, "(Lcom/jd/lib/cashier/sdk/d/f/c;)V", "c", "()Lcom/jd/lib/cashier/sdk/h/c/a;", "", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "(Landroid/content/Intent;)Z", "Landroid/content/Context;", AnnoConst.Constructor_Context, "L", "(Landroid/content/Context;)V", "Landroidx/fragment/app/FragmentActivity;", "cashierPayActivity", "", "isGraduallyPay", "gradualPaymentAmount", NotifyType.LIGHTS, "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;", "activity", "addFlag", "channelCode", "j", "(Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;ZLjava/lang/String;)V", PairKey.COMBINE_TYPE, "channelId", JshopConst.JSHOP_PROMOTIO_H, "(Ljava/lang/String;Ljava/lang/String;Landroidx/fragment/app/FragmentActivity;)V", "Lcom/jd/lib/cashier/sdk/h/f/b;", com.jingdong.app.mall.e.a, "(Lcom/jd/lib/cashier/sdk/h/f/b;)V", "Lcom/jd/lib/cashier/sdk/h/f/f;", "param", "g", "(Lcom/jd/lib/cashier/sdk/h/f/f;)V", "Lcom/jd/lib/cashier/sdk/pay/bean/Payment;", "payment", "f", "(Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;Lcom/jd/lib/cashier/sdk/pay/bean/Payment;)V", "i", "(Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;)V", "Lcom/jd/lib/cashier/sdk/pay/bean/RecChannel;", "recChannel", PersonalConstants.ICON_STYLE_N, "(Lcom/jd/lib/cashier/sdk/pay/bean/RecChannel;)V", "Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;", "cyberMoneyChannel", "o", "(Lcom/jd/lib/cashier/sdk/pay/view/CashierPayActivity;Lcom/jd/lib/cashier/sdk/pay/bean/digitalmoney/DigitalMoneyBankCard;)V", "d", "onCleared", "()V", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayShowDialogLiveData;", "Lkotlin/Lazy;", "G", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayShowDialogLiveData;", "payShowDialogLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExpandFloorLiveData;", "q", "C", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExpandFloorLiveData;", "payExpandFloorLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayFooterLiveData;", "E", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayFooterLiveData;", "payFooterLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanLiveData;", JshopConst.JSHOP_PROMOTIO_Y, "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanLiveData;", "creditCardPayPlanLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BankCouponLiveData;", "s", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BankCouponLiveData;", "bankCouponLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanFailureLiveData;", "k", JshopConst.JSHOP_PROMOTIO_X, "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanFailureLiveData;", "creditCardPayPlanFailureLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/LargePaymentLiveData;", "A", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/LargePaymentLiveData;", "largePaymentLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierPayPageLoadingLiveData;", "u", JshopConst.JSHOP_PROMOTIO_W, "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierPayPageLoadingLiveData;", "cashierPayPageLoadingData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanLiveData;", "r", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanLiveData;", "baiTiaoPayPlanLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierAPayExpandFloorLiveData;", "t", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierAPayExpandFloorLiveData;", "cashierAPayExpandFloorLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayChannelHttpLiveData;", "b", DYConstants.LETTER_H, "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayChannelHttpLiveData;", "paySucLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/OctopusRateLiveData;", "m", "B", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/OctopusRateLiveData;", "octopusRateLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayDynamicExpandFloorLiveData;", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayDynamicExpandFloorLiveData;", "cashierBPayDynamicExpandFloorLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayForwardLiveData;", "F", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayForwardLiveData;", "payForwardLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExceptionLiveData;", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExceptionLiveData;", "payFailLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CyberMoneyCouponLiveData;", "p", "z", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CyberMoneyCouponLiveData;", "cyberMoneyCouponLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BTMultiCouponLiveData;", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BTMultiCouponLiveData;", "baiTiaoMultiCouponInfoLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayExpandFloorLiveData;", "v", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayExpandFloorLiveData;", "cashierBPayExpandFloorLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayTopFloorLiveData;", "I", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayTopFloorLiveData;", "payTopFloorLiveData", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanFailureLiveData;", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanFailureLiveData;", "baiTiaoPayPlanFailureLiveData", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierPayViewModel extends AbsCashierViewModel<com.jd.lib.cashier.sdk.h.c.a> {
    @NotNull
    private static final String v;
    @NotNull

    /* renamed from: b  reason: from kotlin metadata */
    private final Lazy paySucLiveData;
    @NotNull

    /* renamed from: c  reason: from kotlin metadata */
    private final Lazy payFailLiveData;
    @NotNull

    /* renamed from: d  reason: from kotlin metadata */
    private final Lazy payTopFloorLiveData;
    @NotNull

    /* renamed from: e */
    private final Lazy payFooterLiveData;
    @NotNull

    /* renamed from: f  reason: from kotlin metadata */
    private final Lazy payForwardLiveData;
    @NotNull

    /* renamed from: g  reason: from kotlin metadata */
    private final Lazy baiTiaoPayPlanLiveData;
    @NotNull

    /* renamed from: h */
    private final Lazy baiTiaoPayPlanFailureLiveData;
    @NotNull

    /* renamed from: i  reason: from kotlin metadata */
    private final Lazy baiTiaoMultiCouponInfoLiveData;
    @NotNull

    /* renamed from: j  reason: from kotlin metadata */
    private final Lazy creditCardPayPlanLiveData;
    @NotNull

    /* renamed from: k  reason: from kotlin metadata */
    private final Lazy creditCardPayPlanFailureLiveData;
    @NotNull

    /* renamed from: l */
    private final Lazy bankCouponLiveData;
    @NotNull

    /* renamed from: m  reason: from kotlin metadata */
    private final Lazy octopusRateLiveData;
    @NotNull

    /* renamed from: n */
    private final Lazy largePaymentLiveData;
    @NotNull

    /* renamed from: o  reason: from kotlin metadata */
    private final Lazy payShowDialogLiveData;
    @NotNull

    /* renamed from: p  reason: from kotlin metadata */
    private final Lazy cyberMoneyCouponLiveData;
    @NotNull

    /* renamed from: q  reason: from kotlin metadata */
    private final Lazy payExpandFloorLiveData;
    @NotNull

    /* renamed from: r  reason: from kotlin metadata */
    private final Lazy cashierAPayExpandFloorLiveData;
    @NotNull

    /* renamed from: s  reason: from kotlin metadata */
    private final Lazy cashierBPayExpandFloorLiveData;
    @NotNull

    /* renamed from: t  reason: from kotlin metadata */
    private final Lazy cashierBPayDynamicExpandFloorLiveData;
    @NotNull

    /* renamed from: u  reason: from kotlin metadata */
    private final Lazy cashierPayPageLoadingData;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BTMultiCouponLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BTMultiCouponLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function0<BTMultiCouponLiveData> {
        public static final a INSTANCE = new a();

        a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final BTMultiCouponLiveData invoke() {
            return new BTMultiCouponLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanFailureLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanFailureLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class b extends Lambda implements Function0<BaiTiaoPayPlanFailureLiveData> {
        public static final b INSTANCE = new b();

        b() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final BaiTiaoPayPlanFailureLiveData invoke() {
            return new BaiTiaoPayPlanFailureLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class c extends Lambda implements Function0<BaiTiaoPayPlanLiveData> {
        public static final c INSTANCE = new c();

        c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final BaiTiaoPayPlanLiveData invoke() {
            return new BaiTiaoPayPlanLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BankCouponLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BankCouponLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class d extends Lambda implements Function0<BankCouponLiveData> {
        public static final d INSTANCE = new d();

        d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final BankCouponLiveData invoke() {
            return new BankCouponLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierAPayExpandFloorLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierAPayExpandFloorLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class e extends Lambda implements Function0<CashierAPayExpandFloorLiveData> {
        public static final e INSTANCE = new e();

        e() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CashierAPayExpandFloorLiveData invoke() {
            return new CashierAPayExpandFloorLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayDynamicExpandFloorLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayDynamicExpandFloorLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class f extends Lambda implements Function0<CashierBPayDynamicExpandFloorLiveData> {
        public static final f INSTANCE = new f();

        f() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CashierBPayDynamicExpandFloorLiveData invoke() {
            return new CashierBPayDynamicExpandFloorLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayExpandFloorLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierBPayExpandFloorLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class g extends Lambda implements Function0<CashierBPayExpandFloorLiveData> {
        public static final g INSTANCE = new g();

        g() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CashierBPayExpandFloorLiveData invoke() {
            return new CashierBPayExpandFloorLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierPayPageLoadingLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CashierPayPageLoadingLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class h extends Lambda implements Function0<CashierPayPageLoadingLiveData> {
        public static final h INSTANCE = new h();

        h() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CashierPayPageLoadingLiveData invoke() {
            return new CashierPayPageLoadingLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanFailureLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanFailureLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class i extends Lambda implements Function0<CreditCardPayPlanFailureLiveData> {
        public static final i INSTANCE = new i();

        i() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CreditCardPayPlanFailureLiveData invoke() {
            return new CreditCardPayPlanFailureLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class j extends Lambda implements Function0<CreditCardPayPlanLiveData> {
        public static final j INSTANCE = new j();

        j() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CreditCardPayPlanLiveData invoke() {
            return new CreditCardPayPlanLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CyberMoneyCouponLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CyberMoneyCouponLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class k extends Lambda implements Function0<CyberMoneyCouponLiveData> {
        public static final k INSTANCE = new k();

        k() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final CyberMoneyCouponLiveData invoke() {
            return new CyberMoneyCouponLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/LargePaymentLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/LargePaymentLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class l extends Lambda implements Function0<LargePaymentLiveData> {
        public static final l INSTANCE = new l();

        l() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final LargePaymentLiveData invoke() {
            return new LargePaymentLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/OctopusRateLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/OctopusRateLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class m extends Lambda implements Function0<OctopusRateLiveData> {
        public static final m INSTANCE = new m();

        m() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final OctopusRateLiveData invoke() {
            return new OctopusRateLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExpandFloorLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExpandFloorLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class n extends Lambda implements Function0<PayExpandFloorLiveData> {
        public static final n INSTANCE = new n();

        n() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PayExpandFloorLiveData invoke() {
            return new PayExpandFloorLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExceptionLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayExceptionLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class o extends Lambda implements Function0<PayExceptionLiveData> {
        public static final o INSTANCE = new o();

        o() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PayExceptionLiveData invoke() {
            return new PayExceptionLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayFooterLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayFooterLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class p extends Lambda implements Function0<PayFooterLiveData> {
        public static final p INSTANCE = new p();

        p() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PayFooterLiveData invoke() {
            return new PayFooterLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayForwardLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayForwardLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class q extends Lambda implements Function0<PayForwardLiveData> {
        public static final q INSTANCE = new q();

        q() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PayForwardLiveData invoke() {
            return new PayForwardLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayShowDialogLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayShowDialogLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class r extends Lambda implements Function0<PayShowDialogLiveData> {
        public static final r INSTANCE = new r();

        r() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PayShowDialogLiveData invoke() {
            return new PayShowDialogLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayChannelHttpLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayChannelHttpLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class s extends Lambda implements Function0<PayChannelHttpLiveData> {
        public static final s INSTANCE = new s();

        s() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PayChannelHttpLiveData invoke() {
            return new PayChannelHttpLiveData();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayTopFloorLiveData;", "invoke", "()Lcom/jd/lib/cashier/sdk/pay/aac/livedata/PayTopFloorLiveData;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class t extends Lambda implements Function0<PayTopFloorLiveData> {
        public static final t INSTANCE = new t();

        t() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final PayTopFloorLiveData invoke() {
            return new PayTopFloorLiveData();
        }
    }

    static {
        String simpleName = CashierPayViewModel.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "CashierPayViewModel::class.java.simpleName");
        v = simpleName;
    }

    public CashierPayViewModel() {
        Lazy lazy;
        Lazy lazy2;
        Lazy lazy3;
        Lazy lazy4;
        Lazy lazy5;
        Lazy lazy6;
        Lazy lazy7;
        Lazy lazy8;
        Lazy lazy9;
        Lazy lazy10;
        Lazy lazy11;
        Lazy lazy12;
        Lazy lazy13;
        Lazy lazy14;
        Lazy lazy15;
        Lazy lazy16;
        Lazy lazy17;
        Lazy lazy18;
        Lazy lazy19;
        Lazy lazy20;
        lazy = LazyKt__LazyJVMKt.lazy(s.INSTANCE);
        this.paySucLiveData = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(o.INSTANCE);
        this.payFailLiveData = lazy2;
        lazy3 = LazyKt__LazyJVMKt.lazy(t.INSTANCE);
        this.payTopFloorLiveData = lazy3;
        lazy4 = LazyKt__LazyJVMKt.lazy(p.INSTANCE);
        this.payFooterLiveData = lazy4;
        lazy5 = LazyKt__LazyJVMKt.lazy(q.INSTANCE);
        this.payForwardLiveData = lazy5;
        lazy6 = LazyKt__LazyJVMKt.lazy(c.INSTANCE);
        this.baiTiaoPayPlanLiveData = lazy6;
        lazy7 = LazyKt__LazyJVMKt.lazy(b.INSTANCE);
        this.baiTiaoPayPlanFailureLiveData = lazy7;
        lazy8 = LazyKt__LazyJVMKt.lazy(a.INSTANCE);
        this.baiTiaoMultiCouponInfoLiveData = lazy8;
        lazy9 = LazyKt__LazyJVMKt.lazy(j.INSTANCE);
        this.creditCardPayPlanLiveData = lazy9;
        lazy10 = LazyKt__LazyJVMKt.lazy(i.INSTANCE);
        this.creditCardPayPlanFailureLiveData = lazy10;
        lazy11 = LazyKt__LazyJVMKt.lazy(d.INSTANCE);
        this.bankCouponLiveData = lazy11;
        lazy12 = LazyKt__LazyJVMKt.lazy(m.INSTANCE);
        this.octopusRateLiveData = lazy12;
        lazy13 = LazyKt__LazyJVMKt.lazy(l.INSTANCE);
        this.largePaymentLiveData = lazy13;
        lazy14 = LazyKt__LazyJVMKt.lazy(r.INSTANCE);
        this.payShowDialogLiveData = lazy14;
        lazy15 = LazyKt__LazyJVMKt.lazy(k.INSTANCE);
        this.cyberMoneyCouponLiveData = lazy15;
        lazy16 = LazyKt__LazyJVMKt.lazy(n.INSTANCE);
        this.payExpandFloorLiveData = lazy16;
        lazy17 = LazyKt__LazyJVMKt.lazy(e.INSTANCE);
        this.cashierAPayExpandFloorLiveData = lazy17;
        lazy18 = LazyKt__LazyJVMKt.lazy(g.INSTANCE);
        this.cashierBPayExpandFloorLiveData = lazy18;
        lazy19 = LazyKt__LazyJVMKt.lazy(f.INSTANCE);
        this.cashierBPayDynamicExpandFloorLiveData = lazy19;
        lazy20 = LazyKt__LazyJVMKt.lazy(h.INSTANCE);
        this.cashierPayPageLoadingData = lazy20;
    }

    private final void J(Intent r5) {
        if (r5 != null) {
            com.jd.lib.cashier.sdk.h.c.a b2 = b();
            String stringExtra = r5.getStringExtra("payId");
            String stringExtra2 = r5.getStringExtra("appId");
            b2.b = stringExtra2;
            if (TextUtils.isEmpty(stringExtra2)) {
                b2.b = y.l();
            }
            String stringExtra3 = r5.getStringExtra(PairKey.APP_KEY);
            b2.f3510c = stringExtra3;
            if (TextUtils.isEmpty(stringExtra3)) {
                b2.f3510c = y.m();
            }
            com.jd.lib.cashier.sdk.core.utils.m.f().a();
            if (r5.hasExtra(PairKey.CASHIER_SHOW_STYLE)) {
                b2.X = Intrinsics.areEqual("1", r5.getStringExtra(PairKey.CASHIER_SHOW_STYLE));
                com.jd.lib.cashier.sdk.core.utils.m.f().l(b2.X);
            }
            b2.D = r5.getStringExtra("fromActivity");
            b2.C = r5.getIntExtra("requestCode", -1);
            com.jd.lib.cashier.sdk.core.utils.m f2 = com.jd.lib.cashier.sdk.core.utils.m.f();
            if (stringExtra == null) {
                stringExtra = "";
            }
            f2.n(stringExtra);
            com.jd.lib.cashier.sdk.core.utils.m.f().e();
            String stringExtra4 = r5.getStringExtra(AndroidPayConstants.TID);
            if (!TextUtils.isEmpty(stringExtra4)) {
                com.jd.lib.cashier.sdk.core.utils.m.f().p(stringExtra4);
            }
            String h2 = y.h();
            b2.b0 = h2 != null ? h2 : "";
            com.jd.lib.cashier.sdk.core.utils.m.f().d();
            if (TextUtils.isEmpty(b2.b0)) {
                return;
            }
            com.jd.lib.cashier.sdk.core.utils.m.f().o(b2.b0);
        }
    }

    private final void K(Intent r7) {
        if (r7 != null) {
            com.jd.lib.cashier.sdk.h.c.a b2 = b();
            b2.Z = r7.getStringExtra("payUrl");
            b2.f3514h = r7.getStringExtra("back_url");
            b2.f3511e = r7.getStringExtra("orderId");
            b2.f3515i = r7.getStringExtra("orderType");
            b2.f3516j = r7.getStringExtra("payablePrice");
            b2.f3517k = r7.getStringExtra("orderTypeCode");
            b2.o = r7.getStringExtra("paySourceId");
            b2.f3518l = g0.d(b().b, b().f3510c, b().f3511e, b().f3515i, b().f3516j);
            if (r7.hasExtra("orderIdList")) {
                b2.f3512f = r7.getStringExtra("orderIdList");
            }
        }
    }

    private final void M(Intent r3) {
        if (r3 != null) {
            com.jd.lib.cashier.sdk.h.c.a b2 = b();
            b2.a0 = r3.getStringExtra("from");
            b2.p = r3.getStringExtra("unJieSuan");
            b2.q = r3.getStringExtra("baiTiaoNum");
            b2.r = r3.getStringExtra("businessTag");
            b2.t = r3.getStringExtra("submitOrderExtFlag");
            b2.V = r3.getStringExtra(AndroidPayConstants.SUCCESS_NOTIFICATION_NAME);
            b2.W = r3.getStringExtra(AndroidPayConstants.QUIT_NOTIFICATION_NAME);
            b2.s = r3.getStringExtra("isGoodsDetailBaiTiaoFlag");
            b2.f3519m = r3.getStringArrayListExtra("lastWebViewUrls");
            b2.f3520n = r3.getStringArrayListExtra("preWebViewUrls");
        }
    }

    private final void O(com.jd.lib.cashier.sdk.d.f.c cVar) {
        if (cVar != null) {
            cVar.appId = b().b;
            cVar.paySign = b().f3518l;
            cVar.orderId = b().f3511e;
            cVar.orderType = b().f3515i;
            cVar.orderPrice = b().f3516j;
            cVar.orderTypeCode = b().f3517k;
            if (!TextUtils.isEmpty(b().d())) {
                cVar.groupOrders = b().d();
            }
            if (TextUtils.isEmpty(b().f3513g)) {
                return;
            }
            cVar.combinedOrderId = b().f3513g;
        }
    }

    public static /* synthetic */ void m(CashierPayViewModel cashierPayViewModel, FragmentActivity fragmentActivity, String str, String str2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            str2 = null;
        }
        cashierPayViewModel.l(fragmentActivity, str, str2);
    }

    @NotNull
    public final LargePaymentLiveData A() {
        return (LargePaymentLiveData) this.largePaymentLiveData.getValue();
    }

    @NotNull
    public final OctopusRateLiveData B() {
        return (OctopusRateLiveData) this.octopusRateLiveData.getValue();
    }

    @NotNull
    public final PayExpandFloorLiveData C() {
        return (PayExpandFloorLiveData) this.payExpandFloorLiveData.getValue();
    }

    @NotNull
    public final PayExceptionLiveData D() {
        return (PayExceptionLiveData) this.payFailLiveData.getValue();
    }

    @NotNull
    public final PayFooterLiveData E() {
        return (PayFooterLiveData) this.payFooterLiveData.getValue();
    }

    @NotNull
    public final PayForwardLiveData F() {
        return (PayForwardLiveData) this.payForwardLiveData.getValue();
    }

    @NotNull
    public final PayShowDialogLiveData G() {
        return (PayShowDialogLiveData) this.payShowDialogLiveData.getValue();
    }

    @NotNull
    public final PayChannelHttpLiveData H() {
        return (PayChannelHttpLiveData) this.paySucLiveData.getValue();
    }

    @NotNull
    public final PayTopFloorLiveData I() {
        return (PayTopFloorLiveData) this.payTopFloorLiveData.getValue();
    }

    public final void L(@Nullable Context r5) {
        if (r5 != null) {
            com.jd.lib.cashier.sdk.h.c.a b2 = b();
            b2.z = l0.c();
            b2.A = com.jd.lib.cashier.sdk.core.utils.d.c();
            String str = "0";
            b2.w = n0.a(r5) ? "1" : "0";
            b2.v = p0.a(r5) ? "1" : "0";
            b2.x = k0.a(r5) ? "1" : "0";
            try {
                str = k0.b(r5) ? "1" : "0";
            } catch (Exception unused) {
            }
            b2.y = str;
        }
    }

    public final boolean N(@Nullable Intent intent) {
        if (intent != null) {
            J(intent);
            K(intent);
            M(intent);
            return true;
        }
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    @NotNull
    /* renamed from: c */
    public com.jd.lib.cashier.sdk.h.c.a a() {
        return new com.jd.lib.cashier.sdk.h.c.a();
    }

    public final void d(@Nullable CashierPayActivity activity) {
        PaymentChoseHolder b2;
        com.jd.lib.cashier.sdk.d.g.c.c.b bVar = new com.jd.lib.cashier.sdk.d.g.c.c.b();
        bVar.setActivity(activity);
        bVar.a = ((com.jd.lib.cashier.sdk.h.c.a) b()).D;
        CashierPayEntity cashierPayEntity = ((com.jd.lib.cashier.sdk.h.c.a) b()).K;
        bVar.f3274k = cashierPayEntity != null ? cashierPayEntity.requireUUID : null;
        if (activity != null && (b2 = com.jd.lib.cashier.sdk.h.h.m.b(activity)) != null) {
            bVar.w = b2.jdPayChannel;
        }
        O(bVar);
        new com.jd.lib.cashier.sdk.h.a.a.k().e(bVar);
    }

    public final void e(@NotNull com.jd.lib.cashier.sdk.h.f.b bVar) {
        O(bVar);
        new com.jd.lib.cashier.sdk.h.a.a.b().e(bVar);
    }

    public final void f(@NotNull CashierPayActivity activity, @Nullable Payment payment) {
        String str;
        if (payment == null || (str = payment.channelId) == null) {
            str = "";
        }
        com.jd.lib.cashier.sdk.h.f.c cVar = new com.jd.lib.cashier.sdk.h.f.c(str, activity);
        O(cVar);
        com.jd.lib.cashier.sdk.core.utils.r.b(v, "BankCouponRequestParam requestParam = " + cVar);
        com.jd.lib.cashier.sdk.h.a.a.c cVar2 = new com.jd.lib.cashier.sdk.h.a.a.c();
        cVar2.t(payment);
        cVar2.e(cVar);
    }

    public final void g(@NotNull com.jd.lib.cashier.sdk.h.f.f param) {
        O(param);
        new com.jd.lib.cashier.sdk.h.a.a.e().e(param);
    }

    public final void h(@Nullable String str, @Nullable String str2, @NotNull FragmentActivity fragmentActivity) {
        Payment payment;
        com.jd.lib.cashier.sdk.h.f.a aVar = new com.jd.lib.cashier.sdk.h.f.a();
        aVar.setActivity(fragmentActivity);
        aVar.h(((com.jd.lib.cashier.sdk.h.c.a) b()).B);
        aVar.g(str);
        aVar.e(str2);
        com.jd.lib.cashier.sdk.h.c.a aVar2 = (com.jd.lib.cashier.sdk.h.c.a) b();
        aVar.f((aVar2 == null || (payment = aVar2.O) == null) ? null : payment.code);
        O(aVar);
        new com.jd.lib.cashier.sdk.h.a.a.a().e(aVar);
    }

    public final void i(@NotNull CashierPayActivity activity) {
        com.jd.lib.cashier.sdk.h.f.h hVar = new com.jd.lib.cashier.sdk.h.f.h(activity);
        O(hVar);
        new com.jd.lib.cashier.sdk.h.a.a.f().e(hVar);
    }

    public final void j(@NotNull CashierPayActivity activity, boolean addFlag, @NotNull String channelCode) {
        try {
            if (((com.jd.lib.cashier.sdk.h.c.a) b()).G) {
                return;
            }
            ((com.jd.lib.cashier.sdk.h.c.a) b()).G = true;
            com.jd.lib.cashier.sdk.b.f.a aVar = new com.jd.lib.cashier.sdk.b.f.a();
            if (addFlag) {
                aVar.b = "1";
            }
            aVar.setActivity(activity);
            aVar.a = channelCode;
            String str = ((com.jd.lib.cashier.sdk.h.c.a) b()).f3514h;
            if (Intrinsics.areEqual(aVar.a, "octopusPay")) {
                com.jd.lib.cashier.sdk.d.g.g.d iPay = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.OCTOPUS);
                Intrinsics.checkExpressionValueIsNotNull(iPay, "iPay");
                com.jd.lib.cashier.sdk.d.g.g.b a2 = iPay.a();
                if (a2 != null) {
                    aVar.f2872c = ((com.jd.lib.cashier.sdk.d.g.f.c.a) a2).f3282c;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.jd.lib.cashier.sdk.core.paychannel.octopuspay.param.OctopusPayApiParam");
                }
            }
            O(aVar);
            new com.jd.lib.cashier.sdk.h.a.a.h().e(aVar);
        } catch (Exception e2) {
            ((com.jd.lib.cashier.sdk.h.c.a) b()).G = false;
            com.jd.lib.cashier.sdk.core.utils.r.b(v, e2.getMessage());
        }
    }

    @JvmOverloads
    public final void k(@NotNull FragmentActivity fragmentActivity) {
        m(this, fragmentActivity, null, null, 6, null);
    }

    @JvmOverloads
    public final void l(@NotNull FragmentActivity fragmentActivity, @Nullable String str, @Nullable String str2) {
        w().b();
        com.jd.lib.cashier.sdk.h.f.d dVar = new com.jd.lib.cashier.sdk.h.f.d();
        dVar.setActivity(fragmentActivity);
        dVar.f3546j = ((com.jd.lib.cashier.sdk.h.c.a) b()).v;
        dVar.f3547k = ((com.jd.lib.cashier.sdk.h.c.a) b()).w;
        dVar.f3550n = ((com.jd.lib.cashier.sdk.h.c.a) b()).z;
        dVar.o = ((com.jd.lib.cashier.sdk.h.c.a) b()).A;
        dVar.f3548l = ((com.jd.lib.cashier.sdk.h.c.a) b()).x;
        dVar.f3549m = ((com.jd.lib.cashier.sdk.h.c.a) b()).y;
        dVar.z = ((com.jd.lib.cashier.sdk.h.c.a) b()).Z;
        dVar.s = ((com.jd.lib.cashier.sdk.h.c.a) b()).p;
        dVar.t = ((com.jd.lib.cashier.sdk.h.c.a) b()).q;
        dVar.u = ((com.jd.lib.cashier.sdk.h.c.a) b()).r;
        dVar.v = ((com.jd.lib.cashier.sdk.h.c.a) b()).t;
        dVar.w = ((com.jd.lib.cashier.sdk.h.c.a) b()).s;
        com.jd.lib.cashier.sdk.h.c.a state = (com.jd.lib.cashier.sdk.h.c.a) b();
        Intrinsics.checkExpressionValueIsNotNull(state, "state");
        dVar.x = state.c();
        dVar.p = str;
        dVar.q = str2;
        dVar.A = ((com.jd.lib.cashier.sdk.h.c.a) b()).a0;
        dVar.f3540c = ((com.jd.lib.cashier.sdk.h.c.a) b()).f3514h;
        dVar.a = ((com.jd.lib.cashier.sdk.h.c.a) b()).f3516j;
        dVar.b = ((com.jd.lib.cashier.sdk.h.c.a) b()).o;
        dVar.y = ((com.jd.lib.cashier.sdk.h.c.a) b()).Y;
        dVar.r = ((com.jd.lib.cashier.sdk.h.c.a) b()).B;
        dVar.f3542f = ((com.jd.lib.cashier.sdk.h.c.a) b()).d;
        dVar.f3545i = y.g();
        dVar.d = y.k();
        dVar.f3541e = y.o();
        com.jd.lib.cashier.sdk.b.e.a a2 = com.jd.lib.cashier.sdk.b.e.a.a();
        Intrinsics.checkExpressionValueIsNotNull(a2, "CashierLbsSingleton.getInstance()");
        dVar.f3544h = a2.b();
        com.jd.lib.cashier.sdk.b.e.a a3 = com.jd.lib.cashier.sdk.b.e.a.a();
        Intrinsics.checkExpressionValueIsNotNull(a3, "CashierLbsSingleton.getInstance()");
        dVar.f3543g = a3.c();
        dVar.B = new HashMap();
        List<String> list = ((com.jd.lib.cashier.sdk.h.c.a) b()).f3519m;
        if (!(list == null || list.isEmpty())) {
            Map<String, List<String>> webViewUrlsDic = dVar.B;
            Intrinsics.checkExpressionValueIsNotNull(webViewUrlsDic, "webViewUrlsDic");
            webViewUrlsDic.put("lastWebViewUrls", ((com.jd.lib.cashier.sdk.h.c.a) b()).f3519m);
        }
        List<String> list2 = ((com.jd.lib.cashier.sdk.h.c.a) b()).f3520n;
        if (!(list2 == null || list2.isEmpty())) {
            Map<String, List<String>> webViewUrlsDic2 = dVar.B;
            Intrinsics.checkExpressionValueIsNotNull(webViewUrlsDic2, "webViewUrlsDic");
            webViewUrlsDic2.put("preWebViewUrls", ((com.jd.lib.cashier.sdk.h.c.a) b()).f3520n);
        }
        com.jd.lib.cashier.sdk.h.a.a.g gVar = new com.jd.lib.cashier.sdk.h.a.a.g();
        O(dVar);
        com.jd.lib.cashier.sdk.core.utils.r.b(v, "cashierPayParam = " + dVar);
        gVar.e(dVar);
    }

    public final void n(@Nullable RecChannel recChannel) {
        String str;
        String str2;
        String str3 = "";
        if (recChannel == null || (str = recChannel.channelId) == null) {
            str = "";
        }
        if (recChannel != null && (str2 = recChannel.recommendDesc) != null) {
            str3 = str2;
        }
        com.jd.lib.cashier.sdk.h.f.e eVar = new com.jd.lib.cashier.sdk.h.f.e(str, str3);
        O(eVar);
        new com.jd.lib.cashier.sdk.h.a.a.d().e(eVar);
    }

    public final void o(@Nullable CashierPayActivity activity, @Nullable DigitalMoneyBankCard cyberMoneyChannel) {
        com.jd.lib.cashier.sdk.h.f.g gVar = new com.jd.lib.cashier.sdk.h.f.g();
        gVar.setActivity(activity);
        gVar.f3557f = cyberMoneyChannel;
        gVar.f3556e = cyberMoneyChannel != null ? cyberMoneyChannel.channelId : null;
        gVar.b = ((com.jd.lib.cashier.sdk.h.c.a) b()).f3514h;
        O(gVar);
        new com.jd.lib.cashier.sdk.h.a.a.j().e(gVar);
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        com.jd.lib.cashier.sdk.core.utils.r.b(v, "onCleared");
    }

    @NotNull
    public final BTMultiCouponLiveData p() {
        return (BTMultiCouponLiveData) this.baiTiaoMultiCouponInfoLiveData.getValue();
    }

    @NotNull
    public final BaiTiaoPayPlanFailureLiveData q() {
        return (BaiTiaoPayPlanFailureLiveData) this.baiTiaoPayPlanFailureLiveData.getValue();
    }

    @NotNull
    public final BaiTiaoPayPlanLiveData r() {
        return (BaiTiaoPayPlanLiveData) this.baiTiaoPayPlanLiveData.getValue();
    }

    @NotNull
    public final BankCouponLiveData s() {
        return (BankCouponLiveData) this.bankCouponLiveData.getValue();
    }

    @NotNull
    public final CashierAPayExpandFloorLiveData t() {
        return (CashierAPayExpandFloorLiveData) this.cashierAPayExpandFloorLiveData.getValue();
    }

    @NotNull
    public final CashierBPayDynamicExpandFloorLiveData u() {
        return (CashierBPayDynamicExpandFloorLiveData) this.cashierBPayDynamicExpandFloorLiveData.getValue();
    }

    @NotNull
    public final CashierBPayExpandFloorLiveData v() {
        return (CashierBPayExpandFloorLiveData) this.cashierBPayExpandFloorLiveData.getValue();
    }

    @NotNull
    public final CashierPayPageLoadingLiveData w() {
        return (CashierPayPageLoadingLiveData) this.cashierPayPageLoadingData.getValue();
    }

    @NotNull
    public final CreditCardPayPlanFailureLiveData x() {
        return (CreditCardPayPlanFailureLiveData) this.creditCardPayPlanFailureLiveData.getValue();
    }

    @NotNull
    public final CreditCardPayPlanLiveData y() {
        return (CreditCardPayPlanLiveData) this.creditCardPayPlanLiveData.getValue();
    }

    @NotNull
    public final CyberMoneyCouponLiveData z() {
        return (CyberMoneyCouponLiveData) this.cyberMoneyCouponLiveData.getValue();
    }
}
