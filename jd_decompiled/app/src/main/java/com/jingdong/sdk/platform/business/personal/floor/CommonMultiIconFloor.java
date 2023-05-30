package com.jingdong.sdk.platform.business.personal.floor;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.text.OnTextScaleModeChangeListener;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.platform.business.personal.common.ImageTools;
import com.jingdong.sdk.platform.business.personal.common.MtaCommonMultiIconExpoRunnable;
import com.jingdong.sdk.platform.business.personal.entity.CommonMultiIconEntity;
import com.jingdong.sdk.platform.business.personal.floor.adapter.CommonMultiIconAdapter;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.widget.IconPageIndicator;
import com.jingdong.sdk.platform.widget.IconPagerAdapter;
import com.jingdong.sdk.platform.widget.RoundAngleFrameLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public class CommonMultiIconFloor extends AbstractPersonalFloor implements OnTextScaleModeChangeListener, DeepDarkChangeManager.OnUIModeChangeListener {
    private static final String BACKGROUND_TYPE_IMAGE = "0";
    private static final String TAG = "CommonMultiIconFloor";
    private MultiIconPagerAdapter adapter;
    private CommonMultiIconEntity.CommonInfo commonInfo;
    @BindView(R2.id.floor_background)
    SimpleDraweeView floorBackground;
    @BindView(R2.id.floor_container)
    RoundAngleFrameLayout floorContainer;
    private MtaCommonMultiIconExpoRunnable iconExpoRunnable;
    private int iconHeight;
    @BindView(R2.id.indicator)
    IconPageIndicator indicator;
    @BindView(R2.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class MultiIconPagerAdapter extends PagerAdapter implements IconPagerAdapter {
        private final BaseActivity baseActivity;
        private boolean commonChange;
        private CommonMultiIconEntity.CommonInfo commonInfo;
        private CommonMultiIconEntity.CommonInfo commonInfoCache;
        private final LayoutInflater layoutInflater;
        private final String manageKey;
        private boolean needClearCache;
        private SparseArray<CommonMultiIconAdapter> entranceAdapterSparseArray = new SparseArray<>();
        private SparseArray<RecyclerView> recyclerViewSparseArray = new SparseArray<>();
        private SparseArray<WeakReference<CommonMultiIconAdapter.MultiIconViewHolder>> viewHolderSparseArray = new SparseArray<>();
        private final List<CommonMultiIconEntity.IconElement> models = new ArrayList();
        private final List<List<CommonMultiIconEntity.IconElement>> iconPages = new ArrayList();

        public MultiIconPagerAdapter(BaseActivity baseActivity, LayoutInflater layoutInflater, String str) {
            this.baseActivity = baseActivity;
            this.layoutInflater = layoutInflater;
            this.manageKey = str;
        }

        private void splitList() {
            if (this.commonInfo == null) {
                return;
            }
            this.iconPages.clear();
            int size = this.models.size();
            CommonMultiIconEntity.CommonInfo commonInfo = this.commonInfo;
            int i2 = commonInfo.itemPerRow * commonInfo.rows;
            int i3 = 0;
            if (commonInfo.isPage != 0) {
                while (i3 < size) {
                    int i4 = i3 + i2;
                    this.iconPages.add(this.models.subList(i3, Math.min(size, i4)));
                    i3 = i4;
                }
            } else if (size > i2) {
                try {
                    List<CommonMultiIconEntity.IconElement> list = this.models;
                    list.subList(i2, list.size()).clear();
                } catch (Exception e2) {
                    if (OKLog.D) {
                        OKLog.d(CommonMultiIconFloor.TAG, String.format("icon sub list exception:%s", e2));
                    }
                }
                this.iconPages.add(this.models);
            } else {
                this.iconPages.add(this.models);
            }
        }

        public boolean canRefresh(List<CommonMultiIconEntity.IconElement> list) {
            CommonMultiIconEntity.IconElement.SubTitle subTitle;
            CommonMultiIconEntity.IconElement.SubTitle subTitle2;
            CommonMultiIconEntity.IconElement.Title title;
            boolean z = true;
            if (list != null && this.models.size() == list.size()) {
                int size = list.size();
                boolean z2 = true;
                for (int i2 = 0; i2 < size; i2++) {
                    CommonMultiIconEntity.IconElement iconElement = this.models.get(i2);
                    CommonMultiIconEntity.IconElement iconElement2 = list.get(i2);
                    if (iconElement != null && iconElement2 != null) {
                        CommonMultiIconEntity.IconElement.Title title2 = iconElement.title;
                        z2 = (title2 == null && iconElement2.title != null) || (title2 != null && iconElement2.title == null) || (!(title2 == null || (title = iconElement2.title) == null || (TextUtils.equals(title2.value, title.value) && TextUtils.equals(iconElement.title.color, iconElement2.title.color))) || (((subTitle = iconElement.subTitle) == null && iconElement2.subTitle != null) || ((subTitle != null && iconElement2.subTitle == null) || !((subTitle == null || (subTitle2 = iconElement2.subTitle) == null || (TextUtils.equals(subTitle.value, subTitle2.value) && TextUtils.equals(iconElement.subTitle.color, iconElement2.subTitle.color))) && TextUtils.equals(iconElement.contentType, iconElement2.contentType) && TextUtils.equals(iconElement.safeImage, iconElement2.safeImage) && TextUtils.equals(iconElement.iconContent, iconElement2.iconContent) && TextUtils.equals(iconElement.mUrl, iconElement2.mUrl) && TextUtils.equals(iconElement.lottieContent, iconElement2.lottieContent) && iconElement.playTimes == iconElement2.playTimes && iconElement.needLogin == iconElement2.needLogin && iconElement.jumpType == iconElement2.jumpType))));
                        if (OKLog.D) {
                            OKLog.d(CommonMultiIconFloor.TAG, "equal: " + z2);
                        }
                        if (z2) {
                            break;
                        }
                    }
                }
                z = z2;
            }
            if (OKLog.D) {
                OKLog.d(CommonMultiIconFloor.TAG, "result: " + z);
            }
            return z;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i2, Object obj) {
            if (obj instanceof View) {
                viewGroup.removeView((View) obj);
            }
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.iconPages.size();
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
            RecyclerView recyclerView;
            CommonMultiIconAdapter commonMultiIconAdapter;
            List<CommonMultiIconEntity.IconElement> list = this.iconPages.get(i2);
            SparseArray<RecyclerView> sparseArray = this.recyclerViewSparseArray;
            if (sparseArray == null || sparseArray.size() == 0) {
                recyclerView = null;
            } else {
                recyclerView = this.recyclerViewSparseArray.get(i2);
                if (OKLog.D) {
                    OKLog.d("DaCuFloor", "getRecyclerView  form Cache " + System.identityHashCode(recyclerView) + " position " + i2);
                }
            }
            if (recyclerView == null) {
                recyclerView = (RecyclerView) this.layoutInflater.inflate(R.layout.layout_common_multi_icon, viewGroup, false);
                SparseArray<RecyclerView> sparseArray2 = this.recyclerViewSparseArray;
                if (sparseArray2 != null) {
                    sparseArray2.put(i2, recyclerView);
                }
                if (OKLog.D) {
                    OKLog.d("DaCuFloor", "putRecyclerView  to Cache " + System.identityHashCode(recyclerView) + " position " + i2);
                }
            }
            SparseArray<CommonMultiIconAdapter> sparseArray3 = this.entranceAdapterSparseArray;
            if (sparseArray3 != null) {
                commonMultiIconAdapter = sparseArray3.get(i2);
                if (OKLog.D) {
                    OKLog.d("DaCuFloor", "getAdapter  form Cache" + commonMultiIconAdapter + "position " + i2);
                }
            } else {
                commonMultiIconAdapter = null;
            }
            if (commonMultiIconAdapter == null) {
                commonMultiIconAdapter = new CommonMultiIconAdapter(this.baseActivity, recyclerView, this.layoutInflater, this.manageKey, this.viewHolderSparseArray);
                SparseArray<CommonMultiIconAdapter> sparseArray4 = this.entranceAdapterSparseArray;
                if (sparseArray4 != null) {
                    sparseArray4.put(i2, commonMultiIconAdapter);
                }
                if (OKLog.D) {
                    OKLog.d("DaCuFloor", "putAdapter  to Cache" + commonMultiIconAdapter + "position " + i2);
                }
            }
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), this.commonInfo.itemPerRow));
            recyclerView.setItemAnimator(null);
            recyclerView.setAdapter(commonMultiIconAdapter);
            commonMultiIconAdapter.swapModels(list);
            viewGroup.addView(recyclerView, 0);
            return recyclerView;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void setCommonInfo(CommonMultiIconEntity.CommonInfo commonInfo) {
            this.commonInfo = commonInfo;
        }

        public void swapAdapter(List<CommonMultiIconEntity.IconElement> list, CommonMultiIconEntity.CommonInfo commonInfo) {
            boolean z = (list == null || list.isEmpty()) ? false : true;
            setCommonInfo(commonInfo);
            if (z && commonInfo != null && canRefresh(list)) {
                this.models.clear();
                this.models.addAll(list);
                splitList();
                this.needClearCache = true;
            } else {
                this.needClearCache = false;
            }
            CommonMultiIconEntity.CommonInfo commonInfo2 = this.commonInfoCache;
            this.commonChange = (commonInfo2 == null && commonInfo != null) || (commonInfo2 != null && commonInfo == null) || !(commonInfo2 == null || commonInfo == null || (commonInfo2.isPage == commonInfo.isPage && commonInfo2.rows == commonInfo.rows && commonInfo2.itemPerRow == commonInfo.itemPerRow));
            if (OKLog.D) {
                OKLog.i(CommonMultiIconFloor.TAG, String.format("needClearCache:%s | commonChanage:%s", Boolean.valueOf(this.needClearCache), Boolean.valueOf(this.commonChange)));
            }
            if (this.needClearCache || this.commonChange) {
                notifyDataSetChanged();
            }
            this.commonInfoCache = commonInfo;
        }
    }

    public CommonMultiIconFloor(Context context, BaseFloorData baseFloorData, String str, ViewGroup viewGroup) {
        super(context, baseFloorData, str, viewGroup);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0070 -> B:56:0x0077). Please submit an issue!!! */
    private void bindFloorAttrs(CommonMultiIconEntity.CommonInfo commonInfo) {
        int i2;
        int i3;
        this.commonInfo = commonInfo;
        if (commonInfo == null) {
            return;
        }
        CommonMultiIconEntity.CommonInfo.Background background = commonInfo.background;
        if (background != null && this.floorBackground != null) {
            if (TextUtils.equals(background.type, "0") && !TextUtils.isEmpty(background.value)) {
                ImageTools.displayImage(background.value, this.floorBackground, null, false, null);
            } else if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                this.floorBackground.setBackgroundColor(Color.parseColor(JDDarkUtil.COLOR_1D1B1B));
            } else {
                try {
                    if (!TextUtils.isEmpty(background.value)) {
                        this.floorBackground.setBackgroundColor(Color.parseColor(background.value));
                    } else {
                        this.floorBackground.setBackgroundResource(R.color.bp_personal_white);
                    }
                } catch (Exception e2) {
                    if (OKLog.D) {
                        OKLog.d(TAG, String.format("bindFloorAttrs parseColor exception: %s", e2));
                    }
                    this.floorBackground.setBackgroundResource(R.color.bp_personal_white);
                }
            }
        }
        try {
            i2 = Integer.parseInt(commonInfo.angle);
        } catch (Exception unused) {
            if (OKLog.D) {
                OKLog.d(TAG, "parse angel Exception");
            }
            i2 = 0;
        }
        RoundAngleFrameLayout roundAngleFrameLayout = this.floorContainer;
        if (roundAngleFrameLayout != null) {
            roundAngleFrameLayout.setRadius(i2);
        }
        try {
            i3 = Integer.parseInt(commonInfo.margin);
        } catch (Exception e3) {
            if (OKLog.D) {
                OKLog.d(TAG, String.format("parse margin exception: %s", e3));
            }
            i3 = 0;
        }
        LinearLayout linearLayout = this.rootLayout;
        if (linearLayout != null && (linearLayout.getLayoutParams() instanceof RecyclerView.LayoutParams)) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.rootLayout.getLayoutParams();
            if (i3 > 0) {
                layoutParams.setMargins(i3, 0, i3, 0);
            }
        }
        RoundAngleFrameLayout roundAngleFrameLayout2 = this.floorContainer;
        if (roundAngleFrameLayout2 == null || !(roundAngleFrameLayout2.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            return;
        }
        ((LinearLayout.LayoutParams) this.floorContainer.getLayoutParams()).height = commonInfo.rows * this.iconHeight;
    }

    private void bindFloorData(CommonMultiIconEntity commonMultiIconEntity) {
        if (commonMultiIconEntity == null) {
            hideViewHolder();
            return;
        }
        bindFloorAttrs(commonMultiIconEntity.commonInfo);
        bindMultiIconViewPager(commonMultiIconEntity.commonInfo, commonMultiIconEntity.iconElementList);
        mtaExpo(commonMultiIconEntity.commonInfo);
    }

    private void bindMultiIconViewPager(CommonMultiIconEntity.CommonInfo commonInfo, List<CommonMultiIconEntity.IconElement> list) {
        if (commonInfo == null || list == null || list.size() == 0) {
            return;
        }
        if (this.adapter == null) {
            MultiIconPagerAdapter multiIconPagerAdapter = new MultiIconPagerAdapter(this.baseActivity, getLayoutInflater(), getBaseData().mManageKey);
            this.adapter = multiIconPagerAdapter;
            this.viewPager.setAdapter(multiIconPagerAdapter);
        }
        this.adapter.swapAdapter(list, commonInfo);
        if (this.adapter.getCount() > 1) {
            this.indicator.setVisibility(0);
            this.indicator.setViewPager(this.viewPager);
            this.indicator.notifyDataSetChanged();
            return;
        }
        this.indicator.setVisibility(4);
    }

    private void handleDarkMode() {
        MultiIconPagerAdapter multiIconPagerAdapter = this.adapter;
        if (multiIconPagerAdapter != null) {
            multiIconPagerAdapter.notifyDataSetChanged();
        }
        if (this.floorBackground != null) {
            bindFloorAttrs(this.commonInfo);
        }
    }

    private void handleTextScaleModeChanged() {
        MultiIconPagerAdapter multiIconPagerAdapter = this.adapter;
        if (multiIconPagerAdapter != null) {
            multiIconPagerAdapter.notifyDataSetChanged();
        }
    }

    private CommonMultiIconEntity parserData(Object obj) {
        if (obj instanceof String) {
            try {
                if (OKLog.D) {
                    OKLog.d(TAG, String.format("CommonMultiIconFloor parserData: %s", obj));
                }
                return (CommonMultiIconEntity) JDJSON.parseObject((String) obj, CommonMultiIconEntity.class);
            } catch (Exception e2) {
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format("CommonMultiIconFloor parserData get error: %s  | data: %s", e2, obj));
                if (OKLog.D) {
                    OKLog.d(TAG, illegalArgumentException);
                }
                ExceptionReporter.reportExceptionToBugly(illegalArgumentException);
            }
        }
        return null;
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    @Override // com.jingdong.sdk.platform.business.personal.floor.AbstractPersonalFloor
    public ViewGroup createRootView(ViewGroup viewGroup) {
        return inflate(R.layout.floor_common_multi_icon, viewGroup);
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class<BaseViewModel> getViewModelClass() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void initData() {
        super.initData();
        this.iconHeight = getResources().getDimensionPixelOffset(R.dimen.bp_personal_multi_icon_height);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void initView() {
        TextScaleModeHelper.INSTANCE.getInstance().addOnTextSizeChangeListener(this);
        DeepDarkChangeManager.getInstance().addDeepDarkChangeListener(this);
        handleDarkMode();
    }

    public void mtaExpo(CommonMultiIconEntity.CommonInfo commonInfo) {
        String str;
        if (this.iconExpoRunnable == null && commonInfo != null) {
            CommonMultiIconEntity.CommonInfo.MtaEntity mtaEntity = commonInfo.mtaEntity;
            String str2 = "";
            if (mtaEntity != null) {
                str2 = mtaEntity.eventId;
                str = mtaEntity.eventParam;
            } else {
                str = "";
            }
            this.iconExpoRunnable = new MtaCommonMultiIconExpoRunnable(getBaseData().mManageKey, str2, str);
        }
        runOnAttachToWindow(this.iconExpoRunnable);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onCreatedView() {
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void onDestroyView() {
        TextScaleModeHelper.INSTANCE.getInstance().removeOnTextSizeChangeListener(this);
        DeepDarkChangeManager.getInstance().removeDeepDarkChangeListener(this);
    }

    @Override // com.jingdong.common.utils.text.OnTextScaleModeChangeListener
    public void onTextScaleModeChanged() {
        handleTextScaleModeChanged();
    }

    @Override // com.jingdong.common.utils.DeepDarkChangeManager.OnUIModeChangeListener
    public void onUIModeChanged(int i2) {
        handleDarkMode();
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(BaseTemplateEntity baseTemplateEntity) {
        bindFloorData(parserData(baseTemplateEntity.mData));
    }
}
