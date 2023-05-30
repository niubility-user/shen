package org.mp4parser.aspectj.runtime.reflect;

import com.jd.dynamic.DYConstants;
import com.tencent.smtt.sdk.ProxyConfig;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.StringTokenizer;
import org.mp4parser.aspectj.lang.JoinPoint;
import org.mp4parser.aspectj.lang.Signature;
import org.mp4parser.aspectj.lang.reflect.AdviceSignature;
import org.mp4parser.aspectj.lang.reflect.CatchClauseSignature;
import org.mp4parser.aspectj.lang.reflect.ConstructorSignature;
import org.mp4parser.aspectj.lang.reflect.FieldSignature;
import org.mp4parser.aspectj.lang.reflect.InitializerSignature;
import org.mp4parser.aspectj.lang.reflect.LockSignature;
import org.mp4parser.aspectj.lang.reflect.MethodSignature;
import org.mp4parser.aspectj.lang.reflect.SourceLocation;
import org.mp4parser.aspectj.lang.reflect.UnlockSignature;
import org.mp4parser.aspectj.runtime.reflect.JoinPointImpl;

/* loaded from: classes11.dex */
public final class Factory {
    private static Object[] NO_ARGS;
    static /* synthetic */ Class class$java$lang$ClassNotFoundException;
    static Hashtable prims;
    int count = 0;
    String filename;
    Class lexicalClass;
    ClassLoader lookupClassLoader;

    static {
        Hashtable hashtable = new Hashtable();
        prims = hashtable;
        hashtable.put("void", Void.TYPE);
        prims.put("boolean", Boolean.TYPE);
        prims.put("byte", Byte.TYPE);
        prims.put(DYConstants.DY_CHAR, Character.TYPE);
        prims.put("short", Short.TYPE);
        prims.put("int", Integer.TYPE);
        prims.put("long", Long.TYPE);
        prims.put("float", Float.TYPE);
        prims.put("double", Double.TYPE);
        NO_ARGS = new Object[0];
    }

    public Factory(String str, Class cls) {
        this.filename = str;
        this.lexicalClass = cls;
        this.lookupClassLoader = cls.getClassLoader();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    public static Class makeClass(String str, ClassLoader classLoader) {
        if (str.equals(ProxyConfig.MATCH_ALL_SCHEMES)) {
            return null;
        }
        Class cls = (Class) prims.get(str);
        if (cls != null) {
            return cls;
        }
        try {
            if (classLoader == null) {
                return Class.forName(str);
            }
            return Class.forName(str, false, classLoader);
        } catch (ClassNotFoundException unused) {
            Class cls2 = class$java$lang$ClassNotFoundException;
            if (cls2 == null) {
                Class class$ = class$("java.lang.ClassNotFoundException");
                class$java$lang$ClassNotFoundException = class$;
                return class$;
            }
            return cls2;
        }
    }

    public static JoinPoint.StaticPart makeEncSJP(Member member) {
        Signature constructorSignatureImpl;
        String str;
        if (member instanceof Method) {
            Method method = (Method) member;
            constructorSignatureImpl = new MethodSignatureImpl(method.getModifiers(), method.getName(), method.getDeclaringClass(), method.getParameterTypes(), new String[method.getParameterTypes().length], method.getExceptionTypes(), method.getReturnType());
            str = JoinPoint.METHOD_EXECUTION;
        } else if (member instanceof Constructor) {
            Constructor constructor = (Constructor) member;
            constructorSignatureImpl = new ConstructorSignatureImpl(constructor.getModifiers(), constructor.getDeclaringClass(), constructor.getParameterTypes(), new String[constructor.getParameterTypes().length], constructor.getExceptionTypes());
            str = JoinPoint.CONSTRUCTOR_EXECUTION;
        } else {
            throw new IllegalArgumentException("member must be either a method or constructor");
        }
        return new JoinPointImpl.EnclosingStaticPartImpl(-1, str, constructorSignatureImpl, null);
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2) {
        return new JoinPointImpl(staticPart, obj, obj2, NO_ARGS);
    }

    public AdviceSignature makeAdviceSig(String str) {
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(str);
        adviceSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return adviceSignatureImpl;
    }

    public CatchClauseSignature makeCatchClauseSig(String str) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(str);
        catchClauseSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return catchClauseSignatureImpl;
    }

