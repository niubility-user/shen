package com.jingdong.common.network.cronet;

import android.text.TextUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes5.dex */
public class OkhttpEventListenerFactory implements EventListener.Factory {

    /* loaded from: classes5.dex */
    public static class InternalEventListener extends EventListener {
        private String mReqProtocol;
        private long mReqStartTimeStamp;
        private long mSecureHandShakeEnd;
        private long mSecureHandShakeStart;

        public InternalEventListener() {
            NetworkPerformanceDealer.okhttpPerfData.totalReqCount.getAndIncrement();
        }

        @Override // okhttp3.EventListener
        public void callEnd(Call call) {
            super.callEnd(call);
            String str = this.mReqProtocol;
            if (!TextUtils.isEmpty(str) && str.toLowerCase().contains("h3")) {
                NetworkPerformanceDealer.okhttpPerfData.h3SucceedCount.getAndIncrement();
                int i2 = (int) (this.mSecureHandShakeEnd - this.mSecureHandShakeStart);
                if (i2 > 0) {
                    NetworkPerformanceDealer.okhttpPerfData.h3HandShakeSum.getAndAdd(i2);
                    NetworkPerformanceDealer.okhttpPerfData.h3HandShakeCount.getAndIncrement();
                }
            } else if (!TextUtils.isEmpty(str) && str.toLowerCase().contains("h2")) {
                NetworkPerformanceDealer.okhttpPerfData.h2SucceedCount.getAndIncrement();
                int i3 = (int) (this.mSecureHandShakeEnd - this.mSecureHandShakeStart);
                if (i3 > 0) {
                    NetworkPerformanceDealer.okhttpPerfData.h2HandShakeSum.getAndAdd(i3);
                    NetworkPerformanceDealer.okhttpPerfData.h2HandShakeCount.getAndIncrement();
                }
            } else {
                NetworkPerformanceDealer.okhttpPerfData.h1SucceedCount.getAndIncrement();
                int i4 = (int) (this.mSecureHandShakeEnd - this.mSecureHandShakeStart);
                if (i4 > 0) {
                    NetworkPerformanceDealer.okhttpPerfData.h1HandShakeSum.getAndAdd(i4);
                    NetworkPerformanceDealer.okhttpPerfData.h1HandShakeCount.getAndIncrement();
                }
            }
        }

        @Override // okhttp3.EventListener
        public void callFailed(Call call, IOException iOException) {
            super.callFailed(call, iOException);
        }

        @Override // okhttp3.EventListener
        public void callStart(Call call) {
            super.callStart(call);
            this.mReqStartTimeStamp = System.currentTimeMillis();
        }

        @Override // okhttp3.EventListener
        public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
            super.connectEnd(call, inetSocketAddress, proxy, protocol);
            this.mReqProtocol = protocol != null ? protocol.toString() : "unknown";
        }

        @Override // okhttp3.EventListener
        public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException iOException) {
            super.connectFailed(call, inetSocketAddress, proxy, protocol, iOException);
        }

        @Override // okhttp3.EventListener
        public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
            super.connectStart(call, inetSocketAddress, proxy);
        }

        @Override // okhttp3.EventListener
        public void connectionAcquired(Call call, Connection connection) {
            super.connectionAcquired(call, connection);
            try {
                if (TextUtils.isEmpty(this.mReqProtocol)) {
                    this.mReqProtocol = connection.protocol().toString();
                }
            } catch (Throwable unused) {
            }
        }

        @Override // okhttp3.EventListener
        public void connectionReleased(Call call, Connection connection) {
            super.connectionReleased(call, connection);
        }

        @Override // okhttp3.EventListener
        public void dnsEnd(Call call, String str, List<InetAddress> list) {
            super.dnsEnd(call, str, list);
        }

        @Override // okhttp3.EventListener
        public void dnsStart(Call call, String str) {
            super.dnsStart(call, str);
        }

        @Override // okhttp3.EventListener
        public void requestBodyEnd(Call call, long j2) {
            super.requestBodyEnd(call, j2);
        }

        @Override // okhttp3.EventListener
        public void requestBodyStart(Call call) {
            super.requestBodyStart(call);
        }

        @Override // okhttp3.EventListener
        public void requestHeadersEnd(Call call, Request request) {
            super.requestHeadersEnd(call, request);
        }

        @Override // okhttp3.EventListener
        public void requestHeadersStart(Call call) {
            super.requestHeadersStart(call);
        }

        @Override // okhttp3.EventListener
        public void responseBodyEnd(Call call, long j2) {
            super.responseBodyEnd(call, j2);
        }

        @Override // okhttp3.EventListener
        public void responseBodyStart(Call call) {
            super.responseBodyStart(call);
        }

        @Override // okhttp3.EventListener
        public void responseHeadersEnd(Call call, Response response) {
            super.responseHeadersEnd(call, response);
            long currentTimeMillis = System.currentTimeMillis() - this.mReqStartTimeStamp;
            String protocol = response.protocol().toString();
            this.mReqProtocol = protocol;
            if (!TextUtils.isEmpty(protocol) && protocol.toLowerCase().contains("h3")) {
                NetworkPerformanceDealer.okhttpPerfData.h3RttSum.getAndAdd((int) currentTimeMillis);
            } else if (!TextUtils.isEmpty(protocol) && protocol.toLowerCase().contains("h2")) {
                NetworkPerformanceDealer.okhttpPerfData.h2RttSum.getAndAdd((int) currentTimeMillis);
            } else {
                NetworkPerformanceDealer.okhttpPerfData.h1RttSum.getAndAdd((int) currentTimeMillis);
            }
        }

        @Override // okhttp3.EventListener
        public void responseHeadersStart(Call call) {
            super.responseHeadersStart(call);
        }

        @Override // okhttp3.EventListener
        public void secureConnectEnd(Call call, Handshake handshake) {
            super.secureConnectEnd(call, handshake);
            this.mSecureHandShakeEnd = System.currentTimeMillis();
        }

        @Override // okhttp3.EventListener
        public void secureConnectStart(Call call) {
            super.secureConnectStart(call);
            this.mSecureHandShakeStart = System.currentTimeMillis();
        }
    }

    @Override // okhttp3.EventListener.Factory
    public EventListener create(Call call) {
        return new InternalEventListener();
    }
}
