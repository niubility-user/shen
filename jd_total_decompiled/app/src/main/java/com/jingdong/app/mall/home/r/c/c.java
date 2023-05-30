package com.jingdong.app.mall.home.r.c;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.home.floor.model.entity.BannerFloorEntity;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;

/* loaded from: classes4.dex */
public class c {
    String a;
    String b;

    /* renamed from: c */
    Object f10651c;
    private HashMap<String, String> d = new HashMap<>();

    /* renamed from: e */
    private ConcurrentHashMap<String, Integer> f10652e = new ConcurrentHashMap<>();

    /* renamed from: f */
    private ConcurrentHashMap<b, Integer> f10653f = new ConcurrentHashMap<>();

    /* renamed from: g */
    private ConcurrentHashMap<b, BannerFloorEntity.VariaModel> f10654g = new ConcurrentHashMap<>();

    public c(String str, String str2, Object obj) {
        this.a = str;
        this.b = str2;
        this.f10651c = obj;
    }

    public void a(String str) {
        if (str == null) {
            return;
        }
        Integer num = this.f10652e.get(str);
        if (num != null) {
            this.f10652e.put(str, Integer.valueOf(num.intValue() + 1));
        } else {
            this.f10652e.put(str, 1);
        }
    }

    public void b(b bVar) {
        if (bVar == null) {
            return;
        }
        Integer num = this.f10653f.get(bVar);
        if (num != null) {
            this.f10653f.put(bVar, Integer.valueOf(num.intValue() + 1));
        } else {
            this.f10653f.put(bVar, 1);
        }
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.d.put("extension_id", str);
    }

    public boolean d() {
        return this.f10652e.size() > 0 && this.f10653f.size() > 0;
    }

    public String e() {
        if (this.f10652e.size() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            for (Map.Entry<String, Integer> entry : this.f10652e.entrySet()) {
                f.n(entry);
                Map.Entry<String, Integer> entry2 = entry;
                sb.append((Object) entry2.getKey());
                sb.append(":");
                sb.append(entry2.getValue());
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("}");
            return sb.toString();
        }
        return "";
    }

    public HashMap<String, String> f() {
        return this.d;
    }

    public String g() {
        BannerFloorEntity.VariaModel variaModel;
        try {
            if (this.f10653f.size() > 0) {
                JSONArray d = b.d();
                for (Map.Entry<b, Integer> entry : this.f10653f.entrySet()) {
                    b key = entry.getKey();
                    key.put("frequency", String.valueOf(entry.getValue()));
                    ConcurrentHashMap<b, BannerFloorEntity.VariaModel> concurrentHashMap = this.f10654g;
                    if (concurrentHashMap != null && (variaModel = concurrentHashMap.get(key)) != null) {
                        key.put(CartConstant.KEY_LENGTH, Math.round(variaModel.displayRatio * 1000.0f) / 1000.0f);
                        key.put("ts", variaModel.allDisplayTime);
                    }
                    d.put(key);
                }
                return d.toString();
            }
            return "";
        } catch (Exception e2) {
            f.s0(this, e2);
            return "";
        }
    }

    public void h() {
        this.f10652e.clear();
        this.f10653f.clear();
        this.f10654g.clear();
    }

    public void i(ConcurrentHashMap<Integer, BannerFloorEntity.VariaModel> concurrentHashMap, CopyOnWriteArrayList<b> copyOnWriteArrayList) {
        if (concurrentHashMap == null || copyOnWriteArrayList == null) {
            return;
        }
        int size = concurrentHashMap.size();
        for (int i2 = 0; i2 < size; i2++) {
            BannerFloorEntity.VariaModel variaModel = concurrentHashMap.get(Integer.valueOf(i2));
            b bVar = copyOnWriteArrayList.get(i2);
            if (variaModel != null && bVar != null) {
                this.f10654g.put(bVar, variaModel);
            }
        }
    }
}
