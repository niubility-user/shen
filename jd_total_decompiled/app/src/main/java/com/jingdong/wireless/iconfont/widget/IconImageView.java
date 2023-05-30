package com.jingdong.wireless.iconfont.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.JDIconFontUtil;
import com.jingdong.wireless.iconfont.R;

/* loaded from: classes12.dex */
public class IconImageView extends ImageView {
    private int color;
    private IconImpl icon;
    private String iconFontPath;
    private IconDrawable mDrawable;
    private String src;
    private Typeface typeface;

    public IconImageView(Context context) {
        super(context);
        this.typeface = null;
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.iconfont)) != null) {
            String string = obtainStyledAttributes.getString(R.styleable.iconfont_icon_font_color);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.color = Color.parseColor(string);
                } catch (Exception unused) {
                    this.color = -16777216;
                }
            }
            String string2 = obtainStyledAttributes.getString(R.styleable.iconfont_icon_font_code);
            if (!TextUtils.isEmpty(string2)) {
                this.src = string2;
            }
            this.iconFontPath = obtainStyledAttributes.getString(R.styleable.iconfont_icon_font_path);
            obtainStyledAttributes.recycle();
        }
        String str = TextUtils.isEmpty(this.iconFontPath) ? JDIconFontUtil.COMMON_PATH : this.iconFontPath;
        this.iconFontPath = str;
        this.typeface = JDIconFontUtil.getTypeface(context, str);
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

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    public IconImageView setCharCode(String str) {
        this.src = str;
        refreshView();
        return this;
    }

    public IconImageView setColor(String str) {
        try {
            this.color = Color.parseColor(str);
        } catch (Exception unused) {
            this.color = -16777216;
        }
        refreshView();
        return this;
    }

    public IconImageView setResCode(int i2) {
        this.src = getContext().getString(i2);
        refreshView();
        return this;
    }

    public IconImageView setTypefacePath(String str) {
        this.iconFontPath = str;
        this.typeface = JDIconFontUtil.getTypeface(getContext(), this.iconFontPath);
        refreshView();
        return this;
    }

    public IconImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.typeface = null;
        init(context, attributeSet);
    }

    public IconImageView setColor(int i2) {
        this.color = i2;
        refreshView();
        return this;
    }

    public IconImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.typeface = null;
        init(context, attributeSet);
    }
}
