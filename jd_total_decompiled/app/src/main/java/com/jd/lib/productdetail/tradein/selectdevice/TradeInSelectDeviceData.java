package com.jd.lib.productdetail.tradein.selectdevice;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInSelectDeviceData implements Serializable {
    public String code;
    public Data data;

    /* loaded from: classes16.dex */
    public static class Data implements Serializable {
        public CategoriesInfo categoriesInfo;
        public CategoryIdListForClap categoryIdListForClap;
        public devicesInfo devicesInfo;
        public InquiriesInfo inquiriesInfo;
        public String limitCountImg;
        public String oldProductLimitCountText;
        public TagsInfo tagsInfo;

        /* loaded from: classes16.dex */
        public static class CategoriesInfo implements Serializable {
            public List<CateItem> categories;
            public CateItem mCurrentCate;

            /* loaded from: classes16.dex */
            public static class CateItem implements Serializable {
                public String categoryId;
                public String categoryName;
                public String jdCat;
                public String ruleId;
                public boolean selected;

                public boolean isValid() {
                    return !TextUtils.isEmpty(this.categoryName);
                }

                public String toString() {
                    return "CateItem{categoryId='" + this.categoryId + "'categoryName='" + this.categoryName + "'}";
                }
            }

            public boolean isValid() {
                List<CateItem> list = this.categories;
                return list != null && list.size() > 0;
            }
        }

        /* loaded from: classes16.dex */
        public static class CategoryIdListForClap implements Serializable {
            public int bizCode;
            public ArrayList<CateItem> categories;

            /* loaded from: classes16.dex */
            public static class CateItem implements Serializable {
                public long categoryId;
                public String categoryName;
                public boolean selected;

                public boolean isValid() {
                    return !TextUtils.isEmpty(this.categoryName);
                }

                public String toString() {
                    return "CateItem{categoryId='" + this.categoryId + "'categoryName='" + this.categoryName + "'}";
                }
            }

            public boolean isValid() {
                ArrayList<CateItem> arrayList = this.categories;
                return arrayList != null && arrayList.size() > 0;
            }
        }

        /* loaded from: classes16.dex */
        public static class InquiriesInfo implements Serializable {
            public ArrayList<OldProductInquiries> oldProductInquiries;
            public int pageNo;
            public int pageSize;
            public String searchDefaultText;
            public int totalNumber;

            /* loaded from: classes16.dex */
            public static class OldProductInquiries implements Serializable {
                public String attrId;
                public String attrName;
                public String fullImageUrl;
                public String inquiryId;
                public String inquiryName;
                public boolean leaf;
                public int legacyMode;
                public String oldProductId;
                public String oldProductLogoText;
                public String oldProductName;
                public String oldProductNameIntroductionText;
                public String price;
                public String productName;
                public String venderId;

                public boolean isValid() {
                    return true;
                }
            }

            public boolean isValid() {
                ArrayList<OldProductInquiries> arrayList = this.oldProductInquiries;
                return arrayList != null && arrayList.size() > 0;
            }
        }

        /* loaded from: classes16.dex */
        public static class TagsInfo implements Serializable {
            public TagItem mCurrentTag;
            public List<TagItem> tagInfoList;

            /* loaded from: classes16.dex */
            public static class TagItem implements Serializable {
                public static final int TAG_TYPE_3C = 1;
                public static final int TAG_TYPE_PP = 2;
                public boolean enable;
                public boolean selected;
                public String tagId;
                public String tagName;
                public int tagType;

                public boolean isValid() {
                    int i2 = this.tagType;
                    return i2 == 1 || i2 == 2;
                }
            }

            public int getCurrentIndex() {
                try {
                    List<TagItem> list = this.tagInfoList;
                    if (list != null) {
                        TagItem tagItem = this.mCurrentTag;
                        if (tagItem != null) {
                            return list.indexOf(tagItem);
                        }
                        for (int i2 = 0; i2 < this.tagInfoList.size(); i2++) {
                            if (this.tagInfoList.get(i2).selected) {
                                return i2;
                            }
                        }
                        return -1;
                    }
                    return -1;
                } catch (Exception unused) {
                    return -1;
                }
            }

            public boolean isValid() {
                List<TagItem> list = this.tagInfoList;
                return list != null && list.size() > 0;
            }
        }

        /* loaded from: classes16.dex */
        public static class devicesInfo implements Serializable {
            public ArrayList<OwnedDevice> deviceInfoList;
            public HeaderTitleTwo headerTitleTwo;

            /* loaded from: classes16.dex */
            public static class HeaderTitleTwo implements Serializable {
                public String icon;
                public String text;
            }

            /* loaded from: classes16.dex */
            public static class OwnedDevice implements Serializable {
                public String deviceAmount;
                public String deviceId;
                public String deviceName;
                public String devicePicUrl;
                public String inquiryParentId;
                public String inquiryParentName;
                public String itemStr;
                public String purchasedYears;
                public String workId;

                public boolean isValid() {
                    return (TextUtils.isEmpty(this.deviceAmount) || TextUtils.isEmpty(this.deviceId) || TextUtils.isEmpty(this.deviceName) || TextUtils.isEmpty(this.inquiryParentId) || TextUtils.isEmpty(this.inquiryParentName) || TextUtils.isEmpty(this.itemStr)) ? false : true;
                }
            }
        }
    }

    public boolean isValid() {
        return TextUtils.equals(this.code, "0") && this.data != null;
    }
}
