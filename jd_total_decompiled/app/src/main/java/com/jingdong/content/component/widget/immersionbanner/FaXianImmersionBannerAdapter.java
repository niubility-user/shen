package com.jingdong.content.component.widget.immersionbanner;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.content.component.R;
import com.jingdong.content.component.b;
import com.jingdong.content.component.entity.ImmersionItemEntity;
import com.jingdong.content.component.widget.immersionbanner.ClickEventConstants;
import com.jingdong.content.component.widget.videocontrol.ContentVideoHolderView;
import com.jingdong.content.component.widget.videocontrol.ContentVideoInfo;
import com.jingdong.content.component.widget.videocontrol.IPlayClickListener;
import com.jingdong.content.component.widget.videocontrol.IVoiceClickListener;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes12.dex */
public class FaXianImmersionBannerAdapter extends FaxianTJBannerViewAdapter {
    public static final String TAG = "FaXianImmersionBannerAdapter";
    private IBannerViewClick iViewClickListener;
    boolean isVideoAutoPlay;
    boolean isVoiceOn;
    private List<ImmersionItemEntity> mBanners;
    private Context mContext;
    private SparseArray<View> mViewControlls;
    private IPlayClickListener playClickListener;
    private IVoiceClickListener voiceClickListener;

    public FaXianImmersionBannerAdapter(List<ImmersionItemEntity> list, boolean z, Context context) {
        this.mBanners = new ArrayList();
        SparseArray<View> sparseArray = new SparseArray<>();
        this.mViewControlls = sparseArray;
        this.mBanners = list;
        this.mContext = context;
        sparseArray.clear();
        this.isVideoAutoPlay = z;
    }

