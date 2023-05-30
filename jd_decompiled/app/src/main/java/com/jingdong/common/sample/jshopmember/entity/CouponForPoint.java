package com.jingdong.common.sample.jshopmember.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class CouponForPoint implements Parcelable {
    public static final Parcelable.Creator<CouponForPoint> CREATOR = new Parcelable.Creator<CouponForPoint>() { // from class: com.jingdong.common.sample.jshopmember.entity.CouponForPoint.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CouponForPoint createFromParcel(Parcel parcel) {
            return new CouponForPoint(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CouponForPoint[] newArray(int i2) {
            return new CouponForPoint[i2];
        }
    };
    public long batchId;
    public String batchKey;
    public int condition;
    public int couponType;
    public String create;
    public int discount;
    public String expiryDate;
    public int period;
    public List<String> platFormDescList;
    public int points;
    public int remainingCount;
    public int sendCount;
    public int tradeCount;

    public CouponForPoint(JDJSONObject jDJSONObject) {
        this.platFormDescList = new ArrayList();
        if (jDJSONObject != null) {
            this.batchId = jDJSONObject.optInt(JshopConst.JSKEY_BATCH_ID);
            this.batchKey = jDJSONObject.optString("batchKey");
            this.condition = jDJSONObject.optInt("condition");
            this.couponType = jDJSONObject.optInt(AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE);
            this.create = jDJSONObject.optString("create");
            this.discount = jDJSONObject.optInt("discount");
            this.period = jDJSONObject.optInt("period");
            this.expiryDate = jDJSONObject.optString(Constant.KEY_EXPIRY_DATE);
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("platFormDesc");
            if (optJSONArray != null && optJSONArray.size() > 0) {
                for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                    String optString = optJSONArray.optString(i2);
                    if (!TextUtils.isEmpty(optString)) {
                        this.platFormDescList.add(optString);
                    }
                }
            }
            this.points = jDJSONObject.optInt("points");
            this.sendCount = jDJSONObject.optInt("sendCount");
            this.tradeCount = jDJSONObject.optInt("tradeCount");
            this.condition = jDJSONObject.optInt("condition");
            this.remainingCount = jDJSONObject.optInt("remainingCount");
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.batchId);
        parcel.writeString(this.batchKey);
        parcel.writeInt(this.condition);
        parcel.writeInt(this.couponType);
        parcel.writeString(this.create);
        parcel.writeInt(this.discount);
        parcel.writeInt(this.period);
        parcel.writeStringList(this.platFormDescList);
        parcel.writeInt(this.points);
        parcel.writeInt(this.sendCount);
        parcel.writeInt(this.tradeCount);
        parcel.writeInt(this.remainingCount);
        parcel.writeString(this.expiryDate);
    }

    protected CouponForPoint(Parcel parcel) {
        this.platFormDescList = new ArrayList();
        this.batchId = parcel.readLong();
        this.batchKey = parcel.readString();
        this.condition = parcel.readInt();
        this.couponType = parcel.readInt();
        this.create = parcel.readString();
        this.discount = parcel.readInt();
        this.period = parcel.readInt();
        this.platFormDescList = parcel.createStringArrayList();
        this.points = parcel.readInt();
        this.sendCount = parcel.readInt();
        this.tradeCount = parcel.readInt();
        this.remainingCount = parcel.readInt();
        this.expiryDate = parcel.readString();
    }
}
