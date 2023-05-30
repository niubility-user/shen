package com.jingdong.common.utils.personal.platform.floorframe;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.sdk.jdcrashreport.JdCrashReport;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 +2\u00020\u0001:\u0002,+B-\b\u0016\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001f\u0012\u0006\u0010!\u001a\u00020\u0010\u0012\u0006\u0010\"\u001a\u00020\u0010\u00a2\u0006\u0004\b#\u0010$B\u001b\b\u0016\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\u0006\u0010%\u001a\u00020\u0010\u00a2\u0006\u0004\b#\u0010&B+\b\u0016\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\u0006\u0010%\u001a\u00020\u0010\u0012\u0006\u0010'\u001a\u00020\u0010\u0012\u0006\u0010)\u001a\u00020(\u00a2\u0006\u0004\b#\u0010*J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J'\u0010\f\u001a\u00020\u00042\f\u0010\t\u001a\b\u0018\u00010\u0007R\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016\u00a2\u0006\u0004\b\f\u0010\rJ5\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\n\u0010\t\u001a\u00060\u0007R\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\f\u0010\t\u001a\b\u0018\u00010\u0007R\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016\u00a2\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00102\f\u0010\t\u001a\b\u0018\u00010\u0007R\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0016J)\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001a\u001a\u00020\u0010H\u0016\u00a2\u0006\u0004\b\u001b\u0010\u001c\u00a8\u0006-"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/GridLayoutManagerWrapper;", "Landroidx/recyclerview/widget/GridLayoutManager;", "Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;", "spanSizeLookup", "", "setSpanSizeLookup", "(Landroidx/recyclerview/widget/GridLayoutManager$SpanSizeLookup;)V", "Landroidx/recyclerview/widget/RecyclerView$Recycler;", "Landroidx/recyclerview/widget/RecyclerView;", "recycler", "Landroidx/recyclerview/widget/RecyclerView$State;", XView2Constants.STATE, "onLayoutChildren", "(Landroidx/recyclerview/widget/RecyclerView$Recycler;Landroidx/recyclerview/widget/RecyclerView$State;)V", "Landroid/view/View;", "focused", "", "focusDirection", "onFocusSearchFailed", "(Landroid/view/View;ILandroidx/recyclerview/widget/RecyclerView$Recycler;Landroidx/recyclerview/widget/RecyclerView$State;)Landroid/view/View;", "dx", "scrollHorizontallyBy", "(ILandroidx/recyclerview/widget/RecyclerView$Recycler;Landroidx/recyclerview/widget/RecyclerView$State;)I", "dy", "scrollVerticallyBy", "recyclerView", "position", "smoothScrollToPosition", "(Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$State;I)V", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Landroid/util/AttributeSet;", "attrs", "defStyleAttr", "defStyleRes", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "spanCount", "(Landroid/content/Context;I)V", MBaseKeyNames.KEY_ORIENTATION, "", "reverseLayout", "(Landroid/content/Context;IIZ)V", "Companion", "CenterSmoothScroller", "personallib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes6.dex */
public final class GridLayoutManagerWrapper extends GridLayoutManager {
    public static final int SPAN_COUNT = 2;
    private static final String TAG = "GridLayoutManagerWrapper";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J7\u0010\f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0014\u00a2\u0006\u0004\b\u0011\u0010\u0012\u00a8\u0006\u0017"}, d2 = {"Lcom/jingdong/common/utils/personal/platform/floorframe/GridLayoutManagerWrapper$CenterSmoothScroller;", "Landroidx/recyclerview/widget/LinearSmoothScroller;", "", "targetPosition", "Landroid/graphics/PointF;", "computeScrollVectorForPosition", "(I)Landroid/graphics/PointF;", "viewStart", "viewEnd", "boxStart", "boxEnd", "snapPreference", "calculateDtToFit", "(IIIII)I", "Landroid/util/DisplayMetrics;", "displayMetrics", "", "calculateSpeedPerPixel", "(Landroid/util/DisplayMetrics;)F", "Landroid/content/Context;", AnnoConst.Constructor_Context, "<init>", "(Lcom/jingdong/common/utils/personal/platform/floorframe/GridLayoutManagerWrapper;Landroid/content/Context;)V", "personallib"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes6.dex */
    private final class CenterSmoothScroller extends LinearSmoothScroller {
        public CenterSmoothScroller(@Nullable Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            return (boxStart + ((boxEnd - boxStart) / 2)) - (viewStart + ((viewEnd - viewStart) / 2));
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected float calculateSpeedPerPixel(@NotNull DisplayMetrics displayMetrics) {
            return 0.2f;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        @Nullable
        public PointF computeScrollVectorForPosition(int targetPosition) {
            return GridLayoutManagerWrapper.this.computeScrollVectorForPosition(targetPosition);
        }
    }

    public GridLayoutManagerWrapper(@Nullable Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    @Nullable
    public View onFocusSearchFailed(@NotNull View focused, int focusDirection, @NotNull RecyclerView.Recycler recycler, @NotNull RecyclerView.State state) {
        try {
            return super.onFocusSearchFailed(focused, focusDirection, recycler, state);
        } catch (Throwable th) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("GridLayoutManagerWrapper onFocusSearchFailed error", th));
            return null;
        }
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(@Nullable RecyclerView.Recycler recycler, @Nullable RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Throwable th) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("GridLayoutManagerWrapper onLayoutChildren error", th));
        }
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int dx, @Nullable RecyclerView.Recycler recycler, @Nullable RecyclerView.State state) {
        try {
            return super.scrollHorizontallyBy(dx, recycler, state);
        } catch (Throwable th) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("GridLayoutManagerWrapper scrollHorizontallyBy error", th));
            return 0;
        }
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int dy, @Nullable RecyclerView.Recycler recycler, @Nullable RecyclerView.State state) {
        try {
            return super.scrollVerticallyBy(dy, recycler, state);
        } catch (Throwable th) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("GridLayoutManagerWrapper scrollVerticallyBy error", th));
            return 0;
        }
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager
    public void setSpanSizeLookup(@Nullable GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        super.setSpanSizeLookup(spanSizeLookup);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(@NotNull RecyclerView recyclerView, @Nullable RecyclerView.State state, int position) {
        try {
            CenterSmoothScroller centerSmoothScroller = new CenterSmoothScroller(recyclerView.getContext());
            centerSmoothScroller.setTargetPosition(position);
            startSmoothScroll(centerSmoothScroller);
        } catch (Throwable th) {
            JdCrashReport.postCaughtException(new IllegalArgumentException("GridLayoutManagerWrapper smoothScrollToPosition error", th));
        }
    }

    public GridLayoutManagerWrapper(@Nullable Context context, int i2) {
        super(context, i2);
    }

    public GridLayoutManagerWrapper(@Nullable Context context, int i2, int i3, boolean z) {
        super(context, i2, i3, z);
    }
}
