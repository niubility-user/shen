package com.jingdong.common.unification.uniwidget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.widget.button.UnButton;

/* loaded from: classes6.dex */
public class UnErrorPageView extends FrameLayout implements OnViewThemeConfig<UnErrorPageView> {
    public static final int COMPLTE_ORDER = 6;
    public static final int COUPON_SUCCESS = 11;
    public static final int EMPTY_ADDRESS = 1;
    public static final int EMPTY_BROWSE = 9;
    public static final int EMPTY_CART = 2;
    public static final int EMPTY_COLLECT = 16;
    public static final int EMPTY_COUPON = 7;
    public static final int EMPTY_EVALUATE = 8;
    public static final int EMPTY_FAVOR = 14;
    public static final int EMPTY_SHEARH = 15;
    public static final int EMPTY_STATUE = 4;
    public static final int ERROR_NETWORK = 3;
    public static final int ERROR_STATE = 10;
    public static final int FAILE_SCANE = 5;
    private View buttonHolderView;
    private UnButton buttonLeft;
    private UnButton buttonRight;
    private ImageView errorIcon;
    private TextView errorTip1;
    private TextView errorTip2;
    private boolean isAutoDarkMode;
    private boolean isAutoElderMode;
    private boolean isDarkMode;
    private boolean isElderMode;
    private LinearLayout rootLayout;
    private int style;
    private boolean unEpvHalfScreen;

    public UnErrorPageView(@NonNull Context context) {
        super(context);
        initView(null);
    }

    private void centerLayout() {
        this.rootLayout.setGravity(17);
        this.rootLayout.setPadding(0, 0, 0, 0);
    }

    private void fullLayout(int i2) {
        this.rootLayout.setGravity(1);
        this.rootLayout.setPadding(0, i2 / 5, 0, 0);
    }

    private Drawable getErrorIcon(boolean z, int i2) {
        int i3;
        switch (i2) {
            case 1:
                if (z) {
                    i3 = R.drawable.y_01_dark;
                    break;
                } else {
                    i3 = R.drawable.y_01;
                    break;
                }
            case 2:
                if (z) {
                    i3 = R.drawable.y_02_dark;
                    break;
                } else {
                    i3 = R.drawable.y_02;
                    break;
                }
            case 3:
                if (z) {
                    i3 = R.drawable.y_03_dark;
                    break;
                } else {
                    i3 = R.drawable.y_03;
                    break;
                }
            case 4:
                if (z) {
                    i3 = R.drawable.y_04_dark;
                    break;
                } else {
                    i3 = R.drawable.y_04;
                    break;
                }
            case 5:
                if (z) {
                    i3 = R.drawable.y_05_dark;
                    break;
                } else {
                    i3 = R.drawable.y_05;
                    break;
                }
            case 6:
                if (z) {
                    i3 = R.drawable.y_06_dark;
                    break;
                } else {
                    i3 = R.drawable.y_06;
                    break;
                }
            case 7:
                if (z) {
                    i3 = R.drawable.y_07_dark;
                    break;
                } else {
                    i3 = R.drawable.y_07;
                    break;
                }
            case 8:
                if (z) {
                    i3 = R.drawable.y_08_dark;
                    break;
                } else {
                    i3 = R.drawable.y_08;
                    break;
                }
            case 9:
                if (z) {
                    i3 = R.drawable.y_09_dark;
                    break;
                } else {
                    i3 = R.drawable.y_09;
                    break;
                }
            case 10:
                if (z) {
                    i3 = R.drawable.y_10_dark;
                    break;
                } else {
                    i3 = R.drawable.y_10;
                    break;
                }
            case 11:
                if (z) {
                    i3 = R.drawable.y_11_dark;
                    break;
                } else {
                    i3 = R.drawable.y_11;
                    break;
                }
            case 12:
                if (z) {
                    i3 = R.drawable.y_12_dark;
                    break;
                } else {
                    i3 = R.drawable.y_12;
                    break;
                }
            case 13:
                if (z) {
                    i3 = R.drawable.y_13_dark;
                    break;
                } else {
                    i3 = R.drawable.y_13;
                    break;
                }
            case 14:
                if (z) {
                    i3 = R.drawable.y_14_dark;
                    break;
                } else {
                    i3 = R.drawable.y_14;
                    break;
                }
            case 15:
                if (z) {
                    i3 = R.drawable.y_15_dark;
                    break;
                } else {
                    i3 = R.drawable.y_15;
                    break;
                }
            case 16:
                if (z) {
                    i3 = R.drawable.y_16_dark;
                    break;
                } else {
                    i3 = R.drawable.y_16;
                    break;
                }
            default:
                if (z) {
                    i3 = R.drawable.y_04_dark;
                    break;
                } else {
                    i3 = R.drawable.y_04;
                    break;
                }
        }
        return getContext().getResources().getDrawable(i3);
    }

