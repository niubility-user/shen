package com.jd.viewkit.tool;

import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes18.dex */
public class ExpressionParserTool {
    private static String kVK_AND = "&&";
    private static String kVK_ELSE = "else";
    private static String kVK_ELSEIF = "else if";
    private static String kVK_IF = "if";
    private static String kVK_OR = "||";
    private static Pattern patternAll = Pattern.compile("^#(\\w+)\\{\\{(.+)\\}\\}$");
    private static Pattern patternEquation = Pattern.compile("^\\$(\\w+)(==|>=|<=|<|>|!=)(.+)");
    private static Pattern pattern = Pattern.compile(".*['|\"].+['|\"].*");
    private static Pattern patternIsArrayValue = Pattern.compile("\\S+\\[[0-9]+\\]");
    private static Pattern patternArrayValues = Pattern.compile("(.*)\\[(.*)\\]");

    private static Boolean _expressionIsStringType(String str) {
        return Boolean.valueOf(pattern.matcher(str).matches());
    }

    private static String _getExpressionStringValue(String str) {
        if (str.length() <= 2) {
            return null;
        }
        return str.substring(1, str.length() - 1);
    }

    private static Map<String, Object> _getObjectMap(String[] strArr, Map<String, Object> map) {
        int valueOfInt;
        for (String str : strArr) {
            Object obj = null;
            if (!StringTool.isEmpty(str) && map != null && (map instanceof Map)) {
                if (patternIsArrayValue.matcher(str).matches()) {
                    Matcher matcher = patternArrayValues.matcher(str);
                    if (matcher.find() && matcher.groupCount() == 2) {
                        String group = matcher.group(1);
                        String group2 = matcher.group(2);
                        Object obj2 = map.get(group);
                        if (obj2 != null && (obj2 instanceof List)) {
                            List list = (List) obj2;
                            if (StringTool.isInteger(group2) && list.size() > (valueOfInt = NumberTool.valueOfInt(group2))) {
                                obj = list.get(valueOfInt);
                            }
                        }
                    }
                } else {
                    map = map.get(str);
                }
            }
            map = obj;
        }
        return map;
    }

