package com.jingdong.common.recommend.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.anotation.JSONField;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes.dex */
public class RecommendCoupon implements Parcelable {
    public static final Parcelable.Creator<RecommendCoupon> CREATOR = new Parcelable.Creator<RecommendCoupon>() { // from class: com.jingdong.common.recommend.entity.RecommendCoupon.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendCoupon createFromParcel(Parcel parcel) {
            return new RecommendCoupon(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendCoupon[] newArray(int i2) {
            return new RecommendCoupon[i2];
        }
    };
    private static final String TAG = "RecommendCoupon";
    @JSONField(name = "wareId")
    public String couponId;
    public String couponKey;
    public String couponRoleId;
    @JSONField(name = "couponSortType")
    public int couponSort;
    @JSONField(name = "couponDenomination")
    public String denomination;
    @JSONField(name = "couponEffectEnd")
    public String endTime;
    public boolean isReceived;
    @JSONField(name = "couponLimit")
    public String limitStr;
    @JSONField(name = "couponQuota")
    public String quota;
    @JSONField(name = "couponReceiveUrl")
    public String receiveUrl;
    @JSONField(name = "couponShopId")
    public String shopId;
    public String sourceValue;
    @JSONField(name = "couponEffectStart")
    public String startTime;
    @JSONField(name = "client_click_url")
    public String targetUrl;
    @JSONField(name = AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE)
    public String type;

    public RecommendCoupon(Parcel parcel) {
        readToParcel(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getDenomination() {
        try {
            return ((int) Double.parseDouble(this.denomination)) + "";
        } catch (Exception e2) {
            e2.printStackTrace();
            OKLog.e(TAG, e2);
            return "";
        }
    }

    public void readToParcel(Parcel parcel) {
        this.couponId = parcel.readString();
        this.type = parcel.readString();
        this.receiveUrl = parcel.readString();
        this.denomination = parcel.readString();
        this.limitStr = parcel.readString();
        this.quota = parcel.readString();
        this.startTime = parcel.readString();
        this.endTime = parcel.readString();
        this.couponSort = parcel.readInt();
        this.sourceValue = parcel.readString();
        this.targetUrl = parcel.readString();
        this.couponKey = parcel.readString();
        this.couponRoleId = parcel.readString();
        this.shopId = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.couponId);
        parcel.writeString(this.type);
        parcel.writeString(this.receiveUrl);
        parcel.writeString(this.denomination);
        parcel.writeString(this.limitStr);
        parcel.writeString(this.quota);
        parcel.writeString(this.startTime);
        parcel.writeString(this.endTime);
        parcel.writeInt(this.couponSort);
        parcel.writeString(this.sourceValue);
        parcel.writeString(this.targetUrl);
        parcel.writeString(this.couponKey);
        parcel.writeString(this.couponRoleId);
        parcel.writeString(this.shopId);
    }

    public RecommendCoupon() {
    }

    public RecommendCoupon(JSONObjectProxy jSONObjectProxy) {
        this.couponId = jSONObjectProxy.optString("wareId");
        this.type = jSONObjectProxy.optString(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE);
        this.limitStr = jSONObjectProxy.optString("couponLimit");
        this.receiveUrl = jSONObjectProxy.optString("couponReceiveUrl");
        this.couponKey = jSONObjectProxy.optString("couponKey");
        this.couponRoleId = jSONObjectProxy.optString("couponRoleId");
        this.denomination = jSONObjectProxy.optString("couponDenomination");
        this.quota = jSONObjectProxy.optString("couponQuota");
        this.startTime = jSONObjectProxy.optString("couponEffectStart");
        this.endTime = jSONObjectProxy.optString("couponEffectEnd");
        this.couponSort = jSONObjectProxy.optInt("couponSortType");
        this.sourceValue = jSONObjectProxy.optString("sourceValue", "");
        this.targetUrl = jSONObjectProxy.optString("client_click_url");
        this.shopId = jSONObjectProxy.optString("couponShopId");
    }
}
