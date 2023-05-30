package com.jd.lib.productdetail.tradein.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;

/* loaded from: classes16.dex */
public class TradeInScrollView extends ScrollView {

    /* renamed from: g */
    public a f5644g;

    /* loaded from: classes16.dex */
    public interface a {
    }

    public TradeInScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5644g = null;
    }

    public void a(a aVar) {
        this.f5644g = aVar;
    }

    @Override // android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        a aVar = this.f5644g;
        if (aVar != null) {
            TradeInResultFragment.h hVar = (TradeInResultFragment.h) aVar;
            TradeInResultFragment tradeInResultFragment = TradeInResultFragment.this;
            if (tradeInResultFragment.M) {
                if (tradeInResultFragment.D.getVisibility() != 4) {
                    TradeInResultFragment.this.D.setVisibility(4);
                    return;
                }
                return;
            }
            TradeInResultData.BatterFreeFa batterFreeFa = tradeInResultFragment.I;
            if (batterFreeFa == null || !batterFreeFa.isValid()) {
                return;
            }
            int top = TradeInResultFragment.this.s.getTop();
            int height = TradeInResultFragment.this.f5402k.getHeight();
            int height2 = TradeInResultFragment.this.D.getHeight();
            int scrollY = TradeInResultFragment.this.f5402k.getScrollY();
            if (TradeInResultFragment.this.D.getVisibility() == 0) {
                if (height + scrollY > top + height2) {
                    TradeInResultFragment.this.D.setVisibility(4);
                }
            } else if (height + scrollY < top) {
                TradeInResultFragment.this.D.setVisibility(0);
            }
        }
    }
}
