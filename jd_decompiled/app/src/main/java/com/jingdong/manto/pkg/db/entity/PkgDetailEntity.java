package com.jingdong.manto.pkg.db.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jingdong.manto.provider.db.anno.Table;
import org.json.JSONObject;

@Table(primaryKeys = {"appId", "type"}, value = "pkgDetail")
/* loaded from: classes16.dex */
public class PkgDetailEntity implements Parcelable {
    public static final String CONFIG_JSON_KEY_DOWN_GRADE_H5_URL = "mpUrl";
    public static final Parcelable.Creator<PkgDetailEntity> CREATOR = new Parcelable.Creator<PkgDetailEntity>() { // from class: com.jingdong.manto.pkg.db.entity.PkgDetailEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PkgDetailEntity createFromParcel(Parcel parcel) {
            return new PkgDetailEntity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PkgDetailEntity[] newArray(int i2) {
            return new PkgDetailEntity[i2];
        }
    };
    public static final int DEBUG_NOT_EXISTS = 10511;
    public static final int DEBUG_NOT_GRANTED = 10512;
    public static final int DOWNLOAD_ERROR = 60001;
    public static final int MASK_ADD_CART = 3;
    public static final int MASK_APP_DOWN_TO_H5 = 6;
    public static final int MASK_APP_H5_OPEN_INNER = 7;
    public static final int MASK_APP_HOT_LAUNCH = 8;
    public static final int MASK_APP_UPDATE_ASYNC = 5;
    public static final int MASK_CASHIER = 1;
    public static final int MASK_CHECK_OUT = 0;
    public static final int MASK_CLOSE_MINI_PROGRAMME = 2;
    public static final int MASK_JUMP_CART = 4;
    public static final int NOT_AVAILABLE = 10501;
    public static final int NOT_EXISTS = 10513;
    public static final int NO_INFO = 40001;
    public static final int OPEN_ERROR = 50001;
    public static final int SUB_NO_INFO = 40002;
    public String apiBlackList;
    public String apiWhiteList;
    @NonNull
    public String appId;
    public String build;
    public String categories;
    public String charteredUrl;
    public String configJson;
    public String description;
    public String domains;
    public boolean favorite;
    public long lastOpenTime;
    public String logo;
    public String name;
    public String ownerName;
    public int permissions;
    public String pkgUrl;
    public String serviceEmail;
    public String servicePhone;
    public String subPkgInfos;
    public String templateId;
    public String templateVersion;
    @NonNull
    public String type;
    public String venderId;
    public String venderName;
    public String venderType;
    public String versionName;
    public String zipUrl;

    public PkgDetailEntity() {
    }

    protected PkgDetailEntity(Parcel parcel) {
        this.appId = parcel.readString();
        this.name = parcel.readString();
        this.description = parcel.readString();
        this.logo = parcel.readString();
        this.serviceEmail = parcel.readString();
        this.servicePhone = parcel.readString();
        this.ownerName = parcel.readString();
        this.build = parcel.readString();
        this.versionName = parcel.readString();
        this.pkgUrl = parcel.readString();
        this.type = parcel.readString();
        this.domains = parcel.readString();
        this.categories = parcel.readString();
        this.lastOpenTime = parcel.readLong();
        this.favorite = parcel.readByte() != 0;
        this.venderName = parcel.readString();
        this.zipUrl = parcel.readString();
        this.charteredUrl = parcel.readString();
        this.permissions = parcel.readInt();
        this.configJson = parcel.readString();
        this.apiWhiteList = parcel.readString();
        this.apiBlackList = parcel.readString();
        this.templateId = parcel.readString();
        this.templateVersion = parcel.readString();
        this.subPkgInfos = parcel.readString();
        this.venderId = parcel.readString();
    }

    public PkgDetailEntity(PkgCollectEntity pkgCollectEntity) {
        this.appId = pkgCollectEntity.appId;
        this.name = pkgCollectEntity.name;
        this.description = pkgCollectEntity.description;
        this.logo = pkgCollectEntity.logo;
        this.type = pkgCollectEntity.type;
        this.favorite = pkgCollectEntity.favorite;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof PkgDetailEntity) {
            PkgDetailEntity pkgDetailEntity = (PkgDetailEntity) obj;
            return TextUtils.equals(this.appId, pkgDetailEntity.appId) && TextUtils.equals(this.type, pkgDetailEntity.type) && TextUtils.equals(this.build, pkgDetailEntity.build);
        }
        return false;
    }

    public String getShareUrl() {
        try {
            return new JSONObject(this.configJson).getString("shareUrl");
        } catch (Throwable unused) {
            return "";
        }
    }

    public boolean isSwitchOpen(int i2) {
        return ((this.permissions >>> i2) & 1) > 0;
    }

    public String toString() {
        return "PkgDetailEntity{appId='" + this.appId + "', name='" + this.name + "', description='" + this.description + "', logo='" + this.logo + "', serviceEmail='" + this.serviceEmail + "', servicePhone='" + this.servicePhone + "', ownerName='" + this.ownerName + "', build='" + this.build + "', versionName='" + this.versionName + "', pkgUrl='" + this.pkgUrl + "', type='" + this.type + "', domains=" + this.domains + ", categories='" + this.categories + "', lastOpenTime=" + this.lastOpenTime + ", favorite=" + this.favorite + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.appId);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeString(this.logo);
        parcel.writeString(this.serviceEmail);
        parcel.writeString(this.servicePhone);
        parcel.writeString(this.ownerName);
        parcel.writeString(this.build);
        parcel.writeString(this.versionName);
        parcel.writeString(this.pkgUrl);
        parcel.writeString(this.type);
        parcel.writeString(this.domains);
        parcel.writeString(this.categories);
        parcel.writeLong(this.lastOpenTime);
        parcel.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        parcel.writeString(this.venderName);
        parcel.writeString(this.zipUrl);
        parcel.writeString(this.charteredUrl);
        parcel.writeInt(this.permissions);
        parcel.writeString(this.configJson);
        parcel.writeString(this.apiWhiteList);
        parcel.writeString(this.apiBlackList);
        parcel.writeString(this.templateId);
        parcel.writeString(this.templateVersion);
        parcel.writeString(this.subPkgInfos);
        parcel.writeString(this.venderId);
    }
}
