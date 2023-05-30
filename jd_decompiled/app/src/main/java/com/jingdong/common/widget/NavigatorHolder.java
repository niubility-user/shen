package com.jingdong.common.widget;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.GraySwitch;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.IThemeChangeListener;
import com.jingdong.common.unification.title.theme.JdThemeTitle;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;
import com.jingdong.common.util.WebNaviSettings;
import com.jingdong.common.util.WebNaviUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.JDMenuWindow;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.remoteimage.CalorieImageUtil;
import com.jingdong.sdk.navigatorholder.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import com.jingdong.wireless.iconfont.IconDrawable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class NavigatorHolder implements View.OnClickListener {
    public static final int HIDE = 0;
    private static final String IS_SHOW = "isShow";
    private static final int MAX_1_DIGIT = 9;
    private static final int MAX_2_DIGIT = 99;
    public static final int NAVI_BAR_HEIGHT = DPIUtil.dip2px(49.0f);
    public static final int NAVI_BAR_HEIGHT_DP = 49;
    private static final String NO = "N";
    public static final String SCHEME_ICONFONT = "iconfont://";
    public static final int SHOW_INSIDE = 1;
    public static final int SHOW_OUTSIDE = 2;
    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_WHITE = 2;
    public static final String TAG = "NavigatorHolder";
    private static final String YES = "Y";
    private ImageView bnMore;
    private FrameLayout cartContainer;
    private ImageView closeIcon;
    private boolean closeNeedShow;
    private Context context;
    private ImageView custom1;
    private RelativeLayout customRootView;
    private ColorDrawable darkColorDrawable;
    private boolean darkMode;
    private int dp160;
    private int dp225;
    private int dp30;
    private int dp35;
    private boolean enableStatusTint;
    private RelativeLayout firstSeat;
    private int height49;
    private ImageView homeIcon;
    private boolean immersive;
    LayoutInflater inflater;
    private boolean isShareGift;
    private boolean isShowMoreBtn;
    private JdThemeTitle jdThemeTitle;
    JDMenuWindow.Builder mBuilder;
    private View mFollowView;
    boolean mIsShowRedPoint;
    private TextView mMoreRedCountView;
    View mMoreRedPointView;
    private TextView mOutMsgCountView;
    private int mShowMsgRedPointNum;
    private int mTitleAlpha;
    private ImageView mTitleBack;
    private boolean maxNineOrNineNine;
    private ViewGroup msgContainer;
    private ImageView msgIcon;
    public NaviBtnStates naviBtnStates;
    private boolean naviExcludeStatusGone;
    private BaseNaviFollowAdapter naviFollowAdapter;
    private int naviIconStyle;
    private NaviListener naviListener;
    private ViewGroup naviRootView;
    private NaviStateListener naviStateListener;
    private boolean needTransitionForTitle;
    private OnChangeListener onSecondBtnChangeListener;
    private boolean outSideShow;
    private int padding10;
    private int padding15;
    private int padding6;
    private ViewGroup parent;
    private ArrayList<NaviEntity> popupItemEntities;
    RelativeLayout rightLayout;
    private TextView rightTextView;
    private ImageView searchIcon;
    private RelativeLayout secondSeat;
    private View shareGiftIcon;
    private ImageView shareIcon;
    private ImageView shoppingCart;
    private View statusBarHeightView;
    private int statusTextStyle;
    private boolean statusbarAlwaysTransparent;
    private IThemeChangeListener themeChangeListener;
    private RelativeLayout thirdSeat;
    private SimpleDraweeView titleBg;
    private String titleImageUrl1;
    private String titleImageUrl2;
    private SimpleDraweeView titleImg;
    private SimpleDraweeView titleImgTemp;
    private TextView titleText;
    private boolean titleTransitioning;
    public boolean topLogoGrayEnable;
    private ImageView topLogoIcon;
    private ViewGroup webMoreContainer;
    private ColorDrawable whiteColorDrawable;
    private int width40;

    /* loaded from: classes12.dex */
    public static abstract class BaseNaviFollowAdapter {
        private String channelId;
        private int style = 1;

        public String getChannelId() {
            return this.channelId;
        }

        public int getStyle() {
            return this.style;
        }

        public abstract void onChannelIdChanged(@Nullable View view, String str);

        @Nullable
        public abstract View onCreateFollowView(int i2, int i3, int i4);

        public abstract void onStyleChanged(@Nullable View view, int i2);
    }

    /* loaded from: classes12.dex */
    public static class HomeNaviEntity extends NaviEntity {
        public HomeNaviEntity() {
            this.type = NaviEntity.TYPE_HOME;
            this.show = true;
            this.isOutSide = false;
            this.title = NaviEntity.getRezString(R.string.webview_navi_home);
            this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + NaviEntity.getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_shouye);
        }
    }

    /* loaded from: classes12.dex */
    public static class MessageNaviEntity extends NaviEntity {
        public MessageNaviEntity() {
            this.type = "message";
            this.show = true;
            this.isOutSide = false;
            this.title = NaviEntity.getRezString(R.string.webview_navi_message);
            this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + NaviEntity.getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_xiaoxi);
            this.uri = CalorieImageUtil.getRemoteImageUriSync("112", "2304");
        }
    }

    /* loaded from: classes12.dex */
    public class NaviBtnStates {
        public List<NaviEntity> insideIcons;
        private boolean isShowShare;
        public NaviEntity outsideIcon;

        public NaviBtnStates(NavigatorHolder navigatorHolder) {
            this(true);
        }

        private boolean isAlreadyShow(NaviEntity naviEntity) {
            NaviEntity naviEntity2 = this.outsideIcon;
            if (naviEntity2 == null || !naviEntity2.type.equals(naviEntity.type)) {
                Iterator<NaviEntity> it = this.insideIcons.iterator();
                while (it.hasNext()) {
                    if (it.next().type.equals(naviEntity.type)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }

        public boolean getIsInsideIcons(String str) {
            int i2 = -1;
            for (int i3 = 0; i3 < this.insideIcons.size(); i3++) {
                if (this.insideIcons.get(i3).type.equals(str)) {
                    i2 = i3;
                }
            }
            return i2 >= 0;
        }

        public boolean getIsShowOutside(String str) {
            NaviEntity naviEntity = this.outsideIcon;
            return naviEntity != null && naviEntity.type.equals(str);
        }

        public boolean isCustomInsertEnable(NaviEntity naviEntity) {
            if (naviEntity == null) {
                return false;
            }
            if (naviEntity.type.equals(NaviEntity.TYPE_CUSTOM)) {
                NaviEntity naviEntity2 = this.outsideIcon;
                int i2 = (naviEntity2 == null || !naviEntity2.type.equals(NaviEntity.TYPE_CUSTOM)) ? 0 : 1;
                Iterator<NaviEntity> it = this.insideIcons.iterator();
                while (it.hasNext()) {
                    if (it.next().type.equals(NaviEntity.TYPE_CUSTOM)) {
                        i2++;
                    }
                }
                return i2 < 2;
            }
            return true;
        }

        public boolean isOutsideAvailable() {
            NaviEntity naviEntity = this.outsideIcon;
            return naviEntity == null || naviEntity.type.equals("share");
        }

        public void push(NaviEntity naviEntity) {
            if (naviEntity == null) {
                return;
            }
            if (NaviEntity.TYPE_CLEAR.equals(naviEntity.type)) {
                reset();
            } else if (!NaviEntity.TYPE_HIDEMORE.equals(naviEntity.type)) {
                if (!NavigatorHolder.this.outSideShow) {
                    naviEntity.isOutSide = false;
                }
                if (!"share".equals(naviEntity.type)) {
                    if ("follow".equals(naviEntity.type) && naviEntity.show && !naviEntity.isOutSide) {
                        naviEntity.show = false;
                    }
                    if (!naviEntity.show) {
                        if ("search".equals(naviEntity.type) || "cart".equals(naviEntity.type) || NaviEntity.TYPE_HOME.equals(naviEntity.type) || "message".equals(naviEntity.type) || "feedback".equals(naviEntity.type)) {
                            return;
                        }
                        removeInside(naviEntity);
                        removeOutSide(naviEntity);
                    } else if (naviEntity.isOutSide) {
                        NaviEntity naviEntity2 = this.outsideIcon;
                        if ((naviEntity2 == null || "share".equals(naviEntity2.type)) && isCustomInsertEnable(naviEntity)) {
                            this.outsideIcon = naviEntity;
                            removeInside(naviEntity);
                        }
                    } else if (!getIsInsideIcons(naviEntity) && isCustomInsertEnable(naviEntity)) {
                        this.insideIcons.add(naviEntity);
                        removeOutSide(naviEntity);
                    }
                } else {
                    this.isShowShare = naviEntity.show;
                }
                if (this.isShowShare) {
                    NaviEntity naviEntity3 = this.outsideIcon;
                    if ((naviEntity3 == null || naviEntity3.type.equals("share")) && NavigatorHolder.this.outSideShow) {
                        this.outsideIcon = new NaviEntity("share", true, true, NavigatorHolder.this.isShareGift);
                        removeInside("share");
                    } else if (!getIsInsideIcons("share")) {
                        this.insideIcons.add(new NaviEntity("share", true, false, NavigatorHolder.this.isShareGift));
                    }
                } else {
                    removeOutSide("share");
                    removeInside("share");
                }
                Collections.sort(this.insideIcons, new Comparator<NaviEntity>() { // from class: com.jingdong.common.widget.NavigatorHolder.NaviBtnStates.1
                    {
                        NaviBtnStates.this = this;
                    }

                    @Override // java.util.Comparator
                    public int compare(NaviEntity naviEntity4, NaviEntity naviEntity5) {
                        return naviEntity4.getOrder().compareTo(naviEntity5.getOrder());
                    }
                });
            } else {
                this.insideIcons = new ArrayList();
            }
        }

        public void removeInside(NaviEntity naviEntity) {
            int i2 = -1;
            for (int i3 = 0; i3 < this.insideIcons.size(); i3++) {
                if (this.insideIcons.get(i3).equals(naviEntity)) {
                    i2 = i3;
                }
            }
            if (i2 >= 0) {
                this.insideIcons.remove(i2);
            }
        }

        public void removeOutSide(NaviEntity naviEntity) {
            NaviEntity naviEntity2 = this.outsideIcon;
            if (naviEntity2 == null || !naviEntity2.equals(naviEntity)) {
                return;
            }
            this.outsideIcon = null;
        }

        public void reset() {
            NavigatorHolder.this.isShareGift = false;
            this.outsideIcon = null;
            ArrayList arrayList = new ArrayList();
            this.insideIcons = arrayList;
            arrayList.add(new MessageNaviEntity());
            this.insideIcons.add(new HomeNaviEntity());
            this.insideIcons.add(new NaviEntity("search", true, false));
            this.insideIcons.add(new NaviEntity("cart", true, false));
            this.insideIcons.add(new NaviEntity("feedback", true, false));
            NavigatorHolder.this.resetFollowView();
            NavigatorHolder navigatorHolder = NavigatorHolder.this;
            navigatorHolder.setRedPointVisibility(navigatorHolder.mIsShowRedPoint);
        }

        public NaviBtnStates(boolean z) {
            NavigatorHolder.this = r1;
            if (z) {
                reset();
            }
        }

        public boolean getIsInsideIcons(NaviEntity naviEntity) {
            int i2 = -1;
            for (int i3 = 0; i3 < this.insideIcons.size(); i3++) {
                if (this.insideIcons.get(i3).equals(naviEntity)) {
                    i2 = i3;
                }
            }
            return i2 >= 0;
        }

        public void removeOutSide(String str) {
            NaviEntity naviEntity = this.outsideIcon;
            if (naviEntity == null || !naviEntity.type.equals(str)) {
                return;
            }
            this.outsideIcon = null;
        }

        public void removeInside(String str) {
            int i2 = -1;
            for (int i3 = 0; i3 < this.insideIcons.size(); i3++) {
                if (this.insideIcons.get(i3).type.equals(str)) {
                    i2 = i3;
                }
            }
            if (i2 >= 0) {
                this.insideIcons.remove(i2);
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface NaviListener {
        @Deprecated
        void onClickCalendar();

        void onClickCart();

        void onClickClose();

        void onClickCustom(String str);

        void onClickHome();

        void onClickMore();

        void onClickMsg();

        void onClickPopCart();

        void onClickPopCustom(String str);

        void onClickPopFeedback();

        void onClickPopHome();

        void onClickPopMsg();

        void onClickPopSearch();

        void onClickPopShare();

        void onClickSearch();

        void onClickShare();

        void onClickTitleBack();

        void onRightTextView();
    }

    /* loaded from: classes12.dex */
    public interface NaviStateListener {
        void onStyleChanged(int i2);
    }

    /* loaded from: classes12.dex */
    public interface OnChangeListener {
        void onChange(boolean z, NaviEntity naviEntity);
    }

    public NavigatorHolder(Context context, ViewGroup viewGroup) {
        this(context, viewGroup, false);
    }

    public void cancelTitleTransition() {
        if (this.titleTransitioning) {
            SimpleDraweeView simpleDraweeView = this.titleImg;
            if (simpleDraweeView != null) {
                simpleDraweeView.animate().cancel();
            }
            SimpleDraweeView simpleDraweeView2 = this.titleImgTemp;
            if (simpleDraweeView2 != null) {
                simpleDraweeView2.animate().cancel();
            }
            this.titleTransitioning = false;
        }
    }

    private void changeIconStyle(ImageView imageView) {
        if (imageView == null || !(imageView.getDrawable() instanceof IconDrawable)) {
            return;
        }
        IconDrawable iconDrawable = (IconDrawable) imageView.getDrawable();
        if (this.naviIconStyle == 2) {
            iconDrawable.color(WebNaviSettings.iconDarkModeColor);
        } else {
            iconDrawable.color(WebNaviSettings.iconWhiteModeColor);
        }
    }

    @Deprecated
    private void defaultNavi() {
        resetNaviVisibleExcludeStatus();
        this.titleImageUrl1 = null;
        this.titleImageUrl2 = null;
        this.immersive = false;
        ensureTitle();
        this.titleBg.setImageDrawable(!this.darkMode ? this.whiteColorDrawable : this.darkColorDrawable);
        this.titleImg.setVisibility(8);
        this.titleImgTemp.setVisibility(8);
        this.titleText.setVisibility(0);
        setCloseBtnVisible(false);
        updateNaviIconAndTitle(!this.darkMode ? 1 : 2);
        resetBtn();
    }

    private void defaultTitleBgHeight() {
        ViewGroup.LayoutParams layoutParams = this.titleBg.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).addRule(12);
        }
        layoutParams.height = this.height49;
        this.titleBg.setLayoutParams(layoutParams);
    }

    private void ensureCartBtn() {
        if (this.cartContainer == null || this.shoppingCart == null) {
            initCartContainer();
        }
    }

    private void ensureCloseBtn() {
        if (this.closeIcon == null) {
            initCloseIcon();
            this.topLogoIcon = null;
        }
    }

    private void ensureCustomBtn() {
        if (this.custom1 == null) {
            iniCustomIcon();
        }
    }

    private void ensureHomeBtn() {
        if (this.homeIcon == null) {
            initHomeIcon();
        }
    }

    private void ensureMessageBtn() {
        if (this.msgContainer == null || this.msgIcon == null || this.mOutMsgCountView == null) {
            initMsgContainer();
        }
    }

    private void ensureMoreBtn() {
        if (this.webMoreContainer == null || this.bnMore == null || this.mMoreRedPointView == null || this.mMoreRedCountView == null || this.popupItemEntities == null) {
            initMoreContainer();
        }
    }

    private void ensureRightLayout() {
        if (this.rightLayout == null || this.rightTextView == null || this.firstSeat == null || this.secondSeat == null || this.thirdSeat == null) {
            initRightLayout();
        }
    }

    private void ensureSearchBtn() {
        if (this.searchIcon == null) {
            initSearchIcon();
        }
    }

    private void ensureShareBtn() {
        if (this.shareIcon == null || this.shareGiftIcon == null) {
            initShareIcon();
        }
    }

    private void ensureTitle() {
        if (this.titleText == null || this.titleImg == null || this.titleImgTemp == null) {
            initTitleView();
        }
    }

    private void ensureTopLogon() {
        if (this.topLogoIcon == null) {
            initTopLogo();
            this.closeIcon = null;
        }
    }

    public void hideTempTitleImg() {
        SimpleDraweeView simpleDraweeView = this.titleImgTemp;
        if (simpleDraweeView != null) {
            simpleDraweeView.setImageDrawable(null);
            this.titleImgTemp.setAlpha(0.0f);
            this.titleImgTemp.setVisibility(8);
        }
    }

    public void hideTitleImgAndShowText() {
        SimpleDraweeView simpleDraweeView = this.titleImg;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(1.0f);
            this.titleImg.setVisibility(8);
        }
        hideTempTitleImg();
        TextView textView = this.titleText;
        if (textView != null) {
            textView.setVisibility(0);
        }
    }

    public void hideTopLogo() {
        ImageView imageView = this.topLogoIcon;
        if (imageView != null) {
            imageView.setOnClickListener(null);
            this.topLogoIcon.setVisibility(8);
        }
        this.topLogoIcon = null;
        resetTitleTextWidth();
        if (this.closeNeedShow) {
            setCloseBtnVisible(true);
        }
    }

    private ImageView iniCustomIcon() {
        ImageView imageView = (ImageView) this.inflater.inflate(R.layout.common_navi_icon_item, (ViewGroup) null, false);
        this.custom1 = imageView;
        imageView.setId(R.id.web_custom1_btn);
        this.custom1.setOnClickListener(this);
        return this.custom1;
    }

    private ViewGroup initCartContainer() {
        FrameLayout frameLayout = (FrameLayout) this.inflater.inflate(R.layout.common_navi_icon_item_cart, (ViewGroup) null, false);
        this.cartContainer = frameLayout;
        ImageView imageView = (ImageView) frameLayout.findViewById(R.id.shopping_cart);
        this.shoppingCart = imageView;
        imageView.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_gouwuche).color(this.naviIconStyle == 1 ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.shoppingCart.setOnClickListener(this);
        FrameLayout frameLayout2 = this.cartContainer;
        Context context = this.context;
        int i2 = R.string.contentDescription_cart;
        frameLayout2.setContentDescription(context.getString(i2));
        this.shoppingCart.setContentDescription(this.context.getString(i2));
        return this.cartContainer;
    }

    private ImageView initCloseIcon() {
        ImageView left2ImageView = this.jdThemeTitle.getLeft2ImageView();
        this.closeIcon = left2ImageView;
        left2ImageView.setId(R.id.web_close);
        this.closeIcon.setOnClickListener(this);
        this.closeIcon.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_guanbi).color(this.naviIconStyle == 1 ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        ViewGroup.LayoutParams layoutParams = this.closeIcon.getLayoutParams();
        layoutParams.width = this.width40;
        layoutParams.height = this.height49;
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(1, this.mTitleBack.getId());
            layoutParams2.addRule(15);
        }
        ImageView imageView = this.closeIcon;
        int i2 = this.padding6;
        int i3 = this.padding15;
        imageView.setPadding(i2, i3, i2, i3);
        this.closeIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.closeIcon.setContentDescription(this.context.getString(R.string.contentDescription_close));
        this.closeIcon.setVisibility(8);
        return this.closeIcon;
    }

    private ImageView initHomeIcon() {
        ImageView imageView = (ImageView) this.inflater.inflate(R.layout.common_navi_icon_item, (ViewGroup) null, false);
        this.homeIcon = imageView;
        imageView.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_shouye).color(this.naviIconStyle == 1 ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.homeIcon.setId(R.id.web_home_btn);
        this.homeIcon.setOnClickListener(this);
        this.homeIcon.setContentDescription(this.context.getString(R.string.contentDescription_home));
        return this.homeIcon;
    }

    private ViewGroup initMoreContainer() {
        ViewGroup viewGroup = (ViewGroup) this.inflater.inflate(R.layout.common_navi_icon_item_more, (ViewGroup) null, false);
        this.webMoreContainer = viewGroup;
        viewGroup.setOnClickListener(this);
        ImageView imageView = (ImageView) this.webMoreContainer.findViewById(R.id.web_title_more);
        this.bnMore = imageView;
        imageView.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_gengduo).color(this.naviIconStyle == 1 ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.bnMore.setOnClickListener(this);
        this.mMoreRedPointView = this.webMoreContainer.findViewById(R.id.web_title_more_red_point);
        this.mMoreRedCountView = (TextView) this.webMoreContainer.findViewById(R.id.web_title_more_count);
        ImageView imageView2 = this.bnMore;
        Context context = this.context;
        int i2 = R.string.contentDescription_more;
        imageView2.setContentDescription(context.getString(i2));
        this.webMoreContainer.setContentDescription(this.context.getString(i2));
        this.popupItemEntities = new ArrayList<>();
        return this.webMoreContainer;
    }

    private ViewGroup initMsgContainer() {
        ViewGroup viewGroup = (ViewGroup) this.inflater.inflate(R.layout.common_navi_icon_item_msg, (ViewGroup) null, false);
        this.msgContainer = viewGroup;
        viewGroup.setOnClickListener(this);
        ImageView imageView = (ImageView) this.msgContainer.findViewById(R.id.web_msg_btn);
        this.msgIcon = imageView;
        imageView.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_xiaoxi).color(this.naviIconStyle == 1 ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.mOutMsgCountView = (TextView) this.msgContainer.findViewById(R.id.msg_count);
        this.msgIcon.setOnClickListener(this);
        this.msgContainer.setContentDescription(this.context.getString(R.string.contentDescription_message));
        return this.msgContainer;
    }

    private void initPopupData(ArrayList<NaviEntity> arrayList) {
        Iterator<NaviEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            NaviEntity next = it.next();
            if ("message".equals(next.type)) {
                next.count = this.mShowMsgRedPointNum;
                next.showRedPoint = this.mIsShowRedPoint;
            }
        }
        if (this.mBuilder == null) {
            this.mBuilder = new JDMenuWindow.Builder(this.context).bindItemListener(new JDMenuWindow.OnMenuClickListener() { // from class: com.jingdong.common.widget.NavigatorHolder.2
                {
                    NavigatorHolder.this = this;
                }

                @Override // com.jingdong.common.widget.JDMenuWindow.OnMenuClickListener
                public void onClick(AdapterView<?> adapterView, View view, JDMenuWindow.ISubMenu iSubMenu, int i2) {
                    if (iSubMenu instanceof NaviEntity) {
                        NaviEntity naviEntity = (NaviEntity) iSubMenu;
                        String str = naviEntity.type;
                        str.hashCode();
                        char c2 = '\uffff';
                        switch (str.hashCode()) {
                            case -1349088399:
                                if (str.equals(NaviEntity.TYPE_CUSTOM)) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                            case -906336856:
                                if (str.equals("search")) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case -485371922:
                                if (str.equals(NaviEntity.TYPE_HOME)) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case -191501435:
                                if (str.equals("feedback")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case 3046176:
                                if (str.equals("cart")) {
                                    c2 = 4;
                                    break;
                                }
                                break;
                            case 109400031:
                                if (str.equals("share")) {
                                    c2 = 5;
                                    break;
                                }
                                break;
                            case 954925063:
                                if (str.equals("message")) {
                                    c2 = 6;
                                    break;
                                }
                                break;
                        }
                        switch (c2) {
                            case 0:
                                if (NavigatorHolder.this.naviListener != null) {
                                    NavigatorHolder.this.naviListener.onClickPopCustom(naviEntity.jump);
                                    return;
                                }
                                return;
                            case 1:
                                if (NavigatorHolder.this.naviListener != null) {
                                    NavigatorHolder.this.naviListener.onClickPopSearch();
                                    return;
                                }
                                return;
                            case 2:
                                if (NavigatorHolder.this.naviListener != null) {
                                    NavigatorHolder.this.naviListener.onClickPopHome();
                                    return;
                                }
                                return;
                            case 3:
                                if (NavigatorHolder.this.naviListener != null) {
                                    NavigatorHolder.this.naviListener.onClickPopFeedback();
                                    return;
                                }
                                return;
                            case 4:
                                if (NavigatorHolder.this.naviListener != null) {
                                    NavigatorHolder.this.naviListener.onClickPopCart();
                                    return;
                                }
                                return;
                            case 5:
                                if (NavigatorHolder.this.naviListener != null) {
                                    NavigatorHolder.this.naviListener.onClickPopShare();
                                    return;
                                }
                                return;
                            case 6:
                                if (NavigatorHolder.this.naviListener != null) {
                                    NavigatorHolder.this.naviListener.onClickPopMsg();
                                    return;
                                }
                                return;
                            default:
                                return;
                        }
                    }
                }
            });
        }
        this.mBuilder.bindDatas(arrayList).styleType(!this.darkMode ? 2 : 1);
    }

    private void initRightLayout() {
        RelativeLayout relativeLayout = (RelativeLayout) this.inflater.inflate(R.layout.common_navi_right_layout, (ViewGroup) null, false);
        this.rightLayout = relativeLayout;
        this.jdThemeTitle.setRightView(relativeLayout);
        TextView textView = (TextView) this.rightLayout.findViewById(R.id.title_right_textView);
        this.rightTextView = textView;
        textView.setOnClickListener(this);
        this.firstSeat = (RelativeLayout) this.rightLayout.findViewById(R.id.common_navi_first_seat);
        this.secondSeat = (RelativeLayout) this.rightLayout.findViewById(R.id.common_navi_second_seat);
        this.thirdSeat = (RelativeLayout) this.rightLayout.findViewById(R.id.common_navi_third_seat);
    }

    private ImageView initSearchIcon() {
        ImageView imageView = (ImageView) this.inflater.inflate(R.layout.common_navi_icon_item, (ViewGroup) null, false);
        this.searchIcon = imageView;
        imageView.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_sousuo).color(this.naviIconStyle == 1 ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.searchIcon.setId(R.id.web_search_btn);
        this.searchIcon.setOnClickListener(this);
        this.searchIcon.setContentDescription(this.context.getString(R.string.contentDescription_search));
        return this.searchIcon;
    }

    private ImageView initShareIcon() {
        View inflate = this.inflater.inflate(R.layout.common_navi_icon_item_share, (ViewGroup) null, false);
        this.shareIcon = (ImageView) inflate.findViewById(R.id.web_share_icon);
        View findViewById = inflate.findViewById(R.id.web_share_gift);
        this.shareGiftIcon = findViewById;
        if (this.isShareGift) {
            findViewById.setVisibility(0);
        } else {
            findViewById.setVisibility(8);
        }
        this.shareIcon.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_fenxiang).color(this.naviIconStyle == 1 ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        inflate.setId(R.id.web_share_btn);
        inflate.setOnClickListener(this);
        inflate.setContentDescription(this.context.getString(R.string.contentDescription_share));
        return this.shareIcon;
    }

    private void initTitleView() {
        TextView titleTextView = this.jdThemeTitle.getTitleTextView();
        this.titleText = titleTextView;
        titleTextView.setTextColor(this.naviIconStyle == 1 ? WebNaviSettings.titleWhiteModeColor : WebNaviSettings.titleDarkModeColor);
        TextView textView = this.titleText;
        int i2 = this.padding10;
        textView.setPadding(i2, i2, i2, i2);
        this.titleText.setVisibility(0);
        RelativeLayout relativeLayout = (RelativeLayout) this.inflater.inflate(R.layout.common_navi_title_img, (ViewGroup) null, false);
        this.titleImg = (SimpleDraweeView) relativeLayout.findViewById(R.id.common_title_img);
        this.titleImgTemp = (SimpleDraweeView) relativeLayout.findViewById(R.id.common_title_img_temp);
        this.jdThemeTitle.setCenterView(relativeLayout);
    }

    private ImageView initTopLogo() {
        ImageView left2ImageView = this.jdThemeTitle.getLeft2ImageView();
        this.topLogoIcon = left2ImageView;
        left2ImageView.setImageDrawable(null);
        this.topLogoIcon.setOnClickListener(null);
        ViewGroup.LayoutParams layoutParams = this.topLogoIcon.getLayoutParams();
        int i2 = this.dp30;
        layoutParams.width = i2 * 3;
        layoutParams.height = i2;
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.addRule(1, this.mTitleBack.getId());
            layoutParams2.addRule(15);
        }
        this.topLogoIcon.setPadding(0, 0, 0, 0);
        this.topLogoIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.topLogoIcon.setContentDescription(this.context.getString(R.string.contentDescription_toplogo));
        this.topLogoIcon.setVisibility(8);
        return this.topLogoIcon;
    }

    private void initViewNow() {
        ViewGroup viewGroup = (ViewGroup) this.inflater.inflate(R.layout.common_navi_icon_item_more, (ViewGroup) null, false);
        this.webMoreContainer = viewGroup;
        viewGroup.setOnClickListener(this);
        ImageView imageView = (ImageView) this.webMoreContainer.findViewById(R.id.web_title_more);
        this.bnMore = imageView;
        imageView.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_gengduo).color(!this.darkMode ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.bnMore.setOnClickListener(this);
        this.mMoreRedPointView = this.webMoreContainer.findViewById(R.id.web_title_more_red_point);
        this.mMoreRedCountView = (TextView) this.webMoreContainer.findViewById(R.id.web_title_more_count);
        ImageView imageView2 = this.bnMore;
        Context context = this.context;
        int i2 = R.string.contentDescription_more;
        imageView2.setContentDescription(context.getString(i2));
        this.webMoreContainer.setContentDescription(this.context.getString(i2));
        View inflate = this.inflater.inflate(R.layout.common_navi_icon_item_share, (ViewGroup) null, false);
        this.shareIcon = (ImageView) inflate.findViewById(R.id.web_share_icon);
        this.shareGiftIcon = inflate.findViewById(R.id.web_share_gift);
        this.shareIcon.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_fenxiang).color(!this.darkMode ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        inflate.setId(R.id.web_share_btn);
        inflate.setOnClickListener(this);
        inflate.setContentDescription(this.context.getString(R.string.contentDescription_share));
        LayoutInflater layoutInflater = this.inflater;
        int i3 = R.layout.common_navi_icon_item;
        ImageView imageView3 = (ImageView) layoutInflater.inflate(i3, (ViewGroup) null, false);
        this.searchIcon = imageView3;
        imageView3.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_sousuo).color(!this.darkMode ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.searchIcon.setId(R.id.web_search_btn);
        this.searchIcon.setOnClickListener(this);
        this.searchIcon.setContentDescription(this.context.getString(R.string.contentDescription_search));
        ImageView imageView4 = (ImageView) this.inflater.inflate(i3, (ViewGroup) null, false);
        this.homeIcon = imageView4;
        imageView4.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_shouye).color(!this.darkMode ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.homeIcon.setId(R.id.web_home_btn);
        this.homeIcon.setOnClickListener(this);
        this.homeIcon.setContentDescription(this.context.getString(R.string.contentDescription_home));
        ViewGroup viewGroup2 = (ViewGroup) this.inflater.inflate(R.layout.common_navi_icon_item_msg, (ViewGroup) null, false);
        this.msgContainer = viewGroup2;
        viewGroup2.setOnClickListener(this);
        ImageView imageView5 = (ImageView) this.msgContainer.findViewById(R.id.web_msg_btn);
        this.msgIcon = imageView5;
        imageView5.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_xiaoxi).color(!this.darkMode ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.mOutMsgCountView = (TextView) this.msgContainer.findViewById(R.id.msg_count);
        this.msgIcon.setOnClickListener(this);
        this.msgContainer.setContentDescription(this.context.getString(R.string.contentDescription_message));
        FrameLayout frameLayout = (FrameLayout) this.inflater.inflate(R.layout.common_navi_icon_item_cart, (ViewGroup) null, false);
        this.cartContainer = frameLayout;
        ImageView imageView6 = (ImageView) frameLayout.findViewById(R.id.shopping_cart);
        this.shoppingCart = imageView6;
        imageView6.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_gouwuche).color(!this.darkMode ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
        this.shoppingCart.setOnClickListener(this);
        FrameLayout frameLayout2 = this.cartContainer;
        Context context2 = this.context;
        int i4 = R.string.contentDescription_cart;
        frameLayout2.setContentDescription(context2.getString(i4));
        this.shoppingCart.setContentDescription(this.context.getString(i4));
        ImageView imageView7 = (ImageView) this.inflater.inflate(i3, (ViewGroup) null, false);
        this.custom1 = imageView7;
        imageView7.setId(R.id.web_custom1_btn);
        this.custom1.setOnClickListener(this);
        this.popupItemEntities = new ArrayList<>();
        TextView titleTextView = this.jdThemeTitle.getTitleTextView();
        this.titleText = titleTextView;
        titleTextView.setTextColor(!this.darkMode ? WebNaviSettings.titleWhiteModeColor : WebNaviSettings.titleDarkModeColor);
        TextView textView = this.titleText;
        int i5 = this.padding10;
        textView.setPadding(i5, i5, i5, i5);
        this.titleText.setVisibility(0);
        RelativeLayout relativeLayout = (RelativeLayout) this.inflater.inflate(R.layout.common_navi_title_img, (ViewGroup) null, false);
        this.titleImg = (SimpleDraweeView) relativeLayout.findViewById(R.id.common_title_img);
        this.titleImgTemp = (SimpleDraweeView) relativeLayout.findViewById(R.id.common_title_img_temp);
        this.jdThemeTitle.setCenterView(relativeLayout);
        if (this.topLogoGrayEnable) {
            initCloseIcon();
        } else {
            ImageView left2ImageView = this.jdThemeTitle.getLeft2ImageView();
            this.closeIcon = left2ImageView;
            left2ImageView.setId(R.id.web_close);
            this.closeIcon.setOnClickListener(this);
            this.closeIcon.setImageDrawable(WebNaviUtil.createIconDrawable(this.context, com.jingdong.wireless.iconfont.R.string.jdif_common_guanbi).color(!this.darkMode ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
            this.closeIcon.setVisibility(0);
            ViewGroup.LayoutParams layoutParams = this.closeIcon.getLayoutParams();
            layoutParams.width = this.width40;
            layoutParams.height = this.height49;
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                layoutParams2.addRule(1, this.mTitleBack.getId());
                layoutParams2.addRule(15);
            }
            ImageView imageView8 = this.closeIcon;
            int i6 = this.padding6;
            int i7 = this.padding15;
            imageView8.setPadding(i6, i7, i6, i7);
            this.closeIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.closeIcon.setContentDescription(this.context.getString(R.string.contentDescription_close));
        }
        RelativeLayout relativeLayout2 = (RelativeLayout) this.inflater.inflate(R.layout.common_navi_right_layout, (ViewGroup) null, false);
        this.rightLayout = relativeLayout2;
        this.jdThemeTitle.setRightView(relativeLayout2);
        TextView textView2 = (TextView) this.rightLayout.findViewById(R.id.title_right_textView);
        this.rightTextView = textView2;
        textView2.setOnClickListener(this);
        this.firstSeat = (RelativeLayout) this.rightLayout.findViewById(R.id.common_navi_first_seat);
        this.secondSeat = (RelativeLayout) this.rightLayout.findViewById(R.id.common_navi_second_seat);
        this.thirdSeat = (RelativeLayout) this.rightLayout.findViewById(R.id.common_navi_third_seat);
        this.naviBtnStates = new NaviBtnStates(this);
        defaultNavi();
    }

    private void resetFollowParams() {
        BaseNaviFollowAdapter baseNaviFollowAdapter = this.naviFollowAdapter;
        if (baseNaviFollowAdapter != null) {
            baseNaviFollowAdapter.style = 1;
            this.naviFollowAdapter.channelId = null;
        }
    }

    public void resetFollowView() {
        this.mFollowView = null;
        resetFollowParams();
    }

    private void resetSecondSeatSize() {
        if (this.topLogoGrayEnable) {
            resetSecondSeatSizeNew();
            return;
        }
        ensureRightLayout();
        ensureTitle();
        ViewGroup.LayoutParams layoutParams = this.secondSeat.getLayoutParams();
        if (layoutParams != null) {
            int i2 = layoutParams.width;
            int i3 = this.dp35;
            if (i2 != i3 || layoutParams.height != i3) {
                layoutParams.width = i3;
                layoutParams.height = i3;
                this.secondSeat.setLayoutParams(layoutParams);
            }
        }
        if (Build.VERSION.SDK_INT >= 16) {
            int maxWidth = this.titleText.getMaxWidth();
            int i4 = this.dp225;
            if (maxWidth != i4) {
                this.titleText.setMaxWidth(i4);
                return;
            }
            return;
        }
        this.titleText.setMaxWidth(this.dp225);
    }

    private void resetSecondSeatSizeNew() {
        ensureRightLayout();
        ViewGroup.LayoutParams layoutParams = this.secondSeat.getLayoutParams();
        if (layoutParams != null) {
            int i2 = layoutParams.width;
            int i3 = this.dp35;
            if (i2 != i3 || layoutParams.height != i3) {
                layoutParams.width = i3;
                layoutParams.height = i3;
                this.secondSeat.setLayoutParams(layoutParams);
            }
        }
        resetTitleTextWidth();
    }

    private void resetTitleTextWidth() {
        NaviBtnStates naviBtnStates;
        NaviEntity naviEntity;
        boolean z = (this.mFollowView == null || (naviBtnStates = this.naviBtnStates) == null || (naviEntity = naviBtnStates.outsideIcon) == null || !"follow".equals(naviEntity.type)) ? false : true;
        boolean z2 = this.topLogoIcon != null;
        if (z || z2) {
            return;
        }
        ensureTitle();
        if (Build.VERSION.SDK_INT >= 16) {
            int maxWidth = this.titleText.getMaxWidth();
            int i2 = this.dp225;
            if (maxWidth != i2) {
                this.titleText.setMaxWidth(i2);
                return;
            }
            return;
        }
        this.titleText.setMaxWidth(this.dp225);
    }

    private void setCloseBtnVisibleNew(boolean z) {
        this.closeNeedShow = z;
        if (this.topLogoIcon != null) {
            return;
        }
        if (z) {
            if (this.customRootView == null) {
                ensureCloseBtn();
                this.closeIcon.setVisibility(0);
                return;
            }
            return;
        }
        this.closeIcon.setVisibility(8);
    }

    private void setJdThemeTitleRootViewHeight() {
        ViewGroup.LayoutParams layoutParams;
        JdThemeTitle jdThemeTitle = this.jdThemeTitle;
        if (jdThemeTitle == null || (layoutParams = jdThemeTitle.getLayoutParams()) == null) {
            return;
        }
        int statusBarHeight = this.enableStatusTint ? UnStatusBarTintUtil.getStatusBarHeight(this.context) : 0;
        if (this.naviExcludeStatusGone) {
            layoutParams.height = statusBarHeight;
        } else {
            layoutParams.height = this.height49 + statusBarHeight;
        }
        this.jdThemeTitle.setLayoutParams(layoutParams);
    }

    public void showTitleImgFinally(String str) {
        this.titleImg.setVisibility(0);
        JDImageUtils.displayImage(str, this.titleImg, null, false, new JDImageLoadingListener() { // from class: com.jingdong.common.widget.NavigatorHolder.7
            {
                NavigatorHolder.this = this;
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
                NavigatorHolder.this.hideTitleImgAndShowText();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                NavigatorHolder.this.titleImg.setAlpha(1.0f);
                NavigatorHolder.this.hideTempTitleImg();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                NavigatorHolder.this.hideTitleImgAndShowText();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
            }
        }, null);
    }

    private void showTopLogo(int i2, int i3, String str, View.OnClickListener onClickListener) {
        this.titleText.setMaxWidth(this.dp160);
        ensureTopLogon();
        ViewGroup.LayoutParams layoutParams = this.topLogoIcon.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        this.topLogoIcon.setVisibility(0);
        JDImageUtils.displayImage(str, this.topLogoIcon, null, false, new JDImageLoadingListener() { // from class: com.jingdong.common.widget.NavigatorHolder.3
            {
                NavigatorHolder.this = this;
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                OKLog.d(NavigatorHolder.TAG, "onLoadingFailed: " + jDFailReason.getCause());
                NavigatorHolder.this.hideTopLogo();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
            }
        }, null);
        this.topLogoIcon.setOnClickListener(onClickListener);
    }

    public void addCustomView(View view, RelativeLayout.LayoutParams layoutParams) {
        if (view == null) {
            RelativeLayout relativeLayout = this.customRootView;
            if (relativeLayout != null && relativeLayout.getParent() != null) {
                ((ViewGroup) this.customRootView.getParent()).removeView(this.customRootView);
            }
            this.customRootView = null;
            return;
        }
        setCloseBtnVisible(false);
        RelativeLayout relativeLayout2 = this.customRootView;
        if (relativeLayout2 == null) {
            this.customRootView = new RelativeLayout(this.jdThemeTitle.getContext());
            ensureRightLayout();
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.setMargins(0, 0, DPIUtil.dip2px(40.0f), 0);
            this.rightLayout.addView(this.customRootView, layoutParams2);
        } else {
            relativeLayout2.removeAllViews();
        }
        this.customRootView.addView(view, layoutParams);
    }

    public void changeTitleImgSmoothly(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            ensureTitle();
            OKLog.d(TAG, "smoothly display title image");
            cancelTitleTransition();
            boolean z = this.titleImg.getVisibility() == 0;
            if (!z) {
                this.titleImg.setAlpha(0.0f);
                this.titleImg.setVisibility(0);
            }
            SimpleDraweeView simpleDraweeView = this.titleImg;
            boolean z2 = z && !str.equals(simpleDraweeView.getTag());
            this.needTransitionForTitle = z2;
            if (z2) {
                simpleDraweeView = this.titleImgTemp;
                simpleDraweeView.setAlpha(0.0f);
                this.titleImgTemp.setVisibility(0);
            }
            JDImageUtils.displayImage(str, simpleDraweeView, new JDDisplayImageOptions().cacheInMemory(true), false, new JDImageLoadingListener() { // from class: com.jingdong.common.widget.NavigatorHolder.6
                {
                    NavigatorHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                    OKLog.d(NavigatorHolder.TAG, "onLoadingCancelled");
                    NavigatorHolder.this.needTransitionForTitle = false;
                    NavigatorHolder.this.cancelTitleTransition();
                    NavigatorHolder.this.hideTempTitleImg();
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    NavigatorHolder.this.titleImg.setTag(str);
                    NavigatorHolder.this.titleText.setVisibility(8);
                    NavigatorHolder.this.cancelTitleTransition();
                    if (!NavigatorHolder.this.needTransitionForTitle) {
                        NavigatorHolder.this.titleImg.setAlpha(1.0f);
                        NavigatorHolder.this.titleImg.setVisibility(0);
                        NavigatorHolder.this.hideTempTitleImg();
                        return;
                    }
                    NavigatorHolder.this.needTransitionForTitle = false;
                    NavigatorHolder.this.titleTransitioning = true;
                    NavigatorHolder.this.titleImg.setAlpha(1.0f);
                    NavigatorHolder.this.titleImg.setVisibility(0);
                    NavigatorHolder.this.titleImg.animate().alpha(0.0f).setDuration(400L).setListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.widget.NavigatorHolder.6.1
                        {
                            AnonymousClass6.this = this;
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                            OKLog.d(NavigatorHolder.TAG, "fade-out cancel");
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            OKLog.d(NavigatorHolder.TAG, "fade-out ends");
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationRepeat(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            OKLog.d(NavigatorHolder.TAG, "fade-out starts");
                        }
                    }).start();
                    NavigatorHolder.this.titleImgTemp.setAlpha(0.0f);
                    NavigatorHolder.this.titleImgTemp.setVisibility(0);
                    NavigatorHolder.this.titleImgTemp.animate().alpha(1.0f).setDuration(500L).setListener(new Animator.AnimatorListener() { // from class: com.jingdong.common.widget.NavigatorHolder.6.2
                        {
                            AnonymousClass6.this = this;
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationCancel(Animator animator) {
                            OKLog.d(NavigatorHolder.TAG, "fade-in cancel");
                            NavigatorHolder.this.titleTransitioning = false;
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationEnd(Animator animator) {
                            OKLog.d(NavigatorHolder.TAG, "fade-in ends");
                            NavigatorHolder.this.titleTransitioning = false;
                            AnonymousClass6 anonymousClass6 = AnonymousClass6.this;
                            NavigatorHolder.this.showTitleImgFinally(str);
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationRepeat(Animator animator) {
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public void onAnimationStart(Animator animator) {
                            OKLog.d(NavigatorHolder.TAG, "fade-in starts");
                        }
                    }).start();
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    OKLog.d(NavigatorHolder.TAG, "smoothly onLoadingFailed: " + jDFailReason.getCause());
                    NavigatorHolder.this.needTransitionForTitle = false;
                    NavigatorHolder.this.hideTitleImgAndShowText();
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            }, null);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            this.needTransitionForTitle = false;
            cancelTitleTransition();
            hideTitleImgAndShowText();
        }
    }

    public void closePopup() {
        JDMenuWindow.Builder builder = this.mBuilder;
        if (builder == null || !builder.isShowing()) {
            return;
        }
        this.mBuilder.dismiss();
    }

    public void configBtn(String str) {
        OKLog.d(TAG, "configParam:" + str);
        if (this.naviBtnStates == null) {
            this.naviBtnStates = new NaviBtnStates(this);
        }
        this.naviBtnStates.push(NaviEntity.parse(str));
        try {
            updateStates(this.naviBtnStates);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void controlNavigationItems(JSONObject jSONObject) {
        if (this.naviBtnStates == null) {
            this.naviBtnStates = new NaviBtnStates(this);
        }
        NaviEntity parse = NaviEntity.parse(jSONObject.toString());
        if (parse != null) {
            if (TextUtils.equals(parse.type, NaviEntity.TYPE_HOME) || TextUtils.equals(parse.type, "message")) {
                parse.isOutSide = false;
            }
            if (!parse.show && !TextUtils.equals(parse.type, NaviEntity.TYPE_CLEAR) && !TextUtils.equals(parse.type, NaviEntity.TYPE_HIDEMORE)) {
                this.naviBtnStates.removeOutSide(parse.type);
                this.naviBtnStates.removeInside(parse.type);
            } else {
                this.naviBtnStates.push(parse);
            }
            if (TextUtils.equals("message", parse.type)) {
                if (!parse.show) {
                    View view = this.mMoreRedPointView;
                    if (view != null) {
                        view.setVisibility(8);
                    }
                } else {
                    setRedPointVisibility(this.mIsShowRedPoint);
                }
            }
        }
        try {
            updateStates(this.naviBtnStates);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public View createStatusBarHeightView(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return null;
        }
        int statusBarHeight = WebNaviUtil.getStatusBarHeight((Activity) context);
        View view = new View(context);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, statusBarHeight));
        view.setBackgroundResource(R.color.status_bar_bg);
        return view;
    }

    public void defaultNaviWithoutClostBtn() {
        resetNaviVisibleExcludeStatus();
        this.titleImageUrl1 = null;
        this.titleImageUrl2 = null;
        this.immersive = false;
        ensureTitle();
        this.titleBg.setImageDrawable(!this.darkMode ? this.whiteColorDrawable : this.darkColorDrawable);
        this.titleImg.setVisibility(8);
        this.titleImgTemp.setVisibility(8);
        this.titleText.setVisibility(0);
        updateNaviIconAndTitle(!this.darkMode ? 1 : 2);
        if (this.topLogoGrayEnable) {
            hideTopLogo();
        }
        resetBtn();
        setTitleImgWidthDefault();
        unRegisterTitleThemeChangeListener();
    }

    public View getBarHeightView() {
        return this.statusBarHeightView;
    }

    public SimpleDraweeView getNaviBgView() {
        return this.titleBg;
    }

    public BaseNaviFollowAdapter getNaviFollowAdapter() {
        return this.naviFollowAdapter;
    }

    public int getNaviHeight() {
        JdThemeTitle jdThemeTitle = this.jdThemeTitle;
        if (jdThemeTitle != null) {
            return jdThemeTitle.getMeasuredHeight();
        }
        return 0;
    }

    public NaviListener getNaviListener() {
        return this.naviListener;
    }

    public NaviStateListener getNaviStateListener() {
        return this.naviStateListener;
    }

    @Deprecated
    public RelativeLayout getRightLayout() {
        ensureRightLayout();
        return this.rightLayout;
    }

    public int getStyle() {
        return this.naviIconStyle;
    }

    public SimpleDraweeView getTitleImg() {
        ensureTitle();
        return this.titleImg;
    }

    public TextView getTitleTextView() {
        ensureTitle();
        return this.titleText;
    }

    public boolean isDarkMode() {
        return this.darkMode;
    }

    public boolean isNaviImmersive() {
        return this.immersive;
    }

    public boolean isOnlyNaviGone() {
        return this.naviExcludeStatusGone;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.web_title_more && id != R.id.web_more_container) {
            if (id == R.id.web_share_btn) {
                NaviListener naviListener = this.naviListener;
                if (naviListener != null) {
                    naviListener.onClickShare();
                    return;
                }
                return;
            } else if (id == R.id.title_back) {
                NaviListener naviListener2 = this.naviListener;
                if (naviListener2 != null) {
                    naviListener2.onClickTitleBack();
                    return;
                }
                return;
            } else if (id == R.id.title_right_textView) {
                NaviListener naviListener3 = this.naviListener;
                if (naviListener3 != null) {
                    naviListener3.onRightTextView();
                    return;
                }
                return;
            } else if (id == R.id.web_close) {
                NaviListener naviListener4 = this.naviListener;
                if (naviListener4 != null) {
                    naviListener4.onClickClose();
                    return;
                }
                return;
            } else if (id == R.id.web_msg_btn) {
                NaviListener naviListener5 = this.naviListener;
                if (naviListener5 != null) {
                    naviListener5.onClickMsg();
                    return;
                }
                return;
            } else if (id == R.id.web_home_btn) {
                NaviListener naviListener6 = this.naviListener;
                if (naviListener6 != null) {
                    naviListener6.onClickHome();
                    return;
                }
                return;
            } else if (id == R.id.web_search_btn) {
                NaviListener naviListener7 = this.naviListener;
                if (naviListener7 != null) {
                    naviListener7.onClickSearch();
                    return;
                }
                return;
            } else if (id == R.id.shopping_cart) {
                NaviListener naviListener8 = this.naviListener;
                if (naviListener8 != null) {
                    naviListener8.onClickCart();
                    return;
                }
                return;
            } else if (id == R.id.web_custom1_btn && (view.getTag() instanceof NaviEntity)) {
                NaviEntity naviEntity = (NaviEntity) view.getTag();
                NaviListener naviListener9 = this.naviListener;
                if (naviListener9 != null) {
                    naviListener9.onClickCustom(naviEntity.jump);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        openOrClosePopup();
        NaviListener naviListener10 = this.naviListener;
        if (naviListener10 != null) {
            naviListener10.onClickMore();
        }
    }

    void openOrClosePopup() {
        JDMenuWindow.Builder builder = this.mBuilder;
        if (builder != null && builder.isShowing()) {
            this.mBuilder.dismiss();
            return;
        }
        initPopupData(this.popupItemEntities);
        Context context = this.context;
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        try {
            this.mBuilder.show(this.bnMore, -DPIUtil.dip2px(120.0f), DPIUtil.dip2px(8.0f));
        } catch (Throwable th) {
            OKLog.e(TAG, th);
        }
    }

    public void reSetRightTextView(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ensureRightLayout();
        this.rightTextView.setText(str);
    }

    @Deprecated
    public void refreshCart(int i2) {
    }

    @Deprecated
    public void refreshCartWithAnim(int i2) {
    }

    public void refreshOutsideButtonStyle() {
        changeIconStyle(this.homeIcon);
        changeIconStyle(this.shoppingCart);
        changeIconStyle(this.searchIcon);
        changeIconStyle(this.msgIcon);
        changeIconStyle(this.shareIcon);
        if (this.isShareGift) {
            ensureShareBtn();
            this.shareGiftIcon.setVisibility(0);
        } else {
            View view = this.shareGiftIcon;
            if (view != null) {
                view.setVisibility(8);
            }
        }
        if (this.naviIconStyle == 2) {
            BaseNaviFollowAdapter baseNaviFollowAdapter = this.naviFollowAdapter;
            if (baseNaviFollowAdapter != null) {
                baseNaviFollowAdapter.style = 2;
                this.naviFollowAdapter.onStyleChanged(this.mFollowView, 2);
            }
            NaviStateListener naviStateListener = this.naviStateListener;
            if (naviStateListener != null) {
                naviStateListener.onStyleChanged(2);
                return;
            }
            return;
        }
        BaseNaviFollowAdapter baseNaviFollowAdapter2 = this.naviFollowAdapter;
        if (baseNaviFollowAdapter2 != null) {
            baseNaviFollowAdapter2.style = 1;
            this.naviFollowAdapter.onStyleChanged(this.mFollowView, 1);
        }
        NaviStateListener naviStateListener2 = this.naviStateListener;
        if (naviStateListener2 != null) {
            naviStateListener2.onStyleChanged(1);
        }
    }

    public void registerTitleThemeChangeListener() {
        Context context = this.context;
        final Activity activity = context instanceof Activity ? (Activity) context : null;
        this.jdThemeTitle.setModuleId(ThemeTitleConstant.WEBVIEW_MODULE_ID);
        IThemeChangeListener iThemeChangeListener = new IThemeChangeListener() { // from class: com.jingdong.common.widget.NavigatorHolder.1
            private String dark = "DARK";

            {
                NavigatorHolder.this = this;
            }

            @Override // com.jingdong.common.unification.title.theme.IThemeChangeListener
            public void onThemeChange(boolean z, String str) {
                if (NavigatorHolder.this.jdThemeTitle != null) {
                    NavigatorHolder.this.jdThemeTitle.notifyChange();
                    if (this.dark.equals(str)) {
                        UnStatusBarTintUtil.setStatusBarLightMode(activity);
                        NavigatorHolder.this.statusTextStyle = 1;
                        NavigatorHolder.this.updateNaviIconAndTitle(1);
                        return;
                    }
                    UnStatusBarTintUtil.setStatusBarDarkMode(activity);
                    NavigatorHolder.this.statusTextStyle = 2;
                    NavigatorHolder.this.updateNaviIconAndTitle(2);
                }
            }
        };
        this.themeChangeListener = iThemeChangeListener;
        ThemeTitleHelper.setThemeTitleChangeListener(ThemeTitleConstant.WEBVIEW_MODULE_ID, iThemeChangeListener);
        this.statusBarHeightView.setVisibility(8);
        ViewGroup.LayoutParams layoutParams = this.titleBg.getLayoutParams();
        layoutParams.height = -1;
        this.titleBg.setLayoutParams(layoutParams);
        ThemeTitleHelper.notifySomeTitleChange(ThemeTitleConstant.WEBVIEW_MODULE_ID);
    }

    public void removeMoreBtn() {
        ensureRightLayout();
        this.firstSeat.removeAllViews();
        this.secondSeat.removeAllViews();
        resetSecondSeatSize();
        this.firstSeat.setVisibility(8);
        this.isShowMoreBtn = false;
    }

    public void resetBtn() {
        if (this.naviBtnStates == null) {
            this.naviBtnStates = new NaviBtnStates(false);
        }
        this.naviBtnStates.push(new NaviEntity(NaviEntity.TYPE_CLEAR));
        updateStates(this.naviBtnStates);
    }

    public void resetNaviVisibleExcludeStatus() {
        setNaviVisibleExcludeStatus(true);
    }

    public void setCart(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (YES.equals(jSONObject.optString(IS_SHOW))) {
                setHeadStyle("", "", -1, 1, -1, -1, -1);
            } else if ("N".equals(jSONObject.optString(IS_SHOW))) {
                setHeadStyle("", "", -1, 0, -1, -1, -1);
            }
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
        }
    }

    public void setCloseBtnVisible(boolean z) {
        if (this.topLogoGrayEnable) {
            setCloseBtnVisibleNew(z);
        } else if (z) {
            if (this.customRootView == null) {
                ensureCloseBtn();
                this.closeIcon.setVisibility(0);
            }
        } else {
            this.closeIcon.setVisibility(8);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setDarkMode(boolean z) {
        this.darkMode = z;
        setDarkModeForBackground(z);
        setDarkModeForIconTitle(z);
    }

    public synchronized void setDarkModeForBackground(boolean z) {
        this.darkMode = z;
        boolean z2 = false;
        if (this.statusbarAlwaysTransparent) {
            setStatusBarHeightViewVisibility(0);
            this.statusBarHeightView.setBackgroundResource(R.color.status_bar_bg);
        } else {
            Context context = this.context;
            Activity activity = context instanceof Activity ? (Activity) context : null;
            if (activity != null) {
                if (!z) {
                    if (UnStatusBarTintUtil.setStatusBarLightMode(activity)) {
                        this.statusTextStyle = 1;
                        this.statusBarHeightView.setBackgroundColor(WebNaviSettings.bgWhiteModeColor);
                        z2 = true;
                    }
                } else if (UnStatusBarTintUtil.setStatusBarDarkMode(activity)) {
                    this.statusTextStyle = 2;
                    this.statusBarHeightView.setBackgroundColor(WebNaviSettings.bgDarkModeColor);
                    z2 = true;
                }
            }
            if (!z2) {
                this.statusBarHeightView.setBackgroundResource(R.color.status_bar_bg);
            }
        }
        this.titleBg.setImageDrawable(!z ? this.whiteColorDrawable : this.darkColorDrawable);
    }

    public synchronized void setDarkModeForIconTitle(boolean z) {
        this.darkMode = z;
        updateNaviIconAndTitleColor(!z ? 1 : 2);
    }

    public void setDarkModeWithoutChangeUi(boolean z) {
        this.darkMode = z;
    }

    public void setHeadStyle(String str, String str2, int i2, int i3, int i4, int i5, int i6) {
        if (!TextUtils.isEmpty(str)) {
            ensureTitle();
            this.titleText.setText(str);
            this.titleText.setVisibility(0);
            setTitleImg("{\"isShow\":\"N\"}");
        }
        if (!TextUtils.isEmpty(str2)) {
            setTitleImg("{\"isShow\":\"Y\",\"imageUrl\":\"" + str2 + "\"}");
        }
        if (this.naviBtnStates == null) {
            this.naviBtnStates = new NaviBtnStates(false);
        }
        this.naviBtnStates.reset();
        if (i3 > 0) {
            this.naviBtnStates.push(new NaviEntity("cart", true, true));
        } else {
            this.naviBtnStates.push(new NaviEntity("cart", true, false));
        }
        if (i4 == 0) {
            this.naviBtnStates.push(new NaviEntity("search", true, false));
        } else if (i4 > 0) {
            if (this.naviBtnStates.isOutsideAvailable()) {
                this.naviBtnStates.push(new NaviEntity("search", true, true));
            } else {
                this.naviBtnStates.push(new NaviEntity("search", true, false));
            }
        }
        if (i2 == 0) {
            this.naviBtnStates.push(new NaviEntity("share", false, false));
        } else if (i2 > 0) {
            this.naviBtnStates.push(new NaviEntity("share", true, false));
        }
        updateStates(this.naviBtnStates);
    }

    public void setMsgRedPointNum(int i2, boolean z) {
        this.mShowMsgRedPointNum = i2;
        this.maxNineOrNineNine = z;
        if (i2 > 0) {
            if (this.naviBtnStates == null) {
                this.naviBtnStates = new NaviBtnStates(this);
            }
            if (this.naviBtnStates.getIsShowOutside("message")) {
                ensureMessageBtn();
                this.mOutMsgCountView.setVisibility(0);
                if (z) {
                    if (i2 > 9) {
                        this.mOutMsgCountView.setText("9+");
                        this.mOutMsgCountView.setBackgroundResource(R.drawable.message_door_red_bg2);
                    } else {
                        this.mOutMsgCountView.setText(String.valueOf(i2));
                        this.mOutMsgCountView.setBackgroundResource(R.drawable.message_door_red_bg1);
                    }
                } else if (i2 > 9) {
                    if (i2 > 99) {
                        this.mOutMsgCountView.setText("99+");
                    } else {
                        this.mOutMsgCountView.setText(String.valueOf(i2));
                    }
                    this.mOutMsgCountView.setBackgroundResource(R.drawable.message_door_red_bg2);
                } else {
                    this.mOutMsgCountView.setText(String.valueOf(i2));
                    this.mOutMsgCountView.setBackgroundResource(R.drawable.message_door_red_bg1);
                }
                setRedPointVisibility(false);
                return;
            } else if (this.naviBtnStates.getIsInsideIcons("message")) {
                ensureMoreBtn();
                this.mMoreRedCountView.setVisibility(0);
                if (z) {
                    if (i2 > 9) {
                        this.mMoreRedCountView.setText("9+");
                        this.mMoreRedCountView.setBackgroundResource(R.drawable.message_door_red_bg2);
                    } else {
                        this.mMoreRedCountView.setText(String.valueOf(i2));
                        this.mMoreRedCountView.setBackgroundResource(R.drawable.message_door_red_bg1);
                    }
                } else if (i2 > 9) {
                    if (i2 > 99) {
                        this.mMoreRedCountView.setText("99+");
                    } else {
                        this.mMoreRedCountView.setText(String.valueOf(i2));
                    }
                    this.mMoreRedCountView.setBackgroundResource(R.drawable.message_door_red_bg2);
                } else {
                    this.mMoreRedCountView.setText(String.valueOf(i2));
                    this.mMoreRedCountView.setBackgroundResource(R.drawable.message_door_red_bg1);
                }
                setRedPointVisibility(false);
                return;
            } else {
                TextView textView = this.mOutMsgCountView;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                TextView textView2 = this.mMoreRedCountView;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                    return;
                }
                return;
            }
        }
        TextView textView3 = this.mOutMsgCountView;
        if (textView3 != null) {
            textView3.setVisibility(8);
        }
        TextView textView4 = this.mMoreRedCountView;
        if (textView4 != null) {
            textView4.setVisibility(8);
        }
    }

    public void setNaviAndStatusBarHeightViewAlpha(float f2) {
        setNaviBgAlpha(f2);
        setStatusBarHeightViewAlpha(f2);
    }

    public void setNaviBackground(final int i2, String str, String str2) {
        final int i3;
        try {
            i3 = Color.parseColor(str);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            i3 = Integer.MAX_VALUE;
        }
        if (!TextUtils.isEmpty(str2)) {
            JDImageUtils.loadImage(str2, new JDImageLoadingListener() { // from class: com.jingdong.common.widget.NavigatorHolder.4
                {
                    NavigatorHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str3, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                    NavigatorHolder.this.titleBg.setImageBitmap(bitmap);
                    NavigatorHolder.this.updateNaviIconAndTitle(i2);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                    if (i3 != Integer.MAX_VALUE) {
                        NavigatorHolder.this.titleBg.setImageDrawable(new ColorDrawable(i3));
                        NavigatorHolder.this.updateNaviIconAndTitle(i2);
                    }
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str3, View view) {
                }
            });
        } else if (i3 != Integer.MAX_VALUE) {
            this.titleBg.setImageDrawable(new ColorDrawable(i3));
            updateNaviIconAndTitle(i2);
        }
    }

    public void setNaviBg(Drawable drawable) {
        SimpleDraweeView simpleDraweeView = this.titleBg;
        if (simpleDraweeView == null || drawable == null) {
            return;
        }
        simpleDraweeView.setImageDrawable(drawable);
    }

    @TargetApi(11)
    public void setNaviBgAlpha(float f2) {
        SimpleDraweeView simpleDraweeView = this.titleBg;
        if (simpleDraweeView != null) {
            simpleDraweeView.setAlpha(f2);
        }
    }

    public void setNaviFollowAdapter(BaseNaviFollowAdapter baseNaviFollowAdapter) {
        this.naviFollowAdapter = baseNaviFollowAdapter;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setNaviImmersive(boolean r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.widget.NavigatorHolder.setNaviImmersive(boolean, java.lang.String):void");
    }

    public void setNaviListener(NaviListener naviListener) {
        this.naviListener = naviListener;
    }

    public void setNaviStateListener(NaviStateListener naviStateListener) {
        this.naviStateListener = naviStateListener;
    }

    public void setNaviVisible(int i2) {
        View findViewById = this.parent.findViewById(R.id.rl_navi_content);
        if (findViewById != null) {
            findViewById.setVisibility(i2);
        }
    }

    public void setNaviVisibleExcludeStatus(boolean z) {
        if (!z || this.naviExcludeStatusGone) {
            try {
                ViewParent parent = this.statusBarHeightView.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup == null || viewGroup.getChildCount() <= 0) {
                    return;
                }
                boolean z2 = false;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    View childAt = viewGroup.getChildAt(i2);
                    if (childAt != this.statusBarHeightView) {
                        if (z) {
                            if (childAt.getVisibility() != 0) {
                                childAt.setVisibility(0);
                            }
                            this.naviExcludeStatusGone = false;
                        } else {
                            if (childAt.getVisibility() != 8) {
                                childAt.setVisibility(8);
                            }
                            this.naviExcludeStatusGone = true;
                        }
                        z2 = true;
                    }
                }
                if (z2) {
                    setJdThemeTitleRootViewHeight();
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public void setOutSideBtnChangeListener(OnChangeListener onChangeListener) {
        this.onSecondBtnChangeListener = onChangeListener;
    }

    public void setOutSideShow(boolean z) {
        this.outSideShow = z;
    }

    public void setRedPointVisibility(boolean z) {
        this.mIsShowRedPoint = z;
        if (z) {
            ensureMoreBtn();
            View view = this.mMoreRedPointView;
            if (view != null) {
                view.setVisibility(0);
            }
            TextView textView = this.mOutMsgCountView;
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = this.mMoreRedCountView;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            this.mShowMsgRedPointNum = 0;
            return;
        }
        View view2 = this.mMoreRedPointView;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    public void setRightTextViewState(boolean z) {
        ensureRightLayout();
        if (z) {
            this.rightTextView.setVisibility(0);
        } else {
            this.rightTextView.setVisibility(8);
        }
    }

    public void setShareBtnState(boolean z, boolean z2) {
        this.isShareGift = z2;
        if (this.naviBtnStates == null) {
            this.naviBtnStates = new NaviBtnStates(this);
        }
        this.naviBtnStates.push(new NaviEntity("share", z, true, z2));
        updateStates(this.naviBtnStates);
        refreshOutsideButtonStyle();
    }

    public void setStatusBarAlwaysTransparent(boolean z) {
        this.statusbarAlwaysTransparent = z;
        if (z) {
            this.statusBarHeightView.setVisibility(0);
            this.statusBarHeightView.setBackgroundResource(R.color.status_bar_bg);
            return;
        }
        this.statusBarHeightView.setBackgroundColor(!this.darkMode ? WebNaviSettings.bgWhiteModeColor : WebNaviSettings.bgDarkModeColor);
    }

    public void setStatusBarAndTitleBg(int i2, Drawable drawable, Drawable drawable2) {
        updateNaviIconAndTitle(i2);
        setStatusBarBg(drawable);
        setNaviBg(drawable2);
    }

    public void setStatusBarBackground(String str) {
        int i2;
        try {
            i2 = Color.parseColor(str);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            i2 = Integer.MAX_VALUE;
        }
        if (i2 != Integer.MAX_VALUE) {
            setStatusBarBg(new ColorDrawable(i2));
        }
    }

    public void setStatusBarBg(Drawable drawable) {
        View view = this.statusBarHeightView;
        if (view == null || drawable == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
    }

    @TargetApi(11)
    public void setStatusBarHeightViewAlpha(float f2) {
        View view = this.statusBarHeightView;
        if (view != null) {
            view.setAlpha(f2);
        }
    }

    public void setStatusBarHeightViewVisibility(int i2) {
        View view = this.statusBarHeightView;
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    public void setStatusBarSafeBgColor() {
        View view = this.statusBarHeightView;
        if (view != null) {
            view.setBackgroundResource(R.color.status_bar_bg);
        }
    }

    public void setTitleBackBtnVisible(boolean z) {
        if (z) {
            this.mTitleBack.setVisibility(0);
        } else {
            this.mTitleBack.setVisibility(8);
        }
    }

    public void setTitleImg(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            ensureTitle();
            JSONObject jSONObject = new JSONObject(str);
            if (YES.equals(jSONObject.optString(IS_SHOW))) {
                final String optString = jSONObject.optString("imageUrl");
                if (!TextUtils.isEmpty(optString)) {
                    this.titleText.setVisibility(8);
                    this.titleImg.setVisibility(0);
                    JDImageUtils.displayImage(optString, this.titleImg, null, false, new JDImageLoadingListener() { // from class: com.jingdong.common.widget.NavigatorHolder.5
                        {
                            NavigatorHolder.this = this;
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str2, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                            NavigatorHolder.this.titleImg.setTag(optString);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                            OKLog.d(NavigatorHolder.TAG, "onLoadingFailed: " + jDFailReason.getCause());
                            NavigatorHolder.this.titleImg.setVisibility(8);
                            NavigatorHolder.this.titleText.setVisibility(0);
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str2, View view) {
                        }
                    }, null);
                }
            }
            if ("N".equals(jSONObject.optString(IS_SHOW))) {
                this.titleImg.setVisibility(8);
                this.titleImgTemp.setVisibility(8);
                this.titleText.setVisibility(0);
            }
        } catch (JSONException e2) {
            OKLog.e(TAG, e2);
            SimpleDraweeView simpleDraweeView = this.titleImg;
            if (simpleDraweeView != null) {
                simpleDraweeView.setVisibility(8);
            }
            SimpleDraweeView simpleDraweeView2 = this.titleImgTemp;
            if (simpleDraweeView2 != null) {
                simpleDraweeView2.setVisibility(8);
            }
            TextView textView = this.titleText;
            if (textView != null) {
                textView.setVisibility(0);
            }
        }
    }

    public void setTitleImgWidth(int i2) {
        ensureTitle();
        ViewGroup.LayoutParams layoutParams = this.titleImg.getLayoutParams();
        layoutParams.width = i2;
        this.titleImg.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.titleImgTemp.getLayoutParams();
        layoutParams2.width = i2;
        this.titleImgTemp.setLayoutParams(layoutParams2);
    }

    public void setTitleImgWidthDefault() {
        ensureTitle();
        int dip2px = DPIUtil.dip2px(100.0f);
        ViewGroup.LayoutParams layoutParams = this.titleImg.getLayoutParams();
        layoutParams.width = dip2px;
        this.titleImg.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.titleImgTemp.getLayoutParams();
        layoutParams2.width = dip2px;
        this.titleImgTemp.setLayoutParams(layoutParams2);
    }

    public void setTitleTextOrImg(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            ensureTitle();
            this.titleText.setText(str);
            this.titleText.setVisibility(0);
            setTitleImg("{\"isShow\":\"N\"}");
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        setTitleImg("{\"isShow\":\"Y\",\"imageUrl\":\"" + str2 + "\"}");
    }

    public void setTopLogo(String str, View.OnClickListener onClickListener) {
        if (this.topLogoGrayEnable) {
            if (str != null) {
                try {
                    if (!TextUtils.isEmpty(str)) {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.length() == 0) {
                            hideTopLogo();
                            return;
                        }
                        String optString = jSONObject.optString("img");
                        if (TextUtils.isEmpty(optString)) {
                            hideTopLogo();
                            return;
                        }
                        int optInt = jSONObject.optInt(JshopConst.JSHOP_PROMOTIO_W, 0);
                        int optInt2 = jSONObject.optInt(JshopConst.JSHOP_PROMOTIO_H, 0);
                        int i2 = 30;
                        if (optInt2 <= 0) {
                            optInt2 = 30;
                        }
                        int i3 = 90;
                        if (optInt <= 0) {
                            optInt = 90;
                        }
                        if ("1".equals(jSONObject.optString("unfreezeLimit"))) {
                            i3 = optInt;
                            if (optInt2 > 49) {
                                optInt2 = 49;
                            }
                        } else {
                            if (optInt2 <= 30) {
                                i2 = optInt2;
                            }
                            if (optInt <= 90) {
                                i3 = optInt;
                            }
                            optInt2 = i2;
                        }
                        showTopLogo(DPIUtil.dip2px(i3), DPIUtil.dip2px(optInt2), optString, onClickListener);
                        return;
                    }
                } catch (JSONException e2) {
                    if (OKLog.E) {
                        OKLog.e(TAG, e2);
                        return;
                    }
                    return;
                }
            }
            hideTopLogo();
        }
    }

    @Deprecated
    public void setX5TipVisible(boolean z) {
    }

    public void showMoreBtn() {
        this.isShowMoreBtn = true;
    }

    public void showThirdBtn(View view) {
        ensureRightLayout();
        RelativeLayout relativeLayout = this.thirdSeat;
        if (relativeLayout == null || view == null) {
            return;
        }
        relativeLayout.removeAllViews();
        this.thirdSeat.addView(view);
        this.thirdSeat.setVisibility(0);
        this.firstSeat.setVisibility(8);
        RelativeLayout relativeLayout2 = this.secondSeat;
        if (relativeLayout2 == null || relativeLayout2.getChildCount() != 0) {
            return;
        }
        this.secondSeat.setVisibility(8);
    }

    public void unRegisterTitleThemeChangeListener() {
        IThemeChangeListener iThemeChangeListener = this.themeChangeListener;
        if (iThemeChangeListener != null) {
            ThemeTitleHelper.removeThemeTitleChangeListener(iThemeChangeListener);
            this.themeChangeListener = null;
            this.statusBarHeightView.setVisibility(0);
            defaultTitleBgHeight();
        }
    }

    public synchronized void updateNaviIconAndTitle(int i2) {
        updateNaviIconAndTitle(i2, false);
    }

    public synchronized void updateNaviIconAndTitleColor(int i2) {
        if (this.naviIconStyle == i2) {
            return;
        }
        this.naviIconStyle = i2;
        changeIconStyle(this.bnMore);
        changeIconStyle(this.mTitleBack);
        changeIconStyle(this.closeIcon);
        TextView textView = this.titleText;
        if (textView != null) {
            if (this.naviIconStyle == 2) {
                textView.setTextColor(WebNaviSettings.titleDarkModeColor);
            } else {
                textView.setTextColor(WebNaviSettings.titleWhiteModeColor);
            }
        }
        refreshOutsideButtonStyle();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void updateStates(final NaviBtnStates naviBtnStates) {
        char c2;
        View view;
        boolean z;
        NaviEntity naviEntity;
        NaviEntity naviEntity2;
        ensureRightLayout();
        if (this.onSecondBtnChangeListener != null) {
            addCustomView(null, null);
        }
        NaviEntity naviEntity3 = naviBtnStates.outsideIcon;
        if (naviEntity3 != null) {
            String str = naviEntity3.type;
            str.hashCode();
            boolean z2 = true;
            switch (str.hashCode()) {
                case -1349088399:
                    if (str.equals(NaviEntity.TYPE_CUSTOM)) {
                        c2 = 0;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -1268958287:
                    if (str.equals("follow")) {
                        c2 = 1;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -906336856:
                    if (str.equals("search")) {
                        c2 = 2;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case -485371922:
                    if (str.equals(NaviEntity.TYPE_HOME)) {
                        c2 = 3;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 3046176:
                    if (str.equals("cart")) {
                        c2 = 4;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 109400031:
                    if (str.equals("share")) {
                        c2 = 5;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 790297723:
                    if (str.equals(NaviEntity.TYPE_CLEAR)) {
                        c2 = 6;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                case 954925063:
                    if (str.equals("message")) {
                        c2 = 7;
                        break;
                    }
                    c2 = '\uffff';
                    break;
                default:
                    c2 = '\uffff';
                    break;
            }
            switch (c2) {
                case 0:
                    ensureCustomBtn();
                    this.custom1.setTag(naviBtnStates.outsideIcon);
                    if (!TextUtils.isEmpty(naviBtnStates.outsideIcon.title)) {
                        this.custom1.setContentDescription(naviBtnStates.outsideIcon.title);
                    }
                    view = this.custom1;
                    z = false;
                    break;
                case 1:
                    if (this.mFollowView == null && this.naviFollowAdapter != null) {
                        resetFollowParams();
                        this.mFollowView = this.naviFollowAdapter.onCreateFollowView(DPIUtil.dip2px(59.0f), DPIUtil.dip2px(22.0f), this.naviFollowAdapter.style);
                    }
                    view = this.mFollowView;
                    z = true;
                    break;
                case 2:
                    ensureSearchBtn();
                    view = this.searchIcon;
                    z = false;
                    break;
                case 3:
                    ensureHomeBtn();
                    view = this.homeIcon;
                    z = false;
                    break;
                case 4:
                    ensureCartBtn();
                    view = this.cartContainer;
                    z = false;
                    break;
                case 5:
                    ensureShareBtn();
                    ImageView imageView = this.shareIcon;
                    if (imageView != null && (imageView.getParent() instanceof View)) {
                        view = (View) this.shareIcon.getParent();
                        z = false;
                        break;
                    }
                    view = null;
                    z = false;
                    break;
                case 6:
                    this.isShowMoreBtn = true;
                    view = null;
                    z = false;
                    break;
                case 7:
                    ensureMessageBtn();
                    view = this.msgContainer;
                    z = false;
                    break;
                default:
                    view = null;
                    z = false;
                    break;
            }
            NaviEntity naviEntity4 = naviBtnStates.outsideIcon;
            View view2 = naviEntity4.expand ? null : view;
            OnChangeListener onChangeListener = this.onSecondBtnChangeListener;
            if (onChangeListener != null) {
                onChangeListener.onChange(view2 == 0, naviEntity4);
            }
            if (this.mFollowView != null && (naviEntity2 = naviBtnStates.outsideIcon) != null && !"follow".equals(naviEntity2.type)) {
                resetFollowView();
            }
            if (!z || this.secondSeat.getChildAt(0) == null || this.secondSeat.getChildAt(0) != view2) {
                this.secondSeat.removeAllViews();
                z2 = false;
            }
            if (view2 != null) {
                NaviEntity naviEntity5 = naviBtnStates.outsideIcon;
                if (naviEntity5 != null && "follow".equalsIgnoreCase(naviEntity5.type)) {
                    ViewGroup.LayoutParams layoutParams = this.secondSeat.getLayoutParams();
                    if (layoutParams != null) {
                        layoutParams.width = -2;
                        layoutParams.height = -1;
                        this.secondSeat.setLayoutParams(layoutParams);
                    }
                    ensureTitle();
                    this.titleText.setMaxWidth(this.dp160);
                    RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                    layoutParams2.addRule(15);
                    view2.setLayoutParams(layoutParams2);
                } else {
                    resetSecondSeatSize();
                    view2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
                }
                if (!z2) {
                    this.secondSeat.addView(view2);
                }
                if (this.naviFollowAdapter != null && (naviEntity = naviBtnStates.outsideIcon) != null && "follow".equalsIgnoreCase(naviEntity.type)) {
                    if (!TextUtils.equals(this.naviFollowAdapter.channelId, naviBtnStates.outsideIcon.channelId)) {
                        this.naviFollowAdapter.channelId = naviBtnStates.outsideIcon.channelId;
                        this.naviFollowAdapter.onChannelIdChanged(this.mFollowView, naviBtnStates.outsideIcon.channelId);
                    }
                    int i2 = this.naviFollowAdapter.style;
                    int i3 = this.naviIconStyle;
                    if (i2 != i3) {
                        this.naviFollowAdapter.style = i3;
                        this.naviFollowAdapter.onStyleChanged(this.mFollowView, this.naviIconStyle);
                    }
                }
                if (view2.getTag() != null && (view2.getTag() instanceof NaviEntity) && NaviEntity.TYPE_CUSTOM.equals(((NaviEntity) view2.getTag()).type)) {
                    JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
                    int i4 = R.drawable.common_nav_default_icon;
                    jDDisplayImageOptions.showImageOnFail(i4);
                    jDDisplayImageOptions.showImageOnLoading(i4);
                    jDDisplayImageOptions.showImageForEmptyUri(i4);
                    NaviEntity naviEntity6 = naviBtnStates.outsideIcon;
                    JDImageUtils.displayImage(naviEntity6 != null ? naviEntity6.iconUrl : "", this.custom1, jDDisplayImageOptions, new JDSimpleImageLoadingListener() { // from class: com.jingdong.common.widget.NavigatorHolder.8
                        {
                            NavigatorHolder.this = this;
                        }

                        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str2, View view3, JDFailReason jDFailReason) {
                            super.onLoadingFailed(str2, view3, jDFailReason);
                            NavigatorHolder.this.secondSeat.removeAllViews();
                            naviBtnStates.outsideIcon = null;
                        }
                    });
                }
            }
        } else {
            this.secondSeat.removeAllViews();
        }
        if (this.secondSeat.getChildCount() == 0) {
            resetSecondSeatSize();
        }
        if (this.isShowMoreBtn) {
            this.firstSeat.setVisibility(0);
            this.secondSeat.setVisibility(0);
        } else {
            this.firstSeat.setVisibility(8);
        }
        this.firstSeat.removeAllViews();
        this.thirdSeat.removeAllViews();
        this.thirdSeat.setVisibility(8);
        if (this.isShowMoreBtn) {
            if (naviBtnStates.insideIcons.size() > 0) {
                ensureMoreBtn();
                this.firstSeat.addView(this.webMoreContainer);
                this.popupItemEntities = new ArrayList<>();
                Iterator<NaviEntity> it = naviBtnStates.insideIcons.iterator();
                while (it.hasNext()) {
                    this.popupItemEntities.add(it.next());
                }
            }
            this.firstSeat.setVisibility(naviBtnStates.insideIcons.isEmpty() ? 8 : 0);
        }
        setMsgRedPointNum(this.mShowMsgRedPointNum, this.maxNineOrNineNine);
    }

    public Boolean updateStatusBarStyle(int i2) {
        if (this.statusTextStyle == i2) {
            return null;
        }
        Context context = this.context;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null) {
            return null;
        }
        this.statusTextStyle = i2;
        if (i2 == 2) {
            return Boolean.valueOf(UnStatusBarTintUtil.setStatusBarDarkMode(activity));
        }
        return Boolean.valueOf(UnStatusBarTintUtil.setStatusBarLightMode(activity));
    }

    public void updateTitleBg(int i2) {
        if (this.mTitleAlpha == i2) {
            return;
        }
        this.mTitleAlpha = i2;
        if (this.immersive) {
            if (TextUtils.isEmpty(i2 >= 1 ? this.titleImageUrl1 : this.titleImageUrl2)) {
                setTitleImg("{\"isShow\":\"N\"}");
            } else {
                setTitleTextOrImg(null, i2 >= 1 ? this.titleImageUrl1 : this.titleImageUrl2);
            }
        }
    }

    public NavigatorHolder(Context context, ViewGroup viewGroup, boolean z) {
        this(context, viewGroup, z, false);
    }

    public NavigatorHolder(Context context, ViewGroup viewGroup, boolean z, boolean z2) {
        this.padding15 = DPIUtil.dip2px(15.0f);
        this.padding6 = DPIUtil.dip2px(6.0f);
        this.padding10 = DPIUtil.dip2px(10.0f);
        this.width40 = DPIUtil.dip2px(40.0f);
        this.height49 = DPIUtil.dip2px(49.0f);
        this.dp30 = DPIUtil.dip2px(30.0f);
        this.dp35 = DPIUtil.dip2px(35.0f);
        this.dp160 = DPIUtil.dip2px(160.0f);
        this.dp225 = DPIUtil.dip2px(225.0f);
        this.maxNineOrNineNine = true;
        this.mIsShowRedPoint = false;
        this.mShowMsgRedPointNum = -1;
        this.closeNeedShow = false;
        this.isShowMoreBtn = true;
        this.naviIconStyle = 1;
        this.statusTextStyle = 1;
        this.isShareGift = false;
        this.enableStatusTint = false;
        this.statusbarAlwaysTransparent = false;
        this.immersive = false;
        this.mTitleAlpha = 0;
        this.outSideShow = true;
        this.needTransitionForTitle = false;
        this.titleTransitioning = false;
        this.darkMode = false;
        this.naviExcludeStatusGone = false;
        this.context = context;
        this.parent = viewGroup;
        this.darkMode = z;
        this.naviIconStyle = z ? 2 : 1;
        this.whiteColorDrawable = new ColorDrawable(WebNaviSettings.bgWhiteModeColor);
        this.darkColorDrawable = new ColorDrawable(WebNaviSettings.bgDarkModeColor);
        GraySwitch.SwitchProvider switchProvider = GraySwitch.provider;
        this.topLogoGrayEnable = switchProvider != null && switchProvider.topLogoEnable();
        JdThemeTitle jdThemeTitle = (JdThemeTitle) viewGroup.findViewById(R.id.common_navi_jd_theme_title);
        this.jdThemeTitle = jdThemeTitle;
        View statusBar = jdThemeTitle.getStatusBar();
        this.statusBarHeightView = statusBar;
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        boolean isEnable = UnStatusBarTintUtil.isEnable(context);
        this.enableStatusTint = isEnable;
        if (isEnable) {
            this.statusBarHeightView.setVisibility(0);
            layoutParams.height = UnStatusBarTintUtil.getStatusBarHeight(context);
        } else {
            this.statusBarHeightView.setVisibility(8);
            layoutParams.height = 0;
        }
        this.statusBarHeightView.setLayoutParams(layoutParams);
        this.statusBarHeightView.setBackgroundColor(!z ? WebNaviSettings.bgWhiteModeColor : WebNaviSettings.bgDarkModeColor);
        this.jdThemeTitle.setStatusBarHint(true);
        ViewGroup viewGroup2 = (ViewGroup) this.parent.findViewById(R.id.common_navi_root);
        this.naviRootView = viewGroup2;
        if (viewGroup2 == null) {
            this.naviRootView = (ViewGroup) this.parent.findViewById(R.id.app_webview_title);
        }
        if (this.jdThemeTitle.getTitleBgImageView() instanceof SimpleDraweeView) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) this.jdThemeTitle.getTitleBgImageView();
            this.titleBg = simpleDraweeView;
            simpleDraweeView.setBackgroundDrawable(!z ? this.whiteColorDrawable : this.darkColorDrawable);
            defaultTitleBgHeight();
            this.titleBg.setScaleType(ImageView.ScaleType.FIT_XY);
            this.inflater = LayoutInflater.from(context);
            ImageView left1ImageView = this.jdThemeTitle.getLeft1ImageView();
            this.mTitleBack = left1ImageView;
            left1ImageView.setId(R.id.title_back);
            this.mTitleBack.setOnClickListener(this);
            this.mTitleBack.setImageDrawable(WebNaviUtil.createIconDrawable(context, com.jingdong.wireless.iconfont.R.string.jdif_common_fanhui).color(!z ? WebNaviSettings.iconWhiteModeColor : WebNaviSettings.iconDarkModeColor));
            this.mTitleBack.setVisibility(0);
            ViewGroup.LayoutParams layoutParams2 = this.mTitleBack.getLayoutParams();
            layoutParams2.width = this.width40;
            layoutParams2.height = this.height49;
            if (layoutParams2 instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
                layoutParams3.addRule(9);
                layoutParams3.addRule(15);
                layoutParams3.rightMargin = DPIUtil.dip2px(1.0f);
                layoutParams3.leftMargin = 0;
            }
            ImageView imageView = this.mTitleBack;
            int i2 = this.padding6;
            int i3 = this.padding15;
            imageView.setPadding(i2, i3, i2, i3);
            this.mTitleBack.setScaleType(ImageView.ScaleType.FIT_START);
            this.mTitleBack.setContentDescription(context.getString(R.string.contentDescription_back));
            if (z2) {
                return;
            }
            initViewNow();
            return;
        }
        throw new RuntimeException("JdThemeTitle's title bg ImageView must be a SimpleDraweeView");
    }

    public synchronized void updateNaviIconAndTitle(int i2, boolean z) {
        if (!z) {
            if (this.naviIconStyle == i2) {
                return;
            }
        }
        this.naviIconStyle = i2;
        changeIconStyle(this.bnMore);
        changeIconStyle(this.mTitleBack);
        changeIconStyle(this.closeIcon);
        if (this.naviIconStyle == 2) {
            TextView textView = this.titleText;
            if (textView != null) {
                textView.setTextColor(WebNaviSettings.titleDarkModeColor);
            }
            if (this.immersive) {
                if (TextUtils.isEmpty(this.titleImageUrl2)) {
                    setTitleImg("{\"isShow\":\"N\"}");
                } else {
                    setTitleTextOrImg(null, this.titleImageUrl2);
                }
            }
        } else {
            TextView textView2 = this.titleText;
            if (textView2 != null) {
                textView2.setTextColor(WebNaviSettings.titleWhiteModeColor);
            }
            if (this.immersive) {
                if (TextUtils.isEmpty(this.titleImageUrl1)) {
                    setTitleImg("{\"isShow\":\"N\"}");
                } else {
                    setTitleTextOrImg(null, this.titleImageUrl1);
                }
            }
        }
        refreshOutsideButtonStyle();
    }

    /* loaded from: classes12.dex */
    public static class NaviEntity implements JDMenuWindow.ISubMenu {
        @Deprecated
        public static final String TYPE_CALENDAR = "calendar";
        public static final String TYPE_CART = "cart";
        public static final String TYPE_CLEAR = "clear_js";
        public static final String TYPE_CUSTOM = "custom";
        public static final String TYPE_FEEDBACK = "feedback";
        public static final String TYPE_FOLLOW = "follow";
        public static final String TYPE_HIDEMORE = "hidemore";
        public static final String TYPE_HOME = "homepage";
        public static final String TYPE_MESSAGE = "message";
        public static final String TYPE_SEARCH = "search";
        public static final String TYPE_SHARE = "share";
        public String channelId;
        public int count;
        public boolean expand;
        public String ext;
        public String iconUrl;
        public boolean isOutSide;
        public boolean isShareGift;
        public String jump;
        public boolean show;
        public boolean showRedPoint;
        public String text;
        public String title;
        public String type;
        public Uri uri;

        public NaviEntity() {
            this.show = false;
            this.isShareGift = false;
            this.iconUrl = "";
            this.title = "";
            this.jump = "";
            this.channelId = "";
            this.text = "";
            this.expand = false;
            this.ext = "";
        }

        public Integer getOrder() {
            String str = this.type;
            str.hashCode();
            int i2 = 7;
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -1349088399:
                    if (str.equals(TYPE_CUSTOM)) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -1268958287:
                    if (str.equals("follow")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -906336856:
                    if (str.equals("search")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case -485371922:
                    if (str.equals(TYPE_HOME)) {
                        c2 = 3;
                        break;
                    }
                    break;
                case -191501435:
                    if (str.equals("feedback")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 3046176:
                    if (str.equals("cart")) {
                        c2 = 5;
                        break;
                    }
                    break;
                case 109400031:
                    if (str.equals("share")) {
                        c2 = 6;
                        break;
                    }
                    break;
                case 954925063:
                    if (str.equals("message")) {
                        c2 = 7;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    i2 = 6;
                    break;
                case 1:
                    i2 = 8;
                    break;
                case 2:
                    i2 = 3;
                    break;
                case 3:
                    i2 = 1;
                    break;
                case 4:
                    i2 = 5;
                    break;
                case 5:
                    i2 = 4;
                    break;
                case 6:
                    break;
                case 7:
                    i2 = 0;
                    break;
                default:
                    i2 = Integer.MAX_VALUE;
                    break;
            }
            return Integer.valueOf(i2);
        }

        protected static String getRezString(@StringRes int i2) {
            return JdSdk.getInstance().getApplication().getResources().getString(i2);
        }

        public static NaviEntity parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            NaviEntity naviEntity = new NaviEntity();
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("type");
                char c2 = '\uffff';
                switch (optString.hashCode()) {
                    case -1349088399:
                        if (optString.equals(TYPE_CUSTOM)) {
                            c2 = '\b';
                            break;
                        }
                        break;
                    case -1268958287:
                        if (optString.equals("follow")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case -906336856:
                        if (optString.equals("search")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -485371922:
                        if (optString.equals(TYPE_HOME)) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -191501435:
                        if (optString.equals("feedback")) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case 3046176:
                        if (optString.equals("cart")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 109400031:
                        if (optString.equals("share")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case 790297723:
                        if (optString.equals(TYPE_CLEAR)) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case 954925063:
                        if (optString.equals("message")) {
                            c2 = 3;
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        naviEntity.title = getRezString(R.string.webview_navi_home);
                        naviEntity.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_shouye);
                        break;
                    case 1:
                        naviEntity.title = getRezString(R.string.webview_navi_cart);
                        naviEntity.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_gouwuche);
                        break;
                    case 2:
                        naviEntity.title = getRezString(R.string.webview_navi_search);
                        naviEntity.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_sousuo);
                        break;
                    case 3:
                        naviEntity.title = getRezString(R.string.webview_navi_message);
                        naviEntity.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_xiaoxi);
                        break;
                    case 4:
                        naviEntity.title = getRezString(R.string.webview_navi_share);
                        naviEntity.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_fenxiang);
                        break;
                    case 5:
                        break;
                    case 6:
                        naviEntity.title = getRezString(R.string.webview_navi_feedback);
                        naviEntity.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_fankui);
                        break;
                    case 7:
                        JSONObject optJSONObject = jSONObject.optJSONObject("params");
                        naviEntity.channelId = optJSONObject != null ? optJSONObject.optString("collectionId") : null;
                        break;
                    default:
                        naviEntity.iconUrl = jSONObject.optString("icon").trim();
                        naviEntity.jump = jSONObject.optString("jump").trim();
                        naviEntity.title = jSONObject.optString("title").trim();
                        break;
                }
                JSONObject optJSONObject2 = jSONObject.optJSONObject("params");
                if (optJSONObject2 != null) {
                    naviEntity.text = optJSONObject2.optString("text");
                    naviEntity.expand = "1".equals(optJSONObject2.optString("expand"));
                    naviEntity.ext = optJSONObject2.optString("ext");
                }
                naviEntity.type = jSONObject.optString("type");
                naviEntity.show = "show".equals(jSONObject.optString(ViewProps.DISPLAY));
                naviEntity.isOutSide = "outside".equals(jSONObject.optString("position"));
                return naviEntity;
            } catch (Exception e2) {
                OKLog.e(NavigatorHolder.TAG, e2);
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof NaviEntity)) {
                NaviEntity naviEntity = (NaviEntity) obj;
                if (this.type.equals(naviEntity.type) && this.iconUrl.equals(naviEntity.iconUrl) && this.title.equals(naviEntity.title) && this.jump.equals(naviEntity.jump)) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public Uri getIconUri() {
            return this.uri;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public String getImgUrl() {
            return this.iconUrl;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public String getItemTitle() {
            return this.title;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public int getMessageCount() {
            return this.count;
        }

        public void init(String str) {
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case -906336856:
                    if (str.equals("search")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case -485371922:
                    if (str.equals(TYPE_HOME)) {
                        c2 = 1;
                        break;
                    }
                    break;
                case -191501435:
                    if (str.equals("feedback")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 3046176:
                    if (str.equals("cart")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 109400031:
                    if (str.equals("share")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 954925063:
                    if (str.equals("message")) {
                        c2 = 5;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    this.title = getRezString(R.string.webview_navi_search);
                    this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_sousuo);
                    return;
                case 1:
                    this.title = getRezString(R.string.webview_navi_home);
                    this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_shouye);
                    return;
                case 2:
                    this.title = getRezString(R.string.webview_navi_feedback);
                    this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_fankui);
                    this.uri = CalorieImageUtil.getRemoteImageUriSync("112", "2302");
                    return;
                case 3:
                    this.title = getRezString(R.string.webview_navi_cart);
                    this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_gouwuche);
                    return;
                case 4:
                    this.title = getRezString(R.string.webview_navi_share);
                    this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_fenxiang);
                    return;
                case 5:
                    this.title = getRezString(R.string.webview_navi_message);
                    this.iconUrl = NavigatorHolder.SCHEME_ICONFONT + getRezString(com.jingdong.wireless.iconfont.R.string.jdif_common_xiaoxi);
                    return;
                default:
                    return;
            }
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public boolean isRedPointVisible() {
            return this.showRedPoint;
        }

        @Override // com.jingdong.common.widget.JDMenuWindow.ISubMenu
        public boolean isSameWith(JDMenuWindow.ISubMenu iSubMenu) {
            return iSubMenu != null && TextUtils.equals(getImgUrl(), iSubMenu.getImgUrl()) && TextUtils.equals(getItemTitle(), iSubMenu.getItemTitle()) && isRedPointVisible() == iSubMenu.isRedPointVisible() && getMessageCount() == iSubMenu.getMessageCount();
        }

        public NaviEntity(String str) {
            this.show = false;
            this.isShareGift = false;
            this.iconUrl = "";
            this.title = "";
            this.jump = "";
            this.channelId = "";
            this.text = "";
            this.expand = false;
            this.ext = "";
            this.type = str;
            init(str);
        }

        public NaviEntity(String str, boolean z, boolean z2) {
            this.show = false;
            this.isShareGift = false;
            this.iconUrl = "";
            this.title = "";
            this.jump = "";
            this.channelId = "";
            this.text = "";
            this.expand = false;
            this.ext = "";
            this.type = str;
            this.show = z;
            this.isOutSide = z2;
            init(str);
        }

        public NaviEntity(String str, boolean z, boolean z2, boolean z3) {
            this.show = false;
            this.isShareGift = false;
            this.iconUrl = "";
            this.title = "";
            this.jump = "";
            this.channelId = "";
            this.text = "";
            this.expand = false;
            this.ext = "";
            this.type = str;
            this.show = z;
            this.isOutSide = z2;
            this.isShareGift = z3;
            init(str);
        }
    }

    public void setMsgRedPointNum(int i2) {
        setMsgRedPointNum(i2, true);
    }
}
