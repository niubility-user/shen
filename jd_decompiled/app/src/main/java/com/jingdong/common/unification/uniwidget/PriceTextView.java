package com.jingdong.common.unification.uniwidget;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class PriceTextView extends TextView implements IThemeChange {
    private int RMBSP;
    private Context context;
    private GlobalThemeController controller;
    private boolean hasRMBIcon;
    private int lSp;
    private int pointCount;
    private int rSp;

    public PriceTextView(Context context) {
        super(context);
        this.pointCount = -1;
        this.context = context;
        init();
    }

    private String addZeroForString(String str, boolean z, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (z) {
            sb.append(OrderISVUtil.MONEY_DECIMAL);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("0");
        }
        return sb.toString();
    }

    private void init() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
        setTextColor(this.controller.getThemeConfig().e());
    }

    public void setElideLine(boolean z) {
        if (z) {
            getPaint().setFlags(16);
        }
    }

    public void setHasRMBIcon(boolean z) {
        this.hasRMBIcon = z;
    }

    public void setPointCount(int i2) {
        this.pointCount = i2;
    }

    public void setPriceText(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.hasRMBIcon) {
            if (!str.contains("\u00a5")) {
                str = "\u00a5" + str;
            }
        } else if (str.contains("\u00a5")) {
            this.hasRMBIcon = true;
        }
        int i2 = this.pointCount;
        if (i2 != -1 && i2 > 0) {
            String[] split = str.split("\\.");
            if (split.length > 2) {
                return;
            }
            if (split.length == 1) {
                str = addZeroForString(str, true, this.pointCount);
            } else {
                int length = this.pointCount - split[1].length();
                if (length > 0) {
                    str = addZeroForString(str, false, length);
                } else if (length < 0) {
                    str = str.substring(0, split[0].length() + 1 + this.pointCount);
                }
            }
        }
        String[] split2 = str.split("\\.");
        if (split2.length > 2 || split2.length == 0) {
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        if (this.hasRMBIcon) {
            int i3 = this.RMBSP;
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i3 == 0 ? DpiUtil.sp2px(this.context, 14.0f) : DpiUtil.sp2px(this.context, i3)), 0, 1, 33);
        }
        int i4 = this.lSp;
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i4 == 0 ? DpiUtil.sp2px(this.context, 14.0f) : DpiUtil.sp2px(this.context, i4)), this.hasRMBIcon ? 1 : 0, split2[0].length(), 33);
        if (split2.length == 2) {
            int i5 = this.rSp;
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i5 == 0 ? DpiUtil.sp2px(this.context, 14.0f) : DpiUtil.sp2px(this.context, i5)), split2[0].length() + 1, str.length(), 33);
        }
        setText(spannableStringBuilder);
    }

    public void setRMBSP(int i2) {
        this.RMBSP = i2;
    }

    public void setlSp(int i2) {
        this.lSp = i2;
    }

    public void setrSp(int i2) {
        this.rSp = i2;
    }

    public PriceTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.pointCount = -1;
        this.context = context;
        init();
    }

    public PriceTextView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.pointCount = -1;
        this.context = context;
        init();
    }
}
