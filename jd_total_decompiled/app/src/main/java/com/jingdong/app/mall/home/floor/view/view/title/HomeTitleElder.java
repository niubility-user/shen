package com.jingdong.app.mall.home.floor.view.view.title;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.e;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.view.view.CategoryTabTitle;
import com.jingdong.app.mall.home.floor.view.view.SearchWordEntity;
import com.jingdong.app.mall.home.floor.view.widget.HomeTitleViewFlipper;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.j;
import com.jingdong.app.mall.home.pullrefresh.BaseVerticalRefresh;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.c.b;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.widget.HomeTopBgView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkProductListHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.messagecenter.view.ElderModeMessageDoorView;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.ui.CameraUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeManager;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes4.dex */
public class HomeTitleElder extends IHomeTitle implements e.b {
    public static final int DOUBLE_LINE_HEIGHT = 228;
    public static final int MIN_HEIGHT = 88;
    private static final int SEARCH_BAR_MARGIN_TOP_INTERVAL = 107;
    private static int SEARCH_BAR_MAX_MARGIN_TOP = 108;
    private static final int SEARCH_BAR_MAX_WIDTH = 694;
    private static final int SEARCH_MAX_OFFSET = 86;
    public static final int SINGLE_LINE_HEIGHT = 121;
    protected static final int TITLE_MAX_OFFSET = 200;
    private static long sLastClickTime = Long.MAX_VALUE;
    private final int DEFAULT_LOGO_RES;
    private final String TAG;
    AccelerateInterpolator accelerate;
    ValueAnimator alphaAnimator;
    DecelerateInterpolator decelerate;
    private JDHomeFragment fragment;
    private SimpleDraweeView iconSearch;
    private SimpleDraweeView iconStandard;
    private boolean isLightBg;
    private boolean isRefreshing;
    private final JDDisplayImageOptions leftOptions;
    private final int lightBgColor;
    private FrameLayout logoContainer;
    private View mBaseLine;
    private Bitmap mBgBitmap;
    private Matrix mBgMatrix;
    private int mBindWidth;
    private boolean mIsPause;
    private SimpleDraweeView mIvLogo;
    private JumpEntity mJumpPhotoBuyEntity;
    private final f mLogoContentSize;
    private final f mLogoSize;
    private final f mMaxSize;
    private final f mMinSize;
    private h mScanModel;
    private int mScrollHeight;
    private GradientDrawable mSearchBarBg;
    private HomeTopBgView mSkinBgView;
    private final int modeRes;
    private ElderModeMessageDoorView msgIcon;
    private final f msgIconSize;
    private final int msgRes;
    private TextView msgText;
    private SimpleDraweeView photoBuyIcon;
    private JDDisplayImageOptions photoBuyOptions;
    private final int photoBuyRes;
    private final f photoBuySize;
    private SimpleDraweeView scanIcon;
    private final int scanRes;
    private final f scanSize;
    private RelativeLayout searchBarContent;
    private final f searchContentSize;
    private final f searchIconSize;
    private final int searchRes;
    private final f searchSize;
    private HomeTitleViewFlipper searchViewFlipper;
    private View splitLine;
    private final f splitLineSize;
    private RelativeLayout standardContainer;
    private final f standardContainerSize;
    private final f standardIconSize;
    private TextView standardText;
    private final f standardTextSize;
    private int statusBarHeight;
    private long swiftTime;
    private BaseActivity thisActivity;
    private final int unLightBgColor;

