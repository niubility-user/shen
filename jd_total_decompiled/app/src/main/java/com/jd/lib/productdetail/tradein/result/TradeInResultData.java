package com.jd.lib.productdetail.tradein.result;

import android.content.Context;
import android.text.TextUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.bean.TradeInServiceInfo;
import com.jd.lib.productdetail.tradein.bean.TradeInTitleInfo;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jd.lib.productdetail.tradein.inform.TradeInInformData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes16.dex */
public class TradeInResultData implements Serializable {
    public List<String> abTouchStones;
    public TradeInFloorData accountStatement;
    public BarterButtonInfo buttonInfo;
    public TradInBarterCardE eCard;
    public BatterFreeFa freeFa;
    public boolean mHasCheckedOldDevice = false;
    public String minPriceLimitButtonToast;
    public TradeInInformData.Data.TradeinInformInfo noHaveLocalMachineInfo;
    public TradeInWareCardInfo oldWare1;
    public TradeInWareCardInfo oldWare2;
    public int oldWareLimit;
    public TradeInFloorData oldWareSubsidy;
    public BarterProtocol protocol;
    public TradeInTitleInfo ruleInfo;
    public HashMap<String, Object> saveInfo;
    public long storeId;
    public ArrayList<SubsidyInfos> subsidyInfos;
    public String title;
    public String tradeTypeRefreshToast;
    public TradeInWareCardInfo wareNew;
    public TradeInFloorData wareNewCoupon;
    public TradeInFloorData wareNewSubsidy;

    /* loaded from: classes16.dex */
    public static class BarterButtonInfo implements Serializable {
        public BarterButton main;
        public BarterButton second;

        /* loaded from: classes16.dex */
        public static class BarterButton implements Serializable {
            public String bgColor;
            public String name;
            public int scene;
            public int source;
            public String subName;
            public String textColor;
            public int type;
            public String url;

            public boolean isValid() {
                return (TextUtils.isEmpty(this.name) || TextUtils.isEmpty(this.bgColor) || TextUtils.isEmpty(this.textColor)) ? false : true;
            }
        }

        public boolean isValid() {
            BarterButton barterButton;
            BarterButton barterButton2 = this.main;
            return barterButton2 != null && barterButton2.isValid() && ((barterButton = this.second) == null || barterButton.isValid());
        }
    }

    /* loaded from: classes16.dex */
    public static class BarterProtocol implements Serializable {
        public String text1;
        public String text2;
        public String url;

        public boolean isValid() {
            return (TextUtils.isEmpty(this.text1) || TextUtils.isEmpty(this.text2) || TextUtils.isEmpty(this.url)) ? false : true;
        }
    }

    /* loaded from: classes16.dex */
    public static class BarterText implements Serializable {
        public int colorLength;
        public BarterOperateEvent event;
        public int recycleDemand;
        public int replacementMode;
        public String saveText;
        public int sizeLength;
        public int subReplacementMode;
        public String text;
        public int colorLocation = -1;
        public int sizeLocation = -1;
        public int tradeTypeRefreshTarget = -1;

        /* loaded from: classes16.dex */
        public static class BarterOperateEvent implements Serializable {
            public int biz;
            public Data data;

            /* loaded from: classes16.dex */
            public static class Data implements Serializable {
                public String bankCardId;
                public String bankCardName;
                public boolean canJump;
                public String cardNoEnd;
                public TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate;
                public boolean hasCard;
                public String jumpBiz;
                public ArrayList<TradeInFloorData> oldWareSubsidyEventDataList;
                public ArrayList<ReplacementModeList> replacementModeList;
                public TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo storeInfo;
                public int tradeModeId;
                public String tradeModeName;

                /* loaded from: classes16.dex */
                public static class ReplacementModeList implements Serializable {
                    public int recycleDemand;
                    public int replacementMode;
                    public int subReplacementMode;
                    public String t1;
                    public String t2;
                    public int tradeTypeRefreshTarget = -1;
                    public boolean showTradeDate = true;

