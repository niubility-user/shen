package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendAutoFitTextView;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.JDImageUtils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendCartFloorItemViewHolder extends BaseRecommendViewHolder {
    private static final String TAG = "com.jingdong.common.recommend.forlist.RecommendCartFloorItemViewHolder";
    private BaseActivity activity;
    SimpleDraweeView addCar;
    private View dot;
    SimpleDraweeView image;
    String imageUrl;
    TextView name;
    RelativeLayout parentLayout;
    RecommendAutoFitTextView price;
    TextView recommendInfo;

    public RecommendCartFloorItemViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.activity = baseActivity;
        this.parentLayout = (RelativeLayout) view;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_cart_floor_item_img);
        this.image = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.name = (TextView) view.findViewById(R.id.recommend_cart_floor_item_name);
        this.price = (RecommendAutoFitTextView) view.findViewById(R.id.recommend_cart_floor_item_price);
        this.recommendInfo = (TextView) view.findViewById(R.id.recommend_product_item_info);
        this.addCar = (SimpleDraweeView) view.findViewById(R.id.recommend_cart_floor_item_addcar);
        this.dot = view.findViewById(R.id.recommend_dot);
    }

    private void setJdPrice(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.presaleText)) {
            String jdPrice = recommendProduct.getJdPrice();
            if (!TextUtils.equals(this.activity.getString(R.string.recommend_product_no_price), jdPrice)) {
                this.price.setText(this.activity.getResources().getString(R.string.yangjiao) + jdPrice);
                return;
            }
            this.price.setText(jdPrice);
            return;
        }
        this.price.setText(this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.presaleText);
        if (TextUtils.isEmpty(recommendProduct.presaleTextColor)) {
            return;
        }
        this.price.setTextColor(Color.parseColor("#" + recommendProduct.presaleTextColor));
    }

    private void setNameInfo(RecommendProduct recommendProduct) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(recommendProduct.icon1);
        arrayList.add(recommendProduct.icon2);
        this.name.setText(UnIconConfigHelper.getSpanableString(arrayList, recommendProduct.getName(), this.name));
    }

    private void setPreSaleInfo(RecommendProduct recommendProduct) {
        if (!TextUtils.isEmpty(recommendProduct.presaleInfo)) {
            this.recommendInfo.setVisibility(0);
            this.recommendInfo.setText(recommendProduct.presaleInfo);
            return;
        }
        this.recommendInfo.setVisibility(8);
    }

    public void setProduct(final RecommendProduct recommendProduct, final int i2, ExpoDataStore expoDataStore, final int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        String str;
        if (recommendProduct == null) {
            return;
        }
        if (this.image.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl)) {
            String str2 = recommendProduct.imgUrl;
            this.imageUrl = str2;
            JDImageUtils.displayImage(str2, this.image, jDDisplayImageOptions);
        }
        recommendProduct.productItemImage = this.image;
        setNameInfo(recommendProduct);
        setJdPrice(recommendProduct);
        setPreSaleInfo(recommendProduct);
        if (recommendProduct.isShowDot()) {
            this.dot.setVisibility(0);
        } else {
            this.dot.setVisibility(8);
        }
        if (recommendProduct.hasAddCartButton()) {
            this.addCar.setVisibility(0);
            if (TextUtils.isEmpty(recommendProduct.activityBtnUrl)) {
                JDImageUtils.displayImage("res:///" + R.drawable.dyn_pd_addshoppingcart_recommend_gray, this.addCar);
            } else {
                JDImageUtils.displayImage(recommendProduct.activityBtnUrl, this.addCar, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendCartFloorItemViewHolder.1
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str3, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str3, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str3, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.dyn_pd_addshoppingcart_recommend_gray, RecommendCartFloorItemViewHolder.this.addCar);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str3, View view) {
                    }
                });
            }
            this.addCar.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendCartFloorItemViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendCartFloorItemViewHolder.this.clickedListener;
                    if (onRecommendClickedListener != null) {
                        if (i2 == -1) {
                            String str3 = null;
                            int i4 = i3;
                            if (i4 == 6) {
                                str3 = RecommendMtaUtils.Shopcart_Compare_AddtoCart;
                            } else if (i4 == 10) {
                                str3 = RecommendMtaUtils.OrderCenterMyPurchase_FloorAddToCart;
                            }
                            onRecommendClickedListener.onAddCarClick(recommendProduct, str3);
                            return;
                        }
                        onRecommendClickedListener.onAddCarClick(recommendProduct);
                    }
                }
            });
        }
        this.parentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendCartFloorItemViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendCartFloorItemViewHolder.this.clickedListener;
                if (onRecommendClickedListener != null) {
                    if (i2 == -1) {
                        String str3 = null;
                        int i4 = i3;
                        if (i4 == 6) {
                            str3 = RecommendMtaUtils.Shopcart_Compare_Productid;
                        } else if (i4 == 10) {
                            str3 = RecommendMtaUtils.OrderCenterMyPurchase_FloorProduct;
                        }
                        onRecommendClickedListener.onProductClick(recommendProduct, str3);
                        return;
                    }
                    onRecommendClickedListener.onProductClick(recommendProduct);
                }
            }
        });
        if ("-1".equals(recommendProduct.wareId) || expoDataStore == null || recommendProduct.wareId == null) {
            return;
        }
        if (!TextUtils.isEmpty(recommendProduct.exposureJsonSourceValue)) {
            expoDataStore.putExpoJsonDada(recommendProduct.exposureJsonSourceValue);
        } else if (TextUtils.isEmpty(recommendProduct.exposureSourceValue)) {
        } else {
            expoDataStore.putExpoData(recommendProduct.exposureSourceValue);
        }
    }
}
