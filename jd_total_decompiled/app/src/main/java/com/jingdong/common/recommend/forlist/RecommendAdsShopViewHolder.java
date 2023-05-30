package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.TextViewCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendAdsShopViewHolder extends BaseRecommendViewHolder {
    private SimpleDraweeView adsShopBg;
    private SimpleDraweeView adsShopButton;
    private TextView adsShopDescription;
    private SimpleDraweeView adsShopIcon;
    private SimpleDraweeView adsShopImg;
    private View.OnClickListener clickListener;
    private View dot;
    private int from;
    private String imageUrl;
    private View leftBottomDot;
    private View rootView;
    private AppCompatTextView shopName;

    public RecommendAdsShopViewHolder(View view) {
        super(view);
        this.imageUrl = null;
        this.clickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendAdsShopViewHolder.1
            {
                RecommendAdsShopViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendDna recommendDna = (RecommendDna) view2.getTag();
                if (recommendDna != null) {
                    RecommendAdsShopViewHolder recommendAdsShopViewHolder = RecommendAdsShopViewHolder.this;
                    if (recommendAdsShopViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendAdsShopViewHolder.itemView.getContext(), recommendDna.sourceValue, RecommendAdsShopViewHolder.this.from, recommendDna.extension_id);
                        RecommendAdsShopViewHolder.this.clickedListener.onRecommendJump(recommendDna.channelJumpUrl, recommendDna.isOpenApp);
                        if (TextUtils.isEmpty(recommendDna.client_click_url)) {
                            return;
                        }
                        RecommendAdsShopViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendDna.client_click_url);
                    }
                }
            }
        };
        this.rootView = view;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_adsshop_bg);
        this.adsShopBg = simpleDraweeView;
        simpleDraweeView.setAspectRatio(this.bgAspectRatio);
        this.adsShopImg = (SimpleDraweeView) view.findViewById(R.id.recommend_adsshop_img);
        this.adsShopIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_adsshop_icon);
        this.adsShopImg.setAspectRatio(1.0f);
        this.shopName = (AppCompatTextView) view.findViewById(R.id.recommend_shop_wname);
        this.adsShopDescription = (TextView) view.findViewById(R.id.recommend_adsshop_description);
        this.adsShopButton = (SimpleDraweeView) view.findViewById(R.id.recommend_adsshop_button);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    private void initTextSize() {
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this.shopName, 13, 16, 1, 2);
            this.adsShopDescription.setTextSize(13.0f);
            return;
        }
        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(this.shopName, 11, 13, 1, 2);
        this.adsShopDescription.setTextSize(11.0f);
    }

    private void setAdsShopBackground(SimpleDraweeView simpleDraweeView, String str) {
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.displayImage(str, simpleDraweeView, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendAdsShopViewHolder.2
                {
                    RecommendAdsShopViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    RecommendAdsShopViewHolder.this.adsShopBg.setImageResource(R.drawable.recommend_adsshop_bg);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        } else {
            this.adsShopBg.setImageResource(R.drawable.recommend_adsshop_bg);
        }
    }

    private void setAdsShopIcon(SimpleDraweeView simpleDraweeView, JDDisplayImageOptions jDDisplayImageOptions, String str) {
        this.adsShopIcon.setVisibility(0);
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.displayImage(str, simpleDraweeView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendAdsShopViewHolder.3
                {
                    RecommendAdsShopViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    int a = com.jd.lib.un.basewidget.widget.simple.e.a.a(30.0f);
                    layoutParams.height = a;
                    layoutParams.width = (width / height) * a;
                    view.requestLayout();
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    RecommendAdsShopViewHolder.this.adsShopIcon.setVisibility(8);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        } else {
            this.adsShopIcon.setVisibility(8);
        }
    }

    private void setGoShopButton(SimpleDraweeView simpleDraweeView, String str) {
        if (!TextUtils.isEmpty(str)) {
            JDImageUtils.displayImage(str, simpleDraweeView, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendAdsShopViewHolder.4
                {
                    RecommendAdsShopViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    RecommendAdsShopViewHolder.this.adsShopButton.setImageResource(R.drawable.recommend_adsshop_goshop);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            });
        } else {
            this.adsShopButton.setImageResource(R.drawable.recommend_adsshop_goshop);
        }
    }

    public void setData(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, int i2) {
        RecommendProduct recommendProduct;
        String str;
        if (recommendDna != null) {
            this.from = i2;
            setFrom(i2);
            if (jDDisplayImageOptions == null) {
                jDDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565);
            }
            initTextSize();
            List<RecommendProduct> list = recommendDna.wareList;
            if (list == null || list.size() <= 0) {
                recommendProduct = null;
            } else {
                recommendProduct = recommendDna.wareList.get(0);
                if (recommendProduct != null && (this.adsShopImg.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl))) {
                    String str2 = recommendProduct.imgUrl;
                    this.imageUrl = str2;
                    JDImageUtils.displayImage(str2, this.adsShopImg, jDDisplayImageOptions);
                }
            }
            setAdsShopBackground(this.adsShopBg, recommendDna.mergePicUrl);
            setAdsShopIcon(this.adsShopIcon, jDDisplayImageOptions, recommendDna.imageurl);
            setGoShopButton(this.adsShopButton, recommendDna.buttonImg);
            if (!TextUtils.isEmpty(recommendDna.dnaName)) {
                this.shopName.setText(recommendDna.dnaName);
            }
            if (!TextUtils.isEmpty(recommendDna.description)) {
                this.adsShopDescription.setText(recommendDna.description);
            }
            this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
            this.leftBottomDot.setVisibility((recommendDna.isShowDot() && recommendDna.isMonetized) ? 0 : 8);
            this.rootView.setTag(recommendDna);
            this.rootView.setOnClickListener(this.clickListener);
            if (expoDataStore != null) {
                if (!TextUtils.isEmpty(recommendDna.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendDna.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendDna.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendDna.exposureSourceValue);
                }
            }
            realExpo(recommendDna.client_exposal_url, recommendProduct != null ? recommendProduct.wareId : null);
        }
    }
}
