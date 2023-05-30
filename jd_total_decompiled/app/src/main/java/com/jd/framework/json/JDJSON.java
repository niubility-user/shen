package com.jd.framework.json;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/* loaded from: classes.dex */
public abstract class JDJSON implements JSONStreamAware, JSONAware {
    public static final String DEFAULT_TYPE_KEY = "@type";
    public static final String VERSION = "1.1.65";
    public static TimeZone defaultTimeZone = TimeZone.getDefault();
    public static Locale defaultLocale = Locale.getDefault();
    public static int DEFAULT_PARSER_FEATURE = ((Feature.UseBigDecimal.mask | 0) | Feature.SortFeidFastMatch.mask) | Feature.IgnoreNotMatch.mask;
    public static String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static boolean debug = true;
    public static int DEFAULT_GENERATE_FEATURE = (((SerializerFeature.QuoteFieldNames.mask | 0) | SerializerFeature.SkipTransientField.mask) | SerializerFeature.WriteEnumUsingToString.mask) | SerializerFeature.SortField.mask;

    public static void init(boolean z) {
        debug = z;
    }

    public static final JDJSONArray optParseArray(String str) {
        try {
            return parseArray(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final JDJSONObject optParseObject(String str, Feature... featureArr) {
        try {
            return parseObject(str, featureArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final Object parse(String str) {
        return parse(str, DEFAULT_PARSER_FEATURE);
    }

    public static final JDJSONArray parseArray(String str) {
        JDJSONArray jDJSONArray = null;
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.global);
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = jSONLexer.token();
        if (i2 == 8) {
            jSONLexer.nextToken();
        } else if (i2 != 20) {
            jDJSONArray = new JDJSONArray();
            defaultJSONParser.parseArray(jDJSONArray);
            defaultJSONParser.handleResovleTask(jDJSONArray);
        }
        defaultJSONParser.close();
        return jDJSONArray;
    }

    public static final JDJSONObject parseObject(String str, Feature... featureArr) {
        return (JDJSONObject) parse(str, featureArr);
    }

    public static final <T> List<T> parseTemplateArray(String str, Type[] typeArr) {
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.global);
        Object[] parseArray = defaultJSONParser.parseArray(typeArr);
        List<T> asList = parseArray != null ? Arrays.asList(parseArray) : null;
        defaultJSONParser.handleResovleTask(asList);
        defaultJSONParser.close();
        return asList;
    }

    public static final Object toJSON(Object obj) {
        return toJSON(obj, SerializeConfig.globalInstance);
    }

    public static final byte[] toJSONBytes(Object obj, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            new JSONSerializer(serializeWriter, SerializeConfig.globalInstance).write(obj);
            return serializeWriter.toBytes("UTF-8");
        } finally {
            serializeWriter.close();
        }
    }

    public static final String toJSONString(Object obj) {
        return toJSONString(obj, SerializeConfig.globalInstance, null, null, DEFAULT_GENERATE_FEATURE, new SerializerFeature[0]);
    }

    public static final String toJSONStringWithDateFormat(Object obj, String str, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, null, str, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final String toJSONStringZ(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, null, null, 0, serializerFeatureArr);
    }

    public static final <T> T toJavaObject(JDJSON jdjson, Class<T> cls) {
        return (T) TypeUtils.cast((Object) jdjson, (Class) cls, ParserConfig.global);
    }

    public static final void writeJSONStringTo(Object obj, Writer writer, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(writer, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            new JSONSerializer(serializeWriter, SerializeConfig.globalInstance).write(obj);
        } finally {
            serializeWriter.close();
        }
    }

    public String toString() {
        return toJSONString();
    }

    @Override // com.alibaba.fastjson.JSONStreamAware
    public void writeJSONString(Appendable appendable) {
        SerializeWriter serializeWriter = new SerializeWriter(null, DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
        try {
            try {
                new JSONSerializer(serializeWriter, SerializeConfig.globalInstance).write(this);
                appendable.append(serializeWriter.toString());
            } catch (IOException e2) {
                throw new JSONException(e2.getMessage(), e2);
            }
        } finally {
            serializeWriter.close();
        }
    }

    public static final <T> List<T> optParseArray(String str, Class<T> cls) {
        try {
            return parseArray(str, cls);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final JDJSONObject optParseObject(String str) {
        try {
            return parseObject(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final Object parse(String str, int i2) {
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.global, i2);
        Object parse = defaultJSONParser.parse(null);
        defaultJSONParser.handleResovleTask(parse);
        defaultJSONParser.close();
        return parse;
    }

    public static final JDJSONObject parseObject(String str) {
        Object parse = parse(str);
        if (parse instanceof JDJSONObject) {
            return (JDJSONObject) parse;
        }
        return (JDJSONObject) toJSON(parse);
    }

    @Deprecated
    public static final Object toJSON(Object obj, ParserConfig parserConfig) {
        return toJSON(obj, SerializeConfig.globalInstance);
    }

    public static final String toJSONString(Object obj, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public <T> T toJavaObject(Class<T> cls) {
        return (T) TypeUtils.cast((Object) this, (Class) cls, ParserConfig.getGlobalInstance());
    }

    public static final List<Object> optParseArray(String str, Type[] typeArr) {
        try {
            return parseArray(str, typeArr);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static final <T> T optParseObject(String str, Class<T> cls) {
        try {
            return (T) parseObject(str, cls);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Object toJSON(Object obj, SerializeConfig serializeConfig) {
        Map hashMap;
        if (obj == null) {
            return null;
        }
        if (obj instanceof JDJSON) {
            return (JDJSON) obj;
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            int size = map.size();
            if (map instanceof LinkedHashMap) {
                hashMap = new LinkedHashMap(size);
            } else if (map instanceof TreeMap) {
                hashMap = new TreeMap();
            } else {
                hashMap = new HashMap(size);
            }
            JDJSONObject jDJSONObject = new JDJSONObject(hashMap);
            for (Map.Entry entry : map.entrySet()) {
                jDJSONObject.put(TypeUtils.castToString(entry.getKey()), toJSON(entry.getValue()));
            }
            return jDJSONObject;
        } else if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            JDJSONArray jDJSONArray = new JDJSONArray(collection.size());
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                jDJSONArray.add(toJSON(it.next()));
            }
            return jDJSONArray;
        } else {
            Class<?> cls = obj.getClass();
            if (cls.isEnum()) {
                return ((Enum) obj).name();
            }
            if (cls.isArray()) {
                int length = Array.getLength(obj);
                JDJSONArray jDJSONArray2 = new JDJSONArray(length);
                for (int i2 = 0; i2 < length; i2++) {
                    jDJSONArray2.add(toJSON(Array.get(obj, i2)));
                }
                return jDJSONArray2;
            } else if (ParserConfig.isPrimitive(cls)) {
                return obj;
            } else {
                ObjectSerializer objectSerializer = serializeConfig.get(cls);
                if (objectSerializer instanceof JavaBeanSerializer) {
                    JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer) objectSerializer;
                    JDJSONObject jDJSONObject2 = new JDJSONObject();
                    try {
                        for (Map.Entry<String, Object> entry2 : javaBeanSerializer.getFieldValuesMap(obj).entrySet()) {
                            jDJSONObject2.put(entry2.getKey(), toJSON(entry2.getValue()));
                        }
                        return jDJSONObject2;
                    } catch (Exception e2) {
                        throw new JSONException("toJSON error", e2);
                    }
                }
                return null;
            }
        }
    }

    public static final String toJSONString(Object obj, int i2, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, null, null, i2, serializerFeatureArr);
    }

    public static final String toJSONString(Object obj, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, new SerializeFilter[]{serializeFilter}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final String toJSONString(Object obj, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, SerializeConfig.globalInstance, serializeFilterArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final Object parse(byte[] bArr, Feature... featureArr) {
        try {
            return parseObject(new String(bArr, "UTF-8"), featureArr);
        } catch (UnsupportedEncodingException e2) {
            throw new JSONException("UTF-8 not support", e2);
        }
    }

    public static final <T> T parseObject(String str, TypeReference<T> typeReference, Feature... featureArr) {
        return (T) parseObject(str, typeReference.type, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final String toJSONString(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, null, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final <T> T parseObject(String str, Class<T> cls, Feature... featureArr) {
        return (T) parseObject(str, cls, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final byte[] toJSONBytes(Object obj, SerializeConfig serializeConfig, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            new JSONSerializer(serializeWriter, serializeConfig).write(obj);
            return serializeWriter.toBytes("UTF-8");
        } finally {
            serializeWriter.close();
        }
    }

    public static final String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter serializeFilter, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, new SerializeFilter[]{serializeFilter}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final Object parse(String str, Feature... featureArr) {
        int i2 = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            i2 |= feature.mask;
        }
        return parse(str, i2);
    }

    public static final <T> T parseObject(String str, Class<T> cls, ParseProcess parseProcess, Feature... featureArr) {
        return (T) parseObject(str, cls, ParserConfig.global, parseProcess, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, serializeConfig, serializeFilterArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final <T> List<T> parseArray(String str, Class<T> cls) {
        ArrayList arrayList = null;
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.global);
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = jSONLexer.token();
        if (i2 == 8) {
            jSONLexer.nextToken();
        } else if (i2 != 20 || !jSONLexer.isBlankInput()) {
            arrayList = new ArrayList();
            defaultJSONParser.parseArray((Class<?>) cls, (Collection) arrayList);
            defaultJSONParser.handleResovleTask(arrayList);
        }
        defaultJSONParser.close();
        return arrayList;
    }

    public static final <T> T parseObject(String str, Type type, Feature... featureArr) {
        return (T) parseObject(str, type, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final String toJSONString(Object obj, boolean z) {
        return !z ? toJSONString(obj) : toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    public static final <T> T parseObject(String str, Type type, ParseProcess parseProcess, Feature... featureArr) {
        return (T) parseObject(str, type, ParserConfig.global, parseProcess, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Type type, int i2, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature feature : featureArr) {
            i2 |= feature.mask;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.global, i2);
        T t = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t);
        defaultJSONParser.close();
        return t;
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        SerializeWriter serializeWriter = new SerializeWriter(null, DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
        try {
            new JSONSerializer(serializeWriter, SerializeConfig.globalInstance).write(this);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static Object parse(String str, ParserConfig parserConfig) {
        return parse(str, parserConfig, DEFAULT_PARSER_FEATURE);
    }

    public static Object parse(String str, ParserConfig parserConfig, int i2) {
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i2);
        Object parse = defaultJSONParser.parse();
        defaultJSONParser.handleResovleTask(parse);
        defaultJSONParser.close();
        return parse;
    }

    public static final <T> T parseObject(String str, Type type, ParserConfig parserConfig, int i2, Feature... featureArr) {
        return (T) parseObject(str, type, parserConfig, null, i2, featureArr);
    }

    public static String toJSONString(Object obj, SerializeConfig serializeConfig, SerializeFilter[] serializeFilterArr, String str, int i2, SerializerFeature... serializerFeatureArr) {
        SerializeWriter serializeWriter = new SerializeWriter(null, i2, serializerFeatureArr);
        try {
            JSONSerializer jSONSerializer = new JSONSerializer(serializeWriter, serializeConfig);
            for (SerializerFeature serializerFeature : serializerFeatureArr) {
                jSONSerializer.config(serializerFeature, true);
            }
            if (str != null && str.length() != 0) {
                jSONSerializer.setDateFormat(str);
                jSONSerializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (serializeFilterArr != null) {
                for (SerializeFilter serializeFilter : serializeFilterArr) {
                    if (serializeFilter != null) {
                        if (serializeFilter instanceof PropertyPreFilter) {
                            jSONSerializer.getPropertyPreFilters().add((PropertyPreFilter) serializeFilter);
                        }
                        if (serializeFilter instanceof NameFilter) {
                            jSONSerializer.getNameFilters().add((NameFilter) serializeFilter);
                        }
                        if (serializeFilter instanceof ValueFilter) {
                            jSONSerializer.getValueFilters().add((ValueFilter) serializeFilter);
                        }
                        if (serializeFilter instanceof PropertyFilter) {
                            jSONSerializer.getPropertyFilters().add((PropertyFilter) serializeFilter);
                        }
                        if (serializeFilter instanceof BeforeFilter) {
                            jSONSerializer.getBeforeFilters().add((BeforeFilter) serializeFilter);
                        }
                        if (serializeFilter instanceof AfterFilter) {
                            jSONSerializer.getAfterFilters().add((AfterFilter) serializeFilter);
                        }
                    }
                }
            }
            jSONSerializer.write(obj);
            return serializeWriter.toString();
        } finally {
            serializeWriter.close();
        }
    }

    public static final List<Object> parseArray(String str, Type[] typeArr) {
        if (str == null) {
            return null;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.global);
        Object[] parseArray = defaultJSONParser.parseArray(typeArr);
        List<Object> asList = parseArray != null ? Arrays.asList(parseArray) : null;
        defaultJSONParser.handleResovleTask(asList);
        defaultJSONParser.close();
        return asList;
    }

    public static final <T> T parseObject(String str, Type type, ParserConfig parserConfig, ParseProcess parseProcess, int i2, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature feature : featureArr) {
            i2 |= feature.mask;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i2);
        if (parseProcess instanceof ExtraTypeProvider) {
            defaultJSONParser.getExtraTypeProviders().add((ExtraTypeProvider) parseProcess);
        }
        if (parseProcess instanceof ExtraProcessor) {
            defaultJSONParser.getExtraProcessors().add((ExtraProcessor) parseProcess);
        }
        if (parseProcess instanceof FieldTypeResolver) {
            defaultJSONParser.fieldTypeResolver = (FieldTypeResolver) parseProcess;
        }
        T t = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t);
        defaultJSONParser.close();
        return t;
    }

    public static final <T> T parseObject(byte[] bArr, Type type, Feature... featureArr) {
        try {
            return (T) parseObject(new String(bArr, "UTF-8"), type, featureArr);
        } catch (UnsupportedEncodingException unused) {
            throw new JSONException("UTF-8 not support");
        }
    }

    public static final <T> T parseObject(char[] cArr, int i2, Type type, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        int i3 = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            i3 |= feature.mask;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(cArr, i2, ParserConfig.global, i3);
        T t = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t);
        defaultJSONParser.close();
        return t;
    }

    public static final <T> T parseObject(String str, Class<T> cls) {
        return (T) parseObject(str, (Class) cls, new Feature[0]);
    }
}
