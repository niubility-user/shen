package com.tencent.mapsdk.internal;

import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.LangUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class a {
    private static ClassLoader a = null;
    private static boolean b = true;

    public static Object a(String str, boolean z, ClassLoader classLoader) {
        Class<?> cls;
        if (str.equals("java.lang.Integer")) {
            return 0;
        }
        if (str.equals("java.lang.Boolean")) {
            return Boolean.FALSE;
        }
        if (str.equals("java.lang.Byte")) {
            return (byte) 0;
        }
        if (str.equals("java.lang.Double")) {
            return Double.valueOf(0.0d);
        }
        if (str.equals("java.lang.Float")) {
            return Float.valueOf(0.0f);
        }
        if (str.equals("java.lang.Long")) {
            return 0L;
        }
        if (str.equals("java.lang.Short")) {
            return (short) 0;
        }
        if (str.equals("java.lang.Character")) {
            throw new IllegalArgumentException("can not support java.lang.Character");
        }
        if (str.equals("java.lang.String")) {
            return "";
        }
        if (str.equals("java.util.List")) {
            return new ArrayList();
        }
        if (str.equals("java.util.Map")) {
            return new HashMap();
        }
        if (str.equals("Array")) {
            return "Array";
        }
        if (str.equals("?")) {
            return str;
        }
        try {
            if (classLoader != null) {
                cls = Class.forName(str, z, classLoader);
            } else {
                ClassLoader classLoader2 = a;
                cls = classLoader2 != null ? Class.forName(str, b, classLoader2) : Class.forName(str);
            }
            return cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            throw new b(e2);
        }
    }

    public static String a(String str) {
        return str.equals("int") ? "Integer" : str.equals("boolean") ? "Boolean" : str.equals("byte") ? "Byte" : str.equals("double") ? "Double" : str.equals("float") ? "Float" : str.equals("long") ? "Long" : str.equals("short") ? "Short" : str.equals(DYConstants.DY_CHAR) ? "Character" : str;
    }

    public static String a(String str, String str2) {
        StringBuilder sb;
        String str3;
        if (!str2.equals("int")) {
            if (!str2.equals("boolean")) {
                if (str2.equals("byte")) {
                    sb = new StringBuilder();
                } else if (str2.equals("double")) {
                    sb = new StringBuilder();
                } else if (str2.equals("float")) {
                    sb = new StringBuilder();
                } else if (str2.equals("long")) {
                    sb = new StringBuilder();
                } else if (str2.equals("short")) {
                    sb = new StringBuilder();
                } else if (str2.equals(DYConstants.DY_CHAR)) {
                    sb = new StringBuilder();
                } else {
                    sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(LangUtils.SINGLE_SPACE);
                    sb.append(str);
                    str3 = " = null ;\n";
                }
                sb.append(str2);
                sb.append(LangUtils.SINGLE_SPACE);
                sb.append(str);
                sb.append(" ;\n");
                return sb.toString();
            }
            sb = new StringBuilder();
            sb.append(str2);
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(str);
            str3 = "=false ;\n";
            sb.append(str3);
            return sb.toString();
        }
        sb = new StringBuilder();
        sb.append(str2);
        sb.append(LangUtils.SINGLE_SPACE);
        sb.append(str);
        sb.append("=0 ;\n");
        return sb.toString();
    }

    public static String a(ArrayList<String> arrayList) {
        StringBuilder sb;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList.set(i2, d(arrayList.get(i2)));
        }
        Collections.reverse(arrayList);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            String str = arrayList.get(i3);
            if (str.equals(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID)) {
                int i4 = i3 - 1;
                arrayList.set(i4, "<" + arrayList.get(i4));
                sb = new StringBuilder();
            } else if (str.equals("map")) {
                int i5 = i3 - 1;
                arrayList.set(i5, "<" + arrayList.get(i5) + DYConstants.DY_REGEX_COMMA);
                sb = new StringBuilder();
            } else if (str.equals("Array")) {
                int i6 = i3 - 1;
                arrayList.set(i6, "<" + arrayList.get(i6));
                sb = new StringBuilder();
            }
            sb.append(arrayList.get(0));
            sb.append(">");
            arrayList.set(0, sb.toString());
        }
        Collections.reverse(arrayList);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    private static void a(ArrayList<String> arrayList, String str) {
        int length = str.length();
        while (str.charAt(length - 1) == '>' && length - 1 != 0) {
        }
        arrayList.add(0, e(str.substring(0, length)));
    }

    public static void a(boolean z, ClassLoader classLoader) {
        b = z;
        a = classLoader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (r3 == null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0075, code lost:
        if (r3 == null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
        r3 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
        r4 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Object b(String str, boolean z, ClassLoader classLoader) {
        Iterator<String> it = b(str).iterator();
        Object obj = null;
        Object obj2 = null;
        while (true) {
            Object obj3 = obj2;
            while (it.hasNext()) {
                obj = a(it.next(), z, classLoader);
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if ("Array".equals(str2)) {
                        if (obj2 == null) {
                            obj = Array.newInstance(Byte.class, 0);
                        }
                    } else if (!"?".equals(str2)) {
                    }
                } else if (obj instanceof List) {
                    if (obj2 == null || !(obj2 instanceof Byte)) {
                        if (obj2 != null) {
                            ((List) obj).add(obj2);
                        }
                        obj2 = null;
                    } else {
                        obj = Array.newInstance(Byte.class, 1);
                        Array.set(obj, 0, obj2);
                    }
                } else if (obj instanceof Map) {
                    if ((obj2 != null) & (obj3 != null)) {
                        ((Map) obj).put(obj2, obj3);
                    }
                    obj2 = null;
                }
            }
            return obj;
        }
    }

    public static ArrayList<String> b(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        int indexOf = str.indexOf("<");
        int i2 = 0;
        while (i2 < indexOf) {
            a(arrayList, str.substring(i2, indexOf));
            i2 = indexOf + 1;
            indexOf = str.indexOf("<", i2);
            int indexOf2 = str.indexOf(DYConstants.DY_REGEX_COMMA, i2);
            if (indexOf == -1) {
                indexOf = indexOf2;
            }
            if (indexOf2 != -1 && indexOf2 < indexOf) {
                indexOf = indexOf2;
            }
        }
        a(arrayList, str.substring(i2, str.length()));
        return arrayList;
    }

    public static boolean c(String str) {
        return str.equals("int") || str.equals("boolean") || str.equals("byte") || str.equals("double") || str.equals("float") || str.equals("long") || str.equals("short") || str.equals(DYConstants.DY_CHAR) || str.equals("Integer") || str.equals("Boolean") || str.equals("Byte") || str.equals("Double") || str.equals("Float") || str.equals("Long") || str.equals("Short") || str.equals("Char");
    }

    public static String d(String str) {
        if (str.equals("java.lang.Integer") || str.equals("int")) {
            return "int32";
        }
        if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
            return "bool";
        }
        if (str.equals("java.lang.Byte") || str.equals("byte")) {
            return DYConstants.DY_CHAR;
        }
        String str2 = "double";
        if (!str.equals("java.lang.Double") && !str.equals("double")) {
            str2 = "float";
            if (!str.equals("java.lang.Float") && !str.equals("float")) {
                if (str.equals("java.lang.Long") || str.equals("long")) {
                    return "int64";
                }
                if (str.equals("java.lang.Short") || str.equals("short")) {
                    return "short";
                }
                if (str.equals("java.lang.Character")) {
                    throw new IllegalArgumentException("can not support java.lang.Character");
                }
                return str.equals("java.lang.String") ? "string" : str.equals("java.util.List") ? ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID : str.equals("java.util.Map") ? "map" : str;
            }
        }
        return str2;
    }

    public static String e(String str) {
        return str.equals("int32") ? "java.lang.Integer" : str.equals("bool") ? "java.lang.Boolean" : str.equals(DYConstants.DY_CHAR) ? "java.lang.Byte" : str.equals("double") ? "java.lang.Double" : str.equals("float") ? "java.lang.Float" : str.equals("int64") ? "java.lang.Long" : str.equals("short") ? "java.lang.Short" : str.equals("string") ? "java.lang.String" : str.equals(ThemeTitleConstant.TITLE_LIST_DRAWABLE_ID) ? "java.util.List" : str.equals("map") ? "java.util.Map" : str;
    }
}
