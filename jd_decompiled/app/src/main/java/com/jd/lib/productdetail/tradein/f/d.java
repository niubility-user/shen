package com.jd.lib.productdetail.tradein.f;

import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes16.dex */
public class d implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ String f5351g;

    /* renamed from: h */
    public final /* synthetic */ TradeInResultData.BarterButtonInfo f5352h;

    /* renamed from: i */
    public final /* synthetic */ TradeInResultFragment f5353i;

    public d(TradeInResultFragment tradeInResultFragment, String str, TradeInResultData.BarterButtonInfo barterButtonInfo) {
        this.f5353i = tradeInResultFragment;
        this.f5351g = str;
        this.f5352h = barterButtonInfo;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (!this.f5353i.w(this.f5351g) || this.f5353i.getContext() == null) {
                return;
            }
            TradeInViewModel tradeInViewModel = this.f5353i.f5399h;
            JDJSONObject jDJSONObject = tradeInViewModel != null ? tradeInViewModel.s : null;
            if (!TextUtils.isEmpty(jDJSONObject != null ? jDJSONObject.optString("insuredNo") : "")) {
                this.f5353i.h(this.f5352h, 1);
            } else {
                this.f5353i.g(this.f5352h);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }
}
