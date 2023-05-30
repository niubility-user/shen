package com.jingdong.common.recommend;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.forlist.BaseRecommendMaterialViewHolder;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendInteractionBViewHolder extends BaseRecommendMaterialViewHolder {
    private View dot;
    private int from;
    private SimpleDraweeView imageView0;
    private SimpleDraweeView imageView1;
    private SimpleDraweeView imageView2;
    private SimpleDraweeView imageView3;
    private View leftBottomDot;
    private TextView title0;
    private TextView title1;
    private TextView title2;
    private TextView title3;

    public RecommendInteractionBViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.imageView0 = (SimpleDraweeView) view.findViewById(R.id.recommend_interaction_image0);
        this.imageView1 = (SimpleDraweeView) view.findViewById(R.id.recommend_interaction_image1);
        this.imageView2 = (SimpleDraweeView) view.findViewById(R.id.recommend_interaction_image2);
        this.imageView3 = (SimpleDraweeView) view.findViewById(R.id.recommend_interaction_image3);
        this.title0 = (TextView) view.findViewById(R.id.recommend_interaction_title0);
        this.title1 = (TextView) view.findViewById(R.id.recommend_interaction_title1);
        this.title2 = (TextView) view.findViewById(R.id.recommend_interaction_title2);
        this.title3 = (TextView) view.findViewById(R.id.recommend_interaction_title3);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    public void clickProduct(RecommendProduct recommendProduct) {
        if (recommendProduct == null || this.clickedListener == null) {
            return;
        }
        RecommendMtaUtils.aggregationClickMtaRealize(this.itemView.getContext(), recommendProduct.sourceValue, this.from, recommendProduct.extension_id);
        this.clickedListener.onRecommendJump(recommendProduct.channelJumpUrl, recommendProduct.isOpenApp);
        if (TextUtils.isEmpty(recommendProduct.targetUrl)) {
            return;
        }
        this.clickedListener.onRecommendMoneyExpo(recommendProduct.targetUrl);
    }

    private void setInteractionTitle(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions) {
        List<RecommendProduct> list;
        if (recommendDna == null || (list = recommendDna.wareList) == null || list.size() < 4) {
            return;
        }
        List<RecommendProduct> list2 = recommendDna.wareList;
        setProduct(this.imageView0, this.title0, list2.get(0), jDDisplayImageOptions);
        setProduct(this.imageView1, this.title1, list2.get(1), jDDisplayImageOptions);
        setProduct(this.imageView2, this.title2, list2.get(2), jDDisplayImageOptions);
        setProduct(this.imageView3, this.title3, list2.get(3), jDDisplayImageOptions);
    }

    private void setProduct(SimpleDraweeView simpleDraweeView, TextView textView, final RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        if (recommendProduct == null) {
            return;
        }
        if (!TextUtils.isEmpty(recommendProduct.imgUrl)) {
            JDImageUtils.displayImage(recommendProduct.imgUrl, simpleDraweeView, jDDisplayImageOptions);
        }
        textView.setText(recommendProduct.wareTitle);
        textView.setCompoundDrawablePadding(DpiUtil.dip2px(this.itemView.getContext(), 4.0f));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.recom_arrow_black_right_small, 0);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.RecommendInteractionBViewHolder.1
            {
                RecommendInteractionBViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendInteractionBViewHolder.this.clickProduct(recommendProduct);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.RecommendInteractionBViewHolder.2
            {
                RecommendInteractionBViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendInteractionBViewHolder.this.clickProduct(recommendProduct);
            }
        });
        realExpo(recommendProduct.client_exposal_url, recommendProduct.wareId);
    }

    public void setDna(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i2, int i3) {
        if (recommendDna == null) {
            return;
        }
        this.from = i2;
        setFrom(i2);
        setInteractionTitle(recommendDna, jDDisplayImageOptions);
        this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
        this.leftBottomDot.setVisibility((recommendDna.isShowDot() && recommendDna.isMonetized) ? 0 : 8);
        if (expoDataStore2 != null) {
            if (!TextUtils.isEmpty(recommendDna.exposureJsonSourceValue)) {
                expoDataStore2.putExpoJsonDada(recommendDna.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendDna.exposureSourceValue)) {
                expoDataStore2.putExpoData(recommendDna.exposureSourceValue);
            }
        }
        setMaterialData(recommendDna, i3);
    }
}
