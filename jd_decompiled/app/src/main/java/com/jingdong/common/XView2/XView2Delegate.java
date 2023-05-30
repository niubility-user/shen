package com.jingdong.common.XView2;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.container.XView2Container;
import com.jingdong.common.XView2.entity.PreDownLoadResourceEntity;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.BaseLayer;
import com.jingdong.common.XView2.layer.LayerTypeManager;
import com.jingdong.common.XView2.page.PageManager;
import com.jingdong.common.XView2.strategy.downloader.XViewCache;
import com.jingdong.common.XView2.strategy.downloader.entity.XViewFileEntity;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.common.utils.SwitchQueryFetcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.ext.report.ReportConstant;

/* loaded from: classes5.dex */
public class XView2Delegate implements IXView2Delegate {
    Context mContext;
    public LayerTypeManager mLayerTypeManager;
    public ArrayList<XViewConfigEntity.LayersEntity> mLayersEntityList;
    XView2 mXView2;
    public XViewConfigEntity mXViewConfigEntity;
    public ReentrantReadWriteLock mLock = new ReentrantReadWriteLock();
    private AtomicBoolean mHasScrolled = new AtomicBoolean(false);
    private AtomicBoolean mControlVisible = new AtomicBoolean(false);
    boolean isNeedPreLoad = false;

    public boolean canLayerCreatedByPopType(XViewConfigEntity.LayersEntity layersEntity) {
        if (layersEntity == null) {
            return false;
        }
        return XView2Utils.isLayerEnterPop(layersEntity) || XView2Utils.isLayerEnterImmediatelyPop(layersEntity) || XView2Utils.canWebViewLayerPreLoaded(layersEntity);
    }

