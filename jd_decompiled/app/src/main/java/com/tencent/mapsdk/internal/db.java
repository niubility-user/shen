package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.map.tools.net.NetAdapter;
import com.tencent.map.tools.net.NetConfig;
import com.tencent.map.tools.net.NetRequest;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.map.tools.net.http.HttpProxyRule;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class db implements NetAdapter {

    /* renamed from: h  reason: collision with root package name */
    private static final String f16403h = "NetImpl";
    public Bundle a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f16404c;
    private List<HttpProxyRule> d;

    /* renamed from: g  reason: collision with root package name */
    private String f16407g;
    private boolean b = true;

    /* renamed from: e  reason: collision with root package name */
    private HashMap<String, String> f16405e = new HashMap<>();

    /* renamed from: f  reason: collision with root package name */
    private HashSet<Class<? extends qb>> f16406f = new HashSet<>();

    public static String a(String str) {
        if (str != null) {
            String[] split = str.split(";");
            int length = split.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                String str2 = split[i2];
                if (str2.contains("charset")) {
                    String[] split2 = str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
                    if (split2.length > 1) {
                        return split2[1].trim();
                    }
                } else {
                    i2++;
                }
            }
        }
        return "GBK";
    }

    public abstract NetResponse a(NetRequest netRequest);

    public abstract void a(Context context, Bundle bundle);

    public void a(Class<? extends qb> cls) {
        this.f16406f.add(cls);
    }

    public void a(HashMap<String, String> hashMap) {
        this.f16405e.putAll(hashMap);
    }

    public void a(List<HttpProxyRule> list) {
        this.d = list;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public abstract NetResponse b(NetRequest netRequest);

    public void b(String str) {
        this.f16407g = str;
    }

    public void b(boolean z) {
        this.f16404c = z;
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public NetResponse doGet(NetRequest netRequest) {
        return a(netRequest);
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public NetResponse doPost(NetRequest netRequest) {
        return b(netRequest);
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public HashSet<Class<? extends qb>> getNetFlowProcessor() {
        return this.f16406f;
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public HashMap<String, String> getNetFlowRuleList() {
        return this.f16405e;
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public List<HttpProxyRule> getProxyRuleList() {
        return this.d;
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public String getSecretKey() {
        return this.f16407g;
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public void initNet(Context context, NetConfig netConfig) {
        try {
            a(netConfig.isForceHttps());
            b(netConfig.isLogEnable());
            a(netConfig.getProxyRuleList());
            a(netConfig.getNetFlowRuleList());
            b(netConfig.getSecretKey());
            a(netConfig.getProcessor());
            Bundle arguments = netConfig.getArguments();
            this.a = arguments;
            a(context, arguments);
        } catch (Exception e2) {
            ma.b(f16403h, "initNet error:" + e2.toString());
        }
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public boolean isForceHttps() {
        return this.b;
    }

    @Override // com.tencent.map.tools.net.NetAdapter
    public boolean isLogEnable() {
        return this.f16404c;
    }
}
