package com.jingdong.common.XView2;

import android.app.Activity;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.jd.dynamic.DYConstants;
import com.jd.hybrid.downloader.m.b;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.common.XView2LayerObservableManager;
import com.jingdong.common.XView2.entity.PreDownLoadResourceEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.event.XView2DispatchEventManager;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.IReLoadLayer;
import com.jingdong.common.XView2.layer.LayerTypeManager;
import com.jingdong.common.XView2.layer.flexcube.FlexCubeLayer;
import com.jingdong.common.XView2.layer.flexcube.view.XViewWebView;
import com.jingdong.common.XView2.page.PageManager;
import com.jingdong.common.XView2.strategy.downloader.XViewCache;
import com.jingdong.common.XView2.strategy.preDownLoadManager.PreDownLoadManager;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.XView2VideoDownloadCommonUtil;
import com.jingdong.common.XView2.utils.XViewMtaUtil;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.jdsdk.utils.Md5Encrypt;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class BaseXView2 {
    protected SoftReference<Activity> mActivity;
    protected SoftReference<Fragment> mCurrentFragment;
    public LayerTypeManager mLayerTypeManager;
    protected String mTargetName;
    protected int mTargetType;
    protected XViewConfigEntity.TargetsEntity mTargetsEntity;
    protected XView2DispatchEventManager mXView2DispatchEventManager;
    public XViewConfigEntity mXViewConfigEntity;
    public ArrayList<XViewConfigEntity.LayersEntity> mLayersEntityList = new ArrayList<>();
    public AtomicBoolean mIsTabModel = new AtomicBoolean(false);
    public AtomicBoolean mIsHasGlobalModel = new AtomicBoolean(false);
    public CopyOnWriteArrayList<String> mFragmentArray = new CopyOnWriteArrayList<>();
    public XView2Delegate mXViewDelegate = new XView2Delegate();
    public ArrayList<XViewConfigEntity.LayersEntity> mGlobalLayersEntityList = new ArrayList<>();
    Comparator<XViewConfigEntity.LayersEntity> comparator = new Comparator<XViewConfigEntity.LayersEntity>() { // from class: com.jingdong.common.XView2.BaseXView2.2
        {
            BaseXView2.this = this;
        }

        @Override // java.util.Comparator
        public int compare(XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.LayersEntity layersEntity2) {
            if (layersEntity != null && layersEntity2 != null) {
                int i2 = layersEntity.priority;
                int i3 = layersEntity2.priority;
                if (i2 < i3) {
                    return 1;
                }
                if (i2 - i3 != 0 && i2 > i3) {
                    return -1;
                }
            }
            return 0;
        }
    };

    public void addGlobalPageLayerList() {
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && this.mIsHasGlobalModel.get() && !isHasAddGlobalPageLayerList() && !XView2Utils.isListEmpty(this.mXViewConfigEntity.targets)) {
            Iterator<XViewConfigEntity.TargetsEntity> it = this.mXViewConfigEntity.targets.iterator();
            while (it.hasNext()) {
                XViewConfigEntity.TargetsEntity next = it.next();
                if (XView2Constants.JDGLOBAL_WINDOWPAGE.equals(next.targetName) && !XView2Utils.isListEmpty(next.layers)) {
                    this.mLayersEntityList.addAll(next.layers);
                }
            }
        }
    }

    public boolean canLayerCreatedByPopType(XViewConfigEntity.LayersEntity layersEntity) {
        XView2Delegate xView2Delegate = this.mXViewDelegate;
        if (xView2Delegate == null) {
            return false;
        }
        return xView2Delegate.canLayerCreatedByPopType(layersEntity);
    }

    public void configXView(XViewConfigEntity xViewConfigEntity) {
        this.mXViewConfigEntity = xViewConfigEntity;
    }

    /* JADX WARN: Removed duplicated region for block: B:176:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0094 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x002f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void filterCurrentActivityPageData(SoftReference<Activity> softReference) {
        String str;
        String str2;
        String str3;
        if (softReference == null || softReference.get() == null || this.mXViewConfigEntity == null) {
            return;
        }
        this.mIsTabModel.set(false);
        XViewConfigEntity xViewConfigEntity = this.mXViewConfigEntity;
        if (xViewConfigEntity == null || XView2Utils.isListEmpty(xViewConfigEntity.targets)) {
            return;
        }
        handleGlobalPageLayerList();
        Iterator<XViewConfigEntity.TargetsEntity> it = this.mXViewConfigEntity.targets.iterator();
        boolean z = false;
        while (it.hasNext()) {
            XViewConfigEntity.TargetsEntity next = it.next();
            String str4 = next.targetName;
            if (!TextUtils.isEmpty(str4)) {
                try {
                    JSONObject jSONObject = new JSONObject(str4);
                    if (jSONObject.length() > 0) {
                        str = jSONObject.optString("activity");
                        try {
                            if (jSONObject.has(XView2Constants.FRAGMENT)) {
                                str3 = jSONObject.optString(XView2Constants.FRAGMENT);
                                str2 = str;
                            } else {
                                str2 = str;
                                str3 = null;
                            }
                            z = true;
                        } catch (JSONException unused) {
                            str2 = str;
                            str3 = null;
                            z = false;
                            if (z) {
                            }
                            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "targetName " + str4);
                            if (!isValidTargetName(softReference, str4, null)) {
                            }
                        }
                    } else {
                        str2 = null;
                        str3 = null;
                    }
                } catch (JSONException unused2) {
                    str = null;
                }
                if (z) {
                    str4 = str2;
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "targetName " + str4);
                if (!isValidTargetName(softReference, str4, null)) {
                    if (TextUtils.isEmpty(str3) && !XView2Utils.isH5Page(str4, softReference, null)) {
                        this.mFragmentArray.clear();
                        this.mTargetsEntity = next;
                        this.mTargetType = next.targetType;
                        if (!XView2Utils.isListEmpty(next.layers) && this.mIsHasGlobalModel.get()) {
                            ArrayList<XViewConfigEntity.LayersEntity> arrayList = new ArrayList<>();
                            if (!XView2Utils.isListEmpty(next.layers)) {
                                arrayList.addAll(next.layers);
                            }
                            this.mLayersEntityList = arrayList;
                        } else {
                            this.mLayersEntityList = next.layers;
                        }
                        this.mTargetName = str4;
                        return;
                    }
                    this.mIsTabModel.set(true);
                    return;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:256:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x007a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0033 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void filterCurrentFragmentPageData(SoftReference<Activity> softReference, Fragment fragment) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (softReference == null || softReference.get() == null || this.mXViewConfigEntity == null) {
            return;
        }
        this.mFragmentArray.clear();
        this.mIsTabModel.set(false);
        XViewConfigEntity xViewConfigEntity = this.mXViewConfigEntity;
        if (xViewConfigEntity == null || XView2Utils.isListEmpty(xViewConfigEntity.targets)) {
            return;
        }
        handleGlobalPageLayerList();
        Iterator<XViewConfigEntity.TargetsEntity> it = this.mXViewConfigEntity.targets.iterator();
        boolean z = true;
        while (true) {
            String str6 = null;
            if (!it.hasNext()) {
                break;
            }
            String str7 = it.next().targetName;
            if (!TextUtils.isEmpty(str7)) {
                try {
                    JSONObject jSONObject = new JSONObject(str7);
                    if (jSONObject.length() > 0) {
                        str4 = jSONObject.optString("activity");
                        try {
                            str5 = jSONObject.has(XView2Constants.FRAGMENT) ? jSONObject.optString(XView2Constants.FRAGMENT) : null;
                            str6 = str4;
                            z = true;
                        } catch (JSONException unused) {
                            str5 = null;
                            str6 = str4;
                            z = false;
                            if (z) {
                            }
                            if (isValidTargetName(softReference, str7, fragment)) {
                            }
                        }
                    } else {
                        str5 = null;
                    }
                } catch (JSONException unused2) {
                    str4 = null;
                }
                if (z) {
                    str7 = str6;
                }
                if (isValidTargetName(softReference, str7, fragment)) {
                    if (!TextUtils.isEmpty(str5)) {
                        this.mFragmentArray.add(str5);
                        this.mIsTabModel.set(true);
                    }
                    if (XView2Utils.isHitH5Fragment(str7, fragment)) {
                        this.mIsTabModel.set(true);
                    }
                }
            }
        }
        Iterator<XViewConfigEntity.TargetsEntity> it2 = this.mXViewConfigEntity.targets.iterator();
        boolean z2 = false;
        while (it2.hasNext()) {
            XViewConfigEntity.TargetsEntity next = it2.next();
            String str8 = next.targetName;
            if (!TextUtils.isEmpty(str8)) {
                try {
                    JSONObject jSONObject2 = new JSONObject(str8);
                    if (jSONObject2.length() > 0) {
                        str = jSONObject2.optString("activity");
                        try {
                            if (jSONObject2.has(XView2Constants.FRAGMENT)) {
                                str3 = jSONObject2.optString(XView2Constants.FRAGMENT);
                                str2 = str;
                            } else {
                                str2 = str;
                                str3 = null;
                            }
                            z2 = true;
                        } catch (JSONException unused3) {
                            str2 = str;
                            str3 = null;
                            z2 = false;
                            if (z2) {
                            }
                            if (isValidTargetName(softReference, str8, fragment)) {
                                this.mTargetName = str8;
                                this.mTargetsEntity = next;
                                if (XView2Utils.isListEmpty(next.layers)) {
                                }
                                this.mLayersEntityList = next.layers;
                                return;
                            }
                            continue;
                        }
                    } else {
                        str2 = null;
                        str3 = null;
                    }
                } catch (JSONException unused4) {
                    str = null;
                }
                if (z2) {
                    str8 = str2;
                }
                if (isValidTargetName(softReference, str8, fragment) && ((fragment != null && !TextUtils.isEmpty(str3) && str3.equals(fragment.getClass().getName())) || XView2Utils.isHitH5Fragment(str8, fragment))) {
                    this.mTargetName = str8;
                    this.mTargetsEntity = next;
                    if (XView2Utils.isListEmpty(next.layers) && this.mIsHasGlobalModel.get()) {
                        ArrayList<XViewConfigEntity.LayersEntity> arrayList = new ArrayList<>();
                        if (!XView2Utils.isListEmpty(next.layers)) {
                            arrayList.addAll(next.layers);
                        }
                        this.mLayersEntityList = arrayList;
                        return;
                    }
                    this.mLayersEntityList = next.layers;
                    return;
                }
            }
        }
    }

    public CopyOnWriteArrayList<String> getCurrentFragmentArray() {
        return this.mFragmentArray;
    }

    public JSONObject getLayerBasicInfoByLayerId(String str) {
        XView2Delegate xView2Delegate = this.mXViewDelegate;
        if (xView2Delegate == null) {
            return null;
        }
        return xView2Delegate.getLayerBasicInfoByLayerId(str);
    }

    public XViewConfigEntity.LayersEntity getMatchEntityByBusinessID(JSONObject jSONObject) {
        JSONArray popAbleLayersByBuzID;
        if (jSONObject != null && this.mXViewDelegate != null && (popAbleLayersByBuzID = getPopAbleLayersByBuzID(jSONObject)) != null && popAbleLayersByBuzID.length() != 0) {
            try {
                JSONObject jSONObject2 = (JSONObject) popAbleLayersByBuzID.get(0);
                if (jSONObject2 != null && !TextUtils.isEmpty(jSONObject2.optString("layerID"))) {
                    return this.mXViewDelegate.getMatchEntityById(jSONObject2.optString("layerID"));
                }
                return null;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public XViewConfigEntity.LayersEntity getPopAbleEntity(JSONObject jSONObject) {
        if (jSONObject == null || this.mXViewDelegate == null) {
            return null;
        }
        if (jSONObject.has(XView2Constants.LAYER_ID) && !TextUtils.isEmpty(jSONObject.optString(XView2Constants.LAYER_ID))) {
            return this.mXViewDelegate.getMatchEntityById(jSONObject.optString(XView2Constants.LAYER_ID));
        } else if (jSONObject.has(XView2Constants.LOGIC_KEY) && !TextUtils.isEmpty(jSONObject.optString(XView2Constants.LOGIC_KEY))) {
            return this.mXViewDelegate.getMatchEntityByLogicKey(jSONObject.optString(XView2Constants.LOGIC_KEY));
        } else if (!jSONObject.has(XView2Constants.BUZID) || TextUtils.isEmpty(jSONObject.optString(XView2Constants.BUZID))) {
            return null;
        } else {
            return getMatchEntityByBusinessID(jSONObject);
        }
    }

    public JSONArray getPopAbleLayersByBuzID(JSONObject jSONObject) {
        if (jSONObject == null || XView2Utils.isListEmpty(this.mLayersEntityList)) {
            return null;
        }
        String optString = jSONObject.optString(XView2Constants.BUZID);
        boolean optBoolean = jSONObject.optBoolean(XView2Constants.IGNORE_PRIORITY);
        JSONArray jSONArray = new JSONArray();
        Collections.sort(this.mLayersEntityList, this.comparator);
        Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity next = it.next();
            if (!TextUtils.isEmpty(optString) && optString.equals(next.businessID) && getXViewPopStatus(next.layerId, optBoolean)) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("layerID", next.layerId);
                    jSONObject2.put(XView2Constants.LOGIC_KEY, next.logicKey);
                    jSONArray.put(jSONObject2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONArray;
    }

    public boolean getXViewPopStatus(String str, boolean z) {
        XView2Delegate xView2Delegate;
        return (TextUtils.isEmpty(str) || (xView2Delegate = this.mXViewDelegate) == null || !isLayerCanPop(xView2Delegate.getMatchEntityById(str), z, null)) ? false : true;
    }

    public void handleGlobalPageLayerList() {
        this.mIsHasGlobalModel.set(false);
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_GLOBALMODEL, false) && !XView2Utils.isListEmpty(this.mXViewConfigEntity.targets)) {
            Iterator<XViewConfigEntity.TargetsEntity> it = this.mXViewConfigEntity.targets.iterator();
            while (it.hasNext()) {
                XViewConfigEntity.TargetsEntity next = it.next();
                if (XView2Constants.JDGLOBAL_WINDOWPAGE.equals(next.targetName) && !XView2Utils.isListEmpty(next.layers)) {
                    Iterator<XViewConfigEntity.LayersEntity> it2 = next.layers.iterator();
                    while (it2.hasNext()) {
                        it2.next().isGlobalWindow = true;
                    }
                    this.mIsHasGlobalModel.set(true);
                }
            }
        }
    }

    public boolean isCanPopByParams(JSONObject jSONObject, IReLoadLayer iReLoadLayer) {
        if (jSONObject == null || this.mXViewDelegate == null) {
            return false;
        }
        boolean optBoolean = jSONObject.optBoolean(XView2Constants.IGNORE_PRIORITY, false);
        if (jSONObject.has(XView2Constants.LAYER_ID) && !TextUtils.isEmpty(jSONObject.optString(XView2Constants.LAYER_ID))) {
            return isLayerCanPop(this.mXViewDelegate.getMatchEntityById(jSONObject.optString(XView2Constants.LAYER_ID)), optBoolean, iReLoadLayer);
        } else if (!jSONObject.has(XView2Constants.LOGIC_KEY) || TextUtils.isEmpty(jSONObject.optString(XView2Constants.LOGIC_KEY))) {
            return !jSONObject.has(XView2Constants.BUZID) || TextUtils.isEmpty(jSONObject.optString(XView2Constants.BUZID)) || isLayerCanPop(getMatchEntityByBusinessID(jSONObject), optBoolean, iReLoadLayer);
        } else {
            return isLayerCanPop(this.mXViewDelegate.getMatchEntityByLogicKey(jSONObject.optString(XView2Constants.LOGIC_KEY)), optBoolean, iReLoadLayer);
        }
    }

    public boolean isChannelNotMatch(XViewConfigEntity.LayersEntity layersEntity, JSONObject jSONObject) {
        XViewConfigEntity.RuleEntity ruleEntity;
        if (layersEntity == null || (ruleEntity = layersEntity.rule) == null || TextUtils.isEmpty(ruleEntity.channelWhitelist)) {
            return false;
        }
        String optString = jSONObject != null ? jSONObject.optString("channel") : null;
        if (TextUtils.isEmpty(optString)) {
            return true;
        }
        for (String str : layersEntity.rule.channelWhitelist.split(DYConstants.DY_REGEX_COMMA)) {
            if (str.equals(optString)) {
                layersEntity.channel = optString;
                return false;
            }
        }
        return true;
    }

    public boolean isControlImgLocalSuccess(ArrayList<XViewConfigEntity.ControlEntity> arrayList) {
        Iterator<XViewConfigEntity.ControlEntity> it = arrayList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.ControlEntity next = it.next();
            if (!"1".equals(next.type) && XView2Utils.isConvertBool(next.mustLocal) && !isImageLocalSuccess(next.img)) {
                return false;
            }
        }
        return true;
    }

    public boolean isHasAddGlobalPageLayerList() {
        if (XView2Utils.isListEmpty(this.mLayersEntityList)) {
            return false;
        }
        Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
        while (it.hasNext()) {
            if (it.next().isGlobalWindow) {
                return true;
            }
        }
        return false;
    }

    public boolean isHasPopUpOnScreen(XViewConfigEntity.LayersEntity layersEntity) {
        if (!XView2Utils.isGlobalLayer(layersEntity) && this.mXViewDelegate.getRealPopLayerEntity(layersEntity) == null) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5df2\u7ecf\u5b58\u5728\u540c\u7ec4\u53f7\u5f39\u7a97");
            return false;
        }
        return true;
    }

    public boolean isImageLayerLocalSuccess(XViewConfigEntity.LayersEntity layersEntity) {
        XViewConfigEntity.RenderDataEntity renderDataEntity;
        if (layersEntity == null || (renderDataEntity = layersEntity.renderData) == null) {
            return false;
        }
        if (XView2Utils.isConvertBool(renderDataEntity.mustLocal)) {
            return isImageLocalSuccess(layersEntity.renderData.url);
        }
        return true;
    }

    public boolean isImageLocalSuccess(String str) {
        File imageDiskCacheFile;
        return !TextUtils.isEmpty(str) && (imageDiskCacheFile = JDImageUtils.getImageDiskCacheFile(str)) != null && imageDiskCacheFile.exists() && imageDiskCacheFile.length() > 0;
    }

    public boolean isLayerCanPop(XViewConfigEntity.LayersEntity layersEntity, boolean z, IReLoadLayer iReLoadLayer) {
        if (layersEntity == null) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "entity \u4e3anull");
            return false;
        } else if (isNeedCheckPageTimes(layersEntity)) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5f39\u7a97\u5df2\u7ecf\u5f39\u51fa\u8fc7\u4e00\u6b21\uff0c\u88ab\u5173\u95ed\u4e86");
            return false;
        } else if (!isLayerCanPopByMutexGroupID(layersEntity, z)) {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u4e0d\u6ee1\u8db3\u4e92\u65a5\u903b\u8f91,\u5f39\u7a97\u5931\u8d25");
            return false;
        } else if (isLayerCanPopByConfig(layersEntity, iReLoadLayer)) {
            return true;
        } else {
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u4e0d\u6ee1\u8db3\u5f39\u6846\u6761\u4ef6,\u542f\u52a8XView\u5931\u8d25\u5904\u7406");
            return false;
        }
    }

    public boolean isLayerCanPopByBusiness(XViewConfigEntity.LayersEntity layersEntity) {
        JSONObject layerBasicInfoByLayerId;
        if (layersEntity == null || TextUtils.isEmpty(layersEntity.layerId) || (layerBasicInfoByLayerId = this.mXViewDelegate.getLayerBasicInfoByLayerId(layersEntity.layerId)) == null) {
            return false;
        }
        return XView2LayerObservableManager.getManager().notifyLayerCanPopStatus(layersEntity.layerId, layerBasicInfoByLayerId);
    }

    public boolean isLayerCanPopByConfig(XViewConfigEntity.LayersEntity layersEntity, IReLoadLayer iReLoadLayer) {
        if (layersEntity != null && isTriggerLayerPop(layersEntity) && isHasPopUpOnScreen(layersEntity)) {
            if (!isLayerCanPopByBusiness(layersEntity)) {
                XView2Utils.reportXView2ErrorWithInvokeSwitch("business", "NXViewException", "", "", layersEntity.layerId, layersEntity.name, "");
                return false;
            } else if (isNeedLoadRequiredBefore(layersEntity, iReLoadLayer)) {
                return true;
            } else {
                XViewMtaUtil.popClickMta(this.mActivity.get(), layersEntity, this.mTargetsEntity, "1");
                return false;
            }
        }
        return false;
    }

    public boolean isLayerCanPopByMutexGroupID(XViewConfigEntity.LayersEntity layersEntity, boolean z) {
        if (layersEntity == null || TextUtils.isEmpty(layersEntity.layerId)) {
            return false;
        }
        if (XView2Utils.isGlobalLayer(layersEntity)) {
            return true;
        }
        if (XView2Utils.isListEmpty(this.mLayersEntityList)) {
            return false;
        }
        if (layersEntity.layout != null) {
            Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
            while (it.hasNext()) {
                XViewConfigEntity.LayersEntity next = it.next();
                if (!TextUtils.equals(layersEntity.layerId, next.layerId) && !XView2Utils.isGlobalLayer(next) && layersEntity.mutexGroupID == next.mutexGroupID && !this.mXViewDelegate.isExclusiveCanPopUp(layersEntity, next, z)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isLayerNeedReCreateChecked(BaseLayer baseLayer) {
        return (baseLayer == null || baseLayer.isRenderSuccess()) ? false : true;
    }

    public boolean isNeedCheckPageTimes(XViewConfigEntity.LayersEntity layersEntity) {
        LayerTypeManager layerTypeManager;
        XViewWebView currentFlexWebView;
        XView xView;
        if (layersEntity == null) {
            return false;
        }
        XViewConfigEntity.RuleEntity ruleEntity = layersEntity.rule;
        if ((ruleEntity == null || ruleEntity.pageMultiTimes != 1) && (layerTypeManager = this.mLayerTypeManager) != null && layerTypeManager.getLayoutControls() != null && this.mLayerTypeManager.getLayoutControls().size() != 0) {
            Iterator<Map.Entry<String, BaseLayer>> it = this.mLayerTypeManager.getLayoutControls().entrySet().iterator();
            while (it.hasNext()) {
                BaseLayer value = it.next().getValue();
                if (value != null && TextUtils.equals(layersEntity.layerId, value.getLayerId())) {
                    if (XView2Utils.canWebViewLayerPreLoaded(layersEntity)) {
                        if ((value instanceof FlexCubeLayer) && (currentFlexWebView = ((FlexCubeLayer) value).getCurrentFlexWebView()) != null && (xView = currentFlexWebView.xView) != null && xView.isXViewShow()) {
                            return true;
                        }
                    } else if (value.isRenderSuccess() && !XView2Utils.isLayerInVisible(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isNeedLoadRequiredBefore(XViewConfigEntity.LayersEntity layersEntity, IReLoadLayer iReLoadLayer) {
        XViewConfigEntity.RuleEntity ruleEntity;
        if (layersEntity == null) {
            return false;
        }
        if (layersEntity.renderType == 6 && !XView2Utils.isListEmpty(layersEntity.preDownLoadList) && SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PREDOWNLOAD, false)) {
            Iterator<PreDownLoadResourceEntity> it = layersEntity.preDownLoadList.iterator();
            while (it.hasNext()) {
                PreDownLoadResourceEntity next = it.next();
                if (XView2Utils.isConvertBool(next.popAfterDownload)) {
                    String finalLayerId = XView2Utils.getFinalLayerId(layersEntity.layerId);
                    b files = XViewCache.getInstance().getFiles(finalLayerId + next.url);
                    if (files == null || TextUtils.isEmpty(files.getFilePath())) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u9884\u52a0\u8f7d\u6210\u529f\u4e0d\u6210\u529f" + finalLayerId);
                        SoftReference<Activity> softReference = this.mActivity;
                        if (softReference != null) {
                            XViewMtaUtil.sendLayerCloseMta(softReference.get(), layersEntity, this.mTargetsEntity, 14);
                        }
                        XView2Utils.reportXView2ErrorWithSwitch("preloadFail", "NXViewException", next.url, "\u9700\u8981\u9884\u52a0\u8f7d\u8d44\u6e90\u672a\u51c6\u5907\u597d", finalLayerId, layersEntity.name, "");
                        reLoadLayer(layersEntity, iReLoadLayer);
                        return false;
                    }
                }
            }
        } else if (layersEntity.renderData != null && (ruleEntity = layersEntity.rule) != null && XView2Utils.isConvertBool(ruleEntity.loadRequiredBefore)) {
            boolean isControlImgLocalSuccess = isControlImgLocalSuccess(layersEntity.controls);
            int i2 = layersEntity.renderType;
            if (i2 == 3) {
                if (isVideoLocalSuccess(layersEntity) && isControlImgLocalSuccess) {
                    return true;
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u89c6\u9891\u9884\u52a0\u8f7d\u5931\u8d25");
                XView2Utils.reportXView2Error("preFailOld", "NXViewException", layersEntity.renderData.url, "\u89c6\u9891\u9884\u52a0\u8f7d\u5931\u8d25", layersEntity.layerId, layersEntity.name, "");
                return false;
            } else if (i2 == 1) {
                if (isImageLayerLocalSuccess(layersEntity) && isControlImgLocalSuccess) {
                    return true;
                }
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u56fe\u7247\u9884\u52a0\u8f7d\u5931\u8d25");
                XView2Utils.reportXView2Error("preFailOld", "NXViewException", layersEntity.renderData.url, "\u56fe\u7247\u9884\u52a0\u8f7d\u5931\u8d25", layersEntity.layerId, layersEntity.name, "");
                return false;
            }
        }
        return true;
    }

    public boolean isTriggerLayerPop(XViewConfigEntity.LayersEntity layersEntity) {
        XViewConfigEntity.RuleEntity ruleEntity;
        if (layersEntity == null || (ruleEntity = layersEntity.rule) == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = ruleEntity.dayTimes;
        int i3 = ruleEntity.times;
        int showedTimes = PageManager.getInstance().getShowedTimes(layersEntity.layerId);
        if (i3 > 0 && showedTimes >= i3) {
            XView2Utils.reportXView2ErrorWithInvokeSwitch("runOut", "NXViewException", "", "\u5f39\u7a97\u603b\u6b21\u6570\u5df2\u7ecf\u5f39\u5b8c", layersEntity.layerId, layersEntity.name, "");
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5f39\u7a97\u603b\u6b21\u6570\u5df2\u7ecf\u5f39\u5b8c");
            return false;
        }
        int todayTimes = PageManager.getInstance().getTodayTimes(layersEntity.layerId);
        if (i2 > 0 && todayTimes >= i2) {
            XView2Utils.reportXView2ErrorWithInvokeSwitch("runOut", "NXViewException", "", "\u4eca\u5929\u7684\u5f39\u7a97\u6b21\u6570\u5df2\u7ecf\u5f39\u5b8c", layersEntity.layerId, layersEntity.name, "");
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u4eca\u5929\u7684\u5f39\u7a97\u6b21\u6570\u5df2\u7ecf\u5f39\u5b8c");
            return false;
        } else if (currentTimeMillis < ruleEntity.beginTime || currentTimeMillis > ruleEntity.endTime) {
            XView2Utils.reportXView2ErrorWithInvokeSwitch("timeNot", "NXViewException", "", "\u5f39\u7a97\u4e0d\u5728\u6709\u6548\u65f6\u95f4\u5185", layersEntity.layerId, layersEntity.name, "");
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5f39\u7a97\u4e0d\u5728\u6709\u6548\u65f6\u95f4\u5185");
            return false;
        } else {
            return true;
        }
    }

    public boolean isTriggerTargetPop() {
        SoftReference<Activity> softReference = this.mActivity;
        return (softReference == null || softReference.get() == null || XView2Utils.isListEmpty(this.mLayersEntityList) || this.mXViewConfigEntity == null || TextUtils.isEmpty(this.mTargetName)) ? false : true;
    }

    public boolean isValidTargetName(SoftReference<Activity> softReference, String str, Fragment fragment) {
        return (TextUtils.isEmpty(str) || softReference == null || softReference.get() == null || (!str.equals(softReference.get().getClass().getName()) && !XView2Utils.isH5Page(str, softReference, fragment) && !XView2Utils.isHitRNPage(str, softReference))) ? false : true;
    }

    public boolean isVideoLocalSuccess(XViewConfigEntity.LayersEntity layersEntity) {
        if (layersEntity == null) {
            return false;
        }
        if (XView2Utils.isConvertBool(layersEntity.renderData.mustLocal)) {
            String md5 = Md5Encrypt.md5(layersEntity.layerId + layersEntity.renderData.url);
            String videoPathNameById = XView2VideoDownloadCommonUtil.getVideoPathNameById(PreDownLoadManager.VIDEO_SKU_BG_DIR, PreDownLoadManager.VIDEO_SKU_SUFFIX, md5);
            if (TextUtils.isEmpty(videoPathNameById) || TextUtils.isEmpty(PreDownLoadManager.isLegalFile(new File(videoPathNameById), md5, PreDownLoadManager.VIDEO_SKU_PATH, PreDownLoadManager.VIDEO_SKU_SIZE, PreDownLoadManager.VIDEO_ID, PreDownLoadManager.VIDEO_URL))) {
                return false;
            }
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u89c6\u9891\u8bf7\u6c42\u7f13\u5b58\u6210\u529f");
            return true;
        }
        return true;
    }

    public void reLoadLayer(final XViewConfigEntity.LayersEntity layersEntity, final IReLoadLayer iReLoadLayer) {
        SoftReference<Activity> softReference;
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5f00\u59cb\u5c1d\u8bd5\u91cd\u65b0\u52a0\u8f7d\u52a0\u8f7d");
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.LAYER_RELOAD, false) && (softReference = this.mActivity) != null && (softReference.get() instanceof BaseActivity) && iReLoadLayer != null && layersEntity != null && layersEntity.mHasReloadCount == 0) {
            ((BaseActivity) this.mActivity.get()).post(new Runnable() { // from class: com.jingdong.common.XView2.BaseXView2.1
                {
                    BaseXView2.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u5f00\u59cb\u5c1d\u8bd5\u91cd\u65b0\u52a0\u8f7d\u52a0\u8f7d1");
                    IReLoadLayer iReLoadLayer2 = iReLoadLayer;
                    if (iReLoadLayer2 != null) {
                        iReLoadLayer2.reloadLayer(layersEntity);
                    }
                }
            }, 2000);
        }
    }

    public void setXViewVisible(JSONObject jSONObject) {
        XView2Delegate xView2Delegate = this.mXViewDelegate;
        if (xView2Delegate == null) {
            return;
        }
        xView2Delegate.setXViewVisible(jSONObject);
    }

    public boolean getXViewPopStatus(JSONObject jSONObject) {
        return isCanPopByParams(jSONObject, null);
    }
}
