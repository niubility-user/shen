package com.jingdong.manto.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.FrameLayout;
import com.jingdong.manto.widget.f;

/* loaded from: classes16.dex */
public class a extends FrameLayout implements f.d {

    /* renamed from: g  reason: collision with root package name */
    public static final boolean f14337g = f.f14360e;
    private final Paint a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    private int f14338c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f14339e;

    /* renamed from: f  reason: collision with root package name */
    public final Activity f14340f;

    public a(Context context) {
        super(context);
        this.d = 0;
        this.f14339e = true;
        if (!f14337g) {
            this.f14340f = null;
            this.a = null;
            return;
        }
        Activity a = com.jingdong.manto.ui.d.a(context);
        this.f14340f = a;
        f.a(a).a(this);
        Paint paint = new Paint(1);
        this.a = paint;
        paint.setStyle(Paint.Style.FILL);
        setWillNotDraw(false);
    }

    @Override // com.jingdong.manto.widget.f.d
    public final void a(int i2) {
        setStatusBarHeight(i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.b > 0 && f14337g && !this.f14339e) {
            this.a.setColor(this.f14338c);
            canvas.drawRect(0.0f, 0.0f, getWidth(), this.b, this.a);
        }
        super.dispatchDraw(canvas);
        if (this.b <= 0 || !f14337g || this.f14339e) {
            return;
        }
        this.a.setColor(this.d);
        canvas.drawRect(0.0f, 0.0f, getWidth(), this.b, this.a);
    }

    public final void setStatusBarHeight(int i2) {
        int max = Math.max(0, i2);
        this.b = max;
        if (this.f14339e) {
            max = 0;
        }
        setPadding(0, max, 0, 0);
        postInvalidate();
    }
}
