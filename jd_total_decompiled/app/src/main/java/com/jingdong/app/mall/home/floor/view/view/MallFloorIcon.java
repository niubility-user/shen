package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.i;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.IconFloorEngine;
import com.jingdong.app.mall.home.floor.view.AnimationLinerPagerCursor;
import com.jingdong.app.mall.home.floor.view.adapter.IconViewPagerAdapter;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.widget.JDIconSingleContainerRy;
import com.jingdong.app.mall.home.floor.view.widget.LinerPagerCursor;
import com.jingdong.app.mall.home.floor.view.widget.b;
import com.jingdong.app.mall.home.floor.view.widget.c;
import com.jingdong.app.mall.home.n.h.g;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.f.a.o;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class MallFloorIcon extends BaseMallFloor<o> implements ViewPager.OnPageChangeListener, e.b {
    private static final int DELAY_TIME = 1000;
    private static final String TAG = "MallFloor_Icon";
    protected int currentPagePosition;
    protected IconViewPagerAdapter iconViewPagerAdapter;
    private final AtomicBoolean isFirstLoaded;
    private boolean isScrollFinish;
    private final GradientDrawable mBgShadeDrawable;
    private final Paint mBgShadePaint;
    private final Path mBgShadePath;
    private i mCursorContentViewCtrl;
    private int mItemCountPreRow;
    public SparseArray<b> mPageCache;
    private Drawable mPreDrawable;
    protected JDViewPagerWithGridView mViewPager;
    private int oldPagePosition;
    private boolean useClip;

    /* loaded from: classes4.dex */
    public static class JDViewPagerWithGridView extends ViewPager {
        public JDViewPagerWithGridView(Context context) {
            super(context);
        }

        public JDViewPagerWithGridView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public MallFloorIcon(Context context) {
        super(context);
        this.mViewPager = null;
        this.mPreDrawable = null;
        this.isFirstLoaded = new AtomicBoolean(true);
        this.isScrollFinish = true;
        this.useClip = false;
        this.mBgShadePaint = new Paint(1);
        this.mBgShadePath = new Path();
        this.mBgShadeDrawable = new GradientDrawable() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorIcon.3
            @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                super.draw(canvas);
                canvas.drawPath(MallFloorIcon.this.mBgShadePath, MallFloorIcon.this.mBgShadePaint);
            }
        };
        this.mPageCache = new SparseArray<>();
        initIconView();
    }

    private JDIconSingleContainerRy createGridView(int i2, o oVar) {
        JDIconSingleContainerRy jDIconSingleContainerRy;
        b bVar = this.mPageCache.get(i2);
        if (!(bVar instanceof JDIconSingleContainerRy)) {
            jDIconSingleContainerRy = new JDIconSingleContainerRy(getContext(), oVar);
            this.mPageCache.put(i2, jDIconSingleContainerRy);
        } else {
            jDIconSingleContainerRy = (JDIconSingleContainerRy) bVar;
            m.K(jDIconSingleContainerRy);
        }
        jDIconSingleContainerRy.setLayoutParams(new ViewPager.LayoutParams());
        jDIconSingleContainerRy.setClipChildren(false);
        jDIconSingleContainerRy.setBackgroundColor(0);
        jDIconSingleContainerRy.setPadding(((o) this.mPresenter).Z(), ((o) this.mPresenter).a0(), ((o) this.mPresenter).Z(), 0);
        jDIconSingleContainerRy.setContentDescription("NewAppcenter");
        return jDIconSingleContainerRy;
    }

    private JDViewPagerWithGridView generateViewPager() {
        JDViewPagerWithGridView jDViewPagerWithGridView = new JDViewPagerWithGridView(getContext());
        jDViewPagerWithGridView.setContentDescription("\u767e\u5b9d\u7bb1");
        jDViewPagerWithGridView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        jDViewPagerWithGridView.setOnPageChangeListener(this);
        jDViewPagerWithGridView.setId(R.id.mallfloor_iconfloor_pager);
        jDViewPagerWithGridView.setBackgroundColor(0);
        return jDViewPagerWithGridView;
    }

    private void setFloorColorBg() {
        if (((o) this.mPresenter).C0()) {
            if (((o) this.mPresenter).V() != 0) {
                setBgShadeColor(this, needClip() ? d.d(20) : 0, ((o) this.mPresenter).V());
                return;
            } else {
                resetBgShadeColor(this);
                return;
            }
        }
        float[] r = ((o) this.mPresenter).r();
        if (!isLineIcon() && ((o) this.mPresenter).c0() == 2) {
            r = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{r[0], r[0], r[1], r[1], r[2], r[2], r[3], r[3]}, null, null));
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setColor(((o) this.mPresenter).V());
        this.mPreDrawable = shapeDrawable;
        setBackgroundDrawable(shapeDrawable);
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
        this.mCursorContentViewCtrl.g(((o) this.mPresenter).getCursorMarginBottom());
        this.mViewPager.setCurrentItem(i3);
    }

    public void beforeNewAppCenterInfoParse() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void beforeViewBind() {
        super.beforeViewBind();
        if (this.isScrollFinish || isLineIcon()) {
            return;
        }
        this.mViewPager.requestLayout();
    }

    protected void generateIconFloor() {
        if (isLineIcon()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int r0 = ((o) this.mPresenter).r0();
        int m0 = ((o) this.mPresenter).m0();
        for (int i2 = 0; i2 < r0; i2++) {
            JDIconSingleContainerRy createGridView = createGridView(i2, (o) this.mPresenter);
            createGridView.setPageIndex(i2);
            createGridView.setLayoutManager(new GridLayoutManager(getContext(), m0));
            arrayList.add(createGridView);
        }
        IconViewPagerAdapter iconViewPagerAdapter = this.iconViewPagerAdapter;
        if (iconViewPagerAdapter == null) {
            IconViewPagerAdapter iconViewPagerAdapter2 = new IconViewPagerAdapter(arrayList);
            this.iconViewPagerAdapter = iconViewPagerAdapter2;
            this.mViewPager.setAdapter(iconViewPagerAdapter2);
        } else {
            iconViewPagerAdapter.f(arrayList);
            this.iconViewPagerAdapter.notifyDataSetChanged();
        }
        ViewGroup.LayoutParams layoutParams = this.mViewPager.getLayoutParams();
        f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        if (((o) this.mPresenter).b0() == 2 && ((o) this.mPresenter).t0() == 2 && !((o) this.mPresenter).B0() && !((o) this.mPresenter).z0() && !((o) this.mPresenter).K0() && !((o) this.mPresenter).y0() && !((o) this.mPresenter).C0() && !inVideoFloor()) {
            layoutParams2.topMargin = d.d(8);
        } else {
            layoutParams2.topMargin = 0;
        }
        generateViewPagerCursor((o) this.mPresenter);
    }

    protected void generateViewPagerCursor(o oVar) {
        i iVar = this.mCursorContentViewCtrl;
        if (iVar != null) {
            iVar.h();
        }
        com.jingdong.app.mall.home.floor.ctrl.d dVar = new com.jingdong.app.mall.home.floor.ctrl.d();
        this.mCursorContentViewCtrl = dVar;
        dVar.f(oVar.H0() ? new AnimationLinerPagerCursor(getContext()) : new LinerPagerCursor(getContext()), this.mViewPager.getId(), oVar, oVar.getCursorMarginBottom());
        View d = this.mCursorContentViewCtrl.d();
        if (d != null) {
            d.setContentDescription("\u767e\u5b9d\u7bb1\u6307\u793a\u5668");
        }
        int count = this.mViewPager.getAdapter().getCount();
        if (count > 1) {
            updateUiWithPagePosition(count, this.currentPagePosition);
        }
    }

    protected int getClipRadius() {
        if (((o) this.mPresenter).y0()) {
            return d.d(24);
        }
        if (((o) this.mPresenter).C0()) {
            return d.d(12);
        }
        return 0;
    }

    @Override // com.jingdong.app.mall.home.e.b
    public int getTextSize() {
        return ((o) this.mPresenter).w0();
    }

    public boolean inVideoFloor() {
        return false;
    }

    public void initIconView() {
        JDViewPagerWithGridView generateViewPager = generateViewPager();
        this.mViewPager = generateViewPager;
        this.currentPagePosition = 0;
        addView(generateViewPager);
    }

    public boolean isLineIcon() {
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean isShowItemDivider() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean needClip() {
        return this.useClip && ((o) this.mPresenter).T0();
    }

    public void notifyAllPageDataSetChanged() {
        JDViewPagerWithGridView jDViewPagerWithGridView;
        List<b> d;
        if (isLineIcon() || (jDViewPagerWithGridView = this.mViewPager) == null) {
            return;
        }
        PagerAdapter adapter = jDViewPagerWithGridView.getAdapter();
        f.n(adapter);
        IconViewPagerAdapter iconViewPagerAdapter = (IconViewPagerAdapter) adapter;
        if (iconViewPagerAdapter == null || (d = iconViewPagerAdapter.d()) == null) {
            return;
        }
        Iterator<b> it = d.iterator();
        while (it.hasNext()) {
            it.next().notifyDataSetByChange();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        JDViewPagerWithGridView jDViewPagerWithGridView = this.mViewPager;
        if (jDViewPagerWithGridView != null) {
            int currentItem = jDViewPagerWithGridView.getCurrentItem();
            this.currentPagePosition = currentItem;
            this.oldPagePosition = currentItem;
            if (this.isScrollFinish || isLineIcon()) {
                return;
            }
            this.mViewPager.requestLayout();
            i iVar = this.mCursorContentViewCtrl;
            if (iVar != null) {
                iVar.onPageScrolled(this.mViewPager.getCurrentItem(), 0.0f, 0);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeResume(MallFloorEvent mallFloorEvent) {
        super.onHomeResume(mallFloorEvent);
        if (mallFloorEvent.d() || ((o) this.mPresenter).B0() || ((o) this.mPresenter).J0() || ((o) this.mPresenter).A0() || !((o) this.mPresenter).F0() || !this.mFloorBindElement.isLastData()) {
            return;
        }
        ((o) this.mPresenter).R0();
    }

    public void onLoadView(boolean z) {
        e.b().a(this);
        f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorIcon.1
            @Override // com.jingdong.app.mall.home.o.a.b
            public void safeRun() {
                MallFloorIcon.this.onRefreshViewInMainThread(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onLoadingBgCompleteOnMainThread(String str, Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            c cVar = new c(bitmap);
            this.mPreDrawable = cVar;
            setBackgroundDrawable(cVar);
            notifyAllPageDataSetChanged();
            return;
        }
        onLoadingBgFailed(null, null);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected void onLoadingBgFailedOnMainThread(String str, JDFailReason jDFailReason) {
        setFloorColorBg();
        notifyAllPageDataSetChanged();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i2) {
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.mCursorContentViewCtrl == null || isLineIcon()) {
            return;
        }
        this.isScrollFinish = i3 == 0;
        this.mCursorContentViewCtrl.onPageScrolled(i2, f2, i3);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        if (this.mCursorContentViewCtrl == null || isLineIcon()) {
            return;
        }
        this.currentPagePosition = i2;
        this.mCursorContentViewCtrl.onPageSelected(i2);
        this.mCursorContentViewCtrl.e(i2);
        sendExpoOnePageMaidian(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onParseEnd() {
        super.onParseEnd();
        ((o) this.mPresenter).x0(this.mItemCountPreRow);
    }

    public synchronized void onRefreshViewInMainThread(boolean z) {
        if (!isLineIcon() && ((o) this.mPresenter).D0()) {
            generateIconFloor();
            int i2 = 0;
            if (z) {
                if (this.isFirstLoaded.get()) {
                    f.F0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorIcon.2
                        @Override // com.jingdong.app.mall.home.o.a.b
                        public void safeRun() {
                            MallFloorIcon mallFloorIcon = MallFloorIcon.this;
                            mallFloorIcon.sendExpoOnePageMaidian(mallFloorIcon.currentPagePosition);
                            MallFloorIcon.this.isFirstLoaded.set(true);
                        }
                    }, 1000L);
                    this.isFirstLoaded.set(false);
                }
            } else if (((o) this.mPresenter).G0()) {
                int i3 = this.oldPagePosition;
                int i4 = this.currentPagePosition;
                if (i3 == i4) {
                    sendExpoOnePageMaidian(i4);
                }
            }
            Drawable drawable = this.mPreDrawable;
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            if (z) {
                if (needClip()) {
                    int d = d.d(20);
                    com.jingdong.app.mall.home.n.h.e.f(this.mViewPager, true, new Rect(d, 0, d, 0), getClipRadius());
                    i2 = d;
                } else {
                    com.jingdong.app.mall.home.n.h.e.i(this.mViewPager);
                }
                if (useShadeColor()) {
                    setBgShadeColor(this.mViewPager, i2, ((o) this.mPresenter).W());
                } else {
                    resetBgShadeColor(this.mViewPager);
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.home.e.b
    public void onTextScaleModeChanged(int i2) {
        List<b> d;
        if (this.iconViewPagerAdapter == null || ((o) this.mPresenter).z0() || ((o) this.mPresenter).K0() || ((o) this.mPresenter).y0() || ((o) this.mPresenter).J0() || (d = this.iconViewPagerAdapter.d()) == null) {
            return;
        }
        Iterator<b> it = d.iterator();
        while (it.hasNext()) {
            it.next().updateItemTextSize(i2);
        }
    }

    protected void resetBgShadeColor(View view) {
        view.setBackgroundColor(0);
    }

    public void sendExpoOnePageMaidian(int i2) {
        if (isLineIcon() || ((o) this.mPresenter).j()) {
            return;
        }
        int g0 = ((o) this.mPresenter).g0(i2);
        int g02 = i2 <= 0 ? 0 : ((o) this.mPresenter).g0(i2 - 1) * i2;
        int i3 = i2 + 1;
        int i4 = i3 * g0;
        boolean z = true;
        if (((o) this.mPresenter).K0() && i2 == 1) {
            i4 = g02 + g0;
        }
        StringBuilder sb = new StringBuilder();
        JSONArray d = com.jingdong.app.mall.home.r.c.b.d();
        while (true) {
            if (g02 >= i4) {
                z = false;
                break;
            }
            com.jingdong.app.mall.home.r.e.k.c U = ((o) this.mPresenter).U(g02);
            if (U != null) {
                if (U.p()) {
                    break;
                }
                U.r();
                sb.append(((o) this.mPresenter).U(g02).getJump().srv);
                sb.append("&&");
                d.put(U.o);
            }
            g02++;
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

    /* JADX INFO: Access modifiers changed from: protected */
    public void setBgShadeColor(View view, int i2, int i3) {
        if (i3 == 0) {
            resetBgShadeColor(view);
            return;
        }
        this.mBgShadePath.reset();
        this.mBgShadePaint.setColor(i3);
        g.g(i2, 0.0f, d.f9279g - i2, getLayoutHeight(), getClipRadius(), this.mBgShadePath);
        view.setBackgroundDrawable(this.mBgShadeDrawable);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean unUseSelfBg() {
        return false;
    }

    public boolean useDarkTextColor() {
        return com.jingdong.app.mall.home.state.dark.a.h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useRoundMarginColor() {
        com.jingdong.app.mall.home.r.e.d dVar = this.mFloorBindElement;
        return dVar != null && dVar.t > 1;
    }

    protected boolean useShadeColor() {
        return ((o) this.mPresenter).y0();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public o createPresenter() {
        return new o(IconFloorEntity.class, IconFloorEngine.class);
    }

    public MallFloorIcon(Context context, int i2) {
        super(context);
        this.mViewPager = null;
        this.mPreDrawable = null;
        this.isFirstLoaded = new AtomicBoolean(true);
        this.isScrollFinish = true;
        this.useClip = false;
        this.mBgShadePaint = new Paint(1);
        this.mBgShadePath = new Path();
        this.mBgShadeDrawable = new GradientDrawable() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorIcon.3
            @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                super.draw(canvas);
                canvas.drawPath(MallFloorIcon.this.mBgShadePath, MallFloorIcon.this.mBgShadePaint);
            }
        };
        this.mPageCache = new SparseArray<>();
        this.useClip = true;
        this.mItemCountPreRow = i2;
        initIconView();
    }
}
