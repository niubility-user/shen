package com.jingdong.manto.widget.k;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

/* loaded from: classes16.dex */
public class b extends com.jingdong.manto.widget.k.a<int[]> implements NumberPicker.OnValueChangeListener {
    private LinearLayout b;

    /* renamed from: c  reason: collision with root package name */
    private NumberPicker[] f14519c;
    private c d;

    /* renamed from: e  reason: collision with root package name */
    private Context f14520e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a implements NumberPicker.Formatter {
        final /* synthetic */ C0702b a;

        a(b bVar, C0702b c0702b) {
            this.a = c0702b;
        }

        @Override // android.widget.NumberPicker.Formatter
        public String format(int i2) {
            String[] strArr = this.a.a;
            if (strArr == null || strArr.length <= i2) {
                return String.valueOf(i2);
            }
            String str = strArr[i2];
            if (str.length() > 7) {
                return str.substring(0, 6) + "...";
            }
            return str;
        }
    }

    /* renamed from: com.jingdong.manto.widget.k.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public static class C0702b {
        String[] a;
        int b;

        public C0702b(String[] strArr, int i2) {
            this.a = strArr;
            this.b = Math.max(0, Math.min(i2, strArr.length - 1));
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a(int i2, int i3);
    }

    public b(Context context) {
        this.f14520e = context;
        this.b = new LinearLayout(context);
    }

    private void a(NumberPicker numberPicker, C0702b c0702b) {
        if (c0702b == null || c0702b.a == null) {
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(0);
            numberPicker.setFormatter(null);
            return;
        }
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(Math.max(c0702b.a.length - 1, 0));
        numberPicker.setFormatter(new a(this, c0702b));
        numberPicker.setValue(c0702b.b);
        numberPicker.requestLayout();
        numberPicker.setWrapSelectorWheel(false);
    }

    private void b(C0702b[] c0702bArr) {
        if (c0702bArr.length != this.f14519c.length) {
            return;
        }
        for (int i2 = 0; i2 < c0702bArr.length; i2++) {
            NumberPicker numberPicker = this.f14519c[i2];
            C0702b c0702b = c0702bArr[i2];
            if (numberPicker != null) {
                a(numberPicker, c0702b);
            }
        }
    }

    private void c(C0702b[] c0702bArr) {
        if (c0702bArr == null) {
            return;
        }
        NumberPicker[] numberPickerArr = this.f14519c;
        if (numberPickerArr != null && numberPickerArr.length == c0702bArr.length) {
            return;
        }
        this.b.removeAllViews();
        int length = c0702bArr.length;
        NumberPicker[] numberPickerArr2 = new NumberPicker[length];
        NumberPicker[] numberPickerArr3 = this.f14519c;
        int i2 = 0;
        if (numberPickerArr3 == null) {
            for (int i3 = 0; i3 < length; i3++) {
                numberPickerArr2[i3] = com.jingdong.manto.widget.k.a.a(this.b.getContext());
            }
        } else {
            int min = Math.min(length, numberPickerArr3.length);
            System.arraycopy(this.f14519c, 0, numberPickerArr2, 0, min);
            if (length > this.f14519c.length) {
                while (min < length) {
                    numberPickerArr2[min] = com.jingdong.manto.widget.k.a.a(this.b.getContext());
                    min++;
                }
            }
        }
        this.f14519c = numberPickerArr2;
        while (true) {
            NumberPicker[] numberPickerArr4 = this.f14519c;
            if (i2 >= numberPickerArr4.length) {
                return;
            }
            NumberPicker numberPicker = numberPickerArr4[i2];
            if (numberPicker != null) {
                numberPicker.setTag(Integer.valueOf(i2));
                this.b.setWeightSum(this.f14519c.length);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.weight = 1.0f;
                this.b.addView(numberPicker, layoutParams);
                numberPicker.setOnValueChangedListener(this);
            }
            i2++;
        }
    }

    public void a(int i2, C0702b c0702b) {
        if (i2 >= 0) {
            NumberPicker[] numberPickerArr = this.f14519c;
            if (i2 >= numberPickerArr.length || c0702b == null) {
                return;
            }
            a(numberPickerArr[i2], c0702b);
        }
    }

    public void a(c cVar) {
        this.d = cVar;
    }

    public void a(C0702b[] c0702bArr) {
        if (c0702bArr == null) {
            return;
        }
        c(c0702bArr);
        b(c0702bArr);
    }

    @Override // com.jingdong.manto.widget.k.a
    public View c() {
        for (NumberPicker numberPicker : this.f14519c) {
            d.a(this.f14520e, numberPicker);
        }
        return this.b;
    }

    @Override // com.jingdong.manto.widget.k.a
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public int[] b() {
        int length = this.f14519c.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f14519c[i2].getValue();
        }
        return iArr;
    }

    @Override // android.widget.NumberPicker.OnValueChangeListener
    public void onValueChange(NumberPicker numberPicker, int i2, int i3) {
        int intValue = ((Integer) numberPicker.getTag()).intValue();
        c cVar = this.d;
        if (cVar != null) {
            cVar.a(intValue, i3);
        }
    }
}
