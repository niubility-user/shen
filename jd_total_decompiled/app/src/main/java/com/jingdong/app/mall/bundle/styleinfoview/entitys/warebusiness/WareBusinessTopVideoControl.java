package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes3.dex */
public class WareBusinessTopVideoControl implements Parcelable {
    public static final Parcelable.Creator<WareBusinessTopVideoControl> CREATOR = new Parcelable.Creator<WareBusinessTopVideoControl>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessTopVideoControl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessTopVideoControl createFromParcel(Parcel parcel) {
            return new WareBusinessTopVideoControl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessTopVideoControl[] newArray(int i2) {
            return new WareBusinessTopVideoControl[i2];
        }
    };
    public boolean autoPlay;
    public boolean floatLayer;
    public WareBusinessMaterVideoInfo masterVideo;
    public boolean optimize;

    public WareBusinessTopVideoControl() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isHasMasterVideo() {
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo = this.masterVideo;
        return (wareBusinessMaterVideoInfo == null || TextUtils.isEmpty(wareBusinessMaterVideoInfo.playUrl)) ? false : true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.optimize ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.autoPlay ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.masterVideo, i2);
        parcel.writeByte(this.floatLayer ? (byte) 1 : (byte) 0);
    }

    protected WareBusinessTopVideoControl(Parcel parcel) {
        this.optimize = parcel.readByte() != 0;
        this.autoPlay = parcel.readByte() != 0;
        this.masterVideo = (WareBusinessMaterVideoInfo) parcel.readParcelable(WareBusinessMaterVideoInfo.class.getClassLoader());
        this.floatLayer = parcel.readByte() != 0;
    }
}
