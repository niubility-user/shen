package com.jingdong.jdreact.plugin.jdmodal;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView;
import java.util.Map;

/* loaded from: classes13.dex */
public class JDReactModalHostManager extends ViewGroupManager<JDReactModalHostView> {
    protected static final String REACT_CLASS = "JDRCTModalHostView";
    private static final int REFRESH_LAYOUT = 1;

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("refreshLayout", 1);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("topRequestClose", MapBuilder.of("registrationName", "onRequestClose")).put("topShow", MapBuilder.of("registrationName", "onShow")).put(JDShowEvent2.EVENT_NAME, MapBuilder.of("registrationName", "onShow2")).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return JDModalHostShadowNode.class;
    }

    @ReactProp(name = "animationType")
    public void setAnimationType(JDReactModalHostView jDReactModalHostView, String str) {
        jDReactModalHostView.setAnimationType(str);
    }

    @ReactProp(name = "dismiss")
    public void setDismiss(JDReactModalHostView jDReactModalHostView, boolean z) {
        jDReactModalHostView.setDismiss(z);
    }

    @ReactProp(name = "hardwareAccelerated")
    public void setHardwareAccelerated(JDReactModalHostView jDReactModalHostView, boolean z) {
        jDReactModalHostView.setHardwareAccelerated(z);
    }

    @ReactProp(name = "overlaycolor")
    public void setOverlayColor(JDReactModalHostView jDReactModalHostView, String str) {
        jDReactModalHostView.setOverlayColor(str);
    }

    @ReactProp(name = "transparent")
    public void setTransparent(JDReactModalHostView jDReactModalHostView, boolean z) {
        jDReactModalHostView.setTransparent(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, final JDReactModalHostView jDReactModalHostView) {
        ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        jDReactModalHostView.setOnRequestCloseListener(new JDReactModalHostView.OnRequestCloseListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0011: INVOKE 
              (r3v0 'jDReactModalHostView' com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView)
              (wrap: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$OnRequestCloseListener : 0x000e: CONSTRUCTOR 
              (r1v0 'this' com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager A[IMMUTABLE_TYPE, THIS])
              (r2 I:com.facebook.react.uimanager.events.EventDispatcher A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'jDReactModalHostView' com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView A[DONT_INLINE])
             A[MD:(com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager, com.facebook.react.uimanager.events.EventDispatcher, com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView):void (m), WRAPPED] (LINE:4) call: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager.1.<init>(com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager, com.facebook.react.uimanager.events.EventDispatcher, com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView.setOnRequestCloseListener(com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$OnRequestCloseListener):void A[MD:(com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView$OnRequestCloseListener):void (m)] (LINE:4) in method: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager.addEventEmitters(com.facebook.react.uimanager.ThemedReactContext, com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView):void, file: classes13.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            java.lang.Class<com.facebook.react.uimanager.UIManagerModule> r0 = com.facebook.react.uimanager.UIManagerModule.class
            com.facebook.react.bridge.NativeModule r2 = r2.getNativeModule(r0)
            com.facebook.react.uimanager.UIManagerModule r2 = (com.facebook.react.uimanager.UIManagerModule) r2
            com.facebook.react.uimanager.events.EventDispatcher r2 = r2.getEventDispatcher()
            com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager$1 r0 = new com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager$1
            r0.<init>()
            r3.setOnRequestCloseListener(r0)
            com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager$2 r0 = new com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager$2
            r0.<init>()
            r3.setOnShowListener(r0)
            com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager$3 r0 = new com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager$3
            r0.<init>()
            r3.setOnShow2Listener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostManager.addEventEmitters(com.facebook.react.uimanager.ThemedReactContext, com.jingdong.jdreact.plugin.jdmodal.JDReactModalHostView):void");
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new JDModalHostShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactModalHostView createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactModalHostView(themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(JDReactModalHostView jDReactModalHostView) {
        super.onAfterUpdateTransaction((JDReactModalHostManager) jDReactModalHostView);
        jDReactModalHostView.showOrUpdate();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(JDReactModalHostView jDReactModalHostView) {
        super.onDropViewInstance((JDReactModalHostManager) jDReactModalHostView);
        jDReactModalHostView.onDropInstance();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(JDReactModalHostView jDReactModalHostView, int i2, @Nullable ReadableArray readableArray) {
        if (i2 != 1) {
            return;
        }
        jDReactModalHostView.refreshLayout();
    }
}
