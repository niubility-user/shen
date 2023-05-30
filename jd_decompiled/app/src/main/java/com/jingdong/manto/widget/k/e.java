package com.jingdong.manto.widget.k;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes16.dex */
public class e extends com.jingdong.manto.widget.k.a<String> {
    private String[] b;

    /* renamed from: c  reason: collision with root package name */
    private final NumberPicker f14531c;
    private FrameLayout d;

    /* renamed from: e  reason: collision with root package name */
    private Context f14532e;

    /* loaded from: classes16.dex */
    class a implements NumberPicker.Formatter {
        a() {
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i2) {
            return (e.this.b == null || e.this.b.length <= i2) ? String.valueOf(i2) : e.this.b[i2];
        }
    }

    public e(Context context) {
        this.f14532e = context;
        NumberPicker b = com.jingdong.manto.widget.k.a.b(context);
        this.f14531c = b;
        this.d = new FrameLayout(context);
        int pixel2dip = MantoDensityUtils.pixel2dip(MantoDensityUtils.getDMWidthPixels()) - ((int) MantoDensityUtils.dip2pixel(12));
        this.d.addView(b, new ViewGroup.LayoutParams(MantoDensityUtils.dip2pixel(context, pixel2dip < 200 ? 200 : pixel2dip), -1));
    }

    public final void a(int i2) {
        this.f14531c.setValue(i2);
    }

    public final void a(String[] strArr) {
        this.b = strArr;
        this.f14531c.setMinValue(0);
        if (this.b == null) {
            this.f14531c.setMaxValue(0);
            this.f14531c.setFormatter(null);
            return;
        }
        this.f14531c.setMaxValue(Math.max(r3.length - 1, 0));
        this.f14531c.setFormatter(new a());
        this.f14531c.requestLayout();
        this.f14531c.setWrapSelectorWheel(false);
    }

    @Override // com.jingdong.manto.widget.k.a
    public View c() {
        d.a(this.f14532e, this.f14531c);
        return this.d;
    }

    @Override // com.jingdong.manto.widget.k.a
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public String b() {
        String[] strArr = this.b;
        return (strArr == null || strArr.length <= 0) ? "" : strArr[this.f14531c.getValue()];
    }

    public final int e() {
        return this.f14531c.getValue();
    }
}
