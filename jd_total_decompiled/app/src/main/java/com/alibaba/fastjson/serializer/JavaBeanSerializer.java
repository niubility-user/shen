package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.anotation.JSONType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class JavaBeanSerializer implements ObjectSerializer {
    protected int features;
    private final FieldSerializer[] getters;
    private final FieldSerializer[] sortedGetters;
    protected final String typeKey;
    protected final String typeName;
    private static final char[] true_chars = {'t', 'r', 'u', 'e'};
    private static final char[] false_chars = {'f', 'a', 'l', 's', 'e'};

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (PropertyNamingStrategy) null);
    }

    private static Map<String, String> map(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(3:(7:91|92|(3:(3:109|(3:112|(2:114|115)(1:334)|110)|335)|336|(7:117|(5:119|(1:121)(2:324|(2:326|327)(2:328|(1:330)(1:331)))|(4:(3:124|(1:126)(2:128|(1:130)(2:131|(1:133)))|127)|134|(3:137|(2:140|141)(1:139)|135)|322)|323|(1:143)(7:(4:(3:147|(1:149)(2:151|(1:153)(2:154|(1:156)))|150)|157|(2:160|158)|161)(1:321)|(5:(3:165|(1:167)(2:169|(1:171)(2:172|(1:174)))|168)|175|(2:178|176)|179|180)(1:320)|(1:319)(2:186|(4:188|100|101|102))|(6:(4:216|(2:218|(1:220)(1:221))|222|(1:224))(1:318)|(2:(1:227)|228)(1:(2:(1:234)|235)(2:(1:(4:238|(2:240|(1:242)(4:243|(1:244)|247|248))|250|248)(1:251))|(4:(2:288|(1:(3:291|(1:296)|297)(2:298|(1:300)(1:301)))(2:302|(1:(2:305|(3:307|(1:309)(1:313)|(1:311)(1:312))(1:314))(1:315))(1:316)))(1:317)|230|231|102)(2:254|(2:256|(1:258)(10:(1:260)(1:278)|261|(2:264|262)|265|266|(1:268)|269|(2:271|(1:273)(2:274|(1:276)))|277|(0)))(2:279|(1:281)(2:282|(1:(1:285)(1:286)))))))|229|230|231|102)|100|101|102))(1:333)|332|327|(0)|323|(0)(0)))|99|100|101|102)|88|89) */
    /* JADX WARN: Code restructure failed: missing block: B:382:0x0538, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:383:0x0539, code lost:
        r1 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:384:0x053c, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:385:0x053d, code lost:
        r1 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x0546, code lost:
        r3 = r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0132 A[Catch: all -> 0x0096, Exception -> 0x009b, TRY_ENTER, TRY_LEAVE, TryCatch #6 {Exception -> 0x009b, all -> 0x0096, blocks: (B:41:0x0089, B:43:0x008d, B:44:0x0091, B:52:0x00ab, B:54:0x00b4, B:58:0x00c3, B:61:0x00ca, B:63:0x00d1, B:65:0x00d5, B:71:0x00df, B:73:0x00e5, B:77:0x00ee, B:79:0x00f5, B:80:0x00fd, B:89:0x010f, B:90:0x0113, B:92:0x0119, B:100:0x0132, B:76:0x00ea), top: B:409:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0409 A[Catch: all -> 0x04d0, Exception -> 0x04d6, TryCatch #9 {Exception -> 0x04d6, all -> 0x04d0, blocks: (B:117:0x0162, B:119:0x017d, B:121:0x0181, B:124:0x0186, B:126:0x018a, B:130:0x0193, B:131:0x0197, B:133:0x019d, B:139:0x01b6, B:141:0x01bd, B:143:0x01c1, B:156:0x020d, B:158:0x0211, B:160:0x0218, B:162:0x021c, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:167:0x022e, B:169:0x0234, B:179:0x0254, B:181:0x0258, B:183:0x0260, B:185:0x0264, B:186:0x0269, B:188:0x026d, B:189:0x0272, B:190:0x0279, B:192:0x027f, B:197:0x0299, B:199:0x029d, B:201:0x02a4, B:203:0x02a8, B:204:0x02ad, B:206:0x02b1, B:207:0x02b6, B:208:0x02bd, B:210:0x02c3, B:216:0x02e0, B:218:0x02e4, B:225:0x02f8, B:227:0x02fc, B:229:0x0300, B:231:0x0304, B:233:0x0308, B:235:0x030c, B:242:0x031e, B:244:0x0322, B:246:0x0326, B:237:0x0310, B:239:0x0314, B:250:0x0338, B:252:0x0341, B:254:0x0345, B:255:0x0349, B:256:0x034d, B:258:0x0362, B:262:0x036e, B:263:0x0372, B:267:0x037c, B:268:0x037f, B:271:0x0387, B:273:0x0392, B:275:0x0396, B:277:0x039b, B:281:0x03b7, B:282:0x03c1, B:285:0x03c8, B:289:0x03d2, B:294:0x03de, B:296:0x03e4, B:298:0x03e8, B:299:0x03ea, B:301:0x03f2, B:303:0x03f6, B:304:0x03fa, B:307:0x0409, B:308:0x0413, B:309:0x0416, B:311:0x041a, B:312:0x0423, B:315:0x0429, B:316:0x0434, B:321:0x0447, B:323:0x0450, B:326:0x0456, B:327:0x045b, B:328:0x0462, B:330:0x0466, B:331:0x046b, B:332:0x0472, B:335:0x0478, B:337:0x0481, B:342:0x0495, B:343:0x049a, B:344:0x049f, B:345:0x04aa, B:346:0x04af, B:347:0x04b4, B:144:0x01d1, B:146:0x01d5, B:147:0x01e1, B:149:0x01e5, B:150:0x01f5, B:151:0x01fc, B:360:0x04eb, B:361:0x04ef, B:363:0x04f5, B:368:0x0505, B:370:0x050e, B:373:0x051d, B:375:0x0521, B:376:0x0525), top: B:404:0x0162 }] */
    /* JADX WARN: Removed duplicated region for block: B:357:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0505 A[Catch: all -> 0x04d0, Exception -> 0x04d6, TRY_ENTER, TryCatch #9 {Exception -> 0x04d6, all -> 0x04d0, blocks: (B:117:0x0162, B:119:0x017d, B:121:0x0181, B:124:0x0186, B:126:0x018a, B:130:0x0193, B:131:0x0197, B:133:0x019d, B:139:0x01b6, B:141:0x01bd, B:143:0x01c1, B:156:0x020d, B:158:0x0211, B:160:0x0218, B:162:0x021c, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:167:0x022e, B:169:0x0234, B:179:0x0254, B:181:0x0258, B:183:0x0260, B:185:0x0264, B:186:0x0269, B:188:0x026d, B:189:0x0272, B:190:0x0279, B:192:0x027f, B:197:0x0299, B:199:0x029d, B:201:0x02a4, B:203:0x02a8, B:204:0x02ad, B:206:0x02b1, B:207:0x02b6, B:208:0x02bd, B:210:0x02c3, B:216:0x02e0, B:218:0x02e4, B:225:0x02f8, B:227:0x02fc, B:229:0x0300, B:231:0x0304, B:233:0x0308, B:235:0x030c, B:242:0x031e, B:244:0x0322, B:246:0x0326, B:237:0x0310, B:239:0x0314, B:250:0x0338, B:252:0x0341, B:254:0x0345, B:255:0x0349, B:256:0x034d, B:258:0x0362, B:262:0x036e, B:263:0x0372, B:267:0x037c, B:268:0x037f, B:271:0x0387, B:273:0x0392, B:275:0x0396, B:277:0x039b, B:281:0x03b7, B:282:0x03c1, B:285:0x03c8, B:289:0x03d2, B:294:0x03de, B:296:0x03e4, B:298:0x03e8, B:299:0x03ea, B:301:0x03f2, B:303:0x03f6, B:304:0x03fa, B:307:0x0409, B:308:0x0413, B:309:0x0416, B:311:0x041a, B:312:0x0423, B:315:0x0429, B:316:0x0434, B:321:0x0447, B:323:0x0450, B:326:0x0456, B:327:0x045b, B:328:0x0462, B:330:0x0466, B:331:0x046b, B:332:0x0472, B:335:0x0478, B:337:0x0481, B:342:0x0495, B:343:0x049a, B:344:0x049f, B:345:0x04aa, B:346:0x04af, B:347:0x04b4, B:144:0x01d1, B:146:0x01d5, B:147:0x01e1, B:149:0x01e5, B:150:0x01f5, B:151:0x01fc, B:360:0x04eb, B:361:0x04ef, B:363:0x04f5, B:368:0x0505, B:370:0x050e, B:373:0x051d, B:375:0x0521, B:376:0x0525), top: B:404:0x0162 }] */
    /* JADX WARN: Removed duplicated region for block: B:373:0x051d A[Catch: all -> 0x04d0, Exception -> 0x04d6, TRY_ENTER, TryCatch #9 {Exception -> 0x04d6, all -> 0x04d0, blocks: (B:117:0x0162, B:119:0x017d, B:121:0x0181, B:124:0x0186, B:126:0x018a, B:130:0x0193, B:131:0x0197, B:133:0x019d, B:139:0x01b6, B:141:0x01bd, B:143:0x01c1, B:156:0x020d, B:158:0x0211, B:160:0x0218, B:162:0x021c, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:167:0x022e, B:169:0x0234, B:179:0x0254, B:181:0x0258, B:183:0x0260, B:185:0x0264, B:186:0x0269, B:188:0x026d, B:189:0x0272, B:190:0x0279, B:192:0x027f, B:197:0x0299, B:199:0x029d, B:201:0x02a4, B:203:0x02a8, B:204:0x02ad, B:206:0x02b1, B:207:0x02b6, B:208:0x02bd, B:210:0x02c3, B:216:0x02e0, B:218:0x02e4, B:225:0x02f8, B:227:0x02fc, B:229:0x0300, B:231:0x0304, B:233:0x0308, B:235:0x030c, B:242:0x031e, B:244:0x0322, B:246:0x0326, B:237:0x0310, B:239:0x0314, B:250:0x0338, B:252:0x0341, B:254:0x0345, B:255:0x0349, B:256:0x034d, B:258:0x0362, B:262:0x036e, B:263:0x0372, B:267:0x037c, B:268:0x037f, B:271:0x0387, B:273:0x0392, B:275:0x0396, B:277:0x039b, B:281:0x03b7, B:282:0x03c1, B:285:0x03c8, B:289:0x03d2, B:294:0x03de, B:296:0x03e4, B:298:0x03e8, B:299:0x03ea, B:301:0x03f2, B:303:0x03f6, B:304:0x03fa, B:307:0x0409, B:308:0x0413, B:309:0x0416, B:311:0x041a, B:312:0x0423, B:315:0x0429, B:316:0x0434, B:321:0x0447, B:323:0x0450, B:326:0x0456, B:327:0x045b, B:328:0x0462, B:330:0x0466, B:331:0x046b, B:332:0x0472, B:335:0x0478, B:337:0x0481, B:342:0x0495, B:343:0x049a, B:344:0x049f, B:345:0x04aa, B:346:0x04af, B:347:0x04b4, B:144:0x01d1, B:146:0x01d5, B:147:0x01e1, B:149:0x01e5, B:150:0x01f5, B:151:0x01fc, B:360:0x04eb, B:361:0x04ef, B:363:0x04f5, B:368:0x0505, B:370:0x050e, B:373:0x051d, B:375:0x0521, B:376:0x0525), top: B:404:0x0162 }] */
    /* JADX WARN: Removed duplicated region for block: B:402:0x054e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0162 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010f A[Catch: all -> 0x0096, Exception -> 0x009b, TRY_ENTER, TryCatch #6 {Exception -> 0x009b, all -> 0x0096, blocks: (B:41:0x0089, B:43:0x008d, B:44:0x0091, B:52:0x00ab, B:54:0x00b4, B:58:0x00c3, B:61:0x00ca, B:63:0x00d1, B:65:0x00d5, B:71:0x00df, B:73:0x00e5, B:77:0x00ee, B:79:0x00f5, B:80:0x00fd, B:89:0x010f, B:90:0x0113, B:92:0x0119, B:100:0x0132, B:76:0x00ea), top: B:409:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0128  */
    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        IdentityHashMap<Object, SerialContext> identityHashMap;
        FieldSerializer[] fieldSerializerArr;
        SerialContext serialContext;
        Exception exc;
        Throwable th;
        boolean z;
        boolean z2;
        List<BeforeFilter> list;
        int i2;
        boolean z3;
        int i3;
        FieldSerializer[] fieldSerializerArr2;
        List<AfterFilter> list2;
        int i4;
        int i5;
        String str;
        boolean z4;
        long j2;
        boolean z5;
        boolean z6;
        int i6;
        boolean z7;
        boolean z8;
        List<PropertyPreFilter> list3;
        String str2;
        List<ValueFilter> list4;
        Object obj3;
        List<NameFilter> list5;
        List<PropertyFilter> list6;
        boolean z9;
        int i7;
        Object valueOf;
        SerialContext serialContext2;
        JavaBeanSerializer javaBeanSerializer = this;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        SerialContext serialContext3 = jSONSerializer.context;
        if ((serialContext3 == null || (serialContext3.features & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) && (identityHashMap = jSONSerializer.references) != null && identityHashMap.containsKey(obj)) {
            jSONSerializer.writeReference(obj);
            return;
        }
        int i8 = serializeWriter.features;
        if ((SerializerFeature.SortField.mask & i8) != 0) {
            fieldSerializerArr = javaBeanSerializer.sortedGetters;
        } else {
            fieldSerializerArr = javaBeanSerializer.getters;
        }
        SerialContext serialContext4 = jSONSerializer.context;
        if ((i8 & SerializerFeature.DisableCircularReferenceDetect.mask) == 0) {
            jSONSerializer.context = new SerialContext(serialContext4, obj, obj2, javaBeanSerializer.features);
            if (jSONSerializer.references == null) {
                jSONSerializer.references = new IdentityHashMap<>();
            }
            jSONSerializer.references.put(obj, jSONSerializer.context);
        }
        int i9 = javaBeanSerializer.features;
        int i10 = SerializerFeature.BeanToArray.mask;
        boolean z10 = ((i9 & i10) == 0 && (serializeWriter.features & i10) == 0) ? false : true;
        char c2 = z10 ? '[' : '{';
        char c3 = z10 ? ']' : '}';
        try {
            int i11 = serializeWriter.count + 1;
            if (i11 > serializeWriter.buf.length) {
                try {
                    if (serializeWriter.writer == null) {
                        serializeWriter.expandCapacity(i11);
                    } else {
                        serializeWriter.flush();
                        i11 = 1;
                    }
                } catch (Exception e2) {
                    exc = e2;
                    serialContext = serialContext4;
                    String str3 = "write javaBean error, fastjson version 1.1.65";
                    if (obj2 != null) {
                    }
                    throw new JSONException(str3, exc);
                } catch (Throwable th2) {
                    th = th2;
                    serialContext = serialContext4;
                    jSONSerializer.context = serialContext;
                    throw th;
                }
            }
            serializeWriter.buf[serializeWriter.count] = c2;
            serializeWriter.count = i11;
            if (fieldSerializerArr.length > 0 && (serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                jSONSerializer.incrementIndent();
                jSONSerializer.println();
            }
            int i12 = javaBeanSerializer.features;
            int i13 = SerializerFeature.WriteClassName.mask;
            if ((i12 & i13) == 0) {
                int i14 = serializeWriter.features;
                if ((i13 & i14) == 0 || (type == null && (i14 & SerializerFeature.NotWriteRootClassName.mask) != 0 && ((serialContext2 = jSONSerializer.context) == null || serialContext2.parent == null))) {
                    z = false;
                    if (z || obj.getClass() == type) {
                        z2 = false;
                    } else {
                        String str4 = javaBeanSerializer.typeKey;
                        if (str4 == null) {
                            str4 = jSONSerializer.config.typeKey;
                        }
                        serializeWriter.writeFieldName(str4, false);
                        String str5 = javaBeanSerializer.typeName;
                        if (str5 == null) {
                            str5 = obj.getClass().getName();
                        }
                        jSONSerializer.write(str5);
                        z2 = true;
                    }
                    char c4 = !z2 ? ',' : (char) 0;
                    list = jSONSerializer.beforeFilters;
                    if (list != null) {
                        Iterator<BeforeFilter> it = list.iterator();
                        while (it.hasNext()) {
                            c4 = it.next().writeBefore(jSONSerializer, obj, c4);
                        }
                    }
                    boolean z11 = c4 != ',';
                    i2 = serializeWriter.features;
                    if ((SerializerFeature.QuoteFieldNames.mask & i2) != 0) {
                        if ((SerializerFeature.UseSingleQuotes.mask & i2) == 0) {
                            z3 = true;
                            boolean z12 = (SerializerFeature.UseSingleQuotes.mask & i2) != 0;
                            boolean z13 = (SerializerFeature.NotWriteDefaultValue.mask & i2) != 0;
                            List<PropertyFilter> list7 = jSONSerializer.propertyFilters;
                            List<NameFilter> list8 = jSONSerializer.nameFilters;
                            boolean z14 = z11;
                            List<ValueFilter> list9 = jSONSerializer.valueFilters;
                            List<PropertyPreFilter> list10 = jSONSerializer.propertyPreFilters;
                            char c5 = c3;
                            i3 = 0;
                            while (i3 < fieldSerializerArr.length) {
                                try {
                                    FieldSerializer fieldSerializer = fieldSerializerArr[i3];
                                    FieldSerializer[] fieldSerializerArr3 = fieldSerializerArr;
                                    FieldInfo fieldInfo = fieldSerializer.fieldInfo;
                                    int i15 = i3;
                                    Class<?> cls = fieldInfo.fieldClass;
                                    boolean z15 = z12;
                                    String str6 = fieldInfo.name;
                                    boolean z16 = z3;
                                    boolean z17 = z13;
                                    if (((SerializerFeature.SkipTransientField.mask & serializeWriter.features) == 0 || fieldInfo.field == null || !fieldInfo.fieldTransient) && ((str = javaBeanSerializer.typeKey) == null || !str.equals(str6))) {
                                        if (list10 != null) {
                                            Iterator<PropertyPreFilter> it2 = list10.iterator();
                                            while (it2.hasNext()) {
                                                if (!it2.next().apply(jSONSerializer, obj, str6)) {
                                                    z4 = false;
                                                    break;
                                                }
                                            }
                                        }
                                        z4 = true;
                                        if (z4) {
                                            Object obj4 = null;
                                            if (fieldInfo.fieldAccess) {
                                                if (cls == Integer.TYPE) {
                                                    j2 = 0;
                                                    z6 = false;
                                                    z7 = false;
                                                    i6 = fieldInfo.field.getInt(obj);
                                                    z5 = true;
                                                } else if (cls == Long.TYPE) {
                                                    j2 = fieldInfo.field.getLong(obj);
                                                    z5 = true;
                                                    z6 = false;
                                                    i6 = 0;
                                                    z7 = false;
                                                } else if (cls == Boolean.TYPE) {
                                                    z7 = fieldInfo.field.getBoolean(obj);
                                                    j2 = 0;
                                                    z5 = true;
                                                    z6 = false;
                                                    i6 = 0;
                                                } else {
                                                    obj4 = fieldInfo.field.get(obj);
                                                }
                                                if (list7 != null) {
                                                    if (z5) {
                                                        if (cls == Integer.TYPE) {
                                                            obj4 = Integer.valueOf(i6);
                                                        } else if (cls == Long.TYPE) {
                                                            obj4 = Long.valueOf(j2);
                                                        } else if (cls == Boolean.TYPE) {
                                                            obj4 = Boolean.valueOf(z7);
                                                        }
                                                        z6 = true;
                                                    }
                                                    Iterator<PropertyFilter> it3 = list7.iterator();
                                                    while (it3.hasNext()) {
                                                        Iterator<PropertyFilter> it4 = it3;
                                                        if (!it3.next().apply(obj, str6, obj4)) {
                                                            z8 = false;
                                                            break;
                                                        }
                                                        it3 = it4;
                                                    }
                                                }
                                                z8 = true;
                                                if (!z8) {
                                                    if (list8 != null) {
                                                        if (z5 && !z6) {
                                                            if (cls == Integer.TYPE) {
                                                                valueOf = Integer.valueOf(i6);
                                                            } else if (cls == Long.TYPE) {
                                                                valueOf = Long.valueOf(j2);
                                                            } else if (cls == Boolean.TYPE) {
                                                                valueOf = Boolean.valueOf(z7);
                                                            }
                                                            obj4 = valueOf;
                                                            z6 = true;
                                                        }
                                                        list3 = list10;
                                                        str2 = str6;
                                                        for (Iterator<NameFilter> it5 = list8.iterator(); it5.hasNext(); it5 = it5) {
                                                            str2 = it5.next().process(obj, str2, obj4);
                                                        }
                                                    } else {
                                                        list3 = list10;
                                                        str2 = str6;
                                                    }
                                                    if (list9 != null) {
                                                        if (z5 && !z6) {
                                                            if (cls == Integer.TYPE) {
                                                                obj4 = Integer.valueOf(i6);
                                                            } else if (cls == Long.TYPE) {
                                                                obj4 = Long.valueOf(j2);
                                                            } else if (cls == Boolean.TYPE) {
                                                                obj4 = Boolean.valueOf(z7);
                                                            }
                                                            z6 = true;
                                                        }
                                                        list4 = list9;
                                                        Object obj5 = obj4;
                                                        for (Iterator<ValueFilter> it6 = list9.iterator(); it6.hasNext(); it6 = it6) {
                                                            obj5 = it6.next().process(obj, str6, obj5);
                                                        }
                                                        obj3 = obj4;
                                                        obj4 = obj5;
                                                    } else {
                                                        list4 = list9;
                                                        obj3 = obj4;
                                                    }
                                                    if (!z6 || obj4 != null || z10 || fieldSerializer.writeNull) {
                                                        list5 = list8;
                                                    } else {
                                                        list5 = list8;
                                                        if ((serializeWriter.features & SerializerFeature.WriteMapNullValue.mask) == 0) {
                                                            list6 = list7;
                                                            i3 = i15 + 1;
                                                            javaBeanSerializer = this;
                                                            fieldSerializerArr = fieldSerializerArr3;
                                                            z12 = z15;
                                                            z3 = z16;
                                                            z13 = z17;
                                                            list10 = list3;
                                                            list9 = list4;
                                                            list8 = list5;
                                                            list7 = list6;
                                                        }
                                                    }
                                                    if (!z6 || obj4 == null || !z17 || (((cls != Byte.TYPE && cls != Short.TYPE && cls != Integer.TYPE && cls != Long.TYPE && cls != Float.TYPE && cls != Double.TYPE) || !(obj4 instanceof Number) || ((Number) obj4).byteValue() != 0) && (cls != Boolean.TYPE || !(obj4 instanceof Boolean) || ((Boolean) obj4).booleanValue()))) {
                                                        if (z14) {
                                                            int i16 = serializeWriter.count + 1;
                                                            if (i16 > serializeWriter.buf.length) {
                                                                if (serializeWriter.writer == null) {
                                                                    serializeWriter.expandCapacity(i16);
                                                                } else {
                                                                    serializeWriter.flush();
                                                                    i16 = 1;
                                                                }
                                                            }
                                                            list6 = list7;
                                                            serializeWriter.buf[serializeWriter.count] = ',';
                                                            serializeWriter.count = i16;
                                                            if ((serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                                                                jSONSerializer.println();
                                                            }
                                                        } else {
                                                            list6 = list7;
                                                        }
                                                        if (str2 != str6) {
                                                            if (!z10) {
                                                                serializeWriter.writeFieldName(str2, true);
                                                            }
                                                            jSONSerializer.write(obj4);
                                                        } else if (obj3 != obj4) {
                                                            if (!z10) {
                                                                fieldSerializer.writePrefix(jSONSerializer);
                                                            }
                                                            jSONSerializer.write(obj4);
                                                        } else {
                                                            if (!z10) {
                                                                if (z16) {
                                                                    char[] cArr = fieldSerializer.name_chars;
                                                                    int length = cArr.length;
                                                                    int i17 = serializeWriter.count + length;
                                                                    if (i17 > serializeWriter.buf.length) {
                                                                        if (serializeWriter.writer == null) {
                                                                            serializeWriter.expandCapacity(i17);
                                                                        } else {
                                                                            int i18 = 0;
                                                                            do {
                                                                                char[] cArr2 = serializeWriter.buf;
                                                                                int length2 = cArr2.length;
                                                                                int i19 = serializeWriter.count;
                                                                                int i20 = length2 - i19;
                                                                                System.arraycopy(cArr, i18, cArr2, i19, i20);
                                                                                serializeWriter.count = serializeWriter.buf.length;
                                                                                serializeWriter.flush();
                                                                                length -= i20;
                                                                                i18 += i20;
                                                                            } while (length > serializeWriter.buf.length);
                                                                            i7 = i18;
                                                                            i17 = length;
                                                                            System.arraycopy(cArr, i7, serializeWriter.buf, serializeWriter.count, length);
                                                                            serializeWriter.count = i17;
                                                                        }
                                                                    }
                                                                    i7 = 0;
                                                                    System.arraycopy(cArr, i7, serializeWriter.buf, serializeWriter.count, length);
                                                                    serializeWriter.count = i17;
                                                                } else {
                                                                    fieldSerializer.writePrefix(jSONSerializer);
                                                                }
                                                            }
                                                            if (!z5 || z6) {
                                                                if (!z10) {
                                                                    if (cls == String.class) {
                                                                        if (obj4 == null) {
                                                                            int i21 = serializeWriter.features;
                                                                            int i22 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                                            if ((i21 & i22) == 0 && (fieldSerializer.features & i22) == 0) {
                                                                                serializeWriter.writeNull();
                                                                            }
                                                                            serializeWriter.writeString("");
                                                                        } else {
                                                                            String str7 = (String) obj4;
                                                                            if (z15) {
                                                                                serializeWriter.writeStringWithSingleQuote(str7);
                                                                            } else {
                                                                                serializeWriter.writeStringWithDoubleQuote(str7, (char) 0, true);
                                                                            }
                                                                        }
                                                                    } else if (!fieldInfo.isEnum) {
                                                                        fieldSerializer.writeValue(jSONSerializer, obj4);
                                                                    } else if (obj4 != null) {
                                                                        if ((serializeWriter.features & SerializerFeature.WriteEnumUsingToString.mask) != 0) {
                                                                            String str8 = ((Enum) obj4).toString();
                                                                            if ((serializeWriter.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                                                                                serializeWriter.writeStringWithSingleQuote(str8);
                                                                            } else {
                                                                                serializeWriter.writeStringWithDoubleQuote(str8, (char) 0, false);
                                                                            }
                                                                        } else {
                                                                            serializeWriter.writeInt(((Enum) obj4).ordinal());
                                                                        }
                                                                    } else {
                                                                        serializeWriter.writeNull();
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer, obj4);
                                                                }
                                                                z14 = true;
                                                                i3 = i15 + 1;
                                                                javaBeanSerializer = this;
                                                                fieldSerializerArr = fieldSerializerArr3;
                                                                z12 = z15;
                                                                z3 = z16;
                                                                z13 = z17;
                                                                list10 = list3;
                                                                list9 = list4;
                                                                list8 = list5;
                                                                list7 = list6;
                                                            } else if (cls == Integer.TYPE) {
                                                                int i23 = i6;
                                                                if (i23 == Integer.MIN_VALUE) {
                                                                    serializeWriter.write("-2147483648");
                                                                } else {
                                                                    int i24 = 0;
                                                                    while ((i23 < 0 ? -i23 : i23) > SerializeWriter.sizeTable[i24]) {
                                                                        i24++;
                                                                    }
                                                                    int i25 = i24 + 1;
                                                                    if (i23 < 0) {
                                                                        i25++;
                                                                    }
                                                                    int i26 = serializeWriter.count + i25;
                                                                    if (i26 > serializeWriter.buf.length) {
                                                                        if (serializeWriter.writer == null) {
                                                                            serializeWriter.expandCapacity(i26);
                                                                        } else {
                                                                            char[] cArr3 = new char[i25];
                                                                            SerializeWriter.getChars(i23, i25, cArr3);
                                                                            serializeWriter.write(cArr3, 0, i25);
                                                                            z9 = true;
                                                                            if (!z9) {
                                                                                SerializeWriter.getChars(i23, i26, serializeWriter.buf);
                                                                                serializeWriter.count = i26;
                                                                            }
                                                                        }
                                                                    }
                                                                    z9 = false;
                                                                    if (!z9) {
                                                                    }
                                                                }
                                                            } else if (cls == Long.TYPE) {
                                                                jSONSerializer.out.writeLong(j2);
                                                            } else if (cls == Boolean.TYPE) {
                                                                if (z7) {
                                                                    SerializeWriter serializeWriter2 = jSONSerializer.out;
                                                                    char[] cArr4 = true_chars;
                                                                    serializeWriter2.write(cArr4, 0, cArr4.length);
                                                                } else {
                                                                    SerializeWriter serializeWriter3 = jSONSerializer.out;
                                                                    char[] cArr5 = false_chars;
                                                                    serializeWriter3.write(cArr5, 0, cArr5.length);
                                                                }
                                                            }
                                                        }
                                                        z14 = true;
                                                        i3 = i15 + 1;
                                                        javaBeanSerializer = this;
                                                        fieldSerializerArr = fieldSerializerArr3;
                                                        z12 = z15;
                                                        z3 = z16;
                                                        z13 = z17;
                                                        list10 = list3;
                                                        list9 = list4;
                                                        list8 = list5;
                                                        list7 = list6;
                                                    }
                                                    list6 = list7;
                                                    i3 = i15 + 1;
                                                    javaBeanSerializer = this;
                                                    fieldSerializerArr = fieldSerializerArr3;
                                                    z12 = z15;
                                                    z3 = z16;
                                                    z13 = z17;
                                                    list10 = list3;
                                                    list9 = list4;
                                                    list8 = list5;
                                                    list7 = list6;
                                                }
                                            } else {
                                                obj4 = fieldSerializer.getPropertyValue(obj);
                                            }
                                            j2 = 0;
                                            z5 = false;
                                            z6 = true;
                                            i6 = 0;
                                            z7 = false;
                                            if (list7 != null) {
                                            }
                                            z8 = true;
                                            if (!z8) {
                                            }
                                        }
                                    }
                                    list3 = list10;
                                    list4 = list9;
                                    list5 = list8;
                                    list6 = list7;
                                    i3 = i15 + 1;
                                    javaBeanSerializer = this;
                                    fieldSerializerArr = fieldSerializerArr3;
                                    z12 = z15;
                                    z3 = z16;
                                    z13 = z17;
                                    list10 = list3;
                                    list9 = list4;
                                    list8 = list5;
                                    list7 = list6;
                                } catch (Exception e3) {
                                    exc = e3;
                                    serialContext = serialContext4;
                                    String str32 = "write javaBean error, fastjson version 1.1.65";
                                    if (obj2 != null) {
                                        try {
                                            str32 = "write javaBean error, fastjson version 1.1.65, fieldName : " + obj2;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            th = th;
                                            jSONSerializer.context = serialContext;
                                            throw th;
                                        }
                                    }
                                    throw new JSONException(str32, exc);
                                } catch (Throwable th4) {
                                    th = th4;
                                    serialContext = serialContext4;
                                    jSONSerializer.context = serialContext;
                                    throw th;
                                }
                            }
                            fieldSerializerArr2 = fieldSerializerArr;
                            list2 = jSONSerializer.afterFilters;
                            if (list2 != null) {
                                char c6 = z14 ? ',' : (char) 0;
                                Iterator<AfterFilter> it7 = list2.iterator();
                                while (it7.hasNext()) {
                                    c6 = it7.next().writeAfter(jSONSerializer, obj, c6);
                                }
                            }
                            if (fieldSerializerArr2.length > 0 && (serializeWriter.features & SerializerFeature.PrettyFormat.mask) != 0) {
                                jSONSerializer.decrementIdent();
                                jSONSerializer.println();
                            }
                            i4 = serializeWriter.count + 1;
                            if (i4 > serializeWriter.buf.length) {
                                if (serializeWriter.writer == null) {
                                    serializeWriter.expandCapacity(i4);
                                } else {
                                    serializeWriter.flush();
                                    i5 = 1;
                                    serializeWriter.buf[serializeWriter.count] = c5;
                                    serializeWriter.count = i5;
                                    jSONSerializer.context = serialContext4;
                                }
                            }
                            i5 = i4;
                            serializeWriter.buf[serializeWriter.count] = c5;
                            serializeWriter.count = i5;
                            jSONSerializer.context = serialContext4;
                        }
                    }
                    z3 = false;
                    if ((SerializerFeature.UseSingleQuotes.mask & i2) != 0) {
                    }
                    if ((SerializerFeature.NotWriteDefaultValue.mask & i2) != 0) {
                    }
                    List<PropertyFilter> list72 = jSONSerializer.propertyFilters;
                    List<NameFilter> list82 = jSONSerializer.nameFilters;
                    boolean z142 = z11;
                    List<ValueFilter> list92 = jSONSerializer.valueFilters;
                    List<PropertyPreFilter> list102 = jSONSerializer.propertyPreFilters;
                    char c52 = c3;
                    i3 = 0;
                    while (i3 < fieldSerializerArr.length) {
                    }
                    fieldSerializerArr2 = fieldSerializerArr;
                    list2 = jSONSerializer.afterFilters;
                    if (list2 != null) {
                    }
                    if (fieldSerializerArr2.length > 0) {
                        jSONSerializer.decrementIdent();
                        jSONSerializer.println();
                    }
                    i4 = serializeWriter.count + 1;
                    if (i4 > serializeWriter.buf.length) {
                    }
                    i5 = i4;
                    serializeWriter.buf[serializeWriter.count] = c52;
                    serializeWriter.count = i5;
                    jSONSerializer.context = serialContext4;
                }
            }
            z = true;
            if (z) {
            }
            z2 = false;
            if (!z2) {
            }
            list = jSONSerializer.beforeFilters;
            if (list != null) {
            }
            if (c4 != ',') {
            }
            i2 = serializeWriter.features;
            if ((SerializerFeature.QuoteFieldNames.mask & i2) != 0) {
            }
            z3 = false;
            if ((SerializerFeature.UseSingleQuotes.mask & i2) != 0) {
            }
            if ((SerializerFeature.NotWriteDefaultValue.mask & i2) != 0) {
            }
            List<PropertyFilter> list722 = jSONSerializer.propertyFilters;
            List<NameFilter> list822 = jSONSerializer.nameFilters;
            boolean z1422 = z11;
            List<ValueFilter> list922 = jSONSerializer.valueFilters;
            List<PropertyPreFilter> list1022 = jSONSerializer.propertyPreFilters;
            char c522 = c3;
            i3 = 0;
            while (i3 < fieldSerializerArr.length) {
            }
            fieldSerializerArr2 = fieldSerializerArr;
            list2 = jSONSerializer.afterFilters;
            if (list2 != null) {
            }
            if (fieldSerializerArr2.length > 0) {
            }
            i4 = serializeWriter.count + 1;
            if (i4 > serializeWriter.buf.length) {
            }
            i5 = i4;
            serializeWriter.buf[serializeWriter.count] = c522;
            serializeWriter.count = i5;
            jSONSerializer.context = serialContext4;
        } catch (Exception e4) {
            e = e4;
            serialContext = serialContext4;
        } catch (Throwable th5) {
            th = th5;
            serialContext = serialContext4;
        }
    }

    public JavaBeanSerializer(Class<?> cls, PropertyNamingStrategy propertyNamingStrategy) {
        this(cls, cls.getModifiers(), null, false, true, true, true, propertyNamingStrategy);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, cls.getModifiers(), map(strArr), false, true, true, true, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a7 A[LOOP:2: B:35:0x00a1->B:37:0x00a7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JavaBeanSerializer(Class<?> cls, int i2, Map<String, String> map, boolean z, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        String str;
        String str2;
        Iterator<FieldInfo> it;
        FieldSerializer[] fieldSerializerArr;
        String[] orders;
        FieldSerializer[] fieldSerializerArr2;
        this.features = 0;
        JSONType jSONType = z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null;
        if (jSONType != null) {
            this.features = SerializerFeature.of(jSONType.serialzeFeatures());
            str = jSONType.typeName();
            if (str.length() != 0) {
                str2 = null;
                for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                    JSONType jSONType2 = (JSONType) superclass.getAnnotation(JSONType.class);
                    if (jSONType2 == null) {
                        break;
                    }
                    str2 = jSONType2.typeKey();
                    if (str2.length() != 0) {
                        break;
                    }
                }
                for (Class<?> cls2 : cls.getInterfaces()) {
                    JSONType jSONType3 = (JSONType) cls2.getAnnotation(JSONType.class);
                    if (jSONType3 != null) {
                        str2 = jSONType3.typeKey();
                        if (str2.length() != 0) {
                            break;
                        }
                    }
                }
                if (str2 != null && str2.length() == 0) {
                    str2 = null;
                }
                this.typeName = str;
                this.typeKey = str2;
                List<FieldInfo> computeGetters = TypeUtils.computeGetters(cls, i2, z, jSONType, map, false, z3, z4, propertyNamingStrategy);
                ArrayList arrayList = new ArrayList();
                it = computeGetters.iterator();
                while (it.hasNext()) {
                    arrayList.add(new FieldSerializer(it.next()));
                }
                fieldSerializerArr = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
                this.getters = fieldSerializerArr;
                orders = jSONType != null ? jSONType.orders() : null;
                if (orders == null && orders.length != 0) {
                    List<FieldInfo> computeGetters2 = TypeUtils.computeGetters(cls, i2, z, jSONType, map, true, z3, z4, propertyNamingStrategy);
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<FieldInfo> it2 = computeGetters2.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(new FieldSerializer(it2.next()));
                    }
                    this.sortedGetters = (FieldSerializer[]) arrayList2.toArray(new FieldSerializer[arrayList2.size()]);
                    return;
                }
                fieldSerializerArr2 = new FieldSerializer[fieldSerializerArr.length];
                System.arraycopy(fieldSerializerArr, 0, fieldSerializerArr2, 0, fieldSerializerArr.length);
                Arrays.sort(fieldSerializerArr2);
                if (!Arrays.equals(fieldSerializerArr2, fieldSerializerArr)) {
                    this.sortedGetters = fieldSerializerArr;
                    return;
                } else {
                    this.sortedGetters = fieldSerializerArr2;
                    return;
                }
            }
        }
        str = null;
        str2 = null;
        this.typeName = str;
        this.typeKey = str2;
        List<FieldInfo> computeGetters3 = TypeUtils.computeGetters(cls, i2, z, jSONType, map, false, z3, z4, propertyNamingStrategy);
        ArrayList arrayList3 = new ArrayList();
        it = computeGetters3.iterator();
        while (it.hasNext()) {
        }
        fieldSerializerArr = (FieldSerializer[]) arrayList3.toArray(new FieldSerializer[arrayList3.size()]);
        this.getters = fieldSerializerArr;
        if (jSONType != null) {
        }
        if (orders == null) {
        }
        fieldSerializerArr2 = new FieldSerializer[fieldSerializerArr.length];
        System.arraycopy(fieldSerializerArr, 0, fieldSerializerArr2, 0, fieldSerializerArr.length);
        Arrays.sort(fieldSerializerArr2);
        if (!Arrays.equals(fieldSerializerArr2, fieldSerializerArr)) {
        }
    }
}
