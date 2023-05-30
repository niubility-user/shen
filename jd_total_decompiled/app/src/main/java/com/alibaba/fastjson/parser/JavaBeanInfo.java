package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.anotation.JSONCreator;
import com.jd.framework.json.anotation.JSONField;
import com.jd.framework.json.anotation.JSONType;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class JavaBeanInfo {
    final Constructor<?> creatorConstructor;
    public final String[] creatorConstructorParameters;
    final Constructor<?> defaultConstructor;
    final int defaultConstructorParameterSize;
    final Method factoryMethod;
    final FieldInfo[] fields;
    final JSONType jsonType;
    boolean ordered = false;
    public final int parserFeatures;
    final FieldInfo[] sortedFields;
    final boolean supportBeanToArray;
    public final String typeKey;
    public final String typeName;

    JavaBeanInfo(Class<?> cls, Constructor<?> constructor, Constructor<?> constructor2, Method method, FieldInfo[] fieldInfoArr, FieldInfo[] fieldInfoArr2, JSONType jSONType, String[] strArr) {
        int i2;
        boolean z;
        int i3 = 0;
        this.defaultConstructor = constructor;
        this.creatorConstructor = constructor2;
        this.factoryMethod = method;
        this.fields = fieldInfoArr;
        this.jsonType = jSONType;
        if (strArr != null && strArr.length == fieldInfoArr.length) {
            this.creatorConstructorParameters = null;
        } else {
            this.creatorConstructorParameters = strArr;
        }
        if (jSONType != null) {
            String typeName = jSONType.typeName();
            this.typeName = typeName.length() <= 0 ? cls.getName() : typeName;
            String typeKey = jSONType.typeKey();
            this.typeKey = typeKey.length() > 0 ? typeKey : null;
            i2 = 0;
            for (Feature feature : jSONType.parseFeatures()) {
                i2 |= feature.mask;
            }
        } else {
            this.typeName = cls.getName();
            this.typeKey = null;
            i2 = 0;
        }
        this.parserFeatures = i2;
        if (jSONType != null) {
            Feature[] parseFeatures = jSONType.parseFeatures();
            z = false;
            for (Feature feature2 : parseFeatures) {
                if (feature2 == Feature.SupportArrayToBean) {
                    z = true;
                }
            }
        } else {
            z = false;
        }
        this.supportBeanToArray = z;
        FieldInfo[] computeSortedFields = computeSortedFields(fieldInfoArr, fieldInfoArr2);
        this.sortedFields = Arrays.equals(fieldInfoArr, computeSortedFields) ? fieldInfoArr : computeSortedFields;
        if (constructor != null) {
            i3 = constructor.getParameterTypes().length;
        } else if (method != null) {
            i3 = method.getParameterTypes().length;
        }
        this.defaultConstructorParameterSize = i3;
    }

    static boolean addField(List<FieldInfo> list, FieldInfo fieldInfo, boolean z) {
        if (!z) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                FieldInfo fieldInfo2 = list.get(i2);
                if (fieldInfo2.name.equals(fieldInfo.name) && (!fieldInfo2.getOnly || fieldInfo.getOnly)) {
                    return false;
                }
            }
        }
        list.add(fieldInfo);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:360:0x07ae, code lost:
        if (r1.length() > 0) goto L363;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0582  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x05ea  */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.reflect.Type[]] */
    /* JADX WARN: Type inference failed for: r0v35, types: [java.lang.reflect.Type[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JavaBeanInfo build(Class<?> cls, int i2, Type type, boolean z, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        Constructor<?> constructor;
        Class<? super Object> cls2;
        Method[] methodArr;
        Method method;
        Method method2;
        Constructor<?> constructor2;
        Constructor<?> constructor3;
        Method[] methodArr2;
        HashMap hashMap;
        Field[] fieldArr;
        JSONField jSONField;
        String str;
        int i3;
        int i4;
        Constructor<?> constructor4;
        JSONField jSONField2;
        JSONField jSONField3;
        Constructor<?> constructor5;
        Method method3;
        Field[] fieldArr2;
        Method[] methodArr3;
        int i5;
        int i6;
        String str2;
        PropertyNamingStrategy propertyNamingStrategy2;
        int i7;
        int i8;
        Class<?> returnType;
        int i9;
        int i10;
        Method[] methodArr4;
        Constructor<?> constructor6;
        Method method4;
        Field[] fieldArr3;
        ArrayList arrayList;
        Method method5;
        int i11;
        int i12;
        String decapitalize;
        Field[] fieldArr4;
        Field field;
        Field field2;
        Field[] fieldArr5;
        HashMap hashMap2;
        PropertyNamingStrategy propertyNamingStrategy3;
        int i13;
        JSONField jSONField4;
        Constructor<?> constructor7;
        Class<? super Object> cls3 = Object.class;
        ArrayList arrayList2 = new ArrayList();
        HashMap hashMap3 = new HashMap();
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        boolean isKotlin = TypeUtils.isKotlin(cls);
        int i14 = i2 & 1024;
        if (i14 != 0 || (declaredConstructors.length != 1 && isKotlin)) {
            constructor = null;
        } else {
            try {
                constructor7 = cls.getDeclaredConstructor(new Class[0]);
            } catch (Exception unused) {
                constructor7 = null;
            }
            if (constructor7 == null && cls.isMemberClass() && (i2 & 8) == 0) {
                int length = declaredConstructors.length;
                int i15 = 0;
                while (i15 < length) {
                    Constructor<?> constructor8 = declaredConstructors[i15];
                    Class<?>[] parameterTypes = constructor8.getParameterTypes();
                    Constructor<?> constructor9 = constructor7;
                    if (parameterTypes.length == 1 && parameterTypes[0].equals(cls.getDeclaringClass())) {
                        constructor = constructor8;
                        break;
                    }
                    i15++;
                    constructor7 = constructor9;
                }
            }
            constructor = constructor7;
        }
        String[] strArr = null;
        if (z) {
            cls2 = cls3;
            methodArr = null;
            method = null;
        } else {
            ArrayList arrayList3 = new ArrayList();
            Method method6 = null;
            for (Class<? super Object> cls4 = cls; cls4 != null && cls4 != cls3; cls4 = cls4.getSuperclass()) {
                Method[] declaredMethods = cls4.getDeclaredMethods();
                int length2 = declaredMethods.length;
                Class<? super Object> cls5 = cls3;
                int i16 = 0;
                while (i16 < length2) {
                    int i17 = length2;
                    Method method7 = declaredMethods[i16];
                    Method[] methodArr5 = declaredMethods;
                    int modifiers = method7.getModifiers();
                    if ((modifiers & 8) != 0) {
                        if (method7.isAnnotationPresent(JSONCreator.class)) {
                            if (method6 != null) {
                                throw new JSONException("multi-json creator");
                            }
                            method6 = method7;
                            i16++;
                            length2 = i17;
                            declaredMethods = methodArr5;
                        }
                    } else if ((modifiers & 2) == 0) {
                        method2 = method6;
                        if ((modifiers & 256) == 0 && (modifiers & 4) == 0) {
                            arrayList3.add(method7);
                        }
                        method6 = method2;
                        i16++;
                        length2 = i17;
                        declaredMethods = methodArr5;
                    }
                    method2 = method6;
                    method6 = method2;
                    i16++;
                    length2 = i17;
                    declaredMethods = methodArr5;
                }
                cls3 = cls5;
            }
            cls2 = cls3;
            Method[] methodArr6 = new Method[arrayList3.size()];
            arrayList3.toArray(methodArr6);
            methodArr = methodArr6;
            method = method6;
        }
        Field[] declaredFields = cls.getDeclaredFields();
        boolean z5 = cls.isInterface() || i14 != 0;
        if (constructor == null || z5) {
            int length3 = declaredConstructors.length;
            int i18 = 0;
            while (true) {
                if (i18 >= length3) {
                    constructor2 = null;
                    break;
                }
                constructor2 = declaredConstructors[i18];
                int i19 = length3;
                if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                    break;
                }
                i18++;
                length3 = i19;
            }
            String str3 = "illegal json creator";
            if (constructor2 != null) {
                TypeUtils.setAccessible(cls, constructor2, i2);
                Class<?>[] parameterTypes2 = constructor2.getParameterTypes();
                Class<?>[] genericParameterTypes = z4 ? constructor2.getGenericParameterTypes() : parameterTypes2;
                Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
                int i20 = 0;
                while (i20 < parameterTypes2.length) {
                    Annotation[] annotationArr = parameterAnnotations[i20];
                    int length4 = annotationArr.length;
                    String str4 = str3;
                    int i21 = 0;
                    while (true) {
                        if (i21 >= length4) {
                            jSONField3 = null;
                            break;
                        }
                        int i22 = length4;
                        Annotation annotation = annotationArr[i21];
                        Annotation[] annotationArr2 = annotationArr;
                        if (annotation instanceof JSONField) {
                            jSONField3 = (JSONField) annotation;
                            break;
                        }
                        i21++;
                        length4 = i22;
                        annotationArr = annotationArr2;
                    }
                    if (jSONField3 != null) {
                        Class<?> cls6 = parameterTypes2[i20];
                        Class<?> cls7 = genericParameterTypes[i20];
                        Field field3 = TypeUtils.getField(cls, jSONField3.name(), declaredFields, hashMap3);
                        if (field3 != null) {
                            TypeUtils.setAccessible(cls, field3, i2);
                        }
                        addField(arrayList2, new FieldInfo(jSONField3.name(), cls, cls6, cls7, field3, jSONField3.ordinal(), SerializerFeature.of(jSONField3.serialzeFeatures())), z);
                        i20++;
                        str3 = str4;
                        constructor2 = constructor2;
                        hashMap3 = hashMap3;
                        declaredFields = declaredFields;
                        parameterTypes2 = parameterTypes2;
                        methodArr = methodArr;
                        constructor = constructor;
                    } else {
                        throw new JSONException(str4);
                    }
                }
                constructor3 = constructor2;
                Field[] fieldArr6 = declaredFields;
                Constructor<?> constructor10 = constructor;
                methodArr2 = methodArr;
                HashMap hashMap4 = hashMap3;
                int size = arrayList2.size();
                FieldInfo[] fieldInfoArr = new FieldInfo[size];
                arrayList2.toArray(fieldInfoArr);
                FieldInfo[] fieldInfoArr2 = new FieldInfo[size];
                System.arraycopy(fieldInfoArr, 0, fieldInfoArr2, 0, size);
                Arrays.sort(fieldInfoArr2);
                if (z2) {
                    JSONType jSONType = (JSONType) cls.getAnnotation(JSONType.class);
                }
                String[] strArr2 = new String[size];
                for (int i23 = 0; i23 < size; i23++) {
                    strArr2[i23] = fieldInfoArr[i23].name;
                }
                strArr = strArr2;
                hashMap = hashMap4;
                fieldArr = fieldArr6;
                constructor4 = constructor10;
            } else {
                constructor3 = constructor2;
                Field[] fieldArr7 = declaredFields;
                Constructor<?> constructor11 = constructor;
                methodArr2 = methodArr;
                HashMap hashMap5 = hashMap3;
                if (method != null) {
                    TypeUtils.setAccessible(cls, method, i2);
                    Class<?>[] parameterTypes3 = method.getParameterTypes();
                    if (parameterTypes3.length > 0) {
                        Class<?>[] genericParameterTypes2 = z4 ? method.getGenericParameterTypes() : parameterTypes3;
                        Annotation[][] parameterAnnotations2 = method.getParameterAnnotations();
                        int i24 = 0;
                        while (i24 < parameterTypes3.length) {
                            Annotation[] annotationArr3 = parameterAnnotations2[i24];
                            int length5 = annotationArr3.length;
                            int i25 = 0;
                            while (true) {
                                if (i25 >= length5) {
                                    jSONField2 = null;
                                    break;
                                }
                                Annotation annotation2 = annotationArr3[i25];
                                if (annotation2 instanceof JSONField) {
                                    jSONField2 = (JSONField) annotation2;
                                    break;
                                }
                                i25++;
                            }
                            if (jSONField2 != null) {
                                HashMap hashMap6 = hashMap5;
                                Field[] fieldArr8 = fieldArr7;
                                addField(arrayList2, new FieldInfo(jSONField2.name(), cls, parameterTypes3[i24], genericParameterTypes2[i24], TypeUtils.getField(cls, jSONField2.name(), fieldArr8, hashMap6), jSONField2.ordinal(), SerializerFeature.of(jSONField2.serialzeFeatures())), z);
                                i24++;
                                hashMap5 = hashMap6;
                                parameterTypes3 = parameterTypes3;
                                fieldArr7 = fieldArr8;
                            } else {
                                throw new JSONException("illegal json creator");
                            }
                        }
                        int size2 = arrayList2.size();
                        FieldInfo[] fieldInfoArr3 = new FieldInfo[size2];
                        arrayList2.toArray(fieldInfoArr3);
                        FieldInfo[] fieldInfoArr4 = new FieldInfo[size2];
                        System.arraycopy(fieldInfoArr3, 0, fieldInfoArr4, 0, size2);
                        Arrays.sort(fieldInfoArr4);
                        return new JavaBeanInfo(cls, null, null, method, fieldInfoArr3, Arrays.equals(fieldInfoArr3, fieldInfoArr4) ? fieldInfoArr3 : fieldInfoArr4, z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null, null);
                    }
                    hashMap = hashMap5;
                    fieldArr = fieldArr7;
                } else {
                    hashMap = hashMap5;
                    Field[] fieldArr9 = fieldArr7;
                    if (z5) {
                        fieldArr = fieldArr9;
                    } else if (isKotlin && declaredConstructors.length > 0) {
                        String[] koltinConstructorParameters = TypeUtils.getKoltinConstructorParameters(cls);
                        if (koltinConstructorParameters != null) {
                            Constructor<?> constructor12 = constructor3;
                            for (Constructor<?> constructor13 : declaredConstructors) {
                                Class<?>[] parameterTypes4 = constructor13.getParameterTypes();
                                if ((parameterTypes4.length <= 0 || !parameterTypes4[parameterTypes4.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) && (constructor12 == null || constructor12.getParameterTypes().length < parameterTypes4.length)) {
                                    constructor12 = constructor13;
                                }
                            }
                            constructor12.setAccessible(true);
                            TypeUtils.setAccessible(cls, constructor12, i2);
                            Class<?>[] parameterTypes5 = constructor12.getParameterTypes();
                            Class<?>[] genericParameterTypes3 = z4 ? constructor12.getGenericParameterTypes() : parameterTypes5;
                            Annotation[][] parameterAnnotations3 = constructor12.getParameterAnnotations();
                            int i26 = 0;
                            while (i26 < parameterTypes5.length) {
                                String str5 = koltinConstructorParameters[i26];
                                Annotation[] annotationArr4 = parameterAnnotations3[i26];
                                int length6 = annotationArr4.length;
                                int i27 = 0;
                                while (true) {
                                    if (i27 >= length6) {
                                        jSONField = null;
                                        break;
                                    }
                                    Annotation annotation3 = annotationArr4[i27];
                                    if (annotation3 instanceof JSONField) {
                                        jSONField = (JSONField) annotation3;
                                        break;
                                    }
                                    i27++;
                                }
                                Class<?> cls8 = parameterTypes5[i26];
                                Class<?> cls9 = genericParameterTypes3[i26];
                                Field[] fieldArr10 = fieldArr9;
                                Field field4 = TypeUtils.getField(cls, str5, fieldArr10, hashMap);
                                if (field4 != null && jSONField == null) {
                                    jSONField = (JSONField) field4.getAnnotation(JSONField.class);
                                }
                                if (jSONField != null) {
                                    i3 = jSONField.ordinal();
                                    i4 = SerializerFeature.of(jSONField.serialzeFeatures());
                                    String name = jSONField.name();
                                    if (name.length() != 0) {
                                        str5 = name;
                                    }
                                    str = str5;
                                } else {
                                    str = str5;
                                    i3 = 0;
                                    i4 = 0;
                                }
                                addField(arrayList2, new FieldInfo(str, cls, cls8, cls9, field4, i3, i4), z);
                                i26++;
                                parameterTypes5 = parameterTypes5;
                                fieldArr9 = fieldArr10;
                            }
                            fieldArr = fieldArr9;
                            int size3 = arrayList2.size();
                            FieldInfo[] fieldInfoArr5 = new FieldInfo[size3];
                            arrayList2.toArray(fieldInfoArr5);
                            FieldInfo[] fieldInfoArr6 = new FieldInfo[size3];
                            System.arraycopy(fieldInfoArr5, 0, fieldInfoArr6, 0, size3);
                            Arrays.sort(fieldInfoArr6);
                            String[] strArr3 = new String[size3];
                            for (int i28 = 0; i28 < size3; i28++) {
                                strArr3[i28] = fieldInfoArr5[i28].name;
                            }
                            strArr = strArr3;
                            constructor3 = constructor12;
                            constructor4 = constructor11;
                        } else {
                            throw new JSONException("default constructor not found. " + cls);
                        }
                    } else {
                        throw new JSONException("default constructor not found. " + cls);
                    }
                }
                constructor4 = constructor11;
            }
        } else {
            fieldArr = declaredFields;
            methodArr2 = methodArr;
            hashMap = hashMap3;
            constructor3 = null;
            constructor4 = constructor;
        }
        if (constructor4 != null) {
            TypeUtils.setAccessible(cls, constructor4, i2);
        }
        int i29 = 4;
        if (z) {
            constructor5 = constructor4;
            method3 = method;
            fieldArr2 = fieldArr;
            methodArr3 = methodArr2;
        } else {
            Method[] methodArr7 = methodArr2;
            int length7 = methodArr7.length;
            int i30 = 0;
            while (i30 < length7) {
                Method method8 = methodArr7[i30];
                String name2 = method8.getName();
                if (name2.length() >= i29 && (((returnType = method8.getReturnType()) == Void.TYPE || returnType == method8.getDeclaringClass()) && method8.getParameterTypes().length == 1)) {
                    JSONField jSONField5 = z3 ? (JSONField) method8.getAnnotation(JSONField.class) : null;
                    if (jSONField5 == null && z3) {
                        jSONField5 = TypeUtils.getSupperMethodAnnotation(cls, method8);
                    }
                    JSONField jSONField6 = jSONField5;
                    if (jSONField6 == null) {
                        i9 = i30;
                        i10 = length7;
                        methodArr4 = methodArr7;
                        constructor6 = constructor4;
                        method4 = method;
                        fieldArr3 = fieldArr;
                        arrayList = arrayList2;
                        method5 = method8;
                        i11 = 0;
                        i12 = 0;
                    } else if (jSONField6.deserialize()) {
                        int ordinal = jSONField6.ordinal();
                        i12 = SerializerFeature.of(jSONField6.serialzeFeatures());
                        if (jSONField6.name().length() != 0) {
                            i9 = i30;
                            i10 = length7;
                            methodArr4 = methodArr7;
                            constructor6 = constructor4;
                            fieldArr3 = fieldArr;
                            method4 = method;
                            arrayList = arrayList2;
                            addField(arrayList, new FieldInfo(jSONField6.name(), method8, null, cls, type, ordinal, i12, jSONField6, null, z4), z);
                            TypeUtils.setAccessible(cls, method8, i2);
                            hashMap2 = hashMap;
                            fieldArr5 = fieldArr3;
                            i30 = i9 + 1;
                            arrayList2 = arrayList;
                            constructor4 = constructor6;
                            length7 = i10;
                            method = method4;
                            hashMap = hashMap2;
                            methodArr7 = methodArr4;
                            fieldArr = fieldArr5;
                            i29 = 4;
                        } else {
                            i9 = i30;
                            i10 = length7;
                            methodArr4 = methodArr7;
                            constructor6 = constructor4;
                            method4 = method;
                            fieldArr3 = fieldArr;
                            arrayList = arrayList2;
                            method5 = method8;
                            i11 = ordinal;
                        }
                    }
                    if (name2.startsWith("set")) {
                        char charAt = name2.charAt(3);
                        if (Character.isUpperCase(charAt)) {
                            if (TypeUtils.compatibleWithJavaBean) {
                                decapitalize = TypeUtils.decapitalize(name2.substring(3));
                                fieldArr4 = fieldArr3;
                                field = TypeUtils.getField(cls, decapitalize, fieldArr4, hashMap);
                                if (field == null && method5.getParameterTypes()[0] == Boolean.TYPE) {
                                    field = TypeUtils.getField(cls, "is" + Character.toUpperCase(decapitalize.charAt(0)) + decapitalize.substring(1), fieldArr4, hashMap);
                                }
                                field2 = field;
                                if (field2 != null) {
                                    JSONField jSONField7 = z3 ? (JSONField) field2.getAnnotation(JSONField.class) : null;
                                    if (jSONField7 != null) {
                                        i11 = jSONField7.ordinal();
                                        i12 = SerializerFeature.of(jSONField7.serialzeFeatures());
                                        if (jSONField7.name().length() != 0) {
                                            hashMap2 = hashMap;
                                            fieldArr5 = fieldArr4;
                                            addField(arrayList, new FieldInfo(jSONField7.name(), method5, field2, cls, type, i11, i12, jSONField6, jSONField7, z4), z);
                                            i30 = i9 + 1;
                                            arrayList2 = arrayList;
                                            constructor4 = constructor6;
                                            length7 = i10;
                                            method = method4;
                                            hashMap = hashMap2;
                                            methodArr7 = methodArr4;
                                            fieldArr = fieldArr5;
                                            i29 = 4;
                                        } else {
                                            fieldArr5 = fieldArr4;
                                            hashMap2 = hashMap;
                                            if (jSONField6 == null) {
                                                propertyNamingStrategy3 = propertyNamingStrategy;
                                                i13 = i11;
                                                jSONField4 = jSONField7;
                                                int i31 = i12;
                                                if (propertyNamingStrategy3 != null) {
                                                    decapitalize = propertyNamingStrategy3.translate(decapitalize);
                                                }
                                                addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i13, i31, jSONField4, null, z4), z);
                                                TypeUtils.setAccessible(cls, method5, i2);
                                                i30 = i9 + 1;
                                                arrayList2 = arrayList;
                                                constructor4 = constructor6;
                                                length7 = i10;
                                                method = method4;
                                                hashMap = hashMap2;
                                                methodArr7 = methodArr4;
                                                fieldArr = fieldArr5;
                                                i29 = 4;
                                            }
                                            propertyNamingStrategy3 = propertyNamingStrategy;
                                            i13 = i11;
                                            jSONField4 = jSONField6;
                                            int i312 = i12;
                                            if (propertyNamingStrategy3 != null) {
                                            }
                                            addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i13, i312, jSONField4, null, z4), z);
                                            TypeUtils.setAccessible(cls, method5, i2);
                                            i30 = i9 + 1;
                                            arrayList2 = arrayList;
                                            constructor4 = constructor6;
                                            length7 = i10;
                                            method = method4;
                                            hashMap = hashMap2;
                                            methodArr7 = methodArr4;
                                            fieldArr = fieldArr5;
                                            i29 = 4;
                                        }
                                    }
                                }
                                fieldArr5 = fieldArr4;
                                hashMap2 = hashMap;
                                propertyNamingStrategy3 = propertyNamingStrategy;
                                i13 = i11;
                                jSONField4 = jSONField6;
                                int i3122 = i12;
                                if (propertyNamingStrategy3 != null) {
                                }
                                addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i13, i3122, jSONField4, null, z4), z);
                                TypeUtils.setAccessible(cls, method5, i2);
                                i30 = i9 + 1;
                                arrayList2 = arrayList;
                                constructor4 = constructor6;
                                length7 = i10;
                                method = method4;
                                hashMap = hashMap2;
                                methodArr7 = methodArr4;
                                fieldArr = fieldArr5;
                                i29 = 4;
                            } else {
                                decapitalize = Character.toLowerCase(name2.charAt(3)) + name2.substring(4);
                            }
                        } else if (charAt == '_') {
                            decapitalize = name2.substring(4);
                        } else if (charAt == 'f') {
                            decapitalize = name2.substring(3);
                        } else if (name2.length() >= 5 && Character.isUpperCase(name2.charAt(4))) {
                            decapitalize = TypeUtils.decapitalize(name2.substring(3));
                        }
                        fieldArr4 = fieldArr3;
                        field = TypeUtils.getField(cls, decapitalize, fieldArr4, hashMap);
                        if (field == null) {
                            field = TypeUtils.getField(cls, "is" + Character.toUpperCase(decapitalize.charAt(0)) + decapitalize.substring(1), fieldArr4, hashMap);
                        }
                        field2 = field;
                        if (field2 != null) {
                        }
                        fieldArr5 = fieldArr4;
                        hashMap2 = hashMap;
                        propertyNamingStrategy3 = propertyNamingStrategy;
                        i13 = i11;
                        jSONField4 = jSONField6;
                        int i31222 = i12;
                        if (propertyNamingStrategy3 != null) {
                        }
                        addField(arrayList, new FieldInfo(decapitalize, method5, null, cls, type, i13, i31222, jSONField4, null, z4), z);
                        TypeUtils.setAccessible(cls, method5, i2);
                        i30 = i9 + 1;
                        arrayList2 = arrayList;
                        constructor4 = constructor6;
                        length7 = i10;
                        method = method4;
                        hashMap = hashMap2;
                        methodArr7 = methodArr4;
                        fieldArr = fieldArr5;
                        i29 = 4;
                    }
                    hashMap2 = hashMap;
                    fieldArr5 = fieldArr3;
                    i30 = i9 + 1;
                    arrayList2 = arrayList;
                    constructor4 = constructor6;
                    length7 = i10;
                    method = method4;
                    hashMap = hashMap2;
                    methodArr7 = methodArr4;
                    fieldArr = fieldArr5;
                    i29 = 4;
                }
                i9 = i30;
                i10 = length7;
                methodArr4 = methodArr7;
                constructor6 = constructor4;
                method4 = method;
                fieldArr5 = fieldArr;
                arrayList = arrayList2;
                hashMap2 = hashMap;
                i30 = i9 + 1;
                arrayList2 = arrayList;
                constructor4 = constructor6;
                length7 = i10;
                method = method4;
                hashMap = hashMap2;
                methodArr7 = methodArr4;
                fieldArr = fieldArr5;
                i29 = 4;
            }
            methodArr3 = methodArr7;
            constructor5 = constructor4;
            method3 = method;
            fieldArr2 = fieldArr;
        }
        ArrayList arrayList4 = arrayList2;
        Field[] fieldArr11 = fieldArr2;
        ArrayList<Field> arrayList5 = new ArrayList(fieldArr11.length);
        for (Field field5 : fieldArr11) {
            int modifiers2 = field5.getModifiers();
            if ((modifiers2 & 8) == 0) {
                if ((modifiers2 & 16) != 0) {
                    Class<?> type2 = field5.getType();
                    if (!(Map.class.isAssignableFrom(type2) || Collection.class.isAssignableFrom(type2))) {
                    }
                }
                if ((field5.getModifiers() & 1) != 0) {
                    arrayList5.add(field5);
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        while (superclass != null) {
            Class<? super Object> cls10 = cls2;
            if (superclass == cls10) {
                break;
            }
            for (Field field6 : superclass.getDeclaredFields()) {
                int modifiers3 = field6.getModifiers();
                if ((modifiers3 & 8) == 0) {
                    if ((modifiers3 & 16) != 0) {
                        Class<?> type3 = field6.getType();
                        if (!(Map.class.isAssignableFrom(type3) || Collection.class.isAssignableFrom(type3))) {
                        }
                    }
                    if ((modifiers3 & 1) != 0) {
                        arrayList5.add(field6);
                    }
                }
            }
            superclass = superclass.getSuperclass();
            cls2 = cls10;
        }
        for (Field field7 : arrayList5) {
            String name3 = field7.getName();
            int size4 = arrayList4.size();
            boolean z6 = false;
            for (int i32 = 0; i32 < size4; i32++) {
                if (((FieldInfo) arrayList4.get(i32)).name.equals(name3)) {
                    z6 = true;
                }
            }
            if (!z6) {
                JSONField jSONField8 = z3 ? (JSONField) field7.getAnnotation(JSONField.class) : null;
                if (jSONField8 != null) {
                    int ordinal2 = jSONField8.ordinal();
                    int of = SerializerFeature.of(jSONField8.serialzeFeatures());
                    if (jSONField8.name().length() != 0) {
                        name3 = jSONField8.name();
                    }
                    propertyNamingStrategy2 = propertyNamingStrategy;
                    i7 = ordinal2;
                    i8 = of;
                } else {
                    propertyNamingStrategy2 = propertyNamingStrategy;
                    i7 = 0;
                    i8 = 0;
                }
                if (propertyNamingStrategy2 != null) {
                    name3 = propertyNamingStrategy2.translate(name3);
                }
                TypeUtils.setAccessible(cls, field7, i2);
                addField(arrayList4, new FieldInfo(name3, null, field7, cls, type, i7, i8, null, jSONField8, z4), z);
            }
        }
        if (!z) {
            Method[] methodArr8 = methodArr3;
            int length8 = methodArr8.length;
            int i33 = 0;
            while (i33 < length8) {
                Method method9 = methodArr8[i33];
                String name4 = method9.getName();
                if (name4.length() >= 4 && name4.startsWith(IMantoServerRequester.GET) && Character.isUpperCase(name4.charAt(3)) && method9.getParameterTypes().length == 0) {
                    Class<?> returnType2 = method9.getReturnType();
                    if (Collection.class.isAssignableFrom(returnType2) || Map.class.isAssignableFrom(returnType2)) {
                        JSONField jSONField9 = z3 ? (JSONField) method9.getAnnotation(JSONField.class) : null;
                        if (jSONField9 != null) {
                            str2 = jSONField9.name();
                        }
                        str2 = Character.toLowerCase(name4.charAt(3)) + name4.substring(4);
                        JSONField jSONField10 = jSONField9;
                        i5 = i33;
                        i6 = length8;
                        addField(arrayList4, new FieldInfo(str2, method9, null, cls, type, 0, 0, jSONField10, null, z4), z);
                        TypeUtils.setAccessible(cls, method9, i2);
                        i33 = i5 + 1;
                        length8 = i6;
                    }
                }
                i5 = i33;
                i6 = length8;
                i33 = i5 + 1;
                length8 = i6;
            }
        }
        int size5 = arrayList4.size();
        FieldInfo[] fieldInfoArr7 = new FieldInfo[size5];
        arrayList4.toArray(fieldInfoArr7);
        FieldInfo[] fieldInfoArr8 = new FieldInfo[size5];
        System.arraycopy(fieldInfoArr7, 0, fieldInfoArr8, 0, size5);
        Arrays.sort(fieldInfoArr8);
        return new JavaBeanInfo(cls, constructor5, constructor3, method3, fieldInfoArr7, fieldInfoArr8, z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null, strArr);
    }

    private FieldInfo[] computeSortedFields(FieldInfo[] fieldInfoArr, FieldInfo[] fieldInfoArr2) {
        String[] orders;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        JSONType jSONType = this.jsonType;
        if (jSONType != null && (orders = jSONType.orders()) != null && orders.length != 0) {
            int i2 = 0;
            while (true) {
                if (i2 >= orders.length) {
                    z = true;
                    break;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= fieldInfoArr2.length) {
                        z4 = false;
                        break;
                    } else if (fieldInfoArr2[i3].name.equals(orders[i2])) {
                        z4 = true;
                        break;
                    } else {
                        i3++;
                    }
                }
                if (!z4) {
                    z = false;
                    break;
                }
                i2++;
            }
            if (!z) {
                return fieldInfoArr2;
            }
            if (orders.length == fieldInfoArr.length) {
                int i4 = 0;
                while (true) {
                    if (i4 >= orders.length) {
                        z3 = true;
                        break;
                    } else if (!fieldInfoArr2[i4].name.equals(orders[i4])) {
                        z3 = false;
                        break;
                    } else {
                        i4++;
                    }
                }
                if (z3) {
                    return fieldInfoArr2;
                }
                FieldInfo[] fieldInfoArr3 = new FieldInfo[fieldInfoArr2.length];
                for (int i5 = 0; i5 < orders.length; i5++) {
                    int i6 = 0;
                    while (true) {
                        if (i6 >= fieldInfoArr2.length) {
                            break;
                        } else if (fieldInfoArr2[i6].name.equals(orders[i5])) {
                            fieldInfoArr3[i5] = fieldInfoArr2[i6];
                            break;
                        } else {
                            i6++;
                        }
                    }
                }
                this.ordered = true;
                return fieldInfoArr3;
            }
            int length = fieldInfoArr2.length;
            FieldInfo[] fieldInfoArr4 = new FieldInfo[length];
            for (int i7 = 0; i7 < orders.length; i7++) {
                int i8 = 0;
                while (true) {
                    if (i8 >= fieldInfoArr2.length) {
                        break;
                    } else if (fieldInfoArr2[i8].name.equals(orders[i7])) {
                        fieldInfoArr4[i7] = fieldInfoArr2[i8];
                        break;
                    } else {
                        i8++;
                    }
                }
            }
            int length2 = orders.length;
            for (int i9 = 0; i9 < fieldInfoArr2.length; i9++) {
                for (int i10 = 0; i10 < length && i10 < length2; i10++) {
                    if (fieldInfoArr4[i9].equals(fieldInfoArr2[i10])) {
                        z2 = true;
                        break;
                    }
                }
                z2 = false;
                if (!z2) {
                    fieldInfoArr4[length2] = fieldInfoArr2[i9];
                    length2++;
                }
            }
            this.ordered = true;
        }
        return fieldInfoArr2;
    }
}
