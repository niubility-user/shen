package com.jingdong.app.mall.personel.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class RecommendItem extends RelativeLayout {
    public RecommendItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.personal_home_recommend_item, (ViewGroup) this, true);
        if (isInEditMode()) {
            return;
        }
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.recommend_product_item_img);
        TextView textView = (TextView) findViewById(R.id.axj);
        TextView textView2 = (TextView) findViewById(R.id.axk);
        TextView textView3 = (TextView) findViewById(R.id.axl);
    }
}
