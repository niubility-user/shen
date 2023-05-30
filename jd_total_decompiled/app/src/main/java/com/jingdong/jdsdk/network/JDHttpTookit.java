package com.jingdong.jdsdk.network;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.jd.framework.network.JDNetworkHelper;
import com.jd.framework.network.dialing.ConnectivityChangeObserver;
import com.jd.framework.network.dialing.DNSManager;
import com.jd.framework.network.dialing.LocalDNSDailer;
import com.jd.framework.network.dialingv2.DialingManager;
import com.jd.framework.network.filedown.internal.BaseDownloader;
import com.jd.framework.network.impl.JDNetworkDefault;
import com.jingdong.common.network.AbsDialogController;
import com.jingdong.common.network.InternalActivityLifecycleCallbacks;
import com.jingdong.common.network.InternalAppLifecycleListener;
import com.jingdong.jdsdk.network.config.BuildInIPBackUpConfig;
import com.jingdong.jdsdk.network.dependency.DefaultDependencyFactory;
import com.jingdong.jdsdk.network.dependency.IAppProxy;
import com.jingdong.jdsdk.network.dependency.IBusinessJsonCodeEventListener;
import com.jingdong.jdsdk.network.dependency.ICustomUIComponent;
import com.jingdong.jdsdk.network.dependency.IDownloadDomainsResolver;
import com.jingdong.jdsdk.network.dependency.IExceptionReportHandler;
import com.jingdong.jdsdk.network.dependency.IExternalDebugConfig;
import com.jingdong.jdsdk.network.dependency.IGatewayRespHeaderListener;
import com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin;
import com.jingdong.jdsdk.network.dependency.IHardGuardVerifyPlugin;
import com.jingdong.jdsdk.network.dependency.IHttpDnsController;
import com.jingdong.jdsdk.network.dependency.IJDGuardPlugin;
import com.jingdong.jdsdk.network.dependency.ILoginUserController;
import com.jingdong.jdsdk.network.dependency.INetworkController;
import com.jingdong.jdsdk.network.dependency.INetworkEventDataReporter;
import com.jingdong.jdsdk.network.dependency.IPHCEncryptionPlugin;
import com.jingdong.jdsdk.network.dependency.IRetrofitExceptionReporter;
import com.jingdong.jdsdk.network.dependency.IRuntimeConfig;
import com.jingdong.jdsdk.network.dependency.ISignatureHandler;
import com.jingdong.jdsdk.network.dependency.IStatInfoConfig;
import com.jingdong.jdsdk.network.toolbox.ExceptionReportDelegate;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Interceptor;

/* loaded from: classes.dex */
public final class JDHttpTookit {
    public static final String TAG = "JDHttpTookit";
    private static Engine sEngine;

    /* loaded from: classes.dex */
    public static class Engine {
        String appId;
        IAppProxy appProxy;
        IBusinessJsonCodeEventListener businessJsonCodeEventListener;
        ConnectivityChangeObserver connectivityChangeObserver;
        Context context;
        ICustomUIComponent customUIComponentImpl;
        IDownloadDomainsResolver downloadDomainsResolver;
        boolean enableBusinessLayerCheck;
        boolean enableEncryptWhiteList;
        ExceptionReportDelegate exceptionReportDelegate;
        IExternalDebugConfig externalDebugConfigImpl;
        String functionIdPrefix;
        IGatewayRespHeaderListener gatewayRespHeaderListener;
        IGuardVerifyPlugin guardVerifyPlugin;
        IHardGuardVerifyPlugin hardGuardVerifyPlugin;
        IHttpDnsController httpDnsControllerImpl;
        AbsDialogController.IDialog httpErrorDialogImpl;
        InternalActivityLifecycleCallbacks internalActivityLifecycleCallbacks;
        boolean isPrintLog;
        IJDGuardPlugin jdGuardPlugin;
        ILoginUserController loginUserControllerImpl;
        boolean needVerifySignatureFlag;
        INetworkController networkControllerImpl;
        final List<Interceptor> networkInterceptors;
        IPHCEncryptionPlugin phcEncryptionPlugin;
        INetworkEventDataReporter reporterImpl;
        IRetrofitExceptionReporter retrofitExceptionReporter;
        IRuntimeConfig runtimeConfigImpl;
        String secretKey;
        ISignatureHandler signatureHandlerImpl;
        IStatInfoConfig statInfoConfigImpl;
        String userAgent;

