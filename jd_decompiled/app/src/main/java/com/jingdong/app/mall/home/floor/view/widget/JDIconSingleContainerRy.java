package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.view.adapter.MallIconBaseAdapter;
import com.jingdong.app.mall.home.floor.view.adapter.MallIconFloorNewAdapter;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.f.a.o;

/* loaded from: classes4.dex */
public class JDIconSingleContainerRy extends RecyclerView implements b {

    /* renamed from: g */
    private MallIconBaseAdapter f10103g;

    /* renamed from: h */
    private Handler f10104h;

    /* renamed from: i */
    private Context f10105i;

    /* renamed from: j */
    private o f10106j;

    /* loaded from: classes4.dex */
    public class a implements Runnable {
        a() {
            JDIconSingleContainerRy.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            JDIconSingleContainerRy jDIconSingleContainerRy = JDIconSingleContainerRy.this;
            if (jDIconSingleContainerRy.e(jDIconSingleContainerRy.f10105i, JDIconSingleContainerRy.this.f10106j)) {
                return;
            }
            if (!JDIconSingleContainerRy.this.isComputingLayout() && JDIconSingleContainerRy.this.f10103g != null) {
                JDIconSingleContainerRy.this.f10103g.notifyDataSetChanged();
            } else {
                JDIconSingleContainerRy.this.notifyDataSetByChange();
            }
        }
    }

    public JDIconSingleContainerRy(Context context, o oVar) {
        super(context);
        this.f10105i = context;
        this.f10106j = oVar;
        this.f10104h = new Handler();
        e(context, oVar);
        setBackgroundColor(0);
    }

    public boolean e(Context context, o oVar) {
        MallIconBaseAdapter a2 = com.jingdong.app.mall.home.floor.view.adapter.a.a(context, oVar);
        if (this.f10103g == null || a2.getClass() != this.f10103g.getClass()) {
            this.f10103g = a2;
            setAdapter(a2);
            return true;
        }
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.b
    public void notifyDataSetByChange() {
        this.f10104h.post(new a());
    }

    public void setPageIndex(int i2) {
        MallIconBaseAdapter mallIconBaseAdapter = this.f10103g;
        if (mallIconBaseAdapter != null) {
            mallIconBaseAdapter.l(i2);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.widget.b
    public void updateItemTextSize(int i2) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        f.n(layoutManager);
        GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
        int findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition > findLastVisibleItemPosition) {
            return;
        }
        while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
            View childAt = getChildAt(findFirstVisibleItemPosition);
            if (childAt != null) {
                RecyclerView.ViewHolder childViewHolder = getChildViewHolder(childAt);
                if (childViewHolder instanceof MallIconFloorNewAdapter.IconViewHolder) {
                    ((MallIconFloorNewAdapter.IconViewHolder) childViewHolder).l(i2);
                }
            }
            findFirstVisibleItemPosition++;
        }
    }
}
