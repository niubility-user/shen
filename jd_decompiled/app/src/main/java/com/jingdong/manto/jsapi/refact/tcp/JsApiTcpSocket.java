package com.jingdong.manto.jsapi.refact.tcp;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.jsapi.refact.udp.RemoteInfo;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.t;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes15.dex */
public abstract class JsApiTcpSocket extends AbstractMantoModule {
    static final String CLOSE_TCP_SOCKET_NAME = "closeTCPSocket";
    static final String CONNECT_TCP_SOCKET_NAME = "connectTCPSocket";
    static final String CREATE_TCP_SOCKET_NAME = "createTCPSocket";
    static final String EVENT_ON_TCP_SOCKET_CLOSE_NAME = "onTCPSocketClose";
    static final String EVENT_ON_TCP_SOCKET_CONNECT_NAME = "onTCPSocketConnect";
    static final String EVENT_ON_TCP_SOCKET_ERROR_NAME = "onTCPSocketError";
    static final String EVENT_ON_TCP_SOCKET_MESSAGE_NAME = "onTCPSocketMessage";
    static final String MODULE_NAME = "JsApiTcpSocket";
    static final String SEND_TCP_SOCKET_MESSAGE_NAME = "writeTCPSocket";
    static final String TAG = "JsApiTcpSocket";
    private MantoCore core;
    private ByteBuffer mByteBuffer;
    private int mHashCode = 0;

    public abstract Bundle closeTCPSocket(String str, int i2);

    public abstract void connectTCPSocket(String str, int i2, String str2, int i3, MantoResultCallBack mantoResultCallBack);

    public abstract Bundle createTCPSocket(String str, int i2);

    public Activity getActivity() {
        return this.core.getActivity();
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "JsApiTcpSocket";
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
        this.core = mantoCore;
        String string = bundle.getString("appid");
        this.mHashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        Bundle bundle2 = new Bundle();
        if (CONNECT_TCP_SOCKET_NAME.equals(str)) {
            connectTCPSocket(string, jSONObject.optInt("socketId"), jSONObject.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID, ""), jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, -1), mantoResultCallBack);
        } else if (SEND_TCP_SOCKET_MESSAGE_NAME.equals(str)) {
            int optInt = jSONObject.optInt("socketId");
            if (!bundle.getBoolean(IMantoBaseModule.HAS_NATIVE_BUFFER)) {
                sendTCPSocketMessage(string, optInt, jSONObject.optString("message"), mantoResultCallBack);
                return;
            }
            if (this.mByteBuffer == null) {
                bundle2.putString("message", "ByteBuffer is null, no message");
                mantoResultCallBack.onFailed(bundle2);
            }
            sendTCPSocketMessage(string, optInt, t.a(this.mByteBuffer), jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET), jSONObject.optInt(CartConstant.KEY_LENGTH), mantoResultCallBack);
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
        this.core = mantoCore;
        String string = bundle.getString("appid");
        this.mHashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        if (CREATE_TCP_SOCKET_NAME.equals(str)) {
            return createTCPSocket(string, jSONObject.optInt("socketId"));
        }
        if (CLOSE_TCP_SOCKET_NAME.equals(str)) {
            return closeTCPSocket(string, jSONObject.optInt("socketId"));
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
        list.add(new JsApiMethod(CREATE_TCP_SOCKET_NAME, 3));
        list.add(new JsApiMethod(CONNECT_TCP_SOCKET_NAME, 1));
        list.add(new JsApiMethod(SEND_TCP_SOCKET_MESSAGE_NAME, 1));
        list.add(new JsApiMethod(CLOSE_TCP_SOCKET_NAME, 3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPSocketClose(int i2) {
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiTcpSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("socketId", i2);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ON_TCP_SOCKET_CLOSE_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPSocketConnect(int i2) {
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiTcpSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("socketId", i2);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ON_TCP_SOCKET_CONNECT_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPSocketError(int i2, int i3, String str) {
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiTcpSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", i3);
            jSONObject.put("socketId", i2);
            jSONObject.put("errMsg", str);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ON_TCP_SOCKET_ERROR_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onTCPSocketMessage(int i2, byte[] bArr, RemoteInfo remoteInfo, LocalInfo localInfo) {
        if (this.core == null || this.mHashCode == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("socketId", i2);
            jSONObject.put("remoteInfo", remoteInfo.toJsonObject());
            jSONObject.put("localInfo", localInfo.toJsonObject());
        } catch (JSONException unused) {
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", ByteBuffer.wrap(bArr));
        if (convertNativeBuffer(this.core, jSONObject, hashMap, true)) {
            dispatchEvent(this.core, EVENT_ON_TCP_SOCKET_MESSAGE_NAME, jSONObject, this.mHashCode);
        }
    }

    public abstract void sendTCPSocketMessage(String str, int i2, String str2, MantoResultCallBack mantoResultCallBack);

    public abstract void sendTCPSocketMessage(String str, int i2, byte[] bArr, int i3, int i4, MantoResultCallBack mantoResultCallBack);
}
