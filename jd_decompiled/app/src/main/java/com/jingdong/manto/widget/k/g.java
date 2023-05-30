package com.jingdong.manto.widget.k;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;
import java.util.Locale;

/* loaded from: classes16.dex */
public class g extends com.jingdong.manto.widget.k.a<String> implements NumberPicker.OnValueChangeListener {
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private FrameLayout f14542c;
    private NumberPicker d;

    /* renamed from: e  reason: collision with root package name */
    private NumberPicker f14543e;

    /* renamed from: f  reason: collision with root package name */
    private int f14544f = -1;

    /* renamed from: g  reason: collision with root package name */
    private int f14545g = -1;

    /* renamed from: h  reason: collision with root package name */
    private int f14546h = -1;

    /* renamed from: i  reason: collision with root package name */
    private int f14547i = -1;

    /* loaded from: classes16.dex */
    class a implements NumberPicker.Formatter {
        a(g gVar) {
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i2) {
            return String.format(Locale.US, JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(i2));
        }
    }

    public g(Context context) {
        this.b = context;
        d(context);
        NumberPicker numberPicker = this.f14543e;
        if (numberPicker != null) {
            numberPicker.setOnValueChangedListener(this);
        }
        NumberPicker numberPicker2 = this.d;
        if (numberPicker2 != null) {
            numberPicker2.setOnValueChangedListener(this);
        }
    }

    private TextView c(Context context) {
        TextView textView = new TextView(context);
        textView.setText(":");
        textView.setTextSize(14.0f);
        textView.setTextColor(context.getResources().getColor(R.color.manto_day_text_weight));
        return textView;
    }

    private void d(int i2, int i3) {
        NumberPicker numberPicker;
        int i4;
        if (i2 == this.f14546h) {
            this.f14543e.setMinValue(this.f14547i);
        } else {
            this.f14543e.setMinValue(0);
        }
        if (i2 == this.f14544f) {
            numberPicker = this.f14543e;
            i4 = this.f14545g;
        } else {
            numberPicker = this.f14543e;
            i4 = 59;
        }
        numberPicker.setMaxValue(i4);
        this.d.setMaxValue(this.f14544f);
        this.d.setMinValue(this.f14546h);
        this.d.setWrapSelectorWheel(false);
        this.f14543e.setWrapSelectorWheel(false);
    }

    private void d(Context context) {
        this.f14542c = new FrameLayout(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        this.f14542c.addView(linearLayout);
        this.d = com.jingdong.manto.widget.k.a.b(context);
        this.f14543e = com.jingdong.manto.widget.k.a.b(context);
        linearLayout.addView(this.d, new ViewGroup.LayoutParams(MantoDensityUtils.dip2pixel(context, 125), -1));
        linearLayout.addView(this.f14543e, new ViewGroup.LayoutParams(MantoDensityUtils.dip2pixel(context, 125), -1));
        TextView c2 = c(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f14542c.addView(c2, layoutParams);
    }

    public void a(int i2, int i3) {
        int min = Math.min(Math.max(0, i2), 23);
        int min2 = Math.min(Math.max(0, i3), 59);
        this.f14544f = min;
        this.f14545g = min2;
    }

    public void b(int i2, int i3) {
        int min = Math.min(Math.max(0, i2), 23);
        int min2 = Math.min(Math.max(0, i3), 59);
        this.f14546h = min;
        this.f14547i = min2;
    }

    @Override // com.jingdong.manto.widget.k.a
    public View c() {
        d.a(this.b, this.d);
        d.a(this.b, this.f14543e);
        return this.f14542c;
    }

    public void c(int i2, int i3) {
        int min = Math.min(Math.max(0, i2), 23);
        int min2 = Math.min(Math.max(0, i3), 59);
        d(min, min2);
        a aVar = new a(this);
        this.f14543e.setFormatter(aVar);
        this.d.setFormatter(aVar);
        this.d.setValue(min);
        this.f14543e.setValue(min2);
    }

    public int d() {
        return this.d.getValue();
    }

    public int e() {
        return this.f14543e.getValue();
    }

    @Override // com.jingdong.manto.widget.k.a
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public String b() {
        return String.format(Locale.US, "%02d:%02d", Integer.valueOf(d()), Integer.valueOf(e()));
    }

    @Override // android.widget.NumberPicker.OnValueChangeListener
    public void onValueChange(NumberPicker numberPicker, int i2, int i3) {
        d(d(), e());
    }
}