    public HomeTitleElder(Context context) {
        super(context);
        this.TAG = HomeTitleElder.class.getSimpleName();
        this.lightBgColor = -16777216;
        this.unLightBgColor = -1;
        this.DEFAULT_LOGO_RES = R.drawable.home_title_deflogo;
        this.scanRes = R.drawable.home_title_elder_scan_icon;
        this.msgRes = R.drawable.home_title_elder_msg_icon;
        this.searchRes = R.drawable.home_title_elder_search_icon;
        this.photoBuyRes = R.drawable.home_title_elder_photobuy_icon;
        this.modeRes = R.drawable.home_title_mode_change;
        this.mSearchBarBg = new GradientDrawable();
        this.swiftTime = 0L;
        f fVar = new f(-1, 228);
        this.mMaxSize = fVar;
        f fVar2 = new f(-1, 121);
        this.mMinSize = fVar2;
        this.mLogoContentSize = new f(R2.attr.blendSrc, 88);
        this.mLogoSize = new f(116, 88);
        this.standardContainerSize = new f(-2, 46);
        this.standardIconSize = new f(38, 38);
        this.standardTextSize = new f(-2, -2);
        this.msgIconSize = new f(R2.anim.mtrl_bottom_sheet_slide_in, 86);
        f fVar3 = new f(694, 88);
        this.searchContentSize = fVar3;
        this.searchIconSize = new f(36, 36);
        this.searchSize = new f(-1, 80);
        this.photoBuySize = new f(44, 44);
        this.splitLineSize = new f(2, 48);
        this.scanSize = new f(44, 44);
        this.mBindWidth = d.f9279g;
        this.leftOptions = com.jingdong.app.mall.home.floor.ctrl.f.a();
        this.photoBuyOptions = com.jingdong.app.mall.home.floor.ctrl.f.a();
        this.mBgMatrix = new Matrix();
        this.alphaAnimator = new ValueAnimator();
        this.accelerate = new AccelerateInterpolator();
        this.decelerate = new DecelerateInterpolator();
        com.jingdong.app.mall.home.o.a.f.n(context);
        this.thisActivity = (BaseActivity) context;
        this.statusBarHeight = com.jingdong.app.mall.home.floor.ctrl.h.A;
        if (!com.jingdong.app.mall.home.floor.ctrl.h.N().c0()) {
            fVar.I(0);
            fVar2.I(0);
            SEARCH_BAR_MAX_MARGIN_TOP = 112;
            fVar3.C(80);
        } else {
            SEARCH_BAR_MAX_MARGIN_TOP = 108;
            fVar3.C(88);
            fVar.I(this.statusBarHeight);
            fVar2.I(this.statusBarHeight);
        }
        HomeTopBgView homeTopBgView = new HomeTopBgView(context);
        this.mSkinBgView = homeTopBgView;
        homeTopBgView.setAlpha(0.0f);
        addView(this.mSkinBgView, new RelativeLayout.LayoutParams(-1, -1));
        View view = new View(context);
        this.mBaseLine = view;
        view.setId(R.id.home_title_baseline);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        int o = fVar2.o();
        layoutParams.topMargin = o <= 0 ? d.d(10) : o;
        addView(this.mBaseLine, layoutParams);
        addTitleViews();
        initAnimator();
        setLayoutParams(new ViewGroup.LayoutParams(fVar.v(), fVar.h()));
    }

    private void addDefaultLogo() {
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.mIvLogo = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.mIvLogo.setId(R.id.home_title_icon_logo);
        this.mIvLogo.setImageResource(this.DEFAULT_LOGO_RES);
        this.logoContainer.addView(this.mIvLogo, new FrameLayout.LayoutParams(this.mLogoSize.v(), this.mLogoSize.h()));
    }

    private void addLogoContainer() {
        this.logoContainer = new FrameLayout(getContext());
        this.mLogoContentSize.F(new Rect(0, 8, 0, 0));
        RelativeLayout.LayoutParams u = this.mLogoContentSize.u(this.logoContainer);
        u.addRule(3, this.mBaseLine.getId());
        addView(this.logoContainer, u);
    }

