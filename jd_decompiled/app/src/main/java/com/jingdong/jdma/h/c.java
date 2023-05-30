package com.jingdong.jdma.h;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    private String a = "";
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f12758c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private String f12759e;

    /* renamed from: f  reason: collision with root package name */
    private String f12760f;

    /* renamed from: g  reason: collision with root package name */
    private String f12761g;

    /* loaded from: classes12.dex */
    class a implements Comparator<b> {
        a(c cVar) {
        }

        @Override // java.util.Comparator
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public int compare(b bVar, b bVar2) {
            return bVar2.b - bVar.b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class b {
        private String a;
        private int b;

        /* renamed from: c  reason: collision with root package name */
        private String f12762c;
        private boolean d;

        b(c cVar) {
        }
    }

    private boolean b(String str) {
        Stack<String> stack = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        for (String str2 : str.split(LangUtils.SINGLE_SPACE)) {
            if (str2.length() != 0) {
                if (str2.equals("||")) {
                    while (!stack2.isEmpty() && (stack2.peek().equals("||") || stack2.peek().equals("&&"))) {
                        a(stack, stack2);
                    }
                    stack2.push(str2);
                } else if (str2.equals("&&")) {
                    while (!stack2.isEmpty() && stack2.peek().equals("&&")) {
                        a(stack, stack2);
                    }
                    stack2.push(str2);
                } else {
                    stack.push(str2);
                }
            }
        }
        while (!stack2.isEmpty()) {
            a(stack, stack2);
        }
        return Boolean.valueOf(stack.pop()).booleanValue();
    }

    private boolean c(String str) {
        int indexOf;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains("$version$")) {
            if (TextUtils.isEmpty(this.f12758c)) {
                return false;
            }
            if (str.contains("==")) {
                return com.jingdong.jdma.h.a.c(this.f12758c, a(str, str.indexOf("==") + 2));
            }
            if (str.contains("!=")) {
                return com.jingdong.jdma.h.a.h(this.f12758c, a(str, str.indexOf("!=") + 2));
            }
            return str.contains(">=") ? com.jingdong.jdma.h.a.c(this.f12758c, a(str, str.indexOf(">=") + 2)) || a(this.f12758c, a(str, str.indexOf(">=") + 2)) > 0 : str.contains(">") ? a(this.f12758c, a(str, str.indexOf(">") + 1)) > 0 : str.contains("<=") ? com.jingdong.jdma.h.a.c(this.f12758c, a(str, str.indexOf("<=") + 2)) || a(this.f12758c, a(str, str.indexOf("<=") + 2)) < 0 : str.contains("<") && a(this.f12758c, a(str, str.indexOf("<") + 1)) < 0;
        } else if (str.contains("%") && str.length() >= 3) {
            String substring = str.substring(0, str.indexOf("%"));
            char[] charArray = str.substring(str.indexOf("%") + 1).toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c2 : charArray) {
                if (!Character.isDigit(c2)) {
                    break;
                }
                sb.append(c2);
            }
            if (sb.length() == 0) {
                return false;
            }
            return c(com.jingdong.jdma.h.a.g(substring, sb.toString()) + str.substring(str.indexOf(sb.toString()) + sb.length()));
        } else {
            int indexOf2 = str.indexOf("(");
            int indexOf3 = str.indexOf(")");
            String str2 = "";
            if (str.contains("md5") && str.contains("(") && str.contains(")")) {
                String substring2 = str.substring(indexOf2 + 1, indexOf3);
                String substring3 = str.substring(indexOf3 + 1);
                try {
                    if (!TextUtils.isEmpty(substring2)) {
                        str2 = com.jingdong.jdma.a.a.d.a(substring2);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return c(str2 + substring3);
            } else if (str.contains(".substring") && str.contains("(") && str.contains(")")) {
                String substring4 = str.substring(0, str.indexOf(".substring"));
                String substring5 = str.substring(indexOf2 + 1, indexOf3);
                String substring6 = str.substring(indexOf3 + 1);
                if (TextUtils.isEmpty(substring4) || TextUtils.isEmpty(substring5)) {
                    return false;
                }
                if (substring5.contains(DYConstants.DY_REGEX_COMMA)) {
                    String[] split = substring5.split(DYConstants.DY_REGEX_COMMA);
                    if (split.length != 2) {
                        return false;
                    }
                    return c(com.jingdong.jdma.h.a.a(substring4, split[0], split[1]) + substring6);
                } else if (TextUtils.isDigitsOnly(substring5)) {
                    return c(com.jingdong.jdma.h.a.a(substring4, substring5, substring4.length() + "") + substring6);
                } else {
                    return false;
                }
            } else if (str.contains("hash") && str.contains("(") && str.contains(")")) {
                String substring7 = str.substring(indexOf2 + 1, indexOf3);
                String substring8 = str.substring(indexOf3 + 1);
                if (!TextUtils.isEmpty(substring7) && substring7.contains(DYConstants.DY_REGEX_COMMA)) {
                    String[] split2 = substring7.split(DYConstants.DY_REGEX_COMMA);
                    if (split2.length != 2) {
                        return false;
                    }
                    return c(com.jingdong.jdma.h.a.d(split2[0], split2[1]) + substring8);
                }
                return false;
            } else if (str.contains("IN") && str.contains("(") && str.contains(")")) {
                String substring9 = str.substring(indexOf2 + 1, indexOf3);
                if (TextUtils.isEmpty(substring9) || (indexOf = substring9.indexOf(DYConstants.DY_REGEX_COMMA)) == -1) {
                    return false;
                }
                return com.jingdong.jdma.h.a.f(substring9.substring(0, indexOf), substring9.substring(indexOf + 1));
            } else if (str.contains("(") && str.contains(")") && str.indexOf("(") != 0) {
                return false;
            } else {
                if (str.contains(">=") && str.length() >= 4) {
                    return com.jingdong.jdma.h.a.a(str.substring(0, str.indexOf(">=")), str.substring(str.indexOf(">=") + 2));
                }
                if (str.contains(">") && str.length() >= 3) {
                    return com.jingdong.jdma.h.a.b(str.substring(0, str.indexOf(">")), str.substring(str.indexOf(">") + 1));
                }
                if (str.contains("<=") && str.length() >= 4) {
                    return com.jingdong.jdma.h.a.i(str.substring(0, str.indexOf("<=")), str.substring(str.indexOf("<=") + 2));
                }
                if (str.contains("<") && str.length() >= 3) {
                    return com.jingdong.jdma.h.a.j(str.substring(0, str.indexOf("<")), str.substring(str.indexOf("<") + 1));
                }
                if (str.contains("==") && str.length() >= 4) {
                    return com.jingdong.jdma.h.a.c(str.substring(0, str.indexOf("==")), str.substring(str.indexOf("==") + 2));
                }
                if (str.contains("!=") && str.length() >= 4) {
                    return com.jingdong.jdma.h.a.h(str.substring(0, str.indexOf("!=")), str.substring(str.indexOf("!=") + 2));
                }
                if (str.contains("||") && str.length() >= 4) {
                    return com.jingdong.jdma.h.a.e(str.substring(0, str.indexOf("||")), str.substring(str.indexOf("||") + 2));
                }
                if (str.contains("&&") && str.length() >= 4) {
                    return com.jingdong.jdma.h.a.k(str.substring(0, str.indexOf("&&")), str.substring(str.indexOf("&&") + 2));
                }
                return Boolean.parseBoolean(str);
            }
        }
    }

    private String e(String str) {
        if (str.contains("$os$")) {
            str = str.replaceAll("\\$os\\$", "android");
        }
        if (str.contains("$pin$") && !TextUtils.isEmpty(this.d)) {
            str = str.replaceAll("\\$pin\\$", this.d);
        }
        if (str.contains("$uuid$") && !TextUtils.isEmpty(this.f12759e)) {
            str = str.replaceAll("\\$uuid\\$", this.f12759e);
        }
        if (str.contains("$logType$") && !TextUtils.isEmpty(this.f12760f)) {
            str = str.replaceAll("\\$logType\\$", this.f12760f);
        }
        if (str.contains("$build_id$") && !TextUtils.isEmpty(this.f12761g)) {
            str = str.replaceAll("\\$build_id\\$", this.f12761g);
        }
        return str.replaceAll("'", "");
    }

    public boolean a(String str) {
        boolean b2;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            String e2 = e(str);
            if (!e2.contains("&&") && !e2.contains("||")) {
                b2 = c(e2);
                return b2;
            }
            String str2 = "";
            int i2 = 0;
            for (String str3 : e2.split("&&|\\|\\|")) {
                str2 = str2 + c(str3) + LangUtils.SINGLE_SPACE;
                if (i2 < r1.length - 1) {
                    int indexOf = e2.indexOf(str3);
                    str2 = str2 + e2.substring(str3.length() + indexOf, indexOf + str3.length() + 2) + LangUtils.SINGLE_SPACE;
                }
                i2++;
            }
            b2 = b(str2.substring(0, str2.length() - 1));
            return b2;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public void d(String str) {
        this.a = "";
        this.b = "";
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            if (length == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(length);
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                b bVar = new b(this);
                bVar.a = optJSONObject.optString("condition");
                bVar.b = optJSONObject.optInt(CartConstant.KEY_SKU_WEIGHT);
                bVar.f12762c = optJSONObject.optString("action");
                arrayList.add(bVar);
            }
            Collections.sort(arrayList, new a(this));
            for (int i3 = 0; i3 < length; i3++) {
                b bVar2 = (b) arrayList.get(i3);
                bVar2.d = a(bVar2.a);
                if (bVar2.d) {
                    this.a = bVar2.f12762c;
                    this.b = bVar2.a;
                    return;
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void f(String str) {
        this.f12758c = str;
    }

    public void g(String str) {
        this.f12761g = str;
    }

    public void h(String str) {
        this.f12760f = str;
    }

    public void i(String str) {
        this.d = str;
    }

    public void j(String str) {
        this.f12759e = str;
    }

    private String a(String str, int i2) {
        return str.substring(i2);
    }

    private void a(Stack<String> stack, Stack<String> stack2) {
        String pop = stack2.pop();
        String pop2 = stack.pop();
        String pop3 = stack.pop();
        if (pop.equals("||")) {
            stack.push(com.jingdong.jdma.h.a.e(pop2, pop3) + "");
        } else if (pop.equals("&&")) {
            stack.push(com.jingdong.jdma.h.a.k(pop2, pop3) + "");
        }
    }

    public String b() {
        return this.b;
    }

    private int a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.matches("(\\d+\\.?)+") || !str2.matches("(\\d+\\.?)+")) {
            return -2;
        }
        int i2 = 0;
        if (str.equals(str2)) {
            return 0;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        int i3 = 0;
        while (true) {
            if (i3 >= min) {
                break;
            } else if (Integer.parseInt(split[i3]) > Integer.parseInt(split2[i3])) {
                i2 = 1;
                break;
            } else if (Integer.parseInt(split[i3]) < Integer.parseInt(split2[i3])) {
                i2 = -1;
                break;
            } else {
                i3++;
            }
        }
        return (min == 0 || i2 != 0) ? i2 : split.length > split2.length ? 1 : -1;
    }

    public String a() {
        return this.a;
    }
}
