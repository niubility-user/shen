package com.jd.lib.cashier.sdk.core.ui.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\b\u0016\u0012\u0006\u0010a\u001a\u00020`\u0012\b\u0010c\u001a\u0004\u0018\u00010b\u00a2\u0006\u0004\bd\u0010eB#\b\u0016\u0012\u0006\u0010a\u001a\u00020`\u0012\b\u0010c\u001a\u0004\u0018\u00010b\u0012\u0006\u0010f\u001a\u00020\b\u00a2\u0006\u0004\bd\u0010gJ\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0014J9\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0002\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\bH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010\u001d\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u001d\u0010\u0014J\r\u0010 \u001a\u00020\u0005\u00a2\u0006\u0004\b \u0010\u0014J\u0017\u0010!\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0014\u00a2\u0006\u0004\b!\u0010\u0007J\r\u0010\"\u001a\u00020\u0005\u00a2\u0006\u0004\b\"\u0010\u0014J\u000f\u0010#\u001a\u00020\u0005H\u0014\u00a2\u0006\u0004\b#\u0010\u0014J\u0015\u0010%\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\b\u00a2\u0006\u0004\b%\u0010&J\u0015\u0010)\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'\u00a2\u0006\u0004\b)\u0010*J\r\u0010+\u001a\u00020\u0005\u00a2\u0006\u0004\b+\u0010\u0014J\u0019\u0010.\u001a\u00020\u00052\b\u0010-\u001a\u0004\u0018\u00010,H\u0016\u00a2\u0006\u0004\b.\u0010/J\u0019\u00100\u001a\u00020\u00052\b\u0010-\u001a\u0004\u0018\u00010,H\u0016\u00a2\u0006\u0004\b0\u0010/J\u0019\u00101\u001a\u00020\u00052\b\u0010-\u001a\u0004\u0018\u00010,H\u0016\u00a2\u0006\u0004\b1\u0010/J\u0019\u00102\u001a\u00020\u00052\b\u0010-\u001a\u0004\u0018\u00010,H\u0016\u00a2\u0006\u0004\b2\u0010/R\u0016\u00104\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u00103R\"\u0010<\u001a\u0002058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001c\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00160=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b>\u0010?R\"\u0010C\u001a\u0002058\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bA\u00107\u001a\u0004\bB\u00109\"\u0004\b>\u0010;R\u0016\u0010E\u001a\u00020\r8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b8\u0010DR$\u0010L\u001a\u0004\u0018\u00010F8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bG\u0010H\u001a\u0004\bI\u0010J\"\u0004\b$\u0010KR\u001e\u0010Q\u001a\n N*\u0004\u0018\u00010M0M8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\bO\u0010PR\u0018\u0010S\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010RR\u001c\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010TR\u0016\u0010W\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010VR\u0018\u0010X\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010RR\u001e\u0010Z\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190=8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010YR\u0018\u0010[\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010RR\u0016\u0010\\\u001a\u0002058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u00107R\u0016\u0010]\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u00103R\u0016\u0010^\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u00103R\u0016\u0010_\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u00103\u00a8\u0006h"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/RollingDigitTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "Landroid/animation/Animator$AnimatorListener;", "Landroid/graphics/Canvas;", "canvas", "", "d", "(Landroid/graphics/Canvas;)V", "", "c", ThemeTitleConstant.TITLE_BACK_DRAWABLE_ID, "k", "(II)I", "", "s", "Ljava/util/ArrayList;", "", "f", "(Ljava/lang/String;)Ljava/util/ArrayList;", "q", "()V", "text", "", JshopConst.JSHOP_PROMOTIO_X, JshopConst.JSHOP_PROMOTIO_Y, "Landroid/graphics/Paint;", WebPerfManager.PAINT, com.jingdong.app.mall.e.a, "(Landroid/graphics/Canvas;Ljava/lang/String;FFLandroid/graphics/Paint;)V", "j", "g", "(I)F", JshopConst.JSHOP_PROMOTIO_H, "onDraw", "r", "onDetachedFromWindow", NotifyType.LIGHTS, PersonalConstants.ICON_STYLE_N, "(I)V", "", ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID, "o", "([I)V", "p", "Landroid/animation/Animator;", "animation", "onAnimationRepeat", "(Landroid/animation/Animator;)V", "onAnimationEnd", "onAnimationCancel", "onAnimationStart", "I", "maxLineCount", "", "u", "Z", "i", "()Z", "setRollingAnimating", "(Z)V", "isRollingAnimating", "", "m", "[Ljava/lang/Float;", "fontWidthArray", "t", "getHasPointAnimation", "hasPointAnimation", "Ljava/lang/String;", "currentText", "", "v", "Ljava/lang/CharSequence;", "getFinalShowPrice", "()Ljava/lang/CharSequence;", "(Ljava/lang/CharSequence;)V", "finalShowPrice", "Landroid/animation/ValueAnimator;", "kotlin.jvm.PlatformType", JshopConst.JSHOP_PROMOTIO_W, "Landroid/animation/ValueAnimator;", "valueAnimator", "[I", "overLine", "Ljava/util/ArrayList;", "arrayListText", "F", "pointWidth", "speedList", "[Landroid/graphics/Paint;", "mPaintArray", "speedSum", "isFirstRun", "charLength", "pointIndex", "mBaseline", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class RollingDigitTextView extends AppCompatTextView implements Animator.AnimatorListener {
    @NotNull
    private static final String x;

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private int maxLineCount;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private int charLength;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private String currentText;

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private int[] speedList;

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private int[] speedSum;

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private int[] overLine;

    /* renamed from: m  reason: collision with root package name and from kotlin metadata */
    private Float[] fontWidthArray;

    /* renamed from: n  reason: collision with root package name and from kotlin metadata */
    private boolean isFirstRun;

    /* renamed from: o  reason: from kotlin metadata */
    private Paint[] mPaintArray;

    /* renamed from: p  reason: from kotlin metadata */
    private ArrayList<Character> arrayListText;

    /* renamed from: q  reason: from kotlin metadata */
    private float pointWidth;

    /* renamed from: r  reason: from kotlin metadata */
    private int pointIndex;

    /* renamed from: s  reason: from kotlin metadata */
    private int mBaseline;

    /* renamed from: t  reason: from kotlin metadata */
    private boolean hasPointAnimation;

    /* renamed from: u  reason: from kotlin metadata */
    private boolean isRollingAnimating;
    @Nullable

    /* renamed from: v  reason: from kotlin metadata */
    private CharSequence finalShowPrice;

    /* renamed from: w  reason: from kotlin metadata */
    private final ValueAnimator valueAnimator;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            int[] iArr;
            if (RollingDigitTextView.this.getIsRollingAnimating()) {
                int[] iArr2 = RollingDigitTextView.this.speedSum;
                if (iArr2 != null) {
                    if ((!(iArr2.length == 0)) == true && (iArr = RollingDigitTextView.this.speedList) != null) {
                        if ((!(iArr.length == 0)) == true) {
                            int[] iArr3 = RollingDigitTextView.this.speedSum;
                            int length = iArr3 != null ? iArr3.length : 0;
                            int[] iArr4 = RollingDigitTextView.this.speedList;
                            int length2 = iArr4 != null ? iArr4.length : 0;
                            int i2 = RollingDigitTextView.this.charLength;
                            for (int i3 = 0; i3 < i2; i3++) {
                                if (i3 < length && i3 <= length2) {
                                    int[] iArr5 = RollingDigitTextView.this.speedSum;
                                    if (iArr5 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    int[] iArr6 = RollingDigitTextView.this.speedSum;
                                    if (iArr6 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    int i4 = iArr6[i3];
                                    int[] iArr7 = RollingDigitTextView.this.speedList;
                                    if (iArr7 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    iArr5[i3] = i4 - iArr7[i3];
                                }
                            }
                        }
                    }
                }
                RollingDigitTextView.this.invalidate();
                return;
            }
            RollingDigitTextView.this.r();
        }
    }

    static {
        String simpleName = RollingDigitTextView.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "RollingDigitTextView::class.java.simpleName");
        x = simpleName;
    }

    public RollingDigitTextView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxLineCount = 10;
        this.currentText = "";
        Float valueOf = Float.valueOf(0.0f);
        this.fontWidthArray = new Float[]{valueOf, valueOf};
        this.isFirstRun = true;
        this.mPaintArray = new Paint[]{null, null};
        this.arrayListText = new ArrayList<>();
        this.pointIndex = -1;
        this.finalShowPrice = "";
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addListener(this);
        this.valueAnimator = ofFloat;
    }

    private final void d(Canvas canvas) {
        Paint paint;
        int i2;
        int i3 = this.charLength;
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = this.maxLineCount;
            for (int i6 = 1; i6 < i5; i6++) {
                if (i6 == this.maxLineCount - 1) {
                    int i7 = this.mBaseline * i6;
                    int[] iArr = this.speedSum;
                    if (iArr == null) {
                        Intrinsics.throwNpe();
                    }
                    if (i7 + iArr[i4] <= this.mBaseline) {
                        int[] iArr2 = this.speedList;
                        if (iArr2 == null) {
                            Intrinsics.throwNpe();
                        }
                        iArr2[i4] = 0;
                        int[] iArr3 = this.overLine;
                        if (iArr3 == null) {
                            Intrinsics.throwNpe();
                        }
                        iArr3[i4] = 1;
                        int i8 = this.charLength;
                        int i9 = 0;
                        for (int i10 = 0; i10 < i8; i10++) {
                            int[] iArr4 = this.overLine;
                            if (iArr4 == null) {
                                Intrinsics.throwNpe();
                            }
                            i9 += iArr4[i10];
                        }
                        if (i9 == (this.charLength * 2) - 1) {
                            r();
                            this.isRollingAnimating = false;
                        }
                    }
                }
                if (i4 < this.pointIndex) {
                    paint = this.mPaintArray[0];
                } else {
                    paint = this.mPaintArray[1];
                }
                Paint paint2 = paint;
                int[] iArr5 = this.overLine;
                if (iArr5 == null) {
                    Intrinsics.throwNpe();
                }
                if (iArr5[i4] == 0) {
                    int k2 = k(this.arrayListText.get(i4).charValue(), (this.maxLineCount - i6) - 1);
                    if (k2 >= 0 && 9 >= k2) {
                        String valueOf = String.valueOf(k(this.arrayListText.get(i4).charValue(), (this.maxLineCount - i6) - 1));
                        float g2 = g(i4);
                        float f2 = this.mBaseline * i6;
                        if (this.speedSum == null) {
                            Intrinsics.throwNpe();
                        }
                        e(canvas, valueOf, g2, f2 + r7[i4], paint2);
                    } else {
                        if (this.hasPointAnimation) {
                            int[] iArr6 = this.speedSum;
                            if (iArr6 == null) {
                                Intrinsics.throwNpe();
                            }
                            i2 = iArr6[i4];
                        } else {
                            i2 = 0;
                        }
                        e(canvas, OrderISVUtil.MONEY_DECIMAL, g(i4), (this.mBaseline * i6) + i2, paint2);
                    }
                } else {
                    int[] iArr7 = this.overLine;
                    if (iArr7 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (iArr7[i4] == 1) {
                        int[] iArr8 = this.overLine;
                        if (iArr8 == null) {
                            Intrinsics.throwNpe();
                        }
                        iArr8[i4] = iArr8[i4] + 1;
                        e(canvas, String.valueOf(this.arrayListText.get(i4).charValue()), g(i4), this.mBaseline, paint2);
                    }
                }
            }
        }
    }

    private final void e(Canvas canvas, String text, float x2, float y, Paint paint) {
        if (paint == null || y < (-getMeasuredHeight()) || y > getMeasuredHeight() * 2) {
            return;
        }
        canvas.drawText(text, x2, y, paint);
    }

    private final ArrayList<Character> f(String s) {
        this.pointIndex = -1;
        ArrayList<Character> arrayList = new ArrayList<>();
        int length = s.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = s.charAt(i2);
            arrayList.add(Character.valueOf(charAt));
            if (charAt == '.') {
                this.pointIndex = i2;
            }
        }
        return arrayList;
    }

    private final float g(int j2) {
        int i2 = this.pointIndex;
        if (j2 > i2 && i2 != -1) {
            return (this.fontWidthArray[0].floatValue() * this.pointIndex) + this.pointWidth + (this.fontWidthArray[1].floatValue() * ((j2 - this.pointIndex) - 1));
        }
        return this.fontWidthArray[0].floatValue() * j2;
    }

    private final void j() {
        r();
    }

    private final int k(int c2, int back) {
        if (c2 < 48 || c2 > 57) {
            return c2;
        }
        int i2 = c2 - 48;
        if (back == 0) {
            return i2;
        }
        int i3 = i2 - (back % 10);
        return i3 < 0 ? i3 + 10 : i3;
    }

    private final void q() {
        this.valueAnimator.cancel();
        this.valueAnimator.removeAllUpdateListeners();
        this.isRollingAnimating = true;
        ValueAnimator valueAnimator = this.valueAnimator;
        Intrinsics.checkExpressionValueIsNotNull(valueAnimator, "valueAnimator");
        valueAnimator.setRepeatCount(-1);
        ValueAnimator valueAnimator2 = this.valueAnimator;
        Intrinsics.checkExpressionValueIsNotNull(valueAnimator2, "valueAnimator");
        valueAnimator2.setInterpolator(new DecelerateInterpolator());
        this.valueAnimator.addUpdateListener(new a());
        this.valueAnimator.start();
    }

    public final void h() {
        if (!TextUtils.equals("2", m.f().j()) && !TextUtils.equals("3", m.f().j())) {
            TextPaint paint = getPaint();
            Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
            paint.setColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
        } else {
            TextPaint paint2 = getPaint();
            Intrinsics.checkExpressionValueIsNotNull(paint2, "paint");
            paint2.setColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        }
        this.mPaintArray[0] = getPaint();
        this.mPaintArray[1] = new Paint(getPaint());
        Paint paint3 = this.mPaintArray[0];
        float textSize = (paint3 != null ? paint3.getTextSize() : 50.0f) * 0.68f;
        DisplayMetrics i2 = y.i();
        if (i2 != null) {
            Paint paint4 = this.mPaintArray[1];
            if (paint4 != null) {
                paint4.setTextSize(TypedValue.applyDimension(0, textSize, i2));
            }
        } else {
            Paint paint5 = this.mPaintArray[1];
            if (paint5 != null) {
                paint5.setTextSize(textSize);
            }
        }
        TextPaint paint6 = getPaint();
        Paint.FontMetricsInt fontMetricsInt = paint6 != null ? paint6.getFontMetricsInt() : null;
        int measuredHeight = getMeasuredHeight();
        if (fontMetricsInt == null) {
            Intrinsics.throwNpe();
        }
        int i3 = fontMetricsInt.top;
        this.mBaseline = (((measuredHeight - fontMetricsInt.bottom) + i3) / 2) - i3;
        Paint paint7 = this.mPaintArray[1];
        Float valueOf = paint7 != null ? Float.valueOf(paint7.measureText(OrderISVUtil.MONEY_DECIMAL)) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        this.pointWidth = valueOf.floatValue();
        float[] fArr = new float[2];
        Paint paint8 = this.mPaintArray[0];
        if (paint8 != null) {
            paint8.getTextWidths("99", fArr);
        }
        this.fontWidthArray[0] = Float.valueOf(fArr[0]);
        float[] fArr2 = new float[2];
        Paint paint9 = this.mPaintArray[1];
        if (paint9 != null) {
            paint9.getTextWidths("99", fArr2);
        }
        this.fontWidthArray[1] = Float.valueOf(fArr2[0]);
    }

    /* renamed from: i  reason: from getter */
    public final boolean getIsRollingAnimating() {
        return this.isRollingAnimating;
    }

    public final void l(@Nullable CharSequence charSequence) {
        this.finalShowPrice = charSequence;
    }

    public final void m(boolean z) {
        this.hasPointAnimation = z;
    }

    public final void n(int l2) {
        this.maxLineCount = l2;
    }

    public final void o(@NotNull int[] list) {
        this.currentText = getText().toString();
        this.speedSum = new int[list.length];
        this.overLine = new int[list.length];
        this.speedList = list;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(@Nullable Animator animation) {
        r.b(x, "onAnimationCancel");
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(@Nullable Animator animation) {
        r.b(x, "onAnimationEnd");
        this.isRollingAnimating = false;
        setText(this.finalShowPrice);
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(@Nullable Animator animation) {
        r.b(x, "onAnimationRepeat");
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(@Nullable Animator animation) {
        r.b(x, "onAnimationStart");
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        j();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(@NotNull Canvas canvas) {
        if (!this.isRollingAnimating) {
            super.onDraw(canvas);
        } else if (this.isFirstRun) {
            this.isFirstRun = false;
            h();
        } else {
            d(canvas);
        }
    }

    public final void p() {
        this.isRollingAnimating = false;
        if (getText() == null) {
            return;
        }
        try {
            this.currentText = getText().toString();
            this.charLength = getText().length();
            this.arrayListText = f(this.currentText);
            if ((!r0.isEmpty()) == true) {
                q();
            }
        } catch (Exception e2) {
            r.b(x, String.valueOf(e2));
        }
    }

    public final void r() {
        ValueAnimator valueAnimator = this.valueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
        }
        ValueAnimator valueAnimator2 = this.valueAnimator;
        if (valueAnimator2 != null) {
            valueAnimator2.cancel();
        }
        this.isRollingAnimating = false;
    }

    public RollingDigitTextView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.maxLineCount = 10;
        this.currentText = "";
        Float valueOf = Float.valueOf(0.0f);
        this.fontWidthArray = new Float[]{valueOf, valueOf};
        this.isFirstRun = true;
        this.mPaintArray = new Paint[]{null, null};
        this.arrayListText = new ArrayList<>();
        this.pointIndex = -1;
        this.finalShowPrice = "";
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addListener(this);
        this.valueAnimator = ofFloat;
    }
}
