package com.jingdong.common.face;

import android.text.TextUtils;
import com.google.gson.Gson;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class ApiCallback<T> {
    protected final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public Type getType() {
        return this.type;
    }

    public void onEnd(JSONObject jSONObject) {
        try {
            int i2 = jSONObject.getInt("rc");
            if (i2 != 200) {
                onFailure(new Exception("API error " + i2));
                return;
            }
            onResponse(parseRv(jSONObject.getString("rv")));
        } catch (Exception e2) {
            onFailure(e2);
        }
    }

    public abstract void onFailure(Exception exc);

    public abstract void onResponse(T t);

    public void onResponse(String str, JSONObject jSONObject) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T parseRv(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getType() == String.class ? str : (T) new Gson().fromJson(str, getType());
    }
}
