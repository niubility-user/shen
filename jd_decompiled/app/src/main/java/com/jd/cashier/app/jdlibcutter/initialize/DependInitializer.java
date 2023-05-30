package com.jd.cashier.app.jdlibcutter.initialize;

import android.content.Context;
import com.jd.cashier.app.jdlibcutter.protocol.activity.IActivityConnectorCreator;
import com.jd.cashier.app.jdlibcutter.protocol.aura.IAura;
import com.jd.cashier.app.jdlibcutter.protocol.bcashier.IBCashierConfig;
import com.jd.cashier.app.jdlibcutter.protocol.caas.IHWCaaS;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.config.IHostConfig;
import com.jd.cashier.app.jdlibcutter.protocol.darkmode.IDarkMode;
import com.jd.cashier.app.jdlibcutter.protocol.des.IDes;
import com.jd.cashier.app.jdlibcutter.protocol.dynamic.IDynamic;
import com.jd.cashier.app.jdlibcutter.protocol.eldermode.IElderMode;
import com.jd.cashier.app.jdlibcutter.protocol.font.IFont;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpCreator;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.IImageLoader;
import com.jd.cashier.app.jdlibcutter.protocol.lbs.ILbs;
import com.jd.cashier.app.jdlibcutter.protocol.live.ILive;
import com.jd.cashier.app.jdlibcutter.protocol.log.ILog;
import com.jd.cashier.app.jdlibcutter.protocol.login.ILogin;
import com.jd.cashier.app.jdlibcutter.protocol.monitor.IExceptionMonitor;
import com.jd.cashier.app.jdlibcutter.protocol.mta.IABMta;
import com.jd.cashier.app.jdlibcutter.protocol.mta.IMta;
import com.jd.cashier.app.jdlibcutter.protocol.notification.INotification;
import com.jd.cashier.app.jdlibcutter.protocol.parser.IJsonParser;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.IJDPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.IQQPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.IWXPay;
import com.jd.cashier.app.jdlibcutter.protocol.privacy.IPrivacy;
import com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk;
import com.jd.cashier.app.jdlibcutter.protocol.router.ICashierRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IInnerRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOrderRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IOuterRouter;
import com.jd.cashier.app.jdlibcutter.protocol.router.IWebRouter;
import com.jd.cashier.app.jdlibcutter.protocol.share.IShare;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.IStackManager;
import com.jd.cashier.app.jdlibcutter.protocol.thread.IThreadPool;
import com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog;
import com.jd.cashier.app.jdlibcutter.protocol.ui.loading.ILoading;
import com.jd.cashier.app.jdlibcutter.protocol.ui.push.IPush;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.ITitleChangeEventCreator;
import com.jd.cashier.app.jdlibcutter.protocol.ui.toast.IToast;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IReminder;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebView;
import com.jd.cashier.app.jdlibcutter.protocol.ui.webview.IWebViewFragment;
import com.jd.cashier.app.jdlibcutter.protocol.ui.xview.IXView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;

/* loaded from: classes13.dex */
public final class DependInitializer {
    private static Builder mBuilder;

    /* loaded from: classes13.dex */
    public static class Builder {
        private IABMta mABMta;
        private IActivityConnectorCreator mActivityConnectorCreator;
        private IAura mAura;
        private IBCashierConfig mBCashierConfig;
        private ICashierRouter mCashierRouter;
        private IConfig mConfig;
        private IDarkMode mDarkMode;
        private IDes mDes;
        private IDialog mDialog;
        private IDynamic mDynamic;
        private IExceptionMonitor mExceptionMonitor;
        private IFont mFont;
        private IHWCaaS mHWCassS;
        private IHostConfig mHostConfig;
        private IHttpCreator mHttpCreator;
        private IElderMode mIElderMode;
        private IImageLoader mImageLoader;
        private IInnerRouter mInnerRouter;
        private IJDPay mJDPay;
        private IJsonParser mJsonParser;
        private ILbs mLbs;
        private ILive mLive;
        private ILoading mLoading;
        private ILog mLog;
        private ILogin mLogin;
        private IWebViewFragment mMFragment;
        private IMta mMta;
        private INotification mNotificationImpl;
        private IOrderRouter mOrderRouter;
        private IOuterRouter mOuterRouter;
        private IPrivacy mPrivacy;
        private IPush mPush;
        private IQQPay mQQPay;
        private IReminder mReminder;
        private IRisk mRisk;
        private IShare mShare;
        private IStackManager mStackManager;
        private IThemeTitle mThemeTitleImpl;
        private IThreadPool mThreadPool;
        private ITitleChangeEventCreator mTitleChangeEventCreator;
        private IToast mToast;
        private IUnionPay mUnionPay;
        private IWXPay mWXPay;
        private IWebRouter mWebRouter;
        private IWebView mWebView;
        private IXView mXView;

        public Builder build(Context context) {
            if (context != null && context.getApplicationContext() != null) {
                DpiUtil.setDensity(context.getApplicationContext().getResources().getDisplayMetrics().density);
            }
            return this;
        }

