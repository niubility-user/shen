package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.os.Parcelable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.app.mall.home.o.a.e;

/* loaded from: classes4.dex */
public class IconLineRecyclerView extends RecyclerView {

    /* renamed from: g  reason: collision with root package name */
    private LineLayoutManager f10096g;

    /* loaded from: classes4.dex */
    public class LineLayoutManager extends StaggeredGridLayoutManager {
        Parcelable a;

        public LineLayoutManager(IconLineRecyclerView iconLineRecyclerView, int i2, int i3) {
            super(i2, i3);
            this.a = null;
        }

        public void a() {
            this.a = null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onAttachedToWindow(RecyclerView recyclerView) {
            Parcelable parcelable = this.a;
            if (parcelable != null) {
                onRestoreInstanceState(parcelable);
            }
            super.onAttachedToWindow(recyclerView);
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
            this.a = onSaveInstanceState();
            super.onDetachedFromWindow(recyclerView, recycler);
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                return super.scrollVerticallyBy(i2, recycler, state);
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        }
    }

    public IconLineRecyclerView(Context context, int i2) {
        super(context);
        setNestedScrollingEnabled(false);
        LineLayoutManager lineLayoutManager = new LineLayoutManager(this, i2, 0);
        this.f10096g = lineLayoutManager;
        lineLayoutManager.setAutoMeasureEnabled(true);
        setLayoutManager(this.f10096g);
        e.a(this);
    }

    public void a() {
        scrollToPosition(0);
        this.f10096g.a();
    }

    public void b(int i2) {
        this.f10096g.setSpanCount(Math.max(i2, 1));
    }

    public int getFirstVisibleItem() {
        int[] iArr = new int[2];
        this.f10096g.findFirstVisibleItemPositions(iArr);
        return Math.min(iArr[0], iArr[1]);
    }

    public int getLastVisibleItem() {
        int[] iArr = new int[2];
        this.f10096g.findLastVisibleItemPositions(iArr);
        return Math.max(iArr[0], iArr[1]);
    }
}
