package com.jingdong.common.unification.jdbottomdrawer.content;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.ListView;
import com.jingdong.common.unification.jdbottomdrawer.JDBottomDrawer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class ContentListView extends ListView {
    private final CompositeScrollListener compositeScrollListener;
    private View shadowView;
    private boolean showShadow;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class CompositeScrollListener implements AbsListView.OnScrollListener {
        private final List<AbsListView.OnScrollListener> scrollListenerList;

        private CompositeScrollListener() {
            this.scrollListenerList = new ArrayList();
        }

        public void addOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
            if (onScrollListener == null) {
                return;
            }
            Iterator<AbsListView.OnScrollListener> it = this.scrollListenerList.iterator();
            while (it.hasNext()) {
                if (onScrollListener == it.next()) {
                    return;
                }
            }
            this.scrollListenerList.add(onScrollListener);
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
            Iterator it = new ArrayList(this.scrollListenerList).iterator();
            while (it.hasNext()) {
                ((AbsListView.OnScrollListener) it.next()).onScroll(absListView, i2, i3, i4);
            }
        }

        @Override // android.widget.AbsListView.OnScrollListener
        public void onScrollStateChanged(AbsListView absListView, int i2) {
            Iterator it = new ArrayList(this.scrollListenerList).iterator();
            while (it.hasNext()) {
                ((AbsListView.OnScrollListener) it.next()).onScrollStateChanged(absListView, i2);
            }
        }

        public void removeOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
            if (onScrollListener == null) {
                return;
            }
            Iterator<AbsListView.OnScrollListener> it = this.scrollListenerList.iterator();
            while (it.hasNext()) {
                if (onScrollListener == it.next()) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public ContentListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        CompositeScrollListener compositeScrollListener = new CompositeScrollListener();
        this.compositeScrollListener = compositeScrollListener;
        this.showShadow = false;
        super.setOnScrollListener(compositeScrollListener);
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

    public void addOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        throwIfNotOnMainThread();
        this.compositeScrollListener.addOnScrollListener(onScrollListener);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof JDBottomDrawer) {
                ((JDBottomDrawer) parent).setAssociatedListView(this);
                return;
            }
        }
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void removeOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        throwIfNotOnMainThread();
        this.compositeScrollListener.removeOnScrollListener(onScrollListener);
    }

    @Override // android.widget.AbsListView
    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        addOnScrollListener(onScrollListener);
    }

    public void setTopShadowView(View view) {
        if (view == null) {
            return;
        }
        this.shadowView = view;
        addOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.jingdong.common.unification.jdbottomdrawer.content.ContentListView.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
                View childAt = absListView.getChildAt(0);
                if (childAt != null) {
                    if (i2 != 0 || childAt.getTop() != 0) {
                        if (ContentListView.this.showShadow) {
                            return;
                        }
                        ContentListView.this.showShadow = true;
                        ContentListView.this.showTopShadow();
                        return;
                    }
                    ContentListView.this.showShadow = false;
                    ContentListView.this.showTopShadow();
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i2) {
            }
        });
    }

    public ContentListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        CompositeScrollListener compositeScrollListener = new CompositeScrollListener();
        this.compositeScrollListener = compositeScrollListener;
        this.showShadow = false;
        super.setOnScrollListener(compositeScrollListener);
    }

    public ContentListView(Context context) {
        super(context);
        CompositeScrollListener compositeScrollListener = new CompositeScrollListener();
        this.compositeScrollListener = compositeScrollListener;
        this.showShadow = false;
        super.setOnScrollListener(compositeScrollListener);
    }
}
