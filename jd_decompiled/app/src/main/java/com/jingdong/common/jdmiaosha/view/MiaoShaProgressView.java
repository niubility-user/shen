package com.jingdong.common.jdmiaosha.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.annotation.FloatRange;
import androidx.core.app.NotificationCompat;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdreact.plugin.gradient.JDReactLinearGradientManager;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010H\u001a\u00020G\u00a2\u0006\u0004\bI\u0010JB\u001b\b\u0016\u0012\u0006\u0010H\u001a\u00020G\u0012\b\u0010L\u001a\u0004\u0018\u00010K\u00a2\u0006\u0004\bI\u0010MB#\b\u0016\u0012\u0006\u0010H\u001a\u00020G\u0012\b\u0010L\u001a\u0004\u0018\u00010K\u0012\u0006\u0010N\u001a\u00020\t\u00a2\u0006\u0004\bI\u0010OJ+\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ%\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0016\u001a\u00020\u000e2\b\b\u0001\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u000e2\b\b\u0001\u0010\u0013\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001c\u001a\u00020\u000e2\n\u0010\u001b\u001a\u00020\u001a\"\u00020\t\u00a2\u0006\u0004\b\u001c\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u000e2\n\u0010\u001b\u001a\u00020\u001a\"\u00020\t\u00a2\u0006\u0004\b\u001e\u0010\u001dJ\u001d\u0010!\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\t\u00a2\u0006\u0004\b!\u0010\u0012J\u0015\u0010#\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\t\u00a2\u0006\u0004\b#\u0010$J\u0017\u0010'\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020%H\u0014\u00a2\u0006\u0004\b'\u0010(R\u0016\u0010*\u001a\u00020)8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010,\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b,\u0010-R\u0018\u0010.\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00100\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b0\u00101R\u0018\u00102\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u0010/R\u0016\u00103\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b3\u0010-R\"\u00104\u001a\u00020)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b4\u0010+\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0018\u0010:\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b:\u0010;R\"\u0010<\u001a\u00020)8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010+\u001a\u0004\b=\u00106\"\u0004\b>\u00108R\u0016\u0010?\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b?\u0010-R\u0016\u0010@\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bB\u0010CR\u0016\u0010D\u001a\u00020\t8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bD\u0010-R\u0018\u0010E\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bE\u00101R\u0018\u0010F\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\bF\u00101\u00a8\u0006P"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/MiaoShaProgressView;", "Landroid/view/View;", "Landroid/graphics/Bitmap;", "srcBitmap", "", "desWidth", "desHeight", "scaleBitmap", "(Landroid/graphics/Bitmap;FF)Landroid/graphics/Bitmap;", "", "width", "height", "", "isRound", "", "initSize", "(IIZ)V", "initIconSize", "(II)V", NotificationCompat.CATEGORY_PROGRESS, "", "duration", "setProgressWithAnimation", "(FJ)V", "setProgress", "(F)V", "", JDReactLinearGradientManager.PROP_COLORS, "setBgColor", "([I)V", "setFrontColor", "bgRes", "ftRes", "setDrawable", "iconRes", "setIconDrawable", "(I)V", "Landroid/graphics/Canvas;", "canvas", "onDraw", "(Landroid/graphics/Canvas;)V", "Landroid/graphics/Paint;", "mIconPaint", "Landroid/graphics/Paint;", "mWidth", "I", "mBgColorArray", "[I", "mIconBitamp", "Landroid/graphics/Bitmap;", "mFrontColorArray", "mIconHeight", "mFrontPaint", "getMFrontPaint", "()Landroid/graphics/Paint;", "setMFrontPaint", "(Landroid/graphics/Paint;)V", "Landroid/animation/ValueAnimator;", "mValueAnimator", "Landroid/animation/ValueAnimator;", "mBgPaint", "getMBgPaint", "setMBgPaint", "mIconWidth", "bRound", "Z", "mProgress", "F", "mHeight", "mBgBitmap", "mFrontBitmap", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class MiaoShaProgressView extends View {
    private HashMap _$_findViewCache;
    private boolean bRound;
    private Bitmap mBgBitmap;
    private int[] mBgColorArray;
    @NotNull
    private Paint mBgPaint;
    private Bitmap mFrontBitmap;
    private int[] mFrontColorArray;
    @NotNull
    private Paint mFrontPaint;
    private int mHeight;
    private Bitmap mIconBitamp;
    private int mIconHeight;
    private Paint mIconPaint;
    private int mIconWidth;
    private float mProgress;
    private ValueAnimator mValueAnimator;
    private int mWidth;

    public MiaoShaProgressView(@NotNull Context context) {
        this(context, null);
    }

    private final Bitmap scaleBitmap(Bitmap srcBitmap, float desWidth, float desHeight) {
        if (srcBitmap == null) {
            return null;
        }
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        if (height <= 0 || width <= 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(desWidth / width, desHeight / height);
        Bitmap createBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, false);
        if (!srcBitmap.isRecycled()) {
            srcBitmap.recycle();
        }
        return createBitmap;
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i2) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            this._$_findViewCache.put(Integer.valueOf(i2), findViewById);
            return findViewById;
        }
        return view;
    }

    @NotNull
    public final Paint getMBgPaint() {
        return this.mBgPaint;
    }

    @NotNull
    public final Paint getMFrontPaint() {
        return this.mFrontPaint;
    }

    public final void initIconSize(int width, int height) {
        if (width <= 0 || height <= 0) {
            return;
        }
        this.mIconWidth = width;
        this.mIconHeight = height;
    }

    public final void initSize(int width, int height, boolean isRound) {
        if (width <= 0 || height <= 0) {
            return;
        }
        this.mWidth = width;
        this.mHeight = height;
        float f2 = height;
        this.mBgPaint.setStrokeWidth(f2);
        this.mFrontPaint.setStrokeWidth(f2);
        this.bRound = isRound;
        this.mBgPaint.setStrokeCap(isRound ? Paint.Cap.ROUND : Paint.Cap.SQUARE);
        this.mFrontPaint.setStrokeCap(this.bRound ? Paint.Cap.ROUND : Paint.Cap.SQUARE);
    }

    @Override // android.view.View
    protected void onDraw(@NotNull Canvas canvas) {
        int i2;
        super.onDraw(canvas);
        if (this.mWidth == 0 || (i2 = this.mHeight) == 0) {
            return;
        }
        float f2 = 2;
        float max = Math.max(this.mIconHeight, i2) / f2;
        float f3 = (this.bRound ? max : 0.0f) + 0.0f;
        float f4 = this.mProgress;
        int i3 = this.mWidth;
        float f5 = ((float) i3) * f4 > f2 * f3 ? (f4 * i3) - f3 : f3 + 0.5f;
        if (this.mFrontColorArray != null) {
            Paint paint = this.mFrontPaint;
            int[] iArr = this.mFrontColorArray;
            if (iArr == null) {
                Intrinsics.throwNpe();
            }
            int i4 = iArr[0];
            int[] iArr2 = this.mFrontColorArray;
            if (iArr2 == null) {
                Intrinsics.throwNpe();
            }
            paint.setShader(new LinearGradient(f3, max, f5, max, i4, iArr2[1], Shader.TileMode.CLAMP));
        }
        canvas.drawLine(f3, max, this.mWidth - f3, max, this.mBgPaint);
        if (this.mProgress > 0) {
            canvas.drawLine(f3, max, f5, max, this.mFrontPaint);
            Bitmap bitmap = this.mIconBitamp;
            if (bitmap == null || bitmap == null) {
                return;
            }
            canvas.drawBitmap(bitmap, (f5 - (this.mIconWidth / 2)) + 1, 0.0f, this.mIconPaint);
        }
    }

    public final void setBgColor(@NotNull int... colors) {
        if (colors.length == 0) {
            return;
        }
        if (colors.length == 1) {
            this.mBgPaint.setColor(colors[0]);
            return;
        }
        this.mBgColorArray = new int[]{colors[0], colors[1]};
        float f2 = this.mHeight / 2;
        float f3 = (this.bRound ? f2 : 0.0f) + 0.0f;
        Paint paint = this.mBgPaint;
        float f4 = this.mWidth - f3;
        int[] iArr = this.mBgColorArray;
        if (iArr == null) {
            Intrinsics.throwNpe();
        }
        int i2 = iArr[0];
        int[] iArr2 = this.mBgColorArray;
        if (iArr2 == null) {
            Intrinsics.throwNpe();
        }
        paint.setShader(new LinearGradient(f3, f2, f4, f2, i2, iArr2[1], Shader.TileMode.CLAMP));
    }

    public final void setDrawable(int bgRes, int ftRes) {
        if (this.mWidth == 0 || this.mHeight == 0 || bgRes == -1 || ftRes == -1) {
            return;
        }
        try {
            if (this.mBgBitmap == null) {
                Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), bgRes);
                this.mBgBitmap = decodeResource;
                Bitmap scaleBitmap = scaleBitmap(decodeResource, this.mWidth, this.mHeight);
                if (scaleBitmap != null) {
                    Paint paint = this.mBgPaint;
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    paint.setShader(new BitmapShader(scaleBitmap, tileMode, tileMode));
                }
            }
            if (this.mFrontBitmap == null) {
                Bitmap decodeResource2 = BitmapFactory.decodeResource(getResources(), ftRes);
                this.mFrontBitmap = decodeResource2;
                Bitmap scaleBitmap2 = scaleBitmap(decodeResource2, this.mWidth, this.mHeight);
                if (scaleBitmap2 != null) {
                    Paint paint2 = this.mFrontPaint;
                    Shader.TileMode tileMode2 = Shader.TileMode.CLAMP;
                    paint2.setShader(new BitmapShader(scaleBitmap2, tileMode2, tileMode2));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void setFrontColor(@NotNull int... colors) {
        if (colors.length == 0) {
            return;
        }
        if (colors.length == 1) {
            this.mFrontPaint.setColor(colors[0]);
        } else {
            this.mFrontColorArray = new int[]{colors[0], colors[1]};
        }
    }

    public final void setIconDrawable(int iconRes) {
        Bitmap decodeResource;
        if (this.mIconWidth == 0 || this.mIconHeight == 0) {
            return;
        }
        try {
            if (this.mIconBitamp != null || (decodeResource = BitmapFactory.decodeResource(getResources(), iconRes)) == null) {
                return;
            }
            this.mIconBitamp = scaleBitmap(decodeResource, this.mIconWidth, this.mIconHeight);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void setMBgPaint(@NotNull Paint paint) {
        this.mBgPaint = paint;
    }

    public final void setMFrontPaint(@NotNull Paint paint) {
        this.mFrontPaint = paint;
    }

    public final void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float progress) {
        if (this.mWidth == 0 || this.mHeight == 0) {
            return;
        }
        this.mProgress = progress;
        postInvalidate();
    }

    public final void setProgressWithAnimation(@FloatRange(from = 0.0d, to = 1.0d) float progress, long duration) {
        if (this.mWidth == 0 || this.mHeight == 0 || progress < 0) {
            return;
        }
        if (this.mValueAnimator == null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[0]);
            this.mValueAnimator = ofFloat;
            if (ofFloat != null) {
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.jdmiaosha.view.MiaoShaProgressView$setProgressWithAnimation$1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator animation) {
                        MiaoShaProgressView miaoShaProgressView = MiaoShaProgressView.this;
                        Intrinsics.checkExpressionValueIsNotNull(animation, "animation");
                        Object animatedValue = animation.getAnimatedValue();
                        if (animatedValue == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Float");
                        }
                        miaoShaProgressView.setProgress(((Float) animatedValue).floatValue());
                    }
                });
            }
        }
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimator2 = this.mValueAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.setInterpolator(new DecelerateInterpolator());
        }
        setProgress(0.0f);
        ValueAnimator valueAnimator3 = this.mValueAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.setFloatValues(0.0f, progress);
        }
        ValueAnimator valueAnimator4 = this.mValueAnimator;
        if (valueAnimator4 != null) {
            valueAnimator4.setDuration(((float) duration) * progress);
        }
        ValueAnimator valueAnimator5 = this.mValueAnimator;
        if (valueAnimator5 != null) {
            valueAnimator5.setStartDelay(100L);
        }
        ValueAnimator valueAnimator6 = this.mValueAnimator;
        if (valueAnimator6 != null) {
            valueAnimator6.start();
        }
    }

    public MiaoShaProgressView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MiaoShaProgressView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mBgPaint = new Paint(1);
        this.mFrontPaint = new Paint(1);
        this.mIconPaint = new Paint(1);
        this.bRound = true;
    }
}
