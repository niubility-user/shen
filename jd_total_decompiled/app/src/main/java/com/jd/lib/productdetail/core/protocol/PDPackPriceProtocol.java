package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.suitstyle.PDPackPriceEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDPackPriceProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "wareSuitPrice";

    public PDPackPriceProtocol(String str) {
        super(str);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put(CartConstant.KEY_YANBAO_SUITID, objArr[0]);
            jSONObject.put("skuStr", objArr[1]);
            jSONObject.put("mainSku", objArr[2]);
            jSONObject.put("skuId", objArr[3]);
            if (objArr.length >= 6 && objArr[4] != null && objArr[5] != null) {
                jSONObject.put("storeId", objArr[4]);
                jSONObject.put("business", objArr[5]);
            }
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDPackStyleProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        super.parseError(httpError);
        Log.d("yueliang", "yueliang");
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDPackPriceEntity pDPackPriceEntity = (PDPackPriceEntity) JDJSON.parseObject(str, PDPackPriceEntity.class);
        if (pDPackPriceEntity != null) {
            getEventBus().post(new PDApiEvent("detail_pack_price_key", pDPackPriceEntity.suitPrice, this.mEventKey));
        }
        return pDPackPriceEntity;
    }
}
