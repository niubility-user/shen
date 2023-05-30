package com.jd.lib.productdetail.core.iconfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.R;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.JDIconFontUtil;
import com.jingdong.wireless.iconfont.widget.IconImpl;

/* loaded from: classes15.dex */
public class PDIconSimpleDraweeView extends SimpleDraweeView {
    private int color;
    private IconImpl icon;
    private String iconFontPath;
    private boolean isUseComFont;
    private IconDrawable mDrawable;
    private String src;
    private Typeface typeface;

    public PDIconSimpleDraweeView(Context context) {
        super(context);
        this.typeface = null;
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.pdiconfont)) != null) {
            String string = obtainStyledAttributes.getString(R.styleable.pdiconfont_icon_font_color);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.color = Color.parseColor(string);
                } catch (Exception unused) {
                    this.color = Color.parseColor(JDDarkUtil.COLOR_0000000);
                }
            }
            String string2 = obtainStyledAttributes.getString(R.styleable.pdiconfont_icon_font_code);
            if (!TextUtils.isEmpty(string2)) {
                this.src = string2;
            }
            this.isUseComFont = obtainStyledAttributes.getBoolean(R.styleable.pdiconfont_icon_font_isusecommon, false);
            obtainStyledAttributes.recycle();
        }
        if (this.isUseComFont) {
            this.iconFontPath = JDIconFontUtil.COMMON_PATH;
        } else {
            this.iconFontPath = PDIconFontUtil.PD_ICON_PATH;
        }
        this.typeface = JDIconFontUtil.getTypeface(context, this.iconFontPath);
        refreshView();
    }

    private void refreshView() {
        if (TextUtils.isEmpty(this.src)) {
            return;
        }
        IconImpl iconImpl = this.icon;
        if (iconImpl == null) {
            this.icon = new IconImpl(null, this.src);
        } else {
            iconImpl.setCharacter(this.src);
        }
        IconDrawable iconDrawable = this.mDrawable;
        if (iconDrawable == null) {
            this.mDrawable = new IconDrawable(getContext(), this.icon, this.typeface).color(this.color);
        } else {
            iconDrawable.typeface(this.typeface).color(this.color).setIcon(this.icon);
        }
        if (this.typeface == null) {
            JDIconFontUtil.sendErrMta(getContext(), this.iconFontPath, this.src);
        }
        setImageDrawable(this.mDrawable);
    }

    public PDIconSimpleDraweeView isUseCommon(boolean z) {
        if (this.isUseComFont) {
            this.iconFontPath = JDIconFontUtil.COMMON_PATH;
        } else {
            this.iconFontPath = PDIconFontUtil.PD_ICON_PATH;
        }
        this.typeface = JDIconFontUtil.getTypeface(getContext(), this.iconFontPath);
        refreshView();
        return this;
    }

    public PDIconSimpleDraweeView setCharCode(String str) {
        this.src = str;
        refreshView();
        return this;
    }

    public PDIconSimpleDraweeView setColor(String str) {
        try {
            this.color = Color.parseColor(str);
        } catch (Exception unused) {
            this.color = -16777216;
        }
        refreshView();
        return this;
    }

    public PDIconSimpleDraweeView setResCode(int i2) {
        this.src = getContext().getString(i2);
        refreshView();
        return this;
    }

    public PDIconSimpleDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.typeface = null;
        init(context, attributeSet);
    }

    public PDIconSimpleDraweeView setColor(int i2) {
        this.color = i2;
        refreshView();
        return this;
    }

    public PDIconSimpleDraweeView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.typeface = null;
        init(context, attributeSet);
    }
}
