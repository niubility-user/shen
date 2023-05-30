package com.jingdong.common.recommend.forlist;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.WareInfoReason;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendBuyaSeeViewHolder extends BaseRecommendViewHolder {
    RecommendProductViewHolder ProductViewHolder1;
    RecommendProductViewHolder ProductViewHolder2;
    RecommendProductViewHolder ProductViewHolder3;
    RecommendProductViewHolder ProductViewHolder4;
    private View parentLayout;
    private final LinearLayout recommend_buyasee_ll;
    private TextView seedDescribe;
    private SimpleDraweeView seedGoods;
    private LinearLayout titlehead;

    public RecommendBuyaSeeViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.parentLayout = view;
        this.recommend_buyasee_ll = (LinearLayout) view.findViewById(R.id.recommend_buyasee_ll);
        this.seedGoods = (SimpleDraweeView) view.findViewById(R.id.recommend_seed_goods);
        this.seedDescribe = (TextView) view.findViewById(R.id.recommend_seed_hint_tv);
        int width = DPIUtil.getWidth() - DPIUtil.dip2px(3.0f);
        int i2 = width / 2;
        int i3 = width - i2;
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.recommend_buyasee_1);
        if (viewStub != null) {
            View inflate = viewStub.inflate();
            inflate.getLayoutParams().width = i2;
            inflate.requestLayout();
            this.ProductViewHolder1 = new RecommendProductViewHolder(baseActivity, inflate);
        }
        ViewStub viewStub2 = (ViewStub) view.findViewById(R.id.recommend_buyasee_2);
        if (viewStub2 != null) {
            View inflate2 = viewStub2.inflate();
            inflate2.getLayoutParams().width = i3;
            inflate2.requestLayout();
            this.ProductViewHolder2 = new RecommendProductViewHolder(baseActivity, inflate2);
        }
        ViewStub viewStub3 = (ViewStub) view.findViewById(R.id.recommend_buyasee_3);
        if (viewStub3 != null) {
            View inflate3 = viewStub3.inflate();
            inflate3.getLayoutParams().width = i2;
            inflate3.requestLayout();
            this.ProductViewHolder3 = new RecommendProductViewHolder(baseActivity, inflate3);
        }
        ViewStub viewStub4 = (ViewStub) view.findViewById(R.id.recommend_buyasee_4);
        if (viewStub4 != null) {
            View inflate4 = viewStub4.inflate();
            inflate4.getLayoutParams().width = i3;
            inflate4.requestLayout();
            this.ProductViewHolder4 = new RecommendProductViewHolder(baseActivity, inflate4);
        }
        this.titlehead = (LinearLayout) view.findViewById(R.id.titlehead);
    }

    private RecommendProduct getProduct(ArrayList<RecommendProduct> arrayList, int i2) {
        if (i2 + 1 > arrayList.size()) {
            return null;
        }
        return arrayList.get(i2);
    }

    private void putExpos(RecommendProduct recommendProduct, ExpoDataStore expoDataStore) {
        if (recommendProduct != null) {
            expoDataStore.putExpoData(recommendProduct.exposureSourceValue);
            if (TextUtils.isEmpty(recommendProduct.exposureJsonSourceValue)) {
                return;
            }
            expoDataStore.putExpoJsonDada(recommendProduct.exposureJsonSourceValue);
        }
    }

    public void bindRecommendViewHolder(List<WareInfoReason> list, int i2, int i3, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore) {
        if (list == null || list.size() <= i2) {
            return;
        }
        if (i2 == 0) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = DPIUtil.dip2px(5.0f);
            this.recommend_buyasee_ll.setLayoutParams(layoutParams);
            this.recommend_buyasee_ll.requestLayout();
        } else {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.topMargin = DPIUtil.dip2px(20.0f);
            this.recommend_buyasee_ll.setLayoutParams(layoutParams2);
            this.recommend_buyasee_ll.requestLayout();
        }
        if (i2 == list.size() - 1) {
            if (this.parentLayout == null) {
                return;
            }
            this.titlehead.setVisibility(0);
        } else {
            this.titlehead.setVisibility(8);
        }
        ArrayList<RecommendProduct> arrayList = list.get(i2).wareInfoList;
        JDImageUtils.displayImage(list.get(i2).imageUrl, this.seedGoods);
        this.seedDescribe.setText(list.get(i2).reasonDesc);
        this.ProductViewHolder1.setProduct(getProduct(arrayList, 0), -1, null, i3, jDDisplayImageOptions);
        this.ProductViewHolder2.setProduct(getProduct(arrayList, 1), -1, null, i3, jDDisplayImageOptions);
        this.ProductViewHolder3.setProduct(getProduct(arrayList, 2), -1, null, i3, jDDisplayImageOptions);
        this.ProductViewHolder4.setProduct(getProduct(arrayList, 3), -1, null, i3, jDDisplayImageOptions);
        if (expoDataStore != null) {
            putExpos(getProduct(arrayList, 0), expoDataStore);
            putExpos(getProduct(arrayList, 1), expoDataStore);
            putExpos(getProduct(arrayList, 2), expoDataStore);
            putExpos(getProduct(arrayList, 3), expoDataStore);
        }
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setClickedListener(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener) {
        RecommendProductViewHolder recommendProductViewHolder = this.ProductViewHolder1;
        if (recommendProductViewHolder != null) {
            recommendProductViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendProductViewHolder recommendProductViewHolder2 = this.ProductViewHolder2;
        if (recommendProductViewHolder2 != null) {
            recommendProductViewHolder2.setClickedListener(onRecommendClickedListener);
        }
        RecommendProductViewHolder recommendProductViewHolder3 = this.ProductViewHolder3;
        if (recommendProductViewHolder3 != null) {
            recommendProductViewHolder3.setClickedListener(onRecommendClickedListener);
        }
        RecommendProductViewHolder recommendProductViewHolder4 = this.ProductViewHolder4;
        if (recommendProductViewHolder4 != null) {
            recommendProductViewHolder4.setClickedListener(onRecommendClickedListener);
        }
    }
}
