package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class MiscCodec implements ObjectSerializer, ObjectDeserializer {
    public static final MiscCodec instance = new MiscCodec();

    private MiscCodec() {
    }

    /* JADX WARN: Type inference failed for: r8v7, types: [T, java.text.SimpleDateFormat] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object parse;
        if (type == StackTraceElement.class) {
            return (T) parseStackTraceElement(defaultJSONParser);
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
            defaultJSONParser.accept(16);
            if (jSONLexer.token() == 4) {
                if ("val".equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    parse = defaultJSONParser.parse();
                    defaultJSONParser.accept(13);
                } else {
                    throw new JSONException("syntax error");
                }
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            parse = defaultJSONParser.parse();
        }
        if (parse == null) {
            return null;
        }
        if (parse instanceof String) {
            String str = (String) parse;
            if (str.length() == 0) {
                return null;
            }
            if (type == UUID.class) {
                return (T) UUID.fromString(str);
            }
            if (type == Class.class) {
                return (T) TypeUtils.loadClass(str, defaultJSONParser.config.defaultClassLoader);
            }
            if (type == Locale.class) {
                String[] split = str.split(CartConstant.KEY_YB_INFO_LINK);
                if (split.length == 1) {
                    return (T) new Locale(split[0]);
                }
                if (split.length == 2) {
                    return (T) new Locale(split[0], split[1]);
                }
                return (T) new Locale(split[0], split[1], split[2]);
            } else if (type == URI.class) {
                return (T) URI.create(str);
            } else {
                if (type == URL.class) {
                    try {
                        return (T) new URL(str);
                    } catch (MalformedURLException e2) {
                        throw new JSONException("create url error", e2);
                    }
                } else if (type == Pattern.class) {
                    return (T) Pattern.compile(str);
                } else {
                    if (type == Charset.class) {
                        return (T) Charset.forName(str);
                    }
                    if (type == Currency.class) {
                        return (T) Currency.getInstance(str);
                    }
                    if (type == SimpleDateFormat.class) {
                        ?? r8 = (T) new SimpleDateFormat(str, defaultJSONParser.lexer.locale);
                        r8.setTimeZone(defaultJSONParser.lexer.timeZone);
                        return r8;
                    } else if (type != Character.TYPE && type != Character.class) {
                        if ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName())) {
                            try {
                                return (T) Class.forName("android.net.Uri").getMethod(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, String.class).invoke(null, str);
                            } catch (Exception e3) {
                                throw new JSONException("parse android.net.Uri error.", e3);
                            }
                        }
                        return (T) TimeZone.getTimeZone(str);
                    } else {
                        return (T) TypeUtils.castToChar(str);
                    }
                }
            }
        }
        if (parse instanceof JDJSONObject) {
            JDJSONObject jDJSONObject = (JDJSONObject) parse;
            if (type == Currency.class) {
                String string = jDJSONObject.getString("currency");
                if (string != null) {
                    return (T) Currency.getInstance(string);
                }
                String string2 = jDJSONObject.getString("currencyCode");
                if (string2 != null) {
                    return (T) Currency.getInstance(string2);
                }
            }
            if (type == Map.Entry.class) {
                return (T) jDJSONObject.entrySet().iterator().next();
            }
        }
        throw new JSONException("except string value");
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x0169, code lost:
        return (T) new java.lang.StackTraceElement(r5, r7, r8, r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected <T> T parseStackTraceElement(com.alibaba.fastjson.parser.DefaultJSONParser r17) {
        /*
            Method dump skipped, instructions count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.MiscCodec.parseStackTraceElement(com.alibaba.fastjson.parser.DefaultJSONParser):java.lang.Object");
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            if (type != Character.TYPE && type != Character.class) {
                if ((serializeWriter.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0 && Enumeration.class.isAssignableFrom(TypeUtils.getClass(type))) {
                    serializeWriter.write("[]");
                    return;
                } else {
                    serializeWriter.writeNull();
                    return;
                }
            }
            jSONSerializer.write("");
        } else if (obj instanceof Pattern) {
            jSONSerializer.write(((Pattern) obj).pattern());
        } else if (obj instanceof TimeZone) {
            jSONSerializer.write(((TimeZone) obj).getID());
        } else if (obj instanceof Currency) {
            jSONSerializer.write(((Currency) obj).getCurrencyCode());
        } else if (obj instanceof Class) {
            jSONSerializer.write(((Class) obj).getName());
        } else if (obj instanceof Character) {
            Character ch = (Character) obj;
            if (ch.charValue() == 0) {
                jSONSerializer.write("\u0000");
            } else {
                jSONSerializer.write(ch.toString());
            }
        } else {
            int i2 = 0;
            if (obj instanceof SimpleDateFormat) {
                String pattern = ((SimpleDateFormat) obj).toPattern();
                if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0 && obj.getClass() != type) {
                    serializeWriter.write(123);
                    serializeWriter.writeFieldName(JDJSON.DEFAULT_TYPE_KEY, false);
                    jSONSerializer.write(obj.getClass().getName());
                    serializeWriter.write(44);
                    serializeWriter.writeFieldName("val", false);
                    serializeWriter.writeString(pattern);
                    serializeWriter.write(125);
                    return;
                }
                serializeWriter.writeString(pattern);
            } else if (obj instanceof JSONStreamAware) {
                ((JSONStreamAware) obj).writeJSONString(serializeWriter);
            } else if (obj instanceof JSONAware) {
                serializeWriter.write(((JSONAware) obj).toJSONString());
            } else if (obj instanceof JSONSerializable) {
                ((JSONSerializable) obj).write(jSONSerializer, obj2, type);
            } else if (obj instanceof Enumeration) {
                Type type2 = null;
                if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) != 0 && (type instanceof ParameterizedType)) {
                    type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                }
                Enumeration enumeration = (Enumeration) obj;
                SerialContext serialContext = jSONSerializer.context;
                jSONSerializer.setContext(serialContext, obj, obj2, 0);
                try {
                    serializeWriter.write(91);
                    while (enumeration.hasMoreElements()) {
                        Object nextElement = enumeration.nextElement();
                        int i3 = i2 + 1;
                        if (i2 != 0) {
                            serializeWriter.write(44);
                        }
                        if (nextElement == null) {
                            serializeWriter.writeNull();
                        } else {
                            jSONSerializer.config.get(nextElement.getClass()).write(jSONSerializer, nextElement, Integer.valueOf(i3 - 1), type2);
                        }
                        i2 = i3;
                    }
                    serializeWriter.write(93);
                } finally {
                    jSONSerializer.context = serialContext;
                }
            } else {
                jSONSerializer.write(obj.toString());
            }
        }
    }
}