    @Override // com.jingdong.content.component.widget.immersionbanner.FaxianTJBannerViewAdapter
    public int getItemCount() {
        List<ImmersionItemEntity> list = this.mBanners;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // com.jingdong.content.component.widget.immersionbanner.FaxianTJBannerViewAdapter
    public View getItemView(final int i2, View view, ViewGroup viewGroup) {
        boolean z;
        View inflate = LayoutInflater.from(JdSdk.getInstance().getApplication()).inflate(R.layout.faxain_immersion_banner_item, (ViewGroup) null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.sdv_main_img);
        final ImmersionItemEntity immersionItemEntity = this.mBanners.get(i2);
        if (immersionItemEntity == null) {
            return inflate;
        }
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.immersion_banner_title_ll);
        MarqueeTextView marqueeTextView = (MarqueeTextView) inflate.findViewById(R.id.immersion_banner_title_tv);
        SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) inflate.findViewById(R.id.title_arrow_tv);
        marqueeTextView.setMaxWidth(DPIUtil.getWidthByDesignValue750(this.mContext, 680));
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.immersion_banner_topic_ll);
        TextView textView = (TextView) inflate.findViewById(R.id.immersion_banner_topic_tv);
        SimpleDraweeView simpleDraweeView3 = (SimpleDraweeView) inflate.findViewById(R.id.topic_arrow_sdv);
        textView.setMaxWidth(DPIUtil.getWidthByDesignValue750(this.mContext, 630));
        final ContentVideoHolderView contentVideoHolderView = (ContentVideoHolderView) inflate.findViewById(R.id.content_holder_video);
        this.mViewControlls.put(i2, inflate);
        if (!TextUtils.isEmpty(immersionItemEntity.title)) {
            linearLayout.setVisibility(0);
            marqueeTextView.setText(immersionItemEntity.title);
        } else {
            linearLayout.setVisibility(8);
        }
        JDDisplayImageOptions jDDisplayImageOptions = new JDDisplayImageOptions();
        jDDisplayImageOptions.setPlaceholder(R.drawable.default_img);
        JDImageUtils.displayImage(immersionItemEntity.indexImg, simpleDraweeView, jDDisplayImageOptions);
        JDDisplayImageOptions jDDisplayImageOptions2 = new JDDisplayImageOptions();
        int i3 = R.drawable.loading_empty_image;
        jDDisplayImageOptions2.setPlaceholder(i3).showImageForEmptyUri(i3);
        JDImageUtils.displayImage(immersionItemEntity.titleIcon, simpleDraweeView2, jDDisplayImageOptions2);
        if (!TextUtils.isEmpty(immersionItemEntity.subjectTitle)) {
            textView.setText(immersionItemEntity.subjectTitle);
            linearLayout2.setVisibility(0);
            JDImageUtils.displayImage(immersionItemEntity.subjectIcon, simpleDraweeView3, jDDisplayImageOptions2);
            z = true;
        } else {
            linearLayout2.setVisibility(8);
            z = false;
        }
        if (!TextUtils.isEmpty(immersionItemEntity.playUrl) && ("1".equals(immersionItemEntity.type) || "2".equals(immersionItemEntity.type))) {
            ContentVideoInfo contentVideoInfo = new ContentVideoInfo();
            contentVideoInfo.setVideo(true);
            contentVideoInfo.setVideoUrl(immersionItemEntity.playUrl);
            contentVideoInfo.setContentId(immersionItemEntity.contentId);
            contentVideoInfo.setPlayType("131");
            contentVideoInfo.setVoiceOn(this.isVoiceOn);
            contentVideoInfo.setShowPlayBtn(true ^ this.isVideoAutoPlay);
            contentVideoHolderView.configVideoInfo(contentVideoInfo);
            contentVideoHolderView.setVoiceRightBottomLocation(DPIUtil.getWidthByDesignValue750(this.mContext, 24), DPIUtil.getWidthByDesignValue750(this.mContext, z ? R2.attr.actionTextColorAlpha : 272), DPIUtil.getWidthByDesignValue750(this.mContext, 56), null, null);
            contentVideoHolderView.setVisibility(0);
            contentVideoHolderView.setVoiceClickListener(new IVoiceClickListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerAdapter.1
                @Override // com.jingdong.content.component.widget.videocontrol.IVoiceClickListener
                public void targetVoice(boolean z2) {
                    FaXianImmersionBannerAdapter faXianImmersionBannerAdapter = FaXianImmersionBannerAdapter.this;
                    faXianImmersionBannerAdapter.isVoiceOn = z2;
                    if (faXianImmersionBannerAdapter.voiceClickListener != null) {
                        FaXianImmersionBannerAdapter.this.voiceClickListener.targetVoice(z2);
                    }
                }
            });
            contentVideoHolderView.setPlayClickListener(new IPlayClickListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerAdapter.2
                @Override // com.jingdong.content.component.widget.videocontrol.IPlayClickListener
                public void toPlay(ContentVideoHolderView contentVideoHolderView2) {
                    if (FaXianImmersionBannerAdapter.this.playClickListener != null) {
                        FaXianImmersionBannerAdapter.this.playClickListener.toPlay(contentVideoHolderView2);
                    }
                }
            });
        } else {
            ContentVideoInfo contentVideoInfo2 = new ContentVideoInfo();
            contentVideoInfo2.setVideo(false);
            contentVideoHolderView.configVideoInfo(contentVideoInfo2);
            contentVideoHolderView.setVisibility(8);
        }
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Object obj = immersionItemEntity.subjectJump;
                if (obj != null && obj != null) {
                    b bVar = b.b;
                    if (bVar.a() != null) {
                        bVar.a().a(FaXianImmersionBannerAdapter.this.mContext, immersionItemEntity.subjectJump, 4);
                    }
                }
                if (FaXianImmersionBannerAdapter.this.iViewClickListener != null) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put(ClickEventConstants.CLICK_TYPE_KEY, "0");
                    FaXianImmersionBannerAdapter.this.iViewClickListener.onClick(i2, immersionItemEntity, hashMap, ClickEventConstants.ORGINAL_FROM.TOP_BANNER_TOPIC);
                }
            }
        });
        inflate.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.content.component.widget.immersionbanner.FaXianImmersionBannerAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Object obj = immersionItemEntity.cardJump;
                if (obj != null && obj != null) {
                    b bVar = b.b;
                    if (bVar.a() != null) {
                        bVar.a().a(FaXianImmersionBannerAdapter.this.mContext, immersionItemEntity.cardJump, 4);
                    }
                }
                if (FaXianImmersionBannerAdapter.this.iViewClickListener != null) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put(ClickEventConstants.CLICK_TYPE_KEY, "0");
                    hashMap.put(ClickEventConstants.VIDEO_SCENE_VIEW_KEY, contentVideoHolderView.getJDVideoView());
                    FaXianImmersionBannerAdapter.this.iViewClickListener.onClick(i2, immersionItemEntity, hashMap, ClickEventConstants.ORGINAL_FROM.TOP_BANNER);
                }
            }
        });
        return inflate;
    }

    public boolean isVideoAutoPlay() {
        return this.isVideoAutoPlay;
    }

    public void refreshViews() {
        SparseArray<View> sparseArray = this.mViewControlls;
        if (sparseArray != null && sparseArray.size() > 0) {
            for (int i2 = 0; i2 < this.mViewControlls.size(); i2++) {
                View view = this.mViewControlls.get(this.mViewControlls.keyAt(i2));
                ContentVideoHolderView contentVideoHolderView = view != null ? (ContentVideoHolderView) view.findViewById(R.id.content_holder_video) : null;
                if (contentVideoHolderView != null) {
                    contentVideoHolderView.setVideoVoiceOn(this.isVoiceOn);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setIViewClickListener(IBannerViewClick iBannerViewClick) {
        this.iViewClickListener = iBannerViewClick;
    }

    public void setPlayClickListener(IPlayClickListener iPlayClickListener) {
        this.playClickListener = iPlayClickListener;
    }

    public void setVoiceClickListener(IVoiceClickListener iVoiceClickListener) {
        this.voiceClickListener = iVoiceClickListener;
    }

    public void setVoiceOn(boolean z) {
        this.isVoiceOn = z;
    }
}
