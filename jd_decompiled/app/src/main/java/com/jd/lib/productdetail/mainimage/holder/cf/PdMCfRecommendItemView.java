package com.jd.lib.productdetail.mainimage.holder.cf;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes15.dex */
public class PdMCfRecommendItemView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    public SimpleDraweeView f4695g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f4696h;

    /* renamed from: i  reason: collision with root package name */
    public TextView f4697i;

    public PdMCfRecommendItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(String str, String str2, String str3) {
        JDImageUtils.displayImage(str, this.f4695g);
        if (!TextUtils.isEmpty(str2)) {
            this.f4696h.setText(str2);
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "\u00a5\u6682\u65e0\u62a5\u4ef7";
        }
        this.f4697i.setText(PDUtils.getJPriceTextForTen(str3, 0.6f));
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4695g = (SimpleDraweeView) findViewById(R.id.image_cf_recommend_item_img);
        this.f4696h = (TextView) findViewById(R.id.image_cf_recommend_item_text);
        TextView textView = (TextView) findViewById(R.id.image_cf_recommend_item_price);
        this.f4697i = textView;
        FontsUtil.changeTextFont(textView, 4099);
    }
}
