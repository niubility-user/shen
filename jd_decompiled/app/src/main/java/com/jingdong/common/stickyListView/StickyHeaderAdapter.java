package com.jingdong.common.stickyListView;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.jingdong.common.stickyListView.StickyHeaderListView;
import java.util.List;

/* loaded from: classes6.dex */
public abstract class StickyHeaderAdapter extends BaseAdapter implements StickyHeaderListView.IStickyHeaderAdapter {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    protected List<Integer> headers;
    private boolean hasHeader = false;
    private int itemTypeCount = 1;
    protected SparseIntArray realToDataMap = new SparseIntArray();
    protected SparseIntArray dataTorealMap = new SparseIntArray();

    @Override // com.jingdong.common.stickyListView.StickyHeaderListView.IStickyHeaderAdapter
    public void changeHeaderStatus(boolean z) {
        if (this.hasHeader == z) {
            return;
        }
        this.hasHeader = z;
        if (z) {
            this.headers = getHeaders();
            this.itemTypeCount = 2;
            return;
        }
        List<Integer> list = this.headers;
        if (list != null) {
            list.clear();
        }
        this.itemTypeCount = 1;
    }

    @Override // com.jingdong.common.stickyListView.StickyHeaderListView.IStickyHeaderAdapter
    public int changeToDataPosition(int i2) {
        List<Integer> list;
        return (!this.hasHeader || (list = this.headers) == null || list.isEmpty()) ? i2 : this.realToDataMap.get(i2, -1);
    }

    @Override // com.jingdong.common.stickyListView.StickyHeaderListView.IStickyHeaderAdapter
    public int changeToRealPosition(int i2) {
        List<Integer> list;
        return (!this.hasHeader || (list = this.headers) == null || list.isEmpty()) ? i2 : this.dataTorealMap.get(i2, -1);
    }

    public abstract View getHeaderView(int i2, View view, ViewGroup viewGroup);

    @Override // com.jingdong.common.stickyListView.StickyHeaderListView.IStickyHeaderAdapter
    public View getHeaderViewOrNull(int i2, View view, ViewGroup viewGroup) {
        if (isHeaderView(i2)) {
            return getView(i2, null, viewGroup);
        }
        return null;
    }

    public abstract List<Integer> getHeaders();

    public abstract View getItemView(int i2, View view, ViewGroup viewGroup);

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        return !isHeaderView(i2);
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        if (isHeaderView(i2)) {
            return getHeaderView(i2, view, viewGroup);
        }
        return getItemView(i2, view, viewGroup);
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return this.itemTypeCount;
    }

    @Override // com.jingdong.common.stickyListView.StickyHeaderListView.IStickyHeaderAdapter
    public boolean isHeaderView(int i2) {
        List<Integer> list;
        return (!this.hasHeader || (list = this.headers) == null || list.isEmpty() || this.headers.indexOf(Integer.valueOf(i2)) == -1) ? false : true;
    }
}
