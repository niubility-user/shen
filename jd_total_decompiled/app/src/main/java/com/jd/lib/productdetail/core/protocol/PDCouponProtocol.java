package com.jd.lib.productdetail.core.protocol;

import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponBaseEntity;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponCellEntity;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponMapEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDCouponProtocol extends PDBaseProtocol {
    public int effect;
    public boolean isNotifyUser;

    public PDCouponProtocol(String str) {
        super(str);
        this.isNotifyUser = false;
        this.effect = 0;
    }

    private List<PDCouponCellEntity> createCouponLayerData(List<PDCouponCellEntity> list, boolean z) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (PDCouponCellEntity pDCouponCellEntity : list) {
            if (pDCouponCellEntity != null) {
                if (pDCouponCellEntity.applicability) {
                    arrayList2.add(pDCouponCellEntity);
                } else {
                    arrayList3.add(pDCouponCellEntity);
                }
                pDCouponCellEntity.itemType = 2;
            }
        }
        if (!arrayList2.isEmpty()) {
            PDCouponCellEntity pDCouponCellEntity2 = new PDCouponCellEntity();
            pDCouponCellEntity2.itemType = 0;
            pDCouponCellEntity2.hasFinanceCoupon = z;
            arrayList2.add(0, pDCouponCellEntity2);
            arrayList.addAll(arrayList2);
        } else {
            PDCouponCellEntity pDCouponCellEntity3 = new PDCouponCellEntity();
            pDCouponCellEntity3.itemType = 3;
            pDCouponCellEntity3.hasFinanceCoupon = z;
            arrayList.add(0, pDCouponCellEntity3);
        }
        if (!arrayList3.isEmpty()) {
            PDCouponCellEntity pDCouponCellEntity4 = new PDCouponCellEntity();
            pDCouponCellEntity4.itemType = 1;
            arrayList3.add(0, pDCouponCellEntity4);
            arrayList.addAll(arrayList3);
        }
        return arrayList;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected int getEffect() {
        return this.effect;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return "coupon";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("skuId", objArr[0]);
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDCouponProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return this.isNotifyUser;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        getEventBus().post(new PDApiEvent("detail_coupon_list_key", null, this.mEventKey));
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDCouponMapEntity pDCouponMapEntity;
        List<PDCouponCellEntity> list;
        PDCouponBaseEntity pDCouponBaseEntity = (PDCouponBaseEntity) JDJSON.parseObject(str, PDCouponBaseEntity.class);
        if (pDCouponBaseEntity != null && (pDCouponMapEntity = pDCouponBaseEntity.resMap) != null && (list = pDCouponMapEntity.couponInfo) != null && !list.isEmpty()) {
            PDCouponMapEntity pDCouponMapEntity2 = pDCouponBaseEntity.resMap;
            getEventBus().post(new PDApiEvent("detail_coupon_list_key", createCouponLayerData(pDCouponMapEntity2.couponInfo, pDCouponMapEntity2.hasFinanceCoupon), this.mEventKey));
        } else {
            getEventBus().post(new PDApiEvent("detail_coupon_list_key", null, this.mEventKey));
        }
        return pDCouponBaseEntity;
    }
}
