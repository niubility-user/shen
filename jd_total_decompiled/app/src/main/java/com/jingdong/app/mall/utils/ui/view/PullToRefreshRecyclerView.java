package com.jingdong.app.mall.utils.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/* loaded from: classes4.dex */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException
        */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        /*
            r4 = this;
            T extends android.view.View r0 = r4.mRefreshableView
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            r1 = 0
            if (r0 == 0) goto L35
            T extends android.view.View r0 = r4.mRefreshableView
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.getAdapter()
            if (r0 != 0) goto L16
            goto L35
        L16:
            T extends android.view.View r0 = r4.mRefreshableView     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> L35
            int r0 = r0.findLastCompletelyVisibleItemPosition()     // Catch: java.lang.Throwable -> L35
            T extends android.view.View r2 = r4.mRefreshableView     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.RecyclerView r2 = (androidx.recyclerview.widget.RecyclerView) r2     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = r2.getAdapter()     // Catch: java.lang.Throwable -> L35
            int r2 = r2.getItemCount()     // Catch: java.lang.Throwable -> L35
            r3 = 1
            int r2 = r2 - r3
            if (r0 != r2) goto L35
            r1 = 1
        L35:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.utils.ui.view.PullToRefreshRecyclerView.isReadyForPullEnd():boolean");
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException
        */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        /*
            r5 = this;
            T extends android.view.View r0 = r5.mRefreshableView
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            r1 = 0
            if (r0 == 0) goto L5e
            T extends android.view.View r0 = r5.mRefreshableView
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.getAdapter()
            if (r0 != 0) goto L16
            goto L5e
        L16:
            T extends android.view.View r0 = r5.mRefreshableView     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0     // Catch: java.lang.Throwable -> L5e
            android.view.View r0 = r0.getChildAt(r1)     // Catch: java.lang.Throwable -> L5e
            if (r0 != 0) goto L22
            r2 = 0
            goto L26
        L22:
            int r2 = r0.getHeight()     // Catch: java.lang.Throwable -> L5e
        L26:
            T extends android.view.View r3 = r5.mRefreshableView     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.RecyclerView r3 = (androidx.recyclerview.widget.RecyclerView) r3     // Catch: java.lang.Throwable -> L5e
            int r3 = r3.getHeight()     // Catch: java.lang.Throwable -> L5e
            r4 = 1
            if (r2 <= r3) goto L4d
            if (r0 != 0) goto L35
            r0 = 0
            goto L39
        L35:
            int r0 = r0.getTop()     // Catch: java.lang.Throwable -> L5e
        L39:
            if (r0 != 0) goto L4c
            T extends android.view.View r0 = r5.mRefreshableView     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> L5e
            int r0 = r0.findFirstVisibleItemPosition()     // Catch: java.lang.Throwable -> L5e
            if (r0 != 0) goto L4c
            r1 = 1
        L4c:
            return r1
        L4d:
            T extends android.view.View r0 = r5.mRefreshableView     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.RecyclerView r0 = (androidx.recyclerview.widget.RecyclerView) r0     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()     // Catch: java.lang.Throwable -> L5e
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> L5e
            int r0 = r0.findFirstCompletelyVisibleItemPosition()     // Catch: java.lang.Throwable -> L5e
            if (r0 != 0) goto L5e
            r1 = 1
        L5e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.utils.ui.view.PullToRefreshRecyclerView.isReadyForPullStart():boolean");
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    @TargetApi(4)
    public RecyclerView createRefreshableView(Context context, AttributeSet attributeSet) {
        return new RecyclerView(context);
    }

    public PullToRefreshRecyclerView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }
}
