package com.jd.lib.productdetail.mainimage.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.pgcarticle.PdPgcArticleEntity;
import com.jd.lib.productdetail.core.entitys.pgcarticle.PdPgcVideoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPaperBookEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTopMpWareInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.AutoScrollTagsView;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.presenter.PdImagePagePosViewManager;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.presenter.PdVideoContainer;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PdMainImageTopTagView extends RelativeLayout implements LifecycleObserver {
    private int currPosition;
    private PdTopImageDragLayout dragLayout;
    private SimpleDraweeView imageLable;
    private boolean isDestroy;
    private boolean isV12NewStyle;
    private LifecycleOwner lifecycleOwner;
    private RelativeLayout longImageTitleLayout;
    private PdMainLongImageProgressBar longImageTitleProgressBar;
    private PdAutoChangeTextSize longImageTitleText;
    private TextView mCommentNum;
    private Context mContext;
    private AutoScrollTagsView mScrollTagsView;
    private SimpleDraweeView mSkipImg;
    private FrameLayout mSkipLayout;
    private TextView mSkipText;
    private PdMainImagePresenter mainImagePresenter;
    private PdMainLongImageProgressBar mainImageProgressBar;
    private ImageView miniView;
    private TextView pageCurr;
    private TextView pageCurrNew;
    private View pageNumLayout;
    private View pageNumLayoutNew;
    private TextView pageTotal;
    private TextView pageTotalNew;
    private WareBusinessUnitMainImageEntity topImageAllData;
    private SimpleDraweeView tryReadIcon;

    public PdMainImageTopTagView(Context context) {
        super(context);
        this.isDestroy = false;
        this.isV12NewStyle = false;
        this.mContext = context;
        initView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean activityIsAlive(Context context) {
        return getActivity(context) != null && getActivity(context).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIconVissible(int i2, boolean z, String str) {
        boolean z2 = true;
        boolean z3 = TextUtils.equals(str, "video") && !z;
        if (!TextUtils.isEmpty(str)) {
            if (!TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_HEADPIC) && !TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_WAREIMAGE) && !TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_WAREIMAGESEC) && !TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS_VIDEO) && !TextUtils.equals(str, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_ATLAS)) {
                z2 = false;
            }
            checkIconVissible(this.tryReadIcon, (z2 || z3) ? -2 : 0);
        }
        if (TextUtils.equals(str, "video")) {
            checkIconVissible(this.imageLable, z3 ? i2 : Integer.MAX_VALUE);
        } else {
            checkIconVissible(this.imageLable, i2);
        }
        checkIconVissible(this.mCommentNum, i2);
        if (this.tryReadIcon.getVisibility() == 0) {
            checkIconVissible(this.mScrollTagsView, Integer.MAX_VALUE);
            checkIconVissible(this.mCommentNum, Integer.MAX_VALUE);
            return;
        }
        checkIconVissible(this.mScrollTagsView, i2);
        checkIconVissible(this.mCommentNum, i2);
    }

    private BaseActivity getActivity(Context context) {
        if (context instanceof BaseActivity) {
            return (BaseActivity) context;
        }
        return null;
    }

    private View getDecorView(Context context) {
        if (context instanceof BaseActivity) {
            return ((BaseActivity) context).getWindow().getDecorView();
        }
        return null;
    }

    private void initView() {
        LayoutInflater.from(this.mContext).inflate(R.layout.lib_pd_mainimage_view_mainimage_tag, (ViewGroup) this, true);
        this.dragLayout = (PdTopImageDragLayout) findViewById(R.id.lib_pd_floor_topimage_drag_layout);
        this.pageNumLayout = findViewById(R.id.lib_pd_mainimage_number_layout);
        this.pageNumLayoutNew = findViewById(R.id.lib_pd_mainimage_number_layout_new);
        this.pageTotal = (TextView) findViewById(R.id.lib_pd_mainimage_number_total);
        this.pageCurr = (TextView) findViewById(R.id.lib_pd_mainimage_number_current);
        this.pageTotalNew = (TextView) findViewById(R.id.lib_pd_mainimage_number_total_new);
        TextView textView = (TextView) findViewById(R.id.lib_pd_mainimage_number_current_new);
        this.pageCurrNew = textView;
        FontsUtil.changeTextFont(textView, 4099);
        FontsUtil.changeTextFont(this.pageTotalNew, 4099);
        this.longImageTitleLayout = (RelativeLayout) findViewById(R.id.lib_pd_mainimage_long_image_title_layout);
        this.longImageTitleProgressBar = (PdMainLongImageProgressBar) findViewById(R.id.lib_pd_mainimage_long_image_pager_progress);
        PdMainLongImageProgressBar pdMainLongImageProgressBar = (PdMainLongImageProgressBar) findViewById(R.id.lib_pd_mainimage_main_image_pager_progress);
        this.mainImageProgressBar = pdMainLongImageProgressBar;
        pdMainLongImageProgressBar.isLongImageBar(false);
        this.longImageTitleText = (PdAutoChangeTextSize) findViewById(R.id.lib_pd_mainimage_long_image_title);
        this.imageLable = (SimpleDraweeView) findViewById(R.id.lib_pd_mainimage_label);
        this.tryReadIcon = (SimpleDraweeView) findViewById(R.id.lib_pd_mainimage_try_read_icon);
        this.mSkipLayout = (FrameLayout) findViewById(R.id.lib_pd_skip_live_layout);
        this.mSkipImg = (SimpleDraweeView) findViewById(R.id.lib_pd_skip_live_img);
        TextView textView2 = (TextView) findViewById(R.id.lib_pd_skip_live_num);
        this.mSkipText = textView2;
        FontsUtil.changeTextFont(textView2, 4099);
        this.miniView = (ImageView) findViewById(R.id.lib_pd_big_image_mini_image);
        this.mScrollTagsView = (AutoScrollTagsView) findViewById(R.id.lib_pd_big_image_scrollTagsView);
        this.mCommentNum = (TextView) findViewById(R.id.lib_pd_big_image_comment_num);
    }

    private boolean isLastShow(int i2) {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        return (wareBusinessUnitMainImageEntity == null || (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || extMapEntity.mIsDefault || i2 == 0 || list.size() - 1 != i2) ? false : true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestory() {
        this.isDestroy = true;
        AutoScrollTagsView autoScrollTagsView = this.mScrollTagsView;
        if (autoScrollTagsView != null) {
            autoScrollTagsView.onDestroy();
            this.mScrollTagsView = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {
        AutoScrollTagsView autoScrollTagsView = this.mScrollTagsView;
        if (autoScrollTagsView != null) {
            autoScrollTagsView.pause();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        AutoScrollTagsView autoScrollTagsView = this.mScrollTagsView;
        if (autoScrollTagsView != null) {
            autoScrollTagsView.resume();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLongImageTitleVisiable(boolean z) {
        setShowMainImageProgressBar(z);
        if (this.longImageTitleLayout == null || this.longImageTitleProgressBar == null) {
            return;
        }
        if (z && isShowLongImageTitle()) {
            this.longImageTitleLayout.setVisibility(0);
            this.longImageTitleProgressBar.setVisibility(0);
            return;
        }
        this.longImageTitleLayout.setVisibility(8);
        this.longImageTitleProgressBar.setVisibility(8);
    }

    private void setPageNumLayoutVisiable(boolean z) {
        if (z) {
            if (this.isV12NewStyle) {
                this.pageNumLayout.setVisibility(8);
                this.pageNumLayoutNew.setVisibility(0);
                return;
            }
            this.pageNumLayout.setVisibility(0);
            this.pageNumLayoutNew.setVisibility(8);
            return;
        }
        this.pageNumLayout.setVisibility(8);
        this.pageNumLayoutNew.setVisibility(8);
    }

    private void showLongImageTitle(int i2) {
        if (isShowLongImageTitle()) {
            setLongImageTitleVisiable(true);
            Context applicationContext = this.mContext.getApplicationContext();
            WareBusinessTopMpWareInfo wareBusinessTopMpWareInfo = this.topImageAllData.markerCollect.mpWareInfo;
            SpannableString imageSpan = PDUtils.getImageSpan(applicationContext, wareBusinessTopMpWareInfo.imgToWareName, wareBusinessTopMpWareInfo.productTitle, false);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) imageSpan);
            if (!TextUtils.isEmpty(imageSpan)) {
                this.longImageTitleText.setText(spannableStringBuilder);
            } else {
                this.longImageTitleText.setText(this.topImageAllData.markerCollect.mpWareInfo.productTitle);
            }
            this.longImageTitleProgressBar.setProgressNum(i2, PDUtils.dip2px(getContext(), 80.0f));
            return;
        }
        setLongImageTitleVisiable(false);
    }

    private void showRightBottomLabel() {
        WareBusinessUnitMainImageEntity.MarkerCollectEntity markerCollectEntity;
        WareBusinessPaperBookEntity wareBusinessPaperBookEntity;
        WareBusinessUnitMainImageEntity.MarkerCollectEntity markerCollectEntity2;
        this.tryReadIcon.setVisibility(8);
        this.tryReadIcon.setTag(PdImagePagePosViewManager.TAG_NOT_SHOWED);
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        final boolean z = (wareBusinessUnitMainImageEntity == null || (markerCollectEntity2 = wareBusinessUnitMainImageEntity.markerCollect) == null || TextUtils.isEmpty(markerCollectEntity2.goGymVideo) || TextUtils.isEmpty(this.topImageAllData.markerCollect.gymVideoImg)) ? false : true;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.topImageAllData;
        boolean z2 = (wareBusinessUnitMainImageEntity2 == null || (markerCollectEntity = wareBusinessUnitMainImageEntity2.markerCollect) == null || (wareBusinessPaperBookEntity = markerCollectEntity.paperBook) == null || TextUtils.isEmpty(wareBusinessPaperBookEntity.canReadUrl) || TextUtils.isEmpty(this.topImageAllData.markerCollect.paperBook.readUrl)) ? false : true;
        if (z || z2) {
            this.tryReadIcon.setTag(PdImagePagePosViewManager.TAG_PIC_SHOWED);
            this.tryReadIcon.setVisibility(0);
            if (z) {
                this.mainImagePresenter.mtaExposure("Productdetail_ClassTrialExpo");
            }
            String str = z ? this.topImageAllData.markerCollect.gymVideoImg : this.topImageAllData.markerCollect.paperBook.canReadUrl;
            if (!z && this.topImageAllData.markerCollect.paperBook.type == 2) {
                this.mainImagePresenter.mtaExposure("Productdetail_UseExplainExpo");
            }
            JDImageUtils.displayImage(str, this.tryReadIcon, null, false, new JDImageLoadingListener() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageTopTagView.4
                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str2, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                    if (view != null) {
                        view.setTag(PdImagePagePosViewManager.TAG_NOT_SHOWED);
                        view.setVisibility(8);
                    }
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str2, View view) {
                }
            }, null);
            this.tryReadIcon.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageTopTagView.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (PDUtils.repeatClick()) {
                        PdMainImageTopTagView pdMainImageTopTagView = PdMainImageTopTagView.this;
                        if (pdMainImageTopTagView.activityIsAlive(pdMainImageTopTagView.mContext)) {
                            if (z) {
                                if (PdMainImageTopTagView.this.topImageAllData == null || TextUtils.isEmpty(PdMainImageTopTagView.this.topImageAllData.markerCollect.goGymVideo)) {
                                    return;
                                }
                                OpenAppUtils.openAppForInner(view.getContext(), PdMainImageTopTagView.this.topImageAllData.markerCollect.goGymVideo);
                                PdMainImageTopTagView.this.mainImagePresenter.mtaClick("Productdetail_ClassTrial");
                                return;
                            }
                            if (PdMainImageTopTagView.this.topImageAllData != null && PdMainImageTopTagView.this.topImageAllData.markerCollect != null && PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook != null && !TextUtils.isEmpty(PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook.readUrl)) {
                                if (TextUtils.equals(PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook.jumpType, "2")) {
                                    OpenAppUtils.openAppForInner(view.getContext(), PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook.readUrl);
                                } else {
                                    PDBaseDeepLinkHelper.gotoMWithUrl(PdMainImageTopTagView.this.mContext, PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook.readUrl);
                                }
                            }
                            if (PdMainImageTopTagView.this.topImageAllData == null || PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook == null || PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook.type != 2) {
                                if (PdMainImageTopTagView.this.topImageAllData == null || PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook == null || PdMainImageTopTagView.this.topImageAllData.markerCollect.paperBook.type != 1) {
                                    return;
                                }
                                PdMainImageTopTagView.this.mainImagePresenter.mtaClick("Productdetail_BookPreRead", "", PdMainImageTopTagView.this.currPosition + "");
                                return;
                            }
                            PdMainImageTopTagView.this.mainImagePresenter.mtaClick("Productdetail_UseExplain");
                        }
                    }
                }
            });
        }
    }

    private void showRightTopLabel(boolean z) {
        WareBusinessUnitMainImageEntity.MarkerCollectEntity markerCollectEntity;
        this.imageLable.setVisibility(8);
        this.imageLable.setOnClickListener(null);
        this.imageLable.setClickable(false);
        this.imageLable.setTag(PdImagePagePosViewManager.TAG_NOT_SHOWED);
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        if (wareBusinessUnitMainImageEntity == null || (markerCollectEntity = wareBusinessUnitMainImageEntity.markerCollect) == null || markerCollectEntity.maxSales == null) {
            return;
        }
        this.imageLable.setTag(PdImagePagePosViewManager.TAG_FIRST_SHOWED);
        String str = this.topImageAllData.markerCollect.maxSales.head;
        if (!TextUtils.isEmpty(str)) {
            this.imageLable.setVisibility(0);
            JDImageUtils.displayImage(str, (ImageView) this.imageLable, (JDDisplayImageOptions) null, false);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.imageLable.getLayoutParams();
        if (!this.topImageAllData.extMap.mainPicV12 && !z) {
            layoutParams.setMargins(0, PDUtils.dip2px(this.mContext, 50.0f), 0, 0);
        } else {
            layoutParams.setMargins(0, PDUtils.dip2px(this.mContext, 50.0f) + UnStatusBarTintUtil.getStatusBarHeight((Activity) getActivity(this.mContext)), 0, 0);
        }
        this.imageLable.setLayoutParams(layoutParams);
    }

    private void showSkipLiveLayout() {
        WareBusinessUnitMainImageEntity.MarkerCollectEntity markerCollectEntity;
        PdPgcArticleEntity pdPgcArticleEntity;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        if (wareBusinessUnitMainImageEntity == null || (markerCollectEntity = wareBusinessUnitMainImageEntity.markerCollect) == null || (pdPgcArticleEntity = markerCollectEntity.pgc3cVideo) == null) {
            return;
        }
        ArrayList<PdPgcVideoEntity> arrayList = pdPgcArticleEntity.videoList;
        if (arrayList != null && !arrayList.isEmpty() && this.topImageAllData.markerCollect.pgc3cVideo.videoList.get(0) != null) {
            this.mSkipLayout.setVisibility(0);
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            createSimple.displayer(new JDRoundedBitmapDisplayer(PDUtils.dip2px(8.0f)));
            createSimple.setPlaceholder(19);
            JDImageUtils.displayImage(this.topImageAllData.markerCollect.pgc3cVideo.videoList.get(0).image, this.mSkipImg, createSimple);
            int i2 = this.topImageAllData.markerCollect.pgc3cVideo.totalNum;
            if (i2 > 0) {
                this.mSkipText.setText(String.valueOf(i2));
                this.mSkipText.setVisibility(0);
            } else {
                this.mSkipText.setVisibility(8);
            }
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.topImageAllData;
            if (wareBusinessUnitMainImageEntity2 == null || wareBusinessUnitMainImageEntity2.markerCollect.pgc3cVideo == null) {
                return;
            }
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put("id", (Object) this.topImageAllData.markerCollect.pgc3cVideo.materialId);
            this.mainImagePresenter.mtaExposure("Productdetail_MasterVideoExpo", jDJSONObject.toJSONString());
            this.mSkipLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageTopTagView.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (PdMainImageTopTagView.this.topImageAllData != null && PdMainImageTopTagView.this.topImageAllData.markerCollect.pgc3cVideo != null) {
                        JDJSONObject jDJSONObject2 = new JDJSONObject();
                        jDJSONObject2.put("id", (Object) PdMainImageTopTagView.this.topImageAllData.markerCollect.pgc3cVideo.materialId);
                        PdMainImageTopTagView.this.mainImagePresenter.mtaClick("Productdetail_MasterVideo", jDJSONObject2.toJSONString());
                    }
                    OpenAppUtils.openAppForInner(PdMainImageTopTagView.this.mContext, PdMainImageTopTagView.this.topImageAllData.markerCollect.pgc3cVideo.videoList.get(0).url);
                }
            });
            return;
        }
        this.mSkipLayout.setVisibility(8);
        this.mSkipLayout.setOnClickListener(null);
    }

    public ImageView getMiniView() {
        return this.miniView;
    }

    public boolean isShowLongImageTitle() {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessUnitMainImageEntity.MarkerCollectEntity markerCollectEntity;
        int i2;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        return (wareBusinessUnitMainImageEntity == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || (markerCollectEntity = wareBusinessUnitMainImageEntity.markerCollect) == null || markerCollectEntity.mpWareInfo == null || ((i2 = extMapEntity.magicHeadPicType) != 1 && i2 != 2)) ? false : true;
    }

    public void isShowNumLayout(boolean z) {
        if (this.pageNumLayout == null || this.pageNumLayoutNew == null) {
            return;
        }
        setPageNumLayoutVisiable(z);
    }

    public void setImageIndex(int i2) {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        TextView textView;
        int i3 = i2 - 1;
        this.currPosition = i3;
        if (!isShowLongImageTitle()) {
            setPageNumLayoutVisiable(true);
            this.pageTotal.setText(String.valueOf(this.topImageAllData.magicHeadPicInfo.size()));
            this.pageTotalNew.setText(String.valueOf(this.topImageAllData.magicHeadPicInfo.size()));
            this.pageCurr.setText(String.valueOf(i2));
            this.pageCurrNew.setText(String.valueOf(i2));
        } else {
            setPageNumLayoutVisiable(false);
        }
        PdMainLongImageProgressBar pdMainLongImageProgressBar = this.longImageTitleProgressBar;
        if (pdMainLongImageProgressBar != null) {
            pdMainLongImageProgressBar.onSelectIndex(i3);
        }
        PdMainLongImageProgressBar pdMainLongImageProgressBar2 = this.mainImageProgressBar;
        if (pdMainLongImageProgressBar2 != null) {
            pdMainLongImageProgressBar2.onSelectIndex(i3);
        }
        if (isLastShow(i3) && (textView = this.mCommentNum) != null && (textView.getTag() instanceof String) && TextUtils.equals(PdImagePagePosViewManager.TAG_LAST_SHOWED, (String) this.mCommentNum.getTag())) {
            this.mainImagePresenter.mtaExposure("Productdetail_CommentLabelExpo");
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.topImageAllData;
        if (wareBusinessUnitMainImageEntity == null || (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) == null) {
            return;
        }
        int size = list.size();
        int i4 = this.currPosition;
        if (size > i4) {
            checkIconVissible(i4, false, this.topImageAllData.magicHeadPicInfo.get(i4).anchorType);
        }
    }

    public void setMainImagePresenter(PdMainImagePresenter pdMainImagePresenter) {
        PdVideoContainer pdVideoContainer;
        this.mainImagePresenter = pdMainImagePresenter;
        if (this.lifecycleOwner == null) {
            LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(getDecorView(getContext()));
            this.lifecycleOwner = lifecycleOwner;
            if (lifecycleOwner != null) {
                lifecycleOwner.getLifecycle().addObserver(this);
                if (pdMainImagePresenter == null || (pdVideoContainer = pdMainImagePresenter.pdVideoContainer) == null) {
                    return;
                }
                pdVideoContainer.videoStatus.observe(this.lifecycleOwner, new Observer<PdVideoContainer.VideoStatus>() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageTopTagView.1
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(PdVideoContainer.VideoStatus videoStatus) {
                        String str = (PdMainImageTopTagView.this.topImageAllData == null || PdMainImageTopTagView.this.topImageAllData.magicHeadPicInfo == null || PdMainImageTopTagView.this.topImageAllData.magicHeadPicInfo.size() <= 0) ? "" : PdMainImageTopTagView.this.topImageAllData.magicHeadPicInfo.get(PdMainImageTopTagView.this.currPosition).anchorType;
                        if (TextUtils.isEmpty(str)) {
                            return;
                        }
                        if (videoStatus == PdVideoContainer.VideoStatus.OVER) {
                            PdMainImageTopTagView.this.setLongImageTitleVisiable(true);
                            PdMainImageTopTagView pdMainImageTopTagView = PdMainImageTopTagView.this;
                            pdMainImageTopTagView.checkIconVissible(pdMainImageTopTagView.currPosition, false, str);
                        } else if (videoStatus == PdVideoContainer.VideoStatus.PAUSE) {
                            if (TextUtils.equals(str, "video")) {
                                PdMainImageTopTagView.this.setLongImageTitleVisiable(false);
                            } else {
                                PdMainImageTopTagView.this.setLongImageTitleVisiable(true);
                            }
                            PdMainImageTopTagView pdMainImageTopTagView2 = PdMainImageTopTagView.this;
                            pdMainImageTopTagView2.checkIconVissible(pdMainImageTopTagView2.currPosition, false, str);
                        } else if (videoStatus == PdVideoContainer.VideoStatus.PLAY) {
                            PdMainImageTopTagView.this.setLongImageTitleVisiable(false);
                            PdMainImageTopTagView pdMainImageTopTagView3 = PdMainImageTopTagView.this;
                            pdMainImageTopTagView3.checkIconVissible(pdMainImageTopTagView3.currPosition, true, str);
                        }
                    }
                });
                pdMainImagePresenter.mTopCoverViewId.observe(this.lifecycleOwner, new Observer<Integer>() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageTopTagView.2
                    @Override // androidx.lifecycle.Observer
                    public void onChanged(Integer num) {
                        if (PdMainImageTopTagView.this.dragLayout != null) {
                            PdMainImageTopTagView.this.dragLayout.setmTopCoverViewId(num.intValue());
                        }
                    }
                });
            }
        }
    }

    public void setShowMainImageProgressBar(boolean z) {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        List<WareBusinessMagicHeadPicInfoEntity> list;
        if (z && (wareBusinessUnitMainImageEntity = this.topImageAllData) != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && extMapEntity.mainPicSlide && (list = wareBusinessUnitMainImageEntity.magicHeadPicInfo) != null && list.size() >= 5) {
            this.mainImageProgressBar.setVisibility(0);
        } else {
            this.mainImageProgressBar.setVisibility(8);
        }
    }

    public void setTopImageAllData(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        this.topImageAllData = wareBusinessUnitMainImageEntity;
        if (wareBusinessUnitMainImageEntity == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || extMapEntity.mIsDefault) {
            return;
        }
        this.isV12NewStyle = extMapEntity.mainPicV12;
        int i2 = extMapEntity.magicHeadPicType;
        boolean z = true;
        if (i2 != 1 && i2 != 2) {
            z = false;
        }
        showRightTopLabel(z);
        showRightBottomLabel();
        checkIconVissible(0, false, null);
        showSkipLiveLayout();
        showLongImageTitle(wareBusinessUnitMainImageEntity.magicHeadPicInfo.size());
        if (wareBusinessUnitMainImageEntity.magicHeadPicInfo.size() >= 5 && wareBusinessUnitMainImageEntity.extMap.mainPicSlide) {
            this.mainImageProgressBar.setProgressNum(wareBusinessUnitMainImageEntity.magicHeadPicInfo.size(), PDUtils.dip2px(getContext(), 224.0f));
            this.mainImageProgressBar.setVisibility(0);
            return;
        }
        this.mainImageProgressBar.setVisibility(8);
    }

    public void showCommentView(PdCommentInfo pdCommentInfo) {
        TextView textView = this.mCommentNum;
        if (textView == null || this.mScrollTagsView == null || pdCommentInfo == null) {
            return;
        }
        String str = pdCommentInfo.allCntStr;
        String str2 = pdCommentInfo.mainPicName;
        textView.setVisibility(8);
        this.mCommentNum.setTag(PdImagePagePosViewManager.TAG_NOT_SHOWED);
        this.mScrollTagsView.setVisibility(8);
        this.mScrollTagsView.setTag(PdImagePagePosViewManager.TAG_NOT_SHOWED);
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, "0") || TextUtils.isEmpty(str2) || str2.split(DYConstants.DY_REGEX_COMMA).length <= 1) {
            return;
        }
        this.mCommentNum.setTag(PdImagePagePosViewManager.TAG_LAST_SHOWED);
        this.mCommentNum.setText(this.mContext.getString(R.string.lib_pd_image_comment_count_txt, str));
        this.mCommentNum.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.view.PdMainImageTopTagView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PdMainImageTopTagView.this.mainImagePresenter.mtaClick("Productdetail_CommentLabel");
                Bundle bundle = new Bundle();
                bundle.putString("key", "2");
                PdMainImageTopTagView.this.mainImagePresenter.viewCallBackMutableLiveData.setValue(new PdMImageEventEntity(PdImageEventCode.OPEN_COMMENTPAGE, bundle));
            }
        });
        this.mScrollTagsView.setTag(PdImagePagePosViewManager.TAG_LAST_SHOWED);
        this.mScrollTagsView.setData(str2.split(DYConstants.DY_REGEX_COMMA));
    }

    public PdMainImageTopTagView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isDestroy = false;
        this.isV12NewStyle = false;
        this.mContext = context;
        initView();
    }

    private void checkIconVissible(View view, int i2) {
        if (view != null) {
            int i3 = 8;
            if (i2 == Integer.MAX_VALUE) {
                view.setVisibility(8);
                return;
            }
            if (view.getTag() instanceof String) {
                String str = (String) view.getTag();
                if (TextUtils.equals(PdImagePagePosViewManager.TAG_ALL_SHOWED, str) || (!TextUtils.equals(PdImagePagePosViewManager.TAG_FIRST_SHOWED, str) ? !(!TextUtils.equals(PdImagePagePosViewManager.TAG_LAST_SHOWED, str) ? !TextUtils.equals(PdImagePagePosViewManager.TAG_SEND_SHOWED, str) ? !TextUtils.equals(PdImagePagePosViewManager.TAG_PIC_SHOWED, str) || i2 != -2 : i2 != 1 : !isLastShow(i2)) : i2 == 0)) {
                    i3 = 0;
                }
            }
            view.setVisibility(i3);
        }
    }
}
