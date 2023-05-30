package com.xiaomi.push;

import android.os.Bundle;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class e6 extends g6 {
    private a p;
    private final Map<String, String> q;

    /* loaded from: classes11.dex */
    public static class a {
        public static final a b = new a(IMantoServerRequester.GET);

        /* renamed from: c  reason: collision with root package name */
        public static final a f18571c = new a("set");
        public static final a d = new a("result");

        /* renamed from: e  reason: collision with root package name */
        public static final a f18572e = new a("error");

        /* renamed from: f  reason: collision with root package name */
        public static final a f18573f = new a("command");
        private String a;

        private a(String str) {
            this.a = str;
        }

        public static a a(String str) {
            if (str == null) {
                return null;
            }
            String lowerCase = str.toLowerCase();
            a aVar = b;
            if (aVar.toString().equals(lowerCase)) {
                return aVar;
            }
            a aVar2 = f18571c;
            if (aVar2.toString().equals(lowerCase)) {
                return aVar2;
            }
            a aVar3 = f18572e;
            if (aVar3.toString().equals(lowerCase)) {
                return aVar3;
            }
            a aVar4 = d;
            if (aVar4.toString().equals(lowerCase)) {
                return aVar4;
            }
            a aVar5 = f18573f;
            if (aVar5.toString().equals(lowerCase)) {
                return aVar5;
            }
            return null;
        }

        public String toString() {
            return this.a;
        }
    }

    public e6() {
        this.p = a.b;
        this.q = new HashMap();
    }

    public e6(Bundle bundle) {
        super(bundle);
        this.p = a.b;
        this.q = new HashMap();
        if (bundle.containsKey("ext_iq_type")) {
            this.p = a.a(bundle.getString("ext_iq_type"));
        }
    }

    public synchronized void A(Map<String, String> map) {
        this.q.putAll(map);
    }

    public String B() {
        return null;
    }

    @Override // com.xiaomi.push.g6
    public Bundle a() {
        Bundle a2 = super.a();
        a aVar = this.p;
        if (aVar != null) {
            a2.putString("ext_iq_type", aVar.toString());
        }
        return a2;
    }

    @Override // com.xiaomi.push.g6
    public String f() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("<iq ");
        if (l() != null) {
            sb.append("id=\"" + l() + "\" ");
        }
        if (o() != null) {
            sb.append("to=\"");
            sb.append(r6.b(o()));
            sb.append("\" ");
        }
        if (q() != null) {
            sb.append("from=\"");
            sb.append(r6.b(q()));
            sb.append("\" ");
        }
        if (m() != null) {
            sb.append("chid=\"");
            sb.append(r6.b(m()));
            sb.append("\" ");
        }
        for (Map.Entry<String, String> entry : this.q.entrySet()) {
            sb.append(r6.b(entry.getKey()));
            sb.append("=\"");
            sb.append(r6.b(entry.getValue()));
            sb.append("\" ");
        }
        if (this.p == null) {
            str = "type=\"get\">";
        } else {
            sb.append("type=\"");
            sb.append(y());
            str = "\">";
        }
        sb.append(str);
        String B = B();
        if (B != null) {
            sb.append(B);
        }
        sb.append(u());
        k6 d = d();
        if (d != null) {
            sb.append(d.b());
        }
        sb.append("</iq>");
        return sb.toString();
    }

    public a y() {
        return this.p;
    }

    public void z(a aVar) {
        if (aVar == null) {
            aVar = a.b;
        }
        this.p = aVar;
    }
}
