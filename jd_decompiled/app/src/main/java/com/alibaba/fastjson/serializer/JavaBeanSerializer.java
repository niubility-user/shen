package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.PropertyNamingStrategy;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r35, java.lang.Object r36, java.lang.Object r37, java.lang.reflect.Type r38) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public JavaBeanSerializer(java.lang.Class<?> r15, int r16, java.util.Map<java.lang.String, java.lang.String> r17, boolean r18, boolean r19, boolean r20, boolean r21, com.alibaba.fastjson.PropertyNamingStrategy r22) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.<init>(java.lang.Class, int, java.util.Map, boolean, boolean, boolean, boolean, com.alibaba.fastjson.PropertyNamingStrategy):void");
    }
}
