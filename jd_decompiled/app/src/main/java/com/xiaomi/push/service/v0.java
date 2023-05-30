package com.xiaomi.push.service;

import com.xiaomi.push.p7;
import java.util.Map;

/* loaded from: classes11.dex */
public class v0 {
    public static p7 a(p7 p7Var) {
        Map<String, String> map;
        if (p7Var != null && (map = p7Var.f198b) != null) {
            map.remove("score_info");
        }
        return p7Var;
    }
}
