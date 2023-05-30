package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendVideo;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.ui.JDCircleImageView;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.StringUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class RecommendTemplateThirteenViewHolder extends BaseRecommendMaterialViewHolder {
    private TextView authorName;
    private JDCircleImageView authorPic;
    private int from;
    private RelativeLayout videoContainer;
    private TextView videoDes;
    private TextView videoDuration;
    private LinearLayout videoDurationRoot;
    private TextView viewCount;
    private SimpleDraweeView viewCountImg;
    private int width;

    public RecommendTemplateThirteenViewHolder(BaseActivity baseActivity, View view) {
        super(baseActivity, view);
        this.videoContainer = (RelativeLayout) view.findViewById(R.id.recommend_video_player_container);
        this.videoDurationRoot = (LinearLayout) view.findViewById(R.id.recommend_video_duration_root);
        this.videoDuration = (TextView) view.findViewById(R.id.recommend_video_duration);
        this.videoDes = (TextView) view.findViewById(R.id.recommend_video_des);
        this.authorName = (TextView) view.findViewById(R.id.recommend_video_author_name);
        this.authorPic = (JDCircleImageView) view.findViewById(R.id.recommend_video_author_pic);
        this.viewCountImg = (SimpleDraweeView) view.findViewById(R.id.recommend_video_browse_img);
        this.viewCount = (TextView) view.findViewById(R.id.recommend_video_browse_number);
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
        this.videoDes.setText(UnIconConfigHelper.getSpanableString(arrayList, recommendVideo.mainTitle, this.videoDes));
    }

    private void setVideoPlayListener(final RecommendVideo recommendVideo) {
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateThirteenViewHolder.3
            {
                RecommendTemplateThirteenViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendTemplateThirteenViewHolder.this.jump(view, recommendVideo);
            }
        });
    }

    private void setViewCount(RecommendVideo recommendVideo, JDDisplayImageOptions jDDisplayImageOptions) {
        if (!StringUtil.isEmpty(recommendVideo.viewCount) && !"0".equals(recommendVideo.viewCount)) {
            this.viewCount.setVisibility(0);
            this.viewCountImg.setVisibility(0);
            this.viewCount.setText(recommendVideo.viewCount);
            if (!StringUtil.isEmpty(recommendVideo.viewCountImg)) {
                JDImageUtils.displayImage(recommendVideo.viewCountImg, this.viewCountImg, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateThirteenViewHolder.2
                    {
                        RecommendTemplateThirteenViewHolder.this = this;
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                        RecommendTemplateThirteenViewHolder.this.setViewCountImg();
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str, View view) {
                    }
                });
                return;
            } else {
                setViewCountImg();
                return;
            }
        }
        this.viewCount.setVisibility(8);
        this.viewCountImg.setVisibility(8);
    }

    public void setViewCountImg() {
        int i2 = this.from;
        if (i2 == 1013) {
            this.viewCountImg.setImageResource(R.drawable.recommend_browse_view_dark);
        } else if (i2 == 1014) {
            this.viewCountImg.setImageResource(R.drawable.recommend_browse_view_light);
        }
    }

    public void setData(RecommendVideo recommendVideo, ExpoDataStore expoDataStore, int i2, int i3, int i4, JDDisplayImageOptions jDDisplayImageOptions) {
        if (recommendVideo != null) {
            this.from = i2;
            setFrom(i2);
            if (this.videoContainer.getWidth() > 0) {
                this.width = this.videoContainer.getWidth();
            } else {
                this.width = RecommendViewUtil.getLineTwoItemWidth(this.itemView.getContext());
            }
            double parseStringToDouble = RecommendUtils.parseStringToDouble(recommendVideo.nonWareImageScale);
            if (parseStringToDouble <= 0.0d) {
                if (i3 == 1013) {
                    parseStringToDouble = 1.333d;
                } else if (i3 == 1014) {
                    parseStringToDouble = 1.5d;
                }
            }
            ViewGroup.LayoutParams layoutParams = this.videoContainer.getLayoutParams();
            double d = this.width;
            Double.isNaN(d);
            layoutParams.height = (int) (d * parseStringToDouble);
            this.videoContainer.requestLayout();
            changeVideoViewHeight((float) (1.0d / parseStringToDouble));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            if (!TextUtils.isEmpty(recommendVideo.videoDuration)) {
                this.videoDurationRoot.setVisibility(0);
                this.videoDuration.setText(simpleDateFormat.format(Long.valueOf(Long.parseLong(recommendVideo.videoDuration) * 1000)));
            } else {
                this.videoDurationRoot.setVisibility(8);
            }
            setVideoData(recommendVideo, recommendVideo.imgUrlLocal);
            setMainTitle(recommendVideo);
            JDImageUtils.displayImage(recommendVideo.authorPic, this.authorPic, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.RecommendTemplateThirteenViewHolder.1
                {
                    RecommendTemplateThirteenViewHolder.this = this;
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    RecommendTemplateThirteenViewHolder.this.authorPic.setImageResource(R.drawable.recommend_template_thirteen_author_icon);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            });
            setViewCount(recommendVideo, jDDisplayImageOptions);
            this.authorName.setText(recommendVideo.authorName);
            setVideoPlayListener(recommendVideo);
            if (expoDataStore != null && !recommendVideo.isFromCache) {
                if (!TextUtils.isEmpty(recommendVideo.exposureJsonSourceValue)) {
                    expoDataStore.putExpoJsonDada(recommendVideo.exposureJsonSourceValue);
                } else if (!TextUtils.isEmpty(recommendVideo.exposureSourceValue)) {
                    expoDataStore.putExpoData(recommendVideo.exposureSourceValue);
                }
            }
            setAdData(recommendVideo);
            setMaterialData(recommendVideo, i4);
        }
    }

    public void setPlayDurationVisible(boolean z) {
        if (z) {
            this.videoDurationRoot.setVisibility(0);
        } else {
            this.videoDurationRoot.setVisibility(8);
        }
    }
}
