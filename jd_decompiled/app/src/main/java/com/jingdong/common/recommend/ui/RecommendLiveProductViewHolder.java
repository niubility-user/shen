package com.jingdong.common.recommend.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendLiveProduct;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.utils.JDImageUtils;

/* loaded from: classes6.dex */
public class RecommendLiveProductViewHolder {
    private RecommendLiveProduct product;
    public SimpleDraweeView productImg;
    public TextView productLiveStateText;
    public TextView productNameText;
    public View rootView;

    public RecommendLiveProductViewHolder(View view) {
        this.rootView = view;
        this.productImg = (SimpleDraweeView) RecommendViewUtil.findViewById(view, R.id.recom_live_product_img);
        this.productNameText = (TextView) RecommendViewUtil.findViewById(view, R.id.recom_live_product_name);
        this.productLiveStateText = (TextView) RecommendViewUtil.findViewById(view, R.id.recom_live_product_state);
    }

    public RecommendLiveProduct getProduct() {
        return this.product;
    }

    public void onBindData(int i2, RecommendLiveProduct recommendLiveProduct, int i3) {
        this.product = recommendLiveProduct;
        JDImageUtils.displayImage(recommendLiveProduct.img, this.productImg);
        RecommendViewUtil.setText(this.productNameText, recommendLiveProduct.title);
        if (TextUtils.isEmpty(recommendLiveProduct.label)) {
            RecommendViewUtil.gone(this.productLiveStateText);
            return;
        }
        RecommendViewUtil.visible(this.productLiveStateText);
        RecommendViewUtil.setText(this.productLiveStateText, recommendLiveProduct.label);
        TextView textView = this.productLiveStateText;
        if (textView != null) {
            if (i2 == 1 && i3 == 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.live_state_small, 0, 0, 0);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    public void setGone() {
        RecommendViewUtil.gone(this.rootView);
    }

    public void setVisible() {
        RecommendViewUtil.visible(this.rootView);
    }
}
