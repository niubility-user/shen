package com.jingdong.jdsdk.utils;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.jingdong.jdsdk.utils.RemoteImageUtil;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"com/jingdong/jdsdk/utils/RemoteImageUtil$Companion$setTextViewImage$callBack$1", "Lcom/jingdong/jdsdk/utils/RemoteImageUtil$ImageRequestCallBack;", "Landroid/graphics/drawable/Drawable;", "drawable", "", "onCallDrawable", "(Landroid/graphics/drawable/Drawable;)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class RemoteImageUtil$Companion$setTextViewImage$callBack$1 implements RemoteImageUtil.ImageRequestCallBack {
    final /* synthetic */ int $color;
    final /* synthetic */ int $height;
    final /* synthetic */ int $padding;
    final /* synthetic */ TextView $textView;
    final /* synthetic */ RemoteImageUtil.TextViewType $type;
    final /* synthetic */ int $width;

    public RemoteImageUtil$Companion$setTextViewImage$callBack$1(int i2, int i3, int i4, TextView textView, int i5, RemoteImageUtil.TextViewType textViewType) {
        this.$width = i2;
        this.$height = i3;
        this.$color = i4;
        this.$textView = textView;
        this.$padding = i5;
        this.$type = textViewType;
    }

    @Override // com.jingdong.jdsdk.utils.RemoteImageUtil.ImageRequestCallBack
    public void onCallDrawable(@Nullable Drawable drawable) {
        if (drawable != null) {
            drawable.setBounds(0, 0, this.$width, this.$height);
        }
        if (this.$color != -1) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(this.$color, PorterDuff.Mode.SRC_ATOP));
            }
        } else if (drawable != null) {
            drawable.clearColorFilter();
        }
        this.$textView.setCompoundDrawablePadding(this.$padding);
        int i2 = RemoteImageUtil.Companion.WhenMappings.$EnumSwitchMapping$0[this.$type.ordinal()];
        if (i2 == 1) {
            this.$textView.setCompoundDrawables(drawable, null, null, null);
        } else if (i2 == 2) {
            this.$textView.setCompoundDrawables(null, drawable, null, null);
        } else if (i2 == 3) {
            this.$textView.setCompoundDrawables(null, null, drawable, null);
        } else if (i2 == 4) {
            this.$textView.setCompoundDrawables(null, null, null, drawable);
        }
        this.$textView.requestLayout();
    }
}
