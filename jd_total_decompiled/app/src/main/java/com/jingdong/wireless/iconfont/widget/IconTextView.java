package com.jingdong.wireless.iconfont.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.jingdong.wireless.iconfont.JDIconFontUtil;
import com.jingdong.wireless.iconfont.R;

/* loaded from: classes12.dex */
public class IconTextView extends TextView {
    private String fontPath;

    public IconTextView(Context context) {
        this(context, null, 0);
    }

    private void initParams(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.iconfont)) != null) {
            this.fontPath = obtainStyledAttributes.getString(R.styleable.iconfont_icon_font_path);
            obtainStyledAttributes.recycle();
        }
        this.fontPath = TextUtils.isEmpty(this.fontPath) ? JDIconFontUtil.COMMON_PATH : this.fontPath;
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), this.fontPath);
        if (createFromAsset != null) {
            setTypeface(createFromAsset);
        }
    }

    public IconTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initParams(context, attributeSet);
    }
}
