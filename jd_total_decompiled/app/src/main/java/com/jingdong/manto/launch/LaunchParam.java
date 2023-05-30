package com.jingdong.manto.launch;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes15.dex */
public final class LaunchParam implements Parcelable {
    public static final String AUDIT = "3";
    public static final Parcelable.Creator<LaunchParam> CREATOR = new a();
    public static final String DEBUG = "2";
    public static final String IDE_NATIVE_CMD = "996";
    public static final String IDE_PKG = "5";
    public static final String LOCAL_PKG = "14";
    public static final String LOCAL_TEST = "13";
    public static final String RELEASE = "1";
    public String actionId;
    public String appId;
    public int cardHeight;
    public String cardPreviewParam;
    public int cardWidth;
    public String debugType;
    public String extrasJson;
    public boolean isCard;
    public String launchPath;
    public String launchProxy;
    public com.jingdong.manto.i.d launchReferrer;
    public String logo;
    public String mpMode;
    public String pageAlias;
    public String pkgUrl;
    public String scene;
    public String sourcePath;
    public String sourceSubPkgJson;
    public UIConfig uiConfig;
    public int version;

    /* loaded from: classes15.dex */
    class a implements Parcelable.Creator<LaunchParam> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LaunchParam createFromParcel(Parcel parcel) {
            return new LaunchParam(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public LaunchParam[] newArray(int i2) {
            return new LaunchParam[i2];
        }
    }

    public LaunchParam() {
        this.debugType = "1";
        this.launchProxy = "0";
    }

    protected LaunchParam(Parcel parcel) {
        this.debugType = "1";
        this.launchProxy = "0";
        this.appId = parcel.readString();
        this.debugType = parcel.readString();
        this.launchPath = parcel.readString();
        this.launchReferrer = (com.jingdong.manto.i.d) parcel.readParcelable(com.jingdong.manto.i.d.class.getClassLoader());
        this.version = parcel.readInt();
        this.sourcePath = parcel.readString();
        this.pkgUrl = parcel.readString();
        this.extrasJson = parcel.readString();
        this.scene = parcel.readString();
        this.pageAlias = parcel.readString();
        this.sourceSubPkgJson = parcel.readString();
        this.uiConfig = (UIConfig) parcel.readParcelable(UIConfig.class.getClassLoader());
        this.isCard = parcel.readByte() != 0;
        this.cardHeight = parcel.readInt();
        this.cardWidth = parcel.readInt();
        this.cardPreviewParam = parcel.readString();
        this.actionId = parcel.readString();
        this.mpMode = parcel.readString();
        this.launchProxy = parcel.readString();
        this.logo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "{appId='" + this.appId + "', debugType='" + this.debugType + "', launchPath='" + this.launchPath + "', version=" + this.version + ", isCard=" + this.isCard + ", cardHeight=" + this.cardHeight + ", cardWidth=" + this.cardWidth + ", cardPreviewParam=" + this.cardPreviewParam + ", sourcePath='" + this.sourcePath + "', sourceSubPkgJson='" + this.sourceSubPkgJson + "', pkgUrl='" + this.pkgUrl + "', extrasJson='" + this.extrasJson + "', scene='" + this.scene + "', pageAlias='" + this.pageAlias + "', launchProxy='" + this.launchProxy + "', logo='" + this.logo + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.appId);
        parcel.writeString(this.debugType);
        parcel.writeString(this.launchPath);
        parcel.writeParcelable(this.launchReferrer, i2);
        parcel.writeInt(this.version);
        parcel.writeString(this.sourcePath);
        parcel.writeString(this.pkgUrl);
        parcel.writeString(this.extrasJson);
        parcel.writeString(this.scene);
        parcel.writeString(this.pageAlias);
        parcel.writeString(this.sourceSubPkgJson);
        parcel.writeParcelable(this.uiConfig, i2);
        parcel.writeByte(this.isCard ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.cardHeight);
        parcel.writeInt(this.cardWidth);
        parcel.writeString(this.cardPreviewParam);
        parcel.writeString(this.actionId);
        parcel.writeString(this.mpMode);
        parcel.writeString(this.launchProxy);
        parcel.writeString(this.logo);
    }
}
