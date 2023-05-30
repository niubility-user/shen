package com.jingdong.common.entity.personal;

import android.os.Parcel;
import android.os.Parcelable;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;

/* loaded from: classes5.dex */
public class HomeConfig implements Cloneable, Parcelable {
    public static final Parcelable.Creator<HomeConfig> CREATOR = new Parcelable.Creator<HomeConfig>() { // from class: com.jingdong.common.entity.personal.HomeConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeConfig createFromParcel(Parcel parcel) {
            return new HomeConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HomeConfig[] newArray(int i2) {
            return new HomeConfig[i2];
        }
    };
    private static final String TAG = "HomeConfig";
    public String action;
    public boolean addLimit;
    public List<HomeConfig> chindItem;
    public String color;
    public String content;
    public String eventId;
    public LibMtaEntity expoMta;
    public String functionId;
    public String grouplevel;
    public boolean isBusinessPlatform;
    public boolean isLocalData;
    public ConfigJumpInfo jumpInfo;
    public String lableImage;
    public String lableName;
    public String mUrl;
    public String maiDianId;
    public String next;
    public String orderGrade;
    public String pageId;
    public String platList;
    public int reddotflag;
    public long reddotversion;
    public List<HomeConfig> showItem;
    public int sort;
    public String title;
    public String type;
    public boolean visible;

    /* loaded from: classes5.dex */
    public static class ConfigJumpInfo implements Parcelable {
        public static final Parcelable.Creator<ConfigJumpInfo> CREATOR = new Parcelable.Creator<ConfigJumpInfo>() { // from class: com.jingdong.common.entity.personal.HomeConfig.ConfigJumpInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConfigJumpInfo createFromParcel(Parcel parcel) {
                return new ConfigJumpInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ConfigJumpInfo[] newArray(int i2) {
                return new ConfigJumpInfo[i2];
            }
        };
        public int jumpType;
        public String jumpUrl;
        public int needLogin;

        public ConfigJumpInfo() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public boolean isNeedLogin() {
            return this.needLogin == 1;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.jumpType);
            parcel.writeString(this.jumpUrl);
            parcel.writeInt(this.needLogin);
        }

        protected ConfigJumpInfo(Parcel parcel) {
            this.jumpType = parcel.readInt();
            this.jumpUrl = parcel.readString();
            this.needLogin = parcel.readInt();
        }
    }

    protected HomeConfig(Parcel parcel) {
        this.visible = true;
        this.isLocalData = false;
        this.maiDianId = "";
        this.lableName = parcel.readString();
        this.lableImage = parcel.readString();
        this.functionId = parcel.readString();
        this.sort = parcel.readInt();
        this.next = parcel.readString();
        this.mUrl = parcel.readString();
        this.action = parcel.readString();
        this.platList = parcel.readString();
        this.type = parcel.readString();
        this.content = parcel.readString();
        this.title = parcel.readString();
        this.color = parcel.readString();
        this.reddotflag = parcel.readInt();
        this.reddotversion = parcel.readLong();
        this.addLimit = parcel.readByte() != 0;
        this.grouplevel = parcel.readString();
        this.visible = parcel.readByte() != 0;
        this.eventId = parcel.readString();
        this.pageId = parcel.readString();
        this.orderGrade = parcel.readString();
        Parcelable.Creator<HomeConfig> creator = CREATOR;
        this.chindItem = parcel.createTypedArrayList(creator);
        this.showItem = parcel.createTypedArrayList(creator);
        this.isBusinessPlatform = parcel.readByte() != 0;
        this.isLocalData = parcel.readByte() != 0;
        this.maiDianId = parcel.readString();
        this.jumpInfo = (ConfigJumpInfo) parcel.readParcelable(ConfigJumpInfo.class.getClassLoader());
        this.expoMta = (LibMtaEntity) parcel.readParcelable(LibMtaEntity.class.getClassLoader());
    }

    private HomeConfig getConfigItem(List<HomeConfig> list, String str) {
        if (list == null) {
            return null;
        }
        for (HomeConfig homeConfig : list) {
            if (homeConfig.functionId.equals(str)) {
                return homeConfig;
            }
        }
        return null;
    }

    public Object clone() {
        try {
            return (HomeConfig) super.clone();
        } catch (CloneNotSupportedException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HomeConfig getChildConfigItem(String str) {
        return getConfigItem(this.chindItem, str);
    }

    public HomeConfig getShowConfigItem(String str) {
        return getConfigItem(this.showItem, str);
    }

    public boolean isRedDotFlag() {
        return this.reddotflag != 0;
    }

    public String toString() {
        return "HomeConfig{lableName='" + this.lableName + "', functionId='" + this.functionId + "', sort=" + this.sort + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.lableName);
        parcel.writeString(this.lableImage);
        parcel.writeString(this.functionId);
        parcel.writeInt(this.sort);
        parcel.writeString(this.next);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.action);
        parcel.writeString(this.platList);
        parcel.writeString(this.type);
        parcel.writeString(this.content);
        parcel.writeString(this.title);
        parcel.writeString(this.color);
        parcel.writeInt(this.reddotflag);
        parcel.writeLong(this.reddotversion);
        parcel.writeByte(this.addLimit ? (byte) 1 : (byte) 0);
        parcel.writeString(this.grouplevel);
        parcel.writeByte(this.visible ? (byte) 1 : (byte) 0);
        parcel.writeString(this.eventId);
        parcel.writeString(this.pageId);
        parcel.writeString(this.orderGrade);
        parcel.writeTypedList(this.chindItem);
        parcel.writeTypedList(this.showItem);
        parcel.writeByte(this.isBusinessPlatform ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isLocalData ? (byte) 1 : (byte) 0);
        parcel.writeString(this.maiDianId);
        parcel.writeParcelable(this.jumpInfo, i2);
        parcel.writeParcelable(this.expoMta, i2);
    }

    public HomeConfig() {
        this.visible = true;
        this.isLocalData = false;
        this.maiDianId = "";
    }
}
