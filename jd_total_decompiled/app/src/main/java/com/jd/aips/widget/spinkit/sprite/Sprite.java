package com.jd.aips.widget.spinkit.sprite;

import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import com.jd.aips.widget.spinkit.animation.AnimationUtils;
import com.jd.aips.widget.spinkit.animation.FloatProperty;
import com.jd.aips.widget.spinkit.animation.IntProperty;

/* loaded from: classes12.dex */
public abstract class Sprite extends Drawable implements ValueAnimator.AnimatorUpdateListener, Animatable, Drawable.Callback {
    private float d;

    /* renamed from: e  reason: collision with root package name */
    private float f1660e;

    /* renamed from: f  reason: collision with root package name */
    private int f1661f;

    /* renamed from: g  reason: collision with root package name */
    private int f1662g;

    /* renamed from: h  reason: collision with root package name */
    private int f1663h;

    /* renamed from: i  reason: collision with root package name */
    private int f1664i;

    /* renamed from: j  reason: collision with root package name */
    private int f1665j;

    /* renamed from: k  reason: collision with root package name */
    private int f1666k;

    /* renamed from: l  reason: collision with root package name */
    private float f1667l;

    /* renamed from: m  reason: collision with root package name */
    private float f1668m;

    /* renamed from: n  reason: collision with root package name */
    private ValueAnimator f1669n;
    private static final Rect s = new Rect();
    public static final Property<Sprite, Integer> ROTATE_X = new IntProperty<Sprite>("rotateX") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.1
        @Override // android.util.Property
        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getRotateX());
        }

        @Override // com.jd.aips.widget.spinkit.animation.IntProperty
        public void setValue(Sprite sprite, int i2) {
            sprite.setRotateX(i2);
        }
    };
    public static final Property<Sprite, Integer> ROTATE = new IntProperty<Sprite>("rotate") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.2
        @Override // android.util.Property
        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getRotate());
        }

        @Override // com.jd.aips.widget.spinkit.animation.IntProperty
        public void setValue(Sprite sprite, int i2) {
            sprite.setRotate(i2);
        }
    };
    public static final Property<Sprite, Integer> ROTATE_Y = new IntProperty<Sprite>("rotateY") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.3
        @Override // android.util.Property
        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getRotateY());
        }

        @Override // com.jd.aips.widget.spinkit.animation.IntProperty
        public void setValue(Sprite sprite, int i2) {
            sprite.setRotateY(i2);
        }
    };
    public static final Property<Sprite, Integer> TRANSLATE_X = new IntProperty<Sprite>("translateX") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.4
        @Override // android.util.Property
        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getTranslateX());
        }

        @Override // com.jd.aips.widget.spinkit.animation.IntProperty
        public void setValue(Sprite sprite, int i2) {
            sprite.setTranslateX(i2);
        }
    };
    public static final Property<Sprite, Integer> TRANSLATE_Y = new IntProperty<Sprite>("translateY") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.5
        @Override // android.util.Property
        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getTranslateY());
        }

        @Override // com.jd.aips.widget.spinkit.animation.IntProperty
        public void setValue(Sprite sprite, int i2) {
            sprite.setTranslateY(i2);
        }
    };
    public static final Property<Sprite, Float> TRANSLATE_X_PERCENTAGE = new FloatProperty<Sprite>("translateXPercentage") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.6
        @Override // android.util.Property
        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getTranslateXPercentage());
        }

        @Override // com.jd.aips.widget.spinkit.animation.FloatProperty
        public void setValue(Sprite sprite, float f2) {
            sprite.setTranslateXPercentage(f2);
        }
    };
    public static final Property<Sprite, Float> TRANSLATE_Y_PERCENTAGE = new FloatProperty<Sprite>("translateYPercentage") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.7
        @Override // android.util.Property
        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getTranslateYPercentage());
        }

        @Override // com.jd.aips.widget.spinkit.animation.FloatProperty
        public void setValue(Sprite sprite, float f2) {
            sprite.setTranslateYPercentage(f2);
        }
    };
    public static final Property<Sprite, Float> SCALE_X = new FloatProperty<Sprite>("scaleX") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.8
        @Override // android.util.Property
        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getScaleX());
        }

        @Override // com.jd.aips.widget.spinkit.animation.FloatProperty
        public void setValue(Sprite sprite, float f2) {
            sprite.setScaleX(f2);
        }
    };
    public static final Property<Sprite, Float> SCALE_Y = new FloatProperty<Sprite>("scaleY") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.9
        @Override // android.util.Property
        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getScaleY());
        }

        @Override // com.jd.aips.widget.spinkit.animation.FloatProperty
        public void setValue(Sprite sprite, float f2) {
            sprite.setScaleY(f2);
        }
    };
    public static final Property<Sprite, Float> SCALE = new FloatProperty<Sprite>("scale") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.10
        @Override // android.util.Property
        public Float get(Sprite sprite) {
            return Float.valueOf(sprite.getScale());
        }

        @Override // com.jd.aips.widget.spinkit.animation.FloatProperty
        public void setValue(Sprite sprite, float f2) {
            sprite.setScale(f2);
        }
    };
    public static final Property<Sprite, Integer> ALPHA = new IntProperty<Sprite>("alpha") { // from class: com.jd.aips.widget.spinkit.sprite.Sprite.11
        @Override // android.util.Property
        public Integer get(Sprite sprite) {
            return Integer.valueOf(sprite.getAlpha());
        }

        @Override // com.jd.aips.widget.spinkit.animation.IntProperty
        public void setValue(Sprite sprite, int i2) {
            sprite.setAlpha(i2);
        }
    };
    private float a = 1.0f;
    private float b = 1.0f;

    /* renamed from: c  reason: collision with root package name */
    private float f1659c = 1.0f;
    private int o = 255;
    protected Rect p = s;
    private Camera q = new Camera();
    private Matrix r = new Matrix();

    protected abstract void a(Canvas canvas);

    public Rect clipSquare(Rect rect) {
        int min = Math.min(rect.width(), rect.height());
        int centerX = rect.centerX();
        int centerY = rect.centerY();
        int i2 = min / 2;
        return new Rect(centerX - i2, centerY - i2, centerX + i2, centerY + i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int translateX = getTranslateX();
        if (translateX == 0) {
            translateX = (int) (getBounds().width() * getTranslateXPercentage());
        }
        int translateY = getTranslateY();
        if (translateY == 0) {
            translateY = (int) (getBounds().height() * getTranslateYPercentage());
        }
        canvas.translate(translateX, translateY);
        canvas.scale(getScaleX(), getScaleY(), getPivotX(), getPivotY());
        canvas.rotate(getRotate(), getPivotX(), getPivotY());
        if (getRotateX() != 0 || getRotateY() != 0) {
            this.q.save();
            this.q.rotateX(getRotateX());
            this.q.rotateY(getRotateY());
            this.q.getMatrix(this.r);
            this.r.preTranslate(-getPivotX(), -getPivotY());
            this.r.postTranslate(getPivotX(), getPivotY());
            this.q.restore();
            canvas.concat(this.r);
        }
        a(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.o;
    }

    public int getAnimationDelay() {
        return this.f1661f;
    }

    public abstract int getColor();

    public Rect getDrawBounds() {
        return this.p;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getPivotX() {
        return this.d;
    }

    public float getPivotY() {
        return this.f1660e;
    }

    public int getRotate() {
        return this.f1666k;
    }

    public int getRotateX() {
        return this.f1662g;
    }

    public int getRotateY() {
        return this.f1663h;
    }

    public float getScale() {
        return this.a;
    }

    public float getScaleX() {
        return this.b;
    }

    public float getScaleY() {
        return this.f1659c;
    }

    public int getTranslateX() {
        return this.f1664i;
    }

    public float getTranslateXPercentage() {
        return this.f1667l;
    }

    public int getTranslateY() {
        return this.f1665j;
    }

    public float getTranslateYPercentage() {
        return this.f1668m;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return AnimationUtils.isRunning(this.f1669n);
    }

    public ValueAnimator obtainAnimation() {
        if (this.f1669n == null) {
            this.f1669n = onCreateAnimation();
        }
        ValueAnimator valueAnimator = this.f1669n;
        if (valueAnimator != null) {
            valueAnimator.addUpdateListener(this);
            this.f1669n.setStartDelay(this.f1661f);
        }
        return this.f1669n;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setDrawBounds(rect);
    }

    public abstract ValueAnimator onCreateAnimation();

    public void reset() {
        this.a = 1.0f;
        this.f1662g = 0;
        this.f1663h = 0;
        this.f1664i = 0;
        this.f1665j = 0;
        this.f1666k = 0;
        this.f1667l = 0.0f;
        this.f1668m = 0.0f;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.o = i2;
    }

    public Sprite setAnimationDelay(int i2) {
        this.f1661f = i2;
        return this;
    }

    public abstract void setColor(int i2);

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setDrawBounds(Rect rect) {
        setDrawBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    public void setPivotX(float f2) {
        this.d = f2;
    }

    public void setPivotY(float f2) {
        this.f1660e = f2;
    }

    public void setRotate(int i2) {
        this.f1666k = i2;
    }

    public void setRotateX(int i2) {
        this.f1662g = i2;
    }

    public void setRotateY(int i2) {
        this.f1663h = i2;
    }

    public void setScale(float f2) {
        this.a = f2;
        setScaleX(f2);
        setScaleY(f2);
    }

    public void setScaleX(float f2) {
        this.b = f2;
    }

    public void setScaleY(float f2) {
        this.f1659c = f2;
    }

    public void setTranslateX(int i2) {
        this.f1664i = i2;
    }

    public void setTranslateXPercentage(float f2) {
        this.f1667l = f2;
    }

    public void setTranslateY(int i2) {
        this.f1665j = i2;
    }

    public void setTranslateYPercentage(float f2) {
        this.f1668m = f2;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (AnimationUtils.isStarted(this.f1669n)) {
            return;
        }
        ValueAnimator obtainAnimation = obtainAnimation();
        this.f1669n = obtainAnimation;
        if (obtainAnimation == null) {
            return;
        }
        AnimationUtils.start(obtainAnimation);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (AnimationUtils.isStarted(this.f1669n)) {
            this.f1669n.removeAllUpdateListeners();
            this.f1669n.end();
            reset();
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
    }

    public void setDrawBounds(int i2, int i3, int i4, int i5) {
        this.p = new Rect(i2, i3, i4, i5);
        setPivotX(getDrawBounds().centerX());
        setPivotY(getDrawBounds().centerY());
    }
}
