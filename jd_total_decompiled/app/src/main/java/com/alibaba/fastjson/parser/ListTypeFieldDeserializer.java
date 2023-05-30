package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import com.jd.framework.json.JDJSONArray;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ListTypeFieldDeserializer extends FieldDeserializer {
    private final boolean array;
    private ObjectDeserializer deserializer;
    private final Type itemType;

    public ListTypeFieldDeserializer(ParserConfig parserConfig, Class<?> cls, FieldInfo fieldInfo) {
        super(cls, fieldInfo, 14);
        Type type = fieldInfo.fieldType;
        Class<?> cls2 = fieldInfo.fieldClass;
        if (cls2.isArray()) {
            this.itemType = cls2.getComponentType();
            this.array = true;
            return;
        }
        this.itemType = TypeUtils.getCollectionItemType(type);
        this.array = false;
    }

    final void parseArray(DefaultJSONParser defaultJSONParser, Type type, Collection collection) {
        Class cls;
        int i2;
        int i3;
        Type type2 = this.itemType;
        ObjectDeserializer objectDeserializer = this.deserializer;
        if (type instanceof ParameterizedType) {
            if (type2 instanceof TypeVariable) {
                TypeVariable typeVariable = (TypeVariable) type2;
                ParameterizedType parameterizedType = (ParameterizedType) type;
                cls = parameterizedType.getRawType() instanceof Class ? (Class) parameterizedType.getRawType() : null;
                if (cls != null) {
                    int length = cls.getTypeParameters().length;
                    i3 = 0;
                    while (i3 < length) {
                        if (cls.getTypeParameters()[i3].getName().equals(typeVariable.getName())) {
                            break;
                        }
                        i3++;
                    }
                }
                i3 = -1;
                if (i3 != -1) {
                    type2 = parameterizedType.getActualTypeArguments()[i3];
                    if (!type2.equals(this.itemType)) {
                        objectDeserializer = defaultJSONParser.config.getDeserializer(type2);
                    }
                }
            } else if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
                if (actualTypeArguments.length == 1 && (actualTypeArguments[0] instanceof TypeVariable)) {
                    TypeVariable typeVariable2 = (TypeVariable) actualTypeArguments[0];
                    ParameterizedType parameterizedType3 = (ParameterizedType) type;
                    cls = parameterizedType3.getRawType() instanceof Class ? (Class) parameterizedType3.getRawType() : null;
                    if (cls != null) {
                        int length2 = cls.getTypeParameters().length;
                        i2 = 0;
                        while (i2 < length2) {
                            if (cls.getTypeParameters()[i2].getName().equals(typeVariable2.getName())) {
                                break;
                            }
                            i2++;
                        }
                    }
                    i2 = -1;
                    if (i2 != -1) {
                        actualTypeArguments[0] = parameterizedType3.getActualTypeArguments()[i2];
                        type2 = new ParameterizedTypeImpl(actualTypeArguments, parameterizedType2.getOwnerType(), parameterizedType2.getRawType());
                    }
                }
            }
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (objectDeserializer == null) {
            objectDeserializer = defaultJSONParser.config.getDeserializer(type2);
            this.deserializer = objectDeserializer;
        }
        int i4 = jSONLexer.token;
        if (i4 != 14) {
            if (i4 == 12) {
                collection.add(objectDeserializer.deserialze(defaultJSONParser, type2, 0));
                return;
            }
            String str = "exepct '[', but " + JSONToken.name(jSONLexer.token);
            if (type != null) {
                str = str + ", type : " + type;
            }
            throw new JSONException(str);
        }
        int i5 = 0;
        char c2 = jSONLexer.ch;
        int i6 = 15;
        char c3 = JSONLexer.EOI;
        if (c2 == '[') {
            int i7 = jSONLexer.bp + 1;
            jSONLexer.bp = i7;
            jSONLexer.ch = i7 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i7);
            jSONLexer.token = 14;
        } else if (c2 == '{') {
            int i8 = jSONLexer.bp + 1;
            jSONLexer.bp = i8;
            jSONLexer.ch = i8 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i8);
            jSONLexer.token = 12;
        } else if (c2 == '\"') {
            jSONLexer.scanString();
        } else if (c2 == ']') {
            int i9 = jSONLexer.bp + 1;
            jSONLexer.bp = i9;
            jSONLexer.ch = i9 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i9);
            jSONLexer.token = 15;
        } else {
            jSONLexer.nextToken();
        }
        while (true) {
            int i10 = jSONLexer.token;
            if (i10 == 16) {
                jSONLexer.nextToken();
            } else if (i10 == i6) {
                break;
            } else {
                collection.add(objectDeserializer.deserialze(defaultJSONParser, type2, Integer.valueOf(i5)));
                if (defaultJSONParser.resolveStatus == 1) {
                    defaultJSONParser.checkListResolve(collection);
                }
                if (jSONLexer.token == 16) {
                    char c4 = jSONLexer.ch;
                    if (c4 == '[') {
                        int i11 = jSONLexer.bp + 1;
                        jSONLexer.bp = i11;
                        jSONLexer.ch = i11 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i11);
                        jSONLexer.token = 14;
                    } else if (c4 == '{') {
                        int i12 = jSONLexer.bp + 1;
                        jSONLexer.bp = i12;
                        jSONLexer.ch = i12 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i12);
                        jSONLexer.token = 12;
                    } else if (c4 == '\"') {
                        jSONLexer.scanString();
                    } else {
                        jSONLexer.nextToken();
                    }
                }
                i5++;
                i6 = 15;
            }
        }
        if (jSONLexer.ch == ',') {
            int i13 = jSONLexer.bp + 1;
            jSONLexer.bp = i13;
            if (i13 < jSONLexer.len) {
                c3 = jSONLexer.text.charAt(i13);
            }
            jSONLexer.ch = c3;
            jSONLexer.token = 16;
            return;
        }
        jSONLexer.nextToken();
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        JDJSONArray arrayList;
        JDJSONArray jDJSONArray;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i2 = jSONLexer.token();
        if (i2 != 8 && (i2 != 4 || jSONLexer.stringVal().length() != 0)) {
            if (this.array) {
                JDJSONArray jDJSONArray2 = new JDJSONArray();
                jDJSONArray2.setComponentType(this.itemType);
                jDJSONArray = jDJSONArray2;
                arrayList = jDJSONArray2;
            } else {
                arrayList = new ArrayList();
                jDJSONArray = null;
            }
            ParseContext parseContext = defaultJSONParser.contex;
            defaultJSONParser.setContext(parseContext, obj, this.fieldInfo.name);
            parseArray(defaultJSONParser, type, arrayList);
            defaultJSONParser.setContext(parseContext);
            Object obj2 = arrayList;
            if (this.array) {
                Object array = arrayList.toArray((Object[]) Array.newInstance((Class) this.itemType, arrayList.size()));
                jDJSONArray.setRelatedArray(array);
                obj2 = array;
            }
            if (obj == null) {
                map.put(this.fieldInfo.name, obj2);
                return;
            } else {
                setValue(obj, obj2);
                return;
            }
        }
        setValue(obj, (Object) null);
        defaultJSONParser.lexer.nextToken();
    }
}
