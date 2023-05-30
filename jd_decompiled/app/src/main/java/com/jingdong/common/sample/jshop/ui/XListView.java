package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.jingdong.app.mall.R;

/* loaded from: classes6.dex */
public class XListView extends ListView implements AbsListView.OnScrollListener {
    private static final float OFFSET_RADIO = 2.0f;
    private static final int PULL_LOAD_MORE_DELTA = 50;
    private static final int SCROLLBACK_FOOTER = 1;
    private static final int SCROLLBACK_HEADER = 0;
    private static final int SCROLL_DURATION = 400;
    private boolean isLast;
    private boolean isShowHeader;
    private boolean mEnablePullLoad;
    private boolean mEnablePullRefresh;
    private XListViewFooter mFooterView;
    private TextView mHeaderTimeView;
    private XListViewHeader mHeaderView;
    private RelativeLayout mHeaderViewContent;
    private int mHeaderViewHeight;
    private boolean mIsFooterReady;
    private float mLastY;
    private IXListViewListener mListViewListener;
    private boolean mPullLoading;
    private boolean mPullRefreshing;
    private int mScrollBack;
    private AbsListView.OnScrollListener mScrollListener;
    private Scroller mScroller;
    private int mTotalItemCount;

    /* loaded from: classes6.dex */
    public interface IXListViewListener {
        void onLoadMore();

        void onRefresh();
    }

    /* loaded from: classes6.dex */
    public interface OnXScrollListener extends AbsListView.OnScrollListener {
        void onXScrolling(View view);
    }

    public XListView(Context context) {
        super(context);
        this.mLastY = -1.0f;
        this.mIsFooterReady = false;
        this.isLast = false;
        this.mEnablePullRefresh = false;
        this.mPullRefreshing = false;
        this.isShowHeader = false;
        initWithContext(context);
    }

    private void initWithContext(Context context) {
        this.mScroller = new Scroller(context, new DecelerateInterpolator());
        super.setOnScrollListener(this);
        XListViewHeader xListViewHeader = new XListViewHeader(context);
        this.mHeaderView = xListViewHeader;
        this.mHeaderViewContent = (RelativeLayout) xListViewHeader.findViewById(R.id.xlistview_header_content);
        this.mHeaderTimeView = (TextView) this.mHeaderView.findViewById(R.id.xlistview_header_time);
        addHeaderView(this.mHeaderView);
        this.mFooterView = new XListViewFooter(context);
        this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.sample.jshop.ui.XListView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                XListView xListView = XListView.this;
                xListView.mHeaderViewHeight = xListView.mHeaderViewContent.getHeight();
                XListView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    private void invokeOnScrolling() {
        AbsListView.OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener instanceof OnXScrollListener) {
            ((OnXScrollListener) onScrollListener).onXScrolling(this);
        }
    }

    private void resetFooterHeight() {
        int bottomMargin = this.mFooterView.getBottomMargin();
        if (bottomMargin > 0) {
            this.mScrollBack = 1;
            this.mScroller.startScroll(0, bottomMargin, 0, -bottomMargin, 400);
            invalidate();
        }
    }

