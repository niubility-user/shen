package com.jingdong.common.listui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes5.dex */
public class WrapRecyclerView extends RecyclerView {
    private ReadWriteLock footWriteLock;
    private ReadWriteLock headReadWriteLock;
    private WrapRecyclerAdapter mAdapter;
    private ArrayList<View> mFootViews;
    private ArrayList<View> mHeaderViews;

    public WrapRecyclerView(Context context) {
        super(context);
        this.mHeaderViews = new ArrayList<>();
        this.mFootViews = new ArrayList<>();
        this.headReadWriteLock = new ReentrantReadWriteLock();
        this.footWriteLock = new ReentrantReadWriteLock();
    }

    public void addFootView(View view) {
        this.footWriteLock.writeLock().lock();
        this.mFootViews.add(view);
        this.footWriteLock.writeLock().unlock();
    }

    public void addHeaderView(View view) {
        this.headReadWriteLock.writeLock().lock();
        this.mHeaderViews.add(view);
        this.headReadWriteLock.writeLock().unlock();
    }

    public void cleanFootView() {
        this.footWriteLock.writeLock().lock();
        this.mFootViews.clear();
        this.footWriteLock.writeLock().unlock();
    }

    public void cleanHeaderView() {
        this.headReadWriteLock.writeLock().lock();
        this.mHeaderViews.clear();
        this.headReadWriteLock.writeLock().unlock();
    }

    public boolean containsFootView(View view) {
        this.footWriteLock.readLock().lock();
        boolean contains = this.mFootViews.contains(view);
        this.footWriteLock.readLock().unlock();
        return contains;
    }

    public boolean containsHeadView(View view) {
        this.headReadWriteLock.readLock().lock();
        boolean contains = this.mHeaderViews.contains(view);
        this.headReadWriteLock.readLock().unlock();
        return contains;
    }

    public WrapRecyclerAdapter getWrapAdapter() {
        return this.mAdapter;
    }

    public void removeFootView(View view) {
        this.footWriteLock.writeLock().lock();
        this.mFootViews.remove(view);
        this.footWriteLock.writeLock().unlock();
    }

    public void removeHeaderView(View view) {
        this.headReadWriteLock.writeLock().lock();
        this.mHeaderViews.remove(view);
        this.headReadWriteLock.writeLock().unlock();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        WrapRecyclerAdapter wrapRecyclerAdapter = new WrapRecyclerAdapter(this.mHeaderViews, this.mFootViews, adapter);
        this.mAdapter = wrapRecyclerAdapter;
        super.setAdapter(wrapRecyclerAdapter);
    }

    public WrapRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHeaderViews = new ArrayList<>();
        this.mFootViews = new ArrayList<>();
        this.headReadWriteLock = new ReentrantReadWriteLock();
        this.footWriteLock = new ReentrantReadWriteLock();
    }

    public WrapRecyclerView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHeaderViews = new ArrayList<>();
        this.mFootViews = new ArrayList<>();
        this.headReadWriteLock = new ReentrantReadWriteLock();
        this.footWriteLock = new ReentrantReadWriteLock();
    }
}
