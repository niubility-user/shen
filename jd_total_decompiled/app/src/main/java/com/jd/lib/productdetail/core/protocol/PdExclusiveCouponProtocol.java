package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponReceiveEntity;
import com.jd.lib.productdetail.core.entitys.coupon.PdExclusiveCouponEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PdExclusiveCouponProtocol extends PDBaseProtocol {
    private int curPosition;
    public boolean isPlatformCoupon;

    public PdExclusiveCouponProtocol(int i2, String str, boolean z) {
        super(str);
        this.curPosition = i2;
        this.isPlatformCoupon = z;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "bindCoupon";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getHost() {
        return Configuration.getPersonalHost();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("couponId", objArr[0]);
            jSONObject.put(JshopConst.JSKEY_BATCH_ID, objArr[1]);
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDCouponReceiveProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        PDCouponReceiveEntity pDCouponReceiveEntity = new PDCouponReceiveEntity();
        pDCouponReceiveEntity.msg = httpError.getMessage();
        getEventBus().post(new PDApiEvent("detail_coupon_receive_error_key", pDCouponReceiveEntity, this.mEventKey));
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PdExclusiveCouponEntity pdExclusiveCouponEntity = (PdExclusiveCouponEntity) JDJSON.parseObject(str, PdExclusiveCouponEntity.class);
        if (pdExclusiveCouponEntity != null) {
            getEventBus().post(new PDApiEvent("detail_exclusive_coupon_receive_key", pdExclusiveCouponEntity, this.mEventKey));
        }
        return pdExclusiveCouponEntity;
    }
}
