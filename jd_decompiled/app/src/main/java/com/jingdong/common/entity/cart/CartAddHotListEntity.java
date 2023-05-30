package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class CartAddHotListEntity implements Parcelable {
    public static final Parcelable.Creator<CartAddHotListEntity> CREATOR = new Parcelable.Creator<CartAddHotListEntity>() { // from class: com.jingdong.common.entity.cart.CartAddHotListEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartAddHotListEntity createFromParcel(Parcel parcel) {
            return new CartAddHotListEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartAddHotListEntity[] newArray(int i2) {
            return new CartAddHotListEntity[i2];
        }
    };
    public String aiTag;
    public String instructionsText;
    public List<CartAddHotListBaseItem> items;
    public String prstate;
    public int skuNum;
    public String timeText;

    public CartAddHotListEntity() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.items = parcel.createTypedArrayList(CartAddHotListBaseItem.CREATOR);
        this.aiTag = parcel.readString();
        this.skuNum = parcel.readInt();
        this.instructionsText = parcel.readString();
        this.timeText = parcel.readString();
        this.prstate = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeTypedList(this.items);
        parcel.writeString(this.aiTag);
        parcel.writeInt(this.skuNum);
        parcel.writeString(this.instructionsText);
        parcel.writeString(this.timeText);
        parcel.writeString(this.prstate);
    }

    public CartAddHotListEntity(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray(CartConstant.KEY_ITEMS);
        if (optJSONArray != null && optJSONArray.size() > 0) {
            this.items = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    this.items.add(new CartAddHotListBaseItem(optJSONObject));
                }
            }
        }
        this.aiTag = jDJSONObject.optString("aiTag", "");
        this.skuNum = jDJSONObject.optInt("skuNum", 0);
        this.instructionsText = jDJSONObject.optString("instructionsText", "");
        this.timeText = jDJSONObject.optString("timeText", "");
        this.prstate = jDJSONObject.optString("prstate", "");
    }

    protected CartAddHotListEntity(Parcel parcel) {
        this.items = parcel.createTypedArrayList(CartAddHotListBaseItem.CREATOR);
        this.aiTag = parcel.readString();
        this.skuNum = parcel.readInt();
        this.instructionsText = parcel.readString();
        this.timeText = parcel.readString();
        this.prstate = parcel.readString();
    }
}
