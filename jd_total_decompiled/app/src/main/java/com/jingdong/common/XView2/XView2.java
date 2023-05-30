package com.jingdong.common.XView2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.jingdong.cleanmvp.ui.BaseFragment;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.event.XView2DispatchEventManager;
import com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.ILayerCallBack;
import com.jingdong.common.XView2.layer.IReLoadLayer;
import com.jingdong.common.XView2.layer.LayerTypeManager;
import com.jingdong.common.XView2.strategy.PopStrategyManager;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XViewMtaUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.LangUtils;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class XView2 extends BaseXView2 implements IActivityLifeCallBack, IXView2 {
    ArrayList<BaseLayer> layers;
    private AtomicBoolean mIsImmediatelyPop = new AtomicBoolean(false);
    private AtomicBoolean mIsNeedScreenShot = new AtomicBoolean(false);
    private PopStrategyManager mPopStrategy;
    public XView2FragmentManagerImpl mXView2FragmentManager;

    private void resetLayerData() {
        PopStrategyManager popStrategyManager = this.mPopStrategy;
        if (popStrategyManager != null) {
            popStrategyManager.destroy();
            this.mPopStrategy = null;
        }
        LayerTypeManager layerTypeManager = this.mLayerTypeManager;
        if (layerTypeManager != null) {
            layerTypeManager.removeAllLayers();
            this.mLayerTypeManager.clearMuGroupNumbers();
            this.mLayerTypeManager = null;
        }
        XView2FragmentManagerImpl xView2FragmentManagerImpl = this.mXView2FragmentManager;
        if (xView2FragmentManagerImpl != null) {
            xView2FragmentManagerImpl.dispatchDestroy();
            this.mXView2FragmentManager = null;
        }
        this.mActivity = null;
        this.mXView2DispatchEventManager = null;
        this.mTargetType = 0;
        this.mTargetName = null;
        this.mLayersEntityList = null;
        this.mTargetsEntity = null;
        this.mIsNeedScreenShot.set(false);
        this.mIsImmediatelyPop.set(false);
    }

    public void changeXView2Layout(JSONObject jSONObject) {
        LayerTypeManager layerTypeManager = this.mLayerTypeManager;
        if (layerTypeManager != null) {
            layerTypeManager.changeLayerLayout(jSONObject);
        }
    }

    public void closeXView2ById(final XViewConfigEntity.LayersEntity layersEntity) {
        this.mActivity.get().runOnUiThread(new Runnable() { // from class: com.jingdong.common.XView2.XView2.6
            @Override // java.lang.Runnable
            public void run() {
                XViewConfigEntity.LayersEntity layersEntity2;
                LayerTypeManager layerTypeManager = XView2.this.mLayerTypeManager;
                if (layerTypeManager == null || (layersEntity2 = layersEntity) == null) {
                    return;
                }
                layerTypeManager.closeLayerById(layersEntity2.layerId);
            }
        });
    }

    public void createLayer(final XViewConfigEntity.LayersEntity layersEntity, boolean z, JSONObject jSONObject) {
        final BaseLayer createLayerInstance = this.mLayerTypeManager.createLayerInstance(this.mActivity, layersEntity, this);
        if (createLayerInstance == null) {
            XView2LayerObservableManager.getManager().notifyLayerShowError(layersEntity.layerId, layersEntity.logicKey);
            return;
        }
        XViewMtaUtil.popClickMta(this.mActivity.get(), layersEntity, this.mTargetsEntity, "0");
        createLayerInstance.initParamsBeforeCreateLayer(jSONObject);
        createLayerInstance.mXViewContainer = createLayerInstance.initXViewContainer();
        if (z) {
            if (XView2Utils.isLayerEnterImmediatelyPop(layersEntity)) {
                this.mIsImmediatelyPop.set(true);
            }
            if (XView2Utils.isLayerEnterPop(layersEntity)) {
                this.mIsNeedScreenShot.set(true);
            }
        }
        layersEntity.renderStartTime = System.currentTimeMillis();
        View createLayer = createLayerInstance.createLayer(createLayerInstance.mXViewContainer, layersEntity, new ILayerCallBack() { // from class: com.jingdong.common.XView2.XView2.2
            @Override // com.jingdong.common.XView2.layer.ILayerCallBack
            public void onCloseButtonClicked() {
            }

            @Override // com.jingdong.common.XView2.layer.ILayerCallBack
            public void onError(String str) {
                BaseLayer baseLayer = createLayerInstance;
                if (baseLayer != null) {
                    baseLayer.closeLayer(str, 0);
                }
            }

            @Override // com.jingdong.common.XView2.layer.ILayerCallBack
            public void onLayerClosed(String str, int i2) {
                BaseLayer baseLayer = createLayerInstance;
                if (baseLayer != null) {
                    baseLayer.closeLayer(str, i2);
                }
            }

            @Override // com.jingdong.common.XView2.layer.ILayerCallBack
            public void onLayerDisplayed(String str) {
                XViewConfigEntity.LayersEntity layersEntity2;
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onLayerDisplayed " + str);
                BaseLayer baseLayer = createLayerInstance;
                if (baseLayer == null || (layersEntity2 = layersEntity) == null) {
                    return;
                }
                baseLayer.onLayerDisplayed(str, layersEntity2.logicKey);
            }

            @Override // com.jingdong.common.XView2.layer.ILayerCallBack
            public void onLayerLoading(String str) {
                XViewConfigEntity.LayersEntity layersEntity2;
                BaseLayer baseLayer = createLayerInstance;
                if (baseLayer == null || (layersEntity2 = layersEntity) == null) {
                    return;
                }
                baseLayer.onLoadingLayer(str, layersEntity2.logicKey);
            }

            @Override // com.jingdong.common.XView2.layer.ILayerCallBack
            public void onLayerReady(String str) {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onLayerReady ");
            }
        });
        if (createLayer != null) {
            createLayerInstance.configCurrentLayer(this.mTargetsEntity, createLayer);
        }
        this.mLayerTypeManager.addLayerControlById(layersEntity.layerId, createLayerInstance);
    }

    @Override // com.jingdong.common.XView2.IXView2
    public void createXView2() {
        initBeforeCreateXView2();
        Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
        while (it.hasNext()) {
            obtainLayer(it.next());
        }
    }

    public void createXView2ById(XViewConfigEntity.LayersEntity layersEntity, JSONObject jSONObject) {
        LayerTypeManager layerTypeManager;
        if (layersEntity != null && !TextUtils.isEmpty(layersEntity.layerId)) {
            if (XView2Utils.canWebViewLayerPreLoaded(layersEntity) && (layerTypeManager = this.mLayerTypeManager) != null) {
                BaseLayer showLayerById = layerTypeManager.getShowLayerById(layersEntity);
                if (showLayerById == null) {
                    return;
                }
                if (showLayerById.mXViewContainer == null) {
                    showLayerById.mXViewContainer = showLayerById.initXViewContainer();
                }
                layersEntity.renderStartTime = System.currentTimeMillis();
                return;
            }
            createLayer(layersEntity, false, jSONObject);
            return;
        }
        XView2LayerObservableManager.getManager().notifyLayerShowError(layersEntity.layerId, layersEntity.logicKey);
    }

    @Override // com.jingdong.common.XView2.IXView2
    public void dispatchPop(Context context, Bundle bundle) {
        if (context == null || bundle == null) {
            return;
        }
        if (this.mXView2DispatchEventManager == null) {
            this.mXView2DispatchEventManager = new XView2DispatchEventManager(this);
        }
        this.mXView2DispatchEventManager.upDateLayerEntities(this.mLayersEntityList);
        this.mXView2DispatchEventManager.dispatchPopEvent(context, bundle);
    }

    @Override // com.jingdong.common.XView2.IXView2
    public boolean displayXView2() {
        SoftReference<Activity> softReference = this.mActivity;
        if (softReference != null && softReference.get() != null) {
            if (this.mActivity.get().isFinishing()) {
                LayerTypeManager layerTypeManager = this.mLayerTypeManager;
                if (layerTypeManager != null) {
                    XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
                    layerTypeManager.reportException(1, "pageDestroy", targetsEntity != null ? String.valueOf(targetsEntity.targetId) : "");
                }
                return false;
            }
            this.mActivity.get().runOnUiThread(new Runnable() { // from class: com.jingdong.common.XView2.XView2.3
                @Override // java.lang.Runnable
                public void run() {
                    LayerTypeManager layerTypeManager2 = XView2.this.mLayerTypeManager;
                    if (layerTypeManager2 != null) {
                        layerTypeManager2.showAllEnterPopLayers();
                    }
                }
            });
            return false;
        }
        LayerTypeManager layerTypeManager2 = this.mLayerTypeManager;
        if (layerTypeManager2 != null) {
            XViewConfigEntity.TargetsEntity targetsEntity2 = this.mTargetsEntity;
            layerTypeManager2.reportException(1, "pageDestroy", targetsEntity2 != null ? String.valueOf(targetsEntity2.targetId) : "");
        }
        return false;
    }

    @Override // com.jingdong.common.XView2.IXView2
    public boolean displayXView2ById(final XViewConfigEntity.LayersEntity layersEntity) {
        SoftReference<Activity> softReference;
        if (layersEntity != null && (softReference = this.mActivity) != null && softReference.get() != null && !this.mActivity.get().isFinishing()) {
            this.mActivity.get().runOnUiThread(new Runnable() { // from class: com.jingdong.common.XView2.XView2.5
                @Override // java.lang.Runnable
                public void run() {
                    XViewConfigEntity.LayersEntity layersEntity2;
                    LayerTypeManager layerTypeManager = XView2.this.mLayerTypeManager;
                    if (layerTypeManager == null || (layersEntity2 = layersEntity) == null) {
                        return;
                    }
                    layerTypeManager.showLayerById(layersEntity2.layerId, layersEntity2.logicKey);
                }
            });
            return false;
        }
        XView2LayerObservableManager.getManager().notifyLayerShowError(layersEntity.layerId, layersEntity.logicKey);
        XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
        XView2Utils.reportXView2ErrorWithSwitch("pageDestroy", "NXViewException", "", targetsEntity != null ? String.valueOf(targetsEntity.targetId) : "", layersEntity != null ? layersEntity.layerId : "", layersEntity != null ? layersEntity.name : "", "");
        return false;
    }

    @Override // com.jingdong.common.XView2.IXView2
    public boolean displayXView2Immediately() {
        SoftReference<Activity> softReference = this.mActivity;
        if (softReference != null && softReference.get() != null) {
            if (this.mActivity.get().isFinishing()) {
                LayerTypeManager layerTypeManager = this.mLayerTypeManager;
                if (layerTypeManager != null) {
                    XViewConfigEntity.TargetsEntity targetsEntity = this.mTargetsEntity;
                    layerTypeManager.reportException(2, "pageDestroy", targetsEntity != null ? String.valueOf(targetsEntity.targetId) : "");
                }
                return false;
            }
            this.mActivity.get().runOnUiThread(new Runnable() { // from class: com.jingdong.common.XView2.XView2.4
                @Override // java.lang.Runnable
                public void run() {
                    LayerTypeManager layerTypeManager2 = XView2.this.mLayerTypeManager;
                    if (layerTypeManager2 != null) {
                        layerTypeManager2.showAllPopLayersImmediately();
                    }
                }
            });
            return false;
        }
        LayerTypeManager layerTypeManager2 = this.mLayerTypeManager;
        if (layerTypeManager2 != null) {
            XViewConfigEntity.TargetsEntity targetsEntity2 = this.mTargetsEntity;
            layerTypeManager2.reportException(2, "pageDestroy", targetsEntity2 != null ? String.valueOf(targetsEntity2.targetId) : "");
        }
        return false;
    }

    @Override // com.jingdong.common.XView2.IXView2
    public Fragment getCurrentFragment() {
        SoftReference<Fragment> softReference = this.mCurrentFragment;
        if (softReference == null || softReference.get() == null) {
            return null;
        }
        return this.mCurrentFragment.get();
    }

    @Override // com.jingdong.common.XView2.IXView2
    public String getCurrentTargetName() {
        return this.mTargetName;
    }

    public ArrayList<BaseLayer> getLayersByTargetName(Fragment fragment) {
        SoftReference<Activity> softReference;
        LayerTypeManager layerTypeManager;
        BaseLayer baseLayer;
        ArrayList<BaseLayer> arrayList = new ArrayList<>();
        if ((this.mFragmentArray.contains(fragment.getClass().getName()) || XView2Utils.isHitH5Fragment(this.mTargetName, fragment)) && this.mIsTabModel.get() && (softReference = this.mActivity) != null) {
            ArrayList<XViewConfigEntity.LayersEntity> allLayersByTargetName = XView2Utils.getAllLayersByTargetName(softReference.get(), this.mXViewConfigEntity, fragment);
            if (XView2Utils.isListEmpty(allLayersByTargetName)) {
                return null;
            }
            Iterator<XViewConfigEntity.LayersEntity> it = allLayersByTargetName.iterator();
            while (it.hasNext()) {
                String str = it.next().layerId;
                if (!TextUtils.isEmpty(str) && (layerTypeManager = this.mLayerTypeManager) != null && (baseLayer = layerTypeManager.getLayoutControls().get(str)) != null) {
                    arrayList.add(baseLayer);
                }
            }
            return arrayList;
        }
        return null;
    }

    public XViewConfigEntity.LayersEntity getRealPopLayerEntity(XViewConfigEntity.LayersEntity layersEntity) {
        return this.mXViewDelegate.getRealPopLayerEntity(layersEntity);
    }

    public XViewConfigEntity.TargetsEntity getTargetEntity() {
        return this.mTargetsEntity;
    }

    @Override // com.jingdong.common.XView2.IXView2
    public XView2Delegate getXView2Delegate() {
        return this.mXViewDelegate;
    }

    public XViewConfigEntity getXViewConfigEntity() {
        return this.mXViewConfigEntity;
    }

    public void initBeforeCreateXView2() {
        if (this.mLayerTypeManager == null) {
            this.mLayerTypeManager = new LayerTypeManager();
        }
        SoftReference<Activity> softReference = this.mActivity;
        if (softReference != null) {
            this.mXViewDelegate.setCurrentXView2(this, softReference.get(), this.mLayersEntityList, this.mXViewConfigEntity);
        }
        XView2Delegate xView2Delegate = this.mXViewDelegate;
        if (xView2Delegate != null) {
            xView2Delegate.setLayoutManager(this.mLayerTypeManager);
        }
        XView2Delegate xView2Delegate2 = this.mXViewDelegate;
        if (xView2Delegate2 != null) {
            xView2Delegate2.preLoadLayerResource();
        }
    }

    public void initXView2FragmentManager() {
        if (this.mXView2FragmentManager == null) {
            this.mXView2FragmentManager = new XView2FragmentManagerImpl(this.mActivity.get());
        }
        this.mXView2FragmentManager.initFragmentLifeCallBack();
        this.mXView2FragmentManager.registerFragmentLifecycleCallbacks(new XView2FragmentManagerImpl.XView2FragmentLifecycleCallbacks() { // from class: com.jingdong.common.XView2.XView2.1
            @Override // com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl.XView2FragmentLifecycleCallbacks
            public void onFragmentDestroyed(@NonNull Fragment fragment) {
                super.onFragmentDestroyed(fragment);
                if (fragment != null && (fragment instanceof BaseFragment)) {
                    ((BaseFragment) fragment).removeFragmentStateCallBack();
                }
            }

            @Override // com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl.XView2FragmentLifecycleCallbacks
            public void onFragmentPaused(@NonNull Fragment fragment) {
                super.onFragmentPaused(fragment);
                if (fragment == null) {
                    return;
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onFragmentRealPause " + fragment.getClass().getName() + LangUtils.SINGLE_SPACE + fragment.isHidden() + LangUtils.SINGLE_SPACE + fragment.getUserVisibleHint() + LangUtils.SINGLE_SPACE + fragment.isVisible());
                XView2.this.onXViewFragmentPaused(fragment);
            }

            @Override // com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl.XView2FragmentLifecycleCallbacks
            public void onFragmentRealVisible(@NonNull Fragment fragment, boolean z) {
                if (fragment instanceof BaseFragment) {
                    if (z) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onFragmentRealVisible " + z + LangUtils.SINGLE_SPACE + fragment.getClass().getName());
                    } else if (fragment.isResumed()) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onFragmentRealVisible " + z + LangUtils.SINGLE_SPACE + fragment.getClass().getName());
                        XView2.this.onXViewFragmentViewDestroyed(fragment);
                    }
                }
            }

            @Override // com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl.XView2FragmentLifecycleCallbacks
            public void onFragmentResumed(@NonNull Fragment fragment) {
                super.onFragmentResumed(fragment);
                if (fragment == null) {
                    return;
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onFragmentRealResume " + fragment.getClass().getName() + LangUtils.SINGLE_SPACE + fragment.isHidden());
                XView2.this.onXViewFragmentResumed(fragment);
            }

            @Override // com.jingdong.common.XView2.fragment.XView2FragmentManagerImpl.XView2FragmentLifecycleCallbacks
            public void onFragmentViewDestroyed(@NonNull Fragment fragment) {
                super.onFragmentViewDestroyed(fragment);
                if (fragment == null) {
                    return;
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "onFragmentViewDestroyed " + fragment.getClass().getName() + LangUtils.SINGLE_SPACE + fragment.isHidden());
                XView2.this.onXViewFragmentViewDestroyed(fragment);
            }
        });
    }

    public void obtainLayer(XViewConfigEntity.LayersEntity layersEntity) {
        if (!TextUtils.isEmpty(layersEntity.layerId) && canLayerCreatedByPopType(layersEntity)) {
            BaseLayer baseLayer = this.mLayerTypeManager.getLayoutControls().get(layersEntity.layerId);
            if (baseLayer == null || isLayerNeedReCreateChecked(baseLayer)) {
                if (XView2Utils.canWebViewLayerPreLoaded(layersEntity) || isLayerCanPopByMutexGroupID(layersEntity, false)) {
                    IReLoadLayer iReLoadLayer = new IReLoadLayer() { // from class: com.jingdong.common.XView2.XView2.7
                        @Override // com.jingdong.common.XView2.layer.IReLoadLayer
                        public void reloadLayer(XViewConfigEntity.LayersEntity layersEntity2) {
                            int i2;
                            if (layersEntity2 == null || (i2 = layersEntity2.mHasReloadCount) != 0) {
                                return;
                            }
                            layersEntity2.mHasReloadCount = i2 + 1;
                            XView2.this.obtainLayer(layersEntity2);
                            XView2.this.startPopStrategy();
                            LayerTypeManager layerTypeManager = XView2.this.mLayerTypeManager;
                            if (layerTypeManager != null) {
                                layerTypeManager.onLayerResume();
                            }
                        }
                    };
                    layersEntity.mReLoadLayerCallBack = iReLoadLayer;
                    if (isLayerCanPopByConfig(layersEntity, iReLoadLayer)) {
                        obtainMutexGroup(layersEntity);
                    }
                }
            }
        }
    }

    public void obtainMutexGroup(XViewConfigEntity.LayersEntity layersEntity) {
        if (layersEntity == null || TextUtils.isEmpty(layersEntity.layerId)) {
            return;
        }
        createLayer(layersEntity, true, null);
    }

    @Override // com.jingdong.common.XView2.IActivityLifeCallBack
    public void onCreate(@NonNull Activity activity) {
        if (activity == null) {
            return;
        }
        this.mActivity = new SoftReference<>(activity);
        initXView2FragmentManager();
    }

    @Override // com.jingdong.common.XView2.IActivityLifeCallBack
    public void onDestroy(@NonNull Activity activity) {
        resetLayerData();
    }

    @Override // com.jingdong.common.XView2.IActivityLifeCallBack
    public void onPause(@NonNull Activity activity) {
        if (this.mLayerTypeManager != null && !this.mIsTabModel.get()) {
            this.mLayerTypeManager.onLayerPause();
        }
        PopStrategyManager popStrategyManager = this.mPopStrategy;
        if (popStrategyManager != null) {
            popStrategyManager.stop();
        }
    }

    @Override // com.jingdong.common.XView2.IActivityLifeCallBack
    public void onResume(@NonNull Activity activity) {
        if (this.mXViewConfigEntity == null) {
            return;
        }
        filterCurrentActivityPageData(this.mActivity);
        addGlobalPageLayerList();
        if (this.mIsTabModel.get()) {
            return;
        }
        createXView2();
        startPopStrategy();
        LayerTypeManager layerTypeManager = this.mLayerTypeManager;
        if (layerTypeManager != null) {
            layerTypeManager.onLayerResume();
        }
    }

    @Override // com.jingdong.common.XView2.IActivityLifeCallBack
    public void onStop(Activity activity) {
    }

    public void onXViewFragmentPaused(@NonNull Fragment fragment) {
        if (fragment != null && this.mIsTabModel.get()) {
            ArrayList<BaseLayer> layersByTargetName = getLayersByTargetName(fragment);
            this.layers = layersByTargetName;
            if (XView2Utils.isListEmpty(layersByTargetName)) {
                return;
            }
            Iterator<BaseLayer> it = this.layers.iterator();
            while (it.hasNext()) {
                BaseLayer next = it.next();
                if (next != null && !XView2Utils.isLayerInVisible(next)) {
                    next.onPause();
                }
            }
        }
    }

    public void onXViewFragmentResumed(@NonNull Fragment fragment) {
        filterCurrentFragmentPageData(this.mActivity, fragment);
        addGlobalPageLayerList();
        if (this.mIsTabModel.get()) {
            if ((fragment == null || TextUtils.isEmpty(fragment.getClass().getName()) || !this.mFragmentArray.contains(fragment.getClass().getName())) && !XView2Utils.isHitH5Fragment(this.mTargetName, fragment)) {
                return;
            }
            this.mCurrentFragment = new SoftReference<>(fragment);
            createXView2();
            startPopStrategy();
            ArrayList<BaseLayer> layersByTargetName = getLayersByTargetName(fragment);
            if (XView2Utils.isListEmpty(layersByTargetName)) {
                return;
            }
            Iterator<BaseLayer> it = layersByTargetName.iterator();
            while (it.hasNext()) {
                BaseLayer next = it.next();
                if (next != null && !XView2Utils.isLayerInVisible(next)) {
                    next.onResume();
                }
            }
        }
    }

    public void onXViewFragmentViewDestroyed(@NonNull Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if ((this.mFragmentArray.contains(fragment.getClass().getName()) || XView2Utils.isHitH5Fragment(this.mTargetName, fragment)) && this.mIsTabModel.get()) {
            ArrayList<BaseLayer> layersByTargetName = getLayersByTargetName(fragment);
            if (XView2Utils.isListEmpty(layersByTargetName)) {
                return;
            }
            Iterator<BaseLayer> it = layersByTargetName.iterator();
            while (it.hasNext()) {
                BaseLayer next = it.next();
                if (next != null && (next.isFullScreen() || next.isJumpClose())) {
                    next.closeLayer(next.getLayerId(), 8);
                }
            }
            this.mCurrentFragment = null;
        }
    }

    public void startImmediatelyPopStrategy() {
        startXView2Immediately();
    }

    public void startNeedScreenShotStrategyPop() {
        if (this.mPopStrategy == null) {
            PopStrategyManager popStrategyManager = new PopStrategyManager();
            this.mPopStrategy = popStrategyManager;
            popStrategyManager.buildPopStrategy(this.mTargetsEntity, this.mActivity, this);
        } else if (this.mIsTabModel.get()) {
            this.mPopStrategy.upDateTarget(this.mTargetsEntity);
        }
        this.mPopStrategy.reset();
        this.mPopStrategy.start();
    }

    @Override // com.jingdong.common.XView2.IXView2
    public void startPopStrategy() {
        if (this.mIsImmediatelyPop.get()) {
            this.mIsImmediatelyPop.set(false);
            startImmediatelyPopStrategy();
        }
        if (this.mIsNeedScreenShot.get()) {
            this.mIsNeedScreenShot.set(false);
            startNeedScreenShotStrategyPop();
        }
    }

    @Override // com.jingdong.common.XView2.IXView2
    public void startXView2() {
        if (isTriggerTargetPop()) {
            displayXView2();
        }
    }

    @Override // com.jingdong.common.XView2.IXView2
    public void startXView2ById(XViewConfigEntity.LayersEntity layersEntity, JSONObject jSONObject) {
        if (layersEntity == null || isChannelNotMatch(layersEntity, jSONObject)) {
            return;
        }
        createXView2ById(layersEntity, jSONObject);
        displayXView2ById(layersEntity);
    }

    @Override // com.jingdong.common.XView2.IXView2
    public void startXView2Immediately() {
        if (isTriggerTargetPop()) {
            displayXView2Immediately();
        }
    }

    public void startXView2LayerScrolling(JSONObject jSONObject) {
        XView2Delegate xView2Delegate;
        if (jSONObject == null) {
            return;
        }
        try {
            String optString = jSONObject.optString(XView2Constants.LAYER_ID);
            if (TextUtils.isEmpty(optString) || (xView2Delegate = this.mXViewDelegate) == null) {
                return;
            }
            xView2Delegate.startXView2LayerScrolling(optString);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void stopXView2LayerScrolling(JSONObject jSONObject) {
        XView2Delegate xView2Delegate;
        if (jSONObject == null) {
            return;
        }
        try {
            String optString = jSONObject.optString(XView2Constants.LAYER_ID);
            if (TextUtils.isEmpty(optString) || (xView2Delegate = this.mXViewDelegate) == null) {
                return;
            }
            xView2Delegate.stopXView2LayerScrolling(optString);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