        /* loaded from: classes.dex */
        public static final class Builder {
            String appId;
            IAppProxy appProxy;
            IBusinessJsonCodeEventListener businessJsonCodeEventListener;
            Context context;
            ICustomUIComponent customUIComponentImpl;
            IDownloadDomainsResolver downloadDomainsResolver;
            boolean enableBusinessLayerCheck;
            boolean enableEncryptWhiteList;
            ExceptionReportDelegate exceptionReportDelegate;
            IExternalDebugConfig externalDebugConfigImpl;
            String functionIdPrefix;
            IGatewayRespHeaderListener gatewayRespHeaderListener;
            IGuardVerifyPlugin guardVerifyPlugin;
            IHardGuardVerifyPlugin hardGuardVerifyPlugin;
            IHttpDnsController httpDnsControllerImpl;
            AbsDialogController.IDialog httpErrorDialogImpl;
            boolean isPrintLog;
            IJDGuardPlugin jdGuardPlugin;
            ILoginUserController loginUserControllerImpl;
            boolean needVerifySignatureFlag;
            INetworkController networkControllerImpl;
            final List<Interceptor> networkInterceptors;
            IPHCEncryptionPlugin phcEncryptionPlugin;
            INetworkEventDataReporter reporterImpl;
            IRetrofitExceptionReporter retrofitExceptionReporter;
            IRuntimeConfig runtimeConfigImpl;
            String secretKey;
            ISignatureHandler signatureHandlerImpl;
            IStatInfoConfig statInfoConfigImpl;
            String userAgent;

            public Builder addNetworkIntercepter(Interceptor interceptor) {
                if (interceptor != null) {
                    this.networkInterceptors.add(interceptor);
                    return this;
                }
                throw new IllegalArgumentException("interceptor == null");
            }

            public Engine build() {
                return new Engine(this);
            }

            public Builder enableEncryptWhiteList(boolean z) {
                this.enableEncryptWhiteList = z;
                return this;
            }

            public Builder functionIdPrefix(String str) {
                this.functionIdPrefix = str;
                return this;
            }

            public Builder isPrintLog(boolean z) {
                this.isPrintLog = z;
                return this;
            }

            public Builder needVerifySignature(boolean z) {
                this.needVerifySignatureFlag = z;
                return this;
            }

            public Builder setAppId(String str) {
                this.appId = str;
                return this;
            }

            public Builder setAppProxy(IAppProxy iAppProxy) {
                this.appProxy = iAppProxy;
                return this;
            }

            public Builder setBusinessJsonCodeListener(IBusinessJsonCodeEventListener iBusinessJsonCodeEventListener) {
                this.businessJsonCodeEventListener = iBusinessJsonCodeEventListener;
                return this;
            }

            public Builder setBusinessLayerCheckSwitch(boolean z) {
                this.enableBusinessLayerCheck = z;
                return this;
            }

            public Builder setCustomUIComponentImpl(ICustomUIComponent iCustomUIComponent) {
                this.customUIComponentImpl = iCustomUIComponent;
                return this;
            }

            public Builder setDownloadDomainResolver(IDownloadDomainsResolver iDownloadDomainsResolver) {
                this.downloadDomainsResolver = iDownloadDomainsResolver;
                return this;
            }

            public Builder setExceptionReporter(IExceptionReportHandler iExceptionReportHandler) {
                this.exceptionReportDelegate.setReporter(iExceptionReportHandler);
                return this;
            }

            public Builder setExternalDebugConfigImpl(IExternalDebugConfig iExternalDebugConfig) {
                this.externalDebugConfigImpl = iExternalDebugConfig;
                return this;
            }

            public Builder setGateWayRespHeaderListener(IGatewayRespHeaderListener iGatewayRespHeaderListener) {
                this.gatewayRespHeaderListener = iGatewayRespHeaderListener;
                return this;
            }

            public Builder setGuardVerifyPlugin(IGuardVerifyPlugin iGuardVerifyPlugin) {
                this.guardVerifyPlugin = iGuardVerifyPlugin;
                return this;
            }

            public Builder setHardGuardVerifyPlugin(IHardGuardVerifyPlugin iHardGuardVerifyPlugin) {
                this.hardGuardVerifyPlugin = iHardGuardVerifyPlugin;
                return this;
            }

            public Builder setHttpDnsController(IHttpDnsController iHttpDnsController) {
                this.httpDnsControllerImpl = iHttpDnsController;
                return this;
            }

