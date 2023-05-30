package com.alibaba.fastjson.serializer;

import com.jd.dynamic.DYConstants;
import java.io.IOException;
import java.lang.reflect.Type;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ArraySerializer implements ObjectSerializer {
    private final ObjectSerializer compObjectSerializer;
    private final Class<?> componentType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArraySerializer(Class<?> cls, ObjectSerializer objectSerializer) {
        this.componentType = cls;
        this.compObjectSerializer = objectSerializer;
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public final void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
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
        int i2 = 0;
        if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            serializeWriter.write(91);
            while (i2 < zArr.length) {
                if (i2 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.write(zArr[i2]);
                i2++;
            }
            serializeWriter.write(93);
        } else if (obj instanceof byte[]) {
            serializeWriter.writeByteArray((byte[]) obj);
        } else if (obj instanceof char[]) {
            serializeWriter.writeString(new String((char[]) obj));
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            int length = dArr.length - 1;
            if (length == -1) {
                serializeWriter.append((CharSequence) "[]");
                return;
            }
            serializeWriter.write(91);
            while (i2 < length) {
                double d = dArr[i2];
                if (Double.isNaN(d)) {
                    serializeWriter.writeNull();
                } else {
                    serializeWriter.append((CharSequence) Double.toString(d));
                }
                serializeWriter.write(44);
                i2++;
            }
            double d2 = dArr[length];
            if (Double.isNaN(d2)) {
                serializeWriter.writeNull();
            } else {
                serializeWriter.append((CharSequence) Double.toString(d2));
            }
            serializeWriter.write(93);
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            int length2 = fArr.length - 1;
            if (length2 == -1) {
                serializeWriter.append((CharSequence) "[]");
                return;
            }
            serializeWriter.write(91);
            while (i2 < length2) {
                float f2 = fArr[i2];
                if (Float.isNaN(f2)) {
                    serializeWriter.writeNull();
                } else {
                    serializeWriter.append((CharSequence) Float.toString(f2));
                }
                serializeWriter.write(44);
                i2++;
            }
            float f3 = fArr[length2];
            if (Float.isNaN(f3)) {
                serializeWriter.writeNull();
            } else {
                serializeWriter.append((CharSequence) Float.toString(f3));
            }
            serializeWriter.write(93);
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            serializeWriter.write(91);
            while (i2 < iArr.length) {
                if (i2 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(iArr[i2]);
                i2++;
            }
            serializeWriter.write(93);
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            serializeWriter.write(91);
            while (i2 < jArr.length) {
                if (i2 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeLong(jArr[i2]);
                i2++;
            }
            serializeWriter.write(93);
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            serializeWriter.write(91);
            while (i2 < sArr.length) {
                if (i2 != 0) {
                    serializeWriter.write(44);
                }
                serializeWriter.writeInt(sArr[i2]);
                i2++;
            }
            serializeWriter.write(93);
        } else {
            Object[] objArr = (Object[]) obj;
            int length3 = objArr.length;
            SerialContext serialContext = jSONSerializer.context;
            jSONSerializer.setContext(serialContext, obj, obj2, 0);
            try {
                serializeWriter.write(91);
                while (i2 < length3) {
                    if (i2 != 0) {
                        serializeWriter.write(44);
                    }
                    Object obj3 = objArr[i2];
                    if (obj3 == null) {
                        if (serializeWriter.isEnabled(SerializerFeature.WriteNullStringAsEmpty) && (obj instanceof String[])) {
                            serializeWriter.writeString("");
                        } else {
                            serializeWriter.append((CharSequence) DYConstants.DY_NULL_STR);
                        }
                    } else if (obj3.getClass() == this.componentType) {
                        this.compObjectSerializer.write(jSONSerializer, obj3, Integer.valueOf(i2), null);
                    } else {
                        jSONSerializer.config.get(obj3.getClass()).write(jSONSerializer, obj3, Integer.valueOf(i2), null);
                    }
                    i2++;
                }
                serializeWriter.write(93);
            } finally {
                jSONSerializer.context = serialContext;
            }
        }
    }
}
