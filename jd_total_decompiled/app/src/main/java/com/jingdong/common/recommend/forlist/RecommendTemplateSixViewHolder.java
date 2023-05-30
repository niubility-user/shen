package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplateSixViewHolder extends BaseRecommendMaterialViewHolder {
    private JDCircleImageView authorIv;
    private TextView authorNameTv;
    private int from;
    private SimpleDraweeView imageView;
    private TextView imgCountTV;
    private LinearLayout imgLayout;
    private SimpleDraweeView imgView;
    private View.OnClickListener onItemClickListener;
    private TextView ugcDesTv;
    private SimpleDraweeView viewCountImg;
    private TextView viewCountTV;

    public RecommendTemplateSixViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.onItemClickListener = new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateSixViewHolder.3
            {
                RecommendTemplateSixViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
                RecommendVideo recommendVideo = (RecommendVideo) view2.getTag();
                if (recommendVideo == null || (onRecommendClickedListener = RecommendTemplateSixViewHolder.this.clickedListener) == null) {
                    return;
                }
                onRecommendClickedListener.onRecommendJump(recommendVideo.jump, recommendVideo.isOpenApp);
                if (recommendVideo.isFromCache) {
                    return;
                }
                RecommendTemplateSixViewHolder.this.onAdClick(recommendVideo.client_click_url);
                RecommendMtaUtils.aggregationClickMtaRealize(RecommendTemplateSixViewHolder.this.itemView.getContext(), recommendVideo.sourceValue, RecommendTemplateSixViewHolder.this.from, recommendVideo.extension_id);
            }
        };
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_ugc_image);
        this.imageView = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.ugcDesTv = (TextView) view.findViewById(R.id.recommend_ugc_des);
        this.authorIv = (JDCircleImageView) view.findViewById(R.id.recommend_ugc_pic);
        this.authorNameTv = (TextView) view.findViewById(R.id.recommend_ugc_author_name);
        this.imgLayout = (LinearLayout) view.findViewById(R.id.recommend_ugc_img_layout);
        this.imgView = (SimpleDraweeView) view.findViewById(R.id.recommend_ugc_img);
        this.imgCountTV = (TextView) view.findViewById(R.id.recommend_ugc_img_count);
        this.viewCountImg = (SimpleDraweeView) view.findViewById(R.id.recommend_ugc_browse_img);
        this.viewCountTV = (TextView) view.findViewById(R.id.recommend_ugc_browse_number);
    }

    private float getViewRatio(RecommendVideo recommendVideo) {
        if (!TextUtils.isEmpty(recommendVideo.nonWareImageScale)) {
            try {
                return Float.parseFloat(recommendVideo.nonWareImageScale);
            } catch (NumberFormatException unused) {
            }
        }
        return 1.0f;
    }

    private void setImageViewHeight(RecommendVideo recommendVideo) {
        ViewGroup.LayoutParams layoutParams;
        SimpleDraweeView simpleDraweeView = this.imageView;
        if (simpleDraweeView == null || (layoutParams = simpleDraweeView.getLayoutParams()) == null) {
            return;
        }
        layoutParams.height = (int) ((this.imageView.getWidth() > 0 ? this.imageView.getWidth() : RecommendViewUtil.getLineTwoItemWidth(this.itemView.getContext())) / getViewRatio(recommendVideo));
        this.imageView.setLayoutParams(layoutParams);
        this.imageView.requestLayout();
    }

    private void setMainTitle(RecommendVideo recommendVideo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(recommendVideo.icon2);
        SpannableString spannableString = getSpannableString(arrayList, recommendVideo.mainTitle, this.ugcDesTv);
        this.ugcDesTv.setTextSize(RecommendFontUtils.convertFontSize(14, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)));
        this.ugcDesTv.setText(spannableString);
    }

    private void setViewCount(RecommendVideo recommendVideo, JDDisplayImageOptions jDDisplayImageOptions) {
        if (!StringUtil.isEmpty(recommendVideo.viewCount) && !"0".equals(recommendVideo.viewCount)) {
            this.viewCountTV.setVisibility(0);
            this.viewCountImg.setVisibility(0);
            this.viewCountTV.setText(recommendVideo.viewCount);
            if (!StringUtil.isEmpty(recommendVideo.viewCountImg)) {
                JDImageUtils.displayImage(recommendVideo.viewCountImg, this.viewCountImg, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateSixViewHolder.2
                    {
                        RecommendTemplateSixViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        RecommendTemplateSixViewHolder.this.viewCountImg.setImageResource(R.drawable.recommend_browse_view_dark);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
                return;
            } else {
                this.viewCountImg.setImageResource(R.drawable.recommend_browse_view_dark);
                return;
            }
        }
        this.viewCountTV.setVisibility(8);
        this.viewCountImg.setVisibility(8);
    }

    public void setData(RecommendVideo recommendVideo, ExpoDataStore expoDataStore, int i2, int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        if (recommendVideo == null) {
            return;
        }
        this.from = i2;
        setFrom(i2);
        getRecommendUIMode();
        setMainTitle(recommendVideo);
        setImageViewHeight(recommendVideo);
        JDImageUtils.displayImage(recommendVideo.imgUrlLocal, this.imageView, jDDisplayImageOptions);
        JDImageUtils.displayImage(recommendVideo.authorPic, this.authorIv, jDDisplayImageOptions);
        this.authorNameTv.setTextSize(RecommendFontUtils.convertFontSize(12, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)));
        this.authorNameTv.setText(recommendVideo.authorName);
        if (!StringUtil.isEmpty(recommendVideo.imgCount) && !"0".equals(recommendVideo.imgCount)) {
            this.imgLayout.setVisibility(0);
            this.imgCountTV.setText(recommendVideo.imgCount);
            if (!StringUtil.isEmpty(recommendVideo.imgCountImg)) {
                JDImageUtils.displayImage(recommendVideo.imgCountImg, this.imgView, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateSixViewHolder.1
                    {
                        RecommendTemplateSixViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        RecommendTemplateSixViewHolder.this.imgView.setImageResource(R.drawable.recommend_ugc_photo);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
            } else {
                this.imgView.setImageResource(R.drawable.recommend_ugc_photo);
            }
        } else {
            this.imgLayout.setVisibility(8);
        }
        setViewCount(recommendVideo, jDDisplayImageOptions);
        if (expoDataStore != null && !recommendVideo.isFromCache) {
            if (!TextUtils.isEmpty(recommendVideo.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendVideo.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendVideo.exposureSourceValue)) {
                expoDataStore.putExpoData(recommendVideo.exposureSourceValue);
            }
        }
        this.itemView.setTag(recommendVideo);
        this.itemView.setOnClickListener(this.onItemClickListener);
        setAdData(recommendVideo);
        setMaterialData(recommendVideo, i3);
    }
}
