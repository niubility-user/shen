package com.jingdong.jdsdk.db;

import android.content.res.XmlResourceParser;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jddb.R;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes14.dex */
public class a {
    private static a b;
    private int a;

    private a() {
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                a aVar2 = new a();
                b = aVar2;
                aVar2.c(R.xml.db_tables);
            }
            aVar = b;
        }
        return aVar;
    }

    public List<String> b() {
        ArrayList arrayList = new ArrayList();
        XmlResourceParser xml = JdSdk.getInstance().getApplication().getResources().getXml(this.a);
        try {
            try {
                try {
                    for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                        if (eventType == 2 && "table".equals(xml.getName())) {
                            arrayList.add(xml.getAttributeValue(0));
                        }
                    }
                } catch (XmlPullParserException e2) {
                    OKLog.e("JdTableList", e2);
                }
            } catch (IOException e3) {
                OKLog.e("JdTableList", e3);
            }
            return arrayList;
        } finally {
            xml.close();
        }
    }

    public void c(int i2) {
        this.a = i2;
    }
}
