package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.skin.lib.CodingConstants;
import com.jd.skin.lib.JDSkinSDK;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplateVideoViewHolder extends BaseRecommendMaterialViewHolder {
    private JDCircleImageView authorIv;
    private TextView authorNameTv;
    private int from;
    private TextView timeTv;
    private RelativeLayout videoContainer;
    private TextView videoDesTv;
    private LinearLayout videoDurationRoot;
    private SimpleDraweeView viewCountImg;
    private TextView viewCountTV;
    private int width;

    public RecommendTemplateVideoViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.videoContainer = (RelativeLayout) view.findViewById(R.id.recommend_new_video_player_container);
        TextView textView = (TextView) view.findViewById(R.id.recommend_new_video_duration);
        this.timeTv = textView;
        textView.setTextColor(RecommendViewUtil.generateColor(JDSkinSDK.getInstance().getColor(CodingConstants.C_Gray_01, false), JDDarkUtil.COLOR_FFFFFFF));
        TextView textView2 = (TextView) view.findViewById(R.id.recommend_new_video_des);
        this.videoDesTv = textView2;
        textView2.setTextColor(RecommendViewUtil.generateColor(JDSkinSDK.getInstance().getColor("C_Gray_09", false), "#1a1a1a"));
        this.authorIv = (JDCircleImageView) view.findViewById(R.id.recommend_new_video_author_pic);
        this.authorNameTv = (TextView) view.findViewById(R.id.recommend_new_video_author_name);
        this.viewCountImg = (SimpleDraweeView) view.findViewById(R.id.recommend_video_browse_img);
        TextView textView3 = (TextView) view.findViewById(R.id.recommend_video_browse_number);
        this.viewCountTV = textView3;
        textView3.setTextColor(RecommendViewUtil.generateColor(JDSkinSDK.getInstance().getColor(CodingConstants.C_Gray_07, false), JDDarkUtil.COLOR_808080));
        this.videoDurationRoot = (LinearLayout) view.findViewById(R.id.recommend_new_video_duration_root);
        inflateRecommendVideo();
    }

    public void jump(View view, RecommendVideo recommendVideo) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
        if (recommendVideo == null || (onRecommendClickedListener = this.clickedListener) == null) {
            return;
        }
        onRecommendClickedListener.onRecommendJump(recommendVideo.jump, recommendVideo.isOpenApp);
        if (recommendVideo.isFromCache) {
            return;
        }
        onAdClick(recommendVideo.client_click_url);
        RecommendMtaUtils.aggregationClickMtaRealize(this.itemView.getContext(), recommendVideo.sourceValue, this.from, recommendVideo.extension_id);
    }

    private void setMainTitle(RecommendVideo recommendVideo) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(recommendVideo.icon2);
        SpannableString spannableString = getSpannableString(arrayList, recommendVideo.mainTitle, this.videoDesTv);
        this.videoDesTv.setTextSize(RecommendFontUtils.convertFontSize(14, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)));
        this.videoDesTv.setText(spannableString);
    }

    private void setVideoPlayListener(final RecommendVideo recommendVideo) {
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateVideoViewHolder.2
            {
                RecommendTemplateVideoViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendTemplateVideoViewHolder.this.jump(view, recommendVideo);
            }
        });
    }

    private void setViewCount(RecommendVideo recommendVideo, JDDisplayImageOptions jDDisplayImageOptions) {
        if (!StringUtil.isEmpty(recommendVideo.viewCount) && !"0".equals(recommendVideo.viewCount)) {
            this.viewCountTV.setVisibility(0);
            this.viewCountImg.setVisibility(0);
            this.viewCountTV.setText(recommendVideo.viewCount);
            if (!StringUtil.isEmpty(recommendVideo.viewCountImg)) {
                JDImageUtils.displayImage(recommendVideo.viewCountImg, this.viewCountImg, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateVideoViewHolder.1
                    {
                        RecommendTemplateVideoViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        RecommendTemplateVideoViewHolder.this.viewCountImg.setImageResource(R.drawable.recommend_browse_view_dark);
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
        if (this.videoContainer.getWidth() > 0) {
            this.width = this.videoContainer.getWidth();
        } else {
            this.width = RecommendViewUtil.getLineTwoItemWidth(this.itemView.getContext());
        }
        if ("1".equals(recommendVideo.videoLayout)) {
            ViewGroup.LayoutParams layoutParams = this.videoContainer.getLayoutParams();
            double d = this.width;
            Double.isNaN(d);
            layoutParams.height = (int) (d * 1.418d);
        } else {
            this.videoContainer.getLayoutParams().height = this.width;
        }
        changeVideoViewHeight("1".equals(recommendVideo.videoLayout) ? 0.7f : 1.0f);
        this.videoContainer.requestLayout();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        if (!TextUtils.isEmpty(recommendVideo.videoDuration)) {
            this.videoDurationRoot.setVisibility(0);
            this.timeTv.setText(simpleDateFormat.format(Long.valueOf(Long.parseLong(recommendVideo.videoDuration) * 1000)));
        } else {
            this.videoDurationRoot.setVisibility(8);
        }
        setMainTitle(recommendVideo);
        setVideoData(recommendVideo, recommendVideo.imgUrlLocal);
        JDImageUtils.displayImage(recommendVideo.authorPic, this.authorIv, jDDisplayImageOptions);
        this.authorNameTv.setTextSize(RecommendFontUtils.convertFontSize(12, RecommendFontUtils.getRecommendUIMode(this.mRecommendConfig)));
        this.authorNameTv.setText(recommendVideo.authorName);
        setViewCount(recommendVideo, jDDisplayImageOptions);
        setVideoPlayListener(recommendVideo);
        if (expoDataStore != null && !recommendVideo.isFromCache) {
            if (!TextUtils.isEmpty(recommendVideo.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendVideo.exposureJsonSourceValue);
            } else if (!TextUtils.isEmpty(recommendVideo.exposureSourceValue)) {
                expoDataStore.putExpoData(recommendVideo.exposureSourceValue);
            }
        }
        setAdData(recommendVideo);
        setMaterialData(recommendVideo, i3);
        RecommendUtils.setRecommendDebugMark(this.debugMarkTextView, recommendVideo.showAdDot(), recommendVideo.showMonetizedDot());
    }

    public void setPlayUiVisible(boolean z) {
        if (z) {
            this.videoDurationRoot.setVisibility(0);
        } else {
            this.videoDurationRoot.setVisibility(8);
        }
    }
}
