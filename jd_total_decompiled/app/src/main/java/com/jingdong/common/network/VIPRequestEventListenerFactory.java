package com.jingdong.common.network;

import android.text.TextUtils;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes5.dex */
public class VIPRequestEventListenerFactory implements EventListener.Factory {
    public static ArrayList<String> VIPFunctionIdList = new ArrayList<>(Arrays.asList("search", "wareBusiness", "cart", "currentOrder"));

    /* loaded from: classes5.dex */
    public class RequestEventListener extends EventListener {
        private String functionId;
        private long rawSize;
        private int rtt;
        private long startTime = 0;

        public RequestEventListener() {
        }

        @Override // okhttp3.EventListener
        public void callEnd(Call call) {
            super.callEnd(call);
            if (this.startTime > 0) {
                this.rtt = (int) (System.currentTimeMillis() - this.startTime);
            }
        }

        @Override // okhttp3.EventListener
        public void callFailed(Call call, IOException iOException) {
            super.callFailed(call, iOException);
        }

        @Override // okhttp3.EventListener
        public void callStart(Call call) {
            super.callStart(call);
            String queryParameter = call.request().url().queryParameter("functionId");
            this.functionId = queryParameter;
            if (TextUtils.isEmpty(queryParameter) || !VIPRequestEventListenerFactory.VIPFunctionIdList.contains(this.functionId)) {
                return;
            }
            this.startTime = System.currentTimeMillis();
        }

        @Override // okhttp3.EventListener
        public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
            super.connectEnd(call, inetSocketAddress, proxy, protocol);
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
            if (this.startTime > 0) {
                this.rawSize = j2;
            }
        }

        @Override // okhttp3.EventListener
        public void responseBodyStart(Call call) {
            super.responseBodyStart(call);
        }

        @Override // okhttp3.EventListener
        public void responseHeadersEnd(Call call, Response response) {
            super.responseHeadersEnd(call, response);
        }

        @Override // okhttp3.EventListener
        public void responseHeadersStart(Call call) {
            super.responseHeadersStart(call);
        }

        @Override // okhttp3.EventListener
        public void secureConnectEnd(Call call, Handshake handshake) {
            super.secureConnectEnd(call, handshake);
        }

        @Override // okhttp3.EventListener
        public void secureConnectStart(Call call) {
            super.secureConnectStart(call);
        }
    }

    public static EventListener.Factory newInstance() {
        return new VIPRequestEventListenerFactory();
    }

    @Override // okhttp3.EventListener.Factory
    public EventListener create(Call call) {
        return new RequestEventListener();
    }
}
