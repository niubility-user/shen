package com.jd.lib.productdetail.tradein.d;

import android.content.DialogInterface;
import android.view.View;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimatePropAdapter;
import com.jd.lib.productdetail.tradein.widget.TradeInPropIDialog;

/* loaded from: classes16.dex */
public class e implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap f5288g;

    /* renamed from: h */
    public final /* synthetic */ TradeInEstimatePropAdapter.TradeInEstimatePropVH f5289h;

    /* loaded from: classes16.dex */
    public class a implements DialogInterface.OnClickListener {
        public a(e eVar) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            dialogInterface.dismiss();
        }
    }

    public e(TradeInEstimatePropAdapter.TradeInEstimatePropVH tradeInEstimatePropVH, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap) {
        this.f5289h = tradeInEstimatePropVH;
        this.f5288g = inquiryItemPropertiesMap;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap = this.f5288g;
        if (inquiryItemPropertiesMap != null) {
            TradeInPropIDialog.a aVar = new TradeInPropIDialog.a(TradeInEstimatePropAdapter.this.a);
            aVar.d = inquiryItemPropertiesMap.imageUrls;
            aVar.f5638c = inquiryItemPropertiesMap.textTip;
            aVar.b = inquiryItemPropertiesMap.attrName;
            aVar.f5639e = new a(this);
            aVar.a().show();
        }
    }
}
