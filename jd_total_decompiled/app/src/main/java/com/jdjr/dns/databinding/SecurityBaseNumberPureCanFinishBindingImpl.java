package com.jdjr.dns.databinding;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.Converters;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.views.NumberKeyView;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes18.dex */
public class SecurityBaseNumberPureCanFinishBindingImpl extends SecurityBaseNumberPureCanFinishBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final NumberKeyView mboundView1;
    @NonNull
    private final NumberKeyView mboundView10;
    @NonNull
    private final NumberKeyView mboundView2;
    @NonNull
    private final NumberKeyView mboundView3;
    @NonNull
    private final NumberKeyView mboundView4;
    @NonNull
    private final NumberKeyView mboundView5;
    @NonNull
    private final NumberKeyView mboundView6;
    @NonNull
    private final NumberKeyView mboundView7;
    @NonNull
    private final NumberKeyView mboundView8;
    @NonNull
    private final NumberKeyView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.keyboard_buttons, 13);
        sparseIntArray.put(R.id.symbol_key, 14);
        sparseIntArray.put(R.id.okLayout, 15);
        sparseIntArray.put(R.id.btnOk, 16);
    }

    public SecurityBaseNumberPureCanFinishBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        Context context;
        int i2;
        Context context2;
        int i3;
        Context context3;
        int i4;
        Context context4;
        int i5;
        LinearLayout linearLayout;
        int i6;
        Context context5;
        int i7;
        Context context6;
        int i8;
        Context context7;
        int i9;
        Context context8;
        int i10;
        Context context9;
        int i11;
        Context context10;
        int i12;
        Context context11;
        int i13;
        Context context12;
        int i14;
        Context context13;
        int i15;
        Context context14;
        int i16;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j5 = j2 & 1;
        if (j5 != 0 && j5 != 0) {
            if (KeyboardUiMode.isDark()) {
                j3 = j2 | 4 | 16 | 64 | 256 | 1024 | 4096 | IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT | IjkMediaMeta.AV_CH_TOP_BACK_CENTER | 262144 | 1048576 | 4194304 | 16777216 | 67108864 | 268435456;
                j4 = IjkMediaMeta.AV_CH_STEREO_RIGHT;
            } else {
                j3 = j2 | 2 | 8 | 32 | 128 | 512 | 2048 | IjkMediaMeta.AV_CH_TOP_FRONT_CENTER | IjkMediaMeta.AV_CH_TOP_BACK_LEFT | IjkMediaMeta.AV_CH_TOP_BACK_RIGHT | 524288 | 2097152 | 8388608 | 33554432 | 134217728;
                j4 = IjkMediaMeta.AV_CH_STEREO_LEFT;
            }
            j2 = j3 | j4;
        }
        if ((j2 & 1) != 0) {
            ImageButton imageButton = this.deleteKey;
            if (KeyboardUiMode.isDark()) {
                context = this.deleteKey.getContext();
                i2 = R.drawable.security_general_delete_key_bg_selector_dark;
            } else {
                context = this.deleteKey.getContext();
                i2 = R.drawable.security_general_delete_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(imageButton, AppCompatResources.getDrawable(context, i2));
            ImageButton imageButton2 = this.deleteKey;
            if (KeyboardUiMode.isDark()) {
                context2 = this.deleteKey.getContext();
                i3 = R.drawable.security_general_delete_btn_dark;
            } else {
                context2 = this.deleteKey.getContext();
                i3 = R.drawable.security_general_delete_btn;
            }
            ImageViewBindingAdapter.setImageDrawable(imageButton2, AppCompatResources.getDrawable(context2, i3));
            ImageButton imageButton3 = this.hideKey;
            if (KeyboardUiMode.isDark()) {
                context3 = this.hideKey.getContext();
                i4 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context3 = this.hideKey.getContext();
                i4 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(imageButton3, AppCompatResources.getDrawable(context3, i4));
            ImageButton imageButton4 = this.hideKey;
            if (KeyboardUiMode.isDark()) {
                context4 = this.hideKey.getContext();
                i5 = R.drawable.security_key_hide_dark;
            } else {
                context4 = this.hideKey.getContext();
                i5 = R.drawable.security_key_hide;
            }
            ImageViewBindingAdapter.setImageDrawable(imageButton4, AppCompatResources.getDrawable(context4, i5));
            LinearLayout linearLayout2 = this.mboundView0;
            if (KeyboardUiMode.isDark()) {
                linearLayout = this.mboundView0;
                i6 = R.color.keyboard_color_keyboard_bg_dark;
            } else {
                linearLayout = this.mboundView0;
                i6 = R.color.keyboard_color_keyboard_bg;
            }
            ViewBindingAdapter.setBackground(linearLayout2, Converters.convertColorToDrawable(ViewDataBinding.getColorFromResource(linearLayout, i6)));
            NumberKeyView numberKeyView = this.mboundView1;
            if (KeyboardUiMode.isDark()) {
                context5 = this.mboundView1.getContext();
                i7 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context5 = this.mboundView1.getContext();
                i7 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView, AppCompatResources.getDrawable(context5, i7));
            NumberKeyView numberKeyView2 = this.mboundView10;
            if (KeyboardUiMode.isDark()) {
                context6 = this.mboundView10.getContext();
                i8 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context6 = this.mboundView10.getContext();
                i8 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView2, AppCompatResources.getDrawable(context6, i8));
            NumberKeyView numberKeyView3 = this.mboundView2;
            if (KeyboardUiMode.isDark()) {
                context7 = this.mboundView2.getContext();
                i9 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context7 = this.mboundView2.getContext();
                i9 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView3, AppCompatResources.getDrawable(context7, i9));
            NumberKeyView numberKeyView4 = this.mboundView3;
            if (KeyboardUiMode.isDark()) {
                context8 = this.mboundView3.getContext();
                i10 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context8 = this.mboundView3.getContext();
                i10 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView4, AppCompatResources.getDrawable(context8, i10));
            NumberKeyView numberKeyView5 = this.mboundView4;
            if (KeyboardUiMode.isDark()) {
                context9 = this.mboundView4.getContext();
                i11 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context9 = this.mboundView4.getContext();
                i11 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView5, AppCompatResources.getDrawable(context9, i11));
            NumberKeyView numberKeyView6 = this.mboundView5;
            if (KeyboardUiMode.isDark()) {
                context10 = this.mboundView5.getContext();
                i12 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context10 = this.mboundView5.getContext();
                i12 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView6, AppCompatResources.getDrawable(context10, i12));
            NumberKeyView numberKeyView7 = this.mboundView6;
            if (KeyboardUiMode.isDark()) {
                context11 = this.mboundView6.getContext();
                i13 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context11 = this.mboundView6.getContext();
                i13 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView7, AppCompatResources.getDrawable(context11, i13));
            NumberKeyView numberKeyView8 = this.mboundView7;
            if (KeyboardUiMode.isDark()) {
                context12 = this.mboundView7.getContext();
                i14 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context12 = this.mboundView7.getContext();
                i14 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView8, AppCompatResources.getDrawable(context12, i14));
            NumberKeyView numberKeyView9 = this.mboundView8;
            if (KeyboardUiMode.isDark()) {
                context13 = this.mboundView8.getContext();
                i15 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context13 = this.mboundView8.getContext();
                i15 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView9, AppCompatResources.getDrawable(context13, i15));
            NumberKeyView numberKeyView10 = this.mboundView9;
            if (KeyboardUiMode.isDark()) {
                context14 = this.mboundView9.getContext();
                i16 = R.drawable.security_general_key_bg_selector_dark;
            } else {
                context14 = this.mboundView9.getContext();
                i16 = R.drawable.security_general_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(numberKeyView10, AppCompatResources.getDrawable(context14, i16));
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i2, Object obj, int i3) {
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i2, @Nullable Object obj) {
        return true;
    }

    private SecurityBaseNumberPureCanFinishBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[16], (ImageButton) objArr[12], (ImageButton) objArr[11], (LinearLayout) objArr[13], (FrameLayout) objArr[15], (ImageButton) objArr[14]);
        this.mDirtyFlags = -1L;
        this.deleteKey.setTag(null);
        this.hideKey.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        NumberKeyView numberKeyView = (NumberKeyView) objArr[1];
        this.mboundView1 = numberKeyView;
        numberKeyView.setTag(null);
        NumberKeyView numberKeyView2 = (NumberKeyView) objArr[10];
        this.mboundView10 = numberKeyView2;
        numberKeyView2.setTag(null);
        NumberKeyView numberKeyView3 = (NumberKeyView) objArr[2];
        this.mboundView2 = numberKeyView3;
        numberKeyView3.setTag(null);
        NumberKeyView numberKeyView4 = (NumberKeyView) objArr[3];
        this.mboundView3 = numberKeyView4;
        numberKeyView4.setTag(null);
        NumberKeyView numberKeyView5 = (NumberKeyView) objArr[4];
        this.mboundView4 = numberKeyView5;
        numberKeyView5.setTag(null);
        NumberKeyView numberKeyView6 = (NumberKeyView) objArr[5];
        this.mboundView5 = numberKeyView6;
        numberKeyView6.setTag(null);
        NumberKeyView numberKeyView7 = (NumberKeyView) objArr[6];
        this.mboundView6 = numberKeyView7;
        numberKeyView7.setTag(null);
        NumberKeyView numberKeyView8 = (NumberKeyView) objArr[7];
        this.mboundView7 = numberKeyView8;
        numberKeyView8.setTag(null);
        NumberKeyView numberKeyView9 = (NumberKeyView) objArr[8];
        this.mboundView8 = numberKeyView9;
        numberKeyView9.setTag(null);
        NumberKeyView numberKeyView10 = (NumberKeyView) objArr[9];
        this.mboundView9 = numberKeyView10;
        numberKeyView10.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
