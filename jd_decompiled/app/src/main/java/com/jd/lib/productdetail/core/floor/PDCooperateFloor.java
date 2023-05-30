package com.jd.lib.productdetail.core.floor;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.R;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.entitys.temp.FloorTemplate;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessData;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jd.lib.productdetail.core.events.PDLayerEvent;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.utils.PDLocalReceiver;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.platform.business.utils.JumpHelper;
import com.jingdong.sdk.platform.floor.BaseFloor;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt;
import de.greenrobot.event.EventBus;
import java.util.Map;

/* loaded from: classes15.dex */
public abstract class PDCooperateFloor extends BaseFloor<PDFloorData, FloorTemplate, BaseViewModel, BaseNavigator> implements IBaseCooperateExt {
    protected String TAG;
    public int extraPaddingBottom;
    public int extraPaddingTop;
    protected Map<String, String> generalTrackDic;
    public LocalBroadcastManager localBroadcastManager;
    protected PDManager mDetailManager;
    private boolean mIsRegister;
    public PDLocalReceiver pdLocalReceiver;
    protected Map<String, String> track;

    public PDCooperateFloor(Context context, PDFloorData pDFloorData, String str, ViewGroup viewGroup) {
        super(context, pDFloorData, str, viewGroup);
        this.TAG = "PDCooperateFloor";
        this.mIsRegister = false;
        this.extraPaddingTop = -1;
        this.extraPaddingBottom = -1;
        this.mDetailManager = PDManager.getInstances(((PDFloorData) this.mFloorData).mManageKey);
        registerEventBus();
    }

    private String getExceptionStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        V v = this.mFloorEntity;
        sb.append(v != 0 ? ((FloorTemplate) v).mId : "");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        V v2 = this.mFloorEntity;
        sb.append(v2 != 0 ? ((FloorTemplate) v2).bId : "");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void isvExplore(FloorTemplate floorTemplate, long j2) {
        if (floorTemplate == null || getBaseConfig() == null) {
            return;
        }
        PdBaseConfig baseConfig = getBaseConfig();
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("mid", (Object) floorTemplate.mId);
        jDJSONObject.put("bid", (Object) floorTemplate.bId);
        jDJSONObject.put("cost", (Object) Long.valueOf(j2));
        PDUtils.exposureJsonParam("ISVProductDetailExpo", jDJSONObject.toJSONString(), null, baseConfig.sku, baseConfig.shopId, baseConfig.skuTag);
    }

    private String map2JsonString(Map<String, Object> map) {
        return JDJSON.toJSONString(map);
    }

    private void onAfterDataShowd(FloorTemplate floorTemplate, Object obj) {
        if (getBaseConfig() == null || !getBaseConfig().tenthRevision) {
            return;
        }
        V v = this.mFloorEntity;
        int i2 = ((FloorTemplate) v).extraPaddingTop;
        this.extraPaddingTop = i2;
        int i3 = ((FloorTemplate) v).extraPaddingBottom;
        this.extraPaddingBottom = i3;
        if (i2 == -1 && i3 == -1) {
            return;
        }
        if (((FloorTemplate) v).originalPaddingTop == -1 || ((FloorTemplate) v).originalPaddingBottom == -1) {
            ((FloorTemplate) v).originalPaddingTop = this.mRootView.getPaddingTop();
            ((FloorTemplate) this.mFloorEntity).originalPaddingBottom = this.mRootView.getPaddingBottom();
        }
        V v2 = this.mFloorEntity;
        int i4 = ((FloorTemplate) v2).originalPaddingTop;
        int i5 = this.extraPaddingTop;
        if (i5 == -1) {
            i5 = 0;
        }
        int i6 = i4 + i5;
        int i7 = ((FloorTemplate) v2).originalPaddingBottom;
        int i8 = this.extraPaddingBottom;
        int i9 = i7 + (i8 != -1 ? i8 : 0);
        ViewGroup viewGroup = this.mRootView;
        viewGroup.setPadding(viewGroup.getPaddingLeft(), i6, this.mRootView.getPaddingRight(), i9);
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("PDCooperateFloor onDataShowed after super ");
            sb.append(floorTemplate != null ? floorTemplate.mId : null);
            sb.append(" mFloorEntity.originalPaddingTop = ");
            sb.append(((FloorTemplate) this.mFloorEntity).originalPaddingTop);
            sb.append(" mFloorEntity.originalPaddingBottom = ");
            sb.append(((FloorTemplate) this.mFloorEntity).originalPaddingBottom);
            sb.append(" extraPaddingTop = ");
            sb.append(this.extraPaddingTop);
            sb.append(" extraPaddingBottom = ");
            sb.append(this.extraPaddingBottom);
            sb.append(" newPaddingTop = ");
            sb.append(i6);
            sb.append(" newPaddingBottom = ");
            sb.append(i9);
            sb.toString();
        }
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public abstract void adapterDarkModel(boolean z);

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public abstract void adapterVersion(int i2);

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    protected BaseNavigator createNavigator() {
        return null;
    }

