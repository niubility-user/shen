package com.jd.framework.json;

import com.alibaba.fastjson.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* loaded from: classes.dex */
public class JDJSONArray extends JDJSON implements List<Object>, Cloneable, RandomAccess, Serializable {
    protected transient Type componentType;
    private final List<Object> list;
    protected transient Object relatedArray;

    public JDJSONArray() {
        this.list = new ArrayList(10);
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        return this.list.add(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends Object> collection) {
        return this.list.addAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.list.clear();
    }

    public Object clone() {
        return new JDJSONArray(new ArrayList(this.list));
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.list.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return this.list.equals(obj);
    }

    @Override // java.util.List
    public Object get(int i2) {
        return this.list.get(i2);
    }

    public BigDecimal getBigDecimal(int i2) {
        return TypeUtils.castToBigDecimal(get(i2));
    }

    public BigInteger getBigInteger(int i2) {
        return TypeUtils.castToBigInteger(get(i2));
    }

    public Boolean getBoolean(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return null;
        }
        return TypeUtils.castToBoolean(obj);
    }

    public boolean getBooleanValue(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return false;
        }
        return TypeUtils.castToBoolean(obj).booleanValue();
    }

    public Byte getByte(int i2) {
        return TypeUtils.castToByte(get(i2));
    }

    public byte getByteValue(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return (byte) 0;
        }
        return TypeUtils.castToByte(obj).byteValue();
    }

    public Type getComponentType() {
        return this.componentType;
    }

    public Date getDate(int i2) {
        return TypeUtils.castToDate(get(i2));
    }

    public Double getDouble(int i2) {
        return TypeUtils.castToDouble(get(i2));
    }

    public double getDoubleValue(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return 0.0d;
        }
        return TypeUtils.castToDouble(obj).doubleValue();
    }

    public Float getFloat(int i2) {
        return TypeUtils.castToFloat(get(i2));
    }

    public float getFloatValue(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return 0.0f;
        }
        return TypeUtils.castToFloat(obj).floatValue();
    }

    public int getIntValue(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return 0;
        }
        return TypeUtils.castToInt(obj).intValue();
    }

    public Integer getInteger(int i2) {
        return TypeUtils.castToInt(get(i2));
    }

    public JDJSONArray getJSONArray(int i2) {
        Object obj = this.list.get(i2);
        if (obj instanceof JDJSONArray) {
            return (JDJSONArray) obj;
        }
        return (JDJSONArray) JDJSON.toJSON(obj);
    }

    public JDJSONObject getJSONObject(int i2) {
        Object obj = this.list.get(i2);
        if (obj instanceof JDJSONObject) {
            return (JDJSONObject) obj;
        }
        return (JDJSONObject) JDJSON.toJSON(obj);
    }

    public Long getLong(int i2) {
        return TypeUtils.castToLong(get(i2));
    }

    public long getLongValue(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return 0L;
        }
        return TypeUtils.castToLong(obj).longValue();
    }

    public <T> T getObject(int i2, Class<T> cls) {
        return (T) TypeUtils.castToJavaBean(this.list.get(i2), cls);
    }

    public Object getRelatedArray() {
        return this.relatedArray;
    }

    public Short getShort(int i2) {
        return TypeUtils.castToShort(get(i2));
    }

    public short getShortValue(int i2) {
        Object obj = get(i2);
        if (obj == null) {
            return (short) 0;
        }
        return TypeUtils.castToShort(obj).shortValue();
    }

    public String getString(int i2) {
        return TypeUtils.castToString(get(i2));
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.list.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.list.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public boolean isNull(int i2) {
        Object obj = get(i2);
        return obj == null || obj == JDJSONObject.NULL;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.list.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.list.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator() {
        return this.list.listIterator();
    }

    public boolean optBoolean(int i2) {
        return optBoolean(i2, false);
    }

    public double optDouble(int i2) {
        return optDouble(i2, Double.NaN);
    }

    public int optInt(int i2) {
        return optInt(i2, 0);
    }

    public JDJSONArray optJSONArray(int i2) {
        JDJSONArray jDJSONArray;
        try {
            jDJSONArray = getJSONArray(i2);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str = "parse jsonarray error with index : " + i2;
            }
            jDJSONArray = null;
        }
        if (jDJSONArray != null) {
            return jDJSONArray;
        }
        return null;
    }

    public JDJSONObject optJSONObject(int i2) {
        JDJSONObject jDJSONObject;
        try {
            jDJSONObject = getJSONObject(i2);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str = "parse jsonobject error with index : " + i2;
            }
            jDJSONObject = null;
        }
        if (jDJSONObject != null) {
            return jDJSONObject;
        }
        return null;
    }

    public long optLong(int i2) {
        return optLong(i2, 0L);
    }

    public String optString(int i2) {
        return optString(i2, "");
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        return this.list.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.list.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.list.retainAll(collection);
    }

    @Override // java.util.List
    public Object set(int i2, Object obj) {
        return this.list.set(i2, obj);
    }

    public void setComponentType(Type type) {
        this.componentType = type;
    }

    public void setRelatedArray(Object obj) {
        this.relatedArray = obj;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.list.size();
    }

    @Override // java.util.List
    public List<Object> subList(int i2, int i3) {
        return this.list.subList(i2, i3);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override // java.util.List
    public void add(int i2, Object obj) {
        this.list.add(i2, obj);
    }

    @Override // java.util.List
    public boolean addAll(int i2, Collection<? extends Object> collection) {
        return this.list.addAll(i2, collection);
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator(int i2) {
        return this.list.listIterator(i2);
    }

    public boolean optBoolean(int i2, boolean z) {
        Boolean bool;
        try {
            bool = getBoolean(i2);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str = "parse boolean error with index : " + i2;
            }
            bool = null;
        }
        return bool != null ? bool.booleanValue() : z;
    }

    public double optDouble(int i2, double d) {
        Double d2;
        try {
            d2 = getDouble(i2);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str = "parse double error with index : " + i2;
            }
            d2 = null;
        }
        return d2 != null ? d2.doubleValue() : d;
    }

    public int optInt(int i2, int i3) {
        Integer num;
        try {
            num = getInteger(i2);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str = "parse int error with index : " + i2;
            }
            num = null;
        }
        return num != null ? num.intValue() : i3;
    }

    public long optLong(int i2, long j2) {
        Long l2;
        try {
            l2 = getLong(i2);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str = "parse long error with index : " + i2;
            }
            l2 = null;
        }
        return l2 != null ? l2.longValue() : j2;
    }

    public String optString(int i2, String str) {
        String str2;
        try {
            str2 = getString(i2);
        } catch (Exception unused) {
            if (JDJSON.debug) {
                String str3 = "parse String error with index : " + i2;
            }
            str2 = null;
        }
        return str2 != null ? str2 : str;
    }

    @Override // java.util.List
    public Object remove(int i2) {
        return this.list.remove(i2);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.list.toArray(tArr);
    }

    public JDJSONArray(List<Object> list) {
        this.list = list;
    }

    public JDJSONArray(int i2) {
        this.list = new ArrayList(i2);
    }
}
