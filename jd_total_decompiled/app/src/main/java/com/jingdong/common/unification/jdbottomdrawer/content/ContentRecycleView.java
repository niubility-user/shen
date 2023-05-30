package com.jingdong.common.unification.jdbottomdrawer.content;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class ContentRecycleView extends RecyclerView {
    private final CompositeScrollListener compositeScrollListener;
    private View shadowView;
    private boolean showShadow;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class CompositeScrollListener extends RecyclerView.OnScrollListener {
        private final List<RecyclerView.OnScrollListener> scrollListenerList;

        private CompositeScrollListener() {
            this.scrollListenerList = new ArrayList();
        }

        public void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
            if (onScrollListener == null) {
                return;
            }
            Iterator<RecyclerView.OnScrollListener> it = this.scrollListenerList.iterator();
            while (it.hasNext()) {
                if (onScrollListener == it.next()) {
                    return;
                }
            }
            this.scrollListenerList.add(onScrollListener);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            Iterator it = new ArrayList(this.scrollListenerList).iterator();
            while (it.hasNext()) {
                ((RecyclerView.OnScrollListener) it.next()).onScrollStateChanged(recyclerView, i2);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
            Iterator it = new ArrayList(this.scrollListenerList).iterator();
            while (it.hasNext()) {
                ((RecyclerView.OnScrollListener) it.next()).onScrolled(recyclerView, i2, i3);
            }
        }

        public void removeOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
            if (onScrollListener == null) {
                return;
            }
            Iterator<RecyclerView.OnScrollListener> it = this.scrollListenerList.iterator();
            while (it.hasNext()) {
                if (onScrollListener == it.next()) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public ContentRecycleView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        CompositeScrollListener compositeScrollListener = new CompositeScrollListener();
        this.compositeScrollListener = compositeScrollListener;
        this.showShadow = false;
        super.addOnScrollListener(compositeScrollListener);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.content.ContentRecycleView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = ContentRecycleView.this.getLayoutParams();
                ViewParent parent = ContentRecycleView.this.getParent();
                while (true) {
                    if (parent == null) {
                        break;
                    } else if (parent instanceof JDBottomDrawer) {
                        JDBottomDrawer jDBottomDrawer = (JDBottomDrawer) parent;
                        int measuredHeight = jDBottomDrawer.getMeasuredHeight() - jDBottomDrawer.minOffset;
                        if (layoutParams.height == measuredHeight) {
                            return;
                        }
                        layoutParams.height = measuredHeight;
                    } else {
                        parent = parent.getParent();
                    }
                }
                ContentRecycleView.this.setLayoutParams(layoutParams);
            }
        });
    }

    private void hideTopShadow() {
        View view = this.shadowView;
        if (view == null || view.getVisibility() == 8) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTopShadow() {
        View view = this.shadowView;
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        this.shadowView.setVisibility(0);
    }

    private void throwIfNotOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("Must be invoked from the main thread.");
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        throwIfNotOnMainThread();
        this.compositeScrollListener.addOnScrollListener(onScrollListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof JDBottomDrawer) {
                ((JDBottomDrawer) parent).setAssociatedRecycleView(this);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void removeOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        throwIfNotOnMainThread();
        this.compositeScrollListener.removeOnScrollListener(onScrollListener);
    }

    public void setTopShadowView(View view) {
        if (view == null) {
            return;
        }
        this.shadowView = view;
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.content.ContentRecycleView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                View childAt = recyclerView.getChildAt(0);
                if (childAt != null) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        if (((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() != 0 || childAt.getTop() != 0) {
                            if (ContentRecycleView.this.showShadow) {
                                return;
                            }
                            ContentRecycleView.this.showShadow = true;
                            ContentRecycleView.this.showTopShadow();
                            return;
                        }
                        ContentRecycleView.this.showShadow = false;
                        ContentRecycleView.this.showTopShadow();
                    }
                }
            }
        });
    }

    public ContentRecycleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        CompositeScrollListener compositeScrollListener = new CompositeScrollListener();
        this.compositeScrollListener = compositeScrollListener;
        this.showShadow = false;
        super.addOnScrollListener(compositeScrollListener);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.content.ContentRecycleView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = ContentRecycleView.this.getLayoutParams();
                ViewParent parent = ContentRecycleView.this.getParent();
                while (true) {
                    if (parent == null) {
                        break;
                    } else if (parent instanceof JDBottomDrawer) {
                        JDBottomDrawer jDBottomDrawer = (JDBottomDrawer) parent;
                        int measuredHeight = jDBottomDrawer.getMeasuredHeight() - jDBottomDrawer.minOffset;
                        if (layoutParams.height == measuredHeight) {
                            return;
                        }
                        layoutParams.height = measuredHeight;
                    } else {
                        parent = parent.getParent();
                    }
                }
                ContentRecycleView.this.setLayoutParams(layoutParams);
            }
        });
    }

    public ContentRecycleView(Context context) {
        super(context);
        CompositeScrollListener compositeScrollListener = new CompositeScrollListener();
        this.compositeScrollListener = compositeScrollListener;
        this.showShadow = false;
        super.addOnScrollListener(compositeScrollListener);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.content.ContentRecycleView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ViewGroup.LayoutParams layoutParams = ContentRecycleView.this.getLayoutParams();
                ViewParent parent = ContentRecycleView.this.getParent();
                while (true) {
                    if (parent == null) {
                        break;
                    } else if (parent instanceof JDBottomDrawer) {
                        JDBottomDrawer jDBottomDrawer = (JDBottomDrawer) parent;
                        int measuredHeight = jDBottomDrawer.getMeasuredHeight() - jDBottomDrawer.minOffset;
                        if (layoutParams.height == measuredHeight) {
                            return;
                        }
                        layoutParams.height = measuredHeight;
                    } else {
                        parent = parent.getParent();
                    }
                }
                ContentRecycleView.this.setLayoutParams(layoutParams);
            }
        });
    }
}
