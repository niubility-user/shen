package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.jd.lib.cashier.sdk.R;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.XView2.common.XView2Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\r\u0010\u000eB!\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\u0004\b\r\u0010\u0010J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\b\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/DecimalEditText;", "Landroidx/appcompat/widget/AppCompatEditText;", "", XView2Constants.XVIEW2_ACTION_INIT, "()V", "", "g", "I", "mDecimalNumber", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class DecimalEditText extends AppCompatEditText {

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private int mDecimalNumber;

    /* loaded from: classes14.dex */
    public static final class a implements InputFilter {
        a() {
        }

        @Override // android.text.InputFilter
        @Nullable
        public CharSequence filter(@Nullable CharSequence charSequence, int i2, int i3, @Nullable Spanned spanned, int i4, int i5) {
            boolean contains$default;
            int indexOf$default;
            String valueOf = String.valueOf(spanned);
            if (Intrinsics.areEqual(charSequence, OrderISVUtil.MONEY_DECIMAL)) {
                if (valueOf.length() == 0) {
                    return "0.";
                }
            }
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) valueOf, (CharSequence) OrderISVUtil.MONEY_DECIMAL, false, 2, (Object) null);
            if (contains$default) {
                indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) valueOf, OrderISVUtil.MONEY_DECIMAL, 0, false, 6, (Object) null);
                if (i5 - indexOf$default >= DecimalEditText.this.mDecimalNumber + 1) {
                    return "";
                }
            }
            return null;
        }
    }

    public DecimalEditText(@NotNull Context context, @NotNull AttributeSet attributeSet) {
        this(context, attributeSet, 16842862);
    }

    private final void init() {
        setFilters(new a[]{new a()});
    }

    public DecimalEditText(@NotNull Context context, @NotNull AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDecimalNumber = 2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DecimalEditTextSdk);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr\u2026eable.DecimalEditTextSdk)");
        this.mDecimalNumber = obtainStyledAttributes.getInt(R.styleable.DecimalEditTextSdk_decimalNumber, 2);
        obtainStyledAttributes.recycle();
        init();
    }
}
