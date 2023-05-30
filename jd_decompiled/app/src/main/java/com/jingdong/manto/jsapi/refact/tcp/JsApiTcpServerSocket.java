package com.jingdong.manto.jsapi.refact.tcp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.a;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.AppLifeCycle;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.udp.RemoteInfo;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.q;
import com.jingdong.manto.utils.t;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes15.dex */
public abstract class JsApiTcpServerSocket extends AbstractMantoModule {
    static final String CLOSE_TCP_SERVER_NAME = "closeTCPServer";
    static final String CREATE_TCP_SERVER_NAME = "createTCPServer";
    static final String DISCONNECT_TCP_CLIENT_NAME = "disConnectTCPClient";
    static final String EVENT_ON_TCP_SERVER_CLOSE_NAME = "onTCPServerClose";
    static final String EVENT_ON_TCP_SERVER_CONNECTED = "onTCPServerConnected";
    static final String EVENT_ON_TCP_SERVER_DISCONNECTED = "onTCPServerDisconnected";
    static final String EVENT_ON_TCP_SERVER_ERROR_NAME = "onTCPServerError";
    static final String EVENT_ON_TCP_SERVER_MESSAGE_NAME = "onTCPServerMessage";
    static final String GET_CONNECTED_LIST = "getTCPServerConnectedList";
    static final String MODULE_NAME = "JsApiTcpServerSocket";
    static final String SEND_TCP_SERVER_MESSAGE_NAME = "sendTCPServerMessage";
    static final String TAG = "JsApiTcpServerSocket";
    private ByteBuffer mByteBuffer;
    private MantoCore mantoCore;
    private int mHashCode = 0;
    AppLifeCycle.Listener listener = new AppLifeCycle.Listener() { // from class: com.jingdong.manto.jsapi.refact.tcp.JsApiTcpServerSocket.1
        @Override // com.jingdong.manto.AppLifeCycle.Listener
        public void onAppDestroy() {
            JsApiTcpServerSocket.this.onDestroy();
        }
    };

    public abstract Bundle closeTCPServer(String str, int i2);

    public abstract Bundle createTCPServer(String str, int i2, int i3);

    public abstract Bundle disconnectTCPClient(String str, int i2, String str2, int i3);

    public Activity getActivity() {
        return this.mantoCore.getActivity();
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "JsApiTcpServerSocket";
    }

