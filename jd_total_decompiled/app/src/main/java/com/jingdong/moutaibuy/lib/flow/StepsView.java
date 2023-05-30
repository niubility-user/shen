package com.jingdong.moutaibuy.lib.flow;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.InputDeviceCompat;
import com.jd.lib.un.basewidget.widget.simple.e.a;
import com.jingdong.moutaibuy.lib.R;
import com.jingdong.moutaibuy.lib.flow.StepsViewIndicator;

/* loaded from: classes16.dex */
public class StepsView extends FrameLayout implements StepsViewIndicator.a {

    /* renamed from: g  reason: collision with root package name */
    private StepsViewIndicator f14594g;

    /* renamed from: h  reason: collision with root package name */
    private FrameLayout f14595h;

    /* renamed from: i  reason: collision with root package name */
    private String[] f14596i;

    /* renamed from: j  reason: collision with root package name */
    private int f14597j;

    /* renamed from: k  reason: collision with root package name */
    private int f14598k;

    /* renamed from: l  reason: collision with root package name */
    private int f14599l;

    /* renamed from: m  reason: collision with root package name */
    private int f14600m;

    public StepsView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void b() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.widget_steps_view, this);
        StepsViewIndicator stepsViewIndicator = (StepsViewIndicator) inflate.findViewById(R.id.steps_indicator_view);
        this.f14594g = stepsViewIndicator;
        stepsViewIndicator.f(this);
        this.f14595h = (FrameLayout) inflate.findViewById(R.id.labels_container);
    }

    public void a() {
        int i2 = this.f14600m;
        if (i2 != 0) {
            int i3 = this.f14599l;
            if (i3 >= 0 && i3 <= i2 - 1) {
                this.f14594g.invalidate();
                return;
            }
            throw new IndexOutOfBoundsException(String.format("Index : %s, Size : %s", Integer.valueOf(this.f14599l), Integer.valueOf(this.f14596i.length)));
        }
        throw new IllegalArgumentException("Total steps cannot be zero.");
    }

    public StepsView c(int i2) {
        this.f14598k = i2;
        this.f14594g.c(i2);
        return this;
    }

    public StepsView d(float f2) {
        this.f14594g.d(f2);
        return this;
    }

    public StepsView e(int i2) {
        if (i2 < this.f14600m) {
            this.f14599l = i2;
            this.f14594g.e(i2);
        }
        return this;
    }

    public StepsView f(boolean z) {
        this.f14594g.g(z);
        return this;
    }

    public StepsView g(int i2) {
        return this;
    }

    public StepsView h(float f2) {
        return this;
    }

    public StepsView i(String[] strArr) {
        this.f14596i = strArr;
        if (strArr.length > this.f14600m) {
            this.f14594g.m(strArr.length);
            this.f14600m = strArr.length;
        }
        return this;
    }

    public StepsView j(int i2) {
        this.f14597j = i2;
        this.f14594g.i(i2);
        return this;
    }

    public StepsView k(float f2) {
        this.f14594g.h(f2);
        return this;
    }

    public StepsView l(float f2) {
        this.f14594g.j(f2);
        return this;
    }

    public StepsView m(int i2) {
        this.f14594g.k(i2);
        return this;
    }

    public StepsView n(int i2) {
        this.f14594g.l(i2);
        return this;
    }

    @Override // com.jingdong.moutaibuy.lib.flow.StepsViewIndicator.a
    public void onReady() {
    }

    public StepsView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f14597j = InputDeviceCompat.SOURCE_ANY;
        this.f14598k = -16777216;
        this.f14599l = 0;
        b();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.StepsView);
        int i3 = obtainStyledAttributes.getInt(R.styleable.StepsView_moutai_numOfSteps, 8);
        int i4 = obtainStyledAttributes.getInt(R.styleable.StepsView_moutai_completePosition, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.StepsView_moutai_labels, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.StepsView_moutai_barColor, -7829368);
        int color2 = obtainStyledAttributes.getColor(R.styleable.StepsView_moutai_progressColor, ContextCompat.getColor(context, R.color.orange));
        int color3 = obtainStyledAttributes.getColor(R.styleable.StepsView_moutai_labelColor, -16777216);
        int color4 = obtainStyledAttributes.getColor(R.styleable.StepsView_moutai_progressTextColor, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.StepsView_moutai_hideProgressText, false);
        float f2 = obtainStyledAttributes.getFloat(R.styleable.StepsView_moutai_labelSize, 18.0f);
        float f3 = obtainStyledAttributes.getFloat(R.styleable.StepsView_moutai_progressMargin, a.a(40.0f));
        float f4 = obtainStyledAttributes.getFloat(R.styleable.StepsView_moutai_circleRadius, a.a(10.0f));
        float f5 = obtainStyledAttributes.getFloat(R.styleable.StepsView_moutai_progressStrokeWidth, a.a(2.0f));
        this.f14594g.m(i3);
        this.f14600m = i3;
        if (resourceId > 0) {
            i(getResources().getStringArray(resourceId));
        }
        e(i4);
        c(color);
        j(color2);
        g(color3);
        m(color4);
        f(z);
        h(f2);
        k(f3);
        d(f4);
        l(f5);
        obtainStyledAttributes.recycle();
        a();
    }
}
