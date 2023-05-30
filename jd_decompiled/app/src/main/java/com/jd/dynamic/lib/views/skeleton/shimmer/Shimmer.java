package com.jd.dynamic.lib.views.skeleton.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.Px;
import androidx.core.view.ViewCompat;
import com.jd.dynamic.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes13.dex */
public class Shimmer {
    final float[] a = new float[4];
    final int[] b = new int[4];

    /* renamed from: c  reason: collision with root package name */
    int f2581c;
    @ColorInt
    int d;
    @ColorInt

    /* renamed from: e  reason: collision with root package name */
    int f2582e;

    /* renamed from: f  reason: collision with root package name */
    int f2583f;

    /* renamed from: g  reason: collision with root package name */
    int f2584g;

    /* renamed from: h  reason: collision with root package name */
    int f2585h;

    /* renamed from: i  reason: collision with root package name */
    float f2586i;

    /* renamed from: j  reason: collision with root package name */
    float f2587j;

    /* renamed from: k  reason: collision with root package name */
    float f2588k;

    /* renamed from: l  reason: collision with root package name */
    float f2589l;

    /* renamed from: m  reason: collision with root package name */
    float f2590m;

    /* renamed from: n  reason: collision with root package name */
    boolean f2591n;
    boolean o;
    boolean p;
    int q;
    int r;
    long s;
    long t;
    long u;

    /* loaded from: classes13.dex */
    public static class AlphaHighlightBuilder extends Builder<AlphaHighlightBuilder> {
        public AlphaHighlightBuilder() {
            this.a.p = true;
        }

        @Override // com.jd.dynamic.lib.views.skeleton.shimmer.Shimmer.Builder
        protected /* synthetic */ AlphaHighlightBuilder c() {
            d();
            return this;
        }

        protected AlphaHighlightBuilder d() {
            return this;
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class Builder<T extends Builder<T>> {
        final Shimmer a = new Shimmer();

        private static float a(float f2, float f3, float f4) {
            return Math.min(f3, Math.max(f2, f4));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public T b(TypedArray typedArray) {
            int i2 = R.styleable.ShimmerFrameLayout_shimmer_clip_to_children;
            if (typedArray.hasValue(i2)) {
                setClipToChildren(typedArray.getBoolean(i2, this.a.f2591n));
            }
            int i3 = R.styleable.ShimmerFrameLayout_shimmer_auto_start;
            if (typedArray.hasValue(i3)) {
                setAutoStart(typedArray.getBoolean(i3, this.a.o));
            }
            int i4 = R.styleable.ShimmerFrameLayout_shimmer_base_alpha;
            if (typedArray.hasValue(i4)) {
                setBaseAlpha(typedArray.getFloat(i4, 0.3f));
            }
            int i5 = R.styleable.ShimmerFrameLayout_shimmer_highlight_alpha;
            if (typedArray.hasValue(i5)) {
                setHighlightAlpha(typedArray.getFloat(i5, 1.0f));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_duration)) {
                setDuration(typedArray.getInt(r0, (int) this.a.s));
            }
            int i6 = R.styleable.ShimmerFrameLayout_shimmer_repeat_count;
            if (typedArray.hasValue(i6)) {
                setRepeatCount(typedArray.getInt(i6, this.a.q));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_repeat_delay)) {
                setRepeatDelay(typedArray.getInt(r0, (int) this.a.t));
            }
            int i7 = R.styleable.ShimmerFrameLayout_shimmer_repeat_mode;
            if (typedArray.hasValue(i7)) {
                setRepeatMode(typedArray.getInt(i7, this.a.r));
            }
            if (typedArray.hasValue(R.styleable.ShimmerFrameLayout_shimmer_start_delay)) {
                setStartDelay(typedArray.getInt(r0, (int) this.a.u));
            }
            int i8 = R.styleable.ShimmerFrameLayout_shimmer_direction;
            if (typedArray.hasValue(i8)) {
                int i9 = typedArray.getInt(i8, this.a.f2581c);
                if (i9 != 1) {
                    int i10 = 2;
                    if (i9 != 2) {
                        i10 = 3;
                        if (i9 != 3) {
                            setDirection(0);
                        }
                    }
                    setDirection(i10);
                } else {
                    setDirection(1);
                }
            }
            int i11 = R.styleable.ShimmerFrameLayout_shimmer_shape;
            if (typedArray.hasValue(i11)) {
                if (typedArray.getInt(i11, this.a.f2583f) != 1) {
                    setShape(0);
                } else {
                    setShape(1);
                }
            }
            int i12 = R.styleable.ShimmerFrameLayout_shimmer_dropoff;
            if (typedArray.hasValue(i12)) {
                setDropoff(typedArray.getFloat(i12, this.a.f2589l));
            }
            int i13 = R.styleable.ShimmerFrameLayout_shimmer_fixed_width;
            if (typedArray.hasValue(i13)) {
                setFixedWidth(typedArray.getDimensionPixelSize(i13, this.a.f2584g));
            }
            int i14 = R.styleable.ShimmerFrameLayout_shimmer_fixed_height;
            if (typedArray.hasValue(i14)) {
                setFixedHeight(typedArray.getDimensionPixelSize(i14, this.a.f2585h));
            }
            int i15 = R.styleable.ShimmerFrameLayout_shimmer_intensity;
            if (typedArray.hasValue(i15)) {
                setIntensity(typedArray.getFloat(i15, this.a.f2588k));
            }
            int i16 = R.styleable.ShimmerFrameLayout_shimmer_width_ratio;
            if (typedArray.hasValue(i16)) {
                setWidthRatio(typedArray.getFloat(i16, this.a.f2586i));
            }
            int i17 = R.styleable.ShimmerFrameLayout_shimmer_height_ratio;
            if (typedArray.hasValue(i17)) {
                setHeightRatio(typedArray.getFloat(i17, this.a.f2587j));
            }
            int i18 = R.styleable.ShimmerFrameLayout_shimmer_tilt;
            if (typedArray.hasValue(i18)) {
                setTilt(typedArray.getFloat(i18, this.a.f2590m));
            }
            return c();
        }

        public Shimmer build() {
            this.a.b();
            this.a.d();
            return this.a;
        }

        protected abstract T c();

        public T consumeAttributes(Context context, AttributeSet attributeSet) {
            return b(context.obtainStyledAttributes(attributeSet, R.styleable.ShimmerFrameLayout, 0, 0));
        }

        public T copyFrom(Shimmer shimmer) {
            setDirection(shimmer.f2581c);
            setShape(shimmer.f2583f);
            setFixedWidth(shimmer.f2584g);
            setFixedHeight(shimmer.f2585h);
            setWidthRatio(shimmer.f2586i);
            setHeightRatio(shimmer.f2587j);
            setIntensity(shimmer.f2588k);
            setDropoff(shimmer.f2589l);
            setTilt(shimmer.f2590m);
            setClipToChildren(shimmer.f2591n);
            setAutoStart(shimmer.o);
            setRepeatCount(shimmer.q);
            setRepeatMode(shimmer.r);
            setRepeatDelay(shimmer.t);
            setStartDelay(shimmer.u);
            setDuration(shimmer.s);
            Shimmer shimmer2 = this.a;
            shimmer2.f2582e = shimmer.f2582e;
            shimmer2.d = shimmer.d;
            return c();
        }

        public T setAutoStart(boolean z) {
            this.a.o = z;
            return c();
        }

        public T setBaseAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
            Shimmer shimmer = this.a;
            shimmer.f2582e = (((int) (a(0.0f, 1.0f, f2) * 255.0f)) << 24) | (shimmer.f2582e & ViewCompat.MEASURED_SIZE_MASK);
            return c();
        }

