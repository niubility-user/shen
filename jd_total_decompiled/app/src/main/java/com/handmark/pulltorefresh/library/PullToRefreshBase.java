package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.handmark.pulltorefresh.library.internal.FlipLoadingLayout;
import com.handmark.pulltorefresh.library.internal.JDLoadingLayout;
import com.handmark.pulltorefresh.library.internal.JDLoadingLayoutApi16;
import com.handmark.pulltorefresh.library.internal.RotateLoadingLayout;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.handmark.pulltorefresh.library.internal.ViewCompat;
import com.jd.dynamic.DYConstants;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.UnWidgetThemeController;

/* loaded from: classes12.dex */
public abstract class PullToRefreshBase<T extends View> extends LinearLayout implements IPullToRefresh<T> {
    static final boolean DEBUG = false;
    static final int DEMO_SCROLL_INTERVAL = 225;
    static final float FRICTION = 2.5f;
    static final String LOG_TAG = "PullToRefresh";
    public static final float OFFSET_RADIO = 2.5f;
    public static final int SMOOTH_SCROLL_DURATION_MS = 200;
    public static final int SMOOTH_SCROLL_LONG_DURATION_MS = 325;
    static final String STATE_CURRENT_MODE = "ptr_current_mode";
    static final String STATE_MODE = "ptr_mode";
    static final String STATE_SCROLLING_REFRESHING_ENABLED = "ptr_disable_scrolling";
    static final String STATE_SHOW_REFRESHING_VIEW = "ptr_show_refreshing_view";
    static final String STATE_STATE = "ptr_state";
    static final String STATE_SUPER = "ptr_super";
    static final boolean USE_HW_LAYERS = false;
    private Boolean isAutoDark;
    private boolean isDarkMode;
    private Mode mCurrentMode;
    private PullToRefreshBase<T>.SmoothScrollRunnable mCurrentSmoothScrollRunnable;
    private boolean mFilterTouchEvents;
    private BaseLoadingLayout mFooterLayout;
    private BaseLoadingLayout mHeaderLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private boolean mIsBeingDragged;
    private float mLastMotionX;
    private float mLastMotionY;
    private boolean mLayoutVisibilityChangesEnabled;
    private AnimationStyle mLoadingAnimationStyle;
    private Mode mMode;
    private OnPullEventListener<T> mOnPullEventListener;
    private OnRefreshListener<T> mOnRefreshListener;
    private OnRefreshListener2<T> mOnRefreshListener2;
    private boolean mOverScrollEnabled;
    protected T mRefreshableView;
    private FrameLayout mRefreshableViewWrapper;
    private Interpolator mScrollAnimationInterpolator;
    private boolean mScrollingWhileRefreshingEnabled;
    private boolean mShowViewWhileRefreshing;
    private State mState;
    private int mTouchSlop;

    /* renamed from: com.handmark.pulltorefresh.library.PullToRefreshBase$5 */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle;
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation;
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State;

