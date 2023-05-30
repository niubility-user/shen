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
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendDna;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendChannelViewHolder extends BaseRecommendViewHolder {
    public SimpleDraweeView channelBg;
    public SimpleDraweeView channelNameIcon;
    public TextView channelNameTv;
    private int completeNum;
    public TextView desMoreTv;
    public TextView desTv;
    private View dot;
    private int from;
    private String imageUrl;
    private boolean isLoadFailed;
    private View leftBottomDot;
    private View.OnClickListener onChannelClickListener;
    public SimpleDraweeView productImageView;
    private int themeColorEnd;
    private int themeColorStart;

    public RecommendChannelViewHolder(View view) {
        super(view);
        this.isLoadFailed = false;
        this.completeNum = 0;
        this.onChannelClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendChannelViewHolder.1
            {
                RecommendChannelViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendDna recommendDna = (RecommendDna) view2.getTag();
                if (recommendDna != null) {
                    RecommendChannelViewHolder recommendChannelViewHolder = RecommendChannelViewHolder.this;
                    if (recommendChannelViewHolder.clickedListener != null) {
                        RecommendMtaUtils.aggregationClickMtaRealize(recommendChannelViewHolder.itemView.getContext(), recommendDna.sourceValue, RecommendChannelViewHolder.this.from, recommendDna.extension_id);
                        RecommendChannelViewHolder.this.clickedListener.onRecommendChannelJump(recommendDna);
                    }
                }
            }
        };
        initView(view);
    }

    static /* synthetic */ int access$208(RecommendChannelViewHolder recommendChannelViewHolder) {
        int i2 = recommendChannelViewHolder.completeNum;
        recommendChannelViewHolder.completeNum = i2 + 1;
        return i2;
    }

    private void initTextSize() {
        if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.channelNameTv.setTextSize(17.0f);
            this.desTv.setTextSize(16.0f);
            this.desMoreTv.setTextSize(16.0f);
            return;
        }
        this.channelNameTv.setTextSize(14.0f);
        this.desTv.setTextSize(13.0f);
        this.desMoreTv.setTextSize(13.0f);
    }

    private void loadImage(SimpleDraweeView simpleDraweeView, String str, JDDisplayImageOptions jDDisplayImageOptions) {
        if (this.isLoadFailed) {
            this.completeNum++;
            backup();
            return;
        }
        JDImageUtils.displayImage(str, simpleDraweeView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendChannelViewHolder.2
            {
                RecommendChannelViewHolder.this = this;
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingCancelled(String str2, View view) {
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                RecommendChannelViewHolder.access$208(RecommendChannelViewHolder.this);
                RecommendChannelViewHolder.this.backup();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                RecommendChannelViewHolder.this.isLoadFailed = true;
                RecommendChannelViewHolder.access$208(RecommendChannelViewHolder.this);
                RecommendChannelViewHolder.this.backup();
            }

            @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingStarted(String str2, View view) {
            }
        });
    }

    private void setMaterialGradient() {
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[]{this.themeColorStart, this.themeColorEnd});
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(15.0f));
        this.channelNameTv.setBackgroundDrawable(gradientDrawable);
    }

    public void backup() {
        if (this.isLoadFailed && this.completeNum == 2) {
            this.channelBg.setImageResource(R.drawable.recommend_channel_bg);
            this.channelNameIcon.setImageResource(R.drawable.recommend_channel_name_icon);
        }
    }

    public void initView(View view) {
        this.productImageView = (SimpleDraweeView) view.findViewById(R.id.recommend_channel_product_image);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_channel_bg);
        this.channelBg = simpleDraweeView;
        simpleDraweeView.setAspectRatio(this.bgAspectRatio);
        this.channelNameIcon = (SimpleDraweeView) view.findViewById(R.id.recommend_channel_name_icon);
        this.channelNameTv = (TextView) view.findViewById(R.id.recommend_channel_name);
        this.desTv = (TextView) view.findViewById(R.id.recommend_channel_description);
        this.desMoreTv = (TextView) view.findViewById(R.id.recommend_channel_description_more);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.productImageView.setAspectRatio(1.0f);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
    }

    public void setDna(RecommendDna recommendDna, JDDisplayImageOptions jDDisplayImageOptions, ExpoDataStore expoDataStore, ExpoDataStore expoDataStore2, int i2) {
        RecommendProduct recommendProduct;
        String str;
        if (recommendDna == null) {
            return;
        }
        this.from = i2;
        this.completeNum = 0;
        this.isLoadFailed = false;
        initTextSize();
        List<RecommendProduct> list = recommendDna.wareList;
        if (list == null || list.isEmpty()) {
            recommendProduct = null;
        } else {
            recommendProduct = recommendDna.wareList.get(0);
            if (recommendProduct != null && (this.productImageView.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl))) {
                String str2 = recommendProduct.imgUrl;
                this.imageUrl = str2;
                JDImageUtils.displayImage(str2, this.productImageView, jDDisplayImageOptions);
            }
        }
        if (!TextUtils.isEmpty(recommendDna.mergePicUrl) && !TextUtils.isEmpty(recommendDna.nonWareIcon)) {
            loadImage(this.channelBg, recommendDna.mergePicUrl, jDDisplayImageOptions);
            loadImage(this.channelNameIcon, recommendDna.nonWareIcon, jDDisplayImageOptions);
        } else {
            this.isLoadFailed = true;
            this.completeNum = 2;
            backup();
        }
        String str3 = recommendDna.dnaName;
        String str4 = recommendDna.descriptionMore;
        if (str3 != null && str3.length() > 6) {
            str3 = str3.substring(0, 6);
        }
        this.channelNameTv.setText(str3);
        if (!TextUtils.isEmpty(recommendDna.themeBgcolorStart) && !TextUtils.isEmpty(recommendDna.themeBgcolorEnd)) {
            try {
                this.themeColorStart = Color.parseColor(recommendDna.themeBgcolorStart);
                this.themeColorEnd = Color.parseColor(recommendDna.themeBgcolorEnd);
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
                this.themeColorStart = Color.parseColor("#2D8DFF");
                this.themeColorEnd = Color.parseColor("#A1C6FF");
            }
        } else {
            this.themeColorStart = Color.parseColor("#2D8DFF");
            this.themeColorEnd = Color.parseColor("#A1C6FF");
        }
        setMaterialGradient();
        this.desTv.setText(recommendDna.description);
        this.desMoreTv.setText(str4);
        if (!TextUtils.isEmpty(recommendDna.fontColor)) {
            try {
                this.desMoreTv.setTextColor(Color.parseColor(recommendDna.fontColor));
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        this.dot.setVisibility((!recommendDna.isShowDot() || recommendDna.isMonetized) ? 8 : 0);
        this.leftBottomDot.setVisibility((recommendDna.isShowDot() && recommendDna.isMonetized) ? 0 : 8);
        this.itemView.setTag(recommendDna);
        this.itemView.setOnClickListener(this.onChannelClickListener);
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
