package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.app.mall.home.n.g.g;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class FeedsPlusPrice extends AppCompatTextView {
    public FeedsPlusPrice(Context context) {
        super(context);
        FontsUtil.changeTextFont(this, 4099);
        setGravity(16);
        setSingleLine();
        setTextSize(12.0f);
    }

    public void a(g gVar) {
        CharSequence H = gVar.H();
        setTextColor(com.jingdong.app.mall.home.state.dark.a.e(-2236963, -14277082));
        a.a(this, H == null ? 0 : DPIUtil.dip2px(20.0f), -1, H != null ? DPIUtil.dip2px(5.0f) : 0);
        if (H == null) {
            H = "";
        }
        setText(H);
    }
}