    private void resetHeaderHeight() {
        int i2;
        int visiableHeight = this.mHeaderView.getVisiableHeight();
        if (visiableHeight == 0) {
            return;
        }
        boolean z = this.mPullRefreshing;
        if (!z || visiableHeight > this.mHeaderViewHeight) {
            if (!z || visiableHeight <= (i2 = this.mHeaderViewHeight)) {
                i2 = 0;
            }
            this.mScrollBack = 0;
            this.mScroller.startScroll(0, visiableHeight, 0, i2 - visiableHeight, 400);
            invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoadMore() {
        this.mPullLoading = true;
        this.mFooterView.setState(2);
        IXListViewListener iXListViewListener = this.mListViewListener;
        if (iXListViewListener != null) {
            iXListViewListener.onLoadMore();
        }
    }

    private void updateFooterHeight(float f2) {
        int bottomMargin = this.mFooterView.getBottomMargin() + ((int) f2);
        if (this.mEnablePullLoad && !this.mPullLoading) {
            if (bottomMargin > 50) {
                this.mFooterView.setState(1);
            } else {
                this.mFooterView.setState(0);
            }
        }
        this.mFooterView.setBottomMargin(bottomMargin);
    }

    private void updateHeaderHeight(float f2) {
        XListViewHeader xListViewHeader = this.mHeaderView;
        xListViewHeader.setVisiableHeight(((int) f2) + xListViewHeader.getVisiableHeight());
        if (this.mEnablePullRefresh && !this.mPullRefreshing) {
            if (this.mHeaderView.getVisiableHeight() > this.mHeaderViewHeight) {
                this.mHeaderView.setState(1);
            } else {
                this.mHeaderView.setState(0);
            }
        }
        setSelection(0);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            if (this.mScrollBack == 0) {
                this.mHeaderView.setVisiableHeight(this.mScroller.getCurrY());
            } else {
                this.mFooterView.setBottomMargin(this.mScroller.getCurrY());
            }
            postInvalidate();
            invokeOnScrolling();
        }
        super.computeScroll();
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        this.mTotalItemCount = i4;
        AbsListView.OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScroll(absListView, i2, i3, i4);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i2) {
        AbsListView.OnScrollListener onScrollListener = this.mScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(absListView, i2);
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mLastY == -1.0f) {
            this.mLastY = motionEvent.getRawY();
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mLastY = motionEvent.getRawY();
        } else if (action != 2) {
            this.mLastY = -1.0f;
            if (getFirstVisiblePosition() == 0) {
                if (this.mEnablePullRefresh && this.mHeaderView.getVisiableHeight() > this.mHeaderViewHeight) {
                    this.mPullRefreshing = true;
                    this.mHeaderView.setState(2);
                    IXListViewListener iXListViewListener = this.mListViewListener;
                    if (iXListViewListener != null) {
                        iXListViewListener.onRefresh();
                    }
                }
                resetHeaderHeight();
            } else if (getLastVisiblePosition() == this.mTotalItemCount - 1) {
                if (this.mEnablePullLoad && this.mFooterView.getBottomMargin() > 50) {
                    startLoadMore();
                }
                resetFooterHeight();
            }
        } else {
            float rawY = motionEvent.getRawY() - this.mLastY;
            this.mLastY = motionEvent.getRawY();
            if (getFirstVisiblePosition() == 0 && (this.mHeaderView.getVisiableHeight() > 0 || rawY > 0.0f)) {
                updateHeaderHeight(rawY / 2.0f);
                invokeOnScrolling();
            } else if (getLastVisiblePosition() == this.mTotalItemCount - 1 && (this.mFooterView.getBottomMargin() > 0 || rawY < 0.0f)) {
                updateFooterHeight((-rawY) / 2.0f);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setFooterHintViewVisibility(boolean z) {
        XListViewFooter xListViewFooter = this.mFooterView;
        if (xListViewFooter != null) {
            xListViewFooter.setHintViewVisibility(z ? 0 : 8);
        }
    }

    public void setFooterText(int i2, boolean z) {
        this.isLast = z;
        XListViewFooter xListViewFooter = this.mFooterView;
        if (xListViewFooter != null) {
            xListViewFooter.setFooterText(i2, z);
            invalidate();
        }
    }

    public void setNeedProgressBar(boolean z) {
        XListViewFooter xListViewFooter = this.mFooterView;
        if (xListViewFooter != null) {
            xListViewFooter.isNeedProgressBar = z;
        }
    }

    @Override // android.widget.AbsListView
    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    public void setPullLoadEnable(boolean z) {
        this.mEnablePullLoad = z;
        if (!z) {
            this.mFooterView.hide();
            this.mFooterView.setOnClickListener(null);
            return;
        }
        this.mPullLoading = false;
        this.mFooterView.show();
        this.mFooterView.setState(0);
        this.mFooterView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.ui.XListView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XListView.this.startLoadMore();
            }
        });
    }

    public void setPullRefreshEnable(boolean z) {
        this.mEnablePullRefresh = z;
        if (z && this.isShowHeader) {
            this.mHeaderViewContent.setVisibility(0);
        } else {
            this.mHeaderViewContent.setVisibility(4);
        }
    }

    public void setRefreshTime(String str) {
    }

    public void setShowHeader(boolean z) {
        this.mHeaderViewContent.setVisibility(z ? 0 : 4);
    }

    public void setXListViewListener(IXListViewListener iXListViewListener) {
        this.mListViewListener = iXListViewListener;
    }

    public void stopLoadMore() {
        if (this.mPullLoading) {
            this.mPullLoading = false;
            this.mFooterView.setState(0);
        }
    }

    public void stopRefresh() {
        if (this.mPullRefreshing) {
            this.mPullRefreshing = false;
            resetHeaderHeight();
        }
    }

    @Override // android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        if (!this.mIsFooterReady) {
            this.mIsFooterReady = true;
            addFooterView(this.mFooterView);
        }
        super.setAdapter(listAdapter);
    }

    public void setFooterText(int i2, int i3, boolean z) {
        this.isLast = z;
        XListViewFooter xListViewFooter = this.mFooterView;
        if (xListViewFooter != null) {
            xListViewFooter.setFooterText(i2, i3, z);
            invalidate();
        }
    }

    public XListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLastY = -1.0f;
        this.mIsFooterReady = false;
        this.isLast = false;
        this.mEnablePullRefresh = false;
        this.mPullRefreshing = false;
        this.isShowHeader = false;
        initWithContext(context);
    }

    public void setFooterText(int i2, boolean z, boolean z2) {
        this.isLast = z;
        XListViewFooter xListViewFooter = this.mFooterView;
        if (xListViewFooter != null) {
            if (z2) {
                xListViewFooter.show();
                this.mFooterView.setFooterText(i2, 0, this.isLast);
                invalidate();
                return;
            }
            xListViewFooter.hide();
        }
    }

    public void setFooterText(int i2, int i3, boolean z, boolean z2) {
        this.isLast = z;
        XListViewFooter xListViewFooter = this.mFooterView;
        if (xListViewFooter != null) {
            if (z2) {
                xListViewFooter.show();
                this.mFooterView.setFooterText(i2, i3, this.isLast);
                invalidate();
                return;
            }
            xListViewFooter.hide();
        }
    }

    public XListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mLastY = -1.0f;
        this.mIsFooterReady = false;
        this.isLast = false;
        this.mEnablePullRefresh = false;
        this.mPullRefreshing = false;
        this.isShowHeader = false;
        initWithContext(context);
    }
}
