package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.suitstyle.PDPackStyleEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDPackStyleProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "miniSkuDetail";
    private final boolean isCar;
    private boolean isDpg;

    public PDPackStyleProtocol(String str, boolean z) {
        super(str);
        this.isCar = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public long getCacheTime() {
        if (this.isCar) {
            return 60000L;
        }
        return super.getCacheTime();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "miniSkuDetail";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("skuId", objArr[0]);
            jSONObject.put(CartConstant.KEY_CART_MINI_NEEDATTR, objArr[1]);
            jSONObject.put("requestSource", objArr[2]);
            jSONObject.put("mainSku", objArr[3]);
            jSONObject.put("carModelId", objArr[4]);
            if (objArr.length > 5) {
                jSONObject.put("needCarGift", objArr[5]);
                if (objArr[6] != null) {
                    jSONObject.put("jdCarIds", objArr[6]);
                }
            }
            if (objArr.length > 7 && objArr[7] != null) {
                jSONObject.put("storeId", objArr[7]);
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
    public boolean isCache() {
        return this.isCar || super.isCache();
    }

    public void isDpg(boolean z) {
        this.isDpg = z;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDPackStyleEntity pDPackStyleEntity = (PDPackStyleEntity) JDJSON.parseObject(str, PDPackStyleEntity.class);
        if (pDPackStyleEntity != null) {
            if (this.isDpg) {
                getEventBus().post(new PDApiEvent(PDApiEvent.DETAIL_DPG_STYLE_KEY, pDPackStyleEntity.wareInfo, this.mEventKey));
            } else {
                getEventBus().post(new PDApiEvent("detail_pack_style_key", pDPackStyleEntity.wareInfo, this.mEventKey));
            }
        }
        return pDPackStyleEntity;
    }
}