            public Builder setHttpErrorDialogImpl(AbsDialogController.IDialog iDialog) {
                this.httpErrorDialogImpl = iDialog;
                return this;
            }

            public Builder setJDGuardPlugin(IJDGuardPlugin iJDGuardPlugin) {
                this.jdGuardPlugin = iJDGuardPlugin;
                return this;
            }

            public Builder setLoginUserControllerImpl(ILoginUserController iLoginUserController) {
                this.loginUserControllerImpl = iLoginUserController;
                return this;
            }

            public Builder setNetworkControllerImpl(INetworkController iNetworkController) {
                this.networkControllerImpl = iNetworkController;
                return this;
            }

            public Builder setPhcEncryptionPlugin(IPHCEncryptionPlugin iPHCEncryptionPlugin) {
                this.phcEncryptionPlugin = iPHCEncryptionPlugin;
                return this;
            }

            public Builder setRetrofitExceptionReporter(IRetrofitExceptionReporter iRetrofitExceptionReporter) {
                this.retrofitExceptionReporter = iRetrofitExceptionReporter;
                return this;
            }

            public Builder setRuntimeConfigImpl(IRuntimeConfig iRuntimeConfig) {
                this.runtimeConfigImpl = iRuntimeConfig;
                return this;
            }

            public Builder setSecretKey(String str) {
                this.secretKey = str;
                return this;
            }

            public Builder setSignatureHandler(ISignatureHandler iSignatureHandler) {
                this.signatureHandlerImpl = iSignatureHandler;
                return this;
            }

            public Builder setStatInfoConfigImpl(IStatInfoConfig iStatInfoConfig) {
                this.statInfoConfigImpl = iStatInfoConfig;
                return this;
            }

            public Builder setUserAgent(String str) {
                this.userAgent = str;
                return this;
            }

            private Builder(Context context) {
                this.needVerifySignatureFlag = true;
                this.enableBusinessLayerCheck = true;
                this.enableEncryptWhiteList = false;
                this.networkInterceptors = new ArrayList();
                this.context = context;
                this.exceptionReportDelegate = new ExceptionReportDelegate();
            }
        }

        public String getAppId() {
            return this.appId;
        }

        public IAppProxy getAppProxy() {
            if (this.appProxy == null) {
                this.appProxy = DefaultDependencyFactory.getDefaultAppProxy();
            }
            return this.appProxy;
        }

        public Context getApplicationContext() {
            return this.context;
        }

        public IBusinessJsonCodeEventListener getBusinessJsonCodeEventListener() {
            if (this.businessJsonCodeEventListener == null) {
                this.businessJsonCodeEventListener = DefaultDependencyFactory.getBusinessJsonCodeEventListener();
            }
            return this.businessJsonCodeEventListener;
        }

        public ConnectivityChangeObserver getConnectivityChangeObserver() {
            return this.connectivityChangeObserver;
        }

        public ICustomUIComponent getCustomUIComponentImpl() {
            if (this.customUIComponentImpl == null) {
                this.customUIComponentImpl = DefaultDependencyFactory.getDefaultCustomUIComponent();
            }
            return this.customUIComponentImpl;
        }

        public IDownloadDomainsResolver getDownloadDomainsResolver() {
            if (this.downloadDomainsResolver == null) {
                this.downloadDomainsResolver = DefaultDependencyFactory.getDefaultDownloadDomainResolver();
            }
            return this.downloadDomainsResolver;
        }

        public ExceptionReportDelegate getExceptionReportDelegate() {
            ExceptionReportDelegate exceptionReportDelegate = this.exceptionReportDelegate;
            if (exceptionReportDelegate.reporter == null) {
                exceptionReportDelegate.setReporter(DefaultDependencyFactory.getDefaultExceptionReporter());
            }
            return this.exceptionReportDelegate;
        }

        public IExternalDebugConfig getExternalDebugConfigImpl() {
            if (this.externalDebugConfigImpl == null) {
                this.externalDebugConfigImpl = DefaultDependencyFactory.getDefaultExternalDebugConfig();
            }
            return this.externalDebugConfigImpl;
        }

        public String getFunctionIdPrefix() {
            return this.functionIdPrefix;
        }

        public IGatewayRespHeaderListener getGatewayRespHeaderListener() {
            return this.gatewayRespHeaderListener;
        }

