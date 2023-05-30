package com.unionpay.tsmservice.mi.result;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class EncryptDataResult implements Parcelable {
    public static final Parcelable.Creator<EncryptDataResult> CREATOR = new Parcelable.Creator<EncryptDataResult>() { // from class: com.unionpay.tsmservice.mi.result.EncryptDataResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final EncryptDataResult createFromParcel(Parcel parcel) {
            return new EncryptDataResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final EncryptDataResult[] newArray(int i2) {
            return new EncryptDataResult[i2];
        }
    };
    private List<String> mEncryptData;

    public EncryptDataResult() {
    }

    public EncryptDataResult(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.mEncryptData = arrayList;
        parcel.readList(arrayList, ClassLoader.getSystemClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<String> getEncryptData() {
        return this.mEncryptData;
    }

    public void setEncryptData(List<String> list) {
        this.mEncryptData = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeList(this.mEncryptData);
    }
}
