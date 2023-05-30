package com.jd.lib.productdetail.mainimage.presenter;

import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.PdTempData;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdQuestionInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.bean.GoToBigEntnty;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.old.g0;
import com.jd.lib.productdetail.mainimage.old.h0;
import com.jd.lib.productdetail.mainimage.old.y;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.sdk.platform.floor.isv.ICommonBasicAbility;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdMainImagePresenter {
    public int appImageHeight;
    public int appImageWidth;
    public ICommonBasicAbility iCommonBasicAbility;
    public PdImageFromType imageFromType;
    public ArrayList<PdQuestionInfo> mAskQuesInfos;
    public boolean mHasRecommendPos;
    public boolean mHasRequestRecommend;
    public UnBottomDialog mLayerDialog;
    public PdMainImageParams mainImageParams;
    public MutableLiveData<Integer> mTopCoverViewId = new MutableLiveData<>();
    public MutableLiveData<PdMImageEventEntity> viewCallBackMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<PdMImageEventEntity> viewReceiveLiveData = new MutableLiveData<>();
    public MutableLiveData<PDViewEvent> pDViewEvent = new MutableLiveData<>();
    public PdTempData mTempData = new PdTempData();
    public MutableLiveData<Boolean> windowAttachState = new MutableLiveData<>(Boolean.TRUE);
    public MutableLiveData<Integer> pageSelectedIndex = new MutableLiveData<>();
    public MutableLiveData<WareBusinessUnitMainImageEntity> mainImageData = new MutableLiveData<>();
    public MutableLiveData<GoToBigEntnty> toBigEntntyMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> toDetailPage = new MutableLiveData<>();
    public MutableLiveData<Integer> pageSelectedIndexForMINI = new MutableLiveData<>();
    public MutableLiveData<Boolean> jumpToPage = new MutableLiveData<>();
    public MutableLiveData<PdCommentInfo> pdCommentInfo = new MutableLiveData<>();
    public h0 mTopImageRecommendContainer = new h0();
    public g0 mRecommendContainer = new g0();
    public y mPdMDpgLayerContainer = new y();
    public PdHolderDataContainer pdHolderDataContainer = new PdHolderDataContainer();
    public PdVideoContainer pdVideoContainer = new PdVideoContainer();
    public PdMtaJsonParamUtil pdMtaJsonParamUtil = new PdMtaJsonParamUtil();

    private PdMtaJsonParamUtil getPdMtaJsonParamUtil() {
        if (this.pdMtaJsonParamUtil == null) {
            this.pdMtaJsonParamUtil = new PdMtaJsonParamUtil();
        }
        return this.pdMtaJsonParamUtil;
    }

    public void changeSku(Context context) {
        g0 g0Var = this.mRecommendContainer;
        BaseActivity baseActivity = (BaseActivity) context;
        MutableLiveData<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> mutableLiveData = g0Var.a;
        if (mutableLiveData != null) {
            mutableLiveData.setValue(null);
        }
        g0Var.a.removeObservers(baseActivity);
        g0Var.a = new MutableLiveData<>(new PdBaseProtocolLiveData.Result(PdBaseProtocolLiveData.Result.DataStatus.NONE, null, null));
        this.mPdMDpgLayerContainer.a(baseActivity);
        this.mTopImageRecommendContainer.a(baseActivity);
        this.pdHolderDataContainer.onCleared(baseActivity);
        this.pdVideoContainer.onCleared(baseActivity);
        this.pdCommentInfo.setValue(null);
        this.mHasRecommendPos = false;
        this.mHasRequestRecommend = false;
    }

    public WareBuinessUnitMainImageBizDataEntity getBizDataByType(String str) {
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData = this.mainImageData;
        if (mutableLiveData == null || mutableLiveData.getValue() == null || this.mainImageData.getValue().magicHeadPicInfo == null) {
            return null;
        }
        for (int i2 = 0; i2 < this.mainImageData.getValue().magicHeadPicInfo.size(); i2++) {
            if (this.mainImageData.getValue().magicHeadPicInfo != null && this.mainImageData.getValue().magicHeadPicInfo.get(i2) != null && TextUtils.equals(this.mainImageData.getValue().magicHeadPicInfo.get(i2).anchorType, str)) {
                return this.mainImageData.getValue().magicHeadPicInfo.get(i2).bizData;
            }
        }
        return null;
    }

    public String getCategoryId(int i2) {
        WareBusinessUnitMainImageEntity value;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData = this.mainImageData;
        if (mutableLiveData == null || mutableLiveData.getValue() == null || (extMapEntity = (value = this.mainImageData.getValue()).extMap) == null || extMapEntity.category == null) {
            return "";
        }
        if (i2 == 0) {
            return "" + value.extMap.category.fstId;
        } else if (i2 == 1) {
            return "" + value.extMap.category.sndId;
        } else if (i2 != 2) {
            return "";
        } else {
            return "" + value.extMap.category.thdId;
        }
    }

    public PdMainImageParams getMainImageParams() {
        if (this.mainImageParams == null) {
            this.mainImageParams = new PdMainImageParams();
        }
        return this.mainImageParams;
    }

    public String getShopId() {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData = this.mainImageData;
        return (mutableLiveData == null || mutableLiveData.getValue() == null || (extMapEntity = this.mainImageData.getValue().extMap) == null) ? "" : extMapEntity.shopId;
    }

    public WareBusinessMagicHeadPicInfoEntity getTopImageBanDanData() {
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = null;
        if (this.mainImageData.getValue() != null && this.mainImageData.getValue().magicHeadPicInfo != null && !this.mainImageData.getValue().magicHeadPicInfo.isEmpty()) {
            for (WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 : this.mainImageData.getValue().magicHeadPicInfo) {
                if (wareBusinessMagicHeadPicInfoEntity2 != null && TextUtils.equals(wareBusinessMagicHeadPicInfoEntity2.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN)) {
                    wareBusinessMagicHeadPicInfoEntity = wareBusinessMagicHeadPicInfoEntity2;
                }
            }
        }
        return wareBusinessMagicHeadPicInfoEntity;
    }

    public void mtaClick(String str) {
        mtaClick(str, (String) null);
    }

    public void mtaExposure(String str) {
        mtaExposure(str, "", "");
    }

    public void mtaClick(String str, boolean z) {
        mtaClick(str, (String) null, z);
    }

    public void mtaExposure(String str, boolean z) {
        mtaExposure(str, "", "", z);
    }

    public void mtaClick(String str, String str2) {
        mtaClick(str, str2, "");
    }

    public void mtaExposure(String str, String str2) {
        mtaExposure(str, str2, "");
    }

    public void mtaClick(String str, String str2, boolean z) {
        mtaClick(str, str2, "", z);
    }

    public void mtaExposure(String str, String str2, boolean z) {
        mtaExposure(str, str2, "", z);
    }

    public void mtaClick(String str, String str2, String str3) {
        if (this.mainImageParams != null) {
            String str4 = str + this.mainImageParams.eventParams;
            String jsonParamByEventId = getPdMtaJsonParamUtil().getJsonParamByEventId(str4, this.mainImageParams, str2, this.imageFromType);
            if (TextUtils.isEmpty(jsonParamByEventId)) {
                PdMtaUtil.Builder.newBuiler().setPageName(this.mainImageParams.mtaPageName).setEventId(str4).setPageParam(this.mainImageParams.skuId).setSkuTag(this.mainImageParams.mSkuTag).setPageId(this.mainImageParams.mtaPageId).setShopId(getShopId()).setEventParam(str3).build().click();
            } else {
                PdMtaUtil.Builder.newBuiler().setPageName(this.mainImageParams.mtaPageName).setEventId(str4).setPageParam(this.mainImageParams.skuId).setSkuTag(this.mainImageParams.mSkuTag).setPageId(this.mainImageParams.mtaPageId).setShopId(getShopId()).setJsonParam(jsonParamByEventId).setEventParam(str3).build().clickJson();
            }
        }
    }

    public void mtaExposure(String str, String str2, String str3) {
        if (this.mainImageParams != null) {
            String str4 = str + this.mainImageParams.eventParams;
            PdMtaUtil.Builder.newBuiler().setPageName(this.mainImageParams.mtaPageName).setEventId(str4).setPageParam(this.mainImageParams.skuId).setSkuTag(this.mainImageParams.mSkuTag).setPageId(this.mainImageParams.mtaPageId).setShopId(getShopId()).setEventParam(str3).setJsonParam(getPdMtaJsonParamUtil().getJsonParamByEventId(str4, this.mainImageParams, str2, this.imageFromType)).build().exposure();
        }
    }

    public void mtaExposure(String str, String str2, String str3, boolean z) {
        if (this.mainImageParams != null) {
            String str4 = str + this.mainImageParams.eventParams;
            PdMtaUtil.Builder.newBuiler().setPageName(this.mainImageParams.mtaPageName).setEventId(str4).setPageParam(this.mainImageParams.skuId).setSkuTag(this.mainImageParams.mSkuTag).setPageId(this.mainImageParams.mtaPageId).setShopId(getShopId()).setEventParam(str3).setJsonParam(getPdMtaJsonParamUtil().getJsonParamByEventId(str4, this.mainImageParams, str2, this.imageFromType)).setIsQuick(z).build().exposure();
        }
    }

    public void mtaClick(String str, String str2, String str3, boolean z) {
        if (this.mainImageParams != null) {
            String str4 = str + this.mainImageParams.eventParams;
            String jsonParamByEventId = getPdMtaJsonParamUtil().getJsonParamByEventId(str4, this.mainImageParams, str2, this.imageFromType);
            if (TextUtils.isEmpty(jsonParamByEventId)) {
                PdMtaUtil.Builder.newBuiler().setPageName(this.mainImageParams.mtaPageName).setEventId(str4).setPageParam(this.mainImageParams.skuId).setSkuTag(this.mainImageParams.mSkuTag).setPageId(this.mainImageParams.mtaPageId).setShopId(getShopId()).setIsQuick(z).setEventParam(str3).build().click();
            } else {
                PdMtaUtil.Builder.newBuiler().setPageName(this.mainImageParams.mtaPageName).setEventId(str4).setPageParam(this.mainImageParams.skuId).setSkuTag(this.mainImageParams.mSkuTag).setPageId(this.mainImageParams.mtaPageId).setShopId(getShopId()).setJsonParam(jsonParamByEventId).setEventParam(str3).setIsQuick(z).build().clickJson();
            }
        }
    }
}
