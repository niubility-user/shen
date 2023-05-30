package com.jingdong.common.jdmiaosha.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.R;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 g2\u00020\u0001:\u0001gB'\b\u0007\u0012\u0006\u0010a\u001a\u00020`\u0012\n\b\u0002\u0010c\u001a\u0004\u0018\u00010b\u0012\b\b\u0002\u0010d\u001a\u00020\u0007\u00a2\u0006\u0004\be\u0010fJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0013\u0010\u0012J\u0017\u0010\u0016\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\u0019\u0010\u0012J\u0017\u0010\u001b\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0004\b\u001b\u0010\u0017J\u0017\u0010\u001d\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0004\b\u001d\u0010\u0017J\u0017\u0010\u001f\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0004\b\u001f\u0010\u0017J\u0017\u0010!\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\f\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u00042\b\u0010#\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0004\b$\u0010\u0017J\u001f\u0010%\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b%\u0010\u000bJ\u001f\u0010'\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u000fH\u0016\u00a2\u0006\u0004\b'\u0010(J#\u0010+\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010*\u001a\u0004\u0018\u00010)H\u0016\u00a2\u0006\u0004\b+\u0010,R\"\u0010-\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u0010\u0006R\u0016\u00102\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b2\u00103R\"\u00104\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R$\u0010:\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010\u0012R\"\u0010?\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b?\u00103\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\"\u0010D\u001a\u00020\u000f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bD\u00105\u001a\u0004\bE\u00107\"\u0004\bF\u00109R\"\u0010G\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bG\u00103\u001a\u0004\bH\u0010A\"\u0004\bI\u0010CR$\u0010J\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bJ\u0010;\u001a\u0004\bK\u0010=\"\u0004\bL\u0010\u0012R\"\u0010M\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bM\u00103\u001a\u0004\bN\u0010A\"\u0004\bO\u0010CR\"\u0010P\u001a\u00020\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010\u000e\"\u0004\bS\u0010\"R\"\u0010T\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bT\u00103\u001a\u0004\bU\u0010A\"\u0004\bV\u0010CR\"\u0010W\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bW\u00103\u001a\u0004\bX\u0010A\"\u0004\bY\u0010CR\"\u0010Z\u001a\u00020\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bZ\u00103\u001a\u0004\b[\u0010A\"\u0004\b\\\u0010CR$\u0010]\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b]\u0010Q\u001a\u0004\b^\u0010\u000e\"\u0004\b_\u0010\"\u00a8\u0006h"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDPriceTextView;", "Landroid/widget/TextView;", "", "text", "", "setPriceText", "(Ljava/lang/CharSequence;)V", "", "widthMeasureSpec", "heightMeasureSpec", "removeSpace", "(II)V", "", "getJDLastLineText", "()Ljava/lang/String;", "", "ratio", "setSymbolTextRatio", "(Ljava/lang/Float;)V", "setFractionTextRatio", "", "show", "setShowNonePrice", "(Ljava/lang/Boolean;)V", ApkDownloadTable.FIELD_SIZE, "setNonePriceTextSize", "strike", "setSupportStrike", "isExclude", "setExcludeSpacePadding", "isNormalText", "setNormalText", "str", "setNonePriceText", "(Ljava/lang/String;)V", ViewProps.VISIBLE, "setNonePriceVisible", "onMeasure", "unit", "setTextSize", "(IF)V", "Landroid/widget/TextView$BufferType;", "type", "setText", "(Ljava/lang/CharSequence;Landroid/widget/TextView$BufferType;)V", "mJDPriceText", "Ljava/lang/CharSequence;", "getMJDPriceText", "()Ljava/lang/CharSequence;", "setMJDPriceText", "nonePriceVisible", "Z", "mFractionTextRatio", "F", "getMFractionTextRatio", "()F", "setMFractionTextRatio", "(F)V", "mNonePriceTextSize", "Ljava/lang/Float;", "getMNonePriceTextSize", "()Ljava/lang/Float;", "setMNonePriceTextSize", "mNormalText", "getMNormalText", "()Z", "setMNormalText", "(Z)V", "mSymbolTextRatio", "getMSymbolTextRatio", "setMSymbolTextRatio", "mShowNonePrice", "getMShowNonePrice", "setMShowNonePrice", "mPriceTextSize", "getMPriceTextSize", "setMPriceTextSize", "mSupportStrike", "getMSupportStrike", "setMSupportStrike", "mLastLineText", "Ljava/lang/String;", "getMLastLineText", "setMLastLineText", "mNonePriceTextBold", "getMNonePriceTextBold", "setMNonePriceTextBold", "mExcludeSpacePadding", "getMExcludeSpacePadding", "setMExcludeSpacePadding", "initSuccess", "getInitSuccess", "setInitSuccess", "mNonePriceText", "getMNonePriceText", "setMNonePriceText", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Companion", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes5.dex */
public final class JDPriceTextView extends TextView {
    private static final float DEFAULT_RATIO = 1.0f;
    private static final String DOT = ".";
    private static final String RMB = "\u00a5";
    private HashMap _$_findViewCache;
    private boolean initSuccess;
    private boolean mExcludeSpacePadding;
    private float mFractionTextRatio;
    @NotNull
    private CharSequence mJDPriceText;
    @NotNull
    private String mLastLineText;
    @Nullable
    private String mNonePriceText;
    private boolean mNonePriceTextBold;
    @Nullable
    private Float mNonePriceTextSize;
    private boolean mNormalText;
    @Nullable
    private Float mPriceTextSize;
    private boolean mShowNonePrice;
    private boolean mSupportStrike;
    private float mSymbolTextRatio;
    private boolean nonePriceVisible;
    private static final String NO_PRICE = StringUtil.getString(R.string.product_entity_no_price);

