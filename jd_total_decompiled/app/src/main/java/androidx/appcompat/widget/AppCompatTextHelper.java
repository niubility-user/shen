package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.AutoSizeableTextView;
import androidx.core.widget.TextViewCompat;
import java.lang.ref.WeakReference;
import java.util.Locale;

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
                    	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                    	... 31 more
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

            /* JADX WARN: Removed duplicated region for block: B:360:0x00d0  */
            /* JADX WARN: Removed duplicated region for block: B:371:0x00f9  */
            /* JADX WARN: Removed duplicated region for block: B:375:0x0104  */
            /* JADX WARN: Removed duplicated region for block: B:376:0x0109  */
            /* JADX WARN: Removed duplicated region for block: B:378:0x010c  */
            /* JADX WARN: Removed duplicated region for block: B:391:0x0146  */
            /* JADX WARN: Removed duplicated region for block: B:402:0x0172  */
            /* JADX WARN: Removed duplicated region for block: B:405:0x017a  */
            /* JADX WARN: Removed duplicated region for block: B:411:0x018d  */
            /* JADX WARN: Removed duplicated region for block: B:419:0x01b0  */
            /* JADX WARN: Removed duplicated region for block: B:421:0x01b7  */
            /* JADX WARN: Removed duplicated region for block: B:423:0x01be  */
            /* JADX WARN: Removed duplicated region for block: B:425:0x01c5 A[ADDED_TO_REGION] */
            /* JADX WARN: Removed duplicated region for block: B:429:0x01ce  */
            /* JADX WARN: Removed duplicated region for block: B:434:0x01e2  */
            /* JADX WARN: Removed duplicated region for block: B:436:0x01e9  */
            /* JADX WARN: Removed duplicated region for block: B:444:0x021a  */
            /* JADX WARN: Removed duplicated region for block: B:448:0x022b  */
            /* JADX WARN: Removed duplicated region for block: B:454:0x0268  */
            /* JADX WARN: Removed duplicated region for block: B:455:0x026e  */
            /* JADX WARN: Removed duplicated region for block: B:458:0x0277  */
            /* JADX WARN: Removed duplicated region for block: B:459:0x027d  */
            /* JADX WARN: Removed duplicated region for block: B:462:0x0286  */
            /* JADX WARN: Removed duplicated region for block: B:463:0x028c  */
            /* JADX WARN: Removed duplicated region for block: B:466:0x0295  */
            /* JADX WARN: Removed duplicated region for block: B:467:0x029b  */
            /* JADX WARN: Removed duplicated region for block: B:470:0x02a4  */
            /* JADX WARN: Removed duplicated region for block: B:471:0x02aa  */
            /* JADX WARN: Removed duplicated region for block: B:474:0x02b3  */
            /* JADX WARN: Removed duplicated region for block: B:475:0x02b9  */
            /* JADX WARN: Removed duplicated region for block: B:478:0x02cd  */
            /* JADX WARN: Removed duplicated region for block: B:481:0x02de  */
            /* JADX WARN: Removed duplicated region for block: B:482:0x02ee  */
            /* JADX WARN: Removed duplicated region for block: B:485:0x0306  */
            /* JADX WARN: Removed duplicated region for block: B:487:0x030d  */
            /* JADX WARN: Removed duplicated region for block: B:489:0x0314  */
            /* JADX WARN: Removed duplicated region for block: B:491:? A[RETURN, SYNTHETIC] */
            @SuppressLint({"NewApi"})
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void loadFromAttributes(@Nullable AttributeSet attributeSet, int i2) {
                String str;
                ColorStateList colorStateList;
                String str2;
                boolean z;
                ColorStateList colorStateList2;
                ColorStateList colorStateList3;
                boolean z2;
                String str3;
                int i3;
                int i4;
                String str4;
                AppCompatDrawableManager appCompatDrawableManager;
                Typeface typeface;
                TintTypedArray obtainStyledAttributes;
                int i5;
                int i6;
                int i7;
                int dimensionPixelSize;
                int dimensionPixelSize2;
                int dimensionPixelSize3;
                int[] autoSizeTextAvailableSizes;
                Context context = this.mView.getContext();
                AppCompatDrawableManager appCompatDrawableManager2 = AppCompatDrawableManager.get();
                int[] iArr = R.styleable.AppCompatTextHelper;
                TintTypedArray obtainStyledAttributes2 = TintTypedArray.obtainStyledAttributes(context, attributeSet, iArr, i2, 0);
                TextView textView = this.mView;
                ViewCompat.saveAttributeDataForStyleable(textView, textView.getContext(), iArr, attributeSet, obtainStyledAttributes2.getWrappedTypeArray(), i2, 0);
                int resourceId = obtainStyledAttributes2.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
                int i8 = R.styleable.AppCompatTextHelper_android_drawableLeft;
                if (obtainStyledAttributes2.hasValue(i8)) {
                    this.mDrawableLeftTint = createTintInfo(context, appCompatDrawableManager2, obtainStyledAttributes2.getResourceId(i8, 0));
                }
                int i9 = R.styleable.AppCompatTextHelper_android_drawableTop;
                if (obtainStyledAttributes2.hasValue(i9)) {
                    this.mDrawableTopTint = createTintInfo(context, appCompatDrawableManager2, obtainStyledAttributes2.getResourceId(i9, 0));
                }
                int i10 = R.styleable.AppCompatTextHelper_android_drawableRight;
                if (obtainStyledAttributes2.hasValue(i10)) {
                    this.mDrawableRightTint = createTintInfo(context, appCompatDrawableManager2, obtainStyledAttributes2.getResourceId(i10, 0));
                }
                int i11 = R.styleable.AppCompatTextHelper_android_drawableBottom;
                if (obtainStyledAttributes2.hasValue(i11)) {
                    this.mDrawableBottomTint = createTintInfo(context, appCompatDrawableManager2, obtainStyledAttributes2.getResourceId(i11, 0));
                }
                int i12 = Build.VERSION.SDK_INT;
                if (i12 >= 17) {
                    int i13 = R.styleable.AppCompatTextHelper_android_drawableStart;
                    if (obtainStyledAttributes2.hasValue(i13)) {
                        this.mDrawableStartTint = createTintInfo(context, appCompatDrawableManager2, obtainStyledAttributes2.getResourceId(i13, 0));
                    }
                    int i14 = R.styleable.AppCompatTextHelper_android_drawableEnd;
                    if (obtainStyledAttributes2.hasValue(i14)) {
                        this.mDrawableEndTint = createTintInfo(context, appCompatDrawableManager2, obtainStyledAttributes2.getResourceId(i14, 0));
                    }
                }
                obtainStyledAttributes2.recycle();
                boolean z3 = this.mView.getTransformationMethod() instanceof PasswordTransformationMethod;
                if (resourceId != -1) {
                    TintTypedArray obtainStyledAttributes3 = TintTypedArray.obtainStyledAttributes(context, resourceId, R.styleable.TextAppearance);
                    if (!z3) {
                        int i15 = R.styleable.TextAppearance_textAllCaps;
                        if (obtainStyledAttributes3.hasValue(i15)) {
                            z = obtainStyledAttributes3.getBoolean(i15, false);
                            z2 = true;
                            updateTypefaceAndStyle(context, obtainStyledAttributes3);
                            if (i12 >= 23) {
                                int i16 = R.styleable.TextAppearance_android_textColor;
                                colorStateList = obtainStyledAttributes3.hasValue(i16) ? obtainStyledAttributes3.getColorStateList(i16) : null;
                                int i17 = R.styleable.TextAppearance_android_textColorHint;
                                colorStateList2 = obtainStyledAttributes3.hasValue(i17) ? obtainStyledAttributes3.getColorStateList(i17) : null;
                                int i18 = R.styleable.TextAppearance_android_textColorLink;
                                if (obtainStyledAttributes3.hasValue(i18)) {
                                    colorStateList3 = obtainStyledAttributes3.getColorStateList(i18);
                                    int i19 = R.styleable.TextAppearance_textLocale;
                                    str2 = obtainStyledAttributes3.hasValue(i19) ? obtainStyledAttributes3.getString(i19) : null;
                                    if (i12 >= 26) {
                                        int i20 = R.styleable.TextAppearance_fontVariationSettings;
                                        if (obtainStyledAttributes3.hasValue(i20)) {
                                            str = obtainStyledAttributes3.getString(i20);
                                            obtainStyledAttributes3.recycle();
                                        }
                                    }
                                    str = null;
                                    obtainStyledAttributes3.recycle();
                                }
                            } else {
                                colorStateList = null;
                                colorStateList2 = null;
                            }
                            colorStateList3 = null;
                            int i192 = R.styleable.TextAppearance_textLocale;
                            if (obtainStyledAttributes3.hasValue(i192)) {
                            }
                            if (i12 >= 26) {
                            }
                            str = null;
                            obtainStyledAttributes3.recycle();
                        }
                    }
                    z = false;
                    z2 = false;
                    updateTypefaceAndStyle(context, obtainStyledAttributes3);
                    if (i12 >= 23) {
                    }
                    colorStateList3 = null;
                    int i1922 = R.styleable.TextAppearance_textLocale;
                    if (obtainStyledAttributes3.hasValue(i1922)) {
                    }
                    if (i12 >= 26) {
                    }
                    str = null;
                    obtainStyledAttributes3.recycle();
                } else {
                    str = null;
                    colorStateList = null;
                    str2 = null;
                    z = false;
                    colorStateList2 = null;
                    colorStateList3 = null;
                    z2 = false;
                }
                TintTypedArray obtainStyledAttributes4 = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.TextAppearance, i2, 0);
                if (!z3) {
                    int i21 = R.styleable.TextAppearance_textAllCaps;
                    if (obtainStyledAttributes4.hasValue(i21)) {
                        str3 = str;
                        z = obtainStyledAttributes4.getBoolean(i21, false);
                        i3 = 23;
                        z2 = true;
                        if (i12 < i3) {
                            int i22 = R.styleable.TextAppearance_android_textColor;
                            if (obtainStyledAttributes4.hasValue(i22)) {
                                colorStateList = obtainStyledAttributes4.getColorStateList(i22);
                            }
                            int i23 = R.styleable.TextAppearance_android_textColorHint;
                            if (obtainStyledAttributes4.hasValue(i23)) {
                                colorStateList2 = obtainStyledAttributes4.getColorStateList(i23);
                            }
                            int i24 = R.styleable.TextAppearance_android_textColorLink;
                            if (obtainStyledAttributes4.hasValue(i24)) {
                                colorStateList3 = obtainStyledAttributes4.getColorStateList(i24);
                            }
                        }
                        i4 = R.styleable.TextAppearance_textLocale;
                        if (obtainStyledAttributes4.hasValue(i4)) {
                            str2 = obtainStyledAttributes4.getString(i4);
                        }
                        if (i12 >= 26) {
                            int i25 = R.styleable.TextAppearance_fontVariationSettings;
                            if (obtainStyledAttributes4.hasValue(i25)) {
                                str4 = obtainStyledAttributes4.getString(i25);
                                if (i12 >= 28) {
                                    int i26 = R.styleable.TextAppearance_android_textSize;
                                    if (obtainStyledAttributes4.hasValue(i26)) {
                                        appCompatDrawableManager = appCompatDrawableManager2;
                                        if (obtainStyledAttributes4.getDimensionPixelSize(i26, -1) == 0) {
                                            this.mView.setTextSize(0, 0.0f);
                                        }
                                        updateTypefaceAndStyle(context, obtainStyledAttributes4);
                                        obtainStyledAttributes4.recycle();
                                        if (colorStateList != null) {
                                            this.mView.setTextColor(colorStateList);
                                        }
                                        if (colorStateList2 != null) {
                                            this.mView.setHintTextColor(colorStateList2);
                                        }
                                        if (colorStateList3 != null) {
                                            this.mView.setLinkTextColor(colorStateList3);
                                        }
                                        if (!z3 && z2) {
                                            setAllCaps(z);
                                        }
                                        typeface = this.mFontTypeface;
                                        if (typeface != null) {
                                            if (this.mFontWeight == -1) {
                                                this.mView.setTypeface(typeface, this.mStyle);
                                            } else {
                                                this.mView.setTypeface(typeface);
                                            }
                                        }
                                        if (str4 != null) {
                                            this.mView.setFontVariationSettings(str4);
                                        }
                                        if (str2 != null) {
                                            if (i12 >= 24) {
                                                this.mView.setTextLocales(LocaleList.forLanguageTags(str2));
                                            } else if (i12 >= 21) {
                                                this.mView.setTextLocale(Locale.forLanguageTag(str2.substring(0, str2.indexOf(44))));
                                            }
                                        }
                                        this.mAutoSizeTextHelper.loadFromAttributes(attributeSet, i2);
                                        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && this.mAutoSizeTextHelper.getAutoSizeTextType() != 0) {
                                            autoSizeTextAvailableSizes = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
                                            if (autoSizeTextAvailableSizes.length > 0) {
                                                if (this.mView.getAutoSizeStepGranularity() != -1.0f) {
                                                    this.mView.setAutoSizeTextTypeUniformWithConfiguration(this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
                                                } else {
                                                    this.mView.setAutoSizeTextTypeUniformWithPresetSizes(autoSizeTextAvailableSizes, 0);
                                                }
                                            }
                                        }
                                        obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.AppCompatTextView);
                                        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableLeftCompat, -1);
                                        AppCompatDrawableManager appCompatDrawableManager3 = appCompatDrawableManager;
                                        Drawable drawable = resourceId2 == -1 ? appCompatDrawableManager3.getDrawable(context, resourceId2) : null;
                                        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableTopCompat, -1);
                                        Drawable drawable2 = resourceId3 == -1 ? appCompatDrawableManager3.getDrawable(context, resourceId3) : null;
                                        int resourceId4 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableRightCompat, -1);
                                        Drawable drawable3 = resourceId4 == -1 ? appCompatDrawableManager3.getDrawable(context, resourceId4) : null;
                                        int resourceId5 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableBottomCompat, -1);
                                        Drawable drawable4 = resourceId5 == -1 ? appCompatDrawableManager3.getDrawable(context, resourceId5) : null;
                                        int resourceId6 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableStartCompat, -1);
                                        Drawable drawable5 = resourceId6 == -1 ? appCompatDrawableManager3.getDrawable(context, resourceId6) : null;
                                        int resourceId7 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableEndCompat, -1);
                                        setCompoundDrawables(drawable, drawable2, drawable3, drawable4, drawable5, resourceId7 == -1 ? appCompatDrawableManager3.getDrawable(context, resourceId7) : null);
                                        i5 = R.styleable.AppCompatTextView_drawableTint;
                                        if (obtainStyledAttributes.hasValue(i5)) {
                                            TextViewCompat.setCompoundDrawableTintList(this.mView, obtainStyledAttributes.getColorStateList(i5));
                                        }
                                        i6 = R.styleable.AppCompatTextView_drawableTintMode;
                                        if (obtainStyledAttributes.hasValue(i6)) {
                                            i7 = -1;
                                        } else {
                                            i7 = -1;
                                            TextViewCompat.setCompoundDrawableTintMode(this.mView, DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(i6, -1), null));
                                        }
                                        dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, i7);
                                        dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, i7);
                                        dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, i7);
                                        obtainStyledAttributes.recycle();
                                        if (dimensionPixelSize != i7) {
                                            TextViewCompat.setFirstBaselineToTopHeight(this.mView, dimensionPixelSize);
                                        }
                                        if (dimensionPixelSize2 != i7) {
                                            TextViewCompat.setLastBaselineToBottomHeight(this.mView, dimensionPixelSize2);
                                        }
                                        if (dimensionPixelSize3 == i7) {
                                            TextViewCompat.setLineHeight(this.mView, dimensionPixelSize3);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                appCompatDrawableManager = appCompatDrawableManager2;
                                updateTypefaceAndStyle(context, obtainStyledAttributes4);
                                obtainStyledAttributes4.recycle();
                                if (colorStateList != null) {
                                }
                                if (colorStateList2 != null) {
                                }
                                if (colorStateList3 != null) {
                                }
                                if (!z3) {
                                    setAllCaps(z);
                                }
                                typeface = this.mFontTypeface;
                                if (typeface != null) {
                                }
                                if (str4 != null) {
                                }
                                if (str2 != null) {
                                }
                                this.mAutoSizeTextHelper.loadFromAttributes(attributeSet, i2);
                                if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
                                    autoSizeTextAvailableSizes = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
                                    if (autoSizeTextAvailableSizes.length > 0) {
                                    }
                                }
                                obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.AppCompatTextView);
                                int resourceId22 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableLeftCompat, -1);
                                AppCompatDrawableManager appCompatDrawableManager32 = appCompatDrawableManager;
                                if (resourceId22 == -1) {
                                }
                                int resourceId32 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableTopCompat, -1);
                                if (resourceId32 == -1) {
                                }
                                int resourceId42 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableRightCompat, -1);
                                if (resourceId42 == -1) {
                                }
                                int resourceId52 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableBottomCompat, -1);
                                if (resourceId52 == -1) {
                                }
                                int resourceId62 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableStartCompat, -1);
                                if (resourceId62 == -1) {
                                }
                                int resourceId72 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableEndCompat, -1);
                                setCompoundDrawables(drawable, drawable2, drawable3, drawable4, drawable5, resourceId72 == -1 ? appCompatDrawableManager32.getDrawable(context, resourceId72) : null);
                                i5 = R.styleable.AppCompatTextView_drawableTint;
                                if (obtainStyledAttributes.hasValue(i5)) {
                                }
                                i6 = R.styleable.AppCompatTextView_drawableTintMode;
                                if (obtainStyledAttributes.hasValue(i6)) {
                                }
                                dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, i7);
                                dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, i7);
                                dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, i7);
                                obtainStyledAttributes.recycle();
                                if (dimensionPixelSize != i7) {
                                }
                                if (dimensionPixelSize2 != i7) {
                                }
                                if (dimensionPixelSize3 == i7) {
                                }
                            }
                        }
                        str4 = str3;
                        if (i12 >= 28) {
                        }
                        appCompatDrawableManager = appCompatDrawableManager2;
                        updateTypefaceAndStyle(context, obtainStyledAttributes4);
                        obtainStyledAttributes4.recycle();
                        if (colorStateList != null) {
                        }
                        if (colorStateList2 != null) {
                        }
                        if (colorStateList3 != null) {
                        }
                        if (!z3) {
                        }
                        typeface = this.mFontTypeface;
                        if (typeface != null) {
                        }
                        if (str4 != null) {
                        }
                        if (str2 != null) {
                        }
                        this.mAutoSizeTextHelper.loadFromAttributes(attributeSet, i2);
                        if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
                        }
                        obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.AppCompatTextView);
                        int resourceId222 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableLeftCompat, -1);
                        AppCompatDrawableManager appCompatDrawableManager322 = appCompatDrawableManager;
                        if (resourceId222 == -1) {
                        }
                        int resourceId322 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableTopCompat, -1);
                        if (resourceId322 == -1) {
                        }
                        int resourceId422 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableRightCompat, -1);
                        if (resourceId422 == -1) {
                        }
                        int resourceId522 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableBottomCompat, -1);
                        if (resourceId522 == -1) {
                        }
                        int resourceId622 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableStartCompat, -1);
                        if (resourceId622 == -1) {
                        }
                        int resourceId722 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableEndCompat, -1);
                        setCompoundDrawables(drawable, drawable2, drawable3, drawable4, drawable5, resourceId722 == -1 ? appCompatDrawableManager322.getDrawable(context, resourceId722) : null);
                        i5 = R.styleable.AppCompatTextView_drawableTint;
                        if (obtainStyledAttributes.hasValue(i5)) {
                        }
                        i6 = R.styleable.AppCompatTextView_drawableTintMode;
                        if (obtainStyledAttributes.hasValue(i6)) {
                        }
                        dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, i7);
                        dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, i7);
                        dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, i7);
                        obtainStyledAttributes.recycle();
                        if (dimensionPixelSize != i7) {
                        }
                        if (dimensionPixelSize2 != i7) {
                        }
                        if (dimensionPixelSize3 == i7) {
                        }
                    }
                }
                str3 = str;
                i3 = 23;
                if (i12 < i3) {
                }
                i4 = R.styleable.TextAppearance_textLocale;
                if (obtainStyledAttributes4.hasValue(i4)) {
                }
                if (i12 >= 26) {
                }
                str4 = str3;
                if (i12 >= 28) {
                }
                appCompatDrawableManager = appCompatDrawableManager2;
                updateTypefaceAndStyle(context, obtainStyledAttributes4);
                obtainStyledAttributes4.recycle();
                if (colorStateList != null) {
                }
                if (colorStateList2 != null) {
                }
                if (colorStateList3 != null) {
                }
                if (!z3) {
                }
                typeface = this.mFontTypeface;
                if (typeface != null) {
                }
                if (str4 != null) {
                }
                if (str2 != null) {
                }
                this.mAutoSizeTextHelper.loadFromAttributes(attributeSet, i2);
                if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE) {
                }
                obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.AppCompatTextView);
                int resourceId2222 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableLeftCompat, -1);
                AppCompatDrawableManager appCompatDrawableManager3222 = appCompatDrawableManager;
                if (resourceId2222 == -1) {
                }
                int resourceId3222 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableTopCompat, -1);
                if (resourceId3222 == -1) {
                }
                int resourceId4222 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableRightCompat, -1);
                if (resourceId4222 == -1) {
                }
                int resourceId5222 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableBottomCompat, -1);
                if (resourceId5222 == -1) {
                }
                int resourceId6222 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableStartCompat, -1);
                if (resourceId6222 == -1) {
                }
                int resourceId7222 = obtainStyledAttributes.getResourceId(R.styleable.AppCompatTextView_drawableEndCompat, -1);
                setCompoundDrawables(drawable, drawable2, drawable3, drawable4, drawable5, resourceId7222 == -1 ? appCompatDrawableManager3222.getDrawable(context, resourceId7222) : null);
                i5 = R.styleable.AppCompatTextView_drawableTint;
                if (obtainStyledAttributes.hasValue(i5)) {
                }
                i6 = R.styleable.AppCompatTextView_drawableTintMode;
                if (obtainStyledAttributes.hasValue(i6)) {
                }
                dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, i7);
                dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, i7);
                dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, i7);
                obtainStyledAttributes.recycle();
                if (dimensionPixelSize != i7) {
                }
                if (dimensionPixelSize2 != i7) {
                }
                if (dimensionPixelSize3 == i7) {
                }
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