    private static Boolean _getValueWithExpression(String str, Map<String, Object> map) {
        Matcher matcher = patternEquation.matcher(str);
        if (matcher.find() && matcher.groupCount() == 3) {
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            String group3 = matcher.group(3);
            Boolean _expressionIsStringType = _expressionIsStringType(group3);
            Object obj = map.get(group);
            if (group2.equals("==")) {
                if (_expressionIsStringType.booleanValue() && (obj instanceof String)) {
                    return Boolean.valueOf(obj.equals(_getExpressionStringValue(group3)));
                }
                return Boolean.valueOf(((Float) obj).floatValue() == new Float(group3).floatValue());
            } else if (group2.equals("!=")) {
                if (_expressionIsStringType.booleanValue() && (obj instanceof String)) {
                    return Boolean.valueOf(!obj.equals(_getExpressionStringValue(group3)));
                }
                return Boolean.valueOf(((Float) obj).floatValue() != new Float(group3).floatValue());
            } else {
                if (group2.equals("<=")) {
                    if (!_expressionIsStringType.booleanValue()) {
                        return Boolean.valueOf(((Float) obj).floatValue() <= new Float(group3).floatValue());
                    }
                } else if (group2.equals(">=")) {
                    if (!_expressionIsStringType.booleanValue()) {
                        return Boolean.valueOf(((Float) obj).floatValue() >= new Float(group3).floatValue());
                    }
                } else if (group2.equals(">")) {
                    if (!_expressionIsStringType.booleanValue()) {
                        return Boolean.valueOf(((Float) obj).floatValue() > new Float(group3).floatValue());
                    }
                } else if (group2.equals("<") && !_expressionIsStringType.booleanValue()) {
                    return Boolean.valueOf(((Float) obj).floatValue() < new Float(group3).floatValue());
                }
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static int getIntValueRef(String str, Map<String, Object> map) {
        Object objectValueRef;
        if (StringTool.isEmpty(str) || str.length() < 1 || str.length() <= 0 || (objectValueRef = getObjectValueRef(str, map)) == null || !NumberTool.isInt(objectValueRef.toString())) {
            return -1;
        }
        return NumberTool.valueOfInt(objectValueRef.toString());
    }

    public static List getListByValueRe(String str, Map<String, Object> map) {
        Object objectValueRef;
        if (StringTool.isEmpty(str) || str.length() < 1 || str.length() <= 2 || (objectValueRef = getObjectValueRef(str, map)) == null || !(objectValueRef instanceof List)) {
            return null;
        }
        return (List) objectValueRef;
    }

    public static Map getMapByValueRe(String str, Map<String, Object> map) {
        Object objectValueRef;
        if (StringTool.isEmpty(str) || str.length() < 1 || str.length() <= 2 || (objectValueRef = getObjectValueRef(str, map)) == null || !(objectValueRef instanceof Map)) {
            return null;
        }
        return (Map) objectValueRef;
    }

    public static Object getObjectValueRef(String str, Map<String, Object> map) {
        Object obj;
        Object obj2;
        int valueOfInt;
        if (StringTool.isEmpty(str) || !StringTool.isBegin(str, "$")) {
            return str;
        }
        if (map == null || !(map instanceof Map)) {
            return null;
        }
        String[] split = str.substring(1).split("\\.");
        int length = split.length;
        int i2 = 0;
        Map<String, Object> map2 = map;
        while (i2 < length) {
            String str2 = split[i2];
            if (!StringTool.isEmpty(str2) && patternIsArrayValue.matcher(str2).matches()) {
                Matcher matcher = patternArrayValues.matcher(str2);
                if (matcher.find() && matcher.groupCount() == 2) {
                    String group = matcher.group(1);
                    String group2 = matcher.group(2);
                    if (map2 != null && (map2 instanceof Map) && (obj2 = map2.get(group)) != null && (obj2 instanceof List)) {
                        List list = (List) obj2;
                        if (StringTool.isInteger(group2) && list.size() > (valueOfInt = NumberTool.valueOfInt(group2))) {
                            obj = list.get(valueOfInt);
                        }
                    }
                }
                return null;
            } else if (map2 == null || !(map2 instanceof Map)) {
                return null;
            } else {
                obj = map2.get(str2);
            }
            i2++;
            map2 = obj;
        }
        return map2;
    }

    public static String getStringValueRef(String str, Map<String, Object> map) {
        Object objectValueRef;
        if (StringTool.isEmpty(str) || str.length() < 1 || str.length() <= 0 || (objectValueRef = getObjectValueRef(str, map)) == null) {
            return null;
        }
        return objectValueRef.toString();
    }

    public static String getUrlStringValueRef(String str, Map<String, Object> map) {
        String stringValueRef = getStringValueRef(str, map);
        if (StringTool.isEmpty(stringValueRef) || !StringTool.isBegin(stringValueRef, "http")) {
            return null;
        }
        return stringValueRef;
    }

    public static Boolean getValueWithExpression(String str, Map<String, Object> map) {
        String[] strArr;
        char c2;
        Matcher matcher = patternAll.matcher(str.replace(LangUtils.SINGLE_SPACE, ""));
        if (matcher.find() && matcher.groupCount() == 2) {
            String group = matcher.group(1);
            String group2 = matcher.group(2);
            if (group.length() > 0 && group2.length() > 0 && group.equals(kVK_IF)) {
                if (group2.contains(kVK_AND)) {
                    strArr = group2.split(kVK_AND);
                    c2 = 0;
                } else if (group2.contains(kVK_OR)) {
                    strArr = group2.split(kVK_OR);
                    c2 = 1;
                } else {
                    strArr = new String[]{group2};
                    c2 = 2;
                }
                int length = strArr.length;
                int i2 = 0;
                boolean z = true;
                while (i2 < length) {
                    boolean booleanValue = _getValueWithExpression(strArr[i2], map).booleanValue();
                    if (c2 == 2) {
                        return Boolean.valueOf(booleanValue);
                    }
                    if (c2 == 1) {
                        if (booleanValue) {
                            return Boolean.TRUE;
                        }
                    } else if (!booleanValue) {
                        return Boolean.FALSE;
                    }
                    i2++;
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }
        return Boolean.FALSE;
    }

    public static void main(String[] strArr) {
        System.out.println("hello word!");
        HashMap hashMap = new HashMap();
        hashMap.put("isFav", new Float(0.0f));
        System.out.println(getValueWithExpression("#if{{ $isFav != 0 }}", hashMap));
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("map2key1", "map2value1");
        hashMap2.put("map2key2", "map2value2");
        HashMap hashMap3 = new HashMap();
        hashMap3.put("map3key1", "map3value1");
        hashMap3.put("map3key2", "map3value2");
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashMap2);
        arrayList.add(hashMap3);
        hashMap.put("key3", arrayList);
        System.out.println(getObjectValueRef("$key3[1].map3key1", hashMap));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("aa");
        arrayList2.add("bb");
        arrayList2.add("cc");
        System.out.println(arrayList2);
        arrayList2.set(1, NotificationMessageSummary.DD_MSG);
        System.out.println(arrayList2);
    }

    public static void setMapByValueRef(String str, Map<String, Object> map, Map<String, Object> map2) {
        if (StringTool.isEmpty(str) || !StringTool.isBegin(str, "$")) {
            return;
        }
        String[] split = str.substring(1).split("\\.");
        if (split != null) {
            map = _getObjectMap(split, map);
        }
        if (map2 == null || !(map2 instanceof Map) || map == null || !(map instanceof Map)) {
            return;
        }
        for (String str2 : map2.keySet()) {
            map.put(str2, map2.get(str2));
        }
    }

    public static void setObjectValueRef(String str, Map<String, Object> map, Object obj) {
        if (StringTool.isEmpty(str) || !StringTool.isBegin(str, "$")) {
            return;
        }
        String[] split = str.substring(1).split("\\.");
        String str2 = null;
        if (split != null) {
            if (split.length == 1) {
                str2 = split[0];
            } else {
                map = _getObjectMap((String[]) Arrays.copyOfRange(split, 0, split.length - 1), map);
                str2 = split[split.length - 1];
            }
        }
        if (!StringTool.isNotEmpty(str2) || obj == null || map == null || !(map instanceof Map)) {
            return;
        }
        map.put(str2, obj);
    }
}
