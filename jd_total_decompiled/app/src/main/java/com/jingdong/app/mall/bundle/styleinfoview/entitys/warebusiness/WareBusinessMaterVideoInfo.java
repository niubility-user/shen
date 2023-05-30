package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class WareBusinessMaterVideoInfo implements Parcelable {
    public static final Parcelable.Creator<WareBusinessMaterVideoInfo> CREATOR = new Parcelable.Creator<WareBusinessMaterVideoInfo>() { // from class: com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessMaterVideoInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMaterVideoInfo createFromParcel(Parcel parcel) {
            return new WareBusinessMaterVideoInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WareBusinessMaterVideoInfo[] newArray(int i2) {
            return new WareBusinessMaterVideoInfo[i2];
        }
    };
    public int duration;
    public WareBusinessMasterVideoExtInfo extInfo;
    public String imageUrl;
    public boolean playBackFlag;
    public String playUrl;
    public String videoDuration;
    public String videoId;
    public List<WareBusinessMasterVideoMarkInfo> videoMarkList;
    public WareBusinessMaterVideoShareInfo videoShare;

    public WareBusinessMaterVideoInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean isHasExtInfo() {
        WareBusinessMasterVideoExtInfo wareBusinessMasterVideoExtInfo = this.extInfo;
        return (wareBusinessMasterVideoExtInfo == null || TextUtils.isEmpty(wareBusinessMasterVideoExtInfo.trailerPlayUrl) || TextUtils.isEmpty(this.extInfo.trailerImgUrl) || this.extInfo.trailerDuration <= 0) ? false : true;
    }

    public boolean isHasMarkInfo() {
        List<WareBusinessMasterVideoMarkInfo> list = this.videoMarkList;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public boolean isHasShareInfo() {
        WareBusinessMaterVideoShareInfo wareBusinessMaterVideoShareInfo = this.videoShare;
        return (wareBusinessMaterVideoShareInfo == null || TextUtils.isEmpty(wareBusinessMaterVideoShareInfo.url) || TextUtils.isEmpty(this.videoShare.microBlog)) ? false : true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.videoId);
        parcel.writeInt(this.duration);
        parcel.writeString(this.videoDuration);
        parcel.writeString(this.imageUrl);
        parcel.writeByte(this.playBackFlag ? (byte) 1 : (byte) 0);
        parcel.writeString(this.playUrl);
        parcel.writeList(this.videoMarkList);
        parcel.writeParcelable(this.extInfo, i2);
        parcel.writeParcelable(this.videoShare, i2);
    }

    protected WareBusinessMaterVideoInfo(Parcel parcel) {
        this.videoId = parcel.readString();
        this.duration = parcel.readInt();
        this.videoDuration = parcel.readString();
        this.imageUrl = parcel.readString();
        this.playBackFlag = parcel.readByte() != 0;
        this.playUrl = parcel.readString();
        this.videoMarkList = parcel.readArrayList(WareBusinessHwShareInfoContent.class.getClassLoader());
        this.extInfo = (WareBusinessMasterVideoExtInfo) parcel.readParcelable(WareBusinessMasterVideoExtInfo.class.getClassLoader());
        this.videoShare = (WareBusinessMaterVideoShareInfo) parcel.readParcelable(WareBusinessMaterVideoShareInfo.class.getClassLoader());
    }
}
