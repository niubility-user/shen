package com.jingdong.app.mall.unifiedcontrol.recoder;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class RecoderTimeView extends LinearLayout {
    private TextView decimalPriceView;
    private TextView integerPriceView;
    private int symbolTextSize;
    private TextView symbolTime;
    private TextView symbolTv;
    private int textColor;

    public RecoderTimeView(Context context) {
        this(context, null);
    }

    private void initDecimalPriceView() {
        TextView textView = new TextView(getContext());
        this.decimalPriceView = textView;
        textView.setTextColor(this.textColor);
        this.decimalPriceView.setTextSize(this.symbolTextSize);
        this.decimalPriceView.setText("/03:00");
        addView(this.decimalPriceView);
    }

    private void initIntegerPriceView() {
        TextView textView = new TextView(getContext());
        this.integerPriceView = textView;
        textView.setTextColor(this.textColor);
        this.integerPriceView.setTextSize(this.symbolTextSize);
        this.integerPriceView.setText("00");
        addView(this.integerPriceView);
    }

    private void initSymbolTime() {
        TextView textView = new TextView(getContext());
        this.symbolTime = textView;
        textView.setTextColor(this.textColor);
        this.symbolTime.setTextSize(this.symbolTextSize);
        this.symbolTime.setText(":");
        addView(this.symbolTime);
    }

    private void initSymbolView() {
        TextView textView = new TextView(getContext());
        this.symbolTv = textView;
        textView.setTextColor(this.textColor);
        this.symbolTv.setTextSize(this.symbolTextSize);
        this.symbolTv.setText("00");
        addView(this.symbolTv);
    }

    public void setFinishTime() {
        this.decimalPriceView.setText("/" + ((Object) this.symbolTv.getText()) + ((Object) this.symbolTime.getText()) + ((Object) this.integerPriceView.getText()));
    }

    public void setTextEnd(String str) {
        this.decimalPriceView.setText(str);
    }

    public void setTextTime(String str, String str2) {
        this.symbolTv.setText(str);
        this.integerPriceView.setText(str2);
    }

    public RecoderTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textColor = -9934744;
        this.symbolTextSize = 16;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RecoderTimeView);
        this.textColor = obtainStyledAttributes.getColor(0, this.textColor);
        this.symbolTextSize = obtainStyledAttributes.getInteger(1, this.symbolTextSize);
        obtainStyledAttributes.recycle();
        setOrientation(0);
        setGravity(80);
        initSymbolView();
        initSymbolTime();
        initIntegerPriceView();
        initDecimalPriceView();
    }
}
