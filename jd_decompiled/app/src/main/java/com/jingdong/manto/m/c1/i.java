package com.jingdong.manto.m.c1;

import android.text.TextUtils;
import android.util.Pair;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.u;
import java.nio.ByteBuffer;
import okhttp3.WebSocket;
import okio.ByteString;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i extends d0 {
    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        MantoLog.i("operateSocketTask", "JsApiOperateSocketTask ");
        m b = l.c().b(hVar.c());
        if (b == null) {
            hVar.a(i2, putErrMsg("fail:no webSocket task", null, str));
            MantoLog.w("operateSocketTask", "request is null");
            return;
        }
        String optString = jSONObject.optString("socketTaskId");
        Pair<WebSocket, Boolean> a = b.a(optString);
        if (a == null || a.first == null) {
            hVar.a(i2, putErrMsg("fail:no webSocket task", null, str));
        } else if (jSONObject == null) {
            hVar.a(i2, putErrMsg("fail:data is null", null, str));
            MantoLog.e("operateSocketTask", "exec: data is null ");
        } else if (TextUtils.isEmpty(optString)) {
            MantoLog.e("operateSocketTask", "exec: taskId is null");
            hVar.a(i2, putErrMsg("fail:taskId is null or nil", null, str));
        } else {
            String optString2 = jSONObject.optString(CartConstant.KEY_OPERATIONTYPE);
            if (TextUtils.isEmpty(optString2)) {
                MantoLog.e("operateSocketTask", "exec: operationType is null");
                hVar.a(i2, putErrMsg("fail:operationType is null or nil", null, str));
                return;
            }
            if (optString2.equals("close")) {
                int optInt = jSONObject.optInt("code", 1000);
                if (optInt == 1000 || (optInt >= 3000 && optInt < 5000)) {
                    String optString3 = jSONObject.optString("reason", "");
                    try {
                        MantoLog.i("operateSocketTask", "exec: try to close socket");
                        ((WebSocket) a.first).close(optInt, optString3);
                    } catch (Exception e2) {
                        MantoLog.e("operateSocketTask", "exec: close error" + e2.toString());
                    }
                    hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
                    MantoLog.d("operateSocketTask", String.format("closeSocket code %d JsApiCreateSocketTask, reason %s", Integer.valueOf(optInt), optString3));
                    return;
                }
                str2 = "fail:The code must be either 1000, or between 3000 and 4999";
            } else if (optString2.equals("send")) {
                boolean booleanValue = ((Boolean) a.second).booleanValue();
                if (!booleanValue) {
                    hVar.a(i2, putErrMsg("fail:webSocket:" + optString + " not connected.", null, str));
                    return;
                } else if (!booleanValue) {
                    hVar.a(i2, putErrMsg("fail", null, str));
                    MantoLog.w("operateSocketTask", "send fail taskId: " + optString);
                    return;
                } else {
                    if (u.a(jSONObject)) {
                        u.a(hVar, jSONObject, this);
                    }
                    Object opt = jSONObject.opt("data");
                    if (opt == null) {
                        hVar.a(i2, putErrMsg("fail:message is null or nil", null, str));
                        MantoLog.w("operateSocketTask", "sendSocketMessage fail:" + opt);
                        return;
                    }
                    try {
                        if (opt instanceof ByteBuffer) {
                            byte[] bArr = new byte[((ByteBuffer) opt).remaining()];
                            ((ByteBuffer) opt).get(bArr);
                            MantoLog.i("operateSocketTask", "sendSocketMessage ok message:" + opt);
                            ((WebSocket) a.first).send(ByteString.of(bArr));
                        } else if (!(opt instanceof String)) {
                            MantoLog.w("operateSocketTask", "sendSocketMessage error message type wrong");
                            hVar.a(i2, putErrMsg("fail:unknown data", null, str));
                            return;
                        } else {
                            MantoLog.i("operateSocketTask", "sendSocketMessage ok message : " + opt);
                            ((WebSocket) a.first).send(String.valueOf(opt));
                        }
                        hVar.a(i2, putErrMsg(IMantoBaseModule.SUCCESS, null, str));
                        return;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        hVar.a(i2, putErrMsg("fail:" + e3.getMessage(), null, str));
                        return;
                    }
                }
            } else {
                str2 = "fail:unknown operationType";
            }
            hVar.a(i2, putErrMsg(str2, null, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "operateSocketTask";
    }
}
