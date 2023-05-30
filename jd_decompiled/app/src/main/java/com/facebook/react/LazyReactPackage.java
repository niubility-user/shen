package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class LazyReactPackage implements ReactPackage {
    public static ReactModuleInfoProvider getReactModuleInfoProviderViaReflection(LazyReactPackage lazyReactPackage) {
        try {
            Class<?> cls = Class.forName(lazyReactPackage.getClass().getCanonicalName() + "$$ReactModuleInfoProvider");
            if (cls != null) {
                try {
                    return (ReactModuleInfoProvider) cls.newInstance();
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), e2);
                } catch (InstantiationException e3) {
                    throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + lazyReactPackage.getClass(), e3);
                }
            }
            throw new RuntimeException("ReactModuleInfoProvider class for " + lazyReactPackage.getClass().getCanonicalName() + " not found.");
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() { // from class: com.facebook.react.LazyReactPackage.1
                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public Map<String, ReactModuleInfo> getReactModuleInfos() {
                    return Collections.emptyMap();
                }
            };
        }
    }

    @Override // com.facebook.react.ReactPackage
    public final List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec moduleSpec : getNativeModules(reactApplicationContext)) {
            SystraceMessage.beginSection(0L, "createNativeModule").arg("module", moduleSpec.getType()).flush();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, moduleSpec.getName());
            try {
                NativeModule nativeModule = moduleSpec.getProvider().get();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                SystraceMessage.endSection(0L).flush();
                arrayList.add(nativeModule);
            } catch (Throwable th) {
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                SystraceMessage.endSection(0L).flush();
                throw th;
            }
        }
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> viewManagers = getViewManagers(reactApplicationContext);
        if (viewManagers != null && !viewManagers.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            Iterator<ModuleSpec> it = viewManagers.iterator();
            while (it.hasNext()) {
                arrayList.add((ViewManager) it.next().getProvider().get());
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Iterable<ModuleHolder> getNativeModuleIterator(ReactApplicationContext reactApplicationContext) {
        getReactModuleInfoProvider().getReactModuleInfos();
        getNativeModules(reactApplicationContext);
        return new Iterable<ModuleHolder>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0011: RETURN 
              (wrap: java.lang.Iterable<com.facebook.react.bridge.ModuleHolder> : 0x000e: CONSTRUCTOR 
              (r2v0 'this' com.facebook.react.LazyReactPackage A[IMMUTABLE_TYPE, THIS])
              (r3 I:java.util.List A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r0 I:java.util.Map A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.facebook.react.LazyReactPackage, java.util.List, java.util.Map):void (m), WRAPPED] (LINE:3) call: com.facebook.react.LazyReactPackage.2.<init>(com.facebook.react.LazyReactPackage, java.util.List, java.util.Map):void type: CONSTRUCTOR)
             (LINE:3) in method: com.facebook.react.LazyReactPackage.getNativeModuleIterator(com.facebook.react.bridge.ReactApplicationContext):java.lang.Iterable<com.facebook.react.bridge.ModuleHolder>, file: classes.dex
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
            */
        /*
            this = this;
            com.facebook.react.module.model.ReactModuleInfoProvider r0 = r2.getReactModuleInfoProvider()
            java.util.Map r0 = r0.getReactModuleInfos()
            java.util.List r3 = r2.getNativeModules(r3)
            com.facebook.react.LazyReactPackage$2 r1 = new com.facebook.react.LazyReactPackage$2
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.LazyReactPackage.getNativeModuleIterator(com.facebook.react.bridge.ReactApplicationContext):java.lang.Iterable");
    }

    protected abstract List<ModuleSpec> getNativeModules(ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
