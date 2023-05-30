package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.utils.LangUtils;

/* loaded from: classes6.dex */
public class JDButtonR extends CheckBox implements CompoundButton.OnCheckedChangeListener, IThemeChange {
    private static final int LINE_NUM = 2;
    private CompoundButton.OnCheckedChangeListener changeListener;
    private String content;
    private SpannableString contentSpan;
    private GlobalThemeController controller;
    private Drawable drawable;
    private Context mContext;
    private SpannableString spannableString;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class VerticalImageSpan extends ImageSpan {
        private boolean isTwoLines;

        public VerticalImageSpan(Drawable drawable, boolean z) {
            super(drawable);
            this.isTwoLines = z;
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            int i7 = (((i6 - i4) - drawable.getBounds().bottom) / 2) + i4;
            if (this.isTwoLines) {
                i7 = (i7 * 2) + DpiUtil.dip2px(JDButtonR.this.mContext, 2.0f);
            }
            canvas.translate(f2, i7);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    public JDButtonR(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
        initTheme();
    }

    private String addTextSpace(String str) {
        if (str.length() == 2) {
            str = str.substring(0, 1) + LangUtils.SINGLE_SPACE + str.substring(1, 2);
        }
        return "  " + str;
    }

    private void init(Context context) {
        this.mContext = context;
        setBackgroundResource(R.drawable.button_r);
        setButtonDrawable(new ColorDrawable(0));
        Resources resources = getResources();
        int i2 = R.color.button_r_font_color;
        if (resources.getColorStateList(i2) instanceof ColorStateList) {
            setTextColor(getResources().getColorStateList(i2));
        }
        Drawable drawable = getResources().getDrawable(R.drawable.button_r_img);
        this.drawable = drawable;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.drawable.getIntrinsicHeight());
        setOnCheckedChangeListener(this);
    }

    private void initTheme() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    private void setTextDrawable(SpannableString spannableString, boolean z) {
        spannableString.setSpan(new VerticalImageSpan(this.drawable, z), 0, 1, 17);
        setText(spannableString);
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
        setBackgroundColor(this.controller.getThemeConfig().a());
        setTextColor(this.controller.getThemeConfig().e());
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        setSelectedIconVisible(z);
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.changeListener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(compoundButton, z);
        }
    }

    public void setChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.changeListener = onCheckedChangeListener;
    }

    public void setContentText(String str) {
        this.content = addTextSpace(str);
        this.contentSpan = null;
        this.spannableString = null;
        setSelectedIconVisible(isChecked());
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setSelectedIconVisible(boolean z) {
        if (z) {
            if (this.content != null) {
                if (this.spannableString == null) {
                    this.spannableString = new SpannableString(this.content);
                }
                setTextDrawable(this.spannableString, false);
                return;
            } else if (this.contentSpan != null) {
                if (this.spannableString == null) {
                    this.spannableString = new SpannableString(this.contentSpan);
                }
                setTextDrawable(this.spannableString, true);
                return;
            } else {
                return;
            }
        }
        String str = this.content;
        if (str != null) {
            setText(str.trim());
            return;
        }
        SpannableString spannableString = this.contentSpan;
        if (spannableString != null) {
            setText(spannableString);
        }
    }

    public void setContentText(String str, String str2, int i2, int i3) {
        String addTextSpace = addTextSpace(str + "\n " + str2);
        this.contentSpan = new SpannableString(addTextSpace);
        int indexOf = addTextSpace.indexOf(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        if (indexOf != -1) {
            this.contentSpan.setSpan(new AbsoluteSizeSpan(i2, true), 0, indexOf, 33);
            this.contentSpan.setSpan(new AbsoluteSizeSpan(i3, true), indexOf, addTextSpace.length(), 33);
        }
        this.content = null;
        this.spannableString = null;
        setSelectedIconVisible(isChecked());
    }
}
