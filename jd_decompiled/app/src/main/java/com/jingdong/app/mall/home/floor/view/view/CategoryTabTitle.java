package com.jingdong.app.mall.home.floor.view.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.base.HomeImageView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.floor.view.widget.catatorytab.CategoryTab;
import com.jingdong.app.mall.home.n.h.c;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.f.a.j;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.entity.JumpEntity;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class CategoryTabTitle extends RelativeLayout {
    private static final int ALL_SHADOW_WIDTH = 20;
    private static final int RIGHT_WIDTH = 133;
    public static JDDisplayImageOptions mOption;
    private static final List<b> sExpoList;
    private int currentX;
    private AtomicBoolean isRebind;
    private final AtomicBoolean isReportScroll;
    private LinearLayout mAllContent;
    private f mAllContentSize;
    private ImageView mAllShadow;
    private f mAllShadowSize;
    private int mBindWidth;
    private d mCurrentElements;
    public CategoryTab mCurrentItem;
    private final SparseArray<b> mExpoArr;
    public CategoryTab mFirstItem;
    private final Handler mHandler;
    private SimpleDraweeView mImgCategory;
    private f mImgCategorySize;
    private int mPreScrollX;
    private final j mPresenter;
    public HorizontalScrollView mScrollContent;
    private ScrollType mScrollType;
    private final SparseArray<CategoryTab> mTabCache;
    private final LinearLayout mTabContent;
    private final f mTabContentSize;
    private com.jingdong.app.mall.home.o.a.b scrollRunnable;

    /* loaded from: classes4.dex */
    enum ScrollType {
        IDLE,
        TOUCH_SCROLL,
        FLING
    }

    static {
        JDDisplayImageOptions resetViewBeforeLoading = com.jingdong.app.mall.home.floor.ctrl.f.a().resetViewBeforeLoading(false);
        int i2 = R.drawable.home_icon_select_all;
        mOption = resetViewBeforeLoading.showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
        sExpoList = new CopyOnWriteArrayList();
    }

    public CategoryTabTitle(Context context, j jVar) {
        super(context);
        this.isReportScroll = new AtomicBoolean(true);
        this.mExpoArr = new SparseArray<>();
        this.mTabCache = new SparseArray<>();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.isRebind = new AtomicBoolean(false);
        this.currentX = -999999;
        this.mScrollType = ScrollType.IDLE;
        this.scrollRunnable = new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle.7
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                HorizontalScrollView horizontalScrollView = CategoryTabTitle.this.mScrollContent;
                if (horizontalScrollView == null) {
                    return;
                }
                if (horizontalScrollView.getScrollX() == CategoryTabTitle.this.currentX) {
                    CategoryTabTitle.this.mScrollType = ScrollType.IDLE;
                    CategoryTabTitle.this.mHandler.removeCallbacks(this);
                    CategoryTabTitle.this.mPresenter.a0(true);
                    return;
                }
                CategoryTabTitle.this.mScrollType = ScrollType.FLING;
                CategoryTabTitle categoryTabTitle = CategoryTabTitle.this;
                categoryTabTitle.currentX = categoryTabTitle.mScrollContent.getScrollX();
                CategoryTabTitle.this.mHandler.postDelayed(CategoryTabTitle.this.scrollRunnable, 50L);
            }
        };
        this.mPresenter = jVar;
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context) { // from class: com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle.1
            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    CategoryTabTitle.this.mPreScrollX = -1;
                    CategoryTabTitle.this.isReportScroll.set(true);
                    CategoryTabTitle.this.mScrollType = ScrollType.TOUCH_SCROLL;
                    CategoryTabTitle.this.mHandler.removeCallbacks(CategoryTabTitle.this.scrollRunnable);
                } else if (action == 1) {
                    CategoryTabTitle.this.mHandler.post(CategoryTabTitle.this.scrollRunnable);
                }
                return super.dispatchTouchEvent(motionEvent);
            }

            @Override // android.widget.HorizontalScrollView, android.view.View
            protected void onOverScrolled(int i2, int i3, boolean z, boolean z2) {
                super.onOverScrolled(i2, i3, z, z2);
                if (CategoryTabTitle.this.mPreScrollX > 0 && CategoryTabTitle.this.mPreScrollX - i2 < 0 && CategoryTabTitle.this.isReportScroll.getAndSet(false)) {
                    a.s("Home_ClassifyTabSlide", "", CategoryTabTitle.this.mPresenter.U());
                }
                CategoryTabTitle.this.checkExpoItem();
                CategoryTabTitle.this.mPreScrollX = i2;
            }

            @Override // android.widget.HorizontalScrollView, android.view.View
            public void scrollTo(int i2, int i3) {
                super.scrollTo(i2, i3);
                CategoryTabTitle.this.checkExpoItem();
            }
        };
        this.mScrollContent = horizontalScrollView;
        horizontalScrollView.setClipToPadding(false);
        this.mScrollContent.setHorizontalScrollBarEnabled(false);
        LinearLayout linearLayout = new LinearLayout(context);
        this.mTabContent = linearLayout;
        linearLayout.setOrientation(0);
        this.mScrollContent.addView(linearLayout, new FrameLayout.LayoutParams(-2, -1));
        f fVar = new f(-2, -1);
        this.mTabContentSize = fVar;
        HorizontalScrollView horizontalScrollView2 = this.mScrollContent;
        addView(horizontalScrollView2, fVar.u(horizontalScrollView2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFloorData() {
        this.mExpoArr.clear();
        List<CategoryEntity.CaItem> Q = this.mPresenter.Q();
        int size = Q.size();
        this.mTabContent.removeAllViews();
        if (size <= 0) {
            onSetVisible(false);
            return;
        }
        onSetVisible(true);
        for (int i2 = 0; i2 < size; i2++) {
            CategoryEntity.CaItem caItem = Q.get(i2);
            if (!caItem.isCityBuyTab() || caItem.isDirect()) {
                CategoryTab createItemTab = createItemTab(caItem.getPosition());
                createItemTab.k(this, this.mPresenter, caItem, i2);
                if (i2 == 0) {
                    caItem.setPName(TitleTabManager.getInstance().getHomeName());
                    this.mFirstItem = createItemTab;
                } else {
                    createItemTab.u(false);
                }
                addItemTabByIndex(-1, createItemTab);
            }
        }
        this.mCurrentItem = this.mFirstItem;
        this.mScrollContent.scrollTo(0, 0);
        onItemSelect(this.mFirstItem.m(), 0);
        this.mFirstItem.u(true);
        this.isRebind.set(false);
        com.jingdong.app.mall.home.o.a.f.u0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle.4
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                CategoryTabTitle.this.updateAndCheckExpo();
                CategoryTabTitle.this.mPresenter.a0(true);
            }
        });
    }

    private void initJumpAllBtn() {
        boolean Y = this.mPresenter.Y();
        int T = this.mPresenter.T();
        int i2 = T > 0 ? T : 113;
        this.mTabContentSize.J(16, 0, 16, 0);
        f fVar = this.mTabContentSize;
        if (!Y) {
            i2 = 0;
        }
        fVar.E(0, 0, i2, 0);
        HorizontalScrollView horizontalScrollView = this.mScrollContent;
        horizontalScrollView.setLayoutParams(this.mTabContentSize.u(horizontalScrollView));
        int i3 = T > 0 ? T : 92;
        int i4 = T > 0 ? i3 : 133;
        JDDisplayImageOptions jDDisplayImageOptions = T > 0 ? e.f9402h : mOption;
        if (Y) {
            a.y("Home_AllClassifyExpo", "", this.mPresenter.U());
            f fVar2 = this.mImgCategorySize;
            if (fVar2 != null) {
                fVar2.R(i3, 48);
                this.mImgCategorySize.E(T > 0 ? 0 : 12, 0, 0, 0);
            }
            if (this.mImgCategory != null) {
                e.f(this.mPresenter.R(), this.mImgCategory, jDDisplayImageOptions);
            }
        }
        if (this.mAllContent != null) {
            this.mAllContentSize.R(i4, -1);
            f.d(this.mAllContent, this.mAllContentSize, true);
            f.c(this.mAllShadow, this.mAllShadowSize);
            f.d(this.mImgCategory, this.mImgCategorySize, true);
            c.k(T > 0, this.mAllShadow);
            c.k(!Y, this.mAllContent);
        } else if (Y) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            this.mAllContent = linearLayout;
            linearLayout.setGravity(16);
            this.mAllContent.setOrientation(0);
            HomeImageView homeImageView = new HomeImageView(getContext());
            this.mAllShadow = homeImageView;
            homeImageView.setImageResource(R.drawable.home_icon_select_all_shadow);
            this.mAllShadow.setScaleType(ImageView.ScaleType.FIT_XY);
            this.mAllShadow.setVisibility(T > 0 ? 8 : 0);
            this.mAllShadowSize = new f(20, -1);
            c.k(T > 0, this.mAllShadow);
            LinearLayout linearLayout2 = this.mAllContent;
            ImageView imageView = this.mAllShadow;
            linearLayout2.addView(imageView, this.mAllShadowSize.i(imageView));
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.mImgCategory = homeDraweeView;
            homeDraweeView.setContentDescription("\u5206\u7c7b");
            this.mImgCategory.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.mImgCategory.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
            f fVar3 = new f(i3, 48);
            this.mImgCategorySize = fVar3;
            fVar3.E(T > 0 ? 0 : 12, 0, 0, 0);
            LinearLayout linearLayout3 = this.mAllContent;
            SimpleDraweeView simpleDraweeView = this.mImgCategory;
            linearLayout3.addView(simpleDraweeView, this.mImgCategorySize.i(simpleDraweeView));
            this.mAllContent.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    JumpEntity S = CategoryTabTitle.this.mPresenter.S();
                    if (S == null) {
                        return;
                    }
                    a.s("Home_AllClassify", "", CategoryTabTitle.this.mPresenter.U());
                    l.e(CategoryTabTitle.this.getContext(), S);
                }
            });
            f fVar4 = new f(i4, -1);
            this.mAllContentSize = fVar4;
            RelativeLayout.LayoutParams u = fVar4.u(this.mAllContent);
            u.addRule(11);
            addView(this.mAllContent, u);
            e.f(this.mPresenter.R(), this.mImgCategory, jDDisplayImageOptions);
        }
    }

    public static void onItemSelect(CategoryEntity.CaItem caItem, int i2) {
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 != null) {
            z0.c1(caItem, i2);
        }
    }

    private void onSetVisible(boolean z) {
        ViewParent parent = getParent();
        if (parent instanceof MallFloorCategory) {
            ((MallFloorCategory) parent).onSetVisible(z);
        } else {
            setVisibility(z ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reBindData() {
        if (this.mCurrentElements == null || this.mBindWidth == com.jingdong.app.mall.home.floor.common.d.f9279g) {
            return;
        }
        this.isRebind.set(true);
        onViewBindData(this.mCurrentElements);
    }

    public static void reportExpoData() {
        List<b> list = sExpoList;
        if (list.size() <= 0) {
            return;
        }
        String j2 = com.jingdong.app.mall.home.a.t.j();
        JSONArray d = b.d();
        for (b bVar : list) {
            bVar.a("ceiling", j2);
            d.put(bVar);
        }
        sExpoList.clear();
        a.y("Home_ClassifyTabExpo", "", d.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAndCheckExpo() {
        CategoryEntity.CaItem m2;
        int childCount = this.mTabContent.getChildCount();
        int width = this.mScrollContent.getWidth();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mTabContent.getChildAt(i2);
            if ((childAt instanceof CategoryTab) && (m2 = ((CategoryTab) childAt).m()) != null) {
                b expoJson = m2.getExpoJson();
                this.mExpoArr.put(i2, expoJson);
                if (childAt.getLeft() < width) {
                    expoJson.a("styleexpo", "0");
                } else {
                    expoJson.a("styleexpo", "1");
                }
            }
        }
        checkExpoItem();
    }

    public void addAsyncTab(CategoryEntity.CaItem caItem) {
        int position = caItem.getPosition();
        CategoryTab createItemTab = createItemTab(position);
        createItemTab.k(this, this.mPresenter, caItem, position);
        addItemTabByIndex(position, createItemTab);
        com.jingdong.app.mall.home.o.a.f.u0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle.5
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                CategoryTabTitle.this.updateAndCheckExpo();
            }
        });
    }

    public void addItemTabByIndex(int i2, CategoryTab categoryTab) {
        m.b(this.mTabContent, categoryTab, i2);
    }

    public void changeTabName() {
        CategoryTab categoryTab = this.mFirstItem;
        if (categoryTab != null) {
            categoryTab.t();
        }
    }

    public void checkExpoItem() {
        b bVar;
        try {
            int childCount = this.mTabContent.getChildCount();
            if (sExpoList.size() >= childCount) {
                return;
            }
            int width = this.mScrollContent.getWidth();
            int scrollX = this.mScrollContent.getScrollX();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.mTabContent.getChildAt(i2);
                int right = childAt.getRight();
                if (right > 0 && right > scrollX && childAt.getLeft() < scrollX + width && (bVar = this.mExpoArr.get(i2)) != null) {
                    List<b> list = sExpoList;
                    if (!list.contains(bVar)) {
                        list.add(bVar);
                        if (childAt instanceof CategoryTab) {
                            ((CategoryTab) childAt).s();
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public CategoryTab createItemTab(int i2) {
        CategoryTab categoryTab = this.mTabCache.get(i2);
        if (categoryTab == null) {
            CategoryTab categoryTab2 = new CategoryTab(getContext());
            categoryTab2.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
            this.mTabCache.put(i2, categoryTab2);
            return categoryTab2;
        }
        return categoryTab;
    }

    public CategoryEntity.CaItem getFirstItem() {
        try {
            CategoryTab categoryTab = this.mFirstItem;
            if (categoryTab == null) {
                return this.mPresenter.Q().get(0);
            }
            return categoryTab.m();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean isRebind() {
        return this.isRebind.get();
    }

    public void onBackPressed() {
        CategoryTab categoryTab = this.mCurrentItem;
        if (categoryTab == null || categoryTab == this.mFirstItem) {
            return;
        }
        this.mScrollContent.scrollTo(0, 0);
        this.mCurrentItem.u(false);
        this.mFirstItem.u(true);
        CategoryTab categoryTab2 = this.mFirstItem;
        this.mCurrentItem = categoryTab2;
        onItemSelect(categoryTab2.m(), 0);
    }

    public void onViewBindData(d dVar) {
        this.mBindWidth = com.jingdong.app.mall.home.floor.common.d.f9279g;
        this.mCurrentElements = dVar;
        initJumpAllBtn();
        com.jingdong.app.mall.home.o.a.f.u0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle.2
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                CategoryTabTitle.this.initFloorData();
            }
        });
    }

    public void setLayoutHeight(int i2) {
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = i2;
            com.jingdong.app.mall.home.o.a.f.u0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle.6
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    CategoryTabTitle.this.reBindData();
                    CategoryTabTitle.this.setLayoutParams(layoutParams);
                }
            });
        }
    }
}
