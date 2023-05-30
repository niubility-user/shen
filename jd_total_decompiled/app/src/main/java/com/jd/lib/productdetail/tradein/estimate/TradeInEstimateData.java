package com.jd.lib.productdetail.tradein.estimate;

import android.text.TextUtils;
import com.jd.lib.productdetail.tradein.bean.TradeInTitleInfo;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: classes16.dex */
public class TradeInEstimateData implements Serializable {
    public int code;
    public Data data;
    public boolean success;

    /* loaded from: classes16.dex */
    public static class Data implements Serializable {
        public Bottom bottom;
        public InquiryItemInfo inquiryItemInfo;
        public LocalSubsidyInfo localSubsidyInfo;
        public TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInfo;
        public TradeInTitleInfo ruleInfo;
        public String servicePointText;
        public TradeInSelectDeviceData.Data.TagsInfo.TagItem tagInfo;

        /* loaded from: classes16.dex */
        public static class Bottom implements Serializable {
            public String ruleAndIssues;
            public String ruleAndIssuesText;
            public String ruleAndIssuesTitle;
        }

        /* loaded from: classes16.dex */
        public static class InquiryItemInfo implements Serializable {
            public String headNodeId;
            public LinkedHashMap<String, InquiryItemPropertiesMap> inquiryItemPropertiesMap;
            public int legacyMode;
            public ArrayList<InquiryItemPropertiesMap> mMultiInquiryItems = new ArrayList<>();
            public ArrayList<String> unRecyclablePvIds;
            public ArrayList<ArrayList<String>> validItemPvIdGroup;

            /* loaded from: classes16.dex */
            public static class InquiryItemPropertiesMap implements Serializable {
                public static final int PROP_TYPE_BASE = 1;
                public static final int PROP_TYPE_USER = 2;
                public String attrId;
                public String attrName;
                public int attrType;
                public String id;
                public String imageUrls;
                public ArrayList<InquiryItemBasePropValList> inquiryItemBasePropValList;
                public InquiryItemBasePropValList mCurrentVal;
                public String price;
                public int sortNum;
                public String textTip;
                public boolean mExpanded = true;
                public int attrProptype = 2;

                /* loaded from: classes16.dex */
                public static class InquiryItemBasePropValList implements Serializable {
                    public String attrId;
                    public String attrName;
                    public String attrValId;
                    public boolean hasNextKey;
                    public String imageUrls;
                    public boolean mIsSelectAble;
                    public String nextKey;
                    public String price;
                    public int sortNum;
                    public String textTip;
                    public int valType;
                    public String valueName;
                }

                public InquiryItemBasePropValList getMultiAttrByValType(int i2) {
                    if (isMulti()) {
                        Iterator<InquiryItemBasePropValList> it = this.inquiryItemBasePropValList.iterator();
                        while (it.hasNext()) {
                            InquiryItemBasePropValList next = it.next();
                            if (next.valType == i2) {
                                return next;
                            }
                        }
                    }
                    return null;
                }

                public boolean isMulti() {
                    return this.attrProptype == 2 && this.attrType == 4;
                }

                public void reverseCurrentAttrWhenMulti() {
                    if (!isMulti() || this.mCurrentVal == null) {
                        return;
                    }
                    Iterator<InquiryItemBasePropValList> it = this.inquiryItemBasePropValList.iterator();
                    while (it.hasNext()) {
                        InquiryItemBasePropValList next = it.next();
                        if (next.valType != this.mCurrentVal.valType) {
                            this.mCurrentVal = next;
                            return;
                        }
                    }
                }
            }

            public int getMaxProgress() {
                Collection<InquiryItemPropertiesMap> values;
                LinkedHashMap<String, InquiryItemPropertiesMap> linkedHashMap = this.inquiryItemPropertiesMap;
                int i2 = 0;
                if (linkedHashMap != null && (values = linkedHashMap.values()) != null) {
                    Iterator<InquiryItemPropertiesMap> it = values.iterator();
                    while (it.hasNext()) {
                        if (!it.next().isMulti()) {
                            i2++;
                        }
                    }
                }
                return i2;
            }

            public InquiryItemPropertiesMap getNextProp(InquiryItemPropertiesMap inquiryItemPropertiesMap) {
                LinkedHashMap<String, InquiryItemPropertiesMap> linkedHashMap;
                InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList = inquiryItemPropertiesMap.mCurrentVal;
                if (inquiryItemBasePropValList == null || !inquiryItemBasePropValList.hasNextKey || TextUtils.isEmpty(inquiryItemBasePropValList.nextKey) || (linkedHashMap = this.inquiryItemPropertiesMap) == null || !linkedHashMap.containsKey(inquiryItemPropertiesMap.mCurrentVal.nextKey)) {
                    return null;
                }
                InquiryItemPropertiesMap inquiryItemPropertiesMap2 = this.inquiryItemPropertiesMap.get(inquiryItemPropertiesMap.mCurrentVal.nextKey);
                return inquiryItemPropertiesMap2.isMulti() ? getNextProp(inquiryItemPropertiesMap2) : inquiryItemPropertiesMap2;
            }
        }

        /* loaded from: classes16.dex */
        public static class LocalSubsidyInfo implements Serializable {
            public String evaluationInfoText;
            public String subsidyInfoText;
        }

        public int getMaxProgress() {
            InquiryItemInfo inquiryItemInfo = this.inquiryItemInfo;
            if (inquiryItemInfo != null) {
                return inquiryItemInfo.getMaxProgress();
            }
            return 0;
        }

        public int getTagType() {
            TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem = this.tagInfo;
            if (tagItem == null || !tagItem.isValid()) {
                return 0;
            }
            return this.tagInfo.tagType;
        }

        public boolean isValid() {
            return true;
        }
    }

    public int getMaxProgress() {
        Data.InquiryItemInfo inquiryItemInfo;
        if (!isValid() || (inquiryItemInfo = this.data.inquiryItemInfo) == null) {
            return 0;
        }
        return inquiryItemInfo.getMaxProgress();
    }

    public boolean isValid() {
        Data data;
        return this.code == 0 && (data = this.data) != null && data.isValid();
    }
}
