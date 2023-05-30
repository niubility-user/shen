package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.R;
import com.jingdong.common.jdmiaosha.utils.JDPriceUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 O2\u00020\u0001:\u0001OB\u001d\b\u0007\u0012\u0006\u0010J\u001a\u00020I\u0012\n\b\u0002\u0010L\u001a\u0004\u0018\u00010K\u00a2\u0006\u0004\bM\u0010NJ\u001d\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00052\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\n\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\n\u0010\tJ\u0015\u0010\r\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u000f\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0011\u0010\u000eJ\u0017\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u0013\u0010\u000eR$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\"\u0010\u001c\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\"\u0010\"\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\"\u0010\u001d\u001a\u0004\b#\u0010\u001f\"\u0004\b$\u0010!R$\u0010%\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010\tR\"\u0010+\u001a\u00020*8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0016\u00101\u001a\u00020*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b1\u0010,R\"\u00102\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b2\u0010\u001d\u001a\u0004\b3\u0010\u001f\"\u0004\b4\u0010!R\"\u00105\u001a\u00020\u001b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u0010\u001d\u001a\u0004\b6\u0010\u001f\"\u0004\b7\u0010!R$\u00108\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010>\u001a\u00020*8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b>\u0010,\u001a\u0004\b?\u0010.\"\u0004\b@\u00100R$\u0010A\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bA\u0010\u0016\u001a\u0004\bB\u0010\u0018\"\u0004\bC\u0010\u001aR\"\u0010D\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010\u000e\u00a8\u0006P"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDPriceView;", "Landroid/widget/LinearLayout;", "", "miaoShaPrice", JshopConst.JSKEY_PRODUCT_JDPRICE, "", "handlePrice", "(Ljava/lang/String;Ljava/lang/String;)V", "setMiaoShaPrice", "(Ljava/lang/String;)V", "setJDPrice", "", "color", "setMiaoShaPriceColor", "(I)V", "setJDPriceColor", "margin", "setJDPriceMargin", MBaseKeyNames.KEY_ORIENTATION, "setOrientation", "Landroid/content/res/ColorStateList;", "miaoShaPriceTextColor", "Landroid/content/res/ColorStateList;", "getMiaoShaPriceTextColor", "()Landroid/content/res/ColorStateList;", "setMiaoShaPriceTextColor", "(Landroid/content/res/ColorStateList;)V", "", "mFractionRatio", "F", "getMFractionRatio", "()F", "setMFractionRatio", "(F)V", "jdPriceTextSize", "getJdPriceTextSize", "setJdPriceTextSize", "miaoShaNonePriceText", "Ljava/lang/String;", "getMiaoShaNonePriceText", "()Ljava/lang/String;", "setMiaoShaNonePriceText", "", "excludeSpaceEnabled", "Z", "getExcludeSpaceEnabled", "()Z", "setExcludeSpaceEnabled", "(Z)V", "jdNonePriceVisible", "mSymbolRatio", "getMSymbolRatio", "setMSymbolRatio", "miaoShaPriceTextSize", "getMiaoShaPriceTextSize", "setMiaoShaPriceTextSize", "miaoShaNonePriceTextSize", "Ljava/lang/Float;", "getMiaoShaNonePriceTextSize", "()Ljava/lang/Float;", "setMiaoShaNonePriceTextSize", "(Ljava/lang/Float;)V", "miaoShaShowNonePrice", "getMiaoShaShowNonePrice", "setMiaoShaShowNonePrice", "jdPriceTextColor", "getJdPriceTextColor", "setJdPriceTextColor", "jdPriceMargin", "I", "getJdPriceMargin", "()I", "setJdPriceMargin", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class JDPriceView extends LinearLayout {
    private static final float DEFAULT_RATIO = 1.0f;
    private static final float DEFAULT_SIZE = 12.0f;
    private HashMap _$_findViewCache;
    private boolean excludeSpaceEnabled;
    private boolean jdNonePriceVisible;
    private int jdPriceMargin;
    @Nullable
    private ColorStateList jdPriceTextColor;
    private float jdPriceTextSize;
    private float mFractionRatio;
    private float mSymbolRatio;
    @Nullable
    private String miaoShaNonePriceText;
    @Nullable
    private Float miaoShaNonePriceTextSize;
    @Nullable
    private ColorStateList miaoShaPriceTextColor;
    private float miaoShaPriceTextSize;
    private boolean miaoShaShowNonePrice;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.util.AttributeSet, kotlin.jvm.internal.DefaultConstructorMarker] */
    @JvmOverloads
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDPriceView(@NotNull Context context) {
        this(context, r0, 2, r0);
        ?? r0 = 0;
    }

    @JvmOverloads
    public JDPriceView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSymbolRatio = 1.0f;
        this.mFractionRatio = 1.0f;
        this.miaoShaPriceTextSize = DEFAULT_SIZE;
        this.jdPriceTextSize = DEFAULT_SIZE;
        LayoutInflater.from(context).inflate(R.layout.jd_price_layout, (ViewGroup) this, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JDPriceLayout);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr\u2026.styleable.JDPriceLayout)");
        this.mSymbolRatio = obtainStyledAttributes.getFloat(R.styleable.JDPriceLayout_miaosha_symbolRatio, 1.0f);
        this.mFractionRatio = obtainStyledAttributes.getFloat(R.styleable.JDPriceLayout_miaosha_fractionRatio, 1.0f);
        this.miaoShaPriceTextSize = obtainStyledAttributes.getDimension(R.styleable.JDPriceLayout_miaoShaPriceTextSize, DEFAULT_SIZE);
        this.miaoShaPriceTextColor = obtainStyledAttributes.getColorStateList(R.styleable.JDPriceLayout_miaoShaPriceTextColor);
        this.jdPriceTextSize = obtainStyledAttributes.getDimension(R.styleable.JDPriceLayout_jdPriceTextSize, DEFAULT_SIZE);
        this.jdPriceTextColor = obtainStyledAttributes.getColorStateList(R.styleable.JDPriceLayout_jdPriceTextColor);
        this.jdPriceMargin = obtainStyledAttributes.getDimensionPixelSize(R.styleable.JDPriceLayout_jdPrice_margin, 0);
        this.miaoShaShowNonePrice = obtainStyledAttributes.getBoolean(R.styleable.JDPriceLayout_miaosha_showNonePriceEnabled, false);
        this.miaoShaNonePriceText = obtainStyledAttributes.getString(R.styleable.JDPriceLayout_miaosha_nonePriceText);
        this.miaoShaNonePriceTextSize = Float.valueOf(obtainStyledAttributes.getDimension(R.styleable.JDPriceLayout_miaosha_nonePriceTextSize, DEFAULT_SIZE));
        this.jdNonePriceVisible = obtainStyledAttributes.getBoolean(R.styleable.JDPriceLayout_jd_nonePriceVisible, false);
        this.excludeSpaceEnabled = obtainStyledAttributes.getBoolean(R.styleable.JDPriceLayout_excludeSpaceEnabled, false);
        obtainStyledAttributes.recycle();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.root);
        if (linearLayout != null) {
            linearLayout.setOrientation(getOrientation());
        }
        JDPriceTextView jDPriceTextView = (JDPriceTextView) _$_findCachedViewById(R.id.tvMiaoShaPrice);
        jDPriceTextView.setSymbolTextRatio(Float.valueOf(this.mSymbolRatio));
        jDPriceTextView.setFractionTextRatio(Float.valueOf(this.mFractionRatio));
        jDPriceTextView.setTextSize(0, this.miaoShaPriceTextSize);
        jDPriceTextView.setShowNonePrice(Boolean.valueOf(this.miaoShaShowNonePrice));
        jDPriceTextView.setNonePriceTextSize(this.miaoShaNonePriceTextSize);
        jDPriceTextView.setExcludeSpacePadding(Boolean.valueOf(this.excludeSpaceEnabled));
        ColorStateList colorStateList = this.miaoShaPriceTextColor;
        if (colorStateList != null) {
            jDPriceTextView.setTextColor(colorStateList);
        }
        String str = this.miaoShaNonePriceText;
        if (str != null) {
            jDPriceTextView.setNonePriceText(str);
        }
        JDPriceTextView jDPriceTextView2 = (JDPriceTextView) _$_findCachedViewById(R.id.tvJdPrice);
        jDPriceTextView2.setTextSize(0, this.jdPriceTextSize);
        jDPriceTextView2.setExcludeSpacePadding(Boolean.valueOf(this.excludeSpaceEnabled));
        ColorStateList colorStateList2 = this.jdPriceTextColor;
        if (colorStateList2 != null) {
            jDPriceTextView2.setTextColor(colorStateList2);
        }
        jDPriceTextView2.setNonePriceVisible(Boolean.valueOf(this.jdNonePriceVisible));
        setJDPriceMargin(this.jdPriceMargin);
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

    public final boolean getExcludeSpaceEnabled() {
        return this.excludeSpaceEnabled;
    }

    public final int getJdPriceMargin() {
        return this.jdPriceMargin;
    }

    @Nullable
    public final ColorStateList getJdPriceTextColor() {
        return this.jdPriceTextColor;
    }

    public final float getJdPriceTextSize() {
        return this.jdPriceTextSize;
    }

    public final float getMFractionRatio() {
        return this.mFractionRatio;
    }

    public final float getMSymbolRatio() {
        return this.mSymbolRatio;
    }

    @Nullable
    public final String getMiaoShaNonePriceText() {
        return this.miaoShaNonePriceText;
    }

    @Nullable
    public final Float getMiaoShaNonePriceTextSize() {
        return this.miaoShaNonePriceTextSize;
    }

    @Nullable
    public final ColorStateList getMiaoShaPriceTextColor() {
        return this.miaoShaPriceTextColor;
    }

    public final float getMiaoShaPriceTextSize() {
        return this.miaoShaPriceTextSize;
    }

    public final boolean getMiaoShaShowNonePrice() {
        return this.miaoShaShowNonePrice;
    }

    public final void handlePrice(@NotNull String miaoShaPrice, @NotNull String jdPrice) {
        JDPriceUtils jDPriceUtils = JDPriceUtils.INSTANCE;
        JDPriceTextView tvMiaoShaPrice = (JDPriceTextView) _$_findCachedViewById(R.id.tvMiaoShaPrice);
        Intrinsics.checkExpressionValueIsNotNull(tvMiaoShaPrice, "tvMiaoShaPrice");
        JDPriceTextView tvJdPrice = (JDPriceTextView) _$_findCachedViewById(R.id.tvJdPrice);
        Intrinsics.checkExpressionValueIsNotNull(tvJdPrice, "tvJdPrice");
        jDPriceUtils.handlePricesCompare(tvMiaoShaPrice, tvJdPrice, miaoShaPrice, jdPrice);
    }

    public final void setExcludeSpaceEnabled(boolean z) {
        this.excludeSpaceEnabled = z;
    }

    public final void setJDPrice(@Nullable String jdPrice) {
        JDPriceTextView tvJdPrice = (JDPriceTextView) _$_findCachedViewById(R.id.tvJdPrice);
        Intrinsics.checkExpressionValueIsNotNull(tvJdPrice, "tvJdPrice");
        tvJdPrice.setText(jdPrice);
    }

    public final void setJDPriceColor(int color) {
        this.jdPriceTextColor = ColorStateList.valueOf(color);
        ((JDPriceTextView) _$_findCachedViewById(R.id.tvJdPrice)).setTextColor(this.jdPriceTextColor);
    }

    public final void setJDPriceMargin(int margin) {
        int i2 = R.id.tvJdPrice;
        JDPriceTextView tvJdPrice = (JDPriceTextView) _$_findCachedViewById(i2);
        Intrinsics.checkExpressionValueIsNotNull(tvJdPrice, "tvJdPrice");
        ViewGroup.LayoutParams layoutParams = tvJdPrice.getLayoutParams();
        if (layoutParams != null) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
            if (getOrientation() == 0) {
                layoutParams2.leftMargin = margin;
            } else if (getOrientation() == 1) {
                layoutParams2.topMargin = margin;
            }
            JDPriceTextView tvJdPrice2 = (JDPriceTextView) _$_findCachedViewById(i2);
            Intrinsics.checkExpressionValueIsNotNull(tvJdPrice2, "tvJdPrice");
            tvJdPrice2.setLayoutParams(layoutParams2);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
    }

    public final void setJdPriceMargin(int i2) {
        this.jdPriceMargin = i2;
    }

    public final void setJdPriceTextColor(@Nullable ColorStateList colorStateList) {
        this.jdPriceTextColor = colorStateList;
    }

    public final void setJdPriceTextSize(float f2) {
        this.jdPriceTextSize = f2;
    }

    public final void setMFractionRatio(float f2) {
        this.mFractionRatio = f2;
    }

    public final void setMSymbolRatio(float f2) {
        this.mSymbolRatio = f2;
    }

    public final void setMiaoShaNonePriceText(@Nullable String str) {
        this.miaoShaNonePriceText = str;
    }

    public final void setMiaoShaNonePriceTextSize(@Nullable Float f2) {
        this.miaoShaNonePriceTextSize = f2;
    }

    public final void setMiaoShaPrice(@Nullable String miaoShaPrice) {
        JDPriceTextView tvMiaoShaPrice = (JDPriceTextView) _$_findCachedViewById(R.id.tvMiaoShaPrice);
        Intrinsics.checkExpressionValueIsNotNull(tvMiaoShaPrice, "tvMiaoShaPrice");
        tvMiaoShaPrice.setText(miaoShaPrice);
    }

    public final void setMiaoShaPriceColor(int color) {
        this.miaoShaPriceTextColor = ColorStateList.valueOf(color);
        ((JDPriceTextView) _$_findCachedViewById(R.id.tvMiaoShaPrice)).setTextColor(this.miaoShaPriceTextColor);
    }

    public final void setMiaoShaPriceTextColor(@Nullable ColorStateList colorStateList) {
        this.miaoShaPriceTextColor = colorStateList;
    }

    public final void setMiaoShaPriceTextSize(float f2) {
        this.miaoShaPriceTextSize = f2;
    }

    public final void setMiaoShaShowNonePrice(boolean z) {
        this.miaoShaShowNonePrice = z;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int orientation) {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.root);
        if (linearLayout != null) {
            linearLayout.setOrientation(orientation);
        }
        super.setOrientation(orientation);
    }

    public /* synthetic */ JDPriceView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }
}
