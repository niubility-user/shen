package com.jingdong.app.mall.bundle.styleinfoview.yuyue;

import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewYuYueListener;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDIsAppointEntity;
import com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PDIsAppointProtocol extends PDBaseProtocol {
    private static final String FUNCTION_ID = "isAppoint";
    private boolean hasSr;
    public boolean isNowBuy;
    private LibPdStyleInfoViewYuYueListener viewYuYueListener;

    public PDIsAppointProtocol(String str) {
        super(str);
        this.hasSr = false;
        this.isNowBuy = false;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return FUNCTION_ID;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            jSONObject.put("skuId", objArr[0]);
            if (objArr[1] != null) {
                jSONObject.put("sr", objArr[1]);
                this.hasSr = true;
            } else {
                this.hasSr = false;
            }
            if (objArr.length > 2) {
                jSONObject.put("isShowCode", objArr[2]);
            }
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDIsAppointProtocol", "JSONException -->> ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDIsAppointEntity pDIsAppointEntity = (PDIsAppointEntity) JDJSON.parseObject(str, PDIsAppointEntity.class);
        if (pDIsAppointEntity != null) {
            pDIsAppointEntity.isNowbuy = this.isNowBuy;
            LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener = this.viewYuYueListener;
            if (libPdStyleInfoViewYuYueListener != null) {
                libPdStyleInfoViewYuYueListener.onIsAppointGetted(this.hasSr, pDIsAppointEntity);
            }
        }
        return pDIsAppointEntity;
    }

    public void setViewYuYueListener(LibPdStyleInfoViewYuYueListener libPdStyleInfoViewYuYueListener) {
        this.viewYuYueListener = libPdStyleInfoViewYuYueListener;
    }
}
