package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.IdentityHashMap;

/* loaded from: classes.dex */
public final class ArrayCodec implements ObjectSerializer, ObjectDeserializer {
    public static final ArrayCodec instance = new ArrayCodec();

    private ArrayCodec() {
    }

    private <T> T toObjectArray(DefaultJSONParser defaultJSONParser, Class<?> cls, JDJSONArray jDJSONArray) {
        if (jDJSONArray == null) {
            return null;
        }
        int size = jDJSONArray.size();
        T t = (T) Array.newInstance(cls, size);
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = jDJSONArray.get(i2);
            if (obj == jDJSONArray) {
                Array.set(t, i2, t);
            } else {
                if (cls.isArray()) {
                    if (!cls.isInstance(obj)) {
                        obj = toObjectArray(defaultJSONParser, cls, (JDJSONArray) obj);
                    }
                } else {
                    obj = TypeUtils.cast(obj, (Class<Object>) cls, defaultJSONParser.config);
                }
                Array.set(t, i2, obj);
            }
        }
        jDJSONArray.setRelatedArray(t);
        jDJSONArray.setComponentType(cls);
        return t;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = jSONLexer.token();
        if (i2 == 8) {
            jSONLexer.nextToken(16);
            return null;
        } else if (type != char[].class) {
            if (i2 == 4) {
                T t = (T) jSONLexer.bytesValue();
                jSONLexer.nextToken(16);
                return t;
            }
            Class<?> componentType = ((Class) type).getComponentType();
            JDJSONArray jDJSONArray = new JDJSONArray();
            defaultJSONParser.parseArray(componentType, jDJSONArray, obj);
            return (T) toObjectArray(defaultJSONParser, componentType, jDJSONArray);
        } else if (i2 == 4) {
            String stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            return (T) stringVal.toCharArray();
        } else if (i2 == 2) {
            Number integerValue = jSONLexer.integerValue();
            jSONLexer.nextToken(16);
            return (T) integerValue.toString().toCharArray();
        } else {
            return (T) JDJSON.toJSONString(defaultJSONParser.parse()).toCharArray();
        }
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        Object[] objArr = (Object[]) obj;
        if (obj == null) {
            if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
                serializeWriter.write("[]");
                return;
            } else {
                serializeWriter.writeNull();
                return;
            }
        }
        int length = objArr.length;
        int i2 = length - 1;
        if (i2 == -1) {
            serializeWriter.append((CharSequence) "[]");
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        int i3 = 0;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        try {
            serializeWriter.write(91);
            if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
                while (i3 < length) {
                    if (i3 != 0) {
                        serializeWriter.write(44);
                        jSONSerializer.println();
                    }
                    jSONSerializer.write(objArr[i3]);
                    i3++;
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.write(93);
                return;
            }
            Class<?> cls = null;
            ObjectSerializer objectSerializer = null;
            while (i3 < i2) {
                Object obj3 = objArr[i3];
                if (obj3 == null) {
                    serializeWriter.append((CharSequence) "null,");
                } else {
                    IdentityHashMap<Object, SerialContext> identityHashMap = jSONSerializer.references;
                    if (identityHashMap != null && identityHashMap.containsKey(obj3)) {
                        jSONSerializer.writeReference(obj3);
                    } else {
                        Class<?> cls2 = obj3.getClass();
                        if (cls2 == cls) {
                            objectSerializer.write(jSONSerializer, obj3, null, null);
                        } else {
                            objectSerializer = jSONSerializer.config.get(cls2);
                            objectSerializer.write(jSONSerializer, obj3, null, null);
                            cls = cls2;
                        }
                    }
                    serializeWriter.write(44);
                }
                i3++;
            }
            Object obj4 = objArr[i2];
            if (obj4 == null) {
                serializeWriter.append((CharSequence) "null]");
            } else {
                IdentityHashMap<Object, SerialContext> identityHashMap2 = jSONSerializer.references;
                if (identityHashMap2 != null && identityHashMap2.containsKey(obj4)) {
                    jSONSerializer.writeReference(obj4);
                } else {
                    jSONSerializer.writeWithFieldName(obj4, Integer.valueOf(i2));
                }
                serializeWriter.write(93);
            }
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
