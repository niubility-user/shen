package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.WareBusinessHwShareInfoAgreement;
import java.util.List;

/* loaded from: classes3.dex */
public class WareBusinessHwShareInfo implements Parcelable {
    public static final Parcelable.Creator<WareBusinessHwShareInfo> CREATOR = new Parcelable.Creator<WareBusinessHwShareInfo>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessHwShareInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessHwShareInfo createFromParcel(Parcel parcel) {
            return new WareBusinessHwShareInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessHwShareInfo[] newArray(int i2) {
            return new WareBusinessHwShareInfo[i2];
        }
    };
    public String agreement;
    public String agreementHead;
    public WareBusinessHwShareInfoAgreement agreementToast;
    public String bgImg;
    public String btnText;
    public List<WareBusinessHwShareInfoContent> content;
    public String disAgreeToast;
    public List<String> image;
    public String skuId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.content);
        parcel.writeString(this.bgImg);
        parcel.writeList(this.image);
        parcel.writeString(this.agreementHead);
        parcel.writeString(this.agreement);
        parcel.writeString(this.btnText);
        parcel.writeParcelable(this.agreementToast, i2);
        parcel.writeString(this.disAgreeToast);
        parcel.writeString(this.skuId);
    }

    public WareBusinessHwShareInfo() {
    }

    private WareBusinessHwShareInfo(Parcel parcel) {
        this.content = parcel.readArrayList(WareBusinessHwShareInfoContent.class.getClassLoader());
        this.bgImg = parcel.readString();
        this.image = parcel.readArrayList(String.class.getClassLoader());
        this.agreementHead = parcel.readString();
        this.agreement = parcel.readString();
        this.btnText = parcel.readString();
        this.agreementToast = (WareBusinessHwShareInfoAgreement) parcel.readParcelable(WareBusinessHwShareInfoAgreement.class.getClassLoader());
        this.disAgreeToast = parcel.readString();
        this.skuId = parcel.readString();
    }
}
