package com.xiaomi.push;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes11.dex */
public class n6 {

    /* renamed from: c  reason: collision with root package name */
    private static n6 f18880c;
    private Map<String, Object> a = new ConcurrentHashMap();
    private Map<String, Object> b = new ConcurrentHashMap();

    private n6() {
        d();
    }

    public static synchronized n6 a() {
        n6 n6Var;
        synchronized (n6.class) {
            if (f18880c == null) {
                f18880c = new n6();
            }
            n6Var = f18880c;
        }
        return n6Var;
    }

    private String c(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(str);
        sb.append("/>");
        if (str != null) {
            sb.append("<");
            sb.append(str2);
            sb.append("/>");
        }
        return sb.toString();
    }

    private ClassLoader[] f() {
        ClassLoader[] classLoaderArr = {n6.class.getClassLoader(), Thread.currentThread().getContextClassLoader()};
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 2; i2++) {
            ClassLoader classLoader = classLoaderArr[i2];
            if (classLoader != null) {
                arrayList.add(classLoader);
            }
        }
        return (ClassLoader[]) arrayList.toArray(new ClassLoader[arrayList.size()]);
    }

    public Object b(String str, String str2) {
        return this.a.get(c(str, str2));
    }

    protected void d() {
        Map<String, Object> map;
        Object obj;
        Map<String, Object> map2;
        Object obj2;
        try {
            for (ClassLoader classLoader : f()) {
                Enumeration<URL> resources = classLoader.getResources("META-INF/smack.providers");
                while (resources.hasMoreElements()) {
                    InputStream openStream = resources.nextElement().openStream();
                    XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
                    newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
                    newPullParser.setInput(openStream, "UTF-8");
                    int eventType = newPullParser.getEventType();
                    do {
                        if (eventType == 2) {
                            if (newPullParser.getName().equals("iqProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                String nextText = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText2 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText3 = newPullParser.nextText();
                                String c2 = c(nextText, nextText2);
                                if (!this.b.containsKey(c2)) {
                                    try {
                                        Class<?> cls = Class.forName(nextText3);
                                        if (l6.class.isAssignableFrom(cls)) {
                                            map2 = this.b;
                                            obj2 = cls.newInstance();
                                        } else if (e6.class.isAssignableFrom(cls)) {
                                            map2 = this.b;
                                            obj2 = cls;
                                        }
                                        map2.put(c2, obj2);
                                    } catch (ClassNotFoundException e2) {
                                        e = e2;
                                        e.printStackTrace();
                                        eventType = newPullParser.next();
                                    }
                                }
                            } else if (newPullParser.getName().equals("extensionProvider")) {
                                newPullParser.next();
                                newPullParser.next();
                                String nextText4 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText5 = newPullParser.nextText();
                                newPullParser.next();
                                newPullParser.next();
                                String nextText6 = newPullParser.nextText();
                                String c3 = c(nextText4, nextText5);
                                if (!this.a.containsKey(c3)) {
                                    try {
                                        Class<?> cls2 = Class.forName(nextText6);
                                        if (m6.class.isAssignableFrom(cls2)) {
                                            map = this.a;
                                            obj = cls2.newInstance();
                                        } else if (h6.class.isAssignableFrom(cls2)) {
                                            map = this.a;
                                            obj = cls2;
                                        }
                                        map.put(c3, obj);
                                    } catch (ClassNotFoundException e3) {
                                        e = e3;
                                        e.printStackTrace();
                                        eventType = newPullParser.next();
                                    }
                                }
                            }
                        }
                        eventType = newPullParser.next();
                    } while (eventType != 1);
                    openStream.close();
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void e(String str, String str2, Object obj) {
        if (!(obj instanceof m6) && !(obj instanceof Class)) {
            throw new IllegalArgumentException("Provider must be a PacketExtensionProvider or a Class instance.");
        }
        this.a.put(c(str, str2), obj);
    }
}
