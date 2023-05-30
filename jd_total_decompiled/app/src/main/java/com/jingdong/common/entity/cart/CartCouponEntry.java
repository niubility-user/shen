package com.jingdong.common.entity.cart;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class CartCouponEntry implements Parcelable {
    public static final Parcelable.Creator<CartCouponEntry> CREATOR = new Parcelable.Creator<CartCouponEntry>() { // from class: com.jingdong.common.entity.cart.CartCouponEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponEntry createFromParcel(Parcel parcel) {
            return new CartCouponEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CartCouponEntry[] newArray(int i2) {
            return new CartCouponEntry[i2];
        }
    };
    public String act;
    public int addDays;
    public String appid;
    public Boolean applicability;
    public long availableTimeDif;
    public String beginHour;
    public String beginTime;
    public String biinfo;
    public String btActivityId;
    public String canDiscount;
    public String channelDetail;
    public int checkSkuNum;
    public Boolean closable;
    public String couponCornerMarkUrl;
    public String couponCouDeg;
    public String couponIconStyle;
    public Long couponId;
    public int couponKind;
    public String couponSource;
    public String couponSourceDetail;
    public int couponStyle;
    public String couponTips;
    public CartCouponConfigurationInfo darkModelCouponConfigurationInfo;
    public Integer discount;
    public String discountDesc;
    public int discountLevel;
    public String discountShotDesc;
    public String discountValueDesc;
    public String endHour;
    public String endTime;
    public CartCouponConfigurationInfo generalModelCouponConfigurationInfo;
    public String high;
    public int hourCoupon;
    public ArrayList<String> iconPrioBottom;
    public boolean isAvailableTime;
    public boolean isNoQuota;
    public int isOverLapOpen;
    public boolean isShieldCheckBox;
    public int jumpType;
    public String jumpUrl;
    public int limitType;
    public String low;
    public long mBatchId;
    public String mJshopName;
    public String manJianPerMsg;
    public int manjianCouponStyle;
    public long milliSecond;
    public String name;
    public String needMoney;
    public int overLap;
    public String overLapDesc;
    public Integer platformType;
    public String platformid;
    public int plusStyle;
    public String preciseDiscount;
    public String preciseQuota;
    public String price;
    public Integer quota;
    public String quotaDesc;
    public Integer remain;
    public String reprice;
    public Long shopId;
    public String shopName;
    public int shopType;
    public ArrayList<CartCouponSkuItem> skus;
    public String storeId;
    public Integer takeRule;
    public String trackId;
    public Integer type;
    public String uuid;
    public String validPeriodDesc;
    public Long venderId;
    public Long vendorId;

    public CartCouponEntry() {
        this.isOverLapOpen = 0;
    }

    public static ArrayList<CartCouponEntry> toList(JDJSONArray jDJSONArray, String str, int i2) {
        ArrayList<CartCouponEntry> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
                JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    arrayList.add(new CartCouponEntry(optJSONObject, str, i2));
                }
            }
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCouponFlag(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.couponId);
        if (!z) {
            sb.append(this.mBatchId);
        } else {
            sb.append(0L);
        }
        sb.append(this.beginTime);
        sb.append(this.endTime);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.venderId);
        parcel.writeValue(this.couponId);
        parcel.writeString(this.name);
        parcel.writeValue(this.type);
        parcel.writeValue(this.platformType);
        parcel.writeValue(this.discount);
        parcel.writeString(this.preciseDiscount);
        parcel.writeValue(this.quota);
        parcel.writeString(this.preciseQuota);
        parcel.writeString(this.beginTime);
        parcel.writeString(this.endTime);
        parcel.writeValue(this.takeRule);
        parcel.writeValue(this.remain);
        parcel.writeValue(this.applicability);
        parcel.writeString(this.act);
        parcel.writeString(this.mJshopName);
        parcel.writeString(this.btActivityId);
        parcel.writeString(this.validPeriodDesc);
        parcel.writeLong(this.mBatchId);
        parcel.writeInt(this.shopType);
        parcel.writeInt(this.limitType);
        parcel.writeInt(this.addDays);
        parcel.writeInt(this.couponKind);
        parcel.writeString(this.beginHour);
        parcel.writeString(this.endHour);
        parcel.writeInt(this.hourCoupon);
        parcel.writeInt(this.overLap);
        parcel.writeString(this.overLapDesc);
        parcel.writeString(this.uuid);
        parcel.writeString(this.trackId);
        parcel.writeString(this.couponCornerMarkUrl);
        parcel.writeInt(this.isOverLapOpen);
        parcel.writeTypedList(this.skus);
        parcel.writeInt(this.checkSkuNum);
        parcel.writeString(this.price);
        parcel.writeString(this.reprice);
        parcel.writeString(this.needMoney);
        parcel.writeString(this.canDiscount);
        parcel.writeString(this.low);
        parcel.writeString(this.high);
        parcel.writeInt(this.couponStyle);
        parcel.writeString(this.discountDesc);
        parcel.writeInt(this.discountLevel);
        parcel.writeString(this.discountShotDesc);
        parcel.writeString(this.couponIconStyle);
        parcel.writeLong(this.milliSecond);
        parcel.writeInt(this.plusStyle);
        parcel.writeByte(this.isAvailableTime ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.availableTimeDif);
        parcel.writeInt(this.manjianCouponStyle);
        parcel.writeString(this.manJianPerMsg);
        parcel.writeString(this.discountValueDesc);
        parcel.writeString(this.quotaDesc);
        parcel.writeString(this.storeId);
        parcel.writeStringList(this.iconPrioBottom);
        parcel.writeString(this.couponTips);
    }

    public CartCouponEntry(JDJSONObject jDJSONObject, String str, int i2) {
        this.isOverLapOpen = 0;
        if (jDJSONObject == null) {
            return;
        }
        this.venderId = Long.valueOf(jDJSONObject.optLong("venderId"));
        this.couponId = Long.valueOf(jDJSONObject.optLong("couponId"));
        this.name = jDJSONObject.optString("name");
        this.type = Integer.valueOf(jDJSONObject.optInt("type"));
        this.discount = Integer.valueOf(jDJSONObject.optInt("discount"));
        this.preciseDiscount = jDJSONObject.optString("preciseDiscount");
        this.quota = Integer.valueOf(jDJSONObject.optInt("quota"));
        this.preciseQuota = jDJSONObject.optString("preciseQuota");
        this.beginTime = jDJSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME);
        this.endTime = jDJSONObject.optString("endTime");
        this.takeRule = Integer.valueOf(jDJSONObject.optInt(JshopConst.JSKEY_COUPON_TAKERULE));
        this.remain = Integer.valueOf(jDJSONObject.optInt(JshopConst.JSKEY_COUPON_REAIN));
        this.applicability = Boolean.valueOf(jDJSONObject.optBoolean(JshopConst.JSKEY_APPLI));
        this.act = jDJSONObject.optString("act");
        this.platformType = Integer.valueOf(jDJSONObject.optInt(JshopConst.JSKEY_COUPON_PLATFORM));
        this.mBatchId = jDJSONObject.optInt(JshopConst.JSKEY_BATCH_ID);
        this.shopType = i2;
        this.mJshopName = str;
        this.limitType = jDJSONObject.optInt(CartConstant.KEY_SKU_LIMITTYPE);
        this.addDays = jDJSONObject.optInt("addDays");
        this.couponKind = jDJSONObject.optInt("couponKind", 1);
        this.beginHour = jDJSONObject.optString("beginHour");
        this.endHour = jDJSONObject.optString("endHour");
        this.hourCoupon = jDJSONObject.optInt("hourCoupon", 1);
        this.overLap = jDJSONObject.optInt("overLap", 1);
        this.overLapDesc = jDJSONObject.optString("overLapDesc");
        this.uuid = jDJSONObject.optString("uuid");
        this.trackId = jDJSONObject.optString("trackId");
        this.couponCornerMarkUrl = jDJSONObject.optString("couponCornerMarkUrl");
        this.shopId = Long.valueOf(jDJSONObject.optLong("shopId"));
        this.vendorId = Long.valueOf(jDJSONObject.optLong("vendorId"));
        this.shopName = jDJSONObject.optString("shopName");
        this.btActivityId = jDJSONObject.optString("btActivityId");
        this.validPeriodDesc = jDJSONObject.optString("validPeriodDesc");
        this.isShieldCheckBox = jDJSONObject.optBoolean("isShieldCheckBox");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray(CartConstant.KEY_ITEMS);
        if (optJSONArray != null && optJSONArray.size() > 0) {
            this.skus = new ArrayList<>();
            for (int i3 = 0; i3 < optJSONArray.size(); i3++) {
                JDJSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                if (optJSONObject != null) {
                    this.skus.add(new CartCouponSkuItem(optJSONObject));
                }
            }
        }
        this.checkSkuNum = jDJSONObject.optInt("checkSkuNum", 0);
        this.price = jDJSONObject.optString("price");
        this.reprice = jDJSONObject.optString(CartConstant.KEY_CART_TEXTINFO_REPRICE);
        this.needMoney = jDJSONObject.optString(CartConstant.KEY_GLOBAL_NEED_MONEY);
        this.canDiscount = jDJSONObject.optString("canDiscount");
        this.low = jDJSONObject.optString("low");
        this.high = jDJSONObject.optString("high");
        this.couponStyle = jDJSONObject.optInt("couponStyle");
        this.discountDesc = jDJSONObject.optString("discountDesc");
        this.discountLevel = jDJSONObject.optInt("discountLevel");
        this.discountShotDesc = jDJSONObject.optString("discountShotDesc");
        this.couponIconStyle = jDJSONObject.optString("couponIconStyle");
        this.milliSecond = jDJSONObject.optLong("milliSecond");
        this.plusStyle = jDJSONObject.optInt("plusStyle");
        this.isAvailableTime = jDJSONObject.optBoolean("isAvailableTime");
        this.availableTimeDif = jDJSONObject.optLong("availableTimeDif");
        this.manjianCouponStyle = jDJSONObject.optInt("manjianCouponStyle");
        this.manJianPerMsg = jDJSONObject.optString("manJianPerMsg");
        JDJSONObject optJSONObject2 = jDJSONObject.optJSONObject("couponConfigurationStyles");
        if (optJSONObject2 != null) {
            JDJSONObject optJSONObject3 = optJSONObject2.optJSONObject("generalModel");
            if (optJSONObject3 != null) {
                this.generalModelCouponConfigurationInfo = CartCouponConfigurationInfo.parseCartCouponConfigurationInfo(optJSONObject3);
            }
            JDJSONObject optJSONObject4 = optJSONObject2.optJSONObject(PersonalConstants.FUNCTION_ID_DARK_MODE);
            if (optJSONObject4 != null) {
                this.darkModelCouponConfigurationInfo = CartCouponConfigurationInfo.parseCartCouponConfigurationInfo(optJSONObject4);
            }
        }
        this.discountValueDesc = jDJSONObject.optString("discountValueDesc");
        this.quotaDesc = jDJSONObject.optString("quotaDesc");
        this.closable = Boolean.valueOf(jDJSONObject.optBoolean("closable"));
        this.couponSource = jDJSONObject.optString("couponSource", "-100");
        this.couponSourceDetail = jDJSONObject.optString("couponSourceDetail", "-100");
        this.channelDetail = jDJSONObject.optString("channelDetail", "-100");
        this.biinfo = jDJSONObject.optString("biinfo", "-100");
        this.appid = jDJSONObject.optString("appid", "-100");
        this.platformid = jDJSONObject.optString("platformid", "-100");
        this.storeId = jDJSONObject.optString("storeId");
        this.jumpUrl = jDJSONObject.optString(CartConstant.KEY_JUMPURL);
        this.jumpType = jDJSONObject.optInt("jumpType", 0);
        JDJSONArray optJSONArray2 = jDJSONObject.optJSONArray("iconPrioBottom");
        if (optJSONArray2 != null && optJSONArray2.size() > 0) {
            this.iconPrioBottom = new ArrayList<>();
            for (int i4 = 0; i4 < optJSONArray2.size(); i4++) {
                String optString = optJSONArray2.optString(i4);
                if (optString != null) {
                    this.iconPrioBottom.add(optString);
                }
            }
        }
        this.couponTips = jDJSONObject.optString("couponTips");
        this.couponCouDeg = jDJSONObject.optString("couponCouDeg");
        this.isNoQuota = jDJSONObject.optBoolean("isNoQuota");
    }

    public String getCouponFlag() {
        return this.couponId + this.mBatchId + this.beginTime + this.endTime;
    }

    protected CartCouponEntry(Parcel parcel) {
        this.isOverLapOpen = 0;
        this.venderId = (Long) parcel.readValue(Long.class.getClassLoader());
        this.couponId = (Long) parcel.readValue(Long.class.getClassLoader());
        this.name = parcel.readString();
        this.type = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.platformType = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.discount = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.preciseDiscount = parcel.readString();
        this.quota = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.preciseQuota = parcel.readString();
        this.beginTime = parcel.readString();
        this.endTime = parcel.readString();
        this.takeRule = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.remain = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.applicability = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.act = parcel.readString();
        this.mJshopName = parcel.readString();
        this.btActivityId = parcel.readString();
        this.validPeriodDesc = parcel.readString();
        this.mBatchId = parcel.readLong();
        this.shopType = parcel.readInt();
        this.limitType = parcel.readInt();
        this.addDays = parcel.readInt();
        this.couponKind = parcel.readInt();
        this.beginHour = parcel.readString();
        this.endHour = parcel.readString();
        this.hourCoupon = parcel.readInt();
        this.hourCoupon = parcel.readInt();
        this.overLapDesc = parcel.readString();
        this.uuid = parcel.readString();
        this.trackId = parcel.readString();
        this.couponCornerMarkUrl = parcel.readString();
        this.isOverLapOpen = parcel.readInt();
        this.skus = parcel.createTypedArrayList(CartCouponSkuItem.CREATOR);
        this.checkSkuNum = parcel.readInt();
        this.price = parcel.readString();
        this.reprice = parcel.readString();
        this.needMoney = parcel.readString();
        this.canDiscount = parcel.readString();
        this.low = parcel.readString();
        this.high = parcel.readString();
        this.couponStyle = parcel.readInt();
        this.discountDesc = parcel.readString();
        this.discountLevel = parcel.readInt();
        this.discountShotDesc = parcel.readString();
        this.couponIconStyle = parcel.readString();
        this.milliSecond = parcel.readLong();
        this.plusStyle = parcel.readInt();
        this.isAvailableTime = parcel.readByte() != 0;
        this.availableTimeDif = parcel.readLong();
        this.manjianCouponStyle = parcel.readInt();
        this.manJianPerMsg = parcel.readString();
        this.discountValueDesc = parcel.readString();
        this.quotaDesc = parcel.readString();
        this.storeId = parcel.readString();
        this.iconPrioBottom = parcel.createStringArrayList();
        this.couponTips = parcel.readString();
    }
}
