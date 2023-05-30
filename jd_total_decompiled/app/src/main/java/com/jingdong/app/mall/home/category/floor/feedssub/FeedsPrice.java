package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import android.text.TextUtils;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class FeedsPrice extends AppCompatTextView {
    public FeedsPrice(Context context) {
        super(context);
        FontsUtil.changeTextFont(this, 4099);
        setGravity(16);
        setTextColor(-907508);
        setSingleLine();
        setTextSize(17.0f);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public boolean a(g gVar) {
        CharSequence F = gVar.F();
        boolean z = F == null;
        a.a(this, z ? 0 : -2, -1, 0);
        if (z) {
            F = "";
        }
        setText(F);
        return z;
    }
}
