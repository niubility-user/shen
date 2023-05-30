package com.jd.framework.json;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.anotation.JSONField;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class JDJSONObject extends JDJSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {
    public static final Object NULL = new Object() { // from class: com.jd.framework.json.JDJSONObject.1
        public boolean equals(Object obj) {
            return obj == this || obj == null;
        }

        public String toString() {
            return DYConstants.DY_NULL_STR;
        }
    };
    private final Map<String, Object> map;

    public JDJSONObject() {
        this(16, false);
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    public Object clone() {
        return new JDJSONObject(new LinkedHashMap(this.map));
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        return this.map.get(obj);
    }

    public BigDecimal getBigDecimal(String str) {
        return TypeUtils.castToBigDecimal(get(str));
    }

    public BigInteger getBigInteger(String str) {
        return TypeUtils.castToBigInteger(get(str));
    }

    public Boolean getBoolean(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBoolean(obj);
    }

    public boolean getBooleanValue(String str) {
        Boolean castToBoolean = TypeUtils.castToBoolean(get(str));
        if (castToBoolean == null) {
            return false;
        }
        return castToBoolean.booleanValue();
    }

    public Byte getByte(String str) {
        return TypeUtils.castToByte(get(str));
    }

    public byte getByteValue(String str) {
        Byte castToByte = TypeUtils.castToByte(get(str));
        if (castToByte == null) {
            return (byte) 0;
        }
        return castToByte.byteValue();
    }

    public byte[] getBytes(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBytes(obj);
    }

    public Date getDate(String str) {
        return TypeUtils.castToDate(get(str));
    }

    public Double getDouble(String str) {
        return TypeUtils.castToDouble(get(str));
    }

    public double getDoubleValue(String str) {
        Double castToDouble = TypeUtils.castToDouble(get(str));
        if (castToDouble == null) {
            return 0.0d;
        }
        return castToDouble.doubleValue();
    }

    public Float getFloat(String str) {
        return TypeUtils.castToFloat(get(str));
    }

    public float getFloatValue(String str) {
        Float castToFloat = TypeUtils.castToFloat(get(str));
        if (castToFloat == null) {
            return 0.0f;
        }
        return castToFloat.floatValue();
    }

    public Map<String, Object> getInnerMap() {
        return this.map;
    }

    public int getIntValue(String str) {
        Integer castToInt = TypeUtils.castToInt(get(str));
        if (castToInt == null) {
            return 0;
        }
        return castToInt.intValue();
    }

    public Integer getInteger(String str) {
        return TypeUtils.castToInt(get(str));
    }

    public JDJSONArray getJSONArray(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JDJSONArray) {
            return (JDJSONArray) obj;
        }
        if (obj instanceof String) {
            return (JDJSONArray) JDJSON.parse((String) obj);
        }
        return (JDJSONArray) JDJSON.toJSON(obj);
    }

    public JDJSONObject getJSONObject(String str) {
        Object obj = this.map.get(str);
        if (obj instanceof JDJSONObject) {
            return (JDJSONObject) obj;
        }
        if (obj instanceof String) {
            return JDJSON.parseObject((String) obj);
        }
        return (JDJSONObject) JDJSON.toJSON(obj);
    }

    public Long getLong(String str) {
        return TypeUtils.castToLong(get(str));
    }

    public long getLongValue(String str) {
        Long castToLong = TypeUtils.castToLong(get(str));
        if (castToLong == null) {
            return 0L;
        }
        return castToLong.longValue();
    }

    public <T> T getObject(String str, Class<T> cls) {
        return (T) TypeUtils.castToJavaBean(this.map.get(str), cls);
    }

    public Short getShort(String str) {
        return TypeUtils.castToShort(get(str));
    }

    public short getShortValue(String str) {
        Short castToShort = TypeUtils.castToShort(get(str));
        if (castToShort == null) {
            return (short) 0;
        }
        return castToShort.shortValue();
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String str = null;
        if (parameterTypes.length == 1) {
            if (method.getName().equals("equals")) {
                return Boolean.valueOf(equals(objArr[0]));
            }
            if (method.getReturnType() == Void.TYPE) {
                JSONField jSONField = (JSONField) method.getAnnotation(JSONField.class);
                String name = (jSONField == null || jSONField.name().length() == 0) ? null : jSONField.name();
                if (name == null) {
                    String name2 = method.getName();
                    if (name2.startsWith("set")) {
                        String substring = name2.substring(3);
                        if (substring.length() != 0) {
                            name = Character.toLowerCase(substring.charAt(0)) + substring.substring(1);
                        } else {
                            throw new JSONException("illegal setter");
                        }
                    } else {
                        throw new JSONException("illegal setter");
                    }
                }
                this.map.put(name, objArr[0]);
                return null;
            }
            throw new JSONException("illegal setter");
        } else if (parameterTypes.length == 0) {
            if (method.getReturnType() != Void.TYPE) {
                JSONField jSONField2 = (JSONField) method.getAnnotation(JSONField.class);
                if (jSONField2 != null && jSONField2.name().length() != 0) {
                    str = jSONField2.name();
                }
                if (str == null) {
                    String name3 = method.getName();
                    if (name3.startsWith(IMantoServerRequester.GET)) {
                        String substring2 = name3.substring(3);
                        if (substring2.length() != 0) {
                            str = Character.toLowerCase(substring2.charAt(0)) + substring2.substring(1);
                        } else {
                            throw new JSONException("illegal getter");
                        }
                    } else if (name3.startsWith("is")) {
                        String substring3 = name3.substring(2);
                        if (substring3.length() != 0) {
                            str = Character.toLowerCase(substring3.charAt(0)) + substring3.substring(1);
                        } else {
                            throw new JSONException("illegal getter");
                        }
                    } else if (name3.startsWith("hashCode")) {
                        return Integer.valueOf(hashCode());
                    } else {
                        if (name3.startsWith("toString")) {
                            return toString();
                        }
                        throw new JSONException("illegal getter");
                    }
                }
                return TypeUtils.cast(this.map.get(str), method.getGenericReturnType(), ParserConfig.global);
            }
            throw new JSONException("illegal getter");
        } else {
            throw new UnsupportedOperationException(method.toGenericString());
        }
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public boolean isNull(String str) {
        Object obj = get(str);
        return obj == null || obj == NULL;
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    public boolean optBoolean(String str) {
        return optBoolean(str, false);
    }

    public double optDouble(String str) {
        return optDouble(str, Double.NaN);
    }

    public int optInt(String str) {
        return optInt(str, 0);
    }

    public JDJSONArray optJSONArray(String str) {
        JDJSONArray jDJSONArray;
        try {
            jDJSONArray = getJSONArray(str);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str2 = "parse jsonarray error with key : " + str;
            }
            jDJSONArray = null;
        }
        if (jDJSONArray != null) {
            return jDJSONArray;
        }
        return null;
    }

    public JDJSONObject optJSONObject(String str) {
        JDJSONObject jDJSONObject;
        try {
            jDJSONObject = getJSONObject(str);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str2 = "parse jsonobject error with key : " + str;
            }
            jDJSONObject = null;
        }
        if (jDJSONObject != null) {
            return jDJSONObject;
        }
        return null;
    }

    public long optLong(String str) {
        return optLong(str, 0L);
    }

    public String optString(String str) {
        return optString(str, "");
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        this.map.putAll(map);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.map.values();
    }

    public JDJSONObject(Map<String, Object> map) {
        this.map = map;
    }

    public boolean optBoolean(String str, boolean z) {
        Boolean bool;
        try {
            bool = getBoolean(str);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str2 = "parse boolean error with key : " + str;
            }
            bool = null;
        }
        return bool != null ? bool.booleanValue() : z;
    }

    public double optDouble(String str, double d) {
        Double d2;
        try {
            d2 = getDouble(str);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str2 = "parse double error with key : " + str;
            }
            d2 = null;
        }
        return d2 != null ? d2.doubleValue() : d;
    }

    public int optInt(String str, int i2) {
        Integer num;
        try {
            num = getInteger(str);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str2 = "parse int error with key : " + str;
            }
            num = null;
        }
        return num != null ? num.intValue() : i2;
    }

    public long optLong(String str, long j2) {
        Long l2;
        try {
            l2 = getLong(str);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str2 = "parse long error with key : " + str;
            }
            l2 = null;
        }
        return l2 != null ? l2.longValue() : j2;
    }

    public String optString(String str, String str2) {
        String str3;
        try {
            str3 = getString(str);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str4 = "parse String error with key : " + str;
            }
            str3 = null;
        }
        return str3 != null ? str3 : str2;
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        return this.map.put(str, obj);
    }

    public JDJSONObject(boolean z) {
        this(16, z);
    }

    public JDJSONObject(int i2) {
        this(i2, false);
    }

    public JDJSONObject(int i2, boolean z) {
        if (z) {
            this.map = new LinkedHashMap(i2);
        } else {
            this.map = new HashMap(i2);
        }
    }
}
