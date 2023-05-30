package com.jingdong.common.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.anotation.JSONField;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.app.mall.bundle.jdweather.action.JDWeatherActionKey;
import com.jingdong.common.R;
import com.jingdong.common.database.table.SignUpTable;
import com.jingdong.common.entity.settlement.AddressTagInfo;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NewEasyBuyAddress extends UserInfoCommon implements Serializable {
    public static final Parcelable.Creator<NewEasyBuyAddress> CREATOR = new Parcelable.Creator<NewEasyBuyAddress>() { // from class: com.jingdong.common.entity.NewEasyBuyAddress.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewEasyBuyAddress createFromParcel(Parcel parcel) {
            return new NewEasyBuyAddress(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NewEasyBuyAddress[] newArray(int i2) {
            return new NewEasyBuyAddress[i2];
        }
    };
    public static final int PAYMENT_ID_OFF_LINE = 1;
    public static final int PAYMENT_ID_ON_LINE = 4;
    public static final int PAYMENT_ID_PICK = 200;
    public static final int PAYMENT_ID_PICK_3 = 3;
    public static final int PAYMENT_ID_POST = 2;
    private static final String TAG = "NewEasyBuyAddress";
    private static final long serialVersionUID = 4420986013539080710L;
    public AddressBottomTagMap addressBottomTagMap;
    public String addressDetail;
    public String addressName;
    public AddressTagInfo addressTagMap;
    public String areaName;
    public Integer cityId;
    public String cityName;
    public int coord_type;
    public Integer countyId;
    public String countyName;
    public String email;
    public String fullAddress;
    public Long id;
    public boolean isAreaWrong;
    @JSONField(name = "addressDefault")
    public Boolean isDefaultAddr;
    @JSONField(name = "defaultFirst")
    public Boolean isDefaultFirst;
    @JSONField(name = "easyBuy")
    public Boolean isEasyBuy;
    @JSONField(name = "isIdTown")
    public boolean isNoIdTown;
    public boolean isOperationVisible;
    public double latitude;
    public double longitude;
    public String mobile;
    public String mobileReal;
    public String name;
    public Integer paymentId;
    public String phone;
    public Integer pickId;
    public String pickName;
    public Integer provinceId;
    public Integer townId;
    public String townName;

    public NewEasyBuyAddress(JSONObjectProxy jSONObjectProxy) {
        try {
            this.id = jSONObjectProxy.getLongOrNull("id");
            this.isEasyBuy = jSONObjectProxy.getBooleanOrNull("easyBuy");
            this.isDefaultFirst = jSONObjectProxy.getBooleanOrNull("defaultFirst");
            this.name = jSONObjectProxy.getStringOrNull("name");
            this.addressName = jSONObjectProxy.getStringOrNull("addressName");
            this.provinceId = jSONObjectProxy.getIntOrNull(JDWeatherActionKey.PROVINCE_ID);
            this.cityId = jSONObjectProxy.getIntOrNull(JDWeatherActionKey.CITY_ID);
            this.countyId = jSONObjectProxy.getIntOrNull("countyId");
            this.townId = jSONObjectProxy.getIntOrNull(JDWeatherActionKey.TOWN_ID);
            this.paymentId = jSONObjectProxy.getIntOrNull("paymentId");
            this.pickId = jSONObjectProxy.getIntOrNull("pickId");
            this.pickName = jSONObjectProxy.getStringOrNull("pickName");
            this.addressDetail = jSONObjectProxy.getStringOrNull("addressDetail");
            this.fullAddress = jSONObjectProxy.getStringOrNull("fullAddress");
            this.mobile = jSONObjectProxy.getStringOrNull("mobile");
            this.isDefaultAddr = Boolean.valueOf(jSONObjectProxy.optBoolean("addressDefault"));
            this.isAreaWrong = jSONObjectProxy.optBoolean("isAreaWrong");
            this.isNoIdTown = jSONObjectProxy.optBoolean("isIdTown");
            this.isOperationVisible = jSONObjectProxy.optBoolean("isOperationVisible");
            JSONObject optJSONObject = jSONObjectProxy.optJSONObject("addressTagMap");
            if (optJSONObject != null) {
                this.addressTagMap = (AddressTagInfo) JDJSON.parseObject(optJSONObject.toString(), AddressTagInfo.class);
            }
            JSONObject optJSONObject2 = jSONObjectProxy.optJSONObject("addressBottomTagMap");
            if (optJSONObject2 != null) {
                this.addressBottomTagMap = (AddressBottomTagMap) JDJSON.parseObject(optJSONObject2.toString(), AddressBottomTagMap.class);
            }
            setAreaExplainMsg(jSONObjectProxy.optString("areaExplainMsg"));
            setAreaExplainUrl(jSONObjectProxy.optString("areaExplainUrl"));
            this.phone = jSONObjectProxy.optString(SignUpTable.TB_COLUMN_PHONE);
            this.email = jSONObjectProxy.optString("email");
            this.areaCode = jSONObjectProxy.optString("areaCode");
            this.postCode = jSONObjectProxy.optString("postCode");
            this.nameCode = jSONObjectProxy.optString("nameCode");
            this.isForeignOverSea = jSONObjectProxy.optBoolean("isForeignOverSea");
            this.isGangAoTai = jSONObjectProxy.optBoolean("isGangAoTai");
            this.latitudeString = jSONObjectProxy.optString("latitudeString");
            this.longitudeString = jSONObjectProxy.optString("longitudeString");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public static ArrayList<NewEasyBuyAddress> parseList(JDJSONArray jDJSONArray) {
        ArrayList<NewEasyBuyAddress> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                arrayList.add(new NewEasyBuyAddress(optJSONObject));
            }
        }
        return arrayList;
    }

    public static String resolvePaymentName(int i2) {
        if (i2 != 1 && i2 != 200) {
            return JdSdk.getInstance().getApplication().getString(R.string.online_payment);
        }
        return JdSdk.getInstance().getApplication().getString(R.string.cash_on_delivery);
    }

    public static ArrayList<NewEasyBuyAddress> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<NewEasyBuyAddress> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i2);
            if (jSONObjectOrNull != null) {
                arrayList.add(new NewEasyBuyAddress(jSONObjectOrNull));
            }
        }
        return arrayList;
    }

    @Override // com.jingdong.common.entity.UserInfoCommon, com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddressDetail() {
        String str = this.addressDetail;
        return str == null ? "" : str;
    }

    public String getAddressName() {
        String str = this.addressName;
        return str == null ? "" : str;
    }

    public String getAreaName() {
        String str = this.areaName;
        return str == null ? "" : str;
    }

    public Integer getCityId() {
        Integer num = this.cityId;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getCityName() {
        String str = this.cityName;
        return str == null ? "" : str;
    }

    public Integer getCountyId() {
        Integer num = this.countyId;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getCountyName() {
        String str = this.countyName;
        return str == null ? "" : str;
    }

    public String getEmail() {
        String str = this.email;
        return str == null ? "" : str;
    }

    public String getFullAddress() {
        String str = this.fullAddress;
        return str == null ? "" : str;
    }

    public Long getId() {
        Long l2 = this.id;
        if (l2 == null) {
            return 0L;
        }
        return l2;
    }

    public Boolean getIsDefaultFirst() {
        Boolean bool = this.isDefaultFirst;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean getIsEasyBuy() {
        Boolean bool = this.isEasyBuy;
        return bool == null ? Boolean.FALSE : bool;
    }

    public String getMobile() {
        String str = this.mobile;
        return str == null ? "" : str;
    }

    public String getName() {
        String str = this.name;
        return str == null ? "" : str;
    }

    public Integer getPaymentId() {
        Integer num = this.paymentId;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getPaymentName() {
        return resolvePaymentName(getPaymentId().intValue());
    }

    public String getPhone() {
        String str = this.phone;
        return str == null ? "" : str;
    }

    public Integer getPickId() {
        Integer num = this.pickId;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getPickName() {
        String str = this.pickName;
        return str == null ? "" : str;
    }

    public Integer getProvinceId() {
        Integer num = this.provinceId;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getShowFullAddress() {
        return getFullAddress() + getTownName() + LangUtils.SINGLE_SPACE + getAddressDetail();
    }

    public Integer getTownId() {
        Integer num = this.townId;
        if (num == null) {
            return 0;
        }
        return num;
    }

    public String getTownName() {
        String str = this.townName;
        return str == null ? "" : str;
    }

    public Boolean isDefaultAddr() {
        Boolean bool = this.isDefaultAddr;
        return bool == null ? Boolean.FALSE : bool;
    }

    public JSONObject toAddressJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", getId());
            jSONObject.put("easyBuy", true);
            jSONObject.put("defaultFirst", getIsDefaultFirst());
            jSONObject.put("fullAddress", getFullAddress());
            jSONObject.put("addressName", getAddressName());
            jSONObject.put("name", getName());
            jSONObject.put(JDWeatherActionKey.PROVINCE_ID, getProvinceId());
            jSONObject.put(JDWeatherActionKey.CITY_ID, getCityId());
            jSONObject.put("countyId", getCountyId());
            jSONObject.put(JDWeatherActionKey.TOWN_ID, getTownId());
            jSONObject.put("addressDetail", getAddressDetail());
            AddressUtil.encodeValue(jSONObject, "mobile", getMobile());
            AddressUtil.encodeValue(jSONObject, SignUpTable.TB_COLUMN_PHONE, getPhone());
            jSONObject.put("email", getEmail());
            jSONObject.put("paymentId", getPaymentId());
            jSONObject.put("pickId", getPickId());
            jSONObject.put("pickName", getPickName());
            jSONObject.put("addressDefault", isDefaultAddr());
            jSONObject.put(PdLVBody.LONGITUDE, this.longitude);
            jSONObject.put(PdLVBody.LATITUDE, this.latitude);
            jSONObject.put("coord_type", this.coord_type);
            AddressTagInfo addressTagInfo = this.addressTagMap;
            if (addressTagInfo != null && !this.isForeignOverSea) {
                int i2 = addressTagInfo.addressTagId;
                if (i2 != 0) {
                    jSONObject.put("retTag", i2);
                }
                if (!TextUtils.isEmpty(this.addressTagMap.addressTagName)) {
                    jSONObject.put("userDefinedTag", this.addressTagMap.addressTagName);
                }
                jSONObject.put("tagSource", this.addressTagMap.addressTagType);
            }
            jSONObject.put("areaCode", this.areaCode);
            jSONObject.put("postCode", this.postCode);
            jSONObject.put("nameCode", this.nameCode);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        return jSONObject;
    }

    @Override // com.jingdong.common.entity.UserInfoCommon, com.jingdong.common.entity.settlement.AddressOverSea, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeValue(this.id);
        parcel.writeValue(this.isEasyBuy);
        parcel.writeValue(this.isDefaultFirst);
        parcel.writeString(this.name);
        parcel.writeString(this.addressName);
        parcel.writeValue(this.provinceId);
        parcel.writeValue(this.cityId);
        parcel.writeValue(this.countyId);
        parcel.writeValue(this.townId);
        parcel.writeValue(this.paymentId);
        parcel.writeValue(this.pickId);
        parcel.writeString(this.pickName);
        parcel.writeString(this.addressDetail);
        parcel.writeString(this.fullAddress);
        parcel.writeString(this.email);
        parcel.writeString(this.phone);
        parcel.writeString(this.mobile);
        parcel.writeString(this.mobileReal);
        parcel.writeValue(this.isDefaultAddr);
        parcel.writeString(this.areaName);
        parcel.writeString(this.cityName);
        parcel.writeString(this.countyName);
        parcel.writeString(this.townName);
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.latitude);
        parcel.writeInt(this.coord_type);
        parcel.writeParcelable(this.addressTagMap, i2);
        parcel.writeParcelable(this.addressBottomTagMap, i2);
        parcel.writeByte(this.isNoIdTown ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isAreaWrong ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isOperationVisible ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isForeignOverSea ? (byte) 1 : (byte) 0);
        parcel.writeString(this.areaCode);
        parcel.writeByte(this.isGangAoTai ? (byte) 1 : (byte) 0);
    }

    public NewEasyBuyAddress(JDJSONObject jDJSONObject) {
        try {
            this.id = Long.valueOf(jDJSONObject.optLong("id"));
            this.isEasyBuy = Boolean.valueOf(jDJSONObject.optBoolean("easyBuy"));
            this.isDefaultFirst = Boolean.valueOf(jDJSONObject.optBoolean("defaultFirst"));
            this.name = jDJSONObject.optString("name");
            this.addressName = jDJSONObject.optString("addressName");
            this.provinceId = Integer.valueOf(jDJSONObject.optInt(JDWeatherActionKey.PROVINCE_ID));
            this.cityId = Integer.valueOf(jDJSONObject.optInt(JDWeatherActionKey.CITY_ID));
            this.countyId = Integer.valueOf(jDJSONObject.optInt("countyId"));
            this.townId = Integer.valueOf(jDJSONObject.optInt(JDWeatherActionKey.TOWN_ID));
            this.paymentId = Integer.valueOf(jDJSONObject.optInt("paymentId"));
            this.pickId = Integer.valueOf(jDJSONObject.optInt("pickId"));
            this.pickName = jDJSONObject.optString("pickName");
            this.addressDetail = jDJSONObject.optString("addressDetail");
            this.fullAddress = jDJSONObject.optString("fullAddress");
            this.mobile = jDJSONObject.optString("mobile");
            this.isDefaultAddr = Boolean.valueOf(jDJSONObject.optBoolean("addressDefault"));
            this.isAreaWrong = jDJSONObject.optBoolean("isAreaWrong");
            this.isNoIdTown = jDJSONObject.optBoolean("isIdTown");
            this.isOperationVisible = jDJSONObject.optBoolean("isOperationVisible");
            JDJSONObject optJSONObject = jDJSONObject.optJSONObject("addressTagMap");
            if (optJSONObject != null) {
                this.addressTagMap = (AddressTagInfo) JDJSON.parseObject(optJSONObject.toString(), AddressTagInfo.class);
            }
            JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("addressBottomTagMap");
            if (optJSONObject2 != null) {
                this.addressBottomTagMap = (AddressBottomTagMap) JDJSON.parseObject(optJSONObject2.toString(), AddressBottomTagMap.class);
            }
            setAreaExplainMsg(jDJSONObject.optString("areaExplainMsg"));
            setAreaExplainUrl(jDJSONObject.optString("areaExplainUrl"));
            this.phone = jDJSONObject.optString(SignUpTable.TB_COLUMN_PHONE);
            this.email = jDJSONObject.optString("email");
            this.areaCode = jDJSONObject.optString("areaCode");
            this.postCode = jDJSONObject.optString("postCode");
            this.nameCode = jDJSONObject.optString("nameCode");
            this.isForeignOverSea = jDJSONObject.optBoolean("isForeignOverSea");
            this.isGangAoTai = jDJSONObject.optBoolean("isGangAoTai");
            this.latitudeString = jDJSONObject.optString("latitudeString");
            this.longitudeString = jDJSONObject.optString("longitudeString");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public NewEasyBuyAddress() {
    }

    protected NewEasyBuyAddress(Parcel parcel) {
        super(parcel);
        this.id = (Long) parcel.readValue(Long.class.getClassLoader());
        this.isEasyBuy = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.isDefaultFirst = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.name = parcel.readString();
        this.addressName = parcel.readString();
        this.provinceId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.cityId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.countyId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.townId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.paymentId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.pickId = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.pickName = parcel.readString();
        this.addressDetail = parcel.readString();
        this.fullAddress = parcel.readString();
        this.email = parcel.readString();
        this.phone = parcel.readString();
        this.mobile = parcel.readString();
        this.mobileReal = parcel.readString();
        this.isDefaultAddr = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.areaName = parcel.readString();
        this.cityName = parcel.readString();
        this.countyName = parcel.readString();
        this.townName = parcel.readString();
        this.longitude = parcel.readDouble();
        this.latitude = parcel.readDouble();
        this.coord_type = parcel.readInt();
        this.addressTagMap = (AddressTagInfo) parcel.readParcelable(AddressTagInfo.class.getClassLoader());
        this.addressBottomTagMap = (AddressBottomTagMap) parcel.readParcelable(AddressBottomTagMap.class.getClassLoader());
        this.isNoIdTown = parcel.readByte() != 0;
        this.isAreaWrong = parcel.readByte() != 0;
        this.isOperationVisible = parcel.readByte() != 0;
        this.isForeignOverSea = parcel.readByte() != 0;
        this.areaCode = parcel.readString();
        this.isGangAoTai = parcel.readByte() != 0;
    }
}
