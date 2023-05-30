package com.jingdong.app.mall.bundle.dolphinlib.floor.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.jd.lib.babel.ifloor.entity.BabelScope;
import com.jd.lib.babel.ifloor.ui.IFloorView;
import com.jd.lib.babel.ifloor.utils.ColorUtil;
import com.jd.lib.babel.ifloor.utils.CommonServiceUtil;
import com.jd.lib.babel.servicekit.imagekit.ImageWraper;
import com.jd.lib.babel.servicekit.model.BabelJumpEntity;
import com.jd.lib.babel.servicekit.model.MtaData;
import com.jd.lib.babel.servicekit.util.DPIUtil;
import com.jingdong.app.mall.bundle.dolphinlib.R;
import com.jingdong.app.mall.bundle.dolphinlib.common.FloorData;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.BaseUtil;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.ImageUtil;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.LogUtil;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinAdvMtaEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinLiveStreamEntranceFloorConfig;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.DolphinLiveStreamEntranceFloorModel;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.LiveStreamEntity;
import com.jingdong.app.mall.bundle.dolphinlib.floor.entity.WareInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes19.dex */
public class DolphinLiveStreamEntranceFloor extends FrameLayout implements IFloorView<DolphinLiveStreamEntranceFloorModel> {
    public static final String TAG = "DolphinLiveStreamEntranceFloor";
    private FloorData<DolphinLiveStreamEntranceFloorModel> dataHolder;
    private boolean isInitCompleted;
    private ImageView mBgImage;
    private GradientDrawable mBrowserBgDrawable;
    private GradientDrawable mContainerDrawable;
    private View mContentContainer;
    private ImageWraper mGifPraise;
    private ImageWraper mImageAuthorName;
    private ImageWraper mImageBrowser;
    private ImageWraper mImageLiveStream;
    private ImageWraper mImagePraise;
    private ViewGroup mPraiseContainer;
    private ImageWraper mSkuImageLeft;
    private ImageWraper mSkuImageRight;
    private TextView mTextAuthorName;
    private TextView mTextAuthorTitle;
    private TextView mTextBrowser;

    public DolphinLiveStreamEntranceFloor(Context context) {
        super(context);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        setLayoutParams(layoutParams == null ? new ViewGroup.MarginLayoutParams(-1, -2) : layoutParams);
        setBackgroundColor(0);
    }

    private void onBindView(FloorData<DolphinLiveStreamEntranceFloorModel> floorData) {
        if (floorData == null) {
            return;
        }
        onBindView(floorData.scope, floorData.data, floorData.position);
    }

