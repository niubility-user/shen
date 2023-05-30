package com.jd.cashier.app.jdlibcutter.initialize;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.impl.activity.ActivityConnectorCreatorImpl;
import com.jd.cashier.app.jdlibcutter.impl.aura.JDAuraImpl;
import com.jd.cashier.app.jdlibcutter.impl.bcashier.BCashierConfigImpl;
import com.jd.cashier.app.jdlibcutter.impl.caas.JDHWCaaS;
import com.jd.cashier.app.jdlibcutter.impl.config.JDSdkHostConfigImpl;
import com.jd.cashier.app.jdlibcutter.impl.darkmode.JDDarkModeImpl;
import com.jd.cashier.app.jdlibcutter.impl.des.ThreeDesImpl;
import com.jd.cashier.app.jdlibcutter.impl.dynamic.JDCashierDynamic;
import com.jd.cashier.app.jdlibcutter.impl.eldermode.JDElderModeImpl;
import com.jd.cashier.app.jdlibcutter.impl.font.JDFontImpl;
import com.jd.cashier.app.jdlibcutter.impl.http.JDHttpCreator;
import com.jd.cashier.app.jdlibcutter.impl.imageloader.ImageLoaderImpl;
import com.jd.cashier.app.jdlibcutter.impl.lbs.LbsImpl;
import com.jd.cashier.app.jdlibcutter.impl.live.JDLive;
import com.jd.cashier.app.jdlibcutter.impl.log.LogImpl;
import com.jd.cashier.app.jdlibcutter.impl.login.LoginImpl;
import com.jd.cashier.app.jdlibcutter.impl.monitor.ExceptionMonitorImpl;
import com.jd.cashier.app.jdlibcutter.impl.mta.ABMtaImpl;
import com.jd.cashier.app.jdlibcutter.impl.mta.MtaImpl;
import com.jd.cashier.app.jdlibcutter.impl.notification.JDNotificationImpl;
import com.jd.cashier.app.jdlibcutter.impl.parser.JDJsonParser;
import com.jd.cashier.app.jdlibcutter.impl.pay.JDPay;
import com.jd.cashier.app.jdlibcutter.impl.pay.JDQQPay;
import com.jd.cashier.app.jdlibcutter.impl.pay.JDUnionPay;
import com.jd.cashier.app.jdlibcutter.impl.pay.JDWXPay;
import com.jd.cashier.app.jdlibcutter.impl.privacy.JDPrivacyImpl;
import com.jd.cashier.app.jdlibcutter.impl.risk.RiskImpl;
import com.jd.cashier.app.jdlibcutter.impl.router.CashierRouterImpl;
import com.jd.cashier.app.jdlibcutter.impl.router.InnerRouterImpl;
import com.jd.cashier.app.jdlibcutter.impl.router.OpenAppRouterImpl;
import com.jd.cashier.app.jdlibcutter.impl.router.OrderRouterImpl;
import com.jd.cashier.app.jdlibcutter.impl.router.WebRouterImpl;
import com.jd.cashier.app.jdlibcutter.impl.share.ShareImpl;
import com.jd.cashier.app.jdlibcutter.impl.stackmanager.JDStackManager;
import com.jd.cashier.app.jdlibcutter.impl.thread.JDThreadPool;
import com.jd.cashier.app.jdlibcutter.impl.ui.dialog.JDDialogImpl;
import com.jd.cashier.app.jdlibcutter.impl.ui.loading.LoadingImpl;
import com.jd.cashier.app.jdlibcutter.impl.ui.push.JDPush;
import com.jd.cashier.app.jdlibcutter.impl.ui.title.JDThemeTitleChangeEventCreatorImpl;
import com.jd.cashier.app.jdlibcutter.impl.ui.title.JDThemeTitleImpl;
import com.jd.cashier.app.jdlibcutter.impl.ui.toast.JDToast;
import com.jd.cashier.app.jdlibcutter.impl.ui.webview.JDReminder;
import com.jd.cashier.app.jdlibcutter.impl.ui.webview.JDWebViewFragment;
import com.jd.cashier.app.jdlibcutter.impl.ui.webview.JDX5WebView;
import com.jd.cashier.app.jdlibcutter.impl.ui.xview.JDXView;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;

/* loaded from: classes13.dex */
public final class CashierSdkInitializer {
    public static void initSdk(Context context, IConfig iConfig) {
        try {
            DependInitializer.initialize(new DependInitializer.Builder().setSdkConfig(iConfig).setSdkHostConfig(new JDSdkHostConfigImpl()).setLbs(new LbsImpl()).setLog(new LogImpl()).setMta(new MtaImpl()).setABMta(new ABMtaImpl()).setRisk(new RiskImpl()).setBCashierConfig(new BCashierConfigImpl()).setAura(new JDAuraImpl()).setShare(new ShareImpl()).setDes(new ThreeDesImpl()).setToast(new JDToast()).setPush(new JDPush()).setLive(new JDLive()).setDialog(new JDDialogImpl()).setLoading(new LoadingImpl()).setFont(new JDFontImpl()).setUserLogin(new LoginImpl()).setJDPay(new JDPay()).setWXPay(new JDWXPay()).setQQPay(new JDQQPay()).setUnionPay(new JDUnionPay()).setXView(new JDXView()).setReminder(new JDReminder()).setWebView(new JDX5WebView()).setDynamic(new JDCashierDynamic()).setWebFragment(new JDWebViewFragment()).setThreadPool(new JDThreadPool()).setHttpCreator(new JDHttpCreator()).setHWCaaS(new JDHWCaaS()).setPrivacy(new JDPrivacyImpl()).setDarkMode(new JDDarkModeImpl()).setElderMode(new JDElderModeImpl()).setJsonParser(new JDJsonParser()).setWebRouter(new WebRouterImpl()).setOuterRouter(new OpenAppRouterImpl()).setInnerRouter(new InnerRouterImpl()).setOrderRouter(new OrderRouterImpl()).setCashierRouter(new CashierRouterImpl()).setImageLoader(new ImageLoaderImpl()).setStackManager(new JDStackManager()).setThemeTitle(new JDThemeTitleImpl()).setNotificationImpl(new JDNotificationImpl()).setExceptionMonitor(new ExceptionMonitorImpl()).setActivityConnectorCreator(new ActivityConnectorCreatorImpl()).setTitleChangeEventCreator(new JDThemeTitleChangeEventCreatorImpl()).build(context));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