    public ConstructorSignature makeConstructorSig(String str) {
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(str);
        constructorSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return constructorSignatureImpl;
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String str, Signature signature, SourceLocation sourceLocation) {
        int i2 = this.count;
        this.count = i2 + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i2, str, signature, sourceLocation);
    }

    public FieldSignature makeFieldSig(String str) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(str);
        fieldSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return fieldSignatureImpl;
    }

    public InitializerSignature makeInitializerSig(String str) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(str);
        initializerSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return initializerSignatureImpl;
    }

    public LockSignature makeLockSig(String str) {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(str);
        lockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return lockSignatureImpl;
    }

    public MethodSignature makeMethodSig(String str) {
        MethodSignatureImpl methodSignatureImpl = new MethodSignatureImpl(str);
        methodSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return methodSignatureImpl;
    }

    public JoinPoint.StaticPart makeSJP(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i2) {
        MethodSignature makeMethodSig = makeMethodSig(str2, str3, str4, str5, str6, str7, str8);
        int i3 = this.count;
        this.count = i3 + 1;
        return new JoinPointImpl.StaticPartImpl(i3, str, makeMethodSig, makeSourceLoc(i2, -1));
    }

    public SourceLocation makeSourceLoc(int i2, int i3) {
        return new SourceLocationImpl(this.lexicalClass, this.filename, i2);
    }

    public UnlockSignature makeUnlockSig(String str) {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(str);
        unlockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return unlockSignatureImpl;
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object obj3) {
        return new JoinPointImpl(staticPart, obj, obj2, new Object[]{obj3});
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String str, Signature signature, int i2, int i3) {
        int i4 = this.count;
        this.count = i4 + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i4, str, signature, makeSourceLoc(i2, i3));
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object obj3, Object obj4) {
        return new JoinPointImpl(staticPart, obj, obj2, new Object[]{obj3, obj4});
    }

    public AdviceSignature makeAdviceSig(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        int parseInt = Integer.parseInt(str, 16);
        Class makeClass = makeClass(str3, this.lookupClassLoader);
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            clsArr[i2] = makeClass(stringTokenizer.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i3 = 0; i3 < countTokens2; i3++) {
            strArr[i3] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i4 = 0; i4 < countTokens3; i4++) {
            clsArr2[i4] = makeClass(stringTokenizer3.nextToken(), this.lookupClassLoader);
        }
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(parseInt, str2, makeClass, clsArr, strArr, clsArr2, makeClass(str7, this.lookupClassLoader));
        adviceSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return adviceSignatureImpl;
    }

    public CatchClauseSignature makeCatchClauseSig(String str, String str2, String str3) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(makeClass(str, this.lookupClassLoader), makeClass(new StringTokenizer(str2, ":").nextToken(), this.lookupClassLoader), new StringTokenizer(str3, ":").nextToken());
        catchClauseSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return catchClauseSignatureImpl;
    }

    public ConstructorSignature makeConstructorSig(String str, String str2, String str3, String str4, String str5) {
        int parseInt = Integer.parseInt(str, 16);
        Class makeClass = makeClass(str2, this.lookupClassLoader);
        StringTokenizer stringTokenizer = new StringTokenizer(str3, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            clsArr[i2] = makeClass(stringTokenizer.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str4, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i3 = 0; i3 < countTokens2; i3++) {
            strArr[i3] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str5, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i4 = 0; i4 < countTokens3; i4++) {
            clsArr2[i4] = makeClass(stringTokenizer3.nextToken(), this.lookupClassLoader);
        }
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(parseInt, makeClass, clsArr, strArr, clsArr2);
        constructorSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return constructorSignatureImpl;
    }

    public JoinPoint.EnclosingStaticPart makeESJP(String str, Signature signature, int i2) {
        int i3 = this.count;
        this.count = i3 + 1;
        return new JoinPointImpl.EnclosingStaticPartImpl(i3, str, signature, makeSourceLoc(i2, -1));
    }

    public FieldSignature makeFieldSig(String str, String str2, String str3, String str4) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(Integer.parseInt(str, 16), str2, makeClass(str3, this.lookupClassLoader), makeClass(str4, this.lookupClassLoader));
        fieldSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return fieldSignatureImpl;
    }

    public InitializerSignature makeInitializerSig(String str, String str2) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(Integer.parseInt(str, 16), makeClass(str2, this.lookupClassLoader));
        initializerSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return initializerSignatureImpl;
    }

    public LockSignature makeLockSig() {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(makeClass("Ljava/lang/Object;", this.lookupClassLoader));
        lockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return lockSignatureImpl;
    }

    public MethodSignature makeMethodSig(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        int parseInt = Integer.parseInt(str, 16);
        Class makeClass = makeClass(str3, this.lookupClassLoader);
        StringTokenizer stringTokenizer = new StringTokenizer(str4, ":");
        int countTokens = stringTokenizer.countTokens();
        Class[] clsArr = new Class[countTokens];
        for (int i2 = 0; i2 < countTokens; i2++) {
            clsArr[i2] = makeClass(stringTokenizer.nextToken(), this.lookupClassLoader);
        }
        StringTokenizer stringTokenizer2 = new StringTokenizer(str5, ":");
        int countTokens2 = stringTokenizer2.countTokens();
        String[] strArr = new String[countTokens2];
        for (int i3 = 0; i3 < countTokens2; i3++) {
            strArr[i3] = stringTokenizer2.nextToken();
        }
        StringTokenizer stringTokenizer3 = new StringTokenizer(str6, ":");
        int countTokens3 = stringTokenizer3.countTokens();
        Class[] clsArr2 = new Class[countTokens3];
        for (int i4 = 0; i4 < countTokens3; i4++) {
            clsArr2[i4] = makeClass(stringTokenizer3.nextToken(), this.lookupClassLoader);
        }
        return new MethodSignatureImpl(parseInt, str2, makeClass, clsArr, strArr, clsArr2, makeClass(str7, this.lookupClassLoader));
    }

    public JoinPoint.StaticPart makeSJP(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i2) {
        MethodSignature makeMethodSig = makeMethodSig(str2, str3, str4, str5, str6, "", str7);
        int i3 = this.count;
        this.count = i3 + 1;
        return new JoinPointImpl.StaticPartImpl(i3, str, makeMethodSig, makeSourceLoc(i2, -1));
    }

    public UnlockSignature makeUnlockSig() {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(makeClass("Ljava/lang/Object;", this.lookupClassLoader));
        unlockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return unlockSignatureImpl;
    }

    public static JoinPoint makeJP(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object[] objArr) {
        return new JoinPointImpl(staticPart, obj, obj2, objArr);
    }

    public JoinPoint.StaticPart makeSJP(String str, Signature signature, SourceLocation sourceLocation) {
        int i2 = this.count;
        this.count = i2 + 1;
        return new JoinPointImpl.StaticPartImpl(i2, str, signature, sourceLocation);
    }

    public LockSignature makeLockSig(Class cls) {
        LockSignatureImpl lockSignatureImpl = new LockSignatureImpl(cls);
        lockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return lockSignatureImpl;
    }

    public JoinPoint.StaticPart makeSJP(String str, Signature signature, int i2, int i3) {
        int i4 = this.count;
        this.count = i4 + 1;
        return new JoinPointImpl.StaticPartImpl(i4, str, signature, makeSourceLoc(i2, i3));
    }

    public UnlockSignature makeUnlockSig(Class cls) {
        UnlockSignatureImpl unlockSignatureImpl = new UnlockSignatureImpl(cls);
        unlockSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return unlockSignatureImpl;
    }

    public InitializerSignature makeInitializerSig(int i2, Class cls) {
        InitializerSignatureImpl initializerSignatureImpl = new InitializerSignatureImpl(i2, cls);
        initializerSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return initializerSignatureImpl;
    }

    public JoinPoint.StaticPart makeSJP(String str, Signature signature, int i2) {
        int i3 = this.count;
        this.count = i3 + 1;
        return new JoinPointImpl.StaticPartImpl(i3, str, signature, makeSourceLoc(i2, -1));
    }

    public FieldSignature makeFieldSig(int i2, String str, Class cls, Class cls2) {
        FieldSignatureImpl fieldSignatureImpl = new FieldSignatureImpl(i2, str, cls, cls2);
        fieldSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return fieldSignatureImpl;
    }

    public CatchClauseSignature makeCatchClauseSig(Class cls, Class cls2, String str) {
        CatchClauseSignatureImpl catchClauseSignatureImpl = new CatchClauseSignatureImpl(cls, cls2, str);
        catchClauseSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return catchClauseSignatureImpl;
    }

    public ConstructorSignature makeConstructorSig(int i2, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2) {
        ConstructorSignatureImpl constructorSignatureImpl = new ConstructorSignatureImpl(i2, cls, clsArr, strArr, clsArr2);
        constructorSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return constructorSignatureImpl;
    }

    public MethodSignature makeMethodSig(int i2, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        MethodSignatureImpl methodSignatureImpl = new MethodSignatureImpl(i2, str, cls, clsArr, strArr, clsArr2, cls2);
        methodSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return methodSignatureImpl;
    }

    public AdviceSignature makeAdviceSig(int i2, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        AdviceSignatureImpl adviceSignatureImpl = new AdviceSignatureImpl(i2, str, cls, clsArr, strArr, clsArr2, cls2);
        adviceSignatureImpl.setLookupClassLoader(this.lookupClassLoader);
        return adviceSignatureImpl;
    }
}
