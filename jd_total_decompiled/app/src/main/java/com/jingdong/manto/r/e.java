package com.jingdong.manto.r;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;

/* loaded from: classes16.dex */
public class e extends LinearLayout {
    private final d[] a;
    private final SparseArray<c> b;

    /* renamed from: c */
    private final SparseArray<c> f14150c;

    /* loaded from: classes16.dex */
    public class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ String b;

        a(int i2, String str) {
            e.this = r1;
            this.a = i2;
            this.b = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            e eVar = e.this;
            eVar.a(eVar, this.a, this.b);
        }
    }

    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        b(String str, String str2) {
            e.this = r1;
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            e eVar = e.this;
            eVar.a(eVar, this.a, this.b);
        }
    }

    @SuppressLint({"AppCompatCustomView"})
    /* loaded from: classes16.dex */
    public class c extends TextView {
        private String a;
        private String b;

        public c(e eVar, Context context) {
            super(context);
            setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            setTextSize(1, 12.0f);
            setTextColor(getContext().getResources().getColor(R.color.manto_ui_f6));
        }

        private void a() {
            setText(String.format("%s: %s", this.a, this.b));
        }

        void a(c cVar, String str) {
            cVar.a = str;
            cVar.a();
        }

        void b(c cVar, String str) {
            cVar.b = str;
            cVar.a();
        }
    }

    @SuppressLint({"AppCompatCustomView"})
    /* loaded from: classes16.dex */
    public class d extends TextView {
        public d(e eVar, Context context) {
            super(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            int dip2pixel = MantoDensityUtils.dip2pixel(getContext(), 5);
            layoutParams.setMargins(0, dip2pixel, 0, dip2pixel);
            setLayoutParams(layoutParams);
            setTextSize(1, 12.0f);
            setTextColor(getContext().getResources().getColor(R.color.manto_black));
        }
    }

    public e(Context context) {
        super(context);
        this.a = new d[4];
        this.b = new SparseArray<>();
        this.f14150c = new SparseArray<>();
        setClickable(false);
        int dMWidthPixels = MantoDensityUtils.getDMWidthPixels();
        int dip2pixel = MantoDensityUtils.dip2pixel(getContext(), 10);
        int dip2pixel2 = MantoDensityUtils.dip2pixel(getContext(), 4);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((dMWidthPixels * 3) / 5, -2);
        layoutParams.gravity = 53;
        layoutParams.topMargin = MantoDensityUtils.dip2pixel(getContext(), 90);
        setLayoutParams(layoutParams);
        setPadding(dip2pixel, dip2pixel, dip2pixel, dip2pixel);
        setOrientation(1);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(dip2pixel2);
        gradientDrawable.setColor(getResources().getColor(R.color.manto_half_transparent));
        setBackgroundDrawable(gradientDrawable);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 2);
        TextView textView = new TextView(getContext());
        View view = new View(getContext());
        textView.setTextColor(getContext().getResources().getColor(R.color.manto_ui_f6));
        textView.setLayoutParams(layoutParams2);
        textView.setTextSize(1, 14.0f);
        textView.setText(getContext().getString(R.string.manto_perfomance_title));
        addView(textView);
        layoutParams3.setMargins(0, MantoDensityUtils.dip2pixel(getContext(), 10), 0, 0);
        view.setLayoutParams(layoutParams3);
        view.setBackgroundColor(getResources().getColor(R.color.manto_ui_divider_edee));
        addView(view);
        a();
    }

    private void a() {
        for (int i2 = 0; i2 < 4; i2++) {
            d dVar = new d(this, getContext());
            dVar.setText(getContext().getString(com.jingdong.manto.r.d.b[i2]));
            this.a[i2] = dVar;
            addView(dVar);
        }
    }

    public void a(e eVar, int i2, String str) {
        Integer num = com.jingdong.manto.r.d.f14133c.get(i2);
        if (num == null) {
            MantoLog.e("PerformancePanel", "insertPerformanceData no such performance type: %d", Integer.valueOf(i2));
            return;
        }
        String string = eVar.getContext().getString(num.intValue());
        c cVar = eVar.f14150c.get(i2);
        if (cVar == null) {
            cVar = new c(this, eVar.getContext());
            cVar.a(cVar, string);
            int i3 = (i2 / 10) - 1;
            if (i3 >= 4) {
                MantoLog.e("PerformancePanel", "insertPerformanceLabelView group index is invalid.");
                cVar = null;
            } else {
                if (i3 == 3) {
                    eVar.addView(cVar);
                } else {
                    eVar.addView(cVar, eVar.indexOfChild(eVar.a[i3 + 1]));
                }
                eVar.f14150c.put(i2, cVar);
            }
        }
        if (cVar == null) {
            MantoLog.e("PerformancePanel", "insertPerformanceData label view is null.");
        } else {
            cVar.b(cVar, str);
        }
    }

    public void a(e eVar, String str, String str2) {
        c cVar = eVar.b.get(str.hashCode());
        if (cVar == null) {
            cVar = new c(this, eVar.getContext());
            cVar.a(cVar, str);
            eVar.addView(cVar);
            eVar.b.put(str.hashCode(), cVar);
        }
        cVar.b(cVar, str2);
    }

    public final void a(int i2, String str) {
        MantoUtils.runOnUiThread(new a(i2, str));
    }

    public final void a(String str, String str2) {
        MantoUtils.runOnUiThread(new b(str, str2));
    }
}
