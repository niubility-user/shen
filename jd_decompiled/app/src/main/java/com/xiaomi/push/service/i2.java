package com.xiaomi.push.service;

import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.xiaomi.push.d6;
import com.xiaomi.push.m6;
import com.xiaomi.push.n6;
import com.xiaomi.push.r6;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes11.dex */
public class i2 implements m6 {
    public static d6 a(XmlPullParser xmlPullParser) {
        String[] strArr;
        String[] strArr2;
        String str;
        ArrayList arrayList;
        if (xmlPullParser.getEventType() != 2) {
            return null;
        }
        String name = xmlPullParser.getName();
        String namespace = xmlPullParser.getNamespace();
        if (xmlPullParser.getAttributeCount() > 0) {
            String[] strArr3 = new String[xmlPullParser.getAttributeCount()];
            String[] strArr4 = new String[xmlPullParser.getAttributeCount()];
            for (int i2 = 0; i2 < xmlPullParser.getAttributeCount(); i2++) {
                strArr3[i2] = xmlPullParser.getAttributeName(i2);
                strArr4[i2] = r6.e(xmlPullParser.getAttributeValue(i2));
            }
            strArr = strArr3;
            str = null;
            arrayList = null;
            strArr2 = strArr4;
        } else {
            strArr = null;
            strArr2 = null;
            str = null;
            arrayList = null;
        }
        while (true) {
            int next = xmlPullParser.next();
            if (next == 3) {
                return new d6(name, namespace, strArr, strArr2, str, arrayList);
            }
            if (next == 4) {
                str = xmlPullParser.getText().trim();
            } else if (next == 2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                d6 a = a(xmlPullParser);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        }
    }

    public void b() {
        n6.a().e(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL, "xm:chat", this);
    }

    public d6 c(XmlPullParser xmlPullParser) {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1 && eventType != 2) {
            eventType = xmlPullParser.next();
        }
        if (eventType == 2) {
            return a(xmlPullParser);
        }
        return null;
    }
}
