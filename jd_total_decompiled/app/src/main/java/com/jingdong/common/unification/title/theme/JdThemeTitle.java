package com.jingdong.common.unification.title.theme;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.un.business.R;
import com.jd.lib.un.business.widget.a;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.common.unification.customtheme.entity.ImageInfoEntity;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes6.dex */
public class JdThemeTitle extends RelativeLayout {
    private static final int MAX_RED_NUM = 99;
    private static final String TAG = JdThemeTitle.class.getSimpleName();
    private boolean autoDarkMode;
    private View centerView;
    private boolean customOpen;
    private FrameLayout footView;
    private int footViewHeight;
    private boolean isDarkMode;
    private boolean isDefaultTheme;
    private boolean isGrayBg;
    private boolean isLoaded;
    private String left1DrawableId;
    private String left2DrawableId;
    private int leftTv1TextResource;
    private int leftTv2TextResource;
    private FrameLayout mCenterLayout;
    private Context mContext;
    private ImageView mLeftIv1;
    private ImageView mLeftIv2;
    private FrameLayout mLeftLayout;
    private TextView mLeftTv1;
    private TextView mLeftTv2;
    private String mModuleId;
    private ImageView mRedPointIv;
    private FrameLayout mRedPointLayout;
    private TextView mRedPointNumTv;
    private TextView mRedPointTv;
    private ImageView mRedpointIcon;
    private ImageView mRightIv1;
    private ImageView mRightIv2;
    private FrameLayout mRightLayout;
    private TextView mRightTv1;
    private TextView mRightTv2;
    private ImageView mTitleBgIv;
    private TextView mTitleTv;
    private String redPointDrawableId;
    private String right1DrawableId;
    private String right2DrawableId;
    private int rightTv1TextResource;
    private int rightTv2TextResource;
    private RelativeLayout rootView;
    private boolean showDefaultBg;
    private View statusBar;
    private boolean statusBarColorStyleEnable;
    private boolean statusBarHint;
    private IThemeChangeListener themeChangeListener;
    private boolean themeTextColorEnable;
    private FrameLayout titleBg2Layout;
    private ImageView titleIcon;
    private String titleIconId;
    private RelativeLayout titleLayout;
    private int titleText;
    private int titleTextBg;

    public JdThemeTitle(Context context) {
        this(context, null);
    }

