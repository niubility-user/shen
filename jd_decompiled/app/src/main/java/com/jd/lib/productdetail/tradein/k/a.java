package com.jd.lib.productdetail.tradein.k;

import android.view.View;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.inform.TradeInInformFragment;

/* loaded from: classes16.dex */
public class a implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ TradeInInformFragment f5383g;

    public a(TradeInInformFragment tradeInInformFragment) {
        this.f5383g = tradeInInformFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f5383g.f5370g.moveToStep(TradeInStep.SELECT_OLD_DEVICE);
        TradeInViewModel tradeInViewModel = this.f5383g.f5371h;
        if (tradeInViewModel != null) {
            tradeInViewModel.e("Productdetail_yjhxInformChoose", null);
        }
    }
}