    public JSONObject getLayerBasicInfoByLayerId(String str) {
        JSONObject jSONObject = null;
        if (XView2Utils.isListEmpty(this.mLayersEntityList)) {
            return null;
        }
        Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity next = it.next();
            if (TextUtils.equals(str, next.layerId)) {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("businessID", next.businessID);
                    jSONObject.put(XView2Constants.LOGIC_KEY, next.logicKey);
                    jSONObject.put("name", next.name);
                    jSONObject.put(ReportConstant.PlayStatus.RENDER_TYPE, next.renderType);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONObject;
    }

    public ArrayList<XViewConfigEntity.LayersEntity> getMatchEntityByBusinessID(String str) {
        if (XView2Utils.isListEmpty(this.mLayersEntityList)) {
            return null;
        }
        ArrayList<XViewConfigEntity.LayersEntity> arrayList = new ArrayList<>();
        Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity next = it.next();
            if (next != null && TextUtils.equals(str, next.businessID)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public XViewConfigEntity.LayersEntity getMatchEntityById(String str) {
        if (XView2Utils.isListEmpty(this.mLayersEntityList)) {
            return null;
        }
        Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity next = it.next();
            if (next != null && TextUtils.equals(str, next.layerId)) {
                return next;
            }
        }
        return null;
    }

    public XViewConfigEntity.LayersEntity getMatchEntityByLogicKey(String str) {
        if (XView2Utils.isListEmpty(this.mLayersEntityList) || TextUtils.isEmpty(str)) {
            return null;
        }
        Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
        while (it.hasNext()) {
            XViewConfigEntity.LayersEntity next = it.next();
            if (next != null && !TextUtils.isEmpty(next.logicKey)) {
                List<String> asList = Arrays.asList(next.logicKey.split(DYConstants.DY_REGEX_COMMA));
                if (XView2Utils.isListEmpty(asList)) {
                    continue;
                } else {
                    for (String str2 : asList) {
                        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "logicKeyStr" + str2);
                        if (TextUtils.equals(str2.trim(), str.trim())) {
                            return next;
                        }
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x0048, code lost:
        if (isHasSameGroupID(r2.mXView2.getCurrentFragment().getView(), r3) != false) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jingdong.common.XView2.entity.XViewConfigEntity.LayersEntity getRealPopLayerEntity(com.jingdong.common.XView2.entity.XViewConfigEntity.LayersEntity r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 != 0) goto L4
            return r0
        L4:
            com.jingdong.common.XView2.XView2 r1 = r2.mXView2
            if (r1 == 0) goto L60
            android.content.Context r1 = r2.mContext
            if (r1 != 0) goto Ld
            goto L60
        Ld:
            java.util.concurrent.locks.ReentrantReadWriteLock r1 = r2.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r1.writeLock()
            r1.lock()
            android.content.Context r1 = r2.mContext     // Catch: java.lang.Throwable -> L55
            android.app.Activity r1 = (android.app.Activity) r1     // Catch: java.lang.Throwable -> L55
            android.view.Window r1 = r1.getWindow()     // Catch: java.lang.Throwable -> L55
            android.view.View r1 = r1.getDecorView()     // Catch: java.lang.Throwable -> L55
            boolean r1 = r2.isHasSameGroupID(r1, r3)     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L32
        L28:
            java.util.concurrent.locks.ReentrantReadWriteLock r3 = r2.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r3 = r3.writeLock()
            r3.unlock()
            return r0
        L32:
            com.jingdong.common.XView2.XView2 r1 = r2.mXView2     // Catch: java.lang.Throwable -> L55
            androidx.fragment.app.Fragment r1 = r1.getCurrentFragment()     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L4b
            com.jingdong.common.XView2.XView2 r1 = r2.mXView2     // Catch: java.lang.Throwable -> L55
            androidx.fragment.app.Fragment r1 = r1.getCurrentFragment()     // Catch: java.lang.Throwable -> L55
            android.view.View r1 = r1.getView()     // Catch: java.lang.Throwable -> L55
            boolean r1 = r2.isHasSameGroupID(r1, r3)     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L4b
            goto L28
        L4b:
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r2.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.unlock()
            return r3
        L55:
            r3 = move-exception
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r2.mLock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.unlock()
            throw r3
        L60:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.XView2Delegate.getRealPopLayerEntity(com.jingdong.common.XView2.entity.XViewConfigEntity$LayersEntity):com.jingdong.common.XView2.entity.XViewConfigEntity$LayersEntity");
    }

    public boolean isExclusiveCanPopUp(XViewConfigEntity.LayersEntity layersEntity, XViewConfigEntity.LayersEntity layersEntity2, boolean z) {
        XView2 xView2;
        if (layersEntity == null || layersEntity2 == null) {
            return false;
        }
        int i2 = layersEntity2.exclusiveType;
        if (i2 == 1) {
            if (PageManager.getInstance().getDayTimes(layersEntity2.layerId) > 0) {
                XView2Utils.reportXView2ErrorWithInvokeSwitch("exclusive", "NXViewException", "", "\u6309\u5929\u6392\u4ed6", layersEntity2.layerId, layersEntity2.name, "");
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u6309\u5929\u6392\u4ed6,\u5f39\u51fa\u6765\u8fc7\uff0c\u4f46\u8fd8\u6ca1\u5f39\u5b8c");
                return false;
            }
        } else if (i2 == 2 && PageManager.getInstance().getShowedTimes(layersEntity2.layerId) > 0) {
            XView2Utils.reportXView2ErrorWithInvokeSwitch("exclusive", "NXViewException", "", "\u6309\u6709\u6548\u671f\u6392\u4ed6", layersEntity2.layerId, layersEntity2.name, "");
            XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u6309\u6709\u6548\u671f\u6392\u4ed6,\u5f39\u51fa\u6765\u8fc7\uff0c\u4f46\u8fd8\u6ca1\u5f39\u5b8c");
            return false;
        }
        if (!z && layersEntity.priority < layersEntity2.priority && (xView2 = this.mXView2) != null) {
            if (xView2.isTriggerLayerPop(layersEntity2)) {
                XView2Utils.reportXView2ErrorWithInvokeSwitch(RemoteMessageConst.Notification.PRIORITY, "NXViewException", "", "", layersEntity2.layerId, layersEntity2.name, "");
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u6743\u9650,\u6bd4\u5bf9\u7684\u5f39\u7a97\u6b21\u6570\u8fd8\u80fd\u5f39\u51fa");
                return false;
            } else if (this.mXView2.isHasPopUpOnScreen(layersEntity2)) {
                XViewLogPrint.JDXLog.e(XView2Constants.TAG, "\u6743\u9650,\u6bd4\u5bf9\u7684\u5f39\u7a97\u5df2\u7ecf\u663e\u793a\u518d\u5c4f\u5e55\u4e0a\u4e86");
                return false;
            }
        }
        return true;
    }

    public boolean isHasSameGroupID(View view, XViewConfigEntity.LayersEntity layersEntity) {
        XView2Container xView2Container;
        XViewConfigEntity.LayersEntity layersEntity2;
        if (layersEntity != null && (view instanceof ViewGroup)) {
            int i2 = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i2 >= viewGroup.getChildCount()) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i2);
                if ((childAt instanceof XView2Container) && (((layersEntity2 = (xView2Container = (XView2Container) childAt).mLayersEntity) == null || !layersEntity2.isGlobalWindow) && layersEntity.mutexGroupID == xView2Container.getMutexGroupID() && xView2Container.getVisibility() == 0)) {
                    return true;
                }
                i2++;
            }
        }
        return false;
    }

    public void preLoadLayerResource() {
        int i2;
        int i3;
        if (SwitchQueryFetcher.getSwitchBooleanValue(XView2Constants.XVIEW_PREDOWNLOAD, false) && !XView2Utils.isListEmpty(this.mLayersEntityList)) {
            Iterator<XViewConfigEntity.LayersEntity> it = this.mLayersEntityList.iterator();
            while (it.hasNext()) {
                XViewConfigEntity.LayersEntity next = it.next();
                this.isNeedPreLoad = false;
                if (next.renderType != 6 || XView2Utils.isListEmpty(next.preDownLoadList)) {
                    i2 = 0;
                } else {
                    Iterator<PreDownLoadResourceEntity> it2 = next.preDownLoadList.iterator();
                    i2 = 0;
                    while (it2.hasNext()) {
                        PreDownLoadResourceEntity next2 = it2.next();
                        if ((XView2Utils.isConvertBool(next2.canPreload) || XView2Utils.isConvertBool(next2.popAfterDownload)) && ((i3 = next2.type) == 1 || i3 == 2 || i3 == 4)) {
                            String str = next2.layerId;
                            String str2 = next2.url;
                            String finalLayerId = XView2Utils.getFinalLayerId(str);
                            if (XViewCache.getInstance().getFiles(finalLayerId + str2) == null) {
                                XViewFileEntity xViewFileEntity = new XViewFileEntity();
                                xViewFileEntity.url = next2.url;
                                xViewFileEntity.project_priority = next2.priority;
                                xViewFileEntity.id = finalLayerId + str2;
                                xViewFileEntity.expiredTime = next.rule.endTime;
                                xViewFileEntity.layerId = finalLayerId;
                                xViewFileEntity.name = next.name;
                                xViewFileEntity.targetId = next.targetId;
                                XViewCache.getInstance().downLoadByEntity(xViewFileEntity);
                            }
                            this.isNeedPreLoad = true;
                        }
                        int i4 = next2.priority;
                        if (i4 > i2) {
                            i2 = i4;
                        }
                        if (next2.type == 3) {
                            this.isNeedPreLoad = true;
                        }
                    }
                }
                if (!TextUtils.isEmpty(next.renderData.url) && this.isNeedPreLoad) {
                    String str3 = next.layerId;
                    String str4 = next.renderData.url;
                    String finalLayerId2 = XView2Utils.getFinalLayerId(str3);
                    if (XViewCache.getInstance().getFiles(finalLayerId2 + str4) == null) {
                        XViewFileEntity xViewFileEntity2 = new XViewFileEntity();
                        xViewFileEntity2.url = next.renderData.url;
                        xViewFileEntity2.project_priority = i2 + 1;
                        xViewFileEntity2.id = finalLayerId2 + next.renderData.url;
                        xViewFileEntity2.expiredTime = next.rule.endTime;
                        xViewFileEntity2.layerId = finalLayerId2;
                        xViewFileEntity2.name = next.name;
                        xViewFileEntity2.targetId = next.targetId;
                        xViewFileEntity2.isDsl = true;
                        XViewCache.getInstance().downLoadByEntity(xViewFileEntity2);
                    }
                }
            }
        }
    }

    public void setCurrentXView2(XView2 xView2, Context context, ArrayList<XViewConfigEntity.LayersEntity> arrayList, XViewConfigEntity xViewConfigEntity) {
        this.mXView2 = xView2;
        this.mContext = context;
        this.mLayersEntityList = arrayList;
        this.mXViewConfigEntity = xViewConfigEntity;
    }

    public void setLayoutManager(LayerTypeManager layerTypeManager) {
        this.mLayerTypeManager = layerTypeManager;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0040 A[SYNTHETIC] */
    @Override // com.jingdong.common.XView2.IXView2Delegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setXViewVisible(org.json.JSONObject r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L3
            return
        L3:
            com.jingdong.common.XView2.layer.LayerTypeManager r0 = r6.mLayerTypeManager
            if (r0 != 0) goto L8
            return
        L8:
            java.util.concurrent.ConcurrentHashMap r0 = r0.getLayoutControls()
            if (r0 == 0) goto La0
            com.jingdong.common.XView2.layer.LayerTypeManager r0 = r6.mLayerTypeManager
            java.util.concurrent.ConcurrentHashMap r0 = r0.getLayoutControls()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L1c
            goto La0
        L1c:
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.mControlVisible
            boolean r0 = r0.get()
            if (r0 == 0) goto L25
            return
        L25:
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.mControlVisible
            r1 = 1
            r0.set(r1)
            java.lang.String r0 = "isVisible"
            r1 = 0
            boolean r0 = r7.optBoolean(r0, r1)
            com.jingdong.common.XView2.layer.LayerTypeManager r2 = r6.mLayerTypeManager
            java.util.concurrent.ConcurrentHashMap r2 = r2.getLayoutControls()
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L40:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L9b
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            if (r3 != 0) goto L4f
            goto L40
        L4f:
            java.lang.String r4 = "layerId"
            java.lang.String r4 = r7.optString(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 != 0) goto L68
            java.lang.Object r5 = r3.getKey()
            java.lang.String r5 = (java.lang.String) r5
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 != 0) goto L89
            goto L40
        L68:
            java.lang.String r4 = "logicKey"
            java.lang.String r4 = r7.optString(r4)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L75
            goto L40
        L75:
            java.lang.Object r5 = r3.getValue()
            com.jingdong.common.XView2.layer.BaseLayer r5 = (com.jingdong.common.XView2.layer.BaseLayer) r5
            if (r5 != 0) goto L7e
            goto L40
        L7e:
            java.lang.String r5 = r5.getLogicKey()
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 != 0) goto L89
            goto L40
        L89:
            java.lang.Object r3 = r3.getValue()
            com.jingdong.common.XView2.layer.BaseLayer r3 = (com.jingdong.common.XView2.layer.BaseLayer) r3
            if (r3 != 0) goto L92
            goto L40
        L92:
            com.jingdong.common.XView2.XView2Delegate$1 r4 = new com.jingdong.common.XView2.XView2Delegate$1
            r4.<init>()
            com.jingdong.common.XView2.utils.XView2Utils.runOnUiThread(r4)
            goto L40
        L9b:
            java.util.concurrent.atomic.AtomicBoolean r7 = r6.mControlVisible
            r7.set(r1)
        La0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.XView2.XView2Delegate.setXViewVisible(org.json.JSONObject):void");
    }

