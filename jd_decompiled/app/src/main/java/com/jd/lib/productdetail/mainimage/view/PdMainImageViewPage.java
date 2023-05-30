package com.jd.lib.productdetail.mainimage.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.AbBuriedExpLabelsEntity;
import com.jd.lib.productdetail.core.entitys.BigImageEntity;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDrugInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicAnchorEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicPicItems;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopVideoControl;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.adapter.PdMainImageAdapter;
import com.jd.lib.productdetail.mainimage.bean.GoToBigEntnty;
import com.jd.lib.productdetail.mainimage.bean.PDPendingExplore;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.bean.PdMainImagePagerEntity;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.holder.dym.PdMCooperManager;
import com.jd.lib.productdetail.mainimage.holder.dym.PdMCooperViewHolder;
import com.jd.lib.productdetail.mainimage.old.PdMainImageAnchorView;
import com.jd.lib.productdetail.mainimage.old.g0;
import com.jd.lib.productdetail.mainimage.presenter.PdImagePagePosViewManager;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdMainStaticData;
import com.jd.lib.productdetail.mainimage.protocol.a;
import com.jd.lib.productdetail.mainimage.utils.PdMImagePullToSeekMoreViewPage2;
import com.jd.lib.productdetail.mainimage.utils.ZoomOutPageTransformer;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.abmta.ABMtaUtils;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PdMainImageViewPage extends ConstraintLayout implements LifecycleObserver {
    public static final String INTENT_ACTION_PD_MAINIMAGE_SLIDEPIC = "com.jingdong.productActivity.INTENT_ACTION_PD_MAINIMAGE_SLIDEPIC";
    public String buyersIcon;
    public boolean commentPriorityFlagNew;
    private int currIndex;
    private HashMap<Integer, String> gifIndexMap;
    private PdMainImageTopTagView imageTopTagView;
    private boolean isDestroy;
    public boolean isHasMergeImage;
    private boolean isLongImage;
    private LifecycleOwner lifecycleOwner;
    public LocalBroadcastManager localBroadcastManager;
    private PdMainImageLocalReceiver localReceiver;
    private FrameLayout mAnchorLayout;
    private PdMainImageAnchorView mAnchorRecyclerView;
    public int mAskShowPosition;
    private ArrayList<String> mBigImageList;
    public String mCommentDefaultUrl;
    public int mCommentZcxPosition;
    private Context mContext;
    public int mGiftShowPosition;
    private ArrayList<String> mIsvItems;
    private HashMap<String, PDPendingExplore> mPendingInfo;
    public int mRecommendPosition;
    public int mRecommendRankPosition;
    public String mSuitAnchorType;
    public int mSuitPosition;
    public int mYpsmsPosition;
    private PdMainImagePresenter mainImagePresenter;
    private ViewPager2 pdMainImage;
    private PdMainImageAdapter pdMainImageAdapter;
    private PdMImagePullToSeekMoreViewPage2 pdMainImageWrap;
    public String rankDefaultUrl;
    private WareBusinessUnitMainImageEntity topImageAllData;
    private ZoomOutPageTransformer zoomOutPageTransformer;

    /* loaded from: classes15.dex */
    public class PdMainImageLocalReceiver extends BroadcastReceiver {
        public PdMainImageLocalReceiver() {
            PdMainImageViewPage.this = r1;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (PdMainImageViewPage.this.isDestroy || PdMainImageViewPage.this.pdMainImage == null || PdMainImageViewPage.this.mContext == null) {
                return;
            }
            int intExtra = intent.getIntExtra("page", 0);
            if (intExtra == -1) {
                PdMainImageViewPage.this.mainImagePresenter.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.JUMPTODETAIL, null));
                return;
            }
            PdMainImageViewPage pdMainImageViewPage = PdMainImageViewPage.this;
            pdMainImageViewPage.changeMainImageSize(pdMainImageViewPage.isHasMergeImage && intExtra == 0, pdMainImageViewPage.isLongImage);
            PdMainImageViewPage.this.pdMainImage.setCurrentItem(intExtra, false);
        }
    }

    public PdMainImageViewPage(Context context) {
        super(context);
        this.mAskShowPosition = -1;
        this.mRecommendRankPosition = -1;
        this.mYpsmsPosition = -1;
        this.mSuitPosition = -1;
        this.mRecommendPosition = -1;
        this.mCommentZcxPosition = -1;
        this.mGiftShowPosition = -1;
        this.isDestroy = false;
        this.mPendingInfo = new HashMap<>();
        initView(context);
    }

    private void buildBigImageData() {
        WareBuinessUnitMainImageBizDataEntity.PdCommentBizData pdCommentBizData;
        WareImageRecommendRankEntity wareImageRecommendRankEntity;
        ArrayList<String> arrayList = this.mBigImageList;
        if (arrayList == null) {
            this.mBigImageList = new ArrayList<>(this.topImageAllData.magicHeadPicInfo.size());
        } else {
            arrayList.clear();
        }
        this.mBigImageList.addAll(getBigImageData(this.topImageAllData.magicHeadPicInfo));
        initIsvItemsInfo(this.topImageAllData.magicHeadPicInfo);
        this.mCommentZcxPosition = -1;
        this.mGiftShowPosition = -1;
        this.mAskShowPosition = -1;
        this.mYpsmsPosition = -1;
        this.mSuitPosition = -1;
        this.mSuitAnchorType = null;
        this.mRecommendPosition = -1;
        this.mRecommendRankPosition = -1;
        this.mainImagePresenter.mHasRecommendPos = false;
        for (int i2 = 0; i2 < this.topImageAllData.magicHeadPicInfo.size(); i2++) {
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.topImageAllData.magicHeadPicInfo.get(i2);
            if (wareBusinessMagicHeadPicInfoEntity != null) {
                if (TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN, wareBusinessMagicHeadPicInfoEntity.anchorType)) {
                    this.mRecommendRankPosition = i2;
                    WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData;
                    if (wareBuinessUnitMainImageBizDataEntity != null && (wareImageRecommendRankEntity = wareBuinessUnitMainImageBizDataEntity.bangDanInfo) != null) {
                        this.rankDefaultUrl = wareImageRecommendRankEntity.default_image;
                    }
                }
                if (TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_NEW, wareBusinessMagicHeadPicInfoEntity.anchorType)) {
                    this.mCommentZcxPosition = i2;
                    WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData;
                    if (wareBuinessUnitMainImageBizDataEntity2 != null && (pdCommentBizData = wareBuinessUnitMainImageBizDataEntity2.commentBizData) != null) {
                        this.buyersIcon = pdCommentBizData.buyersIcon;
                        this.mCommentDefaultUrl = pdCommentBizData.defaultImageUrl;
                        this.commentPriorityFlagNew = pdCommentBizData.commentPriorityFlag;
                    }
                }
                if (TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ASK, wareBusinessMagicHeadPicInfoEntity.anchorType)) {
                    this.mAskShowPosition = i2;
                }
                if (TextUtils.equals("gift", wareBusinessMagicHeadPicInfoEntity.anchorType)) {
                    this.mGiftShowPosition = i2;
                }
                if (TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_YPSMS, wareBusinessMagicHeadPicInfoEntity.anchorType)) {
                    this.mYpsmsPosition = i2;
                }
                if (TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_SUIT, wareBusinessMagicHeadPicInfoEntity.anchorType) || TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_DPG_SMALL, wareBusinessMagicHeadPicInfoEntity.anchorType)) {
                    this.mSuitPosition = i2;
                    this.mSuitAnchorType = wareBusinessMagicHeadPicInfoEntity.anchorType;
                }
                if (TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.FB_TOP_IMAGE_RECOMMEND, wareBusinessMagicHeadPicInfoEntity.anchorType)) {
                    this.mRecommendPosition = i2;
                    this.mainImagePresenter.mHasRecommendPos = true;
                }
            }
        }
    }

    public void buildData2View() {
        ViewPager2 viewPager2 = this.pdMainImage;
        if (viewPager2 != null) {
            this.currIndex = 0;
            viewPager2.setCurrentItem(0, false);
            PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
            if (pdMainImagePresenter != null) {
                pdMainImagePresenter.pageSelectedIndex.postValue(0);
            }
        }
        PdMainImagePresenter pdMainImagePresenter2 = this.mainImagePresenter;
        if (pdMainImagePresenter2 != null && pdMainImagePresenter2.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mAnchorRecyclerView.getLayoutParams();
            layoutParams.bottomMargin = 0;
            this.mAnchorRecyclerView.setLayoutParams(layoutParams);
        }
        showAchorInfo();
        PdMainImageTopTagView pdMainImageTopTagView = this.imageTopTagView;
        if (pdMainImageTopTagView != null) {
            pdMainImageTopTagView.setTopImageAllData(this.topImageAllData);
            this.imageTopTagView.setImageIndex(1);
        }
        initCooperManager();
        buildBigImageData();
        expoMainImage();
        mtaExploreEntrance(0);
    }

    public void changeMainImageSize(boolean z, boolean z2) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter == null || pdMainImagePresenter.appImageHeight <= 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.pdMainImageWrap.getLayoutParams();
        PdMainImagePresenter pdMainImagePresenter2 = this.mainImagePresenter;
        layoutParams.width = pdMainImagePresenter2.appImageWidth;
        if (pdMainImagePresenter2.imageFromType != PdImageFromType.PRODUCTDETAIL) {
            this.pdMainImageWrap.getLayoutParams().height = this.mainImagePresenter.appImageHeight;
        } else if (z2) {
            if (z) {
                this.pdMainImageWrap.getLayoutParams().height = this.mainImagePresenter.appImageWidth + PDUtils.dip2px(this.mContext, PdMainStaticData.HOLDER_SHADOW_HEIGHT - PdMainStaticData.ANCHOR_LAYOUT_HEIGHT);
                return;
            }
            this.pdMainImageWrap.getLayoutParams().height = this.mainImagePresenter.appImageHeight + PDUtils.dip2px(this.mContext, PdMainStaticData.HOLDER_SHADOW_HEIGHT);
        } else if (z) {
            this.pdMainImageWrap.getLayoutParams().height = this.mainImagePresenter.appImageHeight + PDUtils.dip2px(this.mContext, PdMainStaticData.HOLDER_SHADOW_HEIGHT - PdMainStaticData.ANCHOR_LAYOUT_HEIGHT);
        } else {
            this.pdMainImageWrap.getLayoutParams().height = this.mainImagePresenter.appImageHeight + PDUtils.dip2px(this.mContext, PdMainStaticData.HOLDER_SHADOW_HEIGHT);
        }
    }

    public void dealData(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
        int i2;
        boolean z = false;
        this.isHasMergeImage = false;
        this.isLongImage = false;
        if (wareBusinessUnitMainImageEntity != null) {
            WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = wareBusinessUnitMainImageEntity.extMap;
            this.isLongImage = extMapEntity != null && ((i2 = extMapEntity.magicHeadPicType) == 1 || i2 == 2);
            if (extMapEntity != null && extMapEntity.hasCover) {
                z = true;
            }
            this.isHasMergeImage = z;
        }
    }

    private void expoMainImage() {
        PdMainImageParams pdMainImageParams;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        Object obj;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdVideoBizData pdVideoBizData;
        WareBusinessTopVideoControl wareBusinessTopVideoControl;
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter == null || (pdMainImageParams = pdMainImagePresenter.mainImageParams) == null || TextUtils.isEmpty(pdMainImageParams.skuId) || this.mainImagePresenter.imageFromType != PdImageFromType.PRODUCTDETAIL || (wareBusinessUnitMainImageEntity = this.topImageAllData) == null || wareBusinessUnitMainImageEntity.extMap == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (!TextUtils.isEmpty(this.topImageAllData.extMap.activityId)) {
            jDJSONObject.put("activity_id", (Object) this.topImageAllData.extMap.activityId);
        } else {
            jDJSONObject.put("activity_id", (Object) "-100");
        }
        if (!TextUtils.isEmpty(this.topImageAllData.extMap.groupId)) {
            jDJSONObject.put("group_id", (Object) this.topImageAllData.extMap.groupId);
        } else {
            jDJSONObject.put("group_id", (Object) "-100");
        }
        this.mainImagePresenter.mtaExposure("Productdetail_MainPhotoExpo", jDJSONObject.toJSONString(), true);
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        List<WareBusinessMagicHeadPicInfoEntity> list = this.topImageAllData.magicHeadPicInfo;
        int size = list != null ? list.size() : 0;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.topImageAllData;
        String str = wareBusinessUnitMainImageEntity2.extMap.mainPicV12 ? "12.0" : "10.0";
        String str2 = "0";
        String str3 = "1";
        if (wareBusinessUnitMainImageEntity2.magicHeadPicInfo != null) {
            for (int i2 = 0; i2 < this.topImageAllData.magicHeadPicInfo.size(); i2++) {
                WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.topImageAllData.magicHeadPicInfo.get(i2);
                if ((wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdVideoBizData = wareBuinessUnitMainImageBizDataEntity.videoBizData) == null || (wareBusinessTopVideoControl = pdVideoBizData.videoControl) == null || !wareBusinessTopVideoControl.isHasMasterVideo()) ? false : true) {
                    obj = "1";
                    break;
                }
            }
        }
        obj = "0";
        List<WareBusinessMagicAnchorEntity> list2 = this.topImageAllData.magicAnchor;
        if (list2 != null && !list2.isEmpty()) {
            str2 = "1";
        }
        if (this.isHasMergeImage) {
            str3 = "2";
        } else if (this.isLongImage) {
            str3 = "3";
        }
        jDJSONObject2.put("page", (Object) ("" + size));
        jDJSONObject2.put("cv", (Object) str);
        jDJSONObject2.put("is_mainvideo", obj);
        jDJSONObject2.put("mainpic_struc", (Object) str2);
        jDJSONObject2.put("manpic_showtype", (Object) str3);
        this.mainImagePresenter.mtaExposure("Productdetail_MainPicSumExpo", jDJSONObject2.toJSONString());
    }

    private BaseActivity getActivity(Context context) {
        if (context instanceof BaseActivity) {
            return (BaseActivity) context;
        }
        return null;
    }

    private String getAskQuesNum(String str) {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter == null || pdMainImagePresenter.mAskQuesInfos == null || !TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ASK)) {
            return "0";
        }
        return this.mainImagePresenter.mAskQuesInfos.size() + "";
    }

    private ArrayList<String> getBigImageData(List<WareBusinessMagicHeadPicInfoEntity> list) {
        WareBusinessWareImageEntity wareBusinessWareImageEntity;
        ArrayList<String> arrayList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            this.gifIndexMap = new HashMap<>();
            for (int i2 = 0; i2 < list.size(); i2++) {
                WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = list.get(i2);
                if (wareBusinessMagicHeadPicInfoEntity != null && (wareBusinessWareImageEntity = wareBusinessMagicHeadPicInfoEntity.wareImage) != null && !TextUtils.isEmpty(wareBusinessWareImageEntity.big)) {
                    if (TextUtils.equals(WareBusinessMagicHeadPicInfoEntity.IMAGE_TYPE_GIF, wareBusinessMagicHeadPicInfoEntity.wareImage.imageType)) {
                        this.gifIndexMap.put(Integer.valueOf(i2), wareBusinessMagicHeadPicInfoEntity.wareImage.big);
                    }
                    arrayList.add(wareBusinessMagicHeadPicInfoEntity.wareImage.big);
                } else {
                    arrayList.add("");
                }
            }
        }
        return arrayList;
    }

    private View getDecorView(Context context) {
        if (context instanceof BaseActivity) {
            return ((BaseActivity) context).getWindow().getDecorView();
        }
        return null;
    }

    private String getISVData(String str, String str2) {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        int i2;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i3 = this.mainImagePresenter.appImageWidth;
            float f2 = i3;
            jSONObject.put("width", PDUtils.px2dp(f2));
            jSONObject.put("isBigImage", true);
            jSONObject.put("frameIndex", str2);
            jSONObject.put("isDark", this.mainImagePresenter.getMainImageParams().isDark ? "1" : "0");
            PdMCooperViewHolder.G(jSONObject, this.topImageAllData, this.mainImagePresenter.getMainImageParams().mSkuTag, this.mainImagePresenter.getMainImageParams().mManagerKey);
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
            if (wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && ((i2 = extMapEntity.magicHeadPicType) == 1 || i2 == 2)) {
                i3 = (int) ((f2 * 4.0f) / 3.0f);
            }
            jSONObject.put("height", PDUtils.px2dp(i3));
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public String getMtaJsonParam(int i2) {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareImageRecommendRankEntity wareImageRecommendRankEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2;
        WareBuinessUnitMainImageBizDataEntity.PdDrugBizData pdDrugBizData;
        PdDrugInfo pdDrugInfo;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        if (wareBusinessUnitMainImageEntity == null || (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) == null || list.size() <= i2 || (wareBusinessMagicHeadPicInfoEntity = this.topImageAllData.magicHeadPicInfo.get(i2)) == null) {
            return "";
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        MutableLiveData<PdCommentInfo> mutableLiveData = this.mainImagePresenter.pdCommentInfo;
        jDJSONObject.put("type", (Object) wareBusinessMagicHeadPicInfoEntity.getAnchorTypeWithCommentInfo(mutableLiveData != null ? mutableLiveData.getValue() : null));
        jDJSONObject.put("categoryId3", (Object) this.mainImagePresenter.getCategoryId(2));
        jDJSONObject.put("frame", (Object) Integer.valueOf(i2 + 1));
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = this.topImageAllData.extMap;
        if (extMapEntity != null && !TextUtils.isEmpty(extMapEntity.activityId)) {
            jDJSONObject.put("activity_id", (Object) this.topImageAllData.extMap.activityId);
        } else {
            jDJSONObject.put("activity_id", (Object) "-100");
        }
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity2 = this.topImageAllData.extMap;
        if (extMapEntity2 != null && !TextUtils.isEmpty(extMapEntity2.groupId)) {
            jDJSONObject.put("group_id", (Object) this.topImageAllData.extMap.groupId);
        } else {
            jDJSONObject.put("group_id", (Object) "-100");
        }
        jDJSONObject.put("QuesNum", (Object) getAskQuesNum(wareBusinessMagicHeadPicInfoEntity.anchorType));
        if (TextUtils.equals(wareBusinessMagicHeadPicInfoEntity.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_YPSMS) && (wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdDrugBizData = wareBuinessUnitMainImageBizDataEntity2.drugInstructionsBizData) != null && (pdDrugInfo = pdDrugBizData.drugInfo) != null) {
            if (pdDrugInfo.tips != null) {
                jDJSONObject.put("LableNum", (Object) (wareBusinessMagicHeadPicInfoEntity.bizData.drugInstructionsBizData.drugInfo.tips.size() + ""));
            } else {
                jDJSONObject.put("LableNum", (Object) "0");
            }
            if (!TextUtils.isEmpty(wareBusinessMagicHeadPicInfoEntity.bizData.drugInstructionsBizData.drugInfo.imageInCell)) {
                jDJSONObject.put("isPhoto", (Object) "1");
            } else {
                jDJSONObject.put("isPhoto", (Object) "0");
            }
        } else {
            jDJSONObject.put("LableNum", (Object) "0");
            jDJSONObject.put("isPhoto", (Object) "");
        }
        if (TextUtils.equals(wareBusinessMagicHeadPicInfoEntity.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_GOLD_RANK_BANG_DAN) && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (wareImageRecommendRankEntity = wareBuinessUnitMainImageBizDataEntity.bangDanInfo) != null) {
            jDJSONObject.put("rankid", (Object) wareImageRecommendRankEntity.rankId);
            jDJSONObject.put("rank_type", (Object) wareImageRecommendRankEntity.rankType);
            jDJSONObject.put("clkSrv", (Object) wareImageRecommendRankEntity.clkSrv);
        }
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null && pdMainImagePresenter.getMainImageParams() != null) {
            PDUtils.setFloorPriceJson(jDJSONObject, this.mainImagePresenter.getMainImageParams().floorPriceMta);
            PDUtils.setCardInfo(jDJSONObject, this.mainImagePresenter.getMainImageParams().brandId, "bpMainImage", getRootView());
        }
        try {
            PDUtils.setFloorCid(jDJSONObject, this.mainImagePresenter.getCategoryId(0), this.mainImagePresenter.getCategoryId(1), this.mainImagePresenter.getCategoryId(2));
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return jDJSONObject.toJSONString();
    }

    public void getRecommendData() {
        g0 g0Var;
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter == null || (g0Var = pdMainImagePresenter.mRecommendContainer) == null || g0Var.a == null || !pdMainImagePresenter.mHasRecommendPos || pdMainImagePresenter.mHasRequestRecommend) {
            return;
        }
        pdMainImagePresenter.mHasRequestRecommend = true;
        g0Var.a((BaseActivity) this.mContext, this.topImageAllData.extMap.skuId, "", "1", pdMainImagePresenter);
    }

    public void gotoBigPig(int i2, boolean z, boolean z2, boolean z3) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        HeadPicGiftInfoEntity headPicGiftInfoEntity;
        int i3;
        PdMainImageAdapter pdMainImageAdapter;
        PdMainImagePagerEntity a;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2;
        WareImageRecommendRankEntity wareImageRecommendRankEntity;
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity3;
        WareBuinessUnitMainImageBizDataEntity.PdDpgSuitBizData pdDpgSuitBizData;
        g0 g0Var;
        MutableLiveData<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> mutableLiveData;
        PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo> value;
        if (JDPrivacyHelper.isAcceptPrivacy(getActivity(this.mContext))) {
            if ((this.mainImagePresenter == null && this.topImageAllData == null) || this.topImageAllData.magicHeadPicInfo == null) {
                return;
            }
            BigImageEntity bigImageEntity = new BigImageEntity();
            int i4 = this.mAskShowPosition;
            if (i4 > 0 && this.topImageAllData.magicHeadPicInfo.get(i4) != null && this.topImageAllData.magicHeadPicInfo.get(this.mAskShowPosition).bizData != null) {
                bigImageEntity.wareImageQaEntity = this.topImageAllData.magicHeadPicInfo.get(this.mAskShowPosition).bizData.drugQuestionBizData;
            }
            bigImageEntity.askPosition = this.mAskShowPosition;
            bigImageEntity.ypsmsPosition = this.mYpsmsPosition;
            bigImageEntity.recommendPosition = this.mRecommendPosition;
            PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
            if (pdMainImagePresenter != null && (g0Var = pdMainImagePresenter.mRecommendContainer) != null && (mutableLiveData = g0Var.a) != null && (value = mutableLiveData.getValue()) != null) {
                bigImageEntity.recommendProductListInfo = value.mData;
            }
            bigImageEntity.commentZcxPosition = this.mCommentZcxPosition;
            bigImageEntity.recommendRankPosition = this.mRecommendRankPosition;
            PdMainImagePresenter pdMainImagePresenter2 = this.mainImagePresenter;
            if (pdMainImagePresenter2 != null) {
                bigImageEntity.mCategroyId1 = pdMainImagePresenter2.getCategoryId(0);
                bigImageEntity.mCategroyId2 = this.mainImagePresenter.getCategoryId(1);
                bigImageEntity.mCategroyId3 = this.mainImagePresenter.getCategoryId(2);
            }
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
            WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = wareBusinessUnitMainImageEntity.extMap;
            bigImageEntity.storeId = extMapEntity.shopId;
            int i5 = this.mSuitPosition;
            bigImageEntity.suitPosition = i5;
            bigImageEntity.suitAnchorType = this.mSuitAnchorType;
            bigImageEntity.magicHeadPicType = extMapEntity.magicHeadPicType;
            bigImageEntity.commentPriorityFlagNew = this.commentPriorityFlagNew;
            bigImageEntity.commentDefaultUrl = this.mCommentDefaultUrl;
            if (i5 != -1 && this.pdMainImageAdapter != null && i5 < wareBusinessUnitMainImageEntity.magicHeadPicInfo.size() && this.topImageAllData.magicHeadPicInfo.get(this.mSuitPosition) != null && (wareBuinessUnitMainImageBizDataEntity3 = this.topImageAllData.magicHeadPicInfo.get(this.mSuitPosition).bizData) != null && (pdDpgSuitBizData = wareBuinessUnitMainImageBizDataEntity3.dpgSuitBizData) != null) {
                bigImageEntity.mainProduct = pdDpgSuitBizData.mainProductShortDpgSuit;
                bigImageEntity.suitDetails = pdDpgSuitBizData.suitDetails;
                bigImageEntity.dpgDetails = pdDpgSuitBizData.dpgDetails;
            }
            int i6 = this.mYpsmsPosition;
            if (i6 != -1 && this.pdMainImageAdapter != null && i6 < this.topImageAllData.magicHeadPicInfo.size() && this.topImageAllData.magicHeadPicInfo.get(this.mYpsmsPosition) != null && this.topImageAllData.magicHeadPicInfo.get(this.mYpsmsPosition).bizData != null && this.topImageAllData.magicHeadPicInfo.get(this.mYpsmsPosition).bizData.drugInstructionsBizData != null) {
                bigImageEntity.drugInfo = this.topImageAllData.magicHeadPicInfo.get(this.mYpsmsPosition).bizData.drugInstructionsBizData.drugInfo;
            }
            int i7 = this.mRecommendPosition;
            if (i7 > 0 && (list = this.topImageAllData.magicHeadPicInfo) != null && list.get(i7) != null && this.topImageAllData.magicHeadPicInfo.get(this.mRecommendPosition).bizData.tjBizData != null) {
                bigImageEntity.wareImageRecommendEntity = this.topImageAllData.magicHeadPicInfo.get(this.mRecommendPosition).bizData.tjBizData.tjInfo;
            }
            if (!TextUtils.isEmpty(this.buyersIcon)) {
                bigImageEntity.buyersIcon = this.buyersIcon;
            }
            if (!TextUtils.isEmpty(this.rankDefaultUrl)) {
                bigImageEntity.rankDefaultUrl = this.rankDefaultUrl;
            }
            int i8 = this.mRecommendRankPosition;
            if (i8 != -1 && (pdMainImageAdapter = this.pdMainImageAdapter) != null && (a = pdMainImageAdapter.a(i8)) != null && (wareBusinessMagicHeadPicInfoEntity = a.magicHeadPicData) != null && (wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (wareImageRecommendRankEntity = wareBuinessUnitMainImageBizDataEntity2.bangDanInfo) != null) {
                bigImageEntity.bangDanInfo = wareImageRecommendRankEntity;
            }
            Intent intent = new Intent(this.mContext, PdBigImageActivity.class);
            intent.putExtra("EXTRA_VIEW_INFO", new Bundle());
            Bundle bundle = new Bundle();
            bundle.putInt("position", i2);
            PdMainImagePresenter pdMainImagePresenter3 = PdBigImageActivity.Q;
            bundle.putSerializable("bigImageEntity", bigImageEntity);
            bundle.putBoolean("player2", this.mainImagePresenter.getBizDataByType("video") != null && this.mainImagePresenter.getBizDataByType("video").videoBizData.videoPlayerFlag);
            bundle.putString("eventParams", this.mainImagePresenter.getMainImageParams().eventParams);
            bundle.putBoolean("fromBigImage", z);
            bundle.putBoolean("autoPlay", z2);
            bundle.putBoolean("pureMode", z3);
            bundle.putBoolean(AddressConstant.INTENT_EXTAS_IS_FROM_ELDER, this.mainImagePresenter.getMainImageParams().isElder);
            bundle.putString("managerKey", this.mainImagePresenter.getMainImageParams().mManagerKey);
            MutableLiveData<PdCommentInfo> mutableLiveData2 = this.mainImagePresenter.pdCommentInfo;
            if (mutableLiveData2 != null) {
                bundle.putParcelable("pdCommentInfo", mutableLiveData2.getValue());
            }
            bundle.putString("sku", this.topImageAllData.extMap.skuId);
            bundle.putString("skuTag", this.mainImagePresenter.getMainImageParams().mSkuTag);
            bundle.putStringArrayList("isvInfo", this.mIsvItems);
            WareBusinessMagicHeadPicInfoEntity topImageGiftData = getTopImageGiftData();
            if (topImageGiftData != null && (wareBuinessUnitMainImageBizDataEntity = topImageGiftData.bizData) != null && (headPicGiftInfoEntity = wareBuinessUnitMainImageBizDataEntity.headPicGiftInfo) != null && (i3 = this.mGiftShowPosition) != -1) {
                headPicGiftInfoEntity.index = i3;
                bundle.putParcelable("topImageGiftInfo", headPicGiftInfoEntity);
            }
            PdMainImagePresenter pdMainImagePresenter4 = this.mainImagePresenter;
            if (pdMainImagePresenter4 == null || pdMainImagePresenter4.getBizDataByType("video") == null || this.mainImagePresenter.getBizDataByType("video").videoBizData.videoControl == null || !this.mainImagePresenter.getBizDataByType("video").videoBizData.videoControl.isHasMasterVideo()) {
                PdMainImagePresenter pdMainImagePresenter5 = this.mainImagePresenter;
                if (pdMainImagePresenter5 != null && pdMainImagePresenter5.getBizDataByType(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS_VIDEO) != null && this.mainImagePresenter.getBizDataByType(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS_VIDEO).videoBizData.videoControl != null && this.mainImagePresenter.getBizDataByType(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS_VIDEO).videoBizData.videoControl.isHasMasterVideo() && !z3) {
                    bundle.putParcelable("video", this.mainImagePresenter.getBizDataByType(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS_VIDEO).videoBizData.videoControl);
                }
            } else if (!z3) {
                bundle.putParcelable("video", this.mainImagePresenter.getBizDataByType("video").videoBizData.videoControl);
            }
            bundle.putStringArrayList("image_show_list_url", this.mBigImageList);
            PdMainImagePresenter pdMainImagePresenter6 = this.mainImagePresenter;
            if (pdMainImagePresenter6 != null) {
                bundle.putSerializable("mainImageParams", pdMainImagePresenter6.mainImageParams);
            }
            HashMap<Integer, String> hashMap = this.gifIndexMap;
            if (hashMap != null && hashMap.size() > 0) {
                bundle.putSerializable("gifIndexMap", this.gifIndexMap);
            }
            intent.putExtras(bundle);
            this.mContext.startActivity(intent);
        }
    }

    private void initCooperManager() {
        PdMainImagePresenter pdMainImagePresenter;
        this.mPendingInfo.clear();
        if (this.lifecycleOwner == null || (pdMainImagePresenter = this.mainImagePresenter) == null || TextUtils.isEmpty(pdMainImagePresenter.getMainImageParams().mManagerKey) || PdMCooperManager.a(this.mainImagePresenter.getMainImageParams().mManagerKey) != null) {
            return;
        }
        this.lifecycleOwner.getLifecycle().addObserver(new PdMCooperManager(this.mainImagePresenter.getMainImageParams().mManagerKey, this.mainImagePresenter.getMainImageParams().moduleName));
    }

    private void initIsvItemsInfo(List<WareBusinessMagicHeadPicInfoEntity> list) {
        ArrayList<String> arrayList = this.mIsvItems;
        if (arrayList == null) {
            this.mIsvItems = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = list.get(i2);
            if (wareBusinessMagicHeadPicInfoEntity != null && wareBusinessMagicHeadPicInfoEntity.iViewType != 0) {
                this.mIsvItems.add(getISVData(wareBusinessMagicHeadPicInfoEntity.mIsvData, String.valueOf(i2)));
            } else {
                this.mIsvItems.add("");
            }
        }
    }

    private void initLifeCycle(Context context) {
        if (this.lifecycleOwner == null) {
            LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(getDecorView(context));
            this.lifecycleOwner = lifecycleOwner;
            if (lifecycleOwner != null) {
                lifecycleOwner.getLifecycle().addObserver(this);
                this.mainImagePresenter.mainImageData.observe(this.lifecycleOwner, new Observer<WareBusinessUnitMainImageEntity>() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.1
                    {
                        PdMainImageViewPage.this = this;
                    }

                    @Override // androidx.lifecycle.Observer
                    public void onChanged(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
                        if (wareBusinessUnitMainImageEntity != null) {
                            try {
                                if (wareBusinessUnitMainImageEntity.magicHeadPicInfo != null) {
                                    PdMainImageViewPage.this.topImageAllData = wareBusinessUnitMainImageEntity;
                                    PdMainImageViewPage pdMainImageViewPage = PdMainImageViewPage.this;
                                    pdMainImageViewPage.dealData(pdMainImageViewPage.topImageAllData);
                                    PdMainImageViewPage pdMainImageViewPage2 = PdMainImageViewPage.this;
                                    pdMainImageViewPage2.setImageSize(pdMainImageViewPage2.isLongImage, 0);
                                    PdMainImageViewPage.this.imageTopTagView.isShowNumLayout(true);
                                    if (PdMainImageViewPage.this.pdMainImageAdapter != null) {
                                        PdMainImageViewPage.this.pdMainImageAdapter.h(PdMainImageViewPage.this.topImageAllData, PdMainImageViewPage.this.mContext);
                                    }
                                    PdMainImageViewPage.this.buildData2View();
                                    return;
                                }
                            } catch (Exception e2) {
                                ExceptionReporter.reportExceptionToBugly(e2);
                                return;
                            }
                        }
                        PdMainImageViewPage.this.imageTopTagView.isShowNumLayout(false);
                    }
                });
                this.mainImagePresenter.pdCommentInfo.observe(this.lifecycleOwner, new Observer<PdCommentInfo>() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.2
                    {
                        PdMainImageViewPage.this = this;
                    }

                    @Override // androidx.lifecycle.Observer
                    public void onChanged(PdCommentInfo pdCommentInfo) {
                        if (pdCommentInfo != null) {
                            try {
                                PdMainImageViewPage.this.imageTopTagView.showCommentView(pdCommentInfo);
                            } catch (Exception e2) {
                                ExceptionReporter.reportExceptionToBugly(e2);
                            }
                        }
                    }
                });
                this.mainImagePresenter.toBigEntntyMutableLiveData.observe(this.lifecycleOwner, new Observer<GoToBigEntnty>() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.3
                    {
                        PdMainImageViewPage.this = this;
                    }

                    @Override // androidx.lifecycle.Observer
                    public void onChanged(GoToBigEntnty goToBigEntnty) {
                        try {
                            if (PdMainImageViewPage.this.mainImagePresenter.imageFromType != PdImageFromType.PRODUCTDETAIL_MINI) {
                                PdMainImageViewPage.this.gotoBigPig(goToBigEntnty.position, goToBigEntnty.fromBigImage, goToBigEntnty.autoPlay, goToBigEntnty.pureMode);
                            } else {
                                PdMainImageViewPage.this.mainImagePresenter.toDetailPage.setValue(Boolean.TRUE);
                            }
                        } catch (Exception e2) {
                            ExceptionReporter.reportExceptionToBugly(e2);
                        }
                    }
                });
            }
        }
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_view_mainimage, (ViewGroup) this, true);
        this.imageTopTagView = (PdMainImageTopTagView) findViewById(R.id.lib_pd_mainimage_toptagview);
        this.mAnchorRecyclerView = (PdMainImageAnchorView) findViewById(R.id.lib_pd_main_image_anchor);
        this.mAnchorLayout = (FrameLayout) findViewById(R.id.lib_pd_main_image_anchor_layout);
        if (this.mainImagePresenter == null) {
            this.mainImagePresenter = new PdMainImagePresenter();
        }
        this.imageTopTagView.setMainImagePresenter(this.mainImagePresenter);
        this.mAnchorRecyclerView.e(this.mainImagePresenter);
        initViewPager();
        initLifeCycle(this.mContext);
        registerBroadcastReceiver();
    }

    private void initViewPager() {
        PdMImagePullToSeekMoreViewPage2 pdMImagePullToSeekMoreViewPage2 = (PdMImagePullToSeekMoreViewPage2) findViewById(R.id.lib_pd_mainimage_viewpager);
        this.pdMainImageWrap = pdMImagePullToSeekMoreViewPage2;
        pdMImagePullToSeekMoreViewPage2.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        this.zoomOutPageTransformer = new ZoomOutPageTransformer();
        ViewPager2 refreshableView = this.pdMainImageWrap.getRefreshableView();
        this.pdMainImage = refreshableView;
        ZoomOutPageTransformer zoomOutPageTransformer = this.zoomOutPageTransformer;
        zoomOutPageTransformer.a = this.pdMainImageWrap;
        refreshableView.setPageTransformer(zoomOutPageTransformer);
        PdMainImageAdapter pdMainImageAdapter = new PdMainImageAdapter(this.mainImagePresenter);
        this.pdMainImageAdapter = pdMainImageAdapter;
        this.pdMainImage.setAdapter(pdMainImageAdapter);
        this.pdMainImage.setOffscreenPageLimit(1);
        this.pdMainImage.setOrientation(0);
        this.pdMainImageWrap.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ViewPager2>() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.6
            {
                PdMainImageViewPage.this = this;
            }

            @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
            public void onRefresh(PullToRefreshBase<ViewPager2> pullToRefreshBase) {
                if (PdMainImageViewPage.this.isDestroy) {
                    return;
                }
                if (PdMainImageViewPage.this.pdMainImageWrap != null) {
                    PdMainImageViewPage.this.pdMainImageWrap.onRefreshComplete();
                }
                PdMainImageViewPage.this.postDelayed(new Runnable() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        if (PdMainImageViewPage.this.isDestroy) {
                            return;
                        }
                        PdMainImageViewPage.this.mainImagePresenter.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.JUMPTODETAIL, null));
                        if (PdMainImageViewPage.this.mainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                            PdMainImageViewPage.this.mainImagePresenter.toDetailPage.setValue(Boolean.FALSE);
                        }
                    }
                }, 150L);
            }
        });
        this.pdMainImage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.7
            {
                PdMainImageViewPage.this = this;
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrollStateChanged(int i2) {
                super.onPageScrollStateChanged(i2);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int i2, float f2, int i3) {
                super.onPageScrolled(i2, f2, i3);
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int i2) {
                super.onPageSelected(i2);
                try {
                    if ((PdMainImageViewPage.this.topImageAllData == null || PdMainImageViewPage.this.topImageAllData.extMap == null || !PdMainImageViewPage.this.topImageAllData.extMap.mIsDefault) && PdMainImageViewPage.this.currIndex != i2) {
                        PdMainImageViewPage.this.currIndex = i2;
                        if (PdMainImageViewPage.this.mainImagePresenter != null) {
                            PdMainImageViewPage.this.mainImagePresenter.pageSelectedIndex.postValue(Integer.valueOf(i2));
                        }
                        if (PdMainImageViewPage.this.mainImagePresenter != null) {
                            PdMainImageViewPage.this.mainImagePresenter.pageSelectedIndexForMINI.postValue(Integer.valueOf(i2));
                        }
                        if (PdMainImageViewPage.this.imageTopTagView != null) {
                            PdMainImageViewPage.this.imageTopTagView.setImageIndex(i2 + 1);
                        }
                        if (PdMainImageViewPage.this.mAnchorRecyclerView != null) {
                            PdMainImageViewPage.this.mAnchorRecyclerView.c(i2);
                        }
                        if (i2 != 0) {
                            PdMainImageViewPage.this.getRecommendData();
                        }
                        if (PdMainImageViewPage.this.mainImagePresenter != null) {
                            PdMainImageViewPage pdMainImageViewPage = PdMainImageViewPage.this;
                            if (i2 == pdMainImageViewPage.mCommentZcxPosition && pdMainImageViewPage.mainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                                PdMainImageViewPage.this.loadCommmetData();
                            }
                        }
                        if (PdMainImageViewPage.this.mainImagePresenter != null) {
                            PdMainImageViewPage.this.mainImagePresenter.mtaClick("Productdetail_PhotoSlide", "", String.valueOf(i2 + 1), true);
                            if (!TextUtils.equals("1", PdMainImageViewPage.this.mainImagePresenter.mTempData.getString(PdImagePagePosViewManager.TEMP_BIG_IMAGE_MAT_CLICK))) {
                                PdMainImageViewPage.this.mainImagePresenter.mtaClick("Productdetail_PhotoSlide_New", PdMainImageViewPage.this.getMtaJsonParam(i2), true);
                            } else {
                                PdMainImageViewPage.this.mainImagePresenter.mTempData.put(PdImagePagePosViewManager.TEMP_BIG_IMAGE_MAT_CLICK, "0");
                            }
                            PdMainImageViewPage.this.mtaExploreEntrance(i2);
                        }
                    }
                } catch (Exception e2) {
                    ExceptionReporter.reportExceptionToBugly(e2);
                }
            }
        });
    }

    private boolean isNeedExploreEntrance(WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdDrugBizData pdDrugBizData;
        PdDrugInfo pdDrugInfo;
        String str = wareBusinessMagicHeadPicInfoEntity.anchorType;
        if (!TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_YPSMS) || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdDrugBizData = wareBuinessUnitMainImageBizDataEntity.drugInstructionsBizData) == null || (pdDrugInfo = pdDrugBizData.drugInfo) == null) {
            return (TextUtils.equals(str, "video") || TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_HEADPIC) || TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_WAREIMAGE) || TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_WAREIMAGESEC)) ? false : true;
        }
        return pdDrugInfo.showDetail;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestory() {
        this.isDestroy = true;
        unRegisterBroadcastReceiver();
    }

    private void reportEntranceExpo(String str, WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2;
        WareBuinessUnitMainImageBizDataEntity.PdDrugBizData pdDrugBizData;
        PdDrugInfo pdDrugInfo;
        if ((wareBusinessMagicHeadPicInfoEntity != null && wareBusinessMagicHeadPicInfoEntity.iViewType > 0) || TextUtils.isEmpty(str) || this.mainImagePresenter == null) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        if (wareBusinessUnitMainImageEntity != null && wareBusinessUnitMainImageEntity.extMap != null) {
            jDJSONObject.put("main_type", (Object) ("" + this.topImageAllData.extMap.magicHeadPicType));
        } else {
            jDJSONObject.put("main_type", (Object) 0);
        }
        jDJSONObject.put("type", (Object) str);
        if (this.mainImagePresenter.mAskQuesInfos != null && TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ASK)) {
            jDJSONObject.put("QuesNum", (Object) Integer.valueOf(this.mainImagePresenter.mAskQuesInfos.size()));
        } else {
            jDJSONObject.put("QuesNum", (Object) "");
        }
        if (wareBusinessMagicHeadPicInfoEntity != null && TextUtils.equals(wareBusinessMagicHeadPicInfoEntity.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_YPSMS) && (wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdDrugBizData = wareBuinessUnitMainImageBizDataEntity2.drugInstructionsBizData) != null && (pdDrugInfo = pdDrugBizData.drugInfo) != null) {
            if (pdDrugInfo.tips != null) {
                jDJSONObject.put("LableNum", (Object) (wareBusinessMagicHeadPicInfoEntity.bizData.drugInstructionsBizData.drugInfo.tips.size() + ""));
            } else {
                jDJSONObject.put("LableNum", (Object) "0");
            }
            WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity3 = wareBusinessMagicHeadPicInfoEntity.bizData;
            if (wareBuinessUnitMainImageBizDataEntity3 != null && !TextUtils.isEmpty(wareBuinessUnitMainImageBizDataEntity3.drugInstructionsBizData.drugInfo.imageInCell)) {
                jDJSONObject.put("isPhoto", (Object) "1");
            } else {
                jDJSONObject.put("isPhoto", (Object) "0");
            }
        } else {
            jDJSONObject.put("LableNum", (Object) "");
            jDJSONObject.put("isPhoto", (Object) "0");
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.topImageAllData;
        if (wareBusinessUnitMainImageEntity2 != null) {
            jDJSONObject.put("touchstone_expids", (Object) wareBusinessUnitMainImageEntity2.magicHeadAbTouchStone);
        }
        WareBusinessMagicHeadPicInfoEntity topImageBanDanData = this.mainImagePresenter.getTopImageBanDanData();
        if (topImageBanDanData != null && (wareBuinessUnitMainImageBizDataEntity = topImageBanDanData.bizData) != null) {
            WareImageRecommendRankEntity wareImageRecommendRankEntity = wareBuinessUnitMainImageBizDataEntity.bangDanInfo;
            if (wareImageRecommendRankEntity != null) {
                jDJSONObject.put("rankid", (Object) wareImageRecommendRankEntity.rankId);
            }
        } else {
            jDJSONObject.put("rankid", (Object) "");
        }
        this.mainImagePresenter.mtaExposure("Productdetail_FunctionEntranceExpo", jDJSONObject.toJSONString());
    }

    public void setImageSize(boolean z, int i2) {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        int i3 = pdMainImagePresenter.appImageWidth;
        if (z) {
            pdMainImagePresenter.appImageHeight = (int) ((i3 * 4.0f) / 3.0f);
        } else {
            pdMainImagePresenter.appImageHeight = i3;
        }
        changeMainImageSize(this.isHasMergeImage && i2 == 0, z);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mAnchorRecyclerView.getLayoutParams();
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        if (wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && extMapEntity.mainPicV12) {
            layoutParams.setMargins(0, PDUtils.dip2px(this.mContext, 8.0f), 0, PDUtils.dip2px(this.mContext, 8.0f));
            layoutParams.height = PDUtils.dip2px(this.mContext, 32.0f);
        } else {
            layoutParams.setMargins(0, PDUtils.dip2px(this.mContext, 10.0f), 0, PDUtils.dip2px(this.mContext, 10.0f));
            layoutParams.height = PDUtils.dip2px(this.mContext, 28.0f);
        }
        this.mAnchorRecyclerView.setLayoutParams(layoutParams);
    }

    private void showAchorInfo() {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        List<WareBusinessMagicHeadPicInfoEntity> list;
        List<WareBusinessMagicHeadPicInfoEntity> list2;
        List<WareBusinessMagicAnchorEntity> list3 = this.topImageAllData.magicAnchor;
        if (list3 != null && !list3.isEmpty()) {
            PdMainImageAnchorView pdMainImageAnchorView = this.mAnchorRecyclerView;
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.topImageAllData;
            boolean z = this.mainImagePresenter.getMainImageParams().isDark;
            pdMainImageAnchorView.f5130h = wareBusinessUnitMainImageEntity2;
            List<WareBusinessMagicAnchorEntity> list4 = wareBusinessUnitMainImageEntity2.magicAnchor;
            Context context = pdMainImageAnchorView.f5131i;
            WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = wareBusinessUnitMainImageEntity2.extMap;
            PdMainImageAnchorView.BigImageAnchorAdapter bigImageAnchorAdapter = new PdMainImageAnchorView.BigImageAnchorAdapter(context, list4, extMapEntity != null && extMapEntity.mainPicV12);
            pdMainImageAnchorView.f5129g = bigImageAnchorAdapter;
            bigImageAnchorAdapter.f5134e = wareBusinessUnitMainImageEntity2;
            bigImageAnchorAdapter.a = z;
            PdMainImagePresenter pdMainImagePresenter = pdMainImageAnchorView.f5132j;
            if (pdMainImagePresenter != null) {
                bigImageAnchorAdapter.f5135f = pdMainImagePresenter;
            }
            pdMainImageAnchorView.setAdapter(bigImageAnchorAdapter);
            pdMainImageAnchorView.setLayoutManager(new LinearLayoutManager(pdMainImageAnchorView.getContext(), 0, false));
            pdMainImageAnchorView.setHasFixedSize(true);
            pdMainImageAnchorView.setItemAnimator(null);
            if (list4 != null && (wareBusinessUnitMainImageEntity = pdMainImageAnchorView.f5130h) != null && wareBusinessUnitMainImageEntity.extMap != null) {
                JDJSONArray jDJSONArray = new JDJSONArray();
                for (WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity : list4) {
                    if (wareBusinessMagicAnchorEntity != null) {
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("main_type", (Object) Integer.valueOf(pdMainImageAnchorView.f5130h.extMap.magicHeadPicType));
                        jDJSONObject.put("type", (Object) wareBusinessMagicAnchorEntity.type);
                        jDJSONObject.put("categoryId3", (Object) pdMainImageAnchorView.f5132j.getCategoryId(2));
                        if (TextUtils.equals(wareBusinessMagicAnchorEntity.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN) && (list2 = pdMainImageAnchorView.f5130h.magicHeadPicInfo) != null && list2.get(wareBusinessMagicAnchorEntity.index) != null && pdMainImageAnchorView.f5130h.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity.index).bizData != null && pdMainImageAnchorView.f5130h.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity.index).bizData.bangDanInfo != null) {
                            jDJSONObject.put("rankid", (Object) pdMainImageAnchorView.f5130h.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity.index).bizData.bangDanInfo.rankId);
                            jDJSONObject.put("rank_type", (Object) "");
                            jDJSONObject.put("clkSrv", (Object) "");
                        } else if (TextUtils.equals(wareBusinessMagicAnchorEntity.type, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_GOLD_RANK_BANG_DAN) && (list = pdMainImageAnchorView.f5130h.magicHeadPicInfo) != null && list.get(wareBusinessMagicAnchorEntity.index) != null && pdMainImageAnchorView.f5130h.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity.index).bizData != null && pdMainImageAnchorView.f5130h.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity.index).bizData.bangDanInfo != null) {
                            WareImageRecommendRankEntity wareImageRecommendRankEntity = pdMainImageAnchorView.f5130h.magicHeadPicInfo.get(wareBusinessMagicAnchorEntity.index).bizData.bangDanInfo;
                            jDJSONObject.put("rankid", (Object) wareImageRecommendRankEntity.rankId);
                            jDJSONObject.put("rank_type", (Object) wareImageRecommendRankEntity.rankType);
                            jDJSONObject.put("clkSrv", (Object) wareImageRecommendRankEntity.clkSrv);
                        } else {
                            jDJSONObject.put("rankid", (Object) "");
                            jDJSONObject.put("rank_type", (Object) "");
                            jDJSONObject.put("clkSrv", (Object) "");
                        }
                        jDJSONArray.add(jDJSONObject);
                    }
                }
                HashMap<String, String> a = PdMainImageAnchorView.a(pdMainImageAnchorView.f5130h.magicHeadAbTouchStone);
                AbBuriedExpLabelsEntity abBuriedExpLabelsEntity = pdMainImageAnchorView.f5130h.abBuriedExpLabels;
                if (abBuriedExpLabelsEntity != null) {
                    PdMainImageAnchorView.b(abBuriedExpLabelsEntity.recommendAbtest, a);
                }
                pdMainImageAnchorView.f5132j.mtaExposure("Productdetail_GuideAccessExpo", ABMtaUtils.joinJsonParamsWithAbTest(jDJSONArray.toJSONString(), a));
            }
            this.mAnchorRecyclerView.d(new PdMainImageAnchorView.a() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.5
                {
                    PdMainImageViewPage.this = this;
                }

                @Override // com.jd.lib.productdetail.mainimage.old.PdMainImageAnchorView.a
                public void onSelect(int i2, WareBusinessMagicAnchorEntity wareBusinessMagicAnchorEntity2) {
                    boolean z2;
                    if (wareBusinessMagicAnchorEntity2 != null) {
                        PdMainImageAnchorView pdMainImageAnchorView2 = PdMainImageViewPage.this.mAnchorRecyclerView;
                        int i3 = PdMainImageViewPage.this.currIndex;
                        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity3 = pdMainImageAnchorView2.f5130h;
                        if (wareBusinessUnitMainImageEntity3 != null && wareBusinessUnitMainImageEntity3.magicAnchor != null) {
                            for (int i4 = 0; i4 < pdMainImageAnchorView2.f5130h.magicAnchor.size(); i4++) {
                                if (TextUtils.equals(pdMainImageAnchorView2.f5130h.magicAnchor.get(i4).type, wareBusinessMagicAnchorEntity2.type) && pdMainImageAnchorView2.f5130h.magicAnchor.get(i4).pageIndex != null && pdMainImageAnchorView2.f5130h.magicAnchor.get(i4).pageIndex.size() > 0 && pdMainImageAnchorView2.f5130h.magicAnchor.get(i4).pageIndex.contains(Integer.valueOf(i3))) {
                                    z2 = true;
                                    break;
                                }
                            }
                        }
                        z2 = false;
                        if (z2) {
                            return;
                        }
                        PdMainImageViewPage.this.mAnchorRecyclerView.c(wareBusinessMagicAnchorEntity2.index);
                        PdMainImageViewPage pdMainImageViewPage = PdMainImageViewPage.this;
                        pdMainImageViewPage.changeMainImageSize(pdMainImageViewPage.isHasMergeImage && i2 == 0, pdMainImageViewPage.isLongImage);
                        PdMainImageViewPage.this.pdMainImage.setCurrentItem(wareBusinessMagicAnchorEntity2.index, false);
                    }
                }
            });
            this.mAnchorRecyclerView.setVisibility(0);
            this.mAnchorRecyclerView.c(0);
            return;
        }
        this.mAnchorRecyclerView.setVisibility(8);
    }

    public void explorePending(PDPendingExplore pDPendingExplore) {
        if (pDPendingExplore == null) {
            return;
        }
        pDPendingExplore.explore(this.mainImagePresenter);
    }

    public PdMainImagePresenter getMainImagePresenter() {
        return this.mainImagePresenter;
    }

    public ImageView getMiniView() {
        return this.imageTopTagView.getMiniView();
    }

    public WareBusinessMagicHeadPicInfoEntity getTopImageGiftData() {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = null;
        if (wareBusinessUnitMainImageEntity != null && (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) != null && !list.isEmpty()) {
            for (WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 : this.topImageAllData.magicHeadPicInfo) {
                if (wareBusinessMagicHeadPicInfoEntity2 != null && TextUtils.equals(wareBusinessMagicHeadPicInfoEntity2.anchorType, "gift")) {
                    wareBusinessMagicHeadPicInfoEntity = wareBusinessMagicHeadPicInfoEntity2;
                }
            }
        }
        return wareBusinessMagicHeadPicInfoEntity;
    }

    public boolean isGetCommentCount() {
        return isLongImageStructure();
    }

    public boolean isLongImageStructure() {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        return (wareBusinessUnitMainImageEntity == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || extMapEntity.magicHeadPicType != 2) ? false : true;
    }

    public boolean isShowComment(PdCommentInfo pdCommentInfo, int i2) {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdCommentBizData pdCommentBizData;
        if (pdCommentInfo == null || (wareBusinessUnitMainImageEntity = this.topImageAllData) == null || (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) == null || list.size() <= i2) {
            return true;
        }
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.topImageAllData.magicHeadPicInfo.get(i2);
        return !(wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdCommentBizData = wareBuinessUnitMainImageBizDataEntity.commentBizData) == null || !pdCommentBizData.commentPriorityFlag) || pdCommentInfo.isBuyerCommentShowSmallerThan(4);
    }

    public void loadCommmetData() {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if ((pdMainImagePresenter != null && pdMainImagePresenter.pdCommentInfo.getValue() != null) || (wareBusinessUnitMainImageEntity = this.topImageAllData) == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || extMapEntity.category == null) {
            return;
        }
        a aVar = new a("", this.mainImagePresenter);
        String str = this.topImageAllData.extMap.category.fstId + ";" + this.topImageAllData.extMap.category.sndId + ";" + this.topImageAllData.extMap.category.thdId;
        String str2 = TextUtils.equals("1", this.topImageAllData.extMap.isXnzt) ? "0" : "1";
        Object[] objArr = new Object[11];
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity2 = this.topImageAllData.extMap;
        objArr[0] = extMapEntity2.skuId;
        objArr[1] = str;
        objArr[2] = extMapEntity2.isPop ? "0" : "1";
        String str3 = extMapEntity2.isShadowSku;
        if (str3 == null) {
            str3 = "";
        }
        objArr[3] = str3;
        String str4 = extMapEntity2.venderId;
        if (str4 == null) {
            str4 = "";
        }
        objArr[4] = str4;
        objArr[5] = str2;
        objArr[6] = extMapEntity2.daojiaFlag ? "1" : "0";
        String str5 = extMapEntity2.storeId;
        if (str5 == null) {
            str5 = "";
        }
        objArr[7] = str5;
        objArr[8] = "";
        objArr[9] = "";
        objArr[10] = Integer.valueOf(isGetCommentCount() ? 3 : 0);
        aVar.setInputParams(objArr);
        if (getActivity(getContext()) != null) {
            getActivity(getContext()).addHttpGroupWithNPSSetting(aVar.request());
        }
    }

    public void mtaExploreEntrance(int i2) {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        List<WareBuinessUnitMainImageBizDataEntity.ImageComponentEntity> list2;
        if (this.isDestroy || (wareBusinessUnitMainImageEntity = this.topImageAllData) == null || (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) == null || list.size() <= i2 || i2 < 0 || (wareBusinessMagicHeadPicInfoEntity = this.topImageAllData.magicHeadPicInfo.get(i2)) == null) {
            return;
        }
        String str = wareBusinessMagicHeadPicInfoEntity.anchorType;
        if (isNeedExploreEntrance(wareBusinessMagicHeadPicInfoEntity)) {
            if (TextUtils.equals(str, "comment")) {
                MutableLiveData<PdCommentInfo> mutableLiveData = this.mainImagePresenter.pdCommentInfo;
                if (!isShowComment(mutableLiveData != null ? mutableLiveData.getValue() : null, i2)) {
                    str = WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ZHONGCAOXIU;
                }
            }
            if (!TextUtils.isEmpty(str)) {
                PdMainImageAdapter pdMainImageAdapter = this.pdMainImageAdapter;
                boolean z = false;
                if (pdMainImageAdapter != null && pdMainImageAdapter.l(str.hashCode())) {
                    List<WareBusinessMagicPicItems> list3 = wareBusinessMagicHeadPicInfoEntity.items;
                    if (list3 != null && list3.size() > 0) {
                        reportEntranceExpo(wareBusinessMagicHeadPicInfoEntity.items.get(0).anchorType, wareBusinessMagicHeadPicInfoEntity);
                    }
                    List<WareBusinessMagicPicItems> list4 = wareBusinessMagicHeadPicInfoEntity.items;
                    if (list4 != null && list4.size() > 1) {
                        reportEntranceExpo(wareBusinessMagicHeadPicInfoEntity.items.get(1).anchorType, wareBusinessMagicHeadPicInfoEntity);
                    }
                } else if (TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS)) {
                    WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData;
                    if (wareBuinessUnitMainImageBizDataEntity != null && (list2 = wareBuinessUnitMainImageBizDataEntity.imageComponent) != null && list2.size() > 2) {
                        z = true;
                    }
                    if (z) {
                        reportEntranceExpo(str, wareBusinessMagicHeadPicInfoEntity);
                    }
                } else {
                    reportEntranceExpo(str, wareBusinessMagicHeadPicInfoEntity);
                }
            }
        }
        if (this.mPendingInfo.containsKey(wareBusinessMagicHeadPicInfoEntity.mfStyleId)) {
            explorePending(this.mPendingInfo.get(wareBusinessMagicHeadPicInfoEntity.mfStyleId));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.windowAttachState.setValue(Boolean.TRUE);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.windowAttachState.setValue(Boolean.FALSE);
        }
    }

    public void onPendingMtaInfo(String str, String str2, HashMap<String, String> hashMap, String str3) {
        MutableLiveData<Integer> mutableLiveData;
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str)) {
            return;
        }
        PDPendingExplore pDPendingExplore = new PDPendingExplore(str, str2, hashMap);
        boolean z = false;
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter != null && (mutableLiveData = pdMainImagePresenter.pageSelectedIndex) != null && mutableLiveData.getValue() != null) {
            int intValue = this.mainImagePresenter.pageSelectedIndex.getValue().intValue();
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
            if (wareBusinessUnitMainImageEntity != null && (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) != null && list.size() > intValue && (wareBusinessMagicHeadPicInfoEntity = this.topImageAllData.magicHeadPicInfo.get(intValue)) != null && TextUtils.equals(wareBusinessMagicHeadPicInfoEntity.mfStyleId, str3)) {
                explorePending(pDPendingExplore);
                z = true;
            }
        }
        if (z) {
            return;
        }
        this.mPendingInfo.put(str3, pDPendingExplore);
    }

    public void reSetImageLayout() {
        final int i2 = this.currIndex;
        setImageSize(this.isLongImage, i2);
        PdMainImageAdapter pdMainImageAdapter = this.pdMainImageAdapter;
        if (pdMainImageAdapter != null) {
            pdMainImageAdapter.notifyDataSetChanged();
        }
        this.pdMainImage.postDelayed(new Runnable() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageViewPage.4
            {
                PdMainImageViewPage.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                PdMainImageViewPage.this.pdMainImage.setCurrentItem(0, false);
                PdMainImageViewPage.this.pdMainImage.setCurrentItem(i2, false);
            }
        }, 100L);
    }

    public void registerBroadcastReceiver() {
        PdMainImagePresenter pdMainImagePresenter = this.mainImagePresenter;
        if (pdMainImagePresenter == null || pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
            return;
        }
        this.localBroadcastManager = LocalBroadcastManager.getInstance(this.mContext);
        if (this.localReceiver == null) {
            this.localReceiver = new PdMainImageLocalReceiver();
        }
        IntentFilter intentFilter = new IntentFilter(INTENT_ACTION_PD_MAINIMAGE_SLIDEPIC);
        this.localBroadcastManager.unregisterReceiver(this.localReceiver);
        this.localBroadcastManager.registerReceiver(this.localReceiver, intentFilter);
    }

    public void requestLayoutByFrom() {
        PdImageFromType pdImageFromType = this.mainImagePresenter.imageFromType;
        PdImageFromType pdImageFromType2 = PdImageFromType.PRODUCTDETAIL;
        if (pdImageFromType != pdImageFromType2 && (this.mAnchorLayout.getLayoutParams() instanceof ConstraintLayout.LayoutParams)) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mAnchorLayout.getLayoutParams();
            layoutParams.topToBottom = R.id.lib_pd_mainimage_viewpager;
            layoutParams.bottomToBottom = -1;
            this.mAnchorLayout.setLayoutParams(layoutParams);
        }
        if (this.imageTopTagView.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.imageTopTagView.getLayoutParams();
            if (this.mainImagePresenter.imageFromType == pdImageFromType2) {
                layoutParams2.setMargins(0, 0, 0, PDUtils.dip2px(this.mContext, PdMainStaticData.HOLDER_SHADOW_HEIGHT));
            } else {
                layoutParams2.setMargins(0, 0, 0, 0);
            }
            this.imageTopTagView.setLayoutParams(layoutParams2);
        }
        PdImageFromType pdImageFromType3 = this.mainImagePresenter.imageFromType;
        if (pdImageFromType3 == pdImageFromType2) {
            this.pdMainImageWrap.setContentSlideSize(PDUtils.dip2px(this.mContext, 45.0f));
        } else if (pdImageFromType3 == PdImageFromType.PRODUCTDETAIL_MINI) {
            this.pdMainImageWrap.setContentSlideSize(PDUtils.dip2px(this.mContext, 15.0f));
        }
    }

    public void unRegisterBroadcastReceiver() {
        PdMainImageLocalReceiver pdMainImageLocalReceiver = this.localReceiver;
        if (pdMainImageLocalReceiver != null) {
            LocalBroadcastManager localBroadcastManager = this.localBroadcastManager;
            if (localBroadcastManager != null) {
                localBroadcastManager.unregisterReceiver(pdMainImageLocalReceiver);
            }
            this.localReceiver = null;
        }
    }

    public PdMainImageViewPage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAskShowPosition = -1;
        this.mRecommendRankPosition = -1;
        this.mYpsmsPosition = -1;
        this.mSuitPosition = -1;
        this.mRecommendPosition = -1;
        this.mCommentZcxPosition = -1;
        this.mGiftShowPosition = -1;
        this.isDestroy = false;
        this.mPendingInfo = new HashMap<>();
        initView(context);
    }
}
