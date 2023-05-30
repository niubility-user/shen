package com.jd.lib.cashier.sdk.h.e;

import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.b.b.b;
import com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.model.PopConfirmBtnMta;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.freindpay.view.FriendPayActivity;
import com.jd.lib.cashier.sdk.freindpaydialog.view.FriendPayDialogActivity;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {

    /* renamed from: g */
    private static volatile a f3525g;
    private Map<String, Object> a = new com.jd.lib.cashier.sdk.b.b.a();
    private Map<String, Object> b = new b();

    /* renamed from: c */
    private Map<String, Object> f3526c = new com.jd.lib.cashier.sdk.b.b.a();
    private Map<String, String> d = new HashMap();

    /* renamed from: e */
    private HashMap<String, String> f3527e = new HashMap<>();

    /* renamed from: f */
    private HashSet<String> f3528f = new HashSet<>(2);

    private a() {
    }

    private void a(CashierPayViewModel cashierPayViewModel) {
        String str = "0";
        if (cashierPayViewModel.b().K != null && !TextUtils.isEmpty(cashierPayViewModel.b().K.baitiaoAutoSlideFlag)) {
            str = TextUtils.equals(cashierPayViewModel.b().K.baitiaoAutoSlideFlag, "0") ? cashierPayViewModel.b().K.baitiaoAutoSlideFlag : "1";
        }
        this.a.put("isAutoSlide", str);
    }

    private void b(CashierPayViewModel cashierPayViewModel) {
        this.a.put("is_prepay", cashierPayViewModel.b().e() ? "1" : "0");
    }

    private String c(Context context) {
        if (context instanceof CashierPayActivity) {
            CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
            if (g0.a(cashierPayActivity)) {
                return cashierPayActivity.x().b().b;
            }
        }
        if (context instanceof FriendPayActivity) {
            FriendPayActivity friendPayActivity = (FriendPayActivity) context;
            if (g0.a(friendPayActivity)) {
                return friendPayActivity.x().b().f3349g;
            }
        }
        if (context instanceof FriendPayDialogActivity) {
            FriendPayDialogActivity friendPayDialogActivity = (FriendPayDialogActivity) context;
            if (g0.a(friendPayDialogActivity)) {
                return friendPayDialogActivity.x().b().f3463g;
            }
        }
        if (context instanceof BtCombinationPayActivity) {
            BtCombinationPayActivity btCombinationPayActivity = (BtCombinationPayActivity) context;
            return g0.a(btCombinationPayActivity) ? btCombinationPayActivity.x().b().b : "";
        }
        return "";
    }

    public static a d() {
        if (f3525g == null) {
            synchronized (a.class) {
                if (f3525g == null) {
                    f3525g = new a();
                }
            }
        }
        return f3525g;
    }

    private String e(Context context) {
        if (context instanceof CashierPayActivity) {
            CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
            if (g0.a(cashierPayActivity)) {
                return cashierPayActivity.x().b().f3511e;
            }
        }
        if (context instanceof FriendPayActivity) {
            FriendPayActivity friendPayActivity = (FriendPayActivity) context;
            if (g0.a(friendPayActivity)) {
                return friendPayActivity.x().b().b;
            }
        }
        if (context instanceof FriendPayDialogActivity) {
            FriendPayDialogActivity friendPayDialogActivity = (FriendPayDialogActivity) context;
            if (g0.a(friendPayDialogActivity)) {
                return friendPayDialogActivity.x().b().b;
            }
        }
        if (context instanceof BtCombinationPayActivity) {
            BtCombinationPayActivity btCombinationPayActivity = (BtCombinationPayActivity) context;
            return g0.a(btCombinationPayActivity) ? btCombinationPayActivity.x().b().d : "";
        }
        return "";
    }

    public void A(Context context, String str, String str2) {
        this.a.clear();
        this.a.put("orderid", str);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str2);
        com.jd.lib.cashier.sdk.d.e.b.d(context, 1000, "JDCashierNew_DigitalPopExpo", b, o.b(this.a));
    }

    public void B(FragmentActivity fragmentActivity, String str, String str2, String str3, String str4, boolean z, String str5) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        this.a.put("strategy", str3);
        this.a.put("isdefault", z ? "1" : "0");
        this.a.put("orderid", str4);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str5);
        com.jd.lib.cashier.sdk.d.e.b.d(fragmentActivity, 1000, "JDCashierNew_DigitalPopPaymentExpo", b, o.b(this.a));
    }

    public void C(Context context, String str, String str2) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_DiscountEntrance", o.b(this.a));
    }

    public void D(FragmentActivity fragmentActivity, String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        String str10;
        String str11;
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        this.a.put("iscombined", z ? "1" : "0");
        this.a.put("ordermoney", str3);
        this.a.put("POPtype", str4);
        this.a.put(PdMtaUtil.PARAM_KEY_SKUID, str5);
        this.a.put("orderid", str9);
        str10 = "";
        if (TextUtils.isEmpty(str8)) {
            str8 = "";
        }
        this.a.put(PairKey.CHANGETAG, str8);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            String str12 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.isDefaultCode : "";
            str10 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.payStrategy : "";
            a(cashierPayViewModel);
            b(cashierPayViewModel);
            str11 = str10;
            str10 = str12;
        } else {
            str11 = "";
        }
        this.a.put("isdefaultcode", str10);
        this.a.put("pay_strategy", str11);
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str7);
        this.a.put("paysource", str6);
        com.jd.lib.cashier.sdk.d.e.b.i(fragmentActivity, 1000, "JDCashierNew_Home_ConfirmPayment", b, o.b(this.a));
    }

    public void E(FragmentActivity fragmentActivity, String str, String str2, String str3) {
        this.a.clear();
        if (TextUtils.isEmpty(str2)) {
            str2 = DYConstants.DY_NULL_STR;
        }
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        this.a.put("foldRule", str2);
        this.a.put("orderid", str);
        this.a.put("button_state", str3);
        com.jd.lib.cashier.sdk.d.e.b.g(fragmentActivity, 1000, "JDCashierNew_Home_ExpandAll", o.b(this.a));
    }

    public void F(Context context, String str, String str2, String str3) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put("periodization", str2);
        if (!TextUtils.isEmpty(str3)) {
            this.a.put("coorder", str3);
        }
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String e2 = e(context);
        if (!TextUtils.isEmpty(e2)) {
            this.a.put("orderid", e2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_Instalment", o.b(this.a));
    }

    public void G(FragmentActivity fragmentActivity, String str, String str2, String str3, boolean z) {
        String str4;
        String str5;
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        this.a.put("position", str3);
        String str6 = "0";
        this.a.put("isdefault", z ? "1" : "0");
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str6 = cashierPayViewModel.b().u;
            str4 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            str5 = cashierPayViewModel.b().R;
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
        } else {
            str4 = "";
            str5 = str4;
        }
        String str7 = str5 != null ? str5 : "";
        this.a.put("strategy", str6);
        this.a.put("defautchoosetag", str7);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str4);
        com.jd.lib.cashier.sdk.d.e.b.h(fragmentActivity, 1000, "JDCashierNew_Home_SecToastPaymentMethod", "", b, o.b(this.a));
    }

    public void H(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_SecToastEntrance", o.b(this.a));
    }

    public void I(FragmentActivity fragmentActivity, String str, String str2, boolean z) {
        String str3;
        this.a.clear();
        this.a.put("code", str);
        String str4 = "0";
        this.a.put("isdefault", z ? "1" : "0");
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        str3 = "";
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str4 = cashierPayViewModel.b().u;
            str3 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
        }
        this.a.put("strategy", str4);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str3);
        com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_SecToastPaymentMethodExpo", "", b, o.b(this.a));
    }

    public void J(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_SecToastClose", o.b(this.a));
    }

    public void K(Context context, String str, String str2, String str3, String str4) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put("payid", str4);
        this.a.put("orderid", str3);
        this.a.put("resultstatus", str2);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_Payresult", o.b(this.a));
    }

    public void L(FragmentActivity fragmentActivity, String str, String str2, boolean z, String str3, String str4) {
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        this.a.clear();
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        this.a.put(PairKey.CHANGETAG, str3);
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String str10 = "0";
        this.a.put("isdefault", z ? "1" : "0");
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str10 = cashierPayViewModel.b().u;
            str5 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            str6 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.getTouchstoneExpids() : "";
            str7 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.isDefaultCode : "";
            str8 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.payStrategy : "";
            str9 = cashierPayViewModel.b().R;
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
            a(cashierPayViewModel);
            b(cashierPayViewModel);
        } else {
            str5 = "";
            str6 = str5;
            str7 = str6;
            str8 = str7;
            str9 = str8;
        }
        if (str9 == null) {
            str9 = "";
        }
        this.a.put("strategy", str10);
        this.a.put("defautchoosetag", str9);
        if (g.e(str)) {
            this.a.put("ischeap", str4);
        }
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        this.a.put("isdefaultcode", str7);
        this.a.put("pay_strategy", str8);
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str5);
        String b2 = o.b(this.a);
        this.f3527e.clear();
        this.f3527e.put("touchstone_expids", str6 != null ? str6 : "");
        com.jd.lib.cashier.sdk.d.e.a.l(fragmentActivity, 1000, "JDCashierNew_Home_PaymentMethod", "", b, b2, this.f3527e);
    }

    public void M(Context context, String str, String str2) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        String str3 = "";
        if (context instanceof CashierPayActivity) {
            CashierPayActivity cashierPayActivity = (CashierPayActivity) context;
            if (g0.a(cashierPayActivity)) {
                CashierPayViewModel x = cashierPayActivity.x();
                if (x.b().K != null) {
                    str3 = x.b().K.checkIsNewUser();
                }
            }
        }
        this.a.put("isnew", str3);
        com.jd.lib.cashier.sdk.d.e.b.d(context, 1000, "JDCashierNew_Home_DiscountExpo", b, o.b(this.a));
    }

    public void N(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.a(context, 1000, "JDCashierNew_Home_SecToastEntranceExpo", o.b(this.a));
    }

    public void O(CashierPayActivity cashierPayActivity, String str, String str2) {
        if (g0.a(cashierPayActivity)) {
            this.a.clear();
            this.a.put("code", str);
            this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
            String c2 = c(cashierPayActivity);
            if (!TextUtils.isEmpty(c2)) {
                this.a.put("appid", c2);
            }
            String b = o.b(this.a);
            this.a.clear();
            CashierPayViewModel x = cashierPayActivity.x();
            this.a.put("isnew", x.b().K != null ? x.b().K.checkIsNewUser() : "");
            com.jd.lib.cashier.sdk.d.e.b.i(cashierPayActivity, 1000, "JDCashierNew_Home_Periodtips", b, o.b(this.a));
        }
    }

    public void P(FragmentActivity fragmentActivity) {
        this.a.clear();
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            CashierPayEntity cashierPayEntity = cashierPayViewModel.b().K;
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
            this.a.put("isnew", cashierPayEntity != null ? cashierPayEntity.checkIsNewUser() : "");
        }
        String b = o.b(this.a);
        this.a.clear();
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.i(fragmentActivity, 1000, "JDCashierNew_Home_GYplan", o.b(this.a), b);
    }

    public void Q(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            this.a.clear();
            String c2 = c(fragmentActivity);
            if (!TextUtils.isEmpty(c2)) {
                this.a.put("appid", c2);
            }
            String b = o.b(this.a);
            this.a.clear();
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).b().K;
            if (cashierPayEntity != null) {
                this.a.put("isnew", cashierPayEntity.checkIsNewUser());
                this.a.put("orderid", cashierPayEntity.orderId);
            }
            com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_GYplanExpo", "", b, o.b(this.a));
        }
    }

    public void R(FragmentActivity fragmentActivity) {
        this.a.clear();
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        String str = "";
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            if (cashierPayViewModel.b().K != null) {
                str = cashierPayViewModel.b().K.checkIsNewUser();
            }
        }
        this.a.clear();
        this.a.put("isnew", str);
        com.jd.lib.cashier.sdk.d.e.b.h(fragmentActivity, 1000, "JDCashierNew_Home_PZJtips", "", b, o.b(this.a));
    }

    public void S(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            this.a.clear();
            String c2 = c(fragmentActivity);
            if (!TextUtils.isEmpty(c2)) {
                this.a.put("appid", c2);
            }
            String b = o.b(this.a);
            this.a.clear();
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).b().K;
            this.a.put("isnew", cashierPayEntity != null ? cashierPayEntity.checkIsNewUser() : "");
            com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_PZJtipsExpo", "", b, o.b(this.a));
        }
    }

    public void T(Context context, String str, boolean z, String str2, String str3) {
        this.a.clear();
        this.a.put("code", str3);
        this.a.put("periodization", str);
        this.a.put("isdefault", z ? "1" : "0");
        if (!TextUtils.isEmpty(str2)) {
            this.a.put("coorder", str2);
        }
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String e2 = e(context);
        if (!TextUtils.isEmpty(e2)) {
            this.a.put("orderid", e2);
        }
        com.jd.lib.cashier.sdk.d.e.b.a(context, 1000, "JDCashierNew_Home_InstalmentExpo", o.b(this.a));
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void U(androidx.fragment.app.FragmentActivity r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15) {
        /*
            r7 = this;
            java.util.Map<java.lang.String, java.lang.Object> r12 = r7.b
            r12.clear()
            java.util.Map<java.lang.String, java.lang.Object> r12 = r7.b
            java.lang.String r0 = "clerk"
            r12.put(r0, r10)
            java.util.Map<java.lang.String, java.lang.Object> r10 = r7.b
            java.lang.String r12 = "poptype"
            r10.put(r12, r11)
            java.util.Map<java.lang.String, java.lang.Object> r10 = r7.b
            java.lang.String r11 = "orderid"
            r10.put(r11, r9)
            java.lang.String r9 = r7.c(r8)
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 != 0) goto L2b
            java.util.Map<java.lang.String, java.lang.Object> r10 = r7.b
            java.lang.String r11 = "appid"
            r10.put(r11, r9)
        L2b:
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.b
            java.lang.String r10 = "newuser"
            r9.put(r10, r14)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.f3526c
            if (r9 != 0) goto L3d
            com.jd.lib.cashier.sdk.b.b.a r9 = new com.jd.lib.cashier.sdk.b.b.a
            r9.<init>()
            r7.f3526c = r9
        L3d:
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.f3526c
            r9.clear()
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.f3526c
            java.lang.String r10 = "event_type"
            java.lang.String r11 = "2"
            r9.put(r10, r11)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.f3526c
            java.lang.String r10 = "recom_info"
            r9.put(r10, r13)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.b
            java.util.Map<java.lang.String, java.lang.Object> r10 = r7.f3526c
            java.lang.String r11 = "ubb_feed_v2"
            r9.put(r11, r10)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.b
            java.lang.String r4 = com.jd.lib.cashier.sdk.core.utils.o.b(r9)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.b
            r9.clear()
            boolean r9 = com.jd.lib.cashier.sdk.core.utils.g0.a(r8)
            java.lang.String r10 = ""
            if (r9 == 0) goto L91
            androidx.lifecycle.ViewModelProvider r9 = androidx.lifecycle.ViewModelProviders.of(r8)
            java.lang.Class<com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel> r11 = com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel.class
            androidx.lifecycle.ViewModel r9 = r9.get(r11)
            com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel r9 = (com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel) r9
            com.jd.lib.cashier.sdk.core.aac.b r11 = r9.b()
            com.jd.lib.cashier.sdk.h.c.a r11 = (com.jd.lib.cashier.sdk.h.c.a) r11
            com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity r11 = r11.K
            if (r11 == 0) goto L91
            com.jd.lib.cashier.sdk.core.aac.b r9 = r9.b()
            com.jd.lib.cashier.sdk.h.c.a r9 = (com.jd.lib.cashier.sdk.h.c.a) r9
            com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity r9 = r9.K
            java.lang.String r9 = r9.checkIsNewUser()
            goto L92
        L91:
            r9 = r10
        L92:
            java.util.Map<java.lang.String, java.lang.Object> r11 = r7.b
            java.lang.String r12 = "isnew"
            r11.put(r12, r9)
            java.util.Map<java.lang.String, java.lang.Object> r9 = r7.b
            java.lang.String r5 = com.jd.lib.cashier.sdk.core.utils.o.b(r9)
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r7.f3527e
            r9.clear()
            if (r15 != 0) goto La7
            r15 = r10
        La7:
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r7.f3527e
            java.lang.String r10 = "touchstone_expids"
            r9.put(r10, r15)
            r1 = 1000(0x3e8, float:1.401E-42)
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r7.f3527e
            java.lang.String r2 = "JDCashierNew_Home_SettlementBackExpo"
            java.lang.String r3 = ""
            r0 = r8
            com.jd.lib.cashier.sdk.d.e.a.k(r0, r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.h.e.a.U(androidx.fragment.app.FragmentActivity, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public void V(FragmentActivity fragmentActivity, String str, String str2, boolean z, boolean z2, boolean z3, String str3, String str4, String str5) {
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String str12 = "0";
        this.a.put("isusing", z ? "1" : "0");
        this.a.put("iscombined", z2 ? "1" : "0");
        this.a.put("isdefault", z3 ? "1" : "0");
        this.a.put(PairKey.CHANGETAG, TextUtils.isEmpty(str4) ? "" : str4);
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str12 = cashierPayViewModel.b().u;
            str6 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            str7 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.getTouchstoneExpids() : "";
            str8 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.isCertificated : "";
            str9 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.isDefaultCode : "";
            str10 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.payStrategy : "";
            str11 = cashierPayViewModel.b().R;
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
            a(cashierPayViewModel);
            b(cashierPayViewModel);
        } else {
            str6 = "";
            str7 = str6;
            str8 = str7;
            str9 = str8;
            str10 = str9;
            str11 = str10;
        }
        this.a.put("isopenXJK", str3);
        this.a.put("strategy", str12);
        if (g.e(str)) {
            this.a.put("ischeap", str5);
        }
        if (str11 == null) {
            str11 = "";
        }
        if (str8 == null) {
            str8 = "";
        }
        if (g.k(str)) {
            this.a.put("isCertificated", str8);
        }
        this.a.put("defautchoosetag", str11);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        this.a.put("isdefaultcode", str9);
        this.a.put("pay_strategy", str10);
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str6);
        String b2 = o.b(this.a);
        this.f3527e.clear();
        this.f3527e.put("touchstone_expids", str7 != null ? str7 : "");
        com.jd.lib.cashier.sdk.d.e.a.k(fragmentActivity, 1000, "JDCashierNew_Home_PaymentMethodExpo", "", b, b2, this.f3527e);
    }

    public void W(FragmentActivity fragmentActivity, String str, String str2, String str3, String str4, String str5, String str6, String str7, Map<String, Object> map, Map<String, Object> map2) {
        this.a.clear();
        if (TextUtils.isEmpty(str6)) {
            str6 = DYConstants.DY_NULL_STR;
        }
        if (TextUtils.isEmpty(str7)) {
            str7 = DYConstants.DY_NULL_STR;
        }
        this.a.put("orderid", str);
        this.a.put(PdMtaUtil.PARAM_KEY_SKUID, str2);
        this.a.put("paysource", str3);
        this.a.put("ordermoney", str5);
        this.a.put("foldRule", str6);
        this.a.put("skinrule", str7);
        this.a.put("preinterposetype", str4);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        if (map != null && !map.isEmpty()) {
            for (String str8 : map.keySet()) {
                this.a.put(str8, map.get(str8));
            }
        }
        if (map2 != null && !map2.isEmpty()) {
            for (String str9 : map2.keySet()) {
                this.a.put(str9, map2.get(str9));
            }
        }
        com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_orderid", "", o.b(this.a), "");
    }

    public void X(Context context, String str, String str2, String str3) {
        this.a.clear();
        this.a.put("ordermoney", str);
        this.a.put("suggestprice", str2);
        this.a.put("writeprice", str3);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_SubStepConfirmPayment", o.b(this.a));
    }

    public void Y(Context context, String str) {
        this.a.clear();
        this.a.put("ordermoney", str);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        if (context instanceof CashierPayActivity) {
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().K;
            this.a.put("orderid", cashierPayEntity != null ? cashierPayEntity.orderId : "");
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_SubStepEntrance", o.b(this.a));
    }

    public void Z(Context context, String str) {
        this.a.clear();
        this.a.put("ordermoney", str);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        if (context instanceof CashierPayActivity) {
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().K;
            this.a.put("orderid", cashierPayEntity != null ? cashierPayEntity.orderId : "");
        }
        com.jd.lib.cashier.sdk.d.e.b.a(context, 1000, "JDCashierNew_Home_SubStepEntranceExpo", o.b(this.a));
    }

    public void a0(Context context, String str) {
        this.a.clear();
        this.a.put("ordermoney", str);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_SubStepRec", o.b(this.a));
    }

    public void b0(FragmentActivity fragmentActivity, String str) {
        this.a.clear();
        this.a.put("ordermoney", str);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.a(fragmentActivity, 1000, "JDCashierNew_Home_SubStepRecExpo", o.b(this.a));
    }

    public void c0(FragmentActivity fragmentActivity, String str) {
        this.a.clear();
        this.a.put("Unpaidmoney", str);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(fragmentActivity, 1000, "JDCashierNew_Home_SubStepSuccessContinue", o.b(this.a));
    }

    public void d0(FragmentActivity fragmentActivity, String str) {
        this.a.clear();
        this.a.put("Unpaidmoney", str);
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(fragmentActivity, 1000, "JDCashierNew_Home_SubStepSuccessCheck", o.b(this.a));
    }

    public void e0(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.a(context, 1000, "JDCashierNew_Home_SubStepSuccessToastExpo", o.b(this.a));
    }

    public void f(Context context, String str, int i2) {
        this.a.clear();
        this.a.put("abnormalorderno", str);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, i2, "JDCashierNew_Home_AbnormalOrderToastClose", o.b(this.a));
    }

    public void f0(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        if (context instanceof CashierPayActivity) {
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().K;
            this.a.put("orderid", cashierPayEntity != null ? cashierPayEntity.orderId : "");
        }
        com.jd.lib.cashier.sdk.d.e.b.a(context, 1000, "JDCashierNew_Home_SubStepToastExpo", o.b(this.a));
    }

    public void g(Context context, String str, int i2) {
        this.a.clear();
        this.a.put("abnormalorderno", str);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.a(context, i2, "JDCashierNew_Home_AbnormalOrderToastExpo", o.b(this.a));
    }

    public void g0(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_SubStepToastRule", o.b(this.a));
    }

    public void h(Context context, String str, int i2) {
        this.a.clear();
        this.a.put("abnormalorderno", str);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, i2, "JDCashierNew_Home_AbnormalOrderToastCheck", o.b(this.a));
    }

    public void h0(FragmentActivity fragmentActivity, String str, String str2, String str3) {
        if (g0.a(fragmentActivity)) {
            this.a.clear();
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).b().K;
            if (cashierPayEntity != null) {
                this.a.put("isnew", cashierPayEntity.checkIsNewUser());
            }
            String b = o.b(this.a);
            this.a.clear();
            if (cashierPayEntity != null) {
                this.a.put("orderid", cashierPayEntity.orderId);
            }
            String c2 = c(fragmentActivity);
            if (!TextUtils.isEmpty(c2)) {
                this.a.put("appid", c2);
            }
            this.a.put("code", str);
            this.a.put("yhtype", str3);
            this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
            com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_Dynamiceffect", "", o.b(this.a), b);
        }
    }

    public void i(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.b.clear();
        this.b.put("clerk", str4);
        this.b.put("poptype", str3);
        this.b.put("orderid", str);
        this.b.put("isfromsettlementpage", TextUtils.equals(str2, "1") ? "1" : "0");
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.b.put("appid", c2);
        }
        this.b.put("newuser", str7);
        if (this.f3526c == null) {
            this.f3526c = new com.jd.lib.cashier.sdk.b.b.a();
        }
        this.f3526c.clear();
        this.f3526c.put("event_type", "1");
        this.f3526c.put("recom_info", str6);
        this.b.put("ubb_feed_v2", this.f3526c);
        this.f3527e.clear();
        if (str8 == null) {
            str8 = "";
        }
        this.f3527e.put("touchstone_expids", str8);
        com.jd.lib.cashier.sdk.d.e.a.m(context, 1000, "JDCashierNew_Home_SettlementBackCancel", o.b(this.b), this.f3527e);
    }

    public void i0(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_BaiTiao_FriendPayToastClose", o.b(this.a));
    }

    public void j(Context context, String str) {
        this.a.clear();
        this.a.put("isfromsettlementpage", TextUtils.equals(str, "1") ? "1" : "0");
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_SettlementGetBack", o.b(this.a));
    }

    public void j0(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_BaiTiao_FriendPayToastCnt", o.b(this.a));
    }

    public void k(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.b.clear();
        this.b.put("isfromsettlementpage", TextUtils.equals(str2, "1") ? "1" : "0");
        this.b.put("reason", str3);
        this.b.put("clerk", str6);
        this.b.put("poptype", str7);
        this.b.put("orderid", str);
        this.b.put("leaveto", str11);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.b.put("appid", c2);
        }
        this.b.put("newuser", str10);
        if (this.f3526c == null) {
            this.f3526c = new com.jd.lib.cashier.sdk.b.b.a();
        }
        this.f3526c.clear();
        this.f3526c.put("event_type", "1");
        this.f3526c.put("recom_info", str9);
        this.b.put("ubb_feed_v2", this.f3526c);
        String b = o.b(this.b);
        this.b.clear();
        this.b.put("isnew", str5);
        this.b.put("paysource", str4);
        String b2 = o.b(this.b);
        this.f3527e.clear();
        if (str12 == null) {
            str12 = "";
        }
        this.f3527e.put("touchstone_expids", str12);
        com.jd.lib.cashier.sdk.d.e.a.n(context, 1000, "JDCashierNew_Home_SettlementBackConfirm", b, b2, this.f3527e);
    }

    public void k0(Context context, PopBusinessMap popBusinessMap) {
        if (popBusinessMap != null) {
            this.a.clear();
            this.a.put("orderid", popBusinessMap.orderId);
            this.a.put("user_pin", popBusinessMap.user_pin);
            String c2 = c(context);
            if (!TextUtils.isEmpty(c2)) {
                this.a.put("appid", c2);
            }
            com.jd.lib.cashier.sdk.d.e.b.a(context, 1000, "JDCashierNew_BaiTiao_FriendPayToastExpo", o.b(this.a));
        }
    }

    public void l(FragmentActivity fragmentActivity, String str) {
        if (g0.a(fragmentActivity)) {
            this.a.clear();
            this.a.put("code", str);
            String c2 = c(fragmentActivity);
            if (!TextUtils.isEmpty(c2)) {
                this.a.put("appid", c2);
            }
            String b = o.b(this.a);
            this.a.clear();
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).b().K;
            this.a.put("isnew", cashierPayEntity != null ? cashierPayEntity.checkIsNewUser() : "");
            com.jd.lib.cashier.sdk.d.e.b.h(fragmentActivity, 1000, "JDCashierNew_Home_Quotatips", "", b, o.b(this.a));
        }
    }

    public void l0(CashierPayActivity cashierPayActivity) {
        if (cashierPayActivity == null) {
            return;
        }
        this.a.clear();
        CashierPayEntity cashierPayEntity = cashierPayActivity.x().b().K;
        this.a.put("isnew", cashierPayEntity != null ? cashierPayEntity.checkIsNewUser() : "");
        String b = o.b(this.a);
        this.a.clear();
        String c2 = c(cashierPayActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.c(cashierPayActivity, 1000, "JDCashierNew_Home_BigpayPopExpo", "", o.b(this.a), b);
    }

    public void m(FragmentActivity fragmentActivity) {
        if (g0.a(fragmentActivity)) {
            this.a.clear();
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class)).b().K;
            this.a.put("isnew", cashierPayEntity != null ? cashierPayEntity.checkIsNewUser() : "");
            String b = o.b(this.a);
            this.a.clear();
            String c2 = c(fragmentActivity);
            if (!TextUtils.isEmpty(c2)) {
                this.a.put("appid", c2);
            }
            com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_QuotatipsExpo", "", o.b(this.a), b);
        }
    }

    public void m0(Context context) {
        if (this.d == null) {
            this.d = new HashMap();
        }
        this.d.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.d.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.j(context, 1000, o.b(this.d));
    }

    public void n(Context context, String str) {
        this.a.clear();
        this.a.put("text", str);
        if (context instanceof CashierPayActivity) {
            CashierPayEntity cashierPayEntity = ((CashierPayViewModel) ViewModelProviders.of((CashierPayActivity) context).get(CashierPayViewModel.class)).b().K;
            this.a.put("orderid", cashierPayEntity != null ? cashierPayEntity.orderId : "");
        }
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.b(context, 1000, "JDCashierNew_Home_BaitiaoRateExpo", "", o.b(this.a));
    }

    public void n0(FragmentActivity fragmentActivity, String str) {
        String str2;
        this.a.clear();
        this.a.put("POPtype", str);
        str2 = "";
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str2 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
        }
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str2);
        com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_XYredbagExpo", "", b, o.b(this.a));
    }

    public void o(FragmentActivity fragmentActivity) {
        this.a.clear();
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        String str = "";
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            if (cashierPayViewModel.b().K != null) {
                str = cashierPayViewModel.b().K.checkIsNewUser();
            }
        }
        this.a.put("isnew", str);
        com.jd.lib.cashier.sdk.d.e.b.i(fragmentActivity, 1000, "JDCashierNew_Home_BigpayPopLink", b, o.b(this.a));
    }

    public void o0(FragmentActivity fragmentActivity, String str, String str2) {
        String str3;
        this.a.clear();
        this.a.put("POPtype", str);
        str3 = "";
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str3 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
        }
        this.a.put("type", TextUtils.equals(str2, "0") ? "new" : "old");
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str3);
        com.jd.lib.cashier.sdk.d.e.b.c(fragmentActivity, 1000, "JDCashierNew_Home_ChangePayPopExpo", "", b, o.b(this.a));
    }

    public void onClearEvent() {
        HashSet<String> hashSet = this.f3528f;
        if (hashSet != null) {
            hashSet.clear();
        }
    }

    public void p(FragmentActivity fragmentActivity, String str, String str2) {
        String str3;
        this.a.clear();
        this.a.put("POPtype", str);
        str3 = "";
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str3 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
        }
        this.a.put("type", TextUtils.equals(str2, "0") ? "new" : "old");
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str3);
        com.jd.lib.cashier.sdk.d.e.b.i(fragmentActivity, 1000, "JDCashierNew_Home_ChangePayPopDefine", b, o.b(this.a));
    }

    public void q(FragmentActivity fragmentActivity, String str) {
        String str2;
        this.a.clear();
        this.a.put("POPtype", str);
        str2 = "";
        if (g0.a(fragmentActivity)) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of(fragmentActivity).get(CashierPayViewModel.class);
            str2 = cashierPayViewModel.b().K != null ? cashierPayViewModel.b().K.checkIsNewUser() : "";
            this.a.put("orderid", cashierPayViewModel.b().f3511e);
        }
        String c2 = c(fragmentActivity);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str2);
        com.jd.lib.cashier.sdk.d.e.b.i(fragmentActivity, 1000, "JDCashierNew_Home_ChangePayPopClose", b, o.b(this.a));
    }

    public void r(Context context, String str) {
        this.a.clear();
        this.a.put("orderid", str);
        this.a.put("jumptype", "2");
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_OrderCenter", o.b(this.a));
    }

    public void s(Context context, String str) {
        this.a.clear();
        this.a.put("orderid", str);
        this.a.put("jumptype", "1");
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_OrderCenter", o.b(this.a));
    }

    public void t(Context context, PopBusinessMap popBusinessMap) {
        PopConfirmBtnMta popConfirmBtnMta;
        if (popBusinessMap == null || (popConfirmBtnMta = popBusinessMap.confirmButtonMTA) == null || TextUtils.isEmpty(popConfirmBtnMta.getEventId())) {
            return;
        }
        com.jd.lib.cashier.sdk.d.e.b.e(context, 1000, popBusinessMap.confirmButtonMTA.getEventId(), o.b(popBusinessMap.confirmButtonMTA.getJsonParam()));
    }

    public void u(Context context, String str, String str2, boolean z, String str3) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put("couponid", str3);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        this.a.put("isDefault", z ? "1" : "0");
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_CouponChose", o.b(this.a));
    }

    public void v(Context context, String str, String str2, boolean z, String str3) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        this.a.put("couponid", str3);
        this.a.put("isDefault", z ? "1" : "0");
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.a(context, 1000, "JDCashierNew_Home_CouponChoseExpo", o.b(this.a));
    }

    public void w(Context context, String str, String str2) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_CouponClose", o.b(this.a));
    }

    public void x(Context context) {
        this.a.clear();
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "BaiTiao_CouponListConfirm", o.b(this.a));
    }

    public void y(Context context, String str, String str2) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        com.jd.lib.cashier.sdk.d.e.b.g(context, 1000, "JDCashierNew_Home_CouponNotUse", o.b(this.a));
    }

    public void z(Context context, String str, String str2, String str3, String str4) {
        this.a.clear();
        this.a.put("code", str);
        this.a.put(PairKey.UNIQUE_CHANNEL_ID, str2);
        this.a.put("orderid", str3);
        String c2 = c(context);
        if (!TextUtils.isEmpty(c2)) {
            this.a.put("appid", c2);
        }
        String b = o.b(this.a);
        this.a.clear();
        this.a.put("isnew", str4);
        com.jd.lib.cashier.sdk.d.e.b.i(context, 1000, "JDCashierNew_DigitalPopConfirm", b, o.b(this.a));
    }
}
