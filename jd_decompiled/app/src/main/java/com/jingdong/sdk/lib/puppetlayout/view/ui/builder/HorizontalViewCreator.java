package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.basewidget.widget.HorizontalRecycleView;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetContext;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Iterate;

/* loaded from: classes8.dex */
public class HorizontalViewCreator extends com.jingdong.sdk.lib.puppetlayout.h.a {

    /* renamed from: k  reason: collision with root package name */
    private HorizontalRecycleView f15232k;

    /* renamed from: l  reason: collision with root package name */
    private HorizontalViewAdapter f15233l = null;

    /* loaded from: classes8.dex */
    public static class HorizontalViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private JDJSONArray a;
        private JDJSONObject b;

        /* renamed from: c  reason: collision with root package name */
        private Iterate f15234c;
        private PuppetContext d;

        public HorizontalViewAdapter(Iterate iterate, PuppetContext puppetContext) {
            this.f15234c = iterate;
            this.d = puppetContext;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onBindViewHolder(ItemViewHolder itemViewHolder, int i2) {
            JDJSONObject jDJSONObject = this.b;
            if (jDJSONObject != null) {
                this.f15234c.bindData((ViewGroup) itemViewHolder.itemView, jDJSONObject);
            }
            if (this.a.optJSONObject(i2) != null) {
                this.f15234c.bindItemData(itemViewHolder.itemView, this.a.optJSONObject(i2), false, false, -1);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            JDJSONArray jDJSONArray = this.a;
            if (jDJSONArray != null) {
                return jDJSONArray.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: h  reason: merged with bridge method [inline-methods] */
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
            Iterate iterate;
            if (viewGroup == null || (iterate = this.f15234c) == null || iterate.itemViewBase == null) {
                return null;
            }
            return new ItemViewHolder(iterate.createItemView(viewGroup.getContext(), this.d));
        }

        public void l(JDJSONObject jDJSONObject) {
            this.b = jDJSONObject;
        }

        public void m(JDJSONArray jDJSONArray) {
            this.a = jDJSONArray;
        }
    }

    /* loaded from: classes8.dex */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View view) {
            super(view);
        }
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void d(Context context) {
        HorizontalRecycleView horizontalRecycleView = new HorizontalRecycleView(context);
        this.f15232k = horizontalRecycleView;
        this.a = horizontalRecycleView;
    }

    @Override // com.jingdong.sdk.lib.puppetlayout.h.a
    public void m(JDJSONObject jDJSONObject, JDJSONArray jDJSONArray, Iterate iterate) {
        if (jDJSONObject == null && jDJSONArray == null) {
            return;
        }
        HorizontalViewAdapter horizontalViewAdapter = this.f15233l;
        if (horizontalViewAdapter == null) {
            HorizontalViewAdapter horizontalViewAdapter2 = new HorizontalViewAdapter(iterate, this.b);
            this.f15233l = horizontalViewAdapter2;
            horizontalViewAdapter2.m(jDJSONArray);
            this.f15233l.l(jDJSONObject);
            this.f15232k.c(this.f15233l);
            return;
        }
        horizontalViewAdapter.m(jDJSONArray);
        this.f15233l.l(jDJSONObject);
        this.f15233l.notifyDataSetChanged();
    }
}
