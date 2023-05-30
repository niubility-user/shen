package com.jingdong.common.entity.cart;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NewEasyBuySubmitOrderInfo implements SubmitOrderProductInfo, Parcelable {
    public static final Parcelable.Creator<NewEasyBuySubmitOrderInfo> CREATOR = new Parcelable.Creator<NewEasyBuySubmitOrderInfo>() { // from class: com.jingdong.common.entity.cart.NewEasyBuySubmitOrderInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewEasyBuySubmitOrderInfo createFromParcel(Parcel parcel) {
            return new NewEasyBuySubmitOrderInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewEasyBuySubmitOrderInfo[] newArray(int i2) {
            return new NewEasyBuySubmitOrderInfo[i2];
        }
    };
    private static final String TAG = "NewEasyBuySubmitOrderIn";
    public String skuId;
    public int skuNumber;
    private SourceEntity sourceEntity;

    public NewEasyBuySubmitOrderInfo(String str, SourceEntity sourceEntity, int i2) {
        this.skuId = str;
        this.sourceEntity = sourceEntity;
        this.skuNumber = i2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public Bundle getAdditionalDataBundle() {
        return null;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public HashMap<CartPackSummary, ArrayList<CartSkuSummary>> getCheckedCartPackMap() {
        return null;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public ArrayList<CartPackSummary> getCheckedStatisticsPackList() {
        return new ArrayList<>();
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public ArrayList<CartSkuSummary> getCheckedStatisticsSkuList() {
        ArrayList<CartSkuSummary> arrayList = new ArrayList<>();
        arrayList.add(new CartSkuSummary(this.skuId, 1));
        return arrayList;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public int getCheckedWareNum() {
        return this.skuNumber;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public String getEasyBuySkuId() {
        return this.skuId;
    }

    public SourceEntity getSourceEntity() {
        return this.sourceEntity;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public String getUnJieSuan() {
        return null;
    }

    public void setSourceEntity(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public JSONObject toCheckedCartStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("Id", this.skuId);
            jSONObject2.put("num", this.skuNumber + "");
            jSONArray.put(jSONObject2);
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("Temp", " toCheckedCartStr -->> Exception ", e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.entity.cart.SubmitOrderProductInfo
    public JSONObject toCheckedStatisticsStr() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("Id", this.skuId);
            if (this.sourceEntity == null) {
                this.sourceEntity = new SourceEntity("unknown", null);
            }
            jSONObject2.put(CartConstant.KEY_SOURCE_TYPE, this.sourceEntity.getSourceType());
            jSONObject2.put(CartConstant.KEY_SOURCE_VALUE, this.sourceEntity.getSourceValue());
            jSONArray.put(jSONObject2);
            jSONObject.put(CartConstant.KEY_THE_SKUS, jSONArray);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.skuId);
        parcel.writeSerializable(this.sourceEntity);
        parcel.writeInt(this.skuNumber);
    }

    protected NewEasyBuySubmitOrderInfo(Parcel parcel) {
        this.skuId = parcel.readString();
        this.sourceEntity = (SourceEntity) parcel.readSerializable();
        this.skuNumber = parcel.readInt();
    }
}
