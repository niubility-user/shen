package com.tencent.map.tools.net;

import android.os.Bundle;
import com.tencent.map.tools.net.http.HttpProxyRule;
import com.tencent.mapsdk.internal.qb;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class NetConfig {
    private Bundle mArguments;
    private HashMap<String, String> mNetFlowRuleList;
    private List<HttpProxyRule> mProxyRuleList;
    private String mSecretKey;
    private Class<? extends qb> processorClass;
    private AdapterType mAdapterType = AdapterType.URL;
    private boolean mForceHttps = true;
    private boolean mIsLogEnable = false;

    private NetConfig() {
    }

    public static NetConfig create() {
        return new NetConfig();
    }

    public AdapterType getAdapterType() {
        return this.mAdapterType;
    }

    public Bundle getArguments() {
        return this.mArguments;
    }

    public HashMap<String, String> getNetFlowRuleList() {
        return this.mNetFlowRuleList;
    }

    public Class<? extends qb> getProcessor() {
        return this.processorClass;
    }

    public List<HttpProxyRule> getProxyRuleList() {
        return this.mProxyRuleList;
    }

    public String getSecretKey() {
        return this.mSecretKey;
    }

    public boolean isForceHttps() {
        return this.mForceHttps;
    }

    public boolean isLogEnable() {
        return this.mIsLogEnable;
    }

    public NetConfig setAdapterType(AdapterType adapterType) {
        this.mAdapterType = adapterType;
        return this;
    }

    public NetConfig setArguments(Bundle bundle) {
        this.mArguments = bundle;
        return this;
    }

    public NetConfig setForceHttps(boolean z) {
        this.mForceHttps = z;
        return this;
    }

    public NetConfig setLogEnable(boolean z) {
        this.mIsLogEnable = z;
        return this;
    }

    public NetConfig setNetFlowRuleList(HashMap<String, String> hashMap) {
        this.mNetFlowRuleList = hashMap;
        return this;
    }

    public NetConfig setProcessor(Class<? extends qb> cls) {
        this.processorClass = cls;
        return this;
    }

    public NetConfig setProxyRuleList(List<HttpProxyRule> list) {
        this.mProxyRuleList = list;
        return this;
    }

    public NetConfig setSecretKey(String str) {
        this.mSecretKey = str;
        return this;
    }
}
