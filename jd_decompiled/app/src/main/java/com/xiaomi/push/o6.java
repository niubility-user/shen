package com.xiaomi.push;

import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.xiaomi.push.e6;
import com.xiaomi.push.i6;
import com.xiaomi.push.k6;
import com.xiaomi.push.service.i0;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes11.dex */
public class o6 {
    private static XmlPullParser a;

    public static d6 a(String str, String str2, XmlPullParser xmlPullParser) {
        Object b = n6.a().b(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, "xm:chat");
        if (b == null || !(b instanceof com.xiaomi.push.service.i2)) {
            return null;
        }
        return ((com.xiaomi.push.service.i2) b).c(xmlPullParser);
    }

    public static e6 b(XmlPullParser xmlPullParser, o5 o5Var) {
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        String attributeValue2 = xmlPullParser.getAttributeValue("", RemoteMessageConst.TO);
        String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
        String attributeValue4 = xmlPullParser.getAttributeValue("", "chid");
        e6.a a2 = e6.a.a(xmlPullParser.getAttributeValue("", "type"));
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (int i2 = 0; i2 < xmlPullParser.getAttributeCount(); i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            hashMap.put(attributeName, xmlPullParser.getAttributeValue("", attributeName));
        }
        e6 e6Var = null;
        k6 k6Var = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("error")) {
                    k6Var = f(xmlPullParser);
                } else {
                    e6Var = new e6();
                    e6Var.h(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("iq")) {
                z = true;
            }
        }
        if (e6Var == null) {
            if (e6.a.b == a2 || e6.a.f18571c == a2) {
                p6 p6Var = new p6();
                p6Var.n(attributeValue);
                p6Var.r(attributeValue3);
                p6Var.t(attributeValue2);
                p6Var.z(e6.a.f18572e);
                p6Var.p(attributeValue4);
                p6Var.i(new k6(k6.a.b));
                o5Var.l(p6Var);
                g.j.a.a.a.c.D("iq usage error. send packet in packet parser.");
                return null;
            }
            e6Var = new q6();
        }
        e6Var.n(attributeValue);
        e6Var.r(attributeValue2);
        e6Var.p(attributeValue4);
        e6Var.t(attributeValue3);
        e6Var.z(a2);
        e6Var.i(k6Var);
        e6Var.A(hashMap);
        return e6Var;
    }

    public static g6 c(XmlPullParser xmlPullParser) {
        String str;
        boolean z = false;
        String str2 = null;
        if ("1".equals(xmlPullParser.getAttributeValue("", "s"))) {
            String attributeValue = xmlPullParser.getAttributeValue("", "chid");
            String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
            String attributeValue3 = xmlPullParser.getAttributeValue("", "from");
            String attributeValue4 = xmlPullParser.getAttributeValue("", RemoteMessageConst.TO);
            String attributeValue5 = xmlPullParser.getAttributeValue("", "type");
            i0.b b = com.xiaomi.push.service.i0.c().b(attributeValue, attributeValue4);
            if (b == null) {
                b = com.xiaomi.push.service.i0.c().b(attributeValue, attributeValue3);
            }
            if (b != null) {
                g6 g6Var = null;
                while (!z) {
                    int next = xmlPullParser.next();
                    if (next == 2) {
                        if (!"s".equals(xmlPullParser.getName())) {
                            throw new a6("error while receiving a encrypted message with wrong format");
                        }
                        if (xmlPullParser.next() != 4) {
                            throw new a6("error while receiving a encrypted message with wrong format");
                        }
                        String text = xmlPullParser.getText();
                        if ("5".equals(attributeValue) || "6".equals(attributeValue)) {
                            f6 f6Var = new f6();
                            f6Var.p(attributeValue);
                            f6Var.D(true);
                            f6Var.t(attributeValue3);
                            f6Var.r(attributeValue4);
                            f6Var.n(attributeValue2);
                            f6Var.L(attributeValue5);
                            d6 d6Var = new d6("s", null, null, null);
                            d6Var.g(text);
                            f6Var.h(d6Var);
                            return f6Var;
                        }
                        h(com.xiaomi.push.service.r0.h(com.xiaomi.push.service.r0.g(b.f19101i, attributeValue2), text));
                        a.next();
                        g6Var = c(a);
                    } else if (next == 3 && xmlPullParser.getName().equals("message")) {
                        z = true;
                    }
                }
                if (g6Var != null) {
                    return g6Var;
                }
                throw new a6("error while receiving a encrypted message with wrong format");
            }
            throw new a6("the channel id is wrong while receiving a encrypted message");
        }
        f6 f6Var2 = new f6();
        String attributeValue6 = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue6 == null) {
            attributeValue6 = "ID_NOT_AVAILABLE";
        }
        f6Var2.n(attributeValue6);
        f6Var2.r(xmlPullParser.getAttributeValue("", RemoteMessageConst.TO));
        f6Var2.t(xmlPullParser.getAttributeValue("", "from"));
        f6Var2.p(xmlPullParser.getAttributeValue("", "chid"));
        f6Var2.y(xmlPullParser.getAttributeValue("", "appid"));
        try {
            str = xmlPullParser.getAttributeValue("", "transient");
        } catch (Exception unused) {
            str = null;
        }
        try {
            String attributeValue7 = xmlPullParser.getAttributeValue("", "seq");
            if (!TextUtils.isEmpty(attributeValue7)) {
                f6Var2.C(attributeValue7);
            }
        } catch (Exception unused2) {
        }
        try {
            String attributeValue8 = xmlPullParser.getAttributeValue("", "mseq");
            if (!TextUtils.isEmpty(attributeValue8)) {
                f6Var2.F(attributeValue8);
            }
        } catch (Exception unused3) {
        }
        try {
            String attributeValue9 = xmlPullParser.getAttributeValue("", "fseq");
            if (!TextUtils.isEmpty(attributeValue9)) {
                f6Var2.H(attributeValue9);
            }
        } catch (Exception unused4) {
        }
        try {
            String attributeValue10 = xmlPullParser.getAttributeValue("", "status");
            if (!TextUtils.isEmpty(attributeValue10)) {
                f6Var2.J(attributeValue10);
            }
        } catch (Exception unused5) {
        }
        f6Var2.A(!TextUtils.isEmpty(str) && str.equalsIgnoreCase(DYConstants.DY_TRUE));
        f6Var2.L(xmlPullParser.getAttributeValue("", "type"));
        String i2 = i(xmlPullParser);
        if (i2 == null || "".equals(i2.trim())) {
            g6.x();
        } else {
            f6Var2.R(i2);
        }
        while (!z) {
            int next2 = xmlPullParser.next();
            if (next2 == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (TextUtils.isEmpty(namespace)) {
                    namespace = "xm";
                }
                if (name.equals("subject")) {
                    i(xmlPullParser);
                    f6Var2.N(g(xmlPullParser));
                } else if (name.equals("body")) {
                    String attributeValue11 = xmlPullParser.getAttributeValue("", "encode");
                    String g2 = g(xmlPullParser);
                    if (TextUtils.isEmpty(attributeValue11)) {
                        f6Var2.P(g2);
                    } else {
                        f6Var2.z(g2, attributeValue11);
                    }
                } else if (name.equals("thread")) {
                    if (str2 == null) {
                        str2 = xmlPullParser.nextText();
                    }
                } else if (name.equals("error")) {
                    f6Var2.i(f(xmlPullParser));
                } else {
                    f6Var2.h(a(name, namespace, xmlPullParser));
                }
            } else if (next2 == 3 && xmlPullParser.getName().equals("message")) {
                z = true;
            }
        }
        f6Var2.Q(str2);
        return f6Var2;
    }

    public static i6 d(XmlPullParser xmlPullParser) {
        i6.b bVar = i6.b.available;
        String attributeValue = xmlPullParser.getAttributeValue("", "type");
        if (attributeValue != null && !attributeValue.equals("")) {
            try {
                bVar = i6.b.valueOf(attributeValue);
            } catch (IllegalArgumentException unused) {
                System.err.println("Found invalid presence type " + attributeValue);
            }
        }
        i6 i6Var = new i6(bVar);
        i6Var.r(xmlPullParser.getAttributeValue("", RemoteMessageConst.TO));
        i6Var.t(xmlPullParser.getAttributeValue("", "from"));
        i6Var.p(xmlPullParser.getAttributeValue("", "chid"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "id");
        if (attributeValue2 == null) {
            attributeValue2 = "ID_NOT_AVAILABLE";
        }
        i6Var.n(attributeValue2);
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                String name = xmlPullParser.getName();
                String namespace = xmlPullParser.getNamespace();
                if (name.equals("status")) {
                    i6Var.B(xmlPullParser.nextText());
                } else if (name.equals(RemoteMessageConst.Notification.PRIORITY)) {
                    try {
                        i6Var.y(Integer.parseInt(xmlPullParser.nextText()));
                    } catch (NumberFormatException unused2) {
                    } catch (IllegalArgumentException unused3) {
                        i6Var.y(0);
                    }
                } else if (name.equals("show")) {
                    String nextText = xmlPullParser.nextText();
                    try {
                        i6Var.z(i6.a.valueOf(nextText));
                    } catch (IllegalArgumentException unused4) {
                        System.err.println("Found invalid presence mode " + nextText);
                    }
                } else if (name.equals("error")) {
                    i6Var.i(f(xmlPullParser));
                } else {
                    i6Var.h(a(name, namespace, xmlPullParser));
                }
            } else if (next == 3 && xmlPullParser.getName().equals("presence")) {
                z = true;
            }
        }
        return i6Var;
    }

    public static j6 e(XmlPullParser xmlPullParser) {
        j6 j6Var = null;
        boolean z = false;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                j6Var = new j6(xmlPullParser.getName());
            } else if (next == 3 && xmlPullParser.getName().equals("error")) {
                z = true;
            }
        }
        return j6Var;
    }

    public static k6 f(XmlPullParser xmlPullParser) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        String str = "-1";
        String str2 = null;
        String str3 = null;
        for (int i2 = 0; i2 < xmlPullParser.getAttributeCount(); i2++) {
            if (xmlPullParser.getAttributeName(i2).equals("code")) {
                str = xmlPullParser.getAttributeValue("", "code");
            }
            if (xmlPullParser.getAttributeName(i2).equals("type")) {
                str3 = xmlPullParser.getAttributeValue("", "type");
            }
            if (xmlPullParser.getAttributeName(i2).equals("reason")) {
                str2 = xmlPullParser.getAttributeValue("", "reason");
            }
        }
        String str4 = null;
        String str5 = null;
        while (!z) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("text")) {
                    str5 = xmlPullParser.nextText();
                } else {
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals(namespace)) {
                        str4 = name;
                    } else {
                        arrayList.add(a(name, namespace, xmlPullParser));
                    }
                }
            } else if (next == 3) {
                if (xmlPullParser.getName().equals("error")) {
                    z = true;
                }
            } else if (next == 4) {
                str5 = xmlPullParser.getText();
            }
        }
        return new k6(Integer.parseInt(str), str3 == null ? "cancel" : str3, str2, str4, str5, arrayList);
    }

    private static String g(XmlPullParser xmlPullParser) {
        int depth = xmlPullParser.getDepth();
        String str = "";
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return str;
            }
            str = str + xmlPullParser.getText();
        }
    }

    private static void h(byte[] bArr) {
        if (a == null) {
            try {
                XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                a = newPullParser;
                newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            } catch (XmlPullParserException e2) {
                e2.printStackTrace();
            }
        }
        a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
    }

    private static String i(XmlPullParser xmlPullParser) {
        for (int i2 = 0; i2 < xmlPullParser.getAttributeCount(); i2++) {
            String attributeName = xmlPullParser.getAttributeName(i2);
            if ("xml:lang".equals(attributeName) || ("lang".equals(attributeName) && "xml".equals(xmlPullParser.getAttributePrefix(i2)))) {
                return xmlPullParser.getAttributeValue(i2);
            }
        }
        return null;
    }
}
