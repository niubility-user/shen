package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class NewGiftItem implements Parcelable, Serializable {
    public static final Parcelable.Creator<NewGiftItem> CREATOR = new Parcelable.Creator<NewGiftItem>() { // from class: com.jingdong.common.entity.cart.NewGiftItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewGiftItem createFromParcel(Parcel parcel) {
            return new NewGiftItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewGiftItem[] newArray(int i2) {
            return new NewGiftItem[i2];
        }
    };
    public String Id;
    public String ImgUrl;
    public boolean IsSelect;
    public String Name;
    public int Num;
    public double Price;

    public NewGiftItem() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.Id;
    }

    public String getImgUrl() {
        return this.ImgUrl;
    }

    public String getName() {
        return this.Name;
    }

    public int getNum() {
        return this.Num;
    }

    public double getPrice() {
        return this.Price;
    }

    public boolean isSelect() {
        return this.IsSelect;
    }

    public void setId(String str) {
        this.Id = str;
    }

    public void setImgUrl(String str) {
        this.ImgUrl = str;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public void setNum(int i2) {
        this.Num = i2;
    }

    public void setPrice(double d) {
        this.Price = d;
    }

    public void setSelect(boolean z) {
        this.IsSelect = z;
    }

    public JSONObject toSummaryParams() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(this.Id)) {
            jSONObject.put("Id", this.Id);
        }
        int i2 = this.Num;
        if (i2 != 0) {
            jSONObject.put("num", i2);
        }
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.Id);
        parcel.writeString(this.ImgUrl);
        parcel.writeString(this.Name);
        parcel.writeByte(this.IsSelect ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.Num);
        parcel.writeDouble(this.Price);
    }

    protected NewGiftItem(Parcel parcel) {
        this.Id = parcel.readString();
        this.ImgUrl = parcel.readString();
        this.Name = parcel.readString();
        this.IsSelect = parcel.readByte() != 0;
        this.Num = parcel.readInt();
        this.Price = parcel.readDouble();
    }
}
