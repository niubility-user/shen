package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes11.dex */
public class d6 implements h6 {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String[] f18539c;
    private String[] d;

    /* renamed from: e  reason: collision with root package name */
    private String f18540e;

    /* renamed from: f  reason: collision with root package name */
    private List<d6> f18541f;

    public d6(String str, String str2, String[] strArr, String[] strArr2) {
        this.f18539c = null;
        this.d = null;
        this.f18541f = null;
        this.a = str;
        this.b = str2;
        this.f18539c = strArr;
        this.d = strArr2;
    }

    public d6(String str, String str2, String[] strArr, String[] strArr2, String str3, List<d6> list) {
        this.f18539c = null;
        this.d = null;
        this.f18541f = null;
        this.a = str;
        this.b = str2;
        this.f18539c = strArr;
        this.d = strArr2;
        this.f18540e = str3;
        this.f18541f = list;
    }

    public static d6 c(Bundle bundle) {
        ArrayList arrayList;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        int i2 = 0;
        for (String str : keySet) {
            strArr[i2] = str;
            strArr2[i2] = bundle2.getString(str);
            i2++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            ArrayList arrayList2 = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                arrayList2.add(c((Bundle) parcelable));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return new d6(string, string2, strArr, strArr2, string3, arrayList);
    }

    public static Parcelable[] h(List<d6> list) {
        return i((d6[]) list.toArray(new d6[list.size()]));
    }

    public static Parcelable[] i(d6[] d6VarArr) {
        if (d6VarArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[d6VarArr.length];
        for (int i2 = 0; i2 < d6VarArr.length; i2++) {
            parcelableArr[i2] = d6VarArr[i2].b();
        }
        return parcelableArr;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.a);
        bundle.putString("ext_ns", this.b);
        bundle.putString("ext_text", this.f18540e);
        Bundle bundle2 = new Bundle();
        String[] strArr = this.f18539c;
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            while (true) {
                String[] strArr2 = this.f18539c;
                if (i2 >= strArr2.length) {
                    break;
                }
                bundle2.putString(strArr2[i2], this.d[i2]);
                i2++;
            }
        }
        bundle.putBundle("attributes", bundle2);
        List<d6> list = this.f18541f;
        if (list != null && list.size() > 0) {
            bundle.putParcelableArray("children", h(this.f18541f));
        }
        return bundle;
    }

    public Parcelable b() {
        return a();
    }

    @Override // com.xiaomi.push.h6
    public String d() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.a);
        if (!TextUtils.isEmpty(this.b)) {
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append("xmlns=");
            sb.append("\"");
            sb.append(this.b);
            sb.append("\"");
        }
        String[] strArr = this.f18539c;
        if (strArr != null && strArr.length > 0) {
            for (int i2 = 0; i2 < this.f18539c.length; i2++) {
                if (!TextUtils.isEmpty(this.d[i2])) {
                    sb.append(LangUtils.SINGLE_SPACE);
                    sb.append(this.f18539c[i2]);
                    sb.append("=\"");
                    sb.append(r6.b(this.d[i2]));
                    sb.append("\"");
                }
            }
        }
        if (TextUtils.isEmpty(this.f18540e)) {
            List<d6> list = this.f18541f;
            if (list == null || list.size() <= 0) {
                sb.append("/>");
                return sb.toString();
            }
            sb.append(">");
            Iterator<d6> it = this.f18541f.iterator();
            while (it.hasNext()) {
                sb.append(it.next().d());
            }
        } else {
            sb.append(">");
            sb.append(this.f18540e);
        }
        sb.append("</");
        sb.append(this.a);
        sb.append(">");
        return sb.toString();
    }

    public String e() {
        return this.a;
    }

    public String f(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.f18539c == null) {
            return null;
        }
        int i2 = 0;
        while (true) {
            String[] strArr = this.f18539c;
            if (i2 >= strArr.length) {
                return null;
            }
            if (str.equals(strArr[i2])) {
                return this.d[i2];
            }
            i2++;
        }
    }

    public void g(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = r6.b(str);
        }
        this.f18540e = str;
    }

    public String j() {
        return this.b;
    }

    public String k() {
        return !TextUtils.isEmpty(this.f18540e) ? r6.e(this.f18540e) : this.f18540e;
    }

    public String toString() {
        return d();
    }
}
