package com.jd.lib.productdetail.tradein.k;

import android.view.View;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.inform.TradeInInformFragment;

/* loaded from: classes16.dex */
public class b implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ TradeInInformFragment f5384g;

    public b(TradeInInformFragment tradeInInformFragment) {
        this.f5384g = tradeInInformFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        TradeInViewModel tradeInViewModel = this.f5384g.f5371h;
        if (tradeInViewModel != null) {
            tradeInViewModel.e("Productdetail_yjhxChangeRules", null);
        }
    }
}
