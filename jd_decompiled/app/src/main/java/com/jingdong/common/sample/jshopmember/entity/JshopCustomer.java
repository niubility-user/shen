package com.jingdong.common.sample.jshopmember.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JshopCustomer implements Parcelable {
    public static final Parcelable.Creator<JshopCustomer> CREATOR = new Parcelable.Creator<JshopCustomer>() { // from class: com.jingdong.common.sample.jshopmember.entity.JshopCustomer.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopCustomer createFromParcel(Parcel parcel) {
            return new JshopCustomer(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public JshopCustomer[] newArray(int i2) {
            return new JshopCustomer[i2];
        }
    };
    public CustomerBean customer;
    public List<String> customerPrivilege;
    public boolean isFollowed;
    public boolean isPointsEnabled;
    public boolean isShopCustomer;
    public String mJshopId;
    public String mVendorId;
    public List<CouponForPoint> points2Coupon;
    public PointsEntrance pointsEntrance;
    public List<Privilege2Customer> privilege2CustomerList;
    public List<ShopRulesBean> shopRules;

    public JshopCustomer(JDJSONObject jDJSONObject) {
        this.mVendorId = "";
        this.mJshopId = "";
        this.isFollowed = false;
        this.customerPrivilege = new ArrayList();
        this.points2Coupon = new ArrayList();
        this.shopRules = new ArrayList();
        this.privilege2CustomerList = new ArrayList();
        if (jDJSONObject != null) {
            this.isShopCustomer = jDJSONObject.optBoolean("isShopCustomer");
            this.isPointsEnabled = jDJSONObject.optBoolean("isPointsEnabled");
            this.isFollowed = jDJSONObject.optBoolean(JshopConst.FOLLOWED_KEY);
            this.customer = new CustomerBean(jDJSONObject.optJSONObject("customer"));
            if (jDJSONObject.optJSONObject("pointsEntrance") != null) {
                this.pointsEntrance = new PointsEntrance(jDJSONObject.optJSONObject("pointsEntrance"));
            }
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("customerPrivilegeList");
            if (optJSONArray != null && optJSONArray.size() > 0) {
                int size = optJSONArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.privilege2CustomerList.add(new Privilege2Customer(optJSONArray.optJSONObject(i2)));
                }
            }
            JDJSONArray optJSONArray2 = jDJSONObject.optJSONArray("customerPrivilege");
            if (optJSONArray2 != null && optJSONArray2.size() > 0) {
                for (int i3 = 0; i3 < optJSONArray2.size(); i3++) {
                    String optString = optJSONArray2.optString(i3);
                    if (!TextUtils.isEmpty(optString)) {
                        this.customerPrivilege.add(optString);
                    }
                }
            }
            String str = this.customer.customerLevel;
            JDJSONArray optJSONArray3 = jDJSONObject.optJSONArray("points2Coupon");
            if (optJSONArray3 != null && optJSONArray3.size() > 0) {
                for (int i4 = 0; i4 < optJSONArray3.size(); i4++) {
                    JDJSONObject optJSONObject = optJSONArray3.optJSONObject(i4);
                    if (optJSONObject != null) {
                        this.points2Coupon.add(new CouponForPoint(optJSONObject));
                    }
                }
            }
            JDJSONArray optJSONArray4 = jDJSONObject.optJSONArray("shopRules");
            if (optJSONArray4 == null || optJSONArray4.size() <= 0) {
                return;
            }
            for (int i5 = 0; i5 < optJSONArray4.size(); i5++) {
                JDJSONObject optJSONObject2 = optJSONArray4.optJSONObject(i5);
                if (optJSONObject2 != null) {
                    ShopRulesBean shopRulesBean = new ShopRulesBean(optJSONObject2);
                    if (!TextUtils.isEmpty(str) && str.equals(shopRulesBean.curGrade)) {
                        shopRulesBean.isCurrentLevel = true;
                    } else {
                        shopRulesBean.isCurrentLevel = false;
                    }
                    this.shopRules.add(shopRulesBean);
                }
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.mVendorId);
        parcel.writeString(this.mJshopId);
        parcel.writeByte(this.isFollowed ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isPointsEnabled ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isShopCustomer ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.customer, i2);
        parcel.writeStringList(this.customerPrivilege);
        parcel.writeTypedList(this.points2Coupon);
        parcel.writeTypedList(this.shopRules);
        parcel.writeTypedList(this.privilege2CustomerList);
        parcel.writeParcelable(this.pointsEntrance, i2);
    }

    protected JshopCustomer(Parcel parcel) {
        this.mVendorId = "";
        this.mJshopId = "";
        this.isFollowed = false;
        this.customerPrivilege = new ArrayList();
        this.points2Coupon = new ArrayList();
        this.shopRules = new ArrayList();
        this.privilege2CustomerList = new ArrayList();
        this.mVendorId = parcel.readString();
        this.mJshopId = parcel.readString();
        this.isFollowed = parcel.readByte() != 0;
        this.isPointsEnabled = parcel.readByte() != 0;
        this.isShopCustomer = parcel.readByte() != 0;
        this.customer = (CustomerBean) parcel.readParcelable(CustomerBean.class.getClassLoader());
        this.customerPrivilege = parcel.createStringArrayList();
        this.points2Coupon = parcel.createTypedArrayList(CouponForPoint.CREATOR);
        this.shopRules = parcel.createTypedArrayList(ShopRulesBean.CREATOR);
        this.privilege2CustomerList = parcel.createTypedArrayList(Privilege2Customer.CREATOR);
        this.pointsEntrance = (PointsEntrance) parcel.readParcelable(PointsEntrance.class.getClassLoader());
    }
}
