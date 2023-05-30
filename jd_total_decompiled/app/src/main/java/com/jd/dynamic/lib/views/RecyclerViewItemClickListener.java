package com.jd.dynamic.lib.views;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes13.dex */
public class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {
    private GestureDetector a;
    private ClickListener b;

    public RecyclerViewItemClickListener(Context context, final CollectionView collectionView, final ClickListener clickListener) {
        this.b = clickListener;
        this.a = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(this) { // from class: com.jd.dynamic.lib.views.RecyclerViewItemClickListener.1
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                ClickListener clickListener2;
                View findChildViewUnder = collectionView.getRecyclerView().findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (findChildViewUnder == null || (clickListener2 = clickListener) == null) {
                    return;
                }
                clickListener2.onLongClick(findChildViewUnder, collectionView.getRecyclerView().getChildAdapterPosition(findChildViewUnder), motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return true;
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        View findChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (findChildViewUnder == null || this.b == null || !this.a.onTouchEvent(motionEvent)) {
            return false;
        }
        this.b.onClick(findChildViewUnder, recyclerView.getChildAdapterPosition(findChildViewUnder), motionEvent);
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
    }
}
