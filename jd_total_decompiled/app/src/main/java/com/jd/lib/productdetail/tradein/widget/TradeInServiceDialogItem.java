package com.jd.lib.productdetail.tradein.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.bean.TradeInServiceInfoItem;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes16.dex */
public class TradeInServiceDialogItem extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    public TextView f5645g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5646h;

    public TradeInServiceDialogItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(TradeInServiceInfoItem tradeInServiceInfoItem) {
        if (tradeInServiceInfoItem != null) {
            this.f5645g.setText(tradeInServiceInfoItem.serviceName);
            if (!TextUtils.isEmpty(tradeInServiceInfoItem.serviceFee)) {
                this.f5646h.setText(tradeInServiceInfoItem.serviceFee);
                FontsUtil.changeTextFont(this.f5646h, 4099);
                return;
            }
            this.f5646h.setText("");
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5645g = (TextView) findViewById(R.id.tradein_service_title);
        this.f5646h = (TextView) findViewById(R.id.tradein_service_fee);
    }
}
