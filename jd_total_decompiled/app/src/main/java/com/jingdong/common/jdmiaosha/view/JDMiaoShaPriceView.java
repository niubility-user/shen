package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.R;
import com.jingdong.common.jdmiaosha.utils.JDMiaoShaShareUtil;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 &2\u00020\u0001:\u0001&B\u001f\b\u0007\u0012\b\u0010!\u001a\u0004\u0018\u00010 \u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"\u00a2\u0006\u0004\b$\u0010%J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004JA\u0010\u000e\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJI\u0010\u0006\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\fH\u0007\u00a2\u0006\u0004\b\u0006\u0010\u000fJ\u001f\u0010\u0010\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0012J\u0015\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0017\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0019\u0010\u0016J\u0017\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0013H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001e\u001a\u00020\u001d8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001f\u00a8\u0006'"}, d2 = {"Lcom/jingdong/common/jdmiaosha/view/JDMiaoShaPriceView;", "Landroid/widget/LinearLayout;", "", "initView", "()V", "", "miaoShaPrice", "", "miaoShaPriceSize", "beforeProportion", "afterProportion", "noPriceProportion", "", "showNoPriceView", "setMiaoShaPrice", "(Ljava/lang/String;FFFFZ)Lcom/jingdong/common/jdmiaosha/view/JDMiaoShaPriceView;", JshopConst.JSKEY_PRODUCT_JDPRICE, "jdPriceSize", "(Ljava/lang/String;F)Lcom/jingdong/common/jdmiaosha/view/JDMiaoShaPriceView;", "", "color", "miaoShaPriceColor", "(I)Lcom/jingdong/common/jdmiaosha/view/JDMiaoShaPriceView;", "jdPriceColor", "leftMargin", "jdPriceLeftMargin", MBaseKeyNames.KEY_ORIENTATION, "setOrientation", "(I)V", "", "mMiaoShaPrice", AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class JDMiaoShaPriceView extends LinearLayout {
    private static final String DOT = ".";
    private static final String NO_PRICE = "\u6682\u65e0\u62a5\u4ef7";
    private static final float NO_PRICE_PROPORTION = 0.9f;
    private static final String RMB = "\u00a5";
    private HashMap _$_findViewCache;
    private double mMiaoShaPrice;

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.util.AttributeSet, kotlin.jvm.internal.DefaultConstructorMarker] */
    @JvmOverloads
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDMiaoShaPriceView(@Nullable Context context) {
        this(context, r0, 2, r0);
        ?? r0 = 0;
    }

    @JvmOverloads
    public JDMiaoShaPriceView(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.miaosha_price_layout, (ViewGroup) this, true);
        initView();
    }

    private final void initView() {
        FontsUtil.changeTextFont((TextView) _$_findCachedViewById(R.id.tvMiaoShaPrice), 4099);
        FontsUtil.changeTextFont((TextView) _$_findCachedViewById(R.id.tvJdPrice), 4099);
    }

    public static /* synthetic */ JDMiaoShaPriceView miaoShaPrice$default(JDMiaoShaPriceView jDMiaoShaPriceView, String str, float f2, float f3, float f4, float f5, boolean z, int i2, Object obj) {
        return jDMiaoShaPriceView.miaoShaPrice(str, f2, (i2 & 4) != 0 ? 1.0f : f3, (i2 & 8) != 0 ? 1.0f : f4, (i2 & 16) != 0 ? NO_PRICE_PROPORTION : f5, (i2 & 32) != 0 ? true : z);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x00a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final JDMiaoShaPriceView setMiaoShaPrice(String miaoShaPrice, float miaoShaPriceSize, float beforeProportion, float afterProportion, float noPriceProportion, boolean showNoPriceView) {
        String str;
        SpannableString spannableString;
        boolean contains$default;
        boolean contains$default2;
        int indexOf$default;
        this.mMiaoShaPrice = 0.0d;
        if (!TextUtils.isEmpty(miaoShaPrice)) {
            if (miaoShaPrice == null) {
                try {
                    Intrinsics.throwNpe();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Double doub = Double.valueOf(miaoShaPrice);
            if (Double.compare(doub.doubleValue(), 0) > 0) {
                Intrinsics.checkExpressionValueIsNotNull(doub, "doub");
                this.mMiaoShaPrice = doub.doubleValue();
                str = "\u00a5" + miaoShaPrice;
                spannableString = new SpannableString(str);
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "\u00a5", false, 2, (Object) null);
                if (!contains$default) {
                    float f2 = 0;
                    if (beforeProportion > f2 && beforeProportion < 1) {
                        spannableString.setSpan(new RelativeSizeSpan(beforeProportion), 0, 1, 17);
                    }
                    contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) ".", false, 2, (Object) null);
                    if (contains$default2 && afterProportion > f2 && afterProportion < 1) {
                        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(afterProportion);
                        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, ".", 0, false, 6, (Object) null);
                        spannableString.setSpan(relativeSizeSpan, indexOf$default, str.length(), 34);
                    }
                    ((TextView) _$_findCachedViewById(R.id.tvMiaoShaPrice)).setTextSize(1, miaoShaPriceSize);
                } else {
                    ((TextView) _$_findCachedViewById(R.id.tvMiaoShaPrice)).setTextSize(1, miaoShaPriceSize * ((noPriceProportion <= ((float) 0) || noPriceProportion > ((float) 1)) ? NO_PRICE_PROPORTION : noPriceProportion));
                }
                if (!Intrinsics.areEqual(spannableString.toString(), "\u6682\u65e0\u62a5\u4ef7") && !showNoPriceView) {
                    TextView tvMiaoShaPrice = (TextView) _$_findCachedViewById(R.id.tvMiaoShaPrice);
                    Intrinsics.checkExpressionValueIsNotNull(tvMiaoShaPrice, "tvMiaoShaPrice");
                    tvMiaoShaPrice.setVisibility(8);
                } else {
                    int i2 = R.id.tvMiaoShaPrice;
                    TextView tvMiaoShaPrice2 = (TextView) _$_findCachedViewById(i2);
                    Intrinsics.checkExpressionValueIsNotNull(tvMiaoShaPrice2, "tvMiaoShaPrice");
                    tvMiaoShaPrice2.setVisibility(0);
                    TextView tvMiaoShaPrice3 = (TextView) _$_findCachedViewById(i2);
                    Intrinsics.checkExpressionValueIsNotNull(tvMiaoShaPrice3, "tvMiaoShaPrice");
                    tvMiaoShaPrice3.setText(spannableString);
                }
                return this;
            }
        }
        str = "\u6682\u65e0\u62a5\u4ef7";
        spannableString = new SpannableString(str);
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "\u00a5", false, 2, (Object) null);
        if (!contains$default) {
        }
        if (!Intrinsics.areEqual(spannableString.toString(), "\u6682\u65e0\u62a5\u4ef7")) {
        }
        int i22 = R.id.tvMiaoShaPrice;
        TextView tvMiaoShaPrice22 = (TextView) _$_findCachedViewById(i22);
        Intrinsics.checkExpressionValueIsNotNull(tvMiaoShaPrice22, "tvMiaoShaPrice");
        tvMiaoShaPrice22.setVisibility(0);
        TextView tvMiaoShaPrice32 = (TextView) _$_findCachedViewById(i22);
        Intrinsics.checkExpressionValueIsNotNull(tvMiaoShaPrice32, "tvMiaoShaPrice");
        tvMiaoShaPrice32.setText(spannableString);
        return this;
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
    public final JDMiaoShaPriceView jdPrice(@Nullable String str, float f2) {
        int i2 = R.id.tvJdPrice;
        TextView tvJdPrice = (TextView) _$_findCachedViewById(i2);
        Intrinsics.checkExpressionValueIsNotNull(tvJdPrice, "tvJdPrice");
        tvJdPrice.setVisibility(8);
        double d = 0;
        if (this.mMiaoShaPrice > d && !TextUtils.isEmpty(str)) {
            if (str == null) {
                try {
                    Intrinsics.throwNpe();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            Double doub = Double.valueOf(str);
            if (Double.compare(doub.doubleValue(), d) > 0) {
                double d2 = this.mMiaoShaPrice;
                Intrinsics.checkExpressionValueIsNotNull(doub, "doub");
                if (d2 < doub.doubleValue()) {
                    TextView tvJdPrice2 = (TextView) _$_findCachedViewById(i2);
                    Intrinsics.checkExpressionValueIsNotNull(tvJdPrice2, "tvJdPrice");
                    tvJdPrice2.setVisibility(0);
                }
                TextView tvJdPrice3 = (TextView) _$_findCachedViewById(i2);
                Intrinsics.checkExpressionValueIsNotNull(tvJdPrice3, "tvJdPrice");
                TextPaint paint = tvJdPrice3.getPaint();
                Intrinsics.checkExpressionValueIsNotNull(paint, "tvJdPrice.paint");
                paint.setFlags(16);
                TextView tvJdPrice4 = (TextView) _$_findCachedViewById(i2);
                Intrinsics.checkExpressionValueIsNotNull(tvJdPrice4, "tvJdPrice");
                TextPaint paint2 = tvJdPrice4.getPaint();
                Intrinsics.checkExpressionValueIsNotNull(paint2, "tvJdPrice.paint");
                paint2.setAntiAlias(true);
                TextView tvJdPrice5 = (TextView) _$_findCachedViewById(i2);
                Intrinsics.checkExpressionValueIsNotNull(tvJdPrice5, "tvJdPrice");
                tvJdPrice5.setText('\u00a5' + str);
                TextView tvJdPrice6 = (TextView) _$_findCachedViewById(i2);
                Intrinsics.checkExpressionValueIsNotNull(tvJdPrice6, "tvJdPrice");
                tvJdPrice6.setTextSize(f2);
            }
        }
        return this;
    }

    @NotNull
    public final JDMiaoShaPriceView jdPriceColor(int color) {
        ((TextView) _$_findCachedViewById(R.id.tvJdPrice)).setTextColor(color);
        return this;
    }

    @NotNull
    public final JDMiaoShaPriceView jdPriceLeftMargin(int leftMargin) {
        int i2 = R.id.tvJdPrice;
        TextView tvJdPrice = (TextView) _$_findCachedViewById(i2);
        Intrinsics.checkExpressionValueIsNotNull(tvJdPrice, "tvJdPrice");
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) JDMiaoShaShareUtil.convert(tvJdPrice.getLayoutParams());
        layoutParams.leftMargin = DPIUtil.dip2px(leftMargin);
        TextView tvJdPrice2 = (TextView) _$_findCachedViewById(i2);
        Intrinsics.checkExpressionValueIsNotNull(tvJdPrice2, "tvJdPrice");
        tvJdPrice2.setLayoutParams(layoutParams);
        return this;
    }

    @JvmOverloads
    @NotNull
    public final JDMiaoShaPriceView miaoShaPrice(@Nullable String str, float f2) {
        return miaoShaPrice$default(this, str, f2, 0.0f, 0.0f, 0.0f, false, 60, null);
    }

    @JvmOverloads
    @NotNull
    public final JDMiaoShaPriceView miaoShaPrice(@Nullable String str, float f2, float f3) {
        return miaoShaPrice$default(this, str, f2, f3, 0.0f, 0.0f, false, 56, null);
    }

    @JvmOverloads
    @NotNull
    public final JDMiaoShaPriceView miaoShaPrice(@Nullable String str, float f2, float f3, float f4) {
        return miaoShaPrice$default(this, str, f2, f3, f4, 0.0f, false, 48, null);
    }

    @JvmOverloads
    @NotNull
    public final JDMiaoShaPriceView miaoShaPrice(@Nullable String str, float f2, float f3, float f4, float f5) {
        return miaoShaPrice$default(this, str, f2, f3, f4, f5, false, 32, null);
    }

    @JvmOverloads
    @NotNull
    public final JDMiaoShaPriceView miaoShaPrice(@Nullable String miaoShaPrice, float miaoShaPriceSize, float beforeProportion, float afterProportion, float noPriceProportion, boolean showNoPriceView) {
        return miaoShaPriceSize <= ((float) 0) ? this : setMiaoShaPrice(miaoShaPrice, miaoShaPriceSize, beforeProportion, afterProportion, noPriceProportion, showNoPriceView);
    }

    @NotNull
    public final JDMiaoShaPriceView miaoShaPriceColor(int color) {
        ((TextView) _$_findCachedViewById(R.id.tvMiaoShaPrice)).setTextColor(color);
        return this;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int r3) {
        LinearLayout root = (LinearLayout) _$_findCachedViewById(R.id.root);
        Intrinsics.checkExpressionValueIsNotNull(root, "root");
        root.setOrientation(r3);
        super.setOrientation(r3);
    }

    public /* synthetic */ JDMiaoShaPriceView(Context context, AttributeSet attributeSet, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet);
    }
}
