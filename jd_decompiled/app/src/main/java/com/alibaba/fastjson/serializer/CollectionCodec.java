package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.JDJSONArray;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class CollectionCodec implements ObjectSerializer, ObjectDeserializer {
    public static final CollectionCodec instance = new CollectionCodec();

    private CollectionCodec() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.alibaba.fastjson.parser.DefaultJSONParser] */
    /* JADX WARN: Type inference failed for: r4v5, types: [T, java.util.Collection, com.jd.framework.json.JDJSONArray] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        Class<Object> cls;
        if (defaultJSONParser.lexer.token() == 8) {
            defaultJSONParser.lexer.nextToken(16);
            return null;
        } else if (type == JDJSONArray.class) {
            ?? r4 = (T) new JDJSONArray();
            defaultJSONParser.parseArray(r4);
            return r4;
        } else {
            Type type2 = type;
            while (!(type2 instanceof Class)) {
                if (type2 instanceof ParameterizedType) {
                    type2 = ((ParameterizedType) type2).getRawType();
                } else {
                    throw new JSONException("TODO");
                }
            }
            Class cls2 = (Class) type2;
            if (cls2 != AbstractCollection.class && cls2 != Collection.class) {
                if (cls2.isAssignableFrom(HashSet.class)) {
                    t = (T) new HashSet();
                } else if (cls2.isAssignableFrom(LinkedHashSet.class)) {
                    t = (T) new LinkedHashSet();
                } else if (cls2.isAssignableFrom(TreeSet.class)) {
                    t = (T) new TreeSet();
                } else if (cls2.isAssignableFrom(ArrayList.class)) {
                    t = (T) new ArrayList();
                } else if (cls2.isAssignableFrom(EnumSet.class)) {
                    if (type instanceof ParameterizedType) {
                        cls = ((ParameterizedType) type).getActualTypeArguments()[0];
                    } else {
                        cls = Object.class;
                    }
                    t = (T) EnumSet.noneOf(cls);
                } else {
                    try {
                        t = (Collection) cls2.newInstance();
                    } catch (Exception unused) {
                        throw new JSONException("create instane error, class " + cls2.getName());
                    }
                }
            } else {
                t = (T) new ArrayList();
            }
            defaultJSONParser.parseArray(TypeUtils.getCollectionItemType(type), (Collection) t, obj);
            return t;
        }
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
                serializeWriter.write("[]");
                return;
            } else {
                serializeWriter.writeNull();
                return;
            }
        }
        int i2 = serializeWriter.features;
        SerializerFeature serializerFeature = SerializerFeature.WriteClassName;
        Type collectionItemType = (i2 & serializerFeature.mask) != 0 ? TypeUtils.getCollectionItemType(type) : null;
        Collection collection = (Collection) obj;
        SerialContext serialContext = jSONSerializer.context;
        int i3 = 0;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        if ((serializeWriter.features & serializerFeature.mask) != 0) {
            if (HashSet.class == collection.getClass()) {
                serializeWriter.append((CharSequence) "Set");
            } else if (TreeSet.class == collection.getClass()) {
                serializeWriter.append((CharSequence) "TreeSet");
            }
        }
        try {
            serializeWriter.write(91);
            for (Object obj3 : collection) {
                int i4 = i3 + 1;
                if (i3 != 0) {
                    serializeWriter.write(44);
                }
                if (obj3 == null) {
                    serializeWriter.writeNull();
                } else {
                    Class<?> cls = obj3.getClass();
                    if (cls == Integer.class) {
                        serializeWriter.writeInt(((Integer) obj3).intValue());
                    } else if (cls == Long.class) {
                        serializeWriter.writeLong(((Long) obj3).longValue());
                        if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0) {
                            serializeWriter.write(76);
                        }
                    } else {
                        jSONSerializer.config.get(cls).write(jSONSerializer, obj3, Integer.valueOf(i4 - 1), collectionItemType);
                    }
                }
                i3 = i4;
            }
            serializeWriter.write(93);
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
