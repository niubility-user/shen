package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponReceiveEntity;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class PDCouponReceiveProtocol extends PDBaseProtocol {
    public static final String PARAMS_KEY_BATCH_RECEIVE = "isBatchReceive";
    public static final String PARAMS_KEY_IS_JINGXI = "isJingxiReceive";
    public static final String PARAMS_KEY_IS_N = "isN";
    private static final int PARAMS_LENGTH = 3;
    public String couponReceiveSource;
    private int curPosition;
    private int mBatch;
    private boolean mIsN;
    private ProductDetailEntity mProduct;
    private JSONObject mRequest;
    public String mRequestId;
    public String mRequestTag;
    public int mSkuSource;
    private MutableLiveData<PDCouponReceiveEntity> retData;

    public PDCouponReceiveProtocol(int i2, String str, ProductDetailEntity productDetailEntity) {
        super(str);
        this.mIsN = false;
        this.mBatch = 0;
        this.mRequestId = UUID.randomUUID().toString();
        this.mRequestTag = "";
        this.curPosition = i2;
        this.mProduct = productDetailEntity;
    }

    private void handleParams() {
        this.mIsN = this.mRequest.optBoolean("isN", false);
        this.mBatch = this.mRequest.optInt("isBatchReceive", 0);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected String getFunctionId() {
        return this.mIsN ? "receiveNcoupon" : "nReceiveCoupon";
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
                jSONObject2.put("source", "2");
                if (!TextUtils.isEmpty(this.couponReceiveSource)) {
                    this.mRequest.put("couponReceiveSource ", this.couponReceiveSource);
                }
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

    public MutableLiveData<PDCouponReceiveEntity> getRetData() {
        return this.retData;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected boolean isNotifyUser() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    public void parseError(HttpError httpError) {
        PDCouponReceiveEntity pDCouponReceiveEntity = new PDCouponReceiveEntity();
        pDCouponReceiveEntity.code = "-1";
        pDCouponReceiveEntity.msg = httpError.getMessage();
        pDCouponReceiveEntity.position = this.curPosition;
        pDCouponReceiveEntity.mRequestId = this.mRequestId;
        pDCouponReceiveEntity.mRequestTag = this.mRequestTag;
        pDCouponReceiveEntity.skuSource = this.mSkuSource;
        getEventBus().post(new PDApiEvent("detail_coupon_receive_error_key", pDCouponReceiveEntity, this.mEventKey));
        MutableLiveData<PDCouponReceiveEntity> mutableLiveData = this.retData;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(pDCouponReceiveEntity);
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDCouponReceiveEntity pDCouponReceiveEntity = (PDCouponReceiveEntity) JDJSON.parseObject(str, PDCouponReceiveEntity.class);
        if (pDCouponReceiveEntity != null) {
            pDCouponReceiveEntity.position = this.curPosition;
            pDCouponReceiveEntity.mRequestId = this.mRequestId;
            pDCouponReceiveEntity.mRequestTag = this.mRequestTag;
            pDCouponReceiveEntity.skuSource = this.mSkuSource;
            getEventBus().post(new PDApiEvent("detail_coupon_receive_key", pDCouponReceiveEntity, this.mEventKey));
            MutableLiveData<PDCouponReceiveEntity> mutableLiveData = this.retData;
            if (mutableLiveData != null) {
                mutableLiveData.postValue(pDCouponReceiveEntity);
            }
        }
        return pDCouponReceiveEntity;
    }

    public void setJSONObject(JSONObject jSONObject) {
        this.mRequest = jSONObject;
        handleParams();
    }

    public void setRetData(MutableLiveData<PDCouponReceiveEntity> mutableLiveData) {
        this.retData = mutableLiveData;
    }
}