    private void initView(AttributeSet attributeSet) {
        FrameLayout.inflate(getContext(), R.layout.un_error_page, this);
        this.errorIcon = (ImageView) findViewById(R.id.errorIcon);
        this.errorTip1 = (TextView) findViewById(R.id.errorTip1);
        this.errorTip2 = (TextView) findViewById(R.id.errorTip2);
        this.buttonLeft = (UnButton) findViewById(R.id.errorButtonLeft);
        this.buttonRight = (UnButton) findViewById(R.id.errorButtonRight);
        this.buttonHolderView = findViewById(R.id.v_holder);
        this.rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.UnErrorPageView);
            this.style = obtainStyledAttributes.getInt(R.styleable.UnErrorPageView_unErrorPageStyle, 4);
            String string = obtainStyledAttributes.getString(R.styleable.UnErrorPageView_unErrorTip1);
            String string2 = obtainStyledAttributes.getString(R.styleable.UnErrorPageView_unErrorTip2);
            String string3 = obtainStyledAttributes.getString(R.styleable.UnErrorPageView_unErrorButtonLeftText);
            String string4 = obtainStyledAttributes.getString(R.styleable.UnErrorPageView_unErrorButtonRightText);
            this.isAutoDarkMode = obtainStyledAttributes.getBoolean(R.styleable.UnErrorPageView_unEpvAutoDark, false);
            this.isAutoElderMode = obtainStyledAttributes.getBoolean(R.styleable.UnErrorPageView_unEpvAutoElder, false);
            this.unEpvHalfScreen = obtainStyledAttributes.getBoolean(R.styleable.UnErrorPageView_unEpvHalfScreen, false);
            if (!TextUtils.isEmpty(string)) {
                this.errorTip1.setVisibility(0);
                this.errorTip1.setText(string);
            }
            if (!TextUtils.isEmpty(string2)) {
                this.errorTip2.setVisibility(0);
                this.errorTip2.setText(string2);
                try {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.errorTip2.getLayoutParams();
                    if (TextUtils.isEmpty(string)) {
                        layoutParams.topMargin = 0;
                        this.errorTip2.setLayoutParams(layoutParams);
                    } else {
                        layoutParams.topMargin = DpiUtil.dip2px(getContext(), 8.0f);
                        this.errorTip2.setLayoutParams(layoutParams);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (!TextUtils.isEmpty(string3)) {
                this.buttonLeft.setVisibility(0);
                this.buttonLeft.setText(string3);
            }
            if (!TextUtils.isEmpty(string4)) {
                this.buttonRight.setVisibility(0);
                this.buttonRight.setText(string4);
                if (TextUtils.isEmpty(string3)) {
                    this.buttonHolderView.setVisibility(8);
                } else {
                    this.buttonHolderView.setVisibility(0);
                }
            }
            ImageView imageView = this.errorIcon;
            if (imageView != null) {
                ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
                if (!this.unEpvHalfScreen && isElderMode()) {
                    layoutParams2.height = DpiUtil.dip2px(getContext(), 160.0f);
                    layoutParams2.width = DpiUtil.dip2px(getContext(), 160.0f);
                } else {
                    layoutParams2.height = DpiUtil.dip2px(getContext(), 120.0f);
                    layoutParams2.width = DpiUtil.dip2px(getContext(), 120.0f);
                }
                this.errorIcon.setLayoutParams(layoutParams2);
            }
        }
        if (isElderMode()) {
            elderMode();
        } else if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
    }

    private void setBgColor(boolean z) {
        if (z) {
            setBackgroundResource(R.color.un_bg_level_2_dark);
        } else {
            setBackgroundResource(R.color.c_ffffff);
        }
    }

    private void setErrorIcon(int i2, boolean z) {
        this.errorIcon.setImageDrawable(getErrorIcon(z, i2));
    }

    private void setTextColor(boolean z) {
        if (isDarkMode()) {
            TextView textView = this.errorTip1;
            Resources resources = getContext().getResources();
            int i2 = R.color.un_content_level_3_new_dark;
            textView.setTextColor(resources.getColor(i2));
            this.errorTip2.setTextColor(getContext().getResources().getColor(i2));
            return;
        }
        TextView textView2 = this.errorTip1;
        Resources resources2 = getContext().getResources();
        int i3 = R.color.un_content_level_4;
        textView2.setTextColor(resources2.getColor(i3));
        this.errorTip2.setTextColor(getContext().getResources().getColor(i3));
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoDarkMode() {
        return this.isAutoDarkMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isAutoElderMode() {
        return this.isAutoElderMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isDarkMode() {
        if (isElderMode()) {
            return false;
        }
        if (this.isAutoDarkMode) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public boolean isElderMode() {
        if (this.isAutoElderMode) {
            return UnWidgetThemeController.getInstance().isElderModel();
        }
        return this.isElderMode;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        centerLayout();
    }

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        if (isElderMode()) {
            elderMode();
        } else if (isDarkMode()) {
            darkMode();
        } else {
            normalMode();
        }
    }

    public void setButtonListener(View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (onClickListener != null) {
            this.buttonLeft.setOnClickListener(onClickListener);
        }
        if (onClickListener2 != null) {
            this.buttonRight.setOnClickListener(onClickListener2);
        }
    }

    public void setButtonText(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.buttonLeft.setVisibility(0);
            this.buttonLeft.setText(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.buttonRight.setVisibility(0);
        this.buttonRight.setText(str2);
        if (TextUtils.isEmpty(str)) {
            this.buttonHolderView.setVisibility(8);
        } else {
            this.buttonHolderView.setVisibility(0);
        }
    }

    public UnErrorPageView setHalfScreen(boolean z) {
        try {
            this.unEpvHalfScreen = z;
            int dip2px = DpiUtil.dip2px(getContext(), 72.0f);
            int dip2px2 = DpiUtil.dip2px(getContext(), 28.0f);
            if (this.unEpvHalfScreen) {
                dip2px = DpiUtil.dip2px(getContext(), 64.0f);
                dip2px2 = DpiUtil.dip2px(getContext(), 24.0f);
            }
            UnButton unButton = this.buttonLeft;
            if (unButton != null && this.buttonRight != null) {
                unButton.getLayoutParams().height = dip2px2;
                this.buttonLeft.getLayoutParams().width = dip2px;
                this.buttonRight.getLayoutParams().height = dip2px2;
                this.buttonRight.getLayoutParams().width = dip2px;
                this.buttonRight.refresh();
                this.buttonLeft.refresh();
            }
            ImageView imageView = this.errorIcon;
            if (imageView != null) {
                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                if (!this.unEpvHalfScreen && isElderMode()) {
                    layoutParams.height = DpiUtil.dip2px(getContext(), 160.0f);
                    layoutParams.width = DpiUtil.dip2px(getContext(), 160.0f);
                    this.errorIcon.setLayoutParams(layoutParams);
                }
                layoutParams.height = DpiUtil.dip2px(getContext(), 120.0f);
                layoutParams.width = DpiUtil.dip2px(getContext(), 120.0f);
                this.errorIcon.setLayoutParams(layoutParams);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this;
    }

    public void setTipText(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.errorTip1.setVisibility(0);
                this.errorTip1.setText(str);
            } else {
                this.errorTip1.setVisibility(8);
            }
            if (!TextUtils.isEmpty(str2)) {
                this.errorTip2.setVisibility(0);
                this.errorTip2.setText(str2);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.errorTip2.getLayoutParams();
                if (TextUtils.isEmpty(str)) {
                    layoutParams.topMargin = 0;
                } else {
                    layoutParams.topMargin = DpiUtil.dip2px(getContext(), 8.0f);
                }
                this.errorTip2.setLayoutParams(layoutParams);
                return;
            }
            this.errorTip2.setVisibility(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnErrorPageView darkMode() {
        setErrorIcon(this.style, true);
        setBgColor(true);
        setTextColor(true);
        this.errorTip1.setTextSize(14.0f);
        this.errorTip2.setTextSize(12.0f);
        this.buttonLeft.setTextSize(12.0f);
        this.buttonRight.setTextSize(12.0f);
        int dip2px = DpiUtil.dip2px(getContext(), 72.0f);
        int dip2px2 = DpiUtil.dip2px(getContext(), 28.0f);
        if (this.unEpvHalfScreen) {
            dip2px = DpiUtil.dip2px(getContext(), 64.0f);
            dip2px2 = DpiUtil.dip2px(getContext(), 24.0f);
        }
        this.buttonLeft.getLayoutParams().height = dip2px2;
        this.buttonLeft.getLayoutParams().width = dip2px;
        this.buttonRight.getLayoutParams().height = dip2px2;
        this.buttonRight.getLayoutParams().width = dip2px;
        this.buttonLeft.setDarkMode(true);
        this.buttonRight.setDarkMode(true);
        this.buttonRight.refresh();
        this.buttonLeft.refresh();
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnErrorPageView elderMode() {
        setErrorIcon(this.style, false);
        setBgColor(false);
        TextView textView = this.errorTip1;
        Resources resources = getContext().getResources();
        int i2 = R.color.un_content_level_2_elder;
        textView.setTextColor(resources.getColor(i2));
        this.errorTip2.setTextColor(getContext().getResources().getColor(i2));
        this.errorTip1.setTextSize(18.0f);
        this.errorTip2.setTextSize(14.0f);
        this.buttonLeft.setTextSize(14.0f);
        this.buttonRight.setTextSize(14.0f);
        this.buttonLeft.getLayoutParams().height = DpiUtil.dip2px(getContext(), 32.0f);
        this.buttonLeft.getLayoutParams().width = DpiUtil.dip2px(getContext(), 80.0f);
        this.buttonRight.getLayoutParams().height = DpiUtil.dip2px(getContext(), 32.0f);
        this.buttonRight.getLayoutParams().width = DpiUtil.dip2px(getContext(), 80.0f);
        this.buttonLeft.setIsElder(true);
        this.buttonRight.setIsElder(true);
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnErrorPageView normalMode() {
        setErrorIcon(this.style, false);
        setBgColor(false);
        setTextColor(false);
        this.errorTip1.setTextSize(14.0f);
        this.errorTip2.setTextSize(12.0f);
        this.buttonLeft.setTextSize(12.0f);
        this.buttonRight.setTextSize(12.0f);
        int dip2px = DpiUtil.dip2px(getContext(), 72.0f);
        int dip2px2 = DpiUtil.dip2px(getContext(), 28.0f);
        if (this.unEpvHalfScreen) {
            dip2px = DpiUtil.dip2px(getContext(), 64.0f);
            dip2px2 = DpiUtil.dip2px(getContext(), 24.0f);
        }
        this.buttonLeft.getLayoutParams().height = dip2px2;
        this.buttonLeft.getLayoutParams().width = dip2px;
        this.buttonRight.getLayoutParams().height = dip2px2;
        this.buttonRight.getLayoutParams().width = dip2px;
        this.buttonLeft.setDarkMode(false);
        this.buttonRight.setDarkMode(false);
        this.buttonRight.refresh();
        this.buttonLeft.refresh();
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnErrorPageView setAutoDarkMode(boolean z) {
        this.isAutoDarkMode = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnErrorPageView setAutoElderMode(boolean z) {
        this.isAutoElderMode = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnErrorPageView setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public UnErrorPageView setElderMode(boolean z) {
        this.isElderMode = z;
        return this;
    }

    public void setErrorIcon(int i2) {
        this.errorIcon.setImageDrawable(getErrorIcon(isDarkMode(), i2));
    }

    public UnErrorPageView(@NonNull Context context, boolean z, boolean z2, boolean z3) {
        super(context);
        this.isAutoDarkMode = z;
        this.isAutoElderMode = z2;
        this.unEpvHalfScreen = z3;
        initView(null);
    }

    public UnErrorPageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(attributeSet);
    }

    public UnErrorPageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(attributeSet);
    }

    @RequiresApi(api = 21)
    public UnErrorPageView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        initView(attributeSet);
    }
}
