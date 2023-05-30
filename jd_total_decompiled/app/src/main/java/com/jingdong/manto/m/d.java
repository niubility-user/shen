package com.jingdong.manto.m;

import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class d implements a {
    private com.jingdong.manto.f a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    public String f13315c;

    public final d a(com.jingdong.manto.f fVar, int i2) {
        this.a = fVar;
        this.b = i2;
        return this;
    }

    public final d a(e0 e0Var) {
        if (e0Var != null) {
            this.a = e0Var.h();
            this.b = e0Var.hashCode();
        }
        return this;
    }

    public final d a(String str) {
        this.f13315c = str;
        return this;
    }

    public final d a(Map<String, Object> map) {
        MantoUtils.mapToJson(map);
        this.f13315c = new JSONObject(map).toString();
        return this;
    }

    public final boolean a() {
        com.jingdong.manto.h hVar;
        try {
            com.jingdong.manto.f fVar = this.a;
            if (fVar == null || (hVar = fVar.f13015g) == null) {
                return true;
            }
            hVar.a(getJsApiName(), this.f13315c, this.b);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public final boolean a(int[] iArr) {
        com.jingdong.manto.q.l lVar;
        try {
            com.jingdong.manto.f fVar = this.a;
            if (fVar != null && (lVar = fVar.f13014f) != null) {
                lVar.a(getJsApiName(), this.f13315c, iArr);
                return true;
            }
            return false;
        } catch (Throwable th) {
            MantoLog.e("JsApiEvent", "", th);
            return false;
        }
    }
}
