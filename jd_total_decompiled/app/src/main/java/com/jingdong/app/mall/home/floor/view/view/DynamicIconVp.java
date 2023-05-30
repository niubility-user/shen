package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.d;
import com.jingdong.app.mall.home.floor.ctrl.i;
import com.jingdong.app.mall.home.floor.model.entity.DynamicIconEntity;
import com.jingdong.app.mall.home.floor.view.AnimationLinerPagerCursor;
import com.jingdong.app.mall.home.floor.view.adapter.DynamicIconAdapter;
import com.jingdong.app.mall.home.floor.view.adapter.IconViewPagerAdapter;
import com.jingdong.app.mall.home.floor.view.baseui.ICursorContentViewPresenter;
import com.jingdong.app.mall.home.floor.view.widget.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.f.a.e;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class DynamicIconVp extends DynamicIcon implements ViewPager.OnPageChangeListener {
    private int currentPagePosition;
    private boolean isScrollFinish;
    private i mCursorContentViewCtrl;
    public final SparseArray<b> mPageCache;
    private final ViewPager mViewPager;
    private IconViewPagerAdapter mViewPagerAdapter;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class IconContainerGv extends RecyclerView implements b {
        private DynamicIconAdapter mAdapter;
        private final Context mContext;
        private final Handler mHandler;
        private final e mPresenter;

        public IconContainerGv(@NonNull Context context, e eVar) {
            super(context);
            this.mContext = context;
            this.mPresenter = eVar;
            this.mHandler = new Handler(Looper.getMainLooper());
            setBackgroundColor(0);
            updateAdapter(context, eVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean updateAdapter(Context context, e eVar) {
            DynamicIconAdapter dynamicIconAdapter = new DynamicIconAdapter(context, eVar);
            DynamicIconAdapter dynamicIconAdapter2 = this.mAdapter;
            if (dynamicIconAdapter2 == null || DynamicIconAdapter.class != dynamicIconAdapter2.getClass()) {
                this.mAdapter = dynamicIconAdapter;
                setAdapter(dynamicIconAdapter);
                return true;
            }
            return false;
        }

        @Override // com.jingdong.app.mall.home.floor.view.widget.b
        public void notifyDataSetByChange() {
            this.mHandler.post(new Runnable() { // from class: com.jingdong.app.mall.home.floor.view.view.DynamicIconVp.IconContainerGv.1
                @Override // java.lang.Runnable
                public void run() {
                    IconContainerGv iconContainerGv = IconContainerGv.this;
                    if (iconContainerGv.updateAdapter(iconContainerGv.mContext, IconContainerGv.this.mPresenter)) {
                        return;
                    }
                    if (!IconContainerGv.this.isComputingLayout() && IconContainerGv.this.mAdapter != null) {
                        IconContainerGv.this.mAdapter.notifyDataSetChanged();
                    } else {
                        IconContainerGv.this.notifyDataSetByChange();
                    }
                }
            });
        }

        public void setPageIndex(int i2) {
            DynamicIconAdapter dynamicIconAdapter = this.mAdapter;
            if (dynamicIconAdapter != null) {
                dynamicIconAdapter.n(i2);
            }
        }

        @Override // com.jingdong.app.mall.home.floor.view.widget.b
        public void updateItemTextSize(int i2) {
            int findFirstVisibleItemPosition;
            int findLastVisibleItemPosition;
            RecyclerView.LayoutManager layoutManager = getLayoutManager();
            f.n(layoutManager);
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            if (gridLayoutManager != null && (findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()) <= (findLastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition())) {
                for (findFirstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                    View childAt = getChildAt(findFirstVisibleItemPosition);
                    if (childAt != null) {
                        RecyclerView.ViewHolder childViewHolder = getChildViewHolder(childAt);
                        if (childViewHolder instanceof DynamicIconAdapter.IconViewHolder) {
                            ((DynamicIconAdapter.IconViewHolder) childViewHolder).j(i2);
                        }
                    }
                }
            }
        }
    }

    public DynamicIconVp(Context context) {
        super(context);
        this.mPageCache = new SparseArray<>();
        this.isScrollFinish = true;
        ViewPager viewPager = new ViewPager(context);
        this.mViewPager = viewPager;
        viewPager.setContentDescription("\u767e\u5b9d\u7bb1");
        addView(viewPager, new RelativeLayout.LayoutParams(-1, -1));
        viewPager.addOnPageChangeListener(this);
        viewPager.setBackgroundColor(0);
        viewPager.setId(R.id.mallfloor_iconfloor_pager);
    }

    private void bindIndicator() {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar != null) {
            iVar.h();
        }
        d dVar = new d();
        this.mCursorContentViewCtrl = dVar;
        AnimationLinerPagerCursor animationLinerPagerCursor = new AnimationLinerPagerCursor(getContext());
        int id = this.mViewPager.getId();
        P p = this.mPresenter;
        dVar.f(animationLinerPagerCursor, id, (ICursorContentViewPresenter) p, ((e) p).getCursorMarginBottom());
        View d = this.mCursorContentViewCtrl.d();
        if (d != null) {
            d.setContentDescription("\u767e\u5b9d\u7bb1\u6307\u793a\u5668");
        }
        int count = this.mViewPagerAdapter.getCount();
        if (count > 1) {
            updateUiWithPagePosition(count, this.currentPagePosition);
        }
    }

    private void bindViewPager() {
        DynamicIconEntity.ViewConfig g0 = ((e) this.mPresenter).g0();
        ArrayList arrayList = new ArrayList();
        int e0 = ((e) this.mPresenter).e0();
        int i2 = g0.iconCountPerRow;
        for (int i3 = 0; i3 < e0; i3++) {
            IconContainerGv createGridView = createGridView(i3, (e) this.mPresenter);
            createGridView.setPageIndex(i3);
            createGridView.setLayoutManager(new GridLayoutManager(getContext(), i2));
            arrayList.add(createGridView);
        }
        IconViewPagerAdapter iconViewPagerAdapter = this.mViewPagerAdapter;
        if (iconViewPagerAdapter == null) {
            IconViewPagerAdapter iconViewPagerAdapter2 = new IconViewPagerAdapter(arrayList);
            this.mViewPagerAdapter = iconViewPagerAdapter2;
            this.mViewPager.setAdapter(iconViewPagerAdapter2);
        } else {
            iconViewPagerAdapter.f(arrayList);
            this.mViewPagerAdapter.notifyDataSetChanged();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((b) it.next()).notifyDataSetByChange();
        }
    }

    private IconContainerGv createGridView(int i2, e eVar) {
        IconContainerGv iconContainerGv;
        b bVar = this.mPageCache.get(i2);
        if (!(bVar instanceof IconContainerGv)) {
            iconContainerGv = new IconContainerGv(getContext(), eVar);
            this.mPageCache.put(i2, iconContainerGv);
        } else {
            iconContainerGv = (IconContainerGv) bVar;
            m.K(iconContainerGv);
        }
        iconContainerGv.setLayoutParams(new ViewPager.LayoutParams());
        iconContainerGv.setClipChildren(false);
        iconContainerGv.setBackgroundColor(0);
        DynamicIconEntity.ViewConfig g0 = eVar.g0();
        iconContainerGv.setPadding(com.jingdong.app.mall.home.floor.common.d.d(g0.containerLPadding), com.jingdong.app.mall.home.floor.common.d.d(g0.containerTopPadding), com.jingdong.app.mall.home.floor.common.d.d(g0.containerRPadding), 0);
        iconContainerGv.setContentDescription("\u767e\u5b9d\u7bb1");
        return iconContainerGv;
    }

    private void sendExpoOnePageMaidian(int i2) {
        if (((e) this.mPresenter).j()) {
            return;
        }
        int X = ((e) this.mPresenter).X(i2);
        int X2 = i2 <= 0 ? 0 : ((e) this.mPresenter).X(i2 - 1) * i2;
        boolean z = true;
        int i3 = i2 + 1;
        int i4 = X * i3;
        StringBuilder sb = new StringBuilder();
        JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
        while (true) {
            if (X2 >= i4) {
                z = false;
                break;
            }
            com.jingdong.app.mall.home.r.e.k.b Z = ((e) this.mPresenter).Z(X2);
            if (Z != null) {
                if (Z.k()) {
                    break;
                }
                Z.u();
                sb.append(((e) this.mPresenter).Z(X2).getJump().srv);
                sb.append("&&");
                d.put(Z.f10721k);
            }
            X2++;
        }
        if (z) {
            return;
        }
        String substring = sb.length() > 2 ? sb.substring(0, sb.length() - 2) : "";
        String jSONArray = d.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(i3);
        sb2.append(CartConstant.KEY_YB_INFO_LINK);
        sb2.append(TextUtils.isEmpty(l.a) ? "" : l.a);
        a.A("Home_ShortcutOneExpo", substring, jSONArray, RecommendMtaUtils.Home_PageId, sb2.toString());
    }

    private void updateUiWithPagePosition(int i2, int i3) {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar == null) {
            return;
        }
        if (i2 < i3 + 1 && i3 > 0) {
            i3 = i2 - 1;
        }
        iVar.b(i2, this, 0);
        this.mCursorContentViewCtrl.onPageSelected(i3);
        this.mCursorContentViewCtrl.e(i3);
        this.mCursorContentViewCtrl.g(((e) this.mPresenter).getCursorMarginBottom());
        this.mViewPager.setCurrentItem(i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void beforeViewBind() {
        super.beforeViewBind();
        if (this.isScrollFinish) {
            return;
        }
        this.mViewPager.requestLayout();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.DynamicIcon
    protected void onBindView() {
        bindViewPager();
        bindIndicator();
        doClip(this.mViewPager);
        if (((e) this.mPresenter).h0()) {
            return;
        }
        sendExpoOnePageMaidian(this.currentPagePosition);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.mViewPager;
        if (viewPager != null) {
            this.currentPagePosition = viewPager.getCurrentItem();
            if (this.isScrollFinish) {
                return;
            }
            this.mViewPager.requestLayout();
            i iVar = this.mCursorContentViewCtrl;
            if (iVar != null) {
                iVar.onPageScrolled(this.mViewPager.getCurrentItem(), 0.0f, 0);
            }
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar == null) {
            return;
        }
        this.isScrollFinish = i3 == 0;
        iVar.onPageScrolled(i2, f2, i3);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar == null) {
            return;
        }
        this.currentPagePosition = i2;
        iVar.onPageSelected(i2);
        this.mCursorContentViewCtrl.e(i2);
        sendExpoOnePageMaidian(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.DynamicIcon
    protected void onViewConfigChanged() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.DynamicIcon
    protected void updateItemTextSize(int i2) {
        List<b> d;
        IconViewPagerAdapter iconViewPagerAdapter = this.mViewPagerAdapter;
        if (iconViewPagerAdapter == null || (d = iconViewPagerAdapter.d()) == null) {
            return;
        }
        Iterator<b> it = d.iterator();
        while (it.hasNext()) {
            it.next().updateItemTextSize(i2);
        }
    }
}
