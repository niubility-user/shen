package com.jingdong.common.recommend.forlist;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.entity.ExpoData;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendPdProductFor2;
import com.jingdong.common.recommend.entity.RecommendProduct;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendPdProduct2ViewHolder extends BaseRecommendViewHolder {
    public Recommend9ProductViewHolder leftViewHolder;
    public Recommend9ProductViewHolder rightViewHolder;

    public RecommendPdProduct2ViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.recommend_pd_product_left);
        ViewStub viewStub2 = (ViewStub) view.findViewById(R.id.recommend_pd_product_right);
        if (viewStub != null) {
            Recommend9ProductViewHolder recommend9ProductViewHolder = new Recommend9ProductViewHolder(baseActivity, viewStub.inflate());
            this.leftViewHolder = recommend9ProductViewHolder;
            recommend9ProductViewHolder.setEnableDelete(false);
        }
        if (viewStub2 != null) {
            Recommend9ProductViewHolder recommend9ProductViewHolder2 = new Recommend9ProductViewHolder(baseActivity, viewStub2.inflate());
            this.rightViewHolder = recommend9ProductViewHolder2;
            recommend9ProductViewHolder2.setEnableDelete(false);
        }
    }

    private void setProduct(RecommendProduct recommendProduct, Recommend9ProductViewHolder recommend9ProductViewHolder, int i2, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        if (!TextUtils.isEmpty(recommendProduct.isSimilarGoods) && "1".equals(recommendProduct.isSimilarGoods)) {
            recommend9ProductViewHolder.setProduct(recommendProduct, i2, expoDataStore2, i3, jDDisplayImageOptions);
        } else {
            recommend9ProductViewHolder.setProduct(recommendProduct, i2, expoDataStore, i3, jDDisplayImageOptions);
        }
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setIsAdRealExpo(boolean z) {
        super.setIsAdRealExpo(z);
        Recommend9ProductViewHolder recommend9ProductViewHolder = this.rightViewHolder;
        if (recommend9ProductViewHolder != null) {
            recommend9ProductViewHolder.setIsAdRealExpo(z);
        }
        Recommend9ProductViewHolder recommend9ProductViewHolder2 = this.leftViewHolder;
        if (recommend9ProductViewHolder2 != null) {
            recommend9ProductViewHolder2.setIsAdRealExpo(z);
        }
    }

    public void setProduct2(RecommendPdProductFor2 recommendPdProductFor2, RecommendItem recommendItem, int i2, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        ArrayList<ExpoData> arrayList;
        ArrayList<ExpoData> arrayList2;
        if (recommendPdProductFor2 != null) {
            RecommendProduct recommendProduct = recommendPdProductFor2.leftProduct;
            RecommendProduct recommendProduct2 = recommendPdProductFor2.rightProduct;
            if (recommendProduct != null) {
                setProduct(recommendProduct, this.leftViewHolder, i2, expoDataStore, expoDataStore2, i3, jDDisplayImageOptions);
                if (recommendItem != null && (arrayList2 = recommendItem.expoDatas) != null && arrayList2.size() > 0) {
                    recommendItem.expoDatas.get(0).exposureSourceValue = recommendProduct.exposureJsonSourceValue;
                }
            }
            if (recommendProduct2 != null) {
                this.rightViewHolder.itemView.setVisibility(0);
                setProduct(recommendProduct2, this.rightViewHolder, i2, expoDataStore, expoDataStore2, i3, jDDisplayImageOptions);
                if (recommendItem == null || (arrayList = recommendItem.expoDatas) == null || arrayList.size() <= 1) {
                    return;
                }
                recommendItem.expoDatas.get(1).exposureSourceValue = recommendProduct2.exposureJsonSourceValue;
                return;
            }
            this.rightViewHolder.itemView.setVisibility(4);
        }
    }
}
