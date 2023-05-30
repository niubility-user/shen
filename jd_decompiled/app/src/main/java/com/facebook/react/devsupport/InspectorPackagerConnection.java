package com.facebook.react.devsupport;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Inspector;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class InspectorPackagerConnection {
    private static final String TAG = "InspectorPackagerConnection";
    private BundleStatusProvider mBundleStatusProvider;
    private final Connection mConnection;
    private final Map<String, Inspector.LocalConnection> mInspectorConnections = new HashMap();
    private final String mPackageName;

    /* loaded from: classes12.dex */
    public interface BundleStatusProvider {
        BundleStatus getBundleStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class Connection extends WebSocketListener {
        private static final int RECONNECT_DELAY_MS = 2000;
        private boolean mClosed;
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private OkHttpClient mHttpClient;
        private boolean mSuppressConnectionErrors;
        private final String mUrl;
        @Nullable
        private WebSocket mWebSocket;

        public Connection(String str) {
            this.mUrl = str;
        }

        private void abort(String str, Throwable th) {
            FLog.e(InspectorPackagerConnection.TAG, "Error occurred, shutting down websocket connection: " + str, th);
            InspectorPackagerConnection.this.closeAllConnections();
            closeWebSocketQuietly();
        }

        private void closeWebSocketQuietly() {
            WebSocket webSocket = this.mWebSocket;
            if (webSocket != null) {
                try {
                    webSocket.close(1000, "End of session");
                } catch (Exception unused) {
                }
                this.mWebSocket = null;
            }
        }

        private void reconnect() {
            if (!this.mClosed) {
                if (!this.mSuppressConnectionErrors) {
                    FLog.w(InspectorPackagerConnection.TAG, "Couldn't connect to packager, will silently retry");
                    this.mSuppressConnectionErrors = true;
                }
                this.mHandler.postDelayed(new Runnable() { // from class: com.facebook.react.devsupport.InspectorPackagerConnection.Connection.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (Connection.this.mClosed) {
                            return;
                        }
                        Connection.this.connect();
                    }
                }, 2000L);
                return;
            }
            throw new IllegalStateException("Can't reconnect closed client");
        }

        public void close() {
            this.mClosed = true;
            WebSocket webSocket = this.mWebSocket;
            if (webSocket != null) {
                try {
                    webSocket.close(1000, "End of session");
                } catch (Exception unused) {
                }
                this.mWebSocket = null;
            }
        }

        public void connect() {
            if (!this.mClosed) {
                if (this.mHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    this.mHttpClient = builder.connectTimeout(10L, timeUnit).writeTimeout(10L, timeUnit).readTimeout(0L, TimeUnit.MINUTES).build();
                }
                this.mHttpClient.newWebSocket(new Request.Builder().url(this.mUrl).build(), this);
                return;
            }
            throw new IllegalStateException("Can't connect closed client");
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i2, String str) {
            this.mWebSocket = null;
            InspectorPackagerConnection.this.closeAllConnections();
            if (this.mClosed) {
                return;
            }
            reconnect();
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            if (this.mWebSocket != null) {
                abort("Websocket exception", th);
            }
            if (this.mClosed) {
                return;
            }
            reconnect();
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            try {
                InspectorPackagerConnection.this.handleProxyMessage(new JSONObject(str));
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            this.mWebSocket = webSocket;
        }

        public void send(final JSONObject jSONObject) {
            new AsyncTask<WebSocket, Void, Void>() { // from class: com.facebook.react.devsupport.InspectorPackagerConnection.Connection.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(WebSocket... webSocketArr) {
                    if (webSocketArr != null && webSocketArr.length != 0) {
                        try {
                            webSocketArr[0].send(jSONObject.toString());
                        } catch (Exception e2) {
                            FLog.w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e2);
                        }
                    }
                    return null;
                }
            }.execute(this.mWebSocket);
        }
    }

    public InspectorPackagerConnection(String str, String str2, BundleStatusProvider bundleStatusProvider) {
        this.mConnection = new Connection(str);
        this.mPackageName = str2;
        this.mBundleStatusProvider = bundleStatusProvider;
    }

    private JSONArray getPages() throws JSONException {
        List<Inspector.Page> pages = Inspector.getPages();
        JSONArray jSONArray = new JSONArray();
        BundleStatus bundleStatus = this.mBundleStatusProvider.getBundleStatus();
        for (Inspector.Page page : pages) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", String.valueOf(page.getId()));
            jSONObject.put("title", page.getTitle());
            jSONObject.put("app", this.mPackageName);
            jSONObject.put("vm", page.getVM());
            jSONObject.put("isLastBundleDownloadSuccess", bundleStatus.isLastDownloadSucess);
            jSONObject.put("bundleUpdateTimestamp", bundleStatus.updateTimestamp);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private void handleConnect(JSONObject jSONObject) throws JSONException {
        final String string = jSONObject.getString("pageId");
        if (this.mInspectorConnections.remove(string) == null) {
            try {
                this.mInspectorConnections.put(string, Inspector.connect(Integer.parseInt(string), new Inspector.RemoteConnection() { // from class: com.facebook.react.devsupport.InspectorPackagerConnection.1
                    @Override // com.facebook.react.bridge.Inspector.RemoteConnection
                    public void onDisconnect() {
                        try {
                            InspectorPackagerConnection.this.mInspectorConnections.remove(string);
                            InspectorPackagerConnection inspectorPackagerConnection = InspectorPackagerConnection.this;
                            inspectorPackagerConnection.sendEvent("disconnect", inspectorPackagerConnection.makePageIdPayload(string));
                        } catch (JSONException e2) {
                            FLog.w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e2);
                        }
                    }

                    @Override // com.facebook.react.bridge.Inspector.RemoteConnection
                    public void onMessage(String str) {
                        try {
                            InspectorPackagerConnection.this.sendWrappedEvent(string, str);
                        } catch (JSONException e2) {
                            FLog.w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e2);
                        }
                    }
                }));
                return;
            } catch (Exception e2) {
                FLog.w(TAG, "Failed to open page: " + string, e2);
                sendEvent("disconnect", makePageIdPayload(string));
                return;
            }
        }
        throw new IllegalStateException("Already connected: " + string);
    }

    private void handleDisconnect(JSONObject jSONObject) throws JSONException {
        Inspector.LocalConnection remove = this.mInspectorConnections.remove(jSONObject.getString("pageId"));
        if (remove == null) {
            return;
        }
        remove.disconnect();
    }

    private void handleWrappedEvent(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("pageId");
        String string2 = jSONObject.getString("wrappedEvent");
        Inspector.LocalConnection localConnection = this.mInspectorConnections.get(string);
        if (localConnection != null) {
            localConnection.sendMessage(string2);
            return;
        }
        throw new IllegalStateException("Not connected: " + string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject makePageIdPayload(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pageId", str);
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, Object obj) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event", str);
        jSONObject.put("payload", obj);
        this.mConnection.send(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendWrappedEvent(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pageId", str);
        jSONObject.put("wrappedEvent", str2);
        sendEvent("wrappedEvent", jSONObject);
    }

    void closeAllConnections() {
        Iterator<Map.Entry<String, Inspector.LocalConnection>> it = this.mInspectorConnections.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().disconnect();
        }
        this.mInspectorConnections.clear();
    }

    public void closeQuietly() {
        this.mConnection.close();
    }

    public void connect() {
        this.mConnection.connect();
    }

    void handleProxyMessage(JSONObject jSONObject) throws JSONException, IOException {
        String string = jSONObject.getString("event");
        string.hashCode();
        char c2 = '\uffff';
        switch (string.hashCode()) {
            case 530405532:
                if (string.equals("disconnect")) {
                    c2 = 0;
                    break;
                }
                break;
            case 951351530:
                if (string.equals("connect")) {
                    c2 = 1;
                    break;
                }
                break;
            case 1328613653:
                if (string.equals("wrappedEvent")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1962251790:
                if (string.equals("getPages")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                handleDisconnect(jSONObject.getJSONObject("payload"));
                return;
            case 1:
                handleConnect(jSONObject.getJSONObject("payload"));
                return;
            case 2:
                handleWrappedEvent(jSONObject.getJSONObject("payload"));
                return;
            case 3:
                sendEvent("getPages", getPages());
                return;
            default:
                throw new IllegalArgumentException("Unknown event: " + string);
        }
    }

    public void sendEventToAllConnections(String str) {
        Iterator<Map.Entry<String, Inspector.LocalConnection>> it = this.mInspectorConnections.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().sendMessage(str);
        }
    }

    /* loaded from: classes12.dex */
    public static class BundleStatus {
        public Boolean isLastDownloadSucess;
        public long updateTimestamp;

        public BundleStatus(Boolean bool, long j2) {
            this.updateTimestamp = -1L;
            this.isLastDownloadSucess = bool;
            this.updateTimestamp = j2;
        }

        public BundleStatus() {
            this(Boolean.FALSE, -1L);
        }
    }
}
