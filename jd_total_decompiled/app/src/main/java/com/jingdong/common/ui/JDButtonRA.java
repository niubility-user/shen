package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class JDButtonRA extends CheckBox implements CompoundButton.OnCheckedChangeListener, IThemeChange {
    private String bottomText;
    private int bottomTextSise;
    private int checkedBottomTextColor;
    private int checkedTopTextColor;
    private GlobalThemeController controller;
    private Context mContext;
    private int normalBottomTextColor;
    private int normalTopTextColor;
    private String topText;
    private int topTextSize;

    public JDButtonRA(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
        initTheme();
    }

    private void init() {
        this.normalTopTextColor = Color.parseColor(JDDarkUtil.COLOR_2E2D2D);
        this.checkedTopTextColor = Color.parseColor("#F0250F");
        this.normalBottomTextColor = Color.parseColor("#6C6C6C");
        this.checkedBottomTextColor = Color.parseColor("#F0250F");
        setBackgroundResource(R.drawable.button_r_b);
        setPadding(DpiUtil.dip2px(this.mContext, 20.0f), 0, DpiUtil.dip2px(this.mContext, 20.0f), 0);
        setOnCheckedChangeListener(this);
    }

    private void initTheme() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
        setBackgroundColor(this.controller.getThemeConfig().a());
        setTextColor(this.controller.getThemeConfig().e());
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        setContentText(this.topText, this.bottomText, this.topTextSize, this.bottomTextSise);
    }

    public void setContentText(String str, String str2, int i2, int i3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || i2 < 0 || i3 < 0) {
            return;
        }
        this.topText = str;
        this.bottomText = str2;
        this.topTextSize = i2;
        this.bottomTextSise = i3;
        String str3 = str + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + str2;
        SpannableString spannableString = new SpannableString(str3);
        int length = str.length() + 1;
        int length2 = str3.length();
        spannableString.setSpan(new AbsoluteSizeSpan(i2, true), 0, length, 33);
        if (isChecked()) {
            spannableString.setSpan(new ForegroundColorSpan(this.checkedTopTextColor), 0, length, 33);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(this.normalTopTextColor), 0, length, 33);
        }
        spannableString.setSpan(new AbsoluteSizeSpan(i3, true), length, length2, 33);
        if (isChecked()) {
            spannableString.setSpan(new ForegroundColorSpan(this.checkedBottomTextColor), length, length2, 33);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(this.normalBottomTextColor), length, length2, 33);
        }
        setText(spannableString);
    }
}
