package com.jingdong.sdk.jdhttpdns;

import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import com.jingdong.sdk.jdhttpdns.core.HttpDns;
import com.jingdong.sdk.jdhttpdns.core.HttpDnsImpl;
import com.jingdong.sdk.jdhttpdns.core.InternalAppLifecycleListener;
import com.jingdong.sdk.jdhttpdns.core.RouteSelector;
import com.jingdong.sdk.jdhttpdns.listener.IFailureController;
import com.jingdong.sdk.jdhttpdns.listener.IKeyParamProvider;
import com.jingdong.sdk.jdhttpdns.listener.IReporter;
import com.jingdong.sdk.jdhttpdns.listener.IResolveListener;
import com.jingdong.sdk.jdhttpdns.listener.IRestrictController;
import com.jingdong.sdk.jdhttpdns.listener.IStatReporter;
import com.jingdong.sdk.jdhttpdns.listener.OnDomainResolveListener;
import com.jingdong.sdk.jdhttpdns.pojo.IpModel;
import com.jingdong.sdk.jdhttpdns.utils.DNSLog;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class JDHttpDnsToolkit {
    private static JDHttpDnsToolkit instance;
    private String accountId;
    private Context applicationContext;
    private IFailureController controller;
    private HttpDns defaultHttpDnsImpl;
    private HashMap<String, String> extraParam;
    private boolean isExpiredIPEnabled;
    private boolean isRefreshCacheAfterNetStateChanged;
    private boolean isThirdPartyApp;
    private IKeyParamProvider keyParamProvider;
    private IReporter reporter;
    private ConcurrentHashMap<String, OnDomainResolveListener> resolverObserver;
    private IRestrictController restrictController;
    private RouteSelector routeSelector;
    private boolean safeMode;
    private String secretKey;
    private IStatReporter statReporter;

    /* loaded from: classes.dex */
    public static class Builder {
        String accountId;
        Context applicationContext;
        IFailureController controller;
        HashMap<String, String> extraParam;
        boolean isExpiredIPEnabled;
        boolean isLogEnable;
        boolean isRefreshCacheAfterNetStateChanged;
        boolean isThirdPartyApp;
        IKeyParamProvider keyParamProvider;
        IReporter reporter;
        IRestrictController restrictController;
        boolean safeMode;
        String secretKey;
        IStatReporter statReporter;

        public JDHttpDnsToolkit build() {
            return new JDHttpDnsToolkit(this);
        }

        public Builder enableSafeMode(boolean z) {
            this.safeMode = z;
            return this;
        }

        public Builder setAccountId(String str) {
            this.accountId = str;
            return this;
        }

        public Builder setExpiredIPEnabled(boolean z) {
            this.isExpiredIPEnabled = z;
            return this;
        }

        public Builder setExtraParam(HashMap<String, String> hashMap) {
            this.extraParam = hashMap;
            return this;
        }

        public Builder setFailController(IFailureController iFailureController) {
            this.controller = iFailureController;
            return this;
        }

        public Builder setIRestrictController(IRestrictController iRestrictController) {
            this.restrictController = iRestrictController;
            return this;
        }

        public Builder setIsThirdParty(boolean z) {
            this.isThirdPartyApp = z;
            return this;
        }

        public Builder setKeyParamProvider(IKeyParamProvider iKeyParamProvider) {
            this.keyParamProvider = iKeyParamProvider;
            return this;
        }

        public Builder setLogEnable(boolean z) {
            this.isLogEnable = z;
            return this;
        }

        public Builder setRefreshCacheAfterNetStateChanged(boolean z) {
            this.isRefreshCacheAfterNetStateChanged = z;
            return this;
        }

        public Builder setReporter(IReporter iReporter) {
            this.reporter = iReporter;
            return this;
        }

        public Builder setSecretKey(String str) {
            this.secretKey = str;
            return this;
        }

        public Builder setStatRepoter(IStatReporter iStatReporter) {
            this.statReporter = iStatReporter;
            return this;
        }

        private Builder(Context context) {
            this.isRefreshCacheAfterNetStateChanged = false;
            this.isExpiredIPEnabled = false;
            this.isLogEnable = false;
            this.accountId = HttpDnsConfig.JDMALL_ACCOUNT_ID;
            this.secretKey = HttpDnsConfig.JDMALL_SECRET_KEY;
            this.applicationContext = context;
        }
    }

    public static synchronized JDHttpDnsToolkit getInstance() {
        JDHttpDnsToolkit jDHttpDnsToolkit;
        synchronized (JDHttpDnsToolkit.class) {
            jDHttpDnsToolkit = instance;
        }
        return jDHttpDnsToolkit;
    }

    public static synchronized JDHttpDnsToolkit initialize(Builder builder) {
        JDHttpDnsToolkit jDHttpDnsToolkit;
        synchronized (JDHttpDnsToolkit.class) {
            if (builder != null) {
                if (instance == null) {
                    instance = builder.build();
                }
                jDHttpDnsToolkit = instance;
            } else {
                throw new IllegalArgumentException("Builder is null,It is illegal !!!");
            }
        }
        return jDHttpDnsToolkit;
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    public void addDomainResolveListener(String str, OnDomainResolveListener onDomainResolveListener) {
        if (this.resolverObserver.containsKey(str)) {
            return;
        }
        this.resolverObserver.put(str, onDomainResolveListener);
    }

    public void clearCache(String str) {
        this.defaultHttpDnsImpl.clearCache(str);
    }

    public String getAccountId() {
        return this.accountId;
    }

    public Context getApplication() {
        return this.applicationContext;
    }

    public IFailureController getController() {
        return this.controller;
    }

    public HashMap<String, String> getExtraParam() {
        return this.extraParam;
    }

    public IpModel getIpFromMemoryCache(String str) {
        return ((HttpDnsImpl) this.defaultHttpDnsImpl).getMemoryCache().getIpModelByHost(str);
    }

    public IpModel getIpModelByHost(String str) {
        return getIpModelByHost(str, false);
    }

    public IKeyParamProvider getKeyParamProvider() {
        return this.keyParamProvider;
    }

    public IReporter getReporter() {
        return this.reporter;
    }

    public IRestrictController getRestrictController() {
        if (this.restrictController == null) {
            this.restrictController = new IRestrictController() { // from class: com.jingdong.sdk.jdhttpdns.JDHttpDnsToolkit.1
                {
                    JDHttpDnsToolkit.this = this;
                }

                @Override // com.jingdong.sdk.jdhttpdns.listener.IRestrictController
                public boolean isXTime() {
                    return false;
                }
            };
        }
        return this.restrictController;
    }

    public RouteSelector getRouteSelector() {
        return this.routeSelector;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public IStatReporter getStatReporter() {
        return this.statReporter;
    }

    public boolean isExpiredIPEnabled() {
        return this.isExpiredIPEnabled;
    }

    public boolean isRefreshCacheAfterNetStateChanged() {
        return this.isRefreshCacheAfterNetStateChanged;
    }

    public boolean isSafeMode() {
        return this.safeMode;
    }

    public boolean isThirdPartApp() {
        return this.isThirdPartyApp;
    }

    public void onNotifyResolve(IpModel ipModel) {
        OnDomainResolveListener onDomainResolveListener;
        if (ipModel == null || TextUtils.isEmpty(ipModel.host)) {
            return;
        }
        IpModel m24clone = ipModel.m24clone();
        if (!this.resolverObserver.containsKey(m24clone.host) || (onDomainResolveListener = this.resolverObserver.get(m24clone.host)) == null) {
            return;
        }
        onDomainResolveListener.onResolve(m24clone);
    }

    public void removeResolveListener(String str) {
        if (this.resolverObserver.containsKey(str)) {
            this.resolverObserver.remove(str);
        }
    }

    public void setCdnIpv6Enable(boolean z) {
        this.routeSelector.setCdnIpv6Enable(z);
    }

    public void setDnsVipV4(String str) {
        this.routeSelector.setIpv4(str);
    }

    public void setDnsVipV6(String str) {
        this.routeSelector.setIpv6(str);
    }

    public void startDomainResolve(IResolveListener iResolveListener, String... strArr) {
        this.defaultHttpDnsImpl.startDomainResolve(iResolveListener, strArr);
    }

    private JDHttpDnsToolkit(Builder builder) {
        this.applicationContext = builder.applicationContext;
        this.isRefreshCacheAfterNetStateChanged = builder.isRefreshCacheAfterNetStateChanged;
        this.isExpiredIPEnabled = builder.isExpiredIPEnabled;
        this.extraParam = builder.extraParam;
        this.keyParamProvider = builder.keyParamProvider;
        this.controller = builder.controller;
        this.reporter = builder.reporter;
        this.statReporter = builder.statReporter;
        this.isThirdPartyApp = builder.isThirdPartyApp;
        this.accountId = builder.accountId;
        this.secretKey = builder.secretKey;
        this.safeMode = builder.safeMode;
        this.restrictController = builder.restrictController;
        DNSLog.D = builder.isLogEnable;
        this.defaultHttpDnsImpl = new HttpDnsImpl(this);
        this.routeSelector = new RouteSelector();
        this.resolverObserver = new ConcurrentHashMap<>(1);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new InternalAppLifecycleListener());
    }

    public IpModel getIpModelByHost(String str, boolean z) {
        return this.defaultHttpDnsImpl.getIpModelByHost(str, z);
    }

    public void startDomainResolve(String... strArr) {
        startDomainResolve(null, strArr);
    }
}
