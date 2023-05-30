package com.jd.lib.productdetail.tradein.d;

import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/* loaded from: classes16.dex */
public class f extends PdBaseProtocolLiveData<TradeInEstimateData> {
    public final Object a;

    public f(Object obj) {
        this.a = obj;
    }

    public static void a(TradeInEstimateData.Data data) {
        LinkedHashMap<String, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> linkedHashMap;
        TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap;
        TradeInEstimateData.Data.InquiryItemInfo inquiryItemInfo = data.inquiryItemInfo;
        if (inquiryItemInfo == null || data.getTagType() != 2 || (linkedHashMap = inquiryItemInfo.inquiryItemPropertiesMap) == null) {
            return;
        }
        Set<String> keySet = linkedHashMap.keySet();
        String str = "TradeInEstimateRepository inquiryItemPropertiesMap size = " + inquiryItemInfo.inquiryItemPropertiesMap.size();
        if (keySet == null || (r6 = keySet.iterator()) == null) {
            return;
        }
        for (String str2 : keySet) {
            if (inquiryItemInfo.inquiryItemPropertiesMap.containsKey(str2) && (inquiryItemPropertiesMap = inquiryItemInfo.inquiryItemPropertiesMap.get(str2)) != null && inquiryItemPropertiesMap.isMulti()) {
                Iterator<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList> it = inquiryItemPropertiesMap.inquiryItemBasePropValList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList next = it.next();
                        if (next.valType == 1) {
                            inquiryItemPropertiesMap.mCurrentVal = next;
                            inquiryItemInfo.mMultiInquiryItems.add(inquiryItemPropertiesMap);
                            String str3 = "TradeInEstimateRepository prop " + inquiryItemPropertiesMap.attrName + " isMulti default attr = " + next.valueName;
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return "barterSituation";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public boolean onAfterParseData(TradeInEstimateData tradeInEstimateData) {
        TradeInEstimateData tradeInEstimateData2 = tradeInEstimateData;
        if (tradeInEstimateData2 == null || !tradeInEstimateData2.isValid()) {
            return true;
        }
        a(tradeInEstimateData2.data);
        return true;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.setUseFastJsonParser(false);
        httpSetting.putJsonParam(this.a);
    }
}
