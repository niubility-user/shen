package com.jingdong.app.mall.home.category.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jingdong.common.R;

/* loaded from: classes4.dex */
public class CaPullRefreshLayout extends PullToRefreshRecyclerView {
    private MotionEvent mCurrentEvent;
    private View mHeaderLayout;
    private View mRefreshText;

    public CaPullRefreshLayout(Context context) {
        super(context);
        setRefreshBgTransparent();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchCancelEvent() {
        try {
            MotionEvent motionEvent = this.mCurrentEvent;
            if (motionEvent != null && motionEvent.getAction() != 3 && this.mCurrentEvent.getAction() != 1) {
                MotionEvent obtain = MotionEvent.obtain(this.mCurrentEvent);
                obtain.setAction(3);
                dispatchTouchEvent(obtain);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.mCurrentEvent = motionEvent;
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setRefreshBgTransparent() {
        BaseLoadingLayout headerLayout = getHeaderLayout();
        if (headerLayout == null) {
            return;
        }
        this.mHeaderLayout = headerLayout.findViewById(R.id.pull_header_layout);
        this.mRefreshText = headerLayout.findViewById(R.id.tv_hint);
        View view = this.mHeaderLayout;
        if (view != null) {
            view.setBackgroundColor(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRefreshTextColor(int i2) {
        View view = this.mRefreshText;
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(i2);
        }
    }
}
