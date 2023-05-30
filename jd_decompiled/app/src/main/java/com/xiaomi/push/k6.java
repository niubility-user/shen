package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class k6 {
    private int a;
    private String b;

    /* renamed from: c */
    private String f18795c;
    private String d;

    /* renamed from: e */
    private String f18796e;

    /* renamed from: f */
    private List<d6> f18797f;

    /* loaded from: classes11.dex */
    public static class a {
        public static final a b = new a("feature-not-implemented");
        private String a;

        public a(String str) {
            this.a = str;
        }

        public String toString() {
            return this.a;
        }
    }

    public k6(int i2, String str, String str2, String str3, String str4, List<d6> list) {
        this.f18797f = null;
        this.a = i2;
        this.b = str;
        this.d = str2;
        this.f18795c = str3;
        this.f18796e = str4;
        this.f18797f = list;
    }

    public k6(Bundle bundle) {
        this.f18797f = null;
        this.a = bundle.getInt("ext_err_code");
        if (bundle.containsKey("ext_err_type")) {
            this.b = bundle.getString("ext_err_type");
        }
        this.f18795c = bundle.getString("ext_err_cond");
        this.d = bundle.getString("ext_err_reason");
        this.f18796e = bundle.getString("ext_err_msg");
        Parcelable[] parcelableArray = bundle.getParcelableArray("ext_exts");
        if (parcelableArray != null) {
            this.f18797f = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                d6 c2 = d6.c((Bundle) parcelable);
                if (c2 != null) {
                    this.f18797f.add(c2);
                }
            }
        }
    }

    public k6(a aVar) {
        this.f18797f = null;
        d(aVar);
        this.f18796e = null;
    }

    private void d(a aVar) {
        this.f18795c = aVar.a;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        String str = this.b;
        if (str != null) {
            bundle.putString("ext_err_type", str);
        }
        bundle.putInt("ext_err_code", this.a);
        String str2 = this.d;
        if (str2 != null) {
            bundle.putString("ext_err_reason", str2);
        }
        String str3 = this.f18795c;
        if (str3 != null) {
            bundle.putString("ext_err_cond", str3);
        }
        String str4 = this.f18796e;
        if (str4 != null) {
            bundle.putString("ext_err_msg", str4);
        }
        List<d6> list = this.f18797f;
        if (list != null) {
            Bundle[] bundleArr = new Bundle[list.size()];
            int i2 = 0;
            Iterator<d6> it = this.f18797f.iterator();
            while (it.hasNext()) {
                Bundle a2 = it.next().a();
                if (a2 != null) {
                    bundleArr[i2] = a2;
                    i2++;
                }
            }
            bundle.putParcelableArray("ext_exts", bundleArr);
        }
        return bundle;
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("<error code=\"");
        sb.append(this.a);
        sb.append("\"");
        if (this.b != null) {
            sb.append(" type=\"");
            sb.append(this.b);
            sb.append("\"");
        }
        if (this.d != null) {
            sb.append(" reason=\"");
            sb.append(this.d);
            sb.append("\"");
        }
        sb.append(">");
        if (this.f18795c != null) {
            sb.append("<");
            sb.append(this.f18795c);
            sb.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.f18796e != null) {
            sb.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            sb.append(this.f18796e);
            sb.append("</text>");
        }
        Iterator<d6> it = c().iterator();
        while (it.hasNext()) {
            sb.append(it.next().d());
        }
        sb.append("</error>");
        return sb.toString();
    }

    public synchronized List<d6> c() {
        List<d6> list = this.f18797f;
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.f18795c;
        if (str != null) {
            sb.append(str);
        }
        sb.append("(");
        sb.append(this.a);
        sb.append(")");
        if (this.f18796e != null) {
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(this.f18796e);
        }
        return sb.toString();
    }
}
