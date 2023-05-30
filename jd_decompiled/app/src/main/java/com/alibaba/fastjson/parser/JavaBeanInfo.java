package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.util.FieldInfo;
import com.jd.framework.json.anotation.JSONType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.alibaba.fastjson.parser.JavaBeanInfo build(java.lang.Class<?> r38, int r39, java.lang.reflect.Type r40, boolean r41, boolean r42, boolean r43, boolean r44, com.alibaba.fastjson.PropertyNamingStrategy r45) {
        /*
            Method dump skipped, instructions count: 2111
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JavaBeanInfo.build(java.lang.Class, int, java.lang.reflect.Type, boolean, boolean, boolean, boolean, com.alibaba.fastjson.PropertyNamingStrategy):com.alibaba.fastjson.parser.JavaBeanInfo");
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