                    public String toString() {
                        return "ReplacementModeList{t1='" + this.t1 + "', t2='" + this.t2 + "', replacementMode=" + this.replacementMode + ", tradeTypeRefreshTarget=" + this.tradeTypeRefreshTarget + ", showTradeDate=" + this.showTradeDate + '}';
                    }
                }

                public boolean isBankValid() {
                    return (!this.hasCard || TextUtils.isEmpty(this.cardNoEnd) || TextUtils.isEmpty(this.bankCardName) || TextUtils.isEmpty(this.bankCardId)) ? false : true;
                }

                public boolean isOldWareSubsidyValid() {
                    ArrayList<TradeInFloorData> arrayList = this.oldWareSubsidyEventDataList;
                    return arrayList != null && arrayList.size() > 0;
                }

                public boolean isTranModeValid() {
                    TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate;
                    TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate2;
                    TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo storeInfo;
                    int i2 = this.tradeModeId;
                    return (i2 == 3 ? !((defaultPromiseDate = this.defaultPromiseDate) == null || TextUtils.isEmpty(defaultPromiseDate.date)) : !(i2 == 4 ? (defaultPromiseDate2 = this.defaultPromiseDate) == null || TextUtils.isEmpty(defaultPromiseDate2.date) || TextUtils.isEmpty(this.defaultPromiseDate.startTime) || TextUtils.isEmpty(this.defaultPromiseDate.endTime) : i2 != 5 || (storeInfo = this.storeInfo) == null || storeInfo.storeId == 0 || TextUtils.isEmpty(storeInfo.storeName))) && !TextUtils.isEmpty(this.tradeModeName);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public static class BatterFreeFa implements Serializable {
        public String text1;
        public String text2;
        public String text3;

        public boolean isValid() {
            return (TextUtils.isEmpty(this.text1) && TextUtils.isEmpty(this.text2) && TextUtils.isEmpty(this.text3)) ? false : true;
        }
    }

    /* loaded from: classes16.dex */
    public static class OldContentList implements Serializable {
        public List<String> textList;
        public String title;
    }

    /* loaded from: classes16.dex */
    public static class OldWareSubsidy implements Serializable {
        public String buttonText;
        public List<OldContentList> contentList;
        public String title;
    }

    /* loaded from: classes16.dex */
    public static class SubsidyInfos implements Serializable {
        public int jdBear;
        public int sendType;
        public String subsidyDesc;
        public int subsidyPrice;
        public int venderBear;
    }

    /* loaded from: classes16.dex */
    public static class TradInBarterCardE implements Serializable {
        public String floorContent;
        public String iContent;
        public String iTitle;
    }

    /* loaded from: classes16.dex */
    public static class TradeInFloorData implements Serializable {
        public int biz;
        public String coupon;
        public String iContent;
        public OldWareSubsidy iContent4OldWareSubsidy;
        public BarterFloorRight rightInfo;
        public String text1;
        public boolean show = true;
        public boolean showText2 = true;
        public boolean checkRequired = false;

        /* loaded from: classes16.dex */
        public static class BarterFloorRight implements Serializable {
            public int biz;
            public BarterText text1;
            public BarterText text2;

            public String getText1() {
                BarterText barterText = this.text1;
                if (barterText != null) {
                    return barterText.text;
                }
                return null;
            }

            public String getText2() {
                BarterText barterText = this.text2;
                if (barterText != null) {
                    return barterText.text;
                }
                return null;
            }
        }

        public boolean isValid() {
            BarterText barterText;
            BarterText barterText2;
            BarterText.BarterOperateEvent barterOperateEvent;
            BarterText.BarterOperateEvent.Data data;
            BarterText barterText3;
            BarterText.BarterOperateEvent barterOperateEvent2;
            BarterText.BarterOperateEvent.Data data2;
            int i2 = this.biz;
            if (i2 == 1) {
                BarterFloorRight barterFloorRight = this.rightInfo;
                if (barterFloorRight != null && (barterText = barterFloorRight.text1) != null && barterText.replacementMode != 0) {
                    return true;
                }
            } else if (i2 == 2) {
                BarterFloorRight barterFloorRight2 = this.rightInfo;
                if (barterFloorRight2 != null && (barterText2 = barterFloorRight2.text1) != null && (barterOperateEvent = barterText2.event) != null && (data = barterOperateEvent.data) != null && data.isTranModeValid()) {
                    return true;
                }
            } else if (i2 != 3) {
                return true;
            } else {
                BarterFloorRight barterFloorRight3 = this.rightInfo;
                if (barterFloorRight3 != null && (barterText3 = barterFloorRight3.text1) != null && (barterOperateEvent2 = barterText3.event) != null && (data2 = barterOperateEvent2.data) != null && data2.isBankValid()) {
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes16.dex */
    public static class TradeInWareCardInfo implements Serializable {
        public TradeInFloorData bankCard;
        public int cardType;
        public TradeInFloorData exchangeWareWay;
        public boolean mNewDevice = false;
        public String subsidy;
        public String title;
        public TradeInFloorData tradMode;
        public ArrayList<TradeInWareInfo> wareList;

        /* loaded from: classes16.dex */
        public static class TradeInWareInfo implements Serializable {
            public static final int BTN_TYPE_HSHF = 4;
            public static final int BTN_TYPE_PRICE = 1;
            public static final int BTN_TYPE_PRICE_STYLE = 0;
            public static final int BTN_TYPE_REFRESH = 3;
            public static final int BTN_TYPE_RE_ESTIMATE = 2;
            public int clickStatus;
            public String image;
            public String inquiryId;
            public String landedPriceText;
            public String name;
            public o2nProductDetailedForHouseElectricVO o2nProductDetailedForHouseElectricVO;
            public OldProductInfoForClap oldProductInfoForClap;
            public String price;
            public boolean quoteOk;
            public boolean revisable;
            public TradeInServiceInfo serviceInfo;
            public String subName;
            public int subNameType;
            public TradeInSelectDeviceData.Data.TagsInfo.TagItem tagInfo;
            public String underPriceText;
            public String wareId;

            /* loaded from: classes16.dex */
            public static class OldProductInfoForClap implements Serializable {
                public String inquiryId;
                public String inquiryStatus;
                public OldProductDetailItemSubsidyStatus oldProductDetailItemSubsidyStatus;
                public String oldProductId;
                public String oldProductImageUrl;
                public String oldProductName;
                public String recyclePrice;
                public String sendType;
                public String venderId;

                /* loaded from: classes16.dex */
                public static class OldProductDetailItemSubsidyStatus implements Serializable {
                    public String subsidyStatus;
                }
            }

            /* loaded from: classes16.dex */
            public static class o2nProductDetailedForHouseElectricVO implements Serializable {
                public int aclAmount;
                public ArrayList<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList> choosedInquiryItems;
                public String deviceId;
                public String firstOldItemInfo;
                public String jdOldCatInfo;
                public int legacyMode;
                public String oldProductId;
                public String pathInfo;
                public String ruleId;
                public String ruleName;
                public String tagId;
                public String workerId;
            }

            public boolean isValid() {
                return !TextUtils.isEmpty(this.name);
            }
        }

        public int getCount() {
            ArrayList<TradeInWareInfo> arrayList = this.wareList;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }

        public BarterText.BarterOperateEvent.Data getCurrentBank() {
            TradeInFloorData.BarterFloorRight barterFloorRight;
            BarterText barterText;
            BarterText.BarterOperateEvent barterOperateEvent;
            TradeInFloorData tradeInFloorData = this.bankCard;
            if (tradeInFloorData == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null || (barterOperateEvent = barterText.event) == null) {
                return null;
            }
            return barterOperateEvent.data;
        }

        public BarterText.BarterOperateEvent.Data getCurrentTradeInMode() {
            TradeInFloorData.BarterFloorRight barterFloorRight;
            BarterText barterText;
            BarterText.BarterOperateEvent barterOperateEvent;
            TradeInFloorData tradeInFloorData = this.exchangeWareWay;
            if (tradeInFloorData == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null || (barterOperateEvent = barterText.event) == null) {
                return null;
            }
            return barterOperateEvent.data;
        }

        public int getCurrentTradeType() {
            TradeInFloorData.BarterFloorRight barterFloorRight;
            BarterText barterText;
            TradeInFloorData tradeInFloorData = this.exchangeWareWay;
            if (tradeInFloorData == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null) {
                return -1;
            }
            return barterText.tradeTypeRefreshTarget;
        }

        public BarterText.BarterOperateEvent.Data getCurrentTranMode() {
            TradeInFloorData.BarterFloorRight barterFloorRight;
            BarterText barterText;
            BarterText.BarterOperateEvent barterOperateEvent;
            TradeInFloorData tradeInFloorData = this.tradMode;
            if (tradeInFloorData == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null || (barterOperateEvent = barterText.event) == null) {
                return null;
            }
            return barterOperateEvent.data;
        }

        public ArrayList<String> getInquiryIds() {
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<TradeInWareInfo> arrayList2 = this.wareList;
            if (arrayList2 != null) {
                Iterator<TradeInWareInfo> it = arrayList2.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().inquiryId);
                }
            }
            return arrayList;
        }

        public boolean isValid() {
            if (this.mNewDevice) {
                if (getCount() > 0) {
                    return this.wareList.get(0).isValid();
                }
                return false;
            }
            return true;
        }

        public void setCurrentBank(Context context, String str, boolean z, String str2, String str3) {
            TradeInFloorData.BarterFloorRight barterFloorRight;
            BarterText barterText;
            BarterText.BarterOperateEvent barterOperateEvent;
            TradeInFloorData tradeInFloorData = this.bankCard;
            if (tradeInFloorData == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null || (barterOperateEvent = barterText.event) == null) {
                return;
            }
            BarterText.BarterOperateEvent.Data data = barterOperateEvent.data;
            data.cardNoEnd = str;
            data.hasCard = z;
            data.bankCardName = str2;
            data.bankCardId = str3;
            if (context != null) {
                barterText.text = context.getString(R.string.tradein_result_back_name_format, str2, str);
            }
        }

        public void setCurrentTradeInMode(Context context, BarterText.BarterOperateEvent.Data.ReplacementModeList replacementModeList) {
            TradeInFloorData tradeInFloorData;
            TradeInFloorData.BarterFloorRight barterFloorRight;
            BarterText barterText;
            String str = "setCurrentTradeInMode mode = " + replacementModeList;
            if (replacementModeList == null || (tradeInFloorData = this.exchangeWareWay) == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null || barterText.event == null) {
                return;
            }
            barterText.replacementMode = replacementModeList.replacementMode;
            barterText.recycleDemand = replacementModeList.recycleDemand;
            barterText.subReplacementMode = replacementModeList.subReplacementMode;
            barterText.text = replacementModeList.t1;
            barterText.tradeTypeRefreshTarget = replacementModeList.tradeTypeRefreshTarget;
            BarterText barterText2 = barterFloorRight.text2;
            if (barterText2 != null) {
                barterText2.text = replacementModeList.t2;
            }
            TradeInFloorData tradeInFloorData2 = this.tradMode;
            if (tradeInFloorData2 != null) {
                tradeInFloorData2.showText2 = replacementModeList.showTradeDate;
            }
        }

        public void setCurrentTranMode(Context context, int i2, String str, TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate, TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo storeInfo) {
            TradeInFloorData.BarterFloorRight barterFloorRight;
            BarterText barterText;
            BarterText.BarterOperateEvent barterOperateEvent;
            BarterText barterText2;
            BarterText barterText3;
            String str2 = "setCurrentTranMode tradeModeName = " + str;
            TradeInFloorData tradeInFloorData = this.tradMode;
            if (tradeInFloorData == null || (barterFloorRight = tradeInFloorData.rightInfo) == null || (barterText = barterFloorRight.text1) == null || (barterOperateEvent = barterText.event) == null) {
                return;
            }
            barterText.text = str;
            BarterText.BarterOperateEvent.Data data = barterOperateEvent.data;
            if (data != null) {
                data.tradeModeId = i2;
                data.tradeModeName = str;
                data.defaultPromiseDate = defaultPromiseDate;
                data.storeInfo = storeInfo;
            }
            if (i2 == 3 || i2 == 4) {
                if (defaultPromiseDate == null || (barterText2 = barterFloorRight.text2) == null) {
                    return;
                }
                barterText2.text = defaultPromiseDate.displayText;
                if (this.cardType == 3) {
                    barterText2.saveText = defaultPromiseDate.date;
                }
            } else if (i2 != 5 || (barterText3 = barterFloorRight.text2) == null) {
            } else {
                if (storeInfo != null) {
                    barterText3.text = storeInfo.storeName;
                } else {
                    barterText3.text = "";
                }
            }
        }
    }

    public BarterText.BarterOperateEvent.Data getCurrentBank1() {
        TradeInWareCardInfo tradeInWareCardInfo = this.oldWare1;
        if (tradeInWareCardInfo != null) {
            return tradeInWareCardInfo.getCurrentBank();
        }
        return null;
    }

    public BarterText.BarterOperateEvent.Data getCurrentBank2() {
        TradeInWareCardInfo tradeInWareCardInfo = this.oldWare2;
        if (tradeInWareCardInfo != null) {
            return tradeInWareCardInfo.getCurrentBank();
        }
        return null;
    }

    public int getOldDeviceCount() {
        TradeInWareCardInfo tradeInWareCardInfo = this.oldWare1;
        int count = tradeInWareCardInfo != null ? tradeInWareCardInfo.getCount() : 0;
        TradeInWareCardInfo tradeInWareCardInfo2 = this.oldWare2;
        return count + (tradeInWareCardInfo2 != null ? tradeInWareCardInfo2.getCount() : 0);
    }

    public int getTradeTypeRefreshTarget() {
        TradeInWareCardInfo tradeInWareCardInfo = this.oldWare1;
        if (tradeInWareCardInfo != null && tradeInWareCardInfo.cardType == 4) {
            return tradeInWareCardInfo.getCurrentTradeType();
        }
        TradeInWareCardInfo tradeInWareCardInfo2 = this.oldWare2;
        if (tradeInWareCardInfo2 == null || tradeInWareCardInfo2.cardType != 4) {
            return -1;
        }
        return tradeInWareCardInfo2.getCurrentTradeType();
    }

    public boolean isValid() {
        BarterButtonInfo barterButtonInfo;
        TradeInWareCardInfo tradeInWareCardInfo = this.wareNew;
        return tradeInWareCardInfo != null && tradeInWareCardInfo.isValid() && (barterButtonInfo = this.buttonInfo) != null && barterButtonInfo.isValid();
    }

    public void setCurrentBank(Context context, String str, boolean z, String str2, String str3) {
        TradeInWareCardInfo tradeInWareCardInfo = this.oldWare1;
        if (tradeInWareCardInfo != null) {
            tradeInWareCardInfo.setCurrentBank(context, str, z, str2, str3);
        }
        TradeInWareCardInfo tradeInWareCardInfo2 = this.oldWare2;
        if (tradeInWareCardInfo2 != null) {
            tradeInWareCardInfo2.setCurrentBank(context, str, z, str2, str3);
        }
    }
}
