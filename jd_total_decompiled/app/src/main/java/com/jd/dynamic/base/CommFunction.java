package com.jd.dynamic.base;

import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Keep;
import org.json.JSONObject;

@Keep
/* loaded from: classes13.dex */
public abstract class CommFunction {
    protected DynamicTemplateEngine mEngine;
    public View mTargetView;

    public void addCache(String str, String str2) {
        DynamicTemplateEngine dynamicTemplateEngine;
        if (TextUtils.isEmpty(str) || (dynamicTemplateEngine = this.mEngine) == null) {
            return;
        }
        dynamicTemplateEngine.getCachePool().putData(str, str2);
    }

    public void addObjCache(String str, Object obj) {
        DynamicTemplateEngine dynamicTemplateEngine;
        if (TextUtils.isEmpty(str) || (dynamicTemplateEngine = this.mEngine) == null) {
            return;
        }
        dynamicTemplateEngine.getCachePool().putObjData(str, obj);
    }

    public abstract Object exec(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject);

    public void execFunc(String... strArr) {
        if (this.mEngine == null) {
            return;
        }
        try {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(this.mEngine.getCachePool().getParam(str))) {
                    View view = this.mTargetView;
                    com.jd.dynamic.lib.utils.s.b(str, view, this.mEngine, view);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void execFuncWithLayoutId(int i2, String... strArr) {
        DynamicTemplateEngine dynamicTemplateEngine = this.mEngine;
        if (dynamicTemplateEngine == null) {
            return;
        }
        dynamicTemplateEngine.execEvent(i2, strArr);
    }

    public Object execInner(DynamicTemplateEngine dynamicTemplateEngine, JSONObject jSONObject) {
        if (dynamicTemplateEngine == null) {
            return null;
        }
        this.mEngine = dynamicTemplateEngine;
        return exec(dynamicTemplateEngine, jSONObject);
    }

    public boolean getConditionValue(JSONObject jSONObject) {
        return !jSONObject.has("condition") || jSONObject.optInt("condition", 1) == 1;
    }

    public View getPendingView(int i2) {
        return com.jd.dynamic.lib.utils.m.d(this.mEngine, i2, this.mTargetView);
    }

    public View getPendingView(int i2, View view) {
        return com.jd.dynamic.lib.utils.m.e(this.mEngine, i2, this.mTargetView, view);
    }
}
