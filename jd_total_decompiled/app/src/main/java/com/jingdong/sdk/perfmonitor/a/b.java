package com.jingdong.sdk.perfmonitor.a;

import androidx.annotation.NonNull;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b {
    public long a;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public long f15282c;
    public long d;

    /* renamed from: e  reason: collision with root package name */
    public long f15283e;

    /* renamed from: f  reason: collision with root package name */
    public long f15284f;

    /* renamed from: g  reason: collision with root package name */
    public String f15285g;

    /* renamed from: h  reason: collision with root package name */
    public JSONObject f15286h;

    /* renamed from: i  reason: collision with root package name */
    public ConcurrentHashMap<String, d> f15287i = new ConcurrentHashMap<>();

    /* renamed from: j  reason: collision with root package name */
    public Map<String, Long> f15288j = new ConcurrentHashMap();

    /* renamed from: k  reason: collision with root package name */
    public String f15289k;

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nstart:");
        sb.append(this.f15283e);
        sb.append("\nstop:");
        sb.append(this.f15284f);
        sb.append("\nonCreate:");
        sb.append(this.a);
        sb.append("\nonStart:");
        sb.append(this.b);
        sb.append("\nonResume:");
        sb.append(this.f15282c);
        sb.append("\nextraTime:");
        sb.append(this.f15288j);
        Iterator<String> it = this.f15287i.keySet().iterator();
        while (it.hasNext()) {
            d dVar = this.f15287i.get(it.next());
            if (dVar != null) {
                sb.append("\nnetwork-");
                sb.append(dVar.a);
                sb.append(":");
                sb.append(dVar.b);
                sb.append("-");
                sb.append(dVar.f15300c);
            }
        }
        sb.append("\nfirst render time:");
        sb.append(this.d);
        return sb.toString();
    }
}
