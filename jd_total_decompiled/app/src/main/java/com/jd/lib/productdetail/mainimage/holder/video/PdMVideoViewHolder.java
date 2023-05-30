package com.jd.lib.productdetail.mainimage.holder.video;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDVedioShareEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdVideoSymbolList;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMaterVideoInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopVideoControl;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.bean.PdMainImagePagerEntity;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.old.b0;
import com.jd.lib.productdetail.mainimage.old.k;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdVideoContainer;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.video.player.VideoPlayView;
import com.jingdong.common.utils.JDSettingUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.videoplayer.VideoPlayerUtils;
import com.jingdong.common.widget.videosmallwindow.SmallWindowManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.aac.util.SyncEventBus;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;
import tv.danmaku.ijk.media.ext.cache.preload.PreloadManager;

/* loaded from: classes15.dex */
public class PdMVideoViewHolder extends PdMainImageBaseHolder {
    public String B;
    public String C;
    public String D;
    public com.jd.lib.productdetail.mainimage.old.k E;
    public boolean F;
    public boolean G;
    public FrameLayout H;
    public ImageView I;
    public boolean J;
    public boolean K;
    public boolean L;
    public boolean M;

    /* loaded from: classes15.dex */
    public class a implements Observer<PdVideoContainer.VideoControl> {

        /* renamed from: g */
        public final /* synthetic */ PdMainImagePresenter f4966g;

