package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.facebook.react.devsupport.StackTraceHelper;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.anotation.JSONField;
import com.jd.framework.json.anotation.JSONType;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public class TypeUtils {
    public static boolean compatibleWithJavaBean = false;
    private static volatile Map<Class, String[]> kotlinIgnores = null;
    private static volatile boolean kotlinIgnores_error = false;
    private static volatile boolean kotlin_class_klass_error = false;
    private static volatile boolean kotlin_error = false;
    private static volatile Constructor kotlin_kclass_constructor = null;
    private static volatile Method kotlin_kclass_getConstructors = null;
    private static volatile Method kotlin_kfunction_getParameters = null;
    private static volatile Method kotlin_kparameter_getName = null;
    private static volatile Class kotlin_metadata = null;
    private static volatile boolean kotlin_metadata_error = false;
    private static ConcurrentMap<String, Class<?>> mappings = null;
    private static boolean setAccessibleEnable = true;

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        mappings = concurrentHashMap;
        concurrentHashMap.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put(DYConstants.DY_CHAR, Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put("[B", byte[].class);
        mappings.put("[S", short[].class);
        mappings.put("[I", int[].class);
        mappings.put("[J", long[].class);
        mappings.put("[F", float[].class);
        mappings.put("[D", double[].class);
        mappings.put("[C", char[].class);
        mappings.put("[Z", boolean[].class);
        mappings.put(HashMap.class.getName(), HashMap.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
        T t;
        if (obj == 0) {
            return null;
        }
        if (cls != null) {
            if (cls == obj.getClass()) {
                return obj;
            }
            if (obj instanceof Map) {
                if (cls == Map.class) {
                    return obj;
                }
                Map map = (Map) obj;
                return (cls != Object.class || map.containsKey(JDJSON.DEFAULT_TYPE_KEY)) ? (T) castToJavaBean(map, cls, parserConfig) : obj;
            }
            int i2 = 0;
            if (cls.isArray()) {
                if (obj instanceof Collection) {
                    Collection collection = (Collection) obj;
                    T t2 = (T) Array.newInstance(cls.getComponentType(), collection.size());
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        Array.set(t2, i2, cast(it.next(), (Class) cls.getComponentType(), parserConfig));
                        i2++;
                    }
                    return t2;
                } else if (cls == byte[].class) {
                    return (T) castToBytes(obj);
                }
            }
            if (cls.isAssignableFrom(obj.getClass())) {
                return obj;
            }
            if (cls != Boolean.TYPE && cls != Boolean.class) {
                if (cls != Byte.TYPE && cls != Byte.class) {
                    if ((cls == Character.TYPE || cls == Character.class) && (obj instanceof String)) {
                        String str = (String) obj;
                        if (str.length() == 1) {
                            return (T) Character.valueOf(str.charAt(0));
                        }
                    }
                    if (cls != Short.TYPE && cls != Short.class) {
                        if (cls != Integer.TYPE && cls != Integer.class) {
                            if (cls != Long.TYPE && cls != Long.class) {
                                if (cls != Float.TYPE && cls != Float.class) {
                                    if (cls != Double.TYPE && cls != Double.class) {
                                        if (cls == String.class) {
                                            return (T) castToString(obj);
                                        }
                                        if (cls == BigDecimal.class) {
                                            return (T) castToBigDecimal(obj);
                                        }
                                        if (cls == BigInteger.class) {
                                            return (T) castToBigInteger(obj);
                                        }
                                        if (cls == Date.class) {
                                            return (T) castToDate(obj);
                                        }
                                        if (cls.isEnum()) {
                                            return (T) castToEnum(obj, cls, parserConfig);
                                        }
                                        if (Calendar.class.isAssignableFrom(cls)) {
                                            Date castToDate = castToDate(obj);
                                            if (cls == Calendar.class) {
                                                t = (T) Calendar.getInstance(JDJSON.defaultTimeZone, JDJSON.defaultLocale);
                                            } else {
                                                try {
                                                    t = (T) ((Calendar) cls.newInstance());
                                                } catch (Exception e2) {
                                                    throw new JSONException("can not cast to : " + cls.getName(), e2);
                                                }
                                            }
                                            ((Calendar) t).setTime(castToDate);
                                            return t;
                                        }
                                        if (obj instanceof String) {
                                            String str2 = (String) obj;
                                            if (str2.length() == 0 || DYConstants.DY_NULL_STR.equals(str2)) {
                                                return null;
                                            }
                                            if (cls == Currency.class) {
                                                return (T) Currency.getInstance(str2);
                                            }
                                        }
                                        throw new JSONException("can not cast to : " + cls.getName());
                                    }
                                    return (T) castToDouble(obj);
                                }
                                return (T) castToFloat(obj);
                            }
                            return (T) castToLong(obj);
                        }
                        return (T) castToInt(obj);
                    }
                    return (T) castToShort(obj);
                }
                return (T) castToByte(obj);
            }
            return (T) castToBoolean(obj);
        }
        throw new IllegalArgumentException("clazz is null");
    }

    public static final BigDecimal castToBigDecimal(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String obj2 = obj.toString();
        if (obj2.length() == 0 || DYConstants.DY_NULL_STR.equals(obj2)) {
            return null;
        }
        try {
            return new BigDecimal(obj2);
        } catch (NumberFormatException e2) {
            if (ParserConfig.debug) {
                throw e2;
            }
            return null;
        }
    }

    public static final BigInteger castToBigInteger(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if (!(obj instanceof Float) && !(obj instanceof Double)) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || DYConstants.DY_NULL_STR.equals(obj2)) {
                return null;
            }
            try {
                return new BigInteger(obj2);
            } catch (NumberFormatException e2) {
                if (ParserConfig.debug) {
                    throw e2;
                }
                return null;
            }
        }
        return BigInteger.valueOf(((Number) obj).longValue());
    }

    public static final Boolean castToBoolean(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Number) {
            return Boolean.valueOf(((Number) obj).intValue() == 1);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || DYConstants.DY_NULL_STR.equals(str)) {
                return null;
            }
            if (!DYConstants.DY_TRUE.equalsIgnoreCase(str) && !"1".equals(str)) {
                if (DYConstants.DY_FALSE.equalsIgnoreCase(str) || "0".equals(str)) {
                    return Boolean.FALSE;
                }
            } else {
                return Boolean.TRUE;
            }
        }
        if (ParserConfig.debug) {
            throw new JSONException("can not cast to boolean, value : " + obj);
        }
        return null;
    }

    public static final Byte castToByte(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || DYConstants.DY_NULL_STR.equals(str)) {
                return null;
            }
            try {
                return Byte.valueOf(Byte.parseByte(str));
            } catch (NumberFormatException e2) {
                if (ParserConfig.debug) {
                    throw e2;
                }
                return null;
            }
        } else if (ParserConfig.debug) {
            throw new JSONException("can not cast to byte, value : " + obj);
        } else {
            return null;
        }
    }

    public static final byte[] castToBytes(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            return JSONLexer.decodeFast(str, 0, str.length());
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static final Character castToChar(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0) {
                return null;
            }
            if (str.length() != 1) {
                if (ParserConfig.debug) {
                    throw new JSONException("can not cast to char, value : " + obj);
                }
                return null;
            }
            try {
                return Character.valueOf(str.charAt(0));
            } catch (Exception e2) {
                if (ParserConfig.debug) {
                    throw e2;
                }
                return null;
            }
        } else if (ParserConfig.debug) {
            throw new JSONException("can not cast to char, value : " + obj);
        } else {
            return null;
        }
    }

    public static final Date castToDate(Object obj) {
        String str;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        long longValue = obj instanceof Number ? ((Number) obj).longValue() : -1L;
        if (obj instanceof String) {
            String str2 = (String) obj;
            if (str2.indexOf(45) != -1) {
                if (str2.length() == JDJSON.DEFFAULT_DATE_FORMAT.length()) {
                    str = JDJSON.DEFFAULT_DATE_FORMAT;
                } else if (str2.length() == 10) {
                    str = "yyyy-MM-dd";
                } else if (str2.length() == 19) {
                    str = "yyyy-MM-dd HH:mm:ss";
                } else {
                    str = (str2.length() == 29 && str2.charAt(26) == ':' && str2.charAt(28) == '0') ? "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" : "yyyy-MM-dd HH:mm:ss.SSS";
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, JDJSON.defaultLocale);
                simpleDateFormat.setTimeZone(JDJSON.defaultTimeZone);
                try {
                    return simpleDateFormat.parse(str2);
                } catch (ParseException unused) {
                    throw new JSONException("can not cast to Date, value : " + str2);
                }
            } else if (str2.length() == 0 || DYConstants.DY_NULL_STR.equals(str2)) {
                return null;
            } else {
                longValue = Long.parseLong(str2);
            }
        }
        if (longValue >= 0) {
            return new Date(longValue);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static final Double castToDouble(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || DYConstants.DY_NULL_STR.equals(obj2)) {
                return null;
            }
            try {
                return Double.valueOf(Double.parseDouble(obj2));
            } catch (NumberFormatException e2) {
                if (ParserConfig.debug) {
                    throw e2;
                }
                return null;
            }
        } else if (ParserConfig.debug) {
            throw new JSONException("can not cast to double, value : " + obj);
        } else {
            return null;
        }
    }

    public static final <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                return (T) Enum.valueOf(cls, str);
            }
            if (obj instanceof Number) {
                int intValue = ((Number) obj).intValue();
                T[] enumConstants = cls.getEnumConstants();
                if (intValue < enumConstants.length) {
                    return enumConstants[intValue];
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Exception e2) {
            throw new JSONException("can not cast to : " + cls.getName(), e2);
        }
    }

    public static final Float castToFloat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (obj instanceof String) {
            String obj2 = obj.toString();
            if (obj2.length() == 0 || DYConstants.DY_NULL_STR.equals(obj2)) {
                return null;
            }
            try {
                return Float.valueOf(Float.parseFloat(obj2));
            } catch (NumberFormatException e2) {
                if (ParserConfig.debug) {
                    throw e2;
                }
                return null;
            }
        } else if (ParserConfig.debug) {
            throw new JSONException("can not cast to float, value : " + obj);
        } else {
            return null;
        }
    }

    public static final Integer castToInt(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || DYConstants.DY_NULL_STR.equals(str)) {
                return null;
            }
            try {
                return Integer.valueOf(Integer.parseInt(str));
            } catch (NumberFormatException e2) {
                if (ParserConfig.debug) {
                    throw e2;
                }
                return null;
            }
        } else if (ParserConfig.debug) {
            throw new JSONException("can not cast to int, value : " + obj);
        } else {
            return null;
        }
    }

    public static final <T> T castToJavaBean(Object obj, Class<T> cls) {
        return (T) cast(obj, (Class) cls, ParserConfig.global);
    }

    public static final Long castToLong(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || DYConstants.DY_NULL_STR.equals(str)) {
                return null;
            }
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                JSONLexer jSONLexer = new JSONLexer(str);
                Calendar calendar = jSONLexer.scanISO8601DateIfMatch(false) ? jSONLexer.calendar : null;
                jSONLexer.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        if (ParserConfig.debug) {
            throw new JSONException("can not cast to long, value : " + obj);
        }
        return null;
    }

    public static final Short castToShort(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || DYConstants.DY_NULL_STR.equals(str)) {
                return null;
            }
            try {
                return Short.valueOf(Short.parseShort(str));
            } catch (Exception e2) {
                if (ParserConfig.debug) {
                    throw e2;
                }
                return null;
            }
        } else if (ParserConfig.debug) {
            throw new JSONException("can not cast to short, value : " + obj);
        } else {
            return null;
        }
    }

    public static final String castToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:146:0x02fe, code lost:
        if (r0 == null) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0426, code lost:
        if (r0 == null) goto L208;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:284:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x05d6  */
    /* JADX WARN: Type inference failed for: r18v15, types: [java.lang.annotation.Annotation[][]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<FieldInfo> computeGetters(Class<?> cls, int i2, boolean z, JSONType jSONType, Map<String, String> map, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        String[] strArr;
        boolean z5;
        boolean z6;
        int i3;
        int i4;
        Iterator it;
        LinkedHashMap linkedHashMap;
        String[] strArr2;
        Field[] fieldArr;
        Constructor<?>[] constructorArr;
        JSONField[][] jSONFieldArr;
        JSONField jSONField;
        String[] strArr3;
        short[] sArr;
        HashMap hashMap;
        Class<? super Object> cls2;
        Field[] fieldArr2;
        int i5;
        int i6;
        String str;
        Method method;
        HashMap hashMap2;
        Field[] fieldArr3;
        int i7;
        int i8;
        Map<String, String> map2;
        LinkedHashMap linkedHashMap2;
        Field[] fieldArr4;
        String substring;
        PropertyNamingStrategy propertyNamingStrategy2;
        int i9;
        int i10;
        JSONField jSONField2;
        String decapitalize;
        int i11;
        int i12;
        JSONField jSONField3;
        JSONField jSONField4;
        String[] strArr4;
        Constructor<?>[] constructorArr2;
        String[] strArr5;
        JSONField[] jSONFieldArr2;
        Method[] methodArr;
        Class<?> cls3 = cls;
        int i13 = i2;
        JSONType jSONType2 = jSONType;
        Map<String, String> map3 = map;
        PropertyNamingStrategy propertyNamingStrategy3 = propertyNamingStrategy;
        Class<? super Object> cls4 = Object.class;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        HashMap hashMap3 = new HashMap();
        Field[] declaredFields = cls.getDeclaredFields();
        if (!z) {
            boolean isKotlin = isKotlin(cls);
            ArrayList<Method> arrayList = new ArrayList();
            for (Class<? super Object> cls5 = cls3; cls5 != null && cls5 != cls4; cls5 = cls5.getSuperclass()) {
                Method[] declaredMethods = cls5.getDeclaredMethods();
                int length = declaredMethods.length;
                int i14 = 0;
                while (i14 < length) {
                    Method method2 = declaredMethods[i14];
                    int modifiers = method2.getModifiers();
                    if ((modifiers & 8) == 0 && (modifiers & 2) == 0) {
                        methodArr = declaredMethods;
                        if ((modifiers & 256) == 0 && (modifiers & 4) == 0 && !method2.getReturnType().equals(Void.TYPE) && method2.getParameterTypes().length == 0 && method2.getReturnType() != ClassLoader.class && method2.getDeclaringClass() != cls4) {
                            arrayList.add(method2);
                        }
                    } else {
                        methodArr = declaredMethods;
                    }
                    i14++;
                    declaredMethods = methodArr;
                }
            }
            Constructor<?>[] constructorArr3 = null;
            String[] strArr6 = null;
            short[] sArr2 = null;
            JSONField[][] jSONFieldArr3 = null;
            for (Method method3 : arrayList) {
                String name = method3.getName();
                if (name.equals("getMetaClass")) {
                    strArr2 = strArr6;
                    if (method3.getReturnType().getName().equals("groovy.lang.MetaClass")) {
                        strArr6 = strArr2;
                    }
                } else {
                    strArr2 = strArr6;
                }
                JSONField jSONField5 = z3 ? (JSONField) method3.getAnnotation(JSONField.class) : null;
                if (jSONField5 == null && z3) {
                    jSONField5 = getSupperMethodAnnotation(cls3, method3);
                }
                if (isKotlin && isKotlinIgnore(cls3, name)) {
                    strArr6 = strArr2;
                } else {
                    Class<? super Object> cls6 = cls4;
                    if (jSONField5 == null && isKotlin) {
                        if (constructorArr3 == null) {
                            constructorArr3 = cls.getDeclaredConstructors();
                            JSONField[][] jSONFieldArr4 = jSONFieldArr3;
                            if (constructorArr3.length == 1) {
                                ?? parameterAnnotations = constructorArr3[0].getParameterAnnotations();
                                String[] koltinConstructorParameters = getKoltinConstructorParameters(cls);
                                if (koltinConstructorParameters != null) {
                                    String[] strArr7 = new String[koltinConstructorParameters.length];
                                    System.arraycopy(koltinConstructorParameters, 0, strArr7, 0, koltinConstructorParameters.length);
                                    Arrays.sort(strArr7);
                                    short[] sArr3 = new short[koltinConstructorParameters.length];
                                    jSONField4 = jSONField5;
                                    for (short s = 0; s < koltinConstructorParameters.length; s = (short) (s + 1)) {
                                        sArr3[Arrays.binarySearch(strArr7, koltinConstructorParameters[s])] = s;
                                    }
                                    strArr4 = strArr7;
                                    jSONFieldArr3 = parameterAnnotations;
                                    sArr2 = sArr3;
                                    constructorArr3 = constructorArr3;
                                } else {
                                    jSONField4 = jSONField5;
                                    strArr4 = koltinConstructorParameters;
                                    jSONFieldArr3 = parameterAnnotations;
                                }
                            } else {
                                jSONField4 = jSONField5;
                                strArr4 = strArr2;
                                jSONFieldArr3 = jSONFieldArr4;
                            }
                        } else {
                            jSONField4 = jSONField5;
                            strArr4 = strArr2;
                        }
                        if (strArr4 == null || sArr2 == null || !name.startsWith(IMantoServerRequester.GET)) {
                            constructorArr2 = constructorArr3;
                            strArr5 = strArr4;
                            fieldArr = declaredFields;
                        } else {
                            String decapitalize2 = decapitalize(name.substring(3));
                            int binarySearch = Arrays.binarySearch(strArr4, decapitalize2);
                            constructorArr2 = constructorArr3;
                            fieldArr = declaredFields;
                            if (binarySearch < 0) {
                                int i15 = 0;
                                while (true) {
                                    if (i15 >= strArr4.length) {
                                        break;
                                    } else if (decapitalize2.equalsIgnoreCase(strArr4[i15])) {
                                        binarySearch = i15;
                                        break;
                                    } else {
                                        i15++;
                                    }
                                }
                            }
                            if (binarySearch >= 0 && (jSONFieldArr2 = jSONFieldArr3[sArr2[binarySearch]]) != null) {
                                int length2 = jSONFieldArr2.length;
                                int i16 = 0;
                                while (i16 < length2) {
                                    String[] strArr8 = strArr4;
                                    JSONField jSONField6 = jSONFieldArr2[i16];
                                    JSONField[] jSONFieldArr5 = jSONFieldArr2;
                                    if (jSONField6 instanceof JSONField) {
                                        jSONField = jSONField6;
                                        sArr = sArr2;
                                        jSONFieldArr = jSONFieldArr3;
                                        strArr3 = strArr8;
                                        constructorArr = constructorArr2;
                                        break;
                                    }
                                    i16++;
                                    strArr4 = strArr8;
                                    jSONFieldArr2 = jSONFieldArr5;
                                }
                            }
                            strArr5 = strArr4;
                        }
                        jSONFieldArr = jSONFieldArr3;
                        strArr3 = strArr5;
                        jSONField = jSONField4;
                        constructorArr = constructorArr2;
                    } else {
                        fieldArr = declaredFields;
                        constructorArr = constructorArr3;
                        jSONFieldArr = jSONFieldArr3;
                        jSONField = jSONField5;
                        strArr3 = strArr2;
                    }
                    sArr = sArr2;
                    if (jSONField != null) {
                        if (jSONField.serialize()) {
                            i5 = jSONField.ordinal();
                            i6 = SerializerFeature.of(jSONField.serialzeFeatures());
                            if (jSONField.name().length() != 0) {
                                String name2 = jSONField.name();
                                if (map3 == null || (name2 = map3.get(name2)) != null) {
                                    String str2 = name2;
                                    setAccessible(cls3, method3, i13);
                                    fieldArr2 = fieldArr;
                                    hashMap = hashMap3;
                                    cls2 = cls6;
                                    linkedHashMap3 = linkedHashMap3;
                                    linkedHashMap3.put(str2, new FieldInfo(str2, method3, null, cls, null, i5, i6, jSONField, null, true));
                                    i8 = i2;
                                    linkedHashMap2 = linkedHashMap3;
                                    hashMap2 = hashMap;
                                    map2 = map3;
                                    fieldArr4 = fieldArr2;
                                    cls3 = cls;
                                    linkedHashMap3 = linkedHashMap2;
                                    i13 = i8;
                                    declaredFields = fieldArr4;
                                    map3 = map2;
                                    constructorArr3 = constructorArr;
                                    strArr6 = strArr3;
                                    sArr2 = sArr;
                                    jSONFieldArr3 = jSONFieldArr;
                                    cls4 = cls2;
                                    hashMap3 = hashMap2;
                                    jSONType2 = jSONType;
                                    propertyNamingStrategy3 = propertyNamingStrategy;
                                }
                            } else {
                                hashMap = hashMap3;
                                cls2 = cls6;
                                fieldArr2 = fieldArr;
                            }
                        }
                        hashMap2 = hashMap3;
                        linkedHashMap2 = linkedHashMap3;
                        i8 = i13;
                        map2 = map3;
                        cls2 = cls6;
                        fieldArr4 = fieldArr;
                        cls3 = cls;
                        linkedHashMap3 = linkedHashMap2;
                        i13 = i8;
                        declaredFields = fieldArr4;
                        map3 = map2;
                        constructorArr3 = constructorArr;
                        strArr6 = strArr3;
                        sArr2 = sArr;
                        jSONFieldArr3 = jSONFieldArr;
                        cls4 = cls2;
                        hashMap3 = hashMap2;
                        jSONType2 = jSONType;
                        propertyNamingStrategy3 = propertyNamingStrategy;
                    } else {
                        hashMap = hashMap3;
                        cls2 = cls6;
                        fieldArr2 = fieldArr;
                        i5 = 0;
                        i6 = 0;
                    }
                    if (name.startsWith(IMantoServerRequester.GET)) {
                        if (name.length() >= 4) {
                            if (!name.equals("getClass")) {
                                char charAt = name.charAt(3);
                                if (Character.isUpperCase(charAt)) {
                                    decapitalize = compatibleWithJavaBean ? decapitalize(name.substring(3)) : Character.toLowerCase(name.charAt(3)) + name.substring(4);
                                } else if (charAt == '_') {
                                    decapitalize = name.substring(4);
                                } else if (charAt == 'f') {
                                    decapitalize = name.substring(3);
                                } else if (name.length() >= 5 && Character.isUpperCase(name.charAt(4))) {
                                    decapitalize = decapitalize(name.substring(3));
                                }
                                if (!isJSONTypeIgnore(cls3, jSONType2, decapitalize)) {
                                    Field[] fieldArr5 = fieldArr2;
                                    Field field = getField(cls3, decapitalize, fieldArr5, hashMap);
                                    if (field != null) {
                                        JSONField jSONField7 = z3 ? (JSONField) field.getAnnotation(JSONField.class) : null;
                                        if (jSONField7 != null) {
                                            if (jSONField7.serialize()) {
                                                i5 = jSONField7.ordinal();
                                                i6 = SerializerFeature.of(jSONField7.serialzeFeatures());
                                                if (jSONField7.name().length() != 0) {
                                                    decapitalize = jSONField7.name();
                                                    if (map3 != null) {
                                                        decapitalize = map3.get(decapitalize);
                                                    }
                                                }
                                            }
                                            i8 = i2;
                                            linkedHashMap2 = linkedHashMap3;
                                            hashMap2 = hashMap;
                                            map2 = map3;
                                            fieldArr4 = fieldArr5;
                                            cls3 = cls;
                                            linkedHashMap3 = linkedHashMap2;
                                            i13 = i8;
                                            declaredFields = fieldArr4;
                                            map3 = map2;
                                            constructorArr3 = constructorArr;
                                            strArr6 = strArr3;
                                            sArr2 = sArr;
                                            jSONFieldArr3 = jSONFieldArr;
                                            cls4 = cls2;
                                            hashMap3 = hashMap2;
                                            jSONType2 = jSONType;
                                            propertyNamingStrategy3 = propertyNamingStrategy;
                                        }
                                        i11 = i5;
                                        i12 = i6;
                                        jSONField3 = jSONField7;
                                    } else {
                                        i11 = i5;
                                        i12 = i6;
                                        jSONField3 = null;
                                    }
                                    if (propertyNamingStrategy3 != null) {
                                        decapitalize = propertyNamingStrategy3.translate(decapitalize);
                                    }
                                    if (map3 == null || (decapitalize = map3.get(decapitalize)) != null) {
                                        HashMap hashMap4 = hashMap;
                                        String str3 = decapitalize;
                                        setAccessible(cls3, method3, i2);
                                        fieldArr3 = fieldArr5;
                                        hashMap2 = hashMap4;
                                        str = name;
                                        method = method3;
                                        i7 = 3;
                                        linkedHashMap3 = linkedHashMap3;
                                        linkedHashMap3.put(str3, new FieldInfo(str3, method3, field, cls, null, i11, i12, jSONField, jSONField3, z4));
                                        i5 = i11;
                                        i6 = i12;
                                    }
                                    i8 = i2;
                                    linkedHashMap2 = linkedHashMap3;
                                    hashMap2 = hashMap;
                                    map2 = map3;
                                    fieldArr4 = fieldArr5;
                                    cls3 = cls;
                                    linkedHashMap3 = linkedHashMap2;
                                    i13 = i8;
                                    declaredFields = fieldArr4;
                                    map3 = map2;
                                    constructorArr3 = constructorArr;
                                    strArr6 = strArr3;
                                    sArr2 = sArr;
                                    jSONFieldArr3 = jSONFieldArr;
                                    cls4 = cls2;
                                    hashMap3 = hashMap2;
                                    jSONType2 = jSONType;
                                    propertyNamingStrategy3 = propertyNamingStrategy;
                                }
                            }
                            i8 = i2;
                            linkedHashMap2 = linkedHashMap3;
                            hashMap2 = hashMap;
                            map2 = map3;
                            fieldArr4 = fieldArr2;
                            cls3 = cls;
                            linkedHashMap3 = linkedHashMap2;
                            i13 = i8;
                            declaredFields = fieldArr4;
                            map3 = map2;
                            constructorArr3 = constructorArr;
                            strArr6 = strArr3;
                            sArr2 = sArr;
                            jSONFieldArr3 = jSONFieldArr;
                            cls4 = cls2;
                            hashMap3 = hashMap2;
                            jSONType2 = jSONType;
                            propertyNamingStrategy3 = propertyNamingStrategy;
                        }
                        i8 = i2;
                        map2 = map;
                        linkedHashMap2 = linkedHashMap3;
                        hashMap2 = hashMap;
                        fieldArr4 = fieldArr2;
                        cls3 = cls;
                        linkedHashMap3 = linkedHashMap2;
                        i13 = i8;
                        declaredFields = fieldArr4;
                        map3 = map2;
                        constructorArr3 = constructorArr;
                        strArr6 = strArr3;
                        sArr2 = sArr;
                        jSONFieldArr3 = jSONFieldArr;
                        cls4 = cls2;
                        hashMap3 = hashMap2;
                        jSONType2 = jSONType;
                        propertyNamingStrategy3 = propertyNamingStrategy;
                    } else {
                        str = name;
                        method = method3;
                        hashMap2 = hashMap;
                        fieldArr3 = fieldArr2;
                        i7 = 3;
                    }
                    if (str.startsWith("is") && str.length() >= i7) {
                        char charAt2 = str.charAt(2);
                        if (Character.isUpperCase(charAt2)) {
                            substring = compatibleWithJavaBean ? decapitalize(str.substring(2)) : Character.toLowerCase(str.charAt(2)) + str.substring(i7);
                        } else if (charAt2 == '_') {
                            substring = str.substring(i7);
                        } else if (charAt2 == 'f') {
                            substring = str.substring(2);
                        }
                        if (!isJSONTypeIgnore(cls3, jSONType2, substring)) {
                            fieldArr4 = fieldArr3;
                            HashMap hashMap5 = hashMap2;
                            Field field2 = getField(cls3, substring, fieldArr4, hashMap5);
                            if (field2 == null) {
                                field2 = getField(cls3, str, fieldArr4, hashMap5);
                            }
                            Field field3 = field2;
                            if (field3 != null) {
                                JSONField jSONField8 = z3 ? (JSONField) field3.getAnnotation(JSONField.class) : null;
                                if (jSONField8 == null) {
                                    map2 = map;
                                    propertyNamingStrategy2 = propertyNamingStrategy;
                                    i9 = i5;
                                    i10 = i6;
                                    jSONField2 = jSONField8;
                                } else if (jSONField8.serialize()) {
                                    int ordinal = jSONField8.ordinal();
                                    int of = SerializerFeature.of(jSONField8.serialzeFeatures());
                                    if (jSONField8.name().length() != 0) {
                                        substring = jSONField8.name();
                                        map2 = map;
                                        if (map2 != null) {
                                            substring = map2.get(substring);
                                        }
                                    } else {
                                        map2 = map;
                                    }
                                    propertyNamingStrategy2 = propertyNamingStrategy;
                                    jSONField2 = jSONField8;
                                    i9 = ordinal;
                                    i10 = of;
                                } else {
                                    i8 = i2;
                                    map2 = map;
                                    linkedHashMap2 = linkedHashMap3;
                                    hashMap2 = hashMap5;
                                    cls3 = cls;
                                    linkedHashMap3 = linkedHashMap2;
                                    i13 = i8;
                                    declaredFields = fieldArr4;
                                    map3 = map2;
                                    constructorArr3 = constructorArr;
                                    strArr6 = strArr3;
                                    sArr2 = sArr;
                                    jSONFieldArr3 = jSONFieldArr;
                                    cls4 = cls2;
                                    hashMap3 = hashMap2;
                                    jSONType2 = jSONType;
                                    propertyNamingStrategy3 = propertyNamingStrategy;
                                }
                            } else {
                                map2 = map;
                                propertyNamingStrategy2 = propertyNamingStrategy;
                                i9 = i5;
                                i10 = i6;
                                jSONField2 = null;
                            }
                            if (propertyNamingStrategy2 != null) {
                                substring = propertyNamingStrategy2.translate(substring);
                            }
                            if (map2 == null || (substring = map2.get(substring)) != null) {
                                String str4 = substring;
                                setAccessible(cls3, field3, i2);
                                Method method4 = method;
                                setAccessible(cls3, method4, i2);
                                hashMap2 = hashMap5;
                                i8 = i2;
                                linkedHashMap2 = linkedHashMap3;
                                linkedHashMap2.put(str4, new FieldInfo(str4, method4, field3, cls, null, i9, i10, jSONField, jSONField2, z4));
                                cls3 = cls;
                                linkedHashMap3 = linkedHashMap2;
                                i13 = i8;
                                declaredFields = fieldArr4;
                                map3 = map2;
                                constructorArr3 = constructorArr;
                                strArr6 = strArr3;
                                sArr2 = sArr;
                                jSONFieldArr3 = jSONFieldArr;
                                cls4 = cls2;
                                hashMap3 = hashMap2;
                                jSONType2 = jSONType;
                                propertyNamingStrategy3 = propertyNamingStrategy;
                            }
                            i8 = i2;
                            linkedHashMap2 = linkedHashMap3;
                            hashMap2 = hashMap5;
                            cls3 = cls;
                            linkedHashMap3 = linkedHashMap2;
                            i13 = i8;
                            declaredFields = fieldArr4;
                            map3 = map2;
                            constructorArr3 = constructorArr;
                            strArr6 = strArr3;
                            sArr2 = sArr;
                            jSONFieldArr3 = jSONFieldArr;
                            cls4 = cls2;
                            hashMap3 = hashMap2;
                            jSONType2 = jSONType;
                            propertyNamingStrategy3 = propertyNamingStrategy;
                        }
                    }
                    i8 = i2;
                    map2 = map;
                    linkedHashMap2 = linkedHashMap3;
                    fieldArr4 = fieldArr3;
                    cls3 = cls;
                    linkedHashMap3 = linkedHashMap2;
                    i13 = i8;
                    declaredFields = fieldArr4;
                    map3 = map2;
                    constructorArr3 = constructorArr;
                    strArr6 = strArr3;
                    sArr2 = sArr;
                    jSONFieldArr3 = jSONFieldArr;
                    cls4 = cls2;
                    hashMap3 = hashMap2;
                    jSONType2 = jSONType;
                    propertyNamingStrategy3 = propertyNamingStrategy;
                }
            }
        }
        Class<? super Object> cls7 = cls4;
        int i17 = i13;
        Map<String, String> map4 = map3;
        Field[] fieldArr6 = declaredFields;
        LinkedHashMap linkedHashMap4 = linkedHashMap3;
        ArrayList arrayList2 = new ArrayList(fieldArr6.length);
        for (Field field4 : fieldArr6) {
            if ((field4.getModifiers() & 8) == 0 && !field4.getName().equals("this$0") && (field4.getModifiers() & 1) != 0) {
                arrayList2.add(field4);
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        while (superclass != null) {
            Class<? super Object> cls8 = cls7;
            if (superclass == cls8) {
                break;
            }
            for (Field field5 : superclass.getDeclaredFields()) {
                if ((field5.getModifiers() & 8) == 0 && (field5.getModifiers() & 1) != 0) {
                    arrayList2.add(field5);
                }
            }
            superclass = superclass.getSuperclass();
            cls7 = cls8;
        }
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            Field field6 = (Field) it2.next();
            JSONField jSONField9 = z3 ? (JSONField) field6.getAnnotation(JSONField.class) : null;
            String name3 = field6.getName();
            if (jSONField9 == null) {
                i3 = 0;
                i4 = 0;
            } else if (jSONField9.serialize()) {
                int ordinal2 = jSONField9.ordinal();
                int of2 = SerializerFeature.of(jSONField9.serialzeFeatures());
                if (jSONField9.name().length() != 0) {
                    name3 = jSONField9.name();
                }
                i3 = ordinal2;
                i4 = of2;
            }
            if (map4 == null || (name3 = map4.get(name3)) != null) {
                if (propertyNamingStrategy != null) {
                    name3 = propertyNamingStrategy.translate(name3);
                }
                String str5 = name3;
                if (linkedHashMap4.containsKey(str5)) {
                    it = it2;
                    linkedHashMap = linkedHashMap4;
                } else {
                    setAccessible(cls, field6, i17);
                    it = it2;
                    linkedHashMap = linkedHashMap4;
                    linkedHashMap.put(str5, new FieldInfo(str5, null, field6, cls, null, i3, i4, null, jSONField9, z4));
                }
                linkedHashMap4 = linkedHashMap;
                it2 = it;
            }
        }
        LinkedHashMap linkedHashMap5 = linkedHashMap4;
        ArrayList arrayList3 = new ArrayList();
        if (jSONType != null) {
            strArr = jSONType.orders();
            if (strArr != null && strArr.length == linkedHashMap5.size()) {
                int length3 = strArr.length;
                int i18 = 0;
                while (true) {
                    if (i18 >= length3) {
                        z6 = true;
                        break;
                    } else if (!linkedHashMap5.containsKey(strArr[i18])) {
                        z6 = false;
                        break;
                    } else {
                        i18++;
                    }
                }
                z5 = z6;
                if (z5) {
                    Iterator it3 = linkedHashMap5.values().iterator();
                    while (it3.hasNext()) {
                        arrayList3.add((FieldInfo) it3.next());
                    }
                    if (z2) {
                        Collections.sort(arrayList3);
                    }
                } else {
                    for (String str6 : strArr) {
                        arrayList3.add((FieldInfo) linkedHashMap5.get(str6));
                    }
                }
                return arrayList3;
            }
        } else {
            strArr = null;
        }
        z5 = false;
        if (z5) {
        }
        return arrayList3;
    }

    public static String decapitalize(String str) {
        if (str == null || str.length() == 0 || (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0)))) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Byte.TYPE) {
            return (byte) 0;
        }
        if (cls == Short.TYPE) {
            return (short) 0;
        }
        if (cls == Integer.TYPE) {
            return 0;
        }
        if (cls == Long.TYPE) {
            return 0L;
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        return cls == Character.TYPE ? '0' : null;
    }

    public static long fnv_64_lower(String str) {
        long j2 = -3750763034362895579L;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != '_' && charAt != '-') {
                if (charAt >= 'A' && charAt <= 'Z') {
                    charAt = (char) (charAt + ' ');
                }
                j2 = (j2 ^ charAt) * 1099511628211L;
            }
        }
        return j2;
    }

    public static boolean getArgument(Type[] typeArr, TypeVariable[] typeVariableArr, Type[] typeArr2) {
        if (typeArr2 == null || typeVariableArr.length == 0) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < typeArr.length; i2++) {
            Type type = typeArr[i2];
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (getArgument(actualTypeArguments, typeVariableArr, typeArr2)) {
                    typeArr[i2] = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType.getOwnerType(), parameterizedType.getRawType());
                    z = true;
                }
            } else if (type instanceof TypeVariable) {
                for (int i3 = 0; i3 < typeVariableArr.length; i3++) {
                    if (type.equals(typeVariableArr[i3])) {
                        typeArr[i2] = typeArr2[i3];
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            return (Class) ((TypeVariable) type).getBounds()[0];
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getClass(upperBounds[0]);
            }
            return Object.class;
        }
        return Object.class;
    }

    public static Type getCollectionItemType(Type type) {
        Type type2;
        if (type instanceof ParameterizedType) {
            type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
                if (upperBounds.length == 1) {
                    type2 = upperBounds[0];
                }
            }
        } else {
            if (type instanceof Class) {
                Class cls = (Class) type;
                if (!cls.getName().startsWith("java.")) {
                    type2 = getCollectionItemType(cls.getGenericSuperclass());
                }
            }
            type2 = null;
        }
        return type2 == null ? Object.class : type2;
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr) {
        return getField(cls, str, fieldArr, null);
    }

    private static Field getField0(Class<?> cls, String str, Field[] fieldArr, Map<Class<?>, Field[]> map) {
        char charAt;
        char charAt2;
        for (Field field : fieldArr) {
            String name = field.getName();
            if (str.equals(name)) {
                return field;
            }
            if (str.length() > 2 && (charAt = str.charAt(0)) >= 'a' && charAt <= 'z' && (charAt2 = str.charAt(1)) >= 'A' && charAt2 <= 'Z' && str.equalsIgnoreCase(name)) {
                return field;
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return null;
        }
        Field[] fieldArr2 = map != null ? map.get(superclass) : null;
        if (fieldArr2 == null) {
            fieldArr2 = superclass.getDeclaredFields();
            if (map != null) {
                map.put(superclass, fieldArr2);
            }
        }
        return getField(superclass, str, fieldArr2, map);
    }

    public static Type getGenericParamType(Type type) {
        return type instanceof Class ? getGenericParamType(((Class) type).getGenericSuperclass()) : type;
    }

    public static String[] getKoltinConstructorParameters(Class cls) {
        if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
            try {
                Class<?> cls2 = Class.forName("kotlin.reflect.jvm.internal.KClassImpl");
                kotlin_kclass_constructor = cls2.getConstructor(Class.class);
                kotlin_kclass_getConstructors = cls2.getMethod("getConstructors", new Class[0]);
                kotlin_kfunction_getParameters = Class.forName("kotlin.reflect.KFunction").getMethod("getParameters", new Class[0]);
                kotlin_kparameter_getName = Class.forName("kotlin.reflect.KParameter").getMethod("getName", new Class[0]);
            } catch (Throwable unused) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kclass_constructor == null || kotlin_error) {
            return null;
        }
        try {
            Iterator it = ((Iterable) kotlin_kclass_getConstructors.invoke(kotlin_kclass_constructor.newInstance(cls), new Object[0])).iterator();
            Object obj = null;
            while (it.hasNext()) {
                obj = it.next();
                it.hasNext();
            }
            List list = (List) kotlin_kfunction_getParameters.invoke(obj, new Object[0]);
            String[] strArr = new String[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                strArr[i2] = (String) kotlin_kparameter_getName.invoke(list.get(i2), new Object[0]);
            }
            return strArr;
        } catch (Throwable unused2) {
            kotlin_error = true;
            return null;
        }
    }

    public static JSONField getSupperMethodAnnotation(Class<?> cls, Method method) {
        boolean z;
        JSONField jSONField;
        boolean z2;
        JSONField jSONField2;
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Method method2 : cls2.getMethods()) {
                if (method2.getName().equals(method.getName())) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    Class<?>[] parameterTypes2 = method.getParameterTypes();
                    if (parameterTypes.length != parameterTypes2.length) {
                        continue;
                    } else {
                        int i2 = 0;
                        while (true) {
                            if (i2 >= parameterTypes.length) {
                                z2 = true;
                                break;
                            } else if (!parameterTypes[i2].equals(parameterTypes2[i2])) {
                                z2 = false;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (z2 && (jSONField2 = (JSONField) method2.getAnnotation(JSONField.class)) != null) {
                            return jSONField2;
                        }
                    }
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && Modifier.isAbstract(superclass.getModifiers())) {
            Class<?>[] parameterTypes3 = method.getParameterTypes();
            for (Method method3 : superclass.getMethods()) {
                Class<?>[] parameterTypes4 = method3.getParameterTypes();
                if (parameterTypes4.length == parameterTypes3.length && method3.getName().equals(method.getName())) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= parameterTypes3.length) {
                            z = true;
                            break;
                        } else if (!parameterTypes4[i3].equals(parameterTypes3[i3])) {
                            z = false;
                            break;
                        } else {
                            i3++;
                        }
                    }
                    if (z && (jSONField = (JSONField) method3.getAnnotation(JSONField.class)) != null) {
                        return jSONField;
                    }
                }
            }
        }
        return null;
    }

    public static boolean isGenericParamType(Type type) {
        Type genericSuperclass;
        if (type instanceof ParameterizedType) {
            return true;
        }
        return (type instanceof Class) && (genericSuperclass = ((Class) type).getGenericSuperclass()) != Object.class && isGenericParamType(genericSuperclass);
    }

    private static boolean isJSONTypeIgnore(Class<?> cls, JSONType jSONType, String str) {
        if (jSONType != null && jSONType.ignores() != null) {
            for (String str2 : jSONType.ignores()) {
                if (str.equalsIgnoreCase(str2)) {
                    return true;
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        return (superclass == Object.class || superclass == null || !isJSONTypeIgnore(superclass, (JSONType) superclass.getAnnotation(JSONType.class), str)) ? false : true;
    }

    public static boolean isKotlin(Class cls) {
        if (kotlin_metadata == null && !kotlin_metadata_error) {
            try {
                kotlin_metadata = Class.forName("kotlin.Metadata");
            } catch (Throwable unused) {
                kotlin_metadata_error = true;
            }
        }
        if (kotlin_metadata == null) {
            return false;
        }
        return cls.isAnnotationPresent(kotlin_metadata);
    }

    private static boolean isKotlinIgnore(Class cls, String str) {
        String[] strArr;
        if (kotlinIgnores == null && !kotlinIgnores_error) {
            try {
                HashMap hashMap = new HashMap();
                hashMap.put(Class.forName("kotlin.ranges.CharRange"), new String[]{"getEndInclusive", CartConstant.KEY_GLOBAL_IS_EMPTY});
                hashMap.put(Class.forName("kotlin.ranges.IntRange"), new String[]{"getEndInclusive", CartConstant.KEY_GLOBAL_IS_EMPTY});
                hashMap.put(Class.forName("kotlin.ranges.LongRange"), new String[]{"getEndInclusive", CartConstant.KEY_GLOBAL_IS_EMPTY});
                hashMap.put(Class.forName("kotlin.ranges.ClosedFloatRange"), new String[]{"getEndInclusive", CartConstant.KEY_GLOBAL_IS_EMPTY});
                hashMap.put(Class.forName("kotlin.ranges.ClosedDoubleRange"), new String[]{"getEndInclusive", CartConstant.KEY_GLOBAL_IS_EMPTY});
                kotlinIgnores = hashMap;
            } catch (Throwable unused) {
                kotlinIgnores_error = true;
            }
        }
        return (kotlinIgnores == null || (strArr = kotlinIgnores.get(cls)) == null || Arrays.binarySearch(strArr, str) < 0) ? false : true;
    }

    public static Class<?> loadClass(String str, ClassLoader classLoader) {
        Exception e2;
        Class<?> cls;
        if (str == null || str.length() == 0) {
            return null;
        }
        if (str.length() < 256) {
            Class<?> cls2 = mappings.get(str);
            if (cls2 != null) {
                return cls2;
            }
            if (str.charAt(0) == '[') {
                return Array.newInstance(loadClass(str.substring(1), classLoader), 0).getClass();
            }
            if (str.startsWith("L") && str.endsWith(";")) {
                return loadClass(str.substring(1, str.length() - 1), classLoader);
            }
            if (classLoader != null) {
                try {
                    cls2 = classLoader.loadClass(str);
                    mappings.put(str, cls2);
                    return cls2;
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            try {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader != null && contextClassLoader != classLoader) {
                    cls = contextClassLoader.loadClass(str);
                    try {
                        mappings.put(str, cls);
                        return cls;
                    } catch (Exception e4) {
                        e2 = e4;
                        e2.printStackTrace();
                        cls2 = cls;
                        cls2 = Class.forName(str);
                        mappings.put(str, cls2);
                        return cls2;
                    }
                }
            } catch (Exception e5) {
                Class<?> cls3 = cls2;
                e2 = e5;
                cls = cls3;
            }
            try {
                cls2 = Class.forName(str);
                mappings.put(str, cls2);
                return cls2;
            } catch (Exception e6) {
                e6.printStackTrace();
                return cls2;
            }
        }
        throw new JSONException("className too long. " + str);
    }

    public static boolean setAccessible(Class<?> cls, Member member, int i2) {
        if (member != null && setAccessibleEnable) {
            Class<? super Object> superclass = cls.getSuperclass();
            if ((superclass == null || superclass == Object.class) && (member.getModifiers() & 1) != 0 && (i2 & 1) != 0) {
                return false;
            }
            try {
                ((AccessibleObject) member).setAccessible(true);
                return true;
            } catch (AccessControlException unused) {
                setAccessibleEnable = false;
            }
        }
        return false;
    }

    public static final <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig) {
        JDJSONObject jDJSONObject;
        int i2 = 0;
        try {
            if (cls == StackTraceElement.class) {
                String str = (String) map.get("className");
                String str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get(StackTraceHelper.LINE_NUMBER_KEY);
                if (number != null) {
                    i2 = number.intValue();
                }
                return (T) new StackTraceElement(str, str2, str3, i2);
            }
            Object obj = map.get(JDJSON.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                String str4 = (String) obj;
                Class<?> loadClass = loadClass(str4, null);
                if (loadClass != null) {
                    if (!loadClass.equals(cls)) {
                        return (T) castToJavaBean(map, loadClass, parserConfig);
                    }
                } else {
                    throw new ClassNotFoundException(str4 + " not found");
                }
            }
            if (cls.isInterface()) {
                if (map instanceof JDJSONObject) {
                    jDJSONObject = (JDJSONObject) map;
                } else {
                    jDJSONObject = new JDJSONObject(map);
                }
                if (parserConfig == null) {
                    parserConfig = ParserConfig.getGlobalInstance();
                }
                return parserConfig.getDeserializer(cls) != null ? (T) JDJSON.parseObject(JDJSON.toJSONString(jDJSONObject), cls) : (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, jDJSONObject);
            } else if (cls == String.class && (map instanceof JDJSONObject)) {
                return (T) map.toString();
            } else {
                if (parserConfig == null) {
                    parserConfig = ParserConfig.global;
                }
                ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
                JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
                if (javaBeanDeserializer != null) {
                    return (T) javaBeanDeserializer.createInstance(map, parserConfig);
                }
                throw new JSONException("can not get javaBeanDeserializer");
            }
        } catch (Exception e2) {
            throw new JSONException(e2.getMessage(), e2);
        }
    }

    public static Field getField(Class<?> cls, String str, Field[] fieldArr, Map<Class<?>, Field[]> map) {
        Field field0 = getField0(cls, str, fieldArr, map);
        if (field0 == null) {
            field0 = getField0(cls, CartConstant.KEY_YB_INFO_LINK + str, fieldArr, map);
        }
        if (field0 == null) {
            field0 = getField0(cls, "m_" + str, fieldArr, map);
        }
        if (field0 == null) {
            return getField0(cls, "m" + str.substring(0, 1).toUpperCase() + str.substring(1), fieldArr, map);
        }
        return field0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == 0) {
            return null;
        }
        if (type instanceof Class) {
            return (T) cast(obj, (Class) type, parserConfig);
        }
        if (type instanceof ParameterizedType) {
            return (T) cast(obj, (ParameterizedType) type, parserConfig);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || DYConstants.DY_NULL_STR.equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    /* JADX WARN: Type inference failed for: r7v8, types: [T, java.util.Map, java.util.HashMap] */
    public static final <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        T t;
        Type rawType = parameterizedType.getRawType();
        if (rawType == Set.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == List.class || rawType == ArrayList.class) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                if (rawType != Set.class && rawType != HashSet.class) {
                    if (rawType == TreeSet.class) {
                        t = (T) new TreeSet();
                    } else {
                        t = (T) new ArrayList();
                    }
                } else {
                    t = (T) new HashSet();
                }
                Iterator<T> it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    ((Collection) t).add(cast(it.next(), type, parserConfig));
                }
                return t;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            Type type3 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                ?? r7 = (T) new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    r7.put(cast(entry.getKey(), type2, parserConfig), cast(entry.getValue(), type3, parserConfig));
                }
                return r7;
            }
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || DYConstants.DY_NULL_STR.equals(str)) {
                return null;
            }
        }
        if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return (T) cast(obj, rawType, parserConfig);
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }
}
