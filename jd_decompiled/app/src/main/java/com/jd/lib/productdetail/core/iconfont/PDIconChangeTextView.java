package com.jd.lib.productdetail.core.iconfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jingdong.wireless.iconfont.IconDrawable;
import com.jingdong.wireless.iconfont.JDIconFontUtil;
import com.jingdong.wireless.iconfont.widget.IconImpl;

/* loaded from: classes15.dex */
public class PDIconChangeTextView extends PdAutoChangeTextSize {
    private int drawableH;
    private int drawableW;
    private String iconFontPath;
    private boolean isUseComFont;

    public PDIconChangeTextView(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet == null || (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.pdiconfont_textview)) == null) {
            return;
        }
        this.isUseComFont = obtainStyledAttributes.getBoolean(R.styleable.pdiconfont_textview_iconDrawableisusecommon, false);
        this.drawableW = obtainStyledAttributes.getDimensionPixelSize(R.styleable.pdiconfont_textview_iconDrawableWidth, 0);
        this.drawableH = obtainStyledAttributes.getDimensionPixelSize(R.styleable.pdiconfont_textview_iconDrawableHeight, 0);
        int color = obtainStyledAttributes.getColor(R.styleable.pdiconfont_textview_iconDrawableColor, Color.parseColor(JDDarkUtil.COLOR_0000000));
        if (this.isUseComFont) {
            this.iconFontPath = JDIconFontUtil.COMMON_PATH;
        } else {
            this.iconFontPath = PDIconFontUtil.PD_ICON_PATH;
        }
        IconDrawable iconDrawable = null;
        IconDrawable iconDrawable2 = null;
        IconDrawable iconDrawable3 = null;
        IconDrawable iconDrawable4 = null;
        int i2 = 0;
        for (int i3 = 0; i3 < obtainStyledAttributes.getIndexCount(); i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            int i4 = R.styleable.pdiconfont_textview_iconDrawableRight;
            if (index == i4) {
                iconDrawable3 = new IconDrawable(context, new IconImpl("", obtainStyledAttributes.getString(i4)), this.iconFontPath).color(color);
                iconDrawable3.setBounds(0, 0, this.drawableW, this.drawableH);
            } else {
                int i5 = R.styleable.pdiconfont_textview_iconDrawableLeft;
                if (index == i5) {
                    iconDrawable = new IconDrawable(context, new IconImpl("", obtainStyledAttributes.getString(i5)), this.iconFontPath).color(color);
                    iconDrawable.setBounds(0, 0, this.drawableW, this.drawableH);
                } else {
                    int i6 = R.styleable.pdiconfont_textview_iconDrawableTop;
                    if (index == i6) {
                        iconDrawable2 = new IconDrawable(context, new IconImpl("", obtainStyledAttributes.getString(i6)), this.iconFontPath).color(color);
                        iconDrawable2.setBounds(0, 0, this.drawableW, this.drawableH);
                    } else {
                        int i7 = R.styleable.pdiconfont_textview_iconDrawableBottom;
                        if (index == i7) {
                            iconDrawable4 = new IconDrawable(context, new IconImpl("", obtainStyledAttributes.getString(i7)), this.iconFontPath).color(color);
                            iconDrawable4.setBounds(0, 0, this.drawableW, this.drawableH);
                        } else if (index == R.styleable.pdiconfont_textview_iconDrawablePadding) {
                            i2 = obtainStyledAttributes.getDimensionPixelSize(index, i2);
                        }
                    }
                }
            }
        }
        obtainStyledAttributes.recycle();
        setCompoundDrawables(iconDrawable, iconDrawable2, iconDrawable3, iconDrawable4);
        setCompoundDrawablePadding(i2);
    }

    public PDIconChangeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }
}
