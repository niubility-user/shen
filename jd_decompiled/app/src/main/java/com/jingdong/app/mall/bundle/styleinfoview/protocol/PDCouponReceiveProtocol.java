package com.jingdong.app.mall.bundle.styleinfoview.protocol;

import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.styleinfoview.callback.listener.LibPdStyleInfoViewCouponReceiveListener;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon.PDCouponReceiveEntity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class PDCouponReceiveProtocol extends PDBaseProtocol {
    public static final String PARAMS_KEY_BATCH_RECEIVE = "isBatchReceive";
    public static final String PARAMS_KEY_IS_JINGXI = "isJingxiReceive";
    public static final String PARAMS_KEY_IS_N = "isN";
    private static final int PARAMS_LENGTH = 3;
    private int curPosition;
    private int mBatch;
    private LibPdStyleInfoViewCouponReceiveListener mCouponReceiveListener;
    private boolean mIsN;
    private JSONObject mRequest;
    public String mRequestId;
    public String mRequestTag;

    public PDCouponReceiveProtocol(int i2, String str, LibPdStyleInfoViewCouponReceiveListener libPdStyleInfoViewCouponReceiveListener) {
        super(str);
        this.mIsN = false;
        this.mBatch = 0;
        this.mRequestId = UUID.randomUUID().toString();
        this.mRequestTag = "";
        this.curPosition = i2;
        this.mCouponReceiveListener = libPdStyleInfoViewCouponReceiveListener;
    }

    private void handleParams() {
        this.mIsN = this.mRequest.optBoolean("isN", false);
        this.mBatch = this.mRequest.optInt("isBatchReceive", 0);
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return this.mIsN ? "receiveNcoupon" : "nReceiveCoupon";
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected String getHost() {
        return Configuration.getJshopHost();
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected JSONObject getRequestParam(JSONObject jSONObject, Object[] objArr) {
        try {
            JSONObject jSONObject2 = this.mRequest;
            if (jSONObject2 != null) {
                jSONObject2.put("source", "2");
                return this.mRequest;
            }
            return jSONObject;
        } catch (JSONException e2) {
            if (Log.D) {
                Log.d("PDCouponReceiveProtocol", "JSONException -->> ", e2);
                return jSONObject;
            }
            return jSONObject;
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return true;
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected void parseError(HttpError httpError) {
        PDCouponReceiveEntity pDCouponReceiveEntity = new PDCouponReceiveEntity();
        pDCouponReceiveEntity.msg = httpError.getMessage();
        pDCouponReceiveEntity.position = this.curPosition;
        pDCouponReceiveEntity.mRequestId = this.mRequestId;
        pDCouponReceiveEntity.mRequestTag = this.mRequestTag;
        if (this.mCouponReceiveListener != null) {
            if (Log.D) {
                Log.d("infos", "===========parseError=============");
            }
            this.mCouponReceiveListener.onParseError(pDCouponReceiveEntity, this.mEventKey);
        }
    }

    @Override // com.jingdong.app.mall.bundle.styleinfoview.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDCouponReceiveEntity pDCouponReceiveEntity = (PDCouponReceiveEntity) JDJSON.parseObject(str, PDCouponReceiveEntity.class);
        if (pDCouponReceiveEntity != null) {
            pDCouponReceiveEntity.position = this.curPosition;
            pDCouponReceiveEntity.mRequestId = this.mRequestId;
            pDCouponReceiveEntity.mRequestTag = this.mRequestTag;
            if (this.mCouponReceiveListener != null) {
                if (Log.D) {
                    Log.d("infos", "===========parseResponse=============");
                }
                this.mCouponReceiveListener.onParseResponse(pDCouponReceiveEntity, this.mEventKey);
            }
        }
        return pDCouponReceiveEntity;
    }

    public void setJSONObject(JSONObject jSONObject) {
        this.mRequest = jSONObject;
        handleParams();
    }
}
