package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;

/* loaded from: classes.dex */
public abstract class TurboReactPackage implements ReactPackage {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class ModuleHolderProvider implements Provider<NativeModule> {
        private final String mName;
        private final ReactApplicationContext mReactContext;

        public ModuleHolderProvider(String str, ReactApplicationContext reactApplicationContext) {
            this.mName = str;
            this.mReactContext = reactApplicationContext;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public NativeModule get() {
            return TurboReactPackage.this.getModule(this.mName, this.mReactContext);
        }
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        throw new UnsupportedOperationException("In case of TurboModules, createNativeModules is not supported. NativeModuleRegistry should instead use getModuleList or getModule method");
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

    public abstract NativeModule getModule(String str, ReactApplicationContext reactApplicationContext);

    public Iterable<ModuleHolder> getNativeModuleIterator(final ReactApplicationContext reactApplicationContext) {
        getReactModuleInfoProvider().getReactModuleInfos().entrySet().iterator();
        return new Iterable<ModuleHolder>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: RETURN 
              (wrap: java.lang.Iterable<com.facebook.react.bridge.ModuleHolder> : 0x0012: CONSTRUCTOR 
              (r2v0 'this' com.facebook.react.TurboReactPackage A[IMMUTABLE_TYPE, THIS])
              (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'reactApplicationContext' com.facebook.react.bridge.ReactApplicationContext A[DONT_INLINE])
             A[MD:(com.facebook.react.TurboReactPackage, java.util.Iterator, com.facebook.react.bridge.ReactApplicationContext):void (m), WRAPPED] (LINE:3) call: com.facebook.react.TurboReactPackage.1.<init>(com.facebook.react.TurboReactPackage, java.util.Iterator, com.facebook.react.bridge.ReactApplicationContext):void type: CONSTRUCTOR)
             (LINE:3) in method: com.facebook.react.TurboReactPackage.getNativeModuleIterator(com.facebook.react.bridge.ReactApplicationContext):java.lang.Iterable<com.facebook.react.bridge.ModuleHolder>, file: classes.dex
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
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            com.facebook.react.module.model.ReactModuleInfoProvider r0 = r2.getReactModuleInfoProvider()
            java.util.Map r0 = r0.getReactModuleInfos()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            com.facebook.react.TurboReactPackage$1 r1 = new com.facebook.react.TurboReactPackage$1
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.TurboReactPackage.getNativeModuleIterator(com.facebook.react.bridge.ReactApplicationContext):java.lang.Iterable");
    }

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    protected List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
