package com.jd.lib.productdetail.tradein.f;

import android.text.TextUtils;
import android.view.View;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes16.dex */
public class a implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ String f5345g;

    /* renamed from: h */
    public final /* synthetic */ TradeInResultData.BarterButtonInfo f5346h;

    /* renamed from: i */
    public final /* synthetic */ TradeInResultFragment f5347i;

    public a(TradeInResultFragment tradeInResultFragment, String str, TradeInResultData.BarterButtonInfo barterButtonInfo) {
        this.f5347i = tradeInResultFragment;
        this.f5345g = str;
        this.f5346h = barterButtonInfo;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (!this.f5347i.w(this.f5345g) || this.f5347i.getContext() == null) {
                return;
            }
            TradeInViewModel tradeInViewModel = this.f5347i.f5399h;
            JDJSONObject jDJSONObject = tradeInViewModel != null ? tradeInViewModel.s : null;
            if (!TextUtils.isEmpty(jDJSONObject != null ? jDJSONObject.optString("insuredNo") : "")) {
                this.f5347i.h(this.f5346h, 2);
            } else {
                this.f5347i.u(this.f5346h);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }
}
