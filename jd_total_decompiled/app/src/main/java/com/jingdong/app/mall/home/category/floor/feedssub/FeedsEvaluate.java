package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class FeedsEvaluate extends AppCompatTextView {
    public FeedsEvaluate(Context context) {
        super(context);
        FontsUtil.changeTextFont(this, 4099);
        setGravity(16);
        setTextColor(-7566196);
        setSingleLine();
        setTextSize(10.0f);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public void a(g gVar) {
        String O = gVar.O();
        boolean isEmpty = TextUtils.isEmpty(O);
        a.a(this, isEmpty ? 0 : DPIUtil.dip2px(11.0f), -1, isEmpty ? 0 : DPIUtil.dip2px(5.0f));
        setText(O);
    }
}
