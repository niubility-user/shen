package com.tencent.smtt.sdk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public final class ProxyConfig {
    public static final String MATCH_ALL_SCHEMES = "*";
    private List<ProxyRule> a;
    private List<String> b;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private List<ProxyRule> a;
        private List<String> b;

        public Builder() {
            this.a = new ArrayList();
            this.b = new ArrayList();
        }

        public Builder(ProxyConfig proxyConfig) {
            this.a = proxyConfig.getProxyRules();
            this.b = proxyConfig.getBypassRules();
        }

        private List<ProxyRule> a() {
            return this.a;
        }

        private List<String> b() {
            return this.b;
        }

        public Builder addBypassRule(String str) {
            this.b.add(str);
            return this;
        }

        public Builder addDirect() {
            return addDirect(ProxyConfig.MATCH_ALL_SCHEMES);
        }

        public Builder addDirect(String str) {
            this.a.add(new ProxyRule(str, "direct://"));
            return this;
        }

        public Builder addProxyRule(String str) {
            this.a.add(new ProxyRule(str));
            return this;
        }

        public Builder addProxyRule(String str, String str2) {
            this.a.add(new ProxyRule(str2, str));
            return this;
        }

        public ProxyConfig build() {
            return new ProxyConfig(a(), b());
        }

        public Builder bypassSimpleHostnames() {
            return addBypassRule("<local>");
        }

        public Builder removeImplicitRules() {
            return addBypassRule("<-loopback>");
        }
    }

    /* loaded from: classes9.dex */
    public static final class ProxyRule {
        private String a;
        private String b;

        public ProxyRule(String str) {
            this(ProxyConfig.MATCH_ALL_SCHEMES, str);
        }

        public ProxyRule(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public String getSchemeFilter() {
            return this.a;
        }

        public String getUrl() {
            return this.b;
        }
    }

    public ProxyConfig(List<ProxyRule> list, List<String> list2) {
        this.a = list;
        this.b = list2;
    }

    public List<String> getBypassRules() {
        return Collections.unmodifiableList(this.b);
    }

    public List<ProxyRule> getProxyRules() {
        return Collections.unmodifiableList(this.a);
    }
}