        public T setClipToChildren(boolean z) {
            this.a.f2591n = z;
            return c();
        }

        public T setDirection(int i2) {
            this.a.f2581c = i2;
            return c();
        }

        public T setDropoff(float f2) {
            if (f2 >= 0.0f) {
                this.a.f2589l = f2;
                return c();
            }
            throw new IllegalArgumentException("Given invalid dropoff value: " + f2);
        }

        public T setDuration(long j2) {
            if (j2 >= 0) {
                this.a.s = j2;
                return c();
            }
            throw new IllegalArgumentException("Given a negative duration: " + j2);
        }

        public T setFixedHeight(@Px int i2) {
            if (i2 >= 0) {
                this.a.f2585h = i2;
                return c();
            }
            throw new IllegalArgumentException("Given invalid height: " + i2);
        }

        public T setFixedWidth(@Px int i2) {
            if (i2 >= 0) {
                this.a.f2584g = i2;
                return c();
            }
            throw new IllegalArgumentException("Given invalid width: " + i2);
        }

        public T setHeightRatio(float f2) {
            if (f2 >= 0.0f) {
                this.a.f2587j = f2;
                return c();
            }
            throw new IllegalArgumentException("Given invalid height ratio: " + f2);
        }

        public T setHighlightAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
            Shimmer shimmer = this.a;
            shimmer.d = (((int) (a(0.0f, 1.0f, f2) * 255.0f)) << 24) | (shimmer.d & ViewCompat.MEASURED_SIZE_MASK);
            return c();
        }

        public T setIntensity(float f2) {
            if (f2 >= 0.0f) {
                this.a.f2588k = f2;
                return c();
            }
            throw new IllegalArgumentException("Given invalid intensity value: " + f2);
        }

        public T setRepeatCount(int i2) {
            this.a.q = i2;
            return c();
        }

        public T setRepeatDelay(long j2) {
            if (j2 >= 0) {
                this.a.t = j2;
                return c();
            }
            throw new IllegalArgumentException("Given a negative repeat delay: " + j2);
        }

