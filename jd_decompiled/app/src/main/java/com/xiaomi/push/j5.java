package com.xiaomi.push;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* loaded from: classes11.dex */
public class j5 {
    private XmlPullParser a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j5() {
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            this.a = newPullParser;
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
        } catch (XmlPullParserException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public g6 a(byte[] bArr, o5 o5Var) {
        this.a.setInput(new InputStreamReader(new ByteArrayInputStream(bArr)));
        this.a.next();
        int eventType = this.a.getEventType();
        String name = this.a.getName();
        if (eventType == 2) {
            if (name.equals("message")) {
                return o6.c(this.a);
            }
            if (name.equals("iq")) {
                return o6.b(this.a, o5Var);
            }
            if (name.equals("presence")) {
                return o6.d(this.a);
            }
            if (this.a.getName().equals("stream")) {
                return null;
            }
            if (this.a.getName().equals("error")) {
                throw new a6(o6.e(this.a));
            }
            if (!this.a.getName().equals("warning")) {
                this.a.getName().equals(IExceptionHandler.DynamicExceptionData.TYPE_BIND);
                return null;
            }
            this.a.next();
            this.a.getName().equals("multi-login");
            return null;
        }
        return null;
    }
}
