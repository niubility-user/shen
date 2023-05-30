package com.xiaomi.push;

import android.os.Bundle;
import android.text.TextUtils;

/* loaded from: classes11.dex */
public class f6 extends g6 {
    private String A;
    private boolean B;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private boolean v;
    private String w;
    private String x;
    private String y;
    private String z;

    public f6() {
        this.p = null;
        this.q = null;
        this.v = false;
        this.x = "";
        this.y = "";
        this.z = "";
        this.A = "";
        this.B = false;
    }

    public f6(Bundle bundle) {
        super(bundle);
        this.p = null;
        this.q = null;
        this.v = false;
        this.x = "";
        this.y = "";
        this.z = "";
        this.A = "";
        this.B = false;
        this.p = bundle.getString("ext_msg_type");
        this.r = bundle.getString("ext_msg_lang");
        this.q = bundle.getString("ext_msg_thread");
        this.s = bundle.getString("ext_msg_sub");
        this.t = bundle.getString("ext_msg_body");
        this.u = bundle.getString("ext_body_encode");
        this.w = bundle.getString("ext_msg_appid");
        this.v = bundle.getBoolean("ext_msg_trans", false);
        this.B = bundle.getBoolean("ext_msg_encrypt", false);
        this.x = bundle.getString("ext_msg_seq");
        this.y = bundle.getString("ext_msg_mseq");
        this.z = bundle.getString("ext_msg_fseq");
        this.A = bundle.getString("ext_msg_status");
    }

    public void A(boolean z) {
        this.v = z;
    }

    public String B() {
        return this.p;
    }

    public void C(String str) {
        this.x = str;
    }

    public void D(boolean z) {
        this.B = z;
    }

    public String E() {
        return this.w;
    }

    public void F(String str) {
        this.y = str;
    }

    public String G() {
        return this.x;
    }

    public void H(String str) {
        this.z = str;
    }

    public String I() {
        return this.y;
    }

    public void J(String str) {
        this.A = str;
    }

    public String K() {
        return this.z;
    }

    public void L(String str) {
        this.p = str;
    }

    public String M() {
        return this.A;
    }

    public void N(String str) {
        this.s = str;
    }

    public String O() {
        return this.r;
    }

    public void P(String str) {
        this.t = str;
    }

    public void Q(String str) {
        this.q = str;
    }

    public void R(String str) {
        this.r = str;
    }

    @Override // com.xiaomi.push.g6
    public Bundle a() {
        Bundle a = super.a();
        if (!TextUtils.isEmpty(this.p)) {
            a.putString("ext_msg_type", this.p);
        }
        String str = this.r;
        if (str != null) {
            a.putString("ext_msg_lang", str);
        }
        String str2 = this.s;
        if (str2 != null) {
            a.putString("ext_msg_sub", str2);
        }
        String str3 = this.t;
        if (str3 != null) {
            a.putString("ext_msg_body", str3);
        }
        if (!TextUtils.isEmpty(this.u)) {
            a.putString("ext_body_encode", this.u);
        }
        String str4 = this.q;
        if (str4 != null) {
            a.putString("ext_msg_thread", str4);
        }
        String str5 = this.w;
        if (str5 != null) {
            a.putString("ext_msg_appid", str5);
        }
        if (this.v) {
            a.putBoolean("ext_msg_trans", true);
        }
        if (!TextUtils.isEmpty(this.x)) {
            a.putString("ext_msg_seq", this.x);
        }
        if (!TextUtils.isEmpty(this.y)) {
            a.putString("ext_msg_mseq", this.y);
        }
        if (!TextUtils.isEmpty(this.z)) {
            a.putString("ext_msg_fseq", this.z);
        }
        if (this.B) {
            a.putBoolean("ext_msg_encrypt", true);
        }
        if (!TextUtils.isEmpty(this.A)) {
            a.putString("ext_msg_status", this.A);
        }
        return a;
    }

    @Override // com.xiaomi.push.g6
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || f6.class != obj.getClass()) {
            return false;
        }
        f6 f6Var = (f6) obj;
        if (super.equals(f6Var)) {
            String str = this.t;
            if (str == null ? f6Var.t == null : str.equals(f6Var.t)) {
                String str2 = this.r;
                if (str2 == null ? f6Var.r == null : str2.equals(f6Var.r)) {
                    String str3 = this.s;
                    if (str3 == null ? f6Var.s == null : str3.equals(f6Var.s)) {
                        String str4 = this.q;
                        if (str4 == null ? f6Var.q == null : str4.equals(f6Var.q)) {
                            return this.p == f6Var.p;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // com.xiaomi.push.g6
    public String f() {
        k6 d;
        StringBuilder sb = new StringBuilder();
        sb.append("<message");
        if (w() != null) {
            sb.append(" xmlns=\"");
            sb.append(w());
            sb.append("\"");
        }
        if (this.r != null) {
            sb.append(" xml:lang=\"");
            sb.append(O());
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
        if (!TextUtils.isEmpty(G())) {
            sb.append(" seq=\"");
            sb.append(G());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(I())) {
            sb.append(" mseq=\"");
            sb.append(I());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(K())) {
            sb.append(" fseq=\"");
            sb.append(K());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(M())) {
            sb.append(" status=\"");
            sb.append(M());
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
        if (this.v) {
            sb.append(" transient=\"true\"");
        }
        if (!TextUtils.isEmpty(this.w)) {
            sb.append(" appid=\"");
            sb.append(E());
            sb.append("\"");
        }
        if (!TextUtils.isEmpty(this.p)) {
            sb.append(" type=\"");
            sb.append(this.p);
            sb.append("\"");
        }
        if (this.B) {
            sb.append(" s=\"1\"");
        }
        sb.append(">");
        if (this.s != null) {
            sb.append("<subject>");
            sb.append(r6.b(this.s));
            sb.append("</subject>");
        }
        if (this.t != null) {
            sb.append("<body");
            if (!TextUtils.isEmpty(this.u)) {
                sb.append(" encode=\"");
                sb.append(this.u);
                sb.append("\"");
            }
            sb.append(">");
            sb.append(r6.b(this.t));
            sb.append("</body>");
        }
        if (this.q != null) {
            sb.append("<thread>");
            sb.append(this.q);
            sb.append("</thread>");
        }
        if ("error".equalsIgnoreCase(this.p) && (d = d()) != null) {
            sb.append(d.b());
        }
        sb.append(u());
        sb.append("</message>");
        return sb.toString();
    }

    @Override // com.xiaomi.push.g6
    public int hashCode() {
        String str = this.p;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.t;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.q;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.r;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.s;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public void y(String str) {
        this.w = str;
    }

    public void z(String str, String str2) {
        this.t = str;
        this.u = str2;
    }
}
