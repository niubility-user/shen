package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity;
import com.jingdong.app.mall.home.floor.view.adapter.DynamicIconAdapter;
import com.jingdong.app.mall.home.floor.view.widget.IconIndicatorView;
import com.jingdong.app.mall.home.floor.view.widget.IconLineRecyclerView;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.f.a.e;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class DynamicIconRy extends DynamicIcon {
    private int left;
    private JSONArray mExpoArr;
    private final List<b> mExpoJson;
    private final List<String> mExpoStr;
    private DynamicIconAdapter mIconAdapter;
    private IconIndicatorView mIconIndicatorView;
    private final f mRecyclerSize;
    private IconLineRecyclerView mRecyclerView;
    private int right;

    /* loaded from: classes4.dex */
    public class IconRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        public IconRecyclerViewScrollListener() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            if (i2 == 0) {
                DynamicIconRy.this.updateMtaRange();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            int computeHorizontalScrollExtent = recyclerView.computeHorizontalScrollExtent();
            float computeHorizontalScrollOffset = recyclerView.computeHorizontalScrollRange() == computeHorizontalScrollExtent ? 0.0f : recyclerView.computeHorizontalScrollOffset() / (r3 - computeHorizontalScrollExtent);
            if (DynamicIconRy.this.mIconIndicatorView != null) {
                DynamicIconRy.this.mIconIndicatorView.f(computeHorizontalScrollOffset);
            }
        }
    }

    public DynamicIconRy(Context context) {
        super(context);
        this.mRecyclerSize = new f(-1, -2);
        this.left = 0;
        this.right = 0;
        this.mExpoJson = new ArrayList();
        this.mExpoStr = new ArrayList();
        this.mExpoArr = new JSONArray();
    }

    private void bindIndicator() {
        int Y = ((e) this.mPresenter).Y();
        if (Y == -1) {
            IconIndicatorView iconIndicatorView = this.mIconIndicatorView;
            if (iconIndicatorView != null) {
                iconIndicatorView.setVisibility(8);
                return;
            }
            return;
        }
        DynamicIconEntity.ViewConfig g0 = ((e) this.mPresenter).g0();
        f fVar = new f(Y == 0 ? 48 : 72, g0.indicatorHeight);
        fVar.E(0, 0, 0, g0.indicatorBottomMargin);
        int i2 = g0.indicatorHeight;
        IconIndicatorView iconIndicatorView2 = this.mIconIndicatorView;
        if (iconIndicatorView2 == null) {
            IconIndicatorView iconIndicatorView3 = new IconIndicatorView(getContext(), -3355444, -381927, i2, i2);
            this.mIconIndicatorView = iconIndicatorView3;
            RelativeLayout.LayoutParams u = fVar.u(iconIndicatorView3);
            u.addRule(12);
            u.addRule(14);
            addView(this.mIconIndicatorView, u);
        } else {
            iconIndicatorView2.f(0.0f);
            this.mIconIndicatorView.setVisibility(0);
            f.c(this.mIconIndicatorView, fVar);
        }
        this.mIconIndicatorView.d(i2, i2);
        this.mIconIndicatorView.c(((e) this.mPresenter).getBannerCursorColor(), ((e) this.mPresenter).getCursorSelectColor());
        this.mIconIndicatorView.e(Y);
    }

    private void bindRecycleView() {
        DynamicIconEntity.ViewConfig g0 = ((e) this.mPresenter).g0();
        this.mRecyclerSize.J(g0.containerLPadding, g0.containerTopPadding, g0.containerRPadding, 0);
        IconLineRecyclerView iconLineRecyclerView = this.mRecyclerView;
        if (iconLineRecyclerView == null) {
            IconLineRecyclerView iconLineRecyclerView2 = new IconLineRecyclerView(getContext(), g0.showLines);
            this.mRecyclerView = iconLineRecyclerView2;
            iconLineRecyclerView2.setOverScrollMode(2);
            this.mRecyclerView.addOnScrollListener(new IconRecyclerViewScrollListener());
            this.mRecyclerView.setClipChildren(false);
            this.mRecyclerView.setClipToPadding(false);
            IconLineRecyclerView iconLineRecyclerView3 = this.mRecyclerView;
            addView(iconLineRecyclerView3, this.mRecyclerSize.u(iconLineRecyclerView3));
        } else {
            iconLineRecyclerView.a();
            f.c(this.mRecyclerView, this.mRecyclerSize);
        }
        if (this.mIconAdapter == null) {
            this.mIconAdapter = new DynamicIconAdapter(getContext(), (e) this.mPresenter);
        }
        this.mRecyclerView.setAdapter(this.mIconAdapter);
        this.mIconAdapter.notifyDataSetChanged();
    }

    private void resetMta() {
        this.mExpoJson.clear();
        this.mExpoArr = new JSONArray();
        this.mExpoStr.clear();
        this.left = 0;
        this.right = 0;
    }

    private void sendExpo() {
        if (((e) this.mPresenter).j()) {
            resetMta();
            return;
        }
        updateMtaRange();
        if (this.right <= 0) {
            return;
        }
        for (int i2 = this.left; i2 <= this.right; i2++) {
            com.jingdong.app.mall.home.r.e.k.b Z = ((e) this.mPresenter).Z(i2);
            if (Z != null) {
                b bVar = Z.f10721k;
                if (!this.mExpoJson.contains(bVar)) {
                    this.mExpoJson.add(bVar);
                    this.mExpoArr.put(bVar);
                }
                JumpEntity jump = Z.getJump();
                if (jump != null) {
                    String srv = jump.getSrv();
                    if (!this.mExpoStr.contains(srv)) {
                        this.mExpoStr.add(srv);
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.mExpoStr.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("&&");
        }
        if (sb.length() > 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        String sb2 = sb.toString();
        String jSONArray = this.mExpoArr.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(" _");
        sb3.append(!TextUtils.isEmpty(l.a) ? l.a : "");
        a.A("Home_ShortcutOneExpo", sb2, jSONArray, RecommendMtaUtils.Home_PageId, sb3.toString());
        this.mExpoJson.toString();
        sb.toString();
        resetMta();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMtaRange() {
        int firstVisibleItem = this.mRecyclerView.getFirstVisibleItem();
        int lastVisibleItem = this.mRecyclerView.getLastVisibleItem();
        this.left = Math.min(Math.max(0, firstVisibleItem), this.left);
        this.right = Math.max(lastVisibleItem, this.right);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.DynamicIcon
    protected void onBindView() {
        bindRecycleView();
        bindIndicator();
        doClip(this.mRecyclerView);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        IconLineRecyclerView iconLineRecyclerView = this.mRecyclerView;
        if (iconLineRecyclerView != null) {
            iconLineRecyclerView.stopScroll();
        }
        sendExpo();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeResume(MallFloorEvent mallFloorEvent) {
        super.onHomeResume(mallFloorEvent);
        updateMtaRange();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.DynamicIcon
    protected void onViewConfigChanged() {
        removeAllViews();
        this.mRecyclerView = null;
        this.mIconIndicatorView = null;
        this.mIconAdapter = null;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.DynamicIcon
    protected void updateItemTextSize(int i2) {
        int firstVisibleItem;
        int lastVisibleItem;
        IconLineRecyclerView iconLineRecyclerView = this.mRecyclerView;
        if (iconLineRecyclerView != null && (firstVisibleItem = iconLineRecyclerView.getFirstVisibleItem()) <= (lastVisibleItem = this.mRecyclerView.getLastVisibleItem())) {
            for (firstVisibleItem = iconLineRecyclerView.getFirstVisibleItem(); firstVisibleItem <= lastVisibleItem; firstVisibleItem++) {
                View childAt = getChildAt(firstVisibleItem);
                if (childAt != null) {
                    RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(childAt);
                    if (childViewHolder instanceof DynamicIconAdapter.IconViewHolder) {
                        ((DynamicIconAdapter.IconViewHolder) childViewHolder).j(i2);
                    }
                }
            }
        }
    }
}
