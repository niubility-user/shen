package com.jingdong.common.entity.settlement.global;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class NormalInvoice implements Serializable, Parcelable {
    public static final Parcelable.Creator<NormalInvoice> CREATOR = new Parcelable.Creator<NormalInvoice>() { // from class: com.jingdong.common.entity.settlement.global.NormalInvoice.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NormalInvoice createFromParcel(Parcel parcel) {
            return new NormalInvoice(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NormalInvoice[] newArray(int i2) {
            return new NormalInvoice[i2];
        }
    };
    public String companyAddressPrompt;
    public String companyNamePrompt;
    public String companyPhonePrompt;
    public String invoiceCodePrompt;
    public String invoiceTittleElementName;
    public String notReduceTaxElementName;
    public String reduceTaxElementName;
    public String reduceTaxTittleElementName;

    public NormalInvoice() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.invoiceTittleElementName);
        parcel.writeString(this.companyNamePrompt);
        parcel.writeString(this.invoiceCodePrompt);
        parcel.writeString(this.companyAddressPrompt);
        parcel.writeString(this.companyPhonePrompt);
        parcel.writeString(this.reduceTaxElementName);
        parcel.writeString(this.notReduceTaxElementName);
        parcel.writeString(this.reduceTaxTittleElementName);
    }

    protected NormalInvoice(Parcel parcel) {
        this.invoiceTittleElementName = parcel.readString();
        this.companyNamePrompt = parcel.readString();
        this.invoiceCodePrompt = parcel.readString();
        this.companyAddressPrompt = parcel.readString();
        this.companyPhonePrompt = parcel.readString();
        this.reduceTaxElementName = parcel.readString();
        this.notReduceTaxElementName = parcel.readString();
        this.reduceTaxTittleElementName = parcel.readString();
    }
}