        static {
            int[] iArr = new int[AnimationStyle.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle = iArr;
            try {
                iArr[AnimationStyle.JINGDONG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[AnimationStyle.FLIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[AnimationStyle.ROTATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[AnimationStyle.JINGDONG_FLIP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[Mode.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = iArr2;
            try {
                iArr2[Mode.PULL_FROM_END.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[Mode.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[Mode.MANUAL_REFRESH_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[Mode.BOTH.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr3 = new int[State.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State = iArr3;
            try {
                iArr3[State.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.PULL_TO_REFRESH.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.RELEASE_TO_REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.REFRESHING.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.MANUAL_REFRESHING.ordinal()] = 5;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.OVERSCROLLING.ordinal()] = 6;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[State.REFRESH_COMPLETE.ordinal()] = 7;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr4 = new int[Orientation.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation = iArr4;
            try {
                iArr4[Orientation.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[Orientation.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum AnimationStyle {
        ROTATE,
        FLIP,
        JINGDONG,
        JINGDONG_FLIP;

        static AnimationStyle getDefault() {
            return JINGDONG;
        }

        static AnimationStyle mapIntToValue(int i2) {
            if (i2 != 0) {
                if (i2 != 1) {
                    return JINGDONG;
                }
                return FLIP;
            }
            return ROTATE;
        }

        static AnimationStyle mapStringToValue(String str) {
            if (str != null && !str.isEmpty()) {
                if (TextUtils.equals(str.toLowerCase(), "rotate")) {
                    return ROTATE;
                }
                if (TextUtils.equals(str.toLowerCase(), "flip")) {
                    return FLIP;
                }
                if (TextUtils.equals(str.toLowerCase(), "jingdong")) {
                    return JINGDONG;
                }
                if (TextUtils.equals(str.toLowerCase(), "jingdong_flip")) {
                    return JINGDONG_FLIP;
                }
                return JINGDONG;
            }
            return JINGDONG;
        }

        BaseLoadingLayout createLoadingLayout(Context context, Mode mode, Orientation orientation, TypedArray typedArray) {
            int i2 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$AnimationStyle[ordinal()];
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (!PullToRefreshConfig.getInstance().isLottieEnable()) {
                            return new JDLoadingLayoutApi16(context, mode, orientation, typedArray);
                        }
                        return new JDLoadingLayout(context, mode, orientation, typedArray);
                    } else if (mode == Mode.PULL_FROM_START) {
                        if (!PullToRefreshConfig.getInstance().isLottieEnable()) {
                            return new JDLoadingLayoutApi16(context, mode, orientation, typedArray);
                        }
                        return new JDLoadingLayout(context, mode, orientation, typedArray);
                    } else {
                        return new FlipLoadingLayout(context, mode, orientation, typedArray);
                    }
                }
                return new RotateLoadingLayout(context, mode, orientation, typedArray);
            }
            return new FlipLoadingLayout(context, mode, orientation, typedArray);
        }
    }

    /* loaded from: classes12.dex */
    public enum Mode {
        DISABLED(0),
        PULL_FROM_START(1),
        PULL_FROM_END(2),
        BOTH(3),
        MANUAL_REFRESH_ONLY(4);
        
        public static final Mode PULL_DOWN_TO_REFRESH;
        public static final Mode PULL_UP_TO_REFRESH;
        private int mIntValue;

        static {
            Mode mode = PULL_FROM_START;
            Mode mode2 = PULL_FROM_END;
            PULL_DOWN_TO_REFRESH = mode;
            PULL_UP_TO_REFRESH = mode2;
        }

        Mode(int i2) {
            this.mIntValue = i2;
        }

        static Mode getDefault() {
            return PULL_FROM_START;
        }

        static Mode mapIntToValue(int i2) {
            for (Mode mode : values()) {
                if (i2 == mode.getIntValue()) {
                    return mode;
                }
            }
            return getDefault();
        }

        static Mode mapStringToValue(String str) {
            if (str != null && !str.isEmpty()) {
                if (TextUtils.equals(str.toLowerCase(), "disabled")) {
                    return DISABLED;
                }
                if (TextUtils.equals(str.toLowerCase(), "pullFromStart")) {
                    return PULL_FROM_START;
                }
                if (TextUtils.equals(str.toLowerCase(), "pullFromEnd")) {
                    return PULL_FROM_END;
                }
                if (TextUtils.equals(str.toLowerCase(), DYConstants.DY_SCROLL_BOTH)) {
                    return BOTH;
                }
                if (TextUtils.equals(str.toLowerCase(), "manualOnly")) {
                    return MANUAL_REFRESH_ONLY;
                }
                return DISABLED;
            }
            return DISABLED;
        }

        int getIntValue() {
            return this.mIntValue;
        }

        public boolean permitsPullToRefresh() {
            return (this == DISABLED || this == MANUAL_REFRESH_ONLY) ? false : true;
        }

        public boolean showFooterLoadingLayout() {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }

        public boolean showHeaderLoadingLayout() {
            return this == PULL_FROM_START || this == BOTH;
        }
    }

    /* loaded from: classes12.dex */
    public interface OnLastItemVisibleListener {
        void onLastItemVisible();
    }

    /* loaded from: classes12.dex */
    public interface OnPullEventListener<V extends View> {
        void onPullEvent(PullToRefreshBase<V> pullToRefreshBase, State state, Mode mode);
    }

    /* loaded from: classes12.dex */
    public interface OnRefreshListener<V extends View> {
        void onRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    /* loaded from: classes12.dex */
    public interface OnRefreshListener2<V extends View> {
        void onPullDownToRefresh(PullToRefreshBase<V> pullToRefreshBase);

        void onPullUpToRefresh(PullToRefreshBase<V> pullToRefreshBase);
    }

    /* loaded from: classes12.dex */
    public interface OnSmoothScrollFinishedListener {
        void onSmoothScrollFinished();
    }

    /* loaded from: classes12.dex */
    public enum Orientation {
        VERTICAL,
        HORIZONTAL
    }

    /* loaded from: classes12.dex */
    public final class SmoothScrollRunnable implements Runnable {
        private final long mDuration;
        private final Interpolator mInterpolator;
        private OnSmoothScrollFinishedListener mListener;
        private final int mScrollFromY;
        private final int mScrollToY;
        private boolean mContinueRunning = true;
        private long mStartTime = -1;
        private int mCurrentY = -1;

        public SmoothScrollRunnable(int i2, int i3, long j2, OnSmoothScrollFinishedListener onSmoothScrollFinishedListener) {
            PullToRefreshBase.this = r3;
            this.mScrollFromY = i2;
            this.mScrollToY = i3;
            this.mInterpolator = r3.mScrollAnimationInterpolator;
            this.mDuration = j2;
            this.mListener = onSmoothScrollFinishedListener;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mStartTime == -1) {
                this.mStartTime = System.currentTimeMillis();
            } else {
                int round = this.mScrollFromY - Math.round((this.mScrollFromY - this.mScrollToY) * this.mInterpolator.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.mStartTime) * 1000) / this.mDuration, 1000L), 0L)) / 1000.0f));
                this.mCurrentY = round;
                PullToRefreshBase.this.setHeaderScroll(round);
            }
            if (this.mContinueRunning && this.mScrollToY != this.mCurrentY) {
                ViewCompat.postOnAnimation(PullToRefreshBase.this, this);
                return;
            }
            OnSmoothScrollFinishedListener onSmoothScrollFinishedListener = this.mListener;
            if (onSmoothScrollFinishedListener != null) {
                onSmoothScrollFinishedListener.onSmoothScrollFinished();
            }
        }

        public void stop() {
            this.mContinueRunning = false;
            PullToRefreshBase.this.removeCallbacks(this);
        }
    }

    /* loaded from: classes12.dex */
    public enum State {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9),
        OVERSCROLLING(16),
        REFRESH_COMPLETE(17);
        
        private int mIntValue;

        State(int i2) {
            this.mIntValue = i2;
        }

        static State mapIntToValue(int i2) {
            for (State state : values()) {
                if (i2 == state.getIntValue()) {
                    return state;
                }
            }
            return RESET;
        }

        int getIntValue() {
            return this.mIntValue;
        }
    }

    public PullToRefreshBase(Context context) {
        super(context);
        this.mIsBeingDragged = false;
        this.mState = State.RESET;
        this.mMode = Mode.getDefault();
        this.mShowViewWhileRefreshing = true;
        this.mScrollingWhileRefreshingEnabled = false;
        this.mFilterTouchEvents = true;
        this.mOverScrollEnabled = true;
        this.mLayoutVisibilityChangesEnabled = true;
        this.mLoadingAnimationStyle = AnimationStyle.getDefault();
        init(context, null);
    }

    private void addRefreshableView(Context context, T t) {
        FrameLayout frameLayout = new FrameLayout(context);
        this.mRefreshableViewWrapper = frameLayout;
        frameLayout.addView(t, -1, -1);
        addViewInternal(this.mRefreshableViewWrapper, new LinearLayout.LayoutParams(-1, -1));
    }

    public void callRefreshListener() {
        OnRefreshListener<T> onRefreshListener = this.mOnRefreshListener;
        if (onRefreshListener != null) {
            onRefreshListener.onRefresh(this);
            return;
        }
        OnRefreshListener2<T> onRefreshListener2 = this.mOnRefreshListener2;
        if (onRefreshListener2 != null) {
            Mode mode = this.mCurrentMode;
            if (mode == Mode.PULL_FROM_START) {
                onRefreshListener2.onPullDownToRefresh(this);
            } else if (mode == Mode.PULL_FROM_END) {
                onRefreshListener2.onPullUpToRefresh(this);
            }
        }
    }

    private LinearLayout.LayoutParams getLoadingLayoutLayoutParams() {
        if (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            return new LinearLayout.LayoutParams(-1, -2);
        }
        return new LinearLayout.LayoutParams(-2, -1);
    }

    private int getMaximumPullScroll() {
        if (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            return Math.round(getHeight() / 2.5f);
        }
        return Math.round(getWidth() / 2.5f);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            setOrientation(1);
        } else {
            setOrientation(0);
        }
        setGravity(17);
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PullToRefresh);
        int i2 = R.styleable.PullToRefresh_ptrMode;
        if (obtainStyledAttributes.hasValue(i2)) {
            this.mMode = Mode.mapStringToValue(obtainStyledAttributes.getString(i2));
        }
        int i3 = R.styleable.PullToRefresh_ptrAnimationStyle;
        if (obtainStyledAttributes.hasValue(i3)) {
            this.mLoadingAnimationStyle = AnimationStyle.mapStringToValue(obtainStyledAttributes.getString(i3));
        }
        int i4 = R.styleable.PullToRefresh_ptrIsAutoDark;
        if (obtainStyledAttributes.hasValue(i4)) {
            this.isAutoDark = Boolean.valueOf(obtainStyledAttributes.getBoolean(i4, false));
        }
        this.isDarkMode = obtainStyledAttributes.getBoolean(R.styleable.PullToRefresh_ptrIsDarkMode, false);
        T createRefreshableView = createRefreshableView(context, attributeSet);
        this.mRefreshableView = createRefreshableView;
        addRefreshableView(context, createRefreshableView);
        this.mHeaderLayout = createLoadingLayout(context, Mode.PULL_FROM_START, obtainStyledAttributes);
        this.mFooterLayout = createLoadingLayout(context, Mode.PULL_FROM_END, obtainStyledAttributes);
        int i5 = R.styleable.PullToRefresh_ptrRefreshableViewBackground;
        if (obtainStyledAttributes.hasValue(i5)) {
            Drawable drawable = obtainStyledAttributes.getDrawable(i5);
            if (drawable != null) {
                this.mRefreshableView.setBackgroundDrawable(drawable);
            }
        } else {
            int i6 = R.styleable.PullToRefresh_ptrAdapterViewBackground;
            if (obtainStyledAttributes.hasValue(i6)) {
                Utils.warnDeprecation("ptrAdapterViewBackground", "ptrRefreshableViewBackground");
                Drawable drawable2 = obtainStyledAttributes.getDrawable(i6);
                if (drawable2 != null) {
                    this.mRefreshableView.setBackgroundDrawable(drawable2);
                }
            }
        }
        int i7 = R.styleable.PullToRefresh_ptrOverScroll;
        if (obtainStyledAttributes.hasValue(i7)) {
            this.mOverScrollEnabled = obtainStyledAttributes.getBoolean(i7, true);
        }
        int i8 = R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled;
        if (obtainStyledAttributes.hasValue(i8)) {
            this.mScrollingWhileRefreshingEnabled = obtainStyledAttributes.getBoolean(i8, false);
        }
        handleStyledAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        updateUIForMode();
    }

    private boolean isReadyForPull() {
        int i2 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mMode.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 4) {
                    return false;
                }
                return isReadyForPullEnd() || isReadyForPullStart();
            }
            return isReadyForPullStart();
        }
        return isReadyForPullEnd();
    }

    private void pullEvent() {
        float f2;
        float f3;
        int round;
        int footerSize;
        if (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            f2 = this.mInitialMotionY;
            f3 = this.mLastMotionY;
        } else {
            f2 = this.mInitialMotionX;
            f3 = this.mLastMotionX;
        }
        int[] iArr = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;
        if (iArr[this.mCurrentMode.ordinal()] != 1) {
            round = Math.round(Math.min(f2 - f3, 0.0f) / 2.5f);
            footerSize = getHeaderSize();
        } else {
            round = Math.round(Math.max(f2 - f3, 0.0f) / 2.5f);
            footerSize = getFooterSize();
        }
        setHeaderScroll(round);
        if (round == 0 || isRefreshing()) {
            return;
        }
        float abs = Math.abs(round) / footerSize;
        if (iArr[this.mCurrentMode.ordinal()] != 1) {
            this.mHeaderLayout.onPull(abs);
        } else {
            this.mFooterLayout.onPull(abs);
        }
        State state = this.mState;
        State state2 = State.PULL_TO_REFRESH;
        if (state != state2 && footerSize >= Math.abs(round)) {
            setState(state2, new boolean[0]);
        } else if (this.mState != state2 || footerSize >= Math.abs(round)) {
        } else {
            setState(State.RELEASE_TO_REFRESH, new boolean[0]);
        }
    }

    private final void smoothScrollToAndBack(int i2) {
        smoothScrollTo(i2, 200L, 0L, new OnSmoothScrollFinishedListener() { // from class: com.handmark.pulltorefresh.library.PullToRefreshBase.4
            {
                PullToRefreshBase.this = this;
            }

            @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnSmoothScrollFinishedListener
            public void onSmoothScrollFinished() {
                PullToRefreshBase.this.smoothScrollTo(0, 200L, 225L, null);
            }
        });
    }

    public void addFooterView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mFooterLayout.addHeaderView(view, layoutParams);
    }

    public void addHeaderView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mHeaderLayout.addHeaderView(view, layoutParams);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        T refreshableView = getRefreshableView();
        if (refreshableView instanceof ViewGroup) {
            ((ViewGroup) refreshableView).addView(view, i2, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
    }

    protected final void addViewInternal(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
    }

    public BaseLoadingLayout createLoadingLayout(Context context, Mode mode, TypedArray typedArray) {
        Boolean bool;
        BaseLoadingLayout createLoadingLayout = this.mLoadingAnimationStyle.createLoadingLayout(context, mode, getPullToRefreshScrollDirection(), typedArray);
        if (!createLoadingLayout.isSettingDark() && (bool = this.isAutoDark) != null) {
            createLoadingLayout.setAutoDark(bool.booleanValue());
            createLoadingLayout.setDarkMode(isDarkMode());
        }
        createLoadingLayout.setVisibility(4);
        return createLoadingLayout;
    }

    public LoadingLayoutProxy createLoadingLayoutProxy(boolean z, boolean z2) {
        LoadingLayoutProxy loadingLayoutProxy = new LoadingLayoutProxy();
        if (z && this.mMode.showHeaderLoadingLayout()) {
            loadingLayoutProxy.addLayout(this.mHeaderLayout);
        }
        if (z2 && this.mMode.showFooterLoadingLayout()) {
            loadingLayoutProxy.addLayout(this.mFooterLayout);
        }
        return loadingLayoutProxy;
    }

    protected abstract T createRefreshableView(Context context, AttributeSet attributeSet);

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final boolean demo() {
        if (this.mMode.showHeaderLoadingLayout() && isReadyForPullStart()) {
            smoothScrollToAndBack((-getHeaderSize()) * 2);
            return true;
        } else if (this.mMode.showFooterLoadingLayout() && isReadyForPullEnd()) {
            smoothScrollToAndBack(getFooterSize() * 2);
            return true;
        } else {
            return false;
        }
    }

    protected final void disableLoadingLayoutVisibilityChanges() {
        this.mLayoutVisibilityChangesEnabled = false;
    }

    public Boolean getAutoDark() {
        return this.isAutoDark;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final Mode getCurrentMode() {
        return this.mCurrentMode;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final boolean getFilterTouchEvents() {
        return this.mFilterTouchEvents;
    }

    public BaseLoadingLayout getFooterLayout() {
        return this.mFooterLayout;
    }

    public final int getFooterSize() {
        return this.mFooterLayout.getContentSize();
    }

    public BaseLoadingLayout getHeaderLayout() {
        return this.mHeaderLayout;
    }

    public final int getHeaderSize() {
        return this.mHeaderLayout.getContentSize();
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final ILoadingLayout getLoadingLayoutProxy() {
        return getLoadingLayoutProxy(true, true);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final Mode getMode() {
        return this.mMode;
    }

    public abstract Orientation getPullToRefreshScrollDirection();

    protected int getPullToRefreshScrollDuration() {
        return 200;
    }

    protected int getPullToRefreshScrollDurationLonger() {
        return 325;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final T getRefreshableView() {
        return this.mRefreshableView;
    }

    public FrameLayout getRefreshableViewWrapper() {
        return this.mRefreshableViewWrapper;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final boolean getShowViewWhileRefreshing() {
        return this.mShowViewWhileRefreshing;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final State getState() {
        return this.mState;
    }

    protected void handleStyledAttributes(TypedArray typedArray) {
    }

    public boolean isDarkMode() {
        Boolean bool = this.isAutoDark;
        if (bool != null && bool.booleanValue()) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    public final boolean isDisableScrollingWhileRefreshing() {
        return !isScrollingWhileRefreshingEnabled();
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final boolean isPullToRefreshEnabled() {
        return this.mMode.permitsPullToRefresh();
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    @TargetApi(4)
    public final boolean isPullToRefreshOverScrollEnabled() {
        return Build.VERSION.SDK_INT >= 9 && this.mOverScrollEnabled && OverscrollHelper.isAndroidOverScrollEnabled(this.mRefreshableView);
    }

    protected abstract boolean isReadyForPullEnd();

    protected abstract boolean isReadyForPullStart();

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final boolean isRefreshing() {
        State state = this.mState;
        return state == State.REFRESHING || state == State.MANUAL_REFRESHING;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final boolean isScrollingWhileRefreshingEnabled() {
        return this.mScrollingWhileRefreshingEnabled;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float f2;
        float f3;
        if (isPullToRefreshEnabled()) {
            int action = motionEvent.getAction();
            if (action != 3 && action != 1) {
                if (action == 0 || !this.mIsBeingDragged) {
                    if (action != 0) {
                        if (action == 2) {
                            if (!this.mScrollingWhileRefreshingEnabled && isRefreshing()) {
                                return true;
                            }
                            if (isReadyForPull()) {
                                float y = motionEvent.getY();
                                float x = motionEvent.getX();
                                if (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
                                    f2 = y - this.mLastMotionY;
                                    f3 = x - this.mLastMotionX;
                                } else {
                                    f2 = x - this.mLastMotionX;
                                    f3 = y - this.mLastMotionY;
                                }
                                float abs = Math.abs(f2);
                                if (abs > this.mTouchSlop && (!this.mFilterTouchEvents || abs > Math.abs(f3))) {
                                    if (this.mMode.showHeaderLoadingLayout() && f2 >= 1.0f && isReadyForPullStart()) {
                                        this.mLastMotionY = y;
                                        this.mLastMotionX = x;
                                        this.mIsBeingDragged = true;
                                        if (this.mMode == Mode.BOTH) {
                                            this.mCurrentMode = Mode.PULL_FROM_START;
                                        }
                                    } else if (this.mMode.showFooterLoadingLayout() && f2 <= -1.0f && isReadyForPullEnd()) {
                                        this.mLastMotionY = y;
                                        this.mLastMotionX = x;
                                        this.mIsBeingDragged = true;
                                        if (this.mMode == Mode.BOTH) {
                                            this.mCurrentMode = Mode.PULL_FROM_END;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (isReadyForPull()) {
                        float y2 = motionEvent.getY();
                        this.mInitialMotionY = y2;
                        this.mLastMotionY = y2;
                        float x2 = motionEvent.getX();
                        this.mInitialMotionX = x2;
                        this.mLastMotionX = x2;
                        this.mIsBeingDragged = false;
                    }
                    return this.mIsBeingDragged;
                }
                return true;
            }
            this.mIsBeingDragged = false;
            return false;
        }
        return false;
    }

    protected void onPtrRestoreInstanceState(Bundle bundle) {
    }

    protected void onPtrSaveInstanceState(Bundle bundle) {
    }

    public void onPullToRefresh() {
        int i2 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()];
        if (i2 == 1) {
            this.mFooterLayout.pullToRefresh();
        } else if (i2 != 2) {
        } else {
            this.mHeaderLayout.pullToRefresh();
        }
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void onRefreshComplete() {
        if (isRefreshing()) {
            setState(State.REFRESH_COMPLETE, new boolean[0]);
            setState(State.RESET, new boolean[0]);
        }
    }

    public void onRefreshing(boolean z) {
        if (this.mMode.showHeaderLoadingLayout()) {
            this.mHeaderLayout.refreshing();
        }
        if (this.mMode.showFooterLoadingLayout()) {
            this.mFooterLayout.refreshing();
        }
        if (z) {
            if (this.mShowViewWhileRefreshing) {
                OnSmoothScrollFinishedListener onSmoothScrollFinishedListener = new OnSmoothScrollFinishedListener() { // from class: com.handmark.pulltorefresh.library.PullToRefreshBase.2
                    {
                        PullToRefreshBase.this = this;
                    }

                    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnSmoothScrollFinishedListener
                    public void onSmoothScrollFinished() {
                        PullToRefreshBase.this.callRefreshListener();
                    }
                };
                int i2 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()];
                if (i2 != 1 && i2 != 3) {
                    smoothScrollTo(-getHeaderSize(), onSmoothScrollFinishedListener);
                    return;
                } else {
                    smoothScrollTo(getFooterSize(), onSmoothScrollFinishedListener);
                    return;
                }
            }
            smoothScrollTo(0);
            return;
        }
        callRefreshListener();
    }

    public void onReleaseToRefresh() {
        int i2 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()];
        if (i2 == 1) {
            this.mFooterLayout.releaseToRefresh();
        } else if (i2 != 2) {
        } else {
            this.mHeaderLayout.releaseToRefresh();
        }
    }

    public void onReset() {
        this.mIsBeingDragged = false;
        this.mLayoutVisibilityChangesEnabled = true;
        this.mHeaderLayout.reset();
        this.mFooterLayout.reset();
        smoothScrollTo(0);
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            setMode(Mode.mapIntToValue(bundle.getInt(STATE_MODE, 0)));
            this.mCurrentMode = Mode.mapIntToValue(bundle.getInt(STATE_CURRENT_MODE, 0));
            this.mScrollingWhileRefreshingEnabled = bundle.getBoolean(STATE_SCROLLING_REFRESHING_ENABLED, false);
            this.mShowViewWhileRefreshing = bundle.getBoolean(STATE_SHOW_REFRESHING_VIEW, true);
            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER));
            State mapIntToValue = State.mapIntToValue(bundle.getInt(STATE_STATE, 0));
            if (mapIntToValue == State.REFRESHING || mapIntToValue == State.MANUAL_REFRESHING) {
                setState(mapIntToValue, true);
            }
            onPtrRestoreInstanceState(bundle);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        onPtrSaveInstanceState(bundle);
        bundle.putInt(STATE_STATE, this.mState.getIntValue());
        bundle.putInt(STATE_MODE, this.mMode.getIntValue());
        bundle.putInt(STATE_CURRENT_MODE, this.mCurrentMode.getIntValue());
        bundle.putBoolean(STATE_SCROLLING_REFRESHING_ENABLED, this.mScrollingWhileRefreshingEnabled);
        bundle.putBoolean(STATE_SHOW_REFRESHING_VIEW, this.mShowViewWhileRefreshing);
        bundle.putParcelable(STATE_SUPER, super.onSaveInstanceState());
        return bundle;
    }

    @Override // android.view.View
    public void onScrollChanged(int i2, int i3, int i4, int i5) {
        super.onScrollChanged(i2, i3, i4, i5);
        int i6 = -i3;
        this.mHeaderLayout.onScroll(i2, i6);
        this.mFooterLayout.onScroll(i2, i6);
    }

    @Override // android.view.View
    protected final void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        refreshLoadingViewsSize();
        refreshRefreshableViewSize(i2, i3);
        post(new Runnable() { // from class: com.handmark.pulltorefresh.library.PullToRefreshBase.3
            {
                PullToRefreshBase.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                PullToRefreshBase.this.requestLayout();
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x002d, code lost:
        if (r0 != 3) goto L143;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (isPullToRefreshEnabled()) {
            if (this.mScrollingWhileRefreshingEnabled || !isRefreshing()) {
                if (motionEvent.getAction() != 0 || motionEvent.getEdgeFlags() == 0) {
                    int action = motionEvent.getAction();
                    if (action != 0) {
                        if (action != 1) {
                            if (action == 2) {
                                if (this.mIsBeingDragged) {
                                    this.mLastMotionY = motionEvent.getY();
                                    this.mLastMotionX = motionEvent.getX();
                                    pullEvent();
                                    return true;
                                }
                            }
                        }
                        if (this.mIsBeingDragged) {
                            this.mIsBeingDragged = false;
                            if (this.mState == State.RELEASE_TO_REFRESH && (this.mOnRefreshListener != null || this.mOnRefreshListener2 != null)) {
                                setState(State.REFRESHING, true);
                                return true;
                            } else if (isRefreshing()) {
                                smoothScrollTo(0);
                                return true;
                            } else {
                                setState(State.RESET, new boolean[0]);
                                return true;
                            }
                        }
                    } else if (isReadyForPull()) {
                        float y = motionEvent.getY();
                        this.mInitialMotionY = y;
                        this.mLastMotionY = y;
                        float x = motionEvent.getX();
                        this.mInitialMotionX = x;
                        this.mLastMotionX = x;
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    protected void refreshComplete() {
        if (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[this.mCurrentMode.ordinal()] != 2) {
            return;
        }
        this.mHeaderLayout.refreshComplete();
    }

    protected final void refreshLoadingViewsSize() {
        int maximumPullScroll = (int) (getMaximumPullScroll() * 1.2f);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        int i2 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()];
        if (i2 == 1) {
            if (this.mMode.showHeaderLoadingLayout()) {
                this.mHeaderLayout.setWidth(maximumPullScroll);
                paddingLeft = -maximumPullScroll;
            } else {
                paddingLeft = 0;
            }
            if (this.mMode.showFooterLoadingLayout()) {
                this.mFooterLayout.setWidth(maximumPullScroll);
                paddingRight = -maximumPullScroll;
            } else {
                paddingRight = 0;
            }
        } else if (i2 == 2) {
            if (this.mMode.showHeaderLoadingLayout()) {
                this.mHeaderLayout.setHeight(maximumPullScroll);
                paddingTop = -maximumPullScroll;
            } else {
                paddingTop = 0;
            }
            if (this.mMode.showFooterLoadingLayout()) {
                this.mFooterLayout.setHeight(maximumPullScroll);
                paddingBottom = -maximumPullScroll;
            } else {
                paddingBottom = 0;
            }
        }
        setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    protected final void refreshRefreshableViewSize(int i2, int i3) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mRefreshableViewWrapper.getLayoutParams();
        int i4 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()];
        if (i4 != 1) {
            if (i4 == 2 && layoutParams.height != i3) {
                layoutParams.height = i3;
                this.mRefreshableViewWrapper.requestLayout();
            }
        } else if (layoutParams.width != i2) {
            layoutParams.width = i2;
            this.mRefreshableViewWrapper.requestLayout();
        }
    }

    public void refreshTheme() {
        BaseLoadingLayout baseLoadingLayout = this.mHeaderLayout;
        if (baseLoadingLayout != null) {
            baseLoadingLayout.refreshTheme();
        }
        BaseLoadingLayout baseLoadingLayout2 = this.mFooterLayout;
        if (baseLoadingLayout2 != null) {
            baseLoadingLayout2.refreshTheme();
        }
    }

    public PullToRefreshBase<T> setAutoDark(boolean z) {
        this.isAutoDark = Boolean.valueOf(z);
        BaseLoadingLayout baseLoadingLayout = this.mHeaderLayout;
        if (baseLoadingLayout != null) {
            baseLoadingLayout.setAutoDark(z);
        }
        BaseLoadingLayout baseLoadingLayout2 = this.mFooterLayout;
        if (baseLoadingLayout2 != null) {
            baseLoadingLayout2.setAutoDark(z);
        }
        return this;
    }

    public PullToRefreshBase<T> setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    public void setDisableScrollingWhileRefreshing(boolean z) {
        setScrollingWhileRefreshingEnabled(!z);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setFilterTouchEvents(boolean z) {
        this.mFilterTouchEvents = z;
    }

    public void setFooterLayout(BaseLoadingLayout baseLoadingLayout) {
        Boolean bool;
        this.mFooterLayout = baseLoadingLayout;
        if (this.mHeaderLayout == null || baseLoadingLayout.isSettingDark() || (bool = this.isAutoDark) == null) {
            return;
        }
        this.mFooterLayout.setAutoDark(bool.booleanValue());
        this.mFooterLayout.setDarkMode(this.isDarkMode);
    }

    public void setHeaderLayout(BaseLoadingLayout baseLoadingLayout) {
        Boolean bool;
        this.mHeaderLayout = baseLoadingLayout;
        if (baseLoadingLayout == null || baseLoadingLayout.isSettingDark() || (bool = this.isAutoDark) == null) {
            return;
        }
        this.mHeaderLayout.setAutoDark(bool.booleanValue());
        this.mHeaderLayout.setDarkMode(this.isDarkMode);
    }

    public final void setHeaderScroll(int i2) {
        int maximumPullScroll = getMaximumPullScroll();
        int min = Math.min(maximumPullScroll, Math.max(-maximumPullScroll, i2));
        if (this.mLayoutVisibilityChangesEnabled) {
            if (min < 0) {
                this.mHeaderLayout.setVisibility(0);
            } else if (min > 0) {
                this.mFooterLayout.setVisibility(0);
            } else {
                this.mHeaderLayout.setVisibility(4);
                this.mFooterLayout.setVisibility(4);
            }
        }
        int i3 = AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()];
        if (i3 == 1) {
            scrollTo(min, 0);
        } else if (i3 != 2) {
        } else {
            scrollTo(0, min);
        }
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setLastUpdatedLabel(charSequence);
    }

    public void setLoadingDrawable(Drawable drawable) {
        getLoadingLayoutProxy().setLoadingDrawable(drawable);
    }

    @Override // android.view.View
    public void setLongClickable(boolean z) {
        getRefreshableView().setLongClickable(z);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setMode(Mode mode) {
        if (mode != this.mMode) {
            this.mMode = mode;
            updateUIForMode();
        }
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public void setOnPullEventListener(OnPullEventListener<T> onPullEventListener) {
        this.mOnPullEventListener = onPullEventListener;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setOnRefreshListener(OnRefreshListener<T> onRefreshListener) {
        this.mOnRefreshListener = onRefreshListener;
        this.mOnRefreshListener2 = null;
    }

    public void setPullLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setPullLabel(charSequence);
    }

    public final void setPullToRefreshEnabled(boolean z) {
        setMode(z ? Mode.getDefault() : Mode.DISABLED);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setPullToRefreshOverScrollEnabled(boolean z) {
        this.mOverScrollEnabled = z;
    }

    public void setRedMode(boolean z) {
        BaseLoadingLayout baseLoadingLayout = this.mHeaderLayout;
        if (baseLoadingLayout != null) {
            baseLoadingLayout.setRedMode(true);
        }
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setRefreshing() {
        setRefreshing(true);
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        getLoadingLayoutProxy().setRefreshingLabel(charSequence);
    }

    public void setReleaseLabel(CharSequence charSequence) {
        setReleaseLabel(charSequence, Mode.BOTH);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public void setScrollAnimationInterpolator(Interpolator interpolator) {
        this.mScrollAnimationInterpolator = interpolator;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setScrollingWhileRefreshingEnabled(boolean z) {
        this.mScrollingWhileRefreshingEnabled = z;
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setShowViewWhileRefreshing(boolean z) {
        this.mShowViewWhileRefreshing = z;
    }

    public final void setState(State state, boolean... zArr) {
        this.mState = state;
        Boolean bool = this.isAutoDark;
        if (bool != null && bool.booleanValue()) {
            refreshTheme();
        }
        switch (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$State[this.mState.ordinal()]) {
            case 1:
                onReset();
                break;
            case 2:
                onPullToRefresh();
                break;
            case 3:
                onReleaseToRefresh();
                break;
            case 4:
            case 5:
                onRefreshing(zArr[0]);
                break;
            case 6:
                postDelayed(new Runnable() { // from class: com.handmark.pulltorefresh.library.PullToRefreshBase.1
                    {
                        PullToRefreshBase.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        PullToRefreshBase.this.setHeaderScroll(0);
                    }
                }, 500L);
                break;
            case 7:
                refreshComplete();
                break;
        }
        OnPullEventListener<T> onPullEventListener = this.mOnPullEventListener;
        if (onPullEventListener != null) {
            onPullEventListener.onPullEvent(this, this.mState, this.mCurrentMode);
        }
    }

    protected final void smoothScrollTo(int i2) {
        smoothScrollTo(i2, getPullToRefreshScrollDuration());
    }

    protected final void smoothScrollToLonger(int i2) {
        smoothScrollTo(i2, getPullToRefreshScrollDurationLonger());
    }

    public void updateUIForMode() {
        LinearLayout.LayoutParams loadingLayoutLayoutParams = getLoadingLayoutLayoutParams();
        if (this == this.mHeaderLayout.getParent()) {
            removeView(this.mHeaderLayout);
        }
        if (this.mMode.showHeaderLoadingLayout()) {
            addViewInternal(this.mHeaderLayout, 0, loadingLayoutLayoutParams);
        }
        if (this == this.mFooterLayout.getParent()) {
            removeView(this.mFooterLayout);
        }
        if (this.mMode.showFooterLoadingLayout()) {
            addViewInternal(this.mFooterLayout, loadingLayoutLayoutParams);
        }
        refreshLoadingViewsSize();
        Mode mode = this.mMode;
        if (mode == Mode.BOTH) {
            mode = Mode.PULL_FROM_START;
        }
        this.mCurrentMode = mode;
    }

    protected final void addViewInternal(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, -1, layoutParams);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final ILoadingLayout getLoadingLayoutProxy(boolean z, boolean z2) {
        return createLoadingLayoutProxy(z, z2);
    }

    public void setLoadingDrawable(Drawable drawable, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setLoadingDrawable(drawable);
    }

    public void setPullLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setPullLabel(charSequence);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setRefreshing(boolean z) {
        if (isRefreshing()) {
            return;
        }
        setState(State.MANUAL_REFRESHING, z);
    }

    public void setRefreshingLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setRefreshingLabel(charSequence);
    }

    public void setReleaseLabel(CharSequence charSequence, Mode mode) {
        getLoadingLayoutProxy(mode.showHeaderLoadingLayout(), mode.showFooterLoadingLayout()).setReleaseLabel(charSequence);
    }

    protected final void smoothScrollTo(int i2, OnSmoothScrollFinishedListener onSmoothScrollFinishedListener) {
        smoothScrollTo(i2, getPullToRefreshScrollDuration(), 0L, onSmoothScrollFinishedListener);
    }

    private final void smoothScrollTo(int i2, long j2) {
        smoothScrollTo(i2, j2, 0L, null);
    }

    @Override // com.handmark.pulltorefresh.library.IPullToRefresh
    public final void setOnRefreshListener(OnRefreshListener2<T> onRefreshListener2) {
        this.mOnRefreshListener2 = onRefreshListener2;
        this.mOnRefreshListener = null;
    }

    public final void smoothScrollTo(int i2, long j2, long j3, OnSmoothScrollFinishedListener onSmoothScrollFinishedListener) {
        int scrollX;
        PullToRefreshBase<T>.SmoothScrollRunnable smoothScrollRunnable = this.mCurrentSmoothScrollRunnable;
        if (smoothScrollRunnable != null) {
            smoothScrollRunnable.stop();
        }
        if (AnonymousClass5.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Orientation[getPullToRefreshScrollDirection().ordinal()] != 1) {
            scrollX = getScrollY();
        } else {
            scrollX = getScrollX();
        }
        int i3 = scrollX;
        if (i3 != i2) {
            if (this.mScrollAnimationInterpolator == null) {
                this.mScrollAnimationInterpolator = new DecelerateInterpolator();
            }
            PullToRefreshBase<T>.SmoothScrollRunnable smoothScrollRunnable2 = new SmoothScrollRunnable(i3, i2, j2, onSmoothScrollFinishedListener);
            this.mCurrentSmoothScrollRunnable = smoothScrollRunnable2;
            if (j3 > 0) {
                postDelayed(smoothScrollRunnable2, j3);
            } else {
                post(smoothScrollRunnable2);
            }
        }
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsBeingDragged = false;
        this.mState = State.RESET;
        this.mMode = Mode.getDefault();
        this.mShowViewWhileRefreshing = true;
        this.mScrollingWhileRefreshingEnabled = false;
        this.mFilterTouchEvents = true;
        this.mOverScrollEnabled = true;
        this.mLayoutVisibilityChangesEnabled = true;
        this.mLoadingAnimationStyle = AnimationStyle.getDefault();
        init(context, attributeSet);
    }

    public PullToRefreshBase(Context context, Mode mode) {
        super(context);
        this.mIsBeingDragged = false;
        this.mState = State.RESET;
        this.mMode = Mode.getDefault();
        this.mShowViewWhileRefreshing = true;
        this.mScrollingWhileRefreshingEnabled = false;
        this.mFilterTouchEvents = true;
        this.mOverScrollEnabled = true;
        this.mLayoutVisibilityChangesEnabled = true;
        this.mLoadingAnimationStyle = AnimationStyle.getDefault();
        this.mMode = mode;
        init(context, null);
    }

    public PullToRefreshBase(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context);
        this.mIsBeingDragged = false;
        this.mState = State.RESET;
        this.mMode = Mode.getDefault();
        this.mShowViewWhileRefreshing = true;
        this.mScrollingWhileRefreshingEnabled = false;
        this.mFilterTouchEvents = true;
        this.mOverScrollEnabled = true;
        this.mLayoutVisibilityChangesEnabled = true;
        this.mLoadingAnimationStyle = AnimationStyle.getDefault();
        this.mMode = mode;
        this.mLoadingAnimationStyle = animationStyle;
        init(context, null);
    }
}
