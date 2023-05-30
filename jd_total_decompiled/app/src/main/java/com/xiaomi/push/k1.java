package com.xiaomi.push;

import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes11.dex */
class k1 implements Comparable<k1> {

    /* renamed from: g  reason: collision with root package name */
    private final LinkedList<a1> f18789g;

    /* renamed from: h  reason: collision with root package name */
    String f18790h;

    /* renamed from: i  reason: collision with root package name */
    private long f18791i;

    /* renamed from: j  reason: collision with root package name */
    protected int f18792j;

    public k1() {
        this(null, 0);
    }

    public k1(String str) {
        this(str, 0);
    }

    public k1(String str, int i2) {
        this.f18789g = new LinkedList<>();
        this.f18791i = 0L;
        this.f18790h = str;
        this.f18792j = i2;
    }

    @Override // java.lang.Comparable
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public int compareTo(k1 k1Var) {
        if (k1Var == null) {
            return 1;
        }
        return k1Var.f18792j - this.f18792j;
    }

    public synchronized k1 b(JSONObject jSONObject) {
        this.f18791i = jSONObject.getLong(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP);
        this.f18792j = jSONObject.getInt("wt");
        this.f18790h = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            LinkedList<a1> linkedList = this.f18789g;
            a1 a1Var = new a1();
            a1Var.b(jSONObject2);
            linkedList.add(a1Var);
        }
        return this;
    }

    public synchronized JSONObject c() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put(PushConstants.PUSH_NOTIFICATION_CREATE_TIMES_TAMP, this.f18791i);
        jSONObject.put("wt", this.f18792j);
        jSONObject.put("host", this.f18790h);
        JSONArray jSONArray = new JSONArray();
        Iterator<a1> it = this.f18789g.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().c());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void d(a1 a1Var) {
        if (a1Var != null) {
            this.f18789g.add(a1Var);
            int a = a1Var.a();
            if (a > 0) {
                this.f18792j += a1Var.a();
            } else {
                int i2 = 0;
                for (int size = this.f18789g.size() - 1; size >= 0 && this.f18789g.get(size).a() < 0; size--) {
                    i2++;
                }
                this.f18792j += a * i2;
            }
            if (this.f18789g.size() > 30) {
                this.f18792j -= this.f18789g.remove().a();
            }
        }
    }

    public String toString() {
        return this.f18790h + ":" + this.f18792j;
    }
}