    private void onInflateCompleted() {
        this.mContentContainer = findViewById(R.id.container);
        this.mContainerDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.dolphin_topic_billboard_bg);
        this.mImageLiveStream = (ImageWraper) findViewById(R.id.imageLiveStream);
        this.mBrowserBgDrawable = (GradientDrawable) getResources().getDrawable(R.drawable.dolphin_live_stream_page_view_count_bg);
        this.mImageBrowser = (ImageWraper) findViewById(R.id.imageBrowser);
        this.mTextBrowser = (TextView) findViewById(R.id.textBrowser);
        this.mPraiseContainer = (ViewGroup) findViewById(R.id.containerPraise);
        this.mImagePraise = (ImageWraper) findViewById(R.id.imagePraise);
        this.mGifPraise = (ImageWraper) findViewById(R.id.gifPraise);
        this.mTextAuthorTitle = (TextView) findViewById(R.id.textAuthorTitle);
        this.mImageAuthorName = (ImageWraper) findViewById(R.id.imageAuthorName);
        this.mTextAuthorName = (TextView) findViewById(R.id.textAuthorName);
        this.mSkuImageLeft = (ImageWraper) findViewById(R.id.imageLeft);
        this.mSkuImageRight = (ImageWraper) findViewById(R.id.imageRight);
        if (this.isInitCompleted) {
            return;
        }
        this.isInitCompleted = true;
        onBindView(this.dataHolder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSendClickMta(BabelScope babelScope, DolphinLiveStreamEntranceFloorModel dolphinLiveStreamEntranceFloorModel, String str, String str2, String str3) {
        DolphinAdvMtaEntity dolphinAdvMtaEntity = new DolphinAdvMtaEntity();
        dolphinAdvMtaEntity.aid = babelScope.getPageName();
        dolphinAdvMtaEntity.fno = dolphinLiveStreamEntranceFloorModel.fno;
        dolphinAdvMtaEntity.mid = dolphinLiveStreamEntranceFloorModel.mid;
        dolphinAdvMtaEntity.eventIdSuffix = str2;
        dolphinAdvMtaEntity.agid = dolphinLiveStreamEntranceFloorModel.liveStreamData.groupId;
        if (!TextUtils.isEmpty(str3)) {
            dolphinAdvMtaEntity.sku = str3;
            CommonServiceUtil.onClickMta(getContext(), MtaData.Builder.from(str + str2, dolphinAdvMtaEntity.getEventParam()).page(babelScope.getPageName(), babelScope.getPageParam()).build());
            return;
        }
        CommonServiceUtil.onClickMta(getContext(), MtaData.Builder.from(str + str2, dolphinAdvMtaEntity.makeAvdJson()).page(babelScope.getPageName(), babelScope.getPageParam()).build());
    }

    private void onSendExpoMta(BabelScope babelScope, DolphinLiveStreamEntranceFloorModel dolphinLiveStreamEntranceFloorModel, String str, String str2, String str3) {
        DolphinAdvMtaEntity dolphinAdvMtaEntity = new DolphinAdvMtaEntity();
        dolphinAdvMtaEntity.aid = babelScope.getPageName();
        dolphinAdvMtaEntity.fno = dolphinLiveStreamEntranceFloorModel.fno;
        dolphinAdvMtaEntity.mid = dolphinLiveStreamEntranceFloorModel.mid;
        dolphinAdvMtaEntity.eventIdSuffix = str2;
        dolphinAdvMtaEntity.agid = dolphinLiveStreamEntranceFloorModel.liveStreamData.groupId;
        if (!TextUtils.isEmpty(str3)) {
            dolphinAdvMtaEntity.sku = str3;
            CommonServiceUtil.sendExposureData(babelScope, str + str2, dolphinAdvMtaEntity.getEventParam());
            return;
        }
        CommonServiceUtil.sendExposureData(babelScope, str + str2, dolphinAdvMtaEntity.makeAdvExpoJson());
    }

    private boolean onUpdateAuthorNameView(String str, String str2, DolphinLiveStreamEntranceFloorConfig dolphinLiveStreamEntranceFloorConfig) {
        if (BaseUtil.checkNull(dolphinLiveStreamEntranceFloorConfig, this.mImageAuthorName, this.mTextAuthorName) || TextUtils.isEmpty(str2)) {
            return false;
        }
        ImageUtil.loadImage(str2, this.mImageAuthorName);
        this.mTextAuthorName.setTextColor(ColorUtil.parseColor(dolphinLiveStreamEntranceFloorConfig.authorNameColor, -7829368));
        this.mTextAuthorName.setText(str);
        return true;
    }

    private boolean onUpdateAuthorTitleView(String str, DolphinLiveStreamEntranceFloorConfig dolphinLiveStreamEntranceFloorConfig) {
        if (BaseUtil.checkNull(dolphinLiveStreamEntranceFloorConfig, this.mTextAuthorTitle)) {
            return false;
        }
        this.mTextAuthorTitle.setTextColor(ColorUtil.parseColor(dolphinLiveStreamEntranceFloorConfig.authorTitleColor, -16777216));
        this.mTextAuthorTitle.setText(str);
        return true;
    }

    private boolean onUpdateBrowserView(String str, DolphinLiveStreamEntranceFloorConfig dolphinLiveStreamEntranceFloorConfig) {
        if (BaseUtil.checkNull(dolphinLiveStreamEntranceFloorConfig, this.mBrowserBgDrawable, this.mTextBrowser, this.mImageBrowser)) {
            return false;
        }
        if (!TextUtils.isEmpty(dolphinLiveStreamEntranceFloorConfig.browserIcon)) {
            CommonServiceUtil.displayImage(dolphinLiveStreamEntranceFloorConfig.browserIcon, this.mImageBrowser);
        }
        int parseColor = ColorUtil.parseColor(dolphinLiveStreamEntranceFloorConfig.browserBgColor, 0);
        int parseColor2 = ColorUtil.parseColor(dolphinLiveStreamEntranceFloorConfig.browserTextColor, -1);
        this.mBrowserBgDrawable.setColor(parseColor);
        this.mTextBrowser.setBackground(this.mBrowserBgDrawable);
        this.mTextBrowser.setTextColor(parseColor2);
        this.mTextBrowser.setText(str);
        return true;
    }

    private boolean onUpdateContainerBackground(DolphinLiveStreamEntranceFloorConfig dolphinLiveStreamEntranceFloorConfig) {
        if (BaseUtil.checkNull(dolphinLiveStreamEntranceFloorConfig, this.mContainerDrawable)) {
            return false;
        }
        this.mContainerDrawable.setColor(!TextUtils.isEmpty(dolphinLiveStreamEntranceFloorConfig.bgCellColor) ? ColorUtil.parseColor(dolphinLiveStreamEntranceFloorConfig.bgCellColor, 0) : 0);
        this.mContainerDrawable.setCornerRadii(new float[]{DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.topLeftRadius), DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.topLeftRadius), DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.topRightRadius), DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.topRightRadius), DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.bottomLeftRadius), DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.bottomLeftRadius), DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.bottomRightRadius), DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.bottomRightRadius)});
        this.mContentContainer.setBackgroundDrawable(this.mContainerDrawable);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mContentContainer.getLayoutParams();
        if (marginLayoutParams == null) {
            marginLayoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
        }
        marginLayoutParams.leftMargin = DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.marginLeft);
        marginLayoutParams.topMargin = DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.marginTop);
        marginLayoutParams.rightMargin = DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.marginRight);
        marginLayoutParams.bottomMargin = DPIUtil.dip2px(dolphinLiveStreamEntranceFloorConfig.marginBottom);
        this.mContentContainer.setLayoutParams(marginLayoutParams);
        return true;
    }

    private boolean onUpdateFloorBackground(DolphinLiveStreamEntranceFloorConfig dolphinLiveStreamEntranceFloorConfig) {
        if (dolphinLiveStreamEntranceFloorConfig == null) {
            return false;
        }
        if (!TextUtils.isEmpty(dolphinLiveStreamEntranceFloorConfig.backgroundImage)) {
            return onUpdateFloorBgImage(dolphinLiveStreamEntranceFloorConfig.backgroundImage);
        }
        return onUpdateFloorBgColor(dolphinLiveStreamEntranceFloorConfig.backgroundColor);
    }

    private boolean onUpdateFloorBgColor(String str) {
        setBackgroundColor(TextUtils.isEmpty(str) ? 0 : ColorUtil.parseColor(str, 0));
        return true;
    }

    private boolean onUpdateFloorBgImage(String str) {
        if (this.mBgImage == null) {
            ImageView imageView = CommonServiceUtil.getImageView(getContext());
            this.mBgImage = imageView;
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.mBgImage, 0, new FrameLayout.LayoutParams(-1, -1));
        }
        CommonServiceUtil.displayImage(str, this.mBgImage);
        return true;
    }

    private boolean onUpdateImageLiveStream(final BabelScope babelScope, final DolphinLiveStreamEntranceFloorModel dolphinLiveStreamEntranceFloorModel, String str, final BabelJumpEntity babelJumpEntity) {
        if (BaseUtil.checkNull(this.mImageLiveStream) || TextUtils.isEmpty(str)) {
            return false;
        }
        CommonServiceUtil.displayImage(str, this.mImageLiveStream);
        this.mImageLiveStream.setOnClickListener(null);
        if (babelJumpEntity != null) {
            this.mImageLiveStream.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinLiveStreamEntranceFloor.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CommonServiceUtil.execJump(DolphinLiveStreamEntranceFloor.this.getContext(), babelJumpEntity);
                    DolphinLiveStreamEntranceFloor.this.onSendClickMta(babelScope, dolphinLiveStreamEntranceFloorModel, DolphinAdvMtaEntity.PREFIX_DEV_ADV, DolphinAdvMtaEntity.EVENT_ID_MZZBLC, null);
                }
            });
        }
        return true;
    }

    private boolean onUpdatePraiseView(DolphinLiveStreamEntranceFloorConfig dolphinLiveStreamEntranceFloorConfig) {
        if (BaseUtil.checkNull(dolphinLiveStreamEntranceFloorConfig, this.mPraiseContainer, this.mImagePraise, this.mGifPraise)) {
            return false;
        }
        this.mImagePraise.setOnClickListener(null);
        if (!TextUtils.isEmpty(dolphinLiveStreamEntranceFloorConfig.praiseIcon)) {
            CommonServiceUtil.displayImage(dolphinLiveStreamEntranceFloorConfig.praiseIcon, this.mImagePraise);
            this.mImagePraise.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinLiveStreamEntranceFloor.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DolphinLiveStreamEntranceFloor.this.mGifPraise == null || 4 != DolphinLiveStreamEntranceFloor.this.mGifPraise.getVisibility()) {
                        return;
                    }
                    DolphinLiveStreamEntranceFloor.this.mGifPraise.setVisibility(0);
                }
            });
        }
        if (!TextUtils.isEmpty(dolphinLiveStreamEntranceFloorConfig.praiseGif)) {
            CommonServiceUtil.displayImage(dolphinLiveStreamEntranceFloorConfig.praiseGif, this.mGifPraise);
        }
        return true;
    }

    private boolean onUpdateSkuListImage(final BabelScope babelScope, final DolphinLiveStreamEntranceFloorModel dolphinLiveStreamEntranceFloorModel, List<WareInfo> list) {
        final WareInfo wareInfo;
        int size = (list == null || list.isEmpty()) ? 0 : list.size();
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mSkuImageLeft);
        arrayList.add(this.mSkuImageRight);
        int size2 = arrayList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ImageWraper imageWraper = (ImageWraper) arrayList.get(i2);
            imageWraper.setVisibility(8);
            if (i2 < size && (wareInfo = list.get(i2)) != null && !TextUtils.isEmpty(wareInfo.pictureUrl)) {
                imageWraper.setOnClickListener(null);
                imageWraper.setVisibility(0);
                CommonServiceUtil.displayImage(wareInfo.pictureUrl, imageWraper);
                onSendExpoMta(babelScope, dolphinLiveStreamEntranceFloorModel, DolphinAdvMtaEntity.PREFIX_EXPO_SKU, DolphinAdvMtaEntity.EVENT_ID_MZZBLC, wareInfo.skuId);
                if (wareInfo.jump != null) {
                    imageWraper.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.bundle.dolphinlib.floor.view.DolphinLiveStreamEntranceFloor.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            CommonServiceUtil.execJump(DolphinLiveStreamEntranceFloor.this.getContext(), wareInfo.jump);
                            DolphinLiveStreamEntranceFloor.this.onSendClickMta(babelScope, dolphinLiveStreamEntranceFloorModel, DolphinAdvMtaEntity.PREFIX_DEV_SKU, DolphinAdvMtaEntity.EVENT_ID_MZZBLC, wareInfo.skuId);
                        }
                    });
                }
            }
        }
        return true;
    }

    @Override // com.jd.lib.babel.ifloor.ui.IView
    public void initView(String str) {
        LogUtil.d(TAG, "+++ init live stream entrance floor +++");
        LayoutInflater.from(getContext()).inflate(R.layout.dolphin_live_stream_entrance_floor, this);
        this.isInitCompleted = true;
        onInflateCompleted();
    }

    private void onBindView(BabelScope babelScope, DolphinLiveStreamEntranceFloorModel dolphinLiveStreamEntranceFloorModel, int i2) {
        LiveStreamEntity liveStreamEntity = dolphinLiveStreamEntranceFloorModel.liveStreamData;
        onSendExpoMta(babelScope, dolphinLiveStreamEntranceFloorModel, DolphinAdvMtaEntity.PREFIX_EXPO_ADV, DolphinAdvMtaEntity.EVENT_ID_MZZBLC, null);
        onUpdateAuthorNameView(liveStreamEntity.authorName, liveStreamEntity.authorPic, dolphinLiveStreamEntranceFloorModel.boardParams);
        onUpdateAuthorTitleView(liveStreamEntity.title, dolphinLiveStreamEntranceFloorModel.boardParams);
        onUpdatePraiseView(dolphinLiveStreamEntranceFloorModel.boardParams);
        onUpdateBrowserView(liveStreamEntity.desc, dolphinLiveStreamEntranceFloorModel.boardParams);
        onUpdateSkuListImage(babelScope, dolphinLiveStreamEntranceFloorModel, liveStreamEntity.wareInfoList);
        onUpdateImageLiveStream(babelScope, dolphinLiveStreamEntranceFloorModel, liveStreamEntity.pictureUrl, liveStreamEntity.jump);
        onUpdateContainerBackground(dolphinLiveStreamEntranceFloorModel.boardParams);
        onUpdateFloorBackground(dolphinLiveStreamEntranceFloorModel.boardParams);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jd.lib.babel.ifloor.ui.IView
    public final void update(BabelScope babelScope, DolphinLiveStreamEntranceFloorModel dolphinLiveStreamEntranceFloorModel, int i2) {
        LogUtil.d(TAG, "+++ update live stream entrance floor +++");
        if (this.isInitCompleted) {
            onBindView(babelScope, dolphinLiveStreamEntranceFloorModel, i2);
            return;
        }
        FloorData<DolphinLiveStreamEntranceFloorModel> floorData = new FloorData<>();
        this.dataHolder = floorData;
        floorData.position = i2;
        floorData.scope = babelScope;
        floorData.data = dolphinLiveStreamEntranceFloorModel;
    }

    private boolean onUpdateSkuListImage(String str, String str2, DolphinLiveStreamEntranceFloorConfig dolphinLiveStreamEntranceFloorConfig) {
        ImageWraper imageWraper = this.mSkuImageLeft;
        if (imageWraper != null) {
            CommonServiceUtil.displayImage(str, imageWraper);
        }
        ImageWraper imageWraper2 = this.mSkuImageRight;
        if (imageWraper2 != null) {
            CommonServiceUtil.displayImage(str2, imageWraper2);
            return true;
        }
        return true;
    }
}
