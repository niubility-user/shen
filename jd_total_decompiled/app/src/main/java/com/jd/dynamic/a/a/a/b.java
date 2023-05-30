package com.jd.dynamic.a.a.a;

import com.jd.dynamic.base.DynamicTemplateEngine;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class b implements h {
    public static final JSONObject b = new JSONObject();
    public DynamicTemplateEngine a;

    public b(DynamicTemplateEngine dynamicTemplateEngine) {
        this.a = dynamicTemplateEngine;
    }

    private void b(Object obj, Object obj2) {
        DynamicTemplateEngine dynamicTemplateEngine = this.a;
        if (dynamicTemplateEngine != null && dynamicTemplateEngine.getCachePool() != null && com.jd.dynamic.b.a.b.o().e()) {
            if (obj instanceof String) {
                this.a.getCachePool().putObjData((String) obj, obj2);
                return;
            }
            return;
        }
        DynamicTemplateEngine dynamicTemplateEngine2 = this.a;
        if (dynamicTemplateEngine2 == null || dynamicTemplateEngine2.getCachePool() == null || !(obj instanceof String) || obj2 == null) {
            return;
        }
        this.a.getCachePool().putData((String) obj, obj2.toString());
    }

    @Override // com.jd.dynamic.a.a.a.h
    public Object a(com.jd.dynamic.a.g gVar, String str, Object... objArr) {
        Map<String, String> dataCache;
        DynamicTemplateEngine dynamicTemplateEngine;
        if (objArr.length == 2) {
            b(objArr[0], objArr[1]);
            return b;
        } else if (objArr.length == 1 && com.jd.dynamic.b.a.b.o().e() && (dynamicTemplateEngine = this.a) != null && (objArr[0] instanceof String)) {
            return dynamicTemplateEngine.getCachePool().getDataObj((String) objArr[0]);
        } else {
            DynamicTemplateEngine dynamicTemplateEngine2 = this.a;
            return (dynamicTemplateEngine2 == null || dynamicTemplateEngine2.getCachePool() == null || (dataCache = this.a.getCachePool().getDataCache()) == null) ? b : new JSONObject(dataCache);
        }
    }

    @Override // com.jd.dynamic.a.a.a.h
    public String a() {
        return "cache";
    }
}
