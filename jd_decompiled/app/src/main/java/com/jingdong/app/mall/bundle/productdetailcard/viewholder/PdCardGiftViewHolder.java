package com.jingdong.app.mall.bundle.productdetailcard.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.productdetailcard.R;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardFloorInfo;
import com.jingdong.app.mall.bundle.productdetailcard.entity.PdCardGiftData;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes3.dex */
public class PdCardGiftViewHolder extends RecyclerView.ViewHolder implements a {

    /* renamed from: g  reason: collision with root package name */
    private final TextView f8254g;

    /* renamed from: h  reason: collision with root package name */
    private final LinearLayout f8255h;

    /* renamed from: i  reason: collision with root package name */
    private Context f8256i;

    public PdCardGiftViewHolder(Context context, @NonNull View view) {
        super(view);
        this.f8256i = context;
        this.f8254g = (TextView) view.findViewById(R.id.productdetailcard_gift_title_tv);
        this.f8255h = (LinearLayout) view.findViewById(R.id.productdetailcard_gift_item_layout);
    }

    @Override // com.jingdong.app.mall.bundle.productdetailcard.viewholder.a
    public void a(PdCardFloorInfo pdCardFloorInfo) {
        PdCardGiftData pdCardGiftData;
        if (pdCardFloorInfo != null) {
            if (!TextUtils.isEmpty(pdCardFloorInfo.title)) {
                this.f8254g.setText(pdCardFloorInfo.title);
            }
            this.f8255h.removeAllViews();
            Object obj = pdCardFloorInfo.data;
            if (!(obj instanceof JDJSONObject) || (pdCardGiftData = (PdCardGiftData) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdCardGiftData.class)) == null || pdCardGiftData.giftInfo == null) {
                return;
            }
            for (int i2 = 0; i2 < pdCardGiftData.giftInfo.size(); i2++) {
                TextView textView = new TextView(this.f8256i);
                textView.setTextSize(13.0f);
                textView.setTextColor(Color.parseColor(JDDarkUtil.COLOR_262626));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                textView.setMaxLines(2);
                textView.setText(pdCardGiftData.giftInfo.get(i2));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                if (i2 > 0) {
                    layoutParams.topMargin = DPIUtil.dip2px(10.0f);
                }
                this.f8255h.addView(textView, layoutParams);
            }
        }
    }
}
