package com.jd.lib.productdetail.tradein.inform;

import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.bean.TradeInTitleInfo;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import java.io.Serializable;
import java.util.LinkedHashMap;

/* loaded from: classes16.dex */
public class TradeInInformData implements Serializable {
    public static final int DATATYPE_HAS_INFO = 2;
    public static final int DATATYPE_HAS_NO_INFO = 3;
    public static final int DATATYPE_RESULT = 4;
    public int code;
    public Data data;
    public String msg;
    public boolean success;

    /* loaded from: classes16.dex */
    public static class Data implements Serializable {
        public TradeInEstimateData.Data localMachineInfo;
        public TradeinInformInfo noHaveLocalMachineInfo;
        public int oldProductDetailDataType;
        public TradeInResultData oldProductListInfo;
        public TradeInTitleInfo ruleInfo;
        public String servicePointText;

        /* loaded from: classes16.dex */
        public static class TradeinInformInfo implements Serializable {
            public String chooseProductText;
            public NoHaveLocalSubsidyInfo noHaveLocalSubsidyInfo;
            public OldProductDetailBottom oldProductDetailBottom;
            public String oldProductImageUrl;
            public String oldProductText;
            public String remindExchangeText;
            public TradeInTitleInfo ruleInfo;
            public String servicePointText;

            /* loaded from: classes16.dex */
            public static class NoHaveLocalSubsidyInfo implements Serializable {
                public OldUserSubsidyIntroduction oldUserSubsidyIntroduction;
                public String subsidyInfoText;

                /* loaded from: classes16.dex */
                public static class OldUserSubsidyIntroduction implements Serializable {
                    public String oldUserSubsidyText;
                    public String oldUserSubsidyTitle;
                }
            }

            /* loaded from: classes16.dex */
            public static class OldProductDetailBottom implements Serializable {
                public String ruleAndIssues;
                public String ruleAndIssuesText;
                public String ruleAndIssuesTitle;
            }

            public boolean isValid() {
                return true;
            }
        }

        public TradeInStep getDestStep() {
            TradeInEstimateData.Data data;
            TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem;
            TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries;
            TradeInEstimateData.Data.InquiryItemInfo inquiryItemInfo;
            LinkedHashMap<String, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> linkedHashMap;
            TradeinInformInfo tradeinInformInfo;
            if (this.oldProductDetailDataType == 3 && (tradeinInformInfo = this.noHaveLocalMachineInfo) != null && tradeinInformInfo.isValid()) {
                return TradeInStep.INFORM;
            }
            if (this.oldProductDetailDataType == 2 && (data = this.localMachineInfo) != null && data.isValid() && (tagItem = this.localMachineInfo.tagInfo) != null && tagItem.isValid() && (oldProductInquiries = this.localMachineInfo.oldProductInfo) != null && oldProductInquiries.isValid() && (inquiryItemInfo = this.localMachineInfo.inquiryItemInfo) != null && (linkedHashMap = inquiryItemInfo.inquiryItemPropertiesMap) != null && linkedHashMap.size() > 0) {
                return TradeInStep.ESTIMATE;
            }
            if (this.oldProductDetailDataType == 4 && this.oldProductListInfo != null) {
                return TradeInStep.TRADEIN;
            }
            TradeinInformInfo tradeinInformInfo2 = this.noHaveLocalMachineInfo;
            if (tradeinInformInfo2 == null || !tradeinInformInfo2.isValid()) {
                return null;
            }
            return TradeInStep.INFORM;
        }
    }
}