    private void addMsgIcon() {
        ElderModeMessageDoorView elderModeMessageDoorView = new ElderModeMessageDoorView(getContext());
        this.msgIcon = elderModeMessageDoorView;
        elderModeMessageDoorView.setElderModeStyle(this.msgRes, -1);
        this.msgIcon.setMessageImgSize(d.d(37));
        this.msgIcon.setMessageTextSize(d.d(36));
        this.msgIcon.setUnReadNumTextSize(d.d(28));
        this.msgIcon.setMessageDoorViewColorReverse(1);
        TextView elderTextView = this.msgIcon.getElderTextView();
        this.msgText = elderTextView;
        if (elderTextView != null) {
            elderTextView.getPaint().setFakeBoldText(true);
        }
        this.msgIcon.setMessageClickListener(new ElderModeMessageDoorView.MessageClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.2
            @Override // com.jingdong.common.messagecenter.view.ElderModeMessageDoorView.MessageClickListener
            public void OnMessageClick() {
                b bVar = new b();
                bVar.a("status", HomeTitleElder.this.getSearchProgress());
                a.u("Home_MessageCenter", "", bVar.toString(), "", null, l.f());
            }
        });
        this.msgIconSize.F(new Rect(0, 3, 0, 0));
        RelativeLayout.LayoutParams u = this.msgIconSize.u(this.msgIcon);
        u.addRule(11);
        u.addRule(3, this.mBaseLine.getId());
        addView(this.msgIcon, u);
    }

    private void addSearchBar() {
        this.searchBarContent = new RelativeLayout(getContext());
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.iconSearch = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.iconSearch.setImageResource(this.searchRes);
        this.searchIconSize.F(new Rect(32, 0, 0, 0));
        RelativeLayout.LayoutParams u = this.searchIconSize.u(this.iconSearch);
        u.addRule(15);
        this.searchBarContent.addView(this.iconSearch, u);
        HomeTitleViewFlipper homeTitleViewFlipper = new HomeTitleViewFlipper(getContext(), new HomeTitleViewFlipper.c() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.3
            @Override // com.jingdong.app.mall.home.floor.view.widget.HomeTitleViewFlipper.c
            public void onClick(SearchWordEntity searchWordEntity) {
                HomeTitleElder.this.gotoSearch(searchWordEntity);
            }
        });
        this.searchViewFlipper = homeTitleViewFlipper;
        homeTitleViewFlipper.setBackgroundColor(0);
        this.searchViewFlipper.E(-13421773, true);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.searchSize.v(), this.searchSize.h());
        layoutParams.addRule(15);
        this.searchSize.L(new Rect(80, 8, R2.anim.mtrl_bottom_sheet_slide_in, 8), this.searchViewFlipper);
        this.searchBarContent.addView(this.searchViewFlipper, layoutParams);
        HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
        this.photoBuyIcon = homeDraweeView2;
        homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
        this.photoBuyIcon.setImageResource(this.photoBuyRes);
        this.photoBuySize.F(new Rect(0, 0, 102, 0));
        RelativeLayout.LayoutParams u2 = this.photoBuySize.u(this.photoBuyIcon);
        u2.addRule(11);
        u2.addRule(15);
        this.searchBarContent.addView(this.photoBuyIcon, u2);
        this.photoBuyIcon.setVisibility(8);
        this.splitLine = new View(getContext());
        this.splitLine.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1, -1052689, -1052689, -1}));
        this.splitLineSize.F(new Rect(0, 0, 84, 0));
        RelativeLayout.LayoutParams u3 = this.splitLineSize.u(this.splitLine);
        u3.addRule(11);
        u3.addRule(15);
        this.searchBarContent.addView(this.splitLine, u3);
        this.splitLine.setVisibility(8);
        HomeDraweeView homeDraweeView3 = new HomeDraweeView(getContext());
        this.scanIcon = homeDraweeView3;
        homeDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
        this.scanIcon.setImageResource(this.scanRes);
        this.scanIcon.setContentDescription(getResources().getString(R.string.home_title_scan_word));
        this.scanIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeTitleElder.this.gotoScan();
            }
        });
        this.scanSize.F(new Rect(0, 0, 24, 0));
        RelativeLayout.LayoutParams u4 = this.scanSize.u(this.scanIcon);
        u4.addRule(11);
        u4.addRule(15);
        this.searchBarContent.addView(this.scanIcon, u4);
        this.searchContentSize.F(new Rect(28, SEARCH_BAR_MAX_MARGIN_TOP, 0, 0));
        RelativeLayout.LayoutParams u5 = this.searchContentSize.u(this.searchBarContent);
        u5.addRule(3, this.mBaseLine.getId());
        u5.addRule(9);
        addView(this.searchBarContent, u5);
        setScrollHeight(this.mScrollHeight, true);
    }

    private void addStandardModeIcon() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        this.standardContainer = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HomeTitleElder.this.isDoubleClick()) {
                    return;
                }
                HomeTitleElder.this.popModeSwitchDialog();
            }
        });
        this.standardContainerSize.F(new Rect(0, 23, R2.anim.slide_in_from_top, 0));
        RelativeLayout.LayoutParams u = this.standardContainerSize.u(this.standardContainer);
        u.addRule(11);
        u.addRule(3, this.mBaseLine.getId());
        addView(this.standardContainer, u);
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.iconStandard = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.iconStandard.setImageResource(this.modeRes);
        SimpleDraweeView simpleDraweeView = this.iconStandard;
        Resources resources = getResources();
        int i2 = R.string.home_title_standard_mode;
        simpleDraweeView.setContentDescription(resources.getString(i2));
        RelativeLayout.LayoutParams u2 = this.standardIconSize.u(this.iconStandard);
        u2.addRule(15);
        this.standardContainer.addView(this.iconStandard, u2);
        TextView textView = new TextView(getContext());
        this.standardText = textView;
        textView.setTextColor(-1);
        f.O(this.standardText, 36);
        this.standardText.getPaint().setFakeBoldText(true);
        this.standardText.setMaxLines(1);
        this.standardText.setText(i2);
        this.standardText.setEllipsize(TextUtils.TruncateAt.END);
        this.standardText.setPadding(0, -3, 0, -3);
        this.standardText.setIncludeFontPadding(false);
        this.standardText.setGravity(16);
        this.standardTextSize.F(new Rect(48, 0, 0, 0));
        RelativeLayout.LayoutParams u3 = this.standardTextSize.u(this.standardText);
        u3.addRule(15);
        this.standardContainer.addView(this.standardText, u3);
    }

    private void addTitleViews() {
        addLogoContainer();
        addDefaultLogo();
        addStandardModeIcon();
        addMsgIcon();
        addSearchBar();
        lightInit();
    }

    private boolean checkCameraHardware(Context context) {
        return !SDKUtils.isSDKVersionMoreThan21() || CameraUtils.checkCameraHardware(context);
    }

    private void displayIcon(SimpleDraweeView simpleDraweeView, String str, int i2) {
        if (simpleDraweeView == null) {
            return;
        }
        com.jingdong.app.mall.home.floor.ctrl.e.f(str, simpleDraweeView, com.jingdong.app.mall.home.floor.ctrl.f.a().showImageForEmptyUri(i2).showImageOnFail(i2).showImageOnLoading(i2));
    }

    private void displayScan(boolean z, String str) {
        displayIcon(this.scanIcon, str, this.scanRes);
    }

    private JumpEntity getPhotoBuyJumgEntity() {
        if (this.mJumpPhotoBuyEntity == null) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("merge", (Object) DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            jDJSONObject.put("independent", (Object) "photobuy");
            JumpEntity jumpEntity = new JumpEntity();
            this.mJumpPhotoBuyEntity = jumpEntity;
            jumpEntity.des = JumpUtil.VALUE_DES_SCAN1;
            jumpEntity.params = jDJSONObject.toJSONString();
        }
        return this.mJumpPhotoBuyEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public String getSearchProgress() {
        int i2 = this.mScrollHeight;
        return i2 <= 0 ? "0" : i2 >= d.d(200) ? "1" : String.format("%.1f", Float.valueOf((this.mScrollHeight * 1.0f) / d.d(200)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoScan() {
        JumpEntity jumpEntity;
        h hVar = this.mScanModel;
        if (hVar != null && (jumpEntity = hVar.D) != null && !TextUtils.isEmpty(jumpEntity.des)) {
            l.e(getContext(), this.mScanModel.D);
        } else {
            BaseActivity baseActivity = this.thisActivity;
            if (baseActivity != null && checkCameraHardware(baseActivity.getApplicationContext())) {
                DeepLinkScanHelper.startCaptureActivity(this.thisActivity, null);
            }
        }
        b bVar = new b();
        bVar.a("status", getSearchProgress());
        a.u("Home_Scan", "", bVar.toString(), "", null, l.f());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoSearch(SearchWordEntity searchWordEntity) {
        DeepLinkProductListHelper.homeToSearchActivity(this.thisActivity);
        JDJSONObject jDJSONObject = new JDJSONObject();
        String str = "";
        if (searchWordEntity != null) {
            str = searchWordEntity.sourceValue;
            jDJSONObject.put("word", (Object) searchWordEntity.showWord);
        } else {
            jDJSONObject.put("word", (Object) "");
        }
        JDMtaUtils.sendClickDataWithExt(this.thisActivity, "Home_Search", str + CartConstant.KEY_YB_INFO_LINK + getSearchProgress(), "", RecommendMtaUtils.Home_PageId, a.f10642k, "", "", jDJSONObject.toString(), null);
    }

    private void initAnimator() {
        this.alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                HomeTitleElder.this.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = currentTimeMillis - sLastClickTime;
        if (j2 < 0 || j2 > 1000) {
            sLastClickTime = currentTimeMillis;
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPhotoBuyIconTouchDown() {
        if (System.currentTimeMillis() - this.swiftTime > 1000) {
            l.e(getContext(), getPhotoBuyJumgEntity());
            JDMtaUtils.onClickWithPageId(this.thisActivity, "Home_PhotoSearch", this.fragment.getClass().getSimpleName(), "", RecommendMtaUtils.Home_PageId);
        }
        this.swiftTime = System.currentTimeMillis();
    }

    private void onPullFinish() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void popModeSwitchDialog() {
        if (this.thisActivity == null) {
            return;
        }
        final JDDialog jDDialog = new JDDialog(this.thisActivity);
        jDDialog.setContentView(R.layout.home_title_elder_switch_dialog);
        jDDialog.setCanceledOnTouchOutside(false);
        LinearLayout linearLayout = (LinearLayout) jDDialog.findViewById(R.id.rootView);
        f.c(linearLayout, new f(R2.attr.chipSpacingVertical, -2));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(d.d(20));
        gradientDrawable.setColor(-1);
        linearLayout.setBackground(gradientDrawable);
        TextView textView = (TextView) linearLayout.findViewById(R.id.jd_dialog_title);
        f fVar = new f(R2.attr.btnIconAlpha, -2);
        fVar.F(new Rect(0, 48, 0, 0));
        f.c(textView, fVar);
        f.O(textView, 36);
        textView.setText("\u5f53\u524d\u4e3a\u957f\u8f88\u7248\uff0c\u64cd\u4f5c\u66f4\u7b80\u5355\n\u786e\u5b9a\u8981\u5207\u6362\u5230\u6807\u51c6\u7248\u5417\uff1f");
        TextView textView2 = (TextView) linearLayout.findViewById(R.id.jd_dialog_message);
        f fVar2 = new f(R2.attr.blurDownScale, -2);
        fVar2.F(new Rect(0, 28, 0, 0));
        f.c(textView2, fVar2);
        textView2.setLineSpacing(10.0f, 1.0f);
        f.O(textView2, 32);
        textView2.setText("\u4f60\u53ef\u4ee5\u5728\u3010\u6211\u7684-\u8d26\u6237\u8bbe\u7f6e\u3011\u4e2d\u5207\u6362\u56de\u957f\u8f88\u7248\u54e6");
        f fVar3 = new f(-2, -2);
        fVar3.F(new Rect(0, 42, 0, 50));
        f.c((LinearLayout) linearLayout.findViewById(R.id.ll_btns), fVar3);
        Button button = (Button) linearLayout.findViewById(R.id.left_button);
        f.c(button, new f(192, 64));
        f.O(button, 28);
        button.setText("\u6682\u4e0d\u5207\u6362");
        button.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeTitleElder.this.safeDismissDialog(jDDialog);
            }
        });
        f.c(linearLayout.findViewById(R.id.space), new f(32, 64));
        Button button2 = (Button) linearLayout.findViewById(R.id.right_button);
        f.c(button2, new f(192, 64));
        f.O(button2, 28);
        button2.setText("\u5207\u6362\u6807\u51c6\u7248");
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeTitleElder.this.safeDismissDialog(jDDialog);
                JDElderModeManager.INSTANCE.switchElderMode(0);
                ToastUtils.showToastInCenter(HomeTitleElder.this.thisActivity, "\u5df2\u5207\u6362\u5230\u6807\u51c6\u7248", 0);
            }
        });
        safeShowDialog(jDDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeDismissDialog(JDDialog jDDialog) {
        if (jDDialog != null) {
            try {
                jDDialog.dismiss();
            } catch (Throwable unused) {
            }
        }
    }

    private void safeShowDialog(JDDialog jDDialog) {
        if (jDDialog != null) {
            try {
                jDDialog.show();
            } catch (Throwable unused) {
            }
        }
    }

    private void setScrollProgress(float f2, float f3) {
        HomeTopBgView homeTopBgView = this.mSkinBgView;
        if (homeTopBgView != null) {
            homeTopBgView.setAlpha(f2);
        }
        this.logoContainer.setAlpha(Math.max(1.0f - f3, 0.0f));
        ViewGroup.LayoutParams layoutParams = this.searchBarContent.getLayoutParams();
        com.jingdong.app.mall.home.o.a.f.n(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.addRule(3, this.mBaseLine.getId());
        layoutParams2.width = (int) (d.d(694) - (f3 * d.d(331)));
        layoutParams2.leftMargin = d.d(28);
        layoutParams2.topMargin = (int) (d.d(SEARCH_BAR_MAX_MARGIN_TOP) - (d.d(107) * f2));
        this.searchBarContent.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = getLayoutParams();
        if (layoutParams3 != null) {
            layoutParams3.height = (int) (this.mMaxSize.h() - (f2 * (this.mMaxSize.h() - this.mMinSize.h())));
            setLayoutParams(layoutParams3);
        }
    }

    private void setSearchLayoutBackground(int i2, int i3, boolean z) {
        if (this.searchBarContent == null) {
            return;
        }
        this.mSearchBarBg.setAlpha(i3);
        this.mSearchBarBg.setColor(i2);
        this.mSearchBarBg.setCornerRadius(d.d(54));
        this.mSearchBarBg.setStroke(z ? d.d(3) : 0, 587202560);
        this.searchBarContent.setBackgroundDrawable(this.mSearchBarBg);
    }

    private void showPhotoBuyIcon(String str) {
        this.photoBuyIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeTitleElder.this.onPhotoBuyIconTouchDown();
            }
        });
        this.photoBuyIcon.setContentDescription("\u62cd\u7167\u8d2d");
        this.photoBuyOptions.bitmapConfig(Bitmap.Config.ARGB_8888).resetViewBeforeLoading(false).showImageOnFail(this.photoBuyRes).showImageOnLoading(this.photoBuyRes).showImageForEmptyUri(this.photoBuyRes);
        com.jingdong.app.mall.home.floor.ctrl.e.f(str, this.photoBuyIcon, this.photoBuyOptions);
    }

    private void showSearchIcon(String str) {
        this.leftOptions.bitmapConfig(Bitmap.Config.ARGB_8888).resetViewBeforeLoading(false).showImageOnFail(this.searchRes).showImageOnLoading(this.searchRes).showImageForEmptyUri(this.searchRes);
        com.jingdong.app.mall.home.floor.ctrl.e.f(str, this.iconSearch, this.leftOptions);
    }

    private void toAlpha(float f2, long j2) {
        float alpha = getAlpha();
        this.alphaAnimator.cancel();
        if (Math.abs(f2 - alpha) == 0.0f) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 22) {
            this.alphaAnimator.setInterpolator(alpha > f2 ? this.accelerate : this.decelerate);
        }
        this.alphaAnimator.setDuration(((float) j2) * r1);
        this.alphaAnimator.setFloatValues(alpha, f2);
        this.alphaAnimator.start();
        if (f2 <= 0.0f) {
            this.isRefreshing = true;
        } else if (f2 == 1.0f) {
            this.isRefreshing = false;
            onPullFinish();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean addCategoryView(CategoryTabTitle categoryTabTitle) {
        return true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void addOverseasSwitchIcon(String str, String str2, int i2) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void addTitleResource(h hVar) {
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
        Bitmap bitmap = this.mBgBitmap;
        if (bitmap != null && this.mBgMatrix != null && this.mScrollHeight != 0) {
            if (bitmap.isRecycled()) {
                canvas.drawColor(-1957094);
            } else {
                canvas.drawBitmap(this.mBgBitmap, this.mBgMatrix, null);
            }
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

    @Override // com.jingdong.app.mall.home.e.b
    public int getTextSize() {
        return 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public Pair<Bitmap, Matrix> getTopBitmap() {
        return null;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public int getTopLogoScaleAnimPivotX() {
        return 0;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void hideTopTab() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean isAnimating() {
        return this.alphaAnimator.isRunning();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean isScrollFixed() {
        return false;
    }

    public void lightInit() {
        this.searchViewFlipper.E(-13421773, true);
        setSearchLayoutBackground(-1, 255, false);
        this.searchBarContent.setVisibility(0);
        forceRefreshBarStatus();
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public boolean onBackPressed() {
        return false;
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
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.t();
        }
        this.mIsPause = true;
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onPullOffset(BaseVerticalRefresh.l lVar, int i2, long j2) {
        if (BaseVerticalRefresh.l.MANUAL_REFRESHING != lVar && BaseVerticalRefresh.l.REFRESHING != lVar) {
            int d = d.d(130);
            if (BaseVerticalRefresh.l.RESET == lVar && i2 < d) {
                toAlpha(1.0f, 240L);
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
        ElderModeMessageDoorView elderModeMessageDoorView = this.msgIcon;
        if (elderModeMessageDoorView != null) {
            elderModeMessageDoorView.getMessageDoorRedDot(this.thisActivity.getHttpGroupWithNPSGroup());
        }
        HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
        if (homeTitleViewFlipper != null) {
            homeTitleViewFlipper.u(true);
        }
        this.mIsPause = false;
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
        f.c(this.logoContainer, this.mLogoContentSize);
        f.c(this.mIvLogo, this.mLogoSize);
        f.c(this.standardContainer, this.standardContainerSize);
        f.c(this.iconStandard, this.standardIconSize);
        f.c(this.standardText, this.standardTextSize);
        f.O(this.standardText, 36);
        f.c(this.msgIcon, this.msgIconSize);
        ElderModeMessageDoorView elderModeMessageDoorView = this.msgIcon;
        if (elderModeMessageDoorView != null) {
            elderModeMessageDoorView.setMessageImgSize(d.d(37));
            this.msgIcon.setMessageTextSize(d.d(36));
            this.msgIcon.setUnReadNumTextSize(d.d(28));
        }
        f.c(this.searchBarContent, this.searchContentSize);
        f.d(this.searchViewFlipper, this.searchSize, true);
        f.c(this.iconSearch, this.searchIconSize);
        f.c(this.photoBuyIcon, this.photoBuySize);
        f.c(this.splitLine, this.splitLineSize);
        f.c(this.scanIcon, this.scanSize);
        setScrollHeight(this.mScrollHeight, true);
    }

    @Override // com.jingdong.app.mall.home.e.b
    public void onTextScaleModeChanged(int i2) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onTitleChanged() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void onUiChanged(final boolean z) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new com.jingdong.app.mall.home.o.a.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.HomeTitleElder.5
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    HomeTitleElder.this.onUiChanged(z);
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
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void refreshSearchBoxStyle(h hVar) {
        JDJSONArray jSONArray;
        if (hVar == null) {
            return;
        }
        setSearchLayoutBackground(-1, 255, hVar.getJsonInt("borderSwitch") == 1);
        JDJSONArray j2 = hVar.j();
        if (j2 != null && !j2.isEmpty() && (jSONArray = j2.getJSONObject(0).getJSONArray("data")) != null && !jSONArray.isEmpty()) {
            com.jingdong.app.mall.home.r.e.f fVar = new com.jingdong.app.mall.home.r.e.f(jSONArray.getJSONObject(0), 0);
            this.searchViewFlipper.F(fVar.getJsonInt("darkTextCarouselFreq", 1));
            this.searchViewFlipper.D(fVar.getJsonInt("searchBoxFontSize", 0));
            HomeTitleViewFlipper homeTitleViewFlipper = this.searchViewFlipper;
            homeTitleViewFlipper.G(homeTitleViewFlipper.l());
            this.searchViewFlipper.E(com.jingdong.app.mall.home.floor.view.b.h.a.d(fVar.getJsonString("searchBoxFontColor"), -13421773), fVar.getJsonInt("boldFontSwitch") == 1);
            this.searchViewFlipper.requestLayout();
            showSearchIcon(fVar.getJsonString("zoomIcon", "https://emptyUrl"));
            if (fVar.getJsonInt("showCameraIcon") == 1) {
                this.photoBuyIcon.setVisibility(0);
                this.splitLine.setVisibility(0);
                a.y("Home_PhotoSearchExpo", "", fVar.l());
                showPhotoBuyIcon(fVar.getJsonString("cameraIcon", "https://emptyUrl"));
            } else {
                this.photoBuyIcon.setVisibility(8);
                this.splitLine.setVisibility(8);
            }
        }
        ElderModeMessageDoorView elderModeMessageDoorView = this.msgIcon;
        if (elderModeMessageDoorView != null) {
            elderModeMessageDoorView.getMessageDoorRedDot(this.thisActivity.getHttpGroupWithNPSGroup());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void removeOverseasSwitchIcon() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void resetLogo() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void setBitmap(Bitmap bitmap, Matrix matrix) {
        if (bitmap != null && !bitmap.isRecycled()) {
            this.mBgBitmap = bitmap;
            this.mBgMatrix = matrix;
        }
        postInvalidate();
    }

    public void setScrollHeight(int i2, boolean z) {
        String str = "scrollHeight: " + i2;
        int d = d.d(200);
        if (i2 < 0 || i2 > d) {
            i2 = d;
        }
        int i3 = this.mScrollHeight;
        boolean z2 = i3 == 0 || i2 == 0;
        if (i3 != i2 || z) {
            this.mScrollHeight = i2;
            if (i2 > 0) {
                toAlpha(1.0f, 0L);
            }
            int i4 = this.mScrollHeight;
            setScrollProgress((i4 * 1.0f) / d, i4 < d.d(86) ? (this.mScrollHeight * 1.0f) / d.d(86) : 1.0f);
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
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        super.setVisibility(0);
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void showOverseasBubbleTips() {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void showPromotionIcon(h hVar) {
    }

    @Override // com.jingdong.app.mall.home.floor.view.view.title.IHomeTitle
    public void showSearchBarLeftIcon(h hVar) {
        String f2;
        this.mScanModel = hVar;
        SimpleDraweeView simpleDraweeView = this.scanIcon;
        if (simpleDraweeView != null && simpleDraweeView.getVisibility() == 0) {
            if (hVar == null) {
                b b = b.b();
                b.a("floorid", "-100");
                b.a("moduleid", "-100");
                f2 = b.toString();
            } else {
                f2 = hVar.f();
            }
            a.y("Home_ScanExpo", "", f2);
        }
        displayScan(this.isLightBg, hVar != null ? hVar.y : "");
    }
}
