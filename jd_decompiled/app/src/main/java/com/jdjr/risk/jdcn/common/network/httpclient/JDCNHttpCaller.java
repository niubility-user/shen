package com.jdjr.risk.jdcn.common.network.httpclient;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* loaded from: classes18.dex */
public abstract class JDCNHttpCaller {
    public static final int ASYN_THREAD = 17;
    public static final int MAIN_THREAD = 16;

    /* loaded from: classes18.dex */
    public static class NetworkRequest {
        private int connectionTimeout;
        private Map<String, String> headers;
        private boolean isPost;
        private String postContent;
        private int readTimeout;
        private Map<String, String> requestParams;
        private String requestStr;
        private int retryCount;
        private int threadStrategy;
        private String url;
        private int writeTimeout;

        /* loaded from: classes18.dex */
        public static class Builder {
            private NetworkRequest request = new NetworkRequest();

            public Builder addHeader(String str, String str2) {
                if (this.request.headers == null) {
                    this.request.headers = new HashMap();
                }
                this.request.headers.put(str, str2);
                return this;
            }

            public Builder addRequestParam(String str, String str2) {
                if (this.request.requestParams == null) {
                    this.request.requestParams = new HashMap();
                }
                this.request.requestParams.put(str, str2);
                return this;
            }

            public NetworkRequest build() {
                return this.request;
            }

            public Builder setConnectionTimeout(int i2) {
                this.request.connectionTimeout = i2;
                return this;
            }

            public Builder setIsPost() {
                this.request.isPost = true;
                return this;
            }

            public Builder setPostContent(String str) {
                this.request.postContent = str;
                return this;
            }

            public Builder setReadTimeout(int i2) {
                this.request.readTimeout = i2;
                return this;
            }

            public Builder setRequestStr(String str) {
                this.request.requestStr = str;
                return this;
            }

            public Builder setRetryCount(int i2) {
                this.request.retryCount = i2;
                return this;
            }

            public Builder setThreadStrategy(int i2) {
                this.request.threadStrategy = i2;
                return this;
            }

            public Builder setUrl(String str) {
                this.request.url = str;
                return this;
            }

            public Builder setWriteTimeout(int i2) {
                this.request.writeTimeout = i2;
                return this;
            }
        }

        public int getConnectionTimeout() {
            return this.connectionTimeout;
        }

        public Map<String, String> getHeaders() {
            return this.headers;
        }

        public String getPostContent() {
            return this.postContent;
        }

        public int getReadTimeout() {
            return this.readTimeout;
        }

        public Map<String, String> getRequestParams() {
            return this.requestParams;
        }

        public String getRequestStr() {
            return this.requestStr;
        }

        public int getRetryCount() {
            return this.retryCount;
        }

        public int getThreadStrategy() {
            return this.threadStrategy;
        }

        public String getUrl() {
            return this.url;
        }

        public int getWriteTimeout() {
            return this.writeTimeout;
        }

        public boolean isPost() {
            return this.isPost;
        }

        private NetworkRequest() {
            this.threadStrategy = 17;
            this.connectionTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
        }
    }

    public static NetworkRequest.Builder createRequestBuilder() {
        return new NetworkRequest.Builder();
    }

    public abstract void cancalAllRequest();

    public abstract void cancelRequest(int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public int generateId(String str) {
        return new Random().nextInt(10000) + 90000;
    }

    public abstract int startRequest(NetworkRequest networkRequest, INetworkCallback iNetworkCallback);

    public abstract JDCNHttpResponse startRequestSync(NetworkRequest networkRequest);
}
