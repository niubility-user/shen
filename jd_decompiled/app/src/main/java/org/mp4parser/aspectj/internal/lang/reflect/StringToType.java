package org.mp4parser.aspectj.internal.lang.reflect;

import com.jd.dynamic.DYConstants;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.StringTokenizer;
import org.mp4parser.aspectj.lang.reflect.AjTypeSystem;

/* loaded from: classes11.dex */
public class StringToType {
    public static Type[] commaSeparatedListToTypeArray(String str, Class cls) throws ClassNotFoundException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, DYConstants.DY_REGEX_COMMA);
        Type[] typeArr = new Type[stringTokenizer.countTokens()];
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            typeArr[i2] = stringToType(stringTokenizer.nextToken().trim(), cls);
            i2++;
        }
        return typeArr;
    }

    private static Type makeParameterizedType(String str, Class cls) throws ClassNotFoundException {
        int indexOf = str.indexOf(60);
        Class.forName(str.substring(0, indexOf), false, cls.getClassLoader());
        commaSeparatedListToTypeArray(str.substring(indexOf + 1, str.lastIndexOf(62)), cls);
        return new ParameterizedType
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0028: RETURN 
              (wrap: java.lang.reflect.ParameterizedType : 0x0025: CONSTRUCTOR 
              (r4 I:java.lang.reflect.Type[] A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r1 I:java.lang.Class A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(java.lang.reflect.Type[], java.lang.Class):void (m), WRAPPED] (LINE:7) call: org.mp4parser.aspectj.internal.lang.reflect.StringToType.1.<init>(java.lang.reflect.Type[], java.lang.Class):void type: CONSTRUCTOR)
             (LINE:7) in method: org.mp4parser.aspectj.internal.lang.reflect.StringToType.makeParameterizedType(java.lang.String, java.lang.Class):java.lang.reflect.Type, file: classes11.dex
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
            r0 = 60
            int r0 = r4.indexOf(r0)
            r1 = 0
            java.lang.String r2 = r4.substring(r1, r0)
            java.lang.ClassLoader r3 = r5.getClassLoader()
            java.lang.Class r1 = java.lang.Class.forName(r2, r1, r3)
            r2 = 62
            int r2 = r4.lastIndexOf(r2)
            int r0 = r0 + 1
            java.lang.String r4 = r4.substring(r0, r2)
            java.lang.reflect.Type[] r4 = commaSeparatedListToTypeArray(r4, r5)
            org.mp4parser.aspectj.internal.lang.reflect.StringToType$1 r5 = new org.mp4parser.aspectj.internal.lang.reflect.StringToType$1
            r5.<init>()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mp4parser.aspectj.internal.lang.reflect.StringToType.makeParameterizedType(java.lang.String, java.lang.Class):java.lang.reflect.Type");
    }

    public static Type stringToType(String str, Class cls) throws ClassNotFoundException {
        try {
            if (str.indexOf("<") == -1) {
                return AjTypeSystem.getAjType(Class.forName(str, false, cls.getClassLoader()));
            }
            return makeParameterizedType(str, cls);
        } catch (ClassNotFoundException unused) {
            TypeVariable[] typeParameters = cls.getTypeParameters();
            for (int i2 = 0; i2 < typeParameters.length; i2++) {
                if (typeParameters[i2].getName().equals(str)) {
                    return typeParameters[i2];
                }
            }
            throw new ClassNotFoundException(str);
        }
    }
}
