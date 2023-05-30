package com.jingdong.manto.jsapi.camera.record;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes15.dex */
public class FocusView extends View {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f13147c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private Paint f13148e;

    public FocusView(Context context) {
        this(context, null);
    }

    public FocusView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FocusView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = MantoDensityUtils.getDMWidthPixels() / 3;
        Paint paint = new Paint();
        this.f13148e = paint;
        paint.setAntiAlias(true);
        this.f13148e.setDither(true);
        this.f13148e.setColor(context.getResources().getColor(R.color.manto_ui_txt_f025));
        this.f13148e.setStrokeWidth(4.0f);
        this.f13148e.setStyle(Paint.Style.STROKE);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i2 = this.b;
        int i3 = this.d;
        int i4 = this.f13147c;
        canvas.drawRect(i2 - i3, i4 - i3, i2 + i3, i4 + i3, this.f13148e);
        canvas.drawLine(2.0f, getHeight() / 2, this.a / 10, getHeight() / 2, this.f13148e);
        canvas.drawLine((float) (getWidth() - 2), getHeight() / 2, getWidth() - (this.a / 10), getHeight() / 2, this.f13148e);
        canvas.drawLine(getWidth() / 2, 2.0f, getWidth() / 2, this.a / 10, this.f13148e);
        canvas.drawLine(getWidth() / 2, (float) (getHeight() - 2), getWidth() / 2, getHeight() - (this.a / 10), this.f13148e);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int i4 = this.a;
        double d = i4;
        Double.isNaN(d);
        int i5 = (int) (d / 2.0d);
        this.b = i5;
        this.f13147c = i5;
        this.d = i5 - 2;
        setMeasuredDimension(i4, i4);
    }
}
