package com.xiaomi.push;

import android.os.Bundle;

/* loaded from: classes11.dex */
public class i6 extends g6 {
    private b p;
    private String q;
    private int r;
    private a s;

    /* loaded from: classes11.dex */
    public enum a {
        chat,
        available,
        away,
        xa,
        dnd
    }

    /* loaded from: classes11.dex */
    public enum b {
        available,
        unavailable,
        subscribe,
        subscribed,
        unsubscribe,
        unsubscribed,
        error,
        probe
    }

    public i6(Bundle bundle) {
        super(bundle);
        this.p = b.available;
        this.q = null;
        this.r = Integer.MIN_VALUE;
        this.s = null;
        if (bundle.containsKey("ext_pres_type")) {
            this.p = b.valueOf(bundle.getString("ext_pres_type"));
        }
        if (bundle.containsKey("ext_pres_status")) {
            this.q = bundle.getString("ext_pres_status");
        }
        if (bundle.containsKey("ext_pres_prio")) {
            this.r = bundle.getInt("ext_pres_prio");
        }
        if (bundle.containsKey("ext_pres_mode")) {
            this.s = a.valueOf(bundle.getString("ext_pres_mode"));
        }
    }

    public i6(b bVar) {
        this.p = b.available;
        this.q = null;
        this.r = Integer.MIN_VALUE;
        this.s = null;
        A(bVar);
    }

    public void A(b bVar) {
        if (bVar == null) {
            throw new NullPointerException("Type cannot be null");
        }
        this.p = bVar;
    }

    public void B(String str) {
        this.q = str;
    }

    @Override // com.xiaomi.push.g6
    public Bundle a() {
        Bundle a2 = super.a();
        b bVar = this.p;
        if (bVar != null) {
            a2.putString("ext_pres_type", bVar.toString());
        }
        String str = this.q;
        if (str != null) {
            a2.putString("ext_pres_status", str);
        }
        int i2 = this.r;
        if (i2 != Integer.MIN_VALUE) {
            a2.putInt("ext_pres_prio", i2);
        }
        a aVar = this.s;
        if (aVar != null && aVar != a.available) {
            a2.putString("ext_pres_mode", aVar.toString());
        }
        return a2;
    }

    @Override // com.xiaomi.push.g6
    public String f() {
        StringBuilder sb = new StringBuilder();
        sb.append("<presence");
        if (w() != null) {
            sb.append(" xmlns=\"");
            sb.append(w());
            sb.append("\"");
        }
        if (l() != null) {
            sb.append(" id=\"");
            sb.append(l());
            sb.append("\"");
        }
        if (o() != null) {
            sb.append(" to=\"");
            sb.append(r6.b(o()));
            sb.append("\"");
        }
        if (q() != null) {
            sb.append(" from=\"");
            sb.append(r6.b(q()));
            sb.append("\"");
        }
        if (m() != null) {
            sb.append(" chid=\"");
            sb.append(r6.b(m()));
            sb.append("\"");
        }
        if (this.p != null) {
            sb.append(" type=\"");
            sb.append(this.p);
            sb.append("\"");
        }
        sb.append(">");
        if (this.q != null) {
            sb.append("<status>");
            sb.append(r6.b(this.q));
            sb.append("</status>");
        }
        if (this.r != Integer.MIN_VALUE) {
            sb.append("<priority>");
            sb.append(this.r);
            sb.append("</priority>");
        }
        a aVar = this.s;
        if (aVar != null && aVar != a.available) {
            sb.append("<show>");
            sb.append(this.s);
            sb.append("</show>");
        }
        sb.append(u());
        k6 d = d();
        if (d != null) {
            sb.append(d.b());
        }
        sb.append("</presence>");
        return sb.toString();
    }

    public void y(int i2) {
        if (i2 >= -128 && i2 <= 128) {
            this.r = i2;
            return;
        }
        throw new IllegalArgumentException("Priority value " + i2 + " is not valid. Valid range is -128 through 128.");
    }

    public void z(a aVar) {
        this.s = aVar;
    }
}
