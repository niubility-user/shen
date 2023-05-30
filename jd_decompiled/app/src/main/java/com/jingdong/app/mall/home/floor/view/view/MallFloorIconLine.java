package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.view.adapter.MallIconFloorNewAdapter;
import com.jingdong.app.mall.home.floor.view.widget.IconIndicatorView;
import com.jingdong.app.mall.home.floor.view.widget.IconLineRecyclerView;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.k.c;
import com.jingdong.app.mall.home.r.f.a.o;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.recommend.RecommendMtaUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class MallFloorIconLine extends MallFloorIcon {
    private boolean isSingleLine;
    private int left;
    private JSONArray mExpoArr;
    private List<b> mExpoJson;
    private List<String> mExpoStr;
    private MallIconFloorNewAdapter mIconAdapter;
    private IconIndicatorView mIconIndicatorView;
    private f mIndicatorSize;
    private f mRecyclerSize;
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
                MallFloorIconLine.this.updateMtaRange();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            int computeHorizontalScrollExtent = recyclerView.computeHorizontalScrollExtent();
            int computeHorizontalScrollRange = recyclerView.computeHorizontalScrollRange();
            MallFloorIconLine.this.mIconIndicatorView.f(recyclerView.computeHorizontalScrollOffset() / (computeHorizontalScrollRange - computeHorizontalScrollExtent));
        }
    }

    public MallFloorIconLine(Context context, int i2, boolean z) {
        super(context, i2);
        this.mExpoJson = new ArrayList();
        this.mExpoStr = new ArrayList();
        this.mExpoArr = new JSONArray();
        this.left = 0;
        this.right = 0;
        this.isSingleLine = z;
        IconLineRecyclerView iconLineRecyclerView = new IconLineRecyclerView(context, z ? 1 : 2);
        this.mRecyclerView = iconLineRecyclerView;
        iconLineRecyclerView.addOnScrollListener(new IconRecyclerViewScrollListener());
        this.mRecyclerView.setOverScrollMode(2);
        this.mRecyclerView.setClipChildren(false);
        f fVar = new f(-1, z ? -1 : -2);
        this.mRecyclerSize = fVar;
        if (z) {
            fVar.J(20, 0, 20, 0);
        } else {
            fVar.J(22, 4, 16, 0);
        }
        RelativeLayout.LayoutParams u = this.mRecyclerSize.u(this.mRecyclerView);
        u.addRule(14);
        addView(this.mRecyclerView, u);
        this.mIconIndicatorView = new IconIndicatorView(context, -3355444, -381927, 8, 8);
        f fVar2 = new f(72, 8);
        this.mIndicatorSize = fVar2;
        fVar2.F(new Rect(0, 0, 0, z ? 8 : 12));
        RelativeLayout.LayoutParams u2 = this.mIndicatorSize.u(this.mIconIndicatorView);
        u2.addRule(12);
        u2.addRule(14);
        addView(this.mIconIndicatorView, u2);
    }

    private void resetMta() {
        this.mExpoJson.clear();
        this.mExpoArr = new JSONArray();
        this.mExpoStr.clear();
        this.left = 0;
        this.right = 0;
    }

    private void resetState() {
        this.mRecyclerView.a();
        this.mIconIndicatorView.f(0.0f);
    }

    private void sendExpo() {
        if (((o) this.mPresenter).j()) {
            resetMta();
            return;
        }
        updateMtaRange();
        if (this.right <= 0) {
            return;
        }
        for (int i2 = this.left; i2 <= this.right; i2++) {
            c U = ((o) this.mPresenter).U(i2);
            if (U != null) {
                b bVar = U.o;
                if (!this.mExpoJson.contains(bVar)) {
                    this.mExpoJson.add(bVar);
                    this.mExpoArr.put(bVar);
                }
                JumpEntity jump = U.getJump();
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

    private void setIconAdapter() {
        if (this.mIconAdapter == null) {
            MallIconFloorNewAdapter mallIconFloorNewAdapter = new MallIconFloorNewAdapter((o) this.mPresenter, getContext());
            this.mIconAdapter = mallIconFloorNewAdapter;
            this.mRecyclerView.setAdapter(mallIconFloorNewAdapter);
        }
    }

    private void setIndicatorStyle() {
        int k0 = ((o) this.mPresenter).k0();
        if (k0 == -1) {
            this.mIconIndicatorView.setVisibility(8);
            return;
        }
        this.mIconIndicatorView.setVisibility(0);
        this.mIconIndicatorView.e(k0);
        this.mIconIndicatorView.c(((o) this.mPresenter).getBannerCursorColor(), ((o) this.mPresenter).getCursorSelectColor());
        this.mIndicatorSize.Q(k0 == 0 ? 48 : 72);
    }

    private void setRoundBg(boolean z) {
        if (z) {
            this.mRecyclerView.setClipToPadding(false);
            if (needClip()) {
                int d = d.d(20);
                e.f(this.mRecyclerView, true, new Rect(d, 0, d, 0), 1);
            } else {
                e.i(this.mRecyclerView);
            }
            if (this.isSingleLine) {
                setBgShadeColor(this.mRecyclerView, d.d(20), ((o) this.mPresenter).W());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMtaRange() {
        int firstVisibleItem = this.mRecyclerView.getFirstVisibleItem();
        int lastVisibleItem = this.mRecyclerView.getLastVisibleItem();
        this.left = Math.min(Math.max(0, firstVisibleItem), this.left);
        this.right = Math.max(lastVisibleItem, this.right);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    public void beforeNewAppCenterInfoParse() {
        if (this.mFloorBindElement.isLastData()) {
            sendExpo();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    protected int getClipRadius() {
        return d.d(this.isSingleLine ? 24 : 12);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    public void initIconView() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    public boolean isLineIcon() {
        return true;
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
    public void onHomeRefresh() {
        super.onHomeRefresh();
        sendExpo();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeResume(MallFloorEvent mallFloorEvent) {
        super.onHomeResume(mallFloorEvent);
        updateMtaRange();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onLoadingBgCompleteOnMainThread(String str, Bitmap bitmap) {
        if (this.isSingleLine) {
            return;
        }
        super.onLoadingBgCompleteOnMainThread(str, bitmap);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void onLoadingBgFailedOnMainThread(String str, JDFailReason jDFailReason) {
        if (this.isSingleLine) {
            return;
        }
        setBgShadeColor(this, needClip() ? d.d(20) : 0, ((o) this.mPresenter).V());
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
        if (((com.jingdong.app.mall.home.r.f.a.o) r3.mPresenter).G0() != false) goto L7;
     */
    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void onRefreshViewInMainThread(boolean r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 1
            if (r4 != 0) goto Le
            P extends com.jingdong.app.mall.home.r.f.a.b r1 = r3.mPresenter     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.r.f.a.o r1 = (com.jingdong.app.mall.home.r.f.a.o) r1     // Catch: java.lang.Throwable -> L3b
            boolean r1 = r1.G0()     // Catch: java.lang.Throwable -> L3b
            if (r1 == 0) goto L2b
        Le:
            r3.resetState()     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.floor.view.widget.IconLineRecyclerView r1 = r3.mRecyclerView     // Catch: java.lang.Throwable -> L3b
            boolean r2 = r3.isSingleLine     // Catch: java.lang.Throwable -> L3b
            if (r2 == 0) goto L19
            r2 = 1
            goto L1a
        L19:
            r2 = 2
        L1a:
            r1.b(r2)     // Catch: java.lang.Throwable -> L3b
            r3.setIconAdapter()     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.floor.view.adapter.MallIconFloorNewAdapter r1 = r3.mIconAdapter     // Catch: java.lang.Throwable -> L3b
            r1.notifyDataSetChanged()     // Catch: java.lang.Throwable -> L3b
            r3.setIndicatorStyle()     // Catch: java.lang.Throwable -> L3b
            r3.setRoundBg(r4)     // Catch: java.lang.Throwable -> L3b
        L2b:
            com.jingdong.app.mall.home.floor.view.widget.IconLineRecyclerView r4 = r3.mRecyclerView     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.floor.common.f r1 = r3.mRecyclerSize     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.floor.common.f.d(r4, r1, r0)     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.floor.view.widget.IconIndicatorView r4 = r3.mIconIndicatorView     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.floor.common.f r1 = r3.mIndicatorSize     // Catch: java.lang.Throwable -> L3b
            com.jingdong.app.mall.home.floor.common.f.d(r4, r1, r0)     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r3)
            return
        L3b:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.floor.view.view.MallFloorIconLine.onRefreshViewInMainThread(boolean):void");
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorIcon, com.jingdong.app.mall.home.e.b
    public void onTextScaleModeChanged(int i2) {
        MallIconFloorNewAdapter mallIconFloorNewAdapter;
        if (this.isSingleLine || (mallIconFloorNewAdapter = this.mIconAdapter) == null) {
            return;
        }
        mallIconFloorNewAdapter.notifyDataSetChanged();
    }
}
