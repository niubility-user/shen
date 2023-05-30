package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    public ParserConfig config;
    protected ParseContext contex;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    protected List<ExtraProcessor> extraProcessors;
    protected List<ExtraTypeProvider> extraTypeProviders;
    public FieldTypeResolver fieldTypeResolver;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    /* loaded from: classes.dex */
    public static class ResolveTask {
        private final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        private final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.global, JDJSON.DEFAULT_PARSER_FEATURE);
    }

    public final void accept(int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token == i2) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i2) + ", actual " + JSONToken.name(this.lexer.token));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkListResolve(Collection collection) {
        if (collection instanceof List) {
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, collection.size() - 1);
            lastResolveTask.ownerContext = this.contex;
            this.resolveStatus = 0;
            return;
        }
        ResolveTask lastResolveTask2 = getLastResolveTask();
        lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
        lastResolveTask2.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void checkMapResolve(Map map, Object obj) {
        ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
        ResolveTask lastResolveTask = getLastResolveTask();
        lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
        lastResolveTask.ownerContext = this.contex;
        this.resolveStatus = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            JSONLexer jSONLexer = this.lexer;
            if (jSONLexer.token == 20) {
                jSONLexer.close();
                return;
            }
            throw new JSONException("not close json text, token : " + JSONToken.name(this.lexer.token));
        } catch (Throwable th) {
            this.lexer.close();
            throw th;
        }
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.locale);
            this.dateFormat = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.lexer.timeZone);
        }
        return this.dateFormat;
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ResolveTask getLastResolveTask() {
        return this.resolveTaskList.get(r0.size() - 1);
    }

    public void handleResovleTask(Object obj) {
        List<ResolveTask> list = this.resolveTaskList;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i2);
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                ParseContext parseContext = resolveTask.ownerContext;
                Object obj2 = null;
                Object obj3 = parseContext != null ? parseContext.object : null;
                String str = resolveTask.referenceValue;
                if (!str.startsWith("$")) {
                    obj2 = resolveTask.context.object;
                } else {
                    for (int i3 = 0; i3 < this.contextArrayIndex; i3++) {
                        if (str.equals(this.contextArray[i3].toString())) {
                            obj2 = this.contextArray[i3].object;
                        }
                    }
                }
                fieldDeserializer.setValue(obj3, obj2);
            }
        }
    }

    public Object parse() {
        return parse(null);
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public Object parseArrayWithType(Type type) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token == 8) {
            jSONLexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length == 1) {
            Type type2 = actualTypeArguments[0];
            if (type2 instanceof Class) {
                ArrayList arrayList = new ArrayList();
                parseArray((Class) type2, (Collection) arrayList);
                return arrayList;
            } else if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type2;
                Type type3 = wildcardType.getUpperBounds()[0];
                if (Object.class.equals(type3)) {
                    if (wildcardType.getLowerBounds().length == 0) {
                        return parse();
                    }
                    throw new JSONException("not support type : " + type);
                }
                ArrayList arrayList2 = new ArrayList();
                parseArray((Class) type3, (Collection) arrayList2);
                return arrayList2;
            } else {
                if (type2 instanceof TypeVariable) {
                    TypeVariable typeVariable = (TypeVariable) type2;
                    Type[] bounds = typeVariable.getBounds();
                    if (bounds.length == 1) {
                        Type type4 = bounds[0];
                        if (type4 instanceof Class) {
                            ArrayList arrayList3 = new ArrayList();
                            parseArray((Class) type4, (Collection) arrayList3);
                            return arrayList3;
                        }
                    } else {
                        throw new JSONException("not support : " + typeVariable);
                    }
                }
                if (type2 instanceof ParameterizedType) {
                    ArrayList arrayList4 = new ArrayList();
                    parseArray((ParameterizedType) type2, arrayList4);
                    return arrayList4;
                }
                throw new JSONException("TODO : " + type);
            }
        }
        throw new JSONException("not support type " + type);
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:0x022e, code lost:
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x0235, code lost:
        if (r3.token != 13) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0237, code lost:
        r3.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x023a, code lost:
        r2 = r19.config.getDeserializer(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x0242, code lost:
        if ((r2 instanceof com.alibaba.fastjson.parser.JavaBeanDeserializer) == false) goto L141;
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x0244, code lost:
        r2 = (com.alibaba.fastjson.parser.JavaBeanDeserializer) r2;
        r3 = r2.createInstance(r19, r7);
        r0 = r20.entrySet().iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0256, code lost:
        if (r0.hasNext() == false) goto L436;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0258, code lost:
        r4 = (java.util.Map.Entry) r0.next();
        r5 = r4.getKey();
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x0264, code lost:
        if ((r5 instanceof java.lang.String) == false) goto L439;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0266, code lost:
        r5 = r2.getFieldDeserializer((java.lang.String) r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x026c, code lost:
        if (r5 == null) goto L440;
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x026e, code lost:
        r5.setValue(r3, r4.getValue());
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x0276, code lost:
        r16 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0279, code lost:
        r16 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x027b, code lost:
        if (r16 != null) goto L150;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x027f, code lost:
        if (r7 != java.lang.Cloneable.class) goto L146;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0281, code lost:
        r16 = new java.util.HashMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x028d, code lost:
        if ("java.util.Collections$EmptyMap".equals(r6) == false) goto L149;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x028f, code lost:
        r16 = java.util.Collections.emptyMap();
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0294, code lost:
        r16 = r7.newInstance();
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0298, code lost:
        if (r13 != false) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x029a, code lost:
        r19.contex = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x029c, code lost:
        return r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x029d, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x02a5, code lost:
        throw new com.alibaba.fastjson.JSONException("create instance error", r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x02a6, code lost:
        r19.resolveStatus = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x02ab, code lost:
        if (r19.contex == null) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x02af, code lost:
        if ((r21 instanceof java.lang.Integer) != false) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x02b1, code lost:
        popContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x02b8, code lost:
        if (r20.size() <= 0) goto L167;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x02ba, code lost:
        r0 = com.alibaba.fastjson.util.TypeUtils.cast((java.lang.Object) r20, (java.lang.Class<java.lang.Object>) r7, r19.config);
        parseObject(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x02c3, code lost:
        if (r13 != false) goto L166;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x02c5, code lost:
        r19.contex = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x02c7, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x02c8, code lost:
        r0 = r19.config.getDeserializer(r7).deserialze(r19, r7, r21);
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x02d2, code lost:
        if (r13 != false) goto L170;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x02d4, code lost:
        r19.contex = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x02d6, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01fe A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x03b0 A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x04cc A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:301:0x04db A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x04e4 A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04e8 A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:306:0x04ed A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0504  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0568  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0575 A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:422:0x04f6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0590 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01c3 A[Catch: all -> 0x06ad, TryCatch #2 {all -> 0x06ad, blocks: (B:21:0x0064, B:24:0x006e, B:27:0x0077, B:31:0x008b, B:33:0x0095, B:36:0x009d, B:37:0x00bb, B:96:0x01c3, B:100:0x01d6, B:116:0x01f5, B:119:0x0202, B:122:0x0209, B:124:0x0211, B:126:0x0223, B:127:0x022e, B:129:0x0237, B:130:0x023a, B:132:0x0244, B:133:0x0252, B:135:0x0258, B:137:0x0266, B:139:0x026e, B:145:0x0281, B:146:0x0287, B:148:0x028f, B:149:0x0294, B:154:0x029e, B:155:0x02a5, B:156:0x02a6, B:158:0x02ad, B:160:0x02b1, B:161:0x02b4, B:163:0x02ba, B:167:0x02c8, B:174:0x02de, B:176:0x02e6, B:178:0x02ed, B:180:0x02fc, B:182:0x0304, B:185:0x0309, B:187:0x030d, B:206:0x0357, B:208:0x035b, B:212:0x0365, B:213:0x037d, B:188:0x0310, B:190:0x0318, B:193:0x031e, B:194:0x032a, B:197:0x0333, B:200:0x0339, B:203:0x033f, B:204:0x034b, B:214:0x037e, B:215:0x039a, B:218:0x039f, B:224:0x03b0, B:226:0x03b6, B:228:0x03c2, B:229:0x03c8, B:231:0x03cd, B:330:0x0562, B:334:0x056c, B:337:0x0575, B:341:0x0588, B:340:0x0582, B:345:0x0594, B:349:0x05a7, B:351:0x05b0, B:355:0x05c3, B:372:0x060b, B:354:0x05bd, B:358:0x05ce, B:362:0x05e1, B:361:0x05db, B:365:0x05ec, B:369:0x05ff, B:368:0x05f9, B:370:0x0606, B:348:0x05a1, B:376:0x0615, B:377:0x062d, B:232:0x03d1, B:239:0x03e1, B:243:0x03f0, B:247:0x0407, B:249:0x0410, B:254:0x041d, B:255:0x0420, B:257:0x042a, B:259:0x0431, B:261:0x0435, B:268:0x0447, B:269:0x045f, B:258:0x042e, B:246:0x0401, B:272:0x0464, B:276:0x0477, B:278:0x0488, B:282:0x049c, B:284:0x04a2, B:287:0x04a8, B:289:0x04b2, B:291:0x04ba, B:295:0x04cc, B:298:0x04d4, B:299:0x04d6, B:301:0x04db, B:303:0x04e4, B:306:0x04ed, B:307:0x04f0, B:309:0x04f6, B:311:0x04fd, B:318:0x050a, B:319:0x0522, B:304:0x04e8, B:279:0x0493, B:275:0x0471, B:322:0x0529, B:324:0x0536, B:327:0x0549, B:329:0x0555, B:378:0x062e, B:380:0x063f, B:381:0x0643, B:383:0x064c, B:390:0x0662, B:391:0x067a, B:99:0x01d0, B:118:0x01fe, B:41:0x00c3, B:45:0x00d4, B:44:0x00ce, B:51:0x00e7, B:53:0x00f1, B:54:0x00f4, B:57:0x00f9, B:58:0x010f, B:68:0x0122, B:69:0x0128, B:71:0x012d, B:74:0x013a, B:75:0x013e, B:78:0x0144, B:79:0x015e, B:72:0x0132, B:80:0x015f, B:81:0x0179, B:87:0x0183, B:90:0x0192, B:91:0x0198, B:92:0x01b6, B:93:0x01b7, B:392:0x067b, B:393:0x0693, B:394:0x0694, B:395:0x06ac), top: B:405:0x0064, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object parseObject(java.util.Map r20, java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 1717
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseObject(java.util.Map, java.lang.Object):java.lang.Object");
    }

    public String parseString() {
        JSONLexer jSONLexer = this.lexer;
        int i2 = jSONLexer.token;
        if (i2 != 4) {
            if (i2 == 2) {
                String numberString = jSONLexer.numberString();
                this.lexer.nextToken(16);
                return numberString;
            }
            Object parse = parse();
            if (parse == null) {
                return null;
            }
            return parse.toString();
        }
        String stringVal = jSONLexer.stringVal();
        JSONLexer jSONLexer2 = this.lexer;
        char c2 = jSONLexer2.ch;
        char c3 = JSONLexer.EOI;
        if (c2 == ',') {
            int i3 = jSONLexer2.bp + 1;
            jSONLexer2.bp = i3;
            if (i3 < jSONLexer2.len) {
                c3 = jSONLexer2.text.charAt(i3);
            }
            jSONLexer2.ch = c3;
            this.lexer.token = 16;
        } else if (c2 == ']') {
            int i4 = jSONLexer2.bp + 1;
            jSONLexer2.bp = i4;
            if (i4 < jSONLexer2.len) {
                c3 = jSONLexer2.text.charAt(i4);
            }
            jSONLexer2.ch = c3;
            this.lexer.token = 15;
        } else if (c2 == '}') {
            int i5 = jSONLexer2.bp + 1;
            jSONLexer2.bp = i5;
            if (i5 < jSONLexer2.len) {
                c3 = jSONLexer2.text.charAt(i5);
            }
            jSONLexer2.ch = c3;
            this.lexer.token = 13;
        } else {
            jSONLexer2.nextToken();
        }
        return stringVal;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void popContext() {
        this.contex = this.contex.parent;
        ParseContext[] parseContextArr = this.contextArray;
        int i2 = this.contextArrayIndex;
        parseContextArr[i2 - 1] = null;
        this.contextArrayIndex = i2 - 1;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.disableCircularReferenceDetect) {
            return;
        }
        this.contex = parseContext;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(new JSONLexer(str, JDJSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i2 = jSONLexer.token;
        if (i2 == 2) {
            Number integerValue = jSONLexer.integerValue();
            this.lexer.nextToken();
            return integerValue;
        }
        if (i2 == 3) {
            Number decimalValue = jSONLexer.decimalValue((jSONLexer.features & Feature.UseBigDecimal.mask) != 0);
            this.lexer.nextToken();
            return decimalValue;
        } else if (i2 == 4) {
            String stringVal = jSONLexer.stringVal();
            this.lexer.nextToken(16);
            if ((this.lexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                JSONLexer jSONLexer2 = new JSONLexer(stringVal);
                try {
                    if (jSONLexer2.scanISO8601DateIfMatch(true)) {
                        return jSONLexer2.calendar.getTime();
                    }
                } finally {
                    jSONLexer2.close();
                }
            }
            return stringVal;
        } else if (i2 == 12) {
            return parseObject((jSONLexer.features & Feature.OrderedField.mask) != 0 ? new JDJSONObject(new LinkedHashMap()) : new JDJSONObject(), obj);
        } else if (i2 != 14) {
            switch (i2) {
                case 6:
                    jSONLexer.nextToken(16);
                    return Boolean.TRUE;
                case 7:
                    jSONLexer.nextToken(16);
                    return Boolean.FALSE;
                case 8:
                    break;
                case 9:
                    jSONLexer.nextToken(18);
                    JSONLexer jSONLexer3 = this.lexer;
                    if (jSONLexer3.token == 18) {
                        jSONLexer3.nextToken(10);
                        accept(10);
                        long longValue = this.lexer.integerValue().longValue();
                        accept(2);
                        accept(11);
                        return new Date(longValue);
                    }
                    throw new JSONException("syntax error, " + this.lexer.info());
                default:
                    switch (i2) {
                        case 20:
                            if (jSONLexer.isBlankInput()) {
                                return null;
                            }
                            throw new JSONException("syntax error, " + this.lexer.info());
                        case 21:
                            jSONLexer.nextToken();
                            HashSet hashSet = new HashSet();
                            parseArray(hashSet, obj);
                            return hashSet;
                        case 22:
                            jSONLexer.nextToken();
                            TreeSet treeSet = new TreeSet();
                            parseArray(treeSet, obj);
                            return treeSet;
                        case 23:
                            break;
                        default:
                            throw new JSONException("syntax error, " + this.lexer.info());
                    }
            }
            jSONLexer.nextToken();
            return null;
        } else {
            JDJSONArray jDJSONArray = new JDJSONArray();
            parseArray(jDJSONArray, obj);
            return jDJSONArray;
        }
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i2) {
        this(new JSONLexer(str, i2), parserConfig);
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.disableCircularReferenceDetect) {
            return null;
        }
        this.contex = new ParseContext(parseContext, obj, obj2);
        int i2 = this.contextArrayIndex;
        this.contextArrayIndex = i2 + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (parseContextArr == null) {
            this.contextArray = new ParseContext[8];
        } else if (i2 >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        ParseContext[] parseContextArr3 = this.contextArray;
        ParseContext parseContext2 = this.contex;
        parseContextArr3[i2] = parseContext2;
        return parseContext2;
    }

    public DefaultJSONParser(char[] cArr, int i2, ParserConfig parserConfig, int i3) {
        this(new JSONLexer(cArr, i2, i3), parserConfig);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.global);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        String str;
        JSONLexer jSONLexer = this.lexer;
        int i2 = jSONLexer.token;
        if (i2 == 21 || i2 == 22) {
            jSONLexer.nextToken();
        }
        JSONLexer jSONLexer2 = this.lexer;
        if (jSONLexer2.token == 14) {
            if (Integer.TYPE != type) {
                if (String.class == type) {
                    deserializer = StringCodec.instance;
                    jSONLexer2.nextToken(4);
                } else {
                    deserializer = this.config.getDeserializer(type);
                    this.lexer.nextToken(12);
                }
            } else {
                deserializer = IntegerCodec.instance;
                jSONLexer2.nextToken(2);
            }
            ParseContext parseContext = this.contex;
            if (!this.lexer.disableCircularReferenceDetect) {
                setContext(parseContext, collection, obj);
            }
            int i3 = 0;
            while (true) {
                try {
                    JSONLexer jSONLexer3 = this.lexer;
                    int i4 = jSONLexer3.token;
                    if (i4 == 16) {
                        jSONLexer3.nextToken();
                    } else if (i4 == 15) {
                        this.contex = parseContext;
                        jSONLexer3.nextToken(16);
                        return;
                    } else {
                        Object obj2 = null;
                        String obj3 = null;
                        if (Integer.TYPE != type) {
                            if (String.class == type) {
                                if (i4 == 4) {
                                    str = jSONLexer3.stringVal();
                                    this.lexer.nextToken(16);
                                } else {
                                    Object parse = parse();
                                    if (parse != null) {
                                        obj3 = parse.toString();
                                    }
                                    str = obj3;
                                }
                                collection.add(str);
                            } else {
                                if (i4 == 8) {
                                    jSONLexer3.nextToken();
                                } else {
                                    obj2 = deserializer.deserialze(this, type, Integer.valueOf(i3));
                                }
                                collection.add(obj2);
                                if (this.resolveStatus == 1) {
                                    checkListResolve(collection);
                                }
                            }
                        } else {
                            collection.add(IntegerCodec.instance.deserialze(this, null, null));
                        }
                        JSONLexer jSONLexer4 = this.lexer;
                        if (jSONLexer4.token == 16) {
                            jSONLexer4.nextToken();
                        }
                        i3++;
                    }
                } catch (Throwable th) {
                    this.contex = parseContext;
                    throw th;
                }
            }
        } else {
            throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token) + ", " + this.lexer.info());
        }
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JDJSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.lexer = jSONLexer;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char c2 = jSONLexer.ch;
        char c3 = JSONLexer.EOI;
        if (c2 == '{') {
            int i2 = jSONLexer.bp + 1;
            jSONLexer.bp = i2;
            jSONLexer.ch = i2 < jSONLexer.len ? jSONLexer.text.charAt(i2) : c3;
            jSONLexer.token = 12;
        } else if (c2 == '[') {
            int i3 = jSONLexer.bp + 1;
            jSONLexer.bp = i3;
            jSONLexer.ch = i3 < jSONLexer.len ? jSONLexer.text.charAt(i3) : c3;
            jSONLexer.token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object cast;
        Class<?> cls;
        boolean z;
        int i2;
        JSONLexer jSONLexer = this.lexer;
        int i3 = jSONLexer.token;
        int i4 = 8;
        if (i3 == 8) {
            jSONLexer.nextToken(16);
            return null;
        } else if (i3 == 14) {
            Object[] objArr = new Object[typeArr.length];
            if (typeArr.length == 0) {
                jSONLexer.nextToken(15);
                JSONLexer jSONLexer2 = this.lexer;
                if (jSONLexer2.token == 15) {
                    jSONLexer2.nextToken(16);
                    return new Object[0];
                }
                throw new JSONException("syntax error, " + this.lexer.info());
            }
            jSONLexer.nextToken(2);
            int i5 = 0;
            while (i5 < typeArr.length) {
                JSONLexer jSONLexer3 = this.lexer;
                int i6 = jSONLexer3.token;
                if (i6 == i4) {
                    jSONLexer3.nextToken(16);
                    cast = null;
                } else {
                    Type type = typeArr[i5];
                    if (type == Integer.TYPE || type == Integer.class) {
                        if (i6 == 2) {
                            cast = Integer.valueOf(jSONLexer3.intValue());
                            this.lexer.nextToken(16);
                        } else {
                            cast = TypeUtils.cast(parse(), type, this.config);
                        }
                    } else if (type != String.class) {
                        if (i5 == typeArr.length - 1 && (type instanceof Class)) {
                            Class cls2 = (Class) type;
                            z = cls2.isArray();
                            cls = cls2.getComponentType();
                        } else {
                            cls = null;
                            z = false;
                        }
                        if (z && this.lexer.token != 14) {
                            ArrayList arrayList = new ArrayList();
                            ObjectDeserializer deserializer = this.config.getDeserializer(cls);
                            if (this.lexer.token != 15) {
                                while (true) {
                                    arrayList.add(deserializer.deserialze(this, type, null));
                                    JSONLexer jSONLexer4 = this.lexer;
                                    i2 = jSONLexer4.token;
                                    if (i2 != 16) {
                                        break;
                                    }
                                    jSONLexer4.nextToken(12);
                                }
                                if (i2 != 15) {
                                    throw new JSONException("syntax error, " + this.lexer.info());
                                }
                            }
                            cast = TypeUtils.cast(arrayList, type, this.config);
                        } else {
                            cast = this.config.getDeserializer(type).deserialze(this, type, null);
                        }
                    } else if (i6 == 4) {
                        cast = jSONLexer3.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        cast = TypeUtils.cast(parse(), type, this.config);
                    }
                }
                objArr[i5] = cast;
                JSONLexer jSONLexer5 = this.lexer;
                int i7 = jSONLexer5.token;
                if (i7 == 15) {
                    break;
                } else if (i7 == 16) {
                    if (i5 == typeArr.length - 1) {
                        jSONLexer5.nextToken(15);
                    } else {
                        jSONLexer5.nextToken(2);
                    }
                    i5++;
                    i4 = 8;
                } else {
                    throw new JSONException("syntax error, " + this.lexer.info());
                }
            }
            JSONLexer jSONLexer6 = this.lexer;
            if (jSONLexer6.token == 15) {
                jSONLexer6.nextToken(16);
                return objArr;
            }
            throw new JSONException("syntax error, " + this.lexer.info());
        } else {
            throw new JSONException("syntax error, " + this.lexer.info());
        }
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01d7 A[Catch: all -> 0x0237, TryCatch #0 {all -> 0x0237, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e5, B:115:0x01ec, B:116:0x01ef, B:118:0x01f5, B:120:0x01f9, B:125:0x0209, B:128:0x0215, B:132:0x0229, B:131:0x0223, B:133:0x022c, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0148, B:93:0x0149, B:95:0x0156, B:97:0x0166, B:96:0x0161, B:98:0x016f, B:99:0x0177, B:100:0x0181, B:101:0x018b, B:103:0x01a3, B:105:0x01ae, B:106:0x01b4, B:107:0x01b9, B:109:0x01c6, B:111:0x01d1, B:110:0x01cc, B:112:0x01d7, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:142:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01ec A[Catch: all -> 0x0237, TryCatch #0 {all -> 0x0237, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e5, B:115:0x01ec, B:116:0x01ef, B:118:0x01f5, B:120:0x01f9, B:125:0x0209, B:128:0x0215, B:132:0x0229, B:131:0x0223, B:133:0x022c, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0148, B:93:0x0149, B:95:0x0156, B:97:0x0166, B:96:0x0161, B:98:0x016f, B:99:0x0177, B:100:0x0181, B:101:0x018b, B:103:0x01a3, B:105:0x01ae, B:106:0x01b4, B:107:0x01b9, B:109:0x01c6, B:111:0x01d1, B:110:0x01cc, B:112:0x01d7, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:142:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01f5 A[Catch: all -> 0x0237, TryCatch #0 {all -> 0x0237, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e5, B:115:0x01ec, B:116:0x01ef, B:118:0x01f5, B:120:0x01f9, B:125:0x0209, B:128:0x0215, B:132:0x0229, B:131:0x0223, B:133:0x022c, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0148, B:93:0x0149, B:95:0x0156, B:97:0x0166, B:96:0x0161, B:98:0x016f, B:99:0x0177, B:100:0x0181, B:101:0x018b, B:103:0x01a3, B:105:0x01ae, B:106:0x01b4, B:107:0x01b9, B:109:0x01c6, B:111:0x01d1, B:110:0x01cc, B:112:0x01d7, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:142:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x022f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x007b A[Catch: all -> 0x0237, TryCatch #0 {all -> 0x0237, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e5, B:115:0x01ec, B:116:0x01ef, B:118:0x01f5, B:120:0x01f9, B:125:0x0209, B:128:0x0215, B:132:0x0229, B:131:0x0223, B:133:0x022c, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0148, B:93:0x0149, B:95:0x0156, B:97:0x0166, B:96:0x0161, B:98:0x016f, B:99:0x0177, B:100:0x0181, B:101:0x018b, B:103:0x01a3, B:105:0x01ae, B:106:0x01b4, B:107:0x01b9, B:109:0x01c6, B:111:0x01d1, B:110:0x01cc, B:112:0x01d7, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:142:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ea A[Catch: all -> 0x0237, LOOP:1: B:61:0x00e8->B:62:0x00ea, LOOP_END, TryCatch #0 {all -> 0x0237, blocks: (B:12:0x0028, B:15:0x003c, B:20:0x004b, B:24:0x005d, B:33:0x007b, B:35:0x0081, B:37:0x008d, B:41:0x009f, B:43:0x00a8, B:46:0x00b0, B:40:0x0099, B:48:0x00b9, B:52:0x00cb, B:54:0x00d4, B:55:0x00d7, B:51:0x00c5, B:59:0x00e1, B:60:0x00e4, B:62:0x00ea, B:83:0x0119, B:113:0x01e5, B:115:0x01ec, B:116:0x01ef, B:118:0x01f5, B:120:0x01f9, B:125:0x0209, B:128:0x0215, B:132:0x0229, B:131:0x0223, B:133:0x022c, B:85:0x0121, B:89:0x012b, B:90:0x0138, B:91:0x0140, B:92:0x0148, B:93:0x0149, B:95:0x0156, B:97:0x0166, B:96:0x0161, B:98:0x016f, B:99:0x0177, B:100:0x0181, B:101:0x018b, B:103:0x01a3, B:105:0x01ae, B:106:0x01b4, B:107:0x01b9, B:109:0x01c6, B:111:0x01d1, B:110:0x01cc, B:112:0x01d7, B:23:0x0057, B:25:0x0064, B:27:0x0069, B:30:0x0074), top: B:142:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void parseArray(java.util.Collection r17, java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 615
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.DefaultJSONParser.parseArray(java.util.Collection, java.lang.Object):void");
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    public <T> T parseObject(Type type, Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i2 = jSONLexer.token;
        if (i2 == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (i2 == 4) {
            if (type == byte[].class) {
                T t = (T) jSONLexer.bytesValue();
                this.lexer.nextToken();
                return t;
            } else if (type == char[].class) {
                String stringVal = jSONLexer.stringVal();
                this.lexer.nextToken();
                return (T) stringVal.toCharArray();
            }
        }
        try {
            return (T) this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new JSONException(e3.getMessage(), e3);
        }
    }

    public void parseObject(Object obj) {
        Object deserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        int i2 = this.lexer.token;
        if (i2 != 12 && i2 != 16) {
            throw new JSONException("syntax error, expect {, actual " + JSONToken.name(i2));
        }
        while (true) {
            String scanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (scanSymbol == null) {
                JSONLexer jSONLexer = this.lexer;
                int i3 = jSONLexer.token;
                if (i3 == 13) {
                    jSONLexer.nextToken(16);
                    return;
                } else if (i3 == 16) {
                    continue;
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(scanSymbol) : null;
            if (fieldDeserializer == null) {
                JSONLexer jSONLexer2 = this.lexer;
                if ((jSONLexer2.features & Feature.IgnoreNotMatch.mask) != 0) {
                    jSONLexer2.nextTokenWithChar(':');
                    parse();
                    JSONLexer jSONLexer3 = this.lexer;
                    if (jSONLexer3.token == 13) {
                        jSONLexer3.nextToken();
                        return;
                    }
                } else {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + scanSymbol);
                }
            } else {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                Class<?> cls2 = fieldInfo.fieldClass;
                Type type = fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = parseString();
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithChar(':');
                    deserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithChar(':');
                    deserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, deserialze);
                JSONLexer jSONLexer4 = this.lexer;
                int i4 = jSONLexer4.token;
                if (i4 != 16 && i4 == 13) {
                    jSONLexer4.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JDJSONObject parseObject() {
        return (JDJSONObject) parseObject((this.lexer.features & Feature.OrderedField.mask) != 0 ? new JDJSONObject(new LinkedHashMap()) : new JDJSONObject(), (Object) null);
    }
}