        public Builder setABMta(IABMta iABMta) {
            this.mABMta = iABMta;
            return this;
        }

        public Builder setActivityConnectorCreator(IActivityConnectorCreator iActivityConnectorCreator) {
            this.mActivityConnectorCreator = iActivityConnectorCreator;
            return this;
        }

        public Builder setAura(IAura iAura) {
            this.mAura = iAura;
            return this;
        }

        public Builder setBCashierConfig(IBCashierConfig iBCashierConfig) {
            this.mBCashierConfig = iBCashierConfig;
            return this;
        }

        public Builder setCashierRouter(ICashierRouter iCashierRouter) {
            this.mCashierRouter = iCashierRouter;
            return this;
        }

        public Builder setDarkMode(IDarkMode iDarkMode) {
            this.mDarkMode = iDarkMode;
            return this;
        }

        public Builder setDes(IDes iDes) {
            this.mDes = iDes;
            return this;
        }

        public Builder setDialog(IDialog iDialog) {
            this.mDialog = iDialog;
            return this;
        }

        public Builder setDynamic(IDynamic iDynamic) {
            this.mDynamic = iDynamic;
            return this;
        }

        public Builder setElderMode(IElderMode iElderMode) {
            this.mIElderMode = iElderMode;
            return this;
        }

        public Builder setExceptionMonitor(IExceptionMonitor iExceptionMonitor) {
            this.mExceptionMonitor = iExceptionMonitor;
            return this;
        }

        public Builder setFont(IFont iFont) {
            this.mFont = iFont;
            return this;
        }

        public Builder setHWCaaS(IHWCaaS iHWCaaS) {
            this.mHWCassS = iHWCaaS;
            return this;
        }

        public Builder setHttpCreator(IHttpCreator iHttpCreator) {
            this.mHttpCreator = iHttpCreator;
            return this;
        }

        public Builder setImageLoader(IImageLoader iImageLoader) {
            this.mImageLoader = iImageLoader;
            return this;
        }

        public Builder setInnerRouter(IInnerRouter iInnerRouter) {
            this.mInnerRouter = iInnerRouter;
            return this;
        }

        public Builder setJDPay(IJDPay iJDPay) {
            this.mJDPay = iJDPay;
            return this;
        }

        public Builder setJsonParser(IJsonParser iJsonParser) {
            this.mJsonParser = iJsonParser;
            return this;
        }

        public Builder setLbs(ILbs iLbs) {
            this.mLbs = iLbs;
            return this;
        }

        public Builder setLive(ILive iLive) {
            this.mLive = iLive;
            return this;
        }

        public Builder setLoading(ILoading iLoading) {
            this.mLoading = iLoading;
            return this;
        }

        public Builder setLog(ILog iLog) {
            this.mLog = iLog;
            return this;
        }

        public Builder setMta(IMta iMta) {
            this.mMta = iMta;
            return this;
        }

        public Builder setNotificationImpl(INotification iNotification) {
            this.mNotificationImpl = iNotification;
            return this;
        }

        public Builder setOrderRouter(IOrderRouter iOrderRouter) {
            this.mOrderRouter = iOrderRouter;
            return this;
        }

        public Builder setOuterRouter(IOuterRouter iOuterRouter) {
            this.mOuterRouter = iOuterRouter;
            return this;
        }

        public Builder setPrivacy(IPrivacy iPrivacy) {
            this.mPrivacy = iPrivacy;
            return this;
        }

        public Builder setPush(IPush iPush) {
            this.mPush = iPush;
            return this;
        }

        public Builder setQQPay(IQQPay iQQPay) {
            this.mQQPay = iQQPay;
            return this;
        }

        public Builder setReminder(IReminder iReminder) {
            this.mReminder = iReminder;
            return this;
        }

        public Builder setRisk(IRisk iRisk) {
            this.mRisk = iRisk;
            return this;
        }

        public Builder setSdkConfig(IConfig iConfig) {
            this.mConfig = iConfig;
            return this;
        }

        public Builder setSdkHostConfig(IHostConfig iHostConfig) {
            this.mHostConfig = iHostConfig;
            return this;
        }

        public Builder setShare(IShare iShare) {
            this.mShare = iShare;
            return this;
        }

        public Builder setStackManager(IStackManager iStackManager) {
            this.mStackManager = iStackManager;
            return this;
        }

        public Builder setThemeTitle(IThemeTitle iThemeTitle) {
            this.mThemeTitleImpl = iThemeTitle;
            return this;
        }

        public Builder setThreadPool(IThreadPool iThreadPool) {
            this.mThreadPool = iThreadPool;
            return this;
        }

        public Builder setTitleChangeEventCreator(ITitleChangeEventCreator iTitleChangeEventCreator) {
            this.mTitleChangeEventCreator = iTitleChangeEventCreator;
            return this;
        }

        public Builder setToast(IToast iToast) {
            this.mToast = iToast;
            return this;
        }

