package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.TypeUtils;
import com.jd.dynamic.DYConstants;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.IdentityHashMap;
import java.util.List;

/* loaded from: classes.dex */
public final class ListSerializer implements ObjectSerializer {
    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        int i2 = 1;
        boolean z = (serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0;
        Type collectionItemType = z ? TypeUtils.getCollectionItemType(type) : null;
        if (obj == null) {
            if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
                serializeWriter.write("[]");
                return;
            } else {
                serializeWriter.writeNull();
                return;
            }
        }
        List list = (List) obj;
        int size = list.size();
        if (size == 0) {
            serializeWriter.append((CharSequence) "[]");
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        if ((serializeWriter.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
            jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
            if (jSONSerializer.references == null) {
                jSONSerializer.references = new IdentityHashMap<>();
            }
            jSONSerializer.references.put(obj, jSONSerializer.context);
        }
        try {
            if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                serializeWriter.write(91);
                jSONSerializer.incrementIndent();
                for (int i3 = 0; i3 < size; i3++) {
                    Object obj3 = list.get(i3);
                    if (i3 != 0) {
                        serializeWriter.write(44);
                    }
                    jSONSerializer.println();
                    if (obj3 != null) {
                        IdentityHashMap<Object, SerialContext> identityHashMap = jSONSerializer.references;
                        if (identityHashMap != null && identityHashMap.containsKey(obj3)) {
                            jSONSerializer.writeReference(obj3);
                        } else {
                            ObjectSerializer objectSerializer = jSONSerializer.config.get(obj3.getClass());
                            jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
                            objectSerializer.write(jSONSerializer, obj3, Integer.valueOf(i3), collectionItemType);
                        }
                    } else {
                        jSONSerializer.out.writeNull();
                    }
                }
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
                serializeWriter.write(93);
                return;
            }
            int i4 = serializeWriter.count + 1;
            if (i4 > serializeWriter.buf.length) {
                if (serializeWriter.writer == null) {
                    serializeWriter.expandCapacity(i4);
                } else {
                    serializeWriter.flush();
                    i4 = 1;
                }
            }
            serializeWriter.buf[serializeWriter.count] = '[';
            serializeWriter.count = i4;
            for (int i5 = 0; i5 < list.size(); i5++) {
                Object obj4 = list.get(i5);
                if (i5 != 0) {
                    int i6 = serializeWriter.count + 1;
                    if (i6 > serializeWriter.buf.length) {
                        if (serializeWriter.writer == null) {
                            serializeWriter.expandCapacity(i6);
                        } else {
                            serializeWriter.flush();
                            i6 = 1;
                        }
                    }
                    serializeWriter.buf[serializeWriter.count] = ',';
                    serializeWriter.count = i6;
                }
                if (obj4 == null) {
                    serializeWriter.append((CharSequence) DYConstants.DY_NULL_STR);
                } else {
                    Class<?> cls = obj4.getClass();
                    if (cls == Integer.class) {
                        serializeWriter.writeInt(((Integer) obj4).intValue());
                    } else if (cls == Long.class) {
                        long longValue = ((Long) obj4).longValue();
                        if (z) {
                            serializeWriter.writeLong(longValue);
                            serializeWriter.write(76);
                        } else {
                            serializeWriter.writeLong(longValue);
                        }
                    } else if (cls == String.class) {
                        String str = (String) obj4;
                        if ((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                            serializeWriter.writeStringWithSingleQuote(str);
                        } else {
                            serializeWriter.writeStringWithDoubleQuote(str, (char) 0, true);
                        }
                    } else {
                        if ((serializeWriter.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
                            jSONSerializer.context = new SerialContext(serialContext, obj, obj2, 0);
                        }
                        IdentityHashMap<Object, SerialContext> identityHashMap2 = jSONSerializer.references;
                        if (identityHashMap2 != null && identityHashMap2.containsKey(obj4)) {
                            jSONSerializer.writeReference(obj4);
                        } else {
                            jSONSerializer.config.get(obj4.getClass()).write(jSONSerializer, obj4, Integer.valueOf(i5), collectionItemType);
                        }
                    }
                }
            }
            int i7 = serializeWriter.count + 1;
            if (i7 > serializeWriter.buf.length) {
                if (serializeWriter.writer == null) {
                    serializeWriter.expandCapacity(i7);
                } else {
                    serializeWriter.flush();
                    serializeWriter.buf[serializeWriter.count] = ']';
                    serializeWriter.count = i2;
                }
            }
            i2 = i7;
            serializeWriter.buf[serializeWriter.count] = ']';
            serializeWriter.count = i2;
        } finally {
            jSONSerializer.context = serialContext;
        }
    }
}