        public IGuardVerifyPlugin getGuardVerifyPlugin() {
            if (this.guardVerifyPlugin == null) {
                this.guardVerifyPlugin = new IGuardVerifyPlugin() { // from class: com.jingdong.jdsdk.network.JDHttpTookit.Engine.1
                    {
                        Engine.this = this;
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
                    public String getLmtValue() {
                        return null;
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
                    public String getVerifyToken() {
                        return "";
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
                    public boolean isVerifyWindowShowing() {
                        return false;
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
                    public void onActivityDestroyed(Activity activity) {
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
                    public boolean onLineSwitchOpen() {
                        return false;
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
                    public void onLogout() {
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IGuardVerifyPlugin
                    public void onTriggerVerifyCheck(String str, String str2, String str3) {
                    }
                };
            }
            return this.guardVerifyPlugin;
        }

        public IHardGuardVerifyPlugin getHardGuardVerifyPlugin() {
            if (this.hardGuardVerifyPlugin == null) {
                this.hardGuardVerifyPlugin = new IHardGuardVerifyPlugin() { // from class: com.jingdong.jdsdk.network.JDHttpTookit.Engine.2
                    {
                        Engine.this = this;
                    }

                    @Override // com.jingdong.jdsdk.network.dependency.IHardGuardVerifyPlugin
                    public void triggerGuardVerifyCheck(String str, IHardGuardVerifyPlugin.ICheckListener iCheckListener) {
                    }
                };
            }
            return this.hardGuardVerifyPlugin;
        }

        public IHttpDnsController getHttpDnsControllerImpl() {
            if (this.httpDnsControllerImpl == null) {
                this.httpDnsControllerImpl = DefaultDependencyFactory.getDefaultIDns();
            }
            return this.httpDnsControllerImpl;
        }

        public AbsDialogController.IDialog getHttpErrorDialogImpl() {
            return this.httpErrorDialogImpl;
        }

        public InternalActivityLifecycleCallbacks getInternalActivityLifecycleCallbacks() {
            return this.internalActivityLifecycleCallbacks;
        }

        public IJDGuardPlugin getJdGuardPlugin() {
            return this.jdGuardPlugin;
        }

        public ILoginUserController getLoginUserControllerImpl() {
            if (this.loginUserControllerImpl == null) {
                this.loginUserControllerImpl = DefaultDependencyFactory.getDefaultLoginUserController();
            }
            return this.loginUserControllerImpl;
        }

        public INetworkController getNetworkControllerImpl() {
            if (this.networkControllerImpl == null) {
                this.networkControllerImpl = DefaultDependencyFactory.getDefaultNetworkController();
            }
            return this.networkControllerImpl;
        }

        public INetworkEventDataReporter getNetworkEventDataReporter() {
            if (this.reporterImpl == null) {
                this.reporterImpl = DefaultDependencyFactory.defaultNetworkEventDataReporter();
            }
            return this.reporterImpl;
        }

        public List<Interceptor> getNetworkInterceptors() {
            return this.networkInterceptors;
        }

        public IPHCEncryptionPlugin getPhcEncryptionPlugin() {
            if (this.phcEncryptionPlugin == null) {
                this.phcEncryptionPlugin = DefaultDependencyFactory.getDefaultPHCEncryptionPlugin();
            }
            return this.phcEncryptionPlugin;
        }

        public IRetrofitExceptionReporter getRetrofitExceptionReporter() {
            if (this.retrofitExceptionReporter == null) {
                this.retrofitExceptionReporter = DefaultDependencyFactory.getDefaultRetrofitExceptionReporter();
            }
            return this.retrofitExceptionReporter;
        }

        public IRuntimeConfig getRuntimeConfigImpl() {
            if (this.runtimeConfigImpl == null) {
                this.runtimeConfigImpl = DefaultDependencyFactory.getDefaultRuntimeConfig();
            }
            return this.runtimeConfigImpl;
        }

        public String getSecretKey() {
            return this.secretKey;
        }

        public ISignatureHandler getSignatureHandlerImpl() {
            if (this.signatureHandlerImpl == null) {
                this.signatureHandlerImpl = DefaultDependencyFactory.getDefaultSignatureHandler();
            }
            return this.signatureHandlerImpl;
        }

        public IStatInfoConfig getStatInfoConfigImpl() {
            if (this.statInfoConfigImpl == null) {
                this.statInfoConfigImpl = DefaultDependencyFactory.getDefaultStatInfoConfig();
            }
            return this.statInfoConfigImpl;
        }

        public String getUserAgent() {
            return this.userAgent;
        }

        public boolean isEnableBusinessLayerCheck() {
            return this.enableBusinessLayerCheck;
        }

        public boolean isEnableEncryptWhiteList() {
            return this.enableEncryptWhiteList;
        }

        public boolean isNeedVerifySignature() {
            return this.needVerifySignatureFlag;
        }

        public boolean isPrintLog() {
            return this.isPrintLog;
        }

        public boolean isThirdApp() {
            return (TextUtils.isEmpty(getAppId()) || TextUtils.isEmpty(getSecretKey())) ? false : true;
        }

        public void launch() {
            JDNetworkHelper.setup(JDNetworkDefault.Builder.newBuilder(this.context).isPrintLog(this.isPrintLog).buildInIPBackUpConfig(new BuildInIPBackUpConfig()).build());
            DialingManager.getInstance().initialize();
            InternalActivityLifecycleCallbacks internalActivityLifecycleCallbacks = new InternalActivityLifecycleCallbacks();
            this.internalActivityLifecycleCallbacks = internalActivityLifecycleCallbacks;
            Context context = this.context;
            if (context instanceof Application) {
                ((Application) context).registerActivityLifecycleCallbacks(internalActivityLifecycleCallbacks);
            }
            ProcessLifecycleOwner.get().getLifecycle().addObserver(new InternalAppLifecycleListener());
            ConnectivityChangeObserver connectivityChangeObserver = new ConnectivityChangeObserver(this.context);
            this.connectivityChangeObserver = connectivityChangeObserver;
            connectivityChangeObserver.addEventListener(DNSManager.getInstance());
            this.connectivityChangeObserver.addEventListener(LocalDNSDailer.getInstance());
            this.connectivityChangeObserver.addEventListener(DialingManager.getInstance());
            this.connectivityChangeObserver.addEventListener(BaseDownloader.getDownloadRouteSelector());
        }

        private Engine(Builder builder) {
            this.enableEncryptWhiteList = false;
            ArrayList arrayList = new ArrayList();
            this.networkInterceptors = arrayList;
            this.context = builder.context;
            this.isPrintLog = builder.isPrintLog;
            this.needVerifySignatureFlag = builder.needVerifySignatureFlag;
            this.secretKey = builder.secretKey;
            this.appId = builder.appId;
            arrayList.addAll(builder.networkInterceptors);
            this.runtimeConfigImpl = builder.runtimeConfigImpl;
            this.statInfoConfigImpl = builder.statInfoConfigImpl;
            this.signatureHandlerImpl = builder.signatureHandlerImpl;
            this.loginUserControllerImpl = builder.loginUserControllerImpl;
            this.exceptionReportDelegate = builder.exceptionReportDelegate;
            this.retrofitExceptionReporter = builder.retrofitExceptionReporter;
            this.networkControllerImpl = builder.networkControllerImpl;
            this.externalDebugConfigImpl = builder.externalDebugConfigImpl;
            this.customUIComponentImpl = builder.customUIComponentImpl;
            this.phcEncryptionPlugin = builder.phcEncryptionPlugin;
            this.httpDnsControllerImpl = builder.httpDnsControllerImpl;
            this.reporterImpl = builder.reporterImpl;
            this.appProxy = builder.appProxy;
            this.enableBusinessLayerCheck = builder.enableBusinessLayerCheck;
            this.userAgent = builder.userAgent;
            this.functionIdPrefix = builder.functionIdPrefix;
            this.httpErrorDialogImpl = builder.httpErrorDialogImpl;
            this.enableEncryptWhiteList = builder.enableEncryptWhiteList;
            this.gatewayRespHeaderListener = builder.gatewayRespHeaderListener;
            this.guardVerifyPlugin = builder.guardVerifyPlugin;
            this.hardGuardVerifyPlugin = builder.hardGuardVerifyPlugin;
            this.jdGuardPlugin = builder.jdGuardPlugin;
            this.downloadDomainsResolver = builder.downloadDomainsResolver;
            this.businessJsonCodeEventListener = builder.businessJsonCodeEventListener;
        }
    }

    public static Engine getEngine() {
        if (sEngine == null) {
            OKLog.e("JDHttpTookit", "JdHttpToolkit not initialized yet, please init first.");
        }
        return sEngine;
    }

    public static void initialize(Engine engine) {
        if (sEngine != null) {
            OKLog.e("JDHttpTookit", "duplicate initialize!");
            return;
        }
        sEngine = engine;
        engine.launch();
    }

    public static Engine.Builder newBuilder(Context context) {
        return new Engine.Builder(context);
    }
}
