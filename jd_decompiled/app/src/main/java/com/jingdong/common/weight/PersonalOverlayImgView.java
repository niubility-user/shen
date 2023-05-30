package com.jingdong.common.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.extension.LibDimenExtensionsKt;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.personal.dark.PersonalDarkHelper;
import com.jingdong.sdk.lib.personal.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 32\u00020\u0001:\u00013B'\b\u0007\u0012\u0006\u0010-\u001a\u00020,\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010.\u0012\b\b\u0002\u00100\u001a\u00020\u0007\u00a2\u0006\u0004\b1\u00102J\u0017\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\n\u0010\u000bJ7\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u0014\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0017\u001a\u00020\u00042\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014\u00a2\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0004\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001e\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b \u0010\u001fR\u0016\u0010\"\u001a\u00020!8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b$\u0010\u001fR\u0016\u0010%\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b%\u0010\u001fR\u0016\u0010&\u001a\u00020\f8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010(R\u0016\u0010)\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b)\u0010\u001fR\u0016\u0010*\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b*\u0010\u001fR\u0016\u0010+\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010\u001f\u00a8\u00064"}, d2 = {"Lcom/jingdong/common/weight/PersonalOverlayImgView;", "Landroid/widget/FrameLayout;", "Lcom/jingdong/app/util/image/JDDisplayImageOptions;", "options", "", "setChildImageOptions", "(Lcom/jingdong/app/util/image/JDDisplayImageOptions;)V", "", "widthMeasureSpec", "heightMeasureSpec", "onMeasure", "(II)V", "", "changed", "left", "top", "right", "bottom", ViewProps.ON_LAYOUT, "(ZIIII)V", "", "", "headImage", "notifyDataChanged", "(Ljava/util/List;)V", "handleDarkMode", "()V", "Landroid/graphics/drawable/Drawable;", "childBackgroundResource", "Landroid/graphics/drawable/Drawable;", "childHeight", "I", Constant.KEY_INNER_BOTTOM, "Landroid/widget/ImageView$ScaleType;", "scaleType", "Landroid/widget/ImageView$ScaleType;", "childWidth", Constant.KEY_INNER_RIGHT, "supportDark", "Z", "Lcom/jingdong/app/util/image/JDDisplayImageOptions;", "innerPaddingTop", "childOffset", Constant.KEY_INNER_LEFT, "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "defStyle", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "Companion", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class PersonalOverlayImgView extends FrameLayout {
    @NotNull
    public static final String TAG = "PersonalOverlayImgView";
    private HashMap _$_findViewCache;
    private Drawable childBackgroundResource;
    private int childHeight;
    private int childOffset;
    private int childWidth;
    private int innerPaddingBottom;
    private int innerPaddingLeft;
    private int innerPaddingRight;
    private int innerPaddingTop;
    private JDDisplayImageOptions options;
    private ImageView.ScaleType scaleType;
    private boolean supportDark;

    @JvmOverloads
    public PersonalOverlayImgView(@NotNull Context context) {
        this(context, null, 0, 6, null);
    }

    @JvmOverloads
    public PersonalOverlayImgView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

    public /* synthetic */ PersonalOverlayImgView(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
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

    public final void handleDarkMode() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && (childAt instanceof SimpleDraweeView)) {
                PersonalDarkHelper.INSTANCE.setImageViewDark((ImageView) childAt);
            }
        }
    }

    public final void notifyDataChanged(@Nullable List<String> headImage) {
        removeAllViews();
        if (headImage != null) {
            for (String str : headImage) {
                SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
                simpleDraweeView.setBackgroundDrawable(this.childBackgroundResource);
                simpleDraweeView.setScaleType(simpleDraweeView.getScaleType());
                simpleDraweeView.setPadding(this.innerPaddingLeft, this.innerPaddingTop, this.innerPaddingRight, this.innerPaddingBottom);
                if (this.supportDark) {
                    PersonalDarkHelper.INSTANCE.setImageViewDark(simpleDraweeView);
                }
                addView(simpleDraweeView);
                JDImageUtils.displayImage(str, (ImageView) simpleDraweeView, this.options, true);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        try {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt != null) {
                    int paddingLeft = getPaddingLeft() + ((childAt.getMeasuredWidth() - this.childOffset) * i2);
                    int paddingTop = getPaddingTop();
                    childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "PersonalOverlayImgView onLayout\n: " + e2);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            if (getChildCount() == 0) {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                return;
            }
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((this.childWidth * getChildCount()) - (this.childOffset * (getChildCount() - 1)), 1073741824);
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.childWidth, 1073741824);
            int makeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(this.childHeight, 1073741824);
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt != null) {
                    childAt.measure(makeMeasureSpec2, makeMeasureSpec3);
                }
            }
            setMeasuredDimension(makeMeasureSpec, makeMeasureSpec3);
        } catch (Exception e2) {
            if (OKLog.D) {
                OKLog.d(TAG, "PersonalOverlayImgView  onMeasure\n: " + e2);
            }
        }
    }

    public final void setChildImageOptions(@Nullable JDDisplayImageOptions options) {
        this.options = options;
    }

    @JvmOverloads
    public PersonalOverlayImgView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.childWidth = LibDimenExtensionsKt.getDp(25);
        this.childHeight = LibDimenExtensionsKt.getDp(25);
        this.innerPaddingLeft = LibDimenExtensionsKt.getDp(2);
        this.innerPaddingTop = LibDimenExtensionsKt.getDp(2);
        this.innerPaddingRight = LibDimenExtensionsKt.getDp(2);
        this.innerPaddingBottom = LibDimenExtensionsKt.getDp(2);
        this.childBackgroundResource = ContextCompat.getDrawable(context, R.drawable.bg_circle_write);
        this.childOffset = LibDimenExtensionsKt.getDp(10);
        this.supportDark = true;
        this.scaleType = ImageView.ScaleType.CENTER_CROP;
        this.options = new JDDisplayImageOptions().setPlaceholder(18).displayer(new JDRoundedBitmapDisplayer(DPIUtil.dip2px(1000.0f)));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PersonalOverlayImgView);
        this.childWidth = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.PersonalOverlayImgView_OverlayChildWidth, this.childWidth);
        this.childHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.PersonalOverlayImgView_OverlayChildHeight, this.childHeight);
        this.innerPaddingLeft = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.PersonalOverlayImgView_OverlayInnerPaddingLeft, this.innerPaddingLeft);
        this.innerPaddingRight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.PersonalOverlayImgView_OverlayInnerPaddingRight, this.innerPaddingRight);
        this.innerPaddingTop = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.PersonalOverlayImgView_OverlayInnerPaddingTop, this.innerPaddingTop);
        this.innerPaddingBottom = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.PersonalOverlayImgView_OverlayInnerPaddingBottom, this.innerPaddingBottom);
        int i3 = R.styleable.PersonalOverlayImgView_OverlayChildBackground;
        if (obtainStyledAttributes.hasValue(i3)) {
            this.childBackgroundResource = obtainStyledAttributes.getDrawable(i3);
        }
        this.childOffset = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.PersonalOverlayImgView_OverlayChildOffset, this.childOffset);
        this.supportDark = obtainStyledAttributes.getBoolean(R.styleable.PersonalOverlayImgView_OverlayDark, this.supportDark);
        int i4 = R.styleable.PersonalOverlayImgView_android_scaleType;
        if (obtainStyledAttributes.hasValue(i4)) {
            int i5 = obtainStyledAttributes.getInt(i4, -1);
            ImageView.ScaleType[] values = ImageView.ScaleType.values();
            if (i5 >= 0 && values.length > i5) {
                this.scaleType = values[i5];
            }
        }
        obtainStyledAttributes.recycle();
    }
}
