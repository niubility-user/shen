package com.jingdong.manto.m;

import org.json.JSONObject;

/* loaded from: classes15.dex */
public abstract class d0 extends c0 {
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void exec(e0 e0Var, JSONObject jSONObject, int i2, String str) {
        if (e0Var instanceof com.jingdong.manto.h) {
            exec((com.jingdong.manto.h) e0Var, jSONObject, i2, str);
        } else {
            exec((com.jingdong.manto.q.n) e0Var, jSONObject, i2, str);
        }
    }

    public void exec(com.jingdong.manto.q.n nVar, JSONObject jSONObject, int i2, String str) {
    }
}
