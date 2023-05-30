package com.jingdong.app.mall.bundle.styleinfoview.protocol;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.prescription.PDPrescriptionListCount;
import com.jingdong.app.mall.bundle.styleinfoview.events.PDApiEvent;
import com.jingdong.common.controller.SubShoppingBaseController;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PDPrescriptionAddCartProtocol extends PDBaseProtocol {
    public static final String FOUNCTION_ADD_CART = "rxAddCart";
    public static final String FOUNCTION_CART_NUM = "rxCartNum";
    public static final int SKU_TYPE_1 = 1;
    public static final int SKU_TYPE_4 = 4;
    private int drugsType;
    private String founctionId;

    public PDPrescriptionAddCartProtocol(String str, String str2, int i2) {
        super(str);
        this.founctionId = "rxCartNum";
        this.founctionId = str2;
        this.drugsType = i2;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return this.founctionId;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected String getHost() {
        return Configuration.getWareHost();
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            if (TextUtils.equals(this.founctionId, "rxAddCart")) {
                if (objArr[0] != null) {
                    jSONObject.put("sku", objArr[0]);
                }
                if (objArr[1] != null) {
                    jSONObject.put("num", objArr[1]);
                }
                if (objArr[2] != null) {
                    jSONObject.put("stype", objArr[2]);
                }
            }
            jSONObject.put("type", this.drugsType);
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDPrescriptionAddCartProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return !TextUtils.equals(this.founctionId, "rxCartNum");
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected boolean isPost() {
        return true;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDPrescriptionListCount pDPrescriptionListCount = (PDPrescriptionListCount) JDJSON.parseObject(str, PDPrescriptionListCount.class);
        pDPrescriptionListCount.functionID = this.founctionId;
        if (TextUtils.equals(pDPrescriptionListCount.code, "0")) {
            getEventBus().post(new PDApiEvent("get_prescription_count_key", pDPrescriptionListCount, this.mEventKey));
            SubShoppingBaseController.updateDrugListCartNum();
        }
        return pDPrescriptionListCount;
    }
}
