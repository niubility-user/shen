package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class BigDecimalCodec implements ObjectSerializer, ObjectDeserializer {
    public static final BigDecimalCodec instance = new BigDecimalCodec();

    private BigDecimalCodec() {
    }

    /* JADX WARN: Type inference failed for: r4v5, types: [java.math.BigDecimal, T] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = jSONLexer.token();
        if (i2 == 2) {
            if (type == BigInteger.class) {
                String numberString = jSONLexer.numberString();
                jSONLexer.nextToken(16);
                return (T) new BigInteger(numberString, 10);
            }
            T t = (T) jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            return t;
        } else if (i2 == 3) {
            ?? r4 = (T) jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            return type == BigInteger.class ? (T) r4.toBigInteger() : r4;
        } else {
            Object parse = defaultJSONParser.parse();
            if (parse == null) {
                return null;
            }
            if (type == BigInteger.class) {
                return (T) TypeUtils.castToBigInteger(parse);
            }
            return (T) TypeUtils.castToBigDecimal(parse);
        }
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            if ((serializeWriter.features & SerializerFeature.WriteNullNumberAsZero.mask) != 0) {
                serializeWriter.write(48);
            } else {
                serializeWriter.writeNull();
            }
        } else if (obj instanceof BigInteger) {
            serializeWriter.write(((BigInteger) obj).toString());
        } else {
            BigDecimal bigDecimal = (BigDecimal) obj;
            serializeWriter.write(bigDecimal.toString());
            if ((serializeWriter.features & SerializerFeature.WriteClassName.mask) == 0 || type == BigDecimal.class || bigDecimal.scale() != 0) {
                return;
            }
            serializeWriter.write(46);
        }
    }
}
