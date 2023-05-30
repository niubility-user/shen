package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class JSIModuleRegistry {
    private final Map<Class, JSIModuleHolder> mModules = new HashMap();

    public <T extends JSIModule> T getModule(Class<T> cls) {
        JSIModuleHolder jSIModuleHolder = this.mModules.get(cls);
        if (jSIModuleHolder != null) {
            return (T) Assertions.assertNotNull(jSIModuleHolder.getJSIModule());
        }
        throw new IllegalArgumentException("Unable to find JSIModule for class " + cls);
    }

    public void notifyJSInstanceDestroy() {
        Iterator<JSIModuleHolder> it = this.mModules.values().iterator();
        while (it.hasNext()) {
            it.next().notifyJSInstanceDestroy();
        }
    }

    public void registerModules(List<JSIModuleSpec> list) {
        for (JSIModuleSpec jSIModuleSpec : list) {
            this.mModules.put(jSIModuleSpec.getJSIModuleClass(), new JSIModuleHolder(jSIModuleSpec));
        }
    }
}