        public T setRepeatMode(int i2) {
            this.a.r = i2;
            return c();
        }

        public T setShape(int i2) {
            this.a.f2583f = i2;
            return c();
        }

        public T setStartDelay(long j2) {
            if (j2 >= 0) {
                this.a.u = j2;
                return c();
            }
            throw new IllegalArgumentException("Given a negative start delay: " + j2);
        }

        public T setTilt(float f2) {
            this.a.f2590m = f2;
            return c();
        }

        public T setWidthRatio(float f2) {
            if (f2 >= 0.0f) {
                this.a.f2586i = f2;
                return c();
            }
            throw new IllegalArgumentException("Given invalid width ratio: " + f2);
        }
    }

    /* loaded from: classes13.dex */
    public static class ColorHighlightBuilder extends Builder<ColorHighlightBuilder> {
        public ColorHighlightBuilder() {
            this.a.p = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.jd.dynamic.lib.views.skeleton.shimmer.Shimmer.Builder
        public /* synthetic */ ColorHighlightBuilder b(TypedArray typedArray) {
            e(typedArray);
            return this;
        }

        @Override // com.jd.dynamic.lib.views.skeleton.shimmer.Shimmer.Builder
        protected /* synthetic */ ColorHighlightBuilder c() {
            d();
            return this;
        }

        protected ColorHighlightBuilder d() {
            return this;
        }

        ColorHighlightBuilder e(TypedArray typedArray) {
            super.b(typedArray);
            int i2 = R.styleable.ShimmerFrameLayout_shimmer_base_color;
            if (typedArray.hasValue(i2)) {
                setBaseColor(typedArray.getColor(i2, this.a.f2582e));
            }
            int i3 = R.styleable.ShimmerFrameLayout_shimmer_highlight_color;
            if (typedArray.hasValue(i3)) {
                setHighlightColor(typedArray.getColor(i3, this.a.d));
            }
            d();
            return this;
        }

        public ColorHighlightBuilder setBaseColor(@ColorInt int i2) {
            Shimmer shimmer = this.a;
            shimmer.f2582e = (i2 & ViewCompat.MEASURED_SIZE_MASK) | (shimmer.f2582e & (-16777216));
            d();
            return this;
        }

        public ColorHighlightBuilder setHighlightColor(@ColorInt int i2) {
            this.a.d = i2;
            d();
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Direction {
        public static final int BOTTOM_TO_TOP = 3;
        public static final int LEFT_TO_RIGHT = 0;
        public static final int RIGHT_TO_LEFT = 2;
        public static final int TOP_TO_BOTTOM = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Shape {
        public static final int LINEAR = 0;
        public static final int RADIAL = 1;
    }

    Shimmer() {
        new RectF();
        this.f2581c = 0;
        this.d = -1;
        this.f2582e = 1291845631;
        this.f2583f = 0;
        this.f2584g = 0;
        this.f2585h = 0;
        this.f2586i = 1.0f;
        this.f2587j = 1.0f;
        this.f2588k = 0.0f;
        this.f2589l = 0.5f;
        this.f2590m = 20.0f;
        this.f2591n = true;
        this.o = true;
        this.p = true;
        this.q = -1;
        this.r = 1;
        this.s = 1000L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a(int i2) {
        int i3 = this.f2584g;
        return i3 > 0 ? i3 : Math.round(this.f2586i * i2);
    }

    void b() {
        if (this.f2583f != 1) {
            int[] iArr = this.b;
            int i2 = this.f2582e;
            iArr[0] = i2;
            int i3 = this.d;
            iArr[1] = i3;
            iArr[2] = i3;
            iArr[3] = i2;
            return;
        }
        int[] iArr2 = this.b;
        int i4 = this.d;
        iArr2[0] = i4;
        iArr2[1] = i4;
        int i5 = this.f2582e;
        iArr2[2] = i5;
        iArr2[3] = i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(int i2) {
        int i3 = this.f2585h;
        return i3 > 0 ? i3 : Math.round(this.f2587j * i2);
    }

    void d() {
        if (this.f2583f != 1) {
            this.a[0] = Math.max(((1.0f - this.f2588k) - this.f2589l) / 2.0f, 0.0f);
            this.a[1] = Math.max(((1.0f - this.f2588k) - 0.001f) / 2.0f, 0.0f);
            this.a[2] = Math.min(((this.f2588k + 1.0f) + 0.001f) / 2.0f, 1.0f);
            this.a[3] = Math.min(((this.f2588k + 1.0f) + this.f2589l) / 2.0f, 1.0f);
            return;
        }
        float[] fArr = this.a;
        fArr[0] = 0.0f;
        fArr[1] = Math.min(this.f2588k, 1.0f);
        this.a[2] = Math.min(this.f2588k + this.f2589l, 1.0f);
        this.a[3] = 1.0f;
    }
}
