package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.djcart.PDDJSelfDeliveryCartEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDDJGetCartNumProtocol extends PDBaseProtocol {
    public PDDJGetCartNumProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "o2o_trade_xsg_cart";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getHost() {
        return Configuration.getWareHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            if (objArr[0] != null) {
                jSONObject.put("storeId", objArr[0]);
            }
            if (objArr[1] != null) {
                jSONObject.put("detailFlag", objArr[1]);
            }
            if (!LoginUserBase.hasLogin()) {
                jSONObject.put("randomUUId", UUID.randomUUID().toString());
            }
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDDJGetCartNumProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return false;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isPost() {
        return true;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        try {
            PDDJSelfDeliveryCartEntity pDDJSelfDeliveryCartEntity = (PDDJSelfDeliveryCartEntity) JDJSON.parseObject(str, PDDJSelfDeliveryCartEntity.class);
            if (TextUtils.equals(pDDJSelfDeliveryCartEntity.code, "0")) {
                getEventBus().post(new PDApiEvent(PDApiEvent.PD_DJ_SELF_SELIVERY_CARTNUM, pDDJSelfDeliveryCartEntity, this.mEventKey));
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return str;
    }
}
