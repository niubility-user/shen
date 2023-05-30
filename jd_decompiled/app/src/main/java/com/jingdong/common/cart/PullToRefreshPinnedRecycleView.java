package com.jingdong.common.cart;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/* loaded from: classes5.dex */
public class PullToRefreshPinnedRecycleView extends PullToRefreshBase<CartRecyclerView> {
    private static final String TAG = "PullToRefreshPinnedRecycleView";

    public PullToRefreshPinnedRecycleView(Context context) {
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
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            r1 = 0
            if (r0 == 0) goto L35
            T extends android.view.View r0 = r4.mRefreshableView
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.getAdapter()
            if (r0 != 0) goto L16
            goto L35
        L16:
            T extends android.view.View r0 = r4.mRefreshableView     // Catch: java.lang.Throwable -> L35
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> L35
            int r0 = r0.findLastCompletelyVisibleItemPosition()     // Catch: java.lang.Throwable -> L35
            T extends android.view.View r2 = r4.mRefreshableView     // Catch: java.lang.Throwable -> L35
            com.jingdong.common.cart.CartRecyclerView r2 = (com.jingdong.common.cart.CartRecyclerView) r2     // Catch: java.lang.Throwable -> L35
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = r2.getAdapter()     // Catch: java.lang.Throwable -> L35
            int r2 = r2.getItemCount()     // Catch: java.lang.Throwable -> L35
            r3 = 1
            int r2 = r2 - r3
            if (r0 != r2) goto L35
            r1 = 1
        L35:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.cart.PullToRefreshPinnedRecycleView.isReadyForPullEnd():boolean");
    }

    /*  JADX ERROR: NullPointerException in pass: RegionMakerVisitor
        java.lang.NullPointerException
        */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        /*
            r6 = this;
            T extends android.view.View r0 = r6.mRefreshableView
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            r1 = 0
            if (r0 == 0) goto Lf5
            T extends android.view.View r0 = r6.mRefreshableView
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = r0.getAdapter()
            if (r0 != 0) goto L17
            goto Lf5
        L17:
            T extends android.view.View r0 = r6.mRefreshableView     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0     // Catch: java.lang.Throwable -> Lf5
            android.view.View r0 = r0.getChildAt(r1)     // Catch: java.lang.Throwable -> Lf5
            if (r0 != 0) goto L23
            r2 = 0
            goto L27
        L23:
            int r2 = r0.getHeight()     // Catch: java.lang.Throwable -> Lf5
        L27:
            boolean r3 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r4 = "PullToRefreshPinnedRecycleView"
            if (r3 == 0) goto L5d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lf5
            r3.<init>()     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r5 = " isReadyForPullStart ---> height : "
            r3.append(r5)     // Catch: java.lang.Throwable -> Lf5
            r3.append(r2)     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.sdk.oklog.OKLog.d(r4, r3)     // Catch: java.lang.Throwable -> Lf5
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lf5
            r3.<init>()     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r5 = " isReadyForPullStart ---> mRefreshableView.getHeight() : "
            r3.append(r5)     // Catch: java.lang.Throwable -> Lf5
            T extends android.view.View r5 = r6.mRefreshableView     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.common.cart.CartRecyclerView r5 = (com.jingdong.common.cart.CartRecyclerView) r5     // Catch: java.lang.Throwable -> Lf5
            int r5 = r5.getHeight()     // Catch: java.lang.Throwable -> Lf5
            r3.append(r5)     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.sdk.oklog.OKLog.d(r4, r3)     // Catch: java.lang.Throwable -> Lf5
        L5d:
            T extends android.view.View r3 = r6.mRefreshableView     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.common.cart.CartRecyclerView r3 = (com.jingdong.common.cart.CartRecyclerView) r3     // Catch: java.lang.Throwable -> Lf5
            int r3 = r3.getHeight()     // Catch: java.lang.Throwable -> Lf5
            r5 = 1
            if (r2 <= r3) goto Lbe
            if (r0 != 0) goto L6c
            r0 = 0
            goto L70
        L6c:
            int r0 = r0.getTop()     // Catch: java.lang.Throwable -> Lf5
        L70:
            boolean r2 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> Lf5
            if (r2 == 0) goto Laa
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lf5
            r2.<init>()     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r3 = " isReadyForPullStart ---> in  top : "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lf5
            r2.append(r0)     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.sdk.oklog.OKLog.d(r4, r2)     // Catch: java.lang.Throwable -> Lf5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lf5
            r2.<init>()     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r3 = " isReadyForPullStart ---> in  findFirstVisibleItemPosition : "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lf5
            T extends android.view.View r3 = r6.mRefreshableView     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.common.cart.CartRecyclerView r3 = (com.jingdong.common.cart.CartRecyclerView) r3     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.RecyclerView$LayoutManager r3 = r3.getLayoutManager()     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.LinearLayoutManager r3 = (androidx.recyclerview.widget.LinearLayoutManager) r3     // Catch: java.lang.Throwable -> Lf5
            int r3 = r3.findFirstVisibleItemPosition()     // Catch: java.lang.Throwable -> Lf5
            r2.append(r3)     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.sdk.oklog.OKLog.d(r4, r2)     // Catch: java.lang.Throwable -> Lf5
        Laa:
            if (r0 != 0) goto Lbd
            T extends android.view.View r0 = r6.mRefreshableView     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> Lf5
            int r0 = r0.findFirstVisibleItemPosition()     // Catch: java.lang.Throwable -> Lf5
            if (r0 != 0) goto Lbd
            r1 = 1
        Lbd:
            return r1
        Lbe:
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> Lf5
            if (r0 == 0) goto Le4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lf5
            r0.<init>()     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r2 = " isReadyForPullStart ---> findFirstCompletelyVisibleItemPosition : "
            r0.append(r2)     // Catch: java.lang.Throwable -> Lf5
            T extends android.view.View r2 = r6.mRefreshableView     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.common.cart.CartRecyclerView r2 = (com.jingdong.common.cart.CartRecyclerView) r2     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.RecyclerView$LayoutManager r2 = r2.getLayoutManager()     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.LinearLayoutManager r2 = (androidx.recyclerview.widget.LinearLayoutManager) r2     // Catch: java.lang.Throwable -> Lf5
            int r2 = r2.findFirstCompletelyVisibleItemPosition()     // Catch: java.lang.Throwable -> Lf5
            r0.append(r2)     // Catch: java.lang.Throwable -> Lf5
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.sdk.oklog.OKLog.d(r4, r0)     // Catch: java.lang.Throwable -> Lf5
        Le4:
            T extends android.view.View r0 = r6.mRefreshableView     // Catch: java.lang.Throwable -> Lf5
            com.jingdong.common.cart.CartRecyclerView r0 = (com.jingdong.common.cart.CartRecyclerView) r0     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()     // Catch: java.lang.Throwable -> Lf5
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0     // Catch: java.lang.Throwable -> Lf5
            int r0 = r0.findFirstCompletelyVisibleItemPosition()     // Catch: java.lang.Throwable -> Lf5
            if (r0 != 0) goto Lf5
            r1 = 1
        Lf5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.cart.PullToRefreshPinnedRecycleView.isReadyForPullStart():boolean");
    }

    public PullToRefreshPinnedRecycleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    @TargetApi(4)
    public CartRecyclerView createRefreshableView(Context context, AttributeSet attributeSet) {
        return new CartRecyclerView(context);
    }

    public PullToRefreshPinnedRecycleView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshPinnedRecycleView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }
}
