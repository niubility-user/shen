package com.jingdong.manto.jsapi.refact.udp;

import android.app.Activity;
import android.os.Bundle;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.MantoCore;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoModule;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.JsApiMethod;
import com.jingdong.manto.jsapi.openmodule.MantoResultCallBack;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.t;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* loaded from: classes15.dex */
public abstract class JsApiUDPSocket extends AbstractMantoModule {
    static final String BINDUDPSOCKET_NAME = "bindUDPSocket";
    static final String CLOSEUDPSOCKET_NAME = "closeUDPSocket";
    static final String CREATEUDPSOCKET_NAME = "createUDPSocket";
    static final String EVENT_ONUDPSOCKETCLOSE_NAME = "onUDPSocketClose";
    static final String EVENT_ONUDPSOCKETERROR_NAME = "onUDPSocketError";
    static final String EVENT_ONUDPSOCKETLISTENING_NAME = "onUDPSocketListening";
    static final String EVENT_ONUDPSOCKETMESSAGE_NAME = "onUDPSocketMessage";
    static final String MODULE_NAME = "JsApiUDPSocket";
    static final String SENDUDPSOCKETMESSAGE_NAME = "sendUDPSocketMessage";
    static final String TAG = "JsApiUDPSocket";
    private MantoCore core;
    private ByteBuffer mByteBuffer;
    private int mHashCode = 0;

    public abstract Bundle bindUDPSocket(String str, int i2, int i3);

    public abstract Bundle closeUDPSocket(String str, int i2);

    public abstract Bundle createUDPSocket(String str, int i2);

    public Activity getActivity() {
        return this.core.getActivity();
    }

    @Override // com.jingdong.manto.jsapi.openmodule.IMantoBaseModule
    public String getModuleName() {
        return "JsApiUDPSocket";
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
        String string = bundle.getString("appid", "");
        this.core = mantoCore;
        this.mHashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        Bundle bundle2 = new Bundle();
        if (SENDUDPSOCKETMESSAGE_NAME.equals(str)) {
            int optInt = jSONObject.optInt("socketId");
            int optInt2 = jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT);
            String optString = jSONObject.optString(ThemeTitleConstant.TITLE_ADDRESS_DRAWABLE_ID);
            if (!bundle.getBoolean(IMantoBaseModule.HAS_NATIVE_BUFFER)) {
                String optString2 = jSONObject.optString("message");
                MantoLog.d("JsApiUDPSocket", "sendUDPSocketMessage msg:" + optString2);
                sendUDPSocketMessage(string, optInt, optInt2, optString, optString2, mantoResultCallBack);
                return;
            }
            if (this.mByteBuffer == null) {
                bundle2.putString("message", "ByteBuffer is null, no message");
                mantoResultCallBack.onFailed(bundle2);
            }
            int optInt3 = jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_OFFSET);
            int optInt4 = jSONObject.optInt(CartConstant.KEY_LENGTH);
            byte[] a = t.a(this.mByteBuffer);
            MantoLog.d("JsApiUDPSocket", "sendUDPSocketMessage length:" + optInt4);
            sendUDPSocketMessage(string, optInt, optInt2, optString, a, optInt3, optInt4, mantoResultCallBack);
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
        String string = bundle.getString("appid", "");
        this.core = mantoCore;
        this.mHashCode = bundle.getInt(IMantoBaseModule.COMPONENT_HASHCODE);
        if (CREATEUDPSOCKET_NAME.equals(str)) {
            int optInt = jSONObject.optInt("socketId");
            MantoLog.d("JsApiUDPSocket", CREATEUDPSOCKET_NAME);
            return createUDPSocket(string, optInt);
        } else if (!BINDUDPSOCKET_NAME.equals(str)) {
            if (CLOSEUDPSOCKET_NAME.equals(str)) {
                int optInt2 = jSONObject.optInt("socketId");
                MantoLog.d("JsApiUDPSocket", "closeUDPSocket id:" + optInt2);
                return closeUDPSocket(string, optInt2);
            }
            return null;
        } else {
            int optInt3 = jSONObject.optInt("socketId");
            int optInt4 = jSONObject.optInt(IMediaPlayer.OnNativeInvokeListener.ARG_PORT, -1);
            MantoLog.d("JsApiUDPSocket", "bindUDPSocket id:" + optInt3 + ", port:" + optInt4);
            return bindUDPSocket(string, optInt3, optInt4);
        }
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
        list.add(new JsApiMethod(CREATEUDPSOCKET_NAME, 3));
        list.add(new JsApiMethod(BINDUDPSOCKET_NAME, 3));
        list.add(new JsApiMethod(SENDUDPSOCKETMESSAGE_NAME, 1));
        list.add(new JsApiMethod(CLOSEUDPSOCKET_NAME, 3));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onUDPSocketClose(int i2) {
        MantoLog.d("JsApiUDPSocket", "onUDPSocketClose socketId:" + i2);
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiUDPSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("socketId", i2);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ONUDPSOCKETCLOSE_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onUDPSocketError(int i2, String str) {
        MantoLog.d("JsApiUDPSocket", "onUDPSocketError socketId:" + i2 + ", errMsg:" + str);
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiUDPSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("socketId", i2);
            jSONObject.put("errMsg", str);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ONUDPSOCKETERROR_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onUDPSocketListening(int i2) {
        MantoLog.d("JsApiUDPSocket", "onUDPSocketListening socketId:" + i2);
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiUDPSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("socketId", i2);
        } catch (JSONException unused) {
        }
        dispatchEvent(this.core, EVENT_ONUDPSOCKETLISTENING_NAME, jSONObject, this.mHashCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onUDPSocketMessage(int i2, byte[] bArr, RemoteInfo remoteInfo) {
        MantoLog.d("JsApiUDPSocket", "onUDPSocketMessage socketId:" + i2 + ", messagelen:" + bArr.length);
        MantoCore mantoCore = this.core;
        if (mantoCore == null || this.mHashCode == 0) {
            MantoLog.e("JsApiUDPSocket", mantoCore == null ? "Activity null" : "HashCode error");
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("socketId", i2);
            jSONObject.put("remoteInfo", remoteInfo.toJsonObject());
        } catch (JSONException unused) {
        }
        HashMap hashMap = new HashMap();
        hashMap.put("message", ByteBuffer.wrap(bArr));
        if (convertNativeBuffer(this.core, jSONObject, hashMap, true)) {
            dispatchEvent(this.core, EVENT_ONUDPSOCKETMESSAGE_NAME, jSONObject, this.mHashCode);
        }
    }

    public abstract void sendUDPSocketMessage(String str, int i2, int i3, String str2, String str3, MantoResultCallBack mantoResultCallBack);

    public abstract void sendUDPSocketMessage(String str, int i2, int i3, String str2, byte[] bArr, int i4, int i5, MantoResultCallBack mantoResultCallBack);
}