    @JvmOverloads
    public JDPriceTextView(@NotNull Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public JDPriceTextView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ JDPriceTextView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    private final String getJDLastLineText() {
        this.mLastLineText = "";
        if (getLineCount() == 1) {
            this.mLastLineText = getText().toString();
        } else if (getLineCount() > 1) {
            String obj = getText().toString();
            int lineEnd = getLayout().getLineEnd(getLineCount() - 2);
            int lineEnd2 = getLayout().getLineEnd(getLineCount() - 1);
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String substring = obj.substring(lineEnd, lineEnd2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            this.mLastLineText = substring;
        }
        return this.mLastLineText;
    }

    private final void removeSpace(int widthMeasureSpec, int heightMeasureSpec) {
        TextPaint paint = getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
        Rect rect = new Rect();
        getJDLastLineText();
        String str = this.mLastLineText;
        paint.getTextBounds(str, 0, str.length(), rect);
        Paint.FontMetricsInt fontMetricsInt = new Paint.FontMetricsInt();
        paint.getFontMetricsInt(fontMetricsInt);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() - (fontMetricsInt.bottom - rect.bottom));
    }

    private final void setPriceText(CharSequence text) {
        String result;
        boolean contains$default;
        boolean contains$default2;
        int indexOf$default;
        if (this.mShowNonePrice) {
            result = this.mNonePriceText;
            if (result == null) {
                result = NO_PRICE;
            }
        } else {
            result = "";
        }
        if (!TextUtils.isEmpty(text)) {
            try {
                String valueOf = String.valueOf(text);
                if (valueOf == null) {
                    Intrinsics.throwNpe();
                }
                if (Double.compare(Double.valueOf(valueOf).doubleValue(), 0) > 0) {
                    result = "\u00a5" + String.valueOf(text);
                }
            } catch (Exception e2) {
                if (text instanceof SpannedString) {
                    result = text.toString();
                } else {
                    e2.printStackTrace();
                }
            }
        }
        SpannableString spannableString = new SpannableString(result);
        Intrinsics.checkExpressionValueIsNotNull(result, "result");
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) result, (CharSequence) "\u00a5", false, 2, (Object) null);
        if (contains$default) {
            if ((!Intrinsics.areEqual(getTypeface(), FontsUtil.getTypeFace(getContext(), 4099))) != false) {
                FontsUtil.changeTextFont(this, 4099);
            }
            float f2 = this.mSymbolTextRatio;
            float f3 = 0;
            if (f2 > f3 && f2 < 1) {
                spannableString.setSpan(new RelativeSizeSpan(this.mSymbolTextRatio), 0, 1, 17);
            }
            contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) result, (CharSequence) ".", false, 2, (Object) null);
            if (contains$default2) {
                float f4 = this.mFractionTextRatio;
                if (f4 > f3 && f4 < 1) {
                    RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(this.mFractionTextRatio);
                    indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) result, ".", 0, false, 6, (Object) null);
                    spannableString.setSpan(relativeSizeSpan, indexOf$default, result.length(), 34);
                }
            }
            if (this.mSupportStrike) {
                TextPaint paint = getPaint();
                Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
                paint.setFlags(16);
                TextPaint paint2 = getPaint();
                Intrinsics.checkExpressionValueIsNotNull(paint2, "paint");
                paint2.setAntiAlias(true);
            }
            setVisibility(0);
        } else {
            if (this.mShowNonePrice) {
                String str = this.mNonePriceText;
                if (str == null) {
                    str = NO_PRICE;
                }
                if (Intrinsics.areEqual(result, str)) {
                    if (this.mNonePriceTextBold) {
                        setTypeface(Typeface.DEFAULT, 1);
                    } else {
                        setTypeface(Typeface.DEFAULT, 0);
                    }
                    Float f5 = this.mNonePriceTextSize;
                    if (f5 != null) {
                        setTextSize(0, f5.floatValue());
                        setVisibility(0);
                    }
                }
            }
            if (Intrinsics.areEqual(result, "")) {
                setVisibility(this.nonePriceVisible ? 4 : 8);
            }
        }
        this.mJDPriceText = spannableString;
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

    public final boolean getInitSuccess() {
        return this.initSuccess;
    }

    public final boolean getMExcludeSpacePadding() {
        return this.mExcludeSpacePadding;
    }

    public final float getMFractionTextRatio() {
        return this.mFractionTextRatio;
    }

    @NotNull
    public final CharSequence getMJDPriceText() {
        return this.mJDPriceText;
    }

    @NotNull
    public final String getMLastLineText() {
        return this.mLastLineText;
    }

    @Nullable
    public final String getMNonePriceText() {
        return this.mNonePriceText;
    }

    public final boolean getMNonePriceTextBold() {
        return this.mNonePriceTextBold;
    }

    @Nullable
    public final Float getMNonePriceTextSize() {
        return this.mNonePriceTextSize;
    }

    public final boolean getMNormalText() {
        return this.mNormalText;
    }

    @Nullable
    public final Float getMPriceTextSize() {
        return this.mPriceTextSize;
    }

    public final boolean getMShowNonePrice() {
        return this.mShowNonePrice;
    }

    public final boolean getMSupportStrike() {
        return this.mSupportStrike;
    }

    public final float getMSymbolTextRatio() {
        return this.mSymbolTextRatio;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.mExcludeSpacePadding) {
            removeSpace(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public final void setExcludeSpacePadding(@Nullable Boolean isExclude) {
        if (isExclude != null) {
            isExclude.booleanValue();
            if ((!Intrinsics.areEqual(isExclude, Boolean.valueOf(this.mExcludeSpacePadding))) == true) {
                this.mExcludeSpacePadding = isExclude.booleanValue();
                requestLayout();
                invalidate();
            }
        }
    }

    public final void setFractionTextRatio(@Nullable Float ratio) {
        if (ratio != null) {
            ratio.floatValue();
            if ((!Intrinsics.areEqual(ratio, this.mFractionTextRatio)) == true) {
                this.mFractionTextRatio = ratio.floatValue();
                requestLayout();
                invalidate();
            }
        }
    }

    public final void setInitSuccess(boolean z) {
        this.initSuccess = z;
    }

    public final void setMExcludeSpacePadding(boolean z) {
        this.mExcludeSpacePadding = z;
    }

    public final void setMFractionTextRatio(float f2) {
        this.mFractionTextRatio = f2;
    }

    public final void setMJDPriceText(@NotNull CharSequence charSequence) {
        this.mJDPriceText = charSequence;
    }

    public final void setMLastLineText(@NotNull String str) {
        this.mLastLineText = str;
    }

    public final void setMNonePriceText(@Nullable String str) {
        this.mNonePriceText = str;
    }

    public final void setMNonePriceTextBold(boolean z) {
        this.mNonePriceTextBold = z;
    }

    public final void setMNonePriceTextSize(@Nullable Float f2) {
        this.mNonePriceTextSize = f2;
    }

    public final void setMNormalText(boolean z) {
        this.mNormalText = z;
    }

    public final void setMPriceTextSize(@Nullable Float f2) {
        this.mPriceTextSize = f2;
    }

    public final void setMShowNonePrice(boolean z) {
        this.mShowNonePrice = z;
    }

    public final void setMSupportStrike(boolean z) {
        this.mSupportStrike = z;
    }

    public final void setMSymbolTextRatio(float f2) {
        this.mSymbolTextRatio = f2;
    }

    public final void setNonePriceText(@Nullable String str) {
        if (str != null) {
            this.mNonePriceText = str;
        }
    }

    public final void setNonePriceTextSize(@Nullable Float size) {
        if (size != null) {
            size.floatValue();
            if ((!Intrinsics.areEqual(size, this.mNonePriceTextSize)) == true) {
                this.mNonePriceTextSize = size;
                requestLayout();
                invalidate();
            }
        }
    }

    public final void setNonePriceVisible(@Nullable Boolean visible) {
        if (visible != null) {
            visible.booleanValue();
            this.nonePriceVisible = visible.booleanValue();
        }
    }

    public final void setNormalText(@Nullable Boolean isNormalText) {
        if (isNormalText != null) {
            isNormalText.booleanValue();
            if ((!Intrinsics.areEqual(isNormalText, Boolean.valueOf(this.mNormalText))) == true) {
                this.mNormalText = isNormalText.booleanValue();
                requestLayout();
                invalidate();
            }
        }
    }

    public final void setShowNonePrice(@Nullable Boolean show) {
        if (show != null) {
            show.booleanValue();
            if ((!Intrinsics.areEqual(show, Boolean.valueOf(this.mShowNonePrice))) == true) {
                this.mShowNonePrice = show.booleanValue();
                requestLayout();
                invalidate();
            }
        }
    }

    public final void setSupportStrike(@Nullable Boolean strike) {
        if (strike != null) {
            strike.booleanValue();
            if ((!Intrinsics.areEqual(strike, Boolean.valueOf(this.mSupportStrike))) == true) {
                boolean booleanValue = strike.booleanValue();
                this.mSupportStrike = booleanValue;
                if (!booleanValue) {
                    TextPaint paint = getPaint();
                    Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
                    paint.setFlags(1);
                }
                invalidate();
            }
        }
    }

    public final void setSymbolTextRatio(@Nullable Float ratio) {
        if (ratio != null) {
            ratio.floatValue();
            if ((!Intrinsics.areEqual(ratio, this.mSymbolTextRatio)) == true) {
                this.mSymbolTextRatio = ratio.floatValue();
                requestLayout();
                invalidate();
            }
        }
    }

    @Override // android.widget.TextView
    public void setText(@Nullable CharSequence text, @Nullable TextView.BufferType type) {
        if (this.initSuccess) {
            Float f2 = this.mPriceTextSize;
            if (f2 != null) {
                setTextSize(0, f2.floatValue());
            }
            if (this.mNormalText) {
                super.setText(text, type);
                return;
            }
            setPriceText(text);
            super.setText(this.mJDPriceText, type);
            return;
        }
        super.setText(text, type);
    }

    @Override // android.widget.TextView
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        this.mPriceTextSize = Float.valueOf(getTextSize());
    }

    @JvmOverloads
    public JDPriceTextView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mSymbolTextRatio = 1.0f;
        this.mFractionTextRatio = 1.0f;
        this.mJDPriceText = "";
        this.mLastLineText = "";
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDPriceTextView);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr\u2026tyleable.JDPriceTextView)");
        this.mSymbolTextRatio = obtainStyledAttributes.getFloat(R.styleable.JDPriceTextView_symbolTextRatio, 1.0f);
        this.mFractionTextRatio = obtainStyledAttributes.getFloat(R.styleable.JDPriceTextView_fractionTextRatio, 1.0f);
        this.mShowNonePrice = obtainStyledAttributes.getBoolean(R.styleable.JDPriceTextView_showNonePriceEnabled, true);
        this.mNonePriceText = obtainStyledAttributes.getString(R.styleable.JDPriceTextView_nonePriceText);
        this.mPriceTextSize = Float.valueOf(getTextSize());
        this.mNonePriceTextSize = Float.valueOf(obtainStyledAttributes.getDimension(R.styleable.JDPriceTextView_nonePriceTextSize, getTextSize()));
        this.nonePriceVisible = obtainStyledAttributes.getBoolean(R.styleable.JDPriceTextView_nonePriceVisible, false);
        this.mNonePriceTextBold = obtainStyledAttributes.getBoolean(R.styleable.JDPriceTextView_nonePriceBold, false);
        this.mSupportStrike = obtainStyledAttributes.getBoolean(R.styleable.JDPriceTextView_isSupportStrike, false);
        this.mExcludeSpacePadding = obtainStyledAttributes.getBoolean(R.styleable.JDPriceTextView_excludeSpacePadding, false);
        this.mNormalText = obtainStyledAttributes.getBoolean(R.styleable.JDPriceTextView_isNormalText, false);
        this.initSuccess = true;
        obtainStyledAttributes.recycle();
        if (TextUtils.isEmpty(getText())) {
            return;
        }
        setText(getText());
    }
}
