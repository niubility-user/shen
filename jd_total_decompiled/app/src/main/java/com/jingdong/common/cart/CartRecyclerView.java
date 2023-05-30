package com.jingdong.common.cart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class CartRecyclerView extends RecyclerView {
    private static final String TAG = "CartRecyclerView";
    public boolean isEditStatus;
    public boolean isOpenFloatShop;
    private LinearLayoutManager linearLayoutManager;
    private HeaderAdapter mHeaderAdapter;
    private boolean mHeaderViewStatus;
    private View suspensionView;
    private int suspensionViewHeight;
    private int suspensionViewWidth;

    /* loaded from: classes5.dex */
    public interface HeaderAdapter {
        public static final int PINNED_HEADER_GONE = 0;
        public static final int PINNED_HEADER_PUSHED_UP = 2;
        public static final int PINNED_HEADER_VISIBLE = 1;

        void configureHeader(View view, int i2, int i3, boolean z);

        int getHeaderState(int i2);
    }

    public CartRecyclerView(Context context) {
        super(context);
        this.isEditStatus = false;
        this.isOpenFloatShop = true;
    }

    public void configureHeaderView(int i2, boolean z) {
        if (OKLog.D) {
            OKLog.i(TAG, " configureHeaderView ---> suspensionView : " + this.suspensionView);
            OKLog.d(TAG, " configureHeaderView ---> mHeaderAdapter : " + this.mHeaderAdapter);
        }
        if (this.suspensionView == null || this.mHeaderAdapter == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, " configureHeaderView ---> position : " + i2);
        }
        if (i2 < 0) {
            this.mHeaderViewStatus = false;
            this.suspensionView.layout(0, -this.suspensionViewHeight, this.suspensionViewWidth, 0);
            return;
        }
        int headerState = this.mHeaderAdapter.getHeaderState(i2);
        if (OKLog.D) {
            OKLog.d(TAG, " configureHeaderView ---> headerState : " + headerState);
        }
        if (headerState == 0) {
            this.mHeaderViewStatus = false;
        } else if (headerState != 1) {
            if (headerState != 2) {
                return;
            }
            int bottom = getChildAt(0).getBottom();
            int height = this.suspensionView.getHeight();
            int i3 = bottom < height ? bottom - height : 0;
            if (OKLog.D) {
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_PUSHED_UP---> bottom : " + bottom);
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_PUSHED_UP---> headerHeight : " + height);
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_PUSHED_UP---> suspensionViewHeight : " + this.suspensionViewHeight);
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_PUSHED_UP---> y : " + i3);
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_PUSHED_UP---> getTop : " + this.suspensionView.getTop());
            }
            this.mHeaderAdapter.configureHeader(this.suspensionView, i2, 0, true);
            if (this.suspensionView.getTop() != i3) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.suspensionView.getLayoutParams();
                layoutParams.topMargin = i3;
                layoutParams.bottomMargin = this.suspensionViewHeight + i3;
                requestLayout();
            }
            this.mHeaderViewStatus = true;
        } else {
            if (OKLog.D) {
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_VISIBLE---> getTop : " + this.suspensionView.getTop());
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_VISIBLE---> getY : " + this.suspensionView.getY());
            }
            this.mHeaderAdapter.configureHeader(this.suspensionView, i2, 0, z);
            if (this.suspensionView.getTop() != 0) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.suspensionView.getLayoutParams();
                layoutParams2.topMargin = 0;
                layoutParams2.bottomMargin = this.suspensionViewHeight;
                requestLayout();
                this.mHeaderViewStatus = true;
                return;
            }
            int bottom2 = getChildAt(0).getBottom();
            int height2 = this.suspensionView.getHeight();
            if (OKLog.D) {
                OKLog.d(TAG, " configureHeaderView PINNED_HEADER_VISIBLE---> bottom : " + bottom2);
            }
            this.mHeaderViewStatus = true;
            if (i2 == 0 && bottom2 == height2) {
                if (OKLog.D) {
                    OKLog.i(TAG, " configureHeaderView PINNED_HEADER_VISIBLE---> == : ");
                }
                this.mHeaderViewStatus = false;
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.suspensionView.getLayoutParams();
                layoutParams3.topMargin = -this.suspensionViewHeight;
                layoutParams3.bottomMargin = 0;
                requestLayout();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mHeaderViewStatus && !this.isEditStatus) {
            if (OKLog.D) {
                OKLog.e(TAG, " dispatchDraw ---> getVisibility() : " + this.suspensionView.getVisibility());
            }
            View view = this.suspensionView;
            if (view == null || view.getVisibility() != 8) {
                return;
            }
            this.suspensionView.setVisibility(0);
            return;
        }
        View view2 = this.suspensionView;
        if (view2 == null || view2.getVisibility() != 0) {
            return;
        }
        this.suspensionView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.suspensionView != null) {
            int findFirstVisibleItemPosition = this.linearLayoutManager.findFirstVisibleItemPosition();
            if (OKLog.D) {
                OKLog.i(TAG, " onLayout ---> firstVisibleItemPosition : " + findFirstVisibleItemPosition);
            }
            if (findFirstVisibleItemPosition < 0) {
                this.mHeaderViewStatus = false;
                this.suspensionView.layout(0, -this.suspensionViewHeight, this.suspensionViewWidth, 0);
                return;
            }
            this.mHeaderViewStatus = true;
            this.suspensionView.layout(0, 0, this.suspensionViewWidth, this.suspensionViewHeight);
            configureHeaderView(findFirstVisibleItemPosition, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        View view = this.suspensionView;
        if (view != null) {
            measureChild(view, i2, i3);
            this.suspensionViewWidth = this.suspensionView.getMeasuredWidth();
            this.suspensionViewHeight = this.suspensionView.getMeasuredHeight();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof HeaderAdapter) {
            this.mHeaderAdapter = (HeaderAdapter) adapter;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        super.setLayoutManager(layoutManager);
        if (layoutManager instanceof LinearLayoutManager) {
            this.linearLayoutManager = (LinearLayoutManager) layoutManager;
        }
    }

    public void setSuspensionView(View view) {
        this.suspensionView = view;
    }

    public CartRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isEditStatus = false;
        this.isOpenFloatShop = true;
    }
}
