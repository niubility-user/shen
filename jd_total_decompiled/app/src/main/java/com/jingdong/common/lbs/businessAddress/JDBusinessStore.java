package com.jingdong.common.lbs.businessAddress;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.remoteimage.RemoteImageManager;
import com.jingdong.sdk.platform.business.personal.R2;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDBusinessStore implements Parcelable {
    public static final Parcelable.Creator<JDBusinessStore> CREATOR = new Parcelable.Creator<JDBusinessStore>() { // from class: com.jingdong.common.lbs.businessAddress.JDBusinessStore.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDBusinessStore createFromParcel(Parcel parcel) {
            return new JDBusinessStore(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JDBusinessStore[] newArray(int i2) {
            return new JDBusinessStore[i2];
        }
    };
    private String addressCityCode;
    private String addressCityName;
    private String addressCountryCode;
    private String addressCountryName;
    private String addressDetail;
    private String addressProvinceCode;
    private String addressProvinceName;
    private String addressStreetCode;
    private String addressStreetName;
    private String businessEndHours;
    private String businessStartHours;
    private String categoryId;
    private String categoryId2;
    private String categoryId2Name;
    private double distance;
    private long id;
    private double lat;
    private double lng;
    private String markerPic;
    private String name;
    private int status;
    private String storePic;
    private long venderId;
    private int venderSource;

    public JDBusinessStore() {
    }

    private String getRemoteImageUrl(String str) {
        return RemoteImageManager.getImageUrlById("107" + CartConstant.KEY_YB_INFO_LINK + str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddressCityCode() {
        return TextUtils.isEmpty(this.addressCityCode) ? "" : this.addressCityCode;
    }

    public String getAddressCityName() {
        return TextUtils.isEmpty(this.addressCityName) ? "" : this.addressCityName;
    }

    public String getAddressCountryCode() {
        return TextUtils.isEmpty(this.addressCountryCode) ? "" : this.addressCountryCode;
    }

    public String getAddressCountryName() {
        return TextUtils.isEmpty(this.addressCountryName) ? "" : this.addressCountryName;
    }

    public String getAddressDetail() {
        return TextUtils.isEmpty(this.addressDetail) ? "" : this.addressDetail;
    }

    public String getAddressProvinceCode() {
        return TextUtils.isEmpty(this.addressProvinceCode) ? "" : this.addressProvinceCode;
    }

    public String getAddressProvinceName() {
        return TextUtils.isEmpty(this.addressProvinceName) ? "" : this.addressProvinceName;
    }

    public String getAddressStreetCode() {
        return TextUtils.isEmpty(this.addressStreetCode) ? "" : this.addressStreetCode;
    }

    public String getAddressStreetName() {
        return TextUtils.isEmpty(this.addressStreetName) ? "" : this.addressStreetName;
    }

    public String getBusinessEndHours() {
        return TextUtils.isEmpty(this.businessEndHours) ? "" : this.businessEndHours;
    }

    public String getBusinessStartHours() {
        return TextUtils.isEmpty(this.businessStartHours) ? "" : this.businessStartHours;
    }

    public String getCategoryId() {
        return TextUtils.isEmpty(this.categoryId) ? "" : this.categoryId;
    }

    public String getCategoryId2() {
        return TextUtils.isEmpty(this.categoryId2) ? "" : this.categoryId2;
    }

    public String getCategoryId2Name() {
        return TextUtils.isEmpty(this.categoryId2Name) ? "" : this.categoryId2Name;
    }

    public double getDistance() {
        if (Double.isNaN(this.distance)) {
            return 0.0d;
        }
        return this.distance;
    }

    public long getId() {
        return this.id;
    }

    public String getJsonStr() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", getId());
            jSONObject.put("name", getName());
            jSONObject.put("venderId", getVenderId());
            jSONObject.put("businessStartHours", getBusinessStartHours());
            jSONObject.put("businessEndHours", getBusinessEndHours());
            jSONObject.put(HybridSDK.LNG, getLng());
            jSONObject.put("lat", getLat());
            jSONObject.put("addressProvinceCode", getAddressProvinceCode());
            jSONObject.put("addressCityCode", getAddressCityCode());
            jSONObject.put("addressCountryCode", getAddressCountryCode());
            jSONObject.put("addressStreetCode", getAddressStreetCode());
            jSONObject.put("addressProvinceName", getAddressProvinceName());
            jSONObject.put("addressCityName", getAddressCityName());
            jSONObject.put("addressCountryName", getAddressCountryName());
            jSONObject.put("addressStreetName", getAddressStreetName());
            jSONObject.put("addressDetail", getAddressDetail());
            jSONObject.put("status", getStatus());
            jSONObject.put(CartConstant.KEY_SHOP_VENDERSOURCE, getVenderSource());
            jSONObject.put("storePic", getStorePic());
            jSONObject.put("categoryId2", getCategoryId2());
            jSONObject.put("categoryId", getCategoryId());
            jSONObject.put("distance", getDistance());
            jSONObject.put("markerPic", getMarkerPic());
            jSONObject.put("categoryId2Name", getCategoryId2Name());
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public double getLat() {
        if (Double.isNaN(this.lat)) {
            return 0.0d;
        }
        return this.lat;
    }

    public double getLng() {
        if (Double.isNaN(this.lng)) {
            return 0.0d;
        }
        return this.lng;
    }

    public String getMarkerPic() {
        String categoryId = getCategoryId();
        categoryId.hashCode();
        char c2 = '\uffff';
        switch (categoryId.hashCode()) {
            case 49:
                if (categoryId.equals("1")) {
                    c2 = 0;
                    break;
                }
                break;
            case R2.attr.rTopRightRadius /* 1633 */:
                if (categoryId.equals("34")) {
                    c2 = 1;
                    break;
                }
                break;
            case 48631:
                if (categoryId.equals("106")) {
                    c2 = 2;
                    break;
                }
                break;
            case 48690:
                if (categoryId.equals("123")) {
                    c2 = 3;
                    break;
                }
                break;
            case 48756:
                if (categoryId.equals("147")) {
                    c2 = 4;
                    break;
                }
                break;
            case 48816:
                if (categoryId.equals("165")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.markerPic = getRemoteImageUrl("2007");
                break;
            case 1:
                this.markerPic = getRemoteImageUrl("2005");
                break;
            case 2:
                this.markerPic = getRemoteImageUrl("2009");
                break;
            case 3:
                this.markerPic = getRemoteImageUrl("2012");
                break;
            case 4:
                this.markerPic = getRemoteImageUrl("2008");
                break;
            case 5:
                this.markerPic = getRemoteImageUrl("2011");
                break;
            default:
                this.markerPic = getRemoteImageUrl("2010");
                break;
        }
        return TextUtils.isEmpty(this.markerPic) ? "" : this.markerPic;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public int getStatus() {
        return this.status;
    }

    public String getStorePic() {
        return TextUtils.isEmpty(this.storePic) ? "" : this.storePic;
    }

    public long getVenderId() {
        return this.venderId;
    }

    public int getVenderSource() {
        return this.venderSource;
    }

    public void setAddressCityCode(String str) {
        this.addressCityCode = str;
    }

    public void setAddressCityName(String str) {
        this.addressCityName = str;
    }

    public void setAddressCountryCode(String str) {
        this.addressCountryCode = str;
    }

    public void setAddressCountryName(String str) {
        this.addressCountryName = str;
    }

    public void setAddressDetail(String str) {
        this.addressDetail = str;
    }

    public void setAddressProvinceCode(String str) {
        this.addressProvinceCode = str;
    }

    public void setAddressProvinceName(String str) {
        this.addressProvinceName = str;
    }

    public void setAddressStreetCode(String str) {
        this.addressStreetCode = str;
    }

    public void setAddressStreetName(String str) {
        this.addressStreetName = str;
    }

    public void setBusinessEndHours(String str) {
        this.businessEndHours = str;
    }

    public void setBusinessStartHours(String str) {
        this.businessStartHours = str;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public void setCategoryId2(String str) {
        this.categoryId2 = str;
    }

    public void setCategoryId2Name(String str) {
        this.categoryId2Name = str;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public void setId(long j2) {
        this.id = j2;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setLng(double d) {
        this.lng = d;
    }

    public void setMarkerPic(String str) {
        this.markerPic = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public void setStorePic(String str) {
        this.storePic = str;
    }

    public void setVenderId(long j2) {
        this.venderId = j2;
    }

    public void setVenderSource(int i2) {
        this.venderSource = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.id);
        parcel.writeString(this.name);
        parcel.writeLong(this.venderId);
        parcel.writeString(this.businessStartHours);
        parcel.writeString(this.businessEndHours);
        parcel.writeDouble(this.lng);
        parcel.writeDouble(this.lat);
        parcel.writeString(this.addressProvinceCode);
        parcel.writeString(this.addressCityCode);
        parcel.writeString(this.addressCountryCode);
        parcel.writeString(this.addressStreetCode);
        parcel.writeString(this.addressProvinceName);
        parcel.writeString(this.addressCityName);
        parcel.writeString(this.addressCountryName);
        parcel.writeString(this.addressStreetName);
        parcel.writeString(this.addressDetail);
        parcel.writeInt(this.status);
        parcel.writeInt(this.venderSource);
        parcel.writeString(this.storePic);
        parcel.writeString(this.categoryId2);
        parcel.writeString(this.categoryId);
        parcel.writeDouble(this.distance);
        parcel.writeString(this.markerPic);
        parcel.writeString(this.categoryId2Name);
    }

    protected JDBusinessStore(Parcel parcel) {
        this.id = parcel.readLong();
        this.name = parcel.readString();
        this.venderId = parcel.readLong();
        this.businessStartHours = parcel.readString();
        this.businessEndHours = parcel.readString();
        this.lng = parcel.readDouble();
        this.lat = parcel.readDouble();
        this.addressProvinceCode = parcel.readString();
        this.addressCityCode = parcel.readString();
        this.addressCountryCode = parcel.readString();
        this.addressStreetCode = parcel.readString();
        this.addressProvinceName = parcel.readString();
        this.addressCityName = parcel.readString();
        this.addressCountryName = parcel.readString();
        this.addressStreetName = parcel.readString();
        this.addressDetail = parcel.readString();
        this.status = parcel.readInt();
        this.venderSource = parcel.readInt();
        this.storePic = parcel.readString();
        this.categoryId2 = parcel.readString();
        this.categoryId = parcel.readString();
        this.distance = parcel.readDouble();
        this.markerPic = parcel.readString();
        this.categoryId2Name = parcel.readString();
    }
}
