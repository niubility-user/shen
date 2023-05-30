package com.jingdong.common.XView2.dynamic.context;

import java.util.HashMap;

/* loaded from: classes5.dex */
public interface IExpressContext {
    String getValueScript(Object obj);

    Object runScript(String str);

    void setVariables(HashMap<String, Object> hashMap);
}
