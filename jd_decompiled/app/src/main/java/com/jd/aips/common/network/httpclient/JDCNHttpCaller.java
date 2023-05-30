package com.jd.aips.common.network.httpclient;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes12.dex */
public abstract class JDCNHttpCaller {
    public static final int ASYN_THREAD = 17;
    public static final int MAIN_THREAD = 16;

    /* loaded from: classes12.dex */
    public static class NetworkRequest {
        private String a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, String> f1576c;
        private Map<String, String> d;

        /* renamed from: e  reason: collision with root package name */
        private int f1577e;

        /* renamed from: f  reason: collision with root package name */
        private int f1578f;

        /* renamed from: g  reason: collision with root package name */
        private int f1579g;

        /* renamed from: h  reason: collision with root package name */
        private int f1580h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f1581i;

        /* renamed from: j  reason: collision with root package name */
        private int f1582j;

        /* renamed from: k  reason: collision with root package name */
        private String f1583k;

        /* loaded from: classes12.dex */
        public static class Builder {
            private NetworkRequest a = new NetworkRequest();

            public Builder addHeader(String str, String str2) {
                if (this.a.f1576c == null) {
                    this.a.f1576c = new HashMap();
                }
                this.a.f1576c.put(str, str2);
                return this;
            }

            public Builder addRequestParam(String str, String str2) {
                if (this.a.d == null) {
                    this.a.d = new HashMap();
                }
                this.a.d.put(str, str2);
                return this;
            }

            public NetworkRequest build() {
                return this.a;
            }

            public Builder setConnectionTimeout(int i2) {
                this.a.f1578f = i2;
                return this;
            }

            public Builder setIsPost() {
                this.a.f1581i = true;
                return this;
            }

            public Builder setPostContent(String str) {
                this.a.f1583k = str;
                return this;
            }

            public Builder setReadTimeout(int i2) {
                this.a.f1579g = i2;
                return this;
            }

            public Builder setRequestStr(String str) {
                this.a.b = str;
                return this;
            }

            public Builder setRetryCount(int i2) {
                this.a.f1582j = i2;
                return this;
            }

            public Builder setThreadStrategy(int i2) {
                this.a.f1577e = i2;
                return this;
            }

            public Builder setUrl(String str) {
                this.a.a = str;
                return this;
            }

            public Builder setWriteTimeout(int i2) {
                this.a.f1580h = i2;
                return this;
            }
        }

        public int getConnectionTimeout() {
            return this.f1578f;
        }

        public Map<String, String> getHeaders() {
            return this.f1576c;
        }

        public String getPostContent() {
            return this.f1583k;
        }

        public int getReadTimeout() {
            return this.f1579g;
        }

        public Map<String, String> getRequestParams() {
            return this.d;
        }

        public String getRequestStr() {
            return this.b;
        }

        public int getRetryCount() {
            return this.f1582j;
        }

        public int getThreadStrategy() {
            return this.f1577e;
        }

        public String getUrl() {
            return this.a;
        }

        public int getWriteTimeout() {
            return this.f1580h;
        }

        public boolean isPost() {
            return this.f1581i;
        }

        private NetworkRequest() {
            this.f1577e = 17;
            this.f1578f = 10000;
            this.f1579g = 10000;
            this.f1580h = 10000;
        }
    }

    public static NetworkRequest.Builder createRequestBuilder() {
        return new NetworkRequest.Builder();
    }

    public abstract void cancalAllRequest();

    public abstract void cancelRequest(int i2);

    public abstract int startRequest(NetworkRequest networkRequest, INetworkCallback iNetworkCallback);

    public abstract JDCNHttpResponse startRequestSync(NetworkRequest networkRequest);
}
