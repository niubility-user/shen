package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.coupon.PDHongBaoReceiveEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDHongBaoReceiveProtocol extends PDBaseProtocol {
    private JSONObject mRequest;

    public PDHongBaoReceiveProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "vvipclub_hongbao_receive";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getHost() {
        return Configuration.getJshopHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            JSONObject jSONObject2 = this.mRequest;
            if (jSONObject2 != null) {
                jSONObject2.put("channel", "productDetail");
                return this.mRequest;
            }
            return jSONObject;
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDHongBaoReceiveProtocol", "JSONException -->> ", e2);
                return jSONObject;
            }
            return jSONObject;
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        getEventBus().post(new PDApiEvent("detail_hongbao_receive_error_key", httpError.getMessage(), this.mEventKey));
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDHongBaoReceiveEntity pDHongBaoReceiveEntity = (PDHongBaoReceiveEntity) JDJSON.parseObject(str, PDHongBaoReceiveEntity.class);
        if (pDHongBaoReceiveEntity != null) {
            getEventBus().post(new PDApiEvent("detail_hongbao_receive_key", pDHongBaoReceiveEntity, this.mEventKey));
        }
        return pDHongBaoReceiveEntity;
    }

    public void setJSONObject(JSONObject jSONObject) {
        this.mRequest = jSONObject;
    }
}
