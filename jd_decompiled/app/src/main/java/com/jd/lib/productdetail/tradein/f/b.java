package com.jd.lib.productdetail.tradein.f;

import android.view.View;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;

/* loaded from: classes16.dex */
public class b implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ TradeInResultData f5348g;

    /* renamed from: h */
    public final /* synthetic */ TradeInResultFragment f5349h;

    public b(TradeInResultFragment tradeInResultFragment, TradeInResultData tradeInResultData) {
        this.f5349h = tradeInResultFragment;
        this.f5348g = tradeInResultData;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PDBaseDeepLinkHelper.gotoMWithUrl(this.f5349h.getContext(), this.f5348g.protocol.url);
    }
}