        public Builder setUnionPay(IUnionPay iUnionPay) {
            this.mUnionPay = iUnionPay;
            return this;
        }

        public Builder setUserLogin(ILogin iLogin) {
            this.mLogin = iLogin;
            return this;
        }

        public Builder setWXPay(IWXPay iWXPay) {
            this.mWXPay = iWXPay;
            return this;
        }

        public Builder setWebFragment(IWebViewFragment iWebViewFragment) {
            this.mMFragment = iWebViewFragment;
            return this;
        }

        public Builder setWebRouter(IWebRouter iWebRouter) {
            this.mWebRouter = iWebRouter;
            return this;
        }

        public Builder setWebView(IWebView iWebView) {
            this.mWebView = iWebView;
            return this;
        }

        public Builder setXView(IXView iXView) {
            this.mXView = iXView;
            return this;
        }
    }

    private DependInitializer() {
    }

    public static IABMta getABMta() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mABMta;
        }
        return null;
    }

    public static IActivityConnectorCreator getActivityConnectorCreator() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mActivityConnectorCreator;
        }
        return null;
    }

    public static IAura getAura() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mAura;
        }
        return null;
    }

    public static IBCashierConfig getBCashierConfig() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mBCashierConfig;
        }
        return null;
    }

    public static ICashierRouter getCashierRouter() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mCashierRouter;
        }
        return null;
    }

    public static IDarkMode getDarkMode() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mDarkMode;
        }
        return null;
    }

    public static IDes getDes() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mDes;
        }
        return null;
    }

    public static IDialog getDialog() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mDialog;
        }
        return null;
    }

    public static IDynamic getDynamic() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mDynamic;
        }
        return null;
    }

    public static IElderMode getElderMode() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mIElderMode;
        }
        return null;
    }

    public static IExceptionMonitor getExceptionMonitor() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mExceptionMonitor;
        }
        return null;
    }

    public static IFont getFont() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mFont;
        }
        return null;
    }

    public static IHWCaaS getHWCaaS() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mHWCassS;
        }
        return null;
    }

    public static IHostConfig getHostConfig() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mHostConfig;
        }
        return null;
    }

    public static IHttpCreator getHttpCreator() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mHttpCreator;
        }
        return null;
    }

    public static IImageLoader getImageLoader() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mImageLoader;
        }
        return null;
    }

    public static IInnerRouter getInnerRouter() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mInnerRouter;
        }
        return null;
    }

    public static IJDPay getJDPay() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mJDPay;
        }
        return null;
    }

    public static IJsonParser getJsonParser() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mJsonParser;
        }
        return null;
    }

    public static ILbs getLbs() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mLbs;
        }
        return null;
    }

    public static ILive getLive() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mLive;
        }
        return null;
    }

    public static ILoading getLoading() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mLoading;
        }
        return null;
    }

    public static ILog getLog() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mLog;
        }
        return null;
    }

    public static ILogin getLogin() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mLogin;
        }
        return null;
    }

    public static IMta getMta() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mMta;
        }
        return null;
    }

    public static INotification getNotification() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mNotificationImpl;
        }
        return null;
    }

    public static IOrderRouter getOrderRouter() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mOrderRouter;
        }
        return null;
    }

    public static IPrivacy getPrivacy() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mPrivacy;
        }
        return null;
    }

    public static IPush getPush() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mPush;
        }
        return null;
    }

    public static IQQPay getQQPay() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mQQPay;
        }
        return null;
    }

    public static IReminder getReminder() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mReminder;
        }
        return null;
    }

    public static IRisk getRisk() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mRisk;
        }
        return null;
    }

    public static IOuterRouter getRouter() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mOuterRouter;
        }
        return null;
    }

    public static IConfig getSdkConfig() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mConfig;
        }
        return null;
    }

    public static IShare getShare() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mShare;
        }
        return null;
    }

    public static IStackManager getStackManager() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mStackManager;
        }
        return null;
    }

    public static IThemeTitle getThemeTitle() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mThemeTitleImpl;
        }
        return null;
    }

    public static IThreadPool getThreadPool() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mThreadPool;
        }
        return null;
    }

    public static ITitleChangeEventCreator getTitleChangeEventCreator() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mTitleChangeEventCreator;
        }
        return null;
    }

    public static IToast getToast() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mToast;
        }
        return null;
    }

    public static IUnionPay getUnionPay() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mUnionPay;
        }
        return null;
    }

    public static IWXPay getWXPay() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mWXPay;
        }
        return null;
    }

    public static IWebViewFragment getWebFragment() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mMFragment;
        }
        return null;
    }

    public static IWebRouter getWebRouter() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mWebRouter;
        }
        return null;
    }

    public static IWebView getWebView() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mWebView;
        }
        return null;
    }

    public static IXView getXView() {
        Builder builder = mBuilder;
        if (builder != null) {
            return builder.mXView;
        }
        return null;
    }

    public static void initialize(Builder builder) {
        mBuilder = builder;
    }
}
