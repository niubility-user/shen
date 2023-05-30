package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendSceneLabelViewHolder extends BaseRecommendViewHolder {
    private BaseActivity activity;
    private TextView channelName;
    private SimpleDraweeView checkButton;
    private View.OnClickListener clickListener;
    private int completeNum;
    private TextView description;
    private TextView descriptionMore;
    private View dot;
    private int from;
    private String imageUrl;
    private boolean isLoadFailed;
    private SimpleDraweeView labelBg;
    private View leftBottomDot;
    private SimpleDraweeView productImage;
    private View rootView;
    private int themeColorEnd;
    private int themeColorStart;

    public RecommendSceneLabelViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.imageUrl = null;
        this.isLoadFailed = false;
        this.completeNum = 0;
        this.clickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendSceneLabelViewHolder.1
            {
                RecommendSceneLabelViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendDna recommendDna = (RecommendDna) view2.getTag();
                if (recommendDna != null) {
                    RecommendSceneLabelViewHolder recommendSceneLabelViewHolder = RecommendSceneLabelViewHolder.this;
                    if (recommendSceneLabelViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendSceneLabelViewHolder.itemView.getContext(), recommendDna.sourceValue, RecommendSceneLabelViewHolder.this.from, recommendDna.extension_id);
                        RecommendSceneLabelViewHolder.this.clickedListener.onRecommendJump(recommendDna.channelJumpUrl, recommendDna.isOpenApp);
                        if (TextUtils.isEmpty(recommendDna.client_click_url)) {
                            return;
                        }
                        RecommendSceneLabelViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendDna.client_click_url);
                    }
                }
            }
        };
        this.activity = baseActivity;
        this.rootView = view;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_scene_label_view);
        this.productImage = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.channelName = (TextView) view.findViewById(R.id.recommend_scene_name);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) view.findViewById(R.id.recommend_scene_label_bg);
        this.labelBg = simpleDraweeView2;
        simpleDraweeView2.setAspectRatio(this.bgAspectRatio);
        this.description = (TextView) view.findViewById(R.id.recommend_description);
        this.descriptionMore = (TextView) view.findViewById(R.id.recommend_description_more);
        this.checkButton = (SimpleDraweeView) view.findViewById(R.id.recommend_check_button);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    public void handleLoadImageFailed() {
        this.completeNum++;
        backup();
    }

    private void initTextSize() {
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.channelName.setTextSize(12.0f);
            this.description.setTextSize(18.0f);
            this.descriptionMore.setTextSize(16.0f);
            return;
        }
        this.channelName.setTextSize(10.0f);
        this.description.setTextSize(15.0f);
        this.descriptionMore.setTextSize(13.0f);
    }

    private void setMaterialGradient() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{this.themeColorStart, this.themeColorEnd});
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(9.0f));
        this.channelName.setBackgroundDrawable(gradientDrawable);
    }

    public void backup() {
        if (this.isLoadFailed && this.completeNum == 2) {
            this.labelBg.setImageResource(R.drawable.recommend_scene_label_bottom_bg);
            this.checkButton.setImageResource(R.drawable.recommend_scene_label_button);
        }
    }

    void loadImage(SimpleDraweeView simpleDraweeView, String str, JDDisplayImageOptions jDDisplayImageOptions) {
        if (this.isLoadFailed) {
            this.completeNum++;
            backup();
            return;
        }
        JDImageUtils.displayImage(str, simpleDraweeView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendSceneLabelViewHolder.2
            {
                RecommendSceneLabelViewHolder.this = this;
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                RecommendSceneLabelViewHolder.this.handleLoadImageFailed();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                RecommendSceneLabelViewHolder.this.isLoadFailed = true;
                RecommendSceneLabelViewHolder.this.handleLoadImageFailed();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
            }
        });
    }

    public void setSceneLabelInfo(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i2) {
        RecommendProduct recommendProduct;
        String str;
        if (recommendDna == null) {
            return;
        }
        this.completeNum = 0;
        this.from = i2;
        initTextSize();
        if (jDDisplayImageOptions == null) {
            jDDisplayImageOptions = new JDDisplayImageOptions().bitmapConfig(Bitmap.Config.RGB_565);
        }
        List<RecommendProduct> list = recommendDna.wareList;
        if (list == null || list.size() <= 0) {
            recommendProduct = null;
        } else {
            recommendProduct = recommendDna.wareList.get(0);
            if (recommendProduct != null && (this.productImage.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl))) {
                String str2 = recommendProduct.imgUrl;
                this.imageUrl = str2;
                JDImageUtils.displayImage(str2, this.productImage, jDDisplayImageOptions);
            }
        }
        if (!TextUtils.isEmpty(recommendDna.themeBgcolorStart) && !TextUtils.isEmpty(recommendDna.themeBgcolorEnd)) {
            try {
                this.themeColorStart = Color.parseColor(recommendDna.themeBgcolorStart);
                this.themeColorEnd = Color.parseColor(recommendDna.themeBgcolorEnd);
            } catch (Exception e2) {
                e2.printStackTrace();
                this.themeColorStart = Color.parseColor("#9AE4D4");
                this.themeColorEnd = Color.parseColor("#5CE3C8");
            }
        } else {
            this.themeColorStart = Color.parseColor("#9AE4D4");
            this.themeColorEnd = Color.parseColor("#5CE3C8");
        }
        setMaterialGradient();
        if (!TextUtils.isEmpty(recommendDna.mergePicUrl) && !TextUtils.isEmpty(recommendDna.buttonImg)) {
            loadImage(this.labelBg, recommendDna.mergePicUrl, jDDisplayImageOptions);
            loadImage(this.checkButton, recommendDna.buttonImg, jDDisplayImageOptions);
        } else {
            this.isLoadFailed = true;
            this.completeNum = 2;
            backup();
        }
        if (!TextUtils.isEmpty(recommendDna.dnaName)) {
            this.channelName.setText(recommendDna.dnaName);
        }
        if (!TextUtils.isEmpty(recommendDna.description)) {
            this.description.setText(recommendDna.description);
        }
        if (!TextUtils.isEmpty(recommendDna.descriptionMore)) {
            this.descriptionMore.setText(recommendDna.descriptionMore);
        }
        if (!TextUtils.isEmpty(recommendDna.fontColor)) {
            try {
                this.descriptionMore.setTextColor(Color.parseColor(recommendDna.fontColor));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
        this.leftBottomDot.setVisibility((recommendDna.isShowDot() && recommendDna.isMonetized) ? 0 : 8);
        this.rootView.setTag(recommendDna);
        this.rootView.setOnClickListener(this.clickListener);
        if (expoDataStore2 != null) {
            if (!TextUtils.isEmpty(recommendDna.exposureJsonSourceValue)) {
                expoDataStore2.putExpoJsonDada(recommendDna.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendDna.exposureSourceValue)) {
                expoDataStore2.putExpoData(recommendDna.exposureSourceValue);
            }
        }
        realExpo(recommendDna.client_exposal_url, recommendProduct != null ? recommendProduct.wareId : null);
    }
}
