package com.jd.lib.productdetail.tradein.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jingdong.common.R;

/* loaded from: classes16.dex */
public class TradeinErrorView extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public ImageView f5652g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f5653h;

    /* renamed from: i  reason: collision with root package name */
    public TextView f5654i;

    /* loaded from: classes16.dex */
    public enum a {
        UNKNOWN,
        NONET,
        EMPTY
    }

    public TradeinErrorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void a(a aVar) {
        if (aVar == a.UNKNOWN) {
            this.f5652g.setImageResource(R.drawable.pd_feeds_common_error_unknown_light);
            this.f5653h.setText(com.jd.lib.productdetail.tradein.R.string.tradein_common_error_msg_unknown);
            this.f5654i.setText(com.jd.lib.productdetail.tradein.R.string.tradein_common_error_btn_retry);
        } else if (aVar == a.NONET) {
            this.f5652g.setImageResource(R.drawable.pd_feeds_common_error_nonet_light);
            this.f5653h.setText(com.jd.lib.productdetail.tradein.R.string.tradein_common_error_msg_nonet);
            this.f5654i.setText(com.jd.lib.productdetail.tradein.R.string.tradein_common_error_btn_retry);
        } else if (aVar == a.EMPTY) {
            this.f5652g.setImageResource(com.jd.lib.productdetail.tradein.R.drawable.tradein_common_error_view_empty);
            this.f5653h.setText(com.jd.lib.productdetail.tradein.R.string.tradein_common_error_msg_empty);
            this.f5654i.setText(com.jd.lib.productdetail.tradein.R.string.tradein_common_error_btn_back);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5652g = (ImageView) findViewById(com.jd.lib.productdetail.tradein.R.id.tradein_common_error_icon);
        this.f5653h = (TextView) findViewById(com.jd.lib.productdetail.tradein.R.id.tradein_common_error_msg);
        this.f5654i = (TextView) findViewById(com.jd.lib.productdetail.tradein.R.id.tradein_common_error_btn_retry);
    }

    public TradeinErrorView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
