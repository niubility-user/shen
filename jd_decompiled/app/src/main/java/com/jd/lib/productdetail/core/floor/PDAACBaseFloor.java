package com.jd.lib.productdetail.core.floor;

import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.lib.productdetail.core.entitys.CommonDLayerItemEntity;
import com.jd.lib.productdetail.core.entitys.ProductDetailEntity;
import com.jd.lib.productdetail.core.entitys.temp.FloorTemplate;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jd.lib.productdetail.core.events.PDLayerEvent;
import com.jd.lib.productdetail.core.events.PDViewEvent;
import com.jd.lib.productdetail.core.utils.PDLocalReceiver;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.sdk.aac.model.BaseViewModel;
import com.jingdong.sdk.aac.navigator.BaseNavigator;
import com.jingdong.sdk.platform.floor.BaseFloor;
import de.greenrobot.event.EventBus;

/* loaded from: classes15.dex */
public abstract class PDAACBaseFloor<VM extends BaseViewModel, N extends BaseNavigator> extends BaseFloor<PDFloorData, FloorTemplate, VM, N> {
    protected static final String CLASS_NAME = "com.jd.lib.productdetail.ProductDetailActivity";
    protected String TAG;
    public LocalBroadcastManager localBroadcastManager;
    protected PDManager mDetailManager;
    private boolean mIsRegister;
    public ProductDetailEntity mProduct;
    public PDLocalReceiver pdLocalReceiver;

    public PDAACBaseFloor(Context context, PDFloorData pDFloorData, String str, ViewGroup viewGroup) {
        super(context, pDFloorData, str, viewGroup);
        this.TAG = "PDBaseFloor";
        this.mIsRegister = false;
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

    private FloorThemeEnum getFloorTheme() {
        return this.mProduct.getFloorTheme();
    }

    protected int getCurrentThemeColor() {
        return PDManager.getInstances(getManagerKey()).getThemeHelper().getCurrentThemeColor(this.mContext, getFloorTheme());
    }

    protected EventBus getEventBus() {
        return PDManager.getEventBus();
    }

    public void handlerEvent(PDApiEvent pDApiEvent) {
    }

    public void handlerEvent(PDViewEvent pDViewEvent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    public void initData() {
        this.mProduct = ((PDFloorData) this.mFloorData).mProduct;
    }

    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder
    protected boolean isUsedLayoutParams() {
        return false;
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onAttachToWindow() {
        super.onAttachToWindow();
    }

    @Override // com.jingdong.sdk.platform.base.BaseViewHolder, com.jingdong.sdk.aac.ui.LifecycleBaseViewHolder, com.jingdong.sdk.platform.base.ViewHolder
    public void onDestroy() {
        super.onDestroy();
        unRegisterEventBus();
        unRegisterBroadcastReceiver();
    }

    public void onEventMainThread(PDViewEvent pDViewEvent) {
        if (this.mIsDestroy || !isValid() || pDViewEvent == null || !TextUtils.equals(pDViewEvent.mManagerKey, ((PDFloorData) this.mFloorData).mManageKey)) {
            return;
        }
        handlerEvent(pDViewEvent);
    }

    @Override // com.jingdong.sdk.platform.floor.BaseFloor
    public void onPause() {
    }

    @Override // com.jingdong.sdk.platform.floor.BaseFloor
    public void onResume() {
    }

    public void registerBroadcastReceiver(String str) {
        this.localBroadcastManager = LocalBroadcastManager.getInstance(this.mContext);
        if (this.pdLocalReceiver == null) {
            this.pdLocalReceiver = new PDLocalReceiver(this.mProduct);
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

    protected void resetAdTextViewColor(TextView... textViewArr) {
        PDManager.getInstances(getManagerKey()).getThemeHelper().setAdTextViewColor(this.mContext, getFloorTheme(), textViewArr);
    }

    protected void resetFramePriceTextColor(TextView... textViewArr) {
        PDManager.getInstances(getManagerKey()).getThemeHelper().setFramePriceTextColor(this.mContext, getFloorTheme(), textViewArr);
    }

    protected void resetFrameTextTheme(TextView... textViewArr) {
        PDManager.getInstances(getManagerKey()).getThemeHelper().setFrameTextViewTheme(this.mContext, getFloorTheme(), textViewArr);
    }

    protected void resetIndicatorViewBackground(View... viewArr) {
        PDManager.getInstances(getManagerKey()).getThemeHelper().setIndicatorViewBackground(getFloorTheme(), viewArr);
    }

    protected void resetPriceTextColor(TextView... textViewArr) {
        PDManager.getInstances(getManagerKey()).getThemeHelper().setPriceTextColor(this.mContext, getFloorTheme(), textViewArr);
    }

    protected void resetTextViewColor(TextView... textViewArr) {
        PDManager.getInstances(getManagerKey()).getThemeHelper().setTextViewColor(this.mContext, getFloorTheme(), textViewArr);
    }

    protected void showCommonDLayer(View view, CommonDLayerItemEntity commonDLayerItemEntity) {
        Object[] objArr = {view, commonDLayerItemEntity};
        if (this.mProduct != null) {
            getEventBus().post(new PDLayerEvent("action_event_layer_common_d", objArr, this.mProduct.mManageKey));
        }
    }

    protected void showServiceLayer(String str, String[] strArr) {
        Object[] objArr = {str, strArr};
        if (this.mProduct != null) {
            getEventBus().post(new PDLayerEvent("action_event_layer_common_rule", objArr, this.mProduct.mManageKey));
        }
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

    public void onEventMainThread(PDApiEvent pDApiEvent) {
        if (this.mIsDestroy || !isValid() || pDApiEvent == null || !TextUtils.equals(pDApiEvent.mManagerKey, ((PDFloorData) this.mFloorData).mManageKey)) {
            return;
        }
        handlerEvent(pDApiEvent);
    }
}