        public a(PdMainImagePresenter pdMainImagePresenter) {
            PdMVideoViewHolder.this = r1;
            this.f4966g = pdMainImagePresenter;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdVideoContainer.VideoControl videoControl) {
            PdMainImagePresenter pdMainImagePresenter;
            PdVideoContainer.VideoControl videoControl2 = videoControl;
            PdMVideoViewHolder pdMVideoViewHolder = PdMVideoViewHolder.this;
            com.jd.lib.productdetail.mainimage.old.k kVar = pdMVideoViewHolder.E;
            if (kVar == null || (pdMainImagePresenter = this.f4966g) == null) {
                return;
            }
            if (videoControl2 == PdVideoContainer.VideoControl.RESUME_OUT) {
                if (pdMVideoViewHolder.f4650j) {
                    if (kVar.B) {
                        pdMVideoViewHolder.E = com.jd.lib.productdetail.mainimage.old.k.b(pdMVideoViewHolder.f4649i, pdMainImagePresenter.getMainImageParams().mManagerKey);
                        PdMVideoViewHolder pdMVideoViewHolder2 = PdMVideoViewHolder.this;
                        pdMVideoViewHolder2.E.z = this.f4966g;
                        pdMVideoViewHolder2.M = NetUtils.isNetworkAvailable() && NetUtils.isWifi();
                        if (this.f4966g.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                            PdMVideoViewHolder.this.E.B = false;
                        }
                        PdMVideoViewHolder.this.b(true);
                        return;
                    } else if (kVar.f5170n && kVar.a != null) {
                        if (kVar.z() && PdMVideoViewHolder.this.H.findViewWithTag("topImageVideoTag") != null) {
                            com.jd.lib.productdetail.mainimage.old.k kVar2 = PdMVideoViewHolder.this.E;
                            if (kVar2.o && !kVar2.C()) {
                                PdMVideoViewHolder.this.O();
                            }
                        } else {
                            PdMVideoViewHolder.this.b(false);
                            PdMVideoViewHolder pdMVideoViewHolder3 = PdMVideoViewHolder.this;
                            com.jd.lib.productdetail.mainimage.holder.video.a aVar = new com.jd.lib.productdetail.mainimage.holder.video.a(this);
                            pdMVideoViewHolder3.getClass();
                            SyncEventBus.postToMainThread(aVar, 300);
                        }
                    } else {
                        pdMVideoViewHolder.b(true);
                    }
                } else {
                    kVar.o = false;
                }
                PdMVideoViewHolder.this.I.setVisibility(8);
            } else if (videoControl2 == PdVideoContainer.VideoControl.PAUSE_OUT) {
                MutableLiveData<Integer> mutableLiveData = pdMainImagePresenter.pdVideoContainer.videoPlayTime;
                VideoPlayView videoPlayView = kVar.a;
                mutableLiveData.setValue(Integer.valueOf(videoPlayView != null ? videoPlayView.getDuration() : -1));
                PdMVideoViewHolder.this.K();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements Runnable {
        public b() {
            PdMVideoViewHolder.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            PdMVideoViewHolder pdMVideoViewHolder = PdMVideoViewHolder.this;
            if (pdMVideoViewHolder.f4651k) {
                return;
            }
            VideoPlayerUtils.setActivityFullScreen(pdMVideoViewHolder.L());
        }
    }

    public PdMVideoViewHolder(@NonNull View view, View view2) {
        super(view, view2);
        this.G = false;
        this.J = false;
        this.L = false;
        this.M = false;
    }

    public static void G(PdMVideoViewHolder pdMVideoViewHolder) {
        if (pdMVideoViewHolder.E == null || pdMVideoViewHolder.L() == null) {
            return;
        }
        com.jd.lib.productdetail.mainimage.old.k kVar = pdMVideoViewHolder.E;
        VideoPlayView videoPlayView = kVar.a;
        if ((videoPlayView != null && videoPlayView.getVideoWidth() > kVar.a.getVideoHeight()) && pdMVideoViewHolder.t() != null) {
            pdMVideoViewHolder.t().setRequestedOrientation(0);
        }
        SyncEventBus.postToMainThread(new d(pdMVideoViewHolder), 200);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean A() {
        PdVideoContainer pdVideoContainer;
        PdVideoContainer pdVideoContainer2;
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdMainImagePresenter pdMainImagePresenter;
        if (TextUtils.equals(DYConstants.DY_TRUE, JDMobileConfig.getInstance().getConfig("JDProductdetail", "pdImageMiNiVideoIconClick", "enable", DYConstants.DY_TRUE)) && (pdMainImagePresenter = this.f4654n) != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
            return false;
        }
        PdMainImagePresenter pdMainImagePresenter2 = this.f4654n;
        if (pdMainImagePresenter2 != null && (pdVideoContainer2 = pdMainImagePresenter2.pdVideoContainer) != null && (kVar = this.E) != null && kVar.o) {
            pdVideoContainer2.isClickVideoPlay.setValue(Boolean.TRUE);
            O();
        } else {
            com.jd.lib.productdetail.mainimage.old.k kVar2 = this.E;
            if (kVar2 != null && kVar2.C()) {
                this.E.y(false);
                m(true, true, false);
            } else {
                PdMainImagePresenter pdMainImagePresenter3 = this.f4654n;
                if (pdMainImagePresenter3 != null && (pdVideoContainer = pdMainImagePresenter3.pdVideoContainer) != null) {
                    pdVideoContainer.isClickVideoPlay.setValue(Boolean.TRUE);
                }
                b(false);
            }
        }
        return true;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void E() {
        com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
        if (kVar == null || !kVar.f5170n) {
            return;
        }
        boolean C = kVar.C();
        this.L = C;
        if (C) {
            com.jd.lib.productdetail.mainimage.old.k kVar2 = this.E;
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
            WareBusinessTopVideoControl wareBusinessTopVideoControl = wareBusinessUnitMainImageEntity != null ? this.r.bizData.videoBizData.videoControl : null;
            if (wareBusinessUnitMainImageEntity != null) {
                String str = wareBusinessUnitMainImageEntity.extMap.skuId;
            }
            if (wareBusinessUnitMainImageEntity != null) {
                String str2 = this.f4654n.getMainImageParams().mSkuTag;
            }
            kVar2.i(wareBusinessTopVideoControl, kVar2, false, this.B, this.C, this.D, this.H);
            com.jd.lib.productdetail.mainimage.old.k kVar3 = this.E;
            kVar3.o = kVar3.o;
            VideoPlayView videoPlayView = kVar3.a;
            if (videoPlayView != null) {
                videoPlayView.onPause();
                kVar3.w(false);
            }
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void F() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        if (this.f4650j && (kVar = this.E) != null && kVar.f5170n) {
            if (kVar.f5168l == 3) {
                SyncEventBus.postToMainThread(new b(), 200);
            }
            if (this.L) {
                this.E.B(true);
                P();
            }
        }
    }

    public final void K() {
        int normalVideoDuration;
        WareBusinessTopVideoControl wareBusinessTopVideoControl;
        if (this.E == null) {
            return;
        }
        this.K = false;
        StringBuilder sb = new StringBuilder();
        com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
        sb.append(kVar.a(kVar.F()));
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        com.jd.lib.productdetail.mainimage.old.k kVar2 = this.E;
        boolean F = kVar2.F();
        VideoPlayView videoPlayView = kVar2.a;
        if (videoPlayView == null) {
            normalVideoDuration = 0;
        } else {
            normalVideoDuration = F ? videoPlayView.getNormalVideoDuration() : videoPlayView.getCurrentPosition();
        }
        sb.append(normalVideoDuration);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append((this.v == null || (wareBusinessTopVideoControl = this.r.bizData.videoBizData.videoControl) == null || !wareBusinessTopVideoControl.autoPlay) ? "1" : "2");
        this.f4654n.mtaClick("Productdetail_MainPicVideoExit", sb.toString());
        if (this.E.f5168l == 3) {
            N();
        }
        this.E.c();
        Q();
        this.I.setVisibility(0);
    }

    public final ViewGroup L() {
        View findViewById = t().findViewById(16908290);
        if (findViewById instanceof ViewGroup) {
            return (ViewGroup) findViewById;
        }
        return null;
    }

    public final String M() {
        WareBusinessTopVideoControl wareBusinessTopVideoControl;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo;
        return (this.v == null || (wareBusinessTopVideoControl = this.r.bizData.videoBizData.videoControl) == null || (wareBusinessMaterVideoInfo = wareBusinessTopVideoControl.masterVideo) == null || TextUtils.isEmpty(wareBusinessMaterVideoInfo.videoId)) ? "-100" : this.r.bizData.videoBizData.videoControl.masterVideo.videoId;
    }

    public final void N() {
        if (this.E == null || L() == null) {
            return;
        }
        VideoPlayerUtils.setActivityNotFullScreen(L());
        com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
        if (kVar.f5168l == 3) {
            int i2 = kVar.f5169m;
            if (i2 == 1) {
                R();
            } else if (i2 == 2) {
                if (kVar.C()) {
                    S();
                } else {
                    R();
                }
            }
        }
        if (t().getRequestedOrientation() == 0) {
            t().setRequestedOrientation(1);
        }
        this.f4654n.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.INTERCEPT_KEYBACK, Boolean.FALSE));
    }

    public void O() {
        ImageView imageView = this.I;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
        if (kVar != null) {
            kVar.H(true);
            com.jd.lib.productdetail.mainimage.old.k kVar2 = this.E;
            VideoPlayView videoPlayView = kVar2.a;
            if (videoPlayView != null) {
                kVar2.o = false;
                videoPlayView.startPlay();
                kVar2.p = false;
                kVar2.w(true);
            }
            P();
        }
    }

    public final void P() {
        PdVideoContainer pdVideoContainer;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
            return;
        }
        pdVideoContainer.videoStatus.setValue(PdVideoContainer.VideoStatus.PLAY);
    }

    public final void Q() {
        PdVideoContainer pdVideoContainer;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
            return;
        }
        pdVideoContainer.videoStatus.setValue(PdVideoContainer.VideoStatus.OVER);
    }

    public final void R() {
        com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
        if (kVar == null) {
            return;
        }
        FrameLayout frameLayout = this.H;
        kVar.getClass();
        if (Log.D) {
            Log.d("BusinessVideoPlayerComponent setUpDefaultStatusPlayer", frameLayout + LangUtils.SINGLE_SPACE + kVar.C + LangUtils.SINGLE_SPACE + kVar.B + LangUtils.SINGLE_SPACE + kVar.a);
        }
        if (kVar.B || kVar.a == null || kVar.C) {
            return;
        }
        kVar.a.setVideoViewOnTouchListener(kVar.L);
        kVar.a.setShowBottomProgressBar(true);
        kVar.a.loadErrorRetry(true);
        kVar.a.hideFullscreen(true);
        kVar.a.setShowVoice(true, kVar.F);
        kVar.a.getBarPlayerView().setVisibility(8);
        kVar.a.setBottomSharedEnable(false);
        kVar.E(true);
        kVar.a.changeToScreen(0);
        kVar.a.hide(false);
        kVar.a.setKeepBottomProgressBarVisi(false);
        kVar.a.setBottomProgressBarVisible(true);
        kVar.a.setVoiceIconKeepVisiInFullScreen(true);
        kVar.a.hideCloseBt(true);
        kVar.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        kVar.a.setTranslationX(0.0f);
        kVar.a.setTranslationY(0.0f);
        kVar.d(1);
        k.o oVar = kVar.f5164h;
        if (oVar != null) {
            oVar.a(kVar.f5168l);
        }
        kVar.f(frameLayout, -1);
    }

    public final void S() {
        com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
        if (kVar == null) {
            return;
        }
        kVar.g(L(), false);
    }

    public final void T() {
        PdVideoContainer pdVideoContainer;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
            return;
        }
        pdVideoContainer.videoStatus.setValue(PdVideoContainer.VideoStatus.PAUSE);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void a(boolean z) {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdVideoBizData pdVideoBizData;
        PdVideoContainer pdVideoContainer;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2;
        WareBuinessUnitMainImageBizDataEntity.PdVideoBizData pdVideoBizData2;
        WareBusinessTopVideoControl wareBusinessTopVideoControl;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo;
        WareBusinessTopVideoControl wareBusinessTopVideoControl2;
        String str;
        WareBusinessTopVideoControl wareBusinessTopVideoControl3;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo2;
        List<PdVideoSymbolList> list;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo3;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo4;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo5;
        String str2;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo6;
        List<PdVideoSymbolList> list2;
        this.f4650j = z;
        if (z) {
            PdMainImagePresenter pdMainImagePresenter = this.f4654n;
            if (pdMainImagePresenter != null) {
                com.jd.lib.productdetail.mainimage.old.k kVar2 = this.E;
                if (kVar2 != null) {
                    if (kVar2.z()) {
                        com.jd.lib.productdetail.mainimage.old.k kVar3 = this.E;
                        if (kVar3.o) {
                            kVar3.H(false);
                        }
                        if (!this.E.C()) {
                            this.E.I();
                            this.I.setVisibility(8);
                            P();
                        }
                        PDVedioShareEntity value = PDManager.getInstances(this.f4654n.getMainImageParams().mManagerKey).getVedioShareData().getValue();
                        if (value != null) {
                            value.isFirstFrame = this.q.position == 0;
                            PDManager.getInstances(this.f4654n.getMainImageParams().mManagerKey).getVedioShareData().postValue(value);
                        }
                    }
                } else {
                    if (kVar2 == null) {
                        com.jd.lib.productdetail.mainimage.old.k b2 = com.jd.lib.productdetail.mainimage.old.k.b(this.f4649i, pdMainImagePresenter.getMainImageParams().mManagerKey);
                        this.E = b2;
                        b2.z = this.f4654n;
                        this.M = NetUtils.isNetworkAvailable() && NetUtils.isWifi();
                        if (this.f4654n.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                            this.E.B = false;
                        }
                    }
                    PDVedioShareEntity value2 = PDManager.getInstances(this.f4654n.getMainImageParams().mManagerKey).getVedioShareData().getValue();
                    if ((value2 == null || value2.isMianTab) && (wareBusinessMagicHeadPicInfoEntity = this.r) != null && (wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdVideoBizData2 = wareBuinessUnitMainImageBizDataEntity2.videoBizData) != null) {
                        WareBusinessTopVideoControl wareBusinessTopVideoControl4 = pdVideoBizData2.videoControl;
                        boolean z2 = wareBusinessTopVideoControl4 != null && wareBusinessTopVideoControl4.isHasMasterVideo();
                        this.F = z2;
                        if (z2) {
                            WareBusinessTopVideoControl wareBusinessTopVideoControl5 = this.r.bizData.videoBizData.videoControl;
                            if (wareBusinessTopVideoControl5.optimize && wareBusinessTopVideoControl5.autoPlay && JDSettingUtils.isWifiVideoAutoPlay() && this.f4654n.imageFromType != PdImageFromType.PRODUCTDETAIL_MINI) {
                                SyncEventBus.postToMainThread(new e(this));
                                JDJSONObject jDJSONObject = new JDJSONObject();
                                jDJSONObject.put("videoid", M());
                                PdMainImagePresenter pdMainImagePresenter2 = this.f4654n;
                                if (pdMainImagePresenter2 == null || pdMainImagePresenter2.getMainImageParams() == null) {
                                    str2 = "";
                                } else {
                                    str2 = this.f4654n.getMainImageParams().brandId;
                                    PDUtils.setFloorPriceJson(jDJSONObject, this.f4654n.getMainImageParams().floorPriceMta);
                                }
                                PDUtils.setFloorCid(jDJSONObject, this.f4654n.getCategoryId(0), this.f4654n.getCategoryId(1), this.f4654n.getCategoryId(2));
                                PDUtils.setCardInfo(jDJSONObject, str2, "bpMainImage", this.f4652l);
                                WareBusinessTopVideoControl wareBusinessTopVideoControl6 = this.r.bizData.videoBizData.videoControl;
                                if (wareBusinessTopVideoControl6 != null && (wareBusinessMaterVideoInfo6 = wareBusinessTopVideoControl6.masterVideo) != null && (list2 = wareBusinessMaterVideoInfo6.videoSymbolList) != null && !list2.isEmpty()) {
                                    StringBuilder sb = new StringBuilder();
                                    StringBuilder sb2 = new StringBuilder();
                                    for (int i2 = 0; i2 < this.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.size(); i2++) {
                                        PdVideoSymbolList pdVideoSymbolList = this.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.get(i2);
                                        if (pdVideoSymbolList != null) {
                                            sb.append(pdVideoSymbolList.symbolId);
                                            sb2.append(pdVideoSymbolList.name);
                                            if (i2 != this.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.size() - 1) {
                                                sb.append(CartConstant.KEY_YB_INFO_LINK);
                                                sb2.append(CartConstant.KEY_YB_INFO_LINK);
                                            }
                                        }
                                    }
                                    jDJSONObject.put("lableid", (Object) sb);
                                    jDJSONObject.put("lablename", (Object) sb2);
                                }
                                this.f4654n.mtaClick("Productdetail_MainPicVideoAutoPlay", jDJSONObject.toJSONString(), true);
                            }
                        }
                        this.I.setVisibility(8);
                        if (this.F && this.v != null && (wareBusinessTopVideoControl2 = this.r.bizData.videoBizData.videoControl) != null && !wareBusinessTopVideoControl2.floatLayer) {
                            this.I.setVisibility(0);
                            if (this.f4654n.getMainImageParams().isElder) {
                                PDCalorieImageUtil.get().display("2707", this.I);
                            } else {
                                this.I.setBackgroundResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
                            }
                            this.I.setOnClickListener(new f(this));
                            WareBusinessTopVideoControl wareBusinessTopVideoControl7 = this.r.bizData.videoBizData.videoControl;
                            if (wareBusinessTopVideoControl7.optimize) {
                                str = wareBusinessTopVideoControl7.autoPlay ? "2" : "1";
                            } else {
                                str = "0";
                            }
                            String str3 = str + CartConstant.KEY_YB_INFO_LINK + ((wareBusinessTopVideoControl7.isHasMasterVideo() && (wareBusinessMaterVideoInfo5 = this.r.bizData.videoBizData.videoControl.masterVideo) != null && wareBusinessMaterVideoInfo5.isHasMarkInfo()) ? "1" : "0") + CartConstant.KEY_YB_INFO_LINK + ((this.r.bizData.videoBizData.videoControl.isHasMasterVideo() && (wareBusinessMaterVideoInfo4 = this.r.bizData.videoBizData.videoControl.masterVideo) != null && wareBusinessMaterVideoInfo4.isHasShareInfo()) ? "1" : "0") + CartConstant.KEY_YB_INFO_LINK + ((this.r.bizData.videoBizData.videoControl.isHasMasterVideo() && (wareBusinessMaterVideoInfo3 = this.r.bizData.videoBizData.videoControl.masterVideo) != null && wareBusinessMaterVideoInfo3.isHasExtInfo()) ? "1" : "0");
                            JDJSONObject jDJSONObject2 = new JDJSONObject();
                            jDJSONObject2.put("videoid", M());
                            if (this.v != null && (wareBusinessTopVideoControl3 = this.r.bizData.videoBizData.videoControl) != null && (wareBusinessMaterVideoInfo2 = wareBusinessTopVideoControl3.masterVideo) != null && (list = wareBusinessMaterVideoInfo2.videoSymbolList) != null && !list.isEmpty()) {
                                StringBuilder sb3 = new StringBuilder();
                                StringBuilder sb4 = new StringBuilder();
                                for (int i3 = 0; i3 < this.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.size(); i3++) {
                                    PdVideoSymbolList pdVideoSymbolList2 = this.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.get(i3);
                                    if (pdVideoSymbolList2 != null) {
                                        sb3.append(pdVideoSymbolList2.symbolId);
                                        sb4.append(pdVideoSymbolList2.name);
                                        if (i3 != this.r.bizData.videoBizData.videoControl.masterVideo.videoSymbolList.size() - 1) {
                                            sb3.append(CartConstant.KEY_YB_INFO_LINK);
                                            sb4.append(CartConstant.KEY_YB_INFO_LINK);
                                        }
                                    }
                                }
                                jDJSONObject2.put("lableid", (Object) sb3);
                                jDJSONObject2.put("lablename", (Object) sb4);
                            }
                            this.f4654n.mtaExposure("Productdetail_MainPicVideoExpo", jDJSONObject2.toJSONString(), str3, true);
                        }
                        if (this.v != null && (wareBusinessTopVideoControl = this.r.bizData.videoBizData.videoControl) != null && (wareBusinessMaterVideoInfo = wareBusinessTopVideoControl.masterVideo) != null && wareBusinessMaterVideoInfo.playBackFlag) {
                            this.f4654n.mtaExposure("Productdetail_PhotoVideoReplyExpo", "", wareBusinessMaterVideoInfo.videoId);
                        }
                    }
                }
            }
            PdMainImagePresenter pdMainImagePresenter3 = this.f4654n;
            if (pdMainImagePresenter3 == null || (pdVideoContainer = pdMainImagePresenter3.pdVideoContainer) == null || pdMainImagePresenter3.imageFromType != PdImageFromType.PRODUCTDETAIL_MINI) {
                return;
            }
            pdVideoContainer.isVideoInit.setValue(Boolean.TRUE);
        } else if (!this.f4651k && (kVar = this.E) != null && kVar.f5170n) {
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
            WareBusinessTopVideoControl wareBusinessTopVideoControl8 = (wareBusinessUnitMainImageEntity == null || (wareBuinessUnitMainImageBizDataEntity = this.r.bizData) == null || (pdVideoBizData = wareBuinessUnitMainImageBizDataEntity.videoBizData) == null) ? null : pdVideoBizData.videoControl;
            if (wareBusinessUnitMainImageEntity != null) {
                WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = wareBusinessUnitMainImageEntity.extMap;
            }
            PdMainImagePresenter pdMainImagePresenter4 = this.f4654n;
            if (pdMainImagePresenter4 != null) {
                String str4 = pdMainImagePresenter4.getMainImageParams().mSkuTag;
            }
            kVar.i(wareBusinessTopVideoControl8, kVar, false, this.B, this.C, this.D, this.H);
            this.E.y(false);
            T();
        }
    }

    public void b(boolean z) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdVideoBizData pdVideoBizData;
        WareBusinessTopVideoControl wareBusinessTopVideoControl;
        PdMainImageParams pdMainImageParams;
        VideoPlayView videoPlayView;
        if (this.E == null || this.v == null || (wareBuinessUnitMainImageBizDataEntity = this.r.bizData) == null || (pdVideoBizData = wareBuinessUnitMainImageBizDataEntity.videoBizData) == null || (wareBusinessTopVideoControl = pdVideoBizData.videoControl) == null || !wareBusinessTopVideoControl.isHasMasterVideo()) {
            return;
        }
        com.jingdong.sdk.log.Log.e("videoPlay", "prepare Player view " + z);
        this.E.z = this.f4654n;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo = this.r.bizData.videoBizData.videoControl.masterVideo;
        SmallWindowManager.getInstance().close();
        com.jingdong.common.widget.custom.liveplayer.videosmallwindow.SmallWindowManager.getInstance().close();
        this.E.t(this.r.bizData.videoBizData.videoPlayerFlag);
        this.E.q(true, z);
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter != null && (pdMainImageParams = pdMainImagePresenter.mainImageParams) != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI && (videoPlayView = this.E.a) != null) {
            videoPlayView.setNeedJudgeNetOnStart(!pdMainImageParams.is4GCanPlayVideo);
        }
        if (this.J) {
            if (this.K) {
                com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
                if (kVar != null) {
                    kVar.g(L(), true);
                }
            } else {
                S();
            }
        } else {
            R();
        }
        if (wareBusinessMaterVideoInfo.isHasMarkInfo()) {
            this.E.p(wareBusinessMaterVideoInfo.videoMarkList);
        }
        com.jd.lib.productdetail.mainimage.old.k kVar2 = this.E;
        if (kVar2.o) {
            kVar2.H(false);
            if (this.I.getVisibility() == 0) {
                if (this.f4654n.getMainImageParams().isElder) {
                    PDCalorieImageUtil.get().display("2707", this.I);
                } else {
                    this.I.setBackgroundResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
                }
            }
        } else if (kVar2.f5170n) {
            if (this.f4650j) {
                kVar2.I();
                P();
            }
        } else if (this.f4650j) {
            kVar2.h(wareBusinessMaterVideoInfo, this.r.bizData.videoBizData.videoPlayerFlag);
            P();
        }
        if (this.E.o) {
            this.I.setVisibility(0);
            if (!this.E.p) {
                T();
            }
        } else {
            this.I.setVisibility(8);
        }
        com.jd.lib.productdetail.mainimage.old.k kVar3 = this.E;
        if (kVar3 == null) {
            return;
        }
        kVar3.j(new h(this));
        com.jd.lib.productdetail.mainimage.old.k kVar4 = this.E;
        kVar4.f5166j = new i(this);
        j jVar = new j(this);
        VideoPlayView videoPlayView2 = kVar4.a;
        if (videoPlayView2 != null) {
            kVar4.f5165i = jVar;
            videoPlayView2.setOnClickListener(new b0(kVar4));
        }
        this.E.l(new k(this));
        this.E.m(new com.jd.lib.productdetail.mainimage.holder.video.b(this));
        this.E.k(new c(this));
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.I = (ImageView) view.findViewById(R.id.pd_topimage_playbuttonimage);
        this.H = (FrameLayout) view.findViewById(R.id.lib_pd_hoder_topimage_video_container);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void g(PDViewEvent pDViewEvent) {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        com.jd.lib.productdetail.mainimage.old.k kVar2;
        if (TextUtils.equals("pd_action_show_or_hide_top_view", pDViewEvent.mKey)) {
            int intValue = ((Integer) pDViewEvent.mObject).intValue();
            if (intValue == 4) {
                this.J = true;
                S();
            } else if (intValue == 3) {
                this.J = false;
                R();
            }
        } else if (TextUtils.equals("pd_PdInfoFragment_notify_status", pDViewEvent.mKey)) {
            int intValue2 = ((Integer) pDViewEvent.mObject).intValue();
            if (intValue2 == 3) {
                if (this.E == null || this.J) {
                    return;
                }
                R();
            } else if (intValue2 == 4 && (kVar2 = this.E) != null && kVar2.f5168l == 1) {
                S();
            }
        } else if (!TextUtils.equals("pd_ProductDetailActivity_refreshPage", pDViewEvent.mKey) && !TextUtils.equals("pd_action_event_query_skudyinfo", pDViewEvent.mKey) && !TextUtils.equals("pd_action_event_change_address", pDViewEvent.mKey)) {
            if (TextUtils.equals("pd_action_network_no_wifi", pDViewEvent.mKey)) {
                com.jd.lib.productdetail.mainimage.old.k kVar3 = this.E;
                if (kVar3 == null || !this.M) {
                    return;
                }
                this.M = false;
                VideoPlayView videoPlayView = kVar3.a;
                if (videoPlayView != null) {
                    videoPlayView.wifiChangeTo4G();
                }
            } else if (TextUtils.equals("pd_action_network_wifi", pDViewEvent.mKey)) {
                com.jd.lib.productdetail.mainimage.old.k kVar4 = this.E;
                if (kVar4 == null || this.M) {
                    return;
                }
                this.M = true;
                VideoPlayView videoPlayView2 = kVar4.a;
                if (videoPlayView2 != null) {
                    videoPlayView2.mobileNetChangeToWifi();
                }
            } else if (TextUtils.equals("pd_action_detail_key_back", pDViewEvent.mKey)) {
                N();
            } else if (TextUtils.equals("action_event_every_layer_show", pDViewEvent.mKey)) {
                if (!this.f4650j || (kVar = this.E) == null) {
                    return;
                }
                if (this.J && kVar.f5168l == 1) {
                    return;
                }
                if (kVar.f5170n && kVar.C()) {
                    com.jd.lib.productdetail.mainimage.old.k kVar5 = this.E;
                    WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
                    WareBusinessTopVideoControl wareBusinessTopVideoControl = wareBusinessUnitMainImageEntity != null ? this.r.bizData.videoBizData.videoControl : null;
                    if (wareBusinessUnitMainImageEntity != null) {
                        String str = wareBusinessUnitMainImageEntity.extMap.skuId;
                    }
                    if (wareBusinessUnitMainImageEntity != null) {
                        String str2 = this.f4654n.getMainImageParams().mSkuTag;
                    }
                    kVar5.i(wareBusinessTopVideoControl, kVar5, false, this.B, this.C, this.D, this.H);
                }
                com.jd.lib.productdetail.mainimage.old.k kVar6 = this.E;
                kVar6.y(kVar6.o);
            } else if (TextUtils.equals("action_event_every_layer_dismiss", pDViewEvent.mKey)) {
                com.jd.lib.productdetail.mainimage.old.k kVar7 = this.E;
                if (kVar7 != null) {
                    boolean z = (!this.J && kVar7.z()) || this.E.f5168l != 1;
                    if (!(this.J && this.E.f5168l == 1) && z && this.f4650j) {
                        com.jd.lib.productdetail.mainimage.old.k kVar8 = this.E;
                        if (kVar8.a != null) {
                            kVar8.I();
                            P();
                        }
                    }
                }
            } else if (TextUtils.equals("pd_action_close_default_video", pDViewEvent.mKey)) {
                if (this.f4651k || this.E == null) {
                    return;
                }
                K();
            } else if (!TextUtils.equals("pd_action_event_detail_show_completed", pDViewEvent.mKey) && TextUtils.equals("action_event_change_video_constainer", pDViewEvent.mKey)) {
                this.K = true;
                com.jd.lib.productdetail.mainimage.old.k kVar9 = this.E;
                if (kVar9 != null) {
                    b(kVar9.F);
                }
            }
        } else {
            com.jd.lib.productdetail.mainimage.old.k kVar10 = this.E;
            if (kVar10 != null) {
                kVar10.c();
                Q();
            }
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void h(PdMImageEventEntity pdMImageEventEntity) {
        if (pdMImageEventEntity.pdImageEventCodeCode != PdImageEventCode.EVENT_CHANGE_SCREEN || this.f4651k) {
            return;
        }
        Object obj = pdMImageEventEntity.mObject;
        if (obj instanceof Configuration) {
            Configuration configuration = (Configuration) obj;
            com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
            if (kVar == null || kVar.a == null || kVar.f5168l != 2) {
                return;
            }
            Context context = kVar.D.get();
            if (context instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) context;
                kVar.x = (DPIUtil.getAppWidth(baseActivity) - kVar.q) - PDUtils.dip2px(10.0f);
                kVar.y = (DPIUtil.getAppWidth(baseActivity) - kVar.q) / 2;
                kVar.v = DPIUtil.getAppHeight(baseActivity) - ((kVar.r + UnStatusBarTintUtil.getNavigationBarHeight(baseActivity)) + PDUtils.dip2px(60.0f));
            }
            kVar.a.setTranslationX(kVar.x);
            kVar.a.setTranslationY(kVar.u);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void i(PdMainImagePagerEntity pdMainImagePagerEntity) {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessUnitMainImageEntity.ExtMapEntity.CategoryEntity categoryEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdVideoBizData pdVideoBizData;
        WareBusinessTopVideoControl wareBusinessTopVideoControl;
        WareBusinessMaterVideoInfo wareBusinessMaterVideoInfo;
        super.i(pdMainImagePagerEntity);
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdVideoBizData = wareBuinessUnitMainImageBizDataEntity.videoBizData) != null && (wareBusinessTopVideoControl = pdVideoBizData.videoControl) != null && (wareBusinessMaterVideoInfo = wareBusinessTopVideoControl.masterVideo) != null) {
            String str = wareBusinessMaterVideoInfo.playUrl;
            if (!TextUtils.isEmpty(str) && this.v != null && this.r.bizData.videoBizData.videoPlayerFlag && NetUtils.isWifi()) {
                PreloadManager.getInstance().doPreload(str);
            }
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
        if (wareBusinessUnitMainImageEntity == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || (categoryEntity = extMapEntity.category) == null) {
            return;
        }
        try {
            this.B = String.valueOf(categoryEntity.fstId);
            this.C = String.valueOf(this.v.extMap.category.sndId);
            this.D = String.valueOf(this.v.extMap.category.thdId);
        } catch (Exception unused) {
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void j(PdMainImagePresenter pdMainImagePresenter) {
        PdVideoContainer pdVideoContainer;
        super.j(pdMainImagePresenter);
        LifecycleOwner lifecycleOwner = this.o;
        if (lifecycleOwner == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
            return;
        }
        pdVideoContainer.videoControl.observe(lifecycleOwner, new a(pdMainImagePresenter));
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void r() {
        this.f4651k = true;
        com.jd.lib.productdetail.mainimage.old.k kVar = this.E;
        if (kVar != null) {
            kVar.n(this.f4654n.getMainImageParams().mManagerKey);
            this.E = null;
        }
    }
}
