package com.jd.lib.productdetail.tradein.result;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.lib.productdetail.tradein.R;

/* loaded from: classes16.dex */
public class TradeInUserRuleView extends ConstraintLayout implements Checkable {

    /* renamed from: g  reason: collision with root package name */
    public boolean f5465g;

    /* renamed from: h  reason: collision with root package name */
    public Drawable f5466h;

    /* renamed from: i  reason: collision with root package name */
    public Drawable f5467i;

    /* renamed from: j  reason: collision with root package name */
    public ImageView f5468j;

    public TradeInUserRuleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5466h = getResources().getDrawable(R.drawable.tradein_result_btn_rule_indicator_checked);
        this.f5467i = getResources().getDrawable(R.drawable.tradein_result_btn_rule_indicator_unchecked);
        Drawable drawable = this.f5466h;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.f5466h.getIntrinsicHeight());
        Drawable drawable2 = this.f5467i;
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.f5467i.getIntrinsicHeight());
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.f5465g;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5468j = (ImageView) findViewById(R.id.tradein_result_btn_protocol_hint_image);
        TextView textView = (TextView) findViewById(R.id.tradein_result_btn_protocol_hint);
        TextView textView2 = (TextView) findViewById(R.id.tradein_result_btn_protocol_detail);
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        this.f5465g = z;
        this.f5468j.setBackground(z ? this.f5466h : this.f5467i);
    }

    @Override // android.widget.Checkable
    public void toggle() {
        setChecked(!this.f5465g);
    }
}
