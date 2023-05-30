package com.jingdong.common.utils;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes6.dex */
public class AdapterHelper {
    private AdapterView<Adapter> adapterView;
    private HttpGroup httpGroup;
    private Map<View, Map<Integer, View>> itemView_subViews_map = new WeakHashMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class ChildViewInfo {
        private Integer childCount;
        private Integer childCountWithOutHeaderViews;
        private Integer firstVisiblePosition;
        private Integer firstVisiblePositionWithOutHeaderViews;
        private Integer headerViewsCount;
        private Integer visibleHeaderViewsCount;

        public ChildViewInfo(AdapterView<Adapter> adapterView) {
            this.firstVisiblePosition = null;
            this.childCount = null;
            this.headerViewsCount = null;
            this.firstVisiblePositionWithOutHeaderViews = null;
            this.childCountWithOutHeaderViews = null;
            this.visibleHeaderViewsCount = null;
            Adapter adapter = adapterView.getAdapter();
            this.firstVisiblePosition = Integer.valueOf(adapterView.getFirstVisiblePosition());
            this.childCount = Integer.valueOf(adapterView.getChildCount());
            if (adapter == null || !(adapter instanceof HeaderViewListAdapter)) {
                return;
            }
            this.headerViewsCount = Integer.valueOf(((HeaderViewListAdapter) adapter).getHeadersCount());
            Integer valueOf = Integer.valueOf(this.firstVisiblePosition.intValue() - this.headerViewsCount.intValue());
            this.firstVisiblePositionWithOutHeaderViews = valueOf;
            if (valueOf.intValue() < 0) {
                this.firstVisiblePositionWithOutHeaderViews = 0;
            }
            Integer valueOf2 = Integer.valueOf(this.headerViewsCount.intValue() - this.firstVisiblePosition.intValue());
            this.visibleHeaderViewsCount = valueOf2;
            if (valueOf2.intValue() > 0) {
                this.visibleHeaderViewsCount = Integer.valueOf(Math.min(this.childCount.intValue(), this.visibleHeaderViewsCount.intValue()));
                this.childCountWithOutHeaderViews = Integer.valueOf(this.childCount.intValue() - this.visibleHeaderViewsCount.intValue());
                return;
            }
            this.visibleHeaderViewsCount = 0;
            this.childCountWithOutHeaderViews = this.childCount;
        }
    }

    public static Integer getItemViewIndex(int i2, int i3, int i4) {
        int i5 = i4 - i2;
        if (i5 < 0 || i5 >= i3) {
            return null;
        }
        return Integer.valueOf(i5);
    }

    private Map<Integer, View> getSubViews(View view) {
        return this.itemView_subViews_map.get(view);
    }

    public void clean() {
        this.itemView_subViews_map.clear();
    }

    public AdapterView<Adapter> getAdapterView() {
        return this.adapterView;
    }

    public HttpGroup getImageHttpGroup() {
        if (this.httpGroup == null) {
            this.httpGroup = HttpGroupUtils.getHttpGroupaAsynPool(5000);
        }
        return this.httpGroup;
    }

    public View getItemView(int i2, boolean z) {
        boolean z2 = z && (this.adapterView.getAdapter() instanceof HeaderViewListAdapter);
        ChildViewInfo childViewInfo = new ChildViewInfo(this.adapterView);
        if (OKLog.D) {
            OKLog.d(AdapterHelper.class.getName(), "getItemView() firstVisiblePosition -->> " + childViewInfo.firstVisiblePosition);
            OKLog.d(AdapterHelper.class.getName(), "getItemView() childCount -->> " + childViewInfo.childCount);
            OKLog.d(AdapterHelper.class.getName(), "getItemView() firstVisiblePositionWithOutHeaderViews -->> " + childViewInfo.firstVisiblePositionWithOutHeaderViews);
            OKLog.d(AdapterHelper.class.getName(), "getItemView() childCountWithOutHeaderViews -->> " + childViewInfo.childCountWithOutHeaderViews);
        }
        Integer itemViewIndex = getItemViewIndex((z2 ? childViewInfo.firstVisiblePositionWithOutHeaderViews : childViewInfo.firstVisiblePosition).intValue(), (z2 ? childViewInfo.childCountWithOutHeaderViews : childViewInfo.childCount).intValue(), i2);
        if (itemViewIndex != null) {
            int intValue = itemViewIndex.intValue();
            if (z2) {
                intValue += childViewInfo.visibleHeaderViewsCount.intValue();
            }
            return this.adapterView.getChildAt(Integer.valueOf(intValue).intValue());
        }
        return null;
    }

    public View getSubView(View view, int i2) {
        if (OKLog.D) {
            OKLog.d("Temp", "getSubViews itemView -->> " + view);
            OKLog.d("Temp", "getSubViews(itemView) -->> " + getSubViews(view));
        }
        return getSubViews(view).get(Integer.valueOf(i2));
    }

    public void putSubViews(View view, Map<Integer, View> map) {
        this.itemView_subViews_map.put(view, map);
    }

    public void setAdapterView(AdapterView<Adapter> adapterView) {
        this.adapterView = adapterView;
    }
}
