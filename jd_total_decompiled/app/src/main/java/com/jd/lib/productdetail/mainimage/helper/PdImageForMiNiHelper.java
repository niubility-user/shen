package com.jd.lib.productdetail.mainimage.helper;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicAnchorEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.callback.OnCallBackByMiNi;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdVideoContainer;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jd.lib.productdetail.mainimage.view.PdMainImageView;
import com.jingdong.common.BaseActivity;
import java.util.List;

/* loaded from: classes15.dex */
public class PdImageForMiNiHelper {
    private BaseActivity activity;
    private Context context;
    private Observer<Boolean> isCLickObserver;
    private boolean isHasVideo = false;
    private Observer<Boolean> isVideoInitObserver;
    private Observer<Boolean> jumpToPageOserver;
    private LifecycleOwner lifecycleOwner;
    private WareBusinessUnitMainImageEntity mTopImageEntity;
    private OnCallBackByMiNi onCallBackByMiNi;
    private Observer<Integer> pageSelectedObserver;
    private PdMainImageView pdMainImageView;
    private PdMainImagePresenter presenter;
    private Observer<Boolean> toDetailPageOserver;
    private Observer<Integer> videoPlayTimeObserver;
    private Observer<PdVideoContainer.VideoStatus> videoStatusObserver;

    public PdImageForMiNiHelper(Context context) {
        this.context = context;
        BaseActivity baseActivity = (BaseActivity) context;
        this.activity = baseActivity;
        PdMainImageView pdMainImageView = new PdMainImageView(context);
        this.pdMainImageView = pdMainImageView;
        pdMainImageView.setComeType(PdImageFromType.PRODUCTDETAIL_MINI);
        this.presenter = this.pdMainImageView.getMainImagePresenter();
        this.lifecycleOwner = ViewTreeLifecycleOwner.get(baseActivity.getWindow().getDecorView());
    }

    public static PdImageForMiNiHelper getInstance(Context context) {
        return new PdImageForMiNiHelper(context);
    }

    public void buildDataToView() {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        PdMainImageView pdMainImageView = this.pdMainImageView;
        if (pdMainImageView == null || (wareBusinessUnitMainImageEntity = this.mTopImageEntity) == null) {
            return;
        }
        pdMainImageView.setMainData(wareBusinessUnitMainImageEntity);
    }

    public PdMainImageView getMainImageView() {
        this.presenter = this.pdMainImageView.getMainImagePresenter();
        return this.pdMainImageView;
    }

    public ImageView getMiniView() {
        PdMainImageView pdMainImageView = this.pdMainImageView;
        if (pdMainImageView != null) {
            return pdMainImageView.getMiniView();
        }
        return null;
    }

    public boolean isHasVideo() {
        return this.isHasVideo;
    }

    public void pauseVideo() {
        PdVideoContainer pdVideoContainer;
        PdMainImagePresenter pdMainImagePresenter = this.presenter;
        if (pdMainImagePresenter == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
            return;
        }
        pdVideoContainer.videoControl.setValue(PdVideoContainer.VideoControl.PAUSE_OUT);
    }

    public void resumePlay() {
        PdVideoContainer pdVideoContainer;
        PdMainImagePresenter pdMainImagePresenter = this.presenter;
        if (pdMainImagePresenter == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
            return;
        }
        pdVideoContainer.videoControl.setValue(PdVideoContainer.VideoControl.RESUME_OUT);
    }

