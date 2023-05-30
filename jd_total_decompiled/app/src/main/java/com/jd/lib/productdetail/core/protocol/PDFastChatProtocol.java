package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.wareindex.PDFastChatData;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDFastChatProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "asynInteface";

    public PDFastChatProtocol(String str) {
        super(str);
    }

    private Map<String, Object> listKeyMaps(String str) {
        HashMap hashMap = null;
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap hashMap2 = new HashMap();
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    Object obj2 = jSONObject.get(obj);
                    if (obj2 == null) {
                        obj2 = "";
                    }
                    hashMap2.put(obj, obj2);
                }
                return hashMap2;
            } catch (Exception unused) {
                hashMap = hashMap2;
                return hashMap;
            }
        } catch (Exception unused2) {
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        Map<String, Object> listKeyMaps;
        if ((objArr[0] instanceof String) && (listKeyMaps = listKeyMaps((String) objArr[0])) != null && !listKeyMaps.isEmpty()) {
            for (String str : listKeyMaps.keySet()) {
                try {
                    jSONObject.put(str, listKeyMaps.get(str));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        try {
            getEventBus().post(new PDApiEvent("detail_fast_chat_close_key", (PDFastChatData) JDJSON.parseObject(str, PDFastChatData.class), this.mEventKey));
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return str;
    }
}
