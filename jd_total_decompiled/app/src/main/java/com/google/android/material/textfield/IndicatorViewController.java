package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.legacy.widget.Space;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public final class IndicatorViewController {
    private static final int CAPTION_OPACITY_FADE_ANIMATION_DURATION = 167;
    private static final int CAPTION_STATE_ERROR = 1;
    private static final int CAPTION_STATE_HELPER_TEXT = 2;
    private static final int CAPTION_STATE_NONE = 0;
    private static final int CAPTION_TRANSLATE_Y_ANIMATION_DURATION = 217;
    static final int COUNTER_INDEX = 2;
    static final int ERROR_INDEX = 0;
    static final int HELPER_INDEX = 1;
    @Nullable
    private Animator captionAnimator;
    private FrameLayout captionArea;
    private int captionDisplayed;
    private int captionToShow;
    private final float captionTranslationYPx;
    private int captionViewsAdded;
    private final Context context;
    private boolean errorEnabled;
    private CharSequence errorText;
    private int errorTextAppearance;
    private TextView errorView;
    private CharSequence helperText;
    private boolean helperTextEnabled;
    private int helperTextTextAppearance;
    private TextView helperTextView;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    private final TextInputLayout textInputView;
    private Typeface typeface;

    public IndicatorViewController(TextInputLayout textInputLayout) {
        this.context = textInputLayout.getContext();
        this.textInputView = textInputLayout;
        this.captionTranslationYPx = r0.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
    }

    private boolean canAdjustIndicatorPadding() {
        return (this.indicatorArea == null || this.textInputView.getEditText() == null) ? false : true;
    }

    private void createCaptionAnimators(List<Animator> list, boolean z, TextView textView, int i2, int i3, int i4) {
        if (textView == null || !z) {
            return;
        }
        if (i2 == i4 || i2 == i3) {
            list.add(createCaptionOpacityAnimator(textView, i4 == i2));
            if (i4 == i2) {
                list.add(createCaptionTranslationYAnimator(textView));
            }
        }
    }

    private ObjectAnimator createCaptionOpacityAnimator(TextView textView, boolean z) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.ALPHA, z ? 1.0f : 0.0f);
        ofFloat.setDuration(167L);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return ofFloat;
    }

    private ObjectAnimator createCaptionTranslationYAnimator(TextView textView) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, View.TRANSLATION_Y, -this.captionTranslationYPx, 0.0f);
        ofFloat.setDuration(217L);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        return ofFloat;
    }

    @Nullable
    private TextView getCaptionViewFromDisplayState(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                return null;
            }
            return this.helperTextView;
        }
        return this.errorView;
    }

    private boolean isCaptionStateError(int i2) {
        return (i2 != 1 || this.errorView == null || TextUtils.isEmpty(this.errorText)) ? false : true;
    }

    private boolean isCaptionStateHelperText(int i2) {
        return (i2 != 2 || this.helperTextView == null || TextUtils.isEmpty(this.helperText)) ? false : true;
    }

    private void setCaptionViewVisibilities(int i2, int i3) {
        TextView captionViewFromDisplayState;
        TextView captionViewFromDisplayState2;
        if (i2 == i3) {
            return;
        }
        if (i3 != 0 && (captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i3)) != null) {
            captionViewFromDisplayState2.setVisibility(0);
            captionViewFromDisplayState2.setAlpha(1.0f);
        }
        if (i2 != 0 && (captionViewFromDisplayState = getCaptionViewFromDisplayState(i2)) != null) {
            captionViewFromDisplayState.setVisibility(4);
            if (i2 == 1) {
                captionViewFromDisplayState.setText((CharSequence) null);
            }
        }
        this.captionDisplayed = i3;
    }

    private void setTextViewTypeface(@Nullable TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void setViewGroupGoneIfEmpty(ViewGroup viewGroup, int i2) {
        if (i2 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean shouldAnimateCaptionView(TextView textView, @Nullable CharSequence charSequence) {
        return ViewCompat.isLaidOut(this.textInputView) && this.textInputView.isEnabled() && !(this.captionToShow == this.captionDisplayed && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void updateCaptionViewsVisibility(final int i2, final int i3, boolean z) {
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.captionAnimator = animatorSet;
            ArrayList arrayList = new ArrayList();
            createCaptionAnimators(arrayList, this.helperTextEnabled, this.helperTextView, 2, i2, i3);
            createCaptionAnimators(arrayList, this.errorEnabled, this.errorView, 1, i2, i3);
            AnimatorSetCompat.playTogether(animatorSet, arrayList);
            getCaptionViewFromDisplayState(i2);
            getCaptionViewFromDisplayState(i3);
            animatorSet.addListener(new AnimatorListenerAdapter
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0036: INVOKE 
                  (r7v0 'animatorSet' android.animation.AnimatorSet)
                  (wrap: android.animation.AnimatorListenerAdapter : 0x0033: CONSTRUCTOR 
                  (r9v0 'this' com.google.android.material.textfield.IndicatorViewController A[IMMUTABLE_TYPE, THIS])
                  (r11v0 'i3' int A[DONT_INLINE])
                  (r3 I:android.widget.TextView A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r10v0 'i2' int A[DONT_INLINE])
                  (r5 I:android.widget.TextView A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.google.android.material.textfield.IndicatorViewController, int, android.widget.TextView, int, android.widget.TextView):void (m), WRAPPED] (LINE:9) call: com.google.android.material.textfield.IndicatorViewController.1.<init>(com.google.android.material.textfield.IndicatorViewController, int, android.widget.TextView, int, android.widget.TextView):void type: CONSTRUCTOR)
                 type: VIRTUAL call: android.animation.AnimatorSet.addListener(android.animation.Animator$AnimatorListener):void A[MD:(android.animation.Animator$AnimatorListener):void (c)] (LINE:9) in method: com.google.android.material.textfield.IndicatorViewController.updateCaptionViewsVisibility(int, int, boolean):void, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                	... 21 more
                */
            /*
                this = this;
                if (r12 == 0) goto L3d
                android.animation.AnimatorSet r7 = new android.animation.AnimatorSet
                r7.<init>()
                r9.captionAnimator = r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                boolean r2 = r9.helperTextEnabled
                android.widget.TextView r3 = r9.helperTextView
                r4 = 2
                r0 = r9
                r1 = r8
                r5 = r10
                r6 = r11
                r0.createCaptionAnimators(r1, r2, r3, r4, r5, r6)
                boolean r2 = r9.errorEnabled
                android.widget.TextView r3 = r9.errorView
                r4 = 1
                r0.createCaptionAnimators(r1, r2, r3, r4, r5, r6)
                com.google.android.material.animation.AnimatorSetCompat.playTogether(r7, r8)
                android.widget.TextView r3 = r9.getCaptionViewFromDisplayState(r10)
                android.widget.TextView r5 = r9.getCaptionViewFromDisplayState(r11)
                com.google.android.material.textfield.IndicatorViewController$1 r6 = new com.google.android.material.textfield.IndicatorViewController$1
                r0 = r6
                r1 = r9
                r2 = r11
                r4 = r10
                r0.<init>()
                r7.addListener(r6)
                r7.start()
                goto L40
            L3d:
                r9.setCaptionViewVisibilities(r10, r11)
            L40:
                com.google.android.material.textfield.TextInputLayout r0 = r9.textInputView
                r0.updateEditTextBackground()
                com.google.android.material.textfield.TextInputLayout r0 = r9.textInputView
                r0.updateLabelState(r12)
                com.google.android.material.textfield.TextInputLayout r0 = r9.textInputView
                r0.updateTextInputBoxState()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.IndicatorViewController.updateCaptionViewsVisibility(int, int, boolean):void");
        }

        public void addIndicator(TextView textView, int i2) {
            if (this.indicatorArea == null && this.captionArea == null) {
                LinearLayout linearLayout = new LinearLayout(this.context);
                this.indicatorArea = linearLayout;
                linearLayout.setOrientation(0);
                this.textInputView.addView(this.indicatorArea, -1, -2);
                FrameLayout frameLayout = new FrameLayout(this.context);
                this.captionArea = frameLayout;
                this.indicatorArea.addView(frameLayout, -1, new FrameLayout.LayoutParams(-2, -2));
                this.indicatorArea.addView(new Space(this.context), new LinearLayout.LayoutParams(0, 0, 1.0f));
                if (this.textInputView.getEditText() != null) {
                    adjustIndicatorPadding();
                }
            }
            if (isCaptionView(i2)) {
                this.captionArea.setVisibility(0);
                this.captionArea.addView(textView);
                this.captionViewsAdded++;
            } else {
                this.indicatorArea.addView(textView, i2);
            }
            this.indicatorArea.setVisibility(0);
            this.indicatorsAdded++;
        }

        public void adjustIndicatorPadding() {
            if (canAdjustIndicatorPadding()) {
                ViewCompat.setPaddingRelative(this.indicatorArea, ViewCompat.getPaddingStart(this.textInputView.getEditText()), 0, ViewCompat.getPaddingEnd(this.textInputView.getEditText()), 0);
            }
        }

        void cancelCaptionAnimator() {
            Animator animator = this.captionAnimator;
            if (animator != null) {
                animator.cancel();
            }
        }

        boolean errorIsDisplayed() {
            return isCaptionStateError(this.captionDisplayed);
        }

        public boolean errorShouldBeShown() {
            return isCaptionStateError(this.captionToShow);
        }

        public CharSequence getErrorText() {
            return this.errorText;
        }

        @ColorInt
        public int getErrorViewCurrentTextColor() {
            TextView textView = this.errorView;
            if (textView != null) {
                return textView.getCurrentTextColor();
            }
            return -1;
        }

        @Nullable
        public ColorStateList getErrorViewTextColors() {
            TextView textView = this.errorView;
            if (textView != null) {
                return textView.getTextColors();
            }
            return null;
        }

        public CharSequence getHelperText() {
            return this.helperText;
        }

        @Nullable
        ColorStateList getHelperTextViewColors() {
            TextView textView = this.helperTextView;
            if (textView != null) {
                return textView.getTextColors();
            }
            return null;
        }

        @ColorInt
        public int getHelperTextViewCurrentTextColor() {
            TextView textView = this.helperTextView;
            if (textView != null) {
                return textView.getCurrentTextColor();
            }
            return -1;
        }

        public boolean helperTextIsDisplayed() {
            return isCaptionStateHelperText(this.captionDisplayed);
        }

        boolean helperTextShouldBeShown() {
            return isCaptionStateHelperText(this.captionToShow);
        }

        public void hideError() {
            this.errorText = null;
            cancelCaptionAnimator();
            if (this.captionDisplayed == 1) {
                if (this.helperTextEnabled && !TextUtils.isEmpty(this.helperText)) {
                    this.captionToShow = 2;
                } else {
                    this.captionToShow = 0;
                }
            }
            updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, null));
        }

        void hideHelperText() {
            cancelCaptionAnimator();
            int i2 = this.captionDisplayed;
            if (i2 == 2) {
                this.captionToShow = 0;
            }
            updateCaptionViewsVisibility(i2, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, null));
        }

        boolean isCaptionView(int i2) {
            return i2 == 0 || i2 == 1;
        }

        public boolean isErrorEnabled() {
            return this.errorEnabled;
        }

        public boolean isHelperTextEnabled() {
            return this.helperTextEnabled;
        }

        public void removeIndicator(TextView textView, int i2) {
            FrameLayout frameLayout;
            if (this.indicatorArea == null) {
                return;
            }
            if (isCaptionView(i2) && (frameLayout = this.captionArea) != null) {
                int i3 = this.captionViewsAdded - 1;
                this.captionViewsAdded = i3;
                setViewGroupGoneIfEmpty(frameLayout, i3);
                this.captionArea.removeView(textView);
            } else {
                this.indicatorArea.removeView(textView);
            }
            int i4 = this.indicatorsAdded - 1;
            this.indicatorsAdded = i4;
            setViewGroupGoneIfEmpty(this.indicatorArea, i4);
        }

        public void setErrorEnabled(boolean z) {
            if (this.errorEnabled == z) {
                return;
            }
            cancelCaptionAnimator();
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(this.context);
                this.errorView = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_error);
                Typeface typeface = this.typeface;
                if (typeface != null) {
                    this.errorView.setTypeface(typeface);
                }
                setErrorTextAppearance(this.errorTextAppearance);
                this.errorView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(this.errorView, 1);
                addIndicator(this.errorView, 0);
            } else {
                hideError();
                removeIndicator(this.errorView, 0);
                this.errorView = null;
                this.textInputView.updateEditTextBackground();
                this.textInputView.updateTextInputBoxState();
            }
            this.errorEnabled = z;
        }

        public void setErrorTextAppearance(@StyleRes int i2) {
            this.errorTextAppearance = i2;
            TextView textView = this.errorView;
            if (textView != null) {
                this.textInputView.setTextAppearanceCompatWithErrorFallback(textView, i2);
            }
        }

        public void setErrorViewTextColor(@Nullable ColorStateList colorStateList) {
            TextView textView = this.errorView;
            if (textView != null) {
                textView.setTextColor(colorStateList);
            }
        }

        public void setHelperTextAppearance(@StyleRes int i2) {
            this.helperTextTextAppearance = i2;
            TextView textView = this.helperTextView;
            if (textView != null) {
                TextViewCompat.setTextAppearance(textView, i2);
            }
        }

        public void setHelperTextEnabled(boolean z) {
            if (this.helperTextEnabled == z) {
                return;
            }
            cancelCaptionAnimator();
            if (z) {
                AppCompatTextView appCompatTextView = new AppCompatTextView(this.context);
                this.helperTextView = appCompatTextView;
                appCompatTextView.setId(R.id.textinput_helper_text);
                Typeface typeface = this.typeface;
                if (typeface != null) {
                    this.helperTextView.setTypeface(typeface);
                }
                this.helperTextView.setVisibility(4);
                ViewCompat.setAccessibilityLiveRegion(this.helperTextView, 1);
                setHelperTextAppearance(this.helperTextTextAppearance);
                addIndicator(this.helperTextView, 1);
            } else {
                hideHelperText();
                removeIndicator(this.helperTextView, 1);
                this.helperTextView = null;
                this.textInputView.updateEditTextBackground();
                this.textInputView.updateTextInputBoxState();
            }
            this.helperTextEnabled = z;
        }

        public void setHelperTextViewTextColor(@Nullable ColorStateList colorStateList) {
            TextView textView = this.helperTextView;
            if (textView != null) {
                textView.setTextColor(colorStateList);
            }
        }

        public void setTypefaces(Typeface typeface) {
            if (typeface != this.typeface) {
                this.typeface = typeface;
                setTextViewTypeface(this.errorView, typeface);
                setTextViewTypeface(this.helperTextView, typeface);
            }
        }

        public void showError(CharSequence charSequence) {
            cancelCaptionAnimator();
            this.errorText = charSequence;
            this.errorView.setText(charSequence);
            int i2 = this.captionDisplayed;
            if (i2 != 1) {
                this.captionToShow = 1;
            }
            updateCaptionViewsVisibility(i2, this.captionToShow, shouldAnimateCaptionView(this.errorView, charSequence));
        }

        public void showHelper(CharSequence charSequence) {
            cancelCaptionAnimator();
            this.helperText = charSequence;
            this.helperTextView.setText(charSequence);
            int i2 = this.captionDisplayed;
            if (i2 != 2) {
                this.captionToShow = 2;
            }
            updateCaptionViewsVisibility(i2, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, charSequence));
        }
    }