    public void startXView2LayerScrolling(String str) {
        LayerTypeManager layerTypeManager;
        BaseLayer value;
        XViewConfigEntity.LayersEntity layersEntity;
        if (TextUtils.isEmpty(str) || (layerTypeManager = this.mLayerTypeManager) == null || layerTypeManager.getLayoutControls() == null || this.mLayerTypeManager.getLayoutControls().isEmpty() || this.mHasScrolled.get()) {
            return;
        }
        this.mHasScrolled.set(true);
        for (Map.Entry<String, BaseLayer> entry : this.mLayerTypeManager.getLayoutControls().entrySet()) {
            if (str.equals(entry.getKey()) && (value = entry.getValue()) != null && (layersEntity = value.getLayersEntity()) != null && value.getContainer() != null && XView2Utils.isCanDrag(layersEntity.dragMode)) {
                value.getContainer().onScroll(false);
            }
        }
    }

    public void stopXView2LayerScrolling(String str) {
        LayerTypeManager layerTypeManager;
        BaseLayer value;
        XViewConfigEntity.LayersEntity layersEntity;
        if (TextUtils.isEmpty(str) || (layerTypeManager = this.mLayerTypeManager) == null || layerTypeManager.getLayoutControls() == null || this.mLayerTypeManager.getLayoutControls().isEmpty() || !this.mHasScrolled.get()) {
            return;
        }
        this.mHasScrolled.set(false);
        for (Map.Entry<String, BaseLayer> entry : this.mLayerTypeManager.getLayoutControls().entrySet()) {
            if (str.equals(entry.getKey()) && (value = entry.getValue()) != null && (layersEntity = value.getLayersEntity()) != null && value.getContainer() != null && XView2Utils.isCanDrag(layersEntity.dragMode)) {
                value.getContainer().onScroll(true);
            }
        }
    }
}
