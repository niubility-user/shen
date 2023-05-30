package com.jingdong.app.mall.home.floor.view.view.title;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.h;
import com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle;
import com.jingdong.app.mall.home.floor.view.view.SearchWordEntity;
import com.jingdong.app.mall.home.floor.view.widget.HomeTitleViewFlipper;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.widget.HomeTopBgView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.messagecenter.view.MainPageMessageDoorView;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class HomeTitleB extends IHomeTitle implements e.b {
    private static final int MIN_HEIGHT = 108;
    private static final int MSG_ICON_SIZE = 48;
    private static final int NORMAL_HEIGHT = 168;
    private static final int SEARCH_BAR_MAX_MARGIN_TOP = 20;
    private static final int SEARCH_BAR_MAX_WIDTH = 626;
    protected static final int TITLE_MAX_OFFSET = 200;
    private final String TAG;
    AccelerateInterpolator accelerate;
    ValueAnimator alphaAnimator;
    private String clickSrvJson;
    DecelerateInterpolator decelerate;
    private JDHomeFragment fragment;
    private String iconImg;
    private JumpEntity iconJump;
    private SimpleDraweeView iconSearch;
    private boolean isLightBg;
    private boolean isRefreshing;
    private final int lightBgColor;
    private final View mBaseLine;
    private Bitmap mBgBitmap;
    private Matrix mBgMatrix;
    private int mBindWidth;
    private CategoryTabTitle mCategoryTabTitle;
    private float mCurrentScrollHeight;
    private final f mFlipperSize;
    private boolean mIsPause;
    private final int mMessageBlackRes;
    private MainPageMessageDoorView mMessageDoorView;
    private final int mMessageRes;
    private final f mMsgSize;
    private float mRealScrollHeight;
    private final f mSearchBtnSize;
    private final f mSearchContentSize;
    private final f mSearchIconSize;
    private HomeTopBgView mSkinBgView;
    private Bitmap mSkinBitmap;
    private Matrix mSkinMatrix;
    private final f mTitleSize;
    private RelativeLayout searchBarContent;
    private TextView searchBtn;
    private HomeTitleViewFlipper searchViewFlipper;
    private int statusBarHeight;
    private BaseActivity thisActivity;
    private final int unLightBgColor;

    public HomeTitleB(Context context) {
        super(context);
        this.TAG = HomeTitleB.class.getSimpleName();
        this.lightBgColor = -16777216;
        this.unLightBgColor = -1;
        this.mMessageRes = R.drawable.home_title_new_message_white;
        this.mMessageBlackRes = R.drawable.home_title_new_message_black;
        f fVar = new f(-1, 168);
        this.mTitleSize = fVar;
        this.mSearchContentSize = new f(R2.attr.cameraTargetLat, 64);
        this.mSearchIconSize = new f(64, 64);
        this.mFlipperSize = new f(-1, 62);
        this.mSearchBtnSize = new f(104, 56);
        this.mMsgSize = new f(76, 104);
        this.mBindWidth = d.f9279g;
        this.mBgMatrix = new Matrix();
        this.alphaAnimator = new ValueAnimator();
        this.accelerate = new AccelerateInterpolator();
        this.decelerate = new DecelerateInterpolator();
        com.jingdong.app.mall.home.o.a.f.n(context);
        this.thisActivity = (BaseActivity) context;
        this.statusBarHeight = h.A;
        if (!h.N().c0()) {
            fVar.I(0);
        } else {
            fVar.I(this.statusBarHeight);
        }
        HomeTopBgView homeTopBgView = new HomeTopBgView(context);
        this.mSkinBgView = homeTopBgView;
        homeTopBgView.setAlpha(0.0f);
        addView(this.mSkinBgView, new RelativeLayout.LayoutParams(-1, -1));
        View view = new View(context);
        this.mBaseLine = view;
        view.setId(R.id.home_title_baseline);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams.topMargin = fVar.o();
        addView(view, layoutParams);
        addTitleViews();
        initAnimator();
        setLayoutParams(new ViewGroup.LayoutParams(fVar.v(), fVar.h()));
    }

    private void addMsgIcon() {
        this.mMessageDoorView = new MainPageMessageDoorView(getContext());
        this.mMessageDoorView.setMsgImgDrawable(1, ResourcesCompat.getDrawable(getContext().getResources(), this.mMessageRes, null), ResourcesCompat.getDrawable(getContext().getResources(), this.mMessageBlackRes, null));
        this.mMessageDoorView.setMessageImgSize(d.d(48));
        this.mMessageDoorView.setMessageClickListener(new MainPageMessageDoorView.MessageClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleB.1
            @Override // com.jingdong.common.messagecenter.view.MainPageMessageDoorView.MessageClickListener
            public void OnMessageClick() {
                b bVar = new b();
                bVar.a("status", HomeTitleB.this.getSearchProgress());
                a.s("Home_MessageCenter", "", bVar.toString());
            }
        });
        this.mMessageDoorView.setMessageTypeface(Typeface.defaultFromStyle(0));
        this.mMessageDoorView.setMessageTextSize(-1);
        this.mMsgSize.F(new Rect(0, 0, 6, 0));
        RelativeLayout.LayoutParams u = this.mMsgSize.u(this.mMessageDoorView);
        u.addRule(3, this.mBaseLine.getId());
        u.addRule(11);
        addView(this.mMessageDoorView, u);
    }

    private void addSearchBar() {
        this.searchBarContent = new RelativeLayout(getContext());
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.iconSearch = homeDraweeView;
        homeDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleB.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HomeTitleB.this.iconJump == null) {
                    return;
                }
                b c2 = b.c(HomeTitleB.this.iconJump.srvJson);
                c2.a("status", "0");
                a.s("Home_Scan", "", c2.toString());
                l.e(HomeTitleB.this.getContext(), HomeTitleB.this.iconJump);
            }
        });
        this.iconSearch.setScaleType(ImageView.ScaleType.FIT_XY);
        this.mSearchIconSize.E(12, 0, 0, 0);
        RelativeLayout.LayoutParams u = this.mSearchIconSize.u(this.iconSearch);
        u.addRule(15);
        this.searchBarContent.addView(this.iconSearch, u);
        HomeTitleViewFlipper homeTitleViewFlipper = new HomeTitleViewFlipper(getContext(), new HomeTitleViewFlipper.c() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleB.3
            @Override // com.jingdong.app.mall.home.floor.view.widget.HomeTitleViewFlipper.c
            public void onClick(SearchWordEntity searchWordEntity) {
                HomeTitleB.this.gotoSearch(searchWordEntity);
            }
        });
        this.searchViewFlipper = homeTitleViewFlipper;
        homeTitleViewFlipper.setBackgroundColor(0);
        this.mFlipperSize.J(80, 8, R2.anim.mtrl_bottom_sheet_slide_in, 8);
        RelativeLayout.LayoutParams u2 = this.mFlipperSize.u(this.searchViewFlipper);
        u2.addRule(15);
        this.searchBarContent.addView(this.searchViewFlipper, u2);
        this.searchBarContent.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleB.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeTitleB homeTitleB = HomeTitleB.this;
                homeTitleB.gotoSearch(homeTitleB.searchViewFlipper.m());
            }
        });
        g gVar = new g(getContext(), false);
        gVar.d(17);
        gVar.l(-1);
        gVar.m(28);
        gVar.i("\u641c\u7d22");
        this.searchBtn = gVar.a();
        this.mSearchBtnSize.E(0, 0, 4, 0);
        RelativeLayout.LayoutParams u3 = this.mSearchBtnSize.u(this.searchBtn);
        u3.addRule(11);
        u3.addRule(15);
        this.searchBarContent.addView(this.searchBtn, u3);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-381927);
        gradientDrawable.setCornerRadius(this.mSearchBtnSize.h());
        this.searchBtn.setBackgroundDrawable(gradientDrawable);
        this.searchBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleB.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (l.h()) {
                    return;
                }
                HomeTitleFactory.gotoProductList(HomeTitleB.this.getContext(), HomeTitleB.this.searchViewFlipper.m(), HomeTitleB.this.clickSrvJson);
            }
        });
        this.mSearchContentSize.F(new Rect(20, 20, 0, 0));
        this.mSearchContentSize.Q(626);
        RelativeLayout.LayoutParams u4 = this.mSearchContentSize.u(this.searchBarContent);
        u4.addRule(3, this.mBaseLine.getId());
        u4.addRule(9);
        addView(this.searchBarContent, u4);
        this.searchBarContent.setVisibility(4);
        refreshScrollHeight();
    }

    private void addTitleViews() {
        addSearchBar();
        try {
            addMsgIcon();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        lightInit();
    }

    private void checkSearchViewFlipper(boolean z) {
        RelativeLayout relativeLayout = this.searchBarContent;
        boolean z2 = (relativeLayout == null || relativeLayout.getVisibility() != 0 || this.mIsPause) ? false : true;
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            if (z2) {
                homeTitleViewFlipper.u(z);
            } else {
                homeTitleViewFlipper.t();
            }
        }
    }

    private void drawBitmap(Canvas canvas) {
        Bitmap bitmap = this.mBgBitmap;
        if (bitmap != null && this.mBgMatrix != null && this.mRealScrollHeight != 0.0f) {
            if (bitmap.isRecycled()) {
                canvas.drawColor(-1957094);
            } else {
                canvas.drawBitmap(this.mBgBitmap, this.mBgMatrix, null);
            }
        }
        Pair<Bitmap, Matrix> S = h.N().S();
        if (S != null) {
            Bitmap bitmap2 = (Bitmap) S.first;
            Matrix matrix = (Matrix) S.second;
            if (bitmap2 == null || bitmap2.isRecycled()) {
                return;
            }
            canvas.drawBitmap(bitmap2, matrix, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSearchProgress() {
        return "0";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoSearch(SearchWordEntity searchWordEntity) {
        DeepLinkProductListHelper.homeToSearchActivity(this.thisActivity);
        HomeTitleFactory.sendSearchClickMta(searchWordEntity, getSearchProgress());
    }

    private void initAnimator() {
        this.alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleB.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                HomeTitleB.this.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
    }

    private void setSearchLayoutBackground() {
        if (this.searchBarContent == null) {
            return;
        }
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setAlpha(255);
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(this.mSearchContentSize.h());
        this.searchBarContent.setBackgroundDrawable(gradientDrawable);
    }

    private void toAlpha(float f2, long j2) {
        float alpha = getAlpha();
        this.alphaAnimator.cancel();
        if (Math.abs(f2 - alpha) != 0.0f && j2 > 0) {
            if (Build.VERSION.SDK_INT >= 22) {
                this.alphaAnimator.setInterpolator(alpha > f2 ? this.accelerate : this.decelerate);
            }
            this.alphaAnimator.setDuration(((float) j2) * r1);
            this.alphaAnimator.setFloatValues(alpha, f2);
            this.alphaAnimator.start();
            if (f2 <= 0.0f) {
                this.isRefreshing = true;
                return;
            } else if (f2 == 1.0f) {
                this.isRefreshing = false;
                return;
            } else {
                return;
            }
        }
        setAlpha(f2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean addCategoryView(CategoryTabTitle categoryTabTitle) {
        if (h.x > 0 && categoryTabTitle.getParent() != this) {
            CategoryTabTitle categoryTabTitle2 = this.mCategoryTabTitle;
            if (categoryTabTitle2 != categoryTabTitle) {
                m.K(categoryTabTitle2);
            }
            this.mCategoryTabTitle = categoryTabTitle;
            m.a(this, categoryTabTitle);
            categoryTabTitle.setLayoutHeight(d.d(84));
        }
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void addOverseasSwitchIcon(String str, String str2, int i2) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void addTopTab() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean afterRefresh() {
        checkTitleGray();
        if (com.jingdong.app.mall.home.a.f8565n) {
            if (JDHomeFragment.Q0()) {
                onResume(true);
            }
            com.jingdong.app.mall.home.a.f8565n = false;
        }
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.c();
        }
        if (h.x <= 0) {
            m.K(this.mCategoryTabTitle);
        } else {
            CategoryTabTitle categoryTabTitle = this.mCategoryTabTitle;
            if (categoryTabTitle != null && categoryTabTitle.getParent() == this) {
                this.mCategoryTabTitle.setLayoutHeight(d.d(84));
            }
        }
        this.mTitleSize.C(h.x > 0 ? 168 : 108);
        f.c(this, this.mTitleSize);
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void beforeRefresh() {
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.d();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void beforeSearchBoxWordRefresh() {
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.e();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void bindFragment(JDHomeFragment jDHomeFragment) {
        this.fragment = jDHomeFragment;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void changeSearchBarColorVarScrolling(int i2) {
        if (this.fragment == null) {
            return;
        }
        setScrollHeight(i2, false);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean checkJumpNearby() {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        try {
            drawBitmap(canvas);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.dispatchDraw(canvas);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void forceRefreshBarStatus() {
        if (JDHomeFragment.Q0()) {
            if (this.isLightBg) {
                UnStatusBarTintUtil.setStatusBarLightMode(this.thisActivity);
            } else {
                UnStatusBarTintUtil.setStatusBarDarkMode(this.thisActivity);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public int getBarHeightShrink() {
        return this.mTitleSize.h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public int getBarHeightSpread() {
        return this.mTitleSize.h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public int getCurrentBarHeight() {
        return this.mTitleSize.h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public View getHomeTitleView() {
        return this;
    }

    public boolean getIsPause() {
        return this.mIsPause || i.i() || this.isRefreshing;
    }

    @Override // com.jingdong.app.mall.home.e.b
    public int getTextSize() {
        return 28;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public Pair<Bitmap, Matrix> getTopBitmap() {
        Bitmap bitmap;
        Matrix matrix;
        if (h.N().D() && (bitmap = this.mSkinBitmap) != null) {
            matrix = this.mSkinMatrix;
        } else {
            bitmap = this.mBgBitmap;
            if (bitmap != null) {
                matrix = this.mBgMatrix;
            } else {
                bitmap = null;
                matrix = null;
            }
        }
        if (bitmap == null || matrix == null || bitmap.isRecycled()) {
            return null;
        }
        return new Pair<>(bitmap, matrix);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void hideTopTab() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean isAnimating() {
        return this.alphaAnimator.isRunning();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void isCheckHomeTab(boolean z) {
        super.isCheckHomeTab(z);
        com.jingdong.app.mall.home.v.c.a.b(this, z);
        h.N().b0(z);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean isScrollFixed() {
        return true;
    }

    public void lightInit() {
        this.searchViewFlipper.E(-8355712, false);
        setSearchLayoutBackground();
        this.searchBarContent.setVisibility(0);
        forceRefreshBarStatus();
        MainPageMessageDoorView mainPageMessageDoorView = this.mMessageDoorView;
        if (mainPageMessageDoorView != null) {
            mainPageMessageDoorView.setMessageDoorViewColorReverse(!this.isLightBg);
            this.mMessageDoorView.scrollChangeGrayIcon(this.isLightBg);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean onBackPressed() {
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return getAlpha() < 0.95f || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onPause() {
        this.mIsPause = true;
        checkSearchViewFlipper(false);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onPullOffset(BaseVerticalRefresh.l lVar, int i2, long j2) {
        if (BaseVerticalRefresh.l.MANUAL_REFRESHING != lVar && BaseVerticalRefresh.l.REFRESHING != lVar) {
            int d = d.d(130);
            if (BaseVerticalRefresh.l.RESET == lVar && i2 < d) {
                toAlpha(1.0f, j2);
                return;
            }
            this.alphaAnimator.cancel();
            int min = Math.min(Math.max(i2, 0), d.d(70));
            setAlpha((d.d(70) - min) / d.d(70));
            this.isRefreshing = min > 0;
            return;
        }
        toAlpha(0.0f, 0L);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onResume(boolean z) {
        MainPageMessageDoorView mainPageMessageDoorView = this.mMessageDoorView;
        if (mainPageMessageDoorView != null) {
            mainPageMessageDoorView.getMessageDoorRedDot(this.thisActivity.getHttpGroupWithNPSGroup());
        }
        this.mIsPause = false;
        checkSearchViewFlipper(true);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle, com.jingdong.app.mall.home.floor.common.d.b
    public void onScreenChanged(int i2) {
        if (this.mBindWidth == i2) {
            return;
        }
        this.mBindWidth = i2;
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.v();
        }
        f.c(this.searchBarContent, this.mSearchContentSize);
        f.d(this.searchViewFlipper, this.mFlipperSize, true);
        f.c(this.searchBtn, this.mSearchBtnSize);
        f.c(this.iconSearch, this.mSearchIconSize);
        MainPageMessageDoorView mainPageMessageDoorView = this.mMessageDoorView;
        if (mainPageMessageDoorView != null) {
            f.c(mainPageMessageDoorView, this.mMsgSize);
            this.mMessageDoorView.setMessageTextSize(-1);
            this.mMessageDoorView.setMessageImgSize(d.d(48));
        }
        refreshScrollHeight();
    }

    @Override // com.jingdong.app.mall.home.e.b
    public void onTextScaleModeChanged(int i2) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onTitleChanged() {
        onBackPressed();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onUiChanged(final boolean z) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleB.6
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    HomeTitleB.this.onUiChanged(z);
                }
            });
            return;
        }
        JDHomeFragment z0 = JDHomeFragment.z0();
        if (z0 != null) {
            z0.x1(z ? -16777216 : -1);
        }
        if (this.isLightBg == z) {
            return;
        }
        this.isLightBg = z;
        forceRefreshBarStatus();
        MainPageMessageDoorView mainPageMessageDoorView = this.mMessageDoorView;
        if (mainPageMessageDoorView != null) {
            mainPageMessageDoorView.setMessageDoorViewColorReverse(!z);
            this.mMessageDoorView.scrollChangeGrayIcon(z);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void refreshPlanB(com.jingdong.app.mall.home.r.e.d dVar) {
        super.refreshPlanB(dVar);
        if (!com.jingdong.app.mall.home.o.a.f.k0() || dVar == null) {
            return;
        }
        com.jingdong.app.mall.home.r.e.f c2 = dVar.c(0);
        if (c2 == null) {
            return;
        }
        refreshScrollHeight();
        setSearchLayoutBackground();
        try {
            String jsonString = c2.getJsonString("resourceImg");
            this.iconImg = jsonString;
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.iconSearch, jsonString);
            a.y("Home_ScanExpo", "", c2.l());
            this.iconJump = (JumpEntity) JDJSON.parseObject(c2.getJsonString("jump"), JumpEntity.class);
        } catch (Exception e2) {
            this.iconJump = null;
            e2.printStackTrace();
        }
        this.searchBtn.setTextColor(com.jingdong.app.mall.home.floor.view.b.h.a.d(c2.getJsonString("searchBtnWordColor"), -1));
        g.k(this.searchBtn, TextUtils.equals(c2.getJsonString("boldFontSwitch2"), "1"));
        g.o(this.searchBtn, Math.max(c2.getJsonInt("searchBtnWordSize"), 28));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(com.jingdong.app.mall.home.floor.view.b.h.a.d(c2.getJsonString("searchBtnColor"), -381927));
        gradientDrawable.setCornerRadius(this.mSearchBtnSize.h());
        this.searchBtn.setBackgroundDrawable(gradientDrawable);
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.F(c2.getJsonInt("darkTextCarouselFreq", 1));
            this.searchViewFlipper.D(c2.getJsonInt("searchBoxFontSize", 0));
            this.searchViewFlipper.G(e.b().c(this.searchViewFlipper.l()));
            this.searchViewFlipper.E(com.jingdong.app.mall.home.floor.view.b.h.a.d(c2.getJsonString("searchBoxFontColor"), -8355712), c2.getJsonInt("boldFontSwitch2") == 1);
            this.searchViewFlipper.requestLayout();
        }
        this.clickSrvJson = c2.getJsonString("srvJson");
        String l2 = c2.l();
        if (dVar.isCacheData) {
            return;
        }
        a.y("Home_SearchBtnExpo", "", l2);
    }

    public void refreshScrollHeight() {
        setScrollHeight(this.mRealScrollHeight, true);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void removeOverseasSwitchIcon() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void setBitmap(Bitmap bitmap, Matrix matrix) {
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mBgBitmap = bitmap;
            this.mBgMatrix = matrix;
        }
        postInvalidate();
    }

    public void setScrollHeight(float f2, boolean z) {
        this.mRealScrollHeight = f2;
        if (!com.jingdong.app.mall.home.o.a.f.k0()) {
            f2 *= 3.0f;
        }
        int d = d.d(200);
        if (f2 < 0.0f || f2 > d) {
            f2 = d;
        }
        float f3 = this.mCurrentScrollHeight;
        boolean z2 = f3 == 0.0f || f2 == 0.0f;
        if (f3 != f2 || z) {
            this.mCurrentScrollHeight = f2;
            if (f2 > 0.0f) {
                toAlpha(1.0f, 0L);
            }
            float f4 = this.mCurrentScrollHeight / d;
            HomeTopBgView homeTopBgView = this.mSkinBgView;
            if (homeTopBgView != null) {
                homeTopBgView.setAlpha(f4);
            }
        }
        if (z2) {
            if (com.jingdong.app.mall.home.o.a.f.p0()) {
                postInvalidate();
            } else {
                invalidate();
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void setSearchBarDataEntity(j.b bVar) {
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.B(bVar);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void setSkinBitmap(Bitmap bitmap, Matrix matrix) {
        HomeTopBgView homeTopBgView = this.mSkinBgView;
        if (homeTopBgView != null) {
            homeTopBgView.g(bitmap, matrix, -1);
        }
        this.mSkinBitmap = bitmap;
        this.mSkinMatrix = matrix;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(0);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void showOverseasBubbleTips() {
    }
}
