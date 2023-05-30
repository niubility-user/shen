package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.OnViewThemeConfig;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class NotificationBarView extends LinearLayout implements OnViewThemeConfig<NotificationBarView> {
    private FrameLayout flIvLeftContainer;
    private FrameLayout flIvRightContainer;
    private boolean isAutoDarkMode;
    private boolean isAutoElderMode;
    private boolean isDarkMode;
    private boolean isElderMode;
    private ImageView ivLeft;
    private ImageView ivRight;
    private RelativeLayout llRoot;
    private Context mContext;
    private int styleType;
    private TextView tvContent;

    public NotificationBarView(Context context) {
        this(context, null);
    }

    private Drawable getRedBell() {
        int i2;
        if (isDarkMode()) {
            i2 = R.drawable.jd_common_notify_red_bell_dark;
        } else {
            i2 = R.drawable.jd_common_notify_red_bell;
        }
        return getContext().getResources().getDrawable(i2);
    }

    private Drawable getRedClose() {
        int i2;
        if (isDarkMode()) {
            i2 = R.drawable.jd_common_notify_red_close_dark;
        } else {
            i2 = R.drawable.jd_common_notify_red_close;
        }
        return getContext().getResources().getDrawable(i2);
    }

    private int getTextColor(boolean z) {
        int i2;
        if (z) {
            i2 = isDarkMode() ? R.color.un_content_level_1_dark : R.color.un_content_level_1;
        } else {
            i2 = isDarkMode() ? R.color.un_notify_dark_bg_text_color_dark : R.color.c_D9500B;
        }
        return getContext().getResources().getColor(i2);
    }

    private Drawable getWhiteClose() {
        int i2;
        if (isDarkMode()) {
            i2 = R.drawable.jd_common_notify_close_dark;
        } else {
            i2 = R.drawable.jd_common_notify_close;
        }
        return getContext().getResources().getDrawable(i2);
    }

    private int getWhiteRectBg() {
        if (isDarkMode()) {
            return R.drawable.bg_notify_view_white_rect_dark;
        }
        return R.drawable.bg_notify_view_white_rect;
    }

    private int getWhiteRoundBg() {
        if (isDarkMode()) {
            return R.drawable.bg_notify_view_white_round_rect_dark;
        }
        return R.drawable.bg_notify_view_white_round_rect;
    }

    private int getYelloRectBg() {
        if (isDarkMode()) {
            return R.drawable.bg_notify_view_yellow_rect_dark;
        }
        return R.drawable.bg_notify_view_yellow_rect;
    }

    private int getYelloRectBottomBg() {
        if (isDarkMode()) {
            return R.drawable.bg_notify_view_yellow_rect_bottom_dark;
        }
        return R.drawable.bg_notify_view_yellow_rect_bottom;
    }

    private int getYelloRoundBg() {
        if (isDarkMode()) {
            return R.drawable.bg_notify_view_yellow_round_rect_dark;
        }
        return R.drawable.bg_notify_view_yellow_round_rect;
    }

    private int getYelloRoundBottomBg() {
        if (isDarkMode()) {
            return R.drawable.bg_notify_view_yellow_round_rect_bottom_dark;
        }
        return R.drawable.bg_notify_view_yellow_round_rect_bottom;
    }

    private void initStyle() {
        setTvContentSize();
        int i2 = this.styleType;
        if (i2 == 1) {
            changeStyle01();
        } else if (i2 == 2) {
            changeStyle02();
        } else if (i2 == 3) {
            changeStyle03();
        } else if (i2 == 4) {
            changeStyle04();
        } else if (i2 == 5) {
            changeStyle05();
        } else if (i2 == 6) {
            changeStyle06();
        } else if (i2 == 7) {
            changeStyle07();
        } else if (i2 == 8) {
            changeStyle08();
        }
    }

    private void setNotifyViewBg(Drawable drawable, int i2) {
        this.llRoot.setBackgroundDrawable(drawable);
        setTvContentColor(i2);
    }

    private void setTvContentColor(int i2) {
        this.tvContent.setTextColor(i2);
    }

    private void setTvContentSize() {
        if (isElderMode()) {
            this.tvContent.setTextSize(14.0f);
        } else {
            this.tvContent.setTextSize(12.0f);
        }
    }

    public void changeStyle01() {
        Drawable drawable = this.mContext.getResources().getDrawable(getYelloRectBottomBg());
        int textColor = getTextColor(false);
        setSingleLine(true);
        setNotifyViewBg(drawable, textColor);
        setIconsDrawable(getRedBell(), getRedClose());
    }

    public void changeStyle02() {
        Drawable drawable = this.mContext.getResources().getDrawable(getYelloRectBottomBg());
        int textColor = getTextColor(false);
        setSingleLine(false);
        setNotifyViewBg(drawable, textColor);
        setIconsDrawable(getRedBell(), getRedClose());
    }

    public void changeStyle03() {
        Drawable drawable = this.mContext.getResources().getDrawable(getYelloRoundBg());
        int textColor = getTextColor(false);
        setSingleLine(true);
        setNotifyViewBg(drawable, textColor);
        setIconsDrawable(getRedBell(), getRedClose());
    }

    public void changeStyle04() {
        Drawable drawable = this.mContext.getResources().getDrawable(getWhiteRoundBg());
        int textColor = getTextColor(true);
        setSingleLine(true);
        setNotifyViewBg(drawable, textColor);
        setIconsDrawable(null, getWhiteClose());
    }

    public void changeStyle05() {
        Drawable drawable = this.mContext.getResources().getDrawable(getYelloRoundBg());
        int textColor = getTextColor(false);
        setSingleLine(false);
        setNotifyViewBg(drawable, textColor);
    }

    public void changeStyle06() {
        Drawable drawable = this.mContext.getResources().getDrawable(getWhiteRoundBg());
        int textColor = getTextColor(true);
        setSingleLine(false);
        setNotifyViewBg(drawable, textColor);
        setIconsDrawable(null, getWhiteClose());
    }

    public void changeStyle07() {
        Drawable drawable = this.mContext.getResources().getDrawable(getYelloRectBottomBg());
        int textColor = getTextColor(false);
        setSingleLine(true);
        setNotifyViewBg(drawable, textColor);
    }

    public void changeStyle08() {
        Drawable drawable = this.mContext.getResources().getDrawable(getYelloRectBottomBg());
        int textColor = getTextColor(false);
        setSingleLine(false);
        setNotifyViewBg(drawable, textColor);
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

    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public void refresh() {
        initStyle();
    }

    public void setBg(boolean z, boolean z2) {
        Drawable drawable;
        int textColor;
        if (z) {
            Context context = this.mContext;
            drawable = z2 ? context.getResources().getDrawable(getWhiteRoundBg()) : context.getResources().getDrawable(getWhiteRectBg());
            textColor = getTextColor(true);
        } else {
            Context context2 = this.mContext;
            drawable = z2 ? context2.getResources().getDrawable(getYelloRoundBg()) : context2.getResources().getDrawable(getYelloRectBg());
            textColor = getTextColor(false);
        }
        setNotifyViewBg(drawable, textColor);
    }

    public void setContent(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.tvContent.setText(str);
    }

    public void setIconsDrawable(Drawable drawable, Drawable drawable2) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.tvContent.getLayoutParams();
        if (drawable == null) {
            this.flIvLeftContainer.setVisibility(8);
        } else {
            this.flIvLeftContainer.setVisibility(0);
            this.ivLeft.setVisibility(0);
            this.ivLeft.setImageDrawable(drawable);
        }
        if (drawable2 == null) {
            this.flIvRightContainer.setVisibility(8);
        } else {
            this.flIvRightContainer.setVisibility(0);
            this.ivRight.setVisibility(0);
            this.ivRight.setImageDrawable(drawable2);
        }
        this.tvContent.setLayoutParams(layoutParams);
    }

    public void setIvLeftClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            return;
        }
        this.flIvLeftContainer.setOnClickListener(onClickListener);
    }

    public void setIvRightClickListener(View.OnClickListener onClickListener) {
        if (onClickListener == null) {
            return;
        }
        this.flIvRightContainer.setOnClickListener(onClickListener);
    }

    public void setSingleLine(boolean z) {
        if (z) {
            this.tvContent.setSingleLine(true);
            this.llRoot.setPadding(DpiUtil.dip2px(getContext(), 12.0f), DpiUtil.dip2px(getContext(), 11.0f), DpiUtil.dip2px(getContext(), 12.0f), DpiUtil.dip2px(getContext(), 11.0f));
            return;
        }
        this.tvContent.setSingleLine(false);
        this.tvContent.setMaxLines(2);
        this.llRoot.setPadding(DpiUtil.dip2px(getContext(), 12.0f), DpiUtil.dip2px(getContext(), 8.0f), DpiUtil.dip2px(getContext(), 12.0f), DpiUtil.dip2px(getContext(), 8.0f));
    }

    public NotificationBarView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public NotificationBarView darkMode() {
        initStyle();
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public NotificationBarView elderMode() {
        initStyle();
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public NotificationBarView normalMode() {
        initStyle();
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public NotificationBarView setAutoDarkMode(boolean z) {
        this.isAutoDarkMode = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public NotificationBarView setAutoElderMode(boolean z) {
        this.isAutoElderMode = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public NotificationBarView setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jd.lib.un.global.theme.OnViewThemeConfig
    public NotificationBarView setElderMode(boolean z) {
        this.isElderMode = z;
        return this;
    }

    public NotificationBarView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.styleType = 1;
        LayoutInflater.from(context).inflate(R.layout.jd_common_notification_bar_view, (ViewGroup) this, true);
        this.mContext = context;
        this.llRoot = (RelativeLayout) findViewById(R.id.ll_root);
        this.flIvLeftContainer = (FrameLayout) findViewById(R.id.fl_iv_left_container);
        this.ivLeft = (ImageView) findViewById(R.id.iv_left);
        this.tvContent = (TextView) findViewById(R.id.f5682tv);
        this.flIvRightContainer = (FrameLayout) findViewById(R.id.fl_iv_right_container);
        this.ivRight = (ImageView) findViewById(R.id.iv_right);
        setBg(true, true);
        TypedArray obtainStyledAttributes = attributeSet == null ? null : getContext().obtainStyledAttributes(attributeSet, R.styleable.NotificationBarView);
        if (obtainStyledAttributes != null) {
            this.styleType = obtainStyledAttributes.getInt(R.styleable.NotificationBarView_unStyleType, 1);
            String string = obtainStyledAttributes.getString(R.styleable.NotificationBarView_unContent);
            this.isAutoDarkMode = obtainStyledAttributes.getBoolean(R.styleable.NotificationBarView_unNotiAutoDarkMode, false);
            this.isAutoElderMode = obtainStyledAttributes.getBoolean(R.styleable.NotificationBarView_unNotiAutoElderMode, false);
            if (!TextUtils.isEmpty(string)) {
                this.tvContent.setText(string);
            }
            initStyle();
        }
    }
}
