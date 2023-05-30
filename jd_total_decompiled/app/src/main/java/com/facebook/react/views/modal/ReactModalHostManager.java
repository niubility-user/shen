package com.facebook.react.views.modal;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.modal.ReactModalHostView;
import java.util.Map;

@ReactModule(name = ReactModalHostManager.REACT_CLASS)
/* loaded from: classes12.dex */
public class ReactModalHostManager extends ViewGroupManager<ReactModalHostView> {
    public static final String REACT_CLASS = "RCTModalHostView";

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("topRequestClose", MapBuilder.of("registrationName", "onRequestClose")).put("topShow", MapBuilder.of("registrationName", "onShow")).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ModalHostShadowNode.class;
    }

    @ReactProp(name = "animationType")
    public void setAnimationType(ReactModalHostView reactModalHostView, String str) {
        reactModalHostView.setAnimationType(str);
    }

    @ReactProp(name = "hardwareAccelerated")
    public void setHardwareAccelerated(ReactModalHostView reactModalHostView, boolean z) {
        reactModalHostView.setHardwareAccelerated(z);
    }

    @ReactProp(name = "transparent")
    public void setTransparent(ReactModalHostView reactModalHostView, boolean z) {
        reactModalHostView.setTransparent(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, final ReactModalHostView reactModalHostView) {
        ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
        reactModalHostView.setOnRequestCloseListener(new ReactModalHostView.OnRequestCloseListener
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0011: INVOKE 
              (r3v0 'reactModalHostView' com.facebook.react.views.modal.ReactModalHostView)
              (wrap: com.facebook.react.views.modal.ReactModalHostView$OnRequestCloseListener : 0x000e: CONSTRUCTOR 
              (r1v0 'this' com.facebook.react.views.modal.ReactModalHostManager A[IMMUTABLE_TYPE, THIS])
              (r2 I:com.facebook.react.uimanager.events.EventDispatcher A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'reactModalHostView' com.facebook.react.views.modal.ReactModalHostView A[DONT_INLINE])
             A[MD:(com.facebook.react.views.modal.ReactModalHostManager, com.facebook.react.uimanager.events.EventDispatcher, com.facebook.react.views.modal.ReactModalHostView):void (m), WRAPPED] (LINE:4) call: com.facebook.react.views.modal.ReactModalHostManager.1.<init>(com.facebook.react.views.modal.ReactModalHostManager, com.facebook.react.uimanager.events.EventDispatcher, com.facebook.react.views.modal.ReactModalHostView):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.facebook.react.views.modal.ReactModalHostView.setOnRequestCloseListener(com.facebook.react.views.modal.ReactModalHostView$OnRequestCloseListener):void A[MD:(com.facebook.react.views.modal.ReactModalHostView$OnRequestCloseListener):void (m)] (LINE:4) in method: com.facebook.react.views.modal.ReactModalHostManager.addEventEmitters(com.facebook.react.uimanager.ThemedReactContext, com.facebook.react.views.modal.ReactModalHostView):void, file: classes12.dex
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
            com.facebook.react.views.modal.ReactModalHostManager$1 r0 = new com.facebook.react.views.modal.ReactModalHostManager$1
            r0.<init>()
            r3.setOnRequestCloseListener(r0)
            com.facebook.react.views.modal.ReactModalHostManager$2 r0 = new com.facebook.react.views.modal.ReactModalHostManager$2
            r0.<init>()
            r3.setOnShowListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.modal.ReactModalHostManager.addEventEmitters(com.facebook.react.uimanager.ThemedReactContext, com.facebook.react.views.modal.ReactModalHostView):void");
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new ModalHostShadowNode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ReactModalHostView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactModalHostView(themedReactContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ReactModalHostView reactModalHostView) {
        super.onAfterUpdateTransaction((ReactModalHostManager) reactModalHostView);
        reactModalHostView.showOrUpdate();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(ReactModalHostView reactModalHostView) {
        super.onDropViewInstance((ReactModalHostManager) reactModalHostView);
        reactModalHostView.onDropInstance();
    }
}
