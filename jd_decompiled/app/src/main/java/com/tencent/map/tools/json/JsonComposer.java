package com.tencent.map.tools.json;

import android.os.Build;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.jdsdk.constant.CartConstant;
import com.tencent.map.tools.Util;
import com.tencent.map.tools.json.JsonParser;
import com.tencent.map.tools.json.annotation.Json;
import com.tencent.map.tools.json.annotation.JsonType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class JsonComposer implements JsonEncoder, JsonParser {
    private static Map<Class, Map<Field, String>> sClassJsonMap = new ConcurrentHashMap();
    private String mFieldNamePrefix;
    private Map<Field, String> mJsonFields;
    private FieldNameStyle mFieldNameStyle = FieldNameStyle.HUMP;
    private boolean mAllowEmpty = true;
    private Map<Field, JsonParser.Deserializer> mFieldDeserializer = new HashMap();
    private Map<Class, JsonParser.Deserializer> mClassDeserializer = new HashMap();

    private synchronized void checkJsonComposerElements() {
        ArrayList<Field> arrayList;
        Map<Field, String> map;
        String name;
        Class<? extends JsonParser.Deserializer> deserializer;
        JsonType jsonType = (JsonType) getClass().getAnnotation(JsonType.class);
        if (jsonType != null) {
            this.mAllowEmpty = jsonType.allowEmpty();
            this.mFieldNameStyle = jsonType.fieldNameStyle();
            this.mFieldNamePrefix = jsonType.fieldNamePrefix();
            Class<? extends JsonParser.Deserializer> deserializer2 = jsonType.deserializer();
            if (deserializer2 != JsonParser.Deserializer.class) {
                this.mClassDeserializer.put(getClass(), (JsonParser.Deserializer) Util.newInstance(deserializer2, new Object[0]));
            }
        }
        this.mJsonFields = sClassJsonMap.get(getClass());
        ArrayList arrayList2 = new ArrayList();
        Map<Field, String> map2 = this.mJsonFields;
        if (map2 == null) {
            this.mJsonFields = new ConcurrentHashMap();
            for (Class<?> cls = getClass(); cls != JsonComposer.class; cls = cls.getSuperclass()) {
                arrayList2.addAll(Arrays.asList(cls.getDeclaredFields()));
            }
            sClassJsonMap.put(getClass(), this.mJsonFields);
            arrayList = arrayList2;
        } else {
            arrayList = map2.keySet();
        }
        for (Field field : arrayList) {
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())) {
                JsonType jsonType2 = (JsonType) field.getType().getAnnotation(JsonType.class);
                if (jsonType2 != null && (deserializer = jsonType2.deserializer()) != JsonParser.Deserializer.class) {
                    this.mFieldDeserializer.put(field, (JsonParser.Deserializer) Util.newInstance(deserializer, new Object[0]));
                }
                Json json = (Json) field.getAnnotation(Json.class);
                if (json != null) {
                    if (!json.ignore()) {
                        if (TextUtils.isEmpty(json.name())) {
                            map = this.mJsonFields;
                            name = translateFieldName(field.getName());
                        } else {
                            map = this.mJsonFields;
                            name = json.name();
                        }
                        map.put(field, name);
                    }
                    Class<? extends JsonParser.Deserializer> deserializer3 = json.deserializer();
                    if (deserializer3 != JsonParser.Deserializer.class) {
                        this.mFieldDeserializer.put(field, (JsonParser.Deserializer) Util.newInstance(deserializer3, new Object[0]));
                    }
                } else if (!field.getName().contains("this")) {
                    this.mJsonFields.put(field, translateFieldName(field.getName()));
                }
            }
        }
    }

    private String pickString(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int indexOf = str.indexOf("<");
        int lastIndexOf = str.lastIndexOf(">");
        if (indexOf < 0) {
            indexOf = 0;
        }
        if (lastIndexOf < 0) {
            lastIndexOf = str.length();
        }
        if (lastIndexOf > indexOf) {
            String substring = str.substring(indexOf + 1, lastIndexOf);
            return (substring.contains("<") && substring.contains(">")) ? pickString(substring) : substring;
        }
        return str;
    }

    private String translateFieldName(String str) {
        String str2 = this.mFieldNamePrefix;
        if (str2 != null && str.startsWith(str2)) {
            String substring = str.substring(this.mFieldNamePrefix.length(), this.mFieldNamePrefix.length() + 1);
            str = substring.toLowerCase() + str.substring(this.mFieldNamePrefix.length() + 1);
        }
        if (this.mFieldNameStyle == FieldNameStyle.UNDERLINE) {
            Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(stringBuffer, CartConstant.KEY_YB_INFO_LINK + matcher.group(0).toLowerCase());
            }
            matcher.appendTail(stringBuffer);
            return stringBuffer.toString();
        }
        return str;
    }

    public Object getFieldValueByJsonName(String str) {
        Map<Field, String> map = this.mJsonFields;
        if (map != null) {
            for (Map.Entry<Field, String> entry : map.entrySet()) {
                if (entry.getValue().equals(str)) {
                    return entry.getKey().get(this);
                }
            }
            return null;
        }
        return null;
    }

    public boolean onEachItemParse(String str, Object obj) {
        return false;
    }

    public boolean onEachItemToJson(JSONObject jSONObject, String str, Object obj) {
        return false;
    }

    @Override // com.tencent.map.tools.json.JsonParser
    public void parse(JSONObject jSONObject) {
        Class findClass;
        Collection parseTo;
        byte byteValue;
        char charValue;
        double doubleValue;
        float floatValue;
        int intValue;
        long longValue;
        short shortValue;
        checkJsonComposerElements();
        JsonParser.Deserializer deserializer = this.mClassDeserializer.get(getClass());
        if (deserializer != null) {
            try {
                deserializer.deserialize(this, getClass().getName(), jSONObject);
                return;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        for (Map.Entry<Field, String> entry : this.mJsonFields.entrySet()) {
            Field key = entry.getKey();
            key.setAccessible(true);
            Class<?> type = key.getType();
            Object opt = jSONObject.opt(entry.getValue());
            JsonParser.Deserializer deserializer2 = this.mFieldDeserializer.get(key);
            if (deserializer2 != null) {
                try {
                    key.set(this, deserializer2.deserialize(this, entry.getValue(), opt));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            if (!onEachItemParse(entry.getValue(), opt)) {
                if (opt instanceof JSONArray) {
                    Type genericType = key.getGenericType();
                    if (genericType instanceof ParameterizedType) {
                        String pickString = pickString(genericType.toString());
                        try {
                            if (!TextUtils.isEmpty(pickString) && (findClass = Util.findClass(pickString, getClass().getClassLoader())) != null) {
                                Collection collection = null;
                                if (Set.class.isAssignableFrom(key.getType())) {
                                    parseTo = JsonUtils.parseTo(HashSet.class, (JSONArray) opt, findClass, new Object[0]);
                                } else {
                                    if (List.class.isAssignableFrom(key.getType())) {
                                        parseTo = JsonUtils.parseTo(ArrayList.class, (JSONArray) opt, findClass, new Object[0]);
                                    }
                                    key.set(this, collection);
                                }
                                collection = parseTo;
                                key.set(this, collection);
                            }
                        } catch (IllegalAccessException e4) {
                            e4.printStackTrace();
                        }
                    } else {
                        Class cls = (Class) genericType;
                        if (cls.isArray()) {
                            key.set(this, JsonUtils.parseToArray((JSONArray) opt, cls.getComponentType()));
                        }
                    }
                } else if (opt instanceof JSONObject) {
                    key.set(this, JsonUtils.parseToModel((JSONObject) opt, key.getType(), new Object[0]));
                } else if (type == String.class) {
                    key.set(this, String.valueOf(opt));
                } else {
                    try {
                        if (type == Boolean.TYPE) {
                            if (opt instanceof Boolean) {
                                key.setBoolean(this, ((Boolean) opt).booleanValue());
                            } else if (opt instanceof String) {
                                if (opt.equals(DYConstants.DY_FALSE)) {
                                    key.setBoolean(this, false);
                                } else if (opt.equals(DYConstants.DY_TRUE)) {
                                    key.setBoolean(this, true);
                                }
                            }
                        } else if (type == Byte.TYPE) {
                            if (opt instanceof Byte) {
                                byteValue = ((Byte) opt).byteValue();
                            } else if (opt instanceof Number) {
                                byteValue = ((Number) opt).byteValue();
                            }
                            key.setByte(this, byteValue);
                        } else if (type == Character.TYPE) {
                            if (opt instanceof Character) {
                                charValue = ((Character) opt).charValue();
                            } else if (opt instanceof Integer) {
                                if (Character.isLetter(((Integer) opt).intValue())) {
                                    charValue = Character.toChars(((Integer) opt).intValue())[0];
                                } else if (10 > ((Integer) opt).intValue() && ((Integer) opt).intValue() >= 0) {
                                    charValue = Character.forDigit(((Integer) opt).intValue(), 10);
                                }
                            }
                            key.setChar(this, charValue);
                        } else if (type == Double.TYPE) {
                            if (opt instanceof Number) {
                                doubleValue = ((Number) opt).doubleValue();
                            } else if (opt instanceof String) {
                                doubleValue = Double.parseDouble((String) opt);
                            }
                            key.setDouble(this, doubleValue);
                        } else if (type == Float.TYPE) {
                            if (opt instanceof Number) {
                                floatValue = ((Number) opt).floatValue();
                            } else if (opt instanceof String) {
                                floatValue = Float.parseFloat((String) opt);
                            }
                            key.setFloat(this, floatValue);
                        } else if (type == Integer.TYPE) {
                            if (opt instanceof Number) {
                                intValue = ((Number) opt).intValue();
                            } else if (opt instanceof String) {
                                intValue = Integer.parseInt((String) opt);
                            }
                            key.setInt(this, intValue);
                        } else if (type == Long.TYPE) {
                            if (opt instanceof Number) {
                                longValue = ((Number) opt).longValue();
                            } else if (opt instanceof String) {
                                longValue = Long.parseLong((String) opt);
                            }
                            key.setLong(this, longValue);
                        } else if (type == Short.TYPE) {
                            if (opt instanceof Number) {
                                shortValue = ((Number) opt).shortValue();
                            } else if (opt instanceof String) {
                                shortValue = Short.parseShort((String) opt);
                            }
                            key.setShort(this, shortValue);
                        } else if (opt != null) {
                            key.set(this, opt);
                        }
                    } catch (NumberFormatException e5) {
                        e5.printStackTrace();
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // com.tencent.map.tools.json.JsonEncoder
    public JSONObject toJson() {
        Object obj;
        String value;
        checkJsonComposerElements();
        Set<Map.Entry<Field, String>> entrySet = this.mJsonFields.entrySet();
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<Field, String> entry : entrySet) {
            Field key = entry.getKey();
            key.setAccessible(true);
            Class<?> type = key.getType();
            Object obj2 = null;
            try {
                obj = key.get(this);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
                obj = null;
            }
            if (!onEachItemToJson(jSONObject, entry.getValue(), obj)) {
                if (Collection.class.isAssignableFrom(type)) {
                    try {
                        Collection collection = (Collection) key.get(this);
                        JSONArray jSONArray = new JSONArray();
                        if (collection != null && !collection.isEmpty()) {
                            for (Object obj3 : collection) {
                                if (obj3 instanceof JsonEncoder) {
                                    jSONArray.put(JsonUtils.modelToJson(obj3));
                                }
                            }
                        }
                        String value2 = entry.getValue();
                        if (this.mAllowEmpty || (collection != null && !collection.isEmpty())) {
                            obj2 = jSONArray;
                        }
                        jSONObject.put(value2, obj2);
                    } catch (IllegalAccessException e3) {
                        e3.printStackTrace();
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                } else {
                    Object obj4 = key.get(this);
                    if (obj4 instanceof Double) {
                        jSONObject.put(entry.getValue(), ((Double) obj4).doubleValue());
                    } else if (obj4 instanceof Long) {
                        jSONObject.put(entry.getValue(), ((Long) obj4).longValue());
                    } else if (obj4 instanceof Integer) {
                        jSONObject.put(entry.getValue(), ((Integer) obj4).intValue());
                    } else if (obj4 instanceof Boolean) {
                        jSONObject.put(entry.getValue(), ((Boolean) obj4).booleanValue());
                    } else {
                        if (obj4 instanceof JsonEncoder) {
                            value = entry.getValue();
                            obj4 = JsonUtils.modelToJson(obj4);
                        } else if (obj4 == null || !obj4.getClass().isArray()) {
                            value = entry.getValue();
                            if (obj4 == null && this.mAllowEmpty) {
                                obj4 = "";
                            }
                        } else if (Build.VERSION.SDK_INT >= 19) {
                            jSONObject.put(entry.getValue(), new JSONArray(obj4));
                        } else {
                            int length = Array.getLength(obj4);
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i2 = 0; i2 < length; i2++) {
                                jSONArray2.put(i2, Array.get(obj4, i2));
                            }
                            jSONObject.put(entry.getValue(), jSONArray2);
                        }
                        jSONObject.put(value, obj4);
                    }
                }
            }
        }
        return jSONObject;
    }
}
