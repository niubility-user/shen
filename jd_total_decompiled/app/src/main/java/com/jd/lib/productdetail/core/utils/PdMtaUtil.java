package com.jd.lib.productdetail.core.utils;

import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdma.minterface.BaseEvent;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class PdMtaUtil {
    public static final String PARAM_KEY_SKUID = "skuid";
    public static final String PARAM_KEY_TABNAME = "page";
    private final String mEventId;
    private final String mEventParam;
    private boolean mIsQuick;
    private final String mJsonParam;
    private final String mNextPageName;
    private final String mOrderId;
    private final String mPageId;
    private final String mPageName;
    private final String mPageParam;
    private final String mShopId;
    private final String mSku;
    private final String mSkuTag;

    /* loaded from: classes15.dex */
    public static final class Builder {
        private String mEventId;
        private String mEventParam;
        private boolean mIsQuick;
        private JsonBuiler mJsonParam;
        private String mNextPageName;
        private String mOrderId;
        private String mPageId;
        private String mPageParam;
        private String mShopId;
        private String mSku;
        private String mSkuTag;
        private String mPageName = "com.jd.lib.productdetailfeeds.PdFeedsActivity";
        private String mJsonParamStr = "";

        private Builder() {
        }

        public static Builder newBuiler() {
            return new Builder();
        }

        public PdMtaUtil build() {
            String pageId = PdMtaUtil.getPageId(this.mPageName);
            if (TextUtils.isEmpty(pageId)) {
                pageId = this.mPageId;
            }
            String str = pageId;
            JsonBuiler jsonBuiler = this.mJsonParam;
            String jsonBuiler2 = jsonBuiler != null ? jsonBuiler.toString() : "";
            if (TextUtils.isEmpty(jsonBuiler2)) {
                jsonBuiler2 = this.mJsonParamStr;
            }
            return new PdMtaUtil(this.mEventId, this.mEventParam, str, this.mPageName, this.mPageParam, this.mNextPageName, jsonBuiler2, this.mShopId, this.mOrderId, this.mSkuTag, this.mSku, this.mIsQuick);
        }

        public Builder setEventId(String str) {
            this.mEventId = str;
            return this;
        }

        public Builder setEventParam(String str) {
            this.mEventParam = str;
            return this;
        }

        public Builder setIsQuick(boolean z) {
            this.mIsQuick = z;
            return this;
        }

        public Builder setJsonParam(JsonBuiler jsonBuiler) {
            this.mJsonParam = jsonBuiler;
            return this;
        }

        public Builder setNextPageName(String str) {
            this.mNextPageName = str;
            return this;
        }

        public Builder setOrderId(String str) {
            this.mOrderId = str;
            return this;
        }

        public Builder setPageId(String str) {
            this.mPageId = str;
            return this;
        }

        public Builder setPageName(String str) {
            this.mPageName = str;
            return this;
        }

        public Builder setPageParam(ProductDetailEntity productDetailEntity) {
            if (productDetailEntity != null) {
                this.mPageParam = productDetailEntity.skuId;
            }
            return this;
        }

        public Builder setShopId(String str) {
            this.mShopId = str;
            return this;
        }

        public Builder setSku(String str) {
            this.mSku = str;
            return this;
        }

        public Builder setSkuTag(String str) {
            this.mSkuTag = str;
            return this;
        }

        public Builder setJsonParam(String str) {
            this.mJsonParamStr = str;
            return this;
        }

        public Builder setPageParam(String str) {
            this.mPageParam = str;
            return this;
        }
    }

    /* loaded from: classes15.dex */
    public static final class JsonBuiler {
        JDJSONObject jdjsonObject = new JDJSONObject();

        public JsonBuiler put(String str, Object obj) {
            this.jdjsonObject.put(str, obj);
            return this;
        }

        public String toString() {
            return this.jdjsonObject.toJSONString();
        }

        public JsonBuiler put(Map<? extends String, ? extends Object> map) {
            this.jdjsonObject.putAll(map);
            return this;
        }
    }

    public static String getPageId(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (TextUtils.equals(str, "com.jd.lib.productdetailfeeds.PdFeedsActivity")) {
            return RecommendMtaUtils.ProductdetailFeeds_MainPage;
        }
        if (TextUtils.equals(str, "com.jd.lib.productdetail.ProductDetailActivity")) {
            return RecommendMtaUtils.Productdetail_MainPage;
        }
        return null;
    }

    public static JsonBuiler newJsonBuiler() {
        return new JsonBuiler();
    }

    public void click() {
        HashMap hashMap;
        Log.d("PdMtaUtil", "click" + this);
        if (this.mIsQuick) {
            hashMap = new HashMap();
            hashMap.put(BaseEvent.SCENE, "quick");
        } else {
            hashMap = null;
        }
        JDMtaUtils.sendCommonData4ProductDetail(JdSdk.getInstance().getApplication().getApplicationContext(), this.mEventId, this.mEventParam, "onClick", this.mPageId, this.mPageName, this.mPageParam, this.mSkuTag, this.mShopId, null, null, null, hashMap);
    }

    public void clickJson() {
        if (Log.D) {
            Log.d("PdMtaUtil", "click" + this);
        }
        HashMap hashMap = null;
        if (this.mIsQuick) {
            hashMap = new HashMap();
            hashMap.put(BaseEvent.SCENE, "quick");
        }
        JDMtaUtils.sendClickDataWithExt(JdSdk.getInstance().getApplication().getApplicationContext(), this.mEventId, this.mEventParam, "onClick", this.mPageId, this.mPageName, this.mPageParam, this.mNextPageName, this.mJsonParam, this.mShopId, this.mOrderId, this.mSku, this.mSkuTag, null, hashMap);
    }

    public void exposure() {
        if (Log.D) {
            Log.d("PdMtaUtil", "exposure" + this);
        }
        if (this.mIsQuick) {
            HashMap hashMap = new HashMap();
            hashMap.put(BaseEvent.SCENE, "quick");
            JDMtaUtils.sendExposureExtend(JdSdk.getInstance().getApplicationContext(), this.mEventId, this.mEventParam, this.mPageId, this.mPageName, this.mPageParam, this.mJsonParam, this.mShopId, this.mOrderId, this.mSkuTag, null, hashMap);
            return;
        }
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplicationContext(), this.mEventId, this.mEventParam, this.mPageId, this.mPageName, this.mPageParam, this.mJsonParam, this.mShopId, this.mOrderId, this.mSkuTag, null);
    }

    public String toString() {
        return "PdMtaUtil{mEventId='" + this.mEventId + "', mEventParam='" + this.mEventParam + "', mPageId='" + this.mPageId + "', mPageName='" + this.mPageName + "', mPageParam='" + this.mPageParam + "', mNextPageName='" + this.mNextPageName + "', mJsonParam='" + this.mJsonParam + "', mShopId='" + this.mShopId + "', mOrderId='" + this.mOrderId + "', mSkuTag='" + this.mSkuTag + "'}";
    }

    private PdMtaUtil(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, boolean z) {
        this.mEventId = str;
        this.mEventParam = str2;
        this.mPageId = str3;
        this.mPageName = str4;
        this.mPageParam = str5;
        this.mNextPageName = str6;
        this.mJsonParam = str7;
        this.mShopId = str8;
        this.mOrderId = str9;
        this.mSkuTag = str10;
        this.mSku = str11;
        this.mIsQuick = z;
    }
}
