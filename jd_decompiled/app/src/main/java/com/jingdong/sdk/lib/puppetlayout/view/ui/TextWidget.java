package com.jingdong.sdk.lib.puppetlayout.view.ui;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.lib.puppetlayout.e.a;
import com.jingdong.sdk.lib.puppetlayout.g.b;
import com.jingdong.sdk.lib.puppetlayout.ylayout.ColorUtils;

/* loaded from: classes8.dex */
public class TextWidget extends TextView {
    public TextWidget(Context context) {
        super(context);
    }

    public void a(String str) {
        try {
            setTextColor(Color.parseColor(ColorUtils.translate2ArgbColor(str)));
        } catch (Exception e2) {
            if (b.a) {
                e2.printStackTrace();
            }
        }
    }

    public void b(String str) {
        if (str.equals("end")) {
            setEllipsize(TextUtils.TruncateAt.END);
        } else if (str.equals("start")) {
            setEllipsize(TextUtils.TruncateAt.START);
        } else if (str.equals(DYConstants.DY_MIDDLE)) {
            setEllipsize(TextUtils.TruncateAt.MIDDLE);
        }
    }

    public void c(String str) {
        if (str.equals(DYConstants.DY_TRUE)) {
            setIncludeFontPadding(true);
        } else {
            setIncludeFontPadding(false);
        }
    }

    public void d(String str) {
        if (str.equals("bold")) {
            a.a(this, 4097);
        } else if (str.equals(FontsUtil.KEY_MULTI_LIGHT)) {
            a.a(this, 4098);
        } else if (str.equals(FontsUtil.KEY_MULTI_REGULAR)) {
            a.a(this, 4099);
        }
    }

    public void e(String str) {
        str.hashCode();
        if (str.equals("italic")) {
            setTypeface(getTypeface(), 2);
        } else if (str.equals("bold")) {
            setTypeface(getTypeface(), 1);
        }
    }

    public void f(String str) {
        super.setText(str);
    }
}
