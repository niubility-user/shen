package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.JDJSON;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public final class DateCodec implements ObjectSerializer, ObjectDeserializer {
    public static final DateCodec instance = new DateCodec();

    private DateCodec() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.util.Calendar, T] */
    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, String str) {
        DateFormat dateFormat;
        if (obj2 == 0) {
            return null;
        }
        if (obj2 instanceof Date) {
            return obj2;
        }
        if (obj2 instanceof Number) {
            return (T) new Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String str2 = (String) obj2;
            if (str2.length() == 0) {
                return null;
            }
            JSONLexer jSONLexer = new JSONLexer(str2);
            try {
                if (jSONLexer.scanISO8601DateIfMatch(false)) {
                    ?? r3 = (T) jSONLexer.calendar;
                    return type == Calendar.class ? r3 : (T) r3.getTime();
                }
                jSONLexer.close();
                if ("0000-00-00".equals(str2) || "0000-00-00T00:00:00".equalsIgnoreCase(str2) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(str2)) {
                    return null;
                }
                if (str != null) {
                    dateFormat = new SimpleDateFormat(str);
                } else {
                    dateFormat = defaultJSONParser.getDateFormat();
                }
                try {
                    return (T) dateFormat.parse(str2);
                } catch (ParseException unused) {
                    return (T) new Date(Long.parseLong(str2));
                }
            } finally {
                jSONLexer.close();
            }
        }
        throw new JSONException("parse error");
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        Date date;
        char[] charArray;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
        } else if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0 && obj.getClass() != type) {
            if (obj.getClass() == Date.class) {
                serializeWriter.write("new Date(");
                serializeWriter.writeLong(((Date) obj).getTime());
                serializeWriter.write(41);
                return;
            }
            serializeWriter.write(123);
            serializeWriter.writeFieldName(JDJSON.DEFAULT_TYPE_KEY, false);
            jSONSerializer.write(obj.getClass().getName());
            serializeWriter.write(44);
            serializeWriter.writeFieldName("val", false);
            serializeWriter.writeLong(((Date) obj).getTime());
            serializeWriter.write(125);
        } else {
            if (obj instanceof Calendar) {
                date = ((Calendar) obj).getTime();
            } else {
                date = (Date) obj;
            }
            if ((serializeWriter.features & SerializerFeature.WriteDateUseDateFormat.mask) != 0) {
                DateFormat dateFormat = jSONSerializer.getDateFormat();
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(JDJSON.DEFFAULT_DATE_FORMAT, jSONSerializer.locale);
                    dateFormat.setTimeZone(jSONSerializer.timeZone);
                }
                serializeWriter.writeString(dateFormat.format(date));
                return;
            }
            long time = date.getTime();
            int i2 = serializeWriter.features;
            if ((SerializerFeature.UseISO8601DateFormat.mask & i2) != 0) {
                SerializerFeature serializerFeature = SerializerFeature.UseSingleQuotes;
                if ((i2 & serializerFeature.mask) != 0) {
                    serializeWriter.write(39);
                } else {
                    serializeWriter.write(34);
                }
                Calendar calendar = Calendar.getInstance(jSONSerializer.timeZone, jSONSerializer.locale);
                calendar.setTimeInMillis(time);
                int i3 = calendar.get(1);
                int i4 = calendar.get(2) + 1;
                int i5 = calendar.get(5);
                int i6 = calendar.get(11);
                int i7 = calendar.get(12);
                int i8 = calendar.get(13);
                int i9 = calendar.get(14);
                if (i9 != 0) {
                    charArray = "0000-00-00T00:00:00.000".toCharArray();
                    SerializeWriter.getChars(i9, 23, charArray);
                    SerializeWriter.getChars(i8, 19, charArray);
                    SerializeWriter.getChars(i7, 16, charArray);
                    SerializeWriter.getChars(i6, 13, charArray);
                    SerializeWriter.getChars(i5, 10, charArray);
                    SerializeWriter.getChars(i4, 7, charArray);
                    SerializeWriter.getChars(i3, 4, charArray);
                } else if (i8 == 0 && i7 == 0 && i6 == 0) {
                    charArray = "0000-00-00".toCharArray();
                    SerializeWriter.getChars(i5, 10, charArray);
                    SerializeWriter.getChars(i4, 7, charArray);
                    SerializeWriter.getChars(i3, 4, charArray);
                } else {
                    charArray = "0000-00-00T00:00:00".toCharArray();
                    SerializeWriter.getChars(i8, 19, charArray);
                    SerializeWriter.getChars(i7, 16, charArray);
                    SerializeWriter.getChars(i6, 13, charArray);
                    SerializeWriter.getChars(i5, 10, charArray);
                    SerializeWriter.getChars(i4, 7, charArray);
                    SerializeWriter.getChars(i3, 4, charArray);
                }
                serializeWriter.write(charArray);
                if ((serializeWriter.features & serializerFeature.mask) != 0) {
                    serializeWriter.write(39);
                    return;
                } else {
                    serializeWriter.write(34);
                    return;
                }
            }
            serializeWriter.writeLong(time);
        }
    }

    /* JADX WARN: Type inference failed for: r12v9, types: [java.util.Calendar, T] */
    /* JADX WARN: Type inference failed for: r1v18, types: [java.util.Calendar, T] */
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str) {
        Object obj2;
        T t;
        Long l2;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = jSONLexer.token();
        if (i2 == 2) {
            Long valueOf = Long.valueOf(jSONLexer.longValue());
            jSONLexer.nextToken(16);
            l2 = valueOf;
        } else if (i2 == 4) {
            String stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            l2 = stringVal;
            if ((jSONLexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                JSONLexer jSONLexer2 = new JSONLexer(stringVal);
                Date date = stringVal;
                if (jSONLexer2.scanISO8601DateIfMatch(true)) {
                    ?? r1 = (T) jSONLexer2.calendar;
                    if (type == Calendar.class) {
                        jSONLexer2.close();
                        return r1;
                    }
                    date = r1.getTime();
                }
                jSONLexer2.close();
                l2 = date;
            }
        } else if (i2 == 8) {
            jSONLexer.nextToken();
            obj2 = null;
            t = (T) cast(defaultJSONParser, type, obj, obj2, str);
            if (type != Calendar.class && !(t instanceof Calendar)) {
                Date date2 = (Date) t;
                if (date2 == null) {
                    return null;
                }
                ?? r12 = (T) Calendar.getInstance(jSONLexer.timeZone, jSONLexer.locale);
                r12.setTime(date2);
                return r12;
            }
        } else if (i2 == 12) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 4) {
                if (JDJSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    Class<?> loadClass = TypeUtils.loadClass(jSONLexer.stringVal(), defaultJSONParser.config.defaultClassLoader);
                    if (loadClass != null) {
                        type = loadClass;
                    }
                    defaultJSONParser.accept(4);
                    defaultJSONParser.accept(16);
                }
                jSONLexer.nextTokenWithChar(':');
                int i3 = jSONLexer.token();
                if (i3 == 2) {
                    long longValue = jSONLexer.longValue();
                    jSONLexer.nextToken();
                    Long valueOf2 = Long.valueOf(longValue);
                    defaultJSONParser.accept(13);
                    l2 = valueOf2;
                } else {
                    throw new JSONException("syntax error : " + JSONToken.name(i3));
                }
            } else {
                throw new JSONException("syntax error");
            }
        } else if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
            defaultJSONParser.accept(16);
            if (jSONLexer.token() == 4) {
                if ("val".equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    Object parse = defaultJSONParser.parse();
                    defaultJSONParser.accept(13);
                    l2 = parse;
                } else {
                    throw new JSONException("syntax error");
                }
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            l2 = defaultJSONParser.parse();
        }
        obj2 = l2;
        t = (T) cast(defaultJSONParser, type, obj, obj2, str);
        return type != Calendar.class ? t : t;
    }
}
