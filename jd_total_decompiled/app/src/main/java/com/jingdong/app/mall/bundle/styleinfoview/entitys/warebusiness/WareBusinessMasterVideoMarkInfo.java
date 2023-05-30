package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class WareBusinessMasterVideoMarkInfo implements Parcelable {
    public static final Parcelable.Creator<WareBusinessMasterVideoMarkInfo> CREATOR = new Parcelable.Creator<WareBusinessMasterVideoMarkInfo>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessMasterVideoMarkInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMasterVideoMarkInfo createFromParcel(Parcel parcel) {
            return new WareBusinessMasterVideoMarkInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMasterVideoMarkInfo[] newArray(int i2) {
            return new WareBusinessMasterVideoMarkInfo[i2];
        }
    };
    public String mark;
    public int startTime;

    public WareBusinessMasterVideoMarkInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.startTime);
        parcel.writeString(this.mark);
    }

    protected WareBusinessMasterVideoMarkInfo(Parcel parcel) {
        this.startTime = parcel.readInt();
        this.mark = parcel.readString();
    }
}
