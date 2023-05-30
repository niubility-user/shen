package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.PDSkuNoticeEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import de.greenrobot.event.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDSkuNoticeProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "skuNotice";

    public PDSkuNoticeProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("wareId", objArr[0]);
            jSONObject.put("type", objArr[1]);
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDSkuNoticeProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDSkuNoticeEntity pDSkuNoticeEntity = (PDSkuNoticeEntity) JDJSON.parseObject(str, PDSkuNoticeEntity.class);
        if (pDSkuNoticeEntity != null) {
            EventBus.getDefault().post(new PDApiEvent("detail_skunotice_key", pDSkuNoticeEntity, this.mEventKey));
        }
        return pDSkuNoticeEntity;
    }
}
