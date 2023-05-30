package com.jingdong.common.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.basewidget.widget.watermark.WatermarkHelper;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;
import com.jingdong.common.unification.dialog.UnBaseDialog;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.widget.image.UnNetImageView;
import com.jingdong.common.widget.shadow.widget.JDShadowViewGroup;

/* loaded from: classes6.dex */
public class JDBottomDialog extends UnBaseDialog implements UnWidgetThemeController.OnConfigurationChangedListener {
    public static final int BOTTOM_DIALOG_STYLE_JINGXUAN = 1;
    public static final int BOTTOM_DIALOG_STYLE_NORMAL = 0;
    private static final int DIALOG_MAX_HEIGHT_FLAG = 3;
    private static final int DIALOG_MIN_HEIGHT_FLAG = 2;
    private static final int PERCENT_CUSTOM = 4;
    private static final String TAG = "JDBottomDialog";
    private static final int WRAP_CONTENT = 1;
    private int bottomOffset;
    private int btnDefaultBg;
    private int btnDefaultTextCor;
    private int btnJingXuanBg;
    private int btnJingXuanTextCor;
    public View.OnClickListener cancelClickListener;
    private int contentFlag;
    private int customHeight;
    private float customPercent;
    public boolean isAutoRefresh;
    private boolean isCustomTheme;
    private boolean isNeedGetPercentX;
    public ImageView mBack;
    public ImageView mCancel;
    private int mContentHeight;
    public FrameLayout mContentLayout;
    protected Context mContext;
    private LinearLayout mParentLayout;
    public Button mPosButton;
    public RelativeLayout mTitleContentLayout;
    private int maxHeight;
    private float maxPercent;
    private int minHeight;
    private float minPercent;
    private FrameLayout rectButtonLayout;
    private JDShadowViewGroup shadowViewGroup;
    public boolean statusBarFollowActivity;
    private UnNetImageView titleBg;
    private TextView titleExplain;
    TextView titleTv;
    private int tureScreenHeight;
    private boolean useBg;
    public boolean useSetDialogHeightPx;

    public JDBottomDialog(Context context) {
        this(context, R.style.JD_Dialog_From_Bottom);
    }

    private void addContentView(View view) {
        FrameLayout frameLayout;
        if (view == null || (frameLayout = this.mContentLayout) == null) {
            return;
        }
        frameLayout.removeAllViews();
        this.mContentLayout.addView(view);
    }