    public abstract Bundle getTCPServerConnectedList(String str, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public String getWifiIpAddress() {
        return q.a(a.g());
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public void handleMethod(String str, MantoCore mantoCore, Bundle bundle, MantoResultCallBack mantoResultCallBack) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        this.mantoCore = mantoCore;
        String string = bundle.getString("appid");
        this.mHashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        Bundle bundle2 = new Bundle();
        if (SEND_TCP_SERVER_MESSAGE_NAME.equals(str)) {
            int optInt = jSONObject.optInt("serverId");
            String optString = jSONObject.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
            int optInt2 = jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT);
            if (!bundle.getBoolean(IMantoBaseModule.HAS_NATIVE_BUFFER)) {
                sendTCPServerMessage(string, optInt, optString, optInt2, jSONObject.optString("message"), mantoResultCallBack);
                return;
            }
            if (this.mByteBuffer == null) {
                bundle2.putString("message", "ByteBuffer is null, no message");
                mantoResultCallBack.onFailed(bundle2);
            }
            sendTCPServerMessage(string, optInt, optString, optInt2, t.a(this.mByteBuffer), jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET), jSONObject.optInt(CartConstant.KEY_LENGTH), mantoResultCallBack);
        }
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule, com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle handleMethodSync(String str, MantoCore mantoCore, Bundle bundle) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(bundle.getString("json"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            jSONObject = null;
        }
        this.mantoCore = mantoCore;
        String string = bundle.getString("appid");
        this.mHashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        if (CREATE_TCP_SERVER_NAME.equals(str)) {
            return createTCPServer(string, jSONObject.optInt("serverId"), jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT));
        }
        if (CLOSE_TCP_SERVER_NAME.equals(str)) {
            return closeTCPServer(string, jSONObject.optInt("serverId"));
        }
        if (DISCONNECT_TCP_CLIENT_NAME.equals(str)) {
            return disconnectTCPClient(string, jSONObject.optInt("serverId"), jSONObject.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID), jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT));
        }
        if (TextUtils.equals(GET_CONNECTED_LIST, str)) {
            return getTCPServerConnectedList(string, jSONObject.optInt("serverId"));
        }
        return null;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public Bundle initData(String str, MantoCore mantoCore, JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        Object opt = jSONObject.opt("message");
        if (opt != null && (opt instanceof ByteBuffer)) {
            this.mByteBuffer = (ByteBuffer) opt;
            jSONObject.remove("message");
        }
        bundle.putString("json", jSONObject.toString());
        return bundle;
    }

    @Override // com.jingdong.manto.jsapi.openmodule.AbstractMantoModule
    protected void injectJsApiMethod(List<JsApiMethod> list) {
        list.add(new JsApiMethod(CREATE_TCP_SERVER_NAME, 3));
        list.add(new JsApiMethod(SEND_TCP_SERVER_MESSAGE_NAME, 1));
        list.add(new JsApiMethod(GET_CONNECTED_LIST, 3));
        list.add(new JsApiMethod(CLOSE_TCP_SERVER_NAME, 3));
        list.add(new JsApiMethod(DISCONNECT_TCP_CLIENT_NAME, 3));
    }

    public abstract void onDestroy();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPServerClose(int i2) {
        MantoCore mantoCore = this.mantoCore;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiTcpServerSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("serverId", i2);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.mantoCore, EVENT_ON_TCP_SERVER_CLOSE_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPServerConnected(int i2, String str, int i3) {
        MantoCore mantoCore = this.mantoCore;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiTcpServerSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("serverId", i2);
            jSONObject.put(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, str);
            jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i3);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.mantoCore, EVENT_ON_TCP_SERVER_CONNECTED, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPServerDisconnected(int i2, String str, int i3) {
        MantoCore mantoCore = this.mantoCore;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiTcpServerSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("serverId", i2);
            jSONObject.put(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, str);
            jSONObject.put(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, i3);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.mantoCore, EVENT_ON_TCP_SERVER_DISCONNECTED, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPServerError(int i2, int i3, String str) {
        MantoCore mantoCore = this.mantoCore;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiTcpServerSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", i3);
            jSONObject.put("serverId", i2);
            jSONObject.put("errMsg", str);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.mantoCore, EVENT_ON_TCP_SERVER_ERROR_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPServerMessage(int i2, byte[] bArr, RemoteInfo remoteInfo, LocalInfo localInfo) {
        if (this.mantoCore == null || this.mHashCode == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("serverId", i2);
            jSONObject.put("remoteInfo", remoteInfo.toJsonObject());
            jSONObject.put("localInfo", localInfo.toJsonObject());
        } catch (JSONException unused) {
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", ByteBuffer.wrap(bArr));
        if (convertNativeBuffer(this.mantoCore, jSONObject, hashMap, true)) {
            dispatchEvent(this.mantoCore, EVENT_ON_TCP_SERVER_MESSAGE_NAME, jSONObject, this.mHashCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerLifecycle(String str) {
        AppLifeCycle.add(str, this.listener);
    }

    public abstract void sendTCPServerMessage(String str, int i2, String str2, int i3, String str3, MantoResultCallBack mantoResultCallBack);

    public abstract void sendTCPServerMessage(String str, int i2, String str2, int i3, byte[] bArr, int i4, int i5, MantoResultCallBack mantoResultCallBack);

    /* JADX INFO: Access modifiers changed from: protected */
    public void unRegisterLifecycle(String str) {
        AppLifeCycle.remove(str, this.listener);
    }
}
