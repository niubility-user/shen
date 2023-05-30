package com.facebook.react.animated;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.uimanager.GuardedFrameCallback;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModuleListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

@ReactModule(name = NativeAnimatedModule.NAME)
/* loaded from: classes.dex */
public class NativeAnimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, UIManagerModuleListener {
    public static final String NAME = "NativeAnimatedModule";
    private final GuardedFrameCallback mAnimatedFrameCallback;
    @Nullable
    private NativeAnimatedNodesManager mNodesManager;
    private ArrayList<UIThreadOperation> mOperations;
    private ArrayList<UIThreadOperation> mPreOperations;
    private final ReactChoreographer mReactChoreographer;

    /* loaded from: classes.dex */
    private interface UIThreadOperation {
        void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager);
    }

    public NativeAnimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mOperations = new ArrayList<>();
        this.mPreOperations = new ArrayList<>();
        this.mReactChoreographer = ReactChoreographer.getInstance();
        this.mAnimatedFrameCallback = new GuardedFrameCallback(reactApplicationContext) { // from class: com.facebook.react.animated.NativeAnimatedModule.1
            @Override // com.facebook.react.uimanager.GuardedFrameCallback
            protected void doFrameGuarded(long j2) {
                NativeAnimatedNodesManager nodesManager = NativeAnimatedModule.this.getNodesManager();
                if (nodesManager.hasActiveAnimations()) {
                    nodesManager.runUpdates(j2);
                }
                ((ReactChoreographer) Assertions.assertNotNull(NativeAnimatedModule.this.mReactChoreographer)).postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, NativeAnimatedModule.this.mAnimatedFrameCallback);
            }
        };
    }

    private void clearFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    private void enqueueFrameCallback() {
        ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mAnimatedFrameCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NativeAnimatedNodesManager getNodesManager() {
        if (this.mNodesManager == null) {
            this.mNodesManager = new NativeAnimatedNodesManager((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class));
        }
        return this.mNodesManager;
    }

    @ReactMethod
    public void addAnimatedEventToView(final int i2, final String str, final ReadableMap readableMap) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.20
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.addAnimatedEventToView(i2, str, readableMap);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodeToView(final int i2, final int i3) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.17
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.connectAnimatedNodeToView(i2, i3);
            }
        });
    }

    @ReactMethod
    public void connectAnimatedNodes(final int i2, final int i3) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.15
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.connectAnimatedNodes(i2, i3);
            }
        });
    }

    @ReactMethod
    public void createAnimatedNode(final int i2, final ReadableMap readableMap) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.4
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.createAnimatedNode(i2, readableMap);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodeFromView(final int i2, final int i3) {
        this.mPreOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.18
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.restoreDefaultValues(i2, i3);
            }
        });
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.19
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.disconnectAnimatedNodeFromView(i2, i3);
            }
        });
    }

    @ReactMethod
    public void disconnectAnimatedNodes(final int i2, final int i3) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.16
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.disconnectAnimatedNodes(i2, i3);
            }
        });
    }

    @ReactMethod
    public void dropAnimatedNode(final int i2) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.8
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.dropAnimatedNode(i2);
            }
        });
    }

    @ReactMethod
    public void extractAnimatedNodeOffset(final int i2) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.12
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.extractAnimatedNodeOffset(i2);
            }
        });
    }

    @ReactMethod
    public void flattenAnimatedNodeOffset(final int i2) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.11
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.flattenAnimatedNodeOffset(i2);
            }
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.addLifecycleEventListener(this);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIManagerListener(this);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        clearFrameCallback();
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        enqueueFrameCallback();
    }

    @ReactMethod
    public void removeAnimatedEventFromView(final int i2, final String str, final int i3) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.21
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.removeAnimatedEventFromView(i2, str, i3);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeOffset(final int i2, final double d) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.10
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.setAnimatedNodeOffset(i2, d);
            }
        });
    }

    @ReactMethod
    public void setAnimatedNodeValue(final int i2, final double d) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.9
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.setAnimatedNodeValue(i2, d);
            }
        });
    }

    @VisibleForTesting
    public void setNodesManager(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNodesManager = nativeAnimatedNodesManager;
    }

    @ReactMethod
    public void startAnimatingNode(final int i2, final int i3, final ReadableMap readableMap, final Callback callback) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.13
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.startAnimatingNode(i2, i3, readableMap, callback);
            }
        });
    }

    @ReactMethod
    public void startListeningToAnimatedNodeValue(final int i2) {
        new AnimatedNodeValueListener() { // from class: com.facebook.react.animated.NativeAnimatedModule.5
            @Override // com.facebook.react.animated.AnimatedNodeValueListener
            public void onValueUpdate(double d) {
                WritableMap createMap = Arguments.createMap();
                createMap.putInt("tag", i2);
                createMap.putDouble("value", d);
                ((DeviceEventManagerModule.RCTDeviceEventEmitter) NativeAnimatedModule.this.getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", createMap);
            }
        };
        this.mOperations.add(new UIThreadOperation
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000c: INVOKE 
              (wrap: java.util.ArrayList<com.facebook.react.animated.NativeAnimatedModule$UIThreadOperation> : 0x0005: IGET (r3v0 'this' com.facebook.react.animated.NativeAnimatedModule A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:2) com.facebook.react.animated.NativeAnimatedModule.mOperations java.util.ArrayList)
              (wrap: com.facebook.react.animated.NativeAnimatedModule$UIThreadOperation : 0x0009: CONSTRUCTOR 
              (r3v0 'this' com.facebook.react.animated.NativeAnimatedModule A[IMMUTABLE_TYPE, THIS])
              (r4v0 'i2' int A[DONT_INLINE])
              (r0 I:com.facebook.react.animated.AnimatedNodeValueListener A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.facebook.react.animated.NativeAnimatedModule, int, com.facebook.react.animated.AnimatedNodeValueListener):void (m), WRAPPED] call: com.facebook.react.animated.NativeAnimatedModule.6.<init>(com.facebook.react.animated.NativeAnimatedModule, int, com.facebook.react.animated.AnimatedNodeValueListener):void type: CONSTRUCTOR)
             type: VIRTUAL call: java.util.ArrayList.add(java.lang.Object):boolean A[MD:(E):boolean (c)] (LINE:2) in method: com.facebook.react.animated.NativeAnimatedModule.startListeningToAnimatedNodeValue(int):void, file: classes.dex
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
            com.facebook.react.animated.NativeAnimatedModule$5 r0 = new com.facebook.react.animated.NativeAnimatedModule$5
            r0.<init>()
            java.util.ArrayList<com.facebook.react.animated.NativeAnimatedModule$UIThreadOperation> r1 = r3.mOperations
            com.facebook.react.animated.NativeAnimatedModule$6 r2 = new com.facebook.react.animated.NativeAnimatedModule$6
            r2.<init>()
            r1.add(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.NativeAnimatedModule.startListeningToAnimatedNodeValue(int):void");
    }

    @ReactMethod
    public void stopAnimation(final int i2) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.14
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.stopAnimation(i2);
            }
        });
    }

    @ReactMethod
    public void stopListeningToAnimatedNodeValue(final int i2) {
        this.mOperations.add(new UIThreadOperation() { // from class: com.facebook.react.animated.NativeAnimatedModule.7
            @Override // com.facebook.react.animated.NativeAnimatedModule.UIThreadOperation
            public void execute(NativeAnimatedNodesManager nativeAnimatedNodesManager) {
                nativeAnimatedNodesManager.stopListeningToAnimatedNodeValue(i2);
            }
        });
    }

    @Override // com.facebook.react.uimanager.UIManagerModuleListener
    public void willDispatchViewUpdates(UIManagerModule uIManagerModule) {
        if (this.mOperations.isEmpty() && this.mPreOperations.isEmpty()) {
            return;
        }
        final ArrayList<UIThreadOperation> arrayList = this.mPreOperations;
        final ArrayList<UIThreadOperation> arrayList2 = this.mOperations;
        this.mPreOperations = new ArrayList<>();
        this.mOperations = new ArrayList<>();
        uIManagerModule.prependUIBlock(new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule.2
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NativeAnimatedNodesManager nodesManager = NativeAnimatedModule.this.getNodesManager();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((UIThreadOperation) it.next()).execute(nodesManager);
                }
            }
        });
        uIManagerModule.addUIBlock(new UIBlock() { // from class: com.facebook.react.animated.NativeAnimatedModule.3
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                NativeAnimatedNodesManager nodesManager = NativeAnimatedModule.this.getNodesManager();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((UIThreadOperation) it.next()).execute(nodesManager);
                }
            }
        });
    }
}
