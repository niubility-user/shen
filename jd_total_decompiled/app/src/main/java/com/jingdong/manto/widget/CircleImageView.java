package com.jingdong.manto.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import com.jingdong.manto.R;

/* loaded from: classes16.dex */
public class CircleImageView extends ImageView {
    private static final ImageView.ScaleType u = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config v = Bitmap.Config.ARGB_8888;
    private final RectF a;
    private final RectF b;

    /* renamed from: c  reason: collision with root package name */
    private final Matrix f14316c;
    private final Paint d;

    /* renamed from: e  reason: collision with root package name */
    private final Paint f14317e;

    /* renamed from: f  reason: collision with root package name */
    private final Paint f14318f;

    /* renamed from: g  reason: collision with root package name */
    private int f14319g;

    /* renamed from: h  reason: collision with root package name */
    private int f14320h;

    /* renamed from: i  reason: collision with root package name */
    private int f14321i;

    /* renamed from: j  reason: collision with root package name */
    private Bitmap f14322j;

    /* renamed from: k  reason: collision with root package name */
    private BitmapShader f14323k;

    /* renamed from: l  reason: collision with root package name */
    private int f14324l;

    /* renamed from: m  reason: collision with root package name */
    private int f14325m;

    /* renamed from: n  reason: collision with root package name */
    private float f14326n;
    private float o;
    private ColorFilter p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 21)
    /* loaded from: classes16.dex */
    public class b extends ViewOutlineProvider {
        private b() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            Rect rect = new Rect();
            CircleImageView.this.b.roundOut(rect);
            outline.setRoundRect(rect, rect.width() / 2.0f);
        }
    }

    public CircleImageView(Context context) {
        super(context);
        this.a = new RectF();
        this.b = new RectF();
        this.f14316c = new Matrix();
        this.d = new Paint();
        this.f14317e = new Paint();
        this.f14318f = new Paint();
        this.f14319g = -16777216;
        this.f14320h = 0;
        this.f14321i = 0;
        c();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0069, code lost:
        if (r4.hasValue(r5) != false) goto L4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CircleImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = new RectF();
        this.b = new RectF();
        this.f14316c = new Matrix();
        this.d = new Paint();
        this.f14317e = new Paint();
        this.f14318f = new Paint();
        this.f14319g = -16777216;
        this.f14320h = 0;
        this.f14321i = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleImageView, i2, 0);
        this.f14320h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleImageView_civ_border_width, 0);
        this.f14319g = obtainStyledAttributes.getColor(R.styleable.CircleImageView_civ_border_color, -16777216);
        this.s = obtainStyledAttributes.getBoolean(R.styleable.CircleImageView_civ_border_overlay, false);
        int i3 = R.styleable.CircleImageView_civ_circle_background_color;
        if (!obtainStyledAttributes.hasValue(i3)) {
            i3 = R.styleable.CircleImageView_civ_fill_color;
        }
        this.f14321i = obtainStyledAttributes.getColor(i3, 0);
        obtainStyledAttributes.recycle();
        c();
    }

    private Bitmap a(Drawable drawable) {
        int intrinsicHeight;
        Bitmap.Config config;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            int i2 = 2;
            if (drawable instanceof ColorDrawable) {
                config = v;
                intrinsicHeight = 2;
            } else {
                i2 = drawable.getIntrinsicWidth();
                intrinsicHeight = drawable.getIntrinsicHeight();
                config = v;
            }
            Bitmap createBitmap = Bitmap.createBitmap(i2, intrinsicHeight, config);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void a() {
        Paint paint = this.d;
        if (paint != null) {
            paint.setColorFilter(this.p);
        }
    }

    private boolean a(float f2, float f3) {
        return Math.pow((double) (f2 - this.b.centerX()), 2.0d) + Math.pow((double) (f3 - this.b.centerY()), 2.0d) <= Math.pow((double) this.o, 2.0d);
    }

    private RectF b() {
        int min = Math.min((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        float paddingLeft = getPaddingLeft() + ((r0 - min) / 2.0f);
        float paddingTop = getPaddingTop() + ((r1 - min) / 2.0f);
        float f2 = min;
        return new RectF(paddingLeft, paddingTop, paddingLeft + f2, f2 + paddingTop);
    }

    private void c() {
        super.setScaleType(u);
        this.q = true;
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new b());
        }
        if (this.r) {
            e();
            this.r = false;
        }
    }

    private void d() {
        this.f14322j = this.t ? null : a(getDrawable());
        e();
    }

    private void e() {
        int i2;
        if (!this.q) {
            this.r = true;
        } else if (getWidth() == 0 && getHeight() == 0) {
        } else {
            if (this.f14322j == null) {
                invalidate();
                return;
            }
            Bitmap bitmap = this.f14322j;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.f14323k = new BitmapShader(bitmap, tileMode, tileMode);
            this.d.setAntiAlias(true);
            this.d.setShader(this.f14323k);
            this.f14317e.setStyle(Paint.Style.STROKE);
            this.f14317e.setAntiAlias(true);
            this.f14317e.setColor(this.f14319g);
            this.f14317e.setStrokeWidth(this.f14320h);
            this.f14318f.setStyle(Paint.Style.FILL);
            this.f14318f.setAntiAlias(true);
            this.f14318f.setColor(this.f14321i);
            this.f14325m = this.f14322j.getHeight();
            this.f14324l = this.f14322j.getWidth();
            this.b.set(b());
            this.o = Math.min((this.b.height() - this.f14320h) / 2.0f, (this.b.width() - this.f14320h) / 2.0f);
            this.a.set(this.b);
            if (!this.s && (i2 = this.f14320h) > 0) {
                float f2 = i2 - 1.0f;
                this.a.inset(f2, f2);
            }
            this.f14326n = Math.min(this.a.height() / 2.0f, this.a.width() / 2.0f);
            a();
            f();
            invalidate();
        }
    }

    private void f() {
        float width;
        float f2;
        this.f14316c.set(null);
        float f3 = 0.0f;
        if (this.f14324l * this.a.height() > this.a.width() * this.f14325m) {
            width = this.a.height() / this.f14325m;
            f2 = (this.a.width() - (this.f14324l * width)) * 0.5f;
        } else {
            width = this.a.width() / this.f14324l;
            f3 = (this.a.height() - (this.f14325m * width)) * 0.5f;
            f2 = 0.0f;
        }
        this.f14316c.setScale(width, width);
        Matrix matrix = this.f14316c;
        RectF rectF = this.a;
        matrix.postTranslate(((int) (f2 + 0.5f)) + rectF.left, ((int) (f3 + 0.5f)) + rectF.top);
        this.f14323k.setLocalMatrix(this.f14316c);
    }

    public int getBorderColor() {
        return this.f14319g;
    }

    public int getBorderWidth() {
        return this.f14320h;
    }

    public int getCircleBackgroundColor() {
        return this.f14321i;
    }

    @Override // android.widget.ImageView
    public ColorFilter getColorFilter() {
        return this.p;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return u;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.t) {
            super.onDraw(canvas);
        } else if (this.f14322j == null) {
        } else {
            if (this.f14321i != 0) {
                canvas.drawCircle(this.a.centerX(), this.a.centerY(), this.f14326n, this.f14318f);
            }
            canvas.drawCircle(this.a.centerX(), this.a.centerY(), this.f14326n, this.d);
            if (this.f14320h > 0) {
                canvas.drawCircle(this.b.centerX(), this.b.centerY(), this.o, this.f14317e);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        e();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return a(motionEvent.getX(), motionEvent.getY()) && super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.ImageView
    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public void setBorderColor(@ColorInt int i2) {
        if (i2 == this.f14319g) {
            return;
        }
        this.f14319g = i2;
        this.f14317e.setColor(i2);
        invalidate();
    }

    public void setBorderOverlay(boolean z) {
        if (z == this.s) {
            return;
        }
        this.s = z;
        e();
    }

    public void setBorderWidth(int i2) {
        if (i2 == this.f14320h) {
            return;
        }
        this.f14320h = i2;
        e();
    }

    public void setCircleBackgroundColor(@ColorInt int i2) {
        if (i2 == this.f14321i) {
            return;
        }
        this.f14321i = i2;
        this.f14318f.setColor(i2);
        invalidate();
    }

    public void setCircleBackgroundColorResource(@ColorRes int i2) {
        setCircleBackgroundColor(getContext().getResources().getColor(i2));
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter == this.p) {
            return;
        }
        this.p = colorFilter;
        a();
        invalidate();
    }

    public void setDisableCircularTransformation(boolean z) {
        if (this.t == z) {
            return;
        }
        this.t = z;
        d();
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        d();
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        d();
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i2) {
        super.setImageResource(i2);
        d();
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        d();
    }

    @Override // android.view.View
    public void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
        e();
    }

    @Override // android.view.View
    public void setPaddingRelative(int i2, int i3, int i4, int i5) {
        super.setPaddingRelative(i2, i3, i4, i5);
        e();
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != u) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }
}
