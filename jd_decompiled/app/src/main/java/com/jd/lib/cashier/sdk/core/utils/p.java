package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.navigator.CashierNavigator;
import com.jd.cashier.app.jdlibcutter.protocol.router.ICashierRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IInnerRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOrderRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOuterRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IWebRouter;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.connect.common.Constants;
import java.util.Map;

/* loaded from: classes14.dex */
public class p {

    /* loaded from: classes14.dex */
    public interface a {
        void onRefresh();
    }

    public static void a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        IOuterRouter router = DependInitializer.getRouter();
        IWebRouter webRouter = DependInitializer.getWebRouter();
        IInnerRouter innerRouter = DependInitializer.getInnerRouter();
        if (router != null && router.isTargetRouter(str)) {
            router.router(context, str);
        } else if (innerRouter != null && innerRouter.isTargetRouter(str)) {
            innerRouter.router(context, str);
        } else if (webRouter == null || !webRouter.isTargetRouter(str)) {
        } else {
            webRouter.routerToWeb(context, str);
        }
    }

    public static void b(Context context, String str, String str2, a aVar) {
        if (context == null || TextUtils.isEmpty(str2)) {
            return;
        }
        char c2 = '\uffff';
        try {
            int hashCode = str2.hashCode();
            switch (hashCode) {
                case 49:
                    if (str2.equals("1")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 50:
                    if (str2.equals("2")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 51:
                    if (str2.equals("3")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 52:
                    if (str2.equals("4")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 53:
                    if (str2.equals("5")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 54:
                    if (str2.equals("6")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 55:
                    if (str2.equals("7")) {
                        c2 = 11;
                        break;
                    }
                    break;
                case 56:
                    if (str2.equals("8")) {
                        c2 = '\f';
                        break;
                    }
                    break;
                case 57:
                    if (str2.equals("9")) {
                        c2 = 14;
                        break;
                    }
                    break;
                default:
                    switch (hashCode) {
                        case R2.attr.progress_cancel /* 1567 */:
                            if (str2.equals("10")) {
                                c2 = '\r';
                                break;
                            }
                            break;
                        case R2.attr.prospectNum /* 1568 */:
                            if (str2.equals("11")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case R2.attr.prospectStyle /* 1569 */:
                            if (str2.equals("12")) {
                                c2 = 7;
                                break;
                            }
                            break;
                        case R2.attr.pstsDividerColor /* 1570 */:
                            if (str2.equals("13")) {
                                c2 = '\b';
                                break;
                            }
                            break;
                        case R2.attr.pstsDividerLeftRightMargin /* 1571 */:
                            if (str2.equals("14")) {
                                c2 = '\n';
                                break;
                            }
                            break;
                        case R2.attr.pstsDividerPadding /* 1572 */:
                            if (str2.equals("15")) {
                                c2 = '\t';
                                break;
                            }
                            break;
                        case R2.attr.pstsDividerWidth /* 1573 */:
                            if (str2.equals("16")) {
                                c2 = 15;
                                break;
                            }
                            break;
                        case R2.attr.pstsIndicatorColor /* 1574 */:
                            if (str2.equals(Constants.VIA_REPORT_TYPE_START_GROUP)) {
                                c2 = 16;
                                break;
                            }
                            break;
                    }
            }
            switch (c2) {
                case 0:
                    return;
                case 1:
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case 2:
                    e.c();
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case 3:
                    n(context, str);
                    return;
                case 4:
                    if (TextUtils.isEmpty(str)) {
                        m(context);
                    } else {
                        n(context, str);
                    }
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case 5:
                    e.c();
                    if (TextUtils.isEmpty(str)) {
                        m(context);
                    } else {
                        n(context, str);
                    }
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case 6:
                    n(context, str);
                    if (aVar != null) {
                        aVar.onRefresh();
                        return;
                    }
                    return;
                case 7:
                    o(context, str);
                    return;
                case '\b':
                    o(context, str);
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case '\t':
                    e.c();
                    o(context, str);
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case '\n':
                    o(context, str);
                    if (aVar != null) {
                        aVar.onRefresh();
                        return;
                    }
                    return;
                case 11:
                    h(context, str);
                    return;
                case '\f':
                    if (TextUtils.isEmpty(str)) {
                        m(context);
                    } else {
                        h(context, str);
                    }
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case '\r':
                    e.c();
                    if (TextUtils.isEmpty(str)) {
                        m(context);
                    } else {
                        h(context, str);
                    }
                    PayTaskStackManager.removeAllCashierTask();
                    return;
                case 14:
                case 15:
                    h(context, str);
                    if (aVar != null) {
                        aVar.onRefresh();
                        return;
                    }
                    return;
                case 16:
                    j(context);
                    return;
                default:
                    m(context);
                    PayTaskStackManager.removeAllCashierTask();
                    return;
            }
        } catch (Exception unused) {
            m(context);
            PayTaskStackManager.removeAllCashierTask();
        }
    }

    private static void c(FragmentActivity fragmentActivity, int i2, Map<String, String> map) {
        if (map == null) {
            return;
        }
        String str = map.get("url");
        String str2 = map.get("appId");
        boolean equals = TextUtils.equals(map.get("supportXView"), "0");
        boolean equals2 = TextUtils.equals(map.get("statusBarHint"), "1");
        if (fragmentActivity == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            IOuterRouter router = DependInitializer.getRouter();
            if (x.a(Uri.parse(str).getScheme())) {
                Bundle bundle = new Bundle();
                bundle.putString("url", str);
                bundle.putBoolean("statusBarHint", equals2);
                if (!TextUtils.isEmpty(str2)) {
                    bundle.putString("appId", str2);
                }
                if (equals) {
                    ICashierRouter cashierRouter = DependInitializer.getCashierRouter();
                    if (cashierRouter != null) {
                        cashierRouter.routerToPluginFinishPage(fragmentActivity, bundle, i2);
                        return;
                    }
                    return;
                }
                CashierNavigator.jumpToFinishPage(fragmentActivity, bundle, i2);
            } else if (router == null || !router.isTargetRouter(str)) {
            } else {
                router.router(fragmentActivity, str);
            }
        } catch (Exception unused) {
            m(fragmentActivity);
            fragmentActivity.finish();
        }
    }

    public static void d(FragmentActivity fragmentActivity, Map<String, String> map) {
        c(fragmentActivity, -1, map);
    }

    public static void e(FragmentActivity fragmentActivity, Map<String, String> map) {
        c(fragmentActivity, 0, map);
    }

    public static void f(FragmentActivity fragmentActivity, Map<String, String> map) {
        c(fragmentActivity, 0, map);
    }

    public static void g(Context context, Bundle bundle) {
        if (context != null) {
            try {
                CashierNavigator.jumpToPayPage(context, bundle);
            } catch (Exception e2) {
                r.d("CashierJumpUtil", e2.getMessage());
            }
        }
    }

    public static void h(Context context, String str) {
        IWebRouter webRouter;
        if (context == null || TextUtils.isEmpty(str) || (webRouter = DependInitializer.getWebRouter()) == null) {
            return;
        }
        webRouter.routerToWeb(context, str);
    }

    public static void i(Context context, String str) {
        IWebRouter webRouter;
        if (context == null || TextUtils.isEmpty(str) || (webRouter = DependInitializer.getWebRouter()) == null) {
            return;
        }
        webRouter.routerToWebWithoutMore(context, str);
    }

    public static void j(Context context) {
        if (context instanceof CashierPayActivity) {
            CashierPayViewModel cashierPayViewModel = (CashierPayViewModel) ViewModelProviders.of((FragmentActivity) context).get(CashierPayViewModel.class);
            cashierPayViewModel.b().H = false;
            cashierPayViewModel.d((CashierPayActivity) context);
        }
    }

    public static void k(Context context, String str) {
        IOrderRouter orderRouter;
        if (context != null) {
            try {
                if (TextUtils.isEmpty(str) || (orderRouter = DependInitializer.getOrderRouter()) == null) {
                    return;
                }
                orderRouter.routerToOrderDetailLayer(context, str);
            } catch (Exception e2) {
                r.d("CashierJumpUtil", e2.getMessage());
            }
        }
    }

    public static void l(Context context, String str) {
        try {
            IOrderRouter orderRouter = DependInitializer.getOrderRouter();
            if (context == null || TextUtils.isEmpty(str)) {
                return;
            }
            orderRouter.routerToOrderDetail(context, str);
        } catch (Exception e2) {
            r.d("CashierJumpUtil", e2.getMessage());
        }
    }

    public static void m(Context context) {
        try {
            IOrderRouter orderRouter = DependInitializer.getOrderRouter();
            if (context == null || orderRouter == null) {
                return;
            }
            orderRouter.routerToOrderList(context);
        } catch (Exception e2) {
            r.d("CashierJumpUtil", e2.getMessage());
        }
    }

    public static void n(Context context, String str) {
        IOuterRouter router;
        if (TextUtils.isEmpty(str) || context == null || Uri.parse(str).getScheme() == null || (router = DependInitializer.getRouter()) == null) {
            return;
        }
        router.router(context, str);
    }

    private static void o(Context context, String str) {
        IInnerRouter innerRouter;
        if (context == null || TextUtils.isEmpty(str) || (innerRouter = DependInitializer.getInnerRouter()) == null) {
            return;
        }
        innerRouter.router(context, str);
    }
}
