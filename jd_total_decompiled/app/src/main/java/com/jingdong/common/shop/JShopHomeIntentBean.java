package com.jingdong.common.shop;

import android.os.Parcel;
import android.os.Parcelable;
import com.jd.framework.json.anotation.JSONField;
import java.util.List;

/* loaded from: classes.dex */
public class JShopHomeIntentBean {
    public static final int TARGET_PAGE_ALL_PRODUCT = 1;
    public static final int TARGET_PAGE_DYNAMIC = 4;
    public static final int TARGET_PAGE_HOME = 0;
    public static final int TARGET_PAGE_NEW = 3;
    public static final int TARGET_PAGE_PROMOTION = 2;
    private AllProductSort allProductSort;
    public String complexSource;
    private FloatProduct floatProduct;
    public String shopId;
    @JSONField(name = "shopname")
    public String shopName;
    public String targetPage;
    public String venderId;

    /* loaded from: classes6.dex */
    public static class AllProductSort implements Parcelable {
        public static final Parcelable.Creator<AllProductSort> CREATOR = new Parcelable.Creator<AllProductSort>() { // from class: com.jingdong.common.shop.JShopHomeIntentBean.AllProductSort.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AllProductSort createFromParcel(Parcel parcel) {
                return new AllProductSort(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AllProductSort[] newArray(int i2) {
                return new AllProductSort[i2];
            }
        };
        public String sku;
        public List<String> skuArray;

        public AllProductSort() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.sku);
            parcel.writeStringList(this.skuArray);
        }

        protected AllProductSort(Parcel parcel) {
            this.sku = parcel.readString();
            this.skuArray = parcel.createStringArrayList();
        }
    }

    /* loaded from: classes6.dex */
    public static class FloatProduct implements Parcelable {
        public static final Parcelable.Creator<FloatProduct> CREATOR = new Parcelable.Creator<FloatProduct>() { // from class: com.jingdong.common.shop.JShopHomeIntentBean.FloatProduct.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FloatProduct createFromParcel(Parcel parcel) {
                return new FloatProduct(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public FloatProduct[] newArray(int i2) {
                return new FloatProduct[i2];
            }
        };
        public String abTest;
        public String cateId;
        public String cateName;
        public String sku;

        public FloatProduct() {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.sku);
            parcel.writeString(this.cateId);
            parcel.writeString(this.cateName);
            parcel.writeString(this.abTest);
        }

        protected FloatProduct(Parcel parcel) {
            this.sku = parcel.readString();
            this.cateId = parcel.readString();
            this.cateName = parcel.readString();
            this.abTest = parcel.readString();
        }
    }

    public AllProductSort getAllProductSort() {
        return this.allProductSort;
    }

    public FloatProduct getFloatProduct() {
        return this.floatProduct;
    }

    public int getTargetPageType() {
        if ("allProduct".equals(this.targetPage)) {
            return 1;
        }
        if ("promotion".equals(this.targetPage)) {
            return 2;
        }
        if ("new".equals(this.targetPage)) {
            return 3;
        }
        if ("dynamic".equals(this.targetPage)) {
            return 4;
        }
        "home".equals(this.targetPage);
        return 0;
    }

    public void setAllProductSort(AllProductSort allProductSort) {
        this.allProductSort = allProductSort;
    }

    public void setFloatProduct(FloatProduct floatProduct) {
        this.floatProduct = floatProduct;
    }
}