    public static boolean checkDeviceHasNavigationBar(Activity activity) {
        if (Build.VERSION.SDK_INT < 17) {
            return (ViewConfiguration.get(activity).hasPermanentMenuKey() || KeyCharacterMap.deviceHasKey(4)) ? false : true;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point2 = new Point();
        Point point3 = new Point();
        defaultDisplay.getSize(point2);
        defaultDisplay.getRealSize(point3);
        return point3.y != point2.y;
    }

    private float checkPercent(float f2) {
        float f3 = this.maxPercent;
        if (f2 > f3) {
            return f3;
        }
        float f4 = this.minPercent;
        return f2 < f4 ? f4 : f2;
    }

    private int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void initViews() {
        this.mParentLayout = (LinearLayout) findViewById(R.id.parent_layout);
        this.mTitleContentLayout = (RelativeLayout) findViewById(R.id.title_content);
        this.mContentLayout = (FrameLayout) findViewById(R.id.dialog_content_layout);
        ImageView imageView = (ImageView) findViewById(R.id.dialog_cancel);
        this.mCancel = imageView;
        imageView.setOnClickListener(this.cancelClickListener);
        this.mBack = (ImageView) findViewById(R.id.dialog_back);
        this.mPosButton = (Button) findViewById(R.id.dialog_pos_button);
        this.titleTv = (TextView) findViewById(R.id.dialog_title);
        this.titleBg = (UnNetImageView) findViewById(R.id.title_bg);
        this.titleExplain = (TextView) findViewById(R.id.title_explain);
        try {
            this.shadowViewGroup = (JDShadowViewGroup) findViewById(R.id.posButtonShadow);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.rectButtonLayout = (FrameLayout) findViewById(R.id.buttonLayout);
        } catch (Exception e3) {
            if (UnLog.D) {
                e3.printStackTrace();
            }
        }
        if (this.useBg) {
            setWindowBg(R.drawable.jd_dialog_common_bg);
        }
    }

    private boolean isSmallFold() {
        int height = DpiUtil.getHeight(this.mContext);
        int width = DpiUtil.getWidth(this.mContext);
        if (!UnAndroidUtils.isFoldScreen() || width == 0 || height / width < 2) {
            return false;
        }
        String str = "isSmallFold:" + height + LangUtils.SINGLE_SPACE + width;
        return true;
    }

    private void setContent(View view, String str) {
        this.mTitleContentLayout.setVisibility(8);
        addContentView(view);
        if (!TextUtils.isEmpty(str)) {
            this.mPosButton.setVisibility(0);
            this.mPosButton.setText(str);
            this.mPosButton.setOnClickListener(this.cancelClickListener);
            return;
        }
        this.mPosButton.setVisibility(8);
    }

    private void setContentWithTitle(String str, View view, String str2, boolean z) {
        this.mTitleContentLayout.setVisibility(0);
        ((TextView) findViewById(R.id.dialog_title)).setText(str);
        if (z) {
            this.mBack.setVisibility(0);
        } else {
            this.mBack.setVisibility(8);
        }
        addContentView(view);
        if (!TextUtils.isEmpty(str2)) {
            this.mPosButton.setVisibility(0);
            this.mPosButton.setText(str2);
            this.mPosButton.setOnClickListener(this.cancelClickListener);
            return;
        }
        this.mPosButton.setVisibility(8);
    }

    private void setDialogHeight(int i2) {
        if (i2 > 0) {
            this.mContentHeight = i2;
        } else {
            this.mContentHeight = this.maxHeight;
        }
    }

    private void titleContentLayoutThemeChange(boolean z) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mTitleContentLayout.getLayoutParams();
        if (z) {
            layoutParams.height = getContext().getResources().getDimensionPixelSize(R.dimen.jd_bottom_dialog_title_height);
        } else {
            layoutParams.height = getContext().getResources().getDimensionPixelSize(R.dimen.jd_bottom_dialog_title_theme_height);
        }
    }

    public void addContent(View view, String str) {
        setContent(view, str);
        this.contentFlag = 1;
    }

    public void addContentWithHeight(View view, String str, boolean z) {
        setContent(view, str);
        setDialogHeight(z);
    }

    public void addContentWithTitle(String str, View view, String str2, boolean z) {
        setContentWithTitle(str, view, str2, z);
        this.contentFlag = 1;
    }

    public void addContentWithTitleAndHeight(String str, View view, String str2, boolean z, boolean z2) {
        setContentWithTitle(str, view, str2, z);
        setDialogHeight(z2);
    }

    public void addContentWrapContent(View view, String str) {
        setContent(view, str);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        super.cancel();
        UnWidgetThemeController.getInstance().removeOnConfigurationChangedListener(this);
    }

