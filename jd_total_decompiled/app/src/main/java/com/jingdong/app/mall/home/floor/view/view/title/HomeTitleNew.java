package com.jingdong.app.mall.home.floor.view.view.title;

import android.animation.ValueAnimator;
import android.app.Activity;
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
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONArray;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.t;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import com.jingdong.app.mall.home.floor.view.view.SearchWordEntity;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.HourlyGoBridge;
import com.jingdong.app.mall.home.floor.view.widget.HomeTitleViewFlipper;
import com.jingdong.app.mall.home.floor.view.widget.PhotoBuyIconView;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.widget.HomeTopBgView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.messagecenter.view.MainPageMessageDoorView;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class HomeTitleNew extends IHomeTitle implements e.b {
    public static final int MIN_HEIGHT = 88;
    private static final int MSG_ICON_SIZE = 48;
    private static final int SEARCH_BAR_MAX_MARGIN_TOP = 88;
    private static final int SEARCH_BAR_MAX_WIDTH = 694;
    protected static final int SEARCH_MAX_OFFSET = 86;
    protected static final int TITLE_MAX_OFFSET = 200;
    private final String TAG;
    AccelerateInterpolator accelerate;
    ValueAnimator alphaAnimator;
    private String clickSrvJson;
    DecelerateInterpolator decelerate;
    private JDHomeFragment fragment;
    private SimpleDraweeView iconSearch;
    private SimpleDraweeView innerScanIcon;
    private boolean isLightBg;
    private boolean isRefreshing;
    protected boolean isScrollFixed;
    private final JDDisplayImageOptions leftOptions;
    private final int lightBgColor;
    private b mAddTobTabRunnable;
    private final View mBaseLine;
    private Bitmap mBgBitmap;
    private Matrix mBgMatrix;
    private int mBindWidth;
    protected float mCurrentScrollHeight;
    protected int mCurrentType;
    private boolean mHasOverseasIcon;
    private SimpleDraweeView mIconOverseas;
    private final f mIconOverseasSize;
    private SimpleDraweeView mIconRes;
    private final f mIconResSize;
    private final f mInnerScanIconSize;
    private final int mInnerScanRes;
    private final int mInnerScanResB;
    private boolean mIsPause;
    private final f mLogoSize;
    protected f mMaxSize;
    private final int mMessageBlackRes;
    private MainPageMessageDoorView mMessageDoorView;
    private final int mMessageRes;
    protected f mMinSize;
    private final f mMsgSize;
    private final int mOverseasBlackRes;
    private SimpleDraweeView mOverseasBubbleTips;
    private final int mOverseasRes;
    private final f mOverseasTipSize;
    private String mOverseasUrl;
    private float mRealScrollHeight;
    private final AtomicBoolean mResLoadSuccess;
    private h mScanModel;
    private String mScanUrl;
    private TextView mSearchBtn;
    private final f mSearchBtnSize;
    private final f mSearchContentSize;
    private final f mSearchIconSize;
    private final int mSearchRes;
    private final f mSearchRightSize;
    private final f mSearchSize;
    private HomeTopBgView mSkinBgView;
    private Bitmap mSkinBitmap;
    private Matrix mSkinMatrix;
    private final f mSplitLineSize;
    private TitleTabLayout mTabLayout;
    private f mTabSize;
    private TitleLogo mTitleLogo;
    private RelativeLayout searchBarContent;
    private PhotoBuyIconView searchBarRightIcon;
    private HomeTitleViewFlipper searchViewFlipper;
    private View splitLine;
    private int statusBarHeight;
    private BaseActivity thisActivity;
    private final int unLightBgColor;
    public static final int DEFAULT_LOGO_RES = R.drawable.home_title_deflogo;
    public static final int sPhotoRes = R.drawable.home_title_new_photobuy_icon;
    private static int sScrollOffset = 77;
    public static int mMaxHeight = R2.anim.pop_left_bottom_in;
    public static int mSearchHeight = 64;
    public static int mSearchBoldWidth = 0;
    public static int mSearchBtnWidth = 104;
    private static boolean isNewSearch = HomeTitleFactory.isNewSearch;

    public HomeTitleNew(Context context) {
        super(context);
        this.TAG = HomeTitleNew.class.getSimpleName();
        this.lightBgColor = -16777216;
        this.unLightBgColor = -1;
        this.mOverseasRes = R.drawable.home_title_new_oversea_white;
        this.mOverseasBlackRes = R.drawable.home_title_new_oversea_black;
        this.mInnerScanRes = R.drawable.home_title_new_scan_icon;
        this.mInnerScanResB = R.drawable.home_title_new_scan_icon_b;
        this.mSearchRes = R.drawable.home_title_new_search;
        this.mMessageRes = R.drawable.home_title_new_message_white;
        this.mMessageBlackRes = R.drawable.home_title_new_message_black;
        this.mResLoadSuccess = new AtomicBoolean(false);
        this.mMaxSize = new f(-1, mMaxHeight);
        this.mMinSize = new f(-1, 88);
        this.mLogoSize = new f(-2, 88);
        this.mSearchContentSize = new f(R2.attr.cameraTargetLat, mSearchHeight);
        this.mSearchIconSize = new f(44, 44);
        this.mSearchSize = new f(-1, 62);
        this.mSearchRightSize = new f(44, 44);
        this.mSplitLineSize = new f(2, 48);
        this.mInnerScanIconSize = new f(44, 44);
        this.mSearchBtnSize = new f(mSearchBtnWidth, 56);
        this.mIconOverseasSize = new f(48, 48);
        this.mIconResSize = new f(48, 48);
        this.mOverseasTipSize = new f(226, 55);
        this.mMsgSize = new f(72, 88);
        this.isScrollFixed = false;
        this.mBindWidth = d.f9279g;
        this.leftOptions = com.jingdong.app.mall.home.floor.ctrl.f.a();
        this.mBgMatrix = new Matrix();
        this.alphaAnimator = new ValueAnimator();
        this.accelerate = new AccelerateInterpolator();
        this.decelerate = new DecelerateInterpolator();
        this.mCurrentType = 0;
        com.jingdong.app.mall.home.o.a.f.n(context);
        this.thisActivity = (BaseActivity) context;
        this.statusBarHeight = com.jingdong.app.mall.home.floor.ctrl.h.A;
        if (!com.jingdong.app.mall.home.floor.ctrl.h.N().c0()) {
            this.mMaxSize.I(0);
            this.mMinSize.I(0);
        } else {
            this.mMaxSize.I(this.statusBarHeight);
            this.mMinSize.I(this.statusBarHeight);
        }
        checkSearchType();
        HomeTopBgView homeTopBgView = new HomeTopBgView(context);
        this.mSkinBgView = homeTopBgView;
        homeTopBgView.setAlpha(0.0f);
        addView(this.mSkinBgView, new RelativeLayout.LayoutParams(-1, -1));
        View view = new View(context);
        this.mBaseLine = view;
        view.setId(R.id.home_title_baseline);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams.topMargin = this.mMinSize.o();
        addView(view, layoutParams);
        addTitleViews();
        initAnimator();
        setLayoutParams(new ViewGroup.LayoutParams(this.mMaxSize.v(), this.mMaxSize.h()));
    }

    private void addLogo() {
        TitleLogo titleLogo = new TitleLogo(getContext(), this);
        this.mTitleLogo = titleLogo;
        RelativeLayout.LayoutParams u = this.mLogoSize.u(titleLogo);
        u.addRule(3, this.mBaseLine.getId());
        addView(this.mTitleLogo, u);
    }

    private void addMsgIcon() {
        MainPageMessageDoorView mainPageMessageDoorView = new MainPageMessageDoorView(getContext());
        this.mMessageDoorView = mainPageMessageDoorView;
        mainPageMessageDoorView.setMsgImgDrawable(1, getContext().getResources().getDrawable(this.mMessageRes), getContext().getResources().getDrawable(this.mMessageBlackRes));
        this.mMessageDoorView.setMessageImgSize(d.d(48));
        this.mMessageDoorView.setMessageClickListener(new MainPageMessageDoorView.MessageClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.3
            @Override // com.jingdong.common.messagecenter.view.MainPageMessageDoorView.MessageClickListener
            public void OnMessageClick() {
                com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
                bVar.a("status", HomeTitleNew.this.getSearchProgress());
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
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.iconSearch.setImageResource(this.mSearchRes);
        this.mSearchIconSize.E(16, 0, 0, 0);
        RelativeLayout.LayoutParams u = this.mSearchIconSize.u(this.iconSearch);
        u.addRule(9);
        u.addRule(15);
        this.searchBarContent.addView(this.iconSearch, u);
        this.iconSearch.setVisibility(isNewSearch ? 8 : 0);
        HomeTitleViewFlipper homeTitleViewFlipper = new HomeTitleViewFlipper(getContext(), new HomeTitleViewFlipper.c() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.6
            @Override // com.jingdong.app.mall.home.floor.view.widget.HomeTitleViewFlipper.c
            public void onClick(SearchWordEntity searchWordEntity) {
                HomeTitleNew.this.gotoSearch(searchWordEntity);
            }
        });
        this.searchViewFlipper = homeTitleViewFlipper;
        homeTitleViewFlipper.setBackgroundColor(0);
        int i2 = com.jingdong.app.mall.home.v.d.a.f() ? 80 : 98;
        f fVar = this.mSearchSize;
        boolean z = isNewSearch;
        if (!z) {
            i2 = 70;
        }
        fVar.J(i2, 8, z ? 128 : R2.anim.mtrl_bottom_sheet_slide_in, 8);
        RelativeLayout.LayoutParams u2 = this.mSearchSize.u(this.searchViewFlipper);
        u2.addRule(15);
        this.searchBarContent.addView(this.searchViewFlipper, u2);
        this.searchBarRightIcon = new PhotoBuyIconView(getContext());
        int i3 = isNewSearch ? 40 : 44;
        this.mSearchRightSize.R(i3, i3);
        this.mSearchRightSize.E(0, 0, isNewSearch ? mSearchBtnWidth + 20 : 98, 0);
        RelativeLayout.LayoutParams u3 = this.mSearchRightSize.u(this.searchBarRightIcon);
        u3.addRule(11);
        u3.addRule(15);
        this.searchBarContent.addView(this.searchBarRightIcon, u3);
        this.searchBarRightIcon.setVisibility(8);
        this.splitLine = new View(getContext());
        this.splitLine.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1, -1052689, -1052689, -1}));
        f fVar2 = this.mSplitLineSize;
        boolean z2 = isNewSearch;
        fVar2.E(z2 ? 80 : 0, 0, z2 ? 0 : 80, 0);
        RelativeLayout.LayoutParams u4 = this.mSplitLineSize.u(this.splitLine);
        u4.addRule(isNewSearch ? 9 : 11);
        u4.addRule(15);
        this.searchBarContent.addView(this.splitLine, u4);
        this.splitLine.setVisibility(isNewSearch ? 0 : 8);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
        this.innerScanIcon = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        this.innerScanIcon.setImageResource(com.jingdong.app.mall.home.v.d.a.f() ? this.mInnerScanResB : this.mInnerScanRes);
        this.innerScanIcon.setContentDescription(getResources().getString(R.string.home_title_scan_word));
        this.innerScanIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeTitleNew homeTitleNew = HomeTitleNew.this;
                PhotoBuyIconView.d(homeTitleNew, homeTitleNew.mScanModel, HomeTitleNew.this.thisActivity);
            }
        });
        int i4 = isNewSearch ? 40 : 44;
        this.mInnerScanIconSize.R(i4, i4);
        f fVar3 = this.mInnerScanIconSize;
        boolean z3 = isNewSearch;
        fVar3.E(z3 ? 24 : 0, 0, z3 ? 0 : 20, 0);
        RelativeLayout.LayoutParams u5 = this.mInnerScanIconSize.u(this.innerScanIcon);
        u5.addRule(isNewSearch ? 9 : 11);
        u5.addRule(15);
        this.searchBarContent.addView(this.innerScanIcon, u5);
        this.innerScanIcon.setVisibility(com.jingdong.app.mall.home.o.a.f.k0() ? 0 : 8);
        g gVar = new g(getContext(), false);
        gVar.d(17);
        gVar.l(-1);
        gVar.m(28);
        gVar.i("\u641c\u7d22");
        this.mSearchBtn = gVar.a();
        this.mSearchBtnSize.E(0, 0, 4, 0);
        RelativeLayout.LayoutParams u6 = this.mSearchBtnSize.u(this.mSearchBtn);
        u6.addRule(11);
        u6.addRule(15);
        this.searchBarContent.addView(this.mSearchBtn, u6);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(-381927);
        gradientDrawable.setCornerRadius(this.mSearchBtnSize.h());
        this.mSearchBtn.setBackgroundDrawable(gradientDrawable);
        this.mSearchBtn.setVisibility(isNewSearch ? 0 : 8);
        this.mSearchBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (l.h()) {
                    return;
                }
                HomeTitleFactory.gotoProductList(HomeTitleNew.this.getContext(), HomeTitleNew.this.searchViewFlipper.m(), HomeTitleNew.this.clickSrvJson);
            }
        });
        this.mSearchContentSize.F(new Rect(28, 88, 0, 0));
        this.mSearchContentSize.Q(694);
        RelativeLayout.LayoutParams u7 = this.mSearchContentSize.u(this.searchBarContent);
        u7.addRule(3, this.mBaseLine.getId());
        u7.addRule(9);
        addView(this.searchBarContent, u7);
        this.searchBarContent.setVisibility(4);
        refreshScrollHeight();
    }

    private void addTitleViews() {
        addLogo();
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

    private void displayIcon(SimpleDraweeView simpleDraweeView, String str, int i2) {
        if (simpleDraweeView == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.e.f(str, simpleDraweeView, com.jingdong.app.mall.home.floor.ctrl.f.a().showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
    }

    private void displayOverseas(boolean z, String str) {
        this.mOverseasUrl = str;
        displayIcon(this.mIconOverseas, str, z ? this.mOverseasBlackRes : this.mOverseasRes);
    }

    private void displayScan(boolean z, String str) {
        this.mScanUrl = str;
        displayIcon(this.innerScanIcon, str, com.jingdong.app.mall.home.v.d.a.f() ? this.mInnerScanResB : this.mInnerScanRes);
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
        Pair<Bitmap, Matrix> S = com.jingdong.app.mall.home.floor.ctrl.h.N().S();
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
    public void gotoSearch(SearchWordEntity searchWordEntity) {
        DeepLinkProductListHelper.homeToSearchActivity(this.thisActivity);
        HomeTitleFactory.sendSearchClickMta(searchWordEntity, getSearchProgress());
    }

    private void initAnimator() {
        this.alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.12
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                HomeTitleNew.this.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
    }

    private boolean isTitleDataMatched() {
        return TextUtils.equals(com.jingdong.app.mall.home.a.q, "10.0.0");
    }

    private void onPullFinish() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeRightResIcon() {
        SimpleDraweeView simpleDraweeView = this.mIconRes;
        if (simpleDraweeView != null) {
            removeView(simpleDraweeView);
            this.mResLoadSuccess.set(false);
            this.mIconRes = null;
            refreshScrollHeight();
        }
    }

    private void setScrollProgress(float f2, float f3) {
        HomeTopBgView homeTopBgView = this.mSkinBgView;
        if (homeTopBgView != null) {
            homeTopBgView.setAlpha(f2);
        }
        TitleTabLayout titleTabLayout = this.mTabLayout;
        if (titleTabLayout != null) {
            titleTabLayout.setAlpha(1.0f - f3);
        }
        this.mTitleLogo.onTitleScroll(f3);
        ViewGroup.LayoutParams layoutParams = this.searchBarContent.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.addRule(3, this.mBaseLine.getId());
        layoutParams2.width = (int) (d.d(694) - (d.d(694 - ((this.mHasOverseasIcon || this.mResLoadSuccess.get()) ? R2.attr.borderRound : R2.attr.cameraTargetLat)) * f3));
        layoutParams2.leftMargin = (int) (d.d(28) + (f3 * d.d(82)));
        layoutParams2.topMargin = (int) (d.d(88) - (d.d(sScrollOffset) * f2));
        this.searchBarContent.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = getLayoutParams();
        if (layoutParams3 != null) {
            layoutParams3.height = (int) (this.mMaxSize.h() - (f2 * (this.mMaxSize.h() - this.mMinSize.h())));
            setLayoutParams(layoutParams3);
        }
    }

    private void setSearchLayoutBackground(h hVar) {
        if (this.searchBarContent == null) {
            return;
        }
        boolean z = hVar != null && hVar.getJsonInt("borderSwitch") == 1;
        int i2 = 587202560;
        int i3 = 2;
        if (hVar != null) {
            i2 = com.jingdong.app.mall.home.floor.view.b.h.a.b(m.j(hVar.getJsonString(isNewSearch ? "frameColor" : "borderColor"), -16777216), Math.min((hVar.getJsonInt("transparency", 14) * 256) / 100, 255));
            if (isNewSearch) {
                i3 = hVar.getJsonInt("frameWidth");
            }
        }
        mSearchBoldWidth = z ? d.d(i3) : 0;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setAlpha(255);
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(d.d(mSearchHeight));
        gradientDrawable.setStroke(mSearchBoldWidth, i2);
        this.searchBarContent.setBackgroundDrawable(gradientDrawable);
    }

    private void showSearchIcon(String str) {
        this.leftOptions.bitmapConfig(Bitmap.Config.ARGB_8888).resetViewBeforeLoading(false).showImageOnFail(this.mSearchRes).showImageOnLoading(this.mSearchRes).showImageForEmptyUri(this.mSearchRes);
        com.jingdong.app.mall.home.floor.ctrl.e.f(str, this.iconSearch, this.leftOptions);
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
                onPullFinish();
                return;
            } else {
                return;
            }
        }
        setAlpha(f2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean addCategoryView(CategoryTabTitle categoryTabTitle) {
        return false;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void addOverseasSwitchIcon(String str, String str2, int i2) {
        final String str3;
        if (TextUtils.isEmpty(str2)) {
            str2 = getResources().getString(R.string.home_title_overseas_word);
        }
        removeRightResIcon();
        try {
            com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
            bVar.a("sitetype", "" + i2);
            str3 = bVar.toString();
        } catch (Exception unused) {
            str3 = "";
        }
        if (!this.mHasOverseasIcon) {
            a.z("Home_AreaIconExpo", "", str3, RecommendMtaUtils.Home_PageId);
        }
        this.mHasOverseasIcon = true;
        this.mIconOverseasSize.F(new Rect(0, 20, 94, 0));
        SimpleDraweeView simpleDraweeView = this.mIconOverseas;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.mIconOverseas = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            this.mIconOverseas.setId(R.id.home_title_icon_overseas);
            RelativeLayout.LayoutParams u = this.mIconOverseasSize.u(this.mIconOverseas);
            u.addRule(3, this.mBaseLine.getId());
            u.addRule(11);
            addView(this.mIconOverseas, u);
        } else {
            f.c(simpleDraweeView, this.mIconOverseasSize);
            this.mIconOverseas.setVisibility(0);
        }
        this.mIconOverseas.setContentDescription(str2);
        boolean z = this.isLightBg;
        if (!isTitleDataMatched()) {
            str = "https://emptyUrl";
        }
        displayOverseas(z, str);
        this.mIconOverseas.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.jingdong.app.mall.home.u.a.w().G(view.getContext());
                a.t("Home_AreaIcon", "", str3, RecommendMtaUtils.Home_PageId);
            }
        });
        refreshScrollHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void addTitleResource(h hVar) {
        this.mTitleLogo.setLogoStyle(hVar);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void addTopTab() {
        TitleTabLayout titleTabLayout = this.mTabLayout;
        if (titleTabLayout != null) {
            titleTabLayout.showTab();
            JDHomeFragment jDHomeFragment = this.fragment;
            if (jDHomeFragment != null && jDHomeFragment.T0()) {
                checkJumpNearby();
            }
            refreshScrollHeight();
            return;
        }
        b bVar = new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.1
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                if (HomeTitleNew.this.mTabLayout == null) {
                    boolean useSpread = TitleTabManager.getInstance().useSpread();
                    HomeTitleNew.this.mTabLayout = new TitleTabLayout(HomeTitleNew.this, useSpread) { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.1.1
                        @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout
                        protected boolean needScrollToTop() {
                            return HomeTitleNew.this.needScrollTop();
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout
                        public void notifyTabSkinBitmap(boolean z) {
                            super.notifyTabSkinBitmap(z);
                            HomeTitleNew.this.notifyTopSkin(z);
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout
                        public void onSelect(int i2) {
                            super.onSelect(i2);
                            HomeTitleNew.this.checkTabSelect(i2 == 0);
                        }
                    };
                    HomeTitleNew.this.mTabSize = new f(-1, 88);
                    RelativeLayout.LayoutParams u = HomeTitleNew.this.mTabSize.u(HomeTitleNew.this.mTabLayout);
                    u.addRule(3, HomeTitleNew.this.mBaseLine.getId());
                    HomeTitleNew.this.mTabLayout.setLayoutParams(u);
                    HomeTitleNew homeTitleNew = HomeTitleNew.this;
                    m.a(homeTitleNew, homeTitleNew.mTabLayout);
                    HomeTitleNew.this.mTabLayout.afterRefresh();
                    HomeTitleNew.this.refreshScrollHeight();
                    if (HomeTitleNew.this.fragment != null && HomeTitleNew.this.fragment.T0()) {
                        HomeTitleNew.this.checkJumpNearby();
                    }
                }
                HomeTitleNew.this.mAddTobTabRunnable = null;
            }
        };
        this.mAddTobTabRunnable = bVar;
        com.jingdong.app.mall.home.o.a.f.E0(bVar);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean afterRefresh() {
        boolean checkSearchType = checkSearchType();
        if (checkSearchType) {
            f.c(this, this.mMinSize);
            f.c(this, this.mMaxSize);
            refreshScrollHeight();
        }
        checkTitleGray();
        if (com.jingdong.app.mall.home.a.f8565n) {
            if (JDHomeFragment.Q0()) {
                onResume(true);
            }
            com.jingdong.app.mall.home.a.f8565n = false;
        }
        TitleTabLayout titleTabLayout = this.mTabLayout;
        if (titleTabLayout != null) {
            titleTabLayout.afterRefresh();
        }
        TitleLogo titleLogo = this.mTitleLogo;
        if (titleLogo != null) {
            titleLogo.afterRefresh();
        }
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.c();
        }
        if (this.splitLine != null && com.jingdong.app.mall.home.v.d.a.f()) {
            this.splitLine.setVisibility(8);
        }
        return checkSearchType;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void beforeRefresh() {
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.d();
        }
        this.mTitleLogo.beforeRefresh();
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

    public void checkIfNeedShowLbsTab() {
        b bVar;
        if (this.mTabLayout == null && (bVar = this.mAddTobTabRunnable) != null) {
            com.jingdong.app.mall.home.o.a.f.E0(bVar);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean checkJumpNearby() {
        HourlyGoBridge.refreshHourGoInfo();
        if (TitleTabManager.getInstance().canShowHourlyGoTab()) {
            Activity u0 = JDHomeFragment.u0();
            HourlyGoObserverManager.getInstance().setIntent(u0 == null ? null : u0.getIntent(), "");
            com.jingdong.app.mall.home.o.a.f.F0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.2
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    HomeTitleNew.this.mTabLayout.autoJumpNearby();
                }
            }, 50L);
            return true;
        }
        return false;
    }

    protected boolean checkSearchType() {
        int searchType = HomeTitleFactory.getSearchType();
        boolean z = searchType != this.mCurrentType;
        if (z) {
            this.mCurrentType = searchType;
            mMaxHeight = R2.anim.pop_left_bottom_in;
            mSearchHeight = 64;
            mSearchBtnWidth = 104;
            sScrollOffset = 77;
            if (searchType == 2) {
                mMaxHeight = R2.anim.pop_left_bottom_in + 16;
                mSearchHeight = 64 + 16;
                mSearchBtnWidth = 128;
                sScrollOffset = 77 + 8;
            } else if (searchType == 1) {
                mMaxHeight = R2.anim.pop_left_bottom_in + 8;
                mSearchHeight = 64 + 8;
                mSearchBtnWidth = 120;
                sScrollOffset = 77 + 4;
            }
            this.mSearchContentSize.C(mSearchHeight);
            this.mMaxSize.C(mMaxHeight);
            f.c(this.searchBarContent, this.mSearchContentSize);
            this.mSearchRightSize.E(0, 0, isNewSearch ? mSearchBtnWidth + 20 : 98, 0);
            f.d(this.searchBarRightIcon, this.mSearchRightSize, true);
        }
        this.mSearchBtnSize.E(0, 0, mSearchBoldWidth + 4, 0);
        this.mSearchBtnSize.R(mSearchBtnWidth, (mSearchHeight - 8) - (mSearchBoldWidth << 1));
        f.c(this.mSearchBtn, this.mSearchBtnSize);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkTabSelect(boolean z) {
        RelativeLayout relativeLayout = this.searchBarContent;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(z ? 0 : 8);
        }
        checkSearchViewFlipper(false);
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
        if (this.isScrollFixed) {
            return getBarHeightSpread();
        }
        return this.mMinSize.h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public int getBarHeightSpread() {
        return this.mMaxSize.h();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public int getCurrentBarHeight() {
        return getHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public View getHomeTitleView() {
        return this;
    }

    public boolean getIsPause() {
        return this.mIsPause || i.i() || this.isRefreshing;
    }

    public int getMaxLogoWidthInPx() {
        if (TitleTabLayout.isShowTopTab(this.mTabLayout)) {
            return d.d(180);
        }
        return d.d(286);
    }

    @NonNull
    public String getSearchProgress() {
        float f2 = this.mCurrentScrollHeight;
        if (f2 <= 0.0f) {
            return "0";
        }
        if (f2 >= d.d(200)) {
            return "1";
        }
        return String.format(Locale.getDefault(), "%.1f", Float.valueOf(this.mCurrentScrollHeight / d.d(200)));
    }

    public TitleTabLayout getTabLayout() {
        return this.mTabLayout;
    }

    @Override // com.jingdong.app.mall.home.e.b
    public int getTextSize() {
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            return homeTitleViewFlipper.l();
        }
        return 28;
    }

    public TitleLogo getTitleLogo() {
        return this.mTitleLogo;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public Pair<Bitmap, Matrix> getTopBitmap() {
        Bitmap bitmap;
        Matrix matrix;
        if (com.jingdong.app.mall.home.floor.ctrl.h.N().D() && (bitmap = this.mSkinBitmap) != null) {
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
    public int getTopLogoScaleAnimPivotX() {
        return d.d(224);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void hideTopTab() {
        TitleTabLayout titleTabLayout = this.mTabLayout;
        if (titleTabLayout != null) {
            titleTabLayout.hideTabLong();
        }
        refreshScrollHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean isAnimating() {
        return this.alphaAnimator.isRunning();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void isCheckHomeTab(boolean z) {
        super.isCheckHomeTab(z);
        com.jingdong.app.mall.home.v.c.a.b(this, z);
        com.jingdong.app.mall.home.floor.ctrl.h.N().b0(z);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean isScrollFixed() {
        return this.isScrollFixed;
    }

    public void lightInit() {
        this.searchViewFlipper.E(-8355712, false);
        setSearchLayoutBackground(null);
        this.searchBarContent.setVisibility(0);
        forceRefreshBarStatus();
        MainPageMessageDoorView mainPageMessageDoorView = this.mMessageDoorView;
        if (mainPageMessageDoorView != null) {
            mainPageMessageDoorView.setMessageDoorViewColorReverse(!this.isLightBg);
            this.mMessageDoorView.scrollChangeGrayIcon(this.isLightBg);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean needScrollTop() {
        if (com.jingdong.app.mall.home.floor.common.i.g.f9304n) {
            return true;
        }
        return !isScrollFixed();
    }

    public void notifyTopSkin(boolean z) {
        this.mSkinBgView.setVisibility(z ? 8 : 0);
        postInvalidate();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean onBackPressed() {
        TitleTabLayout titleTabLayout = this.mTabLayout;
        return titleTabLayout != null && titleTabLayout.onBackPressed();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onDarkThemeChange() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onHomeStop() {
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
            if (min > 0) {
                this.isRefreshing = true;
                return;
            }
            this.isRefreshing = false;
            onPullFinish();
            return;
        }
        toAlpha(0.0f, 0L);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onResume(boolean z) {
        TitleTabLayout titleTabLayout = this.mTabLayout;
        if (titleTabLayout != null) {
            titleTabLayout.onResume();
        }
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
        TitleTabLayout titleTabLayout = this.mTabLayout;
        if (titleTabLayout != null) {
            f.c(titleTabLayout, this.mTabSize);
            this.mTabLayout.checkSize();
        }
        f.c(this.mTitleLogo, this.mLogoSize);
        this.mTitleLogo.onScreenChanged();
        f.c(this.searchBarContent, this.mSearchContentSize);
        f.d(this.searchViewFlipper, this.mSearchSize, true);
        f.c(this.iconSearch, this.mSearchIconSize);
        f.c(this.searchBarRightIcon, this.mSearchRightSize);
        f.c(this.splitLine, this.mSplitLineSize);
        f.c(this.innerScanIcon, this.mInnerScanIconSize);
        f.c(this.mSearchBtn, this.mSearchBtnSize);
        f.c(this.mIconRes, this.mIconResSize);
        if (this.mHasOverseasIcon) {
            f.c(this.mIconOverseas, this.mIconOverseasSize);
        }
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
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.G(i2);
            this.searchViewFlipper.requestLayout();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onTitleChanged() {
        onBackPressed();
        refreshScrollHeight();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onUiChanged(final boolean z) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.9
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    HomeTitleNew.this.onUiChanged(z);
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
        displayScan(z, this.mScanUrl);
        displayOverseas(z, this.mOverseasUrl);
        MainPageMessageDoorView mainPageMessageDoorView = this.mMessageDoorView;
        if (mainPageMessageDoorView != null) {
            mainPageMessageDoorView.setMessageDoorViewColorReverse(!z);
            this.mMessageDoorView.scrollChangeGrayIcon(z);
        }
    }

    public void refreshScrollHeight() {
        setScrollHeight(this.mRealScrollHeight, true);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void refreshSearchBoxStyle(h hVar) {
        JDJSONArray jSONArray;
        String jsonString;
        mSearchBoldWidth = 0;
        if (!com.jingdong.app.mall.home.o.a.f.k0()) {
            this.searchBarRightIcon.setVisibility(8);
            this.splitLine.setVisibility(8);
            this.innerScanIcon.setVisibility(8);
            this.mSearchBtn.setVisibility(8);
        } else if (hVar == null) {
        } else {
            refreshScrollHeight();
            boolean z = HomeTitleFactory.isNewSearch;
            isNewSearch = z;
            this.iconSearch.setVisibility(z ? 8 : 0);
            int i2 = com.jingdong.app.mall.home.v.d.a.f() ? 80 : 98;
            f fVar = this.mSearchSize;
            boolean z2 = isNewSearch;
            fVar.J(z2 ? i2 : 70, 8, z2 ? 128 : R2.anim.mtrl_bottom_sheet_slide_in, 8);
            f.d(this.searchViewFlipper, this.mSearchSize, true);
            int i3 = isNewSearch ? 40 : 44;
            this.mSearchRightSize.R(i3, i3);
            this.mSearchRightSize.E(0, 0, isNewSearch ? mSearchBtnWidth + 20 : 98, 0);
            f.d(this.searchBarRightIcon, this.mSearchRightSize, true);
            this.searchBarRightIcon.setVisibility(8);
            f fVar2 = this.mSplitLineSize;
            boolean z3 = isNewSearch;
            fVar2.E(z3 ? 80 : 0, 0, z3 ? 0 : 80, 0);
            RelativeLayout.LayoutParams u = this.mSplitLineSize.u(this.splitLine);
            u.addRule(9, 0);
            u.addRule(11, 0);
            u.addRule(isNewSearch ? 9 : 11);
            u.addRule(15);
            this.splitLine.setLayoutParams(u);
            this.splitLine.setVisibility((!isNewSearch || com.jingdong.app.mall.home.v.d.a.f()) ? 8 : 0);
            int i4 = isNewSearch ? 40 : 44;
            this.mInnerScanIconSize.R(i4, i4);
            f fVar3 = this.mInnerScanIconSize;
            boolean z4 = isNewSearch;
            fVar3.E(z4 ? 24 : 0, 0, z4 ? 0 : 20, 0);
            RelativeLayout.LayoutParams u2 = this.mInnerScanIconSize.u(this.innerScanIcon);
            u2.addRule(9, 0);
            u2.addRule(11, 0);
            u2.addRule(isNewSearch ? 9 : 11);
            u2.addRule(15);
            this.innerScanIcon.setLayoutParams(u2);
            this.innerScanIcon.setVisibility(0);
            setSearchLayoutBackground(hVar);
            JDJSONArray j2 = hVar.j();
            if (j2 != null && !j2.isEmpty() && (jSONArray = j2.getJSONObject(0).getJSONArray("data")) != null && !jSONArray.isEmpty()) {
                com.jingdong.app.mall.home.r.e.f fVar4 = new com.jingdong.app.mall.home.r.e.f(jSONArray.getJSONObject(0), 0);
                this.searchViewFlipper.F(fVar4.getJsonInt("darkTextCarouselFreq", 1));
                this.searchViewFlipper.D(fVar4.getJsonInt("searchBoxFontSize", 0));
                this.searchViewFlipper.G(e.b().c(this.searchViewFlipper.l()));
                this.searchViewFlipper.E(com.jingdong.app.mall.home.floor.view.b.h.a.d(fVar4.getJsonString("searchBoxFontColor"), -8355712), fVar4.getJsonInt("boldFontSwitch") == 1);
                this.searchViewFlipper.requestLayout();
                if (!isNewSearch) {
                    showSearchIcon(isTitleDataMatched() ? fVar4.getJsonString("zoomIcon", "https://emptyUrl") : "https://emptyUrl");
                }
                if (fVar4.getJsonInt("showCameraIcon") == 1) {
                    if (isNewSearch) {
                        this.mSearchSize.J(i2, 8, 180, 8);
                        f.d(this.searchViewFlipper, this.mSearchSize, true);
                        jsonString = fVar4.getJsonString("cameraIconV12", "https://emptyUrl");
                    } else {
                        jsonString = isTitleDataMatched() ? fVar4.getJsonString("cameraIcon", "https://emptyUrl") : "https://emptyUrl";
                    }
                    this.splitLine.setVisibility(!com.jingdong.app.mall.home.v.d.a.f() ? 0 : 8);
                    this.searchBarRightIcon.setVisibility(0);
                    this.searchBarRightIcon.f(fVar4, jsonString);
                    a.y("Home_PhotoSearchExpo", "", fVar4.l());
                }
                if (isNewSearch) {
                    this.mSearchBtn.setTextColor(com.jingdong.app.mall.home.floor.view.b.h.a.d(fVar4.getJsonString("fontColorV12"), -1));
                    g.o(this.mSearchBtn, fVar4.getJsonInt("fontSizeV12", 28));
                    int[] iArr = new int[2];
                    com.jingdong.app.mall.home.floor.view.b.h.a.h(com.jingdong.app.mall.home.floor.view.b.h.a.e(fVar4.getJsonString("buttonColorV12"), -49073), iArr);
                    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, iArr);
                    gradientDrawable.setCornerRadius(this.mSearchBtnSize.h());
                    this.mSearchBtn.setBackgroundDrawable(gradientDrawable);
                    this.clickSrvJson = fVar4.getJsonString("srvJson");
                    String l2 = fVar4.l();
                    if (!hVar.Z) {
                        a.y("Home_SearchBtnExpo", "", l2);
                    }
                }
            }
            this.mSearchBtn.setVisibility(isNewSearch ? 0 : 8);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void removeOverseasSwitchIcon() {
        if (this.mHasOverseasIcon) {
            this.mHasOverseasIcon = false;
            SimpleDraweeView simpleDraweeView = this.mIconOverseas;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void resetLogo() {
    }

    @Override // android.view.View
    public void setAlpha(float f2) {
        super.setAlpha(f2);
        com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
        if (lastCreateView instanceof MallFloorCategory) {
            ((MallFloorCategory) lastCreateView).setAlpha(f2);
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void setBitmap(Bitmap bitmap, Matrix matrix) {
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mBgBitmap = bitmap;
            this.mBgMatrix = matrix;
        }
        postInvalidate();
    }

    public void setHaveTitleLogoResource() {
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.C();
        }
    }

    public void setScrollFixed(boolean z) {
        JDHomeFragment z0;
        boolean z2 = this.isScrollFixed;
        this.isScrollFixed = z;
        i.o(z);
        if (!(this.isScrollFixed ^ z2) || (z0 = JDHomeFragment.z0()) == null) {
            return;
        }
        z0.j1();
    }

    public void setScrollHeight(float f2, boolean z) {
        TitleTabLayout titleTabLayout;
        this.mRealScrollHeight = f2;
        boolean z2 = true;
        if ((com.jingdong.app.mall.home.v.d.a.f() || TitleTabManager.getInstance().getTitleTabInfo().isScrollFixedOpen()) && (titleTabLayout = this.mTabLayout) != null && titleTabLayout.getVisibility() == 0) {
            setScrollFixed(this.isScrollFixed | (f2 == 0.0f));
        } else {
            setScrollFixed(false);
        }
        if (this.isScrollFixed) {
            f2 = 0.0f;
        }
        if (!com.jingdong.app.mall.home.o.a.f.k0()) {
            f2 *= 3.0f;
        }
        int d = d.d(200);
        if (f2 < 0.0f || f2 > d) {
            f2 = d;
        }
        float f3 = this.mCurrentScrollHeight;
        if (f3 != 0.0f && f2 != 0.0f) {
            z2 = false;
        }
        if (f3 != f2 || z) {
            SimpleDraweeView simpleDraweeView = this.mOverseasBubbleTips;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
            }
            this.mCurrentScrollHeight = f2;
            if (f2 > 0.0f || (this.isScrollFixed && this.mRealScrollHeight > 0.0f)) {
                toAlpha(1.0f, 0L);
            }
            float f4 = this.mCurrentScrollHeight;
            setScrollProgress(f4 / d, f4 < ((float) d.d(86)) ? this.mCurrentScrollHeight / d.d(86) : 1.0f);
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
        if (!this.mHasOverseasIcon || this.mIconOverseas == null) {
            return;
        }
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.mOverseasBubbleTips = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.mOverseasBubbleTips.setImageResource(R.drawable.home_title_overseas_bubble_tips);
        this.mOverseasTipSize.E(-221, 3, 0, 0);
        RelativeLayout.LayoutParams u = this.mOverseasTipSize.u(this.mOverseasBubbleTips);
        u.addRule(5, this.mIconOverseas.getId());
        u.addRule(6, this.mIconOverseas.getId());
        addView(this.mOverseasBubbleTips, -1, u);
        postDelayed(new Runnable() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.5
            @Override // java.lang.Runnable
            public void run() {
                HomeTitleNew homeTitleNew = HomeTitleNew.this;
                homeTitleNew.removeView(homeTitleNew.mOverseasBubbleTips);
                HomeTitleNew.this.mOverseasBubbleTips = null;
            }
        }, Final.FIVE_SECOND);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void showPromotionIcon(final h hVar) {
        if (this.mHasOverseasIcon) {
            return;
        }
        SimpleDraweeView simpleDraweeView = this.mIconOverseas;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(8);
        }
        if (hVar != null && isTitleDataMatched()) {
            this.mIconResSize.F(new Rect(0, 20, 94, 0));
            SimpleDraweeView simpleDraweeView2 = this.mIconRes;
            if (simpleDraweeView2 == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.mIconRes = homeDraweeView;
                homeDraweeView.setContentDescription("\u6d3b\u52a8icon");
                this.mIconRes.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams u = this.mIconResSize.u(this.mIconRes);
                u.addRule(3, this.mBaseLine.getId());
                u.addRule(11);
                addView(this.mIconRes, u);
            } else {
                simpleDraweeView2.setVisibility(0);
                f.c(this.mIconRes, this.mIconResSize);
            }
            this.mResLoadSuccess.set(false);
            this.mIconRes.setAlpha(0.01f);
            this.mIconRes.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (l.k()) {
                        return;
                    }
                    l.e(HomeTitleNew.this.thisActivity, hVar.getJump());
                    com.jingdong.app.mall.home.r.c.b bVar = new com.jingdong.app.mall.home.r.c.b();
                    bVar.a("status", HomeTitleNew.this.getSearchProgress());
                    new com.jingdong.app.mall.home.q.a("\u7ea2\u5305Icon\u70b9\u51fb", hVar.getJsonString("clkLog")).b();
                    a.s("Home_SearchBoxIcon", hVar.z, bVar.toString());
                }
            });
            com.jingdong.app.mall.home.floor.ctrl.e.p(this.mIconRes, hVar.y, com.jingdong.app.mall.home.floor.ctrl.e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew.11
                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onFailed(String str, View view) {
                    HomeTitleNew.this.removeRightResIcon();
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onStart(String str, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onSuccess(String str, View view) {
                    HomeTitleNew.this.mResLoadSuccess.set(true);
                    HomeTitleNew.this.mIconRes.setAlpha(1.0f);
                    HomeTitleNew.this.refreshScrollHeight();
                    new com.jingdong.app.mall.home.q.a("\u7ea2\u5305Icon\u66dd\u5149", true, hVar.getJsonString("expoLog")).b();
                    a.w(HomeTitleNew.this.getContext(), "Home_SearchIconExpo", hVar.h(), "", RecommendMtaUtils.Home_PageId);
                }
            });
            return;
        }
        removeRightResIcon();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void showSearchBarLeftIcon(h hVar) {
        String f2;
        this.mScanModel = hVar;
        SimpleDraweeView simpleDraweeView = this.innerScanIcon;
        if (simpleDraweeView != null && simpleDraweeView.getVisibility() == 0) {
            if (hVar == null) {
                com.jingdong.app.mall.home.r.c.b b = com.jingdong.app.mall.home.r.c.b.b();
                b.a("floorid", "-100");
                b.a("moduleid", "-100");
                f2 = b.toString();
            } else {
                f2 = hVar.f();
            }
            a.y("Home_ScanExpo", "", f2);
        }
        displayScan(this.isLightBg, (!isTitleDataMatched() || hVar == null) ? "https://emptyUrl" : hVar.y);
    }

    public void updateTitleResourceVisibleState(boolean z) {
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.H(z);
        }
    }
}
