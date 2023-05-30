package com.jingdong.common.XView2;

import android.app.Activity;
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
import com.jingdong.common.XView2.utils.BaseRunnable;
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

    /* JADX WARN: Code restructure failed: missing block: B:74:0x0048, code lost:
        if (isHasSameGroupID(r2.mXView2.getCurrentFragment().getView(), r3) != false) goto L69;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public XViewConfigEntity.LayersEntity getRealPopLayerEntity(XViewConfigEntity.LayersEntity layersEntity) {
        if (layersEntity == null || this.mXView2 == null || this.mContext == null) {
            return null;
        }
        this.mLock.writeLock().lock();
        try {
            if (!isHasSameGroupID(((Activity) this.mContext).getWindow().getDecorView(), layersEntity)) {
                if (this.mXView2.getCurrentFragment() != null) {
                }
                return layersEntity;
            }
            return null;
        } finally {
            this.mLock.writeLock().unlock();
        }
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

    /* JADX WARN: Removed duplicated region for block: B:165:0x0092 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0040 A[SYNTHETIC] */
    @Override // com.jingdong.common.XView2.IXView2Delegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setXViewVisible(JSONObject jSONObject) {
        LayerTypeManager layerTypeManager;
        final BaseLayer value;
        BaseLayer value2;
        if (jSONObject == null || (layerTypeManager = this.mLayerTypeManager) == null || layerTypeManager.getLayoutControls() == null || this.mLayerTypeManager.getLayoutControls().isEmpty() || this.mControlVisible.get()) {
            return;
        }
        this.mControlVisible.set(true);
        jSONObject.optBoolean(XView2Constants.ISVISIBLE, false);
        for (Map.Entry<String, BaseLayer> entry : this.mLayerTypeManager.getLayoutControls().entrySet()) {
            if (entry != null) {
                String optString = jSONObject.optString(XView2Constants.LAYER_ID);
                if (!TextUtils.isEmpty(optString)) {
                    if (optString.equalsIgnoreCase(entry.getKey())) {
                        value = entry.getValue();
                        if (value == null) {
                            XView2Utils.runOnUiThread(new BaseRunnable
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0097: INVOKE 
                                  (wrap: com.jingdong.common.XView2.utils.BaseRunnable : 0x0094: CONSTRUCTOR 
                                  (r6v0 'this' com.jingdong.common.XView2.XView2Delegate A[IMMUTABLE_TYPE, THIS])
                                  (r3v4 'value' com.jingdong.common.XView2.layer.BaseLayer A[DONT_INLINE])
                                  (r0 I:boolean A[DONT_GENERATE, DONT_INLINE, REMOVE])
                                 A[MD:(com.jingdong.common.XView2.XView2Delegate, com.jingdong.common.XView2.layer.BaseLayer, boolean):void (m), WRAPPED] (LINE:17) call: com.jingdong.common.XView2.XView2Delegate.1.<init>(com.jingdong.common.XView2.XView2Delegate, com.jingdong.common.XView2.layer.BaseLayer, boolean):void type: CONSTRUCTOR)
                                 type: STATIC call: com.jingdong.common.XView2.utils.XView2Utils.runOnUiThread(com.jingdong.common.XView2.utils.BaseRunnable):void A[MD:(com.jingdong.common.XView2.utils.BaseRunnable):void (m)] (LINE:17) in method: com.jingdong.common.XView2.XView2Delegate.setXViewVisible(org.json.JSONObject):void, file: classes5.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:207)
                                	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                Caused by: java.lang.NullPointerException
                                */
                            /*
                                this = this;
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
