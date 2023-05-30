package com.jd.lib.productdetail.tradein.g;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.Iterator;

/* loaded from: classes16.dex */
public class i extends PdBaseProtocolLiveData<TradeInSelectDeviceData> {
    public final Object a;

    public i(Object obj) {
        this.a = obj;
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public String getFunctionId() {
        return "barterCatalogue";
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public boolean onAfterParseData(TradeInSelectDeviceData tradeInSelectDeviceData) {
        TradeInSelectDeviceData.Data data;
        TradeInSelectDeviceData tradeInSelectDeviceData2 = tradeInSelectDeviceData;
        if (tradeInSelectDeviceData2 != null && TextUtils.equals(tradeInSelectDeviceData2.code, "0") && (data = tradeInSelectDeviceData2.data) != null) {
            TradeInSelectDeviceData.Data.TagsInfo tagsInfo = data.tagsInfo;
            if (tagsInfo != null && tagsInfo.isValid()) {
                Iterator<TradeInSelectDeviceData.Data.TagsInfo.TagItem> it = tradeInSelectDeviceData2.data.tagsInfo.tagInfoList.iterator();
                boolean z = false;
                while (it.hasNext()) {
                    TradeInSelectDeviceData.Data.TagsInfo.TagItem next = it.next();
                    if (next.isValid()) {
                        if (next.selected && !z) {
                            z = true;
                        }
                    } else {
                        it.remove();
                    }
                }
                if (!z && tradeInSelectDeviceData2.data.tagsInfo.isValid()) {
                    tradeInSelectDeviceData2.data.tagsInfo.tagInfoList.get(0).selected = true;
                }
            }
            TradeInSelectDeviceData.Data.CategoriesInfo categoriesInfo = tradeInSelectDeviceData2.data.categoriesInfo;
            if (categoriesInfo != null && categoriesInfo.isValid()) {
                Iterator<TradeInSelectDeviceData.Data.CategoriesInfo.CateItem> it2 = tradeInSelectDeviceData2.data.categoriesInfo.categories.iterator();
                boolean z2 = false;
                while (it2.hasNext()) {
                    TradeInSelectDeviceData.Data.CategoriesInfo.CateItem next2 = it2.next();
                    if (next2.isValid()) {
                        if (next2.selected && !z2) {
                            z2 = true;
                        }
                    } else {
                        it2.remove();
                    }
                }
                if (!z2 && tradeInSelectDeviceData2.data.categoriesInfo.isValid()) {
                    tradeInSelectDeviceData2.data.categoriesInfo.categories.get(0).selected = true;
                }
            }
            TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo = tradeInSelectDeviceData2.data.inquiriesInfo;
            if (inquiriesInfo != null && inquiriesInfo.isValid()) {
                Iterator<TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries> it3 = tradeInSelectDeviceData2.data.inquiriesInfo.oldProductInquiries.iterator();
                while (it3.hasNext()) {
                    if (!it3.next().isValid()) {
                        it3.remove();
                    }
                }
            }
        }
        return super.onAfterParseData(tradeInSelectDeviceData2);
    }

    @Override // com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData
    public void onBeforeRequest(@NonNull HttpSetting httpSetting) {
        httpSetting.putJsonParam(this.a);
    }
}
