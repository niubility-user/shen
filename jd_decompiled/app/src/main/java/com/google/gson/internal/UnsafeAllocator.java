package com.google.gson.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/* loaded from: classes12.dex */
public abstract class UnsafeAllocator {
    static void assertInstantiable(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (!Modifier.isInterface(modifiers)) {
            if (Modifier.isAbstract(modifiers)) {
                throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
            }
            return;
        }
        throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
    }

    public static UnsafeAllocator create() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            declaredField.get(null);
            cls.getMethod("allocateInstance", Class.class);
            return new UnsafeAllocator
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0029: RETURN 
                  (wrap: com.google.gson.internal.UnsafeAllocator : 0x0026: CONSTRUCTOR 
                  (r4 I:java.lang.reflect.Method A[DONT_GENERATE, DONT_INLINE, REMOVE])
                  (r5 I:java.lang.Object A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[Catch: Exception -> 0x002a, MD:(java.lang.reflect.Method, java.lang.Object):void (m), TRY_LEAVE, WRAPPED] (LINE:6) call: com.google.gson.internal.UnsafeAllocator.1.<init>(java.lang.reflect.Method, java.lang.Object):void type: CONSTRUCTOR)
                 (LINE:6) in method: com.google.gson.internal.UnsafeAllocator.create():com.google.gson.internal.UnsafeAllocator, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
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
                java.lang.String r0 = "newInstance"
                r1 = 0
                r2 = 0
                r3 = 1
                java.lang.String r4 = "sun.misc.Unsafe"
                java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch: java.lang.Exception -> L2a
                java.lang.String r5 = "theUnsafe"
                java.lang.reflect.Field r5 = r4.getDeclaredField(r5)     // Catch: java.lang.Exception -> L2a
                r5.setAccessible(r3)     // Catch: java.lang.Exception -> L2a
                java.lang.Object r5 = r5.get(r1)     // Catch: java.lang.Exception -> L2a
                java.lang.String r6 = "allocateInstance"
                java.lang.Class[] r7 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L2a
                java.lang.Class<java.lang.Class> r8 = java.lang.Class.class
                r7[r2] = r8     // Catch: java.lang.Exception -> L2a
                java.lang.reflect.Method r4 = r4.getMethod(r6, r7)     // Catch: java.lang.Exception -> L2a
                com.google.gson.internal.UnsafeAllocator$1 r6 = new com.google.gson.internal.UnsafeAllocator$1     // Catch: java.lang.Exception -> L2a
                r6.<init>()     // Catch: java.lang.Exception -> L2a
                return r6
            L2a:
                r4 = 2
                java.lang.Class<java.io.ObjectStreamClass> r5 = java.io.ObjectStreamClass.class
                java.lang.String r6 = "getConstructorId"
                java.lang.Class[] r7 = new java.lang.Class[r3]     // Catch: java.lang.Exception -> L65
                java.lang.Class<java.lang.Class> r8 = java.lang.Class.class
                r7[r2] = r8     // Catch: java.lang.Exception -> L65
                java.lang.reflect.Method r5 = r5.getDeclaredMethod(r6, r7)     // Catch: java.lang.Exception -> L65
                r5.setAccessible(r3)     // Catch: java.lang.Exception -> L65
                java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L65
                java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
                r6[r2] = r7     // Catch: java.lang.Exception -> L65
                java.lang.Object r1 = r5.invoke(r1, r6)     // Catch: java.lang.Exception -> L65
                java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Exception -> L65
                int r1 = r1.intValue()     // Catch: java.lang.Exception -> L65
                java.lang.Class<java.io.ObjectStreamClass> r5 = java.io.ObjectStreamClass.class
                java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L65
                java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
                r6[r2] = r7     // Catch: java.lang.Exception -> L65
                java.lang.Class r7 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L65
                r6[r3] = r7     // Catch: java.lang.Exception -> L65
                java.lang.reflect.Method r5 = r5.getDeclaredMethod(r0, r6)     // Catch: java.lang.Exception -> L65
                r5.setAccessible(r3)     // Catch: java.lang.Exception -> L65
                com.google.gson.internal.UnsafeAllocator$2 r6 = new com.google.gson.internal.UnsafeAllocator$2     // Catch: java.lang.Exception -> L65
                r6.<init>()     // Catch: java.lang.Exception -> L65
                return r6
            L65:
                java.lang.Class<java.io.ObjectInputStream> r1 = java.io.ObjectInputStream.class
                java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L7e
                java.lang.Class<java.lang.Class> r5 = java.lang.Class.class
                r4[r2] = r5     // Catch: java.lang.Exception -> L7e
                java.lang.Class<java.lang.Class> r2 = java.lang.Class.class
                r4[r3] = r2     // Catch: java.lang.Exception -> L7e
                java.lang.reflect.Method r0 = r1.getDeclaredMethod(r0, r4)     // Catch: java.lang.Exception -> L7e
                r0.setAccessible(r3)     // Catch: java.lang.Exception -> L7e
                com.google.gson.internal.UnsafeAllocator$3 r1 = new com.google.gson.internal.UnsafeAllocator$3     // Catch: java.lang.Exception -> L7e
                r1.<init>()     // Catch: java.lang.Exception -> L7e
                return r1
            L7e:
                com.google.gson.internal.UnsafeAllocator$4 r0 = new com.google.gson.internal.UnsafeAllocator$4
                r0.<init>()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.UnsafeAllocator.create():com.google.gson.internal.UnsafeAllocator");
        }

        public abstract <T> T newInstance(Class<T> cls) throws Exception;
    }
