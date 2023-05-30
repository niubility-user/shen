package com.jd.lib.un.basewidget.widget.flow;

import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes16.dex */
public abstract class TagAdapter<T> {
    @Deprecated
    private HashSet<Integer> mCheckedPosList = new HashSet<>();
    private OnDataChangedListener mOnDataChangedListener;
    private List<T> mTagDatas;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public interface OnDataChangedListener {
        void onChanged();
    }

    public TagAdapter(List<T> list) {
        this.mTagDatas = list;
    }

    public int getCount() {
        List<T> list = this.mTagDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public T getItem(int i2) {
        return this.mTagDatas.get(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public HashSet<Integer> getPreCheckedList() {
        return this.mCheckedPosList;
    }

    public abstract View getView(FlowLayout flowLayout, int i2, T t);

    public void notifyDataChanged() {
        OnDataChangedListener onDataChangedListener = this.mOnDataChangedListener;
        if (onDataChangedListener != null) {
            onDataChangedListener.onChanged();
        }
    }

    public void onSelected(int i2, View view) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnDataChangedListener(OnDataChangedListener onDataChangedListener) {
        this.mOnDataChangedListener = onDataChangedListener;
    }

    public boolean setSelected(int i2, T t) {
        return false;
    }

    @Deprecated
    public void setSelectedList(int... iArr) {
        HashSet hashSet = new HashSet();
        for (int i2 : iArr) {
            hashSet.add(Integer.valueOf(i2));
        }
        setSelectedList(hashSet);
    }

    public void unSelected(int i2, View view) {
    }

    @Deprecated
    public TagAdapter(T[] tArr) {
        this.mTagDatas = new ArrayList(Arrays.asList(tArr));
    }

    @Deprecated
    public void setSelectedList(Set<Integer> set) {
        this.mCheckedPosList.clear();
        if (set != null) {
            this.mCheckedPosList.addAll(set);
        }
        notifyDataChanged();
    }
}
