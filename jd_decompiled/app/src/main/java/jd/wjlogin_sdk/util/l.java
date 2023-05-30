package jd.wjlogin_sdk.util;

import com.google.common.net.HttpHeaders;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class l {
    public static String a(JSONObject jSONObject) {
        try {
            return a(jSONObject.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean b(Class<?> cls) {
        return cls != null && (Boolean.TYPE.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls));
    }

    public static boolean c(Class<?> cls) {
        return cls != null && Collection.class.isAssignableFrom(cls);
    }

    public static boolean d(Class<?> cls) {
        return cls != null && Map.class.isAssignableFrom(cls);
    }

    public static boolean e(Class<?> cls) {
        return cls != null && (Byte.TYPE.isAssignableFrom(cls) || Short.TYPE.isAssignableFrom(cls) || Integer.TYPE.isAssignableFrom(cls) || Long.TYPE.isAssignableFrom(cls) || Float.TYPE.isAssignableFrom(cls) || Double.TYPE.isAssignableFrom(cls) || Number.class.isAssignableFrom(cls));
    }

    private static boolean f(Class<?> cls) {
        return (cls == null || g(cls) || a(cls) || c(cls) || d(cls)) ? false : true;
    }

    private static boolean g(Class<?> cls) {
        return b(cls) || e(cls) || h(cls);
    }

    public static boolean h(Class<?> cls) {
        return cls != null && (String.class.isAssignableFrom(cls) || Character.TYPE.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls));
    }

    private static <T> T i(Class<T> cls) throws JSONException {
        if (cls == null) {
            return null;
        }
        if (cls.isInterface()) {
            if (cls.equals(Map.class)) {
                return (T) new HashMap();
            }
            if (cls.equals(List.class)) {
                return (T) new ArrayList();
            }
            if (cls.equals(Set.class)) {
                return (T) new HashSet();
            }
            throw new JSONException("unknown interface: " + cls);
        }
        try {
            return cls.newInstance();
        } catch (Exception unused) {
            throw new JSONException("unknown class type: " + cls);
        }
    }

    public static String a(String str) {
        if (str != null) {
            try {
                if (!"".equals(str)) {
                    StringBuilder sb = new StringBuilder();
                    int i2 = 0;
                    char c2 = 0;
                    boolean z = false;
                    int i3 = 0;
                    while (i2 < str.length()) {
                        char charAt = str.charAt(i2);
                        if (charAt == '\"') {
                            if (c2 != '\\') {
                                z = !z;
                            }
                            sb.append(charAt);
                        } else if (charAt != ',') {
                            if (charAt != '[') {
                                if (charAt != ']') {
                                    if (charAt != '{') {
                                        if (charAt != '}') {
                                            sb.append(charAt);
                                        }
                                    }
                                }
                                if (!z) {
                                    sb.append('\n');
                                    i3--;
                                    a(sb, i3);
                                }
                                sb.append(charAt);
                            }
                            sb.append(charAt);
                            if (!z) {
                                sb.append('\n');
                                i3++;
                                a(sb, i3);
                            }
                        } else {
                            sb.append(charAt);
                            if (c2 != '\\' && !z) {
                                sb.append('\n');
                                a(sb, i3);
                            }
                        }
                        i2++;
                        c2 = charAt;
                    }
                    return sb.toString();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return "";
    }

    private static void a(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                sb.append('\t');
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
    }

    public static boolean a(Method[] methodArr, String str) {
        for (Method method : methodArr) {
            if (str.equals(method.getName())) {
                return true;
            }
        }
        return false;
    }

    public static String a(String str, String str2) {
        if (str == null || "".equals(str)) {
            return null;
        }
        return str2 + str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static void a(Object obj, Method method, String str, Object obj2) {
        if (obj2 != null) {
            try {
                if (!"".equals(obj2)) {
                    if ("String".equals(str)) {
                        method.invoke(obj, obj2.toString());
                    } else if (HttpHeaders.DATE.equals(str)) {
                        method.invoke(obj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(obj2.toString()));
                    } else {
                        if (!"Integer".equals(str) && !"int".equals(str)) {
                            if ("Long".equalsIgnoreCase(str)) {
                                method.invoke(obj, Long.valueOf(Long.parseLong(obj2.toString())));
                            } else if ("Double".equalsIgnoreCase(str)) {
                                method.invoke(obj, Double.valueOf(Double.parseDouble(obj2.toString())));
                            } else if ("Boolean".equalsIgnoreCase(str)) {
                                method.invoke(obj, Boolean.valueOf(Boolean.parseBoolean(obj2.toString())));
                            } else {
                                method.invoke(obj, obj2);
                            }
                        }
                        method.invoke(obj, Integer.valueOf(Integer.parseInt(obj2.toString())));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static <T> T a(JSONObject jSONObject, Class<T> cls) throws JSONException {
        T t;
        if (cls == null || a((Object) jSONObject) || (t = (T) i(cls)) == null) {
            return null;
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Field field : cls.getDeclaredFields()) {
            String a = a(field.getName(), "set");
            if (a(declaredMethods, a)) {
                try {
                    a(t, cls.getMethod(a, field.getType()), field, jSONObject);
                } catch (Exception e2) {
                    c0.a((short) d.g0, "JSONHelper parseObject e=" + e2.getMessage());
                }
            }
        }
        return t;
    }

    public static <T> T a(String str, Class<T> cls) throws JSONException {
        if (cls == null || str == null || str.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        if (a((Object) jSONObject)) {
            return null;
        }
        return (T) a(jSONObject, cls);
    }

    private static void a(Object obj, Method method, Field field, JSONObject jSONObject) {
        String name = field.getName();
        Class<?> type = field.getType();
        try {
            if (!g(type) && !HttpHeaders.DATE.equals(type.getSimpleName())) {
                if (f(type)) {
                    JSONObject optJSONObject = jSONObject.optJSONObject(name);
                    if (a((Object) optJSONObject)) {
                        return;
                    }
                    a(obj, method, type.getSimpleName(), a(optJSONObject, type));
                    return;
                }
                throw new Exception("unknow type!");
            }
            Object opt = jSONObject.opt(name);
            if (opt != null) {
                a(obj, method, type.getSimpleName(), opt);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static boolean a(Object obj) {
        if (obj instanceof JSONObject) {
            return JSONObject.NULL.equals(obj);
        }
        return obj == null;
    }

    public static boolean a(Class<?> cls) {
        return cls != null && cls.isArray();
    }
}