    private void displayTitleBg(boolean z, boolean z2, String str, ImageView imageView, final ILoadBgCallback iLoadBgCallback) {
        this.isDefaultTheme = true;
        if (imageView == null) {
            if (iLoadBgCallback != null) {
                iLoadBgCallback.loadBack();
            }
        } else if (UnCustomThemeHelper.getInstance().customThemeEnable() && !TextUtils.isEmpty(str)) {
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            if (z2) {
                int i2 = R.color.un_theme_title_dark_bg;
                createSimple.showImageForEmptyUri(i2);
                createSimple.showImageOnFail(i2);
            } else {
                int i3 = R.drawable.common_title_background;
                createSimple.showImageForEmptyUri(i3);
                createSimple.showImageOnFail(i3);
            }
            createSimple.showImageOnLoading(ThemeTitleHelper.TRANSPARENT_BACKGROUND);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            createSimple.decodingOptions(options);
            if (UnCustomThemeHelper.getInstance().customThemeEnable()) {
                final ImageInfoEntity imageInfo = UnCustomThemeHelper.getInstance().getImageInfo("title", str, "imageUrl", z2);
                if (imageInfo != null && imageInfo.isEffected) {
                    String str2 = "file://" + imageInfo.localPath;
                    this.isDefaultTheme = false;
                    showDefaultBg();
                    JDImageUtils.displayImage(str2, imageView, createSimple, new JDImageLoadingListener() { // from class: com.jingdong.common.unification.title.theme.JdThemeTitle.4
                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingCancelled(String str3, View view) {
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                            if (!JdThemeTitle.this.isLoaded) {
                                imageInfo.isEffected = true;
                                JdThemeTitle.this.isDefaultTheme = false;
                                ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                                if (iLoadBgCallback2 != null) {
                                    iLoadBgCallback2.loadBack();
                                }
                            }
                            JdThemeTitle.this.isLoaded = true;
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                            imageInfo.isEffected = false;
                            ILoadBgCallback iLoadBgCallback2 = iLoadBgCallback;
                            if (iLoadBgCallback2 != null) {
                                iLoadBgCallback2.loadBack();
                            }
                        }

                        @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                        public void onLoadingStarted(String str3, View view) {
                        }
                    });
                    return;
                }
                ThemeTitleHelper.setDefaultBg(imageView, z2, z);
                if (iLoadBgCallback != null) {
                    iLoadBgCallback.loadBack();
                }
            }
        } else {
            ThemeTitleHelper.setDefaultBg(imageView, z2, z);
            if (iLoadBgCallback != null) {
                iLoadBgCallback.loadBack();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable getTitleDrawable(String str) {
        int darkDrawable;
        if (this.customOpen) {
            return ThemeTitleHelper.getTitleDrawable(this.mContext, str, this.mModuleId, isDarkMode());
        }
        if (isDarkMode()) {
            darkDrawable = ThemeTitleHelper.getLigthDrawable(str);
        } else {
            darkDrawable = ThemeTitleHelper.getDarkDrawable(str);
        }
        if (darkDrawable != -1) {
            return this.mContext.getResources().getDrawable(darkDrawable);
        }
        return null;
    }

    private void setRootViewHeight() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.rootView.getLayoutParams();
        if (UnStatusBarTintUtil.isEnable(getContext()) && this.statusBarHint) {
            layoutParams.height = DpiUtil.dip2px(getContext(), 49.0f) + UnStatusBarTintUtil.getStatusBarHeight(getContext()) + this.footViewHeight;
        } else {
            layoutParams.height = DpiUtil.dip2px(getContext(), 49.0f) + this.footViewHeight;
        }
        this.rootView.setLayoutParams(layoutParams);
    }

    private void setStatusBarView() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.statusBar.getLayoutParams();
        if (UnStatusBarTintUtil.isEnable(getContext()) && this.statusBarHint) {
            layoutParams.height = UnStatusBarTintUtil.getStatusBarHeight(getContext());
            setStatusBarBg(getContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTextColor() {
        int titleTextColor;
        int buttonTextColor;
        if (this.themeTextColorEnable) {
            if (!this.customOpen) {
                if (!isDarkMode()) {
                    Resources resources = this.mContext.getResources();
                    int i2 = R.color.un_content_level_1;
                    titleTextColor = resources.getColor(i2);
                    buttonTextColor = this.mContext.getResources().getColor(i2);
                } else {
                    Resources resources2 = this.mContext.getResources();
                    int i3 = R.color.un_content_level_1_dark;
                    titleTextColor = resources2.getColor(i3);
                    buttonTextColor = this.mContext.getResources().getColor(i3);
                }
            } else {
                titleTextColor = ThemeTitleHelper.getTitleTextColor(this.mContext, this.mModuleId, isDarkMode());
                buttonTextColor = ThemeTitleHelper.getButtonTextColor(this.mContext, this.mModuleId, isDarkMode());
            }
            this.mTitleTv.setTextColor(titleTextColor);
            this.mLeftTv1.setTextColor(buttonTextColor);
            this.mLeftTv2.setTextColor(buttonTextColor);
            this.mRightTv1.setTextColor(buttonTextColor);
            this.mRightTv2.setTextColor(buttonTextColor);
            this.mRedPointTv.setTextColor(buttonTextColor);
        }
    }

    private void setTitleWidthByTitleIcon() {
        this.mTitleTv.setMaxWidth(DpiUtil.getAppWidth((Activity) getContext()) - DpiUtil.dip2px(getContext(), 124.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setbg(ILoadBgCallback iLoadBgCallback) {
        this.isLoaded = false;
        if (this.customOpen) {
            displayTitleBg(this.isGrayBg, isDarkMode(), this.mModuleId, this.mTitleBgIv, iLoadBgCallback);
            return;
        }
        ThemeTitleHelper.setDefaultBg(this.mTitleBgIv, isDarkMode(), this.isGrayBg);
        if (iLoadBgCallback != null) {
            iLoadBgCallback.loadBack();
        }
    }

    public void addViewToFootView(View view) {
        FrameLayout frameLayout = this.footView;
        if (frameLayout == null || view == null) {
            return;
        }
        frameLayout.removeAllViews();
        this.footView.addView(view);
    }

    public FrameLayout getCenterLayout() {
        return this.mCenterLayout;
    }

    public View getCenterView() {
        return this.centerView;
    }

    public String getColorStyle() {
        if (UnLog.D) {
            UnLog.d(TAG, "colorStyle: getColorStyle " + ThemeTitleHelper.getThemeTitleColorStyle(this.mModuleId, isDarkMode()));
        }
        return (this.customOpen || isDarkMode()) ? ThemeTitleHelper.getThemeTitleColorStyle(this.mModuleId, isDarkMode()) : "DARK";
    }

    public ViewGroup getFooterView() {
        return this.footView;
    }

    public ImageView getLeft1ImageView() {
        return this.mLeftIv1;
    }

    public TextView getLeft1TextView() {
        return this.mLeftTv1;
    }

    public ImageView getLeft2ImageView() {
        return this.mLeftIv2;
    }

    public TextView getLeft2TextView() {
        return this.mLeftTv2;
    }

    public ImageView getRedPointImageView() {
        return this.mRedPointIv;
    }

    public FrameLayout getRedPointLayout() {
        return this.mRedPointLayout;
    }

    public TextView getRedPointNumTv() {
        return this.mRedPointNumTv;
    }

    public TextView getRedPointTextView() {
        return this.mRedPointTv;
    }

    public ImageView getRight1ImageView() {
        return this.mRightIv1;
    }

    public TextView getRight1TextView() {
        return this.mRightTv1;
    }

    public ImageView getRight2ImageView() {
        return this.mRightIv2;
    }

    public TextView getRight2TextView() {
        return this.mRightTv2;
    }

    public FrameLayout getRightLayout() {
        return this.mRightLayout;
    }

    public View getStatusBar() {
        return this.statusBar;
    }

    public ImageView getTitleBgImageView() {
        return this.mTitleBgIv;
    }

    public RelativeLayout getTitleLayout() {
        return this.titleLayout;
    }

    public TextView getTitleTextView() {
        return this.mTitleTv;
    }

    public boolean isDarkMode() {
        if (this.autoDarkMode) {
            return a.g().p();
        }
        return this.isDarkMode;
    }

    public boolean isDarkStyle() {
        return TextUtils.equals(getColorStyle(), "LIGHT");
    }

    public boolean isDefaultTheme() {
        return !ThemeTitleHelper.useCustomTheme() && this.isDefaultTheme;
    }

    public boolean isStatusBarHint() {
        return this.statusBarHint;
    }

    public void loadTheme(boolean z) {
        if (z) {
            setbg(null);
        } else {
            loadTheme();
        }
    }

    public void notifyChange() {
        post(new Runnable() { // from class: com.jingdong.common.unification.title.theme.JdThemeTitle.2
            @Override // java.lang.Runnable
            public void run() {
                JdThemeTitle.this.setbg(new ILoadBgCallback() { // from class: com.jingdong.common.unification.title.theme.JdThemeTitle.2.1
                    String tempImage = null;

                    @Override // com.jingdong.common.unification.title.theme.ILoadBgCallback
                    public void loadBack() {
                        if (!TextUtils.isEmpty(JdThemeTitle.this.left1DrawableId)) {
                            ImageView imageView = JdThemeTitle.this.mLeftIv1;
                            JdThemeTitle jdThemeTitle = JdThemeTitle.this;
                            imageView.setImageDrawable(jdThemeTitle.getTitleDrawable(jdThemeTitle.left1DrawableId));
                        }
                        if (!TextUtils.isEmpty(JdThemeTitle.this.left2DrawableId)) {
                            ImageView imageView2 = JdThemeTitle.this.mLeftIv2;
                            JdThemeTitle jdThemeTitle2 = JdThemeTitle.this;
                            imageView2.setImageDrawable(jdThemeTitle2.getTitleDrawable(jdThemeTitle2.left2DrawableId));
                        }
                        if (!TextUtils.isEmpty(JdThemeTitle.this.right1DrawableId)) {
                            ImageView imageView3 = JdThemeTitle.this.mRightIv1;
                            JdThemeTitle jdThemeTitle3 = JdThemeTitle.this;
                            imageView3.setImageDrawable(jdThemeTitle3.getTitleDrawable(jdThemeTitle3.right1DrawableId));
                        }
                        if (!TextUtils.isEmpty(JdThemeTitle.this.right2DrawableId)) {
                            ImageView imageView4 = JdThemeTitle.this.mRightIv2;
                            JdThemeTitle jdThemeTitle4 = JdThemeTitle.this;
                            imageView4.setImageDrawable(jdThemeTitle4.getTitleDrawable(jdThemeTitle4.right2DrawableId));
                        }
                        if (!TextUtils.isEmpty(JdThemeTitle.this.redPointDrawableId)) {
                            TextView textView = JdThemeTitle.this.mRedPointTv;
                            JdThemeTitle jdThemeTitle5 = JdThemeTitle.this;
                            textView.setBackgroundDrawable(jdThemeTitle5.getTitleDrawable(jdThemeTitle5.redPointDrawableId));
                        }
                        if (!TextUtils.isEmpty(JdThemeTitle.this.titleIconId)) {
                            ImageView imageView5 = JdThemeTitle.this.titleIcon;
                            JdThemeTitle jdThemeTitle6 = JdThemeTitle.this;
                            imageView5.setImageDrawable(jdThemeTitle6.getTitleDrawable(jdThemeTitle6.titleIconId));
                        }
                        if (!TextUtils.equals(this.tempImage, ThemeTitleHelper.getTitleBgUrl(JdThemeTitle.this.mModuleId))) {
                            String unused = JdThemeTitle.TAG;
                            String str = "notify  setColor " + JdThemeTitle.this.mModuleId;
                            if (JdThemeTitle.this.getContext() instanceof Activity) {
                                JdThemeTitle jdThemeTitle7 = JdThemeTitle.this;
                                jdThemeTitle7.setStatusBarColorStyle((Activity) jdThemeTitle7.getContext());
                            }
                            JdThemeTitle.this.setTextColor();
                            this.tempImage = ThemeTitleHelper.getTitleBgUrl(JdThemeTitle.this.mModuleId);
                        }
                        if (JdThemeTitle.this.themeChangeListener != null && JdThemeTitle.this.customOpen) {
                            JdThemeTitle.this.themeChangeListener.onThemeChange(ThemeTitleHelper.isThemeTitleEffected(JdThemeTitle.this.mModuleId), JdThemeTitle.this.getColorStyle());
                        }
                        JdThemeTitle jdThemeTitle8 = JdThemeTitle.this;
                        jdThemeTitle8.setStatusBarBg(jdThemeTitle8.getContext());
                    }
                });
            }
        });
    }

    public void removeAllViewFromFootView() {
        FrameLayout frameLayout = this.footView;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
    }

    public void removeViewFromFootView(View view) {
        FrameLayout frameLayout = this.footView;
        if (frameLayout == null || view == null) {
            return;
        }
        frameLayout.removeView(view);
    }

    public void setAutoDarkMode(boolean z) {
        this.autoDarkMode = z;
    }

    public void setButtonAlpha(float f2) {
        if (this.mLeftTv1.getVisibility() == 0) {
            this.mLeftTv1.setAlpha(f2);
        }
        if (this.mLeftTv2.getVisibility() == 0) {
            this.mLeftTv2.setAlpha(f2);
        }
        if (this.mRightTv1.getVisibility() == 0) {
            this.mRightTv1.setAlpha(f2);
        }
        if (this.mRightTv2.getVisibility() == 0) {
            this.mRightTv2.setAlpha(f2);
        }
        if (this.mRedPointTv.getVisibility() == 0) {
            this.mRedPointTv.setAlpha(f2);
        }
    }

    public void setCenterTextClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mTitleTv.setOnClickListener(onClickListener);
        }
    }

    public void setCenterView(View view) {
        this.centerView = view;
        if (view != null) {
            this.mTitleTv.setVisibility(8);
            this.mCenterLayout.addView(view);
            this.mCenterLayout.setVisibility(0);
        }
    }

    public void setCustomBg(View view) {
        if (view != null) {
            this.titleBg2Layout.removeAllViews();
            this.titleBg2Layout.addView(view);
        }
    }

    public void setCustomOpen(boolean z) {
        this.customOpen = z;
    }

    public void setDarkMode(boolean z) {
        this.isDarkMode = z;
    }

    public void setFootViewHeight(int i2) {
        this.footViewHeight = i2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.footView.getLayoutParams();
        layoutParams.height = i2;
        this.footView.setLayoutParams(layoutParams);
        setRootViewHeight();
    }

    public void setLeft1DrawableId(String str) {
        this.left1DrawableId = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mLeftIv1.setImageDrawable(ThemeTitleHelper.getTitleDrawable(this.mContext, str, this.mModuleId));
        this.mLeftIv1.setVisibility(0);
    }

    public void setLeft2DrawableId(String str) {
        this.left2DrawableId = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mLeftIv2.setImageDrawable(ThemeTitleHelper.getTitleDrawable(this.mContext, str, this.mModuleId));
        this.mLeftIv2.setVisibility(0);
    }

    public void setLeftIv1ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mLeftIv1.setOnClickListener(onClickListener);
        }
    }

    public void setLeftIv2ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mLeftIv2.setOnClickListener(onClickListener);
        }
    }

    public void setLeftTv1ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mLeftTv1.setOnClickListener(onClickListener);
        }
    }

    public void setLeftTv1Text(String str) {
        if (str != null) {
            this.mLeftTv1.setText(str);
            this.mLeftTv1.setVisibility(0);
        }
    }

    public void setLeftTv1TextResource(int i2) {
        this.leftTv1TextResource = i2;
        if (i2 != -1) {
            this.mLeftTv1.setText(i2);
            this.mLeftTv1.setVisibility(0);
        }
    }

    public void setLeftTv1Visibility(int i2) {
        this.mLeftTv1.setVisibility(i2);
    }

    public void setLeftTv2ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mLeftTv2.setOnClickListener(onClickListener);
        }
    }

    public void setLeftTv2Text(String str) {
        if (str != null) {
            this.mLeftTv2.setText(str);
            this.mLeftTv2.setVisibility(0);
        }
    }

    public void setLeftTv2TextResource(int i2) {
        this.leftTv2TextResource = i2;
        if (i2 != -1) {
            this.mLeftTv2.setText(i2);
            this.mLeftTv2.setVisibility(0);
        }
    }

    public void setLeftTv2Visibility(int i2) {
        this.mLeftTv2.setVisibility(i2);
    }

    public void setLeftView(View view) {
        if (view != null) {
            this.mLeftLayout.addView(view);
            this.mLeftLayout.setVisibility(0);
        }
    }

    public void setModuleId(String str) {
        this.mModuleId = str;
    }

    public void setRedPointBgDrawable(Drawable drawable) {
        this.redPointDrawableId = null;
        this.mRedPointLayout.setVisibility(0);
        this.mRedPointTv.setBackgroundDrawable(drawable);
    }

    public void setRedPointBgDrawableId(String str) {
        this.redPointDrawableId = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mRedPointLayout.setVisibility(0);
        this.mRedPointTv.setBackgroundDrawable(ThemeTitleHelper.getTitleDrawable(this.mContext, str, this.mModuleId));
    }

    public void setRedPointClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mRedPointLayout.setOnClickListener(onClickListener);
        }
    }

    public void setRedPointNum(int i2) {
        if (i2 == 0) {
            this.mRedPointNumTv.setVisibility(8);
            return;
        }
        this.mRedPointNumTv.setVisibility(0);
        if (i2 > 99) {
            this.mRedPointNumTv.setText("99+");
            return;
        }
        this.mRedPointNumTv.setText(i2 + "");
    }

    public void setRedPointText(String str) {
        if (str != null) {
            this.mRedPointLayout.setVisibility(0);
            this.mRedPointTv.setText(str);
            this.mRedPointTv.setVisibility(0);
        }
    }

    public void setRight1Drawable(Drawable drawable) {
        this.right1DrawableId = null;
        this.mRightIv1.setImageDrawable(drawable);
        this.mRightIv1.setVisibility(0);
    }

    public void setRight1DrawableId(String str) {
        this.right1DrawableId = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mRightIv1.setImageDrawable(ThemeTitleHelper.getTitleDrawable(this.mContext, str, this.mModuleId, isDarkMode()));
        this.mRightIv1.setVisibility(0);
    }

    public void setRight2Drawable(Drawable drawable) {
        this.right2DrawableId = null;
        this.mRightIv2.setImageDrawable(drawable);
        this.mRightIv2.setVisibility(0);
    }

    public void setRight2DrawableId(String str) {
        this.right2DrawableId = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mRightIv2.setImageDrawable(ThemeTitleHelper.getTitleDrawable(this.mContext, str, this.mModuleId));
        this.mRightIv2.setVisibility(0);
    }

    public void setRightIv1ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mRightIv1.setOnClickListener(onClickListener);
        }
    }

    public void setRightIv2ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mRightIv2.setOnClickListener(onClickListener);
        }
    }

    public void setRightTv1ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mRightTv1.setOnClickListener(onClickListener);
        }
    }

    public void setRightTv1Text(String str) {
        if (str != null) {
            this.mRightTv1.setText(str);
            this.mRightTv1.setVisibility(0);
        }
    }

    public void setRightTv1TextResource(int i2) {
        this.rightTv1TextResource = i2;
        if (i2 != -1) {
            this.mRightTv1.setText(i2);
            this.mRightTv1.setVisibility(0);
        }
    }

    public void setRightTv1Visibility(int i2) {
        this.mRightTv1.setVisibility(i2);
    }

    public void setRightTv2ClickListener(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.mRightTv2.setOnClickListener(onClickListener);
        }
    }

    public void setRightTv2Text(String str) {
        if (str != null) {
            this.mRightTv2.setText(str);
            this.mRightTv2.setVisibility(0);
        }
    }

    public void setRightTv2TextResource(int i2) {
        this.rightTv2TextResource = i2;
        if (i2 != -1) {
            this.mRightTv2.setText(i2);
            this.mRightTv2.setVisibility(0);
        }
    }

    public void setRightTv2Visibility(int i2) {
        this.mRightTv2.setVisibility(i2);
    }

    public void setRightView(View view) {
        if (view != null) {
            this.mRightLayout.addView(view);
            this.mRightLayout.setVisibility(0);
        }
    }

    public void setShowRedPoint(boolean z) {
        if (z) {
            this.mRedpointIcon.setVisibility(0);
        } else {
            this.mRedpointIcon.setVisibility(8);
        }
    }

    public void setStatusBarBg(Context context) {
        if (UnStatusBarTintUtil.isEnable(context) && this.statusBarHint && !UnStatusBarTintUtil.setLightOrDarkEnable(context)) {
            if (TextUtils.equals(getColorStyle(), "LIGHT")) {
                this.statusBar.setVisibility(8);
                return;
            }
            this.statusBar.setVisibility(0);
            if (isDarkMode()) {
                this.statusBar.setBackgroundDrawable(getResources().getDrawable(R.color.un_theme_title_dark_bg));
            } else {
                this.statusBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.title_hint_bg));
            }
        }
    }

    public void setStatusBarBgWithForce(Context context, int i2) {
        if (UnStatusBarTintUtil.isEnable(context) && this.statusBarHint) {
            this.statusBar.setVisibility(0);
            this.statusBar.setBackgroundColor(i2);
        }
    }

    public void setStatusBarColorStyle(final Activity activity) {
        UnLog.d(TAG, "setStatusBarColorStyle: " + this.statusBarHint + "  " + this.statusBarColorStyleEnable);
        if (activity != null && this.statusBarHint && this.statusBarColorStyleEnable) {
            post(new Runnable() { // from class: com.jingdong.common.unification.title.theme.JdThemeTitle.3
                @Override // java.lang.Runnable
                public void run() {
                    String colorStyle = JdThemeTitle.this.getColorStyle();
                    UnLog.d(JdThemeTitle.TAG, "setStatusBarColorStyle: " + colorStyle);
                    if (TextUtils.equals(colorStyle, "DARK")) {
                        UnStatusBarTintUtil.setStatusBarLightMode(activity);
                    } else {
                        UnStatusBarTintUtil.setStatusBarDarkMode(activity);
                    }
                }
            });
        }
    }

    public void setStatusBarColorStyleEnable(boolean z) {
        this.statusBarColorStyleEnable = z;
    }

    public void setStatusBarHint(boolean z) {
        this.statusBarHint = z;
        setRootViewHeight();
        setStatusBarView();
    }

    public void setThemeChangeListener(IThemeChangeListener iThemeChangeListener) {
        this.themeChangeListener = iThemeChangeListener;
    }

    public void setThemeTextColorEnable(boolean z) {
        this.themeTextColorEnable = z;
    }

    public void setTitleIcon(String str) {
        this.titleIconId = str;
        if (!TextUtils.isEmpty(str)) {
            this.titleIcon.setImageDrawable(getTitleDrawable(str));
            this.titleIcon.setVisibility(0);
            setTitleWidthByTitleIcon();
            return;
        }
        this.titleIcon.setVisibility(8);
    }

    public void setTitleText(int i2) {
        this.titleText = i2;
        if (i2 != -1) {
            this.mTitleTv.setText(getResources().getString(this.titleText));
        }
    }

    public void setTitleTextBg(int i2) {
        this.titleTextBg = i2;
        if (i2 != -1) {
            this.mTitleTv.setBackgroundResource(i2);
        }
    }

    public void setUseGrayBg(boolean z) {
        this.isGrayBg = z;
    }

    public void setleft1Drawable(Drawable drawable) {
        this.left1DrawableId = null;
        this.mLeftIv1.setImageDrawable(drawable);
        this.mLeftIv1.setVisibility(0);
    }

    public void setleft2Drawable(Drawable drawable) {
        this.left2DrawableId = null;
        this.mLeftIv2.setImageDrawable(drawable);
        this.mLeftIv2.setVisibility(0);
    }

    public void showCustomBg() {
        if (isDefaultTheme()) {
            this.titleBg2Layout.setVisibility(0);
            this.mTitleBgIv.setVisibility(4);
            this.showDefaultBg = false;
        }
    }

    public void showDefaultBg() {
        this.showDefaultBg = true;
        this.titleBg2Layout.setVisibility(4);
        this.mTitleBgIv.setVisibility(0);
    }

    public JdThemeTitle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.statusBarColorStyleEnable = true;
        this.footViewHeight = 0;
        this.customOpen = true;
        this.themeTextColorEnable = true;
        this.isDefaultTheme = true;
        this.showDefaultBg = true;
        LayoutInflater.from(context).inflate(R.layout.jd_theme_title, (ViewGroup) this, true);
        this.mContext = context;
        this.titleBg2Layout = (FrameLayout) findViewById(R.id.titleBg2);
        this.mTitleBgIv = (SimpleDraweeView) findViewById(R.id.titleBg);
        this.mLeftTv1 = (TextView) findViewById(R.id.leftTv1);
        this.mLeftTv2 = (TextView) findViewById(R.id.leftTv2);
        this.mLeftIv1 = (ImageView) findViewById(R.id.leftIv1);
        this.mLeftIv2 = (ImageView) findViewById(R.id.leftIv2);
        this.mTitleTv = (TextView) findViewById(R.id.titleText);
        this.mCenterLayout = (FrameLayout) findViewById(R.id.centerLayout);
        this.mRedPointLayout = (FrameLayout) findViewById(R.id.redpoint_layout);
        this.mRedPointNumTv = (TextView) findViewById(R.id.redpointNumTv);
        this.mRedpointIcon = (ImageView) findViewById(R.id.redpointIcon);
        this.mRedPointTv = (TextView) findViewById(R.id.redpointTv);
        this.mRedPointIv = (ImageView) findViewById(R.id.redpointIv);
        this.mRightTv1 = (TextView) findViewById(R.id.rightTv1);
        this.mRightTv2 = (TextView) findViewById(R.id.rightTv2);
        this.mRightIv1 = (ImageView) findViewById(R.id.rightIv1);
        this.mRightIv2 = (ImageView) findViewById(R.id.rightIv2);
        this.mRightLayout = (FrameLayout) findViewById(R.id.rightLayout);
        this.mLeftLayout = (FrameLayout) findViewById(R.id.leftLayout);
        this.titleIcon = (ImageView) findViewById(R.id.titleIcon);
        this.statusBar = findViewById(R.id.status_bar);
        this.rootView = (RelativeLayout) findViewById(R.id.root_layout);
        this.footView = (FrameLayout) findViewById(R.id.footView);
        this.titleLayout = (RelativeLayout) findViewById(R.id.titleLayout);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.themeTitle);
        this.mModuleId = obtainStyledAttributes.getString(R.styleable.themeTitle_title_module_id);
        this.leftTv1TextResource = obtainStyledAttributes.getResourceId(R.styleable.themeTitle_left1_text, -1);
        this.left1DrawableId = obtainStyledAttributes.getString(R.styleable.themeTitle_left1_drawable_id);
        this.leftTv2TextResource = obtainStyledAttributes.getResourceId(R.styleable.themeTitle_left2_text, -1);
        this.left2DrawableId = obtainStyledAttributes.getString(R.styleable.themeTitle_left2_drawable_id);
        this.titleText = obtainStyledAttributes.getResourceId(R.styleable.themeTitle_title_text_resource, -1);
        this.titleTextBg = obtainStyledAttributes.getResourceId(R.styleable.themeTitle_title_text_bg, -1);
        this.redPointDrawableId = obtainStyledAttributes.getString(R.styleable.themeTitle_redpoint_drawable_id);
        this.rightTv1TextResource = obtainStyledAttributes.getResourceId(R.styleable.themeTitle_right1_text, -1);
        this.right1DrawableId = obtainStyledAttributes.getString(R.styleable.themeTitle_right1_drawable_id);
        this.rightTv2TextResource = obtainStyledAttributes.getResourceId(R.styleable.themeTitle_right2_text, -1);
        this.right2DrawableId = obtainStyledAttributes.getString(R.styleable.themeTitle_right2_drawable_id);
        this.statusBarHint = obtainStyledAttributes.getBoolean(R.styleable.themeTitle_status_bar_hint, false);
        this.statusBarColorStyleEnable = obtainStyledAttributes.getBoolean(R.styleable.themeTitle_status_bar_color_style_enable, true);
        this.footViewHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.themeTitle_foot_view_height, 0);
        this.customOpen = obtainStyledAttributes.getBoolean(R.styleable.themeTitle_custom_open, true);
        this.autoDarkMode = obtainStyledAttributes.getBoolean(R.styleable.themeTitle_auto_dark_theme, false);
        this.isDarkMode = obtainStyledAttributes.getBoolean(R.styleable.themeTitle_is_dark_mode, false);
        this.titleIconId = obtainStyledAttributes.getString(R.styleable.themeTitle_theme_title_icon_id);
        this.isGrayBg = obtainStyledAttributes.getBoolean(R.styleable.themeTitle_title_gray_bg, false);
        setRootViewHeight();
        setStatusBarView();
        setFootViewHeight(this.footViewHeight);
        if (UnLog.D) {
            String str = TAG;
            UnLog.d(str, "leftTv1BgDrawableId:" + this.left1DrawableId);
            UnLog.d(str, "leftTv2BgDrawableId:" + this.left2DrawableId);
        }
        int i2 = this.leftTv1TextResource;
        if (i2 != -1) {
            this.mLeftTv1.setText(i2);
            this.mLeftTv1.setVisibility(0);
        }
        int i3 = this.leftTv2TextResource;
        if (i3 != -1) {
            this.mLeftTv2.setText(i3);
            this.mLeftTv2.setVisibility(0);
        }
        int i4 = this.titleText;
        if (i4 != -1) {
            this.mTitleTv.setText(i4);
        }
        int i5 = this.titleTextBg;
        if (i5 != -1) {
            this.mTitleTv.setBackgroundResource(i5);
        }
        int i6 = this.rightTv1TextResource;
        if (i6 != -1) {
            this.mRightTv1.setText(i6);
            this.mRightTv1.setVisibility(0);
        }
        int i7 = this.rightTv2TextResource;
        if (i7 != -1) {
            this.mRightTv2.setText(i7);
            this.mRightTv2.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.left1DrawableId)) {
            this.mLeftIv1.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.left2DrawableId)) {
            this.mLeftIv2.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.right1DrawableId)) {
            this.mRightIv1.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.left2DrawableId)) {
            this.mLeftIv2.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.right1DrawableId)) {
            this.mRightIv1.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.right2DrawableId)) {
            this.mRightIv2.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.redPointDrawableId)) {
            this.mRedPointLayout.setVisibility(0);
        }
        if (!TextUtils.isEmpty(this.titleIconId)) {
            this.titleIcon.setVisibility(0);
            setTitleWidthByTitleIcon();
        }
        loadTheme();
    }

    public void loadTheme() {
        setbg(new ILoadBgCallback() { // from class: com.jingdong.common.unification.title.theme.JdThemeTitle.1
            private String tempImage = null;

            @Override // com.jingdong.common.unification.title.theme.ILoadBgCallback
            public void loadBack() {
                if (!TextUtils.isEmpty(JdThemeTitle.this.left1DrawableId)) {
                    ImageView imageView = JdThemeTitle.this.mLeftIv1;
                    JdThemeTitle jdThemeTitle = JdThemeTitle.this;
                    imageView.setImageDrawable(jdThemeTitle.getTitleDrawable(jdThemeTitle.left1DrawableId));
                    JdThemeTitle.this.mLeftIv1.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.left1DrawableId));
                }
                if (!TextUtils.isEmpty(JdThemeTitle.this.left2DrawableId)) {
                    ImageView imageView2 = JdThemeTitle.this.mLeftIv2;
                    JdThemeTitle jdThemeTitle2 = JdThemeTitle.this;
                    imageView2.setImageDrawable(jdThemeTitle2.getTitleDrawable(jdThemeTitle2.left2DrawableId));
                    JdThemeTitle.this.mLeftIv2.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.left2DrawableId));
                }
                if (!TextUtils.isEmpty(JdThemeTitle.this.right1DrawableId)) {
                    ImageView imageView3 = JdThemeTitle.this.mRightIv1;
                    JdThemeTitle jdThemeTitle3 = JdThemeTitle.this;
                    imageView3.setImageDrawable(jdThemeTitle3.getTitleDrawable(jdThemeTitle3.right1DrawableId));
                    JdThemeTitle.this.mRightIv1.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.right1DrawableId));
                }
                if (!TextUtils.isEmpty(JdThemeTitle.this.left2DrawableId)) {
                    ImageView imageView4 = JdThemeTitle.this.mLeftIv2;
                    JdThemeTitle jdThemeTitle4 = JdThemeTitle.this;
                    imageView4.setImageDrawable(jdThemeTitle4.getTitleDrawable(jdThemeTitle4.left2DrawableId));
                    JdThemeTitle.this.mLeftIv2.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.left2DrawableId));
                }
                if (!TextUtils.isEmpty(JdThemeTitle.this.right1DrawableId)) {
                    ImageView imageView5 = JdThemeTitle.this.mRightIv1;
                    JdThemeTitle jdThemeTitle5 = JdThemeTitle.this;
                    imageView5.setImageDrawable(jdThemeTitle5.getTitleDrawable(jdThemeTitle5.right1DrawableId));
                    JdThemeTitle.this.mRightIv1.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.right1DrawableId));
                }
                if (!TextUtils.isEmpty(JdThemeTitle.this.right2DrawableId)) {
                    ImageView imageView6 = JdThemeTitle.this.mRightIv2;
                    JdThemeTitle jdThemeTitle6 = JdThemeTitle.this;
                    imageView6.setImageDrawable(jdThemeTitle6.getTitleDrawable(jdThemeTitle6.right2DrawableId));
                    JdThemeTitle.this.mRightIv2.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.right2DrawableId));
                }
                if (!TextUtils.isEmpty(JdThemeTitle.this.redPointDrawableId)) {
                    TextView textView = JdThemeTitle.this.mRedPointTv;
                    JdThemeTitle jdThemeTitle7 = JdThemeTitle.this;
                    textView.setBackgroundDrawable(jdThemeTitle7.getTitleDrawable(jdThemeTitle7.redPointDrawableId));
                    JdThemeTitle.this.mRedPointTv.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.redPointDrawableId));
                }
                if (!TextUtils.isEmpty(JdThemeTitle.this.titleIconId)) {
                    ImageView imageView7 = JdThemeTitle.this.titleIcon;
                    JdThemeTitle jdThemeTitle8 = JdThemeTitle.this;
                    imageView7.setImageDrawable(jdThemeTitle8.getTitleDrawable(jdThemeTitle8.titleIconId));
                    JdThemeTitle.this.titleIcon.setContentDescription(ThemeTitleHelper.getCDStringById(JdThemeTitle.this.mContext, JdThemeTitle.this.titleIconId));
                }
                JdThemeTitle jdThemeTitle9 = JdThemeTitle.this;
                jdThemeTitle9.setStatusBarBg(jdThemeTitle9.getContext());
                if (JdThemeTitle.this.getContext() instanceof Activity) {
                    JdThemeTitle jdThemeTitle10 = JdThemeTitle.this;
                    jdThemeTitle10.setStatusBarColorStyle((Activity) jdThemeTitle10.getContext());
                }
                JdThemeTitle.this.setTextColor();
                this.tempImage = ThemeTitleHelper.getTitleBgUrl(JdThemeTitle.this.mModuleId);
            }
        });
    }

    public void setLeftView(View view, FrameLayout.LayoutParams layoutParams) {
        if (view != null) {
            if (layoutParams != null) {
                this.mLeftLayout.addView(view, layoutParams);
            } else {
                this.mLeftLayout.addView(view);
            }
            this.mRightLayout.setVisibility(0);
        }
    }

    public void setRightView(View view, FrameLayout.LayoutParams layoutParams) {
        if (view != null) {
            if (layoutParams != null) {
                this.mRightLayout.addView(view, layoutParams);
            } else {
                this.mRightLayout.addView(view);
            }
            this.mRightLayout.setVisibility(0);
        }
    }

    public void setTitleText(String str) {
        if (str != null) {
            this.mTitleTv.setText(str);
        }
    }

    public void setCenterView(View view, FrameLayout.LayoutParams layoutParams) {
        this.centerView = view;
        if (view != null) {
            this.mTitleTv.setVisibility(8);
            if (layoutParams != null) {
                this.mCenterLayout.addView(view, layoutParams);
            } else {
                this.mCenterLayout.addView(view);
            }
            this.mCenterLayout.setVisibility(0);
        }
    }

    public void setRedPointNum(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mRedPointNumTv.setVisibility(8);
            return;
        }
        this.mRedPointNumTv.setText(str);
        this.mRedPointNumTv.setVisibility(0);
    }
}
