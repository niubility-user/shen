package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.UnLog;

/* loaded from: classes12.dex */
public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {
    private static final String TAG = "PullToRefreshListView";
    private BaseLoadingLayout mFooterLoadingView;
    private BaseLoadingLayout mHeaderLoadingView;
    private boolean mListViewExtrasEnabled;
    private FrameLayout mLvFooterLoadingFrame;

    /* renamed from: com.handmark.pulltorefresh.library.PullToRefreshListView$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode;

        static {
            int[] iArr = new int[PullToRefreshBase.Mode.values().length];
            $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode = iArr;
            try {
                iArr[PullToRefreshBase.Mode.MANUAL_REFRESH_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.PULL_FROM_END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[PullToRefreshBase.Mode.PULL_FROM_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public class InternalListView extends ListView implements EmptyViewMethodAccessor {
        private boolean mAddedLvFooter;

        public InternalListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mAddedLvFooter = false;
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(PullToRefreshListView.TAG, "", e2);
                }
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            try {
                return super.dispatchTouchEvent(motionEvent);
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(PullToRefreshListView.TAG, "", e2);
                    return false;
                }
                return false;
            }
        }

        @Override // android.widget.AdapterView, com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor
        public void setEmptyView(View view) {
            PullToRefreshListView.this.setEmptyView(view);
        }

        @Override // com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor
        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }

        @Override // android.widget.AdapterView
        public void setAdapter(ListAdapter listAdapter) {
            if (PullToRefreshListView.this.mLvFooterLoadingFrame != null && !this.mAddedLvFooter) {
                addFooterView(PullToRefreshListView.this.mLvFooterLoadingFrame, null, false);
                this.mAddedLvFooter = true;
            }
            super.setAdapter(listAdapter);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(9)
    /* loaded from: classes12.dex */
    public final class InternalListViewSDK9 extends InternalListView {
        public InternalListViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.view.View
        protected boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
            boolean overScrollBy = super.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
            OverscrollHelper.overScrollBy(PullToRefreshListView.this, i2, i4, i3, i5, z);
            return overScrollBy;
        }
    }

    public PullToRefreshListView(Context context) {
        super(context);
    }

    protected ListView createListView(Context context, AttributeSet attributeSet) {
        if (Build.VERSION.SDK_INT >= 9) {
            return new InternalListViewSDK9(context, attributeSet);
        }
        return new InternalListView(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public LoadingLayoutProxy createLoadingLayoutProxy(boolean z, boolean z2) {
        LoadingLayoutProxy createLoadingLayoutProxy = super.createLoadingLayoutProxy(z, z2);
        if (this.mListViewExtrasEnabled) {
            PullToRefreshBase.Mode mode = getMode();
            if (z && mode.showHeaderLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mHeaderLoadingView);
            }
            if (z2 && mode.showFooterLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mFooterLoadingView);
            }
        }
        return createLoadingLayoutProxy;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase, com.handmark.pulltorefresh.library.PullToRefreshBase
    public void handleStyledAttributes(TypedArray typedArray) {
        super.handleStyledAttributes(typedArray);
        boolean z = typedArray.getBoolean(R.styleable.PullToRefresh_ptrListViewExtrasEnabled, true);
        this.mListViewExtrasEnabled = z;
        if (z) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
            FrameLayout frameLayout = new FrameLayout(getContext());
            BaseLoadingLayout createLoadingLayout = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_START, typedArray);
            this.mHeaderLoadingView = createLoadingLayout;
            createLoadingLayout.setVisibility(8);
            frameLayout.addView(this.mHeaderLoadingView, layoutParams);
            ((ListView) this.mRefreshableView).addHeaderView(frameLayout, null, false);
            this.mLvFooterLoadingFrame = new FrameLayout(getContext());
            BaseLoadingLayout createLoadingLayout2 = createLoadingLayout(getContext(), PullToRefreshBase.Mode.PULL_FROM_END, typedArray);
            this.mFooterLoadingView = createLoadingLayout2;
            createLoadingLayout2.setVisibility(8);
            this.mLvFooterLoadingFrame.addView(this.mFooterLoadingView, layoutParams);
            if (typedArray.hasValue(R.styleable.PullToRefresh_ptrScrollingWhileRefreshingEnabled)) {
                return;
            }
            setScrollingWhileRefreshingEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase, com.handmark.pulltorefresh.library.PullToRefreshBase
    public void onReset() {
        BaseLoadingLayout footerLayout;
        BaseLoadingLayout baseLoadingLayout;
        int i2;
        if (!this.mListViewExtrasEnabled) {
            super.onReset();
            return;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$handmark$pulltorefresh$library$PullToRefreshBase$Mode[getCurrentMode().ordinal()];
        int i4 = 1;
        if (i3 != 1 && i3 != 2) {
            footerLayout = getHeaderLayout();
            baseLoadingLayout = this.mHeaderLoadingView;
            i2 = -getHeaderSize();
            if (Math.abs(((ListView) this.mRefreshableView).getFirstVisiblePosition() - 0) > 1) {
                i4 = 0;
            }
        } else {
            footerLayout = getFooterLayout();
            baseLoadingLayout = this.mFooterLoadingView;
            int count = ((ListView) this.mRefreshableView).getCount() - 1;
            int footerSize = getFooterSize();
            i4 = Math.abs(((ListView) this.mRefreshableView).getLastVisiblePosition() - count) <= 1 ? 1 : 0;
            r1 = count;
            i2 = footerSize;
        }
        if (baseLoadingLayout.getVisibility() == 0) {
            footerLayout.showInvisibleViews();
            baseLoadingLayout.setVisibility(8);
            if (i4 != 0 && getState() != PullToRefreshBase.State.MANUAL_REFRESHING) {
                ((ListView) this.mRefreshableView).setSelection(r1);
                setHeaderScroll(i2);
            }
        }
        super.onReset();
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public ListView createRefreshableView(Context context, AttributeSet attributeSet) {
        ListView createListView = createListView(context, attributeSet);
        createListView.setId(16908298);
        return createListView;
    }

    public PullToRefreshListView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshListView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }
}
