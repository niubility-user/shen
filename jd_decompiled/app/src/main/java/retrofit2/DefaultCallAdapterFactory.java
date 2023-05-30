package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import retrofit2.CallAdapter;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class DefaultCallAdapterFactory extends CallAdapter.Factory {
    static final CallAdapter.Factory INSTANCE = new DefaultCallAdapterFactory();

    DefaultCallAdapterFactory() {
    }

    @Override // retrofit2.CallAdapter.Factory
    public CallAdapter<?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.getRawType(type) != Call.class) {
            return null;
        }
        Utils.getCallResponseType(type);
        return new CallAdapter<Call<?>>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0013: RETURN 
              (wrap: retrofit2.CallAdapter<retrofit2.Call<?>> : 0x0010: CONSTRUCTOR 
              (r0v0 'this' retrofit2.DefaultCallAdapterFactory A[IMMUTABLE_TYPE, THIS])
              (r1 I:java.lang.reflect.Type A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(retrofit2.DefaultCallAdapterFactory, java.lang.reflect.Type):void (m), WRAPPED] (LINE:3) call: retrofit2.DefaultCallAdapterFactory.1.<init>(retrofit2.DefaultCallAdapterFactory, java.lang.reflect.Type):void type: CONSTRUCTOR)
             (LINE:3) in method: retrofit2.DefaultCallAdapterFactory.get(java.lang.reflect.Type, java.lang.annotation.Annotation[], retrofit2.Retrofit):retrofit2.CallAdapter<?>, file: classes11.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            java.lang.Class r2 = retrofit2.CallAdapter.Factory.getRawType(r1)
            java.lang.Class<retrofit2.Call> r3 = retrofit2.Call.class
            if (r2 == r3) goto La
            r1 = 0
            return r1
        La:
            java.lang.reflect.Type r1 = retrofit2.Utils.getCallResponseType(r1)
            retrofit2.DefaultCallAdapterFactory$1 r2 = new retrofit2.DefaultCallAdapterFactory$1
            r2.<init>()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.DefaultCallAdapterFactory.get(java.lang.reflect.Type, java.lang.annotation.Annotation[], retrofit2.Retrofit):retrofit2.CallAdapter");
    }
}