    protected int getColorWithTheme(@ColorRes int i2, @ColorRes int i3) {
        return PDUtils.getColorWithTheme(getBaseConfig() != null && getBaseConfig().isDarkTheme(), ContextCompat.getColor(getContext(), i2), ContextCompat.getColor(getContext(), i3));
    }

    protected EventBus getEventBus() {
        return PDManager.getEventBus();
    }

    @Override // com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder
    public Class<BaseViewModel> getViewModelClass() {
        return null;
    }

    public void handlerEvent(PDApiEvent pDApiEvent) {
    }

    public void handlerEvent(PDViewEvent pDViewEvent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void initData() {
    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected boolean isUsedLayoutParams() {
        return false;
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public void jump(int i2, String str) {
        JumpHelper.jump(getContext(), str, i2);
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public void mtaClick(String str, Map<String, Object> map) {
        if (getBaseConfig() == null) {
            return;
        }
        PDUtils.onClickJsonParam(str, null, "com.jd.lib.productdetail.ProductDetailActivity", getBaseConfig().sku, getBaseConfig().skuTag, map2JsonString(map), getBaseConfig().shopId);
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public void mtaExplore(String str, Map<String, Object> map) {
        if (getBaseConfig() == null) {
            return;
        }
        PdBaseConfig baseConfig = getBaseConfig();
        PDUtils.exposureJsonParam(str, map2JsonString(map), null, baseConfig.sku, baseConfig.shopId, baseConfig.skuTag);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onAttachToWindow() {
        super.onAttachToWindow();
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
    }

    public void onEventMainThread(PDViewEvent pDViewEvent) {
        if (this.mIsDestroy || !isValid() || pDViewEvent == null || !TextUtils.equals(pDViewEvent.mManagerKey, ((PDFloorData) this.mFloorData).mManageKey)) {
            return;
        }
        try {
            handlerEvent(pDViewEvent);
        } catch (Exception e2) {
            PDUtils.ISVReportException(e2, (BaseTemplateEntity) this.mFloorEntity);
        }
    }

    protected abstract void onMtaExplore();

    @Override // com.jingdong.sdk.platform.floor.BaseFloor
    public void onPause() {
    }

    @Override // com.jingdong.sdk.platform.floor.BaseFloor
    public void onResume() {
    }

    protected abstract boolean onShowData(FloorTemplate floorTemplate);

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public void refresh() {
        if (getBaseConfig() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("key", getBaseConfig().sku);
        PDManager.getEventBus().post(new PDViewEvent("pd_ProductDetailActivity_refreshPage", bundle, getBaseConfig().manageKey));
    }

    public void registerBroadcastReceiver(String str) {
        this.localBroadcastManager = LocalBroadcastManager.getInstance(this.mContext);
        if (this.pdLocalReceiver == null && getBaseConfig() != null) {
            ProductDetailEntity productDetailEntity = new ProductDetailEntity();
            productDetailEntity.skuId = getBaseConfig().sku;
            productDetailEntity.mManageKey = getBaseConfig().manageKey;
            this.pdLocalReceiver = new PDLocalReceiver(productDetailEntity, "");
        }
        if (Log.D) {
            Log.d(this.TAG, "checkLocUrl() 111 -->> " + this.localBroadcastManager);
        }
        this.localBroadcastManager.registerReceiver(this.pdLocalReceiver, new IntentFilter(str));
    }

    public void registerEventBus() {
        if (!isRegisterEventBus() || getEventBus().isRegistered(this)) {
            return;
        }
        this.mIsRegister = true;
        getEventBus().register(this);
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected void setBackGroundColor() {
        boolean z;
        V v;
        int colorWithTheme;
        if (!canSetBg() || this.mRootView == null || isLine()) {
            return;
        }
        boolean z2 = true;
        if (getBaseConfig() != null) {
            if (getBaseConfig().themeStyle == -1) {
                if (DeepDarkChangeManager.getInstance().getUIMode() == 1) {
                    z = true;
                }
            } else {
                z = getBaseConfig().isDarkTheme();
            }
            v = this.mFloorEntity;
            if (v == 0 && !TextUtils.isEmpty(((FloorTemplate) v).backgroundColor)) {
                colorWithTheme = PDUtils.getColorWithTheme(z, ((FloorTemplate) this.mFloorEntity).backgroundColor, ContextCompat.getColor(getContext(), R.color.lib_pd_core_color_1db1b));
            } else {
                colorWithTheme = PDUtils.getColorWithTheme(z, ContextCompat.getColor(getContext(), R.color.lib_pd_core_white), ContextCompat.getColor(getContext(), R.color.lib_pd_core_color_1db1b));
            }
            if (getBaseConfig() != null && getBaseConfig().tenthRevision) {
                z2 = false;
            }
            ViewCompat.setBackground(this.mRootView, PDUtils.getFloorBgDrawable(getBaseEntity(), colorWithTheme, z, z2));
        }
        z = false;
        v = this.mFloorEntity;
        if (v == 0) {
        }
        colorWithTheme = PDUtils.getColorWithTheme(z, ContextCompat.getColor(getContext(), R.color.lib_pd_core_white), ContextCompat.getColor(getContext(), R.color.lib_pd_core_color_1db1b));
        if (getBaseConfig() != null) {
            z2 = false;
        }
        ViewCompat.setBackground(this.mRootView, PDUtils.getFloorBgDrawable(getBaseEntity(), colorWithTheme, z, z2));
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public void showDialog(View view, float f2) {
        view.setTag(Float.valueOf(f2));
        PDManager.getEventBus().post(new PDLayerEvent(PDLayerEvent.ACTION_EVENT_SHOW_DYNAMIC_VIEW, view, ((PDFloorData) this.mFloorData).mManageKey));
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public void showToast(String str) {
        PDUtils.showToastShortNormal(getContext(), str);
    }

    public void unRegisterBroadcastReceiver() {
        PDLocalReceiver pDLocalReceiver = this.pdLocalReceiver;
        if (pDLocalReceiver != null) {
            this.localBroadcastManager.unregisterReceiver(pDLocalReceiver);
            this.pdLocalReceiver = null;
        }
    }

    public void unRegisterEventBus() {
        if (this.mIsRegister) {
            this.mIsRegister = false;
            getEventBus().unregister(this);
        }
    }

    @Override // com.jingdong.sdk.platform.floor.isv.IBaseCooperateExt
    public PdBaseConfig getBaseConfig() {
        V v = this.mFloorEntity;
        if (v == 0 || !(((FloorTemplate) v).mExtData instanceof PdBaseConfig)) {
            return null;
        }
        return (PdBaseConfig) ((FloorTemplate) v).mExtData;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public boolean onDataShowed(FloorTemplate floorTemplate, Object obj) {
        int paddingTop = this.mRootView.getPaddingTop();
        int paddingBottom = this.mRootView.getPaddingBottom();
        int i2 = this.extraPaddingTop;
        if (i2 != -1 || this.extraPaddingBottom != -1) {
            if (i2 == -1) {
                i2 = 0;
            }
            int i3 = paddingTop - i2;
            int i4 = this.extraPaddingBottom;
            if (i4 == -1) {
                i4 = 0;
            }
            int i5 = paddingBottom - i4;
            ViewGroup viewGroup = this.mRootView;
            viewGroup.setPadding(viewGroup.getPaddingLeft(), i3, this.mRootView.getPaddingRight(), i5);
        }
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("PDCooperateFloor onDataShowed befor super ");
            sb.append(floorTemplate != null ? floorTemplate.mId : null);
            sb.append(" oriPaddingTop = ");
            sb.append(paddingTop);
            sb.append(" oriPaddingBottom = ");
            sb.append(paddingBottom);
            sb.append(" extraPaddingTop = ");
            sb.append(this.extraPaddingTop);
            sb.append(" extraPaddingBottom = ");
            sb.append(this.extraPaddingBottom);
            sb.append(" mRootView.getPaddingTop() = ");
            sb.append(this.mRootView.getPaddingTop());
            sb.append(" mRootView.getPaddingBottom() = ");
            sb.append(this.mRootView.getPaddingBottom());
            sb.toString();
        }
        if (isSameEntity(floorTemplate) && ((floorTemplate == null || floorTemplate.isShow) && floorTemplate != null && floorTemplate.isRoundChanged)) {
            floorTemplate.isRoundChanged = false;
            try {
                setBackGroundColor();
            } catch (Exception unused) {
            }
        }
        super.onDataShowed((PDCooperateFloor) floorTemplate, obj);
        if (this.mFloorEntity != 0 && this.mIsInit && !this.mIsDestroy) {
            onAfterDataShowd(floorTemplate, obj);
            if (Log.D) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("PDCooperateFloor onDataShowed end ");
                sb2.append(floorTemplate != null ? floorTemplate.mId : null);
                sb2.toString();
            }
        }
        return false;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(FloorTemplate floorTemplate, Object obj) {
        if (floorTemplate != null) {
            Map<String, String> map = floorTemplate.track;
            if (map != null) {
                this.track = map;
            }
            WareBusinessData wareBusinessData = floorTemplate.mSkuBaseData;
            if (wareBusinessData != null) {
                this.generalTrackDic = wareBusinessData.generalTrackDic;
            }
        }
        super.showData((PDCooperateFloor) floorTemplate, obj);
    }

    public void onEventMainThread(PDApiEvent pDApiEvent) {
        if (this.mIsDestroy || !isValid() || pDApiEvent == null || !TextUtils.equals(pDApiEvent.mManagerKey, ((PDFloorData) this.mFloorData).mManageKey)) {
            return;
        }
        try {
            handlerEvent(pDApiEvent);
        } catch (Exception e2) {
            PDUtils.ISVReportException(e2, (BaseTemplateEntity) this.mFloorEntity);
        }
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void showData(final FloorTemplate floorTemplate) {
        try {
            long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
            if (onShowData(floorTemplate)) {
                final long currentThreadTimeMillis2 = SystemClock.currentThreadTimeMillis() - currentThreadTimeMillis;
                runOnAttachToWindow(new BaseFloor<PDFloorData, FloorTemplate, BaseViewModel, BaseNavigator>.FloorRunnable() { // from class: com.jd.lib.productdetail.core.floor.PDCooperateFloor.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super();
                    }

                    @Override // com.jingdong.sdk.platform.floor.BaseFloor.FloorRunnable
                    protected void runs() {
                        try {
                            PDCooperateFloor.this.isvExplore(floorTemplate, currentThreadTimeMillis2);
                            PDCooperateFloor.this.onMtaExplore();
                        } catch (Exception e2) {
                            PDUtils.ISVReportException(e2, floorTemplate);
                        }
                    }
                });
                adapterDarkModel(getBaseConfig().isDarkTheme());
                adapterVersion(getBaseConfig().tenthRevision ? 10 : 9);
            } else {
                hideFloor();
            }
        } catch (Exception e2) {
            PDUtils.ISVReportException(e2, floorTemplate);
            hideFloor();
        }
    }
}