    public void changeToTheme(String str, Integer num, Drawable drawable) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!str.startsWith("http")) {
            str = "file://" + str;
        }
        this.isCustomTheme = true;
        titleContentLayoutThemeChange(false);
        this.titleBg.setVisibility(0);
        this.titleBg.setImage(str);
        if (num != null) {
            setTitleTextColor(num.intValue());
        }
        if (drawable != null) {
            this.mCancel.setImageDrawable(drawable);
        }
    }

    @Override // com.jingdong.common.unification.dialog.UnBaseDialog
    public void darkMode() {
        Button button = this.mPosButton;
        if (button != null) {
            button.setTextSize(14.0f);
            this.mPosButton.getLayoutParams().height = DpiUtil.dip2px(getContext(), 38.0f);
            this.mPosButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_d_a_font_color_dark));
            this.mPosButton.setBackgroundResource(R.drawable.button_d_a_dark);
        }
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextSize(18.0f);
        }
        JDShadowViewGroup jDShadowViewGroup = this.shadowViewGroup;
        if (jDShadowViewGroup != null) {
            jDShadowViewGroup.setBackgroundResource(R.color.un_bg_level_2_dark);
        }
        RelativeLayout relativeLayout = this.mTitleContentLayout;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundResource(R.color.un_bg_level_2_dark);
        }
        if (!this.isCustomTheme) {
            setTitleTextColor(getContext().getResources().getColor(R.color.un_content_level_1_dark));
        }
        if (this.useBg) {
            setWindowBg(R.drawable.jd_dialog_bottom_common_bg_dark);
        }
        ImageView imageView = this.mCancel;
        if (imageView != null) {
            imageView.getLayoutParams().height = this.mCancel.getPaddingBottom() + this.mCancel.getPaddingTop() + DpiUtil.dip2px(getContext(), 12.0f);
            this.mCancel.getLayoutParams().width = this.mCancel.getPaddingLeft() + this.mCancel.getPaddingRight() + DpiUtil.dip2px(getContext(), 12.0f);
        }
        FrameLayout frameLayout = this.rectButtonLayout;
        if (frameLayout != null) {
            frameLayout.setBackgroundResource(R.color.un_bg_level_2_dark);
        }
        TextView textView2 = this.titleExplain;
        if (textView2 != null) {
            textView2.setBackgroundResource(R.drawable.un_bottom_dialog_title_explain_bg_dark);
            this.titleExplain.setTextColor(getContext().getResources().getColor(R.color.un_content_level_2_dark));
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        UnWidgetThemeController.getInstance().removeOnConfigurationChangedListener(this);
    }

    @Override // com.jingdong.common.unification.dialog.UnBaseDialog
    public void elderMode() {
        Button button = this.mPosButton;
        if (button != null) {
            button.setTextSize(16.0f);
            this.mPosButton.getLayoutParams().height = DpiUtil.dip2px(getContext(), 42.0f);
            this.mPosButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_main_red_elder_font_color));
            this.mPosButton.setBackgroundResource(R.drawable.button_main_red_elder);
        }
        JDShadowViewGroup jDShadowViewGroup = this.shadowViewGroup;
        if (jDShadowViewGroup != null) {
            jDShadowViewGroup.setBackgroundResource(R.color.un_bg_level_2_elder);
        }
        RelativeLayout relativeLayout = this.mTitleContentLayout;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundResource(R.color.un_bg_level_2_elder);
        }
        if (!this.isCustomTheme) {
            setTitleTextColor(getContext().getResources().getColor(R.color.un_content_level_1_elder));
        }
        FrameLayout frameLayout = this.rectButtonLayout;
        if (frameLayout != null) {
            frameLayout.setBackgroundResource(R.color.un_bg_level_2_elder);
        }
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextSize(20.0f);
        }
        ImageView imageView = this.mCancel;
        if (imageView != null) {
            imageView.getLayoutParams().height = this.mCancel.getPaddingBottom() + this.mCancel.getPaddingTop() + DpiUtil.dip2px(getContext(), 16.0f);
            this.mCancel.getLayoutParams().width = this.mCancel.getPaddingLeft() + this.mCancel.getPaddingRight() + DpiUtil.dip2px(getContext(), 16.0f);
        }
        if (this.useBg) {
            setWindowBg(R.drawable.jd_dialog_bottom_common_bg);
        }
        TextView textView2 = this.titleExplain;
        if (textView2 != null) {
            textView2.setBackgroundResource(R.drawable.un_bottom_dialog_title_explain_bg);
            this.titleExplain.setTextColor(getContext().getResources().getColor(R.color.c_8c8c8c));
        }
    }

    public LinearLayout getParentLayout() {
        return this.mParentLayout;
    }

    public Button getPosButton() {
        return this.mPosButton;
    }

    public int getVirtualBarHeigh() {
        int appHeight = UnWidgetThemeController.getInstance().getAppHeight();
        try {
            if (checkDeviceHasNavigationBar((Activity) this.mContext)) {
                appHeight += getNavigationBarHeight((Activity) this.mContext);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (UnLog.D) {
            UnLog.d(TAG, "appHeight:" + appHeight);
        }
        return appHeight;
    }

    @Override // com.jingdong.common.unification.dialog.UnBaseDialog
    public void normalMode() {
        Button button = this.mPosButton;
        if (button != null) {
            button.getLayoutParams().height = DpiUtil.dip2px(getContext(), 38.0f);
            this.mPosButton.setTextSize(14.0f);
            this.mPosButton.setTextColor(getContext().getResources().getColorStateList(R.color.button_b_font_color));
            this.mPosButton.setBackgroundResource(R.drawable.button_b);
        }
        JDShadowViewGroup jDShadowViewGroup = this.shadowViewGroup;
        if (jDShadowViewGroup != null) {
            jDShadowViewGroup.setBackgroundResource(R.color.un_bg_level_2);
        }
        RelativeLayout relativeLayout = this.mTitleContentLayout;
        if (relativeLayout != null) {
            relativeLayout.setBackgroundResource(R.color.un_bg_level_2);
        }
        if (!this.isCustomTheme) {
            setTitleTextColor(getContext().getResources().getColor(R.color.un_content_level_1));
        }
        FrameLayout frameLayout = this.rectButtonLayout;
        if (frameLayout != null) {
            frameLayout.setBackgroundResource(R.color.un_bg_level_2);
        }
        if (this.useBg) {
            setWindowBg(R.drawable.jd_dialog_bottom_common_bg);
        }
        TextView textView = this.titleTv;
        if (textView != null) {
            textView.setTextSize(18.0f);
        }
        ImageView imageView = this.mCancel;
        if (imageView != null) {
            imageView.getLayoutParams().height = this.mCancel.getPaddingBottom() + this.mCancel.getPaddingTop() + DpiUtil.dip2px(getContext(), 12.0f);
            this.mCancel.getLayoutParams().width = this.mCancel.getPaddingLeft() + this.mCancel.getPaddingRight() + DpiUtil.dip2px(getContext(), 12.0f);
        }
        TextView textView2 = this.titleExplain;
        if (textView2 != null) {
            textView2.setBackgroundResource(R.drawable.un_bottom_dialog_title_explain_bg);
            this.titleExplain.setTextColor(getContext().getResources().getColor(R.color.c_8c8c8c));
        }
    }

    @Override // com.jd.lib.un.global.theme.UnWidgetThemeController.OnConfigurationChangedListener
    public void onChanged() {
        refreshDialogHeight();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            return;
        }
        getWindow().setWindowAnimations(R.style.dialog_annim_bottom_exit_style);
    }

    public void refreshDialogHeight() {
        int virtualBarHeigh = getVirtualBarHeigh();
        this.tureScreenHeight = virtualBarHeigh;
        this.minHeight = (int) (virtualBarHeigh * this.minPercent);
        this.maxHeight = (int) (virtualBarHeigh * this.maxPercent);
        if (this.isNeedGetPercentX) {
            setDialogHeightPercent(this.customPercent);
        }
        if (this.mParentLayout != null) {
            int i2 = this.contentFlag;
            if (i2 == 1) {
                setDialogMaxHeight();
                return;
            }
            if (i2 == 4) {
                setDialogHeightPercent(this.customPercent);
                setDialogHeight(this.customHeight);
            } else if (i2 == 2) {
                this.mContentHeight = this.minHeight;
            } else if (i2 == 3) {
                this.mContentHeight = this.maxHeight;
            }
            setHeight(this.mContentHeight);
        }
    }

    public void setBackClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.mBack;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void setBottomOffset(int i2) {
        this.bottomOffset = i2;
    }

    public void setButtonColor(int i2) {
        if (this.mPosButton == null) {
            return;
        }
        if (i2 == 0) {
            if (this.btnDefaultBg != -1 && this.btnDefaultTextCor != -1) {
                JDShadowViewGroup jDShadowViewGroup = this.shadowViewGroup;
                if (jDShadowViewGroup != null) {
                    jDShadowViewGroup.setShadowClipCanvas(true);
                }
                this.mPosButton.setBackgroundColor(this.btnDefaultBg);
                this.mPosButton.setTextColor(this.btnDefaultTextCor);
                return;
            }
            JDShadowViewGroup jDShadowViewGroup2 = this.shadowViewGroup;
            if (jDShadowViewGroup2 != null) {
                jDShadowViewGroup2.setShadowClipCanvas(false);
            }
            this.mPosButton.setBackgroundResource(R.drawable.button_b);
            this.mPosButton.setTextColor(-1);
        } else if (i2 != 1) {
        } else {
            if (this.btnJingXuanBg != -1 && this.btnJingXuanTextCor != -1) {
                JDShadowViewGroup jDShadowViewGroup3 = this.shadowViewGroup;
                if (jDShadowViewGroup3 != null) {
                    jDShadowViewGroup3.setShadowClipCanvas(true);
                }
                this.mPosButton.setBackgroundColor(this.btnJingXuanBg);
                this.mPosButton.setTextColor(this.btnJingXuanTextCor);
                return;
            }
            JDShadowViewGroup jDShadowViewGroup4 = this.shadowViewGroup;
            if (jDShadowViewGroup4 != null) {
                jDShadowViewGroup4.setShadowClipCanvas(false);
            }
            this.mPosButton.setBackgroundResource(R.drawable.un_button_jingxuan_bg);
            this.btnJingXuanTextCor = this.mContext.getResources().getColor(R.color.pd_drawable_333333);
        }
    }

    public void setCancelClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.mCancel;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    public void setDialogHeightPercent(float f2) {
        if (this.contentFlag != 4) {
            f2 = checkPercent(f2);
        }
        int i2 = this.tureScreenHeight;
        if (i2 == 0) {
            this.isNeedGetPercentX = true;
            this.customPercent = f2;
            return;
        }
        this.isNeedGetPercentX = false;
        this.customHeight = (int) (i2 * f2);
    }

    public void setDialogHeightPx(int i2) {
        if (i2 > 0) {
            this.customHeight = i2;
            this.useSetDialogHeightPx = true;
            return;
        }
        this.useSetDialogHeightPx = false;
    }

    public void setDialogMaxHeight() {
        LinearLayout linearLayout = this.mParentLayout;
        if (linearLayout != null) {
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jingdong.common.ui.JDBottomDialog.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    JDBottomDialog.this.mParentLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = JDBottomDialog.this.mParentLayout.getHeight();
                    int i2 = JDBottomDialog.this.minHeight;
                    if (JDBottomDialog.this.mTitleContentLayout.getVisibility() == 8) {
                        i2 = JDBottomDialog.this.maxHeight;
                    }
                    if (JDBottomDialog.this.customHeight > 0 && JDBottomDialog.this.customHeight > i2) {
                        i2 = JDBottomDialog.this.customHeight;
                    }
                    if (height > i2) {
                        JDBottomDialog.this.setHeight(i2);
                    }
                }
            });
        }
    }

    public void setHeight(int i2) {
        int i3;
        int height = DpiUtil.getHeight(this.mContext);
        if (isSmallFold() && i2 > (i3 = (int) (height * 0.7f))) {
            i2 = i3;
        }
        this.mParentLayout.getLayoutParams().height = i2;
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = i2;
        window.setAttributes(attributes);
        this.mParentLayout.requestLayout();
    }

    public void setPosBtnClickListener(View.OnClickListener onClickListener) {
        Button button = this.mPosButton;
        if (button != null) {
            button.setOnClickListener(onClickListener);
        }
    }

    public void setTitleExplain(String str, View.OnClickListener onClickListener) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.titleExplain.setText(str);
        this.titleExplain.setVisibility(0);
        if (onClickListener != null) {
            this.titleExplain.setOnClickListener(onClickListener);
        }
    }

    public void setTitleTextColor(int i2) {
        TextView textView;
        if (i2 == 0 || (textView = (TextView) findViewById(R.id.dialog_title)) == null) {
            return;
        }
        textView.setTextColor(i2);
    }

    public void setUseBg(boolean z) {
        this.useBg = z;
        setWindowBg(R.drawable.jd_dialog_bottom_common_bg);
    }

    @Override // android.app.Dialog
    public void show() {
        UnWidgetThemeController.getInstance().addOnConfigurationChangedListener(this);
        if (this.statusBarFollowActivity) {
            try {
                getWindow().clearFlags(2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        window.setGravity(80);
        attributes.width = -1;
        attributes.y = this.bottomOffset;
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.dialog_annim_bottom_style);
        if (this.isAutoRefresh) {
            if (isElder()) {
                elderMode();
            } else if (isDarkMode()) {
                darkMode();
            } else {
                normalMode();
            }
        }
        if (this.useSetDialogHeightPx) {
            setHeight(this.customHeight);
        } else {
            refreshDialogHeight();
        }
        new WatermarkHelper().showByCanAdd(this);
    }

    public void toDefaultTheme() {
        this.isCustomTheme = false;
        titleContentLayoutThemeChange(true);
        this.titleBg.setVisibility(8);
        this.mCancel.setImageResource(R.drawable.common_dialog_close);
        refresh();
    }

    public JDBottomDialog(Context context, int i2) {
        super(context, i2);
        this.minPercent = 0.6f;
        this.maxPercent = 0.8333333f;
        this.customHeight = 0;
        this.mContentHeight = -1;
        this.btnDefaultBg = -1;
        this.btnDefaultTextCor = -1;
        this.btnJingXuanBg = -1;
        this.btnJingXuanTextCor = -1;
        this.useBg = false;
        this.isAutoRefresh = true;
        this.useSetDialogHeightPx = false;
        this.cancelClickListener = new View.OnClickListener() { // from class: com.jingdong.common.ui.JDBottomDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JDBottomDialog.this.dismiss();
            }
        };
        this.mContext = context;
        setContentView(UnAndroidUtils.isFoldScreen() ? R.layout.jd_common_bottom_dialog : R.layout.jd_common_bottom_dialog_round_rect_bg);
        setCancelable(true);
        initViews();
    }

    public void addContentWithHeight(View view, String str, float f2) {
        setContent(view, str);
        setDialogHeight(checkPercent(f2) == this.minPercent);
    }

    public void addContentWithTitleAndHeight(String str, View view, String str2, boolean z, float f2, boolean z2) {
        setContentWithTitle(str, view, str2, z);
        this.contentFlag = 4;
        if (z2) {
            this.customPercent = f2;
        } else {
            this.customPercent = checkPercent(f2);
        }
    }

    public void setDialogHeight(boolean z) {
        if (z) {
            this.contentFlag = 2;
        } else {
            this.contentFlag = 3;
        }
    }

    public void addContentWithHeight(View view, String str, float f2, boolean z) {
        setContent(view, str);
        this.contentFlag = 4;
        if (z) {
            this.customPercent = f2;
        } else {
            this.customPercent = checkPercent(f2);
        }
    }

    public void changeToTheme(Bitmap bitmap, Integer num, Drawable drawable) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        this.isCustomTheme = true;
        titleContentLayoutThemeChange(false);
        this.titleBg.setVisibility(0);
        this.titleBg.setImageBitmap(bitmap);
        if (num != null) {
            setTitleTextColor(num.intValue());
        }
        if (drawable != null) {
            this.mCancel.setImageDrawable(drawable);
        }
    }

    public void changeToTheme(Drawable drawable, Integer num, Drawable drawable2) {
        if (drawable == null) {
            return;
        }
        this.isCustomTheme = true;
        titleContentLayoutThemeChange(false);
        this.titleBg.setVisibility(0);
        this.titleBg.setImageDrawable(drawable);
        if (num != null) {
            setTitleTextColor(num.intValue());
        }
        if (drawable2 != null) {
            this.mCancel.setImageDrawable(drawable2);
        }
    }

    public void setButtonColor(int i2, int i3) {
        if (this.mPosButton == null) {
            return;
        }
        if (i2 == 0) {
            if (this.btnDefaultBg == -1) {
                JDShadowViewGroup jDShadowViewGroup = this.shadowViewGroup;
                if (jDShadowViewGroup != null) {
                    jDShadowViewGroup.setShadowClipCanvas(false);
                }
                this.mPosButton.setBackgroundResource(R.drawable.button_b);
            } else {
                JDShadowViewGroup jDShadowViewGroup2 = this.shadowViewGroup;
                if (jDShadowViewGroup2 != null) {
                    jDShadowViewGroup2.setShadowClipCanvas(true);
                }
                this.mPosButton.setBackgroundColor(i2);
            }
        }
        if (i3 == 0) {
            if (this.btnDefaultTextCor == -1) {
                this.btnDefaultTextCor = this.mContext.getResources().getColor(R.color.white);
            }
            i3 = this.btnDefaultTextCor;
        }
        this.mPosButton.setTextColor(i3);
    }

    public void setButtonColor(Drawable drawable, int i2) {
        Button button = this.mPosButton;
        if (button == null || drawable == null) {
            return;
        }
        button.setBackgroundDrawable(drawable);
        if (i2 == 0) {
            if (this.btnDefaultTextCor == -1) {
                this.btnDefaultTextCor = this.mContext.getResources().getColor(R.color.white);
            }
            i2 = this.btnDefaultTextCor;
        }
        this.mPosButton.setTextColor(i2);
    }
}
