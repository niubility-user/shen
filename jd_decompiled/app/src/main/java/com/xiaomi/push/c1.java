package com.xiaomi.push;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c1 {
    private String a;
    private final ArrayList<b1> b = new ArrayList<>();

    public c1() {
    }

    public c1(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.a = str;
    }

    public synchronized b1 a() {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            b1 b1Var = this.b.get(size);
            if (b1Var.p()) {
                f1.c().l(b1Var.b());
                return b1Var;
            }
        }
        return null;
    }

    public synchronized c1 b(JSONObject jSONObject) {
        this.a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            ArrayList<b1> arrayList = this.b;
            b1 b1Var = new b1(this.a);
            b1Var.a(jSONArray.getJSONObject(i2));
            arrayList.add(b1Var);
        }
        return this;
    }

    public String c() {
        return this.a;
    }

    public ArrayList<b1> d() {
        return this.b;
    }

    public synchronized JSONObject e() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("host", this.a);
        JSONArray jSONArray = new JSONArray();
        Iterator<b1> it = this.b.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().f());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public synchronized void f(b1 b1Var) {
        int i2 = 0;
        while (true) {
            if (i2 >= this.b.size()) {
                break;
            } else if (this.b.get(i2).q(b1Var)) {
                this.b.set(i2, b1Var);
                break;
            } else {
                i2++;
            }
        }
        if (i2 >= this.b.size()) {
            this.b.add(b1Var);
        }
    }

    public synchronized void g(boolean z) {
        ArrayList<b1> arrayList;
        for (int size = this.b.size() - 1; size >= 0; size--) {
            b1 b1Var = this.b.get(size);
            if (z) {
                if (b1Var.w()) {
                    arrayList = this.b;
                    arrayList.remove(size);
                }
            } else if (!b1Var.u()) {
                arrayList = this.b;
                arrayList.remove(size);
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        Iterator<b1> it = this.b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }
}
