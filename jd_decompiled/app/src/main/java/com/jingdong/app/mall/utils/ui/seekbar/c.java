package com.jingdong.app.mall.utils.ui.seekbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.TypedValue;

/* loaded from: classes4.dex */
class c {
    private final float a;
    private final Bitmap b;

    /* renamed from: c  reason: collision with root package name */
    private final Bitmap f11956c;
    private final float d;

    /* renamed from: e  reason: collision with root package name */
    private final float f11957e;

    /* renamed from: f  reason: collision with root package name */
    private final float f11958f;

    /* renamed from: g  reason: collision with root package name */
    private final float f11959g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f11960h = false;

    /* renamed from: i  reason: collision with root package name */
    private final float f11961i;

    /* renamed from: j  reason: collision with root package name */
    public float f11962j;

    /* renamed from: k  reason: collision with root package name */
    private Paint f11963k;

    /* renamed from: l  reason: collision with root package name */
    private Paint f11964l;

    /* renamed from: m  reason: collision with root package name */
    private float f11965m;

    /* renamed from: n  reason: collision with root package name */
    private boolean f11966n;
    private int o;
    private int p;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, float f2, int i2, int i3, float f3, int i4, int i5) {
        Resources resources = context.getResources();
        this.b = BitmapFactory.decodeResource(resources, i4);
        this.f11956c = BitmapFactory.decodeResource(resources, i5);
        if (f3 == -1.0f && i2 == -1 && i3 == -1) {
            this.f11966n = true;
        } else {
            this.f11966n = false;
            if (f3 == -1.0f) {
                this.f11965m = TypedValue.applyDimension(1, 14.0f, resources.getDisplayMetrics());
            } else {
                this.f11965m = TypedValue.applyDimension(1, f3, resources.getDisplayMetrics());
            }
            if (i2 == -1) {
                this.o = -13388315;
            } else {
                this.o = i2;
            }
            if (i3 == -1) {
                this.p = -13388315;
            } else {
                this.p = i3;
            }
            Paint paint = new Paint();
            this.f11963k = paint;
            paint.setColor(this.o);
            this.f11963k.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.f11964l = paint2;
            paint2.setColor(this.p);
            this.f11964l.setAntiAlias(true);
        }
        float width = r11.getWidth() / 2.0f;
        this.d = width;
        this.f11957e = r11.getHeight() / 2.0f;
        this.f11958f = r12.getWidth() / 2.0f;
        this.f11959g = r12.getHeight() / 2.0f;
        this.a = TypedValue.applyDimension(1, (int) Math.max(24.0f, f3), resources.getDisplayMetrics());
        this.f11962j = width;
        this.f11961i = f2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Canvas canvas) {
        if (this.f11966n) {
            boolean z = this.f11960h;
            Bitmap bitmap = z ? this.f11956c : this.b;
            if (z) {
                canvas.drawBitmap(bitmap, this.f11962j - this.f11958f, this.f11961i - this.f11959g, (Paint) null);
            } else {
                canvas.drawBitmap(bitmap, this.f11962j - this.d, this.f11961i - this.f11957e, (Paint) null);
            }
        } else if (this.f11960h) {
            canvas.drawCircle(this.f11962j, this.f11961i, this.f11965m, this.f11964l);
        } else {
            canvas.drawCircle(this.f11962j, this.f11961i, this.f11965m, this.f11963k);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float b() {
        return this.d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(float f2, float f3) {
        return Math.abs(f2 - this.f11962j) <= this.a && Math.abs(f3 - this.f11961i) <= this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean d() {
        return this.f11960h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.f11960h = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.f11960h = false;
    }
}
