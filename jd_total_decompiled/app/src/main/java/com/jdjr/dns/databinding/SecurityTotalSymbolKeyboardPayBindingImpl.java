package com.jdjr.dns.databinding;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
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
import com.jdjr.generalKeyboard.views.TotalKeyView;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* loaded from: classes18.dex */
public class SecurityTotalSymbolKeyboardPayBindingImpl extends SecurityTotalSymbolKeyboardPayBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final TotalKeyView mboundView1;
    @NonNull
    private final TotalKeyView mboundView10;
    @NonNull
    private final TotalKeyView mboundView11;
    @NonNull
    private final TotalKeyView mboundView12;
    @NonNull
    private final TotalKeyView mboundView14;
    @NonNull
    private final TotalKeyView mboundView15;
    @NonNull
    private final TotalKeyView mboundView16;
    @NonNull
    private final TotalKeyView mboundView17;
    @NonNull
    private final TotalKeyView mboundView2;
    @NonNull
    private final TotalKeyView mboundView3;
    @NonNull
    private final TotalKeyView mboundView4;
    @NonNull
    private final TotalKeyView mboundView5;
    @NonNull
    private final TotalKeyView mboundView6;
    @NonNull
    private final TotalKeyView mboundView7;
    @NonNull
    private final TotalKeyView mboundView8;
    @NonNull
    private final TotalKeyView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.btn_symbol_sure, 21);
    }

    public SecurityTotalSymbolKeyboardPayBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 22, sIncludes, sViewsWithIds));
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j2;
        Context context;
        int i2;
        Button button;
        int i3;
        Context context2;
        int i4;
        Button button2;
        int i5;
        Context context3;
        int i6;
        Context context4;
        int i7;
        Context context5;
        int i8;
        Button button3;
        int i9;
        LinearLayout linearLayout;
        int i10;
        Context context6;
        int i11;
        Context context7;
        int i12;
        Context context8;
        int i13;
        Context context9;
        int i14;
        Context context10;
        int i15;
        Context context11;
        int i16;
        Context context12;
        int i17;
        Context context13;
        int i18;
        Context context14;
        int i19;
        Context context15;
        int i20;
        Context context16;
        int i21;
        Context context17;
        int i22;
        Context context18;
        int i23;
        Context context19;
        int i24;
        Context context20;
        int i25;
        Context context21;
        int i26;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        long j5 = j2 & 1;
        if (j5 != 0 && j5 != 0) {
            if (KeyboardUiMode.isDark()) {
                j3 = j2 | 4 | 16 | 64 | 256 | 1024 | 4096 | IjkMediaMeta.AV_CH_TOP_FRONT_RIGHT | IjkMediaMeta.AV_CH_TOP_BACK_CENTER | 262144 | 1048576 | 4194304 | 16777216 | 67108864 | 268435456 | IjkMediaMeta.AV_CH_STEREO_RIGHT | IjkMediaMeta.AV_CH_WIDE_RIGHT | IjkMediaMeta.AV_CH_SURROUND_DIRECT_RIGHT | 68719476736L | 274877906944L | 1099511627776L | 4398046511104L | 17592186044416L | 70368744177664L | 281474976710656L;
                j4 = 1125899906842624L;
            } else {
                j3 = j2 | 2 | 8 | 32 | 128 | 512 | 2048 | IjkMediaMeta.AV_CH_TOP_FRONT_CENTER | IjkMediaMeta.AV_CH_TOP_BACK_LEFT | IjkMediaMeta.AV_CH_TOP_BACK_RIGHT | 524288 | 2097152 | 8388608 | 33554432 | 134217728 | IjkMediaMeta.AV_CH_STEREO_LEFT | IjkMediaMeta.AV_CH_WIDE_LEFT | IjkMediaMeta.AV_CH_SURROUND_DIRECT_LEFT | IjkMediaMeta.AV_CH_LOW_FREQUENCY_2 | 137438953472L | 549755813888L | 2199023255552L | 8796093022208L | 35184372088832L | 140737488355328L;
                j4 = 562949953421312L;
            }
            j2 = j3 | j4;
        }
        if ((j2 & 1) != 0) {
            Button button4 = this.btnSymbol123;
            if (KeyboardUiMode.isDark()) {
                context = this.btnSymbol123.getContext();
                i2 = R.drawable.security_total_function_key_gray_bg_selector_dark;
            } else {
                context = this.btnSymbol123.getContext();
                i2 = R.drawable.security_total_function_key_gray_bg_selector;
            }
            ViewBindingAdapter.setBackground(button4, AppCompatResources.getDrawable(context, i2));
            Button button5 = this.btnSymbol123;
            if (KeyboardUiMode.isDark()) {
                button = this.btnSymbol123;
                i3 = R.color.keyboard_color_total_btn_txt_dark;
            } else {
                button = this.btnSymbol123;
                i3 = R.color.keyboard_color_total_btn_txt;
            }
            button5.setTextColor(ViewDataBinding.getColorFromResource(button, i3));
            Button button6 = this.btnSymbolABC;
            if (KeyboardUiMode.isDark()) {
                context2 = this.btnSymbolABC.getContext();
                i4 = R.drawable.security_total_function_key_gray_bg_selector_dark;
            } else {
                context2 = this.btnSymbolABC.getContext();
                i4 = R.drawable.security_total_function_key_gray_bg_selector;
            }
            ViewBindingAdapter.setBackground(button6, AppCompatResources.getDrawable(context2, i4));
            Button button7 = this.btnSymbolABC;
            if (KeyboardUiMode.isDark()) {
                button2 = this.btnSymbolABC;
                i5 = R.color.keyboard_color_total_btn_txt_dark;
            } else {
                button2 = this.btnSymbolABC;
                i5 = R.color.keyboard_color_total_btn_txt;
            }
            button7.setTextColor(ViewDataBinding.getColorFromResource(button2, i5));
            ImageButton imageButton = this.btnSymbolDel;
            if (KeyboardUiMode.isDark()) {
                context3 = this.btnSymbolDel.getContext();
                i6 = R.drawable.security_total_function_key_gray_bg_selector_dark;
            } else {
                context3 = this.btnSymbolDel.getContext();
                i6 = R.drawable.security_total_function_key_gray_bg_selector;
            }
            ViewBindingAdapter.setBackground(imageButton, AppCompatResources.getDrawable(context3, i6));
            ImageButton imageButton2 = this.btnSymbolDel;
            if (KeyboardUiMode.isDark()) {
                context4 = this.btnSymbolDel.getContext();
                i7 = R.drawable.security_key_delete_icon_dark;
            } else {
                context4 = this.btnSymbolDel.getContext();
                i7 = R.drawable.security_key_delete_icon;
            }
            ImageViewBindingAdapter.setImageDrawable(imageButton2, AppCompatResources.getDrawable(context4, i7));
            Button button8 = this.btnSymbolMore;
            if (KeyboardUiMode.isDark()) {
                context5 = this.btnSymbolMore.getContext();
                i8 = R.drawable.security_total_function_key_gray_bg_selector_dark;
            } else {
                context5 = this.btnSymbolMore.getContext();
                i8 = R.drawable.security_total_function_key_gray_bg_selector;
            }
            ViewBindingAdapter.setBackground(button8, AppCompatResources.getDrawable(context5, i8));
            Button button9 = this.btnSymbolMore;
            if (KeyboardUiMode.isDark()) {
                button3 = this.btnSymbolMore;
                i9 = R.color.keyboard_color_total_btn_txt_dark;
            } else {
                button3 = this.btnSymbolMore;
                i9 = R.color.keyboard_color_total_btn_txt;
            }
            button9.setTextColor(ViewDataBinding.getColorFromResource(button3, i9));
            LinearLayout linearLayout2 = this.mboundView0;
            if (KeyboardUiMode.isDark()) {
                linearLayout = this.mboundView0;
                i10 = R.color.keyboard_color_keyboard_bg_dark;
            } else {
                linearLayout = this.mboundView0;
                i10 = R.color.keyboard_color_keyboard_bg;
            }
            ViewBindingAdapter.setBackground(linearLayout2, Converters.convertColorToDrawable(ViewDataBinding.getColorFromResource(linearLayout, i10)));
            TotalKeyView totalKeyView = this.mboundView1;
            if (KeyboardUiMode.isDark()) {
                context6 = this.mboundView1.getContext();
                i11 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context6 = this.mboundView1.getContext();
                i11 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView, AppCompatResources.getDrawable(context6, i11));
            TotalKeyView totalKeyView2 = this.mboundView10;
            if (KeyboardUiMode.isDark()) {
                context7 = this.mboundView10.getContext();
                i12 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context7 = this.mboundView10.getContext();
                i12 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView2, AppCompatResources.getDrawable(context7, i12));
            TotalKeyView totalKeyView3 = this.mboundView11;
            if (KeyboardUiMode.isDark()) {
                context8 = this.mboundView11.getContext();
                i13 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context8 = this.mboundView11.getContext();
                i13 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView3, AppCompatResources.getDrawable(context8, i13));
            TotalKeyView totalKeyView4 = this.mboundView12;
            if (KeyboardUiMode.isDark()) {
                context9 = this.mboundView12.getContext();
                i14 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context9 = this.mboundView12.getContext();
                i14 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView4, AppCompatResources.getDrawable(context9, i14));
            TotalKeyView totalKeyView5 = this.mboundView14;
            if (KeyboardUiMode.isDark()) {
                context10 = this.mboundView14.getContext();
                i15 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context10 = this.mboundView14.getContext();
                i15 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView5, AppCompatResources.getDrawable(context10, i15));
            TotalKeyView totalKeyView6 = this.mboundView15;
            if (KeyboardUiMode.isDark()) {
                context11 = this.mboundView15.getContext();
                i16 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context11 = this.mboundView15.getContext();
                i16 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView6, AppCompatResources.getDrawable(context11, i16));
            TotalKeyView totalKeyView7 = this.mboundView16;
            if (KeyboardUiMode.isDark()) {
                context12 = this.mboundView16.getContext();
                i17 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context12 = this.mboundView16.getContext();
                i17 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView7, AppCompatResources.getDrawable(context12, i17));
            TotalKeyView totalKeyView8 = this.mboundView17;
            if (KeyboardUiMode.isDark()) {
                context13 = this.mboundView17.getContext();
                i18 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context13 = this.mboundView17.getContext();
                i18 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView8, AppCompatResources.getDrawable(context13, i18));
            TotalKeyView totalKeyView9 = this.mboundView2;
            if (KeyboardUiMode.isDark()) {
                context14 = this.mboundView2.getContext();
                i19 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context14 = this.mboundView2.getContext();
                i19 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView9, AppCompatResources.getDrawable(context14, i19));
            TotalKeyView totalKeyView10 = this.mboundView3;
            if (KeyboardUiMode.isDark()) {
                context15 = this.mboundView3.getContext();
                i20 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context15 = this.mboundView3.getContext();
                i20 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView10, AppCompatResources.getDrawable(context15, i20));
            TotalKeyView totalKeyView11 = this.mboundView4;
            if (KeyboardUiMode.isDark()) {
                context16 = this.mboundView4.getContext();
                i21 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context16 = this.mboundView4.getContext();
                i21 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView11, AppCompatResources.getDrawable(context16, i21));
            TotalKeyView totalKeyView12 = this.mboundView5;
            if (KeyboardUiMode.isDark()) {
                context17 = this.mboundView5.getContext();
                i22 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context17 = this.mboundView5.getContext();
                i22 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView12, AppCompatResources.getDrawable(context17, i22));
            TotalKeyView totalKeyView13 = this.mboundView6;
            if (KeyboardUiMode.isDark()) {
                context18 = this.mboundView6.getContext();
                i23 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context18 = this.mboundView6.getContext();
                i23 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView13, AppCompatResources.getDrawable(context18, i23));
            TotalKeyView totalKeyView14 = this.mboundView7;
            if (KeyboardUiMode.isDark()) {
                context19 = this.mboundView7.getContext();
                i24 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context19 = this.mboundView7.getContext();
                i24 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView14, AppCompatResources.getDrawable(context19, i24));
            TotalKeyView totalKeyView15 = this.mboundView8;
            if (KeyboardUiMode.isDark()) {
                context20 = this.mboundView8.getContext();
                i25 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context20 = this.mboundView8.getContext();
                i25 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView15, AppCompatResources.getDrawable(context20, i25));
            TotalKeyView totalKeyView16 = this.mboundView9;
            if (KeyboardUiMode.isDark()) {
                context21 = this.mboundView9.getContext();
                i26 = R.drawable.security_total_key_bg_selector_dark;
            } else {
                context21 = this.mboundView9.getContext();
                i26 = R.drawable.security_total_key_bg_selector;
            }
            ViewBindingAdapter.setBackground(totalKeyView16, AppCompatResources.getDrawable(context21, i26));
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

    private SecurityTotalSymbolKeyboardPayBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (Button) objArr[20], (Button) objArr[19], (ImageButton) objArr[18], (Button) objArr[13], (Button) objArr[21]);
        this.mDirtyFlags = -1L;
        this.btnSymbol123.setTag(null);
        this.btnSymbolABC.setTag(null);
        this.btnSymbolDel.setTag(null);
        this.btnSymbolMore.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag(null);
        TotalKeyView totalKeyView = (TotalKeyView) objArr[1];
        this.mboundView1 = totalKeyView;
        totalKeyView.setTag(null);
        TotalKeyView totalKeyView2 = (TotalKeyView) objArr[10];
        this.mboundView10 = totalKeyView2;
        totalKeyView2.setTag(null);
        TotalKeyView totalKeyView3 = (TotalKeyView) objArr[11];
        this.mboundView11 = totalKeyView3;
        totalKeyView3.setTag(null);
        TotalKeyView totalKeyView4 = (TotalKeyView) objArr[12];
        this.mboundView12 = totalKeyView4;
        totalKeyView4.setTag(null);
        TotalKeyView totalKeyView5 = (TotalKeyView) objArr[14];
        this.mboundView14 = totalKeyView5;
        totalKeyView5.setTag(null);
        TotalKeyView totalKeyView6 = (TotalKeyView) objArr[15];
        this.mboundView15 = totalKeyView6;
        totalKeyView6.setTag(null);
        TotalKeyView totalKeyView7 = (TotalKeyView) objArr[16];
        this.mboundView16 = totalKeyView7;
        totalKeyView7.setTag(null);
        TotalKeyView totalKeyView8 = (TotalKeyView) objArr[17];
        this.mboundView17 = totalKeyView8;
        totalKeyView8.setTag(null);
        TotalKeyView totalKeyView9 = (TotalKeyView) objArr[2];
        this.mboundView2 = totalKeyView9;
        totalKeyView9.setTag(null);
        TotalKeyView totalKeyView10 = (TotalKeyView) objArr[3];
        this.mboundView3 = totalKeyView10;
        totalKeyView10.setTag(null);
        TotalKeyView totalKeyView11 = (TotalKeyView) objArr[4];
        this.mboundView4 = totalKeyView11;
        totalKeyView11.setTag(null);
        TotalKeyView totalKeyView12 = (TotalKeyView) objArr[5];
        this.mboundView5 = totalKeyView12;
        totalKeyView12.setTag(null);
        TotalKeyView totalKeyView13 = (TotalKeyView) objArr[6];
        this.mboundView6 = totalKeyView13;
        totalKeyView13.setTag(null);
        TotalKeyView totalKeyView14 = (TotalKeyView) objArr[7];
        this.mboundView7 = totalKeyView14;
        totalKeyView14.setTag(null);
        TotalKeyView totalKeyView15 = (TotalKeyView) objArr[8];
        this.mboundView8 = totalKeyView15;
        totalKeyView15.setTag(null);
        TotalKeyView totalKeyView16 = (TotalKeyView) objArr[9];
        this.mboundView9 = totalKeyView16;
        totalKeyView16.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
