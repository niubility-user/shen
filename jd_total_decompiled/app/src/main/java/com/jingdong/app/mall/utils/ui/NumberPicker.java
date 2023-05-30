package com.jingdong.app.mall.utils.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.common.widget.custom.pageload.FloorType;
import java.text.DecimalFormatSymbols;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes4.dex */
public class NumberPicker extends LinearLayout {
    private static final int DEFAULT_LAYOUT_RESOURCE_ID = 0;
    private static final long DEFAULT_LONG_PRESS_UPDATE_INTERVAL = 300;
    private static final int SELECTOR_ADJUSTMENT_DURATION_MILLIS = 800;
    private static final int SELECTOR_MAX_FLING_VELOCITY_ADJUSTMENT = 8;
    private static final int SELECTOR_MIDDLE_ITEM_INDEX = 1;
    private static final int SELECTOR_WHEEL_ITEM_COUNT = 3;
    private static final int SIZE_UNSPECIFIED = -1;
    private static final int SNAP_SCROLL_DURATION = 300;
    private static final float TOP_AND_BOTTOM_FADING_EDGE_STRENGTH = 0.9f;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDERS_DISTANCE = 48;
    private static final int UNSCALED_DEFAULT_SELECTION_DIVIDER_HEIGHT = 2;
    private final Scroller mAdjustScroller;
    private d mBeginSoftInputOnLongPressCommand;
    private int mBottomSelectionDividerBottom;
    private e mChangeCurrentByOneFromLongPressCommand;
    private final boolean mComputeMaxWidth;
    private int mCurrentScrollOffset;
    private final ImageButton mDecrementButton;
    private boolean mDecrementVirtualButtonPressed;
    private String[] mDisplayedValues;
    private final Scroller mFlingScroller;
    private f mFormatter;
    private final boolean mHasSelectorWheel;
    private final ImageButton mIncrementButton;
    private boolean mIncrementVirtualButtonPressed;
    private boolean mIngonreMoveEvents;
    private int mInitialScrollOffset;
    private final EditText mInputText;
    private float mLastDownEventY;
    private float mLastDownOrMoveEventY;
    private int mLastHandledDownDpadKeyCode;
    private long mLongPressUpdateInterval;
    private final int mMaxHeight;
    private int mMaxValue;
    private int mMaxWidth;
    private int mMaximumFlingVelocity;
    private final int mMinHeight;
    private int mMinValue;
    private final int mMinWidth;
    private int mMinimumFlingVelocity;
    private h mOnScrollListener;
    private i mOnValueChangeListener;
    private final j mPressedStateHelper;
    private int mPreviousScrollerY;
    private int mScrollState;
    private final Drawable mSelectionDivider;
    private final int mSelectionDividerHeight;
    private final int mSelectionDividersDistance;
    private int mSelectorElementHeight;
    private final SparseArray<String> mSelectorIndexToStringCache;
    private final int[] mSelectorIndices;
    private int mSelectorTextGapHeight;
    private final Paint mSelectorWheelPaint;
    private k mSetSelectionCommand;
    private boolean mShowSoftInputOnTap;
    private final int mSolidColor;
    private final int mTextSize;
    private int mTopSelectionDividerTop;
    private int mTouchSlop;
    private int mValue;
    private VelocityTracker mVelocityTracker;
    private final Drawable mVirtualButtonPressedDrawable;
    private boolean mWrapSelectorWheel;
    private static final l sTwoDigitFormatter = new l();
    private static final char[] DIGIT_CHARACTERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '\u0660', '\u0661', '\u0662', '\u0663', '\u0664', '\u0665', '\u0666', '\u0667', '\u0668', '\u0669', '\u06f0', '\u06f1', '\u06f2', '\u06f3', '\u06f4', '\u06f5', '\u06f6', '\u06f7', '\u06f8', '\u06f9'};

    /* loaded from: classes4.dex */
    public static class CustomEditText extends EditText {
        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.widget.TextView
        public void onEditorAction(int i2) {
            super.onEditorAction(i2);
            if (i2 == 6) {
                clearFocus();
            }
        }
    }

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NumberPicker.this.hideSoftInput();
            NumberPicker.this.mInputText.clearFocus();
            if (view.getId() == R.id.j4) {
                NumberPicker.this.changeValueByOne(true);
            } else {
                NumberPicker.this.changeValueByOne(false);
            }
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnLongClickListener {
        b() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            NumberPicker.this.hideSoftInput();
            NumberPicker.this.mInputText.clearFocus();
            if (view.getId() == R.id.j4) {
                NumberPicker.this.postChangeCurrentByOneFromLongPress(true, 0L);
            } else {
                NumberPicker.this.postChangeCurrentByOneFromLongPress(false, 0L);
            }
            return true;
        }
    }

    /* loaded from: classes4.dex */
    class c implements View.OnFocusChangeListener {
        c() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z) {
                NumberPicker.this.mInputText.selectAll();
                return;
            }
            NumberPicker.this.mInputText.setSelection(0, 0);
            NumberPicker.this.validateInputTextView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.showSoftInput();
            NumberPicker.this.mIngonreMoveEvents = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private boolean f11884g;

        e() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(boolean z) {
            this.f11884g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.changeValueByOne(this.f11884g);
            NumberPicker numberPicker = NumberPicker.this;
            numberPicker.postDelayed(this, numberPicker.mLongPressUpdateInterval);
        }
    }

    /* loaded from: classes4.dex */
    public interface f {
        String format(int i2);
    }

    /* loaded from: classes4.dex */
    class g extends NumberKeyListener {
        g() {
        }

        @Override // android.text.method.NumberKeyListener, android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
            if (NumberPicker.this.mDisplayedValues == null) {
                CharSequence filter = super.filter(charSequence, i2, i3, spanned, i4, i5);
                if (filter == null) {
                    filter = charSequence.subSequence(i2, i3);
                }
                String str = String.valueOf(spanned.subSequence(0, i4)) + ((Object) filter) + ((Object) spanned.subSequence(i5, spanned.length()));
                return "".equals(str) ? str : NumberPicker.this.getSelectedPos(str) > NumberPicker.this.mMaxValue ? "" : filter;
            }
            String valueOf = String.valueOf(charSequence.subSequence(i2, i3));
            if (TextUtils.isEmpty(valueOf)) {
                return "";
            }
            String str2 = String.valueOf(spanned.subSequence(0, i4)) + ((Object) valueOf) + ((Object) spanned.subSequence(i5, spanned.length()));
            String lowerCase = String.valueOf(str2).toLowerCase();
            for (String str3 : NumberPicker.this.mDisplayedValues) {
                if (str3.toLowerCase().startsWith(lowerCase)) {
                    NumberPicker.this.postSetSelectionCommand(str2.length(), str3.length());
                    return str3.subSequence(i4, str3.length());
                }
            }
            return "";
        }

        @Override // android.text.method.NumberKeyListener
        protected char[] getAcceptedChars() {
            return NumberPicker.DIGIT_CHARACTERS;
        }

        @Override // android.text.method.KeyListener
        public int getInputType() {
            return 1;
        }
    }

    /* loaded from: classes4.dex */
    public interface h {
        void a(NumberPicker numberPicker, int i2);
    }

    /* loaded from: classes4.dex */
    public interface i {
        void a(NumberPicker numberPicker, int i2, int i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class j implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private int f11887g;

        /* renamed from: h  reason: collision with root package name */
        private int f11888h;

        j() {
        }

        public void a(int i2) {
            c();
            this.f11888h = 1;
            this.f11887g = i2;
            NumberPicker.this.postDelayed(this, ViewConfiguration.getTapTimeout());
        }

        public void b(int i2) {
            c();
            this.f11888h = 2;
            this.f11887g = i2;
            NumberPicker.this.post(this);
        }

        public void c() {
            this.f11888h = 0;
            this.f11887g = 0;
            NumberPicker.this.removeCallbacks(this);
            if (NumberPicker.this.mIncrementVirtualButtonPressed) {
                NumberPicker.this.mIncrementVirtualButtonPressed = false;
                NumberPicker numberPicker = NumberPicker.this;
                numberPicker.invalidate(0, numberPicker.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
            }
            NumberPicker.this.mDecrementVirtualButtonPressed = false;
            if (NumberPicker.this.mDecrementVirtualButtonPressed) {
                NumberPicker numberPicker2 = NumberPicker.this;
                numberPicker2.invalidate(0, 0, numberPicker2.getRight(), NumberPicker.this.mTopSelectionDividerTop);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i2 = this.f11888h;
            if (i2 == 1) {
                int i3 = this.f11887g;
                if (i3 == 1) {
                    NumberPicker.this.mIncrementVirtualButtonPressed = true;
                    NumberPicker numberPicker = NumberPicker.this;
                    numberPicker.invalidate(0, numberPicker.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                } else if (i3 != 2) {
                } else {
                    NumberPicker.this.mDecrementVirtualButtonPressed = true;
                    NumberPicker numberPicker2 = NumberPicker.this;
                    numberPicker2.invalidate(0, 0, numberPicker2.getRight(), NumberPicker.this.mTopSelectionDividerTop);
                }
            } else if (i2 != 2) {
            } else {
                int i4 = this.f11887g;
                if (i4 == 1) {
                    if (!NumberPicker.this.mIncrementVirtualButtonPressed) {
                        NumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
                    }
                    NumberPicker.access$1380(NumberPicker.this, 1);
                    NumberPicker numberPicker3 = NumberPicker.this;
                    numberPicker3.invalidate(0, numberPicker3.mBottomSelectionDividerBottom, NumberPicker.this.getRight(), NumberPicker.this.getBottom());
                } else if (i4 != 2) {
                } else {
                    if (!NumberPicker.this.mDecrementVirtualButtonPressed) {
                        NumberPicker.this.postDelayed(this, ViewConfiguration.getPressedStateDuration());
                    }
                    NumberPicker.access$1580(NumberPicker.this, 1);
                    NumberPicker numberPicker4 = NumberPicker.this;
                    numberPicker4.invalidate(0, 0, numberPicker4.getRight(), NumberPicker.this.mTopSelectionDividerTop);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class k implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        private int f11890g;

        /* renamed from: h  reason: collision with root package name */
        private int f11891h;

        k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NumberPicker.this.mInputText.setSelection(this.f11890g, this.f11891h);
        }
    }

    /* loaded from: classes4.dex */
    private static class l implements f {
        char b;

        /* renamed from: c  reason: collision with root package name */
        Formatter f11893c;
        final StringBuilder a = new StringBuilder();
        final Object[] d = new Object[1];

        l() {
            c(Locale.getDefault());
        }

        private Formatter a(Locale locale) {
            return new Formatter(this.a, locale);
        }

        private static char b(Locale locale) {
            return new DecimalFormatSymbols(locale).getZeroDigit();
        }

        private void c(Locale locale) {
            this.f11893c = a(locale);
            this.b = b(locale);
        }

        @Override // com.jingdong.app.mall.utils.ui.NumberPicker.f
        public String format(int i2) {
            Locale locale = Locale.getDefault();
            if (this.b != b(locale)) {
                c(locale);
            }
            this.d[0] = Integer.valueOf(i2);
            StringBuilder sb = this.a;
            sb.delete(0, sb.length());
            this.f11893c.format(JDDateTimePickerDialog.TWO_DIGIT_FORMAT, this.d);
            return this.f11893c.toString();
        }
    }

    public NumberPicker(Context context) {
        this(context, null);
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [byte, boolean] */
    static /* synthetic */ boolean access$1380(NumberPicker numberPicker, int i2) {
        ?? r2 = (byte) (i2 ^ (numberPicker.mIncrementVirtualButtonPressed ? 1 : 0));
        numberPicker.mIncrementVirtualButtonPressed = r2;
        return r2;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [byte, boolean] */
    static /* synthetic */ boolean access$1580(NumberPicker numberPicker, int i2) {
        ?? r2 = (byte) (i2 ^ (numberPicker.mDecrementVirtualButtonPressed ? 1 : 0));
        numberPicker.mDecrementVirtualButtonPressed = r2;
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeValueByOne(boolean z) {
        if (!this.mHasSelectorWheel) {
            if (z) {
                setValueInternal(this.mValue + 1, true);
                return;
            } else {
                setValueInternal(this.mValue - 1, true);
                return;
            }
        }
        this.mInputText.setVisibility(4);
        if (!moveToFinalScrollerPosition(this.mFlingScroller)) {
            moveToFinalScrollerPosition(this.mAdjustScroller);
        }
        this.mPreviousScrollerY = 0;
        if (z) {
            this.mFlingScroller.startScroll(0, 0, 0, -this.mSelectorElementHeight, 300);
        } else {
            this.mFlingScroller.startScroll(0, 0, 0, this.mSelectorElementHeight, 300);
        }
        invalidate();
    }

    private void decrementSelectorIndices(int[] iArr) {
        for (int length = iArr.length - 1; length > 0; length--) {
            iArr[length] = iArr[length - 1];
        }
        int i2 = iArr[1] - 1;
        if (this.mWrapSelectorWheel && i2 < this.mMinValue) {
            i2 = this.mMaxValue;
        }
        iArr[0] = i2;
        ensureCachedScrollSelectorValue(i2);
    }

    private void ensureCachedScrollSelectorValue(int i2) {
        String str;
        SparseArray<String> sparseArray = this.mSelectorIndexToStringCache;
        if (sparseArray.get(i2) != null) {
            return;
        }
        int i3 = this.mMinValue;
        if (i2 < i3 || i2 > this.mMaxValue) {
            str = "";
        } else {
            String[] strArr = this.mDisplayedValues;
            if (strArr != null) {
                str = strArr[i2 - i3];
            } else {
                str = formatNumber(i2);
            }
        }
        sparseArray.put(i2, str);
    }

    private boolean ensureScrollWheelAdjusted() {
        int i2 = this.mInitialScrollOffset - this.mCurrentScrollOffset;
        if (i2 != 0) {
            this.mPreviousScrollerY = 0;
            int abs = Math.abs(i2);
            int i3 = this.mSelectorElementHeight;
            if (abs > (i3 >> 1)) {
                if (i2 > 0) {
                    i3 = -i3;
                }
                i2 += i3;
            }
            this.mAdjustScroller.startScroll(0, 0, 0, i2, 800);
            invalidate();
            return true;
        }
        return false;
    }

    private void fling(int i2) {
        this.mPreviousScrollerY = 0;
        if (i2 > 0) {
            this.mFlingScroller.fling(0, 0, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        } else {
            this.mFlingScroller.fling(0, Integer.MAX_VALUE, 0, i2, 0, 0, 0, Integer.MAX_VALUE);
        }
        invalidate();
    }

    private String formatNumber(int i2) {
        f fVar = this.mFormatter;
        return fVar != null ? fVar.format(i2) : formatNumberWithLocale(i2);
    }

    private static String formatNumberWithLocale(int i2) {
        return String.format(Locale.getDefault(), "%d", Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSelectedPos(String str) {
        try {
            if (this.mDisplayedValues == null) {
                return Integer.parseInt(str);
            }
            for (int i2 = 0; i2 < this.mDisplayedValues.length; i2++) {
                str = str.toLowerCase();
                if (this.mDisplayedValues[i2].toLowerCase().startsWith(str)) {
                    return this.mMinValue + i2;
                }
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return this.mMinValue;
        }
    }

    public static final f getTwoDigitFormatter() {
        return sTwoDigitFormatter;
    }

    private int getWrappedSelectorIndex(int i2) {
        int i3 = this.mMaxValue;
        if (i2 > i3) {
            int i4 = this.mMinValue;
            return (i4 + ((i2 - i3) % (i3 - i4))) - 1;
        }
        int i5 = this.mMinValue;
        return i2 < i5 ? (i3 - ((i5 - i2) % (i3 - i5))) + 1 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager == null || !inputMethodManager.isActive(this.mInputText)) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        if (this.mHasSelectorWheel) {
            this.mInputText.setVisibility(4);
        }
    }

    private void incrementSelectorIndices(int[] iArr) {
        int i2 = 0;
        while (i2 < iArr.length - 1) {
            int i3 = i2 + 1;
            iArr[i2] = iArr[i3];
            i2 = i3;
        }
        int i4 = iArr[iArr.length - 2] + 1;
        if (this.mWrapSelectorWheel && i4 > this.mMaxValue) {
            i4 = this.mMinValue;
        }
        iArr[iArr.length - 1] = i4;
        ensureCachedScrollSelectorValue(i4);
    }

    private void initializeFadingEdges() {
        setVerticalFadingEdgeEnabled(true);
        setFadingEdgeLength(((getBottom() - getTop()) - this.mTextSize) >> 1);
    }

    private void initializeSelectorWheel() {
        initializeSelectorWheelIndices();
        int[] iArr = this.mSelectorIndices;
        int bottom = (int) ((((getBottom() - getTop()) - (iArr.length * this.mTextSize)) / iArr.length) + 0.5f);
        this.mSelectorTextGapHeight = bottom;
        this.mSelectorElementHeight = this.mTextSize + bottom;
        int baseline = (this.mInputText.getBaseline() + this.mInputText.getTop()) - (this.mSelectorElementHeight * 1);
        this.mInitialScrollOffset = baseline;
        this.mCurrentScrollOffset = baseline;
        updateInputTextView();
    }

    private void initializeSelectorWheelIndices() {
        this.mSelectorIndexToStringCache.clear();
        int[] iArr = this.mSelectorIndices;
        int value = getValue();
        for (int i2 = 0; i2 < this.mSelectorIndices.length; i2++) {
            int i3 = (i2 - 1) + value;
            if (this.mWrapSelectorWheel) {
                i3 = getWrappedSelectorIndex(i3);
            }
            iArr[i2] = i3;
            ensureCachedScrollSelectorValue(iArr[i2]);
        }
    }

    private int makeMeasureSpec(int i2, int i3) {
        if (i3 == -1) {
            return i2;
        }
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != Integer.MIN_VALUE) {
            if (mode != 0) {
                if (mode == 1073741824) {
                    return i2;
                }
                throw new IllegalArgumentException("Unknown measure mode: " + mode);
            }
            return View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(size, i3), 1073741824);
    }

    private boolean moveToFinalScrollerPosition(Scroller scroller) {
        scroller.forceFinished(true);
        int finalY = scroller.getFinalY() - scroller.getCurrY();
        int i2 = this.mInitialScrollOffset - ((this.mCurrentScrollOffset + finalY) % this.mSelectorElementHeight);
        if (i2 != 0) {
            int abs = Math.abs(i2);
            int i3 = this.mSelectorElementHeight;
            if (abs > (i3 >> 1)) {
                i2 = i2 > 0 ? i2 - i3 : i2 + i3;
            }
            scrollBy(0, finalY + i2);
            return true;
        }
        return false;
    }

    private void notifyChange(int i2, int i3) {
        i iVar = this.mOnValueChangeListener;
        if (iVar != null) {
            iVar.a(this, i2, this.mValue);
        }
    }

    private void onScrollStateChange(int i2) {
        if (this.mScrollState == i2) {
            return;
        }
        this.mScrollState = i2;
        h hVar = this.mOnScrollListener;
        if (hVar != null) {
            hVar.a(this, i2);
        }
    }

    private void onScrollerFinished(Scroller scroller) {
        if (scroller == this.mFlingScroller) {
            if (!ensureScrollWheelAdjusted()) {
                updateInputTextView();
            }
            onScrollStateChange(0);
        } else if (this.mScrollState != 1) {
            updateInputTextView();
        }
    }

    private void postBeginSoftInputOnLongPressCommand() {
        d dVar = this.mBeginSoftInputOnLongPressCommand;
        if (dVar == null) {
            this.mBeginSoftInputOnLongPressCommand = new d();
        } else {
            removeCallbacks(dVar);
        }
        postDelayed(this.mBeginSoftInputOnLongPressCommand, ViewConfiguration.getLongPressTimeout());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postChangeCurrentByOneFromLongPress(boolean z, long j2) {
        e eVar = this.mChangeCurrentByOneFromLongPressCommand;
        if (eVar == null) {
            this.mChangeCurrentByOneFromLongPressCommand = new e();
        } else {
            removeCallbacks(eVar);
        }
        this.mChangeCurrentByOneFromLongPressCommand.b(z);
        postDelayed(this.mChangeCurrentByOneFromLongPressCommand, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postSetSelectionCommand(int i2, int i3) {
        k kVar = this.mSetSelectionCommand;
        if (kVar == null) {
            this.mSetSelectionCommand = new k();
        } else {
            removeCallbacks(kVar);
        }
        this.mSetSelectionCommand.f11890g = i2;
        this.mSetSelectionCommand.f11891h = i3;
        post(this.mSetSelectionCommand);
    }

    private void removeAllCallbacks() {
        e eVar = this.mChangeCurrentByOneFromLongPressCommand;
        if (eVar != null) {
            removeCallbacks(eVar);
        }
        k kVar = this.mSetSelectionCommand;
        if (kVar != null) {
            removeCallbacks(kVar);
        }
        d dVar = this.mBeginSoftInputOnLongPressCommand;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
        this.mPressedStateHelper.c();
    }

    private void removeBeginSoftInputCommand() {
        d dVar = this.mBeginSoftInputOnLongPressCommand;
        if (dVar != null) {
            removeCallbacks(dVar);
        }
    }

    private void removeChangeCurrentByOneFromLongPress() {
        e eVar = this.mChangeCurrentByOneFromLongPressCommand;
        if (eVar != null) {
            removeCallbacks(eVar);
        }
    }

    @SuppressLint({"Override"})
    public static int resolveSizeAndState(int i2, int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        if (mode != Integer.MIN_VALUE) {
            if (mode == 1073741824) {
                i2 = size;
            }
        } else if (size < i2) {
            i2 = 16777216 | size;
        }
        return i2 | ((-16777216) & i4);
    }

    @SuppressLint({"NewApi"})
    private int resolveSizeAndStateRespectingMinSize(int i2, int i3, int i4) {
        return i2 != -1 ? resolveSizeAndState(Math.max(i2, i3), i4, 0) : i3;
    }

    private void setValueInternal(int i2, boolean z) {
        int min;
        if (this.mValue == i2) {
            return;
        }
        if (this.mWrapSelectorWheel) {
            min = getWrappedSelectorIndex(i2);
        } else {
            min = Math.min(Math.max(i2, this.mMinValue), this.mMaxValue);
        }
        int i3 = this.mValue;
        this.mValue = min;
        updateInputTextView();
        if (z) {
            notifyChange(i3, min);
        }
        initializeSelectorWheelIndices();
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (this.mHasSelectorWheel) {
                this.mInputText.setVisibility(0);
            }
            this.mInputText.requestFocus();
            inputMethodManager.showSoftInput(this.mInputText, 0);
        }
    }

    private void tryComputeMaxWidth() {
        int i2;
        if (this.mComputeMaxWidth) {
            String[] strArr = this.mDisplayedValues;
            int i3 = 0;
            if (strArr == null) {
                float f2 = 0.0f;
                for (int i4 = 0; i4 <= 9; i4++) {
                    float measureText = this.mSelectorWheelPaint.measureText(formatNumberWithLocale(i4));
                    if (measureText > f2) {
                        f2 = measureText;
                    }
                }
                for (int i5 = this.mMaxValue; i5 > 0; i5 /= 10) {
                    i3++;
                }
                i2 = (int) (i3 * f2);
            } else {
                int length = strArr.length;
                int i6 = 0;
                while (i3 < length) {
                    float measureText2 = this.mSelectorWheelPaint.measureText(this.mDisplayedValues[i3]);
                    if (measureText2 > i6) {
                        i6 = (int) measureText2;
                    }
                    i3++;
                }
                i2 = i6;
            }
            int paddingLeft = i2 + this.mInputText.getPaddingLeft() + this.mInputText.getPaddingRight();
            if (this.mMaxWidth != paddingLeft) {
                int i7 = this.mMinWidth;
                if (paddingLeft > i7) {
                    this.mMaxWidth = paddingLeft;
                } else {
                    this.mMaxWidth = i7;
                }
                invalidate();
            }
        }
    }

    private boolean updateInputTextView() {
        String[] strArr = this.mDisplayedValues;
        String formatNumber = strArr == null ? formatNumber(this.mValue) : strArr[this.mValue - this.mMinValue];
        if (TextUtils.isEmpty(formatNumber) || formatNumber.equals(this.mInputText.getText().toString())) {
            return false;
        }
        this.mInputText.setText(formatNumber);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void validateInputTextView(View view) {
        String valueOf = String.valueOf(((TextView) view).getText());
        if (TextUtils.isEmpty(valueOf)) {
            updateInputTextView();
        } else {
            setValueInternal(getSelectedPos(valueOf.toString()), true);
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        Scroller scroller = this.mFlingScroller;
        if (scroller.isFinished()) {
            scroller = this.mAdjustScroller;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (this.mPreviousScrollerY == 0) {
            this.mPreviousScrollerY = scroller.getStartY();
        }
        scrollBy(0, currY - this.mPreviousScrollerY);
        this.mPreviousScrollerY = currY;
        if (scroller.isFinished()) {
            onScrollerFinished(scroller);
        } else {
            invalidate();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x004c, code lost:
        requestFocus();
        r5.mLastHandledDownDpadKeyCode = r0;
        removeAllCallbacks();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005a, code lost:
        if (r5.mFlingScroller.isFinished() == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005c, code lost:
        if (r0 != 20) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005e, code lost:
        r6 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0060, code lost:
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0061, code lost:
        changeValueByOne(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0064, code lost:
        return true;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19 || keyCode == 20) {
            if (this.mHasSelectorWheel) {
                int action = keyEvent.getAction();
                if (action != 0) {
                    if (action == 1 && this.mLastHandledDownDpadKeyCode == keyCode) {
                        this.mLastHandledDownDpadKeyCode = -1;
                        return true;
                    }
                } else if (!this.mWrapSelectorWheel) {
                }
            }
        } else if (keyCode == 23 || keyCode == 66) {
            removeAllCallbacks();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 1 || action == 3) {
            removeAllCallbacks();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 1 || action == 3) {
            removeAllCallbacks();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        return TOP_AND_BOTTOM_FADING_EDGE_STRENGTH;
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    @Override // android.view.View
    public int getSolidColor() {
        return this.mSolidColor;
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
        return TOP_AND_BOTTOM_FADING_EDGE_STRENGTH;
    }

    public int getValue() {
        return this.mValue;
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public void handleButtonOnclick(boolean z) {
        if (z) {
            changeValueByOne(true);
        } else {
            changeValueByOne(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        removeAllCallbacks();
        super.onDetachedFromWindow();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        if (!this.mHasSelectorWheel) {
            super.onDraw(canvas);
            return;
        }
        float right = (getRight() - getLeft()) >> 1;
        float f2 = this.mCurrentScrollOffset;
        Drawable drawable = this.mVirtualButtonPressedDrawable;
        if (drawable != null && this.mScrollState == 0) {
            if (this.mDecrementVirtualButtonPressed) {
                drawable.setState(LinearLayout.PRESSED_ENABLED_STATE_SET);
                this.mVirtualButtonPressedDrawable.setBounds(0, 0, getRight(), this.mTopSelectionDividerTop);
                this.mVirtualButtonPressedDrawable.draw(canvas);
            }
            if (this.mIncrementVirtualButtonPressed) {
                this.mVirtualButtonPressedDrawable.setState(LinearLayout.PRESSED_ENABLED_STATE_SET);
                this.mVirtualButtonPressedDrawable.setBounds(0, this.mBottomSelectionDividerBottom, getRight(), getBottom());
                this.mVirtualButtonPressedDrawable.draw(canvas);
            }
        }
        int[] iArr = this.mSelectorIndices;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            String str = this.mSelectorIndexToStringCache.get(iArr[i2]);
            if (i2 != 1 || this.mInputText.getVisibility() != 0) {
                canvas.drawText(str, right, f2, this.mSelectorWheelPaint);
            }
            f2 += this.mSelectorElementHeight;
        }
        Drawable drawable2 = this.mSelectionDivider;
        if (drawable2 != null) {
            int i3 = this.mTopSelectionDividerTop;
            drawable2.setBounds(0, i3, getRight(), this.mSelectionDividerHeight + i3);
            this.mSelectionDivider.draw(canvas);
            int i4 = this.mBottomSelectionDividerBottom;
            this.mSelectionDivider.setBounds(0, i4 - this.mSelectionDividerHeight, getRight(), i4);
            this.mSelectionDivider.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.mHasSelectorWheel && isEnabled() && (motionEvent.getAction() & 255) == 0) {
            removeAllCallbacks();
            this.mInputText.setVisibility(4);
            float y = motionEvent.getY();
            this.mLastDownEventY = y;
            this.mLastDownOrMoveEventY = y;
            this.mIngonreMoveEvents = false;
            this.mShowSoftInputOnTap = false;
            if (y < this.mTopSelectionDividerTop) {
                if (this.mScrollState == 0) {
                    this.mPressedStateHelper.a(2);
                }
            } else if (y > this.mBottomSelectionDividerBottom && this.mScrollState == 0) {
                this.mPressedStateHelper.a(1);
            }
            getParent().requestDisallowInterceptTouchEvent(true);
            if (!this.mFlingScroller.isFinished()) {
                this.mFlingScroller.forceFinished(true);
                this.mAdjustScroller.forceFinished(true);
                onScrollStateChange(0);
            } else if (!this.mAdjustScroller.isFinished()) {
                this.mFlingScroller.forceFinished(true);
                this.mAdjustScroller.forceFinished(true);
            } else {
                float f2 = this.mLastDownEventY;
                if (f2 < this.mTopSelectionDividerTop) {
                    hideSoftInput();
                    postChangeCurrentByOneFromLongPress(false, ViewConfiguration.getLongPressTimeout());
                } else if (f2 > this.mBottomSelectionDividerBottom) {
                    hideSoftInput();
                    postChangeCurrentByOneFromLongPress(true, ViewConfiguration.getLongPressTimeout());
                } else {
                    this.mShowSoftInputOnTap = true;
                    postBeginSoftInputOnLongPressCommand();
                }
            }
            return true;
        }
        return false;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (!this.mHasSelectorWheel) {
            super.onLayout(z, i2, i3, i4, i5);
            return;
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth2 = this.mInputText.getMeasuredWidth();
        int measuredHeight2 = this.mInputText.getMeasuredHeight();
        int i6 = (measuredWidth - measuredWidth2) >> 1;
        int i7 = (measuredHeight - measuredHeight2) >> 1;
        this.mInputText.layout(i6, i7, measuredWidth2 + i6, measuredHeight2 + i7);
        if (z) {
            initializeSelectorWheel();
            initializeFadingEdges();
            int height = getHeight();
            int i8 = this.mSelectionDividersDistance;
            int i9 = this.mSelectionDividerHeight;
            int i10 = ((height - i8) >> 1) - i9;
            this.mTopSelectionDividerTop = i10;
            this.mBottomSelectionDividerBottom = i10 + (i9 * 2) + i8;
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (!this.mHasSelectorWheel) {
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(makeMeasureSpec(i2, this.mMaxWidth), makeMeasureSpec(i3, this.mMaxHeight));
        setMeasuredDimension(resolveSizeAndStateRespectingMinSize(this.mMinWidth, getMeasuredWidth(), i2), resolveSizeAndStateRespectingMinSize(this.mMinHeight, getMeasuredHeight(), i3));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && this.mHasSelectorWheel) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent);
            int action = motionEvent.getAction() & 255;
            if (action != 1) {
                if (action == 2 && !this.mIngonreMoveEvents) {
                    float y = motionEvent.getY();
                    if (this.mScrollState != 1) {
                        if (((int) Math.abs(y - this.mLastDownEventY)) > this.mTouchSlop) {
                            removeAllCallbacks();
                            onScrollStateChange(1);
                        }
                    } else {
                        scrollBy(0, (int) (y - this.mLastDownOrMoveEventY));
                        invalidate();
                    }
                    this.mLastDownOrMoveEventY = y;
                }
            } else {
                removeBeginSoftInputCommand();
                removeChangeCurrentByOneFromLongPress();
                this.mPressedStateHelper.c();
                VelocityTracker velocityTracker = this.mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, this.mMaximumFlingVelocity);
                int yVelocity = (int) velocityTracker.getYVelocity();
                if (Math.abs(yVelocity) > this.mMinimumFlingVelocity) {
                    fling(yVelocity);
                    onScrollStateChange(2);
                } else {
                    int y2 = (int) motionEvent.getY();
                    if (((int) Math.abs(y2 - this.mLastDownEventY)) <= this.mTouchSlop) {
                        if (this.mShowSoftInputOnTap) {
                            this.mShowSoftInputOnTap = false;
                            showSoftInput();
                        } else {
                            int i2 = (y2 / this.mSelectorElementHeight) - 1;
                            if (i2 > 0) {
                                changeValueByOne(true);
                                this.mPressedStateHelper.b(1);
                            } else if (i2 < 0) {
                                changeValueByOne(false);
                                this.mPressedStateHelper.b(2);
                            }
                        }
                    } else {
                        ensureScrollWheelAdjusted();
                    }
                    onScrollStateChange(0);
                }
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void scrollBy(int i2, int i3) {
        int[] iArr = this.mSelectorIndices;
        boolean z = this.mWrapSelectorWheel;
        if (!z && i3 > 0 && iArr[1] <= this.mMinValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
        } else if (!z && i3 < 0 && iArr[1] >= this.mMaxValue) {
            this.mCurrentScrollOffset = this.mInitialScrollOffset;
        } else {
            this.mCurrentScrollOffset += i3;
            while (true) {
                int i4 = this.mCurrentScrollOffset;
                if (i4 - this.mInitialScrollOffset <= this.mSelectorTextGapHeight) {
                    break;
                }
                this.mCurrentScrollOffset = i4 - this.mSelectorElementHeight;
                decrementSelectorIndices(iArr);
                setValueInternal(iArr[1], true);
                if (!this.mWrapSelectorWheel && iArr[1] <= this.mMinValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                }
            }
            while (true) {
                int i5 = this.mCurrentScrollOffset;
                if (i5 - this.mInitialScrollOffset >= (-this.mSelectorTextGapHeight)) {
                    return;
                }
                this.mCurrentScrollOffset = i5 + this.mSelectorElementHeight;
                incrementSelectorIndices(iArr);
                setValueInternal(iArr[1], true);
                if (!this.mWrapSelectorWheel && iArr[1] >= this.mMaxValue) {
                    this.mCurrentScrollOffset = this.mInitialScrollOffset;
                }
            }
        }
    }

    public void setDisplayedValues(String[] strArr) {
        if (this.mDisplayedValues == strArr) {
            return;
        }
        this.mDisplayedValues = strArr;
        if (strArr != null) {
            this.mInputText.setRawInputType(FloorType.LASTREAD);
        } else {
            this.mInputText.setRawInputType(2);
        }
        updateInputTextView();
        initializeSelectorWheelIndices();
        tryComputeMaxWidth();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.mHasSelectorWheel) {
            this.mIncrementButton.setEnabled(z);
        }
        if (!this.mHasSelectorWheel) {
            this.mDecrementButton.setEnabled(z);
        }
        this.mInputText.setEnabled(z);
    }

    public void setFormatter(f fVar) {
        if (fVar == this.mFormatter) {
            return;
        }
        this.mFormatter = fVar;
        initializeSelectorWheelIndices();
        updateInputTextView();
    }

    public void setMaxValue(int i2) {
        if (this.mMaxValue == i2) {
            return;
        }
        if (i2 >= 0) {
            this.mMaxValue = i2;
            if (i2 < this.mValue) {
                this.mValue = i2;
            }
            setWrapSelectorWheel(i2 - this.mMinValue > this.mSelectorIndices.length);
            initializeSelectorWheelIndices();
            updateInputTextView();
            tryComputeMaxWidth();
            invalidate();
            return;
        }
        throw new IllegalArgumentException("maxValue must be >= 0");
    }

    public void setMinValue(int i2) {
        if (this.mMinValue == i2) {
            return;
        }
        if (i2 >= 0) {
            this.mMinValue = i2;
            if (i2 > this.mValue) {
                this.mValue = i2;
            }
            setWrapSelectorWheel(this.mMaxValue - i2 > this.mSelectorIndices.length);
            initializeSelectorWheelIndices();
            updateInputTextView();
            tryComputeMaxWidth();
            invalidate();
            return;
        }
        throw new IllegalArgumentException("minValue must be >= 0");
    }

    public void setOnLongPressUpdateInterval(long j2) {
        this.mLongPressUpdateInterval = j2;
    }

    public void setOnScrollListener(h hVar) {
        this.mOnScrollListener = hVar;
    }

    public void setOnValueChangedListener(i iVar) {
        this.mOnValueChangeListener = iVar;
    }

    public void setValue(int i2) {
        setValueInternal(i2, false);
    }

    public void setWrapSelectorWheel(boolean z) {
        boolean z2 = this.mMaxValue - this.mMinValue >= this.mSelectorIndices.length;
        if ((!z || z2) && z != this.mWrapSelectorWheel) {
            this.mWrapSelectorWheel = z;
        }
    }

    public NumberPicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attrprivate.c2);
    }

    public NumberPicker(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        this.mLongPressUpdateInterval = DEFAULT_LONG_PRESS_UPDATE_INTERVAL;
        this.mSelectorIndexToStringCache = new SparseArray<>();
        this.mSelectorIndices = new int[3];
        this.mInitialScrollOffset = Integer.MIN_VALUE;
        this.mScrollState = 0;
        this.mLastHandledDownDpadKeyCode = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.NumberPicker, i2, 0);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        boolean z = resourceId != 0;
        this.mHasSelectorWheel = z;
        this.mSolidColor = obtainStyledAttributes.getColor(8, 0);
        this.mSelectionDivider = obtainStyledAttributes.getDrawable(5);
        this.mSelectionDividerHeight = obtainStyledAttributes.getDimensionPixelSize(6, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.mSelectionDividersDistance = obtainStyledAttributes.getDimensionPixelSize(7, (int) TypedValue.applyDimension(1, 48.0f, getResources().getDisplayMetrics()));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, -1);
        this.mMinHeight = dimensionPixelSize;
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, -1);
        this.mMaxHeight = dimensionPixelSize2;
        if (dimensionPixelSize != -1 && dimensionPixelSize2 != -1 && dimensionPixelSize > dimensionPixelSize2) {
            throw new IllegalArgumentException("minHeight > maxHeight");
        }
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(4, -1);
        this.mMinWidth = dimensionPixelSize3;
        int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(2, -1);
        this.mMaxWidth = dimensionPixelSize4;
        if (dimensionPixelSize3 != -1 && dimensionPixelSize4 != -1 && dimensionPixelSize3 > dimensionPixelSize4) {
            throw new IllegalArgumentException("minWidth > maxWidth");
        }
        this.mComputeMaxWidth = dimensionPixelSize4 == -1;
        this.mVirtualButtonPressedDrawable = obtainStyledAttributes.getDrawable(9);
        obtainStyledAttributes.recycle();
        this.mPressedStateHelper = new j();
        setWillNotDraw(!z);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(resourceId, (ViewGroup) this, true);
        a aVar = new a();
        b bVar = new b();
        if (!z) {
            ImageButton imageButton = (ImageButton) findViewById(R.id.j4);
            this.mIncrementButton = imageButton;
            imageButton.setOnClickListener(aVar);
            imageButton.setOnLongClickListener(bVar);
        } else {
            this.mIncrementButton = null;
        }
        if (!z) {
            ImageButton imageButton2 = (ImageButton) findViewById(R.id.j3);
            this.mDecrementButton = imageButton2;
            imageButton2.setOnClickListener(aVar);
            imageButton2.setOnLongClickListener(bVar);
        } else {
            this.mDecrementButton = null;
        }
        EditText editText = (EditText) findViewById(R.id.awp);
        this.mInputText = editText;
        editText.setOnFocusChangeListener(new c());
        editText.setFilters(new InputFilter[]{new g()});
        editText.setRawInputType(2);
        editText.setImeOptions(6);
        editText.setTextColor(getResources().getColor(17170444));
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity() / 8;
        int textSize = (int) editText.getTextSize();
        this.mTextSize = textSize;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);
        paint.setTypeface(editText.getTypeface());
        paint.setColor(editText.getTextColors().getColorForState(LinearLayout.ENABLED_STATE_SET, -1));
        this.mSelectorWheelPaint = paint;
        this.mFlingScroller = new Scroller(getContext(), null, true);
        this.mAdjustScroller = new Scroller(getContext(), new DecelerateInterpolator(2.5f));
        updateInputTextView();
    }
}
