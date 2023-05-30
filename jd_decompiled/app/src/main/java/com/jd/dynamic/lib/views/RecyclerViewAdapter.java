package com.jd.dynamic.lib.views;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.handmark.pulltorefresh.library.JDLoadingMoreLayout;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.utils.u;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private JSONArray a;

    /* renamed from: c  reason: collision with root package name */
    private final CollectionView f2489c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private Map<String, Integer> f2490e = new HashMap();

    /* renamed from: f  reason: collision with root package name */
    private AtomicInteger f2491f = new AtomicInteger(100);

    /* renamed from: g  reason: collision with root package name */
    private final GridLayoutManager.SpanSizeLookup f2492g = new GridLayoutManager.SpanSizeLookup() { // from class: com.jd.dynamic.lib.views.RecyclerViewAdapter.1
        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i2) {
            if (RecyclerViewAdapter.this.getItemViewType(i2) == 2000 || RecyclerViewAdapter.this.getItemViewType(i2) == 3000) {
                return RecyclerViewAdapter.this.d;
            }
            return 1;
        }
    };
    private ArrayList<JSONObject> b = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(@NonNull View view) {
            super(view);
        }
    }

    /* loaded from: classes13.dex */
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(@NonNull View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        public LoadMoreViewHolder(@NonNull View view) {
            super(view);
        }
    }

    public RecyclerViewAdapter(CollectionView collectionView) {
        this.f2489c = collectionView;
        this.a = collectionView.getData();
    }

    private void h(View view, int i2) {
        DynamicTemplateEngine engine;
        if (3000 == i2 || 2000 == i2) {
            return;
        }
        View c2 = com.jd.dynamic.lib.utils.m.c(this.f2489c);
        if (c2 == null) {
            if (view != null) {
                int i3 = R.id.dynamic_parent_item_type;
                if (view.getTag(i3) == null) {
                    view.setTag(i3, Integer.valueOf(i2));
                    return;
                }
                return;
            }
            return;
        }
        RecyclerView recyclerView = this.f2489c.getRecyclerView();
        if (recyclerView == null || (engine = this.f2489c.getEngine()) == null) {
            return;
        }
        u.a b = engine.getRecyclerInnerCachePool().b(c2.getTag(R.id.dynamic_parent_item_type));
        if (b != null) {
            b.b(i2);
            recyclerView.setRecycledViewPool(b.a());
        }
    }

    private boolean l() {
        if (this.f2489c.getParent() instanceof YogaLayout) {
            return ((YogaLayout) this.f2489c.getParent()).isRootLayout;
        }
        return false;
    }

    private boolean m(View view) {
        CollectionView collectionView = this.f2489c;
        int i2 = R.id.dynamic_recyclerview_is_item;
        if (collectionView.getTag(i2) != null) {
            return true;
        }
        boolean z = com.jd.dynamic.lib.utils.m.c(view) != null;
        if (z) {
            this.f2489c.setTag(i2, com.jd.dynamic.b.c.a.b);
        }
        return z;
    }

    private int n() {
        if (this.b == null) {
            return 0;
        }
        int maxCount = this.f2489c.getMaxCount();
        int size = this.b.size();
        return maxCount == 0 ? size : Math.min(maxCount, size);
    }

    public JSONArray getData() {
        return this.a;
    }

    public List<JSONObject> getDataList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i2 = this.f2489c.hasHeader() ? 1 : 0;
        if (this.f2489c.hasLoadMore()) {
            i2++;
        }
        int n2 = n();
        if (n2 == 0) {
            return 0;
        }
        return n2 + i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        boolean hasHeader = this.f2489c.hasHeader();
        if (i2 == 0 && hasHeader) {
            return 2000;
        }
        if (this.f2489c.hasLoadMore() && i2 == this.b.size()) {
            return 3000;
        }
        if (hasHeader) {
            i2--;
        }
        JSONObject jSONObject = this.b.get(i2);
        if (jSONObject == null || this.f2489c.getMineNode() == null) {
            return 1000;
        }
        try {
            String str = this.f2489c.getMineNode().getAttributes().get(DYConstants.DY_ITEM_IDENTIFIER);
            if (TextUtils.isEmpty(str)) {
                str = DYConstants.DY_IDENTIFIER;
            }
            String optString = jSONObject.optString(str);
            if (TextUtils.isEmpty(optString)) {
                return 1000;
            }
            Integer num = this.f2490e.get(optString);
            if (num != null) {
                return num.intValue();
            }
            int parseInt = Integer.parseInt(optString);
            this.f2490e.put(optString, Integer.valueOf(parseInt));
            return parseInt;
        } catch (Exception unused) {
            int andDecrement = this.f2491f.getAndDecrement();
            this.f2490e.put(null, Integer.valueOf(andDecrement));
            return andDecrement;
        }
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookUp() {
        return this.f2492g;
    }

    public void notifyChange() {
        this.f2489c.getRecyclerView().setTag(R.id.dynamic_recyclerview_notify_tag, null);
        if (this.f2489c.getRecyclerView().hasPendingAdapterUpdates() || this.f2489c.getRecyclerView().isComputingLayout()) {
            this.f2489c.getRecyclerView().post(new Runnable() { // from class: com.jd.dynamic.lib.views.b0
                @Override // java.lang.Runnable
                public final void run() {
                    RecyclerViewAdapter.this.notifyDataSetChanged();
                }
            });
        } else {
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        JDLoadingMoreLayout.FooterState footerState;
        int i3 = this.f2489c.hasHeader() ? i2 - 1 : i2;
        com.jd.dynamic.lib.utils.t.e("onBindViewHolder", "id = " + this.f2489c.getId(), "position = " + i2);
        if (this.a == null || (viewHolder instanceof HeaderViewHolder)) {
            return;
        }
        if (!(viewHolder instanceof LoadMoreViewHolder)) {
            JSONObject jSONObject = this.b.get(i3);
            viewHolder.itemView.setTag(R.id.dynamic_item_position, Integer.valueOf(i3));
            if (this.f2489c.getEngine() == null || !Boolean.TRUE.equals(this.f2489c.getEngine().useAsyncItem)) {
                this.f2489c.getItemViewFromId(getItemViewType(i2), this.f2490e).bindData(viewHolder.itemView, jSONObject);
                return;
            } else {
                this.f2489c.getItemViewFromId(getItemViewType(i2), this.f2490e).bindDataWithCache(viewHolder.itemView, jSONObject);
                return;
            }
        }
        int loadMoreState = this.f2489c.getLoadMoreState();
        com.jd.dynamic.lib.utils.t.e("updateLoadMoreState", "onBindViewHolder2", Integer.valueOf(loadMoreState));
        LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) viewHolder;
        View view = loadMoreViewHolder.itemView;
        if (!(view instanceof ViewGroup) || ((ViewGroup) view).getChildCount() <= 0) {
            return;
        }
        View childAt = ((ViewGroup) loadMoreViewHolder.itemView).getChildAt(0);
        if (childAt instanceof JDLoadingMoreLayout) {
            JDLoadingMoreLayout jDLoadingMoreLayout = (JDLoadingMoreLayout) childAt;
            if (loadMoreState == 0) {
                footerState = JDLoadingMoreLayout.FooterState.RESET;
            } else if (loadMoreState == 1) {
                footerState = JDLoadingMoreLayout.FooterState.LOADING_SUCCESS;
            } else if (loadMoreState == 3) {
                footerState = JDLoadingMoreLayout.FooterState.LOADING_FAILED;
            } else if (loadMoreState == 4) {
                footerState = JDLoadingMoreLayout.FooterState.REACH_END;
            } else if (loadMoreState != 2) {
                return;
            } else {
                footerState = JDLoadingMoreLayout.FooterState.LOADING;
            }
            jDLoadingMoreLayout.setFootersState(footerState);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2, @NonNull List<Object> list) {
        JDLoadingMoreLayout.FooterState footerState;
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, i2);
        } else if (viewHolder instanceof LoadMoreViewHolder) {
            Object obj = list.get(0);
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                com.jd.dynamic.lib.utils.t.e("updateLoadMoreState", "onBindViewHolder", Integer.valueOf(intValue));
                LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) viewHolder;
                View view = loadMoreViewHolder.itemView;
                if (!(view instanceof ViewGroup) || ((ViewGroup) view).getChildCount() <= 0) {
                    return;
                }
                View childAt = ((ViewGroup) loadMoreViewHolder.itemView).getChildAt(0);
                if (childAt instanceof JDLoadingMoreLayout) {
                    JDLoadingMoreLayout jDLoadingMoreLayout = (JDLoadingMoreLayout) childAt;
                    if (intValue == 0) {
                        footerState = JDLoadingMoreLayout.FooterState.RESET;
                    } else if (intValue == 1) {
                        footerState = JDLoadingMoreLayout.FooterState.LOADING_SUCCESS;
                    } else if (intValue == 3) {
                        footerState = JDLoadingMoreLayout.FooterState.LOADING_FAILED;
                    } else if (intValue == 4) {
                        footerState = JDLoadingMoreLayout.FooterState.REACH_END;
                    } else if (intValue != 2) {
                        return;
                    } else {
                        footerState = JDLoadingMoreLayout.FooterState.LOADING;
                    }
                    jDLoadingMoreLayout.setFootersState(footerState);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d2  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @androidx.annotation.NonNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@androidx.annotation.NonNull android.view.ViewGroup r5, int r6) {
        /*
            r4 = this;
            java.util.ArrayList<org.json.JSONObject> r0 = r4.b
            if (r0 != 0) goto L6
            r5 = 0
            return r5
        L6:
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r6 != r0) goto L34
            com.jd.dynamic.lib.views.CollectionView r5 = r4.f2489c
            com.jd.dynamic.lib.views.ItemView r5 = r5.getHeaderView()
            android.view.View r5 = r5.parse()
            int r6 = com.jd.dynamic.R.id.dynamic_collection_view
            com.jd.dynamic.lib.views.CollectionView r0 = r4.f2489c
            r5.setTag(r6, r0)
            boolean r6 = r5 instanceof com.jd.dynamic.yoga.android.YogaLayout
            if (r6 == 0) goto L2e
            androidx.recyclerview.widget.RecyclerView$LayoutParams r6 = new androidx.recyclerview.widget.RecyclerView$LayoutParams
            r0 = r5
            com.jd.dynamic.yoga.android.YogaLayout r0 = (com.jd.dynamic.yoga.android.YogaLayout) r0
            com.jd.dynamic.yoga.android.YogaLayout$LayoutParams r0 = r0.getYogaLayoutLayoutParams()
            r6.<init>(r0)
            r5.setLayoutParams(r6)
        L2e:
            com.jd.dynamic.lib.views.RecyclerViewAdapter$HeaderViewHolder r6 = new com.jd.dynamic.lib.views.RecyclerViewAdapter$HeaderViewHolder
            r6.<init>(r5)
            return r6
        L34:
            r0 = 3000(0xbb8, float:4.204E-42)
            r1 = -2
            r2 = -1
            if (r6 != r0) goto L75
            android.widget.FrameLayout r6 = new android.widget.FrameLayout
            android.content.Context r0 = r5.getContext()
            r6.<init>(r0)
            com.handmark.pulltorefresh.library.JDLoadingMoreLayout r0 = new com.handmark.pulltorefresh.library.JDLoadingMoreLayout
            android.content.Context r5 = r5.getContext()
            r0.<init>(r5)
            int r5 = com.jd.dynamic.R.id.dynamic_collection_view
            com.jd.dynamic.lib.views.CollectionView r3 = r4.f2489c
            r0.setTag(r5, r3)
            com.jd.dynamic.lib.views.RecyclerViewAdapter$2 r5 = new com.jd.dynamic.lib.views.RecyclerViewAdapter$2
            r5.<init>()
            r0.setOnRetryListener(r5)
            android.widget.FrameLayout$LayoutParams r5 = new android.widget.FrameLayout$LayoutParams
            r5.<init>(r2, r2)
            r3 = 17
            r5.gravity = r3
            r6.addView(r0, r5)
            androidx.recyclerview.widget.RecyclerView$LayoutParams r5 = new androidx.recyclerview.widget.RecyclerView$LayoutParams
            r5.<init>(r2, r1)
            r6.setLayoutParams(r5)
            com.jd.dynamic.lib.views.RecyclerViewAdapter$LoadMoreViewHolder r5 = new com.jd.dynamic.lib.views.RecyclerViewAdapter$LoadMoreViewHolder
            r5.<init>(r6)
            return r5
        L75:
            com.jd.dynamic.lib.views.CollectionView r5 = r4.f2489c
            com.jd.dynamic.base.DynamicTemplateEngine r5 = r5.getEngine()
            if (r5 == 0) goto L9a
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            com.jd.dynamic.lib.views.CollectionView r0 = r4.f2489c
            com.jd.dynamic.base.DynamicTemplateEngine r0 = r0.getEngine()
            java.lang.Boolean r0 = r0.useAsyncItem
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L9a
            com.jd.dynamic.lib.views.CollectionView r5 = r4.f2489c
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r4.f2490e
            com.jd.dynamic.lib.views.ItemView r5 = r5.getItemViewFromId(r6, r0)
            android.view.View r5 = r5.createView()
            goto La6
        L9a:
            com.jd.dynamic.lib.views.CollectionView r5 = r4.f2489c
            java.util.Map<java.lang.String, java.lang.Integer> r0 = r4.f2490e
            com.jd.dynamic.lib.views.ItemView r5 = r5.getItemViewFromId(r6, r0)
            android.view.View r5 = r5.parse()
        La6:
            boolean r0 = r5 instanceof com.jd.dynamic.yoga.android.YogaLayout
            if (r0 == 0) goto Lc0
            r0 = r5
            com.jd.dynamic.yoga.android.YogaLayout r0 = (com.jd.dynamic.yoga.android.YogaLayout) r0
            com.jd.dynamic.yoga.android.YogaLayout$LayoutParams r3 = r0.getYogaLayoutLayoutParams()
            if (r3 == 0) goto Lc0
            androidx.recyclerview.widget.RecyclerView$LayoutParams r1 = new androidx.recyclerview.widget.RecyclerView$LayoutParams
            com.jd.dynamic.yoga.android.YogaLayout$LayoutParams r0 = r0.getYogaLayoutLayoutParams()
            r1.<init>(r0)
            r5.setLayoutParams(r1)
            goto Lc8
        Lc0:
            androidx.recyclerview.widget.RecyclerView$LayoutParams r0 = new androidx.recyclerview.widget.RecyclerView$LayoutParams
            r0.<init>(r2, r1)
            r5.setLayoutParams(r0)
        Lc8:
            com.jd.dynamic.b.a.b r0 = com.jd.dynamic.b.a.b.o()
            boolean r0 = r0.R()
            if (r0 == 0) goto Ld5
            r4.h(r5, r6)
        Ld5:
            int r6 = com.jd.dynamic.R.id.dynamic_collection_view
            com.jd.dynamic.lib.views.CollectionView r0 = r4.f2489c
            r5.setTag(r6, r0)
            com.jd.dynamic.lib.views.RecyclerViewAdapter$ItemViewHolder r6 = new com.jd.dynamic.lib.views.RecyclerViewAdapter$ItemViewHolder
            r6.<init>(r5)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.views.RecyclerViewAdapter.onCreateViewHolder(android.view.ViewGroup, int):androidx.recyclerview.widget.RecyclerView$ViewHolder");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        viewHolder.getLayoutPosition();
        if ((viewHolder instanceof HeaderViewHolder) || (viewHolder instanceof LoadMoreViewHolder)) {
            ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }
    }

    public void setSpanCount(int i2) {
        this.d = i2;
    }

    public void updateData(boolean z) {
        this.a = this.f2489c.getData();
        this.b.clear();
        JSONArray jSONArray = this.a;
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i2 = 0; i2 < this.a.length(); i2++) {
                this.b.add(this.a.optJSONObject(i2));
            }
        }
        if (z) {
            this.f2489c.getRecyclerView().setTag(R.id.dynamic_recyclerview_notify_tag, com.jd.dynamic.b.c.a.b);
            if (m(this.f2489c) || !l()) {
                com.jd.dynamic.lib.utils.t.e("updateData", Integer.valueOf(this.f2489c.getId()), "isItemView");
                notifyChange();
            }
        }
    }
}
