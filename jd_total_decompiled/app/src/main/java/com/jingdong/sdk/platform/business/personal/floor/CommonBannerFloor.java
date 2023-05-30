package com.jingdong.sdk.platform.business.personal.floor;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.platform.business.personal.common.ImageTools;
import com.jingdong.sdk.platform.business.personal.common.MtaCommonBannerExpoRunnable;
import com.jingdong.sdk.platform.business.personal.common.PersonalCommonUtil;
import com.jingdong.sdk.platform.business.personal.constant.MtaConstants;
import com.jingdong.sdk.platform.business.personal.entity.CommonBannerEntity;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.mta.MtaParams;
import com.jingdong.sdk.platform.utils.PlatformEventUtils;
import com.jingdong.sdk.platform.widget.IconPageIndicator;
import com.jingdong.sdk.platform.widget.IconPagerAdapter;
import com.jingdong.sdk.platform.widget.RoundAngleFrameLayout;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class CommonBannerFloor extends AbstractPersonalFloor {
    private static final float PERSONAL_BANNER_RECT_RADIUS = 24.0f;
    private static final String TAG = "CommonBannerFloor";
    private BannerAdapter bannerAdapter;
    private MtaCommonBannerExpoRunnable bannerExpoRunnable;
    @BindView(R2.id.floor_indicator)
    IconPageIndicator floorIndicator;
    @BindView(R2.id.floor_viewpager)
    ViewPager floorViewpager;
    @BindView(R2.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R2.id.roundcontainer)
    RoundAngleFrameLayout roundAngleFrameLayout;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class BannerAdapter extends PagerAdapter implements IconPagerAdapter {
        private SparseArray<SimpleDraweeView> bannerViewSparseArray;
        private final List<CommonBannerEntity.Banner> banners;
        private final CommonBannerFloor commonBannerFloor;
        private final BaseActivity context;
        private final LayoutInflater layoutInflater;
        private final String manageKey;
        private String mtaParam;
        private final JDDisplayImageOptions options;

        private String composeEventId(boolean z, String str) {
            StringBuilder sb;
            String str2;
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            if (z) {
                sb = new StringBuilder();
                sb.append(str);
                str2 = MtaConstants.MTA_BANNER_EXPOSURE_SUFFIX;
            } else {
                sb = new StringBuilder();
                sb.append(str);
                str2 = MtaConstants.MTA_BANNER_CLICK_SUFFIX;
            }
            sb.append(str2);
            return sb.toString();
        }

        private String compostEventParam(String str) {
            if (TextUtils.isEmpty(str)) {
                return this.mtaParam;
            }
            return this.mtaParam + CartConstant.KEY_YB_INFO_LINK + str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doMta(boolean z, String str, String str2) {
            PlatformEventUtils.sendMtaEvent(this.manageKey, new MtaParams(composeEventId(z, str), compostEventParam(str2), null, "1"));
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.banners.size();
        }

        @Override // com.jingdong.sdk.platform.widget.IconPagerAdapter
        public int getIconResId(int i2) {
            return R.drawable.common_banner_floor_indicator;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i2) {
            SimpleDraweeView simpleDraweeView;
            SparseArray<SimpleDraweeView> sparseArray = this.bannerViewSparseArray;
            if (sparseArray == null || sparseArray.size() == 0) {
                simpleDraweeView = null;
            } else {
                simpleDraweeView = this.bannerViewSparseArray.get(i2);
                if (OKLog.D) {
                    OKLog.d(CommonBannerFloor.TAG, "getRecyclerView  form Cache " + System.identityHashCode(simpleDraweeView) + " position " + i2);
                }
            }
            if (simpleDraweeView == null) {
                simpleDraweeView = (SimpleDraweeView) this.layoutInflater.inflate(R.layout.floor_common_banner_item, viewGroup, false);
                simpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                SparseArray<SimpleDraweeView> sparseArray2 = this.bannerViewSparseArray;
                if (sparseArray2 != null) {
                    sparseArray2.put(i2, simpleDraweeView);
                }
                if (OKLog.D) {
                    OKLog.d(CommonBannerFloor.TAG, "putRecyclerView  to Cache " + System.identityHashCode(simpleDraweeView) + " position " + i2);
                }
            }
            final CommonBannerEntity.Banner banner = this.banners.get(i2);
            if (banner != null) {
                simpleDraweeView.setClickable(true);
                simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.sdk.platform.business.personal.floor.CommonBannerFloor.BannerAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        CommonBannerEntity.Banner banner2 = banner;
                        if (banner2 == null) {
                            return;
                        }
                        if (banner2.needLogin == 1) {
                            LoginUserHelper.getInstance().executeLoginRunnable(BannerAdapter.this.context, new Runnable() { // from class: com.jingdong.sdk.platform.business.personal.floor.CommonBannerFloor.BannerAdapter.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    BaseActivity baseActivity = BannerAdapter.this.context;
                                    CommonBannerEntity.Banner banner3 = banner;
                                    JumpHelper.jump(baseActivity, banner3.jumpUrl, banner3.jumpType);
                                }
                            }, "", false);
                        } else {
                            BaseActivity baseActivity = BannerAdapter.this.context;
                            CommonBannerEntity.Banner banner3 = banner;
                            JumpHelper.jump(baseActivity, banner3.jumpUrl, banner3.jumpType);
                        }
                        BannerAdapter bannerAdapter = BannerAdapter.this;
                        CommonBannerEntity.Banner banner4 = banner;
                        bannerAdapter.doMta(false, banner4.eventId, banner4.eventParam);
                    }
                });
                ImageTools.displayImage(banner.lableImage, simpleDraweeView, this.options);
                simpleDraweeView.setContentDescription(simpleDraweeView.getContext().getString(R.string.bp_personal_common_banner_content_description));
                simpleDraweeView.setTag(banner.lableImage);
                viewGroup.addView(simpleDraweeView, 0);
                this.commonBannerFloor.mtaBannerExpo(this.manageKey, composeEventId(true, banner.eventId), compostEventParam(banner.eventParam));
            }
            return simpleDraweeView;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void swapAdapter(List<CommonBannerEntity.Banner> list, String str) {
            this.mtaParam = str;
            if (list == null || list.isEmpty()) {
                return;
            }
            this.banners.clear();
            this.banners.addAll(list);
            notifyDataSetChanged();
        }

        private BannerAdapter(BaseActivity baseActivity, LayoutInflater layoutInflater, String str, CommonBannerFloor commonBannerFloor) {
            this.banners = new ArrayList();
            this.bannerViewSparseArray = new SparseArray<>();
            this.context = baseActivity;
            this.layoutInflater = layoutInflater;
            this.manageKey = str;
            this.commonBannerFloor = commonBannerFloor;
            this.options = new JDDisplayImageOptions().setPlaceholder(6);
        }
    }

    public CommonBannerFloor(Context context, BaseFloorData baseFloorData, String str, ViewGroup viewGroup) {
        super(context, baseFloorData, str, viewGroup);
    }

    private void bindBannerData(CommonBannerEntity commonBannerEntity, String str) {
        ArrayList<CommonBannerEntity.Banner> arrayList;
        if (commonBannerEntity != null && (arrayList = commonBannerEntity.banners) != null && !arrayList.isEmpty()) {
            setBannerAttrs(commonBannerEntity.commonInfo);
            ArrayList<CommonBannerEntity.Banner> arrayList2 = commonBannerEntity.banners;
            this.bannerAdapter.swapAdapter(arrayList2, str);
            if (arrayList2.size() > 1) {
                this.floorIndicator.setVisibility(0);
                this.floorIndicator.setViewPager(this.floorViewpager);
                this.floorIndicator.notifyDataSetChanged();
                return;
            }
            this.floorIndicator.setVisibility(4);
            return;
        }
        hideViewHolder();
    }

    private CommonBannerEntity parserData(Object obj) {
        if (obj instanceof String) {
            try {
                if (OKLog.D) {
                    OKLog.d(TAG, String.format("CommonBannerFloor parserData: %s", obj));
                }
                return (CommonBannerEntity) JDJSON.parseObject((String) obj, CommonBannerEntity.class);
            } catch (Exception e2) {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format("CommonBannerFloor parserData get error: %s  | data: %s", e2, obj));
                if (OKLog.D) {
                    OKLog.d(TAG, illegalArgumentException);
                }
                ExceptionReporter.reportExceptionToBugly(illegalArgumentException);
            }
        }
        return null;
    }

    private void setAttrs(int i2, int i3, int i4) {
        int showPxValue = PersonalCommonUtil.getShowPxValue(i2);
        int showPxValue2 = PersonalCommonUtil.getShowPxValue(i3);
        if (this.rootLayout.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.rootLayout.getLayoutParams();
            marginLayoutParams.setMargins(showPxValue, 0, showPxValue2, 0);
            if (i4 > 0) {
                marginLayoutParams.height = PersonalCommonUtil.getShowPxValue(i4);
            }
        }
    }

    private void setBannerAttrs(CommonBannerEntity.CommonInfo commonInfo) {
        int i2;
        int i3;
        if (commonInfo == null || this.roundAngleFrameLayout == null || this.rootLayout == null) {
            return;
        }
        int i4 = commonInfo.height;
        try {
            i2 = Integer.parseInt(commonInfo.angle);
        } catch (Exception unused) {
            if (Log.D) {
                Log.d(TAG, "parse angel Exception");
            }
            i2 = 0;
        }
        try {
            i3 = Integer.parseInt(commonInfo.margin);
        } catch (Exception unused2) {
            if (Log.D) {
                Log.d(TAG, "parse margin Exception");
            }
            i3 = 0;
        }
        this.rootLayout.setBackgroundColor(0);
        this.roundAngleFrameLayout.setRadius(i2);
        setAttrs(i3, i3, i4);
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.business.personal.floor.AbstractPersonalFloor
    public ViewGroup createRootView(ViewGroup viewGroup) {
        return inflate(R.layout.floor_common_banner, viewGroup);
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class<BaseViewModel> getViewModelClass() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void initView() {
        BannerAdapter bannerAdapter = new BannerAdapter(this.baseActivity, getLayoutInflater(), getBaseData().mManageKey, this);
        this.bannerAdapter = bannerAdapter;
        this.floorViewpager.setAdapter(bannerAdapter);
    }

    public void mtaBannerExpo(String str, String str2, String str3) {
        if (this.bannerExpoRunnable == null) {
            this.bannerExpoRunnable = new MtaCommonBannerExpoRunnable(str, str2, str3);
        }
        runOnAttachToWindow(this.bannerExpoRunnable);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onCreatedView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onDestroyView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseTemplateEntity baseTemplateEntity) {
        CommonBannerEntity parserData = parserData(baseTemplateEntity.mData);
        if (parserData == null) {
            hideViewHolder();
        } else {
            bindBannerData(parserData, baseTemplateEntity.getMtaParam());
        }
    }
}
