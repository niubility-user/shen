package com.jd.dynamic.lib.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class TagViewAdapter extends BaseListAdapter<JSONObject> {

    /* renamed from: h  reason: collision with root package name */
    private TagView f2521h;

    /* renamed from: i  reason: collision with root package name */
    private Map<String, Integer> f2522i;

    /* renamed from: j  reason: collision with root package name */
    private AtomicInteger f2523j;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemView a;
        private boolean b;

        /* renamed from: c  reason: collision with root package name */
        private int f2524c;
        private int d;

        public ItemViewHolder(@NonNull View view, @NonNull ItemView itemView, @NonNull int i2, int i3) {
            super(view);
            this.a = itemView;
            view.setTag(R.id.dynamic_tag_view_holder, this);
            this.f2524c = i2;
            this.d = i3;
        }

        public void dispatchTagViewEvent(JSONObject jSONObject) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.a.dispatchTagViewEvent(jSONObject, this.itemView);
        }

        public ItemView getDynamicItemView() {
            return this.a;
        }

        public int getIndex() {
            return this.f2524c;
        }

        public int getItemType() {
            return this.d;
        }

        public void setIndex(int i2) {
            this.f2524c = i2;
        }

        public void setIsBind(boolean z) {
            this.b = z;
        }
    }

    public TagViewAdapter(Context context, TagView tagView) {
        super(context, tagView.getData());
        this.f2522i = new HashMap();
        this.f2523j = new AtomicInteger(100);
        this.f2521h = tagView;
    }

    private ViewGroup.LayoutParams a(Context context, ViewNode viewNode) {
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
        return (viewNode == null || viewNode.getAttributes() == null) ? marginLayoutParams : b(context, viewNode.getAttributes(), marginLayoutParams);
    }

    private ViewGroup.LayoutParams b(Context context, HashMap<String, String> hashMap, ViewGroup.LayoutParams layoutParams) {
        boolean z = layoutParams instanceof ViewGroup.MarginLayoutParams;
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams;
        if (z) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
            new com.jd.dynamic.lib.viewparse.g.a.a().b(context, hashMap, marginLayoutParams2);
            marginLayoutParams = marginLayoutParams2;
        }
        new com.jd.dynamic.lib.viewparse.g.a.b().a(context, hashMap, marginLayoutParams);
        return marginLayoutParams;
    }

    public static ItemViewHolder getViewHolderByView(View view) {
        return (ItemViewHolder) view.getTag(R.id.dynamic_tag_view_holder);
    }

    @Override // com.jd.dynamic.lib.views.BaseListAdapter
    public void bindViewHolder(int i2, RecyclerView.ViewHolder viewHolder) {
        convert(viewHolder, getItem(i2), i2);
    }

    @Override // com.jd.dynamic.lib.views.BaseListAdapter
    public void convert(RecyclerView.ViewHolder viewHolder, JSONObject jSONObject, int i2) {
        if (jSONObject == null) {
            return;
        }
        viewHolder.itemView.setTag(R.id.dynamic_item_position, Integer.valueOf(i2));
        if (this.f2521h.getEngine() == null || !Boolean.TRUE.equals(this.f2521h.getEngine().useAsyncItem)) {
            this.f2521h.getItemViewFromId(getItemViewType(i2), this.f2522i).bindData(viewHolder.itemView, jSONObject);
        } else {
            this.f2521h.getItemViewFromId(getItemViewType(i2), this.f2522i).bindDataWithCache(viewHolder.itemView, jSONObject);
        }
    }

    @Override // com.jd.dynamic.lib.views.BaseListAdapter, android.widget.Adapter
    public int getCount() {
        JSONArray jSONArray = this.f2466g;
        if (jSONArray == null || jSONArray.length() <= 0) {
            return 0;
        }
        return this.f2466g.length();
    }

    @Override // com.jd.dynamic.lib.views.BaseListAdapter
    public JSONArray getData() {
        return this.f2466g;
    }

    @Override // com.jd.dynamic.lib.views.BaseListAdapter, android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        try {
            if (this.f2466g.get(i2) == null) {
                return 1000;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        JSONObject optJSONObject = this.f2466g.optJSONObject(i2);
        if (optJSONObject == null) {
            return 1000;
        }
        String optString = optJSONObject.optString(DYConstants.DY_IDENTIFIER);
        if (TextUtils.isEmpty(optString)) {
            return 1000;
        }
        Integer num = this.f2522i.get(optString);
        if (num != null) {
            return num.intValue();
        }
        try {
            int parseInt = Integer.parseInt(optString);
            this.f2522i.put(optString, Integer.valueOf(parseInt));
            return parseInt;
        } catch (Exception unused) {
            int andDecrement = this.f2523j.getAndDecrement();
            this.f2522i.put(optString, Integer.valueOf(andDecrement));
            return andDecrement;
        }
    }

    @Override // com.jd.dynamic.lib.views.BaseListAdapter
    public RecyclerView.ViewHolder getViewHolder(View view, ViewGroup viewGroup, int i2) {
        ViewGroup.LayoutParams a;
        int itemViewType = getItemViewType(i2);
        ItemView itemViewFromId = this.f2521h.getItemViewFromId(itemViewType, this.f2522i);
        View parse = (this.f2521h.getEngine() == null || !Boolean.TRUE.equals(this.f2521h.getEngine().useAsyncItem)) ? itemViewFromId.parse(true) : itemViewFromId.createView(true);
        if (parse instanceof YogaLayout) {
            YogaLayout yogaLayout = (YogaLayout) parse;
            if (yogaLayout.getYogaLayoutLayoutParams() != null) {
                YogaLayout.LayoutParams layoutParams = new YogaLayout.LayoutParams(yogaLayout.getYogaLayoutLayoutParams());
                ViewNode viewNode = itemViewFromId.viewNode;
                if (viewNode == null || viewNode.getAttributes() == null) {
                    parse.setLayoutParams(layoutParams);
                    return new ItemViewHolder(parse, itemViewFromId, i2, itemViewType);
                }
                a = b(parse.getContext(), itemViewFromId.viewNode.getAttributes(), layoutParams);
                parse.setLayoutParams(a);
                return new ItemViewHolder(parse, itemViewFromId, i2, itemViewType);
            }
        }
        a = a(itemViewFromId.getContext(), itemViewFromId.viewNode);
        parse.setLayoutParams(a);
        return new ItemViewHolder(parse, itemViewFromId, i2, itemViewType);
    }

    public void setData(JSONArray jSONArray) {
        this.f2466g = jSONArray;
    }
}
