package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class FeedsTitle extends AppCompatTextView {
    public FeedsTitle(Context context) {
        super(context);
        setMaxLines(2);
        setLineSpacing(0.0f, 1.15f);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public void a(g gVar) {
        CharSequence R = gVar.R();
        setTextColor(com.jingdong.app.mall.home.state.dark.a.e(-1250068, -14277082));
        a.a(this, R == null ? 0 : -2, -1, DPIUtil.dip2px(10.0f));
        setTextSize(13.0f);
        if (R == null) {
            R = "";
        }
        setText(R);
    }
}