    public void setMiniMainData(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity, final OnCallBackByMiNi onCallBackByMiNi) {
        LifecycleOwner lifecycleOwner;
        this.mTopImageEntity = wareBusinessUnitMainImageEntity;
        if (wareBusinessUnitMainImageEntity.extMap != null) {
            PdMainImagePresenter pdMainImagePresenter = this.presenter;
            pdMainImagePresenter.imageFromType = PdImageFromType.PRODUCTDETAIL_MINI;
            pdMainImagePresenter.getMainImageParams().mManagerKey = "MINI_DETAIL";
            this.presenter.getMainImageParams().skuId = wareBusinessUnitMainImageEntity.extMap.skuId;
        }
        if (this.onCallBackByMiNi == null) {
            this.onCallBackByMiNi = onCallBackByMiNi;
        }
        if (this.toDetailPageOserver == null) {
            this.toDetailPageOserver = new Observer<Boolean>() { // from class: com.jd.lib.productdetail.mainimage.helper.PdImageForMiNiHelper.1
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean bool) {
                    onCallBackByMiNi.gotoDetail(bool.booleanValue());
                }
            };
        }
        if (this.jumpToPageOserver == null) {
            this.jumpToPageOserver = new Observer<Boolean>() { // from class: com.jd.lib.productdetail.mainimage.helper.PdImageForMiNiHelper.2
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean bool) {
                    onCallBackByMiNi.jumpToPage(bool.booleanValue());
                }
            };
        }
        if (this.videoStatusObserver == null) {
            this.videoStatusObserver = new Observer<PdVideoContainer.VideoStatus>() { // from class: com.jd.lib.productdetail.mainimage.helper.PdImageForMiNiHelper.3
                @Override // androidx.lifecycle.Observer
                public void onChanged(PdVideoContainer.VideoStatus videoStatus) {
                    if (videoStatus == PdVideoContainer.VideoStatus.OVER) {
                        onCallBackByMiNi.onVideoFinish();
                    }
                }
            };
        }
        if (this.pageSelectedObserver == null) {
            this.pageSelectedObserver = new Observer<Integer>() { // from class: com.jd.lib.productdetail.mainimage.helper.PdImageForMiNiHelper.4
                @Override // androidx.lifecycle.Observer
                public void onChanged(Integer num) {
                    onCallBackByMiNi.onPageSelect(num.intValue());
                }
            };
        }
        if (this.videoPlayTimeObserver == null) {
            this.videoPlayTimeObserver = new Observer<Integer>() { // from class: com.jd.lib.productdetail.mainimage.helper.PdImageForMiNiHelper.5
                @Override // androidx.lifecycle.Observer
                public void onChanged(Integer num) {
                    onCallBackByMiNi.onVideoDuration(num.intValue());
                }
            };
        }
        if (this.isCLickObserver == null) {
            this.isCLickObserver = new Observer<Boolean>() { // from class: com.jd.lib.productdetail.mainimage.helper.PdImageForMiNiHelper.6
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean bool) {
                    onCallBackByMiNi.onPlay(bool.booleanValue());
                }
            };
        }
        if (this.isVideoInitObserver == null) {
            this.isVideoInitObserver = new Observer<Boolean>() { // from class: com.jd.lib.productdetail.mainimage.helper.PdImageForMiNiHelper.7
                @Override // androidx.lifecycle.Observer
                public void onChanged(Boolean bool) {
                    onCallBackByMiNi.isInitVideo(bool.booleanValue());
                }
            };
        }
        this.isHasVideo = false;
        if (wareBusinessUnitMainImageEntity.magicHeadPicInfo != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= wareBusinessUnitMainImageEntity.magicHeadPicInfo.size()) {
                    break;
                } else if (TextUtils.equals(wareBusinessUnitMainImageEntity.magicHeadPicInfo.get(i2).anchorType, "video")) {
                    this.isHasVideo = true;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (onCallBackByMiNi == null || (lifecycleOwner = this.lifecycleOwner) == null) {
            return;
        }
        PdMainImagePresenter pdMainImagePresenter2 = this.presenter;
        if (pdMainImagePresenter2.pdVideoContainer != null) {
            pdMainImagePresenter2.toDetailPage.observe(lifecycleOwner, this.toDetailPageOserver);
            int appWidth = PDUtils.getAppWidth(this.activity);
            List<WareBusinessMagicAnchorEntity> list = wareBusinessUnitMainImageEntity.magicAnchor;
            if (list != null && !list.isEmpty()) {
                onCallBackByMiNi.hasAnchor(true);
                onCallBackByMiNi.viewHeight((appWidth - PDUtils.dip2px(this.activity, 20.0f)) + PDUtils.dip2px(this.activity, 38.0f));
            } else {
                onCallBackByMiNi.hasAnchor(false);
                onCallBackByMiNi.viewHeight(appWidth - PDUtils.dip2px(this.activity, 20.0f));
            }
            this.presenter.jumpToPage.observe(this.lifecycleOwner, this.jumpToPageOserver);
            this.presenter.pdVideoContainer.videoStatus.observe(this.lifecycleOwner, this.videoStatusObserver);
            this.presenter.pageSelectedIndexForMINI.observe(this.lifecycleOwner, this.pageSelectedObserver);
            this.presenter.pdVideoContainer.videoPlayTime.observe(this.lifecycleOwner, this.videoPlayTimeObserver);
            this.presenter.pdVideoContainer.isClickVideoPlay.observe(this.lifecycleOwner, this.isCLickObserver);
            this.presenter.pdVideoContainer.isVideoInit.observe(this.lifecycleOwner, this.isVideoInitObserver);
        }
    }

    public void setMtaData(PdMainImageParams pdMainImageParams) {
        PdMainImagePresenter pdMainImagePresenter = this.presenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mainImageParams = pdMainImageParams;
        }
    }

    public void resumePlay(boolean z) {
        PdVideoContainer pdVideoContainer;
        PdMainImagePresenter pdMainImagePresenter = this.presenter;
        if (pdMainImagePresenter == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
            return;
        }
        PdMainImageParams pdMainImageParams = pdMainImagePresenter.mainImageParams;
        if (pdMainImageParams != null) {
            pdMainImageParams.is4GCanPlayVideo = z;
        }
        pdVideoContainer.videoControl.setValue(PdVideoContainer.VideoControl.RESUME_OUT);
    }
}
