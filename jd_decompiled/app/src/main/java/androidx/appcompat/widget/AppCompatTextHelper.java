package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.AutoSizeableTextView;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class AppCompatTextHelper {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private static final int TEXT_FONT_WEIGHT_UNSPECIFIED = -1;
    private boolean mAsyncFontPending;
    @NonNull
    private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
    private TintInfo mDrawableBottomTint;
    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableLeftTint;
    private TintInfo mDrawableRightTint;
    private TintInfo mDrawableStartTint;
    private TintInfo mDrawableTint;
    private TintInfo mDrawableTopTint;
    private Typeface mFontTypeface;
    @NonNull
    private final TextView mView;
    private int mStyle = 0;
    private int mFontWeight = -1;

    public AppCompatTextHelper(@NonNull TextView textView) {
        this.mView = textView;
        this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(textView);
    }

    private void applyCompoundDrawableTint(Drawable drawable, TintInfo tintInfo) {
        if (drawable == null || tintInfo == null) {
            return;
        }
        AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
    }

    private static TintInfo createTintInfo(Context context, AppCompatDrawableManager appCompatDrawableManager, int i2) {
        ColorStateList tintList = appCompatDrawableManager.getTintList(context, i2);
        if (tintList != null) {
            TintInfo tintInfo = new TintInfo();
            tintInfo.mHasTintList = true;
            tintInfo.mTintList = tintList;
            return tintInfo;
        }
        return null;
    }

    private void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4, Drawable drawable5, Drawable drawable6) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17 && (drawable5 != null || drawable6 != null)) {
            Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
            TextView textView = this.mView;
            if (drawable5 == null) {
                drawable5 = compoundDrawablesRelative[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawablesRelative[1];
            }
            if (drawable6 == null) {
                drawable6 = compoundDrawablesRelative[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable5, drawable2, drawable6, drawable4);
        } else if (drawable == null && drawable2 == null && drawable3 == null && drawable4 == null) {
        } else {
            if (i2 >= 17) {
                Drawable[] compoundDrawablesRelative2 = this.mView.getCompoundDrawablesRelative();
                if (compoundDrawablesRelative2[0] != null || compoundDrawablesRelative2[2] != null) {
                    TextView textView2 = this.mView;
                    Drawable drawable7 = compoundDrawablesRelative2[0];
                    if (drawable2 == null) {
                        drawable2 = compoundDrawablesRelative2[1];
                    }
                    Drawable drawable8 = compoundDrawablesRelative2[2];
                    if (drawable4 == null) {
                        drawable4 = compoundDrawablesRelative2[3];
                    }
                    textView2.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable7, drawable2, drawable8, drawable4);
                    return;
                }
            }
            Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
            TextView textView3 = this.mView;
            if (drawable == null) {
                drawable = compoundDrawables[0];
            }
            if (drawable2 == null) {
                drawable2 = compoundDrawables[1];
            }
            if (drawable3 == null) {
                drawable3 = compoundDrawables[2];
            }
            if (drawable4 == null) {
                drawable4 = compoundDrawables[3];
            }
            textView3.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        }
    }

    private void setCompoundTints() {
        TintInfo tintInfo = this.mDrawableTint;
        this.mDrawableLeftTint = tintInfo;
        this.mDrawableTopTint = tintInfo;
        this.mDrawableRightTint = tintInfo;
        this.mDrawableBottomTint = tintInfo;
        this.mDrawableStartTint = tintInfo;
        this.mDrawableEndTint = tintInfo;
    }

    private void setTextSizeInternal(int i2, float f2) {
        this.mAutoSizeTextHelper.setTextSizeInternal(i2, f2);
    }

    private void updateTypefaceAndStyle(Context context, TintTypedArray tintTypedArray) {
        String string;
        this.mStyle = tintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            int i3 = tintTypedArray.getInt(R.styleable.TextAppearance_android_textFontWeight, -1);
            this.mFontWeight = i3;
            if (i3 != -1) {
                this.mStyle = (this.mStyle & 2) | 0;
            }
        }
        int i4 = R.styleable.TextAppearance_android_fontFamily;
        if (!tintTypedArray.hasValue(i4) && !tintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
            int i5 = R.styleable.TextAppearance_android_typeface;
            if (tintTypedArray.hasValue(i5)) {
                this.mAsyncFontPending = false;
                int i6 = tintTypedArray.getInt(i5, 1);
                if (i6 == 1) {
                    this.mFontTypeface = Typeface.SANS_SERIF;
                    return;
                } else if (i6 == 2) {
                    this.mFontTypeface = Typeface.SERIF;
                    return;
                } else if (i6 != 3) {
                    return;
                } else {
                    this.mFontTypeface = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.mFontTypeface = null;
        int i7 = R.styleable.TextAppearance_fontFamily;
        if (tintTypedArray.hasValue(i7)) {
            i4 = i7;
        }
        final int i8 = this.mFontWeight;
        final int i9 = this.mStyle;
        if (!context.isRestricted()) {
            new WeakReference(this.mView);
            try {
                Typeface font = tintTypedArray.getFont(i4, this.mStyle, new ResourcesCompat.FontCallback
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x007e: INVOKE (r11v9 'font' android.graphics.Typeface) = 
                      (r12v0 'tintTypedArray' androidx.appcompat.widget.TintTypedArray)
                      (r5v1 'i4' int)
                      (wrap: int : 0x007c: IGET (r10v0 'this' androidx.appcompat.widget.AppCompatTextHelper A[IMMUTABLE_TYPE, THIS]) A[Catch: NotFoundException | UnsupportedOperationException -> 0x00ab, TRY_ENTER, WRAPPED] (LINE:20) androidx.appcompat.widget.AppCompatTextHelper.mStyle int)
                      (wrap: androidx.core.content.res.ResourcesCompat$FontCallback : 0x0079: CONSTRUCTOR 
                      (r10v0 'this' androidx.appcompat.widget.AppCompatTextHelper A[IMMUTABLE_TYPE, THIS])
                      (r6v3 'i8' int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r8v1 'i9' int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r11 I:java.lang.ref.WeakReference A[DONT_GENERATE, DONT_INLINE, REMOVE])
                     A[MD:(androidx.appcompat.widget.AppCompatTextHelper, int, int, java.lang.ref.WeakReference):void (m), WRAPPED] (LINE:19) call: androidx.appcompat.widget.AppCompatTextHelper.1.<init>(androidx.appcompat.widget.AppCompatTextHelper, int, int, java.lang.ref.WeakReference):void type: CONSTRUCTOR)
                     type: VIRTUAL call: androidx.appcompat.widget.TintTypedArray.getFont(int, int, androidx.core.content.res.ResourcesCompat$FontCallback):android.graphics.Typeface A[Catch: NotFoundException | UnsupportedOperationException -> 0x00ab, DECLARE_VAR, MD:(int, int, androidx.core.content.res.ResourcesCompat$FontCallback):android.graphics.Typeface (m)] (LINE:20) in method: androidx.appcompat.widget.AppCompatTextHelper.updateTypefaceAndStyle(android.content.Context, androidx.appcompat.widget.TintTypedArray):void, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                    	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: java.lang.NullPointerException
                    */
                /*
                    Method dump skipped, instructions count: 218
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.updateTypefaceAndStyle(android.content.Context, androidx.appcompat.widget.TintTypedArray):void");
            }

            public void applyCompoundDrawablesTints() {
                if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
                    Drawable[] compoundDrawables = this.mView.getCompoundDrawables();
                    applyCompoundDrawableTint(compoundDrawables[0], this.mDrawableLeftTint);
                    applyCompoundDrawableTint(compoundDrawables[1], this.mDrawableTopTint);
                    applyCompoundDrawableTint(compoundDrawables[2], this.mDrawableRightTint);
                    applyCompoundDrawableTint(compoundDrawables[3], this.mDrawableBottomTint);
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    if (this.mDrawableStartTint == null && this.mDrawableEndTint == null) {
                        return;
                    }
                    Drawable[] compoundDrawablesRelative = this.mView.getCompoundDrawablesRelative();
                    applyCompoundDrawableTint(compoundDrawablesRelative[0], this.mDrawableStartTint);
                    applyCompoundDrawableTint(compoundDrawablesRelative[2], this.mDrawableEndTint);
                }
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            public void autoSizeText() {
                this.mAutoSizeTextHelper.autoSizeText();
            }

            public int getAutoSizeMaxTextSize() {
                return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
            }

            public int getAutoSizeMinTextSize() {
                return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
            }

            public int getAutoSizeStepGranularity() {
                return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
            }

            public int[] getAutoSizeTextAvailableSizes() {
                return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
            }

            public int getAutoSizeTextType() {
                return this.mAutoSizeTextHelper.getAutoSizeTextType();
            }

            @Nullable
            public ColorStateList getCompoundDrawableTintList() {
                TintInfo tintInfo = this.mDrawableTint;
                if (tintInfo != null) {
                    return tintInfo.mTintList;
                }
                return null;
            }

            @Nullable
            public PorterDuff.Mode getCompoundDrawableTintMode() {
                TintInfo tintInfo = this.mDrawableTint;
                if (tintInfo != null) {
                    return tintInfo.mTintMode;
                }
                return null;
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            public boolean isAutoSizeEnabled() {
                return this.mAutoSizeTextHelper.isAutoSizeEnabled();
            }

            /* JADX WARN: Removed duplicated region for block: B:196:0x00d0  */
            /* JADX WARN: Removed duplicated region for block: B:207:0x00f9  */
            /* JADX WARN: Removed duplicated region for block: B:211:0x0104  */
            /* JADX WARN: Removed duplicated region for block: B:212:0x0109  */
            /* JADX WARN: Removed duplicated region for block: B:214:0x010c  */
            /* JADX WARN: Removed duplicated region for block: B:227:0x0146  */
            /* JADX WARN: Removed duplicated region for block: B:238:0x0172  */
            /* JADX WARN: Removed duplicated region for block: B:241:0x017a  */
            /* JADX WARN: Removed duplicated region for block: B:247:0x018d  */
            /* JADX WARN: Removed duplicated region for block: B:255:0x01b0  */
            /* JADX WARN: Removed duplicated region for block: B:257:0x01b7  */
            /* JADX WARN: Removed duplicated region for block: B:259:0x01be  */
            /* JADX WARN: Removed duplicated region for block: B:261:0x01c5 A[ADDED_TO_REGION] */
            /* JADX WARN: Removed duplicated region for block: B:265:0x01ce  */
            /* JADX WARN: Removed duplicated region for block: B:270:0x01e2  */
            /* JADX WARN: Removed duplicated region for block: B:272:0x01e9  */
            /* JADX WARN: Removed duplicated region for block: B:280:0x021a  */
            /* JADX WARN: Removed duplicated region for block: B:284:0x022b  */
            /* JADX WARN: Removed duplicated region for block: B:290:0x0268  */
            /* JADX WARN: Removed duplicated region for block: B:291:0x026e  */
            /* JADX WARN: Removed duplicated region for block: B:294:0x0277  */
            /* JADX WARN: Removed duplicated region for block: B:295:0x027d  */
            /* JADX WARN: Removed duplicated region for block: B:298:0x0286  */
            /* JADX WARN: Removed duplicated region for block: B:299:0x028c  */
            /* JADX WARN: Removed duplicated region for block: B:302:0x0295  */
            /* JADX WARN: Removed duplicated region for block: B:303:0x029b  */
            /* JADX WARN: Removed duplicated region for block: B:306:0x02a4  */
            /* JADX WARN: Removed duplicated region for block: B:307:0x02aa  */
            /* JADX WARN: Removed duplicated region for block: B:310:0x02b3  */
            /* JADX WARN: Removed duplicated region for block: B:311:0x02b9  */
            /* JADX WARN: Removed duplicated region for block: B:314:0x02cd  */
            /* JADX WARN: Removed duplicated region for block: B:317:0x02de  */
            /* JADX WARN: Removed duplicated region for block: B:318:0x02ee  */
            /* JADX WARN: Removed duplicated region for block: B:321:0x0306  */
            /* JADX WARN: Removed duplicated region for block: B:323:0x030d  */
            /* JADX WARN: Removed duplicated region for block: B:325:0x0314  */
            /* JADX WARN: Removed duplicated region for block: B:327:? A[RETURN, SYNTHETIC] */
            @android.annotation.SuppressLint({"NewApi"})
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void loadFromAttributes(@androidx.annotation.Nullable android.util.AttributeSet r24, int r25) {
                /*
                    Method dump skipped, instructions count: 794
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextHelper.loadFromAttributes(android.util.AttributeSet, int):void");
            }

            void onAsyncTypefaceReceived(WeakReference<TextView> weakReference, Typeface typeface) {
                if (this.mAsyncFontPending) {
                    this.mFontTypeface = typeface;
                    TextView textView = weakReference.get();
                    if (textView != null) {
                        textView.setTypeface(typeface, this.mStyle);
                    }
                }
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
                if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
                    return;
                }
                autoSizeText();
            }

            public void onSetCompoundDrawables() {
                applyCompoundDrawablesTints();
            }

            public void onSetTextAppearance(Context context, int i2) {
                String string;
                ColorStateList colorStateList;
                TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, i2, R.styleable.TextAppearance);
                int i3 = R.styleable.TextAppearance_textAllCaps;
                if (obtainStyledAttributes.hasValue(i3)) {
                    setAllCaps(obtainStyledAttributes.getBoolean(i3, false));
                }
                int i4 = Build.VERSION.SDK_INT;
                if (i4 < 23) {
                    int i5 = R.styleable.TextAppearance_android_textColor;
                    if (obtainStyledAttributes.hasValue(i5) && (colorStateList = obtainStyledAttributes.getColorStateList(i5)) != null) {
                        this.mView.setTextColor(colorStateList);
                    }
                }
                int i6 = R.styleable.TextAppearance_android_textSize;
                if (obtainStyledAttributes.hasValue(i6) && obtainStyledAttributes.getDimensionPixelSize(i6, -1) == 0) {
                    this.mView.setTextSize(0, 0.0f);
                }
                updateTypefaceAndStyle(context, obtainStyledAttributes);
                if (i4 >= 26) {
                    int i7 = R.styleable.TextAppearance_fontVariationSettings;
                    if (obtainStyledAttributes.hasValue(i7) && (string = obtainStyledAttributes.getString(i7)) != null) {
                        this.mView.setFontVariationSettings(string);
                    }
                }
                obtainStyledAttributes.recycle();
                Typeface typeface = this.mFontTypeface;
                if (typeface != null) {
                    this.mView.setTypeface(typeface, this.mStyle);
                }
            }

            public void setAllCaps(boolean z) {
                this.mView.setAllCaps(z);
            }

            public void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) throws IllegalArgumentException {
                this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(i2, i3, i4, i5);
            }

            public void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] iArr, int i2) throws IllegalArgumentException {
                this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i2);
            }

            public void setAutoSizeTextTypeWithDefaults(int i2) {
                this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(i2);
            }

            public void setCompoundDrawableTintList(@Nullable ColorStateList colorStateList) {
                if (this.mDrawableTint == null) {
                    this.mDrawableTint = new TintInfo();
                }
                TintInfo tintInfo = this.mDrawableTint;
                tintInfo.mTintList = colorStateList;
                tintInfo.mHasTintList = colorStateList != null;
                setCompoundTints();
            }

            public void setCompoundDrawableTintMode(@Nullable PorterDuff.Mode mode) {
                if (this.mDrawableTint == null) {
                    this.mDrawableTint = new TintInfo();
                }
                TintInfo tintInfo = this.mDrawableTint;
                tintInfo.mTintMode = mode;
                tintInfo.mHasTintMode = mode != null;
                setCompoundTints();
            }

            @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
            public void setTextSize(int i2, float f2) {
                if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE || isAutoSizeEnabled()) {
                    return;
                }
                setTextSizeInternal(i2, f2);
            }
        }
