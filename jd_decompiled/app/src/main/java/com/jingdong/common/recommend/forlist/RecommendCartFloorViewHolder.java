package com.jingdong.common.recommend.forlist;

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
public class RecommendCartFloorViewHolder extends BaseRecommendViewHolder {
    RecommendCartFloorItemViewHolder ProductViewHolder1;
    RecommendCartFloorItemViewHolder ProductViewHolder2;
    RecommendCartFloorItemViewHolder ProductViewHolder3;
    RecommendCartFloorItemViewHolder ProductViewHolder4;
    RecommendCartFloorItemViewHolder ProductViewHolder5;
    RecommendCartFloorItemViewHolder ProductViewHolder6;
    private View parentLayout;
    private final LinearLayout recommend_buyasee_ll;
    private TextView seedDescribe;
    private SimpleDraweeView seedGoods;
    private LinearLayout space;
    private LinearLayout titlehead;

    public RecommendCartFloorViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.parentLayout = view;
        this.recommend_buyasee_ll = (LinearLayout) view.findViewById(R.id.recommend_buyasee_ll);
        this.seedGoods = (SimpleDraweeView) view.findViewById(R.id.recommend_seed_goods);
        this.seedDescribe = (TextView) view.findViewById(R.id.recommend_seed_hint_tv);
        this.space = (LinearLayout) view.findViewById(R.id.recommend_cart_floor_space);
        int width = (DPIUtil.getWidth() - (DPIUtil.dip2px(3.0f) * 2)) / 3;
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.recommend_cart_floor_item_1);
        if (viewStub != null) {
            View inflate = viewStub.inflate();
            inflate.getLayoutParams().width = width;
            inflate.requestLayout();
            this.ProductViewHolder1 = new RecommendCartFloorItemViewHolder(baseActivity, inflate);
        }
        ViewStub viewStub2 = (ViewStub) view.findViewById(R.id.recommend_cart_floor_item_2);
        if (viewStub2 != null) {
            View inflate2 = viewStub2.inflate();
            inflate2.getLayoutParams().width = width;
            inflate2.requestLayout();
            this.ProductViewHolder2 = new RecommendCartFloorItemViewHolder(baseActivity, inflate2);
        }
        ViewStub viewStub3 = (ViewStub) view.findViewById(R.id.recommend_cart_floor_item_3);
        if (viewStub3 != null) {
            View inflate3 = viewStub3.inflate();
            inflate3.getLayoutParams().width = width;
            inflate3.requestLayout();
            this.ProductViewHolder3 = new RecommendCartFloorItemViewHolder(baseActivity, inflate3);
        }
        ViewStub viewStub4 = (ViewStub) view.findViewById(R.id.recommend_cart_floor_item_4);
        if (viewStub4 != null) {
            View inflate4 = viewStub4.inflate();
            inflate4.getLayoutParams().width = width;
            inflate4.requestLayout();
            this.ProductViewHolder4 = new RecommendCartFloorItemViewHolder(baseActivity, inflate4);
        }
        ViewStub viewStub5 = (ViewStub) view.findViewById(R.id.recommend_cart_floor_item_5);
        if (viewStub5 != null) {
            View inflate5 = viewStub5.inflate();
            inflate5.getLayoutParams().width = width;
            inflate5.requestLayout();
            this.ProductViewHolder5 = new RecommendCartFloorItemViewHolder(baseActivity, inflate5);
        }
        ViewStub viewStub6 = (ViewStub) view.findViewById(R.id.recommend_cart_floor_item_6);
        if (viewStub6 != null) {
            View inflate6 = viewStub6.inflate();
            inflate6.getLayoutParams().width = width;
            inflate6.requestLayout();
            this.ProductViewHolder6 = new RecommendCartFloorItemViewHolder(baseActivity, inflate6);
        }
        this.titlehead = (LinearLayout) view.findViewById(R.id.titlehead);
    }

    private RecommendProduct getProduct(ArrayList<RecommendProduct> arrayList, int i2) {
        if (i2 + 1 > arrayList.size()) {
            return null;
        }
        return arrayList.get(i2);
    }

    public void bindRecommendViewHolder(List<WareInfoReason> list, int i2, int i3, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore) {
        if (list == null || list.size() <= i2) {
            return;
        }
        if (i2 == list.size() - 1) {
            if (this.parentLayout == null) {
                return;
            }
            this.space.setVisibility(8);
            this.titlehead.setVisibility(0);
        } else {
            this.titlehead.setVisibility(8);
            this.space.setVisibility(0);
        }
        ArrayList<RecommendProduct> arrayList = list.get(i2).wareInfoList;
        JDImageUtils.displayImage(list.get(i2).imageUrl, this.seedGoods);
        this.seedDescribe.setText(list.get(i2).reasonDesc);
        this.ProductViewHolder1.setProduct(getProduct(arrayList, 0), -1, expoDataStore, i3, jDDisplayImageOptions);
        this.ProductViewHolder2.setProduct(getProduct(arrayList, 1), -1, expoDataStore, i3, jDDisplayImageOptions);
        this.ProductViewHolder3.setProduct(getProduct(arrayList, 2), -1, expoDataStore, i3, jDDisplayImageOptions);
        this.ProductViewHolder4.setProduct(getProduct(arrayList, 3), -1, expoDataStore, i3, jDDisplayImageOptions);
        this.ProductViewHolder5.setProduct(getProduct(arrayList, 4), -1, expoDataStore, i3, jDDisplayImageOptions);
        this.ProductViewHolder6.setProduct(getProduct(arrayList, 5), -1, expoDataStore, i3, jDDisplayImageOptions);
    }

    @Override // com.jingdong.common.recommend.forlist.BaseRecommendViewHolder
    public void setClickedListener(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener) {
        RecommendCartFloorItemViewHolder recommendCartFloorItemViewHolder = this.ProductViewHolder1;
        if (recommendCartFloorItemViewHolder != null) {
            recommendCartFloorItemViewHolder.setClickedListener(onRecommendClickedListener);
        }
        RecommendCartFloorItemViewHolder recommendCartFloorItemViewHolder2 = this.ProductViewHolder2;
        if (recommendCartFloorItemViewHolder2 != null) {
            recommendCartFloorItemViewHolder2.setClickedListener(onRecommendClickedListener);
        }
        RecommendCartFloorItemViewHolder recommendCartFloorItemViewHolder3 = this.ProductViewHolder3;
        if (recommendCartFloorItemViewHolder3 != null) {
            recommendCartFloorItemViewHolder3.setClickedListener(onRecommendClickedListener);
        }
        RecommendCartFloorItemViewHolder recommendCartFloorItemViewHolder4 = this.ProductViewHolder4;
        if (recommendCartFloorItemViewHolder4 != null) {
            recommendCartFloorItemViewHolder4.setClickedListener(onRecommendClickedListener);
        }
        RecommendCartFloorItemViewHolder recommendCartFloorItemViewHolder5 = this.ProductViewHolder5;
        if (recommendCartFloorItemViewHolder5 != null) {
            recommendCartFloorItemViewHolder5.setClickedListener(onRecommendClickedListener);
        }
        RecommendCartFloorItemViewHolder recommendCartFloorItemViewHolder6 = this.ProductViewHolder6;
        if (recommendCartFloorItemViewHolder6 != null) {
            recommendCartFloorItemViewHolder6.setClickedListener(onRecommendClickedListener);
        }
    }
}
