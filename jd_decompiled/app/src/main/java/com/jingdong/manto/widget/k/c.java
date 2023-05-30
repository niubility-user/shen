package com.jingdong.manto.widget.k;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.manto.utils.MantoDensityUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes16.dex */
public class c extends com.jingdong.manto.widget.k.a<String> implements NumberPicker.OnValueChangeListener {
    private LinearLayout b;

    /* renamed from: c  reason: collision with root package name */
    private NumberPicker f14521c;
    private NumberPicker d;

    /* renamed from: e  reason: collision with root package name */
    private NumberPicker f14522e;

    /* renamed from: f  reason: collision with root package name */
    private EnumC0703c f14523f;

    /* renamed from: g  reason: collision with root package name */
    private Calendar f14524g;

    /* renamed from: h  reason: collision with root package name */
    private Calendar f14525h;

    /* renamed from: i  reason: collision with root package name */
    private Calendar f14526i;

    /* renamed from: j  reason: collision with root package name */
    private Calendar f14527j;

    /* renamed from: k  reason: collision with root package name */
    private Context f14528k;

    /* renamed from: l  reason: collision with root package name */
    private Map<String, Pair<Integer, Integer>> f14529l;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements NumberPicker.Formatter {
        a(c cVar) {
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i2) {
            return String.valueOf(i2 + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements NumberPicker.Formatter {
        b(c cVar) {
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i2) {
            return String.format(Locale.US, JDDateTimePickerDialog.TWO_DIGIT_FORMAT, Integer.valueOf(i2));
        }
    }

    /* renamed from: com.jingdong.manto.widget.k.c$c  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public enum EnumC0703c {
        YEAR,
        MONTH,
        DAY
    }

    public c(Context context) {
        this(context, EnumC0703c.DAY);
    }

    public c(Context context, EnumC0703c enumC0703c) {
        this.f14524g = Calendar.getInstance();
        this.f14525h = Calendar.getInstance();
        this.f14526i = Calendar.getInstance();
        this.f14527j = Calendar.getInstance();
        this.f14529l = new HashMap();
        this.f14523f = enumC0703c;
        this.f14528k = context;
        this.f14527j = Calendar.getInstance();
        c(context);
        NumberPicker numberPicker = this.f14522e;
        if (numberPicker != null) {
            numberPicker.setOnValueChangedListener(this);
        }
        NumberPicker numberPicker2 = this.d;
        if (numberPicker2 != null) {
            numberPicker2.setOnValueChangedListener(this);
        }
        NumberPicker numberPicker3 = this.f14521c;
        if (numberPicker3 != null) {
            numberPicker3.setOnValueChangedListener(this);
            this.f14521c.setMinValue(1900);
        }
        a(enumC0703c);
    }

    private void c(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        this.b = linearLayout;
        linearLayout.setOrientation(0);
        this.f14521c = com.jingdong.manto.widget.k.a.b(context);
        this.d = com.jingdong.manto.widget.k.a.b(context);
        this.f14522e = com.jingdong.manto.widget.k.a.b(context);
        NumberPicker numberPicker = this.f14521c;
        if (numberPicker != null) {
            this.b.addView(numberPicker, new ViewGroup.LayoutParams(MantoDensityUtils.dip2pixel(context, 83), -1));
        }
        NumberPicker numberPicker2 = this.d;
        if (numberPicker2 != null) {
            this.b.addView(numberPicker2, new ViewGroup.LayoutParams(MantoDensityUtils.dip2pixel(context, 83), -1));
        }
        NumberPicker numberPicker3 = this.f14522e;
        if (numberPicker3 != null) {
            this.b.addView(numberPicker3, new ViewGroup.LayoutParams(MantoDensityUtils.dip2pixel(context, 83), -1));
        }
    }

    private int d() {
        NumberPicker numberPicker = this.f14522e;
        if (numberPicker != null) {
            return numberPicker.getValue();
        }
        return 0;
    }

    private int e() {
        NumberPicker numberPicker = this.d;
        if (numberPicker != null) {
            return numberPicker.getValue() + 1;
        }
        return 1;
    }

    private int g() {
        NumberPicker numberPicker = this.f14521c;
        if (numberPicker != null) {
            return numberPicker.getValue();
        }
        return 1900;
    }

    private void h() {
        NumberPicker numberPicker;
        int actualMaximum;
        int i2 = this.f14526i.get(1);
        int i3 = this.f14526i.get(2);
        int i4 = this.f14526i.get(5);
        if (this.d != null) {
            if (this.f14529l.containsKey(String.valueOf(i2))) {
                Pair<Integer, Integer> pair = this.f14529l.get(String.valueOf(i2));
                if (pair != null) {
                    this.d.setMinValue(((Integer) pair.first).intValue());
                    this.d.setMaxValue(((Integer) pair.second).intValue());
                }
            } else {
                this.d.setMinValue(0);
                this.d.setMaxValue(11);
            }
        }
        if (this.f14522e != null) {
            String str = i2 + "-" + i3;
            if (this.f14529l.containsKey(String.valueOf(str))) {
                Pair<Integer, Integer> pair2 = this.f14529l.get(str);
                if (pair2 != null) {
                    this.f14522e.setMinValue(((Integer) pair2.first).intValue());
                    numberPicker = this.f14522e;
                    actualMaximum = ((Integer) pair2.second).intValue();
                }
            } else {
                this.f14522e.setMinValue(1);
                numberPicker = this.f14522e;
                actualMaximum = this.f14526i.getActualMaximum(5);
            }
            numberPicker.setMaxValue(actualMaximum);
        }
        NumberPicker numberPicker2 = this.f14521c;
        if (numberPicker2 != null) {
            numberPicker2.setMinValue(this.f14525h.get(1));
            this.f14521c.setMaxValue(this.f14524g.get(1));
        }
        NumberPicker numberPicker3 = this.d;
        if (numberPicker3 != null) {
            numberPicker3.setFormatter(new a(this));
        }
        b bVar = new b(this);
        NumberPicker numberPicker4 = this.f14522e;
        if (numberPicker4 != null) {
            numberPicker4.setFormatter(bVar);
        }
        NumberPicker numberPicker5 = this.f14521c;
        if (numberPicker5 != null) {
            numberPicker5.setValue(i2);
        }
        NumberPicker numberPicker6 = this.d;
        if (numberPicker6 != null) {
            numberPicker6.setValue(i3);
        }
        NumberPicker numberPicker7 = this.f14522e;
        if (numberPicker7 != null) {
            numberPicker7.setValue(i4);
        }
        NumberPicker numberPicker8 = this.f14522e;
        if (numberPicker8 != null) {
            numberPicker8.setWrapSelectorWheel(false);
        }
        NumberPicker numberPicker9 = this.f14521c;
        if (numberPicker9 != null) {
            numberPicker9.setWrapSelectorWheel(false);
        }
        NumberPicker numberPicker10 = this.d;
        if (numberPicker10 != null) {
            numberPicker10.setWrapSelectorWheel(false);
        }
    }

    public void a(EnumC0703c enumC0703c) {
        this.f14523f = enumC0703c;
        if (enumC0703c == EnumC0703c.YEAR) {
            NumberPicker numberPicker = this.f14521c;
            if (numberPicker != null) {
                numberPicker.setVisibility(0);
            }
            NumberPicker numberPicker2 = this.d;
            if (numberPicker2 != null) {
                numberPicker2.setVisibility(8);
            }
            NumberPicker numberPicker3 = this.f14522e;
            if (numberPicker3 != null) {
                numberPicker3.setVisibility(8);
            }
        }
        if (enumC0703c == EnumC0703c.MONTH) {
            NumberPicker numberPicker4 = this.f14521c;
            if (numberPicker4 != null) {
                numberPicker4.setVisibility(0);
            }
            NumberPicker numberPicker5 = this.d;
            if (numberPicker5 != null) {
                numberPicker5.setVisibility(0);
            }
            NumberPicker numberPicker6 = this.f14522e;
            if (numberPicker6 != null) {
                numberPicker6.setVisibility(8);
            }
        }
        if (enumC0703c == EnumC0703c.DAY) {
            NumberPicker numberPicker7 = this.f14521c;
            if (numberPicker7 != null) {
                numberPicker7.setVisibility(0);
            }
            NumberPicker numberPicker8 = this.d;
            if (numberPicker8 != null) {
                numberPicker8.setVisibility(0);
            }
            NumberPicker numberPicker9 = this.f14522e;
            if (numberPicker9 != null) {
                numberPicker9.setVisibility(0);
            }
        }
    }

    public void a(Date date) {
        this.f14527j.setTime(date);
        this.f14526i.set(this.f14527j.get(1), this.f14527j.get(2), this.f14527j.get(5));
        if (this.f14526i.before(this.f14525h)) {
            this.f14526i.set(this.f14525h.get(1), this.f14525h.get(2), this.f14525h.get(5));
        }
        if (this.f14526i.after(this.f14524g)) {
            this.f14526i.set(this.f14524g.get(1), this.f14524g.get(2), this.f14524g.get(5));
        }
        h();
    }

    public void a(Date date, Date date2) {
        Pair<Integer, Integer> pair;
        this.f14525h.setTime(date);
        this.f14524g.setTime(date2);
        int i2 = this.f14525h.get(1);
        int i3 = this.f14525h.get(2);
        int i4 = this.f14525h.get(5);
        int i5 = this.f14524g.get(1);
        int i6 = this.f14524g.get(2);
        int i7 = this.f14524g.get(5);
        this.f14529l.clear();
        Map<String, Pair<Integer, Integer>> map = this.f14529l;
        String valueOf = String.valueOf(i2);
        Integer valueOf2 = Integer.valueOf(i3);
        if (i2 == i5) {
            pair = new Pair<>(valueOf2, Integer.valueOf(i6));
        } else {
            map.put(valueOf, new Pair<>(valueOf2, 11));
            map = this.f14529l;
            valueOf = String.valueOf(i5);
            pair = new Pair<>(0, Integer.valueOf(i6));
        }
        map.put(valueOf, pair);
        if (i2 == i5 && i3 == i6) {
            this.f14529l.put(i2 + "-" + i3, new Pair<>(Integer.valueOf(i4), Integer.valueOf(i7)));
            return;
        }
        this.f14527j.set(i2, i3, 1);
        int actualMaximum = this.f14527j.getActualMaximum(5);
        this.f14529l.put(i2 + "-" + i3, new Pair<>(Integer.valueOf(i4), Integer.valueOf(actualMaximum)));
        this.f14529l.put(i5 + "-" + i6, new Pair<>(1, Integer.valueOf(i7)));
    }

    @Override // com.jingdong.manto.widget.k.a
    public View c() {
        d.a(this.f14528k, this.f14521c);
        d.a(this.f14528k, this.d);
        d.a(this.f14528k, this.f14522e);
        return this.b;
    }

    @Override // com.jingdong.manto.widget.k.a
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public String b() {
        EnumC0703c enumC0703c = this.f14523f;
        return enumC0703c == EnumC0703c.DAY ? String.format(Locale.US, "%04d-%02d-%02d", Integer.valueOf(g()), Integer.valueOf(e()), Integer.valueOf(d())) : enumC0703c == EnumC0703c.MONTH ? String.format(Locale.US, "%04d-%02d", Integer.valueOf(g()), Integer.valueOf(e())) : String.format(Locale.US, "%04d", Integer.valueOf(g()));
    }

    @Override // android.widget.NumberPicker.OnValueChangeListener
    public void onValueChange(NumberPicker numberPicker, int i2, int i3) {
        if (numberPicker.equals(this.f14522e)) {
            this.f14526i.set(5, i3);
        } else if (numberPicker.equals(this.d)) {
            this.f14526i.set(2, i3);
        } else {
            this.f14526i.set(1, i3);
        }
        if (this.f14526i.before(this.f14525h)) {
            this.f14526i.set(this.f14525h.get(1), this.f14525h.get(2), this.f14525h.get(5));
        }
        if (this.f14526i.after(this.f14524g)) {
            this.f14526i.set(this.f14524g.get(1), this.f14524g.get(2), this.f14524g.get(5));
        }
        h();
    }
}
